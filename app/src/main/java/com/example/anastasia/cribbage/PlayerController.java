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

	
	public PlayerController(Player myself, GameState gameState, PlayerClient view)
	{
    this.myself = myself;
		this.gameState = gameState;
    this.view = view;
    view.setMyself(myself.getIndex());
    view.updateView(gameState);
    instance = this;
	}

  public static PlayerController getInstance() {
    return instance;
  }

	public boolean cardClicked(int cardIndex) 
	{
    if (!myself.isTurn(gameState)) {  // Can only take action if it is person's turn.
      return false;
    }
    if (gameState.getCardBeingHeld() == null) { // Must take card from face up card or stack.
      return false;
    }
    Card newCardBeingHeld = myself.getHand()[cardIndex];
    newCardBeingHeld.flip(true);
    myself.getHand()[cardIndex] = gameState.getCardBeingHeld();
    GameState newGameState = new GameState(gameState.getFaceUpCard(), gameState.getPlayers(),
        gameState.getStack(), myself.getIndex(), newCardBeingHeld);
    updateGameState(newGameState);
    return true;
	}
	
	public boolean faceUpCardClicked()
	{
    if (!myself.isTurn(gameState)) {  // Can only take action if it is person's turn.
      return false;
    }
    Card faceUpCard, cardBeingHeld;
    int nextPerson;
    if (gameState.getCardBeingHeld() == null) {  // Person is picking up card from stack.
      faceUpCard = null;
      cardBeingHeld = gameState.getFaceUpCard();
      nextPerson = myself.getIndex();
    } else {  // Person is putting down card and ending turn.
      nextPerson = (myself.getIndex() + 1) % Configuration.N;
      faceUpCard = gameState.getCardBeingHeld();
      cardBeingHeld = null;
    }
    GameState newGameState = new GameState(faceUpCard, gameState.getPlayers(), gameState.getStack(),
        nextPerson, cardBeingHeld);
    updateGameState(newGameState);
    return true;
	}
	
	public boolean stackClicked()
	{
    if (!myself.isTurn(gameState)) {  // Can only take action if it is person's turn.
      return false;
    }
    if (gameState.getCardBeingHeld() != null) {  // Cannot pickup card when holding one.
      return false;
    }
    Card cardBeingHeld = gameState.getStack().pop();
    GameState newGameState = new GameState(gameState.getFaceUpCard(), gameState.getPlayers(),
        gameState.getStack(), myself.getIndex(), cardBeingHeld);
    updateGameState(newGameState);
    return true;
	}

  private void updateGameState(GameState newGameState) {
    this.gameState = newGameState;
    view.updateView(gameState);
    SingletonSocket.writeLine(gameState.toString());
    while (!myself.isTurn(this.gameState)) {
      GameState gs = new GameState(SingletonSocket.readLine());
      this.gameState = newGameState;
      view.updateView(gameState);
    }
  }
}
