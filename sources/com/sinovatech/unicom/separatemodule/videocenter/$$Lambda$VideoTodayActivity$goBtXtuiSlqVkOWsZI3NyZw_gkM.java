package com.sinovatech.unicom.separatemodule.videocenter;

import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoTodayActivity$goBtXtuiSlqVkOWsZI3NyZw_gkM  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final /* synthetic */ class $$Lambda$VideoTodayActivity$goBtXtuiSlqVkOWsZI3NyZw_gkM implements BaseQuickAdapter.RequestLoadMoreListener {
    private final /* synthetic */ VideoTodayActivity f$0;

    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.RequestLoadMoreListener
    public final void onLoadMoreRequested() {
        r0.getRecommend(this.f$0.pageNum);
    }
}
