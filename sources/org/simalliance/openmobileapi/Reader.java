package org.simalliance.openmobileapi;

import android.os.RemoteException;
import java.io.IOException;
import org.simalliance.openmobileapi.service.ISmartcardServiceReader;
import org.simalliance.openmobileapi.service.ISmartcardServiceSession;
import org.simalliance.openmobileapi.service.SmartcardError;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Reader {
    public final Object mLock = new Object();
    public final String mName;
    public ISmartcardServiceReader mReader;
    public final SEService mService;

    public Reader(SEService sEService, ISmartcardServiceReader iSmartcardServiceReader, String str) {
        this.mName = str;
        this.mService = sEService;
        this.mReader = iSmartcardServiceReader;
    }

    public void closeSessions() {
        SEService sEService = this.mService;
        if (sEService != null && sEService.isConnected()) {
            synchronized (this.mLock) {
                try {
                    this.mReader.closeSessions(new SmartcardError());
                } catch (RemoteException e) {
                    throw new IllegalStateException(e.getMessage());
                }
            }
            return;
        }
        throw new IllegalStateException("service is not connected");
    }

    public String getName() {
        return this.mName;
    }

    public SEService getSEService() {
        return this.mService;
    }

    public boolean isSecureElementPresent() {
        SEService sEService = this.mService;
        if (sEService != null && sEService.isConnected()) {
            try {
                return this.mReader.isSecureElementPresent();
            } catch (RemoteException e) {
                throw new IllegalStateException(e.getMessage());
            }
        }
        throw new IllegalStateException("service is not connected");
    }

    public Session openSession() {
        Session session;
        SEService sEService = this.mService;
        if (sEService != null && sEService.isConnected()) {
            synchronized (this.mLock) {
                try {
                    try {
                        SmartcardError smartcardError = new SmartcardError();
                        ISmartcardServiceSession openSession = this.mReader.openSession(smartcardError);
                        if (smartcardError.isSet()) {
                            smartcardError.throwException();
                        }
                        session = new Session(openSession, this);
                    } catch (RemoteException e) {
                        throw new IOException(e.getMessage());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return session;
        }
        throw new IllegalStateException("service is not connected");
    }
}
