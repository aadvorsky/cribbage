package com.example.anastasia.cribbage.android;

import com.example.anastasia.cribbage.Configuration;
import com.example.anastasia.cribbage.GameState;
import com.example.anastasia.cribbage.Player;
import com.example.anastasia.cribbage.PlayerClient;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.util.AttributeSet;

public class MainView extends LinearLayout implements PlayerClient {
  private HandView leftHand;
  private HandView myHand;
  private HandView rightHand;
  private final Context context;
  private int myIndex;

  public MainView(Context context) {
    this(context, null);
  }

  public MainView(Context context, AttributeSet attrs) {
    this(context, null, 0);
  }

  public MainView(Context context, AttributeSet attrs, int defStyleAttrs) {
    super(context, attrs, defStyleAttrs);
    this.context = context;
  }

  public void setMyself(int myIndex) {
    this.myIndex = myIndex; 
  }

  @Override
  public void onFinishInflate() {
    super.onFinishInflate();
    leftHand = (HandView) findViewById(R.id.left_hand);
    myHand = (HandView) findViewById(R.id.my_hand);
    rightHand = (HandView) findViewById(R.id.right_hand);
  }

  public void updateView(GameState gameState) {
    leftHand.updateView(getLeftPlayer(gameState));
    myHand.updateView(getMyPlayer(gameState));
    rightHand.updateView(getRightPlayer(gameState));
    // TODO - update current card, stack, and faceup card too.
  }

  private Player getLeftPlayer(GameState state) {
    int leftIndex = (myIndex - 1 + Configuration.N) % Configuration.N;
    return state.getPlayers()[leftIndex];
  }

  private Player getRightPlayer(GameState state) {
    int rightIndex = (myIndex + 1) % Configuration.N;
    return state.getPlayers()[rightIndex];
  }

  private Player getMyPlayer(GameState state) {
    return state.getPlayers()[myIndex];
  }
}
