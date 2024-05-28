package com.xuhao.didi.socket.client.sdk.client;

import com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions;
import com.xuhao.didi.core.protocol.IReaderProtocol;
import com.xuhao.didi.socket.client.impl.client.action.ActionDispatcher;
import com.xuhao.didi.socket.client.sdk.client.connection.AbsReconnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.DefaultReconnectManager;
import com.xuhao.didi.socket.client.sdk.client.connection.abilities.IConfiguration;
import com.xuhao.didi.socket.common.interfaces.default_protocol.DefaultNormalReaderProtocol;
import java.nio.ByteOrder;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class OkSocketOptions implements IIOCoreOptions {
    private static boolean isDebug;
    private boolean isCallbackInIndependentThread;
    private boolean isConnectionHolden;
    private ThreadModeToken mCallbackThreadModeToken;
    private int mConnectTimeoutSecond;
    private IOThreadMode mIOThreadMode;
    private int mMaxReadDataMB;
    private OkSocketFactory mOkSocketFactory;
    private int mPulseFeedLoseTimes;
    private long mPulseFrequency;
    private ByteOrder mReadByteOrder;
    private int mReadPackageBytes;
    private IReaderProtocol mReaderProtocol;
    private AbsReconnectionManager mReconnectionManager;
    private OkSocketSSLConfig mSSLConfig;
    private ByteOrder mWriteOrder;
    private int mWritePackageBytes;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum IOThreadMode {
        SIMPLEX,
        DUPLEX
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class ThreadModeToken {
        public abstract void handleCallbackEvent(ActionDispatcher.ActionRunnable actionRunnable);
    }

    private OkSocketOptions() {
    }

    public static void setIsDebug(boolean z) {
        isDebug = z;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Builder {
        private OkSocketOptions mOptions;

        public Builder() {
            this(OkSocketOptions.getDefault());
        }

        public Builder(IConfiguration iConfiguration) {
            this(iConfiguration.getOption());
        }

        public Builder(OkSocketOptions okSocketOptions) {
            this.mOptions = okSocketOptions;
        }

        public Builder setIOThreadMode(IOThreadMode iOThreadMode) {
            this.mOptions.mIOThreadMode = iOThreadMode;
            return this;
        }

        public Builder setMaxReadDataMB(int i) {
            this.mOptions.mMaxReadDataMB = i;
            return this;
        }

        public Builder setSSLConfig(OkSocketSSLConfig okSocketSSLConfig) {
            this.mOptions.mSSLConfig = okSocketSSLConfig;
            return this;
        }

        public Builder setReaderProtocol(IReaderProtocol iReaderProtocol) {
            this.mOptions.mReaderProtocol = iReaderProtocol;
            return this;
        }

        public Builder setPulseFrequency(long j) {
            this.mOptions.mPulseFrequency = j;
            return this;
        }

        public Builder setConnectionHolden(boolean z) {
            this.mOptions.isConnectionHolden = z;
            return this;
        }

        public Builder setPulseFeedLoseTimes(int i) {
            this.mOptions.mPulseFeedLoseTimes = i;
            return this;
        }

        public Builder setWriteOrder(ByteOrder byteOrder) {
            setWriteByteOrder(byteOrder);
            return this;
        }

        public Builder setWriteByteOrder(ByteOrder byteOrder) {
            this.mOptions.mWriteOrder = byteOrder;
            return this;
        }

        public Builder setReadByteOrder(ByteOrder byteOrder) {
            this.mOptions.mReadByteOrder = byteOrder;
            return this;
        }

        public Builder setWritePackageBytes(int i) {
            this.mOptions.mWritePackageBytes = i;
            return this;
        }

        public Builder setReadPackageBytes(int i) {
            this.mOptions.mReadPackageBytes = i;
            return this;
        }

        public Builder setConnectTimeoutSecond(int i) {
            this.mOptions.mConnectTimeoutSecond = i;
            return this;
        }

        public Builder setReconnectionManager(AbsReconnectionManager absReconnectionManager) {
            this.mOptions.mReconnectionManager = absReconnectionManager;
            return this;
        }

        public Builder setSocketFactory(OkSocketFactory okSocketFactory) {
            this.mOptions.mOkSocketFactory = okSocketFactory;
            return this;
        }

        public Builder setCallbackThreadModeToken(ThreadModeToken threadModeToken) {
            this.mOptions.mCallbackThreadModeToken = threadModeToken;
            return this;
        }

        public OkSocketOptions build() {
            return this.mOptions;
        }
    }

    public IOThreadMode getIOThreadMode() {
        return this.mIOThreadMode;
    }

    public long getPulseFrequency() {
        return this.mPulseFrequency;
    }

    public OkSocketSSLConfig getSSLConfig() {
        return this.mSSLConfig;
    }

    public OkSocketFactory getOkSocketFactory() {
        return this.mOkSocketFactory;
    }

    public int getConnectTimeoutSecond() {
        return this.mConnectTimeoutSecond;
    }

    public boolean isConnectionHolden() {
        return this.isConnectionHolden;
    }

    public int getPulseFeedLoseTimes() {
        return this.mPulseFeedLoseTimes;
    }

    public AbsReconnectionManager getReconnectionManager() {
        return this.mReconnectionManager;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions
    public boolean isDebug() {
        return isDebug;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions
    public int getWritePackageBytes() {
        return this.mWritePackageBytes;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions
    public int getReadPackageBytes() {
        return this.mReadPackageBytes;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions
    public ByteOrder getWriteByteOrder() {
        return this.mWriteOrder;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions
    public IReaderProtocol getReaderProtocol() {
        return this.mReaderProtocol;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions
    public int getMaxReadDataMB() {
        return this.mMaxReadDataMB;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions
    public ByteOrder getReadByteOrder() {
        return this.mReadByteOrder;
    }

    public ThreadModeToken getCallbackThreadModeToken() {
        return this.mCallbackThreadModeToken;
    }

    public boolean isCallbackInIndependentThread() {
        return this.isCallbackInIndependentThread;
    }

    public static OkSocketOptions getDefault() {
        OkSocketOptions okSocketOptions = new OkSocketOptions();
        okSocketOptions.mPulseFrequency = 5000L;
        okSocketOptions.mIOThreadMode = IOThreadMode.DUPLEX;
        okSocketOptions.mReaderProtocol = new DefaultNormalReaderProtocol();
        okSocketOptions.mMaxReadDataMB = 5;
        okSocketOptions.mConnectTimeoutSecond = 3;
        okSocketOptions.mWritePackageBytes = 100;
        okSocketOptions.mReadPackageBytes = 50;
        okSocketOptions.mReadByteOrder = ByteOrder.BIG_ENDIAN;
        okSocketOptions.mWriteOrder = ByteOrder.BIG_ENDIAN;
        okSocketOptions.isConnectionHolden = true;
        okSocketOptions.mPulseFeedLoseTimes = 5;
        okSocketOptions.mReconnectionManager = new DefaultReconnectManager();
        okSocketOptions.mSSLConfig = null;
        okSocketOptions.mOkSocketFactory = null;
        okSocketOptions.isCallbackInIndependentThread = true;
        okSocketOptions.mCallbackThreadModeToken = null;
        return okSocketOptions;
    }
}
