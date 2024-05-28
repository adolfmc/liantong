package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.view.View;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.adpter.-$$Lambda$SmallVideoAdapter$TXfxP28_zcGaqKsDU8_6XMRl8kw  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final /* synthetic */ class $$Lambda$SmallVideoAdapter$TXfxP28_zcGaqKsDU8_6XMRl8kw implements View.OnClickListener {
    private final /* synthetic */ SmallVideoAdapter f$0;
    private final /* synthetic */ SmallVideoEntity.Data f$1;

    public /* synthetic */ $$Lambda$SmallVideoAdapter$TXfxP28_zcGaqKsDU8_6XMRl8kw(SmallVideoAdapter smallVideoAdapter, SmallVideoEntity.Data data) {
        this.f$0 = smallVideoAdapter;
        this.f$1 = data;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SmallVideoAdapter.lambda$onBindViewHolder$24(this.f$0, this.f$1, view);
    }
}
