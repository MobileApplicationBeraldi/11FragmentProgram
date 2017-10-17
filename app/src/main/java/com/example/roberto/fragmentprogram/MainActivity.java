package com.example.roberto.fragmentprogram;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SelectionFragment.Callback {

    FragmentManager fm;
    FragmentTransaction ft;
    FlagFragment fragment;
    SelectionFragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        fm =getFragmentManager();  //if this statement is moved inside the if condition
                                  //the application crashes when the device is rotated

        if (savedInstanceState==null){


            fragment = new FlagFragment();
            fragment2 = new SelectionFragment();

        ft = fm.beginTransaction();
        ft      .add(R.id.first_fragment,fragment,"1")
                .add(R.id.second_fragment,fragment2,"2")
                .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FlagFragment ff = (FlagFragment) fm.findFragmentByTag("1");
        outState.putCharSequence("CountryName",ff.getCountryName());
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        FlagFragment ff = (FlagFragment) fm.findFragmentByTag("1");
        ff.setFlag("Europe",(String)inState.getCharSequence("CountryName"));

    }


    public void SelectionEvent(String region, String name) {
        FragmentManager fm = getFragmentManager();
        FlagFragment ff = (FlagFragment) fm.findFragmentById(R.id.first_fragment);
        ff.setFlag(region,name);
    }
}
