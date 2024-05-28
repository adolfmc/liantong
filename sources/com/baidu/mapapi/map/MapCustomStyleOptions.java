package com.baidu.mapapi.map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MapCustomStyleOptions {

    /* renamed from: a */
    private String f6133a;

    /* renamed from: b */
    private String f6134b;

    public MapCustomStyleOptions customStyleId(String str) {
        this.f6134b = str;
        return this;
    }

    public String getCustomMapStyleId() {
        return this.f6134b;
    }

    public String getLocalCustomStyleFilePath() {
        return this.f6133a;
    }

    public MapCustomStyleOptions localCustomStylePath(String str) {
        this.f6133a = str;
        return this;
    }
}
