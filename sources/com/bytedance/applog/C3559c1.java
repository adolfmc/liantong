package com.bytedance.applog;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.c1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3559c1 {

    /* renamed from: a */
    public static boolean f8389a = false;

    /* renamed from: b */
    public static int f8390b = -1;

    /* renamed from: c */
    public static int f8391c = -1;

    /* renamed from: d */
    public static int f8392d = -1;

    /* renamed from: e */
    public static int f8393e = -1;

    /* renamed from: f */
    public static double f8394f = -1.0d;

    /* renamed from: g */
    public static Bitmap f8395g;

    /* renamed from: a */
    public static Bitmap m17320a(View[] viewArr) {
        Bitmap createBitmap = Bitmap.createBitmap(f8390b, f8391c, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        float f = (float) f8394f;
        canvas.scale(f, f);
        int[] iArr = new int[2];
        C3560c2.m17317b();
        for (View view : viewArr) {
            view.getLocationOnScreen(iArr);
            canvas.save();
            canvas.translate(iArr[0], iArr[1]);
            view.draw(canvas);
            canvas.restore();
        }
        return createBitmap;
    }
}
