package com.jzd.record.activitys;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.jzd.record.R;
import com.jzd.record.db.CompanyClass;
import com.jzd.record.db.DataBaseServer;
import com.jzd.record.utils.CityAreaUtils;
import com.jzd.record.utils.DefaultSetting;
import com.jzd.record.utils.SpinnerUtils;
import com.tencent.connect.share.QQShare;
import com.tencent.share.BaseUIListener;
import com.tencent.tauth.Tencent;

public class DetailEditActivity extends Activity implements OnClickListener {
	private DataBaseServer db;
	private Button btn_modify, btn_cpinfo;
	private TextView tv_boot_on_weekend;
	private EditText et_name, et_address, et_devicelocation, et_boot_time,
			et_shut_time, et_main_contact, et_net_contact, et_hddsn, et_qrcode,
			et_net_phone, et_main_phone;
	private Switch sw_boot_on_weekend;
	private Spinner spi_company_type, spi_company_city, spi_company_area,
			spi_hddsn, spi_lan_type, spi_factory;

	private int real_id = 0;
	private boolean installed, isnew;
	public static Tencent mTencent;

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
		spi_hddsn = (Spinner) findViewById(R.id.spi_hddsn);
		spi_lan_type = (Spinner) findViewById(R.id.spi_lan_type);
		spi_factory = (Spinner) findViewById(R.id.spi_factory);

		tv_boot_on_weekend = (TextView) findViewById(R.id.tv_boot_on_weekend);

		et_name = (EditText) findViewById(R.id.et_name);
		et_address = (EditText) findViewById(R.id.et_address);
		et_devicelocation = (EditText) findViewById(R.id.et_devicelocation);

		et_boot_time = (EditText) findViewById(R.id.et_boot_time);
		et_shut_time = (EditText) findViewById(R.id.et_shut_time);
		et_main_contact = (EditText) findViewById(R.id.et_contact);
		et_net_contact = (EditText) findViewById(R.id.et_net_contact);
		et_hddsn = (EditText) findViewById(R.id.et_hddsn);
		et_qrcode = (EditText) findViewById(R.id.et_qrcode);
		et_main_phone = (EditText) findViewById(R.id.et_phone);
		et_net_phone = (EditText) findViewById(R.id.et_net_phone);
		sw_boot_on_weekend = (Switch) findViewById(R.id.sw_boot_on_weekend);
		btn_modify = (Button) findViewById(R.id.btn_modify);
		btn_cpinfo = (Button) findViewById(R.id.btn_cpinfo);

		// Log.e("isnew?", isnew + "");
		CityAreaUtils.loadCityAreaItems(spi_company_city, this, null);
		CityAreaUtils.loadCityAreaItems(spi_company_area, this,
				spi_company_city.getSelectedItem().toString());
		CityAreaUtils.loadHddsnItems(spi_hddsn, this);

		if (isnew) {// 新装
			this.setTitle("录入安装");
			btnControl(true);
		}

		// 读取并 设置控件值
		loadRecord();

