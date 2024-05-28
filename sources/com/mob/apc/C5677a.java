package com.mob.apc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* renamed from: com.mob.apc.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5677a {

    /* renamed from: d */
    public Object f13999d;

    /* renamed from: e */
    public Bundle f14000e;

    /* renamed from: a */
    public int f13996a = -1;

    /* renamed from: b */
    public int f13997b = -1;

    /* renamed from: c */
    public int f13998c = -1;

    /* renamed from: f */
    public int f14001f = -1;

    /* renamed from: a */
    public void m12864a(Parcel parcel, int i) {
        parcel.writeInt(this.f13996a);
        parcel.writeInt(this.f13997b);
        parcel.writeInt(this.f13998c);
        Object obj = this.f13999d;
        if (obj != null) {
            if (obj instanceof Serializable) {
                parcel.writeInt(2);
                parcel.writeSerializable((Serializable) this.f13999d);
            } else if (obj instanceof Parcelable) {
                parcel.writeInt(3);
                parcel.writeParcelable((Parcelable) this.f13999d, i);
            }
        }
        if (this.f14000e == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeBundle(this.f14000e);
    }

    /* renamed from: a */
    public C5677a m12865a(Parcel parcel) {
        this.f13996a = parcel.readInt();
        this.f13997b = parcel.readInt();
        this.f13998c = parcel.readInt();
        int readInt = parcel.readInt();
        if (readInt == 2) {
            this.f13999d = parcel.readSerializable();
            readInt = parcel.readInt();
        } else if (readInt == 3) {
            this.f13999d = parcel.readParcelable(getClass().getClassLoader());
            readInt = parcel.readInt();
        }
        if (readInt == 1) {
            this.f14000e = parcel.readBundle();
        }
        return this;
    }

    public String toString() {
        return "APCMessage{what=" + this.f13996a + ", arg1=" + this.f13997b + ", arg2=" + this.f13998c + ", obj=" + this.f13999d + ", data=" + this.f14000e + '}';
    }
}
