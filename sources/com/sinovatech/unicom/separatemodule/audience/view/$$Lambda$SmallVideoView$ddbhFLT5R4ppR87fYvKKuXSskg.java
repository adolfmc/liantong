package com.sinovatech.unicom.separatemodule.audience.view;

import android.view.View;
import com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$SmallVideoView$ddbhFLT5R4ppR87fYv-KKuXSskg  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final /* synthetic */ class $$Lambda$SmallVideoView$ddbhFLT5R4ppR87fYvKKuXSskg implements View.OnClickListener {
    private final /* synthetic */ SmallVideoAdapter.ItemClickedListener f$0;
    private final /* synthetic */ SmallVideoEntity.Data f$1;

    public /* synthetic */ $$Lambda$SmallVideoView$ddbhFLT5R4ppR87fYvKKuXSskg(SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data) {
        this.f$0 = itemClickedListener;
        this.f$1 = data;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SmallVideoView.lambda$initData$25(this.f$0, this.f$1, view);
    }
}
