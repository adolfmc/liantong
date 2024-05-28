package com.baidu.platform.comapi.pano;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.pano.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PanoResult {

    /* renamed from: a */
    String f8030a;

    /* renamed from: b */
    PanoStateError f8031b;

    /* renamed from: c */
    int f8032c;

    public PanoResult() {
    }

    public PanoResult(PanoStateError panoStateError) {
        this.f8031b = panoStateError;
    }

    /* renamed from: a */
    public PanoStateError m17711a() {
        return this.f8031b;
    }

    /* renamed from: b */
    public String m17708b() {
        return this.f8030a;
    }

    /* renamed from: a */
    public void m17709a(String str) {
        this.f8030a = str;
    }

    /* renamed from: a */
    public void m17710a(int i) {
        this.f8032c = i;
    }

    /* renamed from: c */
    public int m17707c() {
        return this.f8032c;
    }
}
