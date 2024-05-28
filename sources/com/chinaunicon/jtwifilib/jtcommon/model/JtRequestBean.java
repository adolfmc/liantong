package com.chinaunicon.jtwifilib.jtcommon.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtRequestBean implements Parcelable {
    public static final Parcelable.Creator<JtRequestBean> CREATOR = new Parcelable.Creator<JtRequestBean>() { // from class: com.chinaunicon.jtwifilib.jtcommon.model.JtRequestBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JtRequestBean createFromParcel(Parcel parcel) {
            return new JtRequestBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JtRequestBean[] newArray(int i) {
            return new JtRequestBean[i];
        }
    };

    /* renamed from: ID */
    private long f9707ID;
    private String Parameter;
    private String Plugin_Name;
    private String RPCMethod;
    private String Version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setRPCMethod(String str) {
        this.RPCMethod = str;
    }

    public void setID(long j) {
        this.f9707ID = j;
    }

    public void setPlugin_Name(String str) {
        this.Plugin_Name = str;
    }

    public void setVersion(String str) {
        this.Version = str;
    }

    public void setParameter(String str) {
        this.Parameter = str;
    }

    public String getRPCMethod() {
        return this.RPCMethod;
    }

    public double getID() {
        return this.f9707ID;
    }

    public String getPlugin_Name() {
        return this.Plugin_Name;
    }

    public String getVersion() {
        return this.Version;
    }

    public String getParameter() {
        return this.Parameter;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.RPCMethod);
        parcel.writeDouble(this.f9707ID);
        parcel.writeString(this.Plugin_Name);
        parcel.writeString(this.Version);
        parcel.writeString(this.Parameter);
    }

    public JtRequestBean() {
    }

    protected JtRequestBean(Parcel parcel) {
        this.RPCMethod = parcel.readString();
        this.f9707ID = parcel.readInt();
        this.Plugin_Name = parcel.readString();
        this.Version = parcel.readString();
        this.Parameter = parcel.readString();
    }
}
