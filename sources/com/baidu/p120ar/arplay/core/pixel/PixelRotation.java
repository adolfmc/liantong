package com.baidu.p120ar.arplay.core.pixel;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.pixel.PixelRotation */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum PixelRotation {
    NoRotation(0),
    RotateLeft(1),
    RotateRight(2),
    FlipVertical(3),
    FlipHorizontal(4),
    RotateRightFlipVertical(5),
    RotateRightFlipHorizontal(6),
    Rotate180(7);
    
    private final int value;

    PixelRotation(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
