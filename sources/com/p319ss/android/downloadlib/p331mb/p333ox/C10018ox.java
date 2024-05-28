package com.p319ss.android.downloadlib.p331mb.p333ox;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.ss.android.downloadlib.mb.ox.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C10018ox implements Parcelable {
    public static final Parcelable.Creator<C10018ox> CREATOR = new Parcelable.Creator<C10018ox>() { // from class: com.ss.android.downloadlib.mb.ox.ox.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C10018ox createFromParcel(Parcel parcel) {
            return new C10018ox(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C10018ox[] newArray(int i) {
            return new C10018ox[i];
        }
    };

    /* renamed from: b */
    public String f19312b;

    /* renamed from: h */
    public String f19313h;

    /* renamed from: hj */
    public int f19314hj;

    /* renamed from: mb */
    public int f19315mb;

    /* renamed from: ox */
    public int f19316ox;

    /* renamed from: u */
    public String f19317u;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "";
    }

    public C10018ox() {
        this.f19312b = "";
        this.f19313h = "";
        this.f19317u = "";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f19315mb);
        parcel.writeInt(this.f19316ox);
        parcel.writeString(this.f19312b);
        parcel.writeString(this.f19313h);
        parcel.writeString(this.f19317u);
        parcel.writeInt(this.f19314hj);
    }

    protected C10018ox(Parcel parcel) {
        this.f19312b = "";
        this.f19313h = "";
        this.f19317u = "";
        this.f19315mb = parcel.readInt();
        this.f19316ox = parcel.readInt();
        this.f19312b = parcel.readString();
        this.f19313h = parcel.readString();
        this.f19317u = parcel.readString();
        this.f19314hj = parcel.readInt();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C10018ox c10018ox = (C10018ox) obj;
        if (this.f19315mb == c10018ox.f19315mb && this.f19316ox == c10018ox.f19316ox) {
            String str = this.f19312b;
            if (str != null) {
                return str.equals(c10018ox.f19312b);
            }
            return c10018ox.f19312b == null;
        }
        return false;
    }

    public int hashCode() {
        int i = ((this.f19315mb * 31) + this.f19316ox) * 31;
        String str = this.f19312b;
        return i + (str != null ? str.hashCode() : 0);
    }
}
