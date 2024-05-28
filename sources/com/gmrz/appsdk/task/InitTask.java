package com.gmrz.appsdk.task;

import android.content.Context;
import android.os.Build;
import com.gmrz.appsdk.FidoOut;
import com.gmrz.appsdk.commlib.api.FidoParam;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.commlib.api.IAppSDK;
import com.gmrz.appsdk.debug.api.Type;
import com.gmrz.appsdk.util.FpUtil;
import com.gmrz.appsdk.util.Logger;
import com.gmrz.appsdk.util.TeeFidoIn;
import com.gmrz.fido.offline.CostTimeRecorder;
import com.gmrz.fido.offline.DebugSwitch;

/* renamed from: com.gmrz.appsdk.task.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class InitTask {
    /* renamed from: b */
    private void m15760b(Context context) {
        if (DebugSwitch.m15738a(context)) {
            CostTimeRecorder.m15745a(context).m15744a(Type.AUTO);
        }
    }

    /* renamed from: c */
    private void m15759c(Context context) {
        if (DebugSwitch.m15738a(context)) {
            CostTimeRecorder.m15745a(context).m15744a(Type.MANUAL);
        }
    }

    /* renamed from: a */
    public FidoOut m15761a(Context context, FidoParam fidoParam) {
        FidoOut fidoOut = new FidoOut();
        fidoOut.fidoStatus = FidoStatus.FAILED;
        if (fidoParam != null && fidoParam.getLocation() != null && fidoParam.getLocation() != IAppSDK.ClientLocation.AUTO_CLIENT) {
            IAppSDK.ClientLocation location = fidoParam.getLocation();
            IAppSDK.ClientLocation clientLocation = IAppSDK.ClientLocation.LOCAL_CLIENT;
            if (location == clientLocation) {
                fidoOut = m15762a(context);
                if (fidoOut.fidoStatus == FidoStatus.SUCCESS) {
                    fidoOut.clientType = clientLocation;
                }
                m15759c(context);
            } else {
                IAppSDK.ClientLocation location2 = fidoParam.getLocation();
                IAppSDK.ClientLocation clientLocation2 = IAppSDK.ClientLocation.REMOTE_CLIENT;
                if (location2 == clientLocation2) {
                    fidoOut = new ProcessTask().m15767a(context, TeeFidoIn.Builder().getTeeFidoIn(context));
                    if (fidoOut.fidoStatus == FidoStatus.SUCCESS) {
                        fidoOut.clientType = clientLocation2;
                    }
                    m15759c(context);
                }
            }
        } else {
            fidoOut.clientType = IAppSDK.ClientLocation.AUTO_CLIENT;
            fidoOut.fidoStatus = FidoStatus.SUCCESS;
            m15760b(context);
        }
        return fidoOut;
    }

    /* renamed from: a */
    private FidoOut m15762a(Context context) {
        FidoOut fidoOut = new FidoOut();
        FidoStatus fidoStatus = FidoStatus.FAILED;
        fidoOut.fidoStatus = fidoStatus;
        try {
            if (Build.VERSION.SDK_INT < 23) {
                Logger.m15756i("InitTask", "Android version should be Marshmallow or above, disabling the Local Fp ASM");
                fidoOut.fidoStatus = fidoStatus;
                return fidoOut;
            }
            Logger.m15756i("InitTask", "Local init done. returning SUCCESS");
            if (!FpUtil.checkSupport(context)) {
                fidoOut.fidoStatus = fidoStatus;
                return fidoOut;
            }
            fidoOut.fidoStatus = FidoStatus.SUCCESS;
            return fidoOut;
        } catch (Exception e) {
            Logger.m15757e("InitTask", "UafAppSDKProxy exception: " + e);
            fidoOut.fidoStatus = FidoStatus.FAILED;
            return fidoOut;
        }
    }
}
