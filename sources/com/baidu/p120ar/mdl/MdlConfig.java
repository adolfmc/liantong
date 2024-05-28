package com.baidu.p120ar.mdl;

import android.text.TextUtils;
import com.baidu.p120ar.abilityscheme.AbilitySchemeDefaultConfig;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.mdl.MdlConfig */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MdlConfig {
    public static final int TYPE_FACE = 11;
    public static final int TYPE_FACE_ANIMATE = 14;
    public static final int TYPE_FACE_DETECT = 12;
    public static final int TYPE_FACE_TRACK = 13;
    public static final int TYPE_GENDER_TRANS = 10;
    public static final int TYPE_GESTURE = 1;
    public static final int TYPE_HAIR_SEG = 4;
    public static final int TYPE_HAND_SKELETON = 7;
    public static final int TYPE_HEAD_SEG = 8;
    public static final int TYPE_IMG_SEG = 2;
    public static final int TYPE_OBJ_DET = 6;
    public static final int TYPE_POSE = 0;
    public static final int TYPE_SKY_SEG = 5;
    public static final int TYPE_STRETCH = -1;
    public static final int TYPE_STYLE_CONVERSATION = 3;
    public boolean isFromAsset;
    public String[] modelPaths;
    public int type;

    public int getDeviceLevel(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (!(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).trim().equals("{}")) {
                return applyAbilityByServerInfo(jSONObject);
            }
        }
        return AbilitySchemeDefaultConfig.getDeviceLevelByDefaultCpuList();
    }

    private int applyAbilityByServerInfo(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        char c;
        JSONObject optJSONObject3 = jSONObject.optJSONObject("customize");
        if (optJSONObject3 == null || (optJSONObject = optJSONObject3.optJSONObject("algo")) == null || (optJSONObject2 = optJSONObject.optJSONObject("algo_face")) == null) {
            return 0;
        }
        String optString = optJSONObject2.optString("level");
        if (TextUtils.isEmpty(optString)) {
            return 0;
        }
        int hashCode = optString.hashCode();
        if (hashCode == -2135301185) {
            if (optString.equals("mediumlow")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode == -1078030475) {
            if (optString.equals("medium")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 107348) {
            if (hashCode == 3202466 && optString.equals("high")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (optString.equals("low")) {
                c = 3;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return 2;
            case 1:
                return 1;
            default:
                return 0;
        }
    }
}
