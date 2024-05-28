package com.p319ss.android.download.api.clean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.ss.android.download.api.clean.u */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C9790u extends C9783h implements Parcelable {
    public static final Parcelable.Creator<C9790u> CREATOR = new Parcelable.Creator<C9790u>() { // from class: com.ss.android.download.api.clean.u.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C9790u createFromParcel(Parcel parcel) {
            return new C9790u(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public C9790u[] newArray(int i) {
            return new C9790u[i];
        }
    };

    /* renamed from: h */
    private List<C9783h> f18728h;

    /* renamed from: ko */
    private boolean f18729ko;

    /* renamed from: mb */
    private String f18730mb;

    /* renamed from: u */
    private Map<String, C9783h> f18731u;

    @Override // com.p319ss.android.download.api.clean.C9783h, com.p319ss.android.download.api.clean.C9781b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.p319ss.android.download.api.clean.C9783h
    /* renamed from: mb */
    public String mo7954mb() {
        return "clean_folder";
    }

    public C9790u() {
        this.f18728h = new ArrayList();
        this.f18731u = new HashMap();
    }

    @Override // com.p319ss.android.download.api.clean.C9783h, com.p319ss.android.download.api.clean.C9781b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f18730mb);
        parcel.writeInt(this.f18729ko ? 1 : 0);
        parcel.writeInt(this.f18731u.size());
        for (Map.Entry<String, C9783h> entry : this.f18731u.entrySet()) {
            C9783h value = entry.getValue();
            parcel.writeString(entry.getKey());
            parcel.writeString(value.mo7954mb());
            parcel.writeParcelable(value, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C9790u(Parcel parcel) {
        super(parcel);
        Parcelable readParcelable;
        this.f18728h = new ArrayList();
        this.f18731u = new HashMap();
        this.f18730mb = parcel.readString();
        this.f18729ko = parcel.readInt() == 1;
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            if ("apk_clean_file".equals(readString2)) {
                readParcelable = parcel.readParcelable(C9787mb.class.getClassLoader());
            } else if ("clean_app_cache".equals(readString2)) {
                readParcelable = parcel.readParcelable(C9785hj.class.getClassLoader());
            } else if ("clean_folder".equals(readString2)) {
                readParcelable = parcel.readParcelable(C9790u.class.getClassLoader());
            } else {
                readParcelable = parcel.readParcelable(C9783h.class.getClassLoader());
            }
            this.f18731u.put(readString, (C9783h) readParcelable);
        }
    }
}
