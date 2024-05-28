package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.view.View;
import com.sinovatech.unicom.separatemodule.audience.entity.AlivcLiveMessageInfo;

/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.adpter.-$$Lambda$AILPChatListRecyclerViewAdapter$LhKwjM2iH2YhtFprDni0MBk5Sdw */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final /* synthetic */ class View$OnLongClickListenerC8319x7d93fd7c implements View.OnLongClickListener {
    private final /* synthetic */ AILPChatListRecyclerViewAdapter f$0;
    private final /* synthetic */ AlivcLiveMessageInfo f$1;

    public /* synthetic */ View$OnLongClickListenerC8319x7d93fd7c(AILPChatListRecyclerViewAdapter aILPChatListRecyclerViewAdapter, AlivcLiveMessageInfo alivcLiveMessageInfo) {
        this.f$0 = aILPChatListRecyclerViewAdapter;
        this.f$1 = alivcLiveMessageInfo;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        return AILPChatListRecyclerViewAdapter.lambda$onBindViewHolder$1(this.f$0, this.f$1, view);
    }
}
