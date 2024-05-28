package com.huawei.hms.adapter.p213ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import com.bytedance.applog.tracker.Tracker;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.p225ui.NotInstalledHmsDialogHelper;

/* renamed from: com.huawei.hms.adapter.ui.NotInstalledHmsAdapter */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class NotInstalledHmsAdapter implements IBridgeActivityDelegate {

    /* renamed from: c */
    private static final Object f10972c = new Object();

    /* renamed from: d */
    private static boolean f10973d;

    /* renamed from: a */
    private Activity f10974a;

    /* renamed from: b */
    private Dialog f10975b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.adapter.ui.NotInstalledHmsAdapter$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class DialogInterface$OnCancelListenerC4848a implements DialogInterface.OnCancelListener {

        /* renamed from: a */
        private final Activity f10976a;

        public DialogInterface$OnCancelListenerC4848a(Activity activity) {
            this.f10976a = activity;
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            HMSLog.m14110i("NotInstalledHmsAdapter", "<Dialog onCancel>");
            SystemManager.getInstance().notifyUpdateResult(13);
            this.f10976a.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.adapter.ui.NotInstalledHmsAdapter$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class DialogInterface$OnClickListenerC4849b implements DialogInterface.OnClickListener {

        /* renamed from: a */
        private final Activity f10977a;

        public DialogInterface$OnClickListenerC4849b(Activity activity) {
            this.f10977a = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Tracker.onClick(dialogInterface, i);
            HMSLog.m14110i("NotInstalledHmsAdapter", "<Dialog onClick>");
            SystemManager.getInstance().notifyUpdateResult(30);
            this.f10977a.finish();
        }
    }

    /* renamed from: a */
    private void m15247a(Activity activity) {
        Dialog dialog = this.f10975b;
        if (dialog != null && dialog.isShowing()) {
            this.f10975b.setOnCancelListener(null);
            this.f10975b.cancel();
        }
        this.f10975b = NotInstalledHmsDialogHelper.getDialogBuilder(activity).setPositiveButton(NotInstalledHmsDialogHelper.getConfirmResId(activity), new DialogInterface$OnClickListenerC4849b(activity)).setOnCancelListener(new DialogInterface$OnCancelListenerC4848a(activity)).show();
    }

    public static boolean getShowLock() {
        synchronized (f10972c) {
            HMSLog.m14110i("NotInstalledHmsAdapter", "<canShowDialog> sIsShowingDialog: " + f10973d);
            if (f10973d) {
                return false;
            }
            f10973d = true;
            return true;
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        HMSLog.m14110i("NotInstalledHmsAdapter", "<getRequestCode>");
        return 0;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        HMSLog.m14110i("NotInstalledHmsAdapter", "<onBridgeActivityCreate>");
        if (activity != null && !activity.isFinishing()) {
            this.f10974a = activity;
            m15247a(activity);
            return;
        }
        HMSLog.m14112e("NotInstalledHmsAdapter", "<onBridgeActivityCreate> activity is null or finishing");
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        HMSLog.m14110i("NotInstalledHmsAdapter", "<onBridgeActivityDestroy>");
        synchronized (f10972c) {
            f10973d = false;
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        HMSLog.m14110i("NotInstalledHmsAdapter", "<onBridgeActivityResult>");
        return false;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        HMSLog.m14110i("NotInstalledHmsAdapter", "<onBridgeConfigurationChanged>");
        Activity activity = this.f10974a;
        if (activity != null && !activity.isFinishing()) {
            m15247a(this.f10974a);
        } else {
            HMSLog.m14112e("NotInstalledHmsAdapter", "<onBridgeConfigurationChanged> mActivity is null or finishing");
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        HMSLog.m14110i("NotInstalledHmsAdapter", "<onKeyUp>");
    }
}
