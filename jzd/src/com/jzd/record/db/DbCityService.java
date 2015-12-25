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

	public List<String> getCitylist() {
		List<String> cityLst = new ArrayList<String>();
		SQLiteDatabase localSQLiteDatabase = this.dbcityhelper.getWritableDatabase();

		String sql = "SELECT c.* FROM city c ,province p WHERE c.pid=p.id and p.name = ?";

		Cursor cursor = localSQLiteDatabase.rawQuery(sql, new String[] { "ËÄ´¨Ê¡" });
		Log.e("TAG", cursor.getCount() + "");
		while (cursor.moveToNext()) {
			cityLst.add(cursor.getString(cursor.getColumnIndex("name")));
		}
		return cityLst;
	}

	public String[] getCityAreas(String city) {

		return new String[5];
	}

}
