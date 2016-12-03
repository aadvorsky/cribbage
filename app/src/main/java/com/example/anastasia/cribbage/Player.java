package com.example.anastasia.cribbage;


public class Player {

    private Card[] hand;

    public Player(Card[] hand) {
        this.hand = hand;
    }
    public Player(String[] part){
      // TODO(smaslo)
    }

    public Player(String hand){
      // TODO(smaslo)

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
