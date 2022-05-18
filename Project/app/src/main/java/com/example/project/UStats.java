package com.example.project;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class UStats {

    public static  final SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy HH:mm:ss");
    public static final String TAG = UStats.class.getSimpleName();

    public static void getStats(Context context){
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        int interval = UsageStatsManager.INTERVAL_YEARLY;
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.YEAR, -1);
        long startTime = calendar.getTimeInMillis();

        Log.d(TAG, "Range start:" + dateFormat.format(startTime) );
        Log.d(TAG, "Range end:" + dateFormat.format(endTime));

        UsageEvents uEvents = usm.queryEvents(startTime, endTime);
        while (uEvents.hasNextEvent()){
            UsageEvents.Event e = new UsageEvents.Event();
            uEvents.getNextEvent(e);

            if(e != null){
                Log.d(TAG, "Event: " + e.getPackageName() + "\t" +  e.getTimeStamp());
            }
        }
    }

    public static List<UsageStats> getUsageStatsList(Context context){
        UsageStatsManager usm = getUsageStatsManager(context);
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH, -5);
        long startTime = calendar.getTimeInMillis();

        Log.d(TAG, "Range start:" + dateFormat.format(startTime) );
        Log.d(TAG, "Range end:" + dateFormat.format(endTime));

        List<UsageStats> usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);
        return usageStatsList;
    }


    public static void printUsageStats(List<UsageStats> usageStatsList){
        int count_youtube = 0, count_google = 0, count_instagram = 0, count_clash = 0;
        for (UsageStats u : usageStatsList){
            if(u.getPackageName().equals("com.google.android.youtube")) {
                Log.d(TAG, "Pkg: " + u.getPackageName() + "\t" + "ForegroundTime: " + u.getTotalTimeInForeground());
                count_youtube++;
            }
            if(u.getPackageName().equals("com.instagram.android")) {
                Log.d(TAG, "Pkg: " + u.getPackageName() + "\t" + "ForegroundTime: " + u.getTotalTimeInForeground());
                count_instagram++;
            }
            if(u.getPackageName().equals("com.supercell.clashofclans")) {
                Log.d(TAG, "Pkg: " + u.getPackageName() + "\t" + "ForegroundTime: " + u.getTotalTimeInForeground());
                count_clash++;
            }
            if(u.getPackageName().equals("com.com.android.chrome")) {
                Log.d(TAG, "Pkg: " + u.getPackageName() + "\t" + "ForegroundTime: " + u.getTotalTimeInForeground());
                count_google++;
            }
            //per vedere tutti i package
            //Log.d(TAG, "Pkg: " + u.getPackageName() +  "\t" + "ForegroundTime: "
                    //+ u.getTotalTimeInForeground()) ;
        }
        Log.d(TAG, "count_youtube: " + count_youtube);
        Log.d(TAG, "count_instagram: " + count_instagram);
        Log.d(TAG, "count_google: " + count_google);
        Log.d(TAG, "count_clash: " + count_clash);
    }

    public static void printCurrentUsageStatus(Context context){
        printUsageStats(getUsageStatsList(context));
    }

    private static UsageStatsManager getUsageStatsManager(Context context){
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        return usm;
    }
}
