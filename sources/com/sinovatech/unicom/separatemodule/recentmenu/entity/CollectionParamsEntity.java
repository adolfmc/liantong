package com.sinovatech.unicom.separatemodule.recentmenu.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CollectionParamsEntity implements Parcelable {
    public static final Parcelable.Creator<CollectionParamsEntity> CREATOR = new Parcelable.Creator<CollectionParamsEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectionParamsEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CollectionParamsEntity createFromParcel(Parcel parcel) {
            return new CollectionParamsEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CollectionParamsEntity[] newArray(int i) {
            return new CollectionParamsEntity[i];
        }
    };
    private String categoryId;
    private String fromType;
    private int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFromType() {
        return this.fromType;
    }

    public void setFromType(String str) {
        this.fromType = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String str) {
        this.categoryId = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeString(this.categoryId);
        parcel.writeString(this.fromType);
    }

    public void readFromParcel(Parcel parcel) {
        this.type = parcel.readInt();
        this.categoryId = parcel.readString();
        this.fromType = parcel.readString();
    }

    public CollectionParamsEntity() {
        this.fromType = "";
    }

    protected CollectionParamsEntity(Parcel parcel) {
        this.fromType = "";
        this.type = parcel.readInt();
        this.categoryId = parcel.readString();
        this.fromType = parcel.readString();
    }
}
