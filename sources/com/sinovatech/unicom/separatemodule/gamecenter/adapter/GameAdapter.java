package com.sinovatech.unicom.separatemodule.gamecenter.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.Transformation;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import java.util.ArrayList;
import java.util.List;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private int layout;
    private List<GamesEntity.GamesDataEntity> list;
    private GameClickedListener listener;
    private List<GamesEntity.GamesDataEntity> recommendList;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface GameClickedListener {
        void cliced(GamesEntity.GamesDataEntity gamesDataEntity);
    }

    public GameAdapter(Context context, List<GamesEntity.GamesDataEntity> list, int i) {
        this.layout = i;
        this.context = context;
        this.list = list == null ? new ArrayList<>() : list;
    }

    public GameAdapter(Context context, List<GamesEntity.GamesDataEntity> list) {
        this.context = context;
        this.list = list == null ? new ArrayList<>() : list;
    }

    public void update(List<GamesEntity.GamesDataEntity> list) {
        if (list != null) {
            List<GamesEntity.GamesDataEntity> list2 = this.recommendList;
            if (list2 != null) {
                list.addAll(0, list2);
            }
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setListener(GameClickedListener gameClickedListener) {
        this.listener = gameClickedListener;
    }

    public void setFristItem(List<GamesEntity.GamesDataEntity> list) {
        this.recommendList = list;
        if (this.list.size() <= 0 || this.list.get(0).getGameType().equals("center")) {
            return;
        }
        this.list.addAll(0, list);
        notifyItemInserted(0);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.layout == 0 ? 2131493208 : 2131493209, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        GlideApp.with(this.context).load(this.list.get(i).getSmallImage()).placeholder(2131231337).error(2131231337).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(viewHolder.ivIcon);
        GlideApp.with(this.context).load(this.list.get(i).getLaceImgUrl()).into(viewHolder.ivTips);
        GlideApp.with(this.context).load(this.list.get(i).getGameLabel()).into(viewHolder.ivLabel);
        viewHolder.tvName.setText(this.list.get(i).getName());
        if (this.layout == 0) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$GameAdapter$sqdVmGepwQyvqHwMNt2mkk6Xw2I
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GameAdapter.lambda$onBindViewHolder$0(GameAdapter.this, i, view);
                }
            });
        } else {
            viewHolder.tvPlayBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$GameAdapter$ESOWTCxX5bfc7j2nIRCh0KM2INM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GameAdapter.lambda$onBindViewHolder$1(GameAdapter.this, i, view);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$0(GameAdapter gameAdapter, int i, View view) {
        GameClickedListener gameClickedListener = gameAdapter.listener;
        if (gameClickedListener != null) {
            gameClickedListener.cliced(gameAdapter.list.get(i));
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$1(GameAdapter gameAdapter, int i, View view) {
        GameClickedListener gameClickedListener = gameAdapter.listener;
        if (gameClickedListener != null) {
            gameClickedListener.cliced(gameAdapter.list.get(i));
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
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private ImageView ivIcon;
        private ImageView ivLabel;
        private ImageView ivTips;
        private ImageView iv_Icon;
        private TextView tvDesc;
        private TextView tvName;
        private TextView tvPlayBtn;

        public ViewHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(2131299021);
            this.ivIcon = (ImageView) view.findViewById(2131297398);
            this.ivTips = (ImageView) view.findViewById(2131297508);
            this.ivLabel = (ImageView) view.findViewById(2131297415);
            this.itemView = view;
            if (GameAdapter.this.layout > 0) {
                this.tvDesc = (TextView) view.findViewById(2131298925);
                this.tvPlayBtn = (TextView) view.findViewById(2131299048);
            }
        }
    }

    public List<GamesEntity.GamesDataEntity> getList() {
        return this.list;
    }
}
