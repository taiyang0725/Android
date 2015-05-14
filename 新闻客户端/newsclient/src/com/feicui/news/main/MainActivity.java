package com.feicui.news.main;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.ShareSDK;

import com.feicui.news.BaseActivity;
import com.feicui.news.R;
import com.feicui.news.broadcast.DownLoadCompleteBroadcast;
import com.feicui.news.data.UpdateData;
import com.feicui.news.fragment.CmtFragment;
import com.feicui.news.fragment.CmtFragment.OnCmtListener;
import com.feicui.news.fragment.CollectFragment;
import com.feicui.news.fragment.CollectFragment.OnCollectListener;
import com.feicui.news.fragment.FindPwdFragment;
import com.feicui.news.fragment.FindPwdFragment.OnFindPwdListener;
import com.feicui.news.fragment.LogInFragment;
import com.feicui.news.fragment.LogInFragment.OnLogInListener;
import com.feicui.news.fragment.MainFragment;
import com.feicui.news.fragment.MyFragment;
import com.feicui.news.fragment.MyFragment.OnMyListener;
import com.feicui.news.fragment.NewsFragment;
import com.feicui.news.fragment.NewsFragment.OnFromMenuListener;
import com.feicui.news.fragment.PictureFragment;
import com.feicui.news.fragment.PictureFragment.OnPictureListener;
import com.feicui.news.fragment.SignUpFragment;
import com.feicui.news.fragment.SignUpFragment.OnSignUpListener;
import com.feicui.news.fragment.SlidingMenuFragment;
import com.feicui.news.fragment.SlidingMenuFragment.OnSlidingMenuListener;
import com.feicui.news.fragment.WeatherFragment;
import com.feicui.news.fragment.XLstFragment.OnMenuFragmentListener;
import com.feicui.news.mode.NewsList;
import com.feicui.news.mode.Update;
import com.feicui.news.slidingmenu.SlidingMenu;
import com.feicui.news.util.DownLoadUtil;
import com.feicui.news.util.SystemUtil;
import com.feicui.news.view.MenuPopupWindow;
import com.feicui.news.wangan.EFragment.OnEFragmentListener;

/**
 * 这是一个菜单界面类
 * */
