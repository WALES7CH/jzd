package com.jzd.record.activitys;

import com.jzd.record.R;
import com.jzd.record.R.layout;
import com.jzd.record.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DetailEditActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_edit);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_edit, menu);
		return true;
	}

}
