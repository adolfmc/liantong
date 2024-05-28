package com.sinovatech.unicom.separatemodule.videocenter.adapter;

import android.app.Activity;
import android.widget.ImageView;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveOrFengGuangInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import com.sinovatech.unicom.separatemodule.videocenter.utils.FormatUtils;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoZhiBoAdapter extends BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder> {
    private Activity activityContext;

    public VideoZhiBoAdapter(int i, Activity activity) {
        super(i);
        this.activityContext = activity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, LiveOrFengGuangInfoEntity.LiveInfoItem liveInfoItem) {
        baseViewHolder.setText(2131299060, liveInfoItem.getVideoTitle()).setText(2131299130, liveInfoItem.getUserNickName()).setText(2131299157, FormatUtils.str2Wan(liveInfoItem.getVideoNum())).setImageResource(2131297528, "0".equals(liveInfoItem.getVideoCloseState()) ? 2131231701 : 2131231702);
        GlideApp.with(this.activityContext).load(liveInfoItem.getVideoBgImg()).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131231128).error(2131231128).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(UIUtils.dip2px(5.0f), 0, RoundedCornersTransformation.CornerType.ALL)))).into((ImageView) baseViewHolder.getView(2131297474));
        GlideApp.with(this.activityContext).load(liveInfoItem.getUserHeadImg()).error(2131231245).placeholder(2131231245).into((CircularImage) baseViewHolder.getView(2131297517));
    }
}
