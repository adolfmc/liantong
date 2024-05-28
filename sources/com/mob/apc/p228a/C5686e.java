package com.mob.apc.p228a;

import android.os.Parcel;
import com.mob.apc.APCException;
import com.mob.apc.C5677a;
import com.mob.apc.C5688b;

/* renamed from: com.mob.apc.a.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5686e {

    /* renamed from: a */
    public C5677a f14027a;

    /* renamed from: b */
    public String f14028b;

    /* renamed from: e */
    public long f14031e;

    /* renamed from: d */
    public APCException f14030d = null;

    /* renamed from: c */
    public String f14029c = C5688b.m12833a().getPackageName();

    public C5686e(C5677a c5677a, String str, long j) {
        this.f14031e = -1L;
        this.f14027a = c5677a;
        this.f14028b = str;
        this.f14031e = j;
    }

    /* renamed from: a */
    public void m12838a(Parcel parcel, int i) {
        parcel.writeLong(this.f14031e);
        if (this.f14027a != null) {
            parcel.writeInt(1);
            this.f14027a.m12864a(parcel, i);
        }
        if (this.f14028b != null) {
            parcel.writeInt(2);
            parcel.writeString(this.f14028b);
        }
        this.f14029c = C5688b.m12833a().getPackageName();
        parcel.writeInt(3);
        parcel.writeString(this.f14029c);
    }

    /* renamed from: a */
    public static C5686e m12839a(Parcel parcel) {
        C5686e c5686e = new C5686e(null, null, parcel.readLong());
        int readInt = parcel.readInt();
        if (readInt == 1) {
            c5686e.f14027a = new C5677a().m12865a(parcel);
            readInt = parcel.readInt();
        }
        if (readInt == 2) {
            c5686e.f14028b = parcel.readString();
            readInt = parcel.readInt();
        }
        if (readInt == 3) {
            c5686e.f14029c = parcel.readString();
        }
        return c5686e;
    }

    public String toString() {
        return "InnerMessage{apcMessage=" + this.f14027a + ", businessID='" + this.f14028b + "', pkg='" + this.f14029c + "'}";
    }
}
