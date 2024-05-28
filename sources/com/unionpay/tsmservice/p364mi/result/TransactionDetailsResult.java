package com.unionpay.tsmservice.p364mi.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.p364mi.data.TransactionDetail;

/* renamed from: com.unionpay.tsmservice.mi.result.TransactionDetailsResult */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TransactionDetailsResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.mi.result.TransactionDetailsResult.1
        @Override // android.os.Parcelable.Creator
        public final TransactionDetailsResult createFromParcel(Parcel parcel) {
            return new TransactionDetailsResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final TransactionDetailsResult[] newArray(int i) {
            return new TransactionDetailsResult[i];
        }
    };
    private String mLastUpdatedTag;
    private TransactionDetail[] mTransactionDetails;

    public TransactionDetailsResult() {
        this.mLastUpdatedTag = "";
    }

    public TransactionDetailsResult(Parcel parcel) {
        this.mLastUpdatedTag = "";
        this.mTransactionDetails = (TransactionDetail[]) parcel.createTypedArray(TransactionDetail.CREATOR);
        this.mLastUpdatedTag = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getLastUpdatedTag() {
        return this.mLastUpdatedTag;
    }

    public TransactionDetail[] getTransactionDetails() {
        return this.mTransactionDetails;
    }

    public void setLastUpdatedTag(String str) {
        this.mLastUpdatedTag = str;
    }

    public void setTransactionDetails(TransactionDetail[] transactionDetailArr) {
        this.mTransactionDetails = transactionDetailArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.mTransactionDetails, i);
        parcel.writeString(this.mLastUpdatedTag);
    }
}
