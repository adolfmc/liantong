package com.baidu.mapsdkplatform.comapi.map;

import com.baidu.mapapi.map.MapLayer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
/* synthetic */ class C2927e {

    /* renamed from: a */
    static final /* synthetic */ int[] f7295a = new int[MapLayer.values().length];

    static {
        try {
            f7295a[MapLayer.MAP_LAYER_LOCATION.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f7295a[MapLayer.MAP_LAYER_OVERLAY.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f7295a[MapLayer.MAP_LAYER_INDOOR_POI.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f7295a[MapLayer.MAP_LAYER_POI_MARKER.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
