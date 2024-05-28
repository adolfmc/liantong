package com.huawei.hms.support.api.transport;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.client.ApiClient;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface DatagramTransport {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.support.api.transport.DatagramTransport$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5073a {
        /* renamed from: a */
        void mo14135a(int i, IMessageEntity iMessageEntity);
    }

    void post(ApiClient apiClient, InterfaceC5073a interfaceC5073a);

    void send(ApiClient apiClient, InterfaceC5073a interfaceC5073a);
}
