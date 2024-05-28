package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.picker.Picker;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;
import java.util.Collections;

@NBSInstrumented
@SuppressLint({"ViewConstructor"})
/* renamed from: com.bytedance.applog.w0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class View$OnClickListenerC3717w0 extends C3727x0 implements View.OnClickListener, View.OnTouchListener {

    /* renamed from: e */
    public C3672q0 f8874e;

    /* renamed from: f */
    public final C3701u0 f8875f;

    /* renamed from: g */
    public final TextView f8876g;

    /* renamed from: h */
    public final ImageView f8877h;

    /* renamed from: i */
    public final ImageView f8878i;

    /* renamed from: j */
    public float f8879j;

    /* renamed from: k */
    public float f8880k;

    /* renamed from: l */
    public boolean f8881l;

    /* renamed from: m */
    public float f8882m;

    /* renamed from: n */
    public float f8883n;

    /* renamed from: o */
    public final int[] f8884o;

    /* renamed from: p */
    public final int f8885p;

    /* renamed from: q */
    public final WindowManager f8886q;

    /* renamed from: r */
    public Handler f8887r;

    /* renamed from: com.bytedance.applog.w0$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3718a implements Handler.Callback {
        public C3718a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            C3672q0 c3672q0 = (C3672q0) message.obj;
            View$OnClickListenerC3717w0 view$OnClickListenerC3717w0 = View$OnClickListenerC3717w0.this;
            if (c3672q0 == view$OnClickListenerC3717w0.f8874e) {
                C3701u0 c3701u0 = view$OnClickListenerC3717w0.f8875f;
                if (c3701u0.f8828g + 1 < c3701u0.f8827f.size()) {
                    c3701u0.f8827f.get(c3701u0.f8828g).setVisibility(4);
                    ArrayList<View> arrayList = c3701u0.f8827f;
                    int i = c3701u0.f8828g + 1;
                    c3701u0.f8828g = i;
                    arrayList.get(i).setVisibility(0);
                }
                C3672q0 c3672q02 = (C3672q0) c3701u0.f8827f.get(c3701u0.f8828g).getTag();
                View$OnClickListenerC3717w0 view$OnClickListenerC3717w02 = View$OnClickListenerC3717w0.this;
                if (c3672q02 != view$OnClickListenerC3717w02.f8874e) {
                    view$OnClickListenerC3717w02.f8874e = c3672q02;
                    Message obtain = Message.obtain();
                    obtain.obj = view$OnClickListenerC3717w02.f8874e;
                    view$OnClickListenerC3717w02.f8887r.sendMessageDelayed(obtain, 1000L);
                }
            }
            return true;
        }
    }

    public View$OnClickListenerC3717w0(Application application, Picker picker, C3701u0 c3701u0) {
        super(application, picker);
        this.f8887r = new Handler(new C3718a());
        int scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.f8885p = scaledTouchSlop * scaledTouchSlop;
        this.f8886q = (WindowManager) getContext().getSystemService("window");
        this.f8875f = c3701u0;
        this.f8884o = new int[2];
        this.f8876g = new TextView(getContext());
        this.f8876g.setBackgroundResource(C3527R.C3529drawable.picker_bg_blue);
        this.f8876g.setTextColor(Color.parseColor("#FFFFFF"));
        this.f8876g.setText("BAV");
        this.f8876g.setGravity(17);
        this.f8876g.setTextSize(1, 16.0f);
        int m17332a = C3554b2.m17332a(getContext(), 52.0f);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(m17332a, m17332a);
        layoutParams.gravity = 16;
        addView(this.f8876g, layoutParams);
        this.f8876g.setOnClickListener(this);
        this.f8876g.setOnTouchListener(this);
        this.f8877h = new ImageView(getContext());
        this.f8877h.setBackgroundResource(C3527R.C3529drawable.picker_bg_white);
        int m17332a2 = C3554b2.m17332a(getContext(), 12.0f);
        this.f8877h.setPadding(m17332a2, m17332a2, m17332a2, m17332a2);
        this.f8877h.setImageResource(C3527R.C3529drawable.picker_login);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(m17332a, m17332a);
        int i = m17332a2 + m17332a;
        layoutParams2.leftMargin = i;
        layoutParams2.gravity = 16;
        addView(this.f8877h, layoutParams2);
        this.f8877h.setOnClickListener(this);
        this.f8878i = new ImageView(getContext());
        this.f8878i.setBackgroundResource(C3527R.C3529drawable.picker_bg_white);
        this.f8878i.setImageResource(C3527R.C3529drawable.picker_heat);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(m17332a, m17332a);
        layoutParams3.gravity = 16;
        layoutParams3.leftMargin = i * 2;
        addView(this.f8878i, layoutParams3);
        this.f8878i.setOnClickListener(this);
    }

    @Override // com.bytedance.applog.C3727x0
    /* renamed from: b */
    public void mo17042b() {
        this.f8877h.setVisibility(8);
        this.f8878i.setVisibility(8);
    }

    /* renamed from: c */
    public final void m17069c() {
        Message obtain = Message.obtain();
        obtain.obj = this.f8874e;
        this.f8887r.sendMessageDelayed(obtain, 1000L);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view == this.f8876g) {
            ImageView imageView = this.f8877h;
            imageView.setVisibility(imageView.getVisibility() == 0 ? 8 : 0);
            ImageView imageView2 = this.f8878i;
            imageView2.setVisibility(imageView2.getVisibility() != 0 ? 0 : 8);
        } else if (view == this.f8877h) {
            this.f8911d.m17143f();
        } else {
            this.f8911d.m17144e();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                float rawX = motionEvent.getRawX();
                this.f8879j = rawX;
                this.f8882m = rawX;
                float rawY = motionEvent.getRawY();
                this.f8880k = rawY;
                this.f8883n = rawY;
                this.f8881l = false;
                break;
            case 1:
                if (this.f8881l) {
                    C3672q0 c3672q0 = this.f8874e;
                    if (c3672q0 != null) {
                        this.f8911d.m17151a(c3672q0);
                    }
                } else {
                    this.f8876g.performClick();
                }
                this.f8911d.m17148a(false);
                break;
            case 2:
                if (this.f8881l) {
                    float rawX2 = motionEvent.getRawX() - this.f8882m;
                    float rawY2 = motionEvent.getRawY() - this.f8883n;
                    if ((rawY2 * rawY2) + (rawX2 * rawX2) > this.f8885p) {
                        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getLayoutParams();
                        layoutParams.x += (int) rawX2;
                        layoutParams.y -= (int) rawY2;
                        this.f8886q.updateViewLayout(this, layoutParams);
                        this.f8876g.getLocationOnScreen(this.f8884o);
                        C3701u0 c3701u0 = this.f8875f;
                        int width = (getWidth() / 2) + this.f8884o[0];
                        int height = (getHeight() / 2) + this.f8884o[1];
                        int childCount = c3701u0.getChildCount();
                        c3701u0.f8827f.clear();
                        c3701u0.f8828g = 0;
                        for (int i = 0; i < childCount; i++) {
                            View childAt = c3701u0.getChildAt(i);
                            childAt.getGlobalVisibleRect(c3701u0.f8826e);
                            if (c3701u0.f8826e.contains(width, height)) {
                                c3701u0.f8827f.add(childAt);
                            }
                            childAt.setVisibility(4);
                        }
                        if (c3701u0.f8827f.size() > 0) {
                            Collections.sort(c3701u0.f8827f, new C3693t0(c3701u0));
                            c3701u0.f8827f.get(0).setVisibility(0);
                        }
                        this.f8874e = c3701u0.f8827f.size() > 0 ? (C3672q0) c3701u0.f8827f.get(0).getTag() : null;
                        if (this.f8874e != null) {
                            m17069c();
                        }
                        this.f8882m = motionEvent.getRawX();
                        this.f8883n = motionEvent.getRawY();
                        break;
                    }
                } else {
                    float rawX3 = motionEvent.getRawX() - this.f8879j;
                    float rawY3 = motionEvent.getRawY() - this.f8880k;
                    if ((rawY3 * rawY3) + (rawX3 * rawX3) > this.f8885p) {
                        this.f8881l = true;
                        this.f8911d.m17148a(true);
                        this.f8877h.setVisibility(8);
                        break;
                    }
                }
                break;
            case 3:
                this.f8911d.m17148a(false);
                break;
        }
        return true;
    }
}
