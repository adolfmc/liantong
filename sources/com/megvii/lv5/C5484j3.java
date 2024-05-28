package com.megvii.lv5;

import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.j3 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5484j3 {

    /* renamed from: a */
    public float f12824a;

    /* renamed from: b */
    public float f12825b;

    /* renamed from: c */
    public float f12826c;

    /* renamed from: d */
    public int f12827d;

    /* renamed from: e */
    public int f12828e = 0;

    /* renamed from: f */
    public Random f12829f;

    public C5484j3(float f, float f2, float f3) {
        Random random = new Random();
        this.f12829f = random;
        this.f12824a = f;
        this.f12825b = f2;
        this.f12826c = f3;
        this.f12827d = Math.abs(random.nextInt() % 55) + 200;
    }
}
