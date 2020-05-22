package com.example.gcsxdzy;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {
    
	
	private EditText userName;
	private EditText passWord;
	private Button login;
	private Button forgotPassword;
	private ImageButton loginBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		init();
	}
	
	private void init() {
		login = (Button) super.findViewById(R.id.btn_sign_in);
		forgotPassword = (Button) super.findViewById(R.id.btn_forgot_passeprd);
		loginBack=(ImageButton)super.findViewById(R.id.imagebtn_login_back);
		userName=(EditText)super.findViewById(R.id.et_sign_in_username);
		passWord=(EditText)super.findViewById(R.id.et_sign_in_pwd);  
		login.setOnClickListener(this);
		forgotPassword.setOnClickListener(this);
		loginBack.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		if (v == login) { // 跳转到登录界面
			if(userName.getText().toString().trim().equals("admin")&&passWord.getText().toString().trim().equals("root"))
			{	
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
			else
			{
				Toast.makeText(LoginActivity.this,
					     "用户名或密码错误", Toast.LENGTH_SHORT).show();
				return;
			}
		} else if (v == forgotPassword) { // 调转到注册界面
			Intent intent = new Intent(LoginActivity.this,ForgotPassword1.class);
			startActivity(intent);
			finish();
		}
		else if(v==loginBack)
		{
//			Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
//			startActivity(intent);
//			finish();
			//测试ListView是否可用
			Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
			startActivity(intent);
			finish();
		}
		
	}

	
}
