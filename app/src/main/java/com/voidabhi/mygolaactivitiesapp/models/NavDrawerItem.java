package com.voidabhi.mygolaactivitiesapp.models;

/**
 * Created by voidabhi on 06/11/15.
 */
public class NavDrawerItem {

    private String title;
    private int icon;


    public NavDrawerItem(String title, int icon){
        this.title = title;
        this.icon = icon;
    }


    public String getTitle(){
        return this.title;
    }

    public int getIcon(){
        return this.icon;
    }

    public void setTitle(String title){
        this.title = title;
    }
}

