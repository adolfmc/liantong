package com.google.zxing.pdf417.encoder;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class Dimensions {
    private final int maxCols;
    private final int maxRows;
    private final int minCols;
    private final int minRows;

    public Dimensions(int i, int i2, int i3, int i4) {
        this.minCols = i;
        this.maxCols = i2;
        this.minRows = i3;
        this.maxRows = i4;
    }

    public int getMinCols() {
        return this.minCols;
    }

    public int getMaxCols() {
        return this.maxCols;
    }

    public int getMinRows() {
        return this.minRows;
    }

    public int getMaxRows() {
        return this.maxRows;
    }
}
