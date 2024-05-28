package com.huawei.hms.adapter.p213ui;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.adapter.sysobs.ApkResolutionFailedManager;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.IntentUtil;
import com.huawei.hms.utils.ResolutionFlagUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* renamed from: com.huawei.hms.adapter.ui.BaseResolutionAdapter */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BaseResolutionAdapter implements IBridgeActivityDelegate {

    /* renamed from: a */
    private WeakReference<Activity> f10969a;

    /* renamed from: b */
    private String f10970b = "";

    /* renamed from: c */
    private long f10971c = 0;

    /* renamed from: a */
    private void m15251a(long j) {
        if (!SystemUtils.isChinaROM()) {
            HMSLog.m14110i("BaseResolutionAdapter", "not ChinaROM");
            return;
        }
        Activity m15249c = m15249c();
        if (m15249c == null || m15249c.isFinishing()) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("package", m15249c.getPackageName());
        hashMap.put("resolution_flag", this.f10971c + "-" + j);
        HiAnalyticsUtils.getInstance().onEvent(m15249c, "HMS_SDK_BASE_START_RESOLUTION", hashMap);
        HMSLog.m14112e("BaseResolutionAdapter", "check resolution flag failed, transactionId: " + this.f10970b + ", carriedTimeStamp: " + this.f10971c + ", savedTimeStamp: " + j);
    }

    /* renamed from: b */
    private void m15250b() {
        Activity m15249c = m15249c();
        if (m15249c == null || m15249c.isFinishing()) {
            return;
        }
        m15249c.finish();
    }

    /* renamed from: c */
    private Activity m15249c() {
        WeakReference<Activity> weakReference = this.f10969a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    /* renamed from: d */
    private void m15248d() {
        SystemManager.getInstance().notifyResolutionResult(null, this.f10970b);
        m15250b();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 1001;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        this.f10969a = new WeakReference<>(activity);
        Intent intent = activity.getIntent();
        if (intent == null) {
            m15248d();
            return;
        }
        Bundle bundle = null;
        try {
            bundle = intent.getExtras();
            this.f10970b = intent.getStringExtra("transaction_id");
            this.f10971c = intent.getLongExtra("resolution_flag", 0L);
        } catch (Throwable th) {
            HMSLog.m14112e("BaseResolutionAdapter", "get transaction_id or resolution_flag exception:" + th.getClass().getSimpleName());
        }
        if (!m15252a()) {
            m15248d();
            return;
        }
        if (this.f10970b != null && Build.VERSION.SDK_INT >= 29) {
            HMSLog.m14110i("BaseResolutionAdapter", "remove apk resolution failed task.");
            ApkResolutionFailedManager.getInstance().removeTask(this.f10970b);
        }
        if (bundle == null) {
            m15248d();
            return;
        }
        Parcelable parcelable = bundle.getParcelable("resolution");
        if (parcelable == null) {
            m15248d();
        } else if (parcelable instanceof Intent) {
            try {
                activity.startActivityForResult(IntentUtil.modifyIntentBehaviorsSafe((Intent) parcelable), 1001);
            } catch (Throwable unused) {
                m15248d();
                HMSLog.m14112e("BaseResolutionAdapter", "ActivityNotFoundException:exception");
            }
        } else if (parcelable instanceof PendingIntent) {
            try {
                activity.startIntentSenderForResult(((PendingIntent) parcelable).getIntentSender(), 1001, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException unused2) {
                m15248d();
                HMSLog.m14112e("BaseResolutionAdapter", "SendIntentException:exception");
            }
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        HMSLog.m14110i("BaseResolutionAdapter", "onBridgeActivityDestroy");
        this.f10969a = null;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        if (i != getRequestCode()) {
            return false;
        }
        HMSLog.m14110i("BaseResolutionAdapter", "onBridgeActivityResult, resultCode: " + i2);
        if (i2 == 1001 || i2 == 1002) {
            if (intent == null) {
                intent = new Intent();
            }
            intent.putExtra("privacy_statement_confirm_result", i2);
        }
        if (i2 != -1 && !intent.hasExtra("kit_update_result") && !intent.hasExtra("privacy_statement_confirm_result")) {
            SystemManager.getInstance().notifyResolutionResult(null, this.f10970b);
        } else {
            SystemManager.getInstance().notifyResolutionResult(intent, this.f10970b);
        }
        m15250b();
        return true;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        HMSLog.m14110i("BaseResolutionAdapter", "onBridgeConfigurationChanged");
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        HMSLog.m14110i("BaseResolutionAdapter", "On key up when resolve conn error");
    }

    /* renamed from: a */
    private boolean m15252a() {
        long resolutionFlag = ResolutionFlagUtil.getInstance().getResolutionFlag(this.f10970b);
        ResolutionFlagUtil.getInstance().removeResolutionFlag(this.f10970b);
        if (resolutionFlag == 0 || resolutionFlag != this.f10971c) {
            m15251a(resolutionFlag);
            return false;
        }
        return true;
    }
}
