package com.huawei.hms.aaid.init;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.opendevice.RemoteService;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Util;

/* renamed from: com.huawei.hms.aaid.init.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AutoInitRunnable implements Runnable {

    /* renamed from: a */
    private Context f10886a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoInitRunnable(Context context) {
        this.f10886a = context;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0093 -> B:24:0x00a3). Please submit an issue!!! */
    @Override // java.lang.Runnable
    public void run() {
        try {
            int internalCode = ErrorEnum.SUCCESS.getInternalCode();
            String str = null;
            try {
                str = HmsInstanceId.getInstance(this.f10886a).getToken(Util.getAppId(this.f10886a), null);
                HMSLog.m14110i("AutoInit", "Push init succeed");
                if (TextUtils.isEmpty(str)) {
                    return;
                }
            } catch (ApiException e) {
                internalCode = e.getStatusCode();
                HMSLog.m14112e("AutoInit", "new Push init failed");
            }
            try {
                Bundle bundle = this.f10886a.getPackageManager().getApplicationInfo(this.f10886a.getPackageName(), 128).metaData;
                if (bundle != null && bundle.getString("com.huawei.hms.client.service.name:push") != null) {
                    Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
                    intent.setPackage(this.f10886a.getPackageName());
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("message_type", "new_token");
                    bundle2.putString("device_token", str);
                    bundle2.putInt("error", internalCode);
                    if (!new RemoteService().m14360a(this.f10886a, bundle2, intent)) {
                        HMSLog.m14112e("AutoInit", "start service failed");
                    }
                } else {
                    HMSLog.m14110i("AutoInit", "push kit sdk not exists");
                }
            } catch (PackageManager.NameNotFoundException unused) {
                HMSLog.m14110i("AutoInit", "push kit sdk not exists");
            }
        } catch (Exception e2) {
            HMSLog.m14111e("AutoInit", "Push init failed", e2);
        }
    }
}
