package com.jzd.record.utils;

import android.util.Log;
import android.widget.Spinner;

public class SpinnerUtils {

	/**
	 * 通过选项值(显示的值)来设置默认选中 2015-12-25 @author WALES7
	 * 
	 * @param spinner
	 * @param value
	 */
	public static void setSelectedItem(Spinner spinner, String value) {
		Log.e("Spinner", value);
		for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
			if (spinner.getItemAtPosition(i).toString().equals(value)) {
				spinner.setSelection(i);
				break;
			}
		}
	}

}
