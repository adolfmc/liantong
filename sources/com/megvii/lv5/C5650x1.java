package com.megvii.lv5;

import android.content.Context;
import com.megvii.lv5.sdk.C5559R;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.LinkedList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.x1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5650x1 {

    /* renamed from: a */
    public final LinkedList<Runnable> f13885a = new LinkedList<>();

    /* renamed from: b */
    public final String f13886b;

    /* renamed from: c */
    public final String f13887c;

    /* renamed from: d */
    public int f13888d;

    /* renamed from: e */
    public int f13889e;

    /* renamed from: f */
    public int f13890f;

    /* renamed from: g */
    public int f13891g;

    /* renamed from: h */
    public boolean f13892h;

    /* renamed from: i */
    public FloatBuffer f13893i;

    /* renamed from: j */
    public FloatBuffer f13894j;

    /* renamed from: k */
    public float[] f13895k;

    public C5650x1(Context context) {
        this.f13886b = C5379a2.m13618a(context, C5559R.C5564raw.image_vertex);
        this.f13887c = C5379a2.m13618a(context, C5559R.C5564raw.image_fragment);
        float[] m13615a = C5379a2.m13615a(C5379a2.f12365f, C5530p.f13115J.m13214a());
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(m13615a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f13893i = asFloatBuffer;
        asFloatBuffer.put(m13615a).position(0);
        float[] fArr = C5379a2.f12360a;
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f13894j = asFloatBuffer2;
        asFloatBuffer2.put(fArr).position(0);
    }
}
