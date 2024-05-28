package com.baidu.p120ar.ability;

import com.baidu.p120ar.auth.ARAuth;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ability.AbilityAuth */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AbilityAuth {
    private static volatile Map<String, Integer> sARTypeCodes = initARTypeCodes();
    private static volatile Map<String, Integer> sAbilityTypeCodes = initAbilityTypeCodes();

    private static Map<String, Integer> initARTypeCodes() {
        HashMap hashMap = new HashMap();
        hashMap.put("com.baidu.ar.vo.VOAR", 1300);
        hashMap.put("com.baidu.ar.pose.PoseAR", 1400);
        hashMap.put("com.baidu.ar.stretch.StretchAR", 1400);
        hashMap.put("com.baidu.ar.gesture.GestureAR", 1500);
        return hashMap;
    }

    private static Map<String, Integer> initAbilityTypeCodes() {
        HashMap hashMap = new HashMap();
        hashMap.put("ability_face_model", 1200);
        hashMap.put("ability_face_filter", 1000);
        hashMap.put("ability_makeup_filter", 1610);
        hashMap.put("ability_vo", 1300);
        hashMap.put("ability_pose", 1400);
        hashMap.put("ability_body_detect", 1400);
        hashMap.put("ability_gesture", 1500);
        hashMap.put("ability_image_segmentation", 1700);
        hashMap.put("ability_sky_segmentation", 1900);
        hashMap.put("ability_hair_segmentation", 1800);
        hashMap.put("ability_object_detect", 2000);
        hashMap.put("ability_3d_track", 3100);
        return hashMap;
    }

    public static boolean checkARTypeAuth(String str) {
        if (sARTypeCodes.containsKey(str)) {
            return ARAuth.checkFeatureAuth(sARTypeCodes.get(str).intValue());
        }
        return true;
    }

    public static boolean checkAbilityTypeAuth(String str) {
        if (sAbilityTypeCodes.containsKey(str)) {
            return ARAuth.checkFeatureAuth(sAbilityTypeCodes.get(str).intValue());
        }
        return true;
    }

    public static boolean hasAbilityTypeAuth(String str) {
        if (sAbilityTypeCodes.containsKey(str)) {
            return ARAuth.enableFeature(sAbilityTypeCodes.get(str).intValue());
        }
        return true;
    }
}
