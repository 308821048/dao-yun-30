package com.example.gcsxdzy;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMyLocationClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

public class MapActivity extends Activity implements OnClickListener{
    private LocationManager locationManager;
    private String provider;
    MapView mapView;
    BaiduMap baiduMap;
    boolean ifFrist = true;
    private Button btnmylocation;   //我当前的位置，点击会回到自己当前所在的位置
	private MyLocationListener myLocationListener;  //地图的监听
	MyLocationData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.bmapView);
    	btnmylocation = (Button) findViewById(R.id.my_location);
    	btnmylocation.setOnClickListener(this);
        // 获取baiduMap对象
        baiduMap = mapView.getMap();
        // 设置可改变地图位置
        baiduMap.setMyLocationEnabled(true);
        
        
    	myLocationListener = new MyLocationListener();
    	
    	
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> list = locationManager.getProviders(true);
        if (list.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;

        } else {
            Toast.makeText(this, "当前不能提供位置信息", Toast.LENGTH_LONG).show();
            return;
        }
        //获取到记录当前位置
        Location location = locationManager.getLastKnownLocation(provider);
        
       // Toast.makeText(this, location+"", Toast.LENGTH_LONG).show();
        if (location != null) {
        	 navigateTo(location);
        }else{
        	 //Toast.makeText(this, "Location为空", Toast.LENGTH_LONG).show();	
        }
        locationManager.requestLocationUpdates(provider, 5000, 1,
                locationListener);

    }

    private void navigateTo(Location location) {
        // 按照经纬度确定地图位置
        if (ifFrist) {
        	
            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            
          //构建Marker图标  
            BitmapDescriptor bitmap = BitmapDescriptorFactory  
                .fromResource(R.drawable.location);
             
            OverlayOptions textOption = new TextOptions().bgColor(0xFFE33539)
            		.fontSize(42).fontColor(0xFFFFFFFF).text("您现在所处的位置在上课处，现在可以签到").rotate(0)
            		.position(ll);
            
          //构建MarkerOption，用于在地图上添加Marker  
            OverlayOptions option = new MarkerOptions()  
                .position(ll) 
                .icon(bitmap);  
            
          //在地图上添加Marker，并显示  
            baiduMap.addOverlay(option);
            baiduMap.addOverlay(textOption);
            
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            // 移动到某经纬度
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomBy(5f);
            // 放大
            baiduMap.animateMapStatus(update);

            ifFrist = false;
        }
        // 显示个人位置图标
        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.latitude(location.getLatitude());
        builder.longitude(location.getLongitude());
        MyLocationData data = builder.build();
        baiduMap.setMyLocationData(data);
        startAttendance();
        
    }

    //开启签到功能
    private void startAttendance() {
    	baiduMap.setOnMyLocationClickListener(new OnMyLocationClickListener(){
			@Override
			public boolean onMyLocationClick() {
				Intent intent = new Intent(MapActivity.this,KaoQinActivity.class);
				startActivity(intent);
				finish();
				return true;
			}
    	});
	}

	LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLocationChanged(Location arg0) {
            // TODO Auto-generated method stub
            // 位置改变则重新定位并显示地图
            navigateTo(arg0);
        }
    };

    @Override
    protected void onDestroy() {
        // 释放资源
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }

        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);

    }

	@Override
	public void onClick(View v) {
		if(v==btnmylocation){
			/* LatLng ll = new LatLng(location.getLatitude(),
	                 location.getLongitude());
			MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(ll);// 描述地图状态将要发生的变化
			baiduMap.animateMapStatus(msu);// 以动画方式更新地图状态，动画耗时 300 ms		
*/		}
	}

	//
	private class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			 data = new MyLocationData.Builder()// 定位数据建造器
					.accuracy(location.getRadius())// 定位精度
					.latitude(location.getLatitude())// 百度纬度坐标
					.longitude(location.getLongitude())// 百度经度坐标
					.build();
			baiduMap.setMyLocationData(data);  // 将定位的数据设置给地图
		}

		@Override
		public void onConnectHotSpotMessage(String arg0, int arg1) {
			
		}
	}
		
}