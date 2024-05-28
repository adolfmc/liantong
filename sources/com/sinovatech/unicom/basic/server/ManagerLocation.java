package com.sinovatech.unicom.basic.server;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.dingweientity.LocationCallBackEntity;
import com.sinovatech.unicom.basic.myinterface.MyLocationInterface;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerLocation {
    private static final String TAG = "百度定位--->";
    private static ManagerLocation managerLocation;
    private Activity activity;
    private Activity activityContext;
    private List<LocationCallBackEntity> callBackList;
    private int failedCount;
    private LocationEntity locationEntity;
    private LocationClient mLocClient;
    private RxPermissions rxPermissions;
    private long successLocationTime;
    private MyLocationListenner myListener = new MyLocationListenner(this, null);
    boolean isFirstLoc = true;
    boolean isLocationed = false;
    private MyLocationInterface.CallBack myCallBack = null;
    private String name = null;

    static /* synthetic */ int access$408(ManagerLocation managerLocation2) {
        int i = managerLocation2.failedCount;
        managerLocation2.failedCount = i + 1;
        return i;
    }

    public static ManagerLocation getInstance() {
        if (managerLocation == null) {
            managerLocation = new ManagerLocation();
        }
        return managerLocation;
    }

    private ManagerLocation() {
        this.callBackList = null;
        if (this.locationEntity == null) {
            this.locationEntity = new LocationEntity();
        }
        if (this.callBackList == null) {
            this.callBackList = new ArrayList();
        }
        try {
            if (this.mLocClient == null) {
                this.mLocClient = new LocationClient(App.getInstance());
            }
        } catch (Exception e) {
            logE("定位构造方法初始化 异常：" + e.getMessage());
        }
    }

    public static void releaseManagerLocation() {
        managerLocation = null;
    }

    private void addCallBack(Activity activity, String str, MyLocationInterface.CallBack callBack) {
        try {
            if (this.callBackList == null) {
                this.callBackList = new ArrayList();
            }
            boolean z = false;
            if (this.callBackList.size() > 0) {
                int i = 0;
                while (true) {
                    if (i >= this.callBackList.size()) {
                        break;
                    } else if (str.equals(this.callBackList.get(i).getName())) {
                        this.callBackList.get(i).setCallBack(callBack);
                        z = true;
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (z) {
                return;
            }
            LocationCallBackEntity locationCallBackEntity = new LocationCallBackEntity();
            locationCallBackEntity.setCallBack(callBack);
            locationCallBackEntity.setName(str);
            locationCallBackEntity.setActivity(activity);
            this.callBackList.add(locationCallBackEntity);
        } catch (Exception e) {
            logE("添加回调异常：" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCallBackAll(LocationEntity locationEntity) {
        try {
            if (this.callBackList == null || this.callBackList.size() <= 0) {
                return;
            }
            for (int i = 0; i < this.callBackList.size(); i++) {
                LocationCallBackEntity locationCallBackEntity = this.callBackList.get(i);
                if (hasActivity(locationCallBackEntity.getActivity())) {
                    locationCallBackEntity.getCallBack().Success(locationEntity);
                }
            }
            for (int size = this.callBackList.size() - 1; size >= 0; size--) {
                this.callBackList.remove(size);
            }
        } catch (Exception e) {
            logE("发送所有成功回调监听 异常：" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendErrorCallBackAll() {
        try {
            LocationEntity locationEntity = new LocationEntity();
            locationEntity.setLocationSuccess(false);
            locationEntity.setGrant(false);
            locationEntity.setStatus(3);
            locationEntity.setBdLocation(null);
            sendCallBackAll(locationEntity);
        } catch (Exception e) {
            logE("发送所有的失败回调监听 异常：" + e.getMessage());
        }
    }

    private boolean hasActivity(Activity activity) {
        if (activity != null) {
            try {
                if (!activity.isDestroyed()) {
                    if (!activity.isFinishing()) {
                        return true;
                    }
                }
            } catch (Exception e) {
                logE("判断当前上下文是否被销毁 异常：" + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public void startLocation(Activity activity, String str, MyLocationInterface.CallBack callBack) {
        try {
            logD("【开始请求定位：】" + str);
            this.activityContext = SoulPermission.getInstance().getTopActivity();
            this.rxPermissions = new RxPermissions(this.activityContext);
            this.myCallBack = callBack;
            this.name = str;
            this.activity = activity;
            if (this.locationEntity.isLocationSuccess() && System.currentTimeMillis() - this.successLocationTime < 300000) {
                try {
                    callBack.Success(this.locationEntity);
                    UIUtils.logD(TAG, "成功时间-->" + this.successLocationTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (!DeviceHelper.isLocationEnabled()) {
                if (!App.getSharePreferenceUtil().getBoolean("isLocationEnabled") && !this.isLocationed) {
                    this.isLocationed = true;
                    CustomDialogManager.show(this.activityContext, "温馨提示", "开启定位权限，获取您附近营业厅信息", true, "不再提示", "去开启", false, false, (CustomDialogManager.CustomeDialogListener) new C71571());
                    return;
                }
                returnFailLocation(0);
            } else {
                startRequestPermission();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            logE("系统定位权限异常：" + e2.getMessage());
            returnFailLocation(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.server.ManagerLocation$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C71571 implements CustomDialogManager.CustomeDialogListener {
        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
        public void onBackKeyDown() {
        }

        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
        public void onCancel() {
        }

        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
        public void onShow() {
        }

        C71571() {
        }

        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
        public void onClickOk() {
            try {
                new AvoidOnResult(ManagerLocation.this.activityContext).startForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.server.-$$Lambda$ManagerLocation$1$TFSe6btiZRygsHo2oxRDSrsIdsc
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public final void onActivityResult(int i, Intent intent) {
                        ManagerLocation.C71571.lambda$onClickOk$0(ManagerLocation.C71571.this, i, intent);
                    }
                });
            } catch (Exception e) {
                MsLogUtil.m7977e("managerLocation", e.getMessage());
                ManagerLocation.this.returnFailLocation(0);
            }
        }

        public static /* synthetic */ void lambda$onClickOk$0(C71571 c71571, int i, Intent intent) {
            if (!DeviceHelper.isLocationEnabled()) {
                ManagerLocation.this.returnFailLocation(0);
            } else {
                ManagerLocation.this.startRequestPermission();
            }
        }

        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
        public void onClickCancel() {
            App.getSharePreferenceUtil().putBoolean("isLocationEnabled", true);
            ManagerLocation.this.returnFailLocation(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRequestPermission() {
        try {
            PackageManager packageManager = this.activityContext.getPackageManager();
            boolean z = packageManager.checkPermission("android.permission.ACCESS_COARSE_LOCATION", this.activityContext.getPackageName()) == 0;
            boolean z2 = packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", this.activityContext.getPackageName()) == 0;
            if (!z && !z2) {
                returnFailLocation(1);
            }
            this.locationEntity.setGrant(true);
            this.locationEntity.setStatus(3);
            startBDLocation();
        } catch (Exception e) {
            logE("请求app内部权限异常：" + e.getMessage());
            returnFailLocation(1);
        }
    }

    private void startBDLocation() {
        try {
            this.isFirstLoc = true;
            this.failedCount = 0;
            this.mLocClient.registerLocationListener(this.myListener);
            LocationClientOption locationClientOption = new LocationClientOption();
            locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            locationClientOption.setScanSpan(0);
            locationClientOption.setOpenGps(true);
            locationClientOption.setLocationNotify(true);
            locationClientOption.setIgnoreKillProcess(true);
            locationClientOption.SetIgnoreCacheException(false);
            locationClientOption.setWifiCacheTimeOut(600000);
            locationClientOption.setEnableSimulateGps(false);
            locationClientOption.setIsNeedAddress(true);
            this.mLocClient.setLocOption(locationClientOption);
            String coorType = locationClientOption.getCoorType();
            MsLogUtil.m7979d(TAG, "coorType:" + coorType);
            this.mLocClient.start();
            if (!TextUtils.isEmpty(this.name) && this.myCallBack != null) {
                addCallBack(this.activity, this.name, this.myCallBack);
            }
            TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", "", "百度地图", "", "com.baidu.mapapi.map", "0");
        } catch (Exception e) {
            logE("百度定位方法异常：" + e.getMessage());
            returnFailLocation(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MyLocationListenner implements BDLocationListener {
        private MyLocationListenner() {
        }

        /* synthetic */ MyLocationListenner(ManagerLocation managerLocation, C71571 c71571) {
            this();
        }

        @Override // com.baidu.location.BDLocationListener
        public void onReceiveLocation(BDLocation bDLocation) {
            try {
                ManagerLocation.access$408(ManagerLocation.this);
                if (bDLocation != null) {
                    int locType = bDLocation.getLocType();
                    if (ManagerLocation.this.isFirstLoc && (locType == 61 || locType == 161)) {
                        MsLogUtil.m7979d("MyLocationListenner", "latitude" + bDLocation.getLatitude() + "    latitude" + bDLocation.getLongitude());
                        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                        StringBuilder sb = new StringBuilder();
                        sb.append("");
                        sb.append(bDLocation.getLatitude());
                        sharePreferenceUtil.putString("lat", sb.toString());
                        SharePreferenceUtil sharePreferenceUtil2 = App.getSharePreferenceUtil();
                        sharePreferenceUtil2.putString("long", "" + bDLocation.getLongitude());
                        App.getSharePreferenceUtil().putString("cityName", bDLocation.getCity());
                        ManagerLocation.this.isFirstLoc = false;
                        ManagerLocation.this.locationEntity.setLocationSuccess(true);
                        ManagerLocation.this.locationEntity.setBdLocation(bDLocation);
                        ManagerLocation.this.sendCallBackAll(ManagerLocation.this.locationEntity);
                        ManagerLocation.this.successLocationTime = System.currentTimeMillis();
                        if (ManagerLocation.this.mLocClient != null) {
                            ManagerLocation.this.mLocClient.stop();
                            ManagerLocation.this.mLocClient.unRegisterLocationListener(ManagerLocation.this.myListener);
                            return;
                        }
                        return;
                    }
                }
                if (ManagerLocation.this.failedCount < 3 || !ManagerLocation.this.isFirstLoc) {
                    return;
                }
                ManagerLocation.this.isFirstLoc = false;
                ManagerLocation.this.sendErrorCallBackAll();
                if (ManagerLocation.this.mLocClient == null || !ManagerLocation.this.mLocClient.isStarted()) {
                    return;
                }
                ManagerLocation.this.mLocClient.stop();
                ManagerLocation.this.mLocClient.unRegisterLocationListener(ManagerLocation.this.myListener);
            } catch (Exception e) {
                e.printStackTrace();
                ManagerLocation managerLocation = ManagerLocation.this;
                managerLocation.logE("百度定位回调内部 异常：" + e.getMessage());
                ManagerLocation.this.sendErrorCallBackAll();
            }
        }
    }

    public LocationEntity getLocationEntity() {
        if (this.locationEntity == null) {
            this.locationEntity = new LocationEntity();
        }
        return this.locationEntity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void returnFailLocation(int i) {
        try {
            if (this.locationEntity == null || !this.locationEntity.isLocationSuccess() || this.locationEntity.getBdLocation() == null) {
                if (this.locationEntity == null) {
                    this.locationEntity = new LocationEntity();
                }
                this.locationEntity.setStatus(i);
                if (i == 3) {
                    this.locationEntity.setGrant(true);
                } else {
                    this.locationEntity.setGrant(false);
                }
                this.locationEntity.setBdLocation(null);
                this.locationEntity.setLocationSuccess(false);
            }
            if (hasActivity(this.activity) && this.myCallBack != null) {
                this.myCallBack.Success(this.locationEntity);
                logD(this.name + " 【单个回调单独返回 成功】");
                return;
            }
            logD(this.name + " 【单个回调单独返回 失败】");
        } catch (Exception e) {
            logE("返回结果失败：" + e.getMessage());
        }
    }

    private void logD(String str) {
        MsLogUtil.m7979d(TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logE(String str) {
        MsLogUtil.m7977e(TAG, str);
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class LocationEntity {
        private BDLocation bdLocation;
        private boolean isGrant;
        private boolean isLocationSuccess;
        private int status;

        public LocationEntity() {
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public boolean isGrant() {
            return this.isGrant;
        }

        public void setGrant(boolean z) {
            this.isGrant = z;
        }

        public boolean isLocationSuccess() {
            return this.isLocationSuccess;
        }

        public void setLocationSuccess(boolean z) {
            this.isLocationSuccess = z;
        }

        public BDLocation getBdLocation() {
            if (this.bdLocation == null) {
                this.bdLocation = new BDLocation();
            }
            return this.bdLocation;
        }

        public void setBdLocation(BDLocation bDLocation) {
            this.bdLocation = bDLocation;
        }

        public String toString() {
            return "LocationEntity{status=" + this.status + ", bdLocation=" + this.bdLocation + ", isGrant=" + this.isGrant + ", isLocationSuccess=" + this.isLocationSuccess + '}';
        }
    }
}
