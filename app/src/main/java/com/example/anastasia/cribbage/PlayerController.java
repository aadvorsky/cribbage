package com.example.anastasia.cribbage;

import com.example.anastasia.cribbage.Card;
import com.example.anastasia.cribbage.Configuration;
import com.example.anastasia.cribbage.GameState;
import com.example.anastasia.cribbage.PlayerClient;
import com.example.anastasia.cribbage.Player;

public class PlayerController {
  private static PlayerController instance;

  private Player myself;
  private GameState gameState;
  private PlayerClient view;


  public PlayerController(Player myself, GameState gameState, PlayerClient view) {
    this.myself = myself;
    this.gameState = gameState;
    this.view = view;
    view.setMyself(myself.getIndex());
    view.updateView(gameState);
    instance = this;
    if (!myself.isTurn(gameState)) {
      view.waitForOthersAsync();
    }
  }

  public static PlayerController getInstance() {
    return instance;
  }

  public boolean cardClicked(int cardIndex) {
    if (!myself.isTurn(gameState)) {  // Can only take action if it is person's turn.
      return false;
    }
    if (gameState.getCardBeingHeld() == null) { // Must take card from face up card or stack.
      return false;
    }
    Card newCardBeingHeld = myself.getHand()[cardIndex];
    newCardBeingHeld.flip(true);
    myself.getHand()[cardIndex] = gameState.getCardBeingHeld();
    System.out.println(java.util.Arrays.toString(myself.getHand()));
    myself.getHand()[cardIndex].flip(true);

    Player[] players = new Player[3];
    players[myself.getIndex()] = myself;
    int leftIndex = (myself.getIndex() - 1 + Configuration.N) % Configuration.N;
    players[leftIndex] = gameState.getPlayers()[leftIndex];
    int rightIndex = (myself.getIndex() + 1) % Configuration.N;
    players[rightIndex] = gameState.getPlayers()[rightIndex];
    GameState newGameState = new GameState(gameState.getFaceUpCard(), players, gameState.getStack(), myself.getIndex(), newCardBeingHeld);
    updateGameState(newGameState);
    return true;
  }

  public boolean cardBeingHeldClicked() {
    if (!myself.isTurn(gameState) || gameState.getCardBeingHeld() == null) {  // Can only take action if it is person's turn.
      return false;
    }
    Card faceUpCard = gameState.getCardBeingHeld();
    faceUpCard.flip(true);
    Card cardBeingHeld = null;
    int nextPerson;
    nextPerson = (myself.getIndex() + 1) % Configuration.N;
    GameState newGameState = new GameState(faceUpCard, gameState.getPlayers(), gameState.getStack(),
            nextPerson, cardBeingHeld);
    updateGameState(newGameState);
    return true;
  }

  public boolean faceUpCardClicked() {
    if (!myself.isTurn(gameState)) {  // Can only take action if it is person's turn.
      return false;
    }
    Card faceUpCard, cardBeingHeld;
    int nextPerson;
    if (gameState.getCardBeingHeld() == null) {  // Person is picking up card from stack.
      faceUpCard = null;
      cardBeingHeld = gameState.getFaceUpCard();
      cardBeingHeld.flip(true);
      nextPerson = myself.getIndex();
    } else {  // Person is putting down card and ending turn.
      nextPerson = (myself.getIndex() + 1) % Configuration.N;
      faceUpCard = gameState.getCardBeingHeld();
      faceUpCard.flip(true);
      cardBeingHeld = null;
    }
    GameState newGameState = new GameState(faceUpCard, gameState.getPlayers(), gameState.getStack(),
            nextPerson, cardBeingHeld);
    updateGameState(newGameState);
    return true;
  }

  public boolean stackClicked() {
    if (!myself.isTurn(gameState)) {  // Can only take action if it is person's turn.
      return false;
    }
    if (gameState.getCardBeingHeld() != null) {  // Cannot pickup card when holding one.
      return false;
    }
    Card cardBeingHeld = gameState.getStack().pop();
    cardBeingHeld.flip(true);
    GameState newGameState = new GameState(gameState.getFaceUpCard(), gameState.getPlayers(),
            gameState.getStack(), myself.getIndex(), cardBeingHeld);
    updateGameState(newGameState);
    return true;
  }

  public GameState getGameState() {
    return this.gameState;
  }

  public void updateGameState(GameState newGameState) {
    if (myself.isTurn(this.gameState) && this.gameState != newGameState) {
      SingletonSocket.writeLine(newGameState.toString());
    }
    this.gameState = newGameState;
    view.updateView(gameState);
    if (!myself.isTurn(this.gameState)) {
      view.waitForOthersAsync();
    }
  }

  public GameState waitForGameState() {
    return new GameState(SingletonSocket.readLine());
  }
}
