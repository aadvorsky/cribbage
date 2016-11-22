package com.example.anastasia.cribbage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by advorsky on 11/13/16.
 */

public class GameState {

    final Card currentCard;
    final Player[] players;
    final int currentPlayer;
    Stack stack;

    /**
     * Creates GameState for beginning of game.
     */
    public GameState() {
      Stack stack = new Stack();
      players = new Player[Configuration.N];
      for (int i = 0; i < players.length; i++) {
        Card[] hand = new Card[10];
        for (int j = 0; j < 10; j++) {
          hand[j] = stack.pop();
        }
        players[i] = new Player(hand);
      }
      currentCard = stack.pop();
      currentCard.flip(true);
      currentPlayer = 0;
    }

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
        int numofcard = parts.length/Configuration.N;
        Card[][] cards = new Card[numofcard][];
      Random rand = new Random(3);
      for(int i=0; i<parts.length; i++){
          int randint = rand.nextInt();
          Card cd = new Card(parts[i]);
          int index0 = 0;
          if(randint==0) {
              cards[randint][index0] = cd;
              index0++;
          }

      }
        /*
      players = new Player[Configuration.N];
      for (int i = 0; i < Configuration.N; i++) {

       players[i] = new Player(parts[i + 1]);
      }*/
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
      sb.append(currentCard.toString());
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
