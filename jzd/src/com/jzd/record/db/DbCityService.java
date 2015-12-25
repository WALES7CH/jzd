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

	// �ҳ���
	public List<String> getCitylist() {
		List<String> cityLst = new ArrayList<String>();
		SQLiteDatabase localSQLiteDatabase = this.dbcityhelper.getWritableDatabase();

		String sql = "SELECT c.* FROM city c ,province p WHERE c.pid=p.id and p.name = ?";

		Cursor cursor = localSQLiteDatabase.rawQuery(sql, new String[] { "�Ĵ�ʡ" });
		// Log.e("TAG", cursor.getCount() + "");
		while (cursor.moveToNext()) {
			cityLst.add(cursor.getString(cursor.getColumnIndex("name")));
		}
		return cityLst;
	}

	// ����������
	public List<String> getArealist(String city) {
		List<String> areaLst = new ArrayList<String>();

		SQLiteDatabase localSQLiteDatabase = this.dbcityhelper.getWritableDatabase();

		String sql = "SELECT a.* FROM city c ,area a WHERE a.pid=c.id and c.name = ?";

		Cursor cursor = localSQLiteDatabase.rawQuery(sql, new String[] { city });
		// Log.e("TAG", cursor.getCount() + "");
		while (cursor.moveToNext()) {
			areaLst.add(cursor.getString(cursor.getColumnIndex("name")));
		}
		areaLst.add("��������");
		return areaLst;
	}

}
