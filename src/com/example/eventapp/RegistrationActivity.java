package com.example.eventapp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends Activity implements OnClickListener {
	EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7;
	Button Submit;
	String s;
	SQLiteDatabase db;
	Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		ed1 = (EditText) findViewById(R.id.editText1_fullname);
		ed2 = (EditText) findViewById(R.id.editText2_username);
		ed3 = (EditText) findViewById(R.id.editText3_password);
		ed4 = (EditText) findViewById(R.id.editText4_Address);
		ed5 = (EditText) findViewById(R.id.editText5_ConfirmPassword);
		ed6 = (EditText) findViewById(R.id.editText6_Contact);
		ed7 = (EditText) findViewById(R.id.editText7_EmailId);

		Submit = (Button) findViewById(R.id.button1_Submit);
		Submit.setOnClickListener(this);
		db = openOrCreateDatabase("Submitdetail", Context.MODE_PRIVATE, null);

	}

	public void onClick(View v) {
		if (v == Submit) {

			if (ed1.getText().toString().trim().length() == 0) {
				ed1.setError("Enter Fullname");
				return;
			}

			if (ed4.getText().toString().trim().length() == 0) {
				ed4.setError("Enter Address");
				return;
			}

			if (ed2.getText().toString().trim().length() == 0) {
				ed2.setError("Enter Username");
				return;
			}

			if (ed3.getText().toString().trim().length() == 0) {
				ed3.setError("Enter Password");
				return;
			}

			if (ed5.getText().toString().trim().length() == 0) {
				ed5.setError("Enter ConfirmPassword");
				return;
			}

			if (!ed3.getText().toString().equals(ed5.getText().toString())) {
				ed5.setError("password does not match");
				return;
			}
			if (ed6.getText().toString().trim().length() == 0) {
				ed6.setError("Enter Contact");
				return;
			}
			if (ed6.getText().toString().trim().length() < 10) {
				ed6.setError("Enter proper Contact");
				return;
			}
			if (ed7.getText().toString().trim().length() == 0) {
				ed7.setError("Enter EmailId");
				return;
			} else {
				Cursor c = db.rawQuery("select * from regis where u_name='"
						+ ed2.getText().toString() + "'", null);

				if (c.moveToFirst()) {
					showmessage("Sorry", "Username is already registered...");
				}
				if (c.getCount() == 0) {
					String Submit = "Insert into regis values('"
							+ ed1.getText().toString() + "','"
							+ ed2.getText().toString() + "', '"
							+ ed3.getText().toString() + "','"
							+ ed4.getText().toString() + "','"
							+ ed6.getText().toString() + "', '"
							+ ed7.getText().toString() + "')";
					db.execSQL(Submit);
					showmessage("Success", "Registration done...");
					Intent i = new Intent(this, UserformActivity.class);
					startActivity(i);

				}
			}
		}
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
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(intent);
	}
}
