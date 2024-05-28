package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Dialog;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class OrderVideoRingResultDialog {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface setCallingOrder {
        void onClickOk();
    }

    public static void show(final AppCompatActivity appCompatActivity, boolean z, String str, String str2, String str3) {
        final Dialog dialog = new Dialog(appCompatActivity, 2131951850);
        LinearLayout linearLayout = (LinearLayout) appCompatActivity.getLayoutInflater().inflate(2131493291, (ViewGroup) null);
        ImageView imageView = (ImageView) linearLayout.findViewById(2131297375);
        TextView textView = (TextView) linearLayout.findViewById(2131298929);
        TextView textView2 = (TextView) linearLayout.findViewById(2131298927);
        TextView textView3 = (TextView) linearLayout.findViewById(2131298890);
        TextView textView4 = (TextView) linearLayout.findViewById(2131299119);
        final View findViewById = linearLayout.findViewById(2131298304);
        final FrameLayout frameLayout = (FrameLayout) linearLayout.findViewById(2131296981);
        imageView.setImageResource(z ? 2131231700 : 2131231671);
        textView.setVisibility(z ? 0 : 8);
        textView2.setText(str);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$OrderVideoRingResultDialog$VX0wxsvXgmovKbq8N6aMZ1ia_tk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        textView4.setText(str2);
        textView4.setVisibility(z ? 0 : 8);
        if (z && OptionValveUtil.INSTENCE.isShowOrderAD()) {
            AdConfigEntity adConfigEntity = new AdConfigEntity();
            adConfigEntity.setAdType("PANGLE");
            adConfigEntity.setCodeId(TextUtils.isEmpty(str3) ? "946709155" : "946909427");
            adConfigEntity.setBannerWidth(302);
            adConfigEntity.setScale(0.85d);
            AdFactory.getAd(appCompatActivity, adConfigEntity).loadBanner(new IAdInterface.IBannerAdCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.view.OrderVideoRingResultDialog.1
                @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IBannerAdCallBack
                public void onResult(int i, View view) {
                    UIUtils.logD("xcy", "code=" + i);
                    if (view != null) {
                        try {
                            findViewById.setVisibility(0);
                            ViewGroup viewGroup = (ViewGroup) view.getParent();
                            if (viewGroup != null) {
                                viewGroup.removeAllViews();
                            }
                            frameLayout.removeAllViews();
                            frameLayout.addView(view);
                            TextView textView5 = new TextView(frameLayout.getContext());
                            textView5.setText("广告由第三方合作提供");
                            textView5.setTextColor(-6710887);
                            textView5.setTextSize(8.0f);
                            textView5.setGravity(5);
                            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
                            layoutParams.gravity = 80;
                            layoutParams.topMargin = UIUtils.dip2px(appCompatActivity, 5.0f);
                            frameLayout.addView(textView5, layoutParams);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IBannerAdCallBack
                public void onClose() {
                    findViewById.setVisibility(8);
                }
            });
        }
        dialog.setContentView(linearLayout);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setWindowAnimations(2131952266);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -2;
        attributes.height = -2;
        window.setAttributes(attributes);
        dialog.show();
    }
}
