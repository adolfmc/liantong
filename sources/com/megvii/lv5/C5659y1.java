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
/* renamed from: com.megvii.lv5.y1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5659y1 {

    /* renamed from: a */
    public final LinkedList<Runnable> f13923a = new LinkedList<>();

    /* renamed from: b */
    public final String f13924b;

    /* renamed from: c */
    public final String f13925c;

    /* renamed from: d */
    public int f13926d;

    /* renamed from: e */
    public int f13927e;

    /* renamed from: f */
    public int f13928f;

    /* renamed from: g */
    public int f13929g;

    /* renamed from: h */
    public boolean f13930h;

    /* renamed from: i */
    public FloatBuffer f13931i;

    /* renamed from: j */
    public FloatBuffer f13932j;

    /* renamed from: k */
    public float[] f13933k;

    public C5659y1(Context context) {
        this.f13924b = C5379a2.m13618a(context, C5559R.C5564raw.image_vertex);
        this.f13925c = C5379a2.m13618a(context, C5559R.C5564raw.image_fragment);
        float[] fArr = C5379a2.f12365f;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f13931i = asFloatBuffer;
        asFloatBuffer.put(fArr).position(0);
        float[] fArr2 = C5379a2.f12360a;
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f13932j = asFloatBuffer2;
        asFloatBuffer2.put(fArr2).position(0);
    }
}
