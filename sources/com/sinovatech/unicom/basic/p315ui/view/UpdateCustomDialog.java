package com.sinovatech.unicom.basic.p315ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.view.UpdateCustomDialog */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UpdateCustomDialog {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.UpdateCustomDialog$UpdateCustomDialogListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface UpdateCustomDialogListener {
        void onClickCancel();

        void onClickOk();
    }

    public static void show(Activity activity, final String str, String str2, Bitmap bitmap, String str3, String str4, boolean z, final UpdateCustomDialogListener updateCustomDialogListener) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.width = (int) (i * 0.8d);
            attributes.height = (attributes.width * bitmap.getHeight()) / bitmap.getWidth();
            Window window = dialog.getWindow();
            window.setAttributes(attributes);
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = activity.getLayoutInflater().inflate(2131493504, (ViewGroup) null);
            UIUtils.setGJR(TextUtils.equals(MainTabView.currentTab, MainTabView.TABTAG_HOME), inflate);
            ((ImageView) inflate.findViewById(2131296780)).setImageBitmap(bitmap);
            final SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
            final String str5 = str2 + str;
            ImageView imageView = (ImageView) inflate.findViewById(2131296787);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.view.UpdateCustomDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if ("3".equals(str)) {
                        SharePreferenceUtil sharePreferenceUtil2 = sharePreferenceUtil;
                        String str6 = str5;
                        sharePreferenceUtil2.putString(str6, System.currentTimeMillis() + "");
                    }
                    dialog.cancel();
                    updateCustomDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            ImageView imageView2 = (ImageView) inflate.findViewById(2131296781);
            if (z) {
                imageView2.setVisibility(0);
                imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.view.-$$Lambda$UpdateCustomDialog$YMxV4-0gPJLEwVZ9u41fAu9AIIw
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UpdateCustomDialog.lambda$show$0(str, sharePreferenceUtil, str5, dialog, updateCustomDialogListener, view);
                    }
                });
            } else {
                imageView2.setVisibility(4);
            }
            GlideApp.with(activity).load(str3).into(imageView);
            GlideApp.with(activity).load(str4).into(imageView2);
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$0(String str, SharePreferenceUtil sharePreferenceUtil, String str2, Dialog dialog, UpdateCustomDialogListener updateCustomDialogListener, View view) {
        if ("3".equals(str) || "1".equals(str)) {
            sharePreferenceUtil.putString(str2, System.currentTimeMillis() + "");
        }
        dialog.cancel();
        if (updateCustomDialogListener != null) {
            updateCustomDialogListener.onClickCancel();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00d4 A[Catch: Exception -> 0x00f7, TryCatch #0 {Exception -> 0x00f7, blocks: (B:2:0x0000, B:4:0x00b3, B:7:0x00bc, B:9:0x00c9, B:11:0x00d4, B:13:0x00ea, B:12:0x00e6, B:8:0x00c3), top: B:18:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00e6 A[Catch: Exception -> 0x00f7, TryCatch #0 {Exception -> 0x00f7, blocks: (B:2:0x0000, B:4:0x00b3, B:7:0x00bc, B:9:0x00c9, B:11:0x00d4, B:13:0x00ea, B:12:0x00e6, B:8:0x00c3), top: B:18:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void Newshow(android.app.Activity r9, final java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, boolean r14, final com.sinovatech.unicom.basic.p315ui.view.UpdateCustomDialog.UpdateCustomDialogListener r15) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.view.UpdateCustomDialog.Newshow(android.app.Activity, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, com.sinovatech.unicom.basic.ui.view.UpdateCustomDialog$UpdateCustomDialogListener):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$Newshow$1(String str, SharePreferenceUtil sharePreferenceUtil, String str2, Dialog dialog, UpdateCustomDialogListener updateCustomDialogListener, View view) {
        if ("3".equals(str) || "1".equals(str)) {
            sharePreferenceUtil.putString(str2, System.currentTimeMillis() + "");
        }
        dialog.cancel();
        if (updateCustomDialogListener != null) {
            updateCustomDialogListener.onClickCancel();
        }
    }
}
