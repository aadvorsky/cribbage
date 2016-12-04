package com.example.anastasia.cribbage.android;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.util.AttributeSet;

public class HandView extends LinearLayout {
  private ImageView[] cardViews;
  private boolean me = false;
  private final CardUtil cardUtil;
  private final Context context;

  public HandView(Context context) {
    this(context, null);
  }

  public HandView(Context context, AttributeSet attrs) {
    this(context, null, 0);
  }

  public HandView(Context context, AttributeSet attrs, int defStyleAttrs) {
    super(context, attrs, defStyleAttrs);
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
    Card[] newCards = player.getHand();
    for (int i = 0; i < newCards.length; i++) {
      int drawableId = cardUtil.getDrawableId(newCards[i]);
      cardViews[i].setImageResource(drawableId);
    }
  }

  private void addOnClickListeners() {
    final PlayerController playerController = PlayerController.getInstance();
    for (int i = 0; i < cardViews.length; i++) {
      final int index = i;
      cardViews[i].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          playerController.cardClicked(index);
        }
      });
    }
  }
}
