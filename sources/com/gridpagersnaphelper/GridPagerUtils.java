package com.gridpagersnaphelper;

import com.gridpagersnaphelper.transform.AbsRowDataTransform;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class GridPagerUtils {
    public static <T> List<T> transformAndFillEmptyData(List<T> list, int i, int i2) {
        int i3;
        int i4;
        if (i == 0 || i2 == 0) {
            throw new IllegalArgumentException("row or column must be not null");
        }
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        int i5 = i * i2;
        if (size < i5) {
            i3 = i5;
        } else {
            i3 = size % i5 == 0 ? size : ((size / i5) + 1) * i5;
        }
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = i6 / i5;
            int i8 = i6 % i5;
            if (i8 % i == 0) {
                i4 = i8 / 2;
            } else {
                i4 = (i8 / 2) + i2;
            }
            int i9 = i4 + (i7 * i5);
            if (i9 >= 0 && i9 < size) {
                arrayList.add(list.get(i9));
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public static List transformAndFillEmptyData(AbsRowDataTransform absRowDataTransform, List list) {
        if (absRowDataTransform == null) {
            throw new IllegalArgumentException("orderTransform must be not null");
        }
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("data list must be not null or size must > 0");
        }
        return absRowDataTransform.transform(list);
    }
}
