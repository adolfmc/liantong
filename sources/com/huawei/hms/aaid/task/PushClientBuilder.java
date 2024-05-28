package com.huawei.hms.aaid.task;

import android.content.Context;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.ClientSettings;
import java.util.Arrays;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PushClientBuilder extends AbstractClientBuilder<PushClient, Api.ApiOptions.NoOptions> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hms.common.internal.AbstractClientBuilder
    public PushClient buildClient(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
        clientSettings.setApiName(Arrays.asList("HuaweiPush.API", "Core.API"));
        return new PushClient(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
    }
}
