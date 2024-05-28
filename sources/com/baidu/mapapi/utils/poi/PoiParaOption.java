package com.baidu.mapapi.utils.poi;

import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiParaOption {

    /* renamed from: a */
    String f7027a;

    /* renamed from: b */
    String f7028b;

    /* renamed from: c */
    LatLng f7029c;

    /* renamed from: d */
    int f7030d;

    public PoiParaOption uid(String str) {
        this.f7027a = str;
        return this;
    }

    public String getUid() {
        return this.f7027a;
    }

    public PoiParaOption key(String str) {
        this.f7028b = str;
        return this;
    }

    public String getKey() {
        return this.f7028b;
    }

    public PoiParaOption center(LatLng latLng) {
        this.f7029c = latLng;
        return this;
    }

    public LatLng getCenter() {
        return this.f7029c;
    }

    public PoiParaOption radius(int i) {
        this.f7030d = i;
        return this;
    }

    public int getRadius() {
        return this.f7030d;
    }
}
