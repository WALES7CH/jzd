package com.jzd.record.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jzd.record.db.DbCityService;

public class CityAreaUtils {

	// 加载地州市列表
	public static void loadCityAreaItems(Spinner spinner, Context context, String city) {

		List<String> list = new ArrayList<String>();

		if (city != null) {
			list = new DbCityService(context).getArealist(city);
		} else {
			list = new DbCityService(context).getCitylist();
		}
		ArrayAdapter<String> arr_adapter;
		arr_adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
		arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arr_adapter);
	}
}
