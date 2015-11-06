package com.voidabhi.mygolaactivitiesapp.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.voidabhi.mygolaactivitiesapp.domain.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by voidabhi on 06/11/15.
 */
public class FavouriteActivitiesPersistence {

    private static final String APP_PREFERENES_KEY = "mygolaactivitiesapp";
    private static final String FAVOURITED_ACTIVITIES_KEY = "favourited_activities";

    public static void saveActivities(Context context, ArrayList<Activity> activities) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(APP_PREFERENES_KEY,Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(activities);
        editor.putString(FAVOURITED_ACTIVITIES_KEY, jsonFavorites);
        editor.commit();
    }

    public static ArrayList fetchBookmarkedActivities(Context context) {
        SharedPreferences settings;
        List<Activity> activityList;
        settings = context.getSharedPreferences(APP_PREFERENES_KEY,Context.MODE_PRIVATE);
        if (settings.contains(FAVOURITED_ACTIVITIES_KEY)) {
            String jsonFavorites = settings.getString(FAVOURITED_ACTIVITIES_KEY, null);
            Gson gson = new Gson();
            Activity[] activityItems = gson.fromJson(jsonFavorites,Activity[].class);
            activityList = Arrays.asList(activityItems);
            activityList = new ArrayList(activityList);
        } else {
            return null;
        }
        return (ArrayList<Activity>) activityList;
    }
    public static void saveBookmarkedActivity(Context context, Activity Activity) {
        ArrayList<Activity> activities = fetchBookmarkedActivities(context);
        if (activities == null)
            activities = new ArrayList();
        activities.add(Activity);
        saveActivities(context, activities);
    }

    public static void removeBookmarkedActivity(Context context, Activity Activity) {
        ArrayList<Activity>  activities = fetchBookmarkedActivities(context);
        if (activities != null) {
            activities.remove(Activity);
            saveActivities(context, activities);
        }
    }
}
