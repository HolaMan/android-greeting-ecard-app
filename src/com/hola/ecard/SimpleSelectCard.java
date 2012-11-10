package com.hola.ecard;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SimpleSelectCard extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_select_card);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_simple_select_card, menu);
        return true;
    }
    
    public void selectCard(View view) {
    	int id = view.getId();
    	int cardIndex = 0;
    	if( id == R.id.imageButton1 )
    		cardIndex = 1;
    	else if( id == R.id.imageButton2 )
    		cardIndex = 2;
    	else if( id == R.id.imageButton3 )
    		cardIndex = 3;
    	else if( id == R.id.imageButton4 )
    		cardIndex = 4;    	    		
    	Intent intent = new Intent( SimpleSelectCard.this, EditingCard.class);
    	intent.putExtra("CardIndex", cardIndex);
    	SimpleSelectCard.this.startActivity(intent);
    }
}
