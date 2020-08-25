package com.example.gcsxdzy;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ForgotPassword1 extends Activity implements OnClickListener {

	private ImageButton modifyPassWordBack;
	private Button btnComfirm;
	private EditText  newPassword;
	private EditText comfirmNewPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgotpassword);
		init();
	}
	
	private void init() {
		btnComfirm = (Button) super.findViewById(R.id.btn_forgotPassword_comfirm);
		modifyPassWordBack=(ImageButton)super.findViewById(R.id.imagebtn_modify_back);
		newPassword=(EditText)super.findViewById(R.id.et_newPassword);
		comfirmNewPassword=(EditText)super.findViewById(R.id.et_comfirm_newPassword);
		
		btnComfirm.setOnClickListener(this);
		modifyPassWordBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v==btnComfirm)
		{
			if(!newPassword.getText().toString().trim().equals(comfirmNewPassword.getText().toString().trim()))
			{
				Toast.makeText(ForgotPassword1.this,
					     "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
				return;
		    }
			else
			{
				Toast.makeText(ForgotPassword1.this,
					     "密码修改成功！", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ForgotPassword1.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}
		else if(v==modifyPassWordBack)
		{
			Intent intent = new Intent(ForgotPassword1.this,LoginActivity.class);
			startActivity(intent);
			finish();
		}
		
	}

}
