package com.voidabhi.mygolaactivitiesapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voidabhi.mygolaactivitiesapp.R;
import com.voidabhi.mygolaactivitiesapp.domain.Activity;

/**
 * Created by voidabhi on 06/11/15.
 */
public class ActivityFragment extends Fragment {

    private Activity activity;

    public ActivityFragment() {

    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity,container,false);
        return view;
    }


}
