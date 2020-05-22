package com.example.gcsxdzy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
  
public class LeaveActivity extends Activity{  
  
	private Spinner spinner;
	private List<String> data_list;
	private ArrayAdapter<String> arr_adapter;
	private ImageButton back;
	
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.leave); 
        
        spinner = (Spinner) findViewById(R.id.spinner);
        
        //数据
        data_list = new ArrayList<String>();
        data_list.add("软件工程实训");
        data_list.add("数据库技术");
        data_list.add("自然辨证法");
        data_list.add("中国特色社会主义");
        
        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
        
    }
}  
