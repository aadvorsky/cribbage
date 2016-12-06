package com.example.anastasia.cribbage;

public interface PlayerClient {


  public void setMyself(int myIndex);

  public void updateView(GameState gameState);

  public void waitForOthersAsync();
}
