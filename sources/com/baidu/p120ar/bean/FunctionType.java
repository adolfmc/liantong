package com.baidu.p120ar.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bean.FunctionType */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum FunctionType {
    NONE("none"),
    VIDEO("video"),
    IMU("imu");
    
    private final String mValue;

    FunctionType(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    public static FunctionType getValueOf(String str) {
        FunctionType[] values;
        if (str == null) {
            return NONE;
        }
        for (FunctionType functionType : values()) {
            if (functionType.getValue().equalsIgnoreCase(str)) {
                return functionType;
            }
        }
        return NONE;
    }
}
