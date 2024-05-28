package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinSMSDialog;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AnquanzhongxinSMSDialog {
    private static Dialog shareDialog;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface AnquanzhongxinSMSListener {
        void onDissMiss();

        void sendSms();

        void submmit(String str, TextView textView);
    }

    public static void showDialog(Activity activity, final AnquanzhongxinSMSListener anquanzhongxinSMSListener) {
        shareDialog = new Dialog(activity, 2131952244);
        LinearLayout linearLayout = (LinearLayout) activity.getLayoutInflater().inflate(2131492966, (ViewGroup) null);
        TextView textView = (TextView) linearLayout.findViewById(2131296357);
        final TextView textView2 = (TextView) linearLayout.findViewById(2131296341);
        final Button button = (Button) linearLayout.findViewById(2131297927);
        ImageView imageView = (ImageView) linearLayout.findViewById(2131296343);
        final EditText editText = (EditText) linearLayout.findViewById(2131296359);
        final TextView textView3 = (TextView) linearLayout.findViewById(2131296342);
        textView3.setVisibility(8);
        button.setEnabled(false);
        button.setBackgroundResource(2131231880);
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        if (!TextUtils.isEmpty(currentPhoneNumber)) {
            StringBuilder sb = new StringBuilder(currentPhoneNumber);
            if (currentPhoneNumber.length() >= 11) {
                int length = (currentPhoneNumber.length() - 4) / 2;
                sb.replace(length, length + 4, "****");
            } else if (currentPhoneNumber.length() >= 6) {
                int length2 = (currentPhoneNumber.length() - 3) / 2;
                sb.replace(length2, length2 + 3, "***");
            }
            textView.setText(sb.toString());
        }
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        editText.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinSMSDialog.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editText.getText().toString().length() >= 1) {
                    button.setEnabled(true);
                    button.setBackgroundResource(2131231879);
                    return;
                }
                button.setEnabled(false);
                button.setBackgroundResource(2131231880);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinSMSDialog$rBANk6s2u0yEnWbITU_uGeglLJg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AnquanzhongxinSMSDialog.lambda$showDialog$0(textView2, anquanzhongxinSMSListener, view);
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinSMSDialog$2KkDDgoDQJcHRqGK7gs7RPuExuE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AnquanzhongxinSMSDialog.AnquanzhongxinSMSListener.this.submmit(editText.getText().toString(), textView3);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinSMSDialog$zyUJ8sKOaKYOddlC4wUdIVRRJNA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AnquanzhongxinSMSDialog.shareDialog.dismiss();
            }
        });
        shareDialog.setContentView(linearLayout);
        shareDialog.setCancelable(true);
        shareDialog.setCanceledOnTouchOutside(true);
        Window window = shareDialog.getWindow();
        window.setWindowAnimations(2131952266);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = UIUtils.getScreenWidth(activity);
        attributes.height = -2;
        window.setAttributes(attributes);
        shareDialog.show();
        shareDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinSMSDialog.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                AnquanzhongxinSMSListener.this.onDissMiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showDialog$0(final TextView textView, AnquanzhongxinSMSListener anquanzhongxinSMSListener, View view) {
        textView.setEnabled(false);
        new CountDownTimer(60200L, 1000L) { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinSMSDialog.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                TextView textView2 = textView;
                textView2.setText(((j + 20) / 1000) + "s");
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                textView.setText("获取验证码");
                textView.setEnabled(true);
                textView.setTextColor(-1703896);
            }
        }.start();
        anquanzhongxinSMSListener.sendSms();
    }

    public static void dissMiss() {
        Dialog dialog = shareDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
