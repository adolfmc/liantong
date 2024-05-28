package com.baidu.mapapi.map;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SwipeDismissTouchListener implements View.OnTouchListener {

    /* renamed from: a */
    private int f6389a;

    /* renamed from: b */
    private int f6390b;

    /* renamed from: c */
    private int f6391c;

    /* renamed from: d */
    private long f6392d;

    /* renamed from: e */
    private View f6393e;

    /* renamed from: f */
    private DismissCallbacks f6394f;

    /* renamed from: g */
    private int f6395g = 1;

    /* renamed from: h */
    private float f6396h;

    /* renamed from: i */
    private float f6397i;

    /* renamed from: j */
    private boolean f6398j;

    /* renamed from: k */
    private int f6399k;

    /* renamed from: l */
    private Object f6400l;

    /* renamed from: m */
    private VelocityTracker f6401m;

    /* renamed from: n */
    private float f6402n;

    /* renamed from: o */
    private boolean f6403o;

    /* renamed from: p */
    private boolean f6404p;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface DismissCallbacks {
        boolean canDismiss(Object obj);

        void onDismiss(View view, Object obj);

        void onNotify();
    }

    public SwipeDismissTouchListener(View view, Object obj, DismissCallbacks dismissCallbacks) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        this.f6389a = viewConfiguration.getScaledTouchSlop();
        this.f6390b = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f6391c = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f6392d = view.getContext().getResources().getInteger(17694720);
        this.f6393e = view;
        this.f6393e.getContext();
        this.f6400l = obj;
        this.f6394f = dismissCallbacks;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(11)
    /* renamed from: a */
    public void m18874a() {
        ViewGroup.LayoutParams layoutParams = this.f6393e.getLayoutParams();
        int height = this.f6393e.getHeight();
        ValueAnimator duration = ValueAnimator.ofInt(height, 1).setDuration(this.f6392d);
        duration.addListener(new C2784x(this, layoutParams, height));
        duration.addUpdateListener(new C2785y(this, layoutParams));
        duration.start();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
        r9.f6393e.animate().translationX(0.0f).setDuration(r9.f6392d).setListener(null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0188, code lost:
        if (r9.f6398j != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
        if (r9.f6401m == null) goto L82;
     */
    @Override // android.view.View.OnTouchListener
    @android.annotation.TargetApi(12)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r10, android.view.MotionEvent r11) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.SwipeDismissTouchListener.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
