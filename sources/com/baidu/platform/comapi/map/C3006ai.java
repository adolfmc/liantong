package com.baidu.platform.comapi.map;

import android.content.Context;
import android.view.SurfaceView;
import com.baidu.platform.comapi.util.C3095g;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.ai */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3006ai {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.ai$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum EnumC3007a {
        OPENGL_ES,
        VULKAN,
        AUTO
    }

    /* renamed from: a */
    public static InterfaceSurfaceHolder$Callback2C3005ah m17976a(SurfaceView surfaceView, EnumC3007a enumC3007a, boolean z, Context context) {
        int i;
        int i2;
        C3042f c3042f = new C3042f(surfaceView);
        c3042f.m17849c(2);
        if (z) {
            i = 4;
            i2 = 1;
        } else {
            i2 = 0;
            i = 0;
        }
        try {
            if (C3095g.m17682a(8, 8, 8, 8, 24, 8)) {
                c3042f.m17863a(8, 8, 8, 8, 24, 8, i2, i);
            } else {
                c3042f.m17851b(true);
            }
        } catch (IllegalArgumentException unused) {
            c3042f.m17851b(true);
        }
        c3042f.m17855a(true);
        return c3042f;
    }
}
