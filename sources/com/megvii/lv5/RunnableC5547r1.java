package com.megvii.lv5;

import android.opengl.GLES20;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.r1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5547r1 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ C5553s1 f13206a;

    public RunnableC5547r1(C5553s1 c5553s1) {
        this.f13206a = c5553s1;
    }

    @Override // java.lang.Runnable
    public void run() {
        C5650x1 c5650x1 = this.f13206a.f13253d;
        if (c5650x1 != null) {
            c5650x1.f13892h = false;
            GLES20.glDeleteProgram(c5650x1.f13888d);
        }
        int i = this.f13206a.f13264o;
        if (i != -1) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
        }
    }
}
