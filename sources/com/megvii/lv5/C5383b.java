package com.megvii.lv5;

import com.megvii.lv5.lib.jni.MegBlur;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5383b {

    /* renamed from: a */
    public C5376a f12380a = new C5376a();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.b$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class C5384a {

        /* renamed from: a */
        public static final C5383b f12381a = new C5383b();
    }

    /* renamed from: a */
    public void m13609a(int i, float f) {
        long j = this.f12380a.f12359a;
        if (j != 0) {
            MegBlur.nativeSetBlur(j, i, f);
        }
    }

    /* renamed from: a */
    public void m13610a(float f, int i, int i2, int i3) {
        long j = this.f12380a.f12359a;
        if (j != 0) {
            MegBlur.nativeSetCloring(j, f, i, i2, i3);
        }
    }
}
