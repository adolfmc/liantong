package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.view.View;
import com.sinovatech.unicom.separatemodule.audience.entity.AlivcLiveMessageInfo;

/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.adpter.-$$Lambda$AILPChatListRecyclerViewAdapter$GgTqFi3gzaRhzo5KPwbfWiUghcQ */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final /* synthetic */ class View$OnClickListenerC8318x8ff69580 implements View.OnClickListener {
    private final /* synthetic */ AILPChatListRecyclerViewAdapter f$0;
    private final /* synthetic */ AlivcLiveMessageInfo f$1;
    private final /* synthetic */ int f$2;

    public /* synthetic */ View$OnClickListenerC8318x8ff69580(AILPChatListRecyclerViewAdapter aILPChatListRecyclerViewAdapter, AlivcLiveMessageInfo alivcLiveMessageInfo, int i) {
        this.f$0 = aILPChatListRecyclerViewAdapter;
        this.f$1 = alivcLiveMessageInfo;
        this.f$2 = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        AILPChatListRecyclerViewAdapter.lambda$onBindViewHolder$0(this.f$0, this.f$1, this.f$2, view);
    }
}
