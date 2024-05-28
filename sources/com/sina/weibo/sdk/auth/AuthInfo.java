package com.sina.weibo.sdk.auth;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.sina.weibo.sdk.p311b.C7094e;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class AuthInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<AuthInfo> CREATOR = new Parcelable.Creator<AuthInfo>() { // from class: com.sina.weibo.sdk.auth.AuthInfo.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ AuthInfo[] newArray(int i) {
            return new AuthInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ AuthInfo createFromParcel(Parcel parcel) {
            return new AuthInfo(parcel);
        }
    };
    private static final long serialVersionUID = 6421253895937667193L;
    private String app_key;
    private String hash;
    private String package_name;
    private String redirect_url;
    private String scope;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public AuthInfo(Context context, String str, String str2, String str3) {
        this.app_key = str;
        this.redirect_url = str2;
        this.scope = str3;
        this.package_name = context.getPackageName();
        this.hash = C7094e.m8067b(context, this.package_name);
    }

    public final String getAppKey() {
        return this.app_key;
    }

    public final String getRedirectUrl() {
        return this.redirect_url;
    }

    public final String getScope() {
        return this.scope;
    }

    public final String getPackageName() {
        return this.package_name;
    }

    public final String getHash() {
        return this.hash;
    }

    protected AuthInfo(Parcel parcel) {
        this.app_key = parcel.readString();
        this.redirect_url = parcel.readString();
        this.scope = parcel.readString();
        this.package_name = parcel.readString();
        this.hash = parcel.readString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.app_key);
        parcel.writeString(this.redirect_url);
        parcel.writeString(this.scope);
        parcel.writeString(this.package_name);
        parcel.writeString(this.hash);
    }
}
