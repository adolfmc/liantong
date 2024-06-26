package com.xiaomi.mipush.sdk;

import com.xiaomi.push.service.module.PushChannelRegion;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PushConfiguration {
    private boolean mGeoEnable;
    private boolean mOpenCOSPush;
    private boolean mOpenFCMPush;
    private boolean mOpenFTOSPush;
    private boolean mOpenHmsPush;
    private PushChannelRegion mRegion;

    public PushConfiguration() {
        this.mRegion = PushChannelRegion.China;
        this.mOpenHmsPush = false;
        this.mOpenFCMPush = false;
        this.mOpenCOSPush = false;
        this.mOpenFTOSPush = false;
    }

    private PushConfiguration(PushConfigurationBuilder pushConfigurationBuilder) {
        this.mRegion = pushConfigurationBuilder.mRegion == null ? PushChannelRegion.China : pushConfigurationBuilder.mRegion;
        this.mOpenHmsPush = pushConfigurationBuilder.mOpenHmsPush;
        this.mOpenFCMPush = pushConfigurationBuilder.mOpenFCMPush;
        this.mOpenCOSPush = pushConfigurationBuilder.mOpenCOSPush;
        this.mOpenFTOSPush = pushConfigurationBuilder.mOpenFTOSPush;
    }

    public PushChannelRegion getRegion() {
        return this.mRegion;
    }

    public void setRegion(PushChannelRegion pushChannelRegion) {
        this.mRegion = pushChannelRegion;
    }

    public boolean getOpenHmsPush() {
        return this.mOpenHmsPush;
    }

    public void setOpenHmsPush(boolean z) {
        this.mOpenHmsPush = z;
    }

    public boolean getOpenFCMPush() {
        return this.mOpenFCMPush;
    }

    public void setOpenFCMPush(boolean z) {
        this.mOpenFCMPush = z;
    }

    public boolean getOpenCOSPush() {
        return this.mOpenCOSPush;
    }

    public void setOpenCOSPush(boolean z) {
        this.mOpenCOSPush = z;
    }

    public void setOpenFTOSPush(boolean z) {
        this.mOpenFTOSPush = z;
    }

    public boolean getOpenFTOSPush() {
        return this.mOpenFTOSPush;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PushConfiguration{");
        stringBuffer.append("Region:");
        PushChannelRegion pushChannelRegion = this.mRegion;
        if (pushChannelRegion == null) {
            stringBuffer.append("null");
        } else {
            stringBuffer.append(pushChannelRegion.name());
        }
        stringBuffer.append(",mOpenHmsPush:" + this.mOpenHmsPush);
        stringBuffer.append(",mOpenFCMPush:" + this.mOpenFCMPush);
        stringBuffer.append(",mOpenCOSPush:" + this.mOpenCOSPush);
        stringBuffer.append(",mOpenFTOSPush:" + this.mOpenFTOSPush);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class PushConfigurationBuilder {
        private boolean mGeoEnable;
        private boolean mOpenCOSPush;
        private boolean mOpenFCMPush;
        private boolean mOpenFTOSPush;
        private boolean mOpenHmsPush;
        private PushChannelRegion mRegion;

        public PushConfigurationBuilder region(PushChannelRegion pushChannelRegion) {
            this.mRegion = pushChannelRegion;
            return this;
        }

        public PushConfigurationBuilder openHmsPush(boolean z) {
            this.mOpenHmsPush = z;
            return this;
        }

        public PushConfigurationBuilder openFCMPush(boolean z) {
            this.mOpenFCMPush = z;
            return this;
        }

        public PushConfigurationBuilder openCOSPush(boolean z) {
            this.mOpenCOSPush = z;
            return this;
        }

        public PushConfigurationBuilder openFTOSPush(boolean z) {
            this.mOpenFTOSPush = z;
            return this;
        }

        public PushConfiguration build() {
            return new PushConfiguration(this);
        }
    }
}
