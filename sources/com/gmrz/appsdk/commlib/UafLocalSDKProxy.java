package com.gmrz.appsdk.commlib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.gmrz.appsdk.FidoOut;
import com.gmrz.appsdk.commlib.AppSdkBase;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.debug.api.Type;
import com.gmrz.appsdk.util.Logger;
import com.gmrz.fido.offline.CostTimeRecorder;
import com.gmrz.fido.offline.DebugSwitch;

/* renamed from: com.gmrz.appsdk.commlib.g */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafLocalSDKProxy extends AppSdkBase {

    /* renamed from: m */
    private final String f10315m = UafLocalSDKProxy.class.getSimpleName() + "_fido";

    /* renamed from: a */
    private void m15813a(Context context, long j, boolean z) {
        try {
            if (z) {
                CostTimeRecorder.m15745a(context).m15746a(j, Type.LOCAL.toString());
            } else {
                CostTimeRecorder.m15745a(context).m15740b(j, Type.LOCAL.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.gmrz.appsdk.commlib.AppSdkBase, com.gmrz.appsdk.commlib.api.IAppSDK
    public FidoOut process(Context context, Object obj) {
        boolean z;
        FidoOut process = super.process(context, obj);
        if (process.fidoStatus != FidoStatus.SUCCESS) {
            return process;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Logger.m15757e(this.f10315m, "uaf process begin");
        if (obj != null) {
            if (new UafLocalCommClient(context).sendRequest("MFAC:gmrz", obj, new AppSdkBase.C4411a()) == 0) {
                try {
                    this.f10282d.acquire();
                    z = false;
                } catch (InterruptedException unused) {
                    z = true;
                }
                Logger.m15757e(this.f10315m, "Complete waiting for response");
                if (!z) {
                    process = this.f10281c;
                } else {
                    process.fidoStatus = FidoStatus.FAILED;
                }
            } else {
                process.fidoStatus = FidoStatus.FAILED;
            }
        } else {
            process.fidoStatus = FidoStatus.FAILED;
        }
        if (obj != null && DebugSwitch.m15738a(context)) {
            Bundle extras = ((Intent) obj).getExtras();
            if (extras != null && "DISCOVER".equals(extras.getString("UAFIntentType"))) {
                m15813a(context, System.currentTimeMillis() - currentTimeMillis, true);
            }
            if (extras != null && "UAF_OPERATION".equals(extras.getString("UAFIntentType"))) {
                m15813a(context, System.currentTimeMillis() - currentTimeMillis, false);
            }
        }
        return process;
    }
}
