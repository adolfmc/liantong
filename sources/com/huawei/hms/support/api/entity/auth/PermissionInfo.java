package com.huawei.hms.support.api.entity.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PermissionInfo implements Parcelable, IMessageEntity {
    public static final Parcelable.Creator<PermissionInfo> CREATOR = new C5067a();
    @Packed
    private String appID;
    @Packed
    private String packageName;
    @Packed
    private String permission;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.support.api.entity.auth.PermissionInfo$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    class C5067a implements Parcelable.Creator<PermissionInfo> {
        C5067a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public PermissionInfo createFromParcel(Parcel parcel) {
            return new PermissionInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public PermissionInfo[] newArray(int i) {
            return new PermissionInfo[i];
        }
    }

    public PermissionInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PermissionInfo) {
            PermissionInfo permissionInfo = (PermissionInfo) obj;
            return Objects.equal(this.appID, permissionInfo.appID) && Objects.equal(this.packageName, permissionInfo.packageName) && Objects.equal(this.permission, permissionInfo.permission);
        }
        return false;
    }

    public String getAppID() {
        return this.appID;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getPermission() {
        return this.permission;
    }

    public int hashCode() {
        return Objects.hashCode(this.appID, this.packageName, this.permission);
    }

    public void setAppID(String str) {
        this.appID = str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setPermission(String str) {
        this.permission = str;
    }

    public PermissionInfo setPermissionUri(String str) {
        this.permission = str;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.appID);
        parcel.writeString(this.packageName);
        parcel.writeString(this.permission);
    }

    public PermissionInfo(String str, String str2, String str3) {
        this.appID = str;
        this.packageName = str2;
        this.permission = str3;
    }

    public PermissionInfo(Parcel parcel) {
        this.appID = parcel.readString();
        this.packageName = parcel.readString();
        this.permission = parcel.readString();
    }
}
