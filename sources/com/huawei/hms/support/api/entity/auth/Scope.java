package com.huawei.hms.support.api.entity.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.core.aidl.IMessageEntity;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Scope implements Parcelable, IMessageEntity {
    public static final Parcelable.Creator<Scope> CREATOR = new C5068a();
    private String mScopeUri;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.support.api.entity.auth.Scope$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    class C5068a implements Parcelable.Creator<Scope> {
        C5068a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public Scope createFromParcel(Parcel parcel) {
            return new Scope(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public Scope[] newArray(int i) {
            return new Scope[i];
        }
    }

    public Scope() {
        this.mScopeUri = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Scope) {
            return Objects.equal(this.mScopeUri, ((Scope) obj).mScopeUri);
        }
        return false;
    }

    @Deprecated
    public boolean equeals(Object obj) {
        return equals(obj);
    }

    public String getScopeUri() {
        return this.mScopeUri;
    }

    public final int hashCode() {
        String str = this.mScopeUri;
        return str == null ? super.hashCode() : str.hashCode();
    }

    public final String toString() {
        return this.mScopeUri;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mScopeUri);
    }

    public Scope(String str) {
        this.mScopeUri = str;
    }

    protected Scope(Parcel parcel) {
        this.mScopeUri = parcel.readString();
    }
}
