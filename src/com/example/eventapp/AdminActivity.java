package com.example.eventapp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminActivity extends Activity implements OnClickListener {

	Button addVender, listUserAll, listAllVender, listEventAllRegister;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		addVender = (Button) findViewById(R.id.btn_Add_Vender);
		listUserAll = (Button) findViewById(R.id.btn_List_User_All);
		listAllVender = (Button) findViewById(R.id.btn_List_Vender);
		listEventAllRegister = (Button) findViewById(R.id.btn_List_All_Event_Register);

		addVender.setOnClickListener(this);
		listUserAll.setOnClickListener(this);
		listAllVender.setOnClickListener(this);
		listEventAllRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == addVender) {
			Intent intent = new Intent(getApplicationContext(), AddVender.class);
			startActivity(intent);

		}
		if (v == listUserAll) {
			Intent intent = new Intent(getApplicationContext(),
					ListUserAllActivity.class);
			startActivity(intent);
		}
		if (v == listAllVender) {
			Intent intent = new Intent(getApplicationContext(),
					ListAllVendorActivity.class);
			startActivity(intent);
		}
		if (v == listEventAllRegister) {
			Intent intent = new Intent(getApplicationContext(),
					ListAll_Event_Register_Activity.class);
			startActivity(intent);

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
