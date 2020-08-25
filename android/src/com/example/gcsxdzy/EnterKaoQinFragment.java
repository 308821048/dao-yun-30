package com.example.gcsxdzy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class EnterKaoQinFragment extends Fragment implements	OnClickListener{
	
	private ImageButton imageButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enterkaoqin, null);
        imageButton = (ImageButton)view.findViewById(R.id.imageButton1);
        imageButton.setOnClickListener(this);
        return view;
    }
	@Override
	public void onClick(View v) {
		
		if(v==imageButton){
			Intent intent = new Intent(getActivity(), MapActivity.class);
			startActivity(intent);
			 getActivity().onBackPressed(); //销毁自己
		}
	}
}
