package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.app.Dialog;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.adpter.AudienceGoodsAdapter;
import com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AudienceGoodsDialog {
    private static AppCompatActivity activity;
    private static Dialog dialog;
    private static AudienceGoodsAdapter myAdapter;

    private static Dialog initDialog(final AppCompatActivity appCompatActivity, List<GoodListEntity> list, final OnDialogBtnClickListener onDialogBtnClickListener) {
        Dialog dialog2 = new Dialog(appCompatActivity, 2131952244);
        LinearLayout linearLayout = (LinearLayout) appCompatActivity.getLayoutInflater().inflate(2131492979, (ViewGroup) null);
        ((ImageView) linearLayout.findViewById(2131296415)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGoodsDialog$ethaRIeJ4Jf7XOBWyp6vdAO9bRY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceGoodsDialog.dismiss();
            }
        });
        linearLayout.findViewById(2131298095).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGoodsDialog$-Qh0FOnmvq6vIzRDeAFzdl-C3w4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceGoodsDialog.lambda$initDialog$1(OnDialogBtnClickListener.this, appCompatActivity, view);
            }
        });
        if (appCompatActivity instanceof AudiencePlayBackActivity) {
            PvCurrencyLogUtils.pvLogLive("", 3, "", "我的订单", "直播回放页", "", "直播回放页");
        } else {
            PvCurrencyLogUtils.pvLogLive("", 3, "", "我的订单", "直播详情页", "", "直播详情页");
        }
        myAdapter = new AudienceGoodsAdapter(appCompatActivity, list, dialog2, onDialogBtnClickListener);
        ((ListView) linearLayout.findViewById(2131296433)).setAdapter((ListAdapter) myAdapter);
        dialog2.setContentView(linearLayout);
        dialog2.setCancelable(true);
        dialog2.setCanceledOnTouchOutside(true);
        Window window = dialog2.getWindow();
        window.setWindowAnimations(2131952266);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = UIUtils.getScreenWidth((Activity) appCompatActivity);
        attributes.height = -2;
        window.setAttributes(attributes);
        return dialog2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initDialog$1(OnDialogBtnClickListener onDialogBtnClickListener, AppCompatActivity appCompatActivity, View view) {
        AppCompatActivity appCompatActivity2;
        if (onDialogBtnClickListener != null) {
            onDialogBtnClickListener.onClickOrder();
            appCompatActivity2 = appCompatActivity;
        } else {
            appCompatActivity2 = appCompatActivity;
        }
        if (appCompatActivity2 instanceof AudiencePlayBackActivity) {
            PvCurrencyLogUtils.pvLogLive("", 2, "", "我的订单", "直播回放页", "", "直播回放页");
        } else {
            PvCurrencyLogUtils.pvLogLive("", 2, "", "我的订单", "直播详情页", "", "直播详情页");
        }
    }

    public static void show(AppCompatActivity appCompatActivity, List<GoodListEntity> list, OnDialogBtnClickListener onDialogBtnClickListener) {
        activity = appCompatActivity;
        dialog = initDialog(appCompatActivity, list, onDialogBtnClickListener);
        if (UIUtils.isShowDialog(activity, dialog)) {
            dialog.show();
        }
    }

    public static void dismiss() {
        if (UIUtils.isDismissDialog(activity, dialog)) {
            dialog.dismiss();
        }
    }
}
