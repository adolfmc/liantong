package com.sinovatech.unicom.separatemodule.audience;

import com.sinovatech.unicom.separatemodule.audience.entity.AudienceDataEntity;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$O_szAEnYqOmMcHA_G2rZcyeZzOE  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final /* synthetic */ class $$Lambda$AudienceActivity$O_szAEnYqOmMcHA_G2rZcyeZzOE implements Consumer {
    private final /* synthetic */ AudienceActivity f$0;

    public /* synthetic */ $$Lambda$AudienceActivity$O_szAEnYqOmMcHA_G2rZcyeZzOE(AudienceActivity audienceActivity) {
        this.f$0 = audienceActivity;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(Object obj) {
        this.f$0.handleSingleList((AudienceDataEntity) obj);
    }
}
