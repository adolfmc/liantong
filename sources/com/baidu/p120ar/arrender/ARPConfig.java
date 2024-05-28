package com.baidu.p120ar.arrender;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import com.baidu.p120ar.arplay.core.engine.ARPScriptEnvironment;
import com.baidu.p120ar.bean.ARConfig;
import com.baidu.p120ar.utils.ARSDKInfo;
import com.baidu.p120ar.utils.DeviceUuidFactory;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.ARPConfig */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPConfig {
    public static final String APP_CHANNEL = "channel";
    public static final String APP_VERSION = "app_version";
    public static final String AR_KEY = "ar_key";
    public static final String AR_TYPE = "ar_type";
    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_TYPE = "device_type";
    public static final String KEY_EXTRA_INFO = "extra_info";
    public static final String OS_TYPE = "os_type";
    public static final String OS_VERSION = "os_version";
    private String mChannel;
    private String mIsSupportGyroscope;
    private String mIsSupportRotation;
    private String mUUidStr;

    public ARPConfig(Context context) {
        this.mIsSupportGyroscope = "0";
        this.mIsSupportRotation = "0";
        this.mUUidStr = new DeviceUuidFactory(context).getDeviceUuid().toString();
        this.mChannel = ARSDKInfo.getAppId(context);
        for (Sensor sensor : ((SensorManager) context.getSystemService("sensor")).getSensorList(-1)) {
            if (sensor.getType() == 4) {
                this.mIsSupportGyroscope = "1";
            }
            if (sensor.getType() == 11) {
                this.mIsSupportRotation = "1";
            }
        }
    }

    public void updateARPConfig2Engine() {
        updateUserInfo2Engine();
        updateDeviceInfo2Engine();
    }

    private void updateUserInfo2Engine() {
        HashMap hashMap = new HashMap();
        hashMap.put("app_version", String.valueOf(ARSDKInfo.getVersionCode()));
        hashMap.put("ar_key", ARConfig.getARKey());
        hashMap.put("ar_type", Integer.valueOf(ARConfig.getARType()));
        hashMap.put("os_version", String.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("device_id", this.mUUidStr);
        hashMap.put("extra_info", ARConfig.getARExtraInfo());
        hashMap.put("os_type", "android");
        hashMap.put("device_type", Build.BRAND);
        hashMap.put("channel", this.mChannel);
        ARPScriptEnvironment.getInstance().setSharedEnvironmentKV("userinfo", hashMap);
    }

    private void updateDeviceInfo2Engine() {
        HashMap hashMap = new HashMap();
        hashMap.put("android.sensor.gyroscope", this.mIsSupportGyroscope);
        hashMap.put("android.sensor.rotation_vector", this.mIsSupportRotation);
        ARPScriptEnvironment.getInstance().setSharedEnvironmentKV("deviceinfo", hashMap);
    }

    public void setDataPipKV(String str, Object obj) {
        ARPScriptEnvironment.getInstance().setDataPipKV(str, obj);
    }
}
