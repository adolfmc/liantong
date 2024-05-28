package com.sinovatech.unicom.basic.p315ui.home.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeYwJingZhunEntity;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.home.adapter.HomeYwJingZhunAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeYwJingZhunAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "HomeYwJingZhunAdapter";
    private int formType;
    private Activity mActivity;
    private List<HomeYwJingZhunEntity> mList;

    public HomeYwJingZhunAdapter(Activity activity, List<HomeYwJingZhunEntity> list, int i) {
        this.mList = new ArrayList();
        this.formType = 0;
        this.mActivity = activity;
        this.mList = list;
        this.formType = i;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493190, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        try {
            final HomeYwJingZhunEntity homeYwJingZhunEntity = this.mList.get(i);
            if (homeYwJingZhunEntity != null) {
                final String productUrl = homeYwJingZhunEntity.getProductUrl();
                if (!TextUtils.isEmpty(productUrl)) {
                    ShowImageUtils.showImageView(this.mActivity, productUrl, viewHolder.mImgBg);
                } else {
                    ShowImageUtils.showImageView(this.mActivity, homeYwJingZhunEntity.getDefImg(), viewHolder.mImgBg);
                }
                viewHolder.mTvTitle.setText(homeYwJingZhunEntity.getProductName());
                viewHolder.mTvBtn.setText(homeYwJingZhunEntity.getProductDesc());
                viewHolder.mTvBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.adapter.HomeYwJingZhunAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        String postion;
                        String str;
                        String str2;
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        IntentManager.gotoWebViewActivity(HomeYwJingZhunAdapter.this.mActivity, homeYwJingZhunEntity.getProductRedirecturl(), homeYwJingZhunEntity.getProductName());
                        try {
                            if (HomeYwJingZhunAdapter.this.formType == 1) {
                                postion = "5010101";
                                str = "异网个人信息页-精准营销";
                                str2 = "My page";
                            } else {
                                postion = PvCurrencyLogUtils.getPostion("11002", i);
                                str = "首页-背景-异网精准营销";
                                str2 = "homePage";
                            }
                            PvCurrencyLogUtils.pvLogMainDJ(postion, homeYwJingZhunEntity.getProductRedirecturl() + "", productUrl + "", homeYwJingZhunEntity.getProductName() + "", str + "", "", homeYwJingZhunEntity.getProductDesc() + "", "", str2);
                            UnicomHomeLogUtils.getInstance().clickUserLog(postion, homeYwJingZhunEntity.getProductName(), "");
                        } catch (Exception e) {
                            MsLogUtil.m7978e(e.getMessage());
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "首页异网精准营销适配器异常" + e.getMessage());
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<HomeYwJingZhunEntity> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.home.adapter.HomeYwJingZhunAdapter$ViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgBg;
        private TextView mTvBtn;
        private TextView mTvTitle;

        public ViewHolder(View view) {
            super(view);
            this.mImgBg = (ImageView) view.findViewById(2131297216);
            this.mTvTitle = (TextView) view.findViewById(2131297218);
            this.mTvBtn = (TextView) view.findViewById(2131297217);
        }
    }
}
