package com.jzd.record.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.jzd.record.R;
import com.jzd.record.db.CompanyClass;
import com.jzd.record.db.DataBaseServer;

public class DetailViewActivity extends Activity {

	private DataBaseServer db;
	private TextView tv_name,tv_address,tv_area,tv_detailaddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_view);

		init();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_view, menu);
		return true;
	}

	private void init() {
		db = new DataBaseServer(this);
		Bundle bundle = getIntent().getExtras();
		String _id = bundle.getString("_id");
		System.out.println("TAG : " + _id);
		CompanyClass company = db.findRecordById(_id);

		//Log.e("TAG", company.toString());
		
		
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_address = (TextView) findViewById(R.id.tv_address);
		tv_area = (TextView) findViewById(R.id.tv_area);
		tv_detailaddress = (TextView) findViewById(R.id.tv_detail_address);
		
		tv_name.setText(company.getCompany_name());
		tv_address.setText(company.getCompany_address());
		tv_area.setText(company.getCompany_aera());
		tv_detailaddress.setText(company.getCompany_address());
	}

}
