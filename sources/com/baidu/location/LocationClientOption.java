package com.baidu.location;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class LocationClientOption {
    public static final int FUZZY_MODE = 4;
    public static final int GPS_ONLY = 2;
    public static final int GpsFirst = 1;
    public static final int LOC_SENSITIVITY_HIGHT = 1;
    public static final int LOC_SENSITIVITY_LOW = 3;
    public static final int LOC_SENSITIVITY_MIDDLE = 2;
    public static final int MIN_AUTO_NOTIFY_INTERVAL = 10000;
    public static final int MIN_SCAN_SPAN = 1000;
    public static final int NETWORK_FIRST = 3;

    /* renamed from: a */
    protected LocationMode f5113a;
    public String addrType;
    public float autoNotifyLocSensitivity;
    public int autoNotifyMaxInterval;
    public int autoNotifyMinDistance;
    public int autoNotifyMinTimeInterval;
    public String coorType;
    public boolean disableLocCache;
    public boolean enableSimulateGps;
    public FirstLocType firstLocType;
    public boolean isEnableBeidouMode;
    public boolean isIgnoreCacheException;
    public boolean isIgnoreKillProcess;
    public boolean isNeedAltitude;
    public boolean isNeedAptag;
    public boolean isNeedAptagd;
    public boolean isNeedNewVersionRgc;
    public boolean isNeedPoiRegion;
    public boolean isNeedRegular;
    public boolean isOnceLocation;
    public int locLegalStatus;
    public boolean location_change_notify;
    public boolean mIsNeedDeviceDirect;
    public boolean openGps;
    public int priority;
    public String prodName;
    public int scanSpan;
    public String serviceName;
    public int timeOut;
    public int wifiCacheTimeOut;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum BDLocationPurpose {
        SignIn,
        Sport,
        Transport
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum FirstLocType {
        SPEED_IN_FIRST_LOC,
        ACCURACY_IN_FIRST_LOC
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum LocationMode {
        Hight_Accuracy,
        Battery_Saving,
        Device_Sensors,
        Fuzzy_Locating
    }

    public LocationClientOption() {
        this.coorType = "gcj02";
        this.addrType = "noaddr";
        this.openGps = false;
        this.scanSpan = 0;
        this.timeOut = 12000;
        this.prodName = "SDK6.0";
        this.priority = 1;
        this.location_change_notify = false;
        this.disableLocCache = true;
        this.enableSimulateGps = false;
        this.serviceName = "com.baidu.location.service_v2.9";
        this.isIgnoreCacheException = true;
        this.isIgnoreKillProcess = true;
        this.mIsNeedDeviceDirect = false;
        this.isNeedAptag = false;
        this.isNeedAptagd = false;
        this.isNeedPoiRegion = false;
        this.isNeedRegular = false;
        this.isNeedAltitude = false;
        this.isNeedNewVersionRgc = true;
        this.isOnceLocation = false;
        this.autoNotifyMaxInterval = 0;
        this.autoNotifyLocSensitivity = 0.5f;
        this.autoNotifyMinTimeInterval = 0;
        this.autoNotifyMinDistance = 0;
        this.wifiCacheTimeOut = Integer.MAX_VALUE;
        this.locLegalStatus = 1;
        this.isEnableBeidouMode = false;
        this.firstLocType = FirstLocType.SPEED_IN_FIRST_LOC;
    }

    public LocationClientOption(LocationClientOption locationClientOption) {
        this.coorType = "gcj02";
        this.addrType = "noaddr";
        this.openGps = false;
        this.scanSpan = 0;
        this.timeOut = 12000;
        this.prodName = "SDK6.0";
        this.priority = 1;
        this.location_change_notify = false;
        this.disableLocCache = true;
        this.enableSimulateGps = false;
        this.serviceName = "com.baidu.location.service_v2.9";
        this.isIgnoreCacheException = true;
        this.isIgnoreKillProcess = true;
        this.mIsNeedDeviceDirect = false;
        this.isNeedAptag = false;
        this.isNeedAptagd = false;
        this.isNeedPoiRegion = false;
        this.isNeedRegular = false;
        this.isNeedAltitude = false;
        this.isNeedNewVersionRgc = true;
        this.isOnceLocation = false;
        this.autoNotifyMaxInterval = 0;
        this.autoNotifyLocSensitivity = 0.5f;
        this.autoNotifyMinTimeInterval = 0;
        this.autoNotifyMinDistance = 0;
        this.wifiCacheTimeOut = Integer.MAX_VALUE;
        this.locLegalStatus = 1;
        this.isEnableBeidouMode = false;
        this.firstLocType = FirstLocType.SPEED_IN_FIRST_LOC;
        this.coorType = locationClientOption.coorType;
        this.addrType = locationClientOption.addrType;
        this.openGps = locationClientOption.openGps;
        this.scanSpan = locationClientOption.scanSpan;
        this.timeOut = locationClientOption.timeOut;
        this.prodName = locationClientOption.prodName;
        this.priority = locationClientOption.priority;
        this.location_change_notify = locationClientOption.location_change_notify;
        this.serviceName = locationClientOption.serviceName;
        this.disableLocCache = locationClientOption.disableLocCache;
        this.isIgnoreCacheException = locationClientOption.isIgnoreCacheException;
        this.isIgnoreKillProcess = locationClientOption.isIgnoreKillProcess;
        this.enableSimulateGps = locationClientOption.enableSimulateGps;
        this.f5113a = locationClientOption.f5113a;
        this.isNeedAptag = locationClientOption.isNeedAptag;
        this.isNeedAptagd = locationClientOption.isNeedAptagd;
        this.isNeedPoiRegion = locationClientOption.isNeedPoiRegion;
        this.isNeedRegular = locationClientOption.isNeedRegular;
        this.mIsNeedDeviceDirect = locationClientOption.mIsNeedDeviceDirect;
        this.isNeedAltitude = locationClientOption.isNeedAltitude;
        this.autoNotifyMaxInterval = locationClientOption.autoNotifyMaxInterval;
        this.autoNotifyLocSensitivity = locationClientOption.autoNotifyLocSensitivity;
        this.autoNotifyMinTimeInterval = locationClientOption.autoNotifyMinTimeInterval;
        this.autoNotifyMinDistance = locationClientOption.autoNotifyMinDistance;
        this.wifiCacheTimeOut = locationClientOption.wifiCacheTimeOut;
        this.isNeedNewVersionRgc = locationClientOption.isNeedNewVersionRgc;
        this.isOnceLocation = locationClientOption.isOnceLocation;
        this.locLegalStatus = locationClientOption.locLegalStatus;
        this.isEnableBeidouMode = locationClientOption.isEnableBeidouMode;
        this.firstLocType = locationClientOption.firstLocType;
    }

    public void SetIgnoreCacheException(boolean z) {
        this.isIgnoreCacheException = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m19577a() {
        return this.autoNotifyMaxInterval;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public float m19576b() {
        return this.autoNotifyLocSensitivity;
    }

    public void disableCache(boolean z) {
        this.disableLocCache = z;
    }

    public String getAddrType() {
        return this.addrType;
    }

    public int getAutoNotifyMinDistance() {
        return this.autoNotifyMinDistance;
    }

    public int getAutoNotifyMinTimeInterval() {
        return this.autoNotifyMinTimeInterval;
    }

    public String getCoorType() {
        return this.coorType;
    }

    public LocationMode getLocationMode() {
        return this.f5113a;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getProdName() {
        return this.prodName;
    }

    public int getScanSpan() {
        return this.scanSpan;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    public boolean isDisableCache() {
        return this.disableLocCache;
    }

    public boolean isLocationNotify() {
        return this.location_change_notify;
    }

    public boolean isOnceLocation() {
        return this.isOnceLocation;
    }

    public boolean isOpenGps() {
        return this.openGps;
    }

    public boolean optionEquals(LocationClientOption locationClientOption) {
        return this.coorType.equals(locationClientOption.coorType) && this.addrType.equals(locationClientOption.addrType) && this.openGps == locationClientOption.openGps && this.scanSpan == locationClientOption.scanSpan && this.timeOut == locationClientOption.timeOut && this.prodName.equals(locationClientOption.prodName) && this.location_change_notify == locationClientOption.location_change_notify && this.priority == locationClientOption.priority && this.disableLocCache == locationClientOption.disableLocCache && this.isIgnoreCacheException == locationClientOption.isIgnoreCacheException && this.isNeedNewVersionRgc == locationClientOption.isNeedNewVersionRgc && this.isIgnoreKillProcess == locationClientOption.isIgnoreKillProcess && this.isNeedAptag == locationClientOption.isNeedAptag && this.isNeedAptagd == locationClientOption.isNeedAptagd && this.isNeedPoiRegion == locationClientOption.isNeedPoiRegion && this.isNeedRegular == locationClientOption.isNeedRegular && this.mIsNeedDeviceDirect == locationClientOption.mIsNeedDeviceDirect && this.autoNotifyMaxInterval == locationClientOption.autoNotifyMaxInterval && this.autoNotifyLocSensitivity == locationClientOption.autoNotifyLocSensitivity && this.autoNotifyMinTimeInterval == locationClientOption.autoNotifyMinTimeInterval && this.autoNotifyMinDistance == locationClientOption.autoNotifyMinDistance && this.wifiCacheTimeOut == locationClientOption.wifiCacheTimeOut && this.isOnceLocation == locationClientOption.isOnceLocation && this.locLegalStatus == locationClientOption.locLegalStatus && this.isEnableBeidouMode == locationClientOption.isEnableBeidouMode && this.isNeedAltitude == locationClientOption.isNeedAltitude && this.f5113a == locationClientOption.f5113a && this.enableSimulateGps == locationClientOption.enableSimulateGps && this.firstLocType == locationClientOption.firstLocType;
    }

    @Deprecated
    public void setAddrType(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setIsNeedAddress("all".equals(str));
    }

    public void setCoorType(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.equals("gcj02") || lowerCase.equals("bd09") || lowerCase.equals("bd09ll")) {
            this.coorType = lowerCase;
        }
    }

    public void setEnableSimulateGps(boolean z) {
        this.enableSimulateGps = z;
    }

    public void setFirstLocType(FirstLocType firstLocType) {
        this.firstLocType = firstLocType;
    }

    public void setIgnoreKillProcess(boolean z) {
        this.isIgnoreKillProcess = z;
    }

    public void setIsEnableBeidouMode(boolean z) {
        this.isEnableBeidouMode = z;
    }

    public void setIsNeedAddress(boolean z) {
        this.addrType = z ? "all" : "noaddr";
    }

    public void setIsNeedAltitude(boolean z) {
        this.isNeedAltitude = z;
    }

    public void setIsNeedLocationDescribe(boolean z) {
        this.isNeedAptag = z;
    }

    public void setIsNeedLocationPoiList(boolean z) {
        this.isNeedAptagd = z;
    }

    public void setLocStatus(boolean z) {
        this.locLegalStatus = z ? 1 : !z ? -2 : 0;
    }

    public void setLocationMode(LocationMode locationMode) {
        switch (locationMode) {
            case Hight_Accuracy:
                this.openGps = true;
                this.priority = 1;
                break;
            case Battery_Saving:
                this.openGps = false;
                this.priority = 3;
                break;
            case Device_Sensors:
                this.priority = 2;
                this.openGps = true;
                break;
            case Fuzzy_Locating:
                this.priority = 4;
                this.openGps = false;
                break;
            default:
                throw new IllegalArgumentException("Illegal this mode : " + locationMode);
        }
        this.f5113a = locationMode;
    }

    public void setLocationNotify(boolean z) {
        this.location_change_notify = z;
    }

    public void setLocationPurpose(BDLocationPurpose bDLocationPurpose) {
        if (bDLocationPurpose != null) {
            if (bDLocationPurpose == BDLocationPurpose.SignIn) {
                setLocationMode(LocationMode.Hight_Accuracy);
                setLocationNotify(false);
                setScanSpan(0);
                setNeedNewVersionRgc(true);
                setIsNeedAddress(true);
                setIsNeedLocationPoiList(true);
                setIsNeedAltitude(true);
                setIsNeedLocationDescribe(true);
                setWifiCacheTimeOut(10000);
                return;
            }
            if (bDLocationPurpose == BDLocationPurpose.Sport) {
                setLocationMode(LocationMode.Hight_Accuracy);
                setLocationNotify(true);
                setScanSpan(3000);
            } else if (bDLocationPurpose != BDLocationPurpose.Transport) {
                return;
            } else {
                setLocationMode(LocationMode.Hight_Accuracy);
                setLocationNotify(true);
                setScanSpan(1000);
            }
            setNeedNewVersionRgc(true);
            setIsNeedAddress(true);
            setIsNeedLocationPoiList(false);
            setIsNeedAltitude(true);
            setIsNeedLocationDescribe(false);
            setWifiCacheTimeOut(1000);
        }
    }

    public void setNeedDeviceDirect(boolean z) {
        this.mIsNeedDeviceDirect = z;
    }

    public void setNeedNewVersionRgc(boolean z) {
        this.isNeedNewVersionRgc = z;
    }

    public void setOnceLocation(boolean z) {
        this.isOnceLocation = z;
    }

    public void setOpenAutoNotifyMode() {
        setOpenAutoNotifyMode(0, 0, 1);
    }

    public void setOpenAutoNotifyMode(int i, int i2, int i3) {
        float f;
        int i4 = i > 180000 ? i + 1000 : 180000;
        if (i4 < 10000) {
            throw new IllegalArgumentException("Illegal this maxLocInterval : " + i4 + " , maxLocInterval must >= 10000");
        }
        switch (i3) {
            case 1:
                f = 0.5f;
                break;
            case 2:
                f = 0.3f;
                break;
            case 3:
                f = 0.1f;
                break;
            default:
                throw new IllegalArgumentException("Illegal this locSensitivity : " + i3);
        }
        this.autoNotifyLocSensitivity = f;
        this.autoNotifyMaxInterval = i4;
        this.autoNotifyMinTimeInterval = i;
        this.autoNotifyMinDistance = i2;
    }

    public void setOpenGps(boolean z) {
        this.openGps = z;
    }

    @Deprecated
    public void setPriority(int i) {
        if (i == 1 || i == 3) {
            this.priority = i;
        }
    }

    public void setProdName(String str) {
        if (str.length() > 64) {
            str = str.substring(0, 64);
        }
        this.prodName = str;
    }

    public void setScanSpan(int i) {
        if (i >= 0) {
            this.scanSpan = i;
        }
    }

    @Deprecated
    public void setSema(boolean z, boolean z2, boolean z3) {
        this.isNeedAptag = z;
        this.isNeedPoiRegion = z2;
        this.isNeedRegular = z3;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public void setTimeOut(int i) {
        this.timeOut = i;
    }

    public void setWifiCacheTimeOut(int i) {
        if (i >= 10000) {
            this.wifiCacheTimeOut = i;
        }
    }
}
