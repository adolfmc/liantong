package com.p319ss.android.download.api.clean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.ss.android.download.api.clean.ox */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C9788ox extends C9790u implements Parcelable {
    public static final Parcelable.Creator<C9788ox> CREATOR = new Parcelable.Creator<C9788ox>() { // from class: com.ss.android.download.api.clean.ox.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C9788ox createFromParcel(Parcel parcel) {
            return new C9788ox(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C9788ox[] newArray(int i) {
            return new C9788ox[i];
        }
    };

    /* renamed from: mb */
    List<C9790u> f18727mb;

    @Override // com.p319ss.android.download.api.clean.C9790u, com.p319ss.android.download.api.clean.C9783h, com.p319ss.android.download.api.clean.C9781b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public C9788ox() {
        this.f18727mb = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C9788ox(Parcel parcel) {
        super(parcel);
        this.f18727mb = new ArrayList();
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.f18727mb.add((C9790u) parcel.readParcelable(C9790u.class.getClassLoader()));
        }
    }

    @Override // com.p319ss.android.download.api.clean.C9790u, com.p319ss.android.download.api.clean.C9783h, com.p319ss.android.download.api.clean.C9781b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        if (this.f18727mb == null) {
            this.f18727mb = new ArrayList();
        }
        parcel.writeInt(this.f18727mb.size());
        for (C9790u c9790u : this.f18727mb) {
            parcel.writeParcelable(c9790u, 0);
        }
    }
}
