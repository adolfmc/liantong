package com.baidu.cloud.framework;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum PortDirection {
    UNKNOWN(0),
    OUT(1),
    IN(2);
    
    private final int direction;

    PortDirection(int i) {
        this.direction = i;
    }

    public int intValue() {
        return this.direction;
    }
}
