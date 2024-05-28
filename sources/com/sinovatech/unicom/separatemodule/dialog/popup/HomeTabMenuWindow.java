package com.sinovatech.unicom.separatemodule.dialog.popup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sinovatech.unicom.basic.p314po.HomeTabEntity;
import com.sinovatech.unicom.basic.view.decklayout.RecyclerTabLayout;
import com.sinovatech.unicom.separatemodule.dialog.BasePopupWindow;
import com.sinovatech.unicom.separatemodule.dialog.adapter.AppAdapter;
import com.sinovatech.unicom.separatemodule.dialog.adapter.BaseAdapter;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class HomeTabMenuWindow {

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class Builder extends BasePopupWindow.Builder<Builder> implements View.OnLayoutChangeListener, BaseAdapter.OnItemClickListener, Runnable {
        private int index;
        private final MenuAdapter mAdapter;
        private final RecyclerView mRecyclerView;
        private RecyclerTabLayout mTabView;

        public Builder(Context context) {
            super(context);
            setContentView(2131493185);
            setBackgroundDimAmount(0.4f);
            this.mRecyclerView = (RecyclerView) findViewById(2131297167);
            this.mAdapter = new MenuAdapter(getContext());
            this.mAdapter.setOnItemClickListener(this);
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(context, 4));
            this.mRecyclerView.setAdapter(this.mAdapter);
        }

        public Builder setList(List<HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO> list) {
            this.mAdapter.setData(list);
            this.mRecyclerView.addOnLayoutChangeListener(this);
            return this;
        }

        public Builder setTabView(RecyclerTabLayout recyclerTabLayout) {
            this.mTabView = recyclerTabLayout;
            this.mAdapter.setIndex(recyclerTabLayout.getCurrentIndex());
            this.mAdapter.notifyItemChanged(recyclerTabLayout.getCurrentIndex());
            return this;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.adapter.BaseAdapter.OnItemClickListener
        public void onItemClick(RecyclerView recyclerView, View view, int i) {
            RecyclerTabLayout recyclerTabLayout = this.mTabView;
            if (recyclerTabLayout != null) {
                recyclerTabLayout.choseItem(i);
                dismiss();
            }
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.mRecyclerView.removeOnLayoutChangeListener(this);
            post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewGroup.LayoutParams layoutParams = this.mRecyclerView.getLayoutParams();
            int screenHeight = (getScreenHeight() / 4) * 3;
            if (this.mRecyclerView.getHeight() > screenHeight) {
                if (layoutParams.height != screenHeight) {
                    layoutParams.height = screenHeight;
                    this.mRecyclerView.setLayoutParams(layoutParams);
                }
            } else if (layoutParams.height != -2) {
                layoutParams.height = -2;
                this.mRecyclerView.setLayoutParams(layoutParams);
            }
        }

        private int getScreenHeight() {
            return getResources().getDisplayMetrics().heightPixels;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class MenuAdapter extends AppAdapter<HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO> {
        private int index;

        private MenuAdapter(Context context) {
            super(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIndex(int i) {
            this.index = i;
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public final class ViewHolder extends BaseAdapter<BaseAdapter<?>.ViewHolder>.ViewHolder {
            private final TextView mTextView;

            ViewHolder() {
                super(MenuAdapter.this, 2131493186);
                this.mTextView = (TextView) findViewById(2131297200);
            }

            @Override // com.sinovatech.unicom.separatemodule.dialog.adapter.BaseAdapter.ViewHolder
            public void onBindView(int i) {
                try {
                    if (i == MenuAdapter.this.index) {
                        this.mTextView.setTextColor(-1703897);
                        this.mTextView.setBackgroundResource(2131231466);
                    } else {
                        this.mTextView.setTextColor(-13421773);
                        this.mTextView.setBackgroundResource(2131231465);
                    }
                    if (MenuAdapter.this.getItem(i).getNavName().length() > 4) {
                        String navName = MenuAdapter.this.getItem(i).getNavName();
                        String substring = navName.substring(0, 4);
                        String substring2 = navName.substring(4);
                        this.mTextView.setText(substring + "\n" + substring2);
                        this.mTextView.setTextSize(1, 10.0f);
                        return;
                    }
                    this.mTextView.setTextSize(1, 14.0f);
                    this.mTextView.setText(MenuAdapter.this.getItem(i).getNavName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
