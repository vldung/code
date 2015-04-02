package com.example.vocabulary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyData extends Activity {

	String tag = "MyData";
	private SQLiteDatabase sqliteDB;
	private Cursor myCursor = null;

	public MyData(SQLiteDatabase sqliteDB) {
		super();
		this.sqliteDB = sqliteDB;
	}

	// copy data form asset file into device data

	public void getCursor(String tb_Name) {
		String[] columns = { "id", "word", "mean", "example", "category" };

		try {
			myCursor = sqliteDB.query(tb_Name, columns, null, null, null, null,
					null);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public String getMean(String word) {

		// Đọc từng dòng
		while (myCursor.moveToNext()) {
			if (myCursor.getString(1).equals(word)) {
				return myCursor.getString(2);
			}
		}
		myCursor.close();

		return "None";
	}

	public String getExample(String word) {
		while (myCursor.moveToNext()) {
			if (myCursor.getString(1).equals(word))
				return myCursor.getString(3);
		}

		return "NULL";
	}

	public String getCategory(String word) {
		while (myCursor.moveToNext()) {
			if (myCursor.getString(1).equals(word))
				return myCursor.getString(4);
		}

		return "NULL";
	}

	public ArrayList<String> getWord(String tb_Name) {

		ArrayList<String> listWordOfTable = new ArrayList<>();
		// Đọc từng dòng
		while (myCursor.moveToNext()) {
			String word = myCursor.getString(1);
			// Lấy dữ liệ gán vào mảng để trả về
			listWordOfTable.add(word);
		}
		myCursor.close();

		return listWordOfTable;
	}

}
