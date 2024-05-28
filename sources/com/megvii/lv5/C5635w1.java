package com.megvii.lv5;

import android.content.Context;
import android.opengl.GLES20;
import com.megvii.lv5.sdk.C5559R;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.LinkedList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.w1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5635w1 {

    /* renamed from: a */
    public final String f13794a;

    /* renamed from: b */
    public final String f13795b;

    /* renamed from: c */
    public int f13796c;

    /* renamed from: d */
    public FloatBuffer f13797d;

    /* renamed from: e */
    public FloatBuffer f13798e;

    /* renamed from: f */
    public int[] f13799f;

    public C5635w1(Context context) {
        this.f13799f = null;
        new LinkedList();
        this.f13794a = C5379a2.m13618a(context, C5559R.C5564raw.triangle_vertex);
        this.f13795b = C5379a2.m13618a(context, C5559R.C5564raw.triangle_fragment);
        float[] fArr = C5379a2.f12365f;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f13797d = asFloatBuffer;
        asFloatBuffer.put(fArr).position(0);
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(C5379a2.f12360a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f13798e = asFloatBuffer2;
        asFloatBuffer2.put(C5379a2.f12361b).position(0);
        int[] iArr = new int[1];
        this.f13799f = iArr;
        GLES20.glGenFramebuffers(1, iArr, 0);
    }
}
