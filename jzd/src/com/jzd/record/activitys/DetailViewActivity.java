package com.jzd.record.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jzd.record.R;
import com.jzd.record.db.CompanyClass;
import com.jzd.record.db.DataBaseServer;

public class DetailViewActivity extends Activity implements OnClickListener {

	private DataBaseServer db;
	private TextView tv_name, tv_address, tv_area, tv_detailaddress, tv_company_type, tv_lan_type, tv_boot_on_weekend,
			tv_boot_time, tv_shut_time, tv_factory, tv_contact, tv_net_contact, tv_hddsn, tv_qrcode, tv_net_phone,
			tv_phone;

	private Button btn_modify, btn_cpinfo;
	private EditText et_name, et_address, et_area, et_detailaddress, et_company_type, et_lan_type, et_boot_time,
			et_shut_time, et_factory, et_contact, et_net_contact, et_hddsn, et_qrcode, et_net_phone, et_phone;
	private Switch sw_boot_on_weekend;

	private int real_id = 0;
	private boolean installed = true;;

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
		real_id = getIntent().getIntExtra("real_id", -1);
		installed = getIntent().getBooleanExtra("installed", true);

		if (real_id == -1) {
			return;
		}
		// System.out.println("TAG : " + real_id);
		CompanyClass company = db.findRecordById(real_id);

		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_address = (TextView) findViewById(R.id.tv_address);
		tv_area = (TextView) findViewById(R.id.tv_area);
		tv_detailaddress = (TextView) findViewById(R.id.tv_detail_address);
		tv_company_type = (TextView) findViewById(R.id.tv_company_type);
		tv_lan_type = (TextView) findViewById(R.id.tv_lan_type);
		tv_boot_on_weekend = (TextView) findViewById(R.id.tv_boot_on_weekend);
		tv_boot_time = (TextView) findViewById(R.id.tv_boot_time);
		tv_shut_time = (TextView) findViewById(R.id.tv_shut_time);
		tv_factory = (TextView) findViewById(R.id.tv_factory);
		tv_contact = (TextView) findViewById(R.id.tv_contact);
		tv_net_contact = (TextView) findViewById(R.id.tv_net_contact);
		tv_hddsn = (TextView) findViewById(R.id.tv_hddsn);
		tv_qrcode = (TextView) findViewById(R.id.tv_qrcode);
		tv_phone = (TextView) findViewById(R.id.tv_phone);
		tv_net_phone = (TextView) findViewById(R.id.tv_net_phone);

