package com.baidu.p120ar.arplay.core.engine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.SyncSourceClockType */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum SyncSourceClockType {
    SYS_CLOCK(0),
    COUNTER_CLOCK(1);
    
    private final int value;

    SyncSourceClockType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
