package com.p319ss.android.download.api.clean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.download.api.clean.hj */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C9785hj extends C9788ox implements Parcelable {
    public static final Parcelable.Creator<C9785hj> CREATOR = new Parcelable.Creator<C9785hj>() { // from class: com.ss.android.download.api.clean.hj.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C9785hj createFromParcel(Parcel parcel) {
            return new C9785hj(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C9785hj[] newArray(int i) {
            return new C9785hj[i];
        }
    };

    /* renamed from: h */
    private String f18726h;

    @Override // com.p319ss.android.download.api.clean.C9788ox, com.p319ss.android.download.api.clean.C9790u, com.p319ss.android.download.api.clean.C9783h, com.p319ss.android.download.api.clean.C9781b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.p319ss.android.download.api.clean.C9790u, com.p319ss.android.download.api.clean.C9783h
    /* renamed from: mb */
    public String mo7954mb() {
        return "clean_app_cache";
    }

    public C9785hj() {
    }

    C9785hj(Parcel parcel) {
        this.f18726h = parcel.readString();
    }

    @Override // com.p319ss.android.download.api.clean.C9788ox, com.p319ss.android.download.api.clean.C9790u, com.p319ss.android.download.api.clean.C9783h, com.p319ss.android.download.api.clean.C9781b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f18726h);
    }
}
