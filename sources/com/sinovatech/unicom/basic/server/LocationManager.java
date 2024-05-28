package com.sinovatech.unicom.basic.server;

import android.content.Context;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LocationManager {
    private static String TAG = "LocationManager";
    private static LocationClient mLocationClient;

    public static void startLocation(Context context, int i, BDAbstractLocationListener bDAbstractLocationListener) {
        try {
            LocationClientOption locationClientOption = new LocationClientOption();
            locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            locationClientOption.setCoorType("gcj02");
            locationClientOption.setScanSpan(i);
            locationClientOption.setOpenGps(true);
            locationClientOption.setLocationNotify(true);
            locationClientOption.setIgnoreKillProcess(true);
            locationClientOption.SetIgnoreCacheException(false);
            locationClientOption.setEnableSimulateGps(false);
            locationClientOption.setIsNeedAddress(true);
            mLocationClient = new LocationClient(context.getApplicationContext());
            mLocationClient.setLocOption(locationClientOption);
            mLocationClient.registerLocationListener(bDAbstractLocationListener);
            mLocationClient.start();
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("百度地图sdk异常", e.getMessage());
        }
    }

    public static void stopLocaiton() {
        LocationClient locationClient = mLocationClient;
        if (locationClient != null) {
            locationClient.stop();
        }
    }
}
