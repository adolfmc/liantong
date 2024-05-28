package com.baidu.platform.comapi.util.p156a;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import com.baidu.p166vi.VIContext;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.util.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DpiInfo {

    /* renamed from: a */
    private int f8048a = -1;

    /* renamed from: b */
    private int f8049b = -1;

    /* renamed from: c */
    private float f8050c = -1.0f;

    /* renamed from: d */
    private int f8051d = -1;

    /* renamed from: e */
    private int f8052e = -1;

    /* renamed from: f */
    private int f8053f = -1;

    /* renamed from: g */
    private double f8054g = -1.0d;

    /* renamed from: a */
    public void m17698a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f8048a = displayMetrics.widthPixels;
        this.f8049b = displayMetrics.heightPixels;
        this.f8050c = displayMetrics.density;
        this.f8051d = (int) displayMetrics.xdpi;
        this.f8052e = (int) displayMetrics.ydpi;
        if (Build.VERSION.SDK_INT > 3) {
            this.f8053f = displayMetrics.densityDpi;
            if (this.f8053f < 240) {
                this.f8053f = displayMetrics.densityDpi;
            }
        } else {
            this.f8053f = C0567f.f1819h;
        }
        if (this.f8053f == 0) {
            this.f8053f = C0567f.f1819h;
        }
        this.f8054g = this.f8053f / 240.0d;
    }

    /* renamed from: a */
    public int m17699a() {
        if (this.f8048a == -1) {
            m17698a(VIContext.getContext());
        }
        return this.f8048a;
    }

    /* renamed from: b */
    public int m17697b() {
        if (this.f8049b == -1) {
            m17698a(VIContext.getContext());
        }
        return this.f8049b;
    }

    /* renamed from: c */
    public float m17696c() {
        if (this.f8050c == -1.0f) {
            m17698a(VIContext.getContext());
        }
        return this.f8050c;
    }

    /* renamed from: d */
    public int m17695d() {
        if (this.f8053f == -1) {
            m17698a(VIContext.getContext());
        }
        return this.f8053f;
    }
}
