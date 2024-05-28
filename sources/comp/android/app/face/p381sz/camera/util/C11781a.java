package comp.android.app.face.p381sz.camera.util;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: comp.android.app.face.sz.camera.util.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11781a {
    /* renamed from: a */
    public static int m2151a(float f, float f2) {
        return Math.abs(f) > Math.abs(f2) ? f > 4.0f ? SubsamplingScaleImageView.ORIENTATION_270 : f < -4.0f ? 90 : 0 : (f2 <= 7.0f && f2 < -7.0f) ? 180 : 0;
    }
}
