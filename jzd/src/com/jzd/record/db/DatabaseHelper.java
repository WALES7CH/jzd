package com.jzd.record.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "jzd.db"; //数据库名称
    private static final int version = 1; //数据库版本
	
	public DatabaseHelper(Context context) {
		super(context,DB_NAME,null,version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String sql = "CREATE TABLE T_INSTALL (" 
				+ " _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
				+ " company_name  VARCHAR(256) NOT NULL,"
				+ " company_city  VARCHAR(32) NOT NULL,"
				+ " company_address  VARCHAR(512),"
				+ " company_area  VARCHAR(128) NOT NULL,"
				+ " first_contact  VARCHAR(128),"
				+ " first_phone  INTEGER,"
				+ " sec_contact  VARCHAR(128),"
				+ " sec_phone  INTEGER,"
				+ " boot_time  VARCHAR(64),"
				+ " shut_time  VARCHAR(64),"
				+ " creat_date  VARCHAR(64) NOT NULL,"
				+ " modify_date  VARCHAR(64),"
				+ " chat_date  VARCHAR(64),"
				+ " install_date  VARCHAR(64),"
				+ " company_type  VARCHAR(64),"
				+ " factory  VARCHAR(64),"
				+ " lan_type  VARCHAR(64),"
				+ " boot_on_weekend  INTEGER DEFAULT 0,"
				+ " harddisk_no  VARCHAR(64),"
				+ " qrcode_no  VARCHAR(128),"
				+ " wifi_ssid  VARCHAR(128),"
				+ " wifi_password  VARCHAR(128),"
				+ " creator  VARCHAR(128),"
				+ " modifier  VARCHAR(128),"
				+ " note1  VARCHAR(1024),"
				+ " note2  VARCHAR(1024)"
				+ " )";
		
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS T_INSTALL");  
        onCreate(db);  
	}

}
