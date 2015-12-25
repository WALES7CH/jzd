package com.jzd.record.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.jzd.record.DetailEditActivity;
import com.jzd.record.R;
import com.jzd.record.db.DbCityHelper;
import com.jzd.record.db.DataBaseServer;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn_install, btn_installed, btn_repair_search, btn_install_record, btn_repair_record,
			btn_import_installed, btn_export_installed;
	private DataBaseServer db;
	private DbCityHelper dbmanager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);

		init();
	}

	private void init() {

		// ��ʼ�����ݿ�
		DbInit();

		btn_install = (Button) findViewById(R.id.btn_install_search);
		btn_installed = (Button) findViewById(R.id.btn_installed_search);
		btn_repair_search = (Button) findViewById(R.id.btn_repair_search);
		btn_install_record = (Button) findViewById(R.id.btn_install_record);
		btn_repair_record = (Button) findViewById(R.id.btn_repair_record);
		btn_import_installed = (Button) findViewById(R.id.btn_import_installed);
		btn_export_installed = (Button) findViewById(R.id.btn_export_installed);

		btn_install.setOnClickListener(this);
		btn_installed.setOnClickListener(this);
		btn_repair_search.setOnClickListener(this);

		btn_install_record.setOnClickListener(this);
		btn_repair_record.setOnClickListener(this);
		btn_import_installed.setOnClickListener(this);
		btn_export_installed.setOnClickListener(this);
	}

	public void DbInit() {

		dbmanager = new DbCityHelper(this);
		dbmanager.openDatabase();
		dbmanager.closeDatabase();

		db = new DataBaseServer(this);
		/*
		 * String[] companyTemp = new String("����1/����2/����3").split("/");
		 * CompanyClass cc; for (int i = 0; i < companyTemp.length; i++) { cc =
		 * new CompanyClass(); cc.setCompany_name(companyTemp[i]);
		 * cc.setCompany_city("00" + i); cc.setHarddisk_no("11111111111111" +
		 * i); db.insert(cc); //info += '\n' + "add to database classes:" +
		 * c.toString(); }
		 */

	}

	@Override
	public void onClick(View v) {

		Intent intent = new Intent();

		// System.out.println("TAG:" + new Date().getTime() + "--" + v.getId());

		switch (v.getId()) {
		case R.id.btn_install_search:
			// Toast.makeText(this, "Ԥ��װ��ѯ", Toast.LENGTH_SHORT).show();
			intent.putExtra("installed", false);
			intent.setClass(this, InstalledSearchActivity.class);
			break;
		case R.id.btn_installed_search:
			intent.putExtra("installed", true);
			// Toast.makeText(this, "�Ѱ�װ��ѯ", Toast.LENGTH_SHORT).show();
			intent.setClass(this, InstalledSearchActivity.class);
			break;
		case R.id.btn_repair_record:
			// Toast.makeText(this, "Ѳ��ά��¼��", Toast.LENGTH_SHORT).show();
			intent.setClass(this, RepairSearchActivity.class);
			break;
		case R.id.btn_install_record:
			// Toast.makeText(this, R.string.str_btn_install_record,
			// Toast.LENGTH_SHORT).show();
			intent.setClass(this, DetailEditActivity.class);
			break;
		default:
			Toast.makeText(this, "δʵ�ֹ���", Toast.LENGTH_SHORT).show();
			break;
		}

		startActivity(intent);

	}
}
