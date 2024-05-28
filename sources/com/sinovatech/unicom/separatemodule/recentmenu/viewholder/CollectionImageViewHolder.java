package com.sinovatech.unicom.separatemodule.recentmenu.viewholder;

import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinovatech.unicom.separatemodule.recentmenu.view.SwipeMenuLayout;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CollectionImageViewHolder extends RecyclerView.ViewHolder {
    public TextView mCk_Check;
    public LinearLayout mCk_CheckLayout;
    public ImageView mImg_Pic;
    public RelativeLayout mRlItemLayout;
    public SwipeMenuLayout mSl_HuaDong;
    public TextView mTv_Content;
    public TextView mTv_ShanChu;
    public TextView mTv_ShouCang;
    public TextView mTv_Title;

    public CollectionImageViewHolder(@NonNull View view) {
        super(view);
        this.mRlItemLayout = (RelativeLayout) view.findViewById(2131299893);
        this.mCk_Check = (TextView) view.findViewById(2131299888);
        this.mCk_CheckLayout = (LinearLayout) view.findViewById(2131299889);
        this.mImg_Pic = (ImageView) view.findViewById(2131299891);
        this.mTv_Title = (TextView) view.findViewById(2131299897);
        this.mTv_Content = (TextView) view.findViewById(2131299890);
        this.mTv_ShouCang = (TextView) view.findViewById(2131299895);
        this.mTv_ShanChu = (TextView) view.findViewById(2131299894);
        this.mSl_HuaDong = (SwipeMenuLayout) view.findViewById(2131299896);
    }
}
