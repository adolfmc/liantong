package com.sinovatech.unicom.separatemodule.playdetails.channel.adapter;

import android.support.annotation.Nullable;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import com.sinovatech.unicom.separatemodule.playdetails.channel.entity.ChannelEntity;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TouTiaoTwoAdapter extends BaseQuickAdapter<ChannelEntity.DataDTO, BaseViewHolder> {
    public TouTiaoTwoAdapter(int i, @Nullable @org.jetbrains.annotations.Nullable List<ChannelEntity.DataDTO> list) {
        super(i, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, ChannelEntity.DataDTO dataDTO) {
        dataDTO.getPageName();
        baseViewHolder.setText(2131298981, dataDTO.getPageName());
    }
}
