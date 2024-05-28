package com.baidu.p120ar.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.p120ar.bean.ARConfig;
import com.baidu.p120ar.bean.DuMixARConfig;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.utils.RequestParamsBuilder */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RequestParamsBuilder {
    private static String sUUID;

    public static String getUUID(Context context) {
        if (sUUID == null && context != null) {
            UUID deviceUuid = new DeviceUuidFactory(context).getDeviceUuid();
            sUUID = deviceUuid != null ? deviceUuid.toString() : "";
        }
        return sUUID;
    }

    public static void appendUserInfo(Context context, JSONObject jSONObject) throws JSONException {
        if (context == null || jSONObject == null) {
            return;
        }
        jSONObject.put("user_id", getUUID(context));
        jSONObject.put("cuid", ARConfig.getCUID());
    }

    public static void appendSignInfo(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return;
        }
        String aipAppId = DuMixARConfig.getAipAppId();
        appendSignInfoParam(jSONObject, TextUtils.isEmpty(aipAppId) ? 0 : Integer.parseInt(aipAppId), ARConfig.getSignatureAndTime());
    }

    public static void appendSignInfo(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (jSONObject == null) {
            return;
        }
        appendSignInfoParam(jSONObject, TextUtils.isEmpty(str) ? 0 : Integer.parseInt(str), ARConfig.getSignatureAndTime(str, str2));
    }

    private static void appendSignInfoParam(JSONObject jSONObject, int i, Object[] objArr) throws JSONException {
        jSONObject.put("aip_app_id", i);
        jSONObject.put("is_aip", "3");
        jSONObject.put("sign", objArr[0]);
        jSONObject.put("timestamp", objArr[1]);
    }

    public static void appendCaseParams(Context context, JSONObject jSONObject, String str, String str2) throws JSONException {
        JSONObject jSONObject2;
        if (context == null || jSONObject == null) {
            return;
        }
        String arValue = ARConfig.getArValue();
        if (arValue != null) {
            jSONObject2 = new JSONObject(arValue);
        } else {
            jSONObject2 = new JSONObject();
        }
        jSONObject.put("ar_value", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("ar_key", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put("ar_id", str2);
        } else {
            jSONObject.put("ar_id", "");
        }
    }

    public static void appendSystemInfo(Context context, JSONObject jSONObject) throws JSONException {
        if (context == null || jSONObject == null) {
            ARLog.m20420e("bdar: addSystemInfo context/httpParams is null!!!");
            return;
        }
        jSONObject.put("osType", "android");
        jSONObject.put("os_type", "android");
        jSONObject.put("engine_version", ARSDKInfo.getVersionCode());
        jSONObject.put("app_id", ARSDKInfo.getAppId(context));
        jSONObject.put("device_id", Build.MODEL);
        jSONObject.put("system_version", Build.VERSION.SDK_INT);
        jSONObject.put("os_brand", Build.BRAND.toLowerCase());
        jSONObject.put("os_model", Build.MODEL.toLowerCase());
        jSONObject.put("os_version_sdk", Build.VERSION.SDK_INT);
        jSONObject.put("os_version_release", Build.VERSION.RELEASE);
        jSONObject.put("os_width_pixels", context.getResources().getDisplayMetrics().widthPixels);
        jSONObject.put("os_height_pixels", context.getResources().getDisplayMetrics().heightPixels);
        jSONObject.put("os_scale_pdi", context.getResources().getDisplayMetrics().densityDpi);
        long[] romMemroy = SystemInfoUtils.getRomMemroy();
        jSONObject.put("os_rom_memory", romMemroy[0]);
        jSONObject.put("os_rom_avail_memory", romMemroy[1]);
        jSONObject.put("os_sdcard_memory", SystemInfoUtils.getSDCardTotalSize());
        jSONObject.put("os_sdcard_avail_memory", SystemInfoUtils.getSDAvailableSizeByM());
        jSONObject.put("os_ram_memory", SystemInfoUtils.getRamMemory(context));
        jSONObject.put("os_ram_avail_memory", SystemInfoUtils.getAvailMemory(context));
        if (SystemInfoUtils.isHasGyroscope(context)) {
            jSONObject.put("os_has_gyroscope", 1);
        } else {
            jSONObject.put("os_has_gyroscope", 0);
        }
        jSONObject.put("os_cpu_name", SystemInfoUtils.getCpuName());
        jSONObject.put("os_cpu_num_cores", SystemInfoUtils.getNumCores());
        jSONObject.put("os_cpu_min_freq", SystemInfoUtils.getMinCpuFreq());
        jSONObject.put("os_cpu_max_freq", SystemInfoUtils.getMaxCpuFreq());
        jSONObject.put("os_cpu_abi", Build.CPU_ABI);
        jSONObject.put("os_cpu_cur_freq", SystemInfoUtils.getCurCpuFreq());
        jSONObject.put("os_native_heapsize", (int) (Runtime.getRuntime().maxMemory() / 1048576));
        jSONObject.put("os_native_sensor", ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(4) != null);
        jSONObject.put("network_type", NetworkUtils.getNetworkType(context));
        if (Build.VERSION.SDK_INT < 21) {
            jSONObject.put("os_cpu_supported_abis", Build.CPU_ABI);
        } else {
            jSONObject.put("os_cpu_supported_abis", Arrays.asList(Build.SUPPORTED_ABIS));
        }
        jSONObject.put("gles_version", ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >> 16);
        jSONObject.put("board", Build.BOARD);
        jSONObject.put("hardware", Build.HARDWARE);
        jSONObject.put("manufacturer", Build.MANUFACTURER);
    }

    public static void addBasicInfo(Context context, Map<String, Object> map) {
        if (map == null) {
            return;
        }
        String aipAppId = DuMixARConfig.getAipAppId();
        map.put("aip_app_id", Integer.valueOf(TextUtils.isEmpty(aipAppId) ? 0 : Integer.parseInt(aipAppId)));
        map.put("is_aip", "3");
        Object[] signatureAndTime = ARConfig.getSignatureAndTime();
        map.put("sign", signatureAndTime[0]);
        map.put("timestamp", signatureAndTime[1]);
        map.put("user_id", getUUID(context));
        map.put("cuid", ARConfig.getCUID());
    }
}
