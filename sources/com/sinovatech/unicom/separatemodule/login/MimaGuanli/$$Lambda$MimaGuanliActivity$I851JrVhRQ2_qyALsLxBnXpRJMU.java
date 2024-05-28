package com.sinovatech.unicom.separatemodule.login.MimaGuanli;

import android.view.View;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.URLSet;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.login.MimaGuanli.-$$Lambda$MimaGuanliActivity$I851JrVhRQ2_qyALsLxBnXpRJMU  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final /* synthetic */ class $$Lambda$MimaGuanliActivity$I851JrVhRQ2_qyALsLxBnXpRJMU implements View.OnClickListener {
    private final /* synthetic */ MimaGuanliActivity f$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        IntentManager.generateIntentAndGo(this.f$0, URLSet.getFuwumimaUrl(), "");
    }
}
