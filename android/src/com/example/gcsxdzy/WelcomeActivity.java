package com.example.gcsxdzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class WelcomeActivity extends Activity implements OnClickListener {

	private Button login;
	private Button register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		init();
	}

	private void init() {
		login = (Button) super.findViewById(R.id.btn_sign_in);
		register = (Button) super.findViewById(R.id.btn_sign_up);
		login.setOnClickListener(this);
		register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sign_in:
			startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
			finish();
			break;
		case R.id.btn_sign_up:
			startActivity(new Intent(WelcomeActivity.this,
					RegisterActivity.class));
			finish();
			break;
		default:
			break;
		}
	}

}
