package com.voidabhi.mygolaactivitiesapp.domain;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by voidabhi on 06/11/15.
 */
public class Activity {

    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_IMAGE_URL = "image";
    private static final String JSON_KEY_ACTUAL_PRICE = "actual_price";
    private static final String JSON_KEY_DISCOUNT = "discount";
    private static final String JSON_KEY_RATING = "rating";
    private static final String JSON_KEY_CITY = "city";
    private static final String JSON_KEY_LOCATION = "location";
    private static final String JSON_KEY_DESCRIPTION = "description";

    private String name;
    private String imageUrl;
    private double actualPrice;
    private double discount;
    private int rating;
    private String city;
    private String location;
    private String description;

    public Activity() {

    }

    public Activity(String name, String imageUrl, double actualPrice, double discount, int rating, String city, String location, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.actualPrice = actualPrice;
        this.discount = discount;
        this.rating = rating;
        this.city = city;
        this.location = location;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Activity fromJson(JsonObject jsonObject) {
        Activity a = new Activity();
        // Deserialize json into object fields
        try {
            a.setName(jsonObject.has(JSON_KEY_NAME) ? jsonObject.get(JSON_KEY_NAME).getAsString(): "");
            a.setImageUrl(jsonObject.has(JSON_KEY_IMAGE_URL) ? jsonObject.get(JSON_KEY_IMAGE_URL).getAsString() : "");

            String actualPriceString = jsonObject.has(JSON_KEY_ACTUAL_PRICE)? jsonObject.get(JSON_KEY_ACTUAL_PRICE).getAsString(): "0";
            try {
                a.setActualPrice(Double.parseDouble(actualPriceString));
            } catch (NumberFormatException e) {
                a.setActualPrice(0);
            }

            String discountString = jsonObject.has(JSON_KEY_DISCOUNT)? jsonObject.get(JSON_KEY_DISCOUNT).getAsString(): "0";
            discountString = discountString.replace("%", "");
            try {
                a.setActualPrice(Double.parseDouble(discountString));
            } catch (NumberFormatException e) {
                a.setDiscount(0);
            }

            a.setRating(jsonObject.has(JSON_KEY_RATING) ? jsonObject.get(JSON_KEY_RATING).getAsInt() : 0);
            a.setCity(jsonObject.has(JSON_KEY_CITY) ? jsonObject.get(JSON_KEY_CITY).getAsString() : "");
            a.setLocation(jsonObject.has(JSON_KEY_LOCATION) ? jsonObject.get(JSON_KEY_LOCATION).getAsString() : "");
            a.setDescription(jsonObject.has(JSON_KEY_DESCRIPTION) ? jsonObject.get(JSON_KEY_DESCRIPTION).getAsString(): "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return a;
    }

    public static ArrayList<Activity> fromJson(JsonArray jsonArray) {
        ArrayList<Activity> activities = new ArrayList<Activity>(jsonArray.size());
        // Process each result in json array, decode and convert to business object
        for (int i=0; i < jsonArray.size(); i++) {
            JsonObject activityJson = null;
            try {
                activityJson = jsonArray.getAsJsonObject();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Activity activity = Activity.fromJson(activityJson);
            if (activity != null) {
               activities.add(activity);
            }
        }

        return activities;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", actualPrice=" + actualPrice +
                ", discount=" + discount +
                ", rating=" + rating +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static Comparator<Activity> ActivityDiscountComparator
            = new Comparator<Activity>() {

        public int compare(Activity activity1, Activity activity2) {
            return new Double((activity1.getDiscount() - activity2.getDiscount())).intValue();
        }

    };

    public static Comparator<Activity> ActivityPriceComparator
            = new Comparator<Activity>() {

        public int compare(Activity activity1, Activity activity2) {
            return new Double(activity1.getActualPrice() - activity2.getActualPrice()).intValue();
        }

    };

    public static Comparator<Activity> ActivityRatingComparator
            = new Comparator<Activity>() {

        public int compare(Activity activity1, Activity activity2) {
            return activity1.getRating() - activity2.getRating();
        }

    };
}
