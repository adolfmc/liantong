package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.sinovatech.unicom.separatemodule.audience.adpter.AILPChatListRecyclerViewAdapter;
import com.sinovatech.unicom.separatemodule.audience.adpter.BaseChatListRecyclerViewAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.AlivcLiveMessageInfo;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AlivcChatListView extends ChatListView<AlivcLiveMessageInfo> {
    private static final String TAG = "YKLChatListYoukuAdapter";
    private AILPChatListRecyclerViewAdapter adapter;

    public AlivcChatListView(@NonNull Context context) {
        this(context, null);
    }

    public AlivcChatListView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public AlivcChatListView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.view.ChatListView
    protected View inflateLayout(Context context) {
        return LayoutInflater.from(context).inflate(2131492968, this);
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.view.ChatListView
    protected RecyclerView getChatRecyclerView() {
        return (RecyclerView) findViewById(2131298213);
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.view.ChatListView
    protected View getNewMessageTips() {
        return findViewById(2131298212);
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.view.ChatListView
    protected TextView getNewMessageTextView() {
        return (TextView) findViewById(2131298214);
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.view.ChatListView
    protected BaseChatListRecyclerViewAdapter createAdapter(Context context) {
        this.adapter = new AILPChatListRecyclerViewAdapter(context);
        return this.adapter;
    }

    public void setListener(AILPChatListRecyclerViewAdapter.OnItemLongClickedListener onItemLongClickedListener) {
        this.adapter.setListener(onItemLongClickedListener);
    }
}
