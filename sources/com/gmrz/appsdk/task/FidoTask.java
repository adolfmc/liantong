package com.gmrz.appsdk.task;

import android.util.Log;
import com.gmrz.appsdk.commlib.api.FidoStatus;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.appsdk.task.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class FidoTask {

    /* renamed from: a */
    private static final String f10353a = FidoTask.class.getSimpleName() + "_fido";

    /* renamed from: a */
    public static FidoStatus m15763a(short s) {
        FidoStatus fidoStatus = FidoStatus.FAILED;
        if (s != 0) {
            if (s != 1) {
                if (s != 3) {
                    if (s != 255) {
                        switch (s) {
                            case 5:
                                return FidoStatus.NO_MATCH;
                            case 6:
                                return FidoStatus.PROTOCOL_ERROR;
                            case 7:
                                return FidoStatus.APP_NOT_FOUND;
                            default:
                                String str = f10353a;
                                Log.e(str, "Unexpected error code (" + ((int) s) + ") received from the client");
                                return FidoStatus.PROTOCOL_ERROR;
                        }
                    }
                    return fidoStatus;
                }
                return FidoStatus.CANCELED;
            }
            return FidoStatus.WAIT_USER_ACTION;
        }
        return FidoStatus.SUCCESS;
    }
}
