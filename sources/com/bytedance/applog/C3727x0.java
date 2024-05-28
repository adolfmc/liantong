package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.app.Application;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.bytedance.applog.picker.Picker;

@SuppressLint({"ViewConstructor"})
/* renamed from: com.bytedance.applog.x0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3727x0 extends C3748z1 {

    /* renamed from: a */
    public final Application f8908a;

    /* renamed from: b */
    public int f8909b;

    /* renamed from: c */
    public long f8910c;

    /* renamed from: d */
    public final Picker f8911d;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.x0$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class RunnableC3728a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ TextView f8912a;

        public RunnableC3728a(TextView textView) {
            this.f8912a = textView;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3727x0.this.removeView(this.f8912a);
        }
    }

    public C3727x0(Application application, Picker picker) {
        super(application);
        this.f8908a = application;
        this.f8911d = picker;
        this.f8910c = 0L;
        this.f8909b = 0;
    }

    /* renamed from: a */
    public void m17043a(String str) {
        TextView textView = new TextView(getContext());
        textView.setText(str);
        textView.setTextSize(1, 16.0f);
        textView.setGravity(17);
        textView.setBackgroundColor(-14737374);
        textView.setTextColor(-1);
        textView.setPadding(24, 24, 24, 24);
        long currentTimeMillis = System.currentTimeMillis();
        int m17332a = C3554b2.m17332a(this.f8908a, 30.0f);
        if (currentTimeMillis - this.f8910c < 2000) {
            m17332a = this.f8909b + C3554b2.m17332a(this.f8908a, 40.0f);
        }
        this.f8909b = m17332a;
        this.f8910c = currentTimeMillis;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 81;
        layoutParams.bottomMargin = m17332a;
        addView(textView, layoutParams);
        postDelayed(new RunnableC3728a(textView), 2000L);
    }

    /* renamed from: a */
    public boolean mo17044a() {
        return false;
    }

    /* renamed from: b */
    public void mo17042b() {
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1 && (mo17044a() || this.f8911d.m17145d())) || super.dispatchKeyEvent(keyEvent);
    }
}
