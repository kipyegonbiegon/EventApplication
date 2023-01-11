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
import android.widget.Spinner;

public class LoginActivity extends Activity implements OnClickListener {
	Context context = this;
	Spinner spinner1;
	EditText etUsername, etPassword;
	Button btnLogin, btnRegister;
	SQLiteDatabase db;
	static String user = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		db = openOrCreateDatabase("Submitdetail", Context.MODE_PRIVATE, null);

		db.execSQL("create table if not exists admin(u_nm varchar2(20),pwd varchar2(10))");
		db.execSQL("create table if not exists vendor(id integer primary key autoincrement,type varchar2(20), name varchar2(20), contact varchar2(15), price varchar2(10)) ");
		db.execSQL("create table if not exists event(id integer primary key autoincrement, username varchar2(20),eventtype varchar2(20), vanue varchar2(20), baker varchar2(20), decorator varchar2(20), caterer varchar2(20), eventname varchar2(25), date1 varchar2(10), time1 varchar2(10), length varchar2(10))");
		db.execSQL("create table if not exists vender_name(id integer primary key autoincrement ,name text)");
		db.execSQL("create table if not exists regis(f_name text,u_name text,pwd text,adr text,cnt number,e_mail text)");

		String Query = "select * from vender_name";
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {

			} while (c.moveToNext());

		} else {
			db.execSQL("insert into admin values('admin','admin')");
			db.execSQL("insert into vender_name values(?,'Baker')");
			db.execSQL("insert into vender_name values(?,'Cateror')");
			db.execSQL("insert into vender_name values(?,'Venue Owner')");
			db.execSQL("insert into vender_name values(?,'Decorator')");
		}

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		etUsername = (EditText) findViewById(R.id.ed_id);
		etPassword = (EditText) findViewById(R.id.ed_pwd);

		btnLogin = (Button) findViewById(R.id.btn_login);
		btnRegister = (Button) findViewById(R.id.btn_register);
		btnLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnLogin) {

			if (etUsername.getText().toString().trim().length() == 0) {
				etUsername.setError("Enter username");
				return;
			}
			if (etPassword.getText().toString().trim().length() == 0) {
				etPassword.setError("Enter password");
				return;
			} else {
				String Query_check = "select * from admin where u_nm='"
						+ etUsername.getText().toString() + "' and pwd='"
						+ etPassword.getText().toString() + "'";
				Cursor c = db.rawQuery(Query_check, null);
				if (c.moveToFirst()) {
					do {
						Intent intent = new Intent(getApplicationContext(),
								AdminActivity.class);
						startActivity(intent);
					} while (c.moveToNext());

				} else {
					String Query_reg = "select * from regis where u_name='"
							+ etUsername.getText().toString() + "' and pwd='"
							+ etPassword.getText().toString() + "'";
					Cursor c1 = db.rawQuery(Query_reg, null);
					if (c1.moveToFirst()) {
						do {
							user = etUsername.getText().toString();
							Intent intent = new Intent(getApplicationContext(),
									UserformActivity.class);
							startActivity(intent);
						} while (c1.moveToNext());

					}
					if (c1.getCount() == 0) {
						showmessage("Sorry",
								"username and password does not match");
					}
				}
			}
		}
		if (v == btnRegister) {
			Intent i = new Intent(getApplicationContext(),
					RegistrationActivity.class);
			startActivity(i);
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
}
