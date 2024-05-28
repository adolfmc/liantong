package com.baidu.p120ar.arplay.core.engine.rotate;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.rotate.Orientation */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum Orientation {
    UNKNOWN(-1),
    PORTRAIT(0),
    PORTRAIT_REVERSE(180),
    LANDSCAPE(90),
    LANDSCAPE_REVERSE(-90);
    
    private int mDegree;

    Orientation(int i) {
        this.mDegree = i;
    }

    public static Orientation valueOf(int i) {
        if (i == PORTRAIT.getDegree()) {
            return PORTRAIT;
        }
        if (i == PORTRAIT_REVERSE.getDegree()) {
            return PORTRAIT_REVERSE;
        }
        if (i == LANDSCAPE.getDegree()) {
            return LANDSCAPE;
        }
        if (i == LANDSCAPE_REVERSE.getDegree()) {
            return LANDSCAPE_REVERSE;
        }
        return UNKNOWN;
    }

    public int getDegree() {
        return this.mDegree;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.mDegree);
    }
}
