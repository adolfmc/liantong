package com.megvii.lv5;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.megvii.lv5.sdk.C5559R;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.q2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5540q2 {

    /* renamed from: a */
    public Activity f13193a;

    public C5540q2(Activity activity) {
        this.f13193a = activity;
    }

    /* renamed from: a */
    public AlertDialog m13172a(boolean z, View.OnClickListener onClickListener) {
        View inflate = View.inflate(this.f13193a, C5559R.C5563layout.megvii_liveness_dialog3, null);
        TextView textView = (TextView) inflate.findViewById(C5559R.C5562id.tv_megvii_dialog_title0);
        TextView textView2 = (TextView) inflate.findViewById(C5559R.C5562id.tv_megvii_dialog_title);
        TextView textView3 = (TextView) inflate.findViewById(C5559R.C5562id.tv_megvii_dialog_left);
        textView3.setOnClickListener(onClickListener);
        TextView textView4 = (TextView) inflate.findViewById(C5559R.C5562id.tv_megvii_dialog_right);
        textView4.setOnClickListener(onClickListener);
        textView.setText(this.f13193a.getResources().getString(C5667z2.m12879a(this.f13193a).m12875d(this.f13193a.getResources().getString(C5559R.string.key_liveness_exit_titlePrompt_text0))));
        textView.setTextSize(0, this.f13193a.getResources().getDimensionPixelSize(C5559R.dimen.meglive_liveness_exit_titlePrompt_size0));
        textView2.setText(this.f13193a.getResources().getString(C5667z2.m12879a(this.f13193a).m12875d(this.f13193a.getResources().getString(C5559R.string.key_liveness_exit_titlePrompt_text))));
        textView2.setTextSize(0, this.f13193a.getResources().getDimensionPixelSize(C5559R.dimen.meglive_liveness_exit_titlePrompt_size));
        textView3.setTextColor(this.f13193a.getResources().getColor(C5667z2.m12879a(this.f13193a).m12878a(this.f13193a.getResources().getString(C5559R.string.key_liveness_exit_leftPrompt_color))));
        textView4.setTextColor(this.f13193a.getResources().getColor(C5667z2.m12879a(this.f13193a).m12878a(this.f13193a.getResources().getString(C5559R.string.key_liveness_exit_rightPrompt_color))));
        if (z) {
            int m12875d = C5667z2.m12879a(this.f13193a).m12875d(this.f13193a.getResources().getString(C5559R.string.key_liveness_home_exit_popupwindow_cancel_text));
            int m12875d2 = C5667z2.m12879a(this.f13193a).m12875d(this.f13193a.getResources().getString(C5559R.string.key_liveness_home_exit_popupwindow_sure_text));
            textView3.setText(this.f13193a.getResources().getString(m12875d));
            textView4.setText(this.f13193a.getResources().getString(m12875d2));
        }
        AlertDialog create = new AlertDialog.Builder(this.f13193a).setCancelable(false).create();
        create.show();
        create.getWindow().setContentView(inflate);
        create.getWindow().setBackgroundDrawableResource(17170445);
        WindowManager.LayoutParams attributes = create.getWindow().getAttributes();
        attributes.width = (((WindowManager) this.f13193a.getSystemService("window")).getDefaultDisplay().getWidth() * 3) / 4;
        attributes.height = -2;
        create.getWindow().setAttributes(attributes);
        return create;
    }
}
