package com.example.anastasia.cribbage.android;

import com.example.anastasia.cribbage.Card;

import android.content.Context;
import android.content.res.Resources;

public class CardUtil {
  private static final String CARD_BACK = "card_back";
  private static final String EXTENSION = ".png";
  private static final String ANDROID_TYPE = "drawable";

  private final Resources resources;
  private final String packageName = "com.example.anastasia.cribbage";
  private String[] prefixes;

  public CardUtil(Context context) {
    setupPrefixes();
    this.resources = context.getResources();
  }

  int getDrawableId(Card c) {
    String filename;
    if (c == null || !c.isFaceUp()) {
      filename = CARD_BACK;
    } else {
      filename = prefixes[c.getSuit()] + String.format("%02d", c.getRank());
    }
    int id = resources.getIdentifier(filename, ANDROID_TYPE, packageName);
    android.util.Log.e("SUSAN", filename);
    return id;
  }

  void setupPrefixes() {
    prefixes = new String[Card.NUM_SUITS];
    prefixes[Card.CLUBS] = "c";
    prefixes[Card.DIAMONDS] = "d";
    prefixes[Card.HEARTS] = "h";
    prefixes[Card.SPADES] = "s";
  }
}
