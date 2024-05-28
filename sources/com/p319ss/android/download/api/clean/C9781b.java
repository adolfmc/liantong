package com.p319ss.android.download.api.clean;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.ss.android.download.api.clean.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C9781b implements Parcelable {
    public static final Parcelable.Creator<C9781b> CREATOR = new Parcelable.Creator<C9781b>() { // from class: com.ss.android.download.api.clean.b.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C9781b createFromParcel(Parcel parcel) {
            return new C9781b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C9781b[] newArray(int i) {
            return new C9781b[i];
        }
    };

    /* renamed from: b */
    protected long f18718b;

    /* renamed from: hj */
    boolean f18719hj;

    /* renamed from: mb */
    private String f18720mb;

    /* renamed from: ox */
    protected String f18721ox;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public C9781b() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f18721ox);
        parcel.writeLong(this.f18718b);
        parcel.writeString(this.f18720mb);
        parcel.writeInt(this.f18719hj ? 1 : 0);
    }

    C9781b(Parcel parcel) {
        this.f18721ox = parcel.readString();
        this.f18718b = parcel.readLong();
        this.f18720mb = parcel.readString();
        this.f18719hj = parcel.readInt() == 1;
    }
}
