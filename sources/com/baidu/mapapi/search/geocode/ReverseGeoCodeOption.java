package com.baidu.mapapi.search.geocode;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.base.LanguageType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ReverseGeoCodeOption {

    /* renamed from: a */
    private int f6742a = 10;

    /* renamed from: b */
    private int f6743b = 0;

    /* renamed from: c */
    private LatLng f6744c = null;

    /* renamed from: d */
    private int f6745d = 1;

    /* renamed from: e */
    private int f6746e = 1000;

    /* renamed from: f */
    private String f6747f = null;

    /* renamed from: g */
    private boolean f6748g = false;

    /* renamed from: h */
    private LanguageType f6749h = LanguageType.LanguageTypeChinese;

    public ReverseGeoCodeOption location(LatLng latLng) {
        this.f6744c = latLng;
        return this;
    }

    public ReverseGeoCodeOption newVersion(int i) {
        this.f6745d = i;
        return this;
    }

    public ReverseGeoCodeOption pageSize(int i) {
        if (i <= 0) {
            this.f6742a = 10;
        } else if (i > 100) {
            this.f6742a = 100;
        } else {
            this.f6742a = i;
        }
        return this;
    }

    public ReverseGeoCodeOption pageNum(int i) {
        if (i < 0) {
            i = 0;
        }
        this.f6743b = i;
        return this;
    }

    public ReverseGeoCodeOption radius(int i) {
        if (i < 0) {
            this.f6746e = 0;
        } else if (i > 1000) {
            this.f6746e = 1000;
        } else {
            this.f6746e = i;
        }
        return this;
    }

    public ReverseGeoCodeOption poiType(String str) {
        this.f6747f = str;
        return this;
    }

    public ReverseGeoCodeOption extensionsRoad(boolean z) {
        this.f6748g = z;
        return this;
    }

    public ReverseGeoCodeOption language(LanguageType languageType) {
        this.f6749h = languageType;
        return this;
    }

    public int getPageSize() {
        return this.f6742a;
    }

    public int getPageNum() {
        return this.f6743b;
    }

    public int getLatestAdmin() {
        return this.f6745d;
    }

    public LatLng getLocation() {
        return this.f6744c;
    }

    public int getRadius() {
        return this.f6746e;
    }

    public String getPoiType() {
        return this.f6747f;
    }

    public boolean getExtensionsRoad() {
        return this.f6748g;
    }

    public LanguageType getLanguage() {
        return this.f6749h;
    }
}
