package com.baidu.p120ar.ability;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ability.AbilityType */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum AbilityType {
    TRACK_2D("ability_image_track"),
    TRACK_3D("ability_3d_track"),
    IMU("ability_imu"),
    VO("ability_vo"),
    LOCAL_IR("ability_local_image_recognition"),
    CLOUD_IR("ability_cloud_image_recognition"),
    LOGO_IR("ability_logo_recognition"),
    VPAS("ability_vpas"),
    FACE_FILTER("ability_face_filter"),
    FACE_MODEL("ability_face_model"),
    GESTURE("ability_gesture"),
    POSE("ability_pose"),
    BODY_DETECT("ability_body_detect"),
    OBJ_DETECT("ability_object_detect"),
    IMG_SEG("ability_image_segmentation"),
    HAIR_SEG("ability_hair_segmentation"),
    SKY_SEG("ability_sky_segmentation"),
    COMMON_FILTER("ability_common_filter"),
    HAND_SKELETON("ability_hand_skeleton"),
    ANIME("ability_anime"),
    HEAD_SEG("ability_head_segmentation"),
    GENDER_TRANS("com.baidu.ar.gendertrans.GenderTransAR"),
    VPAS_MARKER("ability_marker");
    
    private String mTypeValue;

    AbilityType(String str) {
        this.mTypeValue = str;
    }

    public String getTypeValue() {
        return this.mTypeValue;
    }

    public static AbilityType getTypeOf(String str) {
        AbilityType[] values;
        for (AbilityType abilityType : values()) {
            if (abilityType.getTypeValue() == str) {
                return abilityType;
            }
        }
        return null;
    }
}
