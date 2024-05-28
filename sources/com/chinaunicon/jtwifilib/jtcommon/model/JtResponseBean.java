package com.chinaunicon.jtwifilib.jtcommon.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtResponseBean implements Parcelable {
    public static final Parcelable.Creator<JtResponseBean> CREATOR = new Parcelable.Creator<JtResponseBean>() { // from class: com.chinaunicon.jtwifilib.jtcommon.model.JtResponseBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JtResponseBean createFromParcel(Parcel parcel) {
            return new JtResponseBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JtResponseBean[] newArray(int i) {
            return new JtResponseBean[i];
        }
    };

    /* renamed from: ID */
    private long f9708ID;
    private int Result;
    private String return_Parameter;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setResult(int i) {
        this.Result = i;
    }

    public long getID() {
        return this.f9708ID;
    }

    public void setID(long j) {
        this.f9708ID = j;
    }

    public void setReturn_Parameter(String str) {
        this.return_Parameter = str;
    }

    public int getResult() {
        return this.Result;
    }

    public String getReturn_Parameter() {
        return this.return_Parameter;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Result);
        parcel.writeLong(this.f9708ID);
        parcel.writeString(this.return_Parameter);
    }

    public JtResponseBean() {
    }

    protected JtResponseBean(Parcel parcel) {
        this.Result = parcel.readInt();
        this.f9708ID = parcel.readInt();
        this.return_Parameter = parcel.readString();
    }
}
