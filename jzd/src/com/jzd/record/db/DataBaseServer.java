package com.jzd.record.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseServer {

	private DatabaseHelper dbhelper;

	public DataBaseServer(Context context) {
		this.dbhelper = new DatabaseHelper(context);
	}

	public boolean insert(CompanyClass entity) {
		try {

			ContentValues cv = new ContentValues();
			cv.put("company_name", entity.getCompany_name());
			cv.put("company_city", entity.getCompany_city());
			cv.put("company_address", entity.getCompany_address());
			cv.put("company_area", entity.getCompany_aera());
			cv.put("first_contact", entity.getFirst_contact());
			cv.put("first_phone", entity.getFirst_phone());
			cv.put("sec_contact", entity.getSec_contact());
			cv.put("sec_phone", entity.getSec_phone());
			cv.put("boot_time", entity.getBoot_time());
			cv.put("shut_time", entity.getShut_time());
			cv.put("company_type", entity.getCompany_type());
			cv.put("lan_type", entity.getLan_type());
			cv.put("boot_on_weekend", entity.getBoot_on_weekend());
			cv.put("wifi_ssid", entity.getWifi_ssid());
			cv.put("wifi_password", entity.getWifi_password());
			cv.put("note1", entity.getNote1());
			cv.put("note2", entity.getNote2());

			cv.put("creat_date", new Date().toLocaleString());
			cv.put("chat_date", new Date().toGMTString());
			cv.put("creator", "");

			SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();

			Long rtnId = localSQLiteDatabase.insert("t_install", null, cv);

			localSQLiteDatabase.close();

			if (rtnId != -1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	public boolean update(CompanyClass entity) {
		try {
			SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put("company_name", entity.getCompany_name());
			cv.put("company_city", entity.getCompany_city());
			cv.put("company_address", entity.getCompany_address());
			cv.put("company_area", entity.getCompany_aera());
			cv.put("first_contact", entity.getFirst_contact());
			cv.put("first_phone", entity.getFirst_phone());
			cv.put("sec_contact", entity.getSec_contact());
			cv.put("sec_phone", entity.getSec_phone());
			cv.put("boot_time", entity.getBoot_time());
			cv.put("shut_time", entity.getShut_time());
			cv.put("company_type", entity.getCompany_type());
			cv.put("lan_type", entity.getLan_type());
			cv.put("boot_on_weekend", entity.getBoot_on_weekend());
			cv.put("wifi_ssid", entity.getWifi_ssid());
			cv.put("wifi_password", entity.getWifi_password());
			cv.put("note1", entity.getNote1());
			cv.put("note2", entity.getNote2());

			cv.put("creat_date", new Date().toLocaleString());
			cv.put("chat_date", new Date().toGMTString());
			cv.put("creator", "");

			int t_id = entity.get_id();

			Log.e("TAG", entity.toString());
			int rtnId = localSQLiteDatabase.update("t_install", cv, "_id = ? ", new String[] { t_id + "" });

			localSQLiteDatabase.close();

			if (rtnId != -1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			Log.e("TAG", e.getMessage());
		}
		return false;

	}

	// 根据关键字返回一条或多条记录
	public List<Map<String, Object>> search(String cityname, String otherkey, boolean installed) {

		if (cityname.equals(null) || cityname == "") {
			Log.println(1, "TAG", "城市为空啊！怎么查？");
			return null;
		}

		List<Map<String, Object>> tmp_list = new ArrayList<Map<String, Object>>();
		SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();

		String sql = "select * from t_install where company_city = ? ";
		if (!otherkey.equals(null) && otherkey != "") {
			sql += " and company_name like '%" + otherkey + "%' ";
		}
		if (installed) {
			sql += " and harddisk_no is not null ";
		} else {
			sql += " and harddisk_no is null ";
		}
		sql += "  order by company_name asc ";

		Log.e("TAG", sql);

		Cursor localCursor = localSQLiteDatabase.rawQuery(sql, new String[] { cityname });
		int i = 0;
		while (localCursor.moveToNext()) {
			i++;
			CompanyClass temp = new CompanyClass();
			Map<String, Object> map = new HashMap<String, Object>();
			temp.setCompany_name(localCursor.getString(localCursor.getColumnIndex("company_name")));

			temp.set_id(localCursor.getInt(localCursor.getColumnIndex("_id")));
			temp.setHarddisk_no(localCursor.getString(localCursor.getColumnIndex("harddisk_no")));
			temp.setCompany_address(localCursor.getString(localCursor.getColumnIndex("company_address")));

			// System.out.println("TAG : " + tmp_list.toString());

			map.put("real_id", temp.get_id());
			map.put("index", i);
			map.put("company_name", temp.getCompany_name());
			map.put("harddisk_no", temp.getHarddisk_no());
			map.put("company_address", temp.getCompany_address());

			tmp_list.add(map);
		}

		localSQLiteDatabase.close();
		return tmp_list;

	}

	// 根据ID找对应的记录
	public CompanyClass findRecordById(int _id) {
		CompanyClass company = new CompanyClass();

		SQLiteDatabase localSQLiteDatabase = this.dbhelper.getReadableDatabase();

		Cursor localCursor = localSQLiteDatabase.rawQuery("select * from t_install " + " where _id = ?",
				new String[] { _id + "" });

		while (localCursor.moveToNext()) {

			company.setCompany_name(localCursor.getString(localCursor.getColumnIndex("company_name")));
			company.setCompany_city(localCursor.getString(localCursor.getColumnIndex("company_city")));
			company.setCompany_aera(localCursor.getString(localCursor.getColumnIndex("company_area")));
			company.setCompany_address(localCursor.getString(localCursor.getColumnIndex("company_address")));
			company.set_id(localCursor.getInt(localCursor.getColumnIndex("_id")));
			company.setBoot_on_weekend(localCursor.getInt(localCursor.getColumnIndex("boot_on_weekend")));
			company.setBoot_time(localCursor.getString(localCursor.getColumnIndex("boot_time")));
			company.setChat_date(localCursor.getString(localCursor.getColumnIndex("chat_date")));
			company.setCompany_type(localCursor.getString(localCursor.getColumnIndex("company_type")));
			company.setFactory(localCursor.getString(localCursor.getColumnIndex("factory")));
			company.setFirst_contact(localCursor.getString(localCursor.getColumnIndex("first_contact")));
			company.setFirst_phone(localCursor.getString(localCursor.getColumnIndex("first_phone")));
			company.setHarddisk_no(localCursor.getString(localCursor.getColumnIndex("harddisk_no")));
			company.setLan_type(localCursor.getString(localCursor.getColumnIndex("lan_type")));
			company.setNote1(localCursor.getString(localCursor.getColumnIndex("note1")));
			company.setNote2(localCursor.getString(localCursor.getColumnIndex("note2")));
			company.setQrcode_no(localCursor.getString(localCursor.getColumnIndex("qrcode_no")));
			company.setSec_contact(localCursor.getString(localCursor.getColumnIndex("sec_contact")));
			company.setSec_phone(localCursor.getString(localCursor.getColumnIndex("sec_phone")));
			company.setShut_time(localCursor.getString(localCursor.getColumnIndex("shut_time")));
			company.setWifi_password(localCursor.getString(localCursor.getColumnIndex("wifi_password")));
			company.setWifi_ssid(localCursor.getString(localCursor.getColumnIndex("wifi_ssid")));
		}

		return company;
	}

}
