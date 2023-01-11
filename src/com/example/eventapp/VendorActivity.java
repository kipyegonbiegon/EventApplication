package com.example.eventapp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class VendorActivity extends Activity {
	Spinner sp;
	Button btn;
	EditText name, contact, price;
	String names[] = { "Decorator", "Cateror", "Baker", "VenueOwner" };
	SQLiteDatabase db;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_vendor);
		btn = (Button) findViewById(R.id.btn_submit_vendor);
		sp = (Spinner) findViewById(R.id.sp_vendor_type);
		name = (EditText) findViewById(R.id.et_vendor_name);
		contact = (EditText) findViewById(R.id.et_vendor_contact);
		price = (EditText) findViewById(R.id.et_vendor_price);
		db = openOrCreateDatabase("Submitdetail", Context.MODE_PRIVATE, null);
		// db.execSQL("drop table vendor");
		db.execSQL("create table if not exists vendor(id integer primary key autoincrement,type varchar2(20), name varchar2(20), contact varchar2(15), price varchar2(10)) ");

		ArrayAdapter<String> ad = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.custtextview,
				R.id.txt_custtextview, names);
		sp.setAdapter(ad);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String n = name.getText().toString();
				String c = contact.getText().toString();
				String p = price.getText().toString();
				String s = sp.getSelectedItem().toString();
				db.execSQL("insert into vendor values(?,'" + s + "','" + n
						+ "','" + c + "','" + p + "')");
				// Intent i = new Intent(this,VendorItemActivity.class);
				// startActivity(i);

			}
		});
	}

	void showmessage(String title, String message) {
		Builder b = new Builder(context);
		b.setTitle(title).setMessage(message).show();
	}
}