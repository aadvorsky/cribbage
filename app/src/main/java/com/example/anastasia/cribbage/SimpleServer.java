package com.example.anastasia.cribbage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Scanner;

public class SimpleServer {
  static private int port;
  static int nextUserId = 0;
  static int nextGroup = 0;
  static LinkedList<Integer> waitingUsers = new LinkedList<>();
  static Object groupLock = new Object();
  static private final HashMap<Integer, Integer> groups = new HashMap<>();
  static private final HashMap<Integer, Integer[]> groupsReversed = new HashMap<>();
  static private final ConcurrentHashMap<Integer, GameState> gameStates = new ConcurrentHashMap<>();

  public static void main(String[] args) throws IOException {
    if (args.length == 1) {
      port = Integer.parseInt(args[0]);
    } else {
      port = 8000;
      System.out.println("Invalid arguments, setting port to 8000.");
    }
    ServerSocket serverSocket = new ServerSocket(port);
    while (true) {
      final Socket clientSocket = serverSocket.accept();
      Runnable clientHandler = new ClientHandler(clientSocket);
      Thread clientHandlerThread = new Thread(clientHandler);
    }
  }

  static class ClientHandler implements Runnable {
    final Socket socket;
    final int userId;
    final Scanner scanner;
    final PrintWriter writer;

    public ClientHandler(final Socket socket) throws IOException {
      this.socket = socket;
      userId = nextUserId;
      nextUserId++;
      synchronized(groupLock) {
        waitingUsers.add(userId);
      }

      scanner = new Scanner(socket.getInputStream());
      writer = new PrintWriter(socket.getOutputStream());
    }

    public void run() {
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
        gameStates.put(group, gameState);
      }
      while (!gameStates.containsKey(group)) {}
      GameState gameState = gameStates.get(group);
      write(gameState.toString());
      while (true) {
        if (scanner.hasNextLine()) {
          gameState = new GameState(scanner.nextLine());
          gameStates.put(group, gameState);
        }
        if (gameStates.get(group) != gameState) {
          write(gameStates.get(group).toString());
        }
      }
    }

    void write(String s) {
      writer.println(s);
    }
  }

}
