package com.example.anastasia.cribbage;

import android.content.Context;
import android.widget.LinearLayout;
import android.util.AttributeSet;

public class HandView extends LinearLayout {

  public HandView(Context context) {
    super(context);
  }

  public HandView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public updateView(GameState gameState, Player me) {
  }
}
