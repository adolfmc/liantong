package com.sinovatech.unicom.separatemodule.login.esim;

import android.view.View;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.URLSet;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMXeiyiActivity$4hfs2ijpdDz0jXst_l-gLrVTGCc  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final /* synthetic */ class $$Lambda$ESIMXeiyiActivity$4hfs2ijpdDz0jXst_lgLrVTGCc implements View.OnClickListener {
    private final /* synthetic */ ESIMXeiyiActivity f$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        IntentManager.gotoWebViewActivity(this.f$0.activityContext, URLSet.getLoginFaceUrl(), "");
    }
}
