package com.sinovatech.unicom.separatemodule.audience;

import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$MUR3aGnsMWd57fHbbJO8EogZ9xg  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final /* synthetic */ class $$Lambda$AudienceActivity$MUR3aGnsMWd57fHbbJO8EogZ9xg implements Consumer {
    private final /* synthetic */ AudienceActivity f$0;
    private final /* synthetic */ ZhuboDataEntity f$1;

    public /* synthetic */ $$Lambda$AudienceActivity$MUR3aGnsMWd57fHbbJO8EogZ9xg(AudienceActivity audienceActivity, ZhuboDataEntity zhuboDataEntity) {
        this.f$0 = audienceActivity;
        this.f$1 = zhuboDataEntity;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(Object obj) {
        AudienceActivity.lambda$startPlay$39(this.f$0, this.f$1, (Boolean) obj);
    }
}
