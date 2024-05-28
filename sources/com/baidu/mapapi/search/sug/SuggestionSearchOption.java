package com.baidu.mapapi.search.sug;

import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SuggestionSearchOption {
    public String mCity = null;
    public String mKeyword = null;
    public LatLng mLocation = null;

    /* renamed from: a */
    private boolean f6919a = true;
    public Boolean mCityLimit = false;

    public SuggestionSearchOption city(String str) {
        this.mCity = str;
        return this;
    }

    public SuggestionSearchOption keyword(String str) {
        this.mKeyword = str;
        return this;
    }

    public SuggestionSearchOption location(LatLng latLng) {
        this.mLocation = latLng;
        return this;
    }

    public SuggestionSearchOption citylimit(Boolean bool) {
        this.mCityLimit = bool;
        return this;
    }

    public SuggestionSearchOption extendAdcode(boolean z) {
        this.f6919a = z;
        return this;
    }

    public boolean isExtendAdcode() {
        return this.f6919a;
    }
}
