package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.view.View;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;

/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.adpter.-$$Lambda$AttentionItemAdapter$s4Y8PGY7JrPeADmA6dcQHtJEJEQ  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final /* synthetic */ class $$Lambda$AttentionItemAdapter$s4Y8PGY7JrPeADmA6dcQHtJEJEQ implements View.OnClickListener {
    private final /* synthetic */ AttentionItemAdapter f$0;
    private final /* synthetic */ int f$1;
    private final /* synthetic */ SmallVideoEntity.Data f$2;

    public /* synthetic */ $$Lambda$AttentionItemAdapter$s4Y8PGY7JrPeADmA6dcQHtJEJEQ(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data) {
        this.f$0 = attentionItemAdapter;
        this.f$1 = i;
        this.f$2 = data;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        AttentionItemAdapter.lambda$convert$7(this.f$0, this.f$1, this.f$2, view);
    }
}
