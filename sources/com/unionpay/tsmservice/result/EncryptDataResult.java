package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class EncryptDataResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.result.EncryptDataResult.1
        @Override // android.os.Parcelable.Creator
        public final EncryptDataResult createFromParcel(Parcel parcel) {
            return new EncryptDataResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final EncryptDataResult[] newArray(int i) {
            return new EncryptDataResult[i];
        }
    };
    private List mEncryptData;

    public EncryptDataResult() {
    }

    public EncryptDataResult(Parcel parcel) {
        this.mEncryptData = new ArrayList();
        parcel.readList(this.mEncryptData, ClassLoader.getSystemClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List getEncryptData() {
        return this.mEncryptData;
    }

    public void setEncryptData(List list) {
        this.mEncryptData = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mEncryptData);
    }
}
