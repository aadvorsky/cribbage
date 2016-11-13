package com.example.anastasia.cribbage;

public class SimpleServer {
  private final int port;
  static int nextUserId = 0;
  static int nextGroup = 0;
  static LinkedList<Integer> waitingUsers = new LinkedList<>();
  public static final int N = 3;
  static Object groupLock = new Object();
  private final HashMap<Integer, Integer> groups = new HashMap<>();
  private final HashMap<Integer, Integer[]> groupsReversed = new HashMap<>();
  private final ConcurrentHashMap<Integer, GameState> gameStates = new ConcurrentHashMap<>();

  public static void main(String[] args) throws IOException {
    if (args.length == 1) {
      port = Integer.parseInt(args[0]);
    else {
      port = 8000;
      System.out.println("Invalid arguments, setting port to 8000.");
    }
    ServerSocket serverSocket = new ServerSocket(portNumber);
    while (true) {
      final Socket clientSocket = serverSocket.accept();
      Runnable clientHandler = new ClientHandler(clientSocket);
      Thread clientHandlerThread = new Thread(clientHandler);
    }
  }

  static class ClientHandler extends Runnable {
    final Socket socket;
    final int userId;

    public ClientHandler(final Socket socket) {
      this.socket = socket;
      userId = nextUserId;
      nextUserId++;
      synchronized(groupAssigmentLock) {
        waitingUsers.add(userId);
      }
    }

    public void run() {
      boolean groupFound = false;
      boolean master = false;
      while (!groupFound) {
        synchronized(groupLock) {
          if (groups.containsKey(userId)) {
            groupFound = true;
            break;
          } else if (waitingUsers.size() >= N) {
            users = new Integer[N];
            for (int i = 0; i < N; i++) {
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
        Integer[] users = users.get(group);
        for (int i = 0; i < N; i++) {
          if (users[i] == userId) {
            internalId = i;
          }
        }
      }
      socket.write(internalId);
      if (master) {
        GameState gameState = new GameState();
        gameStates.put(group, gameState);
      }
      while (!gameStates.containsKey(group)) {}
      GameState gameState = gameStates.get(group);
      socket.write(gameState);
      while (true) {
        if (scanner.hasNextLine()) {
          gameState = new GameState(scanner.nextLine());
          gamesStates.put(group, gameState);
        }
        if (gameStates.get(group) != gameState) {
          socket.write(gameStates.get(group));
        }
      }
    }

  }
}
