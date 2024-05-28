package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.RecyclerView.ViewHolder;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.AlivcLiveMessageInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class BaseChatListRecyclerViewAdapter<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {
    protected static int MAX_CHAT_CACHE_LENGTH = 100;
    protected static int MAX_CHAT_LIST_LENGTH = 100;
    protected RecyclerView mRecyclerView;
    private List<T> mLiveCommentItem = new ArrayList();
    private List<T> mCacheList = new ArrayList();
    private List<T> mChatMessage = new ArrayList();

    public void setLimit(int i) {
        synchronized (this) {
            MAX_CHAT_CACHE_LENGTH = i;
            MAX_CHAT_LIST_LENGTH = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<T> getLiveCommentItem() {
        return this.mLiveCommentItem;
    }

    public void notifyAddItem(T t) {
        synchronized (this) {
            UIUtils.logD("notifyAddItem", "---->" + System.currentTimeMillis());
            if (t instanceof AlivcLiveMessageInfo) {
                if (((AlivcLiveMessageInfo) t).getType() != AlivcLiveMessageInfo.AlivcMsgType.ALIVC_MESSAGE_ROOM_DESC.getMsgType()) {
                    this.mChatMessage.add(t);
                } else if (this.mChatMessage.size() == 0 && this.mLiveCommentItem.size() == 0) {
                    this.mChatMessage.add(t);
                }
            } else {
                this.mChatMessage.add(t);
            }
        }
    }

    public void clearMessage() {
        this.mLiveCommentItem.clear();
        notifyDataSetChanged();
    }

    public void removeMessage(int i) {
        if (i < 0 || i > this.mLiveCommentItem.size() - 1) {
            return;
        }
        this.mLiveCommentItem.remove(i);
        notifyRemoveItem(i);
    }

    public int getListLength() {
        return this.mChatMessage.size();
    }

    public void notifyUpdateList() {
        synchronized (this) {
            int size = this.mLiveCommentItem.size();
            this.mLiveCommentItem.addAll(this.mChatMessage);
            if (this.mLiveCommentItem.size() != 0) {
                notifyItemRangeInserted(size, this.mLiveCommentItem.size() - size);
            }
            if (this.mLiveCommentItem.size() > MAX_CHAT_CACHE_LENGTH) {
                int size2 = this.mLiveCommentItem.size() - MAX_CHAT_CACHE_LENGTH;
                for (int i = 0; i < size2; i++) {
                    this.mLiveCommentItem.remove(0);
                    notifyRemoveItem(0);
                }
            }
            scrollToEnd();
            this.mChatMessage.clear();
        }
    }

    public void addCacheData() {
        try {
            synchronized (this) {
                if (this.mRecyclerView != null) {
                    if (this.mCacheList.size() == 0) {
                        return;
                    }
                    int size = this.mCacheList.size();
                    for (int i = 0; i < size; i++) {
                        T t = this.mCacheList.get(i);
                        if (this.mLiveCommentItem.size() > getMaxChatListLength()) {
                            this.mLiveCommentItem.remove(0);
                        }
                        this.mLiveCommentItem.add(t);
                    }
                    this.mCacheList.clear();
                    notifyDataSetChanged();
                    scrollToEnd();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int getMaxChatListLength() {
        return MAX_CHAT_LIST_LENGTH;
    }

    protected int getMaxChatCacheLength() {
        return MAX_CHAT_CACHE_LENGTH;
    }

    public void refreshData() {
        try {
            synchronized (this) {
                notifyDataSetChanged();
                scrollToEnd();
            }
        } catch (Exception unused) {
        }
    }

    public void clearData() {
        synchronized (this) {
            this.mLiveCommentItem.clear();
            notifyDataSetChanged();
        }
    }

    public void scrollToEnd() {
        try {
            synchronized (this) {
                if (this.mRecyclerView != null) {
                    this.mRecyclerView.smoothScrollToPosition(this.mLiveCommentItem.size());
                }
            }
        } catch (Exception unused) {
        }
    }

    public int getNewCount() {
        List<T> list = this.mCacheList;
        if (list != null) {
            return list.size();
        }
        return 1;
    }

    private void notifyRemoveItem(int i) {
        if (isSlideToBottom()) {
            notifyItemRemoved(i);
        }
    }

    public boolean isSlideToBottom() {
        RecyclerView recyclerView = this.mRecyclerView;
        return recyclerView != null && recyclerView.computeVerticalScrollExtent() + this.mRecyclerView.computeVerticalScrollOffset() >= this.mRecyclerView.computeVerticalScrollRange();
    }

    public int getChatMessageSize() {
        List<T> list = this.mChatMessage;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        synchronized (this) {
            if (this.mLiveCommentItem == null) {
                return 0;
            }
            return this.mLiveCommentItem.size();
        }
    }
}
