package com.example.anastasia.cribbage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class SimpleServer {
  static private int port;
  static int nextUserId = 0;
  static int nextGroup = 0;
  static LinkedList<Integer> waitingUsers = new LinkedList<>();
  static Object groupLock = new Object();
  static private final HashMap<Integer, Integer> groups = new HashMap<>();
  static private final HashMap<Integer, Integer[]> groupsReversed = new HashMap<>();
  static Object stateLock = new Object();
  static private HashMap<Integer, String> gameStates;

  public static void main(String[] args) throws IOException {
    if (args.length == 1) {
      port = Integer.parseInt(args[0]);
    } else {
      port = 8000;
      System.out.println("Invalid arguments, setting port to 8000.");
    }
    synchronized(stateLock) {
      gameStates = new HashMap<>();
    }
    ServerSocket serverSocket = new ServerSocket(port);
    while (true) {
      final Socket clientSocket = serverSocket.accept();
      Runnable clientHandler = new ClientHandler(clientSocket);
      Thread clientHandlerThread = new Thread(clientHandler);
      clientHandlerThread.start();
    }
  }

  static class ClientHandler implements Runnable {
    final Socket socket;
    final int userId;
    final BufferedReader scanner;
    final PrintWriter writer;

    public ClientHandler(final Socket socket) throws IOException {
      this.socket = socket;
      userId = nextUserId;
      nextUserId++;
      synchronized(groupLock) {
        waitingUsers.add(userId);
      }

      scanner = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      writer = new PrintWriter(socket.getOutputStream());
    }

    public void run() {
    	try {
      boolean groupFound = false;
      boolean master = false;
      while (!groupFound) {
        synchronized(groupLock) {
          if (groups.containsKey(userId)) {
            groupFound = true;
            break;
          } else if (waitingUsers.size() >= Configuration.N) {
            Integer[] users = new Integer[Configuration.N];
            for (int i = 0; i < Configuration.N; i++) {
              Integer user = waitingUsers.remove();
              users[i] = user;
              groups.put(user, nextGroup);
              if (user == userId) {
                master = true;
              }
            }
            groupsReversed.put(nextGroup, users);
            nextGroup++;
          }
        }
      }

      int group = -1;
      int internalId = -1;
      synchronized (groupLock) {
        group = groups.get(userId);
        Integer[] usersInGroup = groupsReversed.get(group);
        for (int i = 0; i < Configuration.N; i++) {
          if (usersInGroup[i] == userId) {
            internalId = i;
          }
        }
      }
      write(internalId + "");
      if (master) {
        GameState gameState = new GameState();
        synchronized (stateLock) {
          gameStates.put(group, gameState.toString());
        }
      }
      boolean found = false;
      while (!found) {
        synchronized(stateLock) {
          found = gameStates.containsKey(group);
        }
      }
      String gameState;
      synchronized(stateLock) {
        gameState = gameStates.get(group);
      }
      write(gameState.toString());
      while (true) {
        if (scanner.ready()) {
          //System.out.println("Updating server game state."+ userId);
          String gs = scanner.readLine();
          gameState = gs;
          synchronized(stateLock) {
            gameStates.put(group, gs);
          }
          continue;
        }
        String curGameState;
        synchronized(stateLock) {
          curGameState = gameStates.get(group);
        }
        if (curGameState != gameState){
          //System.out.println("Updating server game state 2."+ userId);
          gameState = curGameState;
          write(curGameState.toString());
        }
        try {
          Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {}
      }
    	} catch (IOException e) {
    		System.out.println("IO Exception for user " + userId + ": " + e.getMessage());
    	}
    }

    void write(String s) {
      //System.out.println(userId + " " + s);
      writer.println(s);
      writer.flush();
    }
  }

}
