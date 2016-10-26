package com.jzd.record.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jzd.record.R;
import com.jzd.record.db.DbCityService;
import com.jzd.record.utils.CityAreaUtils;
import com.jzd.record.utils.SpinnerUtils;

public class SetDefaultCityActivity extends Activity {

	private TextView tv_old_defalut_city;
	private EditText et_addhddsn,et_hhd_factory,et_new_area;
	private Spinner spi_default_city;
	private Button btn_set_default_city,btn_addhddsn,btn_add_area;
	private DbCityService dbcityservice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_default_city);

		dbcityservice = new DbCityService(this);
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_default_city, menu);
		return true;
	}

	private void initView() {
		tv_old_defalut_city = (TextView) findViewById(R.id.tv_old_defalut_city);
		spi_default_city = (Spinner) findViewById(R.id.spi_default_city);
		btn_set_default_city = (Button) findViewById(R.id.btn_set_default_city);
		btn_add_area = (Button)findViewById(R.id.btn_add_area);
		
		btn_addhddsn = (Button) findViewById(R.id.btn_addhddsn);
		et_addhddsn = (EditText) findViewById(R.id.et_addhddsn);
		et_hhd_factory = (EditText) findViewById(R.id.et_hdd_factory);
		et_new_area = (EditText)findViewById(R.id.et_new_area);

		CityAreaUtils.loadCityAreaItems(spi_default_city, this, null);

		btn_set_default_city.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String newCity = spi_default_city.getSelectedItem().toString();
				String oldCity = tv_old_defalut_city.getText().toString();
				boolean success = dbcityservice.updateDefaultCity(newCity, oldCity);
				if (success) {
					tv_old_defalut_city.setText(newCity);
					Toast.makeText(SetDefaultCityActivity.this, "设置成功!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(SetDefaultCityActivity.this, "设置失败!", Toast.LENGTH_SHORT).show();
				}
			}

		});
		
		btn_add_area.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String parent = spi_default_city.getSelectedItem().toString();
				String new_area = et_new_area.getText().toString();
				boolean success = dbcityservice.InsertArea(parent, new_area);
				if (success) {
					Toast.makeText(SetDefaultCityActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(SetDefaultCityActivity.this, "添加失败!", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		
		
		
		
		//添加硬盘号前缀
		btn_addhddsn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str_addhddsn = et_addhddsn.getText().toString();
				String str_hdd_factory = et_hhd_factory.getText().toString();
				if(str_addhddsn.equals("")|| str_addhddsn == null ||str_addhddsn.length() < 4)
					return;
				boolean success = dbcityservice.InsertHddsn(str_addhddsn, str_hdd_factory);
				if (success) {
					Toast.makeText(SetDefaultCityActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(SetDefaultCityActivity.this, "添加失败!", Toast.LENGTH_SHORT).show();
				}
			}
			
		});

		loadDefaultValue();
	}

	private void loadDefaultValue() {
		String defaultCity = dbcityservice.getDefaultCity();
		if (defaultCity != null && defaultCity != "") {
			tv_old_defalut_city.setText(defaultCity);
			SpinnerUtils.setSelectedItem(spi_default_city, defaultCity);
		}
	}
}
