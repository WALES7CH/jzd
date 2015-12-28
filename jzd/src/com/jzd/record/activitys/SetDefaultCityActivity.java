package com.jzd.record.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jzd.record.R;
import com.jzd.record.db.DbCityService;
import com.jzd.record.utils.CityAreaUtils;
import com.jzd.record.utils.SpinnerUtils;

public class SetDefaultCityActivity extends Activity {

	private TextView tv_old_defalut_city;
	private Spinner spi_default_city;
	private Button btn_set_default_city;
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
