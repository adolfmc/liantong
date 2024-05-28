package com.gmrz.appsdk.commlib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import org.fidoalliance.aidl.IUAFOperation;

/* renamed from: com.gmrz.appsdk.commlib.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class UafRemoteServiceConnector {

    /* renamed from: b */
    private static final UafRemoteServiceConnector f10324b = new UafRemoteServiceConnector();

    /* renamed from: a */
    private InterfaceC4423b f10325a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: UafRemoteServiceConnector.java */
    /* renamed from: com.gmrz.appsdk.commlib.j$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    class ServiceConnectionC4422a implements ServiceConnection {
        ServiceConnectionC4422a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            UafRemoteServiceConnector.this.f10325a.mo15804a(IUAFOperation.AbstractBinderC13419a.m227a(iBinder));
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: UafRemoteServiceConnector.java */
    /* renamed from: com.gmrz.appsdk.commlib.j$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC4423b {
        /* renamed from: a */
        void mo15804a(IUAFOperation iUAFOperation);

        /* renamed from: a */
        void mo15803a(boolean z);
    }

    private UafRemoteServiceConnector() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static UafRemoteServiceConnector m15808a() {
        return f10324b;
    }

    /* renamed from: a */
    public void m15806a(InterfaceC4423b interfaceC4423b) {
        this.f10325a = interfaceC4423b;
    }

    /* renamed from: a */
    public void m15807a(Context context, String str) {
        if (context == null) {
            Log.wtf("UafRemoteServiceConnect", "try bind fido client service, context is null");
        } else if (TextUtils.isEmpty(str)) {
            Log.wtf("UafRemoteServiceConnect", "try bind fido client service, target pkg name not set");
        } else if (this.f10325a == null) {
            Log.wtf("UafRemoteServiceConnect", "fido client service connection status callback must be not null");
        } else {
            Intent intent = new Intent();
            intent.setAction("org.fidoalliance.aidl.FIDO_OPERATION");
            intent.setType("application/fido.uaf_client+json");
            intent.setPackage(str);
            this.f10325a.mo15803a(context.bindService(intent, new ServiceConnectionC4422a(), 1));
        }
    }
}
