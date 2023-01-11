package com.example.eventapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListAllVendorActivity extends Activity {
	ListView lv;
	List<String> list = new ArrayList<String>();
	SQLiteDatabase db;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_all_vendor);
		db = openOrCreateDatabase("Submitdetail", Context.MODE_PRIVATE, null);
		lv = (ListView) findViewById(R.id.lv_all_vender);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				list);
		lv.setAdapter(adapter);
		String Query = "select * from vender_name";
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				list.add(c.getString(c.getColumnIndex("name")));
			} while (c.moveToNext());

		}

	}

	void showmessage(String title, String message) {
		Builder b = new Builder(context);
		b.setTitle(title).setMessage(message).show();
	}
}