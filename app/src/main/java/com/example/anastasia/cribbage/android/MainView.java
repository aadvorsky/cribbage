package com.example.anastasia.cribbage.android;

import com.example.anastasia.cribbage.Configuration;
import com.example.anastasia.cribbage.GameState;
import com.example.anastasia.cribbage.Player;
import com.example.anastasia.cribbage.PlayerClient;
import com.example.anastasia.cribbage.PlayerController;
import com.example.anastasia.cribbage.SingletonSocket;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.util.AttributeSet;

import android.util.Log;

public class MainView extends LinearLayout implements PlayerClient {
  private HandView leftHand;
  private HandView myHand;
  private HandView rightHand;
  private ImageView faceUpCardImage;
  private ImageView stackImage;
  private ImageView cardBeingHeldImage;
  private Context context;
  private  CardUtil cardUtil;
  private int myIndex;

  public MainView(Context context) {
    super(context);
    setup(context);
  }

  public MainView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setup(context);
  }

  public MainView(Context context, AttributeSet attrs, int defStyleAttrs) {
    super(context, attrs, defStyleAttrs);
    setup(context);
  }

  private void setup(Context context) {
    this.context = context;
    this.cardUtil = new CardUtil(context);
  }

  public void setMyself(int myIndex) {
    Log.e("SUSAN", "Set myself");
    this.myIndex = myIndex; 
  }

  @Override
  public void onFinishInflate() {
    super.onFinishInflate();
    leftHand = (HandView) findViewById(R.id.left_hand);
    myHand = (HandView) findViewById(R.id.my_hand);
    myHand.setIsMe(true);
    rightHand = (HandView) findViewById(R.id.right_hand);

    faceUpCardImage = (ImageView) findViewById(R.id.face_up_card);
    faceUpCardImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PlayerController.getInstance().faceUpCardClicked();
      }
    });

    stackImage = (ImageView) findViewById(R.id.stack);
    stackImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PlayerController.getInstance().stackClicked();
      }
    });

    cardBeingHeldImage = (ImageView) findViewById(R.id.card_being_held);
    cardBeingHeldImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PlayerController.getInstance().cardBeingHeldClicked();
      }
    });
  }

  public void updateView(GameState gameState) {
    leftHand.updateView(getLeftPlayer(gameState));
    myHand.updateView(getMyPlayer(gameState));
    rightHand.updateView(getRightPlayer(gameState));
    if (gameState.getFaceUpCard() == null) {
      faceUpCardImage.setVisibility(INVISIBLE);
    } else {
      faceUpCardImage.setVisibility(VISIBLE);
      faceUpCardImage.setImageDrawable(cardUtil.getDrawable(gameState.getFaceUpCard()));
    }

    if (gameState.getCardBeingHeld() == null) {
      cardBeingHeldImage.setVisibility(INVISIBLE);
    } else {
      cardBeingHeldImage.setVisibility(VISIBLE);
      cardBeingHeldImage.setImageDrawable(cardUtil.getDrawable(gameState.getCardBeingHeld()));
    }

    stackImage.setImageDrawable(cardUtil.getDrawable(null));
    requestLayout();
    invalidate();
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

  public void waitForOthersAsync() {
    AsyncTask<Integer, Integer, GameState> task = new AsyncTask<Integer, Integer, GameState>() {
      @Override
      protected GameState doInBackground(Integer... params) {
        return PlayerController.getInstance().waitForGameState();
      }

      @Override
      protected void onPostExecute(GameState r) {
        PlayerController.getInstance().updateGameState(r);
      }

    };
    task.execute();
  }
}
