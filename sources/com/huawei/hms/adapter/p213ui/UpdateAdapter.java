package com.huawei.hms.adapter.p213ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.adapter.AvailableUtil;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.availableupdate.UpdateAdapterMgr;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.p225ui.UpdateBean;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.PackageManagerHelper;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;

/* renamed from: com.huawei.hms.adapter.ui.UpdateAdapter */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UpdateAdapter implements IBridgeActivityDelegate {

    /* renamed from: a */
    private WeakReference<Activity> f10978a;

    /* renamed from: b */
    private Context f10979b;

    /* renamed from: c */
    private int f10980c;

    /* renamed from: d */
    private UpdateBean f10981d;

    /* renamed from: e */
    private boolean f10982e = false;

    /* renamed from: a */
    private boolean m15243a(Intent intent, Activity activity) {
        if (intent.getBooleanExtra("new_update", false)) {
            HMSLog.m14110i("UpdateAdapter", "4.0 framework HMSCore upgrade process");
            String hMSPackageName = HMSPackageManager.getInstance(activity.getApplicationContext()).getHMSPackageName();
            ComponentName componentName = new ComponentName(hMSPackageName, "com.huawei.hms.fwksdk.stub.UpdateStubActivity");
            Intent intent2 = new Intent();
            intent2.putExtra("kpms_key_caller_packagename", activity.getApplicationContext().getPackageName());
            intent2.putExtra("kitUpdatePackageName", hMSPackageName);
            intent2.setComponent(componentName);
            activity.startActivityForResult(intent2, 1001);
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private Activity m15242b() {
        WeakReference<Activity> weakReference = this.f10978a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    /* renamed from: c */
    private void m15241c() {
        SystemManager.getInstance().notifyUpdateResult(8);
        m15246a();
    }

    public static Object invokeMethod(String str, String str2, Object[] objArr) {
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] instanceof Activity) {
                clsArr[i] = Activity.class;
            } else if (objArr[i] instanceof Context) {
                clsArr[i] = Context.class;
            } else if (objArr[i] instanceof UpdateBean) {
                clsArr[i] = UpdateBean.class;
            } else if (objArr[i] instanceof Integer) {
                clsArr[i] = Integer.TYPE;
            } else if (objArr[i] instanceof Boolean) {
                clsArr[i] = Boolean.TYPE;
            } else {
                HMSLog.m14112e("UpdateAdapter", "not set args[" + i + "] type");
            }
        }
        try {
            Class<?> cls = Class.forName(str);
            return cls.getMethod(str2, clsArr).invoke(cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]), objArr);
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            HMSLog.m14112e("UpdateAdapter", "invoke " + str + "." + str2 + " fail. " + e.getMessage());
            return null;
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 1001;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        if (activity == null) {
            HMSLog.m14110i("UpdateAdapter", "activity == null");
            m15241c();
            return;
        }
        this.f10979b = activity.getApplicationContext();
        this.f10978a = new WeakReference<>(activity);
        if (UpdateAdapterMgr.f11046b.m15187a(m15242b())) {
            Intent intent = activity.getIntent();
            if (intent == null) {
                m15241c();
                return;
            }
            try {
                this.f10980c = intent.getIntExtra("update_version", 0);
            } catch (Exception e) {
                HMSLog.m14112e("UpdateAdapter", "get update_version:" + e.getMessage());
            }
            if (this.f10980c == 0) {
                m15241c();
                return;
            }
            if (intent.hasExtra("installHMS")) {
                this.f10982e = true;
            }
            if (!m15243a(intent, activity) && AvailableUtil.isInstallerLibExist(this.f10979b)) {
                UpdateBean updateBean = (UpdateBean) invokeMethod("com.huawei.hms.adapter.ui.InstallerAdapter", "setUpdateBean", new Object[]{activity, Integer.valueOf(this.f10980c), Boolean.valueOf(this.f10982e)});
                this.f10981d = updateBean;
                invokeMethod("com.huawei.hms.adapter.ui.InstallerAdapter", "startUpdateHms", new Object[]{activity, updateBean, 1001});
                this.f10981d = null;
            }
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        HMSLog.m14110i("UpdateAdapter", "onBridgeActivityDestroy");
        UpdateAdapterMgr.f11046b.m15186b(m15242b());
        this.f10978a = null;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        if (i != getRequestCode()) {
            this.f10981d = null;
            return false;
        }
        HMSLog.m14110i("UpdateAdapter", "onBridgeActivityResult " + i2);
        if (AvailableUtil.isInstallerLibExist(this.f10979b) && i2 == 1214) {
            HMSLog.m14110i("UpdateAdapter", "Enter update escape route");
            Activity m15242b = m15242b();
            if (m15242b == null) {
                HMSLog.m14112e("UpdateAdapter", "bridgeActivity is null, update escape failed ");
                this.f10981d = null;
                return true;
            }
            invokeMethod("com.huawei.hms.update.manager.UpdateManager", "startUpdate", new Object[]{m15242b, 1001, this.f10981d});
            this.f10981d = null;
        }
        if (i2 == -1) {
            if (intent != null) {
                if (intent.getIntExtra("kit_update_result", 0) == 1) {
                    HMSLog.m14110i("UpdateAdapter", "new framework update process,Error resolved successfully!");
                    SystemManager.getInstance().notifyUpdateResult(0);
                    this.f10981d = null;
                    m15246a();
                    return true;
                }
                m15244a(intent);
            }
        } else if (i2 == 0) {
            HMSLog.m14110i("UpdateAdapter", "Activity.RESULT_CANCELED");
            this.f10981d = null;
            Activity m15242b2 = m15242b();
            if (m15242b2 == null) {
                return true;
            }
            String hMSPackageName = HMSPackageManager.getInstance(m15242b2.getApplicationContext()).getHMSPackageName();
            if (!this.f10982e && !m15245a(m15242b2, hMSPackageName, this.f10980c)) {
                SystemManager.getInstance().notifyUpdateResult(0);
            } else {
                HMSLog.m14110i("UpdateAdapter", "Resolve error, process canceled by user clicking back button!");
                SystemManager.getInstance().notifyUpdateResult(13);
            }
        } else if (i2 == 1) {
            SystemManager.getInstance().notifyUpdateResult(28);
        }
        m15246a();
        return true;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        HMSLog.m14110i("UpdateAdapter", "onBridgeConfigurationChanged");
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        HMSLog.m14110i("UpdateAdapter", "On key up when resolve conn error");
    }

    /* renamed from: a */
    private void m15244a(Intent intent) {
        int intExtra = intent.getIntExtra(BridgeActivity.EXTRA_RESULT, -1);
        if (intExtra == 0) {
            HMSLog.m14110i("UpdateAdapter", "Error resolved successfully!");
            SystemManager.getInstance().notifyUpdateResult(0);
        } else if (intExtra == 13) {
            HMSLog.m14110i("UpdateAdapter", "Resolve error process canceled by user!");
            SystemManager.getInstance().notifyUpdateResult(13);
        } else if (intExtra == 8) {
            HMSLog.m14110i("UpdateAdapter", "Internal error occurred, recommended retry.");
            SystemManager.getInstance().notifyUpdateResult(8);
        } else {
            HMSLog.m14110i("UpdateAdapter", "Other error codes.");
            SystemManager.getInstance().notifyUpdateResult(intExtra);
        }
    }

    /* renamed from: a */
    private void m15246a() {
        Activity m15242b = m15242b();
        if (m15242b == null || m15242b.isFinishing()) {
            return;
        }
        m15242b.finish();
    }

    /* renamed from: a */
    private boolean m15245a(Context context, String str, int i) {
        if (context == null || TextUtils.isEmpty(str) || i == 0) {
            return false;
        }
        PackageManagerHelper packageManagerHelper = new PackageManagerHelper(context);
        return PackageManagerHelper.PackageStates.NOT_INSTALLED.equals(packageManagerHelper.getPackageStates(str)) || packageManagerHelper.getPackageVersionCode(str) < i;
    }
}
