package com.example.gcsxdzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {

	private EditText userName;
	private EditText stuNum;
	private EditText passWord;
	private EditText phone;
	private EditText comfirmPassWord;
	private Button register;
	private ImageButton registerBack;
	private RadioGroup rgsex;
	private RadioButton male;
	private RadioButton female;
	private int recLen = 60; // 用于倒计时

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		init();
	}

	private void init() {
		register = (Button) findViewById(R.id.btn_register);
		stuNum = (EditText) findViewById(R.id.et_register_xuehao);
		passWord = (EditText) findViewById(R.id.et_regsiter_pwd);
		comfirmPassWord = (EditText) findViewById(R.id.et_confirm_pwd);
		userName = (EditText) findViewById(R.id.et_register_username);
		rgsex = (RadioGroup) findViewById(R.id.radioGroup_register_sex);
		male = (RadioButton) findViewById(R.id.male);
		female = (RadioButton) findViewById(R.id.female);
		registerBack = (ImageButton) findViewById(R.id.imagebtn_register_back);
		register.setOnClickListener(this);
		rgsex.setOnCheckedChangeListener(new OnCheckedChangeListenerImp());
		registerBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == register) { // 跳转到登录界面
			if (!passWord.getText().toString().trim()
					.equals(comfirmPassWord.getText().toString().trim())) {
				Toast.makeText(RegisterActivity.this, "两次输入密码不一致",
						Toast.LENGTH_SHORT).show();
				return;

			} else {
				Intent intent = new Intent(RegisterActivity.this,
						RoleActivity.class);
				startActivity(intent);
				finish();
			}
		} else if (v == registerBack) { // 调转到注册界面
			Intent intent = new Intent(RegisterActivity.this,
					WelcomeActivity.class);
			startActivity(intent);
			finish();
		}

	}

	private class OnCheckedChangeListenerImp implements OnCheckedChangeListener {
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			String temp = null;
			if (RegisterActivity.this.male.getId() == checkedId) {
				temp = "男";
			} else if (RegisterActivity.this.female.getId() == checkedId) {
				temp = "女";
			}
			Log.i("您的性别是", temp);
		}

	}
}
