package com.example.anastasia.cribbage;

public class Card {
  public static final int CLUBS = 0;
  public static final int DIAMONDS = 1;
  public static final int HEARTS = 2;
  public static final int SPADES  = 3;

  public static final int ACE = 1;
  public static final int JACK = 11;
  public static final int QUEEN = 12;
  public static final int KING = 13;

  public final int suit, rank;
  private boolean faceUp;

  public Card(int suit, int rank, boolean faceUp) {
    if (suit < 0 || suit > 3 || rank < 0 || rank > 13) {
      throw new IllegalArgumentException();
    }
    this.suit = suit;
    this.rank = rank;
    this.faceUp = faceUp;
  }

  public void flip(boolean faceUp) {
    this.faceUp = faceUp;
  }

  public boolean isFaceUp() {
    return faceUp;
  }
}
