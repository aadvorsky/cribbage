package com.example.anastasia.cribbage;

import java.util.Scanner;

public class ConsoleMain implements PlayerClient {
  public static void main(String args[]) {
    ConsoleMain runner = new ConsoleMain();
    runner.play();
  }

  int me;
  GameState state;

  public void play() {
    System.out.print("Server IP: ");
    Scanner in = new Scanner(System.in);
    String ip = in.nextLine();
    System.out.print("Port number: ");
    int port = in.nextInt();
    if (!SingletonSocket.initialize(ip, port)) {
      System.exit(0);
    }

    String me = SingletonSocket.readLine();
    this.me = Integer.parseInt(me);

    System.out.println("You are player " + me);

    String stateSerialized = SingletonSocket.readLine();
    System.out.println(stateSerialized);
    state = new GameState(stateSerialized);

    PlayerController controller = new PlayerController(state.getPlayers()[this.me], state, this);

    while (true) {
      System.out.println("Press 0 - 9 to click on the respective card in your hand, 11 to take the "
          + "faceup card or 22 to take from the stack.");
      int choice = in.nextInt();
      boolean success;
      if (choice >= 0 && choice <= 9) {
        success = controller.cardClicked(choice);
      } else if (choice == 11) {
        success = controller.faceUpCardClicked();
      } else if (choice == 22) {
        success = controller.stackClicked();
      } else {
        success = false;
      }
      if (!success) {
        System.out.println("Invalid input.");
      }
    }
  }

  public void setMyself(int myIndex) {}

  public void updateView(GameState gameState) {
    System.out.println(gameState.toString());
    System.out.println();
  }
}
