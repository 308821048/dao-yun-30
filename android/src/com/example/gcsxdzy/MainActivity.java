package com.example.gcsxdzy;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private ImageView mChatImageView;
	private ImageView mContactsImageView;
	private ImageView mDiscoverImageView;
	private ImageView mMineImageView;

	private TextView mChatTextView;
	private TextView mContactsTextView;
	private TextView mDiscoverTextView;
	private TextView mMineTextView;

	private ViewPager mTabPager;

	private EnterKaoQinFragment chatFragment;
	private LeaveFragment contactsFragment;
	private MessageFragment discoverFragment;
	private UserInfoFragment mineFragment;
	private ArrayList<Fragment> views = null;

	private static final int TAB_INDEX_CHAT = 0;
	private static final int TAB_INDEX_CONTACTS = 1;
	private static final int TAB_INDEX_DISCOVER = 2;
	private static final int TAB_INDEX_MINE = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mChatImageView = (ImageView) findViewById(R.id.tab_chat_icon);
		mContactsImageView = (ImageView) findViewById(R.id.tab_contacts_icon);
		mDiscoverImageView = (ImageView) findViewById(R.id.tab_discover_icon);
		mMineImageView = (ImageView) findViewById(R.id.tab_mine_icon);
		mChatTextView = (TextView) findViewById(R.id.tab_chat_text);
		mContactsTextView = (TextView) findViewById(R.id.tab_contacts_text);
		mDiscoverTextView = (TextView) findViewById(R.id.tab_discover_text);
		mMineTextView = (TextView) findViewById(R.id.tab_mine_text);
		mTabPager = (ViewPager) findViewById(R.id.pagerview);
		findViewById(R.id.tab_chat_layout).setOnClickListener(this);
		findViewById(R.id.tab_contacts_layout).setOnClickListener(this);
		findViewById(R.id.tab_discover_layout).setOnClickListener(this);
		findViewById(R.id.tab_mine_layout).setOnClickListener(this);

		views = new ArrayList<Fragment>();
		views.add(new EnterKaoQinFragment());
		views.add(new LeaveFragment());
		views.add(new MessageFragment());
		views.add(new UserInfoFragment());
		PagerAdapter adapter = new MyFrageStatePagerAdapter(
				getSupportFragmentManager());
		mTabPager.setAdapter(adapter);
		mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mTabPager.setCurrentItem(TAB_INDEX_CHAT); // 初始化状态
	}

	class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

		public MyFrageStatePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return views.get(arg0);
		}

		@Override
		public int getCount() {
			return views.size();
		}
	}

	// 页面切换监听
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case TAB_INDEX_CHAT:
				setTabIcon(true, false, false, false);
				break;
			case TAB_INDEX_CONTACTS:
				setTabIcon(false, true, false, false);
				break;
			case TAB_INDEX_DISCOVER:
				setTabIcon(false, false, true, false);
				break;
			case TAB_INDEX_MINE:
				setTabIcon(false, false, false, true);
				break;
			default:
				break;
			}
		}
	}

	private void setTabIcon(boolean isChatSelected, boolean isContactsSelected,
			boolean isDiscoverSelected, boolean isMineSelected) {
		mChatImageView.setSelected(isChatSelected);
		mContactsImageView.setSelected(isContactsSelected);
		mDiscoverImageView.setSelected(isDiscoverSelected);
		mMineImageView.setSelected(isMineSelected);

		mChatTextView.setSelected(isChatSelected);
		mContactsTextView.setSelected(isContactsSelected);
		mDiscoverTextView.setSelected(isDiscoverSelected);
		mMineTextView.setSelected(isMineSelected);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab_chat_layout:
			mTabPager.setCurrentItem(TAB_INDEX_CHAT);
			break;
		case R.id.tab_contacts_layout:
			mTabPager.setCurrentItem(TAB_INDEX_CONTACTS);
			break;
		case R.id.tab_discover_layout:
			mTabPager.setCurrentItem(TAB_INDEX_DISCOVER);
			break;
		case R.id.tab_mine_layout:
			mTabPager.setCurrentItem(TAB_INDEX_MINE);
			break;
		}
	}

}
