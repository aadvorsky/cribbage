package com.example.anastasia.cribbage;


public class Player {

    private Card[] hand;
    private String stringHand;

    public Player(Card[] hand) {
        this.hand = hand;
        stringHand = toString(hand);
    }

    public Player(String hand){
        stringHand = hand;

    }
    public void startTurn() {
        
    }

    public boolean isTurn()
    {
        Player isTurn = GameManager.returnPlayer();

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
