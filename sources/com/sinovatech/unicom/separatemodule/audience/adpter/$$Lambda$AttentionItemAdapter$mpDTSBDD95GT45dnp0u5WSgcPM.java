package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.view.View;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;

/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.adpter.-$$Lambda$AttentionItemAdapter$mpDTSBDD95GT45dnp0u5WS-gcPM  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final /* synthetic */ class $$Lambda$AttentionItemAdapter$mpDTSBDD95GT45dnp0u5WSgcPM implements View.OnClickListener {
    private final /* synthetic */ AttentionItemAdapter f$0;
    private final /* synthetic */ int f$1;
    private final /* synthetic */ SmallVideoEntity.Data f$2;

    public /* synthetic */ $$Lambda$AttentionItemAdapter$mpDTSBDD95GT45dnp0u5WSgcPM(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data) {
        this.f$0 = attentionItemAdapter;
        this.f$1 = i;
        this.f$2 = data;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        AttentionItemAdapter.lambda$convert$14(this.f$0, this.f$1, this.f$2, view);
    }
}
