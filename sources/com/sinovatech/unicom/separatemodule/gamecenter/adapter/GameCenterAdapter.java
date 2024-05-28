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
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import java.util.List;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import org.jetbrains.annotations.NotNull;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameCenterAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private int layout;
    private List<GamesEntity.GamesDataEntity> list;
    private GameClickedListener listener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface GameClickedListener {
        void cliced(GamesEntity.GamesDataEntity gamesDataEntity);
    }

    public GameCenterAdapter(Context context, List<GamesEntity.GamesDataEntity> list, int i) {
        this.context = context;
        this.list = list;
        this.layout = i;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    @NotNull
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        int i2 = this.layout;
        if (i2 == 2131493216) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493216, viewGroup, false));
        }
        if (i2 == 2131493214) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493214, viewGroup, true));
        }
        if (i2 == 2131493215) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493215, viewGroup, false));
        }
        return null;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        int i2 = this.layout;
        if (i2 == 2131493216) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$GameCenterAdapter$w7eApsfvzNJC8mUHXqZBbOdP6Pg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GameCenterAdapter.lambda$onBindViewHolder$0(GameCenterAdapter.this, i, view);
                }
            });
            GlideApp.with(this.context).load(this.list.get(i).getSmallImage()).placeholder(2131231337).error(2131231337).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(viewHolder.image_icon);
            viewHolder.tv_name.setText(this.list.get(i).getName());
            TextView textView = viewHolder.tv_tagName;
            textView.setText(this.list.get(i).getTagName() + "类");
            TextView textView2 = viewHolder.tv_gamePersonsSum;
            textView2.setText(this.list.get(i).getGamePersonsSum() + "万人在玩");
        } else if (i2 == 2131493214) {
            if (this.list.size() > 0) {
                GlideApp.with(this.context).load(this.list.get(i).getIcon()).placeholder(2131231337).error(2131231337).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(viewHolder.iv_icon);
                viewHolder.tv_name.setText(this.list.get(i).getTitle());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.GameCenterAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        if (GameCenterAdapter.this.listener != null) {
                            GameCenterAdapter.this.listener.cliced((GamesEntity.GamesDataEntity) GameCenterAdapter.this.list.get(viewHolder.getPosition()));
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            }
        } else if (i2 != 2131493215 || this.list.size() <= 0) {
        } else {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$GameCenterAdapter$Pd7Q5ytwAWy6lfmM6uGPdQAB_-Q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GameCenterAdapter.lambda$onBindViewHolder$1(GameCenterAdapter.this, i, view);
                }
            });
            GlideApp.with(this.context).load(this.list.get(i).getSmallImage()).placeholder(2131231337).error(2131231337).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(viewHolder.iv_smallImage);
            viewHolder.tv_name.setText(this.list.get(i).getName());
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$0(GameCenterAdapter gameCenterAdapter, int i, View view) {
        GameClickedListener gameClickedListener = gameCenterAdapter.listener;
        if (gameClickedListener != null) {
            gameClickedListener.cliced(gameCenterAdapter.list.get(i));
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$1(GameCenterAdapter gameCenterAdapter, int i, View view) {
        GameClickedListener gameClickedListener = gameCenterAdapter.listener;
        if (gameClickedListener != null) {
            gameClickedListener.cliced(gameCenterAdapter.list.get(i));
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    public void setData(List<GamesEntity.GamesDataEntity> list) {
        this.list = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView demo_button;
        private ImageView image_icon;
        private ImageView iv_icon;
        private ImageView iv_smallImage;
        private TextView tv_Type;
        private TextView tv_gamePersonsSum;
        private TextView tv_name;
        private TextView tv_tagName;

        public ViewHolder(View view) {
            super(view);
            this.image_icon = (ImageView) view.findViewById(2131297275);
            this.tv_name = (TextView) view.findViewById(2131299021);
            this.tv_Type = (TextView) view.findViewById(2131299120);
            this.tv_tagName = (TextView) view.findViewById(2131299099);
            this.demo_button = (ImageView) view.findViewById(2131296822);
            this.iv_icon = (ImageView) view.findViewById(2131297398);
            this.iv_smallImage = (ImageView) view.findViewById(2131297499);
            this.tv_gamePersonsSum = (TextView) view.findViewById(2131298950);
        }
    }

    public void setListener(GameClickedListener gameClickedListener) {
        this.listener = gameClickedListener;
    }
}
