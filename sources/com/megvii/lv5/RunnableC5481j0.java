package com.megvii.lv5;

import android.widget.RelativeLayout;
import com.megvii.lv5.sdk.C5559R;
import com.megvii.lv5.sdk.detect.guide.GrantActivity;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.j0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5481j0 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ GrantActivity f12814a;

    public RunnableC5481j0(GrantActivity grantActivity) {
        this.f12814a = grantActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f12814a.f13483a.getTop() < this.f12814a.f13492j.getBottom()) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f12814a.f13497o.getLayoutParams();
            layoutParams.addRule(3, C5559R.C5562id.tv_megvii_liveness_guide_title);
            layoutParams.topMargin = C5388b3.m13608a(this.f12814a, 10.0f);
            this.f12814a.f13497o.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f12814a.f13498p.getLayoutParams();
            layoutParams2.width = (int) (layoutParams2.width * 0.9f);
            layoutParams2.height = (int) (layoutParams2.height * 0.9f);
            this.f12814a.f13498p.setLayoutParams(layoutParams2);
            this.f12814a.f13496n.requestLayout();
        }
    }
}
