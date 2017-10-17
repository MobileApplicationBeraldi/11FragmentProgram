package com.example.roberto.fragmentprogram;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;



public class SelectionFragment extends ListFragment {

    String [] items = null;

    String region="Europe";

    private Callback callback = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        AssetManager am = getActivity().getAssets();

        try {

            items = am.list(region);
            for (int i=0;i<items.length;i++) {
                items[i]=items[i].substring(items[i].indexOf('-')+1,items[i].indexOf('.'));
            }
        }
        catch (IOException e){
            Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String name = l.getItemAtPosition(position).toString();
        callback.SelectionEvent(region,region+"-"+name+".png");
    }

    interface Callback {
        void SelectionEvent(String region, String name);
    }
}
