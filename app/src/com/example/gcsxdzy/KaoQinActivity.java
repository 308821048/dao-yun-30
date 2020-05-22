package com.example.gcsxdzy;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
  
public class KaoQinActivity extends Activity implements OnClickListener{  
    private GridView gridview;  
  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.kaoqin);  
          
        gridview = (GridView) findViewById(R.id.gridview);  
  
        // 生成动态数组，并且转入数据  
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
        for (int i = 0; i < 40; i++) {  
            HashMap<String, Object> map = new HashMap<String, Object>();  
            map.put("ItemImage", R.drawable.zuowei);// 添加图像资源的ID  
            map.put("ItemText", "NO." + String.valueOf(i+1));// 按序号做ItemText  
            lstImageItem.add(map);  
        }  
        // 生成适配器的ImageItem <====> 动态数组的元素，两者一一对应  
        SimpleAdapter saImageItems = new SimpleAdapter(this, // 没什么解释  
                lstImageItem,// 数据来源  
                R.layout.item,// night_item的XML实现  
                // 动态数组与ImageItem对应的子项  
                new String[] { "ItemImage", "ItemText" },  
                // ImageItem的XML文件里面的一个ImageView,两个TextView ID  
                new int[] { R.id.ItemImage, R.id.ItemText });  
        // 添加并且显示  
        gridview.setAdapter(saImageItems);  
        // 添加消息处理  
        gridview.setOnItemClickListener(new ItemClickListener());  
    }  
  
    // 当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件  
    class ItemClickListener implements OnItemClickListener {  
        public void onItemClick(AdapterView<?> arg0,View arg1, int arg2,long arg3) {  
            // 在本例中arg2=arg3  
            @SuppressWarnings("unchecked")  
            HashMap<String, Object> item = (HashMap<String, Object>) arg0.getItemAtPosition(arg2);  
            // 显示所选Item的ItemText  
            setTitle((String) item.get("ItemText")); 
            
            new AlertDialog.Builder(KaoQinActivity.this).setTitle("确认要签到吗？") 
            .setIcon(android.R.drawable.ic_dialog_info) 
            .setPositiveButton("签到", new DialogInterface.OnClickListener() { 
         
                @Override 
                public void onClick(DialogInterface dialog, int which) { 
                    // 点击“确认”后的操作 
                    Intent intent = new Intent(KaoQinActivity.this, SuccessKaoQinActivity.class);
        			startActivity(intent);
        			finish();
                } 
            }) 
            .setNegativeButton("我要请假", new DialogInterface.OnClickListener() { 
            	
                @Override 
                public void onClick(DialogInterface dialog, int which) { 
                	
                    Intent intent = new Intent(KaoQinActivity.this, LeaveActivity.class);
         			startActivity(intent);
         			finish();
                   // 点击“返回”后的操作,这里不设置没有任何操作 
                } 
            }).show(); 
    
        }  
    }

	@Override
	public void onClick(View v) {
		
	}  
}  
