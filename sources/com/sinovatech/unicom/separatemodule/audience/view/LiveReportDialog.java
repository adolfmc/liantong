package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Dialog;
import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinovatech.unicom.separatemodule.audience.report.LiveReportActivity;
import com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LiveReportDialog {
    public static void show(final AppCompatActivity appCompatActivity, String str, String str2, final int i) {
        final Dialog dialog = new Dialog(appCompatActivity, 2131951850);
        LinearLayout linearLayout = (LinearLayout) appCompatActivity.getLayoutInflater().inflate(2131493287, (ViewGroup) null);
        ((TextView) linearLayout.findViewById(2131299027)).setText(str.trim());
        ((TextView) linearLayout.findViewById(2131298907)).setText("聊天内容：" + str2 + "\n");
        linearLayout.findViewById(2131298905).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$LiveReportDialog$sZldZ8xpgqb209FaJPWj8aNkuHw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        linearLayout.findViewById(2131299031).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$LiveReportDialog$dJcog74ZUCsj7SLz1PME0Ra7-fY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LiveReportDialog.lambda$show$1(dialog, appCompatActivity, i, view);
            }
        });
        dialog.setContentView(linearLayout);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$1(Dialog dialog, AppCompatActivity appCompatActivity, int i, View view) {
        dialog.dismiss();
        Intent intent = new Intent(appCompatActivity, LiveReportActivity.class);
        intent.putExtra("chatDelPosition", i);
        appCompatActivity.startActivityForResult(intent, VideoCenterActivity.CHANNEL_CODE);
    }
}
