package com.feicui.news.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;
import com.feicui.news.data.UserData;
import com.feicui.news.mode.UserHome;
import com.feicui.news.mode.UserImage;
import com.feicui.news.util.ImageLoader;
import com.feicui.news.util.ShareUtil;
import com.feicui.news.view.UserIconPopupWindow;

/** ��ʾ�������ĵĽ��� */
public class MyFragment extends BaseFragment implements OnClickListener {

	private View view;
	/** ����С���Ӱ�ť */
	private ImageView imageView;
	/** �������㰴ť */
	private ImageView imageView1;
	/** �������е��� */
	private TextView txt;
	/** �û�ͼ�� */
	private ImageView imageIcon;
	/** ��ά�� */
	private ImageView imageQrCode;
	/** �û����� */
	private TextView txtName;
	/** �û����� */
	private TextView txtEmail;
	/** ��½�ص� */
	private TextView txtLocal;
	/** ƽ������ */
	private TextView txtComnum;
	/** ע����ť */
	private Button btn;
	/** ��������ʵ���� */
	private UserHome userHome;
	/** ����ͼ��ʵ���� */
	private UserImage userImage;

	private Platform platform;

	private UserIconPopupWindow window;

	private OnMyListener callBack;

	/** ���߻�ȡ������ */
	private static final int REQUEST_CODE_CAMERA = 100;
	/** ���ػ�ȡ������ */
	private static final int REQUEST_CODE_LOCAL = 200;

	/** �������Ľ���ͨѶ�ӿ� */
	public interface OnMyListener {
		/** �������Ľ���ͨѶ���� */
		void onFromMenu();

		void onFromQr();

	}

	/** תȦ���ط� */
	private ProgressDialog progressDialog;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				userHome = (UserHome) msg.obj;

				if (userHome != null) {
					txtName.setText(userHome.getUid());
					txtComnum.setText(userHome.getComnum());
					ImageLoader loader = new ImageLoader(getActivity());

					imageIcon.setImageBitmap(loader.getImage(userHome
							.getPortrait()));

				}

				break;
			case 2:

				if (progressDialog != null) {

					progressDialog.cancel();
				}

				Toast.makeText(getActivity(), R.string.network_anomaly,
						Toast.LENGTH_SHORT).show();

				break;
			case 3:
				userImage = (UserImage) msg.obj;
				if (userImage.getResult().equals("0")) {
					if (progressDialog != null) {

						progressDialog.cancel();
					}
				}
				break;

			default:
				break;
			}

		}
	};

	public MyFragment(ImageView imageView, TextView txt, ImageView imageView1) {
		super();
		this.imageView = imageView;
		this.imageView1 = imageView1;
		this.txt = txt;
	}

	public MyFragment() {
		super();
	}

	public void setPlatform(String platName) {
		platform = ShareSDK.getPlatform(platName);
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		try {
			callBack = (OnMyListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		userImage = new UserImage();

		UserData.getUserHome(getActivity(),
				ShareUtil.getShare(getActivity(), "Token"), handler);

		// progressDialog = ProgressDialog.show(getActivity(), null,
		// getResources().getString(R.string.defy_death));
		// if (ShareUtil.getShare(getActivity(), "Token") == null) {
		// progressDialog.cancel();
		// }

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.my_domain, container, false);
		initView(view);

		return view;
	}

	private void initView(View view1) {

		imageView.setBackgroundResource(R.drawable.arrow_white_left);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				callBack.onFromMenu();
			}
		});

		txt.setText(R.string.my_my);

		imageIcon = (ImageView) view1.findViewById(R.id.img_domain_icon);
		imageIcon.setOnClickListener(this);

		imageQrCode = (ImageView) view.findViewById(R.id.img_domain_qr_code);
		imageQrCode.setOnClickListener(this);

		txtName = (TextView) view1.findViewById(R.id.txt_domain_name);

		txtEmail = (TextView) view1.findViewById(R.id.txt_domain_email);
		if (ShareUtil.getShare(getActivity(), "E-mail") != null) {
			txtEmail.setText(ShareUtil.getShare(getActivity(), "E-mail"));
		}

		txtComnum = (TextView) view1.findViewById(R.id.txt_domain_integration);

		txtLocal = (TextView) view1.findViewById(R.id.txt_domain_location);

		view.findViewById(R.id.btn_ccount_cancellation)
				.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_domain_icon:
			window = new UserIconPopupWindow(getActivity(), this);
			window.showPopupWindow(imageView1);

			break;

		case R.id.txt_get_local:
			getLocal();

			window.dismiss();
			break;
		case R.id.txt_online_photo:

			getCamera();

			window.dismiss();
			break;
		case R.id.img_domain_qr_code:
			callBack.onFromQr();

			break;
		case R.id.btn_ccount_cancellation:
			ShareUtil.setShare(getActivity(), null, "Token");
			callBack.onFromMenu();
			break;

		default:
			break;
		}

	}

	/** �������� */
	private void getCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		startActivityForResult(intent, REQUEST_CODE_CAMERA);

	}

	/** ���ػ�ȡ */
	private void getLocal() {

		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");

		intent.putExtra("crop", "true");// ���òü�����

		intent.putExtra("aspectX", 1);// ����x����ü�����
		intent.putExtra("aspectY", 1);// ����y����ü�����

		intent.putExtra("outputX", 80);// ����x����ü���С
		intent.putExtra("outputY", 80);// ����y����ü���С

		intent.putExtra("return-data", true);// �ָ��ü����

		startActivityForResult(intent, REQUEST_CODE_LOCAL);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case REQUEST_CODE_CAMERA:
			/** ���߻�ȡ������ */
			Bitmap bitmap = (Bitmap) data.getExtras().get("data");
			if (bitmap == null) {
				return;
			}

			if (ShareUtil.getShare(getActivity(), "Token") != null) {
				imageIcon.setImageBitmap(bitmap);
			}

			UserData.getUserImage(getActivity(),
					ShareUtil.getShare(getActivity(), "Token"),
					saveBitmap(bitmap), handler);

			progressDialog = ProgressDialog.show(getActivity(), null,
					getResources().getString(R.string.log_in_5));

			break;
		case REQUEST_CODE_LOCAL:
			/** ���ػ�ȡ������ */

			Bitmap bitmap1 = data.getParcelableExtra("data");
			if (bitmap1 == null) {
				return;
			}

			if (ShareUtil.getShare(getActivity(), "Token") != null) {
				imageIcon.setImageBitmap(bitmap1);
			}

			UserData.getUserImage(getActivity(),
					ShareUtil.getShare(getActivity(), "Token"),
					saveBitmap(bitmap1), handler);

			progressDialog = ProgressDialog.show(getActivity(), null,
					getResources().getString(R.string.log_in_5));
			break;

		default:
			break;
		}
	}

	/** ͼƬ����� */
	InputStream stream;
	/** ͼƬ������ */
	OutputStream outputStream;

	/**
	 * ͼƬ���뻺���ڴ�ķ���
	 * */
	private File saveBitmap(Bitmap bitmap) {
		File cacheDir = getActivity().getCacheDir();
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
		File file = new File(cacheDir, System.currentTimeMillis() + ".jpg");
		try {
			outputStream = new FileOutputStream(file);
			bitmap.compress(CompressFormat.PNG, 80, outputStream);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		// String path = file.getAbsolutePath();// ��ȡ·��
		return file;

	}

	/** ��ʼ������ */
	private void initData() {

		if (platform != null) {
			userHome.setPortrait(platform.getDb().getUserIcon());
			userHome.setUid(platform.getDb().getUserName());

		}

		txtName.setText(userHome.getUid());

	}
}
