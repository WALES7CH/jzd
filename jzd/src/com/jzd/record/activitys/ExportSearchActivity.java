package com.jzd.record.activitys;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.jzd.record.R;
import com.jzd.record.db.DataBaseServer;
import com.jzd.record.utils.CityAreaUtils;
import com.jzd.record.utils.JxlUtil;

public class ExportSearchActivity extends Activity implements OnClickListener {
	private Button btn_clean, btn_search, btn_export_excel;
	private ListView listview;
	private EditText et_cityname, et_otherkey;
	private Spinner spinner_city;
	private DataBaseServer db;
	private int[] real_id_arr = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_export_search);
		initView();
	}

	private void initView() {
		btn_clean = (Button) findViewById(R.id.btn_clean);
		btn_search = (Button) findViewById(R.id.btn_search);
		listview = (ListView) findViewById(R.id.list_installed);
		btn_export_excel = (Button) findViewById(R.id.btn_export_excel);

		spinner_city = (Spinner) findViewById(R.id.spinner_city);
		CityAreaUtils.loadCityAreaItems(spinner_city, this, null);

		et_cityname = (EditText) findViewById(R.id.et_cityname);
		et_otherkey = (EditText) findViewById(R.id.et_otherkey);

		btn_clean.setOnClickListener(this);
		btn_search.setOnClickListener(this);
		btn_export_excel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_clean:
			et_otherkey.setText("");
			break;
		case R.id.btn_search:
			search();
			break;
		case R.id.btn_export_excel:
			exportExcel();
			break;
		default:
			break;

		}
	}

	private void exportExcel() {
		String city = spinner_city.getSelectedItem().toString();
		db = new DataBaseServer(this);
		JxlUtil jxl = new JxlUtil(db.queryAll(city));

		if (jxl.toExcel(city)) {
			Toast.makeText(getApplicationContext(), "数据导出成功",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplicationContext(), "数据导出失败，请检查SD卡或数据库",
					Toast.LENGTH_SHORT).show();

		}
	}

	private void search() {
		db = new DataBaseServer(this);
		String otherkey = et_otherkey.getText().toString();
		String city = spinner_city.getSelectedItem().toString();

		List<Map<String, Object>> list = db.search(city, otherkey, true);

		int i = 0;
		real_id_arr = new int[list.size()];
		while (i < list.size()) {
			Map<String, Object> map = list.get(i);
			real_id_arr[i] = Integer.parseInt(map.get("real_id").toString());
			// Log.e("TAG", map.get("real_id").toString());
			i++;
		}
		SimpleAdapter adapter = null;
		adapter = new SimpleAdapter(this, list, R.layout.list_item,
				new String[] { "index", "real_id", "company_name", "hddsn" },
				new int[] { R.id.tv_index, R.id.tv_real_id,
						R.id.tv_company_name, R.id.tv_harddisk_no });

		listview.setAdapter(adapter);

	}
}
