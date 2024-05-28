package com.baidu.mapapi.search.poi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiDetailSearchOption {

    /* renamed from: a */
    private String f6760a = "";

    /* renamed from: b */
    private boolean f6761b = true;

    /* renamed from: c */
    private String f6762c = "";

    /* renamed from: d */
    private boolean f6763d = false;

    public PoiDetailSearchOption poiUid(String str) {
        this.f6763d = false;
        this.f6760a = str;
        return this;
    }

    public PoiDetailSearchOption poiUids(String str) {
        this.f6763d = true;
        this.f6762c = str;
        return this;
    }

    public String getUid() {
        return this.f6760a;
    }

    public String getUids() {
        return this.f6762c;
    }

    public boolean isSearchByUids() {
        return this.f6763d;
    }

    public PoiDetailSearchOption extendAdcode(boolean z) {
        this.f6761b = z;
        return this;
    }

    public boolean isExtendAdcode() {
        return this.f6761b;
    }
}