		et_name = (EditText) findViewById(R.id.et_name);
		et_address = (EditText) findViewById(R.id.et_address);
		et_area = (EditText) findViewById(R.id.et_area);
		et_detailaddress = (EditText) findViewById(R.id.et_detail_address);
		et_company_type = (EditText) findViewById(R.id.et_company_type);
		et_lan_type = (EditText) findViewById(R.id.et_lan_type);

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
		btn_modify.setOnClickListener(this);
		btn_cpinfo.setOnClickListener(this);
		sw_boot_on_weekend.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean switch_on) {
				Toast.makeText(DetailViewActivity.this, switch_on + "", Toast.LENGTH_SHORT).show();
				if (switch_on) {
					tv_boot_on_weekend.setText("1");
				} else {
					tv_boot_on_weekend.setText("0");
				}
			}

		});

		// Log.e("company_class", company.toString());

		// Log.e("getBoot_on_weekend==>", company.getBoot_on_weekend() + "");

		this.setTitle(company.getCompany_name());
		tv_name.setText(company.getCompany_name());
		et_name.setText(company.getCompany_name());
		tv_boot_on_weekend.setText(company.getBoot_on_weekend() + "");
		if (company.getBoot_on_weekend() == 1) {
			sw_boot_on_weekend.setChecked(true);
		} else {
			sw_boot_on_weekend.setChecked(false);
		}

		if (company.getCompany_address() != null) {
			tv_address.setText(company.getCompany_address());
			et_address.setText(company.getCompany_address());
		}
		if (company.getCompany_aera() != null) {
			tv_area.setText(company.getCompany_aera());
			et_area.setText(company.getCompany_aera());
		}
		if (company.getCompany_address() != null) {
			tv_detailaddress.setText(company.getCompany_address());
			et_detailaddress.setText(company.getCompany_address());
		}
		if (company.getCompany_type() != null) {
			tv_company_type.setText(company.getCompany_type());
			et_company_type.setText(company.getCompany_type());
		}
		if (company.getLan_type() != null) {
			tv_lan_type.setText(company.getLan_type());
			et_lan_type.setText(company.getLan_type());
		}
		if (company.getBoot_time() != null) {
			tv_boot_time.setText(company.getBoot_time());
			et_boot_time.setText(company.getBoot_time());
		}
		if (company.getShut_time() != null) {
			tv_shut_time.setText(company.getShut_time());
			et_shut_time.setText(company.getShut_time());
		}
		if (company.getFactory() != null) {
			tv_factory.setText(company.getFactory());
			et_factory.setText(company.getFactory());
		}
		if (company.getFirst_contact() != null) {
			tv_contact.setText(company.getFirst_contact());
			et_contact.setText(company.getFirst_contact());
		}
		if (company.getSec_contact() != null) {
			tv_net_contact.setText(company.getSec_contact());
			et_net_contact.setText(company.getSec_contact());
		}
		if (company.getSec_phone() != null) {
			tv_net_phone.setText(company.getSec_phone());
			et_net_phone.setText(company.getSec_phone());
		}
		if (company.getHarddisk_no() != null) {
			tv_hddsn.setText(company.getHarddisk_no());
			et_hddsn.setText(company.getHarddisk_no());
		}
		if (company.getQrcode_no() != null) {
			tv_qrcode.setText(company.getQrcode_no());
			et_qrcode.setText(company.getQrcode_no());
		}
		if (company.getFirst_phone() != null) {
			tv_phone.setText(company.getFirst_phone());
			et_phone.setText(company.getFirst_phone());
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_modify:
			btnControl(true);
			btn_cpinfo.setText("���渴��");
			break;
		case R.id.btn_cpinfo:
			save();
			btnControl(false);
			break;
		default:
			break;

		}
	}

	// �༭״̬����
	private void btnControl(boolean able) {
		Toast.makeText(this, " I am in." + able, Toast.LENGTH_SHORT).show();
		EditText[] ets = { et_name, et_address, et_area, et_detailaddress, et_company_type, et_lan_type, et_boot_time,
				et_shut_time, et_factory, et_contact, et_net_contact, et_hddsn, et_qrcode, et_net_phone, et_phone };
		if (!installed) {
			setEtsFocusable(ets, able);
			sw_boot_on_weekend.setEnabled(able);
		} else {

		}
	}

	private void save() {
		CompanyClass company = new CompanyClass();
		company.set_id(real_id);
		company.setCompany_name(et_name.getText().toString());
		company.setCompany_aera(et_area.getText().toString());
		company.setBoot_on_weekend(Integer.parseInt(tv_boot_on_weekend.getText().toString()));
		company.setCompany_address(et_address.getText().toString());

		db = new DataBaseServer(this);
		boolean success = db.update(company);

		if (success) {
			btn_cpinfo.setText("������Ϣ");
		}
	}

	private void setEtsFocusable(EditText[] ets, boolean able) {
		// Toast.makeText(this, ets.length + "", Toast.LENGTH_SHORT).show();
		for (int i = 0; i < ets.length; i++) {
			// Toast.makeText(this, ets[i].getId() + "",
			// Toast.LENGTH_SHORT).show();
			ets[i].setFocusableInTouchMode(able);
		}
	}

	private void cpinfo() {

	}
}
