package com.megvii.lv5;

import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.k */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5486k {

    /* renamed from: a */
    public static int f12831a = 640;

    /* renamed from: b */
    public static int f12832b = 480;

    /* renamed from: c */
    public static int f12833c = 1280;

    /* renamed from: d */
    public static int f12834d = 720;

    /* renamed from: e */
    public static int f12835e = 90;

    /* renamed from: a */
    public static AbstractC5500m m13446a() {
        return Build.VERSION.SDK_INT >= 21 ? new C5426f() : new C5521o();
    }
}
