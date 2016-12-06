package com.example.anastasia.cribbage.android;

import com.example.anastasia.cribbage.Card;
import com.example.anastasia.cribbage.Configuration;
import com.example.anastasia.cribbage.Player;
import com.example.anastasia.cribbage.PlayerController;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.util.AttributeSet;

public class HandView extends LinearLayout {
  private ImageView[] cardViews;
  private boolean me = false;
  private CardUtil cardUtil;
  private Context context;

  public HandView(Context context) {
    super(context);
    setup(context);
  }

  public HandView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setup(context);
  }

  public HandView(Context context, AttributeSet attrs, int defStyleAttrs) {
    super(context, attrs, defStyleAttrs);
    setup(context);
  }

  private void setup(Context context) {
    this.context = context;
    this.cardUtil = new CardUtil(context);
  }

  public void setIsMe(boolean isMe) {
    this.me = isMe;
    if (me && cardViews != null) {
      addOnClickListeners();
    }
  }

  @Override
  public void onFinishInflate() {
    super.onFinishInflate();
    cardViews = new ImageView[Configuration.HAND_SIZE];
    cardViews[0] = (ImageView) findViewById(R.id.card0);
    cardViews[1] = (ImageView) findViewById(R.id.card1);
    cardViews[2] = (ImageView) findViewById(R.id.card2);
    cardViews[3] = (ImageView) findViewById(R.id.card3);
    cardViews[4] = (ImageView) findViewById(R.id.card4);
    cardViews[5] = (ImageView) findViewById(R.id.card5);
    cardViews[6] = (ImageView) findViewById(R.id.card6);
    cardViews[7] = (ImageView) findViewById(R.id.card7);
    cardViews[7] = (ImageView) findViewById(R.id.card7);
    cardViews[8] = (ImageView) findViewById(R.id.card8);
    cardViews[9] = (ImageView) findViewById(R.id.card9);
    if (me) {
      addOnClickListeners();
    }
  }

  public void updateView(Player player) {
    android.util.Log.e("SUSAN", "Updating view.");
    Card[] newCards = player.getHand();
    for (int i = 0; i < newCards.length; i++) {
      cardViews[i].setImageDrawable(cardUtil.getDrawable(newCards[i]));
    }
    requestLayout();
    invalidate();
  }

  private void addOnClickListeners() {
    for (int i = 0; i < cardViews.length; i++) {
      final int index = i;
      cardViews[i].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          PlayerController.getInstance().cardClicked(index);
        }
      });
    }
  }
}
