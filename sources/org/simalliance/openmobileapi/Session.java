package org.simalliance.openmobileapi;

import android.os.RemoteException;
import android.util.Log;
import org.simalliance.openmobileapi.service.ISmartcardServiceChannel;
import org.simalliance.openmobileapi.service.ISmartcardServiceSession;
import org.simalliance.openmobileapi.service.SmartcardError;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Session {
    public final Object mLock = new Object();
    public final Reader mReader;
    public final ISmartcardServiceSession mSession;

    public Session(ISmartcardServiceSession iSmartcardServiceSession, Reader reader) {
        this.mReader = reader;
        this.mSession = iSmartcardServiceSession;
    }

    public void close() {
        if (this.mReader.getSEService() != null && this.mReader.getSEService().isConnected()) {
            if (this.mSession != null) {
                synchronized (this.mLock) {
                    try {
                        SmartcardError smartcardError = new SmartcardError();
                        this.mSession.close(smartcardError);
                        if (smartcardError.isSet()) {
                            smartcardError.throwException();
                        }
                    } catch (Exception e) {
                        Log.e(getClass().getSimpleName(), "Error closing session", e);
                    }
                }
                return;
            }
            return;
        }
        throw new IllegalStateException("service not connected to system");
    }

    public void closeChannels() {
        if (this.mReader.getSEService() != null && this.mReader.getSEService().isConnected()) {
            if (this.mSession != null) {
                synchronized (this.mLock) {
                    try {
                        SmartcardError smartcardError = new SmartcardError();
                        this.mSession.closeChannels(smartcardError);
                        if (smartcardError.isSet()) {
                            smartcardError.throwException();
                        }
                    } catch (Exception e) {
                        Log.e(getClass().getSimpleName(), "Error closing channels", e);
                    }
                }
                return;
            }
            return;
        }
        throw new IllegalStateException("service not connected to system");
    }

    public byte[] getATR() {
        if (this.mReader.getSEService() != null && this.mReader.getSEService().isConnected()) {
            ISmartcardServiceSession iSmartcardServiceSession = this.mSession;
            if (iSmartcardServiceSession != null) {
                try {
                    return iSmartcardServiceSession.getAtr();
                } catch (RemoteException e) {
                    throw new IllegalStateException(e.getMessage());
                }
            }
            throw new IllegalStateException("service session is null");
        }
        throw new IllegalStateException("service not connected to system");
    }

    public Reader getReader() {
        return this.mReader;
    }

    public boolean isClosed() {
        try {
            if (this.mSession != null) {
                if (!this.mSession.isClosed()) {
                    return false;
                }
            }
            return true;
        } catch (RemoteException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public Channel openBasicChannel(byte[] bArr, byte b) {
        if (this.mReader.getSEService() != null && this.mReader.getSEService().isConnected()) {
            if (this.mSession != null) {
                if (getReader() != null) {
                    synchronized (this.mLock) {
                        try {
                            try {
                                SmartcardError smartcardError = new SmartcardError();
                                ISmartcardServiceChannel openBasicChannel = this.mSession.openBasicChannel(bArr, b, this.mReader.getSEService().getCallback(), smartcardError);
                                if (smartcardError.isSet()) {
                                    smartcardError.throwException();
                                }
                                if (openBasicChannel == null) {
                                    return null;
                                }
                                return new Channel(this, openBasicChannel);
                            } catch (RemoteException e) {
                                throw new IllegalStateException(e.getMessage());
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
                throw new IllegalStateException("reader must not be null");
            }
            throw new IllegalStateException("service session is null");
        }
        throw new IllegalStateException("service not connected to system");
    }

    public Channel openLogicalChannel(byte[] bArr, byte b) {
        if (this.mReader.getSEService() != null && this.mReader.getSEService().isConnected()) {
            if (this.mSession != null) {
                if (getReader() != null) {
                    synchronized (this.mLock) {
                        try {
                            try {
                                SmartcardError smartcardError = new SmartcardError();
                                ISmartcardServiceChannel openLogicalChannel = this.mSession.openLogicalChannel(bArr, b, this.mReader.getSEService().getCallback(), smartcardError);
                                if (smartcardError.isSet()) {
                                    smartcardError.throwException();
                                }
                                if (openLogicalChannel == null) {
                                    return null;
                                }
                                return new Channel(this, openLogicalChannel);
                            } catch (RemoteException e) {
                                throw new IllegalStateException(e.getMessage());
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
                throw new IllegalStateException("reader must not be null");
            }
            throw new IllegalStateException("service session is null");
        }
        throw new IllegalStateException("service not connected to system");
    }

    public Channel openBasicChannel(byte[] bArr) {
        return openBasicChannel(bArr, (byte) 0);
    }

    public Channel openLogicalChannel(byte[] bArr) {
        return openLogicalChannel(bArr, (byte) 0);
    }
}
