package com.example.vocabulary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class VocabActivity extends Activity {
	
	private SQLiteDatabase sqliteDB = null;
	String tag ="vocabulary";
	String lession;
	private Button btnPlay;
	private Button btnPause;
	private MediaPlayer mediaPlayer = new MediaPlayer();
	private AssetFileDescriptor afd =null;
	private static final int  REQUEST_CODE2 = 12;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vocab);
		ListView mListView = (ListView) findViewById(R.id.listViewWord);
		btnPlay = (Button) findViewById(R.id.btn_play);
		btnPause = (Button) findViewById(R.id.btn_pause);
		//mListView.setOnItemClickListener(listener)
		ArrayList<String> listword = new ArrayList<>();
	
		Intent intent = getIntent();
		
		if(intent == null){
			return;
		}
		
		lession = intent.getStringExtra("lession");
		if(lession == null){
			return;
		}
		Log.d(tag, lession);
		
		getSound();
		try {
			mediaPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		copyDatabase();
		MyData myData = new MyData(sqliteDB);
		
		listword = myData.getWord(lession);
		
		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
			      R.layout.item, listword);
		mListView.setAdapter(adapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {
        
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String item = ((TextView)view).getText().toString();
			       Log.v(tag,item);
	               //Create new intent
			       Intent intent = new Intent(VocabActivity.this, MeanActivity.class);
			       intent.putExtra("word", item);
			       intent.putExtra("lession", lession);
			       startActivityForResult(intent, REQUEST_CODE2);
			}
		});
		
	}
	
	public void copyDatabase() {
		if (!new File("/data/data/" + this.getPackageName()
				+ "/vocab.sqlite").exists()) {
			Log.d(tag, "open file database.sqlite");
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
				"/data/data/" + this.getPackageName() + "/vocab.sqlite",
				null);
		if(sqliteDB != null){
			Log.d(tag, "open data success");
			}else{
				Log.d(tag,"can not open data");
			}
	}
	
	public void getSound(){
		try {
			 afd = getAssets().openFd("sound/"+lession+".wav");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void clickPlay(View view) throws IllegalStateException, IOException{
		//mediaPlayer.prepare();
		mediaPlayer.start();
	}
	
	public void clickPause(View view){
		mediaPlayer.pause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//mediaPlayer.stop();
		Log.d("abc","resume");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("abc","onPause");
		mediaPlayer.stop();
	}
	
}
