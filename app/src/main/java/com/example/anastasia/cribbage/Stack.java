package com.example.anastasia.cribbage;

import java.util.Random;
/**
 * Created by advorsky on 11/13/16.
 */

public class Stack {
    Card[] stack;
    int top;

    public Stack()
    {
      int numberOfDecks = Configuration.NUMBER_OF_DECKS;
      stack = new Card[numberOfDecks * 52];
      for (int i = 0; i < numberOfDecks; i++) {
        Card[] deck = Card.createDeck();
        for (int j = 0; j < deck.length; j++) {
          stack[i * 52 + j] = deck[j];
        }
      }
      top = stack.length - 1;
    }

    public Stack(String s) {
      String[] cards = s.split(" ");
      stack = new Card[Configuration.NUMBER_OF_DECKS * 52];
      top = cards.length - 1;
      for (int i = 0; i < cards.length; i++) {
        stack[i] = new Card(cards[i]);
      }
    }

    private void shuffle()
    {
      Random r = new Random();
      for (int i = 0; i < stack.length; i++) {
        int random = r.nextInt(stack.length - i);
        swap(i, random);
      }
    }

    public Card pop()
    {
      if (top < 0) {
        throw new IllegalStateException("Cannot pop from empty stack.");
      }
      return stack[top--];
    }

    public boolean isEmpty() {
      return top >= 0;
    }

    private void swap(int a, int b) {
      Card tmp = stack[a];
      stack[a] = stack[b];
      stack[b] = tmp;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i <= top; i++) {
        sb.append(stack[i].toString());
        sb.append(" ");
      }
      return sb.toString().trim();
    }
}
