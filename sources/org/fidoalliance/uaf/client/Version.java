package org.fidoalliance.uaf.client;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Version implements Parcelable {
    public static final Parcelable.Creator<Version> CREATOR = new Parcelable.Creator<Version>() { // from class: org.fidoalliance.uaf.client.Version.1
        @Override // android.os.Parcelable.Creator
        public Version[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public Version createFromParcel(Parcel parcel) {
            return new Version(parcel);
        }
    };

    /* renamed from: a */
    private final int f27397a;

    /* renamed from: b */
    private final int f27398b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Version(int i, int i2) {
        this.f27397a = i;
        this.f27398b = i2;
    }

    public Version(Parcel parcel) {
        this.f27397a = parcel.readInt();
        this.f27398b = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f27397a);
        parcel.writeInt(this.f27398b);
    }
}
