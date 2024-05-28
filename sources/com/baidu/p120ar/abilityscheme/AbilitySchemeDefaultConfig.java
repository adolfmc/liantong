package com.baidu.p120ar.abilityscheme;

import android.os.Build;
import android.text.TextUtils;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.SystemInfoUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.abilityscheme.AbilitySchemeDefaultConfig */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AbilitySchemeDefaultConfig {
    private static final String DEVICE_CPU_CONFIG = "{\n  \"high\": [\n    \"kirin990\",\n    \"qualcomm technologies, inc sdm855\",\n    \"qualcomm technologies, inc sm8150\",\n    \"kirin980\",\n    \"qualcomm technologies, inc sdm845\",\n    \"kirin810\",\n    \"kirin970\",\n    \"qualcomm technologies, inc sdm730\",\n    \"qualcomm technologies, inc msm8998\",\n    \"hi3660\",\n    \"qualcomm technologies, inc sdm712\",\n    \"kirin960\",\n    \"qualcomm technologies, inc sdm710\",\n    \"qualcomm technologies, inc sdm675\",\n    \"mt6799\",\n    \"mt6785\",\n    \"mt6779\",\n    \"qualcomm technologies, inc sdm670\",\n    \"qualcomm technologies, inc msm8996\",\n    \"mt6775\",\n    \"mt6771v/c\",\n    \"qualcomm technologies, inc sdm665\",\n    \"hisilicon kirin 955\",\n    \"qualcomm technologies, inc sdm660\"\n  ],\n  \"medium\": [\n    \"sailfish\",\n    \"qualcomm technologies, inc sdm636\",\n    \"hi3650\",\n    \"qualcomm technologies, inc msm8994\",\n    \"mt6797\",\n    \"mt6758\",\n    \"mt6763\",\n    \"mt6762\",\n    \"mt6757\",\n    \"mt6795\",\n    \"mt6755\",\n    \"qualcomm technologies, inc msm8976sg\",\n    \"qualcomm technologies, inc msm8976\",\n    \"qualcomm technologies, inc msm8965\",\n    \"qualcomm technologies, inc msm8956\",\n    \"qualcomm technologies, inc msm8992\",\n    \"hi3635\",\n    \"qualcomm technologies, inc 626\",\n    \"qualcomm technologies, inc msm8953\"\n  ],\n  \"low\": [\n    \"qualcomm technologies, inc msm8940\",\n    \"qualcomm technologies, inc msm8974\",\n    \"hi3630\"\n  ],\n  \"blackList\": []\n}";
    public static final int HIGH_DEVICE = 2;
    public static final int LOW_DEVICE = 0;
    public static final int MEDIUM_DEVICE = 1;
    private static Map<String, Integer> deviceLevelMap = new HashMap();

    public static int getDeviceLevelByDefaultCpuList() {
        if (deviceLevelMap.size() <= 0) {
            initDefaultVipList("{\n  \"high\": [\n    \"kirin990\",\n    \"qualcomm technologies, inc sdm855\",\n    \"qualcomm technologies, inc sm8150\",\n    \"kirin980\",\n    \"qualcomm technologies, inc sdm845\",\n    \"kirin810\",\n    \"kirin970\",\n    \"qualcomm technologies, inc sdm730\",\n    \"qualcomm technologies, inc msm8998\",\n    \"hi3660\",\n    \"qualcomm technologies, inc sdm712\",\n    \"kirin960\",\n    \"qualcomm technologies, inc sdm710\",\n    \"qualcomm technologies, inc sdm675\",\n    \"mt6799\",\n    \"mt6785\",\n    \"mt6779\",\n    \"qualcomm technologies, inc sdm670\",\n    \"qualcomm technologies, inc msm8996\",\n    \"mt6775\",\n    \"mt6771v/c\",\n    \"qualcomm technologies, inc sdm665\",\n    \"hisilicon kirin 955\",\n    \"qualcomm technologies, inc sdm660\"\n  ],\n  \"medium\": [\n    \"sailfish\",\n    \"qualcomm technologies, inc sdm636\",\n    \"hi3650\",\n    \"qualcomm technologies, inc msm8994\",\n    \"mt6797\",\n    \"mt6758\",\n    \"mt6763\",\n    \"mt6762\",\n    \"mt6757\",\n    \"mt6795\",\n    \"mt6755\",\n    \"qualcomm technologies, inc msm8976sg\",\n    \"qualcomm technologies, inc msm8976\",\n    \"qualcomm technologies, inc msm8965\",\n    \"qualcomm technologies, inc msm8956\",\n    \"qualcomm technologies, inc msm8992\",\n    \"hi3635\",\n    \"qualcomm technologies, inc 626\",\n    \"qualcomm technologies, inc msm8953\"\n  ],\n  \"low\": [\n    \"qualcomm technologies, inc msm8940\",\n    \"qualcomm technologies, inc msm8974\",\n    \"hi3630\"\n  ],\n  \"blackList\": []\n}");
        }
        String str = getCPUInfo().get("Hardware");
        if (!TextUtils.isEmpty(str)) {
            String trim = str.toLowerCase().replace("hisilicon", "").trim();
            if (deviceLevelMap.containsKey(trim)) {
                return deviceLevelMap.get(trim).intValue();
            }
        }
        return SystemInfoUtils.isHighPerformence() ? 2 : 0;
    }

    private static void initDefaultVipList(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray = jSONObject.getJSONArray("high");
            for (int i = 0; i < jSONArray.length(); i++) {
                deviceLevelMap.put(jSONArray.get(i).toString(), 2);
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray("medium");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                deviceLevelMap.put(jSONArray2.get(i2).toString(), 1);
            }
            JSONArray jSONArray3 = jSONObject.getJSONArray("low");
            for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                deviceLevelMap.put(jSONArray3.get(i3).toString(), 0);
            }
            JSONArray jSONArray4 = jSONObject.getJSONArray("blackList");
            for (int i4 = 0; i4 < jSONArray4.length(); i4++) {
                deviceLevelMap.put(jSONArray4.get(i4).toString(), -1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> getCPUInfo() {
        try {
            HashMap hashMap = new HashMap();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String[] split = readLine.split(":");
                if (split.length > 1) {
                    String replace = split[0].trim().replace(" ", "_");
                    if (replace.equals("model_name")) {
                        replace = "cpu_model";
                    }
                    String trim = split[1].trim();
                    if (replace.equals("cpu_model")) {
                        trim = trim.replaceAll("\\s+", " ");
                    }
                    hashMap.put(replace, trim);
                }
            }
            bufferedReader.close();
            if (!hashMap.keySet().contains("Hardware")) {
                hashMap.put("Hardware", Build.HARDWARE);
            }
            ARLog.m20419e("DEVICE_INFO", (String) hashMap.get("Hardware"));
            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
