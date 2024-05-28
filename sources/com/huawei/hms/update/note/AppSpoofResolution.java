package com.huawei.hms.update.note;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.availableupdate.AppSpoofResolveMgr;
import com.huawei.hms.p224ui.AbstractDialog;
import com.huawei.hms.p224ui.AbstractPromptDialog;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.PackageManagerHelper;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AppSpoofResolution implements IBridgeActivityDelegate {

    /* renamed from: a */
    private Activity f11780a;

    /* renamed from: b */
    private C5085b f11781b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.update.note.AppSpoofResolution$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5084a implements AbstractDialog.Callback {
        C5084a() {
        }

        @Override // com.huawei.hms.p224ui.AbstractDialog.Callback
        public void onCancel(AbstractDialog abstractDialog) {
            AppSpoofResolveMgr.f11039c.m15192a(true);
            AppSpoofResolution.this.f11781b = null;
            AppSpoofResolution.this.m14107a();
        }

        @Override // com.huawei.hms.p224ui.AbstractDialog.Callback
        public void onDoWork(AbstractDialog abstractDialog) {
            AppSpoofResolveMgr.f11039c.m15192a(true);
            AppSpoofResolution.this.f11781b = null;
            AppSpoofResolution.this.m14107a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.update.note.AppSpoofResolution$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5085b extends AbstractPromptDialog {
        private C5085b() {
        }

        @Override // com.huawei.hms.p224ui.AbstractDialog
        public String onGetMessageString(Context context) {
            String applicationName = new PackageManagerHelper(context).getApplicationName("com.huawei.hwid");
            if (TextUtils.isEmpty(applicationName)) {
                applicationName = "com.huawei.hwid";
            }
            if (ResourceLoaderUtil.getmContext() == null) {
                ResourceLoaderUtil.setmContext(context);
            }
            return ResourceLoaderUtil.getString("hms_is_spoof", applicationName);
        }

        @Override // com.huawei.hms.p224ui.AbstractDialog
        public String onGetPositiveButtonString(Context context) {
            if (ResourceLoaderUtil.getmContext() == null) {
                ResourceLoaderUtil.setmContext(context);
            }
            return ResourceLoaderUtil.getString("hms_confirm");
        }

        @Override // com.huawei.hms.p224ui.AbstractPromptDialog, com.huawei.hms.p224ui.AbstractDialog
        public String onGetTitleString(Context context) {
            if (ResourceLoaderUtil.getmContext() == null) {
                ResourceLoaderUtil.setmContext(context);
            }
            return ResourceLoaderUtil.getString("hms_spoof_hints");
        }

        /* synthetic */ C5085b(C5084a c5084a) {
            this();
        }
    }

    /* renamed from: b */
    private void m14104b() {
        Activity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        C5085b c5085b = this.f11781b;
        if (c5085b == null) {
            this.f11781b = new C5085b(null);
        } else {
            c5085b.dismiss();
        }
        HMSLog.m14110i("AppSpoofResolution", "enter AppSpoofResolution showPromptdlg to resolve conn error");
        this.f11781b.show(activity, new C5084a());
    }

    protected Activity getActivity() {
        return this.f11780a;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 0;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        HMSLog.m14110i("AppSpoofResolution", "enter AppSpoofResolution onBridgeActivityCreate");
        this.f11780a = activity;
        AppSpoofResolveMgr appSpoofResolveMgr = AppSpoofResolveMgr.f11039c;
        appSpoofResolveMgr.m15193a(activity);
        appSpoofResolveMgr.m15192a(false);
        m14104b();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        HMSLog.m14110i("AppSpoofResolution", "enter AppSpoofResolution onBridgeActivityDestroy");
        AppSpoofResolveMgr appSpoofResolveMgr = AppSpoofResolveMgr.f11039c;
        if (appSpoofResolveMgr.m15194a().compareAndSet(true, false)) {
            SystemManager.getInstance().notifyNoticeResult(29);
        }
        appSpoofResolveMgr.m15191b(this.f11780a);
        this.f11780a = null;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        if (i != getRequestCode()) {
            return false;
        }
        HMSLog.m14110i("AppSpoofResolution", "enter AppSpoofResolution onBridgeActivityResult");
        return true;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        if (this.f11781b == null) {
            return;
        }
        HMSLog.m14110i("AppSpoofResolution", "enter AppSpoofResolution re show prompt dialog");
        m14104b();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        HMSLog.m14110i("AppSpoofResolution", "enter AppSpoofResolution On key up when resolve spoof error");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m14107a() {
        Activity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        HMSLog.m14110i("AppSpoofResolution", "enter AppSpoofResolution finishBridgeActivity：");
        if (AppSpoofResolveMgr.f11039c.m15194a().compareAndSet(true, false)) {
            SystemManager.getInstance().notifyNoticeResult(29);
        }
        activity.finish();
    }
}
