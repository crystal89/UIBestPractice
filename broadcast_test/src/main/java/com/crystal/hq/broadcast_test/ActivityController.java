package com.crystal.hq.broadcast_test;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crystal on 2017/5/9.
 * 关闭所有活动
 */

public class ActivityController {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing())
                activity.finish();
        }
    }
}
