package com.example.roberto.fragmentprogram;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class FlagFragment extends Fragment {

    ImageView iv=null;
    String countryName="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.flagfragment,container,false);
        iv = (ImageView)view.findViewById(R.id.image_view);
        return view;
    }


    public void setFlag(String region,String name){
        countryName = name;
        AssetManager assetManager = getActivity().getAssets();
        InputStream inputStream;
        try {
            inputStream = assetManager.open(region+"/"+name);
            Drawable flag = Drawable.createFromStream(inputStream,name);
            iv.setImageDrawable(flag);
        }
        catch (IOException e){
            Toast.makeText(getActivity().getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public String getCountryName(){
        return countryName;
    };

}
