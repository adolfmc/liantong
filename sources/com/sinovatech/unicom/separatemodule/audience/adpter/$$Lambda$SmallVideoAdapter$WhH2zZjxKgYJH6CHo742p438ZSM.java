package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.view.View;
import com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;

/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.adpter.-$$Lambda$SmallVideoAdapter$WhH2zZjxKgYJH6CHo742p438ZSM  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final /* synthetic */ class $$Lambda$SmallVideoAdapter$WhH2zZjxKgYJH6CHo742p438ZSM implements View.OnClickListener {
    private final /* synthetic */ SmallVideoAdapter f$0;
    private final /* synthetic */ SmallVideoAdapter.AudienceHolder f$1;
    private final /* synthetic */ SmallVideoEntity.Data f$2;

    public /* synthetic */ $$Lambda$SmallVideoAdapter$WhH2zZjxKgYJH6CHo742p438ZSM(SmallVideoAdapter smallVideoAdapter, SmallVideoAdapter.AudienceHolder audienceHolder, SmallVideoEntity.Data data) {
        this.f$0 = smallVideoAdapter;
        this.f$1 = audienceHolder;
        this.f$2 = data;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SmallVideoAdapter.lambda$onBindViewHolder$16(this.f$0, this.f$1, this.f$2, view);
    }
}
