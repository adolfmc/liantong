package com.baidu.mapapi.map;

import android.os.Bundle;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HeatMapData {

    /* renamed from: a */
    private double[] f6110a;

    /* renamed from: b */
    private double[] f6111b;

    /* renamed from: c */
    private double[] f6112c;

    /* renamed from: d */
    private float f6113d;

    public HeatMapData(Collection<WeightedLatLng> collection, float f) {
        int size = collection.size();
        this.f6110a = new double[size];
        this.f6111b = new double[size];
        this.f6112c = new double[size];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (WeightedLatLng weightedLatLng : collection) {
            this.f6110a[i] = weightedLatLng.getPoint().x;
            this.f6111b[i2] = weightedLatLng.getPoint().y;
            this.f6112c[i3] = weightedLatLng.getIntensity();
            i3++;
            i2++;
            i++;
        }
        this.f6113d = f;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putDoubleArray("x_array", this.f6110a);
        bundle.putDoubleArray("y_array", this.f6111b);
        bundle.putDoubleArray("z_array", this.f6112c);
        bundle.putFloat("max_intentity", this.f6113d);
        return bundle;
    }
}
