package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.support.annotation.NonNull;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.audience.entity.ListDataEntity;
import com.sinovatech.unicom.separatemodule.audience.interfaceImpl.OnItemShowedListener;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceXiaboView;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AudienceAdapter extends RecyclerView.Adapter<AudienceHolder> {
    private AppCompatActivity activityContext;
    private List<ListDataEntity> list;
    private OnItemShowedListener listener;

    public AudienceAdapter(List<ListDataEntity> list, AppCompatActivity appCompatActivity) {
        this.list = list;
        this.activityContext = appCompatActivity;
    }

    public void updateList(List<ListDataEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void loadMore(List<ListDataEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setListener(OnItemShowedListener onItemShowedListener) {
        this.listener = onItemShowedListener;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public AudienceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AudienceHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131492993, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull AudienceHolder audienceHolder, int i) {
        ListDataEntity listDataEntity = this.list.get(i);
        if ("N".equals(listDataEntity.getLiving())) {
            audienceHolder.audienceXiaboView.setVisibility(0);
        } else {
            audienceHolder.audienceXiaboView.setVisibility(8);
            audienceHolder.multiRoot.setVisibility(8);
            GlideApp.with((FragmentActivity) this.activityContext).load(listDataEntity.getCoverImg()).into(audienceHolder.liveRoomCover);
        }
        GlideApp.with((FragmentActivity) this.activityContext).load((Integer) 2131232734).into(audienceHolder.mainBg);
        OnItemShowedListener onItemShowedListener = this.listener;
        if (onItemShowedListener != null) {
            onItemShowedListener.playVideo(i);
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class AudienceHolder extends RecyclerView.ViewHolder {
        AudienceXiaboView audienceXiaboView;
        ImageView bgImageView;
        FrameLayout contairlayout;
        ImageView liveRoomCover;
        ImageView mainBg;
        View multiRoot;

        public AudienceHolder(View view) {
            super(view);
            this.contairlayout = (FrameLayout) view.findViewById(2131296395);
            this.bgImageView = (ImageView) view.findViewById(2131296397);
            this.mainBg = (ImageView) view.findViewById(2131297427);
            this.audienceXiaboView = (AudienceXiaboView) view.findViewById(2131296435);
            this.liveRoomCover = (ImageView) view.findViewById(2131296393);
            this.multiRoot = view.findViewById(2131297749);
        }
    }
}
