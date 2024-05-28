package com.sinovatech.unicom.separatemodule.audience;

import com.sinovatech.unicom.separatemodule.audience.entity.ActivityTimeEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$-DXC4SKTVOr4aU5EfGyOO2Yl7b4  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final /* synthetic */ class $$Lambda$AudienceActivity$DXC4SKTVOr4aU5EfGyOO2Yl7b4 implements Consumer {
    private final /* synthetic */ AudienceActivity f$0;
    private final /* synthetic */ ZhuboDataEntity.AnchorInfoBean f$1;

    public /* synthetic */ $$Lambda$AudienceActivity$DXC4SKTVOr4aU5EfGyOO2Yl7b4(AudienceActivity audienceActivity, ZhuboDataEntity.AnchorInfoBean anchorInfoBean) {
        this.f$0 = audienceActivity;
        this.f$1 = anchorInfoBean;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(Object obj) {
        this.f$0.messageView.setHuodongView(r1.getUserId(), this.f$1.getUserName(), (ActivityTimeEntity) obj);
    }
}
