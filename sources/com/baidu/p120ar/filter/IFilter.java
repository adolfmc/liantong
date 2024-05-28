package com.baidu.p120ar.filter;

import android.graphics.Point;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.filter.IFilter */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IFilter {
    void clearAllFilter();

    void resetAllFilter();

    void setFilterStateListener(FilterStateListener filterStateListener);

    void updateFaceFilterWithKneadJson(String str);

    void updateFilter(FilterParam filterParam, float f);

    void updateFilter(FilterParam filterParam, int i);

    void updateFilter(FilterParam filterParam, String str);

    void updateFilter(FilterParam filterParam, List<Point> list);

    void updateFilter(FilterParam filterParam, float[] fArr);

    void updateFilter(String str, String str2, Object obj);

    String updateFilterCase(String str);
}
