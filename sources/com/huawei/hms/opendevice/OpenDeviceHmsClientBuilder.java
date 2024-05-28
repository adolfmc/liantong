package com.huawei.hms.opendevice;

import android.content.Context;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.ClientSettings;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class OpenDeviceHmsClientBuilder extends AbstractClientBuilder<OpenDeviceHmsClient, OpenDeviceOptions> {
    @Override // com.huawei.hms.common.internal.AbstractClientBuilder
    public OpenDeviceHmsClient buildClient(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
        return new OpenDeviceHmsClient(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
    }
}
