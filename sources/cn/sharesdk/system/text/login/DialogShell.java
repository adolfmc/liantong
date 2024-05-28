package cn.sharesdk.system.text.login;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.sharesdk.framework.utils.SizeHelper;
import cn.sharesdk.system.text.login.utils.CommDialog;
import com.mob.tools.utils.ResHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.system.text.login.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DialogShell {

    /* renamed from: a */
    private static CommDialog f3042a;

    /* renamed from: b */
    private static Context f3043b;

    public DialogShell(Context context) {
        f3043b = context;
        f3042a = new CommDialog(context, 16973835);
    }

    /* renamed from: a */
    public void m21570a() {
        f3042a.dismiss();
    }

    /* renamed from: b */
    public Button m21566b() {
        int stringRes = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_close_identify_page");
        if (stringRes > 0) {
            f3042a.m21459b(stringRes);
        }
        int stringRes2 = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_btn_wait");
        if (stringRes2 > 0) {
            f3042a.m21456d(stringRes2);
        }
        int stringRes3 = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_btn_back");
        if (stringRes3 > 0) {
            f3042a.m21455e(stringRes3);
        }
        f3042a.show();
        return f3042a.m21460b();
    }

    /* renamed from: a */
    public Button m21567a(String str) {
        int stringRes = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_confirm_title");
        if (stringRes > 0) {
            f3042a.m21461a(stringRes);
        }
        int stringRes2 = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_confirm_des");
        if (stringRes2 > 0) {
            f3042a.m21459b(stringRes2);
        }
        int stringRes3 = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_btn_cancel");
        if (stringRes3 > 0) {
            f3042a.m21456d(stringRes3);
        }
        int stringRes4 = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_btn_ok");
        if (stringRes4 > 0) {
            f3042a.m21455e(stringRes4);
        }
        f3042a.show();
        TextView m21462a = f3042a.m21462a();
        if (m21462a != null) {
            m21462a.setText(m21462a.getText().toString() + str);
        }
        return f3042a.m21460b();
    }

    /* renamed from: c */
    public Button m21565c() {
        int stringRes = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_smart_title");
        if (stringRes > 0) {
            f3042a.m21461a(stringRes);
        }
        int stringRes2 = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_smart_dec");
        if (stringRes2 > 0) {
            f3042a.m21459b(stringRes2);
        }
        int stringRes3 = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_btn_cancel");
        if (stringRes3 > 0) {
            f3042a.m21456d(stringRes3);
        }
        int stringRes4 = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_btn_login");
        if (stringRes4 > 0) {
            f3042a.m21455e(stringRes4);
        }
        f3042a.show();
        return f3042a.m21460b();
    }

    /* renamed from: d */
    public void m21564d() {
        int stringRes = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_error_title");
        if (stringRes > 0) {
            f3042a.m21461a(stringRes);
        }
        int stringRes2 = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_error_des");
        if (stringRes2 > 0) {
            f3042a.m21459b(stringRes2);
        }
        int stringRes3 = ResHelper.getStringRes(f3043b, "ssdk_sms_dialog_btn_sure");
        if (stringRes3 > 0) {
            f3042a.m21457c(stringRes3);
        }
        f3042a.show();
    }

    /* renamed from: a */
    public CommDialog m21569a(int i, int i2) {
        if (i > 0) {
            f3042a.m21459b(i);
        }
        f3042a.show();
        new Handler().postDelayed(new Runnable() { // from class: cn.sharesdk.system.text.login.a.1
            @Override // java.lang.Runnable
            public void run() {
                DialogShell.f3042a.dismiss();
            }
        }, i2 * 1000);
        return f3042a;
    }

    /* renamed from: a */
    public static Dialog m21568a(Context context) {
        Dialog dialog = new Dialog(context, 16973835);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        linearLayout.setBackgroundColor(0);
        linearLayout.setOrientation(1);
        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        progressBar.setBackgroundColor(0);
        SizeHelper.m21680a(context);
        int m21679b = SizeHelper.m21679b(20);
        progressBar.setPadding(m21679b, m21679b, m21679b, m21679b);
        linearLayout.addView(progressBar);
        dialog.setContentView(linearLayout);
        return dialog;
    }
}
