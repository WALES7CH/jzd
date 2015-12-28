package com.jzd.record.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jzd.record.R;
import com.jzd.record.db.CompanyClass;
import com.jzd.record.db.DataBaseServer;
import com.jzd.record.utils.CityAreaUtils;
import com.jzd.record.utils.SpinnerUtils;

public class DetailEditActivity extends Activity implements OnClickListener {
	private DataBaseServer db;
	private Button btn_modify, btn_cpinfo;
	private TextView tv_boot_on_weekend;
	private EditText et_name, et_address, et_detailaddress, et_boot_time, et_shut_time, et_factory, et_contact,
			et_net_contact, et_hddsn, et_qrcode, et_net_phone, et_phone;
	private Switch sw_boot_on_weekend;
	private Spinner spi_company_type, spi_company_city, spi_company_area;

	private int real_id = 0;
	private boolean installed, isnew;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_edit);

		init();
	}

	/*
	 * 初始化 找出所有的控件 并开始设置控件
	 */
	private void init() {
		db = new DataBaseServer(this);
		real_id = getIntent().getIntExtra("real_id", -1);
		installed = getIntent().getBooleanExtra("installed", true);
		isnew = getIntent().getBooleanExtra("isnew", false);

		if (real_id == -1 && !isnew) {
			return;
		}
		// System.out.println("TAG : " + real_id);

		spi_company_type = (Spinner) findViewById(R.id.spi_company_type);
		spi_company_city = (Spinner) findViewById(R.id.spi_company_city);
		spi_company_area = (Spinner) findViewById(R.id.spi_company_area);

		tv_boot_on_weekend = (TextView) findViewById(R.id.tv_boot_on_weekend);

		et_name = (EditText) findViewById(R.id.et_name);
		et_address = (EditText) findViewById(R.id.et_address);
		// et_area = (EditText) findViewById(R.id.et_area);
		et_detailaddress = (EditText) findViewById(R.id.et_detail_address);
		// et_company_type = (EditText) findViewById(R.id.et_company_type);
		// et_lan_type = (EditText) findViewById(R.id.et_lan_type);

		et_boot_time = (EditText) findViewById(R.id.et_boot_time);
		et_shut_time = (EditText) findViewById(R.id.et_shut_time);
		et_factory = (EditText) findViewById(R.id.et_factory);
		et_contact = (EditText) findViewById(R.id.et_contact);
		et_net_contact = (EditText) findViewById(R.id.et_net_contact);
		et_hddsn = (EditText) findViewById(R.id.et_hddsn);
		et_qrcode = (EditText) findViewById(R.id.et_qrcode);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_net_phone = (EditText) findViewById(R.id.et_net_phone);
		sw_boot_on_weekend = (Switch) findViewById(R.id.sw_boot_on_weekend);
		btn_modify = (Button) findViewById(R.id.btn_modify);
		btn_cpinfo = (Button) findViewById(R.id.btn_cpinfo);

		Log.e("isnew?", isnew + "");
		CityAreaUtils.loadCityAreaItems(spi_company_city, this, null);
		CityAreaUtils.loadCityAreaItems(spi_company_area, this, spi_company_city.getSelectedItem().toString());

		Log.e("isnew?", isnew + "");
		if (isnew) {// 新装

			this.setTitle("录入安装");
			btnControl(true);
		}

		setListeners();

		// 读取并 设置控件值
		loadRecord();

	}

	private void setListeners() {
		btn_modify.setOnClickListener(this);
		btn_cpinfo.setOnClickListener(this);
		sw_boot_on_weekend.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean switch_on) {
				// Toast.makeText(DetailEditActivity.this, switch_on + "",
				// Toast.LENGTH_SHORT).show();
				if (switch_on) {
					tv_boot_on_weekend.setText("1");
				} else {
					tv_boot_on_weekend.setText("0");
				}
			}

		});

		spi_company_city.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub

				CityAreaUtils.loadCityAreaItems(spi_company_area, DetailEditActivity.this, spi_company_city
						.getSelectedItem().toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_modify:
			btnControl(true);
			btn_cpinfo.setText("保存复制");
			break;
		case R.id.btn_cpinfo:
			save();
			btnControl(false);
			break;
		default:
			break;
		}
	}

	// 编辑状态控制
	private void btnControl(boolean able) {
		// Toast.makeText(this, " I am in." + able, Toast.LENGTH_SHORT).show();
		EditText[] ets = { et_name, et_address, et_detailaddress, et_boot_time, et_shut_time, et_factory, et_contact,
				et_net_contact, et_hddsn, et_qrcode, et_net_phone, et_phone };
		if (!installed || isnew) {
			setEtsFocusable(ets, able);
			sw_boot_on_weekend.setEnabled(able);
			spi_company_city.setClickable(able);
			spi_company_area.setClickable(able);
			spi_company_type.setClickable(able);
		} else {
			setEtsFocusable(ets, able);
			sw_boot_on_weekend.setEnabled(able);
			spi_company_city.setClickable(able);
			spi_company_area.setClickable(able);
			spi_company_type.setClickable(able);
		}
	}

	private void save() {
		CompanyClass company = new CompanyClass();
		company.set_id(real_id);
		company.setCompany_name(et_name.getText().toString());
		company.setCompany_city(spi_company_city.getSelectedItem().toString());
		company.setCompany_aera(spi_company_area.getSelectedItem().toString());
		company.setBoot_on_weekend(Integer.parseInt(tv_boot_on_weekend.getText().toString()));
		company.setCompany_address(et_address.getText().toString());
		company.setCompany_type(spi_company_type.getSelectedItem().toString());

		db = new DataBaseServer(this);

		boolean success = false;
		String msg = "";
		if (real_id == -1 && isnew) {
			success = db.insert(company);
			msg = "保存成功!";
		} else {
			success = db.update(company);
			msg = "保存成功!";
		}

		if (success) {
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
			btn_cpinfo.setText("复制信息");
		} else {
			msg = "保存失败!";
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		}
	}

	private void loadRecord() {
		CompanyClass company = db.findRecordById(real_id);

		this.setTitle(company.getCompany_name());
		et_name.setText(company.getCompany_name());
		tv_boot_on_weekend.setText(company.getBoot_on_weekend() + "");
		if (company.getBoot_on_weekend() == 1) {
			sw_boot_on_weekend.setChecked(true);
		} else {
			sw_boot_on_weekend.setChecked(false);
		}

		if (company.getCompany_address() != null) {
			et_address.setText(company.getCompany_address());
		}
		if (company.getCompany_city() != null) {
			SpinnerUtils.setSelectedItem(spi_company_city, company.getCompany_city());
		}
		if (company.getCompany_aera() != null) {
			SpinnerUtils.setSelectedItem(spi_company_area, company.getCompany_aera());
		}
		if (company.getCompany_address() != null) {
			et_detailaddress.setText(company.getCompany_address());
		}
		if (company.getCompany_type() != null) {
			// et_company_type.setText(company.getCompany_type());
			SpinnerUtils.setSelectedItem(spi_company_type, company.getCompany_type());
		}
		if (company.getLan_type() != null) {
			// et_lan_type.setText(company.getLan_type());
		}
		if (company.getBoot_time() != null) {
			et_boot_time.setText(company.getBoot_time());
		}
		if (company.getShut_time() != null) {
			et_shut_time.setText(company.getShut_time());
		}
		if (company.getFactory() != null) {
			et_factory.setText(company.getFactory());
		}
		if (company.getFirst_contact() != null) {
			et_contact.setText(company.getFirst_contact());
		}
		if (company.getSec_contact() != null) {
			et_net_contact.setText(company.getSec_contact());
		}
		if (company.getSec_phone() != null) {
			et_net_phone.setText(company.getSec_phone());
		}
		if (company.getHarddisk_no() != null) {
			et_hddsn.setText(company.getHarddisk_no());
		}
		if (company.getQrcode_no() != null) {
			et_qrcode.setText(company.getQrcode_no());
		}
		if (company.getFirst_phone() != null) {
			et_phone.setText(company.getFirst_phone());
		}

	}

	private void setEtsFocusable(EditText[] ets, boolean able) {
		for (int i = 0; i < ets.length; i++) {
			ets[i].setFocusableInTouchMode(able);
		}
	}

	private void cpinfo() {

	}

}
