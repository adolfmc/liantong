package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.view.View;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.adpter.-$$Lambda$AttentionItemAdapter$b0It9wPJkbcRRDoGJ5R8fb3R1UI  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final /* synthetic */ class $$Lambda$AttentionItemAdapter$b0It9wPJkbcRRDoGJ5R8fb3R1UI implements View.OnClickListener {
    private final /* synthetic */ AttentionItemAdapter f$0;
    private final /* synthetic */ SmallVideoEntity.Data f$1;

    public /* synthetic */ $$Lambda$AttentionItemAdapter$b0It9wPJkbcRRDoGJ5R8fb3R1UI(AttentionItemAdapter attentionItemAdapter, SmallVideoEntity.Data data) {
        this.f$0 = attentionItemAdapter;
        this.f$1 = data;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        AttentionItemAdapter.lambda$convert$17(this.f$0, this.f$1, view);
    }
}
