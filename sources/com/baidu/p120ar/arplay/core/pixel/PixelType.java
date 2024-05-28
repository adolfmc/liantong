package com.baidu.p120ar.arplay.core.pixel;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.pixel.PixelType */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum PixelType {
    RGBA(0),
    BGRA(1),
    BGR(2),
    NV12(3),
    NV21(4),
    I420(5),
    YV12(6),
    GRAY(7),
    RGB(8),
    RED(9),
    GREEN(10),
    BLUE(11),
    ALPHA(12);
    
    private final int value;

    PixelType(int i) {
        this.value = i;
    }

    public static PixelType valueOf(int i) {
        if (i == RGBA.getValue()) {
            return RGBA;
        }
        if (i == BGRA.getValue()) {
            return BGRA;
        }
        if (i == BGR.getValue()) {
            return BGR;
        }
        if (i == NV12.getValue()) {
            return NV12;
        }
        if (i == NV21.getValue()) {
            return NV21;
        }
        if (i == I420.getValue()) {
            return I420;
        }
        if (i == YV12.getValue()) {
            return YV12;
        }
        if (i == GRAY.getValue()) {
            return GRAY;
        }
        if (i == RGB.getValue()) {
            return RGB;
        }
        if (i == RED.getValue()) {
            return RED;
        }
        if (i == GREEN.getValue()) {
            return GREEN;
        }
        if (i == BLUE.getValue()) {
            return BLUE;
        }
        if (i == ALPHA.getValue()) {
            return ALPHA;
        }
        return RGBA;
    }

    public int getValue() {
        return this.value;
    }
}
