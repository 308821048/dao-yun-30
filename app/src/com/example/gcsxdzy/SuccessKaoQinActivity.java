package com.example.gcsxdzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
  
public class SuccessKaoQinActivity extends Activity implements OnClickListener{  
  
	private ImageButton back;
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.sucessqiandao);  

     /*   super.findViewById( R.id.textview_qiandao_title );
        super.findViewById( R.id.textview_qiandao_userName );
        super.findViewById( R.id.textview_qiandao_userName );*/
        
        back =  (ImageButton) super.findViewById(R.id.imagebtn_qiandao_back);
        
        back.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		 if (v == back) { // 调转到注册界面
			Intent intent = new Intent(SuccessKaoQinActivity.this, KaoQinActivity.class);
			startActivity(intent);
			finish();
		}
	}  
}  
