package com.xuhao.didi.socket.client.sdk.client;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class OkSocketSSLConfig {
    private SSLSocketFactory mCustomSSLFactory;
    private KeyManager[] mKeyManagers;
    private String mProtocol;
    private TrustManager[] mTrustManagers;

    private OkSocketSSLConfig() {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Builder {
        private OkSocketSSLConfig mConfig = new OkSocketSSLConfig();

        public Builder setProtocol(String str) {
            this.mConfig.mProtocol = str;
            return this;
        }

        public Builder setTrustManagers(TrustManager[] trustManagerArr) {
            this.mConfig.mTrustManagers = trustManagerArr;
            return this;
        }

        public Builder setKeyManagers(KeyManager[] keyManagerArr) {
            this.mConfig.mKeyManagers = keyManagerArr;
            return this;
        }

        public Builder setCustomSSLFactory(SSLSocketFactory sSLSocketFactory) {
            this.mConfig.mCustomSSLFactory = sSLSocketFactory;
            return this;
        }

        public OkSocketSSLConfig build() {
            return this.mConfig;
        }
    }

    public KeyManager[] getKeyManagers() {
        return this.mKeyManagers;
    }

    public String getProtocol() {
        return this.mProtocol;
    }

    public TrustManager[] getTrustManagers() {
        return this.mTrustManagers;
    }

    public SSLSocketFactory getCustomSSLFactory() {
        return this.mCustomSSLFactory;
    }
}
