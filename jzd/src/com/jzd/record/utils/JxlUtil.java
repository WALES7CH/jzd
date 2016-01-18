package com.jzd.record.utils;

import java.io.File;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import android.os.Environment;
import android.util.Log;

import com.jzd.record.db.CompanyClass;

public class JxlUtil {

	/**
	 * 导出生成excel文件，存放于SD卡中
	 * 
	 * @author smart *
	 */
	private List<CompanyClass> list;

	public JxlUtil(List<CompanyClass> list) {
		this.list = list;
	}

	public boolean toExcel(String cityName) {
		// 准备设置excel工作表的标题
		String[] title = { "序号", "终端编号", "二维码", "单位名称", "单位地址", "联系人", "联系方式",
				"摆放位置", "楼层", "区域", "开机", "关机", "渠道", "厂家" };
		try {
			// 获得开始时间
			long start = System.currentTimeMillis();

			// 判断SD卡是否存在
			if (!Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED)) {
				return false;
			}

			String SDdir = Environment.getExternalStorageDirectory().toString(); // 获取SD卡的根目录
			// 创建Excel工作薄
			WritableWorkbook wwb;
			// 在SD卡中，新建立一个名为person的jxl文件
			wwb = Workbook.createWorkbook(new File(Environment
					.getExternalStorageDirectory().getAbsolutePath()
					+ "/"
					+ cityName + "-" + new Date().getDate() + ".xls"));
			// 添加第一个工作表并设置第一个Sheet的名字
			WritableSheet sheet = wwb.createSheet(cityName, 0);
			Label label;
			for (int i = 0; i < title.length; i++) {
				label = new Label(i, 0, title[i]);
				// 将定义好的单元格添加到工作表中
				sheet.addCell(label);
			}
			/*
			 * 保存数字到单元格，需要使用jxl.write.Number 必须使用其完整路径，否则会出现错误
			 */
			String bootTime = "08";
			String shutTime = "18";
			for (int i = 0; i < list.size(); i++) {
				CompanyClass company = list.get(i);
				// 添加编号
				jxl.write.Number number = new jxl.write.Number(0, i + 1, i + 1);
				sheet.addCell(number);

				label = new Label(1, i + 1, company.getHddsn());
				sheet.addCell(label);
				label = new Label(2, i + 1, company.getQrcode());
				sheet.addCell(label);
				label = new Label(3, i + 1, company.getCompany_name());
				sheet.addCell(label);
				label = new Label(4, i + 1, company.getCompany_address());
				sheet.addCell(label);
				label = new Label(5, i + 1, company.getmain_contact());
				sheet.addCell(label);
				label = new Label(6, i + 1, company.getmain_phone());
				sheet.addCell(label);
				label = new Label(7, i + 1, company.getDevice_location());
				sheet.addCell(label);
				label = new Label(8, i + 1, "1");
				sheet.addCell(label);
				label = new Label(9, i + 1, company.getCompany_aera());
				sheet.addCell(label);

				if (company.getBoot_time() != null
						&& !company.getBoot_time().equals("")) {
					bootTime = company.getBoot_time().substring(0,
							company.getBoot_time().indexOf(":"));
				}

				label = new Label(10, i + 1, bootTime);
				bootTime = "08";
				sheet.addCell(label);

				if (company.getShut_time() != null
						&& !company.getShut_time().equals("")) {
					shutTime = company.getShut_time().substring(0,
							company.getShut_time().indexOf(":"));
				}
				label = new Label(11, i + 1, shutTime);
				shutTime = "18";
				sheet.addCell(label);
				label = new Label(12, i + 1, company.getCompany_type());
				sheet.addCell(label);
				label = new Label(13, i + 1, company.getFactory());
				sheet.addCell(label);

			}

			wwb.write(); // 写入数据
			wwb.close(); // 关闭文件
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
