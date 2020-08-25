package com.example.gcsxdzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class RoleActivity extends Activity implements OnClickListener {

	private Button imStudent;
	private Button imTeacher;
	private Button imAdamin;
	private ImageButton roleBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.role);
		init();
	}

	private void init() {
		imStudent = (Button) super.findViewById(R.id.btn_Imstudent);
		imTeacher = (Button) super.findViewById(R.id.btn_Imteacher);
		imAdamin = (Button) super.findViewById(R.id.btn_Imadmin);
		roleBack = (ImageButton) super.findViewById(R.id.imagebtn_role_back);

		imStudent.setOnClickListener(this);
		imTeacher.setOnClickListener(this);
		imAdamin.setOnClickListener(this);
		roleBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == imStudent) { // 跳转到登录界面

			Intent intent = new Intent(RoleActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();

		} else if (v == imTeacher) {
			Intent intent = new Intent(RoleActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		} else if (v == imAdamin) {
			Intent intent = new Intent(RoleActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		} else if (v == roleBack) {
			Intent intent = new Intent(RoleActivity.this,
					RegisterActivity.class);
			startActivity(intent);
			finish();
		}

	}

}
