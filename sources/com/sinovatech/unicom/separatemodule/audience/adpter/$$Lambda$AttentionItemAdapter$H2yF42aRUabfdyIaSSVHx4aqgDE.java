package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;

/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.adpter.-$$Lambda$AttentionItemAdapter$H2yF42aRUabfdyIaSSVHx4aqgDE  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final /* synthetic */ class $$Lambda$AttentionItemAdapter$H2yF42aRUabfdyIaSSVHx4aqgDE implements View.OnClickListener {
    private final /* synthetic */ AttentionItemAdapter f$0;
    private final /* synthetic */ LinearLayout f$1;
    private final /* synthetic */ LinearLayout f$2;
    private final /* synthetic */ TextView f$3;
    private final /* synthetic */ SmallVideoEntity.Data f$4;

    public /* synthetic */ $$Lambda$AttentionItemAdapter$H2yF42aRUabfdyIaSSVHx4aqgDE(AttentionItemAdapter attentionItemAdapter, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, SmallVideoEntity.Data data) {
        this.f$0 = attentionItemAdapter;
        this.f$1 = linearLayout;
        this.f$2 = linearLayout2;
        this.f$3 = textView;
        this.f$4 = data;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        AttentionItemAdapter.lambda$convert$11(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, view);
    }
}
