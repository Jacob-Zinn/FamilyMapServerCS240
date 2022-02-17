package com.example.modellitpromx;

import java.util.ArrayList;

public class Utils {

    private static ArrayList<Feed> feeds;

    public static void initFeed() {
        feeds = new ArrayList<>();

        feeds.add(new Feed (R.drawable.feed_img, "2:00:02", 23, "1.5", 4));
        feeds.add(new Feed (R.drawable.feed_img, "2:32:02", 26, "1.6", 5));
        feeds.add(new Feed (R.drawable.feed_img, "2:43:02", 28, "1.9", 6));
    }

    public static ArrayList<Feed> getFeeds() {
        return feeds;
    }


    public static ArrayList<String> getSessionsOptionsArray() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Gatedrop Analytics");
        options.add("Show Twelve Week Target");
        options.add("Clear Filter");
        options.add("Show Hidden Sessions");
        options.add("Cancel");

        return options;
    }
}
