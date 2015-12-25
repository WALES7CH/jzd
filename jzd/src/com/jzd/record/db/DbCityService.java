package com.jzd.record.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbCityService {
	private DatabaseHelper dbhelper;

	public DbCityService(Context context) {
		this.dbhelper = new DatabaseHelper(context);
	}

	public String[] getCitylist() {
		SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();

		String sql = "select * from t_city where level = 3 and province = 'ËÄ´¨' ";

		localSQLiteDatabase.execSQL(sql, null);

		return new String[5];
	}

	public String[] getCityAreas(String city) {

		return new String[5];
	}

}
