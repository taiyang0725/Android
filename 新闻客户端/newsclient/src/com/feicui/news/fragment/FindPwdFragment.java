package com.feicui.news.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;
import com.feicui.news.data.UserData;

/** ��ʾ�һ�����Ľ��� */
public class FindPwdFragment extends BaseFragment implements OnClickListener {

	private View view;

	/** ���ⷵ�ذ�ť */
	private ImageView imageView;
	/** �������е��� */
	private TextView txt;
	/** ���Ͱ�ť */
	private Button btnSend;
	/** ��������� */
	private EditText edt;
	/** �����ַ */
	private String email;
	/** ��ִ�� */
	private String result;

	private OnFindPwdListener callBack;

	/** �һ��������ͨѶ�ӿ� */
	public interface OnFindPwdListener {
		/** �������Ľ���ͨѶ���� */
		void onFromMenu();

	}

	public FindPwdFragment(ImageView imageView, TextView txt) {
		super();
		this.imageView = imageView;
		this.txt = txt;
	}

	/** תȦ���ط� */
	private ProgressDialog progressDialog;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String result = (String) msg.obj;
				if (result != null & result.equals("0")) {
					edt.setEnabled(false);
				}
				progressDialog.cancel();

				break;
			case 2:
				progressDialog.cancel();
				Toast.makeText(getActivity(), R.string.network_anomaly,
						Toast.LENGTH_SHORT).show();

				break;

			default:
				break;
			}

		}
	};

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		try {
			callBack = (OnFindPwdListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.forgetpass, null);

		initView(view);
		return view;
	}

	private void initView(View view2) {

		imageView.setBackgroundResource(R.drawable.arrow_white_left);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				callBack.onFromMenu();
			}
		});

		txt.setText(R.string.find_password);

		edt = (EditText) view2.findViewById(R.id.edt_find_pwd_email);

		btnSend = (Button) view2.findViewById(R.id.btn_send_to);
		btnSend.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		email = edt.getText().toString();

		if (!(email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$"))) {
			edt.setError(getResources().getString(R.string.set_error_email)
					.toString());
		} else {
			progressDialog = ProgressDialog.show(getActivity(), null,
					getResources().getString(R.string.log_in_3));

			UserData.getPassword(getActivity(), email, handler);

		}

	}
}
