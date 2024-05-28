package com.megvii.lv5.sdk.view;

import android.content.Context;
import android.support.p083v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.megvii.lv5.C5490k3;
import com.megvii.lv5.sdk.C5559R;
import com.megvii.lv5.sdk.view.HorizontalStepsViewIndicator;
import java.util.List;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HorizontalStepView extends LinearLayout implements HorizontalStepsViewIndicator.InterfaceC5609a {

    /* renamed from: a */
    public HorizontalStepsViewIndicator f13607a;

    /* renamed from: b */
    public List<C5490k3> f13608b;

    /* renamed from: c */
    public int f13609c;

    /* renamed from: d */
    public int f13610d;

    /* renamed from: e */
    public int f13611e;

    public HorizontalStepView(Context context) {
        this(context, null);
    }

    public HorizontalStepView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalStepView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f13609c = ContextCompat.getColor(getContext(), 17170444);
        this.f13610d = ContextCompat.getColor(getContext(), 17170458);
        this.f13611e = 14;
        m13028a();
    }

    /* renamed from: a */
    public final void m13028a() {
        HorizontalStepsViewIndicator horizontalStepsViewIndicator = (HorizontalStepsViewIndicator) LayoutInflater.from(getContext()).inflate(C5559R.C5563layout.action_flash_horizontal_stepview, this).findViewById(C5559R.C5562id.steps_indicator);
        this.f13607a = horizontalStepsViewIndicator;
        horizontalStepsViewIndicator.setOnDrawListener(this);
    }
}
