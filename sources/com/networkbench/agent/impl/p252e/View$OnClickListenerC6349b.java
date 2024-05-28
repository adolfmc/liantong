package com.networkbench.agent.impl.p252e;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class View$OnClickListenerC6349b implements View.OnClickListener {

    /* renamed from: a */
    private AbstractC6364m f15970a;

    public View$OnClickListenerC6349b(AbstractC6364m abstractC6364m) {
        this.f15970a = abstractC6364m;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Tracker.onClick(view);
        this.f15970a.mo10266a();
    }
}
