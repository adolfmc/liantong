package com.android.client.asm.core.uaf;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.gmrz.android.client.utils.Logger;
import com.gmrz.android.uaf.asm.service.IUafAsmService;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AsmService extends Service {
    private static final String TAG = "AsmService";

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AsmServiceImpl extends IUafAsmService.Stub {
        public AsmServiceImpl() {
        }

        @Override // com.gmrz.android.uaf.asm.service.IUafAsmService
        public String sendRequest(String str) {
            String[] packagesForUid = AsmService.this.getApplicationContext().getPackageManager().getPackagesForUid(getCallingUid());
            if (packagesForUid == null || packagesForUid.length == 0) {
                return null;
            }
            String str2 = AsmService.TAG;
            Logger.m15889i(str2, "Processing ASM service request from " + packagesForUid[0]);
            return new AsmFactory(AsmService.this.getApplicationContext()).createAsmInstance(packagesForUid[0]).process(str, null);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        Logger.m15895d(TAG, "onCreate");
    }

    @Override // android.app.Service
    public void onDestroy() {
        Logger.m15895d(TAG, "onDestroy");
        stopSelf();
        super.onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String str = TAG;
        Logger.m15895d(str, "onBind: " + intent.toString());
        return new AsmServiceImpl();
    }
}
