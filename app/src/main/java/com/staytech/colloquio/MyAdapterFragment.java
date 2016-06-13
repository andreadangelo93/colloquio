package com.staytech.colloquio;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andrea on 13/06/16.
 */
public class MyAdapterFragment extends BaseAdapter {
    Post [] post;
    Context context;
    ViewHolder viewHolder;
    LayoutInflater inflater = null;

    public MyAdapterFragment(Activity activity, List<Post> post) {
        this.post =  post.toArray(new Post[post.size()]);
        context = activity;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return post.length;
    }

    @Override
    public Object getItem(int position) {
        return post[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.row_fragment, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.info_post = (TextView) convertView.findViewById(R.id.info_post);
            viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.info_post.setText("ID: "+post[position].getId()+"\n"+
                "TIMESTAMP: "+post[position].getTimestamp()+"\n"+
                "LATITUDE: "+post[position].getLatitude()+"\n"+
                "LONGITUDE: "+post[position].getLongitude()+"\n"+
                "TITLE: "+post[position].getTitle()+"\n"+
                "TEXT: "+post[position].getText()+"\n");
        if(post[position].getBmp() != null){
            viewHolder.thumbnail.setImageBitmap(post[position].getBmp());
        }


        return convertView;

    }

    static class ViewHolder {
        TextView info_post;
        ImageView thumbnail;
    }
}
