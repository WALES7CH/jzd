package com.jzd.record.utils;

import android.widget.Spinner;

public class SpinnerUtils {

	/**
	 * ͨ��ѡ��ֵ(��ʾ��ֵ)������Ĭ��ѡ�� 2015-12-25 @author WALES7
	 * 
	 * @param spinner
	 * @param value
	 */
	public static void setSelectedItem(Spinner spinner, String value) {
		for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
			if (spinner.getItemAtPosition(i).toString().equals(value)) {
				spinner.setSelection(i);
				break;
			}
		}
	}

}
