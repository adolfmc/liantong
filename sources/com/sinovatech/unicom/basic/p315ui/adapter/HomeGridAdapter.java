package com.sinovatech.unicom.basic.p315ui.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.adapter.HomeGridAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridHolder> {
    private Activity activityContext;
    private int itemWidth;
    private List<MenuEntity> list = new ArrayList();

    public HomeGridAdapter(Activity activity, int i) {
        this.activityContext = activity;
        this.itemWidth = i;
    }

    public void update(List<MenuEntity> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
        notifyDataSetChanged();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public HomeGridHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeGridHolder(LayoutInflater.from(this.activityContext).inflate(2131493165, viewGroup, false), this.itemWidth);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull HomeGridHolder homeGridHolder, int i) {
        MenuEntity menuEntity = this.list.get(i);
        if (menuEntity == null) {
            homeGridHolder.linearLayout.setVisibility(4);
            return;
        }
        homeGridHolder.linearLayout.setVisibility(0);
        homeGridHolder.textView.setText(menuEntity.getMenuTitle());
        Glide.with(this.activityContext).load(menuEntity.getMenuIconURL()).into(homeGridHolder.imageView);
        if (menuEntity.isShowCaiDai()) {
            String caiDaiIcon = menuEntity.getCaiDaiIcon();
            if (!TextUtils.isEmpty(caiDaiIcon) && caiDaiIcon.endsWith("gif")) {
                Glide.with(this.activityContext).asGif().load(caiDaiIcon).into(homeGridHolder.caidaiImage);
                homeGridHolder.caidaiImage.setVisibility(0);
                return;
            }
            homeGridHolder.caidaiImage.setVisibility(0);
            Glide.with(this.activityContext).asBitmap().load(caiDaiIcon).into(homeGridHolder.caidaiImage);
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.HomeGridAdapter$HomeGridHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class HomeGridHolder extends RecyclerView.ViewHolder {
        ImageView caidaiImage;
        ImageView imageView;
        LinearLayout linearLayout;
        ImageView noticeImage;
        TextView textView;

        private HomeGridHolder(View view, int i) {
            super(view);
            this.itemView.getLayoutParams().width = i;
            this.imageView = (ImageView) view.findViewById(2131297141);
            this.textView = (TextView) view.findViewById(2131297144);
            this.caidaiImage = (ImageView) view.findViewById(2131297139);
            this.noticeImage = (ImageView) view.findViewById(2131297143);
            this.linearLayout = (LinearLayout) view.findViewById(2131297145);
        }
    }
}
