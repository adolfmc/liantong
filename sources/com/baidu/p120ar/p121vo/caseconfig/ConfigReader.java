package com.baidu.p120ar.p121vo.caseconfig;

import com.baidu.p120ar.utils.FileUtils;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.caseconfig.ConfigReader */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ConfigReader {
    public static VOConfig read(String str) {
        File file = new File(str, "targets.json");
        if (file.exists()) {
            try {
                return parseConfig(new JSONObject(FileUtils.readFileText(file)).optJSONObject("slam_model"));
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static VOConfig parseConfig(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        VOConfig vOConfig = new VOConfig();
        try {
            if (jSONObject.has("id")) {
                vOConfig.setId(jSONObject.getString("id"));
            }
            if (jSONObject.has("place_type")) {
                vOConfig.setPlaceType(jSONObject.getInt("place_type"));
            }
            if (jSONObject.has("position")) {
                vOConfig.setPosition(jSONObject.getString("position"));
            }
            if (jSONObject.has("distance")) {
                vOConfig.setDistance(jSONObject.getInt("distance"));
            }
            if (jSONObject.has("pitch_angle")) {
                vOConfig.setPitchAngle(jSONObject.getInt("pitch_angle"));
            }
            if (jSONObject.has("rotation")) {
                vOConfig.setRotation(jSONObject.getString("rotation"));
            }
            if (jSONObject.has("immediately_place_model")) {
                boolean z = true;
                if (jSONObject.getInt("immediately_place_model") != 1) {
                    z = false;
                }
                vOConfig.setImmediatelyPlaceModel(z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vOConfig;
    }
}
