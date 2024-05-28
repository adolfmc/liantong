package com.froad.eid.unify.bean;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.eidlink.idocr.sdk.bean.EidlinkInitParams;
import com.froad.eid.unify.utils.StringUtil;
import com.p189cn.froad.clouddecodingsdk.core.EidFFTInitParams;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IDSEConfig implements Parcelable {
    public static final Parcelable.Creator<IDSEConfig> CREATOR = new Parcelable.Creator<IDSEConfig>() { // from class: com.froad.eid.unify.bean.IDSEConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IDSEConfig createFromParcel(Parcel parcel) {
            return new IDSEConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IDSEConfig[] newArray(int i) {
            return new IDSEConfig[i];
        }
    };
    public final String appId;
    public final String binName;

    /* renamed from: ip */
    public final String f10129ip;
    public final String licName;
    public final int port;
    public final int pubkeyIndex;

    /* compiled from: Proguard */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class Builder {
        private String appId;
        private String binName;

        /* renamed from: ip */
        private String f10130ip;
        private String licName;
        private int port;
        private int pubkeyIndex;

        public Builder() {
        }

        public Builder(IDSEConfig iDSEConfig) {
            this.appId = iDSEConfig.appId;
            this.f10130ip = iDSEConfig.f10129ip;
            this.port = iDSEConfig.port;
            this.pubkeyIndex = iDSEConfig.pubkeyIndex;
            this.licName = iDSEConfig.licName;
            this.binName = iDSEConfig.binName;
        }

        public Builder appId(String str) {
            if (StringUtil.isEmpty(str)) {
                throw new NullPointerException("mcid is empty");
            }
            this.appId = str;
            return this;
        }

        public Builder binName(String str) {
            this.binName = str;
            return this;
        }

        public IDSEConfig build() {
            if (StringUtil.isEmpty(this.appId)) {
                throw new NullPointerException("mcid is empty");
            }
            if (StringUtil.isEmpty(this.f10130ip)) {
                throw new NullPointerException("ip is empty");
            }
            return new IDSEConfig(this);
        }

        /* renamed from: ip */
        public Builder m15916ip(String str) {
            if (StringUtil.isEmpty(str)) {
                throw new NullPointerException("ip is empty");
            }
            this.f10130ip = str;
            return this;
        }

        public Builder licName(String str) {
            this.licName = str;
            return this;
        }

        public Builder port(int i) {
            this.port = i;
            return this;
        }

        public Builder pubkeyIndex(int i) {
            this.pubkeyIndex = i;
            return this;
        }
    }

    public IDSEConfig(Parcel parcel) {
        this.appId = parcel.readString();
        this.f10129ip = parcel.readString();
        this.port = parcel.readInt();
        this.pubkeyIndex = parcel.readInt();
        this.licName = parcel.readString();
        this.binName = parcel.readString();
    }

    public IDSEConfig(Builder builder) {
        this.appId = builder.appId;
        this.f10129ip = builder.f10130ip;
        this.port = builder.port;
        this.pubkeyIndex = builder.pubkeyIndex;
        this.licName = builder.licName;
        this.binName = builder.binName;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getBinName() {
        return this.binName;
    }

    public String getIp() {
        return this.f10129ip;
    }

    public String getLicName() {
        return this.licName;
    }

    public int getPort() {
        return this.port;
    }

    public int getPubkeyIndex() {
        return this.pubkeyIndex;
    }

    public Builder newBuilder(IDSEConfig iDSEConfig) {
        return new Builder(iDSEConfig);
    }

    public EidlinkInitParams toEidFFTParams(Context context) {
        return new EidFFTInitParams(context, this.appId, this.f10129ip, this.port, this.pubkeyIndex, this.licName, this.binName);
    }

    public EidlinkInitParams toEidlinkParams(Context context) {
        return new EidlinkInitParams(context, this.appId, this.f10129ip, this.port, this.pubkeyIndex);
    }

    public String toString() {
        return "IDSEConfig{appId='" + this.appId + "', ip='" + this.f10129ip + "', port=" + this.port + ", pubkeyIndex=" + this.pubkeyIndex + ", licName='" + this.licName + "', binName='" + this.binName + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.appId);
        parcel.writeString(this.f10129ip);
        parcel.writeInt(this.port);
        parcel.writeInt(this.pubkeyIndex);
        parcel.writeString(this.licName);
        parcel.writeString(this.binName);
    }
}
