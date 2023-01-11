package com.example.eventapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddVender extends Activity {
	Spinner sp;
	Button submit;
	EditText name, contact, price;
	String names[] = { "select vendor", "Decorator", "Cateror", "Baker",
			"VenueOwner" };
	SQLiteDatabase db;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_vendor);
		submit = (Button) findViewById(R.id.btn_submit_vendor);
		sp = (Spinner) findViewById(R.id.sp_vendor_type);
		name = (EditText) findViewById(R.id.et_vendor_name);
		contact = (EditText) findViewById(R.id.et_vendor_contact);
		price = (EditText) findViewById(R.id.et_vendor_price);
		db = openOrCreateDatabase("Submitdetail", Context.MODE_PRIVATE, null);
		// db.execSQL("drop table vendor");
		ArrayAdapter<String> ad = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.custtextview,
				R.id.txt_custtextview, names);
		sp.setAdapter(ad);
		submit.setOnClickListener(new OnClickListener() {

			@SuppressLint("WrongConstant")
            @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (name.getText().toString().trim().length() == 0) {
					name.setError("Enter name");
					return;
				}

				if (contact.getText().toString().trim().length() == 0) {
					contact.setError("Enter contact");
					return;
				}
				if (contact.getText().toString().trim().length() < 10) {
					contact.setError("Enter proper contact");
					return;
				}

				if (price.getText().toString().trim().length() == 0) {
					price.setError("Enter price");
					return;
				}
				if (sp.getSelectedItemPosition() == 0)

				{

                    Toast.makeText(context,
                            "please select vendor first",
                            3000)
							.show();
				} else {
					String n = name.getText().toString();
					String c = contact.getText().toString();
					String p = price.getText().toString();
					String s = sp.getSelectedItem().toString();
					db.execSQL("insert into vendor values(?,'" + s + "','" + n
							+ "','" + c + "','" + p + "')");
					showmessage("success", "submit successfully");
					name.setText("");
					contact.setText("");
					price.setText("");
					sp.setSelection(0);
					name.requestFocus();
				}
			}
		});
	}

	void showmessage(String title, String message) {
		Builder b = new Builder(context);
		b.setTitle(title).setMessage(message).show();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
		startActivity(intent);
	}
}
