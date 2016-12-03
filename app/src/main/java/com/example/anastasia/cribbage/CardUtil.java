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
    return prefixes[c.getSuit())] + String.format("%02d", c.getRank()) + EXTENSION;
  }

  static vod setupPrefixes() {
    prefixes = new String[Card.NUM_SUITS];
    prefixes[CLUBS] = "c";
    prefixes[DIAMONDS] = "d";
    prefixes[HEARTS] = "h";
    prefixes[SPADES] = "s";
  }
}
