package com.example.anastasia.cribbage;

import java.util.ArrayList;
import java.util.Random;

public class GameState {
  private final String NULL_KEYWORD = "null";

  final Card faceUpCard;
  Player[] players;
  Stack stack;
  final int currentPlayer;
  final Card cardBeingHeld;

  /**
   * Creates GameState for beginning of game.
   */
  public GameState() {
    stack = new Stack();
    players = new Player[Configuration.N];
    for (int i = 0; i < players.length; i++) {
      Card[] hand = new Card[10];
      for (int j = 0; j < 10; j++) {
        hand[j] = stack.pop();
      }
      players[i] = new Player(hand);
    }
    faceUpCard = stack.pop();
    faceUpCard.flip(true);
    currentPlayer = 0;
    cardBeingHeld = null;
  }

  public GameState(Card faceUpCard, Player[] players, Stack stack, int currentPlayer, Card cardBeingHeld) {
    this.faceUpCard = faceUpCard;
    this.players = players;
    this.currentPlayer = currentPlayer;
    this.stack = stack;
    this.cardBeingHeld = cardBeingHeld;
  }

  public GameState(String s) {
    String[] parts = s.split(Configuration.GAME_STATE_DELIM);
    int index = 0;
    faceUpCard = new Card(parts[index]);
    index++;
    
    if (parts[index].equals(NULL_KEYWORD)) {
      cardBeingHeld = null;
    } else {
      cardBeingHeld = new Card(parts[index]);
    }
    index++;

    players = new Player[Configuration.N];
    for (int i = 0; i < Configuration.N; i++) {
      players[i] = new Player(parts[index]);
      index++;
    }

    stack = new Stack(parts[index]);
    index++;
    currentPlayer = Integer.parseInt(parts[index]);
  }

  public Card getFaceUpCard()
  {
    return faceUpCard;
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

  public Card getCardBeingHeld() {
    return cardBeingHeld;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(faceUpCard.toString());
    sb.append(Configuration.GAME_STATE_DELIM);

    if (cardBeingHeld == null) {
      sb.append(NULL_KEYWORD);
    } else {
      sb.append(cardBeingHeld.toString());
    }
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
