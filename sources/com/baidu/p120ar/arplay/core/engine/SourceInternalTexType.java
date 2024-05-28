package com.baidu.p120ar.arplay.core.engine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.SourceInternalTexType */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum SourceInternalTexType {
    INTERNAL_OES_TEX(0),
    INTERNAL_2D_TEX(1);
    
    private final int value;

    SourceInternalTexType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
