package com.baidu.mapapi.favorite;

import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FavoritePoiInfo {

    /* renamed from: a */
    String f5866a;

    /* renamed from: b */
    String f5867b;

    /* renamed from: c */
    LatLng f5868c;

    /* renamed from: d */
    String f5869d;

    /* renamed from: e */
    String f5870e;

    /* renamed from: f */
    String f5871f;

    /* renamed from: g */
    long f5872g;

    public FavoritePoiInfo addr(String str) {
        this.f5869d = str;
        return this;
    }

    public FavoritePoiInfo cityName(String str) {
        this.f5870e = str;
        return this;
    }

    public String getAddr() {
        return this.f5869d;
    }

    public String getCityName() {
        return this.f5870e;
    }

    public String getID() {
        return this.f5866a;
    }

    public String getPoiName() {
        return this.f5867b;
    }

    public LatLng getPt() {
        return this.f5868c;
    }

    public long getTimeStamp() {
        return this.f5872g;
    }

    public String getUid() {
        return this.f5871f;
    }

    public FavoritePoiInfo poiName(String str) {
        this.f5867b = str;
        return this;
    }

    /* renamed from: pt */
    public FavoritePoiInfo m19035pt(LatLng latLng) {
        this.f5868c = latLng;
        return this;
    }

    public FavoritePoiInfo uid(String str) {
        this.f5871f = str;
        return this;
    }
}
