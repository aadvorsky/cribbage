package com.example.anastasia.cribbage;


import java.util.Arrays;

/**
 * Created by advorsky on 11/13/16.
 */

public class GameManager {
    //initialize()
        // wait for player ID -- reference player array
    //nextplayerturn()
    //array player
    private Player[] playerArray;
    private Player currPlayer;

    public GameManager()
    {

    }
    public void initalize()
    {
        while(playerArray.length!=2)
        {

        }
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
        //int playerIndex = (playerArray.indexOf(currPlayer)+1)%2;
        //currPlayer = playerArray[playerIndex];
    }
}