		setListeners();

	}

	private void setListeners() {
		et_boot_time.setOnClickListener(this);
		et_shut_time.setOnClickListener(this);
		btn_modify.setOnClickListener(this);
		btn_cpinfo.setOnClickListener(this);
		et_qrcode.setOnClickListener(this);
		sw_boot_on_weekend
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean switch_on) {
						// Toast.makeText(DetailEditActivity.this, switch_on +
						// "",
						// Toast.LENGTH_SHORT).show();
						if (switch_on) {
							tv_boot_on_weekend.setText("1");
						} else {
							tv_boot_on_weekend.setText("0");
						}
					}

				});

		spi_company_city
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub

						CityAreaUtils.loadCityAreaItems(spi_company_area,
								DetailEditActivity.this, spi_company_city
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
		case R.id.et_boot_time:
			et_setTime(et_boot_time);
			break;
		case R.id.et_shut_time:

			et_setTime(et_shut_time);
			break;
		case R.id.et_qrcode:
			Intent intent = new Intent();
			intent.setClass(this, com.dtr.zxing.activity.CaptureActivity.class);
			// startActivity(intent);
			startActivityForResult(intent, 0);
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			et_qrcode.setText(data.getStringExtra("return"));
		}
	}

	// 编辑状态控制
	private void btnControl(boolean able) {
		// Toast.makeText(this, " I am in." + able, Toast.LENGTH_SHORT).show();
		EditText[] ets = { et_name, et_address, et_devicelocation,
				et_boot_time, et_shut_time, et_main_contact, et_net_contact,
				et_hddsn, et_qrcode, et_net_phone, et_main_phone };
		Spinner[] spis = { spi_company_type, spi_company_city,
				spi_company_area, spi_hddsn, spi_lan_type, spi_factory };
		if (!installed || isnew) {
			setEtsFocusable(ets, able);
			setSpisClickable(spis, able);
			sw_boot_on_weekend.setEnabled(able);
		} else {
			setEtsFocusable(ets, able);
			setSpisClickable(spis, able);
			sw_boot_on_weekend.setEnabled(able);
		}
	}

	private void save() {
		CompanyClass company = new CompanyClass();
		company.set_id(real_id);
		company.setCompany_name(et_name.getText().toString());
		company.setCompany_city(spi_company_city.getSelectedItem().toString());
		company.setCompany_aera(spi_company_area.getSelectedItem().toString());
		company.setBoot_on_weekend(Integer.parseInt(tv_boot_on_weekend
				.getText().toString()));
		company.setCompany_address(et_address.getText().toString());
		company.setDevice_location(et_devicelocation.getText().toString());
		company.setCompany_type(spi_company_type.getSelectedItem().toString());
		company.setBoot_time(et_boot_time.getText().toString());
		company.setShut_time(et_shut_time.getText().toString());
		company.setLan_type(spi_lan_type.getSelectedItem().toString());
		company.setHddsn(spi_hddsn.getSelectedItem().toString()
				+ et_hddsn.getText().toString());
		// Log.e("TAG HDDSN", company.getHddsn());
		company.setmain_contact(et_main_contact.getText().toString());
		company.setmain_phone(et_main_phone.getText().toString());
		company.setnet_contact(et_net_contact.getText().toString());
		company.setnet_phone(et_net_phone.getText().toString());
		company.setQrcode(et_qrcode.getText().toString());
		company.setFactory(spi_factory.getSelectedItem().toString());

		// Log.e("COMPANY TAG ", company.toString());

		db = new DataBaseServer(this);

		int rtn_id = 0;
		String msg = "";
		if (real_id == -1 && isnew) {
			rtn_id = db.insert(company);
			msg = "保存成功! + " + rtn_id;
		} else {
			rtn_id = db.update(company);
			msg = "保存成功!" + +rtn_id;
		}

		if (rtn_id > 0) {
			real_id = rtn_id;
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
			btn_cpinfo.setText("复制信息");
			copy();
		} else {
			msg = "保存失败!" + rtn_id;
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		}

	}

	// 剪贴板
	private ClipboardManager mClipboard = null;

	private void copy() {
		// Gets a handle to the clipboard service.
		if (null == mClipboard) {
			mClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

		}
		// Creates a new text clip to put on the clipboard
		ClipData clip = ClipData.newPlainText("simple text", genSendMsg());
		// Set the clipboard's primary clip.
		mClipboard.setPrimaryClip(clip);

		// onClickShare();
	}

	private void onClickShare() {
		mTencent = Tencent.createInstance("1104014907", this);
		final Bundle params = new Bundle();
		params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,
				QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
		params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
		params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");
		params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
				"http://www.qq.com/news/1.html");
		params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,
				"http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
		params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试应用222222");
		mTencent.shareToQQ(this, params, new BaseUIListener(this));
	}

	private String genSendMsg() {
		String sendMsg = "";
		StringBuilder sb = new StringBuilder();
		sb.append("单位名称：" + et_name.getText().toString() + "\n");
		sb.append("行政区域：" + spi_company_area.getSelectedItem().toString()
				+ "\n");
		sb.append("地址：" + spi_company_city.getSelectedItem().toString()
				+ spi_company_area.getSelectedItem().toString()
				+ et_address.getText().toString() + "\n");
		sb.append("摆放位置：" + et_devicelocation.getText().toString() + "\n");
		sb.append("单位性质：" + spi_company_type.getSelectedItem().toString()
				+ "\n");
		sb.append("连线方式：" + spi_lan_type.getSelectedItem().toString() + "\n");
		sb.append("开关机时间：" + et_boot_time.getText().toString() + " -- "
				+ et_shut_time.getText().toString() + "\n");
		if (tv_boot_on_weekend.getText().toString() == "1") {
			sb.append("周末是否开机：开机\n");
		} else {
			sb.append("周末是否开机：不开机\n");
		}

		sb.append("设备厂家：" + spi_factory.getSelectedItem().toString() + "\n");
		sb.append("联系人方式：" + et_main_contact.getText().toString() + "/"
				+ et_main_phone.getText().toString() + "\n");
		sb.append("终端编号：" + spi_hddsn.getSelectedItem().toString()
				+ et_hddsn.getText().toString() + "\n");
		sb.append("二维码：" + et_qrcode.getText().toString() + "\n");
		sendMsg = sb.toString();
		Log.e("sendMsg TAG", sendMsg);
		return sendMsg;
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
		if (company.getDevice_location() != null) {
			et_devicelocation.setText(company.getDevice_location());
		}
		if (company.getBoot_time() != null) {
			et_boot_time.setText(company.getBoot_time());
		} else {
			et_boot_time.setText(DefaultSetting.boot_time);
		}
		if (company.getShut_time() != null) {
			et_shut_time.setText(company.getShut_time());
		} else {
			et_shut_time.setText(DefaultSetting.shut_time);
		}
		if (company.getmain_contact() != null) {
			et_main_contact.setText(company.getmain_contact());
		}
		if (company.getnet_contact() != null) {
			et_net_contact.setText(company.getnet_contact());
		}
		if (company.getnet_phone() != null) {
			et_net_phone.setText(company.getnet_phone());
		}
		if (company.getHddsn() != null) {
			et_hddsn.setText(company.getHddsn());
		}
		if (company.getQrcode() != null) {
			et_qrcode.setText(company.getQrcode());
		}
		if (company.getmain_phone() != null) {
			et_main_phone.setText(company.getmain_phone());
		}

		// 下拉控件设置
		if (company.getCompany_city() != null) {
			SpinnerUtils.setSelectedItem(spi_company_city,
					company.getCompany_city());
		}
		if (company.getCompany_aera() != null) {
			SpinnerUtils.setSelectedItem(spi_company_area,
					company.getCompany_aera());
		}
		if (company.getHddsn() != null && company.getHddsn().length() >= 8) {
			SpinnerUtils.setSelectedItem(spi_hddsn, company.getHddsn()
					.substring(0, 4));
			et_hddsn.setText(company.getHddsn().substring(4,
					company.getHddsn().length()));
		}
		if (company.getCompany_type() != null) {
			SpinnerUtils.setSelectedItem(spi_company_type,
					company.getCompany_type());
		}
		if (company.getLan_type() != null) {
			SpinnerUtils.setSelectedItem(spi_lan_type, company.getLan_type());
		}
		if (company.getFactory() != null) {
			SpinnerUtils.setSelectedItem(spi_factory, company.getFactory());
		}

	}

	private void setEtsFocusable(EditText[] ets, boolean able) {
		for (int i = 0; i < ets.length; i++) {
			ets[i].setFocusableInTouchMode(able);
		}
	}

	private void setSpisClickable(Spinner[] spis, boolean able) {
		for (int i = 0; i < spis.length; i++) {
			spis[i].setClickable(able);
		}
	}

	private void cpinfo() {

	}

	private void et_setTime(final EditText et) {
		int hourOfDay = 0;
		int minute = 0;
		switch (et.getId()) {
		case R.id.et_boot_time:
			hourOfDay = 8;
			minute = 30;
			break;
		case R.id.et_shut_time:
			hourOfDay = 18;
			minute = 00;
			break;
		default:
			hourOfDay = 12;
			minute = 00;
			break;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		final Calendar c = Calendar.getInstance();
		TimePickerDialog timePickerDialog = new TimePickerDialog(
				DetailEditActivity.this,
				new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {
						String str_time = "";
						if (hourOfDay < 10) {
							str_time += "0" + hourOfDay + ":";
						} else {
							str_time += "" + hourOfDay + ":";
						}
						if (minute < 10) {
							str_time += "0" + minute;
						} else {
							str_time += "" + minute;
						}
						et.setText(str_time);
					}

				}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);

		timePickerDialog.show();
	}
}
