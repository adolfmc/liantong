package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.SparseIntArray;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
class VersionedParcelParcel extends VersionedParcel {
    private static final boolean DEBUG = false;
    private static final String TAG = "VersionedParcelParcel";
    private int mCurrentField;
    private final int mEnd;
    private int mNextRead;
    private final int mOffset;
    private final Parcel mParcel;
    private final SparseIntArray mPositionLookup;
    private final String mPrefix;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VersionedParcelParcel(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "");
    }

    VersionedParcelParcel(Parcel parcel, int i, int i2, String str) {
        this.mPositionLookup = new SparseIntArray();
        this.mCurrentField = -1;
        this.mNextRead = 0;
        this.mParcel = parcel;
        this.mOffset = i;
        this.mEnd = i2;
        this.mNextRead = this.mOffset;
        this.mPrefix = str;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeInt(int i) {
        this.mParcel.writeInt(i);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeLong(long j) {
        this.mParcel.writeLong(j);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeFloat(float f) {
        this.mParcel.writeFloat(f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0000: IGET  (r0 I:android.os.Parcel) = (r1 I:androidx.versionedparcelable.VersionedParcelParcel) androidx.versionedparcelable.VersionedParcelParcel.mParcel android.os.Parcel, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.versionedparcelable.VersionedParcelParcel] */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeByteArray(byte[] bArr) {
        ?? r1;
        r1.mParcel.writeDouble(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeByteArray(byte[] bArr, int i, int i2) {
        bArr.mParcel.writeDouble(i);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeDouble(double d) {
        this.mParcel.writeDouble(d);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeString(String str) {
        this.mParcel.writeString(str);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeStrongBinder(IBinder iBinder) {
        this.mParcel.writeStrongBinder(iBinder);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeParcelable(Parcelable parcelable) {
        this.mParcel.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void setOutputField(int i) {
        this.mParcel.writeInt(i);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeBoolean(boolean z) {
        this.mParcel.writeInt(z ? 1 : 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeStrongInterface(IInterface iInterface) {
        this.mParcel.writeStrongInterface(iInterface);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeBundle(Bundle bundle) {
        this.mParcel.writeBundle(bundle);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public int readInt() {
        return this.mParcel.readInt();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public long readLong() {
        return this.mParcel.readLong();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean, float] */
    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean readField(int i) {
        return i.mParcel.readFloat();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public float readFloat() {
        return this.mParcel.readFloat();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [double, int] */
    private int readUntilField(int i) {
        return i.mParcel.readDouble();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void closeField() {
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.versionedparcelable.VersionedParcel, double] */
    @Override // androidx.versionedparcelable.VersionedParcel
    protected VersionedParcel createSubParcel() {
        return this.mParcel.readDouble();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, double] */
    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean readBoolean() {
        return this.mParcel.readDouble();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [android.os.Bundle, double] */
    @Override // androidx.versionedparcelable.VersionedParcel
    public Bundle readBundle() {
        return this.mParcel.readDouble();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [byte[], double] */
    @Override // androidx.versionedparcelable.VersionedParcel
    public byte[] readByteArray() {
        return this.mParcel.readDouble();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public double readDouble() {
        return this.mParcel.readDouble();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public <T extends Parcelable> T readParcelable() {
        return (T) this.mParcel.readString();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public String readString() {
        return this.mParcel.readString();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public IBinder readStrongBinder() {
        return this.mParcel.readStrongBinder();
    }
}
