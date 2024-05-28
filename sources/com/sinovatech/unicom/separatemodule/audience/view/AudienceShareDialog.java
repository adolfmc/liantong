package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.app.Dialog;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AudienceShareDialog {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$1(View view) {
    }

    public static void show(AppCompatActivity appCompatActivity, List<GoodListEntity> list) {
        final Dialog dialog = new Dialog(appCompatActivity, 2131952244);
        LinearLayout linearLayout = (LinearLayout) appCompatActivity.getLayoutInflater().inflate(2131492979, (ViewGroup) null);
        ((ImageView) linearLayout.findViewById(2131296415)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceShareDialog$NB4plJn4Q_x7KeWI1tOp7m6zUX4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(2131296416);
        linearLayout2.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            View inflate = LayoutInflater.from(appCompatActivity).inflate(2131492980, (ViewGroup) null);
            GoodListEntity goodListEntity = list.get(i);
            GlideApp.with((FragmentActivity) appCompatActivity).load(goodListEntity.getCoverImgUrl()).into((ImageView) inflate.findViewById(2131296410));
            ((TextView) inflate.findViewById(2131296414)).setText(goodListEntity.getName());
            ((TextView) inflate.findViewById(2131296409)).setText(goodListEntity.getDesc());
            ((TextView) inflate.findViewById(2131296412)).setText(goodListEntity.getPrice());
            ((TextView) inflate.findViewById(2131296408)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceShareDialog$_-abYlD85qQM5LfiJblip9Q0DW4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceShareDialog.lambda$show$1(view);
                }
            });
            linearLayout2.addView(inflate);
        }
        dialog.setContentView(linearLayout);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setWindowAnimations(2131952266);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = UIUtils.getScreenWidth((Activity) appCompatActivity);
        attributes.height = -2;
        window.setAttributes(attributes);
        dialog.show();
    }
}
