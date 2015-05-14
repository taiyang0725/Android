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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;
import com.feicui.news.data.UserData;

/** ��ʾע��ҳ�� */
public class SignUpFragment extends BaseFragment implements OnClickListener {
	/**
	 * ע��ҳ�沼��
	 * */
	private View view;
	/** �ֻ�ע�� */
	private Button btnPhone;
	/** ����ע�� */
	private Button btnEmail;
	/** �ֻ�ע������� */
	private LinearLayout layoutPhone;
	/** ����ע�� ����� */
	private LinearLayout layoutEmail;
	/** ���ذ�ť */
	private ImageView imgBack;

	private ImageView imageView;
	/** �������е��� */
	private TextView txt;
	/** �û��� */
	private EditText edtName;
	/** ���� */
	private EditText edtEmail;
	/** ���� */
	private EditText edtPwd;
	/** ������������ */
	private EditText edtPwd1;
	/** ע�ᰴť */
	private Button btnSign;
	/** �û����� */
	private String name;
	/** �û����� */
	private String eMail;
	/** �û����� */
	private String pwd;
	private String pwd1;

	/** תȦ���ط� */
	private ProgressDialog progressDialog;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String result = (String) msg.obj;
				if (result != null) {
					if (result.equals("0")) {

						callBack.onFromMy();
					}
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

	public SignUpFragment(ImageView imageView, TextView txt) {

		this.imageView = imageView;
		this.txt = txt;

	}

	private OnSignUpListener callBack;

	/** ע��ҳ��ص��ӿ� */
	public interface OnSignUpListener {
		/** ע��ҳ��ص����� */
		void onSignUpFromLogIn();

		/** ���ظ������� */
		void onFromMy();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		try {
			callBack = (OnSignUpListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.sign_in, container, false);

		initView(view);

		return view;
	}

	private void initView(View view1) {

		layoutPhone = (LinearLayout) view1
				.findViewById(R.id.layout_sign_up_phone);
		btnPhone = (Button) view1.findViewById(R.id.btn_sign_up_phone);
		btnPhone.setOnClickListener(this);

		layoutEmail = (LinearLayout) view1
				.findViewById(R.id.layout_sign_up_email);
		btnEmail = (Button) view1.findViewById(R.id.btn_sign_up_email);
		btnEmail.setOnClickListener(this);

		imageView.setBackgroundResource(R.drawable.arrow_white_left);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				callBack.onSignUpFromLogIn();

			}
		});

		txt.setText(R.string.sign_up_new);

		edtName = (EditText) view1.findViewById(R.id.edt_user_name);

		edtEmail = (EditText) view1.findViewById(R.id.edt_emali);

		edtPwd = (EditText) view1.findViewById(R.id.edt_pwd);

		edtPwd1 = (EditText) view1.findViewById(R.id.edt_pwd_same);

		btnSign = (Button) view1.findViewById(R.id.btn_sign_log);
		btnSign.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_sign_up_phone:
			layoutPhone.setVisibility(View.VISIBLE);
			layoutEmail.setVisibility(View.GONE);
			btnPhone.setBackgroundResource(R.color.green);
			btnEmail.setBackgroundResource(0);
			break;
		case R.id.btn_sign_up_email:
			layoutEmail.setVisibility(View.VISIBLE);
			layoutPhone.setVisibility(View.GONE);
			btnEmail.setBackgroundResource(R.color.green);
			btnPhone.setBackgroundResource(0);
			break;
		case R.id.btn_sign_log:
			signOrLog();
			break;
		default:
			break;
		}

	}

	/** ע�Ტ��½���� */
	private void signOrLog() {

		name = edtName.getText().toString();
		eMail = edtEmail.getText().toString();
		pwd = edtPwd.getText().toString();
		pwd1 = edtPwd1.getText().toString();

		if (!(name.matches("[0-9a-z]{6,12}"))) {
			edtName.setError(getResources().getString(R.string.set_error_name)
					.toString());
		} else if (!(eMail
				.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$"))) {
			edtEmail.setError(getResources()
					.getString(R.string.set_error_email).toString());
		} else if (!(pwd.matches("[0-9a-z]{6,12}"))) {
			edtPwd.setError(getResources().getString(R.string.set_error_name)
					.toString());
		} else if (!(pwd1.equals(pwd))) {
			edtPwd1.setError(getResources().getString(R.string.pwd_pwd)
					.toString());
		} else {
			UserData.getSignUp(getActivity(), name, eMail, pwd1, handler);
			progressDialog = ProgressDialog.show(getActivity(), null,
					getResources().getString(R.string.log_in_2));

		}

	}

}
