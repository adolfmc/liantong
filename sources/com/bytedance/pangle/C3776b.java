package com.bytedance.pangle;

import android.os.Build;
import com.bytedance.pangle.flipped.C3831a;
import com.bytedance.pangle.flipped.C3832b;
import com.bytedance.pangle.flipped.FlippedV2Impl;
import com.bytedance.pangle.flipped.InterfaceC3833c;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3776b {
    /* renamed from: a */
    public static void m16974a() {
        InterfaceC3833c c3831a;
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 30 || (Build.VERSION.SDK_INT == 29 && Build.VERSION.PREVIEW_SDK_INT > 0)) {
            c3831a = new FlippedV2Impl();
        } else {
            if (Build.VERSION.SDK_INT >= 28 || (Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT > 0)) {
                z = true;
            }
            if (z) {
                c3831a = new C3832b();
            } else {
                c3831a = new C3831a();
            }
        }
        c3831a.invokeHiddenApiRestrictions();
    }
}
