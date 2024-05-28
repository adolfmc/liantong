package com.sinovatech.unicom.basic.p315ui.home.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeBwJingZhunEntity;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.home.adapter.HomeBwJingZhunAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeBwJingZhunAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "HomeBwJingZhunAdapter";
    private Activity mActivity;
    private List<HomeBwJingZhunEntity> mList;

    public HomeBwJingZhunAdapter(Activity activity, List<HomeBwJingZhunEntity> list) {
        this.mList = new ArrayList();
        this.mActivity = activity;
        this.mList = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493158, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        try {
            final HomeBwJingZhunEntity homeBwJingZhunEntity = this.mList.get(i);
            if (homeBwJingZhunEntity != null) {
                final String imgSrc = homeBwJingZhunEntity.getImgSrc();
                ShowImageUtils.showCenterCropRoundImageView(this.mActivity, imgSrc, viewHolder.mImgBg, UIUtils.dip2px(this.mActivity, 8.0f));
                viewHolder.mTvTitle.setText(homeBwJingZhunEntity.getTitle());
                viewHolder.mTvSubTitle.setText(homeBwJingZhunEntity.getViceTitle());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.adapter.HomeBwJingZhunAdapter.1
                    /* JADX WARN: Removed duplicated region for block: B:19:0x006f A[Catch: Exception -> 0x012b, TryCatch #0 {Exception -> 0x012b, blocks: (B:3:0x001b, B:5:0x0027, B:8:0x0036, B:10:0x003f, B:12:0x004b, B:15:0x005a, B:17:0x0063, B:19:0x006f, B:22:0x007e, B:24:0x0087), top: B:30:0x001b }] */
                    @Override // android.view.View.OnClickListener
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void onClick(android.view.View r12) {
                        /*
                            Method dump skipped, instructions count: 311
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.home.adapter.HomeBwJingZhunAdapter.View$OnClickListenerC76811.onClick(android.view.View):void");
                    }
                });
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "首页本网精准营销适配器异常" + e.getMessage());
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mList.size() <= 3) {
            return this.mList.size();
        }
        return 3;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.home.adapter.HomeBwJingZhunAdapter$ViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgBg;
        private TextView mTvSubTitle;
        private TextView mTvTitle;

        public ViewHolder(View view) {
            super(view);
            this.mImgBg = (ImageView) view.findViewById(2131297112);
            this.mTvTitle = (TextView) view.findViewById(2131297114);
            this.mTvSubTitle = (TextView) view.findViewById(2131297113);
        }
    }
}
