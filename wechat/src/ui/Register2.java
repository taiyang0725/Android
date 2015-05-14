package ui;

import tools.AppManager;
import tools.StringUtils;
import tools.UIHelper;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import bean.Entity;

import com.donal.wechat.R;

import config.ApiClent;
import config.ApiClent.ClientCallback;
import config.AppActivity;

public class Register2 extends AppActivity{
	private EditText passET;
	private EditText nicknameET;
	private EditText introET;
	private ProgressDialog loadingPd;
	private String mobile;
	private InputMethodManager imm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register2);
		imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
		initUI();
		initData();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	private void initUI() {
		Button leftBarButton = (Button) findViewById(R.id.leftBarButton);
		accretionArea(leftBarButton);
		Button rightBarButton = (Button) findViewById(R.id.rightBarButton);
		accretionArea(rightBarButton);
		passET = (EditText) findViewById(R.id.editTextPass); 
		nicknameET = (EditText) findViewById(R.id.editTextNickName); 
		introET = (EditText) findViewById(R.id.editTextIntro); 
	}
	
	private void initData() {
		mobile = getIntent().getStringExtra("mobile");
	}
	
	public void ButtonClick(View v) {
		switch (v.getId()) {
		case R.id.leftBarButton:
			AppManager.getAppManager().finishActivity(this);
			break;
		case R.id.rightBarButton:
			imm.hideSoftInputFromWindow(introET.getWindowToken(), 0);
			vertifiedInfo();
			break;
		}
	}
	
	
	private void vertifiedInfo() {
		String pass = passET.getText().toString();
		String nickname = nicknameET.getText().toString();
		String intro = introET.getText().toString();
		if (StringUtils.empty(pass)) {
			showToast("请输入密码");
			return;
		}
		if (StringUtils.empty(nickname)) {
			showToast("请输入昵称");
			return;
		}
		if (StringUtils.empty(nickname)) {
			showToast("请输入介绍");
			return;
		}
		commitInfo(pass, nickname, intro);
	}
	
	private void commitInfo(final String pass, String nickname, String intro) {
		loadingPd = UIHelper.showProgress(this, null, null, true);
		ApiClent.register(appContext, mobile, pass, nickname, intro, "", new ClientCallback() {
			
			@Override
			public void onSuccess(Object data) {
				Entity entity = (Entity) data;
				switch (entity.getError_code()) {
				case 1:
					showToast(entity.getMessage());
					enterIndex();
					break;
				default:
					showToast(entity.getMessage());
					break;
				}
				
			}
			
			@Override
			public void onFailure(String message) {
				showToast(message);
			}
			
			@Override
			public void onError(Exception e) {
			}
		});
	}
	
	private void enterIndex() {
		setResult(RESULT_OK);
		AppManager.getAppManager().finishActivity(this);
	}
	
	
}
