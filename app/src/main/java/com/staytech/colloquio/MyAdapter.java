package com.staytech.colloquio;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andrea on 13/06/16.
 */
public class MyAdapter extends BaseAdapter {

    Viaggio [] viaggi;
    Context context;
    ViewHolder viewHolder;
    LayoutInflater inflater = null;

    public MyAdapter(Activity activity, List<Viaggio> viaggi) {
        this.viaggi =  viaggi.toArray(new Viaggio[viaggi.size()]);
        context = activity;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return viaggi.length;
    }

    @Override
    public Object getItem(int position) {
        return viaggi[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.info_viaggio = (TextView) convertView.findViewById(R.id.info_viaggio);
            viewHolder.mappa = (Button) convertView.findViewById(R.id.mappa);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.info_viaggio.setText("ID: "+viaggi[position].getId()+"\n"+
                "NAME: "+viaggi[position].getName()+"\n"+
                "STARTING DATE: "+viaggi[position].getStarting_date()+"\n"+
                "ENDING DATE: "+viaggi[position].getEnding_date());

        viewHolder.mappa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MappaActivity.class);
                intent.putExtra("id",viaggi[position].getId());
                context.startActivity(intent);
            }
        });

        return convertView;

    }

    static class ViewHolder {
        TextView info_viaggio;
        Button mappa;

    }
}
