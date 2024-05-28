package com.sinovatech.unicom.separatemodule.playdetails.xiangqing.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsData;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.utils.NumUtils;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoDetailsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<VideoDetailsData.DataDTO> list;
    private OnItemClickListener mOnItemClickListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public VideoDetailsAdapter(Context context, List<VideoDetailsData.DataDTO> list) {
        this.context = context;
        this.list = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    @NotNull
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(2131493248, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @SuppressLint({"SetTextI18n"})
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, @SuppressLint({"RecyclerView"}) final int i) {
        VideoDetailsData.DataDTO dataDTO = this.list.get(i);
        viewHolder.mTvTitle.setText(dataDTO.getTitle());
        TextView textView = viewHolder.mTvShichang;
        textView.setText(NumUtils.stringForTime(Integer.parseInt(dataDTO.getVideo_duration()) * 1000) + "时长");
        TextView textView2 = viewHolder.mTvBofang;
        textView2.setText(NumUtils.getBoFangNum(dataDTO.getVideo_watch_count()) + "播放量");
        new RequestOptions();
        Glide.with(this.context).load(dataDTO.getMiddle_image().get(0).getUrl()).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners(15))).into(viewHolder.mIvImage);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.adapter.VideoDetailsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                VideoDetailsAdapter.this.mOnItemClickListener.onItemClick(i);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    public void setData(List<VideoDetailsData.DataDTO> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvImage;
        private TextView mTvBofang;
        private TextView mTvShichang;
        private TextView mTvTitle;

        public ViewHolder(View view) {
            super(view);
            this.mIvImage = (ImageView) view.findViewById(2131297401);
            this.mTvTitle = (TextView) view.findViewById(2131299108);
            this.mTvBofang = (TextView) view.findViewById(2131298886);
            this.mTvShichang = (TextView) view.findViewById(2131299074);
        }
    }
}
