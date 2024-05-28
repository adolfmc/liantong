package com.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum AAID {
    FINGER_ECC("001A#3333", 0),
    FINGER_ECC_NEW("004A#01AA", 1),
    FINGER_HKA_ECC("004A#01AD", 4),
    FINGER_SKA_ECC("004A#01A0", 7),
    FINGER_KA_GM("004A#01AE", 5),
    FACE_LOCAL_ECC("004A#01AB", 2),
    GESTURE_ECC("004A#01AC", 3),
    GESTURE_GM("004A#01AF", 6);
    
    public int index;
    public String name;

    AAID(String str, int i) {
        this.name = str;
        this.index = i;
    }
}
