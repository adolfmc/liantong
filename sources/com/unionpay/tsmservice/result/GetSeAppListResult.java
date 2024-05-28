package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.SeAppListItem;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class GetSeAppListResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.result.GetSeAppListResult.1
        @Override // android.os.Parcelable.Creator
        public final GetSeAppListResult createFromParcel(Parcel parcel) {
            return new GetSeAppListResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final GetSeAppListResult[] newArray(int i) {
            return new GetSeAppListResult[i];
        }
    };
    private String mSeAliasType;
    private SeAppListItem[] mSeAppList;

    public GetSeAppListResult() {
        this.mSeAliasType = "";
    }

    public GetSeAppListResult(Parcel parcel) {
        this.mSeAliasType = "";
        this.mSeAppList = (SeAppListItem[]) parcel.createTypedArray(SeAppListItem.CREATOR);
        this.mSeAliasType = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getSeAliasType() {
        return this.mSeAliasType;
    }

    public SeAppListItem[] getSeAppList() {
        return this.mSeAppList;
    }

    public void setSeAliasType(String str) {
        this.mSeAliasType = str;
    }

    public void setSeAppList(SeAppListItem[] seAppListItemArr) {
        this.mSeAppList = seAppListItemArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.mSeAppList, i);
        parcel.writeString(this.mSeAliasType);
    }
}
