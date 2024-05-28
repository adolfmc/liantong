package com.baidu.p120ar.statistic;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.p120ar.bean.ARConfig;
import com.baidu.p120ar.bean.DuMixARConfig;
import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.HttpFactory;
import com.baidu.p120ar.ihttp.IHttpRequest;
import com.baidu.p120ar.ihttp.IHttpResponse;
import com.baidu.p120ar.utils.ARSDKInfo;
import com.baidu.p120ar.utils.DeviceUuidFactory;
import com.baidu.p120ar.utils.NetworkUtils;
import com.baidu.p120ar.utils.SystemInfoUtils;
import com.baidu.p120ar.utils.UrlUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.LogSender */
/* loaded from: E:\10201592_dexfile_execute.dex */
class LogSender implements ILogSender {
    private static final String RESPONSE_SUCCESS = "success";

    @Override // com.baidu.p120ar.statistic.ILogSender
    public void send(Context context, EventData eventData) throws IOException, HttpException {
        if (context == null) {
            throw new SecurityException("context is cleared");
        }
        try {
            JSONObject json = eventData.toJson();
            appendSingleEventJson(json, eventData);
            appendPublicParams(json, context);
            doRequest(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    @Override // com.baidu.p120ar.statistic.ILogSender
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void send(android.content.Context r8, java.util.List<com.baidu.p120ar.statistic.EventData> r9) throws java.io.IOException, com.baidu.p120ar.ihttp.HttpException {
        /*
            r7 = this;
            if (r8 == 0) goto L7b
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            r1 = 0
            java.util.Map r2 = r7.extractSameKeys(r9)     // Catch: org.json.JSONException -> L71
            if (r2 == 0) goto L13
            java.util.Set r3 = r2.keySet()     // Catch: org.json.JSONException -> L71
            goto L14
        L13:
            r3 = r1
        L14:
            java.util.Iterator r9 = r9.iterator()     // Catch: org.json.JSONException -> L71
        L18:
            boolean r4 = r9.hasNext()     // Catch: org.json.JSONException -> L71
            if (r4 == 0) goto L42
            java.lang.Object r4 = r9.next()     // Catch: org.json.JSONException -> L71
            com.baidu.ar.statistic.EventData r4 = (com.baidu.p120ar.statistic.EventData) r4     // Catch: org.json.JSONException -> L71
            if (r1 != 0) goto L37
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L71
            r5.<init>()     // Catch: org.json.JSONException -> L71
            java.lang.String r1 = "event_id"
            java.lang.String r6 = "[multi]"
            r5.put(r1, r6)     // Catch: org.json.JSONException -> L34
            r1 = r5
            goto L37
        L34:
            r8 = move-exception
            r1 = r5
            goto L72
        L37:
            org.json.JSONObject r5 = r4.toJson(r3)     // Catch: org.json.JSONException -> L71
            r7.appendSingleEventJson(r5, r4)     // Catch: org.json.JSONException -> L71
            r0.put(r5)     // Catch: org.json.JSONException -> L71
            goto L18
        L42:
            if (r1 == 0) goto L75
            if (r2 == 0) goto L68
            java.util.Set r9 = r2.entrySet()     // Catch: org.json.JSONException -> L71
            java.util.Iterator r9 = r9.iterator()     // Catch: org.json.JSONException -> L71
        L4e:
            boolean r2 = r9.hasNext()     // Catch: org.json.JSONException -> L71
            if (r2 == 0) goto L68
            java.lang.Object r2 = r9.next()     // Catch: org.json.JSONException -> L71
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch: org.json.JSONException -> L71
            java.lang.Object r3 = r2.getKey()     // Catch: org.json.JSONException -> L71
            java.lang.String r3 = (java.lang.String) r3     // Catch: org.json.JSONException -> L71
            java.lang.Object r2 = r2.getValue()     // Catch: org.json.JSONException -> L71
            r1.put(r3, r2)     // Catch: org.json.JSONException -> L71
            goto L4e
        L68:
            r7.appendPublicParams(r1, r8)     // Catch: org.json.JSONException -> L71
            java.lang.String r8 = "data"
            r1.put(r8, r0)     // Catch: org.json.JSONException -> L71
            goto L75
        L71:
            r8 = move-exception
        L72:
            r8.printStackTrace()
        L75:
            if (r1 == 0) goto L7a
            r7.doRequest(r1)
        L7a:
            return
        L7b:
            java.lang.SecurityException r8 = new java.lang.SecurityException
            java.lang.String r9 = "context is cleared"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.statistic.LogSender.send(android.content.Context, java.util.List):void");
    }

    protected Map<String, Object> extractSameKeys(List<EventData> list) {
        Object field;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        EventData eventData = list.get(0);
        ArrayList<String> arrayList = new ArrayList();
        Iterator<String> keys = eventData.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!"event_id".equals(next) && (field = eventData.getField(next)) != null) {
                hashMap.put(next, field);
                hashMap2.put(next, field);
                arrayList.add(next);
            }
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            EventData eventData2 = list.get(i);
            for (String str : arrayList) {
                if (hashMap2.containsKey(str) && !hashMap.get(str).equals(eventData2.getField(str))) {
                    hashMap2.remove(str);
                }
            }
        }
        return hashMap2;
    }

    protected void appendSignInfo(JSONObject jSONObject) throws JSONException {
        jSONObject.put("aip_app_id", DuMixARConfig.getAipAppId());
        jSONObject.put("is_aip", "3");
        Object[] signatureAndTime = ARConfig.getSignatureAndTime();
        jSONObject.put("sign", signatureAndTime[0]);
        jSONObject.put("timestamp", signatureAndTime[1]);
    }

    protected void appendSystemInfo(Context context, JSONObject jSONObject) throws JSONException {
        if (context == null || jSONObject == null) {
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
    }

    protected void appendPublicParams(JSONObject jSONObject, Context context) throws JSONException {
        UUID deviceUuid = new DeviceUuidFactory(context).getDeviceUuid();
        String uuid = deviceUuid != null ? deviceUuid.toString() : "";
        appendSignInfo(jSONObject);
        jSONObject.put("aip_app_id", DuMixARConfig.getAipAppId());
        jSONObject.put("cuid", ARConfig.getCUID());
        appendSystemInfo(context, jSONObject);
        jSONObject.put("os_type", "android");
        jSONObject.put("os_version", Build.MODEL);
        jSONObject.put("device_type", Build.BRAND);
        jSONObject.put("user_id", uuid);
        jSONObject.put("device_id", uuid);
        jSONObject.put("os_version", Build.VERSION.SDK_INT);
        jSONObject.put("app_version", ARSDKInfo.getVersionCode());
        jSONObject.put("engine_version", ARSDKInfo.getVersionCode());
        if (!TextUtils.isEmpty(ARSDKInfo.getAppId(context))) {
            jSONObject.put("app_id", ARSDKInfo.getAppId(context));
        }
        jSONObject.put("system_version", Build.VERSION.SDK_INT);
        jSONObject.put("hardware", Build.HARDWARE);
        jSONObject.put("manufacture", Build.MANUFACTURER);
        jSONObject.put("board", Build.BOARD);
    }

    protected JSONObject appendSingleEventJson(JSONObject jSONObject, EventData eventData) throws JSONException {
        String uniqTag = eventData.getUniqTag();
        if (uniqTag != null) {
            jSONObject.put("_uniq", uniqTag);
        }
        return jSONObject;
    }

    protected void doRequest(JSONObject jSONObject) throws IOException, HttpException {
        String str;
        int i;
        IHttpRequest newRequest = HttpFactory.newRequest();
        if (newRequest == null) {
            return;
        }
        newRequest.setUrl(UrlUtils.getStatisticUrl()).setMethod("POST").setBody(jSONObject);
        IHttpResponse execute = newRequest.execute();
        if (execute.isSuccess()) {
            i = execute.getCode();
            str = execute.getContent();
        } else {
            str = "";
            i = 0;
        }
        if (i != 200 || !str.equals("success")) {
            throw new IOException(String.format("stats response is error. code: %d response: %s", Integer.valueOf(i), str));
        }
    }
}