public class MainActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener, OnSlidingMenuListener, OnMenuFragmentListener,
		OnFromMenuListener, OnSignUpListener, OnLogInListener,
		OnPictureListener, OnMyListener, OnFindPwdListener, OnCmtListener,
		OnEFragmentListener, OnCollectListener {
	/** 小房子按钮 */
	private ImageView imgHome;
	/** 三点按钮 */
	private ImageView imgPopup;
	/** 标题中间的子 */
	private TextView txt;

	private SlidingMenu menu;

	private DrawerLayout mDrawerLayout;

	private MenuPopupWindow myPopupWindow;

	/** 版本升级信息 */
	private Update update;

	/** 转圈加载符 */
	private ProgressDialog progressDialog;

	long sTime;
	long nTime;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:

				update = (Update) msg.obj;
				getUpdataId();
				progressDialog.cancel();

				break;
			case 2:
				Toast.makeText(MainActivity.this, R.string.network_anomaly,
						Toast.LENGTH_SHORT).show();
				progressDialog.cancel();
				break;

			default:
				break;
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu);

		Update update = new Update();

		ShareSDK.initSDK(this);

		replace(new MainFragment());

		setSlidingMenu();

		initView();

	}

	/**
	 * 设置左边滑动菜单
	 * */

	private void setSlidingMenu() {

		menu = new SlidingMenu(this);

		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

		menu.setMenu(R.layout.slidingframelayout);

		menu.setBehindOffsetRes(android.R.dimen.thumbnail_height);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		menu.setShadowDrawable(R.drawable.shadow);
		menu.setFadeDegree(0.35f);
		menu.setShadowWidth(R.dimen.in1);

		SlidingMenuFragment menuFragment = new SlidingMenuFragment(menu);
		replace(R.id.slidingfranelayout, menuFragment);

	}

	private void initView() {

		imgHome = (ImageView) findViewById(R.id.img_title_home);
		imgHome.setBackgroundResource(R.drawable.ic_title_home_default);
		imgHome.setOnClickListener(this);

		imgPopup = (ImageView) findViewById(R.id.img_title_popup);
		imgPopup.setOnClickListener(this);

		txt = (TextView) findViewById(R.id.txt_title);
		txt.setText(R.string.app_name);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_title_home:

			if (menu.isMenuShowing()) {
				menu.showContent();
			} else {
				menu.showMenu();
			}
			break;
		case R.id.img_title_popup:

			myPopupWindow = new MenuPopupWindow(this, this);
			myPopupWindow.showPopupWindow(imgPopup);

			break;

		default:
			break;
		}

	}

	/**
	 * 监测手机返回键的方法
	 * */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		sTime = System.currentTimeMillis();
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:

			menu.showMenu();

			break;
		case KeyEvent.KEYCODE_BACK:

			if (nTime > sTime - 100000) {

				return super.onKeyDown(keyCode, event);

			} else {

				Toast.makeText(this, R.string.one_two, Toast.LENGTH_SHORT)
						.show();
				nTime = sTime;
				return true;
			}

		default:
			break;
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:
			Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
			replace(new WeatherFragment());

			myPopupWindow.dismiss();
			break;
		case 1:
			Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
			myPopupWindow.dismiss();
			break;
		case 2:

			updata();
			myPopupWindow.dismiss();
			break;
		case 3:
			Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();

			break;
		case 4:
			myPopupWindow.dismiss();
			finish();
			break;

		default:
			break;
		}

	}

	@Override
	public void onSlidingFromLogIn() {
		// if ((ShareUtil.getShare(this, "Token")) == null) {
		replace(new LogInFragment(imgHome, txt));

	}

	@Override
	public void onMenuFromNews(NewsList list) {

		replace(new NewsFragment(list, imgHome, txt, imgPopup));

	}

	@Override
	public void onFromMenu() {
		replace(new MainFragment());
		initView();
	}

	@Override
	public void onSignUpFromLogIn() {
		replace(new LogInFragment(imgHome, txt));

	}

	@Override
	public void onLogInFromSignUp() {
		replace(new SignUpFragment(imgHome, txt));

	}

	@Override
	public void onFromPicture() {
		replace(new PictureFragment(imgHome, txt));

	}

	@Override
	public void onFromMy() {
		replace(new MyFragment(imgHome, txt, imgPopup));

	}

	@Override
	public void onFromFindPwd() {
		replace(new FindPwdFragment(imgHome, txt));

	}

	@Override
	public void onFromCmt() {
		replace(new CmtFragment(imgHome, txt));

	}

	@Override
	public void onFromQr() {
		Intent intent = new Intent(this, QrCodeAactivity.class);
		startActivity(intent);

	}

	@Override
	public void onFromCollect() {
		replace(new CollectFragment(imgHome, txt));
	}

	/**
	 * 一键升级方法
	 * */

	private void updata() {
		progressDialog = ProgressDialog.show(this, null, getResources()
				.getString(R.string.defy_death));

		UpdateData.setUpdate(this, handler);

	}

	private void getUpdataId() {
		if (SystemUtil.getVersionCode(this) < Integer.parseInt(update
				.getVersion())) {
			long id = DownLoadUtil.downLoad(this, update.getLink());

			DownLoadCompleteBroadcast broadcast = new DownLoadCompleteBroadcast(
					update.getMd5(), id);// 广播接收器

			IntentFilter filter = new IntentFilter();// 过滤器，选择性的接收广播信息

			filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);// 接收广播的条件

			registerReceiver(broadcast, filter);// 注册广播接收者
		} else {
			Toast.makeText(this, R.string.new_version, Toast.LENGTH_SHORT)
					.show();
		}

	}

}
