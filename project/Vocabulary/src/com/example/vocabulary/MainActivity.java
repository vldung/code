package com.example.vocabulary;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	// Array of strings...
    ArrayList<String> listLession = new ArrayList<>();
    String tag ="vocab";
  
    
    private static final int REQUEST_CODE = 10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.v(tag, "onCreate");
		  for(int i=1;i<=40;i++){
		    	listLession.add("Lesson"+i);
		    }
		  //TextView mTextView =(TextView) findViewById(R.id.label);
		  TextView tvTitle = (TextView) findViewById(R.id.tv_title);
			Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/VietAPK.vn-AdHoc.ttf");
			//mTextView.setTypeface(typeFace);
			tvTitle.setTypeface(typeFace);
		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
			      R.layout.item, listLession);
			      
			      ListView listView = (ListView) findViewById(R.id.vocab_list);
			      listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				   Log.v(tag,"onItemClick");
			       String item = ((TextView)view).getText().toString();
			       Log.v(tag,item);
	               //Create new intent
			       Intent intent = new Intent(MainActivity.this, VocabActivity.class);
			       intent.putExtra("lession", item);
			       startActivityForResult(intent, REQUEST_CODE);
			       Log.d(tag, "start intent");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
