package com.jzd.record.activitys;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.jzd.record.R;
import com.jzd.record.db.DataBaseServer;

public class InstalledSearchActivity extends Activity implements
		OnClickListener {

	private Button btn_clean, btn_search;
	private ListView listview;
	private EditText et_cityname, et_otherkey;
	private DataBaseServer db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_installed_search);

		init();
	}

	public void init() {
		btn_clean = (Button) findViewById(R.id.btn_clean);
		btn_search = (Button) findViewById(R.id.btn_search);
		listview = (ListView) findViewById(R.id.list_installed);

		et_cityname = (EditText) findViewById(R.id.et_cityname);
		et_otherkey = (EditText) findViewById(R.id.et_otherkey);

		btn_clean.setOnClickListener(this);
		btn_search.setOnClickListener(this);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				int selectedPosition = adapterView.getSelectedItemPosition();

				Intent intent = new Intent(InstalledSearchActivity.this,
						DetailViewActivity.class);

				TextView tv_real_id = (TextView) findViewById(R.id.tv_real_id);

				intent.putExtra("_id", tv_real_id.getText().toString());

				startActivity(intent);

				Log.e("TAG", arg2 + "");
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.installed_search, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_clean:
			et_cityname.setText("");
			et_otherkey.setText("");
			break;
		case R.id.btn_search:
			search();
			break;
		default:
			break;

		}

	}

	private void search() {
		db = new DataBaseServer(this);
		String cityname = et_cityname.getText().toString();
		String otherkey = et_otherkey.getText().toString();

		List<Map<String, Object>> list = db.search(cityname, otherkey);

		SimpleAdapter adapter = new SimpleAdapter(this, list,
				R.layout.list_item, new String[] { "index", "real_id",
						"company_name", "harddisk_no" }, new int[] {
						R.id.tv_index, R.id.tv_real_id, R.id.tv_company_name,
						R.id.tv_harddisk_no });

		listview.setAdapter(adapter);
	}

}
