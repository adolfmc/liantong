package com.baidu.mapapi.utils.poi;

import com.baidu.mapapi.http.HttpClient;
import com.baidu.platform.comapi.pano.PanoStateError;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BaiduMapPoiSearch.java */
/* renamed from: com.baidu.mapapi.utils.poi.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
/* synthetic */ class C2860b {

    /* renamed from: a */
    static final /* synthetic */ int[] f7032a;

    /* renamed from: b */
    static final /* synthetic */ int[] f7033b = new int[HttpClient.HttpStateError.values().length];

    static {
        try {
            f7033b[HttpClient.HttpStateError.NETWORK_ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f7033b[HttpClient.HttpStateError.INNER_ERROR.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f7032a = new int[PanoStateError.values().length];
        try {
            f7032a[PanoStateError.PANO_UID_ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f7032a[PanoStateError.PANO_NOT_FOUND.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f7032a[PanoStateError.PANO_NO_TOKEN.ordinal()] = 3;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            f7032a[PanoStateError.PANO_NO_ERROR.ordinal()] = 4;
        } catch (NoSuchFieldError unused6) {
        }
    }
}
