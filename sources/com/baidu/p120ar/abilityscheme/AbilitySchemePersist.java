package com.baidu.p120ar.abilityscheme;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.baidu.p120ar.utils.ARSDKInfo;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.abilityscheme.AbilitySchemePersist */
/* loaded from: E:\10201592_dexfile_execute.dex */
class AbilitySchemePersist {
    private static final String FIELD_SCHEME = "s";
    private static final String FIELD_SDK_VER = "sv";
    private static final String FIELD_VERSION = "v";
    private static final String PREF_FILE = "ar_ability_classification";

    AbilitySchemePersist() {
    }

    public static void save(Context context, AbilitySchemeClassification abilitySchemeClassification) {
        SharedPreferences.Editor edit = context.getSharedPreferences("ar_ability_classification", 0).edit();
        edit.putString("v", abilitySchemeClassification.classificationId);
        JSONObject jSONObject = abilitySchemeClassification.scheme;
        edit.putString("s", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        edit.putInt("sv", ARSDKInfo.getVersionCode());
        edit.apply();
    }

    public static AbilitySchemeClassification load(Context context) {
        JSONObject jSONObject;
        SharedPreferences sharedPreferences = context.getSharedPreferences("ar_ability_classification", 0);
        String string = sharedPreferences.getString("s", null);
        if (TextUtils.isEmpty(string)) {
            jSONObject = null;
        } else {
            try {
                jSONObject = new JSONObject(string);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
        }
        if (jSONObject != null) {
            AbilitySchemeClassification abilitySchemeClassification = new AbilitySchemeClassification();
            abilitySchemeClassification.classificationId = sharedPreferences.getString("v", "");
            abilitySchemeClassification.scheme = jSONObject;
            return abilitySchemeClassification;
        }
        return null;
    }
}
