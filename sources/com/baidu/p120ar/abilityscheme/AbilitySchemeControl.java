package com.baidu.p120ar.abilityscheme;

import android.content.Context;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.utils.ARLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.abilityscheme.AbilitySchemeControl */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AbilitySchemeControl {
    private static final int HIGH_DEVICE_CPU_SCORE = 8001;
    private static final String JSON_COMMON_KEY = "common";
    public static final String JSON_CPU_SCORE_KEY = "cpu_score";
    private static final int LOW_DEVICE_CPU_SCORE = 4999;
    private static final int MEDIUM_DEVICE_CPU_SCORE = 5001;
    private static final String TAG = "AbilitySchemeControl";
    private String mConfClassification;
    private Context mContext;
    private JSONObject mDefaultScheme;
    private String mFetchUrl;
    private JSONObject mScheme;
    private ICallbackWith<String> mUpdateFailCallback;
    private AbilitySchemeUpdateTask mUpdateTask;

    public AbilitySchemeControl(Context context) {
        this.mConfClassification = "default";
        if (context != null) {
            this.mContext = context.getApplicationContext();
            AbilitySchemeClassification load = AbilitySchemePersist.load(context);
            if (load != null) {
                this.mScheme = load.scheme;
                this.mConfClassification = load.classificationId;
            }
        }
    }

    public void setUpdateFailCallback(ICallbackWith<String> iCallbackWith) {
        this.mUpdateFailCallback = iCallbackWith;
    }

    public void setFetchUrl(String str) {
        this.mFetchUrl = str;
    }

    public void setDefaultScheme(JSONObject jSONObject) {
        this.mDefaultScheme = jSONObject;
    }

    public JSONObject getScheme() {
        if (this.mDefaultScheme == null) {
            String str = TAG;
            ARLog.m20421d(str, "use inner config: " + this.mScheme);
            return this.mScheme;
        }
        String str2 = TAG;
        ARLog.m20421d(str2, "use outter config: " + this.mDefaultScheme);
        return this.mDefaultScheme;
    }

    public int getDeviceLevelByDefaultCpuList() {
        return AbilitySchemeDefaultConfig.getDeviceLevelByDefaultCpuList();
    }

    public void startUpdate(final ICallbackWith<JSONObject> iCallbackWith) {
        this.mUpdateTask = new AbilitySchemeUpdateTask();
        this.mUpdateTask.setFailCallback(new ICallbackWith<String>() { // from class: com.baidu.ar.abilityscheme.AbilitySchemeControl.1
            @Override // com.baidu.p120ar.callback.ICallbackWith
            public void run(String str) {
                if (AbilitySchemeControl.this.mUpdateFailCallback != null) {
                    AbilitySchemeControl.this.mUpdateFailCallback.run(str);
                }
            }
        });
        this.mUpdateTask.fetch(this.mContext, this.mConfClassification, this.mFetchUrl, new ICallbackWith<AbilitySchemeClassification>() { // from class: com.baidu.ar.abilityscheme.AbilitySchemeControl.2
            @Override // com.baidu.p120ar.callback.ICallbackWith
            public void run(AbilitySchemeClassification abilitySchemeClassification) {
                AbilitySchemeControl.this.mUpdateTask = null;
                AbilitySchemeControl.this.updateScheme(abilitySchemeClassification);
                ICallbackWith iCallbackWith2 = iCallbackWith;
                if (iCallbackWith2 == null || abilitySchemeClassification == null) {
                    return;
                }
                iCallbackWith2.run(abilitySchemeClassification.scheme);
            }
        });
    }

    public Map<String, Object> getGradingInfo() {
        HashMap hashMap = new HashMap();
        if (getScheme() == null) {
            hashMap.put("cpu_score", Integer.valueOf(getDeviceLevel()));
            return hashMap;
        }
        JSONObject scheme = getScheme();
        if (scheme == null || !scheme.has("common")) {
            hashMap.put("cpu_score", Integer.valueOf(getDeviceLevel()));
            return hashMap;
        }
        try {
            JSONObject jSONObject = scheme.getJSONObject("common");
            if (jSONObject.has("cpu_score")) {
                hashMap.put("cpu_score", Integer.valueOf(jSONObject.getInt("cpu_score")));
            } else {
                hashMap.put("cpu_score", Integer.valueOf(getDeviceLevel()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            hashMap.put("cpu_score", Integer.valueOf(getDeviceLevel()));
        }
        return hashMap;
    }

    public void release() {
        this.mUpdateFailCallback = null;
        AbilitySchemeUpdateTask abilitySchemeUpdateTask = this.mUpdateTask;
        if (abilitySchemeUpdateTask != null) {
            abilitySchemeUpdateTask.cancel();
            this.mUpdateTask = null;
        }
        this.mContext = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateScheme(AbilitySchemeClassification abilitySchemeClassification) {
        if (abilitySchemeClassification == null || this.mContext == null) {
            return;
        }
        this.mConfClassification = abilitySchemeClassification.classificationId;
        this.mScheme = abilitySchemeClassification.scheme;
        AbilitySchemePersist.save(this.mContext, abilitySchemeClassification);
    }

    private int getDeviceLevel() {
        switch (AbilitySchemeDefaultConfig.getDeviceLevelByDefaultCpuList()) {
            case 1:
                return 5001;
            case 2:
                return 8001;
            default:
                return 4999;
        }
    }
}
