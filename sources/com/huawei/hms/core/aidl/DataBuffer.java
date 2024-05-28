package com.huawei.hms.core.aidl;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DataBuffer implements Parcelable {
    public static final Parcelable.Creator<DataBuffer> CREATOR = new C4901a();
    public String URI;

    /* renamed from: a */
    private int f11183a;

    /* renamed from: b */
    private Bundle f11184b;
    public Bundle header;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.core.aidl.DataBuffer$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    class C4901a implements Parcelable.Creator<DataBuffer> {
        C4901a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public DataBuffer createFromParcel(Parcel parcel) {
            return new DataBuffer(parcel, (C4901a) null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public DataBuffer[] newArray(int i) {
            return new DataBuffer[i];
        }
    }

    /* synthetic */ DataBuffer(Parcel parcel, C4901a c4901a) {
        this(parcel);
    }

    /* renamed from: a */
    private static ClassLoader m15085a(Class cls) {
        return cls.getClassLoader();
    }

    public DataBuffer addBody(Bundle bundle) {
        this.f11184b = bundle;
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getBody() {
        return this.f11184b;
    }

    public int getBodySize() {
        return this.f11184b == null ? 0 : 1;
    }

    public int getProtocol() {
        return this.f11183a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f11183a);
        parcel.writeString(this.URI);
        parcel.writeBundle(this.header);
        parcel.writeBundle(this.f11184b);
    }

    private DataBuffer(Parcel parcel) {
        this.header = null;
        this.f11183a = 1;
        this.f11184b = null;
        m15086a(parcel);
    }

    /* renamed from: a */
    private void m15086a(Parcel parcel) {
        this.f11183a = parcel.readInt();
        this.URI = parcel.readString();
        this.header = parcel.readBundle(m15085a(Bundle.class));
        this.f11184b = parcel.readBundle(m15085a(Bundle.class));
    }

    public DataBuffer() {
        this.header = null;
        this.f11183a = 1;
        this.f11184b = null;
    }

    public DataBuffer(String str) {
        this.header = null;
        this.f11183a = 1;
        this.f11184b = null;
        this.URI = str;
    }

    public DataBuffer(String str, int i) {
        this.header = null;
        this.f11184b = null;
        this.URI = str;
        this.f11183a = i;
    }
}
