package com.gmrz.asm.p198fp.authui;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.authui.IMatcherResult */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class IMatcherResult implements Parcelable {
    public static final Parcelable.Creator<IMatcherResult> CREATOR = new Parcelable.Creator<IMatcherResult>() { // from class: com.gmrz.asm.fp.authui.IMatcherResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IMatcherResult[] newArray(int i) {
            return new IMatcherResult[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IMatcherResult createFromParcel(Parcel parcel) {
            return new IMatcherResult(parcel);
        }
    };
    public int result;
    public String userId;
    public String uvt;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IMatcherResult() {
        this.result = 5;
        this.uvt = null;
        this.userId = null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.result);
        parcel.writeString(this.uvt);
        parcel.writeString(this.userId);
    }

    public IMatcherResult(Parcel parcel) {
        this.result = 5;
        this.uvt = null;
        this.userId = null;
        this.result = parcel.readInt();
        this.uvt = parcel.readString();
        this.userId = parcel.readString();
    }
}
