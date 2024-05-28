package com.baidu.p120ar;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ARType */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum ARType {
    TRACK_2D(0),
    LBS(1),
    GAME(2),
    UDT(3),
    NPC(4),
    VO(5),
    ON_DEVICE_IR(6),
    CLOUD_IR(7),
    IMU(8),
    FACE(10),
    VPAS(11),
    MARKER(12),
    TRACK_3D(13);
    
    private int mTypeValue;

    ARType(int i) {
        this.mTypeValue = i;
    }

    public int getTypeValue() {
        return this.mTypeValue;
    }

    public static ARType valueOf(int i) {
        ARType[] values;
        for (ARType aRType : values()) {
            if (aRType.getTypeValue() == i) {
                return aRType;
            }
        }
        return null;
    }
}
