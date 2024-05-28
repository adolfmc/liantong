package com.gmrz.appsdk.commlib;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.gmrz.appsdk.FidoOut;
import com.gmrz.appsdk.commlib.AppSdkBase;
import com.gmrz.appsdk.commlib.UafRemoteServiceClient;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.debug.api.Type;
import com.gmrz.appsdk.util.CapableComponentUtil;
import com.gmrz.appsdk.util.Constant;
import com.gmrz.appsdk.util.ExceptionRecorder;
import com.gmrz.appsdk.util.Logger;
import com.gmrz.fido.offline.CostTimeRecorder;
import com.gmrz.fido.offline.DebugSwitch;
import java.util.concurrent.Semaphore;

/* renamed from: com.gmrz.appsdk.commlib.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafAppSDKProxy extends AppSdkBase {

    /* renamed from: m */
    private final String f10298m = UafAppSDKProxy.class.getSimpleName() + "_fido";

    /* compiled from: UafAppSDKProxy.java */
    /* renamed from: com.gmrz.appsdk.commlib.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class C4415a implements UafRemoteServiceClient.InterfaceC4421b {

        /* renamed from: a */
        final /* synthetic */ Context f10299a;

        /* renamed from: b */
        final /* synthetic */ Object f10300b;

        C4415a(Context context, Object obj) {
            this.f10299a = context;
            this.f10300b = obj;
        }

        @Override // com.gmrz.appsdk.commlib.UafRemoteServiceClient.InterfaceC4421b
        /* renamed from: a */
        public void mo15809a() {
            ExceptionRecorder.recordServiceException(this.f10299a, true);
            UafAppSDKProxy.this.m15824a(this.f10299a, this.f10300b);
        }
    }

    /* compiled from: UafAppSDKProxy.java */
    /* renamed from: com.gmrz.appsdk.commlib.c$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class RunnableC4416b implements Runnable {

        /* renamed from: a */
        private final AppSdkBase f10302a;

        /* renamed from: b */
        private final Semaphore f10303b;

        /* renamed from: c */
        private final FidoOut f10304c;

        public RunnableC4416b(AppSdkBase appSdkBase, Semaphore semaphore, FidoOut fidoOut) {
            this.f10302a = appSdkBase;
            this.f10303b = semaphore;
            this.f10304c = fidoOut;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f10304c.fidoStatus = FidoStatus.SUB_MODULE_RESPONSE_TIMEOUT;
            this.f10302a.m15835a(false);
            this.f10303b.release();
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
        process.fidoStatus = FidoStatus.FAILED;
        boolean z2 = Constant.USE_SERVICE;
        Logger.wtf(this.f10298m, "isUseService::" + z2);
        Logger.m15757e(this.f10298m, "uaf process begin");
        try {
            ResolveInfo queryCapableActivity = CapableComponentUtil.getInstance(context).queryCapableActivity();
            ResolveInfo queryCapableASMActivity = CapableComponentUtil.getInstance(context).queryCapableASMActivity();
            if (queryCapableActivity != null && queryCapableASMActivity != null && obj != null) {
                Bundle extras = ((Intent) obj).getExtras();
                Handler handler = new Handler(context.getMainLooper());
                RunnableC4416b runnableC4416b = new RunnableC4416b(this, this.f10282d, process);
                boolean z3 = extras != null && "DISCOVER".equals(extras.getString("UAFIntentType"));
                if (z3) {
                    handler.postDelayed(runnableC4416b, 3000L);
                }
                ResolveInfo queryCapableService = CapableComponentUtil.getInstance(context).queryCapableService();
                Log.wtf(this.f10298m, "** resolveServiceInfo **" + queryCapableService);
                if (z2 && queryCapableService != null && !ExceptionRecorder.isServiceExceptionHappened(context)) {
                    Log.wtf(this.f10298m, "** communication use AIDL with Service **");
                    UafRemoteServiceClient uafRemoteServiceClient = new UafRemoteServiceClient(context);
                    uafRemoteServiceClient.m15811a(new C4415a(context, obj));
                    uafRemoteServiceClient.sendRequest(queryCapableService.serviceInfo.packageName + "#" + queryCapableService.serviceInfo.name, obj, new AppSdkBase.BinderC4414b());
                } else {
                    m15824a(context, obj);
                }
                try {
                    m15835a(true);
                    this.f10282d.acquire();
                    z = false;
                } catch (InterruptedException unused) {
                    z = true;
                }
                Logger.m15757e(this.f10298m, "Complete waiting for response");
                if (!z) {
                    process = this.f10281c;
                } else {
                    process.fidoStatus = FidoStatus.FAILED;
                }
                if (z3) {
                    handler.removeCallbacks(runnableC4416b);
                }
                if (DebugSwitch.m15738a(context)) {
                    if (z3) {
                        m15825a(context, System.currentTimeMillis() - currentTimeMillis, true);
                    }
                    if (extras != null && "UAF_OPERATION".equals(extras.getString("UAFIntentType"))) {
                        m15825a(context, System.currentTimeMillis() - currentTimeMillis, false);
                    }
                }
            }
            return process;
        } catch (Exception e) {
            e.getStackTrace();
            process.fidoStatus = FidoStatus.FAILED;
            return process;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15824a(Context context, Object obj) {
        Log.wtf(this.f10298m, "** communication use Intent with Activity **");
        new UafRemoteCommClient(context).sendRequest("MFAC:gmrz", obj, new AppSdkBase.C4411a());
    }

    /* renamed from: a */
    private void m15825a(Context context, long j, boolean z) {
        try {
            if (z) {
                CostTimeRecorder.m15745a(context).m15746a(j, Type.REMOTE.toString());
            } else {
                CostTimeRecorder.m15745a(context).m15740b(j, Type.REMOTE.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
