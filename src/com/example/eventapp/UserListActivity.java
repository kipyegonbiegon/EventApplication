package com.example.eventapp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;

public class UserListActivity extends Activity {
Context context=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
	}

	void showmessage(String title, String message) {
		Builder b = new Builder(context);
		b.setTitle(title).setMessage(message).show();
	}
}
