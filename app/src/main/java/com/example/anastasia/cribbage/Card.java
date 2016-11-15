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

  public static final String[] suitEncodings = {"C", "D", "H", "S"};

  public Card(int suit, int rank, boolean faceUp) {
    if (suit < 0 || suit > 3 || rank < 0 || rank > 13) {
      throw new IllegalArgumentException();
    }
    this.suit = suit;
    this.rank = rank;
    this.faceUp = faceUp;
  }

  public Card(String s) {
    faceUp = s.charAt(0) == '1';
    String suitString  = s.subString(1, 2);
    int suit = -1;
    for (int i = 0; i < suitEncodings.length; i++) {
      if (suitEncodings[i].equals(suitString)) {
        suit = i;
        break;
      }
    }
    if (suit == -1) {
      throw new IllegalArgumentException("Invalid string for suit.");
    }
    rank = Integer.parseInt(s.substring(2, s.length()));
  }

  public void flip(boolean faceUp) {
    this.faceUp = faceUp;
  }

  public boolean isFaceUp() {
    return faceUp;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(faceUp ? 1 : 0);
    sb.append(suitEncodings[suit]);
    sb.append(rank);
  }
}
