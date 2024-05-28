package com.sinovatech.unicom.separatemodule.gamecenter.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.p083v4.content.ContextCompat;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.Transformation;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.HotGamesEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HotGameAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<Map<String, HotGamesEntity.HotGame>> list;
    private GameClickedListener listener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface GameClickedListener {
        void getCoin(HotGamesEntity.HotGame hotGame);

        void toGameInfo(HotGamesEntity.HotGame hotGame);
    }

    public HotGameAdapter(Context context, List<Map<String, HotGamesEntity.HotGame>> list) {
        this.context = context;
        this.list = list == null ? new ArrayList<>() : list;
    }

    public void update(List<Map<String, HotGamesEntity.HotGame>> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setListener(GameClickedListener gameClickedListener) {
        this.listener = gameClickedListener;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493211, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Map<String, HotGamesEntity.HotGame> map = this.list.get(i);
        if (map.containsKey("up")) {
            final HotGamesEntity.HotGame hotGame = map.get("up");
            viewHolder.itemView0.setVisibility(0);
            GlideApp.with(this.context).load(hotGame.getSmallImage()).placeholder(2131231337).error(2131231337).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(viewHolder.ivIcon0);
            GlideApp.with(this.context).load(hotGame.getLaceImgUrl()).into(viewHolder.ivTips0);
            GlideApp.with(this.context).load(hotGame.getGameLabel()).into(viewHolder.ivLabel0);
            viewHolder.tvName0.setText(hotGame.getName());
            StringBuffer stringBuffer = new StringBuffer(hotGame.getCurrentMinute());
            TextView textView = viewHolder.tvTime0;
            stringBuffer.append("/");
            stringBuffer.append(hotGame.getMinute());
            stringBuffer.append("分钟");
            textView.setText(stringBuffer.toString());
            viewHolder.tvBtn00.setText(hotGame.getCopywriting());
            if ("2".equals(hotGame.getState())) {
                viewHolder.tvBtn00.setVisibility(8);
                viewHolder.tvBtn10.setVisibility(0);
            } else {
                viewHolder.tvBtn00.setVisibility(0);
                viewHolder.tvBtn10.setVisibility(8);
                if (hotGame.getState().equals("1")) {
                    viewHolder.tvBtn00.setBackground(ContextCompat.getDrawable(this.context, 2131231339));
                    viewHolder.tvBtn00.setTextColor(-1);
                } else {
                    viewHolder.tvBtn00.setBackground(ContextCompat.getDrawable(this.context, 2131231338));
                    viewHolder.tvBtn00.setTextColor(-1703897);
                }
            }
            viewHolder.tvBtn00.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$HotGameAdapter$iFYmF1cUdoNACZ8GweU6Ojv6cdQ
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HotGameAdapter.lambda$onBindViewHolder$0(HotGameAdapter.this, hotGame, view);
                }
            });
            viewHolder.tvBtn10.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$HotGameAdapter$450R2z5-SItmkKLh2FN2MXxIJfg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HotGameAdapter.lambda$onBindViewHolder$1(HotGameAdapter.this, hotGame, view);
                }
            });
            viewHolder.itemView0.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$HotGameAdapter$jh7KU893NzRtAHgbkqjw3QTruAg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HotGameAdapter.lambda$onBindViewHolder$2(HotGameAdapter.this, hotGame, view);
                }
            });
        } else {
            viewHolder.itemView0.setVisibility(4);
        }
        if (map.containsKey("down")) {
            final HotGamesEntity.HotGame hotGame2 = map.get("down");
            viewHolder.itemView1.setVisibility(0);
            GlideApp.with(this.context).load(hotGame2.getSmallImage()).placeholder(2131231337).error(2131231337).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(viewHolder.ivIcon1);
            GlideApp.with(this.context).load(hotGame2.getLaceImgUrl()).into(viewHolder.ivTips1);
            GlideApp.with(this.context).load(hotGame2.getGameLabel()).into(viewHolder.ivLabel1);
            viewHolder.tvName1.setText(hotGame2.getName());
            StringBuffer stringBuffer2 = new StringBuffer(hotGame2.getCurrentMinute());
            TextView textView2 = viewHolder.tvTime1;
            stringBuffer2.append("/");
            stringBuffer2.append(hotGame2.getMinute());
            stringBuffer2.append("分钟");
            textView2.setText(stringBuffer2.toString());
            viewHolder.tvBtn01.setText(hotGame2.getCopywriting());
            if ("2".equals(hotGame2.getState())) {
                viewHolder.tvBtn01.setVisibility(8);
                viewHolder.tvBtn11.setVisibility(0);
            } else {
                viewHolder.tvBtn01.setVisibility(0);
                viewHolder.tvBtn11.setVisibility(8);
                if (hotGame2.getState().equals("1")) {
                    viewHolder.tvBtn01.setBackground(ContextCompat.getDrawable(this.context, 2131231339));
                    viewHolder.tvBtn01.setTextColor(-1);
                } else {
                    viewHolder.tvBtn01.setBackground(ContextCompat.getDrawable(this.context, 2131231338));
                    viewHolder.tvBtn01.setTextColor(-1703897);
                }
            }
            viewHolder.tvBtn01.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$HotGameAdapter$8kwp4C0j22oUVFzoYd_8R51-cOw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HotGameAdapter.lambda$onBindViewHolder$3(HotGameAdapter.this, hotGame2, view);
                }
            });
            viewHolder.tvBtn11.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$HotGameAdapter$rHE1yL0-gcUKV5BHm5eF8CPow_s
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HotGameAdapter.lambda$onBindViewHolder$4(HotGameAdapter.this, hotGame2, view);
                }
            });
            viewHolder.itemView1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$HotGameAdapter$DVLUeOmYCcJFupK6Isso74_3p50
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HotGameAdapter.lambda$onBindViewHolder$5(HotGameAdapter.this, hotGame2, view);
                }
            });
            return;
        }
        viewHolder.itemView1.setVisibility(4);
    }

    public static /* synthetic */ void lambda$onBindViewHolder$0(HotGameAdapter hotGameAdapter, HotGamesEntity.HotGame hotGame, View view) {
        if (hotGameAdapter.listener != null) {
            if (hotGame.getState().equals("0")) {
                hotGameAdapter.listener.toGameInfo(hotGame);
            } else {
                hotGameAdapter.listener.getCoin(hotGame);
            }
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$1(HotGameAdapter hotGameAdapter, HotGamesEntity.HotGame hotGame, View view) {
        GameClickedListener gameClickedListener = hotGameAdapter.listener;
        if (gameClickedListener != null) {
            gameClickedListener.toGameInfo(hotGame);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$2(HotGameAdapter hotGameAdapter, HotGamesEntity.HotGame hotGame, View view) {
        GameClickedListener gameClickedListener = hotGameAdapter.listener;
        if (gameClickedListener != null) {
            gameClickedListener.toGameInfo(hotGame);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$3(HotGameAdapter hotGameAdapter, HotGamesEntity.HotGame hotGame, View view) {
        if (hotGameAdapter.listener != null) {
            if (hotGame.getState().equals("0")) {
                hotGameAdapter.listener.toGameInfo(hotGame);
            } else {
                hotGameAdapter.listener.getCoin(hotGame);
            }
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$4(HotGameAdapter hotGameAdapter, HotGamesEntity.HotGame hotGame, View view) {
        GameClickedListener gameClickedListener = hotGameAdapter.listener;
        if (gameClickedListener != null) {
            gameClickedListener.toGameInfo(hotGame);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$5(HotGameAdapter hotGameAdapter, HotGamesEntity.HotGame hotGame, View view) {
        GameClickedListener gameClickedListener = hotGameAdapter.listener;
        if (gameClickedListener != null) {
            gameClickedListener.toGameInfo(hotGame);
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
        private View itemView0;
        private View itemView1;
        private ImageView ivIcon0;
        private ImageView ivIcon1;
        private ImageView ivLabel0;
        private ImageView ivLabel1;
        private ImageView ivTips0;
        private ImageView ivTips1;
        private TextView tvBtn00;
        private TextView tvBtn01;
        private TextView tvBtn10;
        private TextView tvBtn11;
        private TextView tvName0;
        private TextView tvName1;
        private TextView tvTime0;
        private TextView tvTime1;

        public ViewHolder(View view) {
            super(view);
            this.tvName0 = (TextView) view.findViewById(2131299022);
            this.ivIcon0 = (ImageView) view.findViewById(2131297399);
            this.itemView0 = view.findViewById(2131297727);
            this.tvTime0 = (TextView) view.findViewById(2131299103);
            this.tvBtn00 = (TextView) view.findViewById(2131298891);
            this.tvBtn10 = (TextView) view.findViewById(2131298893);
            this.ivTips0 = (ImageView) view.findViewById(2131297509);
            this.ivLabel0 = (ImageView) view.findViewById(2131297416);
            this.tvName1 = (TextView) view.findViewById(2131299023);
            this.ivIcon1 = (ImageView) view.findViewById(2131297400);
            this.itemView1 = view.findViewById(2131297728);
            this.tvTime1 = (TextView) view.findViewById(2131299104);
            this.tvBtn01 = (TextView) view.findViewById(2131298892);
            this.tvBtn11 = (TextView) view.findViewById(2131298894);
            this.ivTips1 = (ImageView) view.findViewById(2131297510);
            this.ivLabel1 = (ImageView) view.findViewById(2131297417);
        }
    }

    public void changeGameStatus(String str) {
        for (int i = 0; i < this.list.size(); i++) {
            Map<String, HotGamesEntity.HotGame> map = this.list.get(i);
            if (map.containsKey("up")) {
                if (map.get("up").getId().equals(str)) {
                    map.get("up").setState("2");
                    notifyItemChanged(i);
                    return;
                } else if (map.get("down").getId().equals(str)) {
                    map.get("down").setState("2");
                    notifyItemChanged(i);
                    return;
                }
            }
        }
    }
}
