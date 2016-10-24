package com.jzd.record.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jzd.record.R;
import com.jzd.record.db.CompanyClass;
import com.jzd.record.db.DataBaseServer;
import com.jzd.record.db.DbCityHelper;
import com.jzd.record.elements.CustomImageButton;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn_install, btn_installed, btn_repair_search,
			btn_install_record, btn_repair_record, btn_import_installed,
			btn_export_installed, btn_set_default_city, btn_login;
	private DataBaseServer db;
	private DbCityHelper dbmanager;

	private LinearLayout ll_install, ll_repair, ll_export, ll_setting; // main布局中包裹本按钮的容器
	private CustomImageButton cbtn_install, cbtn_installed,
			cbtn_install_record, cbtn_repair_record, cbtn_import_installed,
			cbtn_export_installed, cbtn_set_default_city, cbtn_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);

		createBtns();

		// init();
	}

	private void createBtns() {
		// 获取包裹本按钮的容器
		ll_install = (LinearLayout) findViewById(R.id.ll_install);
		ll_repair = (LinearLayout) findViewById(R.id.ll_repair);
		ll_export = (LinearLayout) findViewById(R.id.ll_export);
		ll_setting = (LinearLayout) findViewById(R.id.ll_setting);

		cbtn_install_record = new CustomImageButton(this,
				R.id.btn_install_record, R.drawable.ae1,
				R.string.str_btn_install_record);
		cbtn_install = new CustomImageButton(this, R.id.btn_install_search,
				R.drawable.ai1, R.string.install_search);
		cbtn_installed = new CustomImageButton(this, R.id.btn_installed_search,
				R.drawable.ps1, R.string.installed_search);
		cbtn_export_installed = new CustomImageButton(this,
				R.id.btn_export_installed, R.drawable.lr1,
				R.string.export_installed);
		cbtn_set_default_city = new CustomImageButton(this,
				R.id.btn_set_default_city, R.drawable.id1,
				R.string.set_default_city);

		// 将我们自定义的Button添加进这个容器
		ll_install.addView(cbtn_install_record);
		ll_install.addView(cbtn_install);
		ll_install.addView(cbtn_installed);

		ll_export.addView(cbtn_export_installed);
		ll_setting.addView(cbtn_set_default_city);

		cbtn_install.setOnClickListener(this);
		cbtn_installed.setOnClickListener(this);
		cbtn_install_record.setOnClickListener(this);
		cbtn_export_installed.setOnClickListener(this);
		cbtn_set_default_city.setOnClickListener(this);

	}

	private void init() {

		// 初始化数据库
		DbInit();

		btn_install = (Button) findViewById(R.id.btn_install_search);
		btn_installed = (Button) findViewById(R.id.btn_installed_search);
		btn_repair_search = (Button) findViewById(R.id.btn_repair_search);
		btn_install_record = (Button) findViewById(R.id.btn_install_record);
		btn_repair_record = (Button) findViewById(R.id.btn_repair_record);
		btn_import_installed = (Button) findViewById(R.id.btn_import_installed);
		btn_export_installed = (Button) findViewById(R.id.btn_export_installed);
		btn_set_default_city = (Button) findViewById(R.id.btn_set_default_city);
		btn_login = (Button) findViewById(R.id.btn_login);

		btn_install.setOnClickListener(this);
		btn_installed.setOnClickListener(this);
		btn_repair_search.setOnClickListener(this);

		btn_install_record.setOnClickListener(this);
		btn_repair_record.setOnClickListener(this);
		btn_import_installed.setOnClickListener(this);
		btn_export_installed.setOnClickListener(this);
		btn_set_default_city.setOnClickListener(this);
		btn_login.setOnClickListener(this);
	}

	public void DbInit() {

		dbmanager = new DbCityHelper(this);
		dbmanager.openDatabase();
		dbmanager.closeDatabase();

		db = new DataBaseServer(this);

		String[] companyTemp = new String("测试1/测试2/测试3").split("/");
		CompanyClass cc;

		for (int i = 0; i < companyTemp.length; i++) {
			cc = new CompanyClass();
			cc.setCompany_name(companyTemp[i]);
			cc.setCompany_city("00" + i);
			cc.setHddsn("W4X0523" + i);
			// db.insert(cc); // info += '\n' + "add to database classes:" +
			// cc.toString();
		}

	}

	@Override
	public void onClick(View v) {

		Intent intent = new Intent();

		// System.out.println("TAG:" + new Date().getTime() + "--" + v.getId());

		int id = v.getId();
		System.out.println(id);

		switch (v.getId()) {
		case R.id.btn_install_search:
			// Toast.makeText(this, "预安装查询", Toast.LENGTH_SHORT).show();
			intent.putExtra("installed", false);
			intent.putExtra("isnew", false);
			intent.setClass(this, InstalledSearchActivity.class);
			break;
		case R.id.btn_installed_search:
			intent.putExtra("installed", true);
			intent.putExtra("isnew", false);
			// Toast.makeText(this, "已安装查询", Toast.LENGTH_SHORT).show();
			intent.setClass(this, InstalledSearchActivity.class);
			break;
		case R.id.btn_repair_record:
			// Toast.makeText(this, "巡检维修录入", Toast.LENGTH_SHORT).show();
			intent.setClass(this, RepairSearchActivity.class);
			break;
		case R.id.btn_install_record:
			// Toast.makeText(this, R.string.str_btn_install_record,
			// Toast.LENGTH_SHORT).show();
			intent.putExtra("isnew", true);
			intent.setClass(this, DetailEditActivity.class);
			break;
		case R.id.btn_set_default_city:
			intent.setClass(this, SetDefaultCityActivity.class);
			break;
		case R.id.btn_export_installed:
			intent.setClass(this, ExportSearchActivity.class);
			break;

		case R.id.btn_login:
			intent.setClass(this, LoginActivity.class);
			break;
		default:
			Toast.makeText(this, "未实现功能", Toast.LENGTH_SHORT).show();
			break;
		}

		startActivity(intent);

	}

	private boolean isOpenNetwork() {
		ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager.getActiveNetworkInfo() != null) {
			return connManager.getActiveNetworkInfo().isAvailable();
		}

		return false;
	}
}
