package com.sinovatech.unicom.separatemodule.recentmenu.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.viewholder.CollectionGroupViewHolder;
import com.sinovatech.unicom.separatemodule.recentmenu.viewholder.CollectionImageViewHolder;
import com.sinovatech.unicom.separatemodule.recentmenu.viewholder.CollectionNavigationViewHolder;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CollectionMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "XiaLaMoreAdapter";
    private ChangeCheckListener checkListener;
    private boolean isBianJi = false;
    private OnItemClickListener itemClickListener;
    private Activity mActivity;
    List<RecentMiniEntity> mList;
    private String nowYear;
    private OnSwipeClickListener swipeClickListener;
    private int type;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ChangeCheckListener {
        void onCheckState(RecentMiniEntity recentMiniEntity, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemClickListener {
        void onItemClick(RecentMiniEntity recentMiniEntity);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnSwipeClickListener {
        void onLeftClick(RecentMiniEntity recentMiniEntity, int i);

        void onRightClick(RecentMiniEntity recentMiniEntity, int i);
    }

    public void setBianJi(boolean z) {
        this.isBianJi = z;
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public CollectionMoreAdapter(Activity activity, int i, List<RecentMiniEntity> list) {
        this.mList = new ArrayList();
        this.mActivity = activity;
        this.mList = list;
        this.type = i;
        try {
            Calendar calendar = Calendar.getInstance();
            this.nowYear = calendar.get(1) + "年";
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void setCheckListener(ChangeCheckListener changeCheckListener) {
        this.checkListener = changeCheckListener;
    }

    public void setSwipeClickListener(OnSwipeClickListener onSwipeClickListener) {
        this.swipeClickListener = onSwipeClickListener;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 3) {
            return new CollectionImageViewHolder(from.inflate(2131493600, viewGroup, false));
        }
        if (i == 1 || i == 2) {
            return new CollectionNavigationViewHolder(from.inflate(2131493598, viewGroup, false));
        }
        if (i == 0) {
            return new CollectionGroupViewHolder(from.inflate(2131493599, viewGroup, false));
        }
        return null;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final RecentMiniEntity recentMiniEntity;
        if (viewHolder != null) {
            try {
                if (this.mList == null || (recentMiniEntity = this.mList.get(i)) == null) {
                    return;
                }
                if (recentMiniEntity.getType() == 3) {
                    final CollectionImageViewHolder collectionImageViewHolder = (CollectionImageViewHolder) viewHolder;
                    if (this.isBianJi) {
                        collectionImageViewHolder.mSl_HuaDong.setSwipeEnable(false);
                        collectionImageViewHolder.mCk_CheckLayout.setVisibility(0);
                    } else {
                        collectionImageViewHolder.mSl_HuaDong.setSwipeEnable(true);
                        collectionImageViewHolder.mCk_CheckLayout.setVisibility(8);
                    }
                    if (recentMiniEntity.isSelect()) {
                        collectionImageViewHolder.mCk_Check.setBackgroundResource(2131231497);
                    } else {
                        collectionImageViewHolder.mCk_Check.setBackgroundResource(2131231504);
                    }
                    collectionImageViewHolder.mCk_CheckLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            if (recentMiniEntity.isSelect()) {
                                collectionImageViewHolder.mCk_Check.setBackgroundResource(2131231504);
                            } else {
                                collectionImageViewHolder.mCk_Check.setBackgroundResource(2131231497);
                            }
                            RecentMiniEntity recentMiniEntity2 = recentMiniEntity;
                            recentMiniEntity2.setSelect(!recentMiniEntity2.isSelect());
                            if (CollectionMoreAdapter.this.checkListener != null) {
                                CollectionMoreAdapter.this.checkListener.onCheckState(recentMiniEntity, i);
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    ShowImageUtils.showCenterCropRoundImageView(viewHolder.itemView.getContext(), recentMiniEntity.getAppImg(), 2131231246, collectionImageViewHolder.mImg_Pic, UIUtils.dip2px(this.mActivity, 7.0f));
                    collectionImageViewHolder.mTv_Title.setText(recentMiniEntity.getAppName());
                    collectionImageViewHolder.mTv_Content.setText(recentMiniEntity.getAppDesc());
                    if (1 == this.type) {
                        collectionImageViewHolder.mTv_ShanChu.setText("删除");
                        collectionImageViewHolder.mTv_ShouCang.setText("收藏");
                    } else {
                        collectionImageViewHolder.mTv_ShanChu.setText("取消\n收藏");
                        collectionImageViewHolder.mTv_ShouCang.setText("添加\n主页");
                    }
                    collectionImageViewHolder.mTv_ShanChu.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            collectionImageViewHolder.mTv_ShouCang.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    collectionImageViewHolder.mSl_HuaDong.setSwipeEnable(true);
                                }
                            }, 1500L);
                            collectionImageViewHolder.mSl_HuaDong.setSwipeEnable(false);
                            collectionImageViewHolder.mSl_HuaDong.quickClose();
                            if (CollectionMoreAdapter.this.swipeClickListener != null) {
                                CollectionMoreAdapter.this.swipeClickListener.onRightClick(recentMiniEntity, i);
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    collectionImageViewHolder.mTv_ShouCang.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            collectionImageViewHolder.mTv_ShouCang.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    collectionImageViewHolder.mSl_HuaDong.setSwipeEnable(true);
                                }
                            }, 1500L);
                            collectionImageViewHolder.mSl_HuaDong.setSwipeEnable(false);
                            collectionImageViewHolder.mSl_HuaDong.quickClose();
                            if (CollectionMoreAdapter.this.swipeClickListener != null) {
                                CollectionMoreAdapter.this.swipeClickListener.onLeftClick(recentMiniEntity, i);
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    collectionImageViewHolder.mRlItemLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            if (CollectionMoreAdapter.this.itemClickListener != null && !CollectionMoreAdapter.this.isBianJi) {
                                CollectionMoreAdapter.this.itemClickListener.onItemClick(recentMiniEntity);
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                } else if (recentMiniEntity.getType() == 0) {
                    ((CollectionGroupViewHolder) viewHolder).mTv_GroupTitle.setText(recentMiniEntity.getDateString().replace(this.nowYear, ""));
                } else if (recentMiniEntity.getType() == 1 || recentMiniEntity.getType() == 2) {
                    final CollectionNavigationViewHolder collectionNavigationViewHolder = (CollectionNavigationViewHolder) viewHolder;
                    if (this.isBianJi) {
                        collectionNavigationViewHolder.mSl_HuaDong.setSwipeEnable(false);
                        collectionNavigationViewHolder.mCk_CheckLayout.setVisibility(0);
                    } else {
                        collectionNavigationViewHolder.mSl_HuaDong.setSwipeEnable(true);
                        collectionNavigationViewHolder.mCk_CheckLayout.setVisibility(8);
                    }
                    if (recentMiniEntity.isSelect()) {
                        collectionNavigationViewHolder.mCk_Check.setBackgroundResource(2131231497);
                    } else {
                        collectionNavigationViewHolder.mCk_Check.setBackgroundResource(2131231504);
                    }
                    collectionNavigationViewHolder.mCk_CheckLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            if (recentMiniEntity.isSelect()) {
                                collectionNavigationViewHolder.mCk_Check.setBackgroundResource(2131231504);
                            } else {
                                collectionNavigationViewHolder.mCk_Check.setBackgroundResource(2131231497);
                            }
                            RecentMiniEntity recentMiniEntity2 = recentMiniEntity;
                            recentMiniEntity2.setSelect(!recentMiniEntity2.isSelect());
                            if (CollectionMoreAdapter.this.checkListener != null) {
                                CollectionMoreAdapter.this.checkListener.onCheckState(recentMiniEntity, i);
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    ShowImageUtils.showCenterCropRoundImageView(viewHolder.itemView.getContext(), recentMiniEntity.getAppImg(), 2131231246, collectionNavigationViewHolder.mImg_Pic, UIUtils.dip2px(this.mActivity, 7.0f));
                    if (1 == this.type) {
                        collectionNavigationViewHolder.mTv_ShanChu.setText("删除");
                        collectionNavigationViewHolder.mTv_ShouCang.setText("收藏");
                    } else {
                        collectionNavigationViewHolder.mTv_ShanChu.setText("取消\n收藏");
                        collectionNavigationViewHolder.mTv_ShouCang.setText("添加\n主页");
                    }
                    collectionNavigationViewHolder.mTv_Title.setText(recentMiniEntity.getAppName());
                    if (TextUtils.isEmpty(recentMiniEntity.getAppDesc())) {
                        collectionNavigationViewHolder.mTv_Content.setVisibility(8);
                        collectionNavigationViewHolder.mTv_Content.setText("");
                    } else {
                        collectionNavigationViewHolder.mTv_Content.setVisibility(0);
                        collectionNavigationViewHolder.mTv_Content.setText(recentMiniEntity.getAppDesc());
                    }
                    collectionNavigationViewHolder.mTv_ShanChu.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.6
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            collectionNavigationViewHolder.mTv_ShouCang.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.6.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    collectionNavigationViewHolder.mSl_HuaDong.setSwipeEnable(true);
                                }
                            }, 1500L);
                            collectionNavigationViewHolder.mSl_HuaDong.setSwipeEnable(false);
                            collectionNavigationViewHolder.mSl_HuaDong.quickClose();
                            if (CollectionMoreAdapter.this.swipeClickListener != null) {
                                CollectionMoreAdapter.this.swipeClickListener.onRightClick(recentMiniEntity, i);
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    collectionNavigationViewHolder.mTv_ShouCang.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.7
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            collectionNavigationViewHolder.mTv_ShouCang.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.7.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    collectionNavigationViewHolder.mSl_HuaDong.setSwipeEnable(true);
                                }
                            }, 1500L);
                            collectionNavigationViewHolder.mSl_HuaDong.setSwipeEnable(false);
                            collectionNavigationViewHolder.mSl_HuaDong.quickClose();
                            if (CollectionMoreAdapter.this.swipeClickListener != null) {
                                CollectionMoreAdapter.this.swipeClickListener.onLeftClick(recentMiniEntity, i);
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    collectionNavigationViewHolder.mRlItemLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.8
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            if (CollectionMoreAdapter.this.itemClickListener != null && !CollectionMoreAdapter.this.isBianJi) {
                                CollectionMoreAdapter.this.itemClickListener.onItemClick(recentMiniEntity);
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                }
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mList.get(i).getType();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<RecentMiniEntity> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
