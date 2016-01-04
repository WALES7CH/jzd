package com.jzd.record.thread;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jzd.record.R;

public class ThreadHandlerActivity extends Activity {
	private static final int MSG_SUCCESS = 0;// 获取图片成功的标识
	private static final int MSG_FAILURE = 1;// 获取图片失败的标识
	private ImageView mImageView;
	private Button mButton;
	private Thread mThread;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mImageView = (ImageView) findViewById(R.id.img_code);
		mButton = (Button) findViewById(R.id.btn_login);
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 如果线程还没启动，则启动新的线程
				if (mThread == null) {
					mThread = new Thread(runnable);
					mThread.start();
					// 否则提示："线程已经启动"
				} else {
					Toast.makeText(getApplication(), getApplication().getString(R.string.code), Toast.LENGTH_LONG)
							.show();
				}
			}
		});
	}

	private Handler mHandler = new Handler() {
		// 重写handleMessage()方法，此方法在UI线程运行
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// 如果成功，则显示从网络获取到的图片
			case MSG_SUCCESS:
				mImageView.setImageBitmap((Bitmap) msg.obj);
				Toast.makeText(getApplication(), getApplication().getString(R.string.code), Toast.LENGTH_LONG).show();
				break;
			// 否则提示失败
			case MSG_FAILURE:
				Toast.makeText(getApplication(), getApplication().getString(R.string.code), Toast.LENGTH_LONG).show();
				break;
			}
		}
	};
	Runnable runnable = new Runnable() {
		// 重写run()方法，此方法在新的线程中运行
		@Override
		public void run() {
			HttpClient httpClient = new DefaultHttpClient();
			// 从网络上获取图片
			HttpGet httpGet = new HttpGet("http://www.oschina.net/img/logo.gif");
			final Bitmap bitmap;
			try {
				HttpResponse httpResponse = httpClient.execute(httpGet);
				// 解析为图片
				bitmap = BitmapFactory.decodeStream(httpResponse.getEntity().getContent());
			} catch (Exception e) {
				mHandler.obtainMessage(MSG_FAILURE).sendToTarget();// 获取图片失败
				return;
			}
			// 获取图片成功，向UI线程发送MSG_SUCCESS标识和bitmap对象
			mHandler.obtainMessage(MSG_SUCCESS, bitmap).sendToTarget();
		}
	};
}
