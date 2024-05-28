package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.app.Dialog;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AudienceGoodsAdapter extends BaseAdapter {
    private AppCompatActivity activityContext;
    private Dialog dialog;
    private List<GoodListEntity> list;
    private OnDialogBtnClickListener listener;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public AudienceGoodsAdapter(AppCompatActivity appCompatActivity, List<GoodListEntity> list, Dialog dialog, OnDialogBtnClickListener onDialogBtnClickListener) {
        this.activityContext = appCompatActivity;
        this.list = list;
        this.listener = onDialogBtnClickListener;
        this.dialog = dialog;
    }

    public void update(List<GoodListEntity> list, OnDialogBtnClickListener onDialogBtnClickListener) {
        this.list = list;
        this.listener = onDialogBtnClickListener;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.list.size();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder3 myHolder3;
        if (view == null) {
            view = LayoutInflater.from(this.activityContext).inflate(2131492980, viewGroup, false);
            myHolder3 = new MyHolder3(view);
            view.setTag(myHolder3);
        } else {
            myHolder3 = (MyHolder3) view.getTag();
        }
        final GoodListEntity goodListEntity = this.list.get(i);
        TextView textView = myHolder3.redPointText;
        textView.setText((i + 1) + "");
        GlideApp.with((FragmentActivity) this.activityContext).load(goodListEntity.getCoverImgUrl()).into(myHolder3.goodsImage);
        myHolder3.goodsTilte.setText(goodListEntity.getName());
        myHolder3.goodsContent.setText(goodListEntity.getDesc());
        myHolder3.goodsPrice.setText(goodListEntity.getPrice());
        if (TextUtils.isEmpty(goodListEntity.getOriginalPrice())) {
            myHolder3.goodsOri.setVisibility(8);
        } else {
            myHolder3.goodsOri.setVisibility(0);
            myHolder3.goodsOri.setText(goodListEntity.getOriginalPrice());
            myHolder3.goodsOri.getPaint().setFlags(16);
        }
        myHolder3.goodsLook.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.adpter.-$$Lambda$AudienceGoodsAdapter$sM0anl2tNTpgWz88WQMQ3I9dPaQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AudienceGoodsAdapter.lambda$getView$0(AudienceGoodsAdapter.this, goodListEntity, view2);
            }
        });
        myHolder3.llItemBody.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.adpter.-$$Lambda$AudienceGoodsAdapter$t4S_wPQ2Fni9SCWnPLNAMZcKGoY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AudienceGoodsAdapter.lambda$getView$1(AudienceGoodsAdapter.this, goodListEntity, view2);
            }
        });
        return view;
    }

    public static /* synthetic */ void lambda$getView$0(AudienceGoodsAdapter audienceGoodsAdapter, GoodListEntity goodListEntity, View view) {
        OnDialogBtnClickListener onDialogBtnClickListener = audienceGoodsAdapter.listener;
        if (onDialogBtnClickListener != null) {
            onDialogBtnClickListener.onClickGoodsLook(goodListEntity);
        }
        if (UIUtils.isDismissDialog(audienceGoodsAdapter.activityContext, audienceGoodsAdapter.dialog)) {
            audienceGoodsAdapter.dialog.dismiss();
        }
    }

    public static /* synthetic */ void lambda$getView$1(AudienceGoodsAdapter audienceGoodsAdapter, GoodListEntity goodListEntity, View view) {
        OnDialogBtnClickListener onDialogBtnClickListener = audienceGoodsAdapter.listener;
        if (onDialogBtnClickListener != null) {
            onDialogBtnClickListener.onClickGoodsLook(goodListEntity);
        }
        if (UIUtils.isDismissDialog(audienceGoodsAdapter.activityContext, audienceGoodsAdapter.dialog)) {
            audienceGoodsAdapter.dialog.dismiss();
        }
        if (audienceGoodsAdapter.activityContext instanceof AudiencePlayBackActivity) {
            PvCurrencyLogUtils.pvLogLive("", 2, "", "商品列表(" + goodListEntity.getName() + "-" + goodListEntity.getGoodsUrl() + ")", "直播回放页", "", "直播回放页");
            return;
        }
        PvCurrencyLogUtils.pvLogLive("", 2, "", "商品列表(" + goodListEntity.getName() + "-" + goodListEntity.getGoodsUrl() + ")", "直播详情页", "", "5");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class MyHolder3 {
        TextView goodsContent;
        ImageView goodsImage;
        TextView goodsLook;
        TextView goodsOri;
        TextView goodsPrice;
        TextView goodsTilte;
        LinearLayout llItemBody;
        TextView redPointText;

        public MyHolder3(View view) {
            this.goodsImage = (ImageView) view.findViewById(2131296410);
            this.goodsTilte = (TextView) view.findViewById(2131296414);
            this.goodsContent = (TextView) view.findViewById(2131296409);
            this.goodsPrice = (TextView) view.findViewById(2131296412);
            this.goodsLook = (TextView) view.findViewById(2131296408);
            this.redPointText = (TextView) view.findViewById(2131296413);
            this.goodsOri = (TextView) view.findViewById(2131296411);
            this.llItemBody = (LinearLayout) view.findViewById(2131297729);
        }
    }
}
