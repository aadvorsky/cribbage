package com.example.anastasia.cribbage;

public class PlayerController {
	
	private Player player;
	private GameState gameState;
	//initial game state, int or player reference
	
	public PlayerController(Player player, GameState gameState)
	{
		this.player = player;
		this.gameState = gameState;
	}


	public boolean cardClicked()
	{
		
		if (player.isTurn()) //if not my turn return false
		{
			//gameState.currentCard = null; //
			return true;
		}
		//if true return updatedView
		
		//if false return false
		
		
		
		
		return false;
	}
	
	public boolean faceUpCardClicked()
	{
		//if true return updatedView
		
		//if false return false
		
		
		//if not my turn retun false
		
		return false;
	}
	
	public boolean stackClicked()
	{
		//if true return updatedView
		
		//if false return false
		
		
		//if not my turn retun false
		
		return false;
		
	}
	public boolean myCardClicked(int cardIndex)
	{
		return false; 
	}
}
