package org.fidoalliance.uaf.client;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Authenticator implements Parcelable {
    public static final Parcelable.Creator<Authenticator> CREATOR = new Parcelable.Creator<Authenticator>() { // from class: org.fidoalliance.uaf.client.Authenticator.1
        @Override // android.os.Parcelable.Creator
        public Authenticator[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public Authenticator createFromParcel(Parcel parcel) {
            return new Authenticator(parcel);
        }
    };

    /* renamed from: a */
    private String f27386a;

    /* renamed from: b */
    private String f27387b;

    /* renamed from: c */
    private String f27388c;

    /* renamed from: d */
    private List<Version> f27389d;

    /* renamed from: e */
    private long f27390e;

    /* renamed from: f */
    private long f27391f;

    /* renamed from: g */
    private long f27392g;

    /* renamed from: h */
    private long f27393h;

    /* renamed from: i */
    private long f27394i;

    /* renamed from: j */
    private String f27395j;

    /* renamed from: k */
    private long f27396k;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Authenticator() {
    }

    private Authenticator(Parcel parcel) {
        this.f27386a = parcel.readString();
        this.f27387b = parcel.readString();
        this.f27388c = parcel.readString();
        this.f27390e = parcel.readLong();
        this.f27391f = parcel.readLong();
        this.f27392g = parcel.readLong();
        this.f27393h = parcel.readLong();
        this.f27394i = parcel.readLong();
        this.f27395j = parcel.readString();
        this.f27396k = parcel.readLong();
        this.f27389d = parcel.createTypedArrayList(Version.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f27386a);
        parcel.writeString(this.f27387b);
        parcel.writeString(this.f27388c);
        parcel.writeLong(this.f27390e);
        parcel.writeLong(this.f27391f);
        parcel.writeLong(this.f27392g);
        parcel.writeLong(this.f27393h);
        parcel.writeLong(this.f27394i);
        parcel.writeString(this.f27395j);
        parcel.writeLong(this.f27396k);
        parcel.writeTypedList(this.f27389d);
    }
}
