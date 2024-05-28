package com.networkbench.agent.impl.p252e;

import android.view.MotionEvent;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class View$OnTouchListenerC6350c implements View.OnTouchListener {

    /* renamed from: a */
    private boolean f15971a = false;

    /* renamed from: b */
    private long f15972b;

    /* renamed from: c */
    private float f15973c;

    /* renamed from: d */
    private float f15974d;

    /* renamed from: e */
    private AbstractC6364m f15975e;

    /* renamed from: f */
    private int f15976f;

    /* renamed from: g */
    private int f15977g;

    public View$OnTouchListenerC6350c(AbstractC6364m abstractC6364m) {
        this.f15975e = abstractC6364m;
        this.f15976f = abstractC6364m.getPosBeginX();
        this.f15977g = abstractC6364m.getPosBeginY();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent != null && this.f15975e.mo10267l()) {
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            int action = motionEvent.getAction();
            if (action == 0) {
                this.f15971a = true;
                this.f15973c = rawX - this.f15976f;
                this.f15974d = rawY - this.f15977g;
                this.f15972b = System.currentTimeMillis();
            } else if (action == 1) {
                this.f15971a = false;
                this.f15975e.mo10287c();
            } else if (action == 2 && this.f15971a) {
                this.f15976f = (int) (rawX - this.f15973c);
                this.f15977g = (int) (rawY - this.f15974d);
                this.f15975e.mo10265a(motionEvent, this.f15976f, this.f15977g);
            }
            return false;
        }
        return false;
    }
}
