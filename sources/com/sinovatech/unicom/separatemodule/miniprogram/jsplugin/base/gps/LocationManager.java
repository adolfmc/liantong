package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import android.app.Activity;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LocationManager {
    private static final String TAG = "LocationManager";
    private static LocationManager instance;
    private static LocationClient mLocationClient;
    private Activity activityContext;
    private BDLocation cacheLocation;
    private boolean isLocation;
    private LocationListener locationListener;
    private long locationSuccessTime = 0;
    private long timeInterval;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface LocationListener {
        void onFail(String str, String str2);

        void onSuccess(BDLocation bDLocation);
    }

    public static LocationManager getInstance() {
        if (instance == null) {
            synchronized (LocationManager.class) {
                if (instance == null) {
                    instance = new LocationManager();
                }
            }
        }
        return instance;
    }

    private boolean checkTimeInterval(long j) {
        System.currentTimeMillis();
        long j2 = this.locationSuccessTime;
        return System.currentTimeMillis() - this.locationSuccessTime >= j;
    }

    private boolean isUseLocationCache() {
        return (this.cacheLocation == null || checkTimeInterval(this.timeInterval)) ? false : true;
    }

    public void startLocation(Activity activity, long j, LocationListener locationListener) {
        this.activityContext = activity;
        this.timeInterval = j;
        this.locationListener = locationListener;
        if (this.isLocation) {
            MsLogUtil.m7979d(TAG, "正在定位中");
            locationFailCallBack("10", "正在定位中");
            return;
        }
        this.isLocation = true;
        if (isUseLocationCache()) {
            MsLogUtil.m7979d(TAG, "使用了缓存数据");
            locationSuccessCallBack(this.cacheLocation);
            return;
        }
        MsLogUtil.m7979d(TAG, "无缓存请求定位");
        PermissionDialog.show("需要您授予中国联通APP位置权限，以开启与位置相关的推荐/搜索/安全保障服务。");
        new RxPermissions(activity).request("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.LocationManager.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                PermissionDialog.dimissDialog();
                if (bool.booleanValue()) {
                    if (DeviceHelper.isLocationEnabled()) {
                        LocationManager.this.startLocation();
                        return;
                    } else {
                        LocationManager.this.locationFailCallBack("13", "用户没有开启手机位置信息开关");
                        return;
                    }
                }
                LocationManager.this.locationFailCallBack("12", "用户拒绝访问定位权限");
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.LocationManager.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                PermissionDialog.dimissDialog();
                LocationManager locationManager = LocationManager.this;
                locationManager.locationFailCallBack("10", "程序错误:" + th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLocation() {
        try {
            LocationClientOption locationClientOption = new LocationClientOption();
            locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            locationClientOption.setCoorType("gcj02");
            locationClientOption.setScanSpan(0);
            locationClientOption.setOpenGps(true);
            locationClientOption.setLocationNotify(true);
            locationClientOption.setIgnoreKillProcess(true);
            locationClientOption.SetIgnoreCacheException(false);
            locationClientOption.setEnableSimulateGps(false);
            locationClientOption.setIsNeedAddress(true);
            mLocationClient = new LocationClient(this.activityContext.getApplicationContext());
            mLocationClient.setLocOption(locationClientOption);
            mLocationClient.registerLocationListener(new BDAbstractLocationListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.LocationManager.3
                @Override // com.baidu.location.BDAbstractLocationListener
                public void onReceiveLocation(BDLocation bDLocation) {
                    if (bDLocation != null) {
                        int locType = bDLocation.getLocType();
                        if (locType == 61 || locType == 161) {
                            LocationManager.this.locationSuccessTime = System.currentTimeMillis();
                            LocationManager.this.cacheLocation = bDLocation;
                            LocationManager.this.locationSuccessCallBack(bDLocation);
                            MsLogUtil.m7979d(LocationManager.TAG, "系统定位成功");
                            return;
                        }
                        LocationManager.this.locationFailCallBack(String.valueOf(locType), bDLocation.getLocTypeDescription());
                        return;
                    }
                    LocationManager.this.locationFailCallBack("10", "程序错误:BDLocation为空");
                }
            });
            mLocationClient.start();
            TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", "", "百度地图", "", "com.baidu.mapapi.map", "0");
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "定位异常:" + e.getMessage());
            locationFailCallBack("10", "程序错误:" + e.getMessage());
        }
    }

    public void locationSuccessCallBack(BDLocation bDLocation) {
        this.isLocation = false;
        LocationListener locationListener = this.locationListener;
        if (locationListener != null) {
            locationListener.onSuccess(bDLocation);
        }
    }

    public void locationFailCallBack(String str, String str2) {
        this.isLocation = false;
        String str3 = TAG;
        MsLogUtil.m7979d(str3, str + ":" + str2);
        LocationListener locationListener = this.locationListener;
        if (locationListener != null) {
            locationListener.onFail(str, str2);
        }
    }

    public void stopLocaiton() {
        LocationClient locationClient = mLocationClient;
        if (locationClient != null) {
            this.isLocation = false;
            locationClient.stop();
        }
    }
}
