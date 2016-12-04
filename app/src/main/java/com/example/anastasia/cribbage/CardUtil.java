package com.example.anastasia.cribbage;

import android.content.Context;
import android.content.res.Resources;

public class CardUtil {
  private static final String CARD_BACK = "card_back.png";
  private static final String EXTENSION = ".png";
  private static final String ANDROID_TYPE = "drawable";

  private final Resources resources;
  private final String packageName;
  private String[] prefixes;

  public CardUtil(Context context) {
    setupPrefixes();
    this.resources = context.getResources();
    this.packageName = context.getPackageName();
  }

  int getDrawableId(Card c) {
    String filename;
    if (!c.isFaceUp()) {
      filename = CARD_BACK;
    } else {
      filename = prefixes[c.getSuit()] + String.format("%02d", c.getRank()) + EXTENSION;
    }
    return resources.getIdentifier(filename, ANDROID_TYPE, packageName);
  }

  void setupPrefixes() {
    prefixes = new String[Card.NUM_SUITS];
    prefixes[Card.CLUBS] = "c";
    prefixes[Card.DIAMONDS] = "d";
    prefixes[Card.HEARTS] = "h";
    prefixes[Card.SPADES] = "s";
  }
}
