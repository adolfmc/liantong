package com.sinovatech.unicom.basic.p315ui.fuwu.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.eventbus.ServiceMenuEvent;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p315ui.fragment.ServiceFragment;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;
import com.sinovatech.unicom.basic.p315ui.fuwu.utils.FuWuUtils;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.adapter.DragRecyclerViewAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DragRecyclerViewAdapter extends RecyclerView.Adapter<DragHolder> implements ItemTouchHelperAdapter {
    private List<MenuEntity> contentList;
    private Activity context;
    private boolean isShowDelete = false;
    private OnItemTouchUpListener touchUpListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.fuwu.adapter.DragRecyclerViewAdapter$OnItemTouchUpListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemTouchUpListener {
        void onItemClick(int i, DragHolder dragHolder);

        void onItemTouchUp(RecyclerView.ViewHolder viewHolder);
    }

    public DragRecyclerViewAdapter(Activity activity, List<MenuEntity> list) {
        this.contentList = new ArrayList();
        this.context = activity;
        this.contentList = FuWuConstant.topList;
    }

    public void setData(List<MenuEntity> list) {
        this.contentList = FuWuConstant.topList;
        notifyDataSetChanged();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public DragHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DragHolder(LayoutInflater.from(this.context).inflate(2131493139, viewGroup, false));
    }

    public void setOnItemTouchUpListener(OnItemTouchUpListener onItemTouchUpListener) {
        this.touchUpListener = onItemTouchUpListener;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final DragHolder dragHolder, final int i) {
        final boolean z;
        dragHolder.iv_icon.setImageResource(2131231320);
        if (this.isShowDelete) {
            dragHolder.iv_icon.setVisibility(0);
            if (FuWuUtils.isTrue(i)) {
                z = true;
            } else {
                dragHolder.iv_icon.setVisibility(8);
                z = false;
            }
        } else {
            dragHolder.iv_icon.setVisibility(8);
            z = false;
        }
        String menuTitle = this.contentList.get(i).getMenuTitle();
        if (!TextUtils.isEmpty(menuTitle)) {
            int length = menuTitle.length();
            if (length <= 5) {
                dragHolder.textView.setText(menuTitle);
            } else if (length <= 8) {
                dragHolder.textView.setText(menuTitle.substring(0, 4) + "\n" + menuTitle.substring(4));
            } else if (length <= 10) {
                dragHolder.textView.setText(menuTitle.substring(0, 5) + "\n" + menuTitle.substring(5));
            } else {
                dragHolder.textView.setText(menuTitle);
            }
        } else {
            dragHolder.textView.setText("");
        }
        GlideApp.with(this.context).asBitmap().placeholder(2131231795).error(2131231795).load(this.contentList.get(i).getMenuIconURL()).into(dragHolder.imageView);
        dragHolder.imageView.setContentDescription(this.contentList.get(i).getMenuTitle());
        dragHolder.rl_item_bg.setImageResource(2131231308);
        dragHolder.itemView.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.adapter.DragRecyclerViewAdapter.1
            private float startX;
            private float startY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getActionMasked()) {
                    case 0:
                        this.startX = motionEvent.getX();
                        this.startY = motionEvent.getY();
                        return true;
                    case 1:
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        float abs = Math.abs(x - this.startX);
                        float abs2 = Math.abs(y - this.startY);
                        if (abs >= 10.0f || abs2 >= 10.0f) {
                            if (DragRecyclerViewAdapter.this.touchUpListener != null) {
                                DragRecyclerViewAdapter.this.touchUpListener.onItemTouchUp(dragHolder);
                                return true;
                            }
                            return true;
                        } else if (DragRecyclerViewAdapter.this.touchUpListener != null) {
                            if (!FuWuConstant.isBianJiState) {
                                DragRecyclerViewAdapter.this.touchUpListener.onItemClick(i, dragHolder);
                                return true;
                            } else if (z) {
                                DragRecyclerViewAdapter.this.touchUpListener.onItemClick(i, dragHolder);
                                return true;
                            } else {
                                return true;
                            }
                        } else {
                            return true;
                        }
                    case 2:
                    default:
                        return true;
                }
            }
        });
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.contentList.size();
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fuwu.adapter.ItemTouchHelperAdapter
    public void onItemMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        int adapterPosition = viewHolder.getAdapterPosition();
        int adapterPosition2 = viewHolder2.getAdapterPosition();
        if (!FuWuUtils.isTrue(adapterPosition) || !FuWuUtils.isTrue(adapterPosition2) || adapterPosition >= this.contentList.size() || adapterPosition2 >= this.contentList.size()) {
            return;
        }
        if (adapterPosition < adapterPosition2) {
            int i = adapterPosition;
            while (i < adapterPosition2) {
                int i2 = i + 1;
                Collections.swap(this.contentList, i, i2);
                i = i2;
            }
        } else {
            for (int i3 = adapterPosition; i3 > adapterPosition2; i3--) {
                Collections.swap(this.contentList, i3, i3 - 1);
            }
        }
        notifyItemMoved(adapterPosition, adapterPosition2);
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fuwu.adapter.ItemTouchHelperAdapter
    public void onItemDissmiss(RecyclerView.ViewHolder viewHolder) {
        int adapterPosition = viewHolder.getAdapterPosition();
        MsLogUtil.m7979d("服务页面 onItemDissmiss", "移除数据：" + adapterPosition);
        List<MenuEntity> list = this.contentList;
        if (list == null || list.size() <= 0 || adapterPosition >= this.contentList.size()) {
            return;
        }
        this.contentList.remove(adapterPosition);
        ServiceFragment.isSearchAdapterSX = false;
        MsLogUtil.m7980d("改变------>搜索模块的下标: isSearchAdapterSX = false   remove");
        notifyItemRemoved(adapterPosition);
        EventBusUtils.post(new ServiceMenuEvent(EventBusUtils.EVENT_FUWU_REFRESHADAPTER));
        MenuDataCenter.getInstance().logList("移除之后");
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fuwu.adapter.ItemTouchHelperAdapter
    public void onItemSelect(RecyclerView.ViewHolder viewHolder) {
        viewHolder.getAdapterPosition();
        viewHolder.itemView.setScaleX(1.2f);
        viewHolder.itemView.setScaleY(1.2f);
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fuwu.adapter.ItemTouchHelperAdapter
    public void onItemClear(RecyclerView.ViewHolder viewHolder) {
        viewHolder.getAdapterPosition();
        viewHolder.itemView.setScaleX(1.0f);
        viewHolder.itemView.setScaleY(1.0f);
        notifyDataSetChanged();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.fuwu.adapter.DragRecyclerViewAdapter$DragHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class DragHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ImageView iv_icon;
        private LinearLayout lin_iv_click;
        private RelativeLayout rl_all;
        private ImageView rl_item_bg;
        private TextView textView;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ ImageView access$000(DragHolder dragHolder) {
            return dragHolder.iv_icon;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ TextView access$100(DragHolder dragHolder) {
            return dragHolder.textView;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ ImageView access$200(DragHolder dragHolder) {
            return dragHolder.imageView;
        }

        public DragHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(2131297052);
            this.imageView = (ImageView) view.findViewById(2131297051);
            this.rl_item_bg = (ImageView) view.findViewById(2131298347);
            this.iv_icon = (ImageView) view.findViewById(2131297398);
            this.rl_all = (RelativeLayout) view.findViewById(2131298308);
            this.lin_iv_click = (LinearLayout) view.findViewById(2131297606);
        }
    }

    public void setContentList(Activity activity, List<MenuEntity> list, boolean z) {
        this.context = activity;
        this.contentList = list;
        this.isShowDelete = z;
        notifyDataSetChanged();
    }
}
