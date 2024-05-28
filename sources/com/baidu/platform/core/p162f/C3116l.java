package com.baidu.platform.core.p162f;

import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: RoutePlaneParser.java */
/* renamed from: com.baidu.platform.core.f.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
/* synthetic */ class C3116l {

    /* renamed from: a */
    static final /* synthetic */ int[] f8150a = new int[SearchType.values().length];

    static {
        try {
            f8150a[SearchType.TRANSIT_ROUTE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f8150a[SearchType.DRIVE_ROUTE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f8150a[SearchType.WALK_ROUTE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
