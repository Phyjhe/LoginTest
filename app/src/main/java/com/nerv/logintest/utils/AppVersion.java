package com.nerv.logintest.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Phyj on 2016/10/16.
 */

public class AppVersion {
    public static int getAppVersionCode(Context context){
        PackageManager manager=context.getPackageManager();
        try {
            PackageInfo info=manager.getPackageInfo(context.getPackageName(),0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static String getAppVersionName(Context context){
        PackageManager manager=context.getPackageManager();
        try {
            PackageInfo info=manager.getPackageInfo(context.getPackageName(),0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
