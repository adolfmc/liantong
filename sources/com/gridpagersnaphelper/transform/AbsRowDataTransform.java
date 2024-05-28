package com.gridpagersnaphelper.transform;

import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbsRowDataTransform {
    private static final int DEFAULT_COLUMN = 1;
    private static final int DEFAULT_ROW = 1;
    private int mColumn;
    private int mRow;

    protected abstract int transformIndex(int i, int i2, int i3);

    public AbsRowDataTransform(int i, int i2) {
        this.mRow = 1;
        this.mColumn = 1;
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("row or column must be not null");
        }
        this.mRow = i;
        this.mColumn = i2;
    }

    public List transform(List list) {
        ArrayList arrayList = new ArrayList();
        int i = this.mRow * this.mColumn;
        int size = list.size();
        if (size >= i) {
            i = size % i == 0 ? size : i * ((size / i) + 1);
        }
        for (int i2 = 0; i2 < i; i2++) {
            int transformIndex = transformIndex(i2, this.mRow, this.mColumn);
            if (transformIndex >= 0 && transformIndex < size) {
                arrayList.add(list.get(transformIndex));
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }
}
