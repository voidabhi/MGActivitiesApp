package com.voidabhi.mygolaactivitiesapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.voidabhi.mygolaactivitiesapp.R;
import com.voidabhi.mygolaactivitiesapp.activities.Home;
import com.voidabhi.mygolaactivitiesapp.adapters.ActivityListAdapter;
import com.voidabhi.mygolaactivitiesapp.domain.Activity;
import com.voidabhi.mygolaactivitiesapp.utils.NetUtils;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Future;


/**
 * Created by voidabhi on 06/11/15.
 */
public class ActivityListFragment extends Fragment {

    private static final String DEBUG_TAG = "ArticleListFragment";

    private ActivityListAdapter activityListAdapter;
    Spinner cities;
    ListView activityListView;
    ArrayList<Activity> activities;

    private ArrayList<String>  citiesList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(DEBUG_TAG, "Master created");

        View view = inflater.inflate(R.layout.fragment_activities_list,container,false);
        activityListView = (ListView)view.findViewById(R.id.activities_list);
        final EditText searchEditText = (EditText)view.findViewById(R.id.activity_search);
        cities = (Spinner)view.findViewById(R.id.spinner_cities);
        citiesList = new ArrayList<String>();

//        DHService.fetchArticles(new ArticlesResponseHandler() {
//            @Override
//            public void onArticlesFetchedResult(Exception e, ArrayList<Article> fetchedArticles) {
//                super.onArticlesFetchedResult(e, fetchedArticles);
//                articles = fetchedArticles;
//                if (e == null) {
//                    for (Article article : articles) {
//                        if (!categoriesList.contains(article.getCategory())) {
//                            categoriesList.add(article.getCategory());
//                        }
//                    }
//
//                    ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categoriesList);
//                    categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    categories.setAdapter(categoriesAdapter);
//
////                    categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                        @Override
////                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                            ArrayList<Article> categorizedArticles = new ArrayList<Article>();
////                            for (Article article : articles) {
////                                if (article.getCategory().equals(categoriesList.get(position))) {
////                                    categorizedArticles.add(article);
////                                    articleListAdapter = new ArticleListAdapter(getActivity(), categorizedArticles);
////                                    articleListView.setAdapter(articleListAdapter);
////                                }
////                            }
////                        }
////                    });
//
//                    articleListAdapter = new ArticleListAdapter(getActivity(), articles);
//                    articleListView.setAdapter(articleListAdapter);
//                }
//            }
//        });

        if(NetUtils.isOnline(getActivity())) {
            Ion.with(getActivity())
                    .load("https://mygola.0x10.info/api/mygola?type=json&query=list_activity")
                    .asJsonArray()
                    .setCallback(new FutureCallback<JsonArray>() {
                        @Override
                        public void onCompleted(Exception e, JsonArray activityJsonArray) {
                            if (e == null) {
                                activities = Activity.fromJson(activityJsonArray);
                                for (Activity activity : activities) {
                                    if (!citiesList.contains(activity.getCity())) {
                                        citiesList.add(activity.getCity());
                                    }
                                }

                                ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, citiesList);
                                citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                cities.setAdapter(citiesAdapter);

                                activityListAdapter = new ActivityListAdapter(getActivity(), activities);
                                activityListView.setAdapter(activityListAdapter);
                            }
                        }
                    });
        } else {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

        activityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (activities != null && activities.size() > 0) {
                    ActivityFragment fragment = new ActivityFragment();
                    fragment.setActivity(activities.get(position));
                    ((Home)getActivity()).setCurrentFragment(fragment);
                }
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

//                if (activityListAdapter != null || activityListAdapter.getCount() == 0) {
//                    activityListAdapter.getFilter().filter(cs);
//                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        return view;
    }
}

