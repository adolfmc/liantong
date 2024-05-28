package com.huawei.hms.update.note;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import com.bytedance.applog.tracker.Tracker;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.availableupdate.NotInstalledHmsResolveMgr;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.p225ui.NotInstalledHmsDialogHelper;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class NotInstalledHmsResolution implements IBridgeActivityDelegate {

    /* renamed from: a */
    private Dialog f11783a;

    /* renamed from: b */
    private Activity f11784b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.update.note.NotInstalledHmsResolution$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class DialogInterface$OnClickListenerC5086a implements DialogInterface.OnClickListener {

        /* renamed from: a */
        private final Activity f11785a;

        public DialogInterface$OnClickListenerC5086a(Activity activity) {
            this.f11785a = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Tracker.onClick(dialogInterface, i);
            HMSLog.m14110i("NotInstalledHmsResolution", "<Dialog onClick>");
            this.f11785a.finish();
        }
    }

    /* renamed from: a */
    private void m14102a(Activity activity) {
        m14103a();
        this.f11783a = NotInstalledHmsDialogHelper.getDialogBuilder(activity).setPositiveButton(NotInstalledHmsDialogHelper.getConfirmResId(activity), new DialogInterface$OnClickListenerC5086a(activity)).show();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        HMSLog.m14110i("NotInstalledHmsResolution", "<Resolution getRequestCode>");
        return 0;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        HMSLog.m14110i("NotInstalledHmsResolution", "<Resolution onBridgeActivityCreate>");
        if (activity != null && !activity.isFinishing()) {
            this.f11784b = activity;
            NotInstalledHmsResolveMgr.f11043b.m15190a(activity);
            m14102a(activity);
            return;
        }
        HMSLog.m14112e("NotInstalledHmsResolution", "<Resolution onBridgeActivityCreate> activity is null or finishing");
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        HMSLog.m14110i("NotInstalledHmsResolution", "<Resolution onBridgeActivityDestroy>");
        m14103a();
        NotInstalledHmsResolveMgr.f11043b.m15189b(this.f11784b);
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        HMSLog.m14110i("NotInstalledHmsResolution", "<Resolution onBridgeActivityResult>");
        return false;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        HMSLog.m14110i("NotInstalledHmsResolution", "<Resolution onBridgeConfigurationChanged>");
        Activity activity = this.f11784b;
        if (activity != null && !activity.isFinishing()) {
            m14102a(this.f11784b);
        } else {
            HMSLog.m14112e("NotInstalledHmsResolution", "<Resolution onBridgeActivityCreate> mActivity is null or finishing");
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        HMSLog.m14110i("NotInstalledHmsResolution", "<Resolution onKeyUp>");
    }

    /* renamed from: a */
    private void m14103a() {
        Dialog dialog = this.f11783a;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.f11783a.cancel();
    }
}
