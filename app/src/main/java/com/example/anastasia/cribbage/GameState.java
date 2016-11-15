package com.example.anastasia.cribbage;

/**
 * Created by advorsky on 11/13/16.
 */

public class GameState {

    final Card currentCard;
    final Player[] players;
    final int currentPlayer;
    final Stack stack;

    public GameState(Card currentCard, Player[] players, Stack stack, int currentPlayer) {
      this.currentCard = currentCard;
      this.players = players;
      this.currentPlayer = currentPlayer;
      this.stack = stack;
    }

    public GameState(String s) {
      String[] parts = s.split(Configuration.GAME_STATE_DELIM);
      currentCard = new Card(parts[0]);
      players = new Player[Configuration.N];
      for (int i = 0; i < Configuration.N; i++) {
        players[i] = new Player(parts[i + 1);
      }
      stack = new Stack(parts[Configuration.N + 1]);
      currentPlayer = Integer.parseInt(parts[Configuration.N + 2]);
    }

    public Card currentCard()
    {
      return currentCard;
    }

    public Player[] getPlayers()
    {
      return players;
    }
    public int getCurrentPlayer()
    {
      return currentPlayer;
    }

    public Stack getStack() {
      return stack;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(currentCard.toString())
      sb.append(Configuration.GAME_STATE_DELIM);
      for (int i = 0; i < players.length; i++) {
        sb.append(players[i].toString());
        sb.append(Configuration.GAME_STATE_DELIM);
      }
      sb.append(stack.toString());
      sb.append(Configuration.GAME_STATE_DELIM);
      sb.append(currentPlayer);
      return sb.toString();
    }
}
