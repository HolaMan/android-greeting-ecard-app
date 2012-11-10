package com.hola.ecard;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.support.v4.app.NavUtils;

public class EditingCard extends Activity {

	public static int CAMERA_REQUEST = 10000;
	public static int GALLERY_PICK_REQUEST = 10001;
	
	private View cardRoot = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// remove title
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
                
    	int cardIndex = this.getIntent().getIntExtra("CardIndex", 0);
    	int layout_id = R.layout.xmas_card1;
    	if( cardIndex == 2 )
    		layout_id = R.layout.xmas_card2;
    	else if( cardIndex == 3 )
    		layout_id = R.layout.xmas_card3;    	else if( cardIndex == 4 )
    		layout_id = R.layout.xmas_card4;
    	
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cardRoot = layoutInflater.inflate(layout_id, null);            
        setContentView(cardRoot);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.activity_editing_card, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_share_card:
            	ShareCard();
            	break;
        }
        return super.onOptionsItemSelected(item);
    }
    protected void ShowHideIconButtons(boolean show) {
		ImageView v1 = (ImageView) this.findViewById(R.id.imageViewCamera);
		ImageView v2 = (ImageView) this.findViewById(R.id.imageViewGallery);
		if( v1 != null )
			v1.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		if( v2 != null )
			v2.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }
    protected File CardScreenCapture() {
    	if( cardRoot != null ) {
    		ShowHideIconButtons(false);
    		
    		cardRoot.setDrawingCacheEnabled(true);
    		cardRoot.buildDrawingCache(true);
    		Bitmap bitmap = Bitmap.createBitmap(cardRoot.getDrawingCache());
    		cardRoot.setDrawingCacheEnabled(false);
    		
    		ShowHideIconButtons(true);    		
    		
    		String filename = String.format("card_%s.jpg", UUID.randomUUID());
            File file = new File(Environment.getExternalStorageDirectory(), filename);
    	    try 
    	    {
    	        FileOutputStream ostream = new FileOutputStream(file);
    	        bitmap.compress(CompressFormat.JPEG, 70, ostream);
    	        ostream.close();
    	        bitmap.recycle();
    	        return file;
    	    } 
    	    catch (Exception e) 
    	    {
    	        e.printStackTrace();
    	        return null;
    	    }
    	}
		return null;
    }
    
    public void ShareCard() {
    	
    	File cardImage = CardScreenCapture();
    	
    	Intent sharingIntent = new Intent(Intent.ACTION_SEND);
    	sharingIntent.setType("image/*");    	
        sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(cardImage));
        startActivity(Intent.createChooser(sharingIntent, "Share Card using"));
    }
    
    public void onClickCamera(View view) {
    	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
        startActivityForResult(cameraIntent, CAMERA_REQUEST); 
    }
    
	public void onClickGallery(View view) {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		startActivityForResult(intent, GALLERY_PICK_REQUEST);
	}
	public String getPath(Uri uri) {
	    String[] projection = { MediaStore.Images.Media.DATA };
	    Cursor cursor = managedQuery(uri, projection, null, null, null);
	    int column_index = cursor
	            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    cursor.moveToFirst();
	    return cursor.getString(column_index);
	}
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	ImageView imageView = (ImageView) this.findViewById(R.id.xmasProfile);
        if( imageView == null )
        	return;
        
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);                        
        }
        else if (requestCode == GALLERY_PICK_REQUEST && resultCode == RESULT_OK ) {
        	Uri selectedImageUri = data.getData();
            try {           
            	BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap photo = BitmapFactory.decodeFile(getPath(selectedImageUri), options);
                imageView.setImageBitmap(photo);
            }
        	catch(OutOfMemoryError e) {
        		e.printStackTrace();
        	    System.gc();
        	}
        }
    } 
}
