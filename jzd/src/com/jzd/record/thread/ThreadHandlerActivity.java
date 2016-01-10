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
	private static final int MSG_SUCCESS = 0;// ��ȡͼƬ�ɹ��ı�ʶ
	private static final int MSG_FAILURE = 1;// ��ȡͼƬʧ�ܵı�ʶ
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
				// ����̻߳�û�������������µ��߳�
				if (mThread == null) {
					mThread = new Thread(runnable);
					mThread.start();
					// ������ʾ��"�߳��Ѿ�����"
				} else {
					Toast.makeText(getApplication(), getApplication().getString(R.string.code), Toast.LENGTH_LONG)
							.show();
				}
			}
		});
	}

	private Handler mHandler = new Handler() {
		// ��дhandleMessage()�������˷�����UI�߳�����
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// ����ɹ�������ʾ�������ȡ����ͼƬ
			case MSG_SUCCESS:
				mImageView.setImageBitmap((Bitmap) msg.obj);
				Toast.makeText(getApplication(), getApplication().getString(R.string.code), Toast.LENGTH_LONG).show();
				break;
			// ������ʾʧ��
			case MSG_FAILURE:
				Toast.makeText(getApplication(), getApplication().getString(R.string.code), Toast.LENGTH_LONG).show();
				break;
			}
		}
	};
	Runnable runnable = new Runnable() {
		// ��дrun()�������˷������µ��߳�������
		@Override
		public void run() {
			HttpClient httpClient = new DefaultHttpClient();
			// �������ϻ�ȡͼƬ
			HttpGet httpGet = new HttpGet("http://www.oschina.net/img/logo.gif");
			final Bitmap bitmap;
			try {
				HttpResponse httpResponse = httpClient.execute(httpGet);
				// ����ΪͼƬ
				bitmap = BitmapFactory.decodeStream(httpResponse.getEntity().getContent());
			} catch (Exception e) {
				mHandler.obtainMessage(MSG_FAILURE).sendToTarget();// ��ȡͼƬʧ��
				return;
			}
			// ��ȡͼƬ�ɹ�����UI�̷߳���MSG_SUCCESS��ʶ��bitmap����
			mHandler.obtainMessage(MSG_SUCCESS, bitmap).sendToTarget();
		}
	};
}
