package com.baidu.mapapi.map;

import android.util.Log;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CircleHoleOptions extends HoleOptions {

    /* renamed from: a */
    private LatLng f6003a;

    /* renamed from: b */
    private int f6004b;

    public CircleHoleOptions() {
        this.mHoleType = "circle";
    }

    public CircleHoleOptions center(LatLng latLng) {
        if (latLng == null) {
            Log.e("baidumapsdk", "CircleHole center can not be null");
            return this;
        }
        this.f6003a = latLng;
        return this;
    }

    public LatLng getHoleCenter() {
        return this.f6003a;
    }

    public int getHoleRadius() {
        return this.f6004b;
    }

    public CircleHoleOptions radius(int i) {
        if (i <= 0) {
            Log.e("baidumapsdk", "CircleHole's radius can not be less than zero");
            return this;
        }
        this.f6004b = i;
        return this;
    }
}
