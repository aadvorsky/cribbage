package com.example.anastasia.cribbage;


public class Player {
  private static String DELIM = "_";

	private Card[] hand;
	private int playerIndex;

  public Player(Card[] hand, int playerIndex) {
    this.hand = hand;
    state = new GameState();
    this.playerIndex = playerIndex;
  }

  public Player(String encodedHand){
    String[] parts = endCodedHand.split(DELIM);
    hand = new Card[Configuration.NUM_CARDS];
    int index = 0;
    for (int i = 0; i < hand.length; i++) {
      hand[i] = new Card(parts[index]);
      index++;
    }
    playerIndex = Integer.parseInt(parts[index]);
  }

  public int getIndex() {
    return playerIndex;
  }

  public boolean isTurn(GameState gameState)
  {
    return this == gameState.getCurrentPlayer();
  }

  public Card[] getHand()
  {
    return hand;
  }

  public String toString(Card[] arr)
  { 
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      sb.append(arr[i].toString());
      sb.append(DELIM);
    }
    sb.append(playerIndex);
	  return sb.toString();
  }
}
