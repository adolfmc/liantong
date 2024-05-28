package com.baidu.platform.comapi.map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.ap */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3014ap {

    /* renamed from: a */
    private int f7745a;

    /* renamed from: b */
    private int f7746b;

    /* renamed from: c */
    private int f7747c;

    /* renamed from: d */
    private int f7748d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static int m17939c(int i) {
        return ((i & 16711680) >> 16) | ((-16777216) & i) | ((i & 255) << 16) | (65280 & i);
    }

    /* renamed from: a */
    public int m17944a() {
        return this.f7748d;
    }

    /* renamed from: a */
    public C3014ap m17943a(int i) {
        this.f7745a = i;
        return this;
    }

    /* renamed from: b */
    public int m17942b() {
        return this.f7745a;
    }

    /* renamed from: b */
    public C3014ap m17941b(int i) {
        this.f7746b = i;
        return this;
    }

    /* renamed from: c */
    public int m17940c() {
        return this.f7746b;
    }

    /* renamed from: d */
    public int m17938d() {
        return this.f7747c;
    }

    public String toString() {
        return "Style: color:" + Integer.toHexString(this.f7745a) + " width:" + this.f7746b + " fillcolor:" + Integer.toHexString(this.f7747c);
    }
}
