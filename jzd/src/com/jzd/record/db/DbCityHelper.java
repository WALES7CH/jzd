package com.jzd.record.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.jzd.record.R;

public class DbCityHelper extends SQLiteOpenHelper {
	private final int BUFFER_SIZE = 400000;
	public static final String DB_NAME = "region.db"; // ��������ݿ��ļ���
	public static final String PACKAGE_NAME = "com.jzd.record";
	public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME + "/databases/"; // ���ֻ��������ݿ��λ��

	private SQLiteDatabase database;
	private Context context;

	public DbCityHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	public void openDatabase() {
		this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
	}

	private SQLiteDatabase openDatabase(String dbfile) {
		try {
			// �ж����ݿ��ļ��Ƿ���ڣ�����������ִ�е��룬����ֱ�Ӵ����ݿ�
			if (!(new File(dbfile).exists())) {
				InputStream is = this.context.getResources().openRawResource(R.raw.region); // ����������ݿ�
				FileOutputStream fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
			return db;
		} catch (FileNotFoundException e) {
			Log.e("Database", "File not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Database", "IO exception");
			e.printStackTrace();
		}
		return null;
	}

	// do something else here<br>

	public void closeDatabase() {
		this.database.close();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
}
