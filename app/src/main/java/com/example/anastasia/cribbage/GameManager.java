package com.example.anastasia.cribbage;


import java.util.Arrays;


public class GameManager {

    private Player[] playerArray;
    private static Player currPlayer;

    public GameManager()
    {
    	
    }
    public void initalize()
    {
        while(playerArray.length!=3)
        {
        	//wait until there are 3 players
        }
        currPlayer = playerArray[0];
        //start game with current player being player 1(index 0)
        
    }
    public Player[] playerArray()
    {
        return playerArray;
    }
    public static Player returnPlayer()
    {
        return currPlayer;
    }
    

    public void nextPlayerTurn()

    {
        int playerIndex = (Arrays.asList(playerArray).indexOf(currPlayer)+1)%3;
        currPlayer = playerArray[playerIndex];
    }
}
