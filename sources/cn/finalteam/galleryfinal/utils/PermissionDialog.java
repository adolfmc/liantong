package cn.finalteam.galleryfinal.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.finalteam.galleryfinal.C1656R;
import com.p284qw.soul.permission.SoulPermission;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PermissionDialog {
    private static final String TAG = "PermissionDialog";
    private static Activity activityContext;
    private static Handler handler;
    private static boolean isShow;
    private static Dialog screenshotDialog;

    static /* synthetic */ int access$300() {
        return getScreenWidth();
    }

    public static void show(String str) {
        try {
            isShow = true;
            activityContext = SoulPermission.getInstance().getTopActivity();
            if (activityContext != null && !activityContext.isFinishing() && screenshotDialog != null && screenshotDialog.isShowing()) {
                screenshotDialog.dismiss();
            }
            screenshotDialog = new Dialog(activityContext, C1656R.C1662style.permissiondialog);
            WindowManager.LayoutParams attributes = screenshotDialog.getWindow().getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            Window window = screenshotDialog.getWindow();
            window.setWindowAnimations(C1656R.C1662style.permissiondialog_dialog_animation2);
            window.setGravity(48);
            window.setAttributes(attributes);
            View inflate = activityContext.getLayoutInflater().inflate(C1656R.C1660layout.permission_dialog_layout, (ViewGroup) null);
            ((TextView) ((LinearLayout) inflate.findViewById(C1656R.C1659id.permission_rootlayout)).findViewById(C1656R.C1659id.permission_content)).setText(str);
            screenshotDialog.setContentView(inflate);
            screenshotDialog.setCancelable(true);
            screenshotDialog.setCanceledOnTouchOutside(true);
            screenshotDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: cn.finalteam.galleryfinal.utils.PermissionDialog.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    dialogInterface.cancel();
                    return false;
                }
            });
            handler = new Handler();
            handler.postDelayed(new Runnable() { // from class: cn.finalteam.galleryfinal.utils.PermissionDialog.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!PermissionDialog.isShow || PermissionDialog.activityContext == null || PermissionDialog.activityContext.isFinishing()) {
                        return;
                    }
                    PermissionDialog.screenshotDialog.show();
                    PermissionDialog.screenshotDialog.getWindow().setLayout(PermissionDialog.access$300(), -2);
                }
            }, 200L);
        } catch (Exception unused) {
        }
    }

    private static int getScreenWidth() {
        return activityContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static void dimissDialog() {
        try {
            isShow = false;
            handler.removeCallbacksAndMessages(null);
            if (activityContext == null || activityContext.isFinishing() || screenshotDialog == null || !screenshotDialog.isShowing()) {
                return;
            }
            screenshotDialog.dismiss();
        } catch (Exception unused) {
        }
    }
}
