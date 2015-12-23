package com.jzd.record.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseServer {

	private DatabaseHelper dbhelper;

	public DataBaseServer(Context context) {
		this.dbhelper = new DatabaseHelper(context);
	}

	public void insert(CompanyClass entity) {
		SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
		Object[] arrayOfObject = new Object[3];
		arrayOfObject[0] = entity.getCompany_name();
		arrayOfObject[1] = entity.getCompany_city();
		arrayOfObject[2] = entity.getHarddisk_no();
		localSQLiteDatabase.execSQL("insert into t_install(company_name,company_city,harddisk_no) values(?,?,?)",
				arrayOfObject);
		localSQLiteDatabase.close();
	}

	public void update(CompanyClass entity) {

		SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
		Object[] arrayOfObject = new Object[3];
		arrayOfObject[0] = entity.getCompany_name();
		arrayOfObject[1] = entity.getCompany_city();
		arrayOfObject[2] = entity.get_id();
		localSQLiteDatabase.execSQL("update t_install set company_name = ?  company_city = ?  where _id = ?",
				arrayOfObject);
		localSQLiteDatabase.close();
	}

	// 根据关键字返回一条或多条记录
	public List<Map<String, Object>> search(String cityname, String otherkey) {

		if (cityname.equals(null) || cityname == "") {
			Log.println(1, "TAG", "城市为空啊！怎么查？");
			return null;
		}

		List<Map<String, Object>> tmp_list = new ArrayList<Map<String, Object>>();
		SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();

		String sql = "select * from t_install where company_city = ? ";
		if (!otherkey.equals(null) && otherkey != "") {

			sql += " and harddisk_no like '%" + otherkey + "%' ";
		}
		sql += "  order by company_name asc ";

		Cursor localCursor = localSQLiteDatabase.rawQuery(sql, new String[] { cityname });
		int i = 0;
		while (localCursor.moveToNext()) {
			i++;
			CompanyClass temp = new CompanyClass();
			Map<String, Object> map = new HashMap<String, Object>();
			temp.setCompany_name(localCursor.getString(localCursor.getColumnIndex("company_name")));

			temp.set_id(localCursor.getInt(localCursor.getColumnIndex("_id")));
			temp.setHarddisk_no(localCursor.getString(localCursor.getColumnIndex("harddisk_no")));

			System.out.println("TAG : " + tmp_list.toString());

			map.put("real_id", temp.get_id());
			map.put("index", i);
			map.put("company_name", temp.getCompany_name());
			map.put("harddisk_no", temp.getHarddisk_no());

			tmp_list.add(map);
		}

		localSQLiteDatabase.close();
		return tmp_list;

	}

	// 根据ID找对应的记录
	public CompanyClass findRecordById(String _id) {
		CompanyClass company = new CompanyClass();

		SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();

		Cursor localCursor = localSQLiteDatabase.rawQuery("select * from t_install " + " where _id = ?",
				new String[] { _id + "" });

		while (localCursor.moveToNext()) {
			company.setCompany_name(localCursor.getString(localCursor.getColumnIndex("company_name")));
			company.set_id(localCursor.getInt(localCursor.getColumnIndex("_id")));
			company.setHarddisk_no(localCursor.getString(localCursor.getColumnIndex("harddisk_no")));
		}

		return company;
	}

}
