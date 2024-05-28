package com.baidu.mapapi.map;

import android.util.Log;
import com.baidu.mapapi.model.LatLng;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PolygonHoleOptions extends HoleOptions {

    /* renamed from: a */
    private List<LatLng> f6310a;

    public PolygonHoleOptions() {
        this.mHoleType = "polygon";
    }

    public PolygonHoleOptions addPoints(List<LatLng> list) {
        if (list == null || list.size() < 3) {
            Log.e("baidumapsdk", "PolygonHole's points can not be null or points's size can not be less than three");
            return this;
        }
        this.f6310a = list;
        return this;
    }

    public List<LatLng> getHolePoints() {
        return this.f6310a;
    }
}
