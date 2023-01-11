package com.example.eventapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListUserAllActivity extends Activity {
	ListView lv;
	ArrayList<HashMap<String, String>> ar = new ArrayList<HashMap<String, String>>();
	SQLiteDatabase db;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_user_all);
		db = openOrCreateDatabase("Submitdetail", Context.MODE_PRIVATE, null);
		lv = (ListView) findViewById(R.id.lv_all_user);

		String Query = "select * from regis";
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				String un = c.getString(c.getColumnIndex("u_name"));
				String fn = c.getString(c.getColumnIndex("f_name"));
				String con = c.getString(c.getColumnIndex("cnt"));
				String ad = c.getString(c.getColumnIndex("adr"));
				String em = c.getString(c.getColumnIndex("e_mail"));

				map.put("uname", "Username : "+un);
				map.put("fname", "Firstname : "+fn);
				map.put("contact", "Contact No. : "+con);
				map.put("add", "Address : "+ad);
				map.put("email", "Email : "+em);

				ar.add(map);

			} while (c.moveToNext());

		}
		SimpleAdapter sm = new SimpleAdapter(context, ar,
				R.layout.cust_user_list, new String[] { "uname", "fname",
						"contact", "add", "email" }, new int[] {
						R.id.txt_user_name, R.id.txt_user_firstname,
						R.id.txt_user_contact, R.id.txt_user_add,
						R.id.txt_user_email });
		lv.setAdapter(sm);
	}

	void showmessage(String title, String message) {
		Builder b = new Builder(context);
		b.setTitle(title).setMessage(message).show();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
