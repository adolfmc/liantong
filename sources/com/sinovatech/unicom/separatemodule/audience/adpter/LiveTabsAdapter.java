package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveAudienceTabEntity;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveTabsAdapter extends BaseQuickAdapter<LiveAudienceTabEntity, BaseViewHolder> {
    public LiveTabsAdapter(int i, @Nullable List<LiveAudienceTabEntity> list) {
        super(i, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, LiveAudienceTabEntity liveAudienceTabEntity) {
        boolean z = false;
        ((ImageView) baseViewHolder.getView(2131297404)).setVisibility(liveAudienceTabEntity.isSelected() ? 0 : 4);
        TextView textView = (TextView) baseViewHolder.getView(2131299108);
        textView.setText(liveAudienceTabEntity.getTitle());
        textView.setTextColor(liveAudienceTabEntity.isSelected() ? -1 : -1711276033);
        if (!liveAudienceTabEntity.isSelected() && liveAudienceTabEntity.isShowRedDot()) {
            z = true;
        }
        baseViewHolder.setGone(2131299488, z);
    }
}
