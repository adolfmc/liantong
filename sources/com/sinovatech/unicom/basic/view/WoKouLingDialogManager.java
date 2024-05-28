package com.sinovatech.unicom.basic.view;

import android.app.Activity;
import android.app.Dialog;
import android.support.p086v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.share.ImageMergeUtil;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectConfig;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import java.net.URLEncoder;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WoKouLingDialogManager {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CopyDialogListener {
        void onCancel();
    }

    public static void show(final Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, final CopyDialogListener copyDialogListener) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            activity.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            Window window = dialog.getWindow();
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = activity.getLayoutInflater().inflate(2131493432, (ViewGroup) null);
            ((CardView) inflate.findViewById(2131298586)).getLayoutParams();
            ImageView imageView = (ImageView) inflate.findViewById(2131298587);
            ImageView imageView2 = (ImageView) inflate.findViewById(2131298590);
            ImageView imageView3 = (ImageView) inflate.findViewById(2131298589);
            TextView textView = (TextView) inflate.findViewById(2131298591);
            imageView2.getLayoutParams();
            Button button = (Button) inflate.findViewById(2131298592);
            ((TextView) inflate.findViewById(2131298588)).setText(str4);
            if (!TextUtils.isEmpty(str9)) {
                textView.setText(Html.fromHtml("<font color='#343434'>" + str9 + "</font><font color='#9A9A9A'>给你分享了</font>"));
            } else {
                textView.setText(Html.fromHtml("<font color='#9A9A9A'>给你分享了</font>"));
            }
            ShowImageUtils.showImageView(activity, str8, 2131232482, imageView3);
            if (!TextUtils.isEmpty(str7)) {
                if (!str7.startsWith("http://") && !str7.startsWith("https://")) {
                    ShowImageUtils.showImageView(activity, ImageMergeUtil.stringtoBitmap(str7), imageView2);
                }
                ShowImageUtils.showImageView(activity, str7, imageView2);
            }
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.WoKouLingDialogManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.dismiss();
                    CopyDialogListener copyDialogListener2 = copyDialogListener;
                    if (copyDialogListener2 != null) {
                        copyDialogListener2.onCancel();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            try {
                String str11 = str6.contains("?") ? "&" : "?";
                if (str6.contains("openUrl=")) {
                    String[] split = str6.split("openUrl=");
                    if (split.length > 0) {
                        String str12 = split[split.length - 1];
                        if (!TextUtils.isEmpty(str12)) {
                            str11 = str12.contains("?") ? "&" : "?";
                        }
                    }
                }
                final String str13 = str6 + str11 + "linkType=unicomNewShare&mobileA=" + str + "&mobileB=" + str2 + "&businessName=" + URLEncoder.encode(str3, "UTF-8") + "&businessCode=" + str5 + "&woCommand=" + str10 + "&shareType=1";
                button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.WoKouLingDialogManager.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        UnicomCollectManager unicomCollectManager = UnicomCollectManager.getInstance();
                        unicomCollectManager.setTransId("S2ndpage1172" + CollectConfig.montageTag1 + "沃口令");
                        IntentManager.gotoWebViewActivity(activity, str13, "");
                        dialog.dismiss();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } catch (Exception unused) {
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
    }
}
