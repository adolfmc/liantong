package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.audience.adpter.BaseChatListRecyclerViewAdapter;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class ChatListView<T> extends FrameLayout implements View.OnClickListener {
    protected static final int CHAT_MSG = 1;
    protected static final int CHAT_MSG_LOCAL = 2;
    protected static final int CHAT_UPDATE = 3;
    private static final int MESSAGE_DELAY_TIME = 20;
    private long lastTime;
    private BaseChatListRecyclerViewAdapter mAdapter;
    private RecyclerView mChatRecyclerView;
    private Handler mHandler;
    private TextView mNewMessageTextView;
    private View mNewMessageTips;
    private long updateLastTime;
    private boolean useLocal;

    public void addList() {
    }

    protected abstract BaseChatListRecyclerViewAdapter createAdapter(Context context);

    protected abstract RecyclerView getChatRecyclerView();

    protected abstract TextView getNewMessageTextView();

    protected abstract View getNewMessageTips();

    public View getView() {
        return this;
    }

    protected abstract View inflateLayout(Context context);

    protected boolean isLocalRepetition(T t) {
        return false;
    }

    public ChatListView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.useLocal = false;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.sinovatech.unicom.separatemodule.audience.view.ChatListView.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Object obj = message.obj;
                switch (message.what) {
                    case 1:
                        ChatListView.this.updateChatData(obj, false);
                        return;
                    case 2:
                        ChatListView.this.updateChatData(obj, true);
                        return;
                    case 3:
                        ChatListView.this.mAdapter.notifyUpdateList();
                        return;
                    default:
                        return;
                }
            }
        };
        this.lastTime = 0L;
        this.updateLastTime = 0L;
        inflateLayout(context);
        this.mChatRecyclerView = getChatRecyclerView();
        this.mNewMessageTips = getNewMessageTips();
        this.mNewMessageTextView = getNewMessageTextView();
        this.mAdapter = createAdapter(context);
        this.mChatRecyclerView.setAdapter(this.mAdapter);
        this.mChatRecyclerView.setLayoutManager(getLayoutManager(context));
        this.mChatRecyclerView.addOnScrollListener(new OnScrollListener());
        this.mNewMessageTips.setOnClickListener(this);
        hideNewMsgLayout();
    }

    public BaseChatListRecyclerViewAdapter getAdapter() {
        return this.mAdapter;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view == this.mNewMessageTips) {
            addCacheData();
            hideNewMsgLayout();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    public void refreshData() {
        BaseChatListRecyclerViewAdapter baseChatListRecyclerViewAdapter = this.mAdapter;
        if (baseChatListRecyclerViewAdapter != null) {
            baseChatListRecyclerViewAdapter.refreshData();
        }
    }

    protected RecyclerView.LayoutManager getLayoutManager(Context context) {
        SmoothScrollLayoutManager smoothScrollLayoutManager = new SmoothScrollLayoutManager(context);
        smoothScrollLayoutManager.setOrientation(1);
        return smoothScrollLayoutManager;
    }

    public void addMessage(T t) {
        if (t == null) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = this.lastTime;
        if (uptimeMillis - j < 20) {
            uptimeMillis = j + 20;
        }
        this.lastTime = uptimeMillis;
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = t;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void removeMessage(int i) {
        this.mAdapter.removeMessage(i);
    }

    public int getChatCount() {
        return this.mAdapter.getItemCount();
    }

    public void addMessageLocal(T t) {
        if (t == null) {
            return;
        }
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 2;
        obtainMessage.obj = t;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void clearAll() {
        BaseChatListRecyclerViewAdapter baseChatListRecyclerViewAdapter = this.mAdapter;
        if (baseChatListRecyclerViewAdapter != null) {
            baseChatListRecyclerViewAdapter.clearData();
        }
    }

    protected void updateChatData(T t, boolean z) {
        BaseChatListRecyclerViewAdapter baseChatListRecyclerViewAdapter;
        if (t == null) {
            return;
        }
        if ((!z && isUseLocal() && isLocalRepetition(t)) || (baseChatListRecyclerViewAdapter = this.mAdapter) == null) {
            return;
        }
        baseChatListRecyclerViewAdapter.notifyAddItem(t);
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = this.updateLastTime;
        if (uptimeMillis - j < 20) {
            uptimeMillis = j + 20;
        }
        if (this.mAdapter.getListLength() < 10 && this.mHandler.hasMessages(3)) {
            this.mHandler.removeMessages(3);
        }
        this.updateLastTime = uptimeMillis;
        this.mHandler.sendEmptyMessageAtTime(3, uptimeMillis + 20);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideNewMsgLayout() {
        View view = this.mNewMessageTips;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.mNewMessageTips.setVisibility(8);
    }

    protected void showNewMsgLayout() {
        View view = this.mNewMessageTips;
        if (view != null && view.getVisibility() == 8) {
            this.mNewMessageTips.setVisibility(0);
        }
        BaseChatListRecyclerViewAdapter baseChatListRecyclerViewAdapter = this.mAdapter;
        if (baseChatListRecyclerViewAdapter == null || this.mNewMessageTextView == null) {
            return;
        }
        setNewMessageText(this.mNewMessageTextView, baseChatListRecyclerViewAdapter.getNewCount());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCacheData() {
        RecyclerView recyclerView = this.mChatRecyclerView;
        if (recyclerView != null) {
            recyclerView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.ChatListView.2
                @Override // java.lang.Runnable
                public void run() {
                    if (ChatListView.this.mAdapter != null) {
                        ChatListView.this.mAdapter.addCacheData();
                    }
                }
            });
        }
    }

    public void setLimitSize(String str) {
        try {
            this.mAdapter.setLimit(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    protected void setNewMessageText(TextView textView, int i) {
        if (textView != null) {
            textView.setText(i + "条新消息");
        }
    }

    public void turnOnLocal() {
        this.useLocal = true;
    }

    public void turnOffLocal() {
        this.useLocal = false;
    }

    public boolean isUseLocal() {
        return this.useLocal;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class OnScrollListener extends RecyclerView.OnScrollListener {
        int nNewState;

        private OnScrollListener() {
            this.nNewState = -2;
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            this.nNewState = i;
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            if (ChatListView.this.mAdapter == null || !ChatListView.this.mAdapter.isSlideToBottom()) {
                return;
            }
            ChatListView.this.hideNewMsgLayout();
            ChatListView.this.addCacheData();
        }
    }
}
