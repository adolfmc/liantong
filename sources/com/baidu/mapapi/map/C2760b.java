package com.baidu.mapapi.map;

import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapsdkplatform.comapi.map.EnumC2947w;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
/* synthetic */ class C2760b {

    /* renamed from: a */
    static final /* synthetic */ int[] f6539a;

    /* renamed from: b */
    static final /* synthetic */ int[] f6540b = new int[EnumC2947w.values().length];

    static {
        try {
            f6540b[EnumC2947w.TextureView.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f6540b[EnumC2947w.GLSurfaceView.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f6539a = new int[MyLocationConfiguration.LocationMode.values().length];
        try {
            f6539a[MyLocationConfiguration.LocationMode.COMPASS.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f6539a[MyLocationConfiguration.LocationMode.FOLLOWING.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f6539a[MyLocationConfiguration.LocationMode.NORMAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused5) {
        }
    }
}
