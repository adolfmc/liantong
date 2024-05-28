package com.sina.weibo.sdk.web;

import android.os.Parcel;
import android.os.Parcelable;
import com.sina.weibo.sdk.auth.AuthInfo;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WebData implements Parcelable, Serializable {
    public static final Parcelable.Creator<WebData> CREATOR = new Parcelable.Creator<WebData>() { // from class: com.sina.weibo.sdk.web.WebData.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ WebData[] newArray(int i) {
            return new WebData[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ WebData createFromParcel(Parcel parcel) {
            return new WebData(parcel);
        }
    };
    private static final long serialVersionUID = -4038177938155795889L;

    /* renamed from: av */
    protected AuthInfo f18353av;

    /* renamed from: aw */
    protected String f18354aw;

    /* renamed from: i */
    protected String f18355i;
    protected int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WebData(AuthInfo authInfo, int i, String str, String str2) {
        this.f18353av = authInfo;
        this.type = i;
        this.f18355i = str;
        this.f18354aw = str2;
    }

    protected WebData(Parcel parcel) {
        this.f18353av = (AuthInfo) parcel.readParcelable(AuthInfo.class.getClassLoader());
        this.type = parcel.readInt();
        this.f18355i = parcel.readString();
        this.f18354aw = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f18353av, i);
        parcel.writeInt(this.type);
        parcel.writeString(this.f18355i);
        parcel.writeString(this.f18354aw);
    }

    /* renamed from: a */
    public final AuthInfo m8020a() {
        return this.f18353av;
    }

    public final int getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.f18355i;
    }

    /* renamed from: r */
    public final String m8019r() {
        return this.f18354aw;
    }
}
