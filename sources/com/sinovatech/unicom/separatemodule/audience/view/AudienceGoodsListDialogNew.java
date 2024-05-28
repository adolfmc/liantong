package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceUser;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsListDialogNew;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AudienceGoodsListDialogNew {
    private static AudienceGoodsListDialogNew audienceGoodsListDialog;
    private AppCompatActivity activityContext;
    private AudienceUser audienceUser;
    private String jobNumber;
    private OnExplainListener listener;
    private onLandscapeListener listener1;
    private RecyclerView listview;
    private MyAdapter myAdapter;
    private PopupWindow popupWindow;
    private Dialog shareDialog;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnExplainListener {
        void sendMsg(String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface onLandscapeListener {
        void sendMsg();
    }

    public static AudienceGoodsListDialogNew getInstance() {
        if (audienceGoodsListDialog == null) {
            audienceGoodsListDialog = new AudienceGoodsListDialogNew();
        }
        return audienceGoodsListDialog;
    }

    public void initLandscape(final AppCompatActivity appCompatActivity, List<GoodListEntity> list, String str, View view) {
        this.activityContext = appCompatActivity;
        this.jobNumber = str;
        LinearLayout linearLayout = (LinearLayout) appCompatActivity.getLayoutInflater().inflate(2131492978, (ViewGroup) null);
        if (this.popupWindow == null) {
            this.popupWindow = new PopupWindow((View) linearLayout, UIUtils.dip2px(appCompatActivity, 257.0f), UIUtils.dip2px(appCompatActivity, 156.0f), false);
            this.popupWindow.setFocusable(true);
            this.popupWindow.setOutsideTouchable(true);
        }
        ((ImageView) linearLayout.findViewById(2131297566)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGoodsListDialogNew$QvkdvQEx-WxBeSxj-8GyIWJJkaI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AudienceGoodsListDialogNew.this.popupWindow.dismiss();
            }
        });
        linearLayout.findViewById(2131297567).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGoodsListDialogNew$rFdFx7hmEYZ31pm5npkzpSR_WnE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AudienceGoodsListDialogNew.lambda$initLandscape$1(AudienceGoodsListDialogNew.this, appCompatActivity, view2);
            }
        });
        RecyclerView recyclerView = (RecyclerView) linearLayout.findViewById(2131298404);
        recyclerView.setLayoutManager(new LinearLayoutManager(appCompatActivity));
        this.myAdapter = new MyAdapter(appCompatActivity, list, true);
        recyclerView.setAdapter(this.myAdapter);
        this.popupWindow.showAsDropDown(view, -20, 0, 80);
        this.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGoodsListDialogNew$W_R6Q7j88mkZIunevHcFwaK1huU
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                AudienceGoodsListDialogNew.lambda$initLandscape$2(AudienceGoodsListDialogNew.this);
            }
        });
    }

    public static /* synthetic */ void lambda$initLandscape$1(AudienceGoodsListDialogNew audienceGoodsListDialogNew, AppCompatActivity appCompatActivity, View view) {
        audienceGoodsListDialogNew.popupWindow.dismiss();
        AudienceActivity.IntentGo(appCompatActivity, URLSet.liveOrderMenu());
    }

    public static /* synthetic */ void lambda$initLandscape$2(AudienceGoodsListDialogNew audienceGoodsListDialogNew) {
        for (GoodListEntity goodListEntity : audienceGoodsListDialogNew.myAdapter.list) {
            goodListEntity.setExplain(false);
        }
        audienceGoodsListDialogNew.myAdapter.notifyDataSetChanged();
    }

    public void init(final AppCompatActivity appCompatActivity, List<GoodListEntity> list, String str) {
        this.activityContext = appCompatActivity;
        this.jobNumber = str;
        this.shareDialog = new Dialog(appCompatActivity, 2131952070);
        LinearLayout linearLayout = (LinearLayout) appCompatActivity.getLayoutInflater().inflate(2131492982, (ViewGroup) null);
        ((ImageView) linearLayout.findViewById(2131296415)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGoodsListDialogNew$yDGRdefp5CcMhTyvMRqFb4ohyko
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceGoodsListDialogNew.this.shareDialog.dismiss();
            }
        });
        linearLayout.findViewById(2131299020).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGoodsListDialogNew$eutYUDuFO55YIZ5iSuB8rd12UDE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceGoodsListDialogNew.lambda$init$4(AppCompatActivity.this, view);
            }
        });
        boolean z = appCompatActivity instanceof AudiencePlayBackActivity;
        if (z) {
            PvCurrencyLogUtils.pvLogLive("", 3, "", "我的订单", "直播回放页", "", "直播回放页");
        } else {
            PvCurrencyLogUtils.pvLogLive("", 3, "", "我的订单", "直播详情页", "", "直播详情页");
        }
        this.listview = (RecyclerView) linearLayout.findViewById(2131298399);
        this.listview.setLayoutManager(new LinearLayoutManager(appCompatActivity));
        this.myAdapter = new MyAdapter(appCompatActivity, list);
        this.listview.setAdapter(this.myAdapter);
        if (z) {
            PvCurrencyLogUtils.pvLogLive("", 3, "", "商品列表(" + list.get(0).getName() + "-" + list.get(0).getGoodsUrl() + ")", "直播回放页", "", "直播回放页");
        } else {
            PvCurrencyLogUtils.pvLogLive("", 3, "", "商品列表(" + list.get(0).getName() + "-" + list.get(0).getGoodsUrl() + ")", "直播详情页", "", "直播详情页");
        }
        this.shareDialog.setContentView(linearLayout);
        this.shareDialog.setCancelable(true);
        this.shareDialog.setCanceledOnTouchOutside(true);
        Window window = this.shareDialog.getWindow();
        window.setWindowAnimations(2131952266);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = UIUtils.getScreenWidth((Activity) appCompatActivity);
        attributes.height = -2;
        window.setAttributes(attributes);
        this.shareDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGoodsListDialogNew$kar2lAwEnlkMh1EF8PR1y2R-IPo
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                AudienceGoodsListDialogNew.lambda$init$5(AudienceGoodsListDialogNew.this, dialogInterface);
            }
        });
        this.shareDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$init$4(AppCompatActivity appCompatActivity, View view) {
        AudienceActivity.IntentGo(appCompatActivity, URLSet.liveOrderMenu());
        if (appCompatActivity instanceof AudiencePlayBackActivity) {
            PvCurrencyLogUtils.pvLogLive("", 2, "", "我的订单", "直播回放页", "", "直播回放页");
        } else {
            PvCurrencyLogUtils.pvLogLive("", 2, "", "我的订单", "直播详情页", "", "直播详情页");
        }
    }

    public static /* synthetic */ void lambda$init$5(AudienceGoodsListDialogNew audienceGoodsListDialogNew, DialogInterface dialogInterface) {
        for (GoodListEntity goodListEntity : audienceGoodsListDialogNew.myAdapter.list) {
            goodListEntity.setExplain(false);
        }
        audienceGoodsListDialogNew.myAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        private AppCompatActivity activityContext;
        private boolean isLandScape;
        private List<GoodListEntity> list;

        public MyAdapter(AppCompatActivity appCompatActivity, List<GoodListEntity> list) {
            this.activityContext = appCompatActivity;
            this.list = list;
        }

        public MyAdapter(AppCompatActivity appCompatActivity, List<GoodListEntity> list, boolean z) {
            this.activityContext = appCompatActivity;
            this.list = list;
            this.isLandScape = z;
        }

        public void update(List<GoodListEntity> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View inflate;
            if (this.isLandScape) {
                inflate = LayoutInflater.from(this.activityContext).inflate(2131492991, viewGroup, false);
            } else {
                inflate = LayoutInflater.from(this.activityContext).inflate(2131492981, viewGroup, false);
            }
            return new MyHolder(inflate);
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
            final GoodListEntity goodListEntity = this.list.get(i);
            GlideApp.with((FragmentActivity) this.activityContext).load(goodListEntity.getCoverImgUrl()).into(myHolder.goodsImage);
            myHolder.goodsTilte.setText(goodListEntity.getName());
            myHolder.goodsPrice.setText(goodListEntity.getPrice());
            myHolder.tvBtnEnd.setVisibility(goodListEntity.isExplain() ? 0 : 8);
            myHolder.tvBtnStart.setVisibility(goodListEntity.isExplain() ? 8 : 0);
            TextView textView = myHolder.llTips;
            textView.setText("" + (i + 1));
            myHolder.tvDesc.setText(goodListEntity.getDesc());
            myHolder.tvOriginalPrice.setText(goodListEntity.getOriginalPrice());
            myHolder.tvOriginalPrice.getPaint().setFlags(16);
            myHolder.tvBtnLook.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGoodsListDialogNew$MyAdapter$8Q-JNrCeqrP0Pmk6MkRO2aBz0jc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceGoodsListDialogNew.MyAdapter.lambda$onBindViewHolder$0(AudienceGoodsListDialogNew.MyAdapter.this, goodListEntity, view);
                }
            });
            myHolder.llItemBody.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGoodsListDialogNew$MyAdapter$YmNWKqReWWVKdaTT08jOYi5iqu8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceGoodsListDialogNew.MyAdapter.lambda$onBindViewHolder$1(AudienceGoodsListDialogNew.MyAdapter.this, goodListEntity, view);
                }
            });
            myHolder.tvBtnStart.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGoodsListDialogNew$MyAdapter$6-sfayF7rJ4NgDQQAkEd2lzeHlA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceGoodsListDialogNew.MyAdapter.lambda$onBindViewHolder$2(AudienceGoodsListDialogNew.MyAdapter.this, goodListEntity, i, view);
                }
            });
        }

        public static /* synthetic */ void lambda$onBindViewHolder$0(MyAdapter myAdapter, GoodListEntity goodListEntity, View view) {
            AudienceActivity.IntentGo(myAdapter.activityContext, goodListEntity.getGoodsUrl(), "0", goodListEntity.getId(), 4000);
            if (UIUtils.isDismissDialog(myAdapter.activityContext, AudienceGoodsListDialogNew.this.shareDialog)) {
                AudienceGoodsListDialogNew.this.shareDialog.dismiss();
            }
            if (AudienceGoodsListDialogNew.this.popupWindow != null) {
                AudienceGoodsListDialogNew.this.popupWindow.dismiss();
            }
        }

        public static /* synthetic */ void lambda$onBindViewHolder$1(MyAdapter myAdapter, GoodListEntity goodListEntity, View view) {
            AudienceActivity.IntentGo(myAdapter.activityContext, goodListEntity.getGoodsUrl(), "0", goodListEntity.getId(), 4000);
            if (UIUtils.isDismissDialog(myAdapter.activityContext, AudienceGoodsListDialogNew.this.shareDialog)) {
                AudienceGoodsListDialogNew.this.shareDialog.dismiss();
            }
            if (AudienceGoodsListDialogNew.this.popupWindow != null) {
                AudienceGoodsListDialogNew.this.popupWindow.dismiss();
            }
            if (myAdapter.activityContext instanceof AudiencePlayBackActivity) {
                PvCurrencyLogUtils.pvLogLive("", 2, "", "商品列表(" + goodListEntity.getName() + "-" + goodListEntity.getGoodsUrl() + ")", "直播回放页", "", "直播回放页");
                return;
            }
            PvCurrencyLogUtils.pvLogLive("", 2, "", "商品列表(" + goodListEntity.getName() + "-" + goodListEntity.getGoodsUrl() + ")", "直播详情页", "", "直播详情页");
        }

        public static /* synthetic */ void lambda$onBindViewHolder$2(MyAdapter myAdapter, GoodListEntity goodListEntity, int i, View view) {
            AudienceGoodsListDialogNew audienceGoodsListDialogNew = AudienceGoodsListDialogNew.this;
            audienceGoodsListDialogNew.startExplain(audienceGoodsListDialogNew.jobNumber, goodListEntity, i);
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.list.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView goodsImage;
        TextView goodsPrice;
        TextView goodsTilte;
        LinearLayout llItemBody;
        TextView llTips;
        TextView tvBtnEnd;
        TextView tvBtnLook;
        TextView tvBtnStart;
        TextView tvDesc;
        TextView tvOriginalPrice;

        public MyHolder(View view) {
            super(view);
            this.goodsImage = (ImageView) view.findViewById(2131296410);
            this.goodsTilte = (TextView) view.findViewById(2131296414);
            this.goodsPrice = (TextView) view.findViewById(2131296412);
            this.tvBtnLook = (TextView) view.findViewById(2131298901);
            this.tvBtnEnd = (TextView) view.findViewById(2131298898);
            this.tvBtnStart = (TextView) view.findViewById(2131298904);
            this.llTips = (TextView) view.findViewById(2131297784);
            this.tvOriginalPrice = (TextView) view.findViewById(2131299037);
            this.tvDesc = (TextView) view.findViewById(2131298925);
            this.llItemBody = (LinearLayout) view.findViewById(2131297729);
        }
    }

    public void show() {
        Dialog dialog = this.shareDialog;
        if (dialog != null) {
            dialog.show();
        }
    }

    public AudienceGoodsListDialogNew update(List<GoodListEntity> list) {
        MyAdapter myAdapter = this.myAdapter;
        if (myAdapter != null) {
            myAdapter.update(list);
        }
        return this;
    }

    public void releaseDialog() {
        if (UIUtils.isDismissDialog(this.activityContext, this.shareDialog)) {
            this.shareDialog.dismiss();
        }
        audienceGoodsListDialog = null;
    }

    public void releasePw() {
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
            this.popupWindow = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startExplain(String str, final GoodListEntity goodListEntity, final int i) {
        App.getAsyncHttpClient().get(URLSet.explainGoods(str, goodListEntity.getId()), new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsListDialogNew.1
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i2, String str2) {
                super.onSuccess(i2, str2);
                goodListEntity.setExplain(true);
                AudienceGoodsListDialogNew.this.myAdapter.notifyItemChanged(i);
                if (AudienceGoodsListDialogNew.this.listener != null) {
                    AudienceGoodsListDialogNew.this.listener.sendMsg(goodListEntity.getName());
                }
                if (AudienceGoodsListDialogNew.this.listener1 != null) {
                    AudienceGoodsListDialogNew.this.listener1.sendMsg();
                }
            }
        });
    }

    public String getGoodsInfoById(String str) {
        for (GoodListEntity goodListEntity : this.myAdapter.list) {
            if (str.equals(goodListEntity.getId())) {
                return goodListEntity.getName();
            }
        }
        return "";
    }

    public void setListener(OnExplainListener onExplainListener) {
        this.listener = onExplainListener;
    }

    public void setOnClick(onLandscapeListener onlandscapelistener) {
        this.listener1 = onlandscapelistener;
    }
}
