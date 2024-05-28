package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.SparseArray;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
class VersionedParcelStream extends VersionedParcel {
    private static final int TYPE_BOOLEAN = 5;
    private static final int TYPE_BOOLEAN_ARRAY = 6;
    private static final int TYPE_DOUBLE = 7;
    private static final int TYPE_DOUBLE_ARRAY = 8;
    private static final int TYPE_FLOAT = 13;
    private static final int TYPE_FLOAT_ARRAY = 14;
    private static final int TYPE_INT = 9;
    private static final int TYPE_INT_ARRAY = 10;
    private static final int TYPE_LONG = 11;
    private static final int TYPE_LONG_ARRAY = 12;
    private static final int TYPE_NULL = 0;
    private static final int TYPE_STRING = 3;
    private static final int TYPE_STRING_ARRAY = 4;
    private static final int TYPE_SUB_BUNDLE = 1;
    private static final int TYPE_SUB_PERSISTABLE_BUNDLE = 2;
    private static final Charset UTF_16 = Charset.forName("UTF-16");
    private final SparseArray<InputBuffer> mCachedFields = new SparseArray<>();
    private DataInputStream mCurrentInput;
    private DataOutputStream mCurrentOutput;
    private FieldBuffer mFieldBuffer;
    private boolean mIgnoreParcelables;
    private final DataInputStream mMasterInput;
    private final DataOutputStream mMasterOutput;

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    private void readObject(int r-1, java.lang.String r0, android.os.Bundle r1) {
        /*
            r-2 = this;
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.readObject(int, java.lang.String, android.os.Bundle):void");
    }

    private void writeObject(Object obj) {
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void closeField() {
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected VersionedParcel createSubParcel() {
        return 1;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean isStream() {
        return true;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean readBoolean() {
        return false;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public Bundle readBundle() {
        return null;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public byte[] readByteArray() {
        return null;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public double readDouble() {
        return 0.0d;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean readField(int i) {
        return false;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public float readFloat() {
        return 0.0f;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public int readInt() {
        return 0;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public long readLong() {
        return 0L;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public <T extends Parcelable> T readParcelable() {
        return null;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public String readString() {
        return null;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public IBinder readStrongBinder() {
        return null;
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeBoolean(boolean r0) {
        /*
            r-1 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeBoolean(boolean):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeBundle(android.os.Bundle r0) {
        /*
            r-1 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeBundle(android.os.Bundle):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeByteArray(byte[] r0) {
        /*
            r-1 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeByteArray(byte[]):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeByteArray(byte[] r-2, int r-1, int r0) {
        /*
            r-3 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeByteArray(byte[], int, int):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeDouble(double r-1) {
        /*
            r-2 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeDouble(double):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeFloat(float r0) {
        /*
            r-1 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeFloat(float):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeInt(int r0) {
        /*
            r-1 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeInt(int):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeLong(long r-1) {
        /*
            r-2 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeLong(long):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeParcelable(android.os.Parcelable r0) {
        /*
            r-1 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeParcelable(android.os.Parcelable):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeString(java.lang.String r0) {
        /*
            r-1 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeString(java.lang.String):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeStrongBinder(android.os.IBinder r0) {
        /*
            r-1 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeStrongBinder(android.os.IBinder):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeStrongInterface(android.os.IInterface r0) {
        /*
            r-1 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.writeStrongInterface(android.os.IInterface):void");
    }

    public VersionedParcelStream(InputStream inputStream, OutputStream outputStream) {
        this.mMasterInput = inputStream != null ? new DataInputStream(inputStream) : null;
        this.mMasterOutput = outputStream != null ? new DataOutputStream(outputStream) : null;
        this.mCurrentInput = this.mMasterInput;
        this.mCurrentOutput = this.mMasterOutput;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0002: IPUT  (r2v0 ?? I:boolean), (r0 I:androidx.versionedparcelable.VersionedParcelStream) androidx.versionedparcelable.VersionedParcelStream.mIgnoreParcelables boolean, block:B:3:0x0002 */
    @Override // androidx.versionedparcelable.VersionedParcel
    public void setOutputField(int i) {
        VersionedParcelStream versionedParcelStream;
        if (this == null) {
            throw new RuntimeException("Serialization of this object is not allowed");
        }
        versionedParcelStream.mIgnoreParcelables = i;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void setSerializationFlags(boolean z, boolean z2) {
        if (!z) {
            throw new RuntimeException("Serialization of this object is not allowed");
        }
        this.mIgnoreParcelables = z2;
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    static class FieldBuffer {
        private final int mFieldId;
        private final DataOutputStream mTarget;
        final ByteArrayOutputStream mOutput = new ByteArrayOutputStream();
        final DataOutputStream mDataStream = new DataOutputStream(this.mOutput);

        FieldBuffer(int i, DataOutputStream dataOutputStream) {
            this.mFieldId = i;
            this.mTarget = dataOutputStream;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:java.lang.Object) type: DIRECT call: java.lang.Object.<init>():void, block:B:2:0x0000 */
        /* JADX WARN: Not initialized variable reg: 1, insn: 0x0005: IPUT  (r1 I:int), (r0 I:androidx.versionedparcelable.VersionedParcelStream$InputBuffer) androidx.versionedparcelable.VersionedParcelStream.InputBuffer.mFieldId int, block:B:2:0x0000 */
        /* JADX WARN: Not initialized variable reg: 2, insn: 0x0003: IPUT  (r2 I:int), (r0 I:androidx.versionedparcelable.VersionedParcelStream$InputBuffer) androidx.versionedparcelable.VersionedParcelStream.InputBuffer.mSize int, block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, androidx.versionedparcelable.VersionedParcelStream$InputBuffer] */
        void flushField() throws IOException {
            int i;
            int i2;
            ?? obj = new Object();
            ((InputBuffer) obj).mSize = i;
            obj.mFieldId = i2;
            byte[] bArr = new byte[((InputBuffer) obj).mSize];
            readFully(bArr);
            obj.mInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class InputBuffer {
        final int mFieldId;
        final DataInputStream mInputStream;
        private final int mSize;

        InputBuffer(int i, int i2, DataInputStream dataInputStream) throws IOException {
            this.mSize = i2;
            this.mFieldId = i;
            byte[] bArr = new byte[this.mSize];
            dataInputStream.readFully(bArr);
            this.mInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        }
    }
}
