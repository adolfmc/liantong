package com.fidoalliance.uaf.app.api;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Version implements Parcelable {
    public static final Parcelable.Creator<Version> CREATOR = new Parcelable.Creator() { // from class: com.fidoalliance.uaf.app.api.Version.1
        @Override // android.os.Parcelable.Creator
        public final Version createFromParcel(Parcel parcel) {
            return new Version(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final Version[] newArray(int i) {
            return new Version[i];
        }
    };
    private final int major;
    private final int minor;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Version(int i, int i2) {
        this.major = i;
        this.minor = i2;
    }

    private Version(Parcel parcel) {
        this.major = parcel.readInt();
        this.minor = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.major);
        parcel.writeInt(this.minor);
    }
}
