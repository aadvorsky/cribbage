package com.example.anastasia.cribbage;
import java.util.Arrays;


public class GameManager {
    private Player[] playerArray;
    private Player currPlayer;

    public GameManager()
    {

    }
    public void initalize()
    {
        while(playerArray.length!=2)
        {
            //wait until there are two players
        }
        currPlayer = playerArray[0];
        //start game with curent player being player 1

    }
    public Player[] playerArray()
    {
        return playerArray;
    }
    public Player returnPlayer()
    {
        return currPlayer;
    }

    public void nextPlayerTurn()

    {
        int playerIndex = (Arrays.asList(playerArray).indexOf(currPlayer)+1)%2;
        currPlayer = playerArray[playerIndex];

    }
}
