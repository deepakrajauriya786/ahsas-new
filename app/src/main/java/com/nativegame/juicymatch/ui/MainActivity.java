package com.nativegame.juicymatch.ui;

import android.os.Bundle;

import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ad.AdManager;
import com.nativegame.juicymatch.timer.LivesTimer;
import com.nativegame.juicymatch.ui.fragment.LoadingFragment;
import com.nativegame.natyengine.ui.GameActivity;

public class MainActivity extends GameActivity {

    private AdManager mAdManager;
    private LivesTimer mLivesTimer;

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public AdManager getAdManager() {
        return mAdManager;
    }

    public LivesTimer getLivesTimer() {
        return mLivesTimer;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_JuicyMatch);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_main_game);
        setFragmentContainer(R.id.layout_container);

        mAdManager = new AdManager(this);
        mLivesTimer = new LivesTimer(this);

        // Show the menu fragment
        if (savedInstanceState == null) {
            navigateToFragment(new LoadingFragment());
        }
    }
    //========================================================

}
