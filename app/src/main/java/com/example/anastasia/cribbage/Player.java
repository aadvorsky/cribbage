package com.example.anastasia.cribbage;


public class Player {

	private Card[] hand;
	private String stringHand;
	private Player player;
	private GameManager game;
	private GameState state;
	private PlayerController control;
	private int index;
	

    public Player(Card[] hand) {
      this.hand = hand;
      stringHand = toString(hand);
      game = new GameManager();
      state = new GameState();
      
    }

    public Player(String hand){
    	stringHand = hand;
    	
    }
    public void startTurn() {
    	if (isTurn()==true)
    	{
    		
    		
    		game.nextPlayerTurn();
    	}
    }

    public boolean isTurn()
    {
    	Player playerTurn = GameManager.returnPlayer();
    	
    	if(playerTurn.equals(state.getCurrentPlayer()))
    		return true;
        return false;
    }

    public Card[] hand()
    {
      return hand;
    }
    public String toString(Card[] arr)
    { 
    	String returnString = null;
    	for(Card element : arr)
    	{
    		returnString = element+"_";
    	}
		return returnString;
    	
    }
}
