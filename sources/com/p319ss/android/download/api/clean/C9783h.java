package com.p319ss.android.download.api.clean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.download.api.clean.h */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C9783h extends C9781b implements Parcelable {
    public static final Parcelable.Creator<C9783h> CREATOR = new Parcelable.Creator<C9783h>() { // from class: com.ss.android.download.api.clean.h.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C9783h createFromParcel(Parcel parcel) {
            return new C9783h(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C9783h[] newArray(int i) {
            return new C9783h[i];
        }
    };

    /* renamed from: h */
    private String f18722h;

    /* renamed from: ko */
    private String f18723ko;

    /* renamed from: mb */
    private String f18724mb;

    /* renamed from: u */
    private boolean f18725u;

    @Override // com.p319ss.android.download.api.clean.C9781b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: mb */
    public String mo7954mb() {
        return this.f18723ko;
    }

    public C9783h() {
        this.f18723ko = "clean_file";
    }

    @Override // com.p319ss.android.download.api.clean.C9781b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f18724mb);
        parcel.writeString(this.f18722h);
        parcel.writeInt(this.f18725u ? 1 : 0);
        parcel.writeString(this.f18723ko);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C9783h(Parcel parcel) {
        this.f18723ko = "clean_file";
        this.f18724mb = parcel.readString();
        this.f18722h = parcel.readString();
        this.f18725u = parcel.readInt() == 1;
        this.f18723ko = parcel.readString();
    }
}
