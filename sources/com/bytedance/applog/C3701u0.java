package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Rect;
import android.os.Looper;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bytedance.applog.C3685s0;
import com.bytedance.applog.picker.Picker;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"ViewConstructor"})
/* renamed from: com.bytedance.applog.u0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3701u0 extends C3727x0 {

    /* renamed from: e */
    public final Rect f8826e;

    /* renamed from: f */
    public final ArrayList<View> f8827f;

    /* renamed from: g */
    public int f8828g;

    /* renamed from: com.bytedance.applog.u0$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3702a implements C3685s0.InterfaceC3686a {
        public C3702a() {
        }

        @Override // com.bytedance.applog.C3685s0.InterfaceC3686a
        /* renamed from: a */
        public void mo17113a(C3672q0 c3672q0, List<C3565d1> list, List<C3672q0> list2) {
            for (C3672q0 c3672q02 : list2) {
                ImageView imageView = new ImageView(C3701u0.this.getContext());
                imageView.setImageResource(C3527R.C3529drawable.picker_border);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(c3672q02.f8773y, c3672q02.f8774z);
                int[] iArr = c3672q02.f8772x;
                layoutParams.leftMargin = iArr[0];
                layoutParams.topMargin = iArr[1];
                imageView.setAlpha(0.4f);
                imageView.setTag(c3672q02);
                C3701u0.this.addView(imageView, layoutParams);
            }
        }
    }

    public C3701u0(Application application, Picker picker) {
        super(application, picker);
        setBackgroundColor(0);
        this.f8826e = new Rect();
        this.f8827f = new ArrayList<>();
    }

    @Override // com.bytedance.applog.C3727x0
    /* renamed from: b */
    public void mo17042b() {
        removeAllViews();
        new C3685s0().m17121a((C3685s0.InterfaceC3686a) new C3702a(), Looper.myLooper(), false);
    }
}
