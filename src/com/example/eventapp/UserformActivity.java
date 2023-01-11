package com.example.eventapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class UserformActivity extends Activity {
	Spinner sp_event_type, sp_venue, sp_baker, sp_decorator, sp_caterer;
	EditText name, length;
	DatePicker date1;
	TimePicker time1;
	Button submit;
	SQLiteDatabase db;
	List<String> l_type, l_venue, l_baker, l_decorator, l_caterer;
	String s_event, s_vendor, s_baker, s_deco, s_cat;
	String event_type[] = { "Select Event Type", "seminar", "birthday party",
			"wedding", "exhibition" };

	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userform);
		sp_event_type = (Spinner) findViewById(R.id.sp_select_eventtype);
		sp_venue = (Spinner) findViewById(R.id.sp_select_venue);
		sp_baker = (Spinner) findViewById(R.id.sp_select_bakery);
		sp_decorator = (Spinner) findViewById(R.id.sp_select_decorator);
		sp_caterer = (Spinner) findViewById(R.id.sp_select_caterer);

		name = (EditText) findViewById(R.id.et_select_event_name);
		length = (EditText) findViewById(R.id.et_select_event_length);

		date1 = (DatePicker) findViewById(R.id.dp_select_event_date);
		time1 = (TimePicker) findViewById(R.id.tp_select_event_time);

		submit = (Button) findViewById(R.id.btn_select_event_submit);

		db = openOrCreateDatabase("Submitdetail", Context.MODE_PRIVATE, null);

		gettype();
		getvenue();
		getbaker();
		getdecor();
		getcate();

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String name1 = name.getText().toString();
				String length1 = length.getText().toString();
				String vanue1 = sp_venue.getSelectedItem().toString();
				String baker1 = sp_baker.getSelectedItem().toString();
				String cater1 = sp_caterer.getSelectedItem().toString();
				String decor1 = sp_decorator.getSelectedItem().toString();
				String type1 = sp_event_type.getSelectedItem().toString();
				StringBuffer sbd = new StringBuffer();
				StringBuilder sbt = new StringBuilder();
				int day = date1.getDayOfMonth();
				int month = date1.getMonth() + 1;
				int year = date1.getYear();

				int hour = time1.getCurrentHour();
				int minute = time1.getCurrentMinute();

				String a;

				if (hour > 12) {
					hour = hour - 12;
					a = "PM";
				} else {
					a = "AM";
				}

				sbd.append(day).append("/").append(month);
				sbd.append("/").append(year);

				sbt.append(hour).append(":").append(minute).append(" ")
						.append(a);

				if (name1.trim().length() == 0) {
					name.setError("please enter event name");
					name.requestFocus();
					return;
				}
				if (length1.trim().length() == 0) {
					length.setError("please enter approx people");
					length.requestFocus();
					return;
				}
				if (sp_event_type.getSelectedItemPosition() == 0)

				{
					Toast.makeText(context, "Please select event_type first",
							3000).show();
				} else {
					db.execSQL("insert into event values(?,'"
							+ LoginActivity.user + "','" + type1 + "','"
							+ vanue1 + "','" + baker1 + "','" + decor1 + "','"
							+ cater1 + "','" + name1 + "','" + sbd + "','"
							+ sbt + "','" + length1 + "')");
					showmessage("Success", "successfully insert");
					sp_baker.setSelection(0);
					sp_caterer.setSelection(0);
					sp_decorator.setSelection(0);
					sp_event_type.setSelection(0);
					sp_venue.setSelection(0);
					name.setText("");
					length.setText("");
					name.requestFocus();
				}

			}
		});

	}

	private void getcate() {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery("select name from vendor where type='Cateror'",
				null);
		l_type = new ArrayList<String>();

		if (c != null) {
			if (c.moveToFirst()) {
				do {
					l_type.add(c.getString(c.getColumnIndex("name")));

				} while (c.moveToNext());
			}
			sp_caterer.setAdapter(new ArrayAdapter<String>(
					getApplicationContext(), R.layout.custtextview,
					R.id.txt_custtextview, l_type));

		}
		if (c.getCount() == 0) {
			String no[] = { "No Cateror Found" };
			sp_caterer.setAdapter(new ArrayAdapter<String>(
					getApplicationContext(), R.layout.custtextview,
					R.id.txt_custtextview, no));
		}
	}

	private void getdecor() {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery(
				"select name from vendor where type='Decorator'", null);
		l_type = new ArrayList<String>();

		if (c != null) {
			if (c.moveToFirst()) {
				do {
					l_type.add(c.getString(c.getColumnIndex("name")));

				} while (c.moveToNext());
			}
			sp_decorator.setAdapter(new ArrayAdapter<String>(
					getApplicationContext(), R.layout.custtextview,
					R.id.txt_custtextview, l_type));

		}
		if (c.getCount() == 0) {
			String no[] = { "No Decorator Found" };
			sp_decorator.setAdapter(new ArrayAdapter<String>(
					getApplicationContext(), R.layout.custtextview,
					R.id.txt_custtextview, no));
		}
	}

	private void getbaker() {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery("select name from vendor where type='Baker'",
				null);
		l_type = new ArrayList<String>();

		if (c != null) {
			if (c.moveToFirst()) {
				do {
					l_type.add(c.getString(c.getColumnIndex("name")));

				} while (c.moveToNext());
			}
			sp_baker.setAdapter(new ArrayAdapter<String>(
					getApplicationContext(), R.layout.custtextview,
					R.id.txt_custtextview, l_type));

		}
		if (c.getCount() == 0) {
			String no[] = { "No Baker Found" };
			sp_baker.setAdapter(new ArrayAdapter<String>(
					getApplicationContext(), R.layout.custtextview,
					R.id.txt_custtextview, no));
		}
	}

	private void getvenue() {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery(
				"select name from vendor where type='VenueOwner'", null);
		l_type = new ArrayList<String>();

		if (c != null) {
			if (c.moveToFirst()) {
				do {
					l_type.add(c.getString(c.getColumnIndex("name")));

				} while (c.moveToNext());
			}
			sp_venue.setAdapter(new ArrayAdapter<String>(
					getApplicationContext(), R.layout.custtextview,
					R.id.txt_custtextview, l_type));

		}
		if (c.getCount() == 0) {
			String no[] = { "No Vanue Found" };
			sp_venue.setAdapter(new ArrayAdapter<String>(
					getApplicationContext(), R.layout.custtextview,
					R.id.txt_custtextview, no));
		}

	}

	private void gettype() {
		// TODO Auto-generated method stub
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.custtextview,
				R.id.txt_custtextview, event_type);
		sp_event_type.setAdapter(adapter);
	}

	void showmessage(String title, String message) {
		Builder b = new Builder(context);
		b.setTitle(title).setMessage(message).show();
	}
}
