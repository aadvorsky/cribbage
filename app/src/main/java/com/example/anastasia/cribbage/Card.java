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
    String suitString  = s.substring(1, 2);
    int tmpSuit = -1;
    for (int i = 0; i < suitEncodings.length; i++) {
      if (suitEncodings[i].equals(suitString)) {
        tmpSuit = i;
        break;
      }
    }
    if (tmpSuit == -1) {
      throw new IllegalArgumentException("Invalid string for suit.");
    }
    suit = tmpSuit;
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
    return sb.toString();
  }

  /**
   * Returns standard 52-card deck.
   */
  public static Card[] createDeck() {
    Card[] deck = new Card[52];
    for (int i = 0; i < 4; i++) {
      for (int j = 1; j <= 13; j++) {
        deck[i * 13 + j - 1] = new Card(i, j, false);
      }
    }
    return deck;
  }

}
