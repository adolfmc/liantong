package com.baidu.platform.core.p165i;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.search.base.LanguageType;
import com.baidu.mapapi.search.weather.WeatherDataType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: WeatherRequest.java */
/* renamed from: com.baidu.platform.core.i.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
/* synthetic */ class C3118d {

    /* renamed from: a */
    static final /* synthetic */ int[] f8156a;

    /* renamed from: b */
    static final /* synthetic */ int[] f8157b;

    /* renamed from: c */
    static final /* synthetic */ int[] f8158c = new int[LanguageType.values().length];

    static {
        try {
            f8158c[LanguageType.LanguageTypeEnglish.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f8158c[LanguageType.LanguageTypeChinese.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f8157b = new int[CoordType.values().length];
        try {
            f8157b[CoordType.BD09LL.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f8157b[CoordType.GCJ02.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        f8156a = new int[WeatherDataType.values().length];
        try {
            f8156a[WeatherDataType.WEATHER_DATA_TYPE_REAL_TIME.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            f8156a[WeatherDataType.WEATHER_DATA_TYPE_FORECASTS_FOR_DAY.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            f8156a[WeatherDataType.WEATHER_DATA_TYPE_FORECASTS_FOR_HOUR.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            f8156a[WeatherDataType.WEATHER_DATA_TYPE_LIFE_INDEX.ordinal()] = 4;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            f8156a[WeatherDataType.WEATHER_DATA_TYPE_ALERT.ordinal()] = 5;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            f8156a[WeatherDataType.WEATHER_DATA_TYPE_ALL.ordinal()] = 6;
        } catch (NoSuchFieldError unused10) {
        }
    }
}
