package com.megvii.lv5;

import android.opengl.GLES20;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.u1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5620u1 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ C5627v1 f13742a;

    public RunnableC5620u1(C5627v1 c5627v1) {
        this.f13742a = c5627v1;
    }

    @Override // java.lang.Runnable
    public void run() {
        C5659y1 c5659y1 = this.f13742a.f13759d;
        if (c5659y1 != null) {
            c5659y1.f13930h = false;
            GLES20.glDeleteProgram(c5659y1.f13926d);
        }
        int i = this.f13742a.f13765j;
        if (i != -1) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
        }
    }
}
