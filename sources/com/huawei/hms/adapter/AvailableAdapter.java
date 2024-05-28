package com.huawei.hms.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.adapter.p213ui.NotInstalledHmsAdapter;
import com.huawei.hms.adapter.p213ui.UpdateAdapter;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.adapter.sysobs.SystemObserver;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.note.AppSpoofResolution;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.HMSBIInitializer;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.PackageManagerHelper;
import com.huawei.hms.utils.UIUtil;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AvailableAdapter {

    /* renamed from: a */
    private final int f10914a;

    /* renamed from: b */
    private AvailableCallBack f10915b;

    /* renamed from: d */
    private SystemObserver f10917d = new C4840a();

    /* renamed from: c */
    private boolean f10916c = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface AvailableCallBack {
        void onComplete(int i);
    }

    /* renamed from: com.huawei.hms.adapter.AvailableAdapter$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class C4840a implements SystemObserver {
        C4840a() {
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemObserver
        public boolean onNoticeResult(int i) {
            AvailableCallBack m15318a = AvailableAdapter.this.m15318a();
            if (m15318a == null) {
                HMSLog.m14112e("AvailableAdapter", "onNoticeResult baseCallBack null");
                return true;
            }
            m15318a.onComplete(i);
            return true;
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemObserver
        public boolean onSolutionResult(Intent intent, String str) {
            return false;
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemObserver
        public boolean onUpdateResult(int i) {
            AvailableCallBack m15318a = AvailableAdapter.this.m15318a();
            if (m15318a == null) {
                HMSLog.m14112e("AvailableAdapter", "onUpdateResult baseCallBack null");
                return true;
            }
            m15318a.onComplete(i);
            return true;
        }
    }

    public AvailableAdapter(int i) {
        this.f10914a = i;
    }

    /* renamed from: b */
    private void m15313b(Context context) {
        HMSBIInitializer.getInstance(context).initBI();
    }

    public int checkHuaweiMobileServicesForUpdate(Context context) {
        Checker.checkNonNull(context, "context must not be null.");
        int m15315a = m15315a(context);
        if (m15315a == 0 && HMSPackageManager.getInstance(context).isApkNeedUpdate(this.f10914a)) {
            HMSLog.m14110i("AvailableAdapter", "The current version does not meet the target version requirements");
            return 2;
        }
        return m15315a;
    }

    public int isHuaweiMobileServicesAvailable(Context context) {
        Checker.checkNonNull(context, "context must not be null.");
        int m15315a = m15315a(context);
        if (m15315a == 0 && HMSPackageManager.getInstance(context).isApkUpdateNecessary(this.f10914a)) {
            HMSLog.m14110i("AvailableAdapter", "The current version does not meet the minimum version requirements");
            return 2;
        }
        return m15315a;
    }

    public boolean isUserNoticeError(int i) {
        return i == 29;
    }

    public boolean isUserResolvableError(int i) {
        return i == 1 || i == 2;
    }

    public void setCalledBySolutionInstallHms(boolean z) {
        this.f10916c = z;
    }

    public void startNotice(Activity activity, AvailableCallBack availableCallBack) {
        if (activity == null || availableCallBack == null) {
            return;
        }
        if (UIUtil.isBackground(activity)) {
            HMSLog.m14110i("AvailableAdapter", "current app is in Background");
            availableCallBack.onComplete(28);
            return;
        }
        HMSLog.m14110i("AvailableAdapter", "startNotice");
        this.f10915b = availableCallBack;
        SystemManager.getSystemNotifier().registerObserver(this.f10917d);
        activity.startActivity(BridgeActivity.getIntentStartBridgeActivity(activity, AppSpoofResolution.class.getName()));
    }

    public void startResolution(Activity activity, AvailableCallBack availableCallBack) {
        if (activity == null || availableCallBack == null) {
            return;
        }
        m15313b(activity);
        if (UIUtil.isBackground(activity)) {
            HMSLog.m14110i("AvailableAdapter", "current app is in Background");
            availableCallBack.onComplete(28);
            return;
        }
        boolean m15317a = m15317a(activity);
        if (!AvailableUtil.isInstallerLibExist(activity) && !m15317a) {
            m15316a(activity, availableCallBack);
            return;
        }
        HMSLog.m14110i("AvailableAdapter", "startResolution");
        this.f10915b = availableCallBack;
        SystemManager.getSystemNotifier().registerObserver(this.f10917d);
        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(activity, UpdateAdapter.class.getName());
        intentStartBridgeActivity.putExtra("update_version", this.f10914a);
        if (this.f10916c) {
            intentStartBridgeActivity.putExtra("installHMS", "installHMS");
        }
        intentStartBridgeActivity.putExtra("new_update", m15317a);
        activity.startActivity(intentStartBridgeActivity);
    }

    /* renamed from: a */
    private int m15315a(Context context) {
        if (Build.VERSION.SDK_INT < 16) {
            HMSLog.m14110i("AvailableAdapter", "HMS can not be supported under android 4.1");
            return 21;
        } else if (HMSPackageManager.getInstance(context).isUseOldCertificate()) {
            HMSLog.m14112e("AvailableAdapter", "The CP uses the old certificate to terminate the connection.");
            return 13;
        } else {
            PackageManagerHelper.PackageStates hMSPackageStatesForMultiService = HMSPackageManager.getInstance(context).getHMSPackageStatesForMultiService();
            if (PackageManagerHelper.PackageStates.NOT_INSTALLED.equals(hMSPackageStatesForMultiService)) {
                HMSLog.m14110i("AvailableAdapter", "HMS is not installed");
                return 1;
            } else if (PackageManagerHelper.PackageStates.SPOOF.equals(hMSPackageStatesForMultiService)) {
                HMSLog.m14110i("AvailableAdapter", "HMS is spoofed");
                return 29;
            } else if (PackageManagerHelper.PackageStates.DISABLED.equals(hMSPackageStatesForMultiService)) {
                HMSLog.m14110i("AvailableAdapter", "HMS is disabled");
                return 3;
            } else {
                return 0;
            }
        }
    }

    /* renamed from: a */
    private void m15316a(Activity activity, AvailableCallBack availableCallBack) {
        HMSLog.m14110i("AvailableAdapter", "<showHmsApkNotInstalledDialog> startResolution");
        if (NotInstalledHmsAdapter.getShowLock()) {
            this.f10915b = availableCallBack;
            SystemManager.getSystemNotifier().registerObserver(this.f10917d);
            activity.startActivity(BridgeActivity.getIntentStartBridgeActivity(activity, NotInstalledHmsAdapter.class.getName()));
            return;
        }
        availableCallBack.onComplete(31);
    }

    /* renamed from: a */
    private boolean m15317a(Activity activity) {
        if (HMSPackageManager.getInstance(activity).getHmsVersionCode() >= 40000000) {
            HMSLog.m14110i("AvailableAdapter", "enter 4.0 HmsCore upgrade process");
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public AvailableCallBack m15318a() {
        return this.f10915b;
    }
}
