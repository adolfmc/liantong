package com.gmrz.appsdk.commlib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.gmrz.appsdk.commlib.UafRemoteServiceConnector;
import com.gmrz.appsdk.commlib.api.ICommunicationClient;
import com.gmrz.appsdk.commlib.api.ICommunicationClientResponse;
import org.fidoalliance.aidl.IUAFOperation;
import org.fidoalliance.aidl.IUAFResponseListener;

/* renamed from: com.gmrz.appsdk.commlib.i */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafRemoteServiceClient implements ICommunicationClient {

    /* renamed from: a */
    private final Context f10319a;

    /* renamed from: b */
    private InterfaceC4421b f10320b;

    /* compiled from: UafRemoteServiceClient.java */
    /* renamed from: com.gmrz.appsdk.commlib.i$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class C4420a implements UafRemoteServiceConnector.InterfaceC4423b {

        /* renamed from: a */
        final /* synthetic */ Intent f10321a;

        /* renamed from: b */
        final /* synthetic */ ICommunicationClientResponse f10322b;

        C4420a(Intent intent, ICommunicationClientResponse iCommunicationClientResponse) {
            this.f10321a = intent;
            this.f10322b = iCommunicationClientResponse;
        }

        @Override // com.gmrz.appsdk.commlib.UafRemoteServiceConnector.InterfaceC4423b
        /* renamed from: a */
        public void mo15803a(boolean z) {
            if (z) {
                return;
            }
            UafRemoteServiceClient.this.f10320b.mo15809a();
        }

        @Override // com.gmrz.appsdk.commlib.UafRemoteServiceConnector.InterfaceC4423b
        /* renamed from: a */
        public void mo15804a(IUAFOperation iUAFOperation) {
            if (iUAFOperation == null) {
                UafRemoteServiceClient.this.f10320b.mo15809a();
                return;
            }
            try {
                iUAFOperation.process(this.f10321a, (IUAFResponseListener) this.f10322b);
            } catch (RemoteException e) {
                UafRemoteServiceClient.this.f10320b.mo15809a();
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: UafRemoteServiceClient.java */
    /* renamed from: com.gmrz.appsdk.commlib.i$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC4421b {
        /* renamed from: a */
        void mo15809a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UafRemoteServiceClient(Context context) {
        this.f10319a = context;
    }

    @Override // com.gmrz.appsdk.commlib.api.ICommunicationClient
    public long sendRequest(String str, Object obj, ICommunicationClientResponse iCommunicationClientResponse) {
        String[] split = str.split("#");
        Intent intent = (Intent) obj;
        UafRequestObject uafRequestObject = new UafRequestObject();
        uafRequestObject.m15841a(iCommunicationClientResponse);
        int m15842a = UafRequestObject.m15842a(uafRequestObject);
        intent.putExtra("requestId", m15842a);
        intent.setComponent(new ComponentName(this.f10319a, split[1]));
        UafRemoteServiceConnector m15808a = UafRemoteServiceConnector.m15808a();
        m15808a.m15806a(new C4420a(intent, iCommunicationClientResponse));
        try {
            m15808a.m15807a(this.f10319a, split[0]);
        } catch (Exception e) {
            this.f10320b.mo15809a();
            e.printStackTrace();
        }
        return m15842a;
    }

    /* renamed from: a */
    public void m15811a(InterfaceC4421b interfaceC4421b) {
        this.f10320b = interfaceC4421b;
    }
}
