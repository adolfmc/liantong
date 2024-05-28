package p090c;

import android.os.Parcel;
import android.os.Parcelable;
import cn.ltzf.passguard.C1730a;

/* renamed from: c.i */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1499i implements Parcelable {
    public static final Parcelable.Creator<C1499i> CREATOR = new C1500a();

    /* renamed from: a */
    public float f2530a;

    /* renamed from: b */
    public int f2531b;

    /* renamed from: c */
    public float f2532c;

    /* renamed from: d */
    public float f2533d;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c.i$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C1500a implements Parcelable.Creator<C1499i> {
        @Override // android.os.Parcelable.Creator
        public final C1499i createFromParcel(Parcel parcel) {
            return new C1499i(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final C1499i[] newArray(int i) {
            return new C1499i[i];
        }
    }

    public C1499i(float f, int i, float f2, float f3) {
        this.f2530a = f;
        this.f2531b = i;
        this.f2532c = f2;
        this.f2533d = f3;
    }

    public C1499i(Parcel parcel) {
        this.f2530a = parcel.readFloat();
        this.f2531b = parcel.readInt();
        this.f2532c = parcel.readFloat();
        this.f2533d = parcel.readFloat();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder m22016a = C1730a.m22016a("DisplayMetricsInfo{density=");
        m22016a.append(this.f2530a);
        m22016a.append(", densityDpi=");
        m22016a.append(this.f2531b);
        m22016a.append(", scaledDensity=");
        m22016a.append(this.f2532c);
        m22016a.append(", xdpi=");
        m22016a.append(this.f2533d);
        m22016a.append('}');
        return m22016a.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f2530a);
        parcel.writeInt(this.f2531b);
        parcel.writeFloat(this.f2532c);
        parcel.writeFloat(this.f2533d);
    }
}
