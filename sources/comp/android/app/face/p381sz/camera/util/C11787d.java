package comp.android.app.face.p381sz.camera.util;

import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: comp.android.app.face.sz.camera.util.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11787d {

    /* renamed from: a */
    private static String[] f24010a = {"hwH60", "hwPE", "hwH30", "hwHol", "hwG750", "hw7D", "hwChe2"};

    /* renamed from: a */
    public static String m2140a() {
        return Build.DEVICE;
    }

    /* renamed from: b */
    public static boolean m2139b() {
        int length = f24010a.length;
        for (int i = 0; i < length; i++) {
            if (f24010a[i].equals(m2140a())) {
                return true;
            }
        }
        return false;
    }
}
