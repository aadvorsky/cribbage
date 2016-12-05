package com.example.anastasia.cribbage;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.util.AttributeSet;

/**
 * Abstract client to handle game interactions.
 */
public interface PlayerClient {
  public void setMyself(int myIndex);

  public void updateView(GameState gameState);
}
