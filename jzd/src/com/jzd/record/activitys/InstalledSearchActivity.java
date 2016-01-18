package com.jzd.record.activitys;

import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.jzd.record.R;
import com.jzd.record.db.DataBaseServer;
import com.jzd.record.utils.CityAreaUtils;

public class InstalledSearchActivity extends Activity implements OnClickListener {

	private Button btn_clean, btn_search;
	private ListView listview;
	private EditText et_cityname, et_otherkey;
	private Spinner spinner_city;
	private DataBaseServer db;
	private boolean installed = true;
	private int[] real_id_arr = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_installed_search);

		init();
	}

	public void init() {

		Bundle bundle = getIntent().getExtras();
		installed = bundle.getBoolean("installed");
		if (installed) {
			this.setTitle("已安装查询");
		} else {
			this.setTitle("预安装查询");
		}

		btn_clean = (Button) findViewById(R.id.btn_clean);
		btn_search = (Button) findViewById(R.id.btn_search);
		listview = (ListView) findViewById(R.id.list_installed);

		spinner_city = (Spinner) findViewById(R.id.spinner_city);
		CityAreaUtils.loadCityAreaItems(spinner_city, this, null);

		et_cityname = (EditText) findViewById(R.id.et_cityname);
		et_otherkey = (EditText) findViewById(R.id.et_otherkey);

		btn_clean.setOnClickListener(this);
		btn_search.setOnClickListener(this);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int arg2, long arg3) {
				// TODO Auto-generated method stub
				// int selectedPosition = adapterView.getSelectedItemPosition();

				Intent intent = new Intent(InstalledSearchActivity.this, DetailEditActivity.class);

				// TextView tv_real_id = (TextView)
				// findViewById(R.id.tv_real_id);

				intent.putExtra("real_id", real_id_arr[arg2]);
				intent.putExtra("installed", installed);
				// Log.e("TAG", tv_real_id.getText().toString() + "||" +
				// Integer.parseInt(tv_real_id.getText().toString()));

				startActivity(intent);

				// Log.e("TAG", arg2 + "");
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
		String otherkey = et_otherkey.getText().toString();
		String city = spinner_city.getSelectedItem().toString();

		List<Map<String, Object>> list = db.search(city, otherkey, installed);

		int i = 0;
		real_id_arr = new int[list.size()];
		while (i < list.size()) {
			Map<String, Object> map = list.get(i);
			real_id_arr[i] = Integer.parseInt(map.get("real_id").toString());
			// Log.e("TAG", map.get("real_id").toString());
			i++;
		}
		SimpleAdapter adapter = null;
		if (this.installed) {
			adapter = new SimpleAdapter(this, list, R.layout.list_item, new String[] { "index", "real_id",
					"company_name", "hddsn","company_address" }, new int[] { R.id.tv_index, R.id.tv_real_id, R.id.tv_company_name,
					R.id.tv_harddisk_no,R.id.tv_address });

			listview.setAdapter(adapter);

			// TextView tv_address = (TextView) findViewById(R.id.tv_address);
			// tv_address.setVisibility(View.GONE);

		} else {
			adapter = new SimpleAdapter(this, list, R.layout.list_item, new String[] { "index", "real_id",
					"company_name", "company_address" }, new int[] { R.id.tv_index, R.id.tv_real_id,
					R.id.tv_company_name, R.id.tv_address });

			listview.setAdapter(adapter);

			// TextView tv_harddisk_no = (TextView)
			// findViewById(R.id.tv_harddisk_no);
			// tv_harddisk_no.setVisibility(View.GONE);
		}

	}

}
