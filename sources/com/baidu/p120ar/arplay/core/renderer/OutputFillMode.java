package com.baidu.p120ar.arplay.core.renderer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.renderer.OutputFillMode */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum OutputFillMode {
    StretchToFill(0),
    KeepRatioFill(1),
    KeepRatioCrop(2);
    
    private final int value;

    OutputFillMode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
