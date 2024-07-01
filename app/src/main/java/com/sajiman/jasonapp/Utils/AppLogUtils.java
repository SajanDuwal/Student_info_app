package com.sajiman.jasonapp.Utils;

import android.util.Log;

import com.sajiman.jasonapp.BuildConfig;

/**
 * Created by HP on 2/18/2018.
 */

public class AppLogUtils {

    public static void showLog(String classTag, String message) {
        if (!BuildConfig.BUILD_TYPE.equals("release")) {
            Log.e("APP_NAME : STUDENT_INFO", classTag + " ::: " + message);
        }
    }
}
