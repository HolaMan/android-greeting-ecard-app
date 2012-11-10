package com.hola.ecard;

import java.util.ArrayList;
import java.util.HashMap;

import com.hola.ecard.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.view.*;

public class SelectCardActivity extends Activity {
	private GridView gridView;
	private ArrayList<HashMap<String, Object>> cardList = null;
	
	private void addCard(int card_resource_id, String cardName) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ItemImage", card_resource_id);
		map.put("ItemText", cardName);
		cardList.add(map);
	}
	private void prepareCardList() {
		
		cardList = new ArrayList<HashMap<String, Object>>();
		addCard(R.drawable.xmas_card1_logo, "聖誕卡片 #1");
		for (int i = 0; i < 30; i++)
			addCard(R.drawable.xmas_card1_logo, "聖誕卡片 #" + String.valueOf(i+2));
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// remove title
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card);
        
        prepareCardList();
        gridView = (GridView) findViewById(R.id.gridView1);
        SimpleAdapter saImageItems = new SimpleAdapter(this,  
        		cardList, 
        		R.layout.carditem, 
        		new String[] {"ItemImage","ItemText"}, 
        		new int[] {R.id.ItemImage,R.id.ItemText});
        
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if( display.getWidth() <= 720 )
        	gridView.setNumColumns(2);
        else if( display.getWidth() <= 1080 )
        	gridView.setNumColumns(3);
        else if( display.getWidth() > 1080 )
        	gridView.setNumColumns(4);
        gridView.setAdapter(saImageItems);
        
        gridView.setOnItemClickListener(new GridView.OnItemClickListener(){
           public void onItemClick(AdapterView adapterView,View view,int position,long id) {
        	   Intent intent = new Intent(SelectCardActivity.this, EditingCard.class);
        	   SelectCardActivity.this.startActivity(intent);
               // Toast.makeText(MainActivity.this, "您選擇的是"+list[position], Toast.LENGTH_SHORT).show();
           }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_select_card, menu);
        return true;
    }
}
