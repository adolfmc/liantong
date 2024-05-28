package com.baidu.p120ar.marker;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.marker.TrackerType */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum TrackerType {
    NO_TRACKER_DATA(0),
    STANDARD_TRACKER_DATA(1),
    VIO_ARKIT_DATA(2),
    VIO_ARCORE_DATA(3),
    IMU_IPHONE(4),
    IMU_ANDROID(5);
    
    private int value;

    TrackerType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
