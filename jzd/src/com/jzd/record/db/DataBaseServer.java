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

	public int insert(CompanyClass entity) {
		try {

			ContentValues cv = new ContentValues();
			cv.put("company_name", entity.getCompany_name());
			cv.put("company_city", entity.getCompany_city());
			cv.put("company_address", entity.getCompany_address());
			cv.put("device_location", entity.getDevice_location());
			cv.put("company_area", entity.getCompany_aera());
			cv.put("main_contact", entity.getmain_contact());
			cv.put("main_phone", entity.getmain_phone());
			cv.put("net_contact", entity.getnet_contact());
			cv.put("net_phone", entity.getnet_phone());
			cv.put("factory", entity.getFactory());
			cv.put("boot_time", entity.getBoot_time());
			cv.put("shut_time", entity.getShut_time());
			cv.put("company_type", entity.getCompany_type());
			cv.put("lan_type", entity.getLan_type());
			cv.put("boot_on_weekend", entity.getBoot_on_weekend());
			cv.put("hddsn", entity.getHddsn());
			cv.put("qrcode", entity.getQrcode());
			cv.put("wifi_ssid", entity.getWifi_ssid());
			cv.put("wifi_password", entity.getWifi_password());
			cv.put("note1", entity.getNote1());
			cv.put("note2", entity.getNote2());

			cv.put("creat_date", new Date().toLocaleString());
			cv.put("chat_date", new Date().toGMTString());
			cv.put("creator", "");

			SQLiteDatabase localSQLiteDatabase = this.dbhelper
					.getWritableDatabase();

			Long rtnId = localSQLiteDatabase.insert("t_install", null, cv);

			localSQLiteDatabase.close();

			if (rtnId != -1) {
				return Integer.parseInt("111");
			} else {
				return -1;
			}
		} catch (Exception e) {
			Log.e("TAG", e.toString());
			return -1;
		}
	}

	public int update(CompanyClass entity) {
		try {
			SQLiteDatabase localSQLiteDatabase = this.dbhelper
					.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put("company_name", entity.getCompany_name());
			cv.put("company_city", entity.getCompany_city());
			cv.put("company_address", entity.getCompany_address());
			cv.put("device_location", entity.getDevice_location());
			cv.put("company_area", entity.getCompany_aera());
			cv.put("main_contact", entity.getmain_contact());
			cv.put("main_phone", entity.getmain_phone());
			cv.put("net_contact", entity.getnet_contact());
			cv.put("net_phone", entity.getnet_phone());
			cv.put("boot_time", entity.getBoot_time());
			cv.put("factory", entity.getFactory());
			cv.put("shut_time", entity.getShut_time());
			cv.put("company_type", entity.getCompany_type());
			cv.put("lan_type", entity.getLan_type());
			cv.put("boot_on_weekend", entity.getBoot_on_weekend());
			cv.put("wifi_ssid", entity.getWifi_ssid());
			cv.put("wifi_password", entity.getWifi_password());
			cv.put("hddsn", entity.getHddsn());
			cv.put("qrcode", entity.getQrcode());
			cv.put("note1", entity.getNote1());
			cv.put("note2", entity.getNote2());

			cv.put("creat_date", new Date().toLocaleString());
			cv.put("chat_date", new Date().toGMTString());
			cv.put("creator", "");

			int t_id = entity.get_id();

			Log.e("TAG", entity.toString());
			int rtnId = localSQLiteDatabase.update("t_install", cv, "_id = ? ",
					new String[] { t_id + "" });

			localSQLiteDatabase.close();

			if (rtnId != -1) {
				return rtnId;
			} else {
				return -1;
			}
		} catch (Exception e) {
			Log.e("TAG", e.getMessage());
		}
		return -1;

	}

	public boolean deleteById(String id) {
		try {
			SQLiteDatabase localSQLiteDatabase = this.dbhelper
					.getWritableDatabase();
			int rtn_code = localSQLiteDatabase.delete("t_install", "_id = ?",
					new String[] { id });

			localSQLiteDatabase.close();
			if (rtn_code == 1) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 查询所有数据
	 * 
	 * */
	public List<CompanyClass> queryAll(String cityName) {
		SQLiteDatabase db = this.dbhelper.getReadableDatabase();
		List<CompanyClass> list = new ArrayList<CompanyClass>();
		Cursor cursor = db.query("t_install", null, " company_city = ?",
				new String[] { cityName }, null, null, null);

		while (cursor.moveToNext()) {
			CompanyClass company = new CompanyClass();
			company.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
			company.setHddsn(cursor.getString(cursor.getColumnIndex("hddsn")));
			company.setQrcode(cursor.getString(cursor.getColumnIndex("qrcode")));
			company.setCompany_name(cursor.getString(cursor
					.getColumnIndex("company_name")));
			company.setCompany_address(cursor.getString(cursor
					.getColumnIndex("company_city"))
					+ cursor.getString(cursor.getColumnIndex("company_area"))
					+ cursor.getString(cursor.getColumnIndex("company_address")));
			company.setmain_contact(cursor.getString(cursor
					.getColumnIndex("main_contact")));
			company.setmain_phone(cursor.getString(cursor
					.getColumnIndex("main_phone")));
			company.setBoot_time(cursor.getString(cursor
					.getColumnIndex("boot_time")));
			company.setShut_time(cursor.getString(cursor
					.getColumnIndex("shut_time")));
			company.setFactory(cursor.getString(cursor
					.getColumnIndex("factory")));
			company.setDevice_location(cursor.getString(cursor
					.getColumnIndex("device_location")));
			company.setCompany_aera(cursor.getString(cursor
					.getColumnIndex("company_area")));
			company.setCompany_type(cursor.getString(cursor
					.getColumnIndex("company_type")));

			list.add(company);
		}
		db.close();
		return list;
	}

	// 根据关键字返回一条或多条记录
	public List<Map<String, Object>> search(String cityname, String otherkey,
			boolean installed) {

		if (cityname.equals(null) || cityname == "") {
			Log.println(1, "TAG", "城市为空啊！怎么查？");
			return null;
		}

		List<Map<String, Object>> tmp_list = new ArrayList<Map<String, Object>>();
		SQLiteDatabase localSQLiteDatabase = this.dbhelper
				.getWritableDatabase();

		String sql = "select * from t_install where company_city = ? ";
		if (!otherkey.equals(null) && otherkey != "") {
			sql += " and company_name like '%" + otherkey + "%' ";
		}
		if (installed) {
			sql += " and hddsn is not null ";
		} else {
			sql += " and hddsn is null ";
		}
		sql += "  order by _id desc,creat_date desc ";

		Log.e("TAG", sql);

		Cursor localCursor = localSQLiteDatabase.rawQuery(sql,
				new String[] { cityname });
		int i = 0;
		while (localCursor.moveToNext()) {
			i++;
			CompanyClass temp = new CompanyClass();
			Map<String, Object> map = new HashMap<String, Object>();
			temp.setCompany_name(localCursor.getString(localCursor
					.getColumnIndex("company_name")));

			temp.set_id(localCursor.getInt(localCursor.getColumnIndex("_id")));
			temp.setHddsn(localCursor.getString(localCursor
					.getColumnIndex("hddsn")));
			temp.setCompany_address(localCursor.getString(localCursor
					.getColumnIndex("company_address")));

			// System.out.println("TAG : " + tmp_list.toString());

			map.put("real_id", temp.get_id());
			map.put("index", i);
			map.put("company_name", temp.getCompany_name());
			map.put("hddsn", temp.getHddsn());
			map.put("company_address", temp.getCompany_address());

			tmp_list.add(map);
		}

		localSQLiteDatabase.close();
		return tmp_list;

	}

	// 根据ID找对应的记录
	public CompanyClass findRecordById(int _id) {
		CompanyClass company = new CompanyClass();

		SQLiteDatabase localSQLiteDatabase = this.dbhelper
				.getReadableDatabase();

		Cursor localCursor = localSQLiteDatabase.rawQuery(
				"select * from t_install " + " where _id = ?",
				new String[] { _id + "" });

		while (localCursor.moveToNext()) {

			company.setCompany_name(localCursor.getString(localCursor
					.getColumnIndex("company_name")));
			company.setCompany_city(localCursor.getString(localCursor
					.getColumnIndex("company_city")));
			company.setCompany_aera(localCursor.getString(localCursor
					.getColumnIndex("company_area")));
			company.setCompany_address(localCursor.getString(localCursor
					.getColumnIndex("company_address")));
			company.setDevice_location(localCursor.getString(localCursor
					.getColumnIndex("device_location")));
			company.set_id(localCursor.getInt(localCursor.getColumnIndex("_id")));
			company.setBoot_on_weekend(localCursor.getInt(localCursor
					.getColumnIndex("boot_on_weekend")));
			company.setBoot_time(localCursor.getString(localCursor
					.getColumnIndex("boot_time")));
			company.setChat_date(localCursor.getString(localCursor
					.getColumnIndex("chat_date")));
			company.setCompany_type(localCursor.getString(localCursor
					.getColumnIndex("company_type")));
			company.setFactory(localCursor.getString(localCursor
					.getColumnIndex("factory")));
			company.setmain_contact(localCursor.getString(localCursor
					.getColumnIndex("main_contact")));
			company.setmain_phone(localCursor.getString(localCursor
					.getColumnIndex("main_phone")));
			company.setHddsn(localCursor.getString(localCursor
					.getColumnIndex("hddsn")));
			company.setLan_type(localCursor.getString(localCursor
					.getColumnIndex("lan_type")));
			company.setNote1(localCursor.getString(localCursor
					.getColumnIndex("note1")));
			company.setNote2(localCursor.getString(localCursor
					.getColumnIndex("note2")));
			company.setQrcode(localCursor.getString(localCursor
					.getColumnIndex("qrcode")));
			company.setnet_contact(localCursor.getString(localCursor
					.getColumnIndex("net_contact")));
			company.setnet_phone(localCursor.getString(localCursor
					.getColumnIndex("net_phone")));
			company.setShut_time(localCursor.getString(localCursor
					.getColumnIndex("shut_time")));
			company.setWifi_password(localCursor.getString(localCursor
					.getColumnIndex("wifi_password")));
			company.setWifi_ssid(localCursor.getString(localCursor
					.getColumnIndex("wifi_ssid")));
		}

		localSQLiteDatabase.close();
		return company;
	}

}
