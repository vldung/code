package com.example.vocabulary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewDebug.IntToString;
import android.widget.ImageView;
import android.widget.TextView;

public class MeanActivity extends ActionBarActivity {

	private SQLiteDatabase sqliteDB = null;
	String tag = "mean";
	Bitmap myImage =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mean);

		ImageView mImageView = (ImageView) findViewById(R.id.imageID);
		

		Intent intent = getIntent();

		if (intent == null) {
			return;
		}

		String word = intent.getStringExtra("word");
		String lession = intent.getStringExtra("lession");

		Log.d(tag, word);
		Log.d(tag, lession);
		if (word == null) {
			return;
		}
		copyDatabase();
		String decode = "";
		String mean = getMean(word, lession);
		TextView txtMean = (TextView) findViewById(R.id.txt_mean_id);
		// Typeface
		// typeFace=Typeface.createFromAsset(getAssets(),"fonts/VNI-Alex.ttf");
		// txtMean.setTypeface(typeFace);

		try {
			decode = URLDecoder.decode(mean, "UTF-8");
			Log.d(tag, decode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtMean.setText(decode);
		txtMean.setTextSize(20);
		txtMean.setTextColor(Color.parseColor("#0000FF"));
		
		 if((myImage = getImage(word)) != null){
			 mImageView.setImageBitmap(myImage);
		 }else{
			 mImageView.setImageResource(R.drawable.ic_launcher);
		 }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mean, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void copyDatabase() {
		if (!new File("/data/data/" + this.getPackageName() + "/vocab.sqlite")
				.exists()) {
			// Log.d(tag, "open file database.sqlite");
			try {
				FileOutputStream out = new FileOutputStream("data/data/"
						+ this.getPackageName() + "/vocab.sqlite");
				InputStream in = getAssets().open("databases/DataVocab.s3db");

				byte[] buffer = new byte[1024];
				int readBytes = 0;

				while ((readBytes = in.read(buffer)) != -1)
					out.write(buffer, 0, readBytes);

				in.close();
				out.close();
			} catch (IOException e) {
			}

		}
		sqliteDB = SQLiteDatabase.openOrCreateDatabase(
				"/data/data/" + this.getPackageName() + "/vocab.sqlite", null);

	}



	Bitmap getImage(String name) {
		AssetManager assetManager = this.getAssets();
		Bitmap bitmap = null;
		InputStream is;
		try {
			is = assetManager.open("image/"+name+".jpg");
			 bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
		
	}
}
