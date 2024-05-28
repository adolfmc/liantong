package org.simalliance.openmobileapi;

import android.os.RemoteException;
import android.util.Log;
import org.simalliance.openmobileapi.service.ISmartcardServiceChannel;
import org.simalliance.openmobileapi.service.SmartcardError;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Channel {
    public final ISmartcardServiceChannel mChannel;
    public final Object mLock = new Object();
    public Session mSession;

    public Channel(Session session, ISmartcardServiceChannel iSmartcardServiceChannel) {
        this.mSession = session;
        this.mChannel = iSmartcardServiceChannel;
    }

    public void close() {
        if (this.mSession.getReader().getSEService() != null && this.mSession.getReader().getSEService().isConnected()) {
            if (this.mChannel != null) {
                if (isClosed()) {
                    return;
                }
                synchronized (this.mLock) {
                    try {
                        SmartcardError smartcardError = new SmartcardError();
                        this.mChannel.close(smartcardError);
                        if (smartcardError.isSet()) {
                            smartcardError.throwException();
                        }
                    } catch (Exception e) {
                        Log.w(getClass().getSimpleName(), "Error closing channel", e);
                    }
                }
                return;
            }
            throw new IllegalStateException("channel must not be null");
        }
        throw new IllegalStateException("service not connected to system");
    }

    public byte[] getSelectResponse() {
        if (this.mSession.getReader().getSEService() != null && this.mSession.getReader().getSEService().isConnected()) {
            ISmartcardServiceChannel iSmartcardServiceChannel = this.mChannel;
            if (iSmartcardServiceChannel != null) {
                try {
                    return iSmartcardServiceChannel.getSelectResponse();
                } catch (RemoteException e) {
                    throw new IllegalStateException(e.getMessage());
                }
            }
            throw new IllegalStateException("channel must not be null");
        }
        throw new IllegalStateException("service not connected to system");
    }

    public Session getSession() {
        return this.mSession;
    }

    public boolean isBasicChannel() {
        if (this.mSession.getReader().getSEService() != null && this.mSession.getReader().getSEService().isConnected()) {
            ISmartcardServiceChannel iSmartcardServiceChannel = this.mChannel;
            if (iSmartcardServiceChannel != null) {
                try {
                    return iSmartcardServiceChannel.isBasicChannel();
                } catch (RemoteException e) {
                    throw new IllegalStateException(e.getMessage());
                }
            }
            throw new IllegalStateException("channel must not be null");
        }
        throw new IllegalStateException("service not connected to system");
    }

    public boolean isClosed() {
        if (this.mSession.getReader().getSEService() != null && this.mSession.getReader().getSEService().isConnected()) {
            ISmartcardServiceChannel iSmartcardServiceChannel = this.mChannel;
            if (iSmartcardServiceChannel != null) {
                try {
                    return iSmartcardServiceChannel.isClosed();
                } catch (RemoteException e) {
                    throw new IllegalStateException(e.getMessage());
                }
            }
            throw new IllegalStateException("channel must not be null");
        }
        throw new IllegalStateException("service not connected to system");
    }

    public boolean selectNext() {
        boolean selectNext;
        if (this.mSession.getReader().getSEService() != null && this.mSession.getReader().getSEService().isConnected()) {
            ISmartcardServiceChannel iSmartcardServiceChannel = this.mChannel;
            if (iSmartcardServiceChannel != null) {
                try {
                    if (!iSmartcardServiceChannel.isClosed()) {
                        synchronized (this.mLock) {
                            try {
                                try {
                                    SmartcardError smartcardError = new SmartcardError();
                                    selectNext = this.mChannel.selectNext(smartcardError);
                                    if (smartcardError.isSet()) {
                                        smartcardError.throwException();
                                    }
                                } catch (RemoteException e) {
                                    throw new IllegalStateException(e.getMessage());
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        return selectNext;
                    }
                    throw new IllegalStateException("channel is closed");
                } catch (RemoteException e2) {
                    throw new IllegalStateException(e2.getMessage());
                }
            }
            throw new IllegalStateException("channel must not be null");
        }
        throw new IllegalStateException("service not connected to system");
    }

    public byte[] transmit(byte[] bArr) {
        byte[] transmit;
        if (this.mSession.getReader().getSEService() != null && this.mSession.getReader().getSEService().isConnected()) {
            if (this.mChannel != null) {
                synchronized (this.mLock) {
                    try {
                        try {
                            SmartcardError smartcardError = new SmartcardError();
                            transmit = this.mChannel.transmit(bArr, smartcardError);
                            if (smartcardError.isSet()) {
                                smartcardError.throwException();
                            }
                        } catch (RemoteException e) {
                            throw new IllegalStateException(e.getMessage());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return transmit;
            }
            throw new IllegalStateException("channel must not be null");
        }
        throw new IllegalStateException("service not connected to system");
    }
}
