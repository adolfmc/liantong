package com.p319ss.android.download.api.clean;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.ss.android.download.api.clean.CleanType */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CleanType extends C9788ox implements Parcelable {
    public static final Parcelable.Creator<CleanType> CREATOR = new Parcelable.Creator<CleanType>() { // from class: com.ss.android.download.api.clean.CleanType.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public CleanType createFromParcel(Parcel parcel) {
            return new CleanType(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: mb */
        public CleanType[] newArray(int i) {
            return new CleanType[i];
        }
    };

    /* renamed from: h */
    private int f18716h;

    /* renamed from: u */
    private Map<String, C9785hj> f18717u;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.ss.android.download.api.clean.CleanType$Type */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public @interface Type {
        public static final int APK = -1;
        public static final int CACHE = -3;
        public static final int LOG = -2;
        public static final int OTHERS = -5;
        public static final int REMAIN = -4;
    }

    public int getType() {
        return this.f18716h;
    }

    public CleanType() {
        this.f18717u = new HashMap();
    }

    CleanType(Parcel parcel) {
        super(parcel);
        this.f18717u = new HashMap();
        this.f18716h = parcel.readInt();
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.f18717u.put(parcel.readString(), (C9785hj) parcel.readParcelable(C9785hj.class.getClassLoader()));
        }
    }

    @Override // com.p319ss.android.download.api.clean.C9788ox, com.p319ss.android.download.api.clean.C9790u, com.p319ss.android.download.api.clean.C9783h, com.p319ss.android.download.api.clean.C9781b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f18716h);
        parcel.writeInt(this.f18717u.size());
        for (Map.Entry<String, C9785hj> entry : this.f18717u.entrySet()) {
            try {
                parcel.writeString(entry.getKey());
                parcel.writeParcelable(entry.getValue(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
