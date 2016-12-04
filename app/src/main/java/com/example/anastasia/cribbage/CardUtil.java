package com.example.anastasia.cribbage;

public class CardUtil {
  private static String[] prefixes;
  private static final String CARD_BACK = "card_back.png";
  private static final String EXTENSION = ".png";

  static String getDrawableName(Card c) {
    if (!c.isFaceUp()) {
      return CARD_BACK;
    }
    if (prefixes == null) {
      setupPrefixes();
    }
    return prefixes[c.getSuit()] + String.format("%02d", c.getRank()) + EXTENSION;
  }

  static void setupPrefixes() {
    prefixes = new String[Card.NUM_SUITS];
    prefixes[Card.CLUBS] = "c";
    prefixes[Card.DIAMONDS] = "d";
    prefixes[Card.HEARTS] = "h";
    prefixes[Card.SPADES] = "s";
  }
}
