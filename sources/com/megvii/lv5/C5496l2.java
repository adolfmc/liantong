package com.megvii.lv5;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.l2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5496l2 {

    /* renamed from: b */
    public Context f12862b;

    /* renamed from: c */
    public View f12863c;

    /* renamed from: a */
    public AnimationDrawable f12861a = null;

    /* renamed from: d */
    public Drawable f12864d = null;

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.l2$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5497a implements Runnable {
        public RunnableC5497a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AnimationDrawable animationDrawable = C5496l2.this.f12861a;
            if (animationDrawable != null) {
                animationDrawable.stop();
                C5496l2.this.f12863c.setVisibility(8);
            }
        }
    }

    public C5496l2(Context context, View view) {
        this.f12862b = null;
        this.f12862b = context;
        this.f12863c = view;
    }

    /* renamed from: a */
    public void m13441a() {
        ((Activity) this.f12862b).runOnUiThread(new RunnableC5497a());
    }
}
