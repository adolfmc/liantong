package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.VirtualCardInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class GetCardInfoBySpayResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.result.GetCardInfoBySpayResult.1
        @Override // android.os.Parcelable.Creator
        public final GetCardInfoBySpayResult createFromParcel(Parcel parcel) {
            return new GetCardInfoBySpayResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final GetCardInfoBySpayResult[] newArray(int i) {
            return new GetCardInfoBySpayResult[i];
        }
    };
    private VirtualCardInfo mVirtualCardInfo;

    public GetCardInfoBySpayResult() {
    }

    public GetCardInfoBySpayResult(Parcel parcel) {
        this.mVirtualCardInfo = (VirtualCardInfo) parcel.readParcelable(VirtualCardInfo.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VirtualCardInfo getVirtualCardInfo() {
        return this.mVirtualCardInfo;
    }

    public void setVirtualCardInfo(VirtualCardInfo virtualCardInfo) {
        this.mVirtualCardInfo = virtualCardInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mVirtualCardInfo, i);
    }
}
