package com.jzd.record.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbCityService {
	private DbCityHelper dbcityhelper;

	public DbCityService(Context context) {
		this.dbcityhelper = new DbCityHelper(context);
	}

	// 找城市
	public List<String> getCitylist() {
		List<String> cityLst = new ArrayList<String>();
		SQLiteDatabase localSQLiteDatabase = this.dbcityhelper.getReadableDatabase();

		String sql = "SELECT c.* FROM city c ,province p WHERE c.pid=p.id and p.name = ? order by isdefault desc";

		Cursor cursor = localSQLiteDatabase.rawQuery(sql, new String[] { "四川省" });
		// Log.e("TAG", cursor.getCount() + "");
		while (cursor.moveToNext()) {
			cityLst.add(cursor.getString(cursor.getColumnIndex("name")));
		}
		// cityLst.add("绵阳市");
		return cityLst;
	}

	// 找行政区域
	public List<String> getArealist(String city) {
		List<String> areaLst = new ArrayList<String>();

		SQLiteDatabase localSQLiteDatabase = this.dbcityhelper.getReadableDatabase();

		String sql = "SELECT a.* FROM city c ,area a WHERE a.pid=c.id and c.name = ?";

		Cursor cursor = localSQLiteDatabase.rawQuery(sql, new String[] { city });
		// Log.e("TAG", cursor.getCount() + "");
		while (cursor.moveToNext()) {
			areaLst.add(cursor.getString(cursor.getColumnIndex("name")));
		}
		areaLst.add("其他区县");
		return areaLst;
	}

	public String getDefaultCity() {
		String defaultCity = null;
		SQLiteDatabase localSQLiteDatabase = this.dbcityhelper.getWritableDatabase();

		String sql = "SELECT c.* FROM city c ,province p WHERE c.pid=p.id and p.name = ? and c.isdefault = 1";

		Cursor cursor = localSQLiteDatabase.rawQuery(sql, new String[] { "四川省" });
		// Log.e("TAG", cursor.getCount() + "");
		while (cursor.moveToNext()) {
			defaultCity = cursor.getString(cursor.getColumnIndex("name"));
			break;
		}
		return defaultCity;
	}

	public boolean updateDefaultCity(String newCity, String oldCity) {
		try {
			SQLiteDatabase localSQLiteDatabase = this.dbcityhelper.getWritableDatabase();

			String updateSql_new = "update city set isdefault = 1 where name = ? ";
			String updatesql_old = "update city set isdefault = 0 where name = ? ";

			localSQLiteDatabase.execSQL(updateSql_new, new String[] { newCity });
			localSQLiteDatabase.execSQL(updatesql_old, new String[] { oldCity });
			localSQLiteDatabase.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 新增硬盘号前缀
	 * @param hddsn
	 * @return
	 */
	public boolean InsertHddsn(String hddsn,String factory) {
		try {
			SQLiteDatabase localSQLiteDatabase = this.dbcityhelper.getWritableDatabase();

			String insertSql = "insert into t_hddsn(hddsn,factory) values(?,?)";

			localSQLiteDatabase.execSQL(insertSql, new String[] { hddsn,factory });
			localSQLiteDatabase.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// 列出所有的硬盘号前缀
		public List<String> getHddsnlist() {
			List<String> hddsnLst = new ArrayList<String>();

			SQLiteDatabase localSQLiteDatabase = this.dbcityhelper.getReadableDatabase();

			String sql = "SELECT hddsn from t_hddsn";

			Cursor cursor = localSQLiteDatabase.rawQuery(sql, new String[] { });
			// Log.e("TAG", cursor.getCount() + "");
			while (cursor.moveToNext()) {
				hddsnLst.add(cursor.getString(cursor.getColumnIndex("hddsn")));
			}
			return hddsnLst;
		}

}
