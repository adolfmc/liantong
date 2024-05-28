package com.sinovatech.unicom.separatemodule.gamecenter.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.p086v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.Transformation;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.gamecenter.GameCentersDataManager;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import java.util.List;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GridViewAdapter extends BaseAdapter {
    Activity context;
    private List<GamesEntity.GamesDataEntity> mDatas;
    private int mIndex;
    private LayoutInflater mLayoutInflater;
    private int mPageSize = 5;

    public GridViewAdapter(Activity activity, List<GamesEntity.GamesDataEntity> list, int i) {
        this.mDatas = list;
        this.mLayoutInflater = LayoutInflater.from(activity);
        this.mIndex = i;
        this.context = activity;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int size = this.mDatas.size();
        int i = this.mPageSize;
        return size > (this.mIndex + 1) * i ? i : this.mDatas.size() - (this.mIndex * this.mPageSize);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mDatas.get(i + (this.mIndex * this.mPageSize));
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i + (this.mIndex * this.mPageSize);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Log.i("TAG", "长度:" + getCount());
        if (view == null) {
            view = this.mLayoutInflater.inflate(2131493214, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.f18526tv = (TextView) view.findViewById(2131299021);
            viewHolder.f18525iv = (ImageView) view.findViewById(2131297398);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        int i2 = i + (this.mIndex * this.mPageSize);
        viewHolder.f18526tv.setText(this.mDatas.get(i2).getTitle());
        GlideApp.with(this.context).load(this.mDatas.get(i2).getIcon()).placeholder(2131231337).error(2131231337).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(viewHolder.f18525iv);
        view.setOnClickListener(new View$OnClickListenerC87651(i2));
        return view;
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.gamecenter.adapter.GridViewAdapter$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC87651 implements View.OnClickListener {
        final /* synthetic */ int val$pos;

        View$OnClickListenerC87651(int i) {
            this.val$pos = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            IntentManager.generateIntentAndGo(GridViewAdapter.this.context, ((GamesEntity.GamesDataEntity) GridViewAdapter.this.mDatas.get(this.val$pos)).getUrl(), "", true, "get");
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("p2", "40526");
                hashMap.put("p3", "游戏专区App客户端");
                hashMap.put("p5", "10");
                hashMap.put("p25", ((GamesEntity.GamesDataEntity) GridViewAdapter.this.mDatas.get(this.val$pos)).getTitle());
                hashMap.put("p32", "Android");
                hashMap.put("p26", ((GamesEntity.GamesDataEntity) GridViewAdapter.this.mDatas.get(this.val$pos)).getGroupCode());
                hashMap.put("p34", GridViewAdapter.this.context.getString(2131886969).split("@")[1]);
                GameCentersDataManager.getInstance().commonLog((AppCompatActivity) GridViewAdapter.this.context, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$GridViewAdapter$1$Hfb5bQ6n_8NBHk0RBgFP4EliK38
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        UIUtils.logD("游戏日志结果-->", (String) obj);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class ViewHolder {

        /* renamed from: iv */
        public ImageView f18525iv;

        /* renamed from: tv */
        public TextView f18526tv;

        ViewHolder() {
        }
    }
}
