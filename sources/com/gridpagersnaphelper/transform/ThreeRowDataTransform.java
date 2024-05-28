package com.gridpagersnaphelper.transform;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ThreeRowDataTransform extends AbsRowDataTransform {
    private static final int ROW = 2;

    public ThreeRowDataTransform(int i) {
        super(2, i);
    }

    @Override // com.gridpagersnaphelper.transform.AbsRowDataTransform
    protected int transformIndex(int i, int i2, int i3) {
        int i4;
        int i5 = i2 * i3;
        int i6 = i / i5;
        int i7 = i % i5;
        int i8 = i7 % i2;
        if (i8 == 0) {
            i4 = i7 / i2;
        } else if (i8 == 1) {
            i4 = (i7 / i2) + i3;
        } else {
            i4 = (i7 / i2) + (i3 * 2);
        }
        return i4 + (i6 * i5);
    }
}
