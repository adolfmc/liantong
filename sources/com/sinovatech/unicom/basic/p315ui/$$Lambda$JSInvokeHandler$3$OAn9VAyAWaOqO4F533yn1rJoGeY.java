package com.sinovatech.unicom.basic.p315ui;

import android.content.Intent;
import com.sinovatech.unicom.basic.p315ui.JSInvokeHandler;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;

/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.basic.ui.-$$Lambda$JSInvokeHandler$3$OAn9VAyAWaOqO4F533yn1rJoGeY  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final /* synthetic */ class $$Lambda$JSInvokeHandler$3$OAn9VAyAWaOqO4F533yn1rJoGeY implements AvoidOnResult.Callback {
    private final /* synthetic */ JSInvokeHandler.RunnableC72063 f$0;
    private final /* synthetic */ String f$1;
    private final /* synthetic */ String f$2;

    public /* synthetic */ $$Lambda$JSInvokeHandler$3$OAn9VAyAWaOqO4F533yn1rJoGeY(JSInvokeHandler.RunnableC72063 runnableC72063, String str, String str2) {
        this.f$0 = runnableC72063;
        this.f$1 = str;
        this.f$2 = str2;
    }

    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
    public final void onActivityResult(int i, Intent intent) {
        JSInvokeHandler.RunnableC72063.lambda$run$0(this.f$0, this.f$1, this.f$2, i, intent);
    }
}
