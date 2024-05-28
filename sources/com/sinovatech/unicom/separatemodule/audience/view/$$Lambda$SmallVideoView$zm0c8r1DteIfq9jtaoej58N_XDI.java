package com.sinovatech.unicom.separatemodule.audience.view;

import android.view.View;
import com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$SmallVideoView$zm0c8r1DteIfq9jtaoej58N_XDI  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final /* synthetic */ class $$Lambda$SmallVideoView$zm0c8r1DteIfq9jtaoej58N_XDI implements View.OnClickListener {
    private final /* synthetic */ SmallVideoAdapter.ItemClickedListener f$0;
    private final /* synthetic */ SmallVideoEntity.Data f$1;

    public /* synthetic */ $$Lambda$SmallVideoView$zm0c8r1DteIfq9jtaoej58N_XDI(SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data) {
        this.f$0 = itemClickedListener;
        this.f$1 = data;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SmallVideoView.lambda$initData$24(this.f$0, this.f$1, view);
    }
}
