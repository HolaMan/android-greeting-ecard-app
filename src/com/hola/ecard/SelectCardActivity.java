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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.view.*;

public class SelectCardActivity extends Activity {
	private GridView gridView;
	private ArrayList<HashMap<String, Object>> cardList = null;
	
	private void prepareCardList() {
		cardList = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 30; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.xmas_card1_logo);
			map.put("ItemText", "NO." + String.valueOf(i));
			cardList.add(map);
		}
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
        
        int display_mode = getResources().getConfiguration().orientation;
        if (display_mode == 1) {
        	gridView.setNumColumns(2);
        } else {
        	gridView.setNumColumns(3);
        }        
        gridView.setAdapter(saImageItems);
        
        gridView.setOnItemClickListener(new GridView.OnItemClickListener(){
           @Override
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
