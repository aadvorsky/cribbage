/**
 * Created by advorsky on 11/13/16.
 */

public class GameState {

    final Card currentCard;
    final Player[] players;
    final int currentPlayer;

    public GameState(Card currentCard, Player[] players, int currentPlayer) {
      this.currentCard = currentCard;
      this.players = players;
      this.currentPlayer = currentPlayer;
    }

    public Card currentCard()
    {
      return currentCard;
    }

    public Player[] getPlayers()
    {
      return players;
    }
    public int getCurrentPlayer()
    {
      return currentPlayer;
    }
}
