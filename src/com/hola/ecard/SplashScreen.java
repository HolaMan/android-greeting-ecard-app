package com.hola.ecard;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.support.v4.app.NavUtils;

public class SplashScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// remove title
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        
        // go to the select card activity after splash
        Handler handler = new Handler();        
        handler.postDelayed(new Runnable() { 
            public void run() { 
                // make sure we close the splash screen so the user won't come back when it presses back key 
                finish();
                // start the home screen 
                Intent intent = new Intent(SplashScreen.this, SimpleSelectCard.class);
                SplashScreen.this.startActivity(intent); 
            } 
        }, 100); // time in milliseconds (1 second = 1000 milliseconds)
    }    
}
