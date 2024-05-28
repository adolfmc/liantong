package com.megvii.lv5;

import android.content.Context;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.z2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5667z2 {

    /* renamed from: b */
    public static C5667z2 f13967b;

    /* renamed from: c */
    public static String f13968c;

    /* renamed from: a */
    public Context f13969a;

    public C5667z2(Context context) {
        this.f13969a = context.getApplicationContext();
    }

    /* renamed from: a */
    public static C5667z2 m12879a(Context context) {
        if (f13967b == null) {
            String str = f13968c;
            if (str == null) {
                str = context.getPackageName();
            }
            f13968c = str;
            f13967b = new C5667z2(context);
        }
        return f13967b;
    }

    /* renamed from: a */
    public int m12878a(String str) {
        return this.f13969a.getResources().getIdentifier(str, "color", f13968c);
    }

    /* renamed from: b */
    public int m12877b(String str) {
        return this.f13969a.getResources().getIdentifier(str, "drawable", f13968c);
    }

    /* renamed from: c */
    public int m12876c(String str) {
        return this.f13969a.getResources().getIdentifier(str, "raw", f13968c);
    }

    /* renamed from: d */
    public int m12875d(String str) {
        return this.f13969a.getResources().getIdentifier(str, "string", f13968c);
    }
}
