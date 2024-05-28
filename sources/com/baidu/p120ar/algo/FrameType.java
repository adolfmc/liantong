package com.baidu.p120ar.algo;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.algo.FrameType */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum FrameType {
    TYPE_YUV(0),
    TYPE_RGB(1),
    TYPE_BGR(2),
    TYPE_RGBA(3),
    TYPE_GRAY(4);
    
    private int value;

    FrameType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
