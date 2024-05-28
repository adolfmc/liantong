package org.simalliance.openmobileapi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.simalliance.openmobileapi.service.ISmartcardService;
import org.simalliance.openmobileapi.service.ISmartcardServiceCallback;
import org.simalliance.openmobileapi.service.ISmartcardServiceReader;
import org.simalliance.openmobileapi.service.SmartcardError;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SEService {
    public static final String SERVICE_TAG = "SEService";
    public CallBack mCallerCallback;
    public ServiceConnection mConnection;
    public final Context mContext;
    public Map<String, Reader> mReaders;
    public volatile ISmartcardService mSmartcardService;
    public final Object mLock = new Object();
    public final ISmartcardServiceCallback mCallback = new ISmartcardServiceCallback.Stub() { // from class: org.simalliance.openmobileapi.SEService.1
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface CallBack {
        void serviceConnected(SEService sEService);
    }

    public SEService(Context context, CallBack callBack) {
        if (context != null) {
            this.mContext = context;
            this.mCallerCallback = callBack;
            this.mConnection = new ServiceConnection() { // from class: org.simalliance.openmobileapi.SEService.2
                @Override // android.content.ServiceConnection
                public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    SEService.this.mSmartcardService = ISmartcardService.Stub.asInterface(iBinder);
                    if (SEService.this.mCallerCallback != null) {
                        SEService.this.mCallerCallback.serviceConnected(SEService.this);
                    }
                    Log.v("SEService", "Service onServiceConnected");
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    SEService.this.mSmartcardService = null;
                    Log.v("SEService", "Service onServiceDisconnected");
                }
            };
            Intent intent = new Intent("org.simalliance.openmobileapi.BIND_SERVICE");
            intent.setPackage("org.simalliance.openmobileapi.service");
            boolean bindService = this.mContext.bindService(intent, this.mConnection, 1);
            Log.v("SEService", "bindingSuccessful: " + bindService);
            return;
        }
        throw new NullPointerException("context must not be null");
    }

    private ISmartcardServiceReader getReader(String str) {
        try {
            SmartcardError smartcardError = new SmartcardError();
            ISmartcardServiceReader reader = this.mSmartcardService.getReader(str, smartcardError);
            if (smartcardError.isSet()) {
                smartcardError.throwException();
            }
            return reader;
        } catch (RemoteException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private void initReadersMap() {
        try {
            String[] readers = this.mSmartcardService.getReaders();
            this.mReaders = new HashMap();
            for (String str : readers) {
                try {
                    this.mReaders.put(str, new Reader(this, getReader(str), str));
                } catch (Exception e) {
                    Log.w("SEService", "Error adding reader " + str, e);
                }
            }
        } catch (RemoteException e2) {
            throw new IllegalStateException(e2);
        }
    }

    private Reader[] sortReaders() {
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 1;
        while (true) {
            Reader reader = this.mReaders.get("SIM" + i2);
            if (reader == null) {
                break;
            }
            arrayList.add(reader);
            i2++;
        }
        int i3 = 1;
        while (true) {
            Reader reader2 = this.mReaders.get("eSE" + i3);
            if (reader2 == null) {
                break;
            }
            arrayList.add(reader2);
            i3++;
        }
        while (true) {
            Reader reader3 = this.mReaders.get("SD" + i);
            if (reader3 == null) {
                break;
            }
            arrayList.add(reader3);
            i++;
        }
        for (Reader reader4 : this.mReaders.values()) {
            if (!arrayList.contains(reader4)) {
                arrayList.add(reader4);
            }
        }
        return (Reader[]) arrayList.toArray(new Reader[arrayList.size()]);
    }

    public ISmartcardServiceCallback getCallback() {
        return this.mCallback;
    }

    public Reader[] getReaders() {
        if (this.mSmartcardService != null) {
            if (this.mReaders == null) {
                initReadersMap();
            }
            return sortReaders();
        }
        throw new IllegalStateException("service not connected to system");
    }

    public String getVersion() {
        return "3.0";
    }

    public boolean isConnected() {
        return this.mSmartcardService != null;
    }

    public void shutdown() {
        synchronized (this.mLock) {
            if (this.mSmartcardService != null && this.mReaders != null) {
                for (Reader reader : this.mReaders.values()) {
                    try {
                        reader.closeSessions();
                    } catch (Exception unused) {
                    }
                }
            }
            try {
                this.mContext.unbindService(this.mConnection);
            } catch (IllegalArgumentException unused2) {
            }
            this.mSmartcardService = null;
        }
    }
}
