package com.hola.ecard;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.support.v4.app.NavUtils;

public class EditingCard extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// remove title
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
                
        int display_mode = getResources().getConfiguration().orientation;
        if( display_mode == 1 ) {
        	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        	setContentView(R.layout.activity_editing_card);
        }
        else {
        	LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.xmas_card1, null);            
        	setContentView(view);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_editing_card, menu);
        return true;
    }

    
}
