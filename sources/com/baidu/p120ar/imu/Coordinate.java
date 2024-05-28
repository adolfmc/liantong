package com.baidu.p120ar.imu;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.imu.Coordinate */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum Coordinate {
    WORLD(0),
    RELATIVE(1);
    
    private int mValue;

    Coordinate(int i) {
        this.mValue = i;
    }

    public int getTypeValue() {
        return this.mValue;
    }

    public static Coordinate valueOf(int i) {
        Coordinate[] values;
        for (Coordinate coordinate : values()) {
            if (coordinate.getTypeValue() == i) {
                return coordinate;
            }
        }
        return null;
    }
}
