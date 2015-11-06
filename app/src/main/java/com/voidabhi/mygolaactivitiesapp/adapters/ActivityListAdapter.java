package com.voidabhi.mygolaactivitiesapp.adapters;

/**
 * Created by voidabhi on 06/11/15.
 */

import com.voidabhi.mygolaactivitiesapp.R;
import com.voidabhi.mygolaactivitiesapp.domain.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by voidabhi on 18/10/15.
 */
public class ActivityListAdapter extends ArrayAdapter<Activity> {

    public ActivityListAdapter(Context context, ArrayList<Activity> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Activity activity = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item,null);
        }

        TextView title = (TextView)convertView.findViewById(R.id.activity_title);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.activity_image);

        title.setText(activity.getName());
        Picasso.with(getContext()).load(activity.getImageUrl()).fit().centerCrop().into(imageView);


        return convertView;

    }

    @Override
    public Activity getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

}

