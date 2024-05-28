package comp.android.app.face.p381sz.camera.util;

import android.hardware.Camera;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: comp.android.app.face.sz.camera.util.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11786c {
    /* renamed from: a */
    public static synchronized boolean m2141a(int i) {
        boolean z;
        synchronized (C11786c.class) {
            Camera camera = null;
            z = false;
            try {
                try {
                    camera = Camera.open(i);
                    camera.setParameters(camera.getParameters());
                    if (camera != null) {
                        camera.release();
                        z = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (camera != null) {
                    camera.release();
                }
            }
        }
        return z;
    }
}
