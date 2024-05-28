package com.gmrz.appsdk.commlib.api;

import android.content.Context;
import com.gmrz.appsdk.FidoOut;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IAppSDK {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum ClientLocation {
        REMOTE_CLIENT,
        LOCAL_CLIENT,
        AUTO_CLIENT
    }

    FidoOut process(Context context, Object obj);
}
