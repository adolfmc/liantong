package com.baidu.platform.core.p160d;

import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PoiSearchParser.java */
/* renamed from: com.baidu.platform.core.d.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
/* synthetic */ class C3115h {

    /* renamed from: a */
    static final /* synthetic */ int[] f8144a = new int[SearchType.values().length];

    static {
        try {
            f8144a[SearchType.POI_NEAR_BY_SEARCH.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f8144a[SearchType.POI_IN_CITY_SEARCH.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f8144a[SearchType.POI_IN_BOUND_SEARCH.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
