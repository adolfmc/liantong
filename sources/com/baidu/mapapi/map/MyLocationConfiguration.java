package com.baidu.mapapi.map;

import android.graphics.Color;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MyLocationConfiguration {
    public int accuracyCircleFillColor;
    public int accuracyCircleStrokeColor;
    public final BitmapDescriptor customMarker;
    public final boolean enableDirection;
    public final LocationMode locationMode;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum LocationMode {
        NORMAL,
        FOLLOWING,
        COMPASS
    }

    public MyLocationConfiguration(LocationMode locationMode, boolean z, BitmapDescriptor bitmapDescriptor) {
        this.accuracyCircleFillColor = 4521984;
        this.accuracyCircleStrokeColor = 4653056;
        this.locationMode = locationMode == null ? LocationMode.NORMAL : locationMode;
        this.enableDirection = z;
        this.customMarker = bitmapDescriptor;
        this.accuracyCircleFillColor = m18894a(this.accuracyCircleFillColor);
        this.accuracyCircleStrokeColor = m18894a(this.accuracyCircleStrokeColor);
    }

    public MyLocationConfiguration(LocationMode locationMode, boolean z, BitmapDescriptor bitmapDescriptor, int i, int i2) {
        this.accuracyCircleFillColor = 4521984;
        this.accuracyCircleStrokeColor = 4653056;
        this.locationMode = locationMode == null ? LocationMode.NORMAL : locationMode;
        this.enableDirection = z;
        this.customMarker = bitmapDescriptor;
        this.accuracyCircleFillColor = m18894a(i);
        this.accuracyCircleStrokeColor = m18894a(i2);
    }

    /* renamed from: a */
    private int m18894a(int i) {
        int i2 = (65280 & i) >> 8;
        return Color.argb(((-16777216) & i) >> 24, i & 255, i2, (16711680 & i) >> 16);
    }
}
