package com.gmrz.appsdk.commlib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.gmrz.appsdk.commlib.api.ICommunicationClient;
import com.gmrz.appsdk.commlib.api.ICommunicationClientResponse;

/* renamed from: com.gmrz.appsdk.commlib.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafRemoteCommClient implements ICommunicationClient {

    /* renamed from: a */
    private final Context f10316a;

    /* compiled from: UafRemoteCommClient.java */
    /* renamed from: com.gmrz.appsdk.commlib.h$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC4419a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Intent f10317a;

        RunnableC4419a(Intent intent) {
            this.f10317a = intent;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f10317a.addFlags(268435456);
            UafRemoteCommClient.this.f10316a.startActivity(this.f10317a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UafRemoteCommClient(Context context) {
        this.f10316a = context;
    }

    @Override // com.gmrz.appsdk.commlib.api.ICommunicationClient
    public long sendRequest(String str, Object obj, ICommunicationClientResponse iCommunicationClientResponse) {
        try {
            Intent intent = (Intent) obj;
            intent.setClass(this.f10316a, IntentHelperActivity.class);
            UafRequestObject uafRequestObject = new UafRequestObject();
            uafRequestObject.m15841a(iCommunicationClientResponse);
            int m15842a = UafRequestObject.m15842a(uafRequestObject);
            IntentHelperActivity.f10261d = m15842a;
            intent.putExtra("requestId", m15842a);
            Context context = this.f10316a;
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
                this.f10316a.startActivity(intent);
            } else {
                ((Activity) context).runOnUiThread(new RunnableC4419a(intent));
            }
            return m15842a;
        } catch (Exception unused) {
            return -1L;
        }
    }
}
