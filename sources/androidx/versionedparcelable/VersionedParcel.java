package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class VersionedParcel {
    private static final int EX_BAD_PARCELABLE = -2;
    private static final int EX_ILLEGAL_ARGUMENT = -3;
    private static final int EX_ILLEGAL_STATE = -5;
    private static final int EX_NETWORK_MAIN_THREAD = -6;
    private static final int EX_NULL_POINTER = -4;
    private static final int EX_PARCELABLE = -9;
    private static final int EX_SECURITY = -1;
    private static final int EX_UNSUPPORTED_OPERATION = -7;
    private static final String TAG = "VersionedParcel";
    private static final int TYPE_BINDER = 5;
    private static final int TYPE_PARCELABLE = 2;
    private static final int TYPE_SERIALIZABLE = 3;
    private static final int TYPE_STRING = 4;
    private static final int TYPE_VERSIONED_PARCELABLE = 1;

    private void writeVersionedParcelableCreator(VersionedParcelable versionedParcelable) {
    }

    protected abstract void closeField();

    protected abstract VersionedParcel createSubParcel();

    public boolean isStream() {
        return false;
    }

    protected abstract boolean readBoolean();

    protected abstract Bundle readBundle();

    protected abstract byte[] readByteArray();

    protected abstract double readDouble();

    protected abstract boolean readField(int i);

    protected abstract float readFloat();

    protected abstract int readInt();

    protected abstract long readLong();

    protected abstract <T extends Parcelable> T readParcelable();

    protected abstract String readString();

    protected abstract IBinder readStrongBinder();

    protected abstract void setOutputField(int i);

    public void setSerializationFlags(boolean z, boolean z2) {
    }

    protected abstract void writeBoolean(boolean z);

    protected abstract void writeBundle(Bundle bundle);

    protected abstract void writeByteArray(byte[] bArr);

    protected abstract void writeByteArray(byte[] bArr, int i, int i2);

    protected abstract void writeDouble(double d);

    protected abstract void writeFloat(float f);

    protected abstract void writeInt(int i);

    protected abstract void writeLong(long j);

    protected abstract void writeParcelable(Parcelable parcelable);

    protected abstract void writeString(String str);

    protected abstract void writeStrongBinder(IBinder iBinder);

    protected abstract void writeStrongInterface(IInterface iInterface);

    public void writeStrongInterface(IInterface iInterface, int i) {
        setOutputField(i);
        writeStrongInterface(iInterface);
    }

    public void writeBundle(Bundle bundle, int i) {
        setOutputField(i);
        writeBundle(bundle);
    }

    public void writeBoolean(boolean z, int i) {
        setOutputField(i);
        writeBoolean(z);
    }

    public void writeByteArray(byte[] bArr, int i) {
        setOutputField(i);
        writeByteArray(bArr);
    }

    public void writeByteArray(byte[] bArr, int i, int i2, int i3) {
        setOutputField(i3);
        writeByteArray(bArr, i, i2);
    }

    public void writeInt(int i, int i2) {
        setOutputField(i2);
        writeInt(i);
    }

    public void writeLong(long j, int i) {
        setOutputField(i);
        writeLong(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeException(Exception exc, int i) {
        setOutputField(i);
        writeFloat(exc);
    }

    public void writeFloat(float f, int i) {
        setOutputField(i);
        writeFloat(f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.versionedparcelable.VersionedParcel), (r3 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.setOutputField(int):void, block:B:2:0x0000 */
    public void writeCharArray(char[] cArr, int i) {
        VersionedParcel outputField;
        outputField.setOutputField(i);
        outputField.writeDouble(this);
    }

    public void writeDouble(double d, int i) {
        setOutputField(i);
        writeDouble(d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeSparseBooleanArray(SparseBooleanArray sparseBooleanArray, int i) {
        setOutputField(i);
        writeString(sparseBooleanArray);
    }

    public void writeString(String str, int i) {
        setOutputField(i);
        writeString(str);
    }

    public void writeStrongBinder(IBinder iBinder, int i) {
        setOutputField(i);
        writeStrongBinder(iBinder);
    }

    public void writeParcelable(Parcelable parcelable, int i) {
        setOutputField(i);
        writeParcelable(parcelable);
    }

    public boolean readBoolean(boolean z, int i) {
        return !readField(i) ? z : readBoolean();
    }

    public int readInt(int i, int i2) {
        return !readField(i2) ? i : readInt();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r3 I:boolean) = (r0 I:androidx.versionedparcelable.VersionedParcel), (r3 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.readField(int):boolean, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.versionedparcelable.VersionedParcel] */
    public <T> List<T> readList(List<T> list, int i) {
        ?? readField;
        return !readField.readField(i) ? this : (List<T>) readField.readLong();
    }

    public long readLong(long j, int i) {
        return !readField(i) ? j : readLong();
    }

    public float readFloat(float f, int i) {
        return !readField(i) ? f : readFloat();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r3 I:boolean) = (r0 I:androidx.versionedparcelable.VersionedParcel), (r3 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.readField(int):boolean, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.versionedparcelable.VersionedParcel] */
    /* JADX WARN: Type inference failed for: r1v1, types: [char[], double] */
    public char[] readCharArray(char[] cArr, int i) {
        ?? readField;
        return !readField.readField(i) ? this : readField.readDouble();
    }

    public double readDouble(double d, int i) {
        return !readField(i) ? d : readDouble();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r2 I:boolean) = (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.readField(int):boolean, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0006: RETURN  (r1 I:java.io.Serializable), block:B:4:0x0006 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.versionedparcelable.VersionedParcel] */
    protected Serializable readSerializable() {
        ?? readField;
        Serializable readString;
        return !readField.readField(this) ? readString : readField.readString();
    }

    public <T> Set<T> readSet(Set<T> set, int i) {
        return !readField(i) ? set : (Set<T>) readString();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String, android.util.Size] */
    @RequiresApi(api = 21)
    public Size readSize(Size size, int i) {
        return !readField(i) ? size : readString();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.util.SizeF, java.lang.String] */
    @RequiresApi(api = 21)
    public SizeF readSizeF(SizeF sizeF, int i) {
        return !readField(i) ? sizeF : readString();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.util.SparseBooleanArray, java.lang.String] */
    public SparseBooleanArray readSparseBooleanArray(SparseBooleanArray sparseBooleanArray, int i) {
        return !readField(i) ? sparseBooleanArray : readString();
    }

    public String readString(String str, int i) {
        return !readField(i) ? str : readString();
    }

    public IBinder readStrongBinder(IBinder iBinder, int i) {
        return !readField(i) ? iBinder : readStrongBinder();
    }

    public byte[] readByteArray(byte[] bArr, int i) {
        return !readField(i) ? bArr : readByteArray();
    }

    public <T extends Parcelable> T readParcelable(T t, int i) {
        return !readField(i) ? t : (T) readParcelable();
    }

    public Bundle readBundle(Bundle bundle, int i) {
        return !readField(i) ? bundle : readBundle();
    }

    public void writeByte(byte b, int i) {
        setOutputField(i);
        writeInt(b);
    }

    @RequiresApi(api = 21)
    public void writeSize(Size size, int i) {
        setOutputField(i);
        writeBoolean(size != null);
        if (size != null) {
            writeInt(size.getWidth());
            writeInt(size.getHeight());
        }
    }

    @RequiresApi(api = 21)
    public void writeSizeF(SizeF sizeF, int i) {
        setOutputField(i);
        writeBoolean(sizeF != null);
        if (sizeF != null) {
            writeFloat(sizeF.getWidth());
            writeFloat(sizeF.getHeight());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.setOutputField(int):void, block:B:2:0x0000 */
    protected void writeBooleanArray(boolean[] zArr) {
        VersionedParcel outputField;
        outputField.setOutputField(zArr);
        outputField.writeBooleanArray(this);
    }

    public void writeBooleanArray(boolean[] zArr, int i) {
        setOutputField(i);
        writeBooleanArray(zArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r2 I:boolean) = (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.readField(int):boolean, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0006: RETURN  (r1 I:boolean[]), block:B:4:0x0006 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.versionedparcelable.VersionedParcel] */
    protected boolean[] readBooleanArray() {
        ?? readField;
        boolean[] readBooleanArray;
        return !readField.readField(this) ? readBooleanArray : readField.readBooleanArray();
    }

    public boolean[] readBooleanArray(boolean[] zArr, int i) {
        return !readField(i) ? zArr : readBooleanArray();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.setOutputField(int):void, block:B:2:0x0000 */
    protected void writeIntArray(int[] iArr) {
        VersionedParcel outputField;
        outputField.setOutputField(iArr);
        outputField.writeIntArray(this);
    }

    public void writeIntArray(int[] iArr, int i) {
        setOutputField(i);
        writeIntArray(iArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r2 I:boolean) = (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.readField(int):boolean, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0006: RETURN  (r1 I:int[]), block:B:4:0x0006 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.versionedparcelable.VersionedParcel] */
    protected int[] readIntArray() {
        ?? readField;
        int[] readIntArray;
        return !readField.readField(this) ? readIntArray : readField.readIntArray();
    }

    public int[] readIntArray(int[] iArr, int i) {
        return !readField(i) ? iArr : readIntArray();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.setOutputField(int):void, block:B:2:0x0000 */
    protected void writeLongArray(long[] jArr) {
        VersionedParcel outputField;
        outputField.setOutputField(jArr);
        outputField.writeLongArray(this);
    }

    public void writeLongArray(long[] jArr, int i) {
        setOutputField(i);
        writeLongArray(jArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r2 I:boolean) = (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.readField(int):boolean, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0006: RETURN  (r1 I:long[]), block:B:4:0x0006 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.versionedparcelable.VersionedParcel] */
    protected long[] readLongArray() {
        ?? readField;
        long[] readLongArray;
        return !readField.readField(this) ? readLongArray : readField.readLongArray();
    }

    public long[] readLongArray(long[] jArr, int i) {
        return !readField(i) ? jArr : readLongArray();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.setOutputField(int):void, block:B:2:0x0000 */
    protected void writeFloatArray(float[] fArr) {
        VersionedParcel outputField;
        outputField.setOutputField(fArr);
        outputField.writeFloatArray(this);
    }

    public void writeFloatArray(float[] fArr, int i) {
        setOutputField(i);
        writeFloatArray(fArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r2 I:boolean) = (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.readField(int):boolean, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0006: RETURN  (r1 I:float[]), block:B:4:0x0006 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.versionedparcelable.VersionedParcel] */
    protected float[] readFloatArray() {
        ?? readField;
        float[] readFloatArray;
        return !readField.readField(this) ? readFloatArray : readField.readFloatArray();
    }

    public float[] readFloatArray(float[] fArr, int i) {
        return !readField(i) ? fArr : readFloatArray();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.setOutputField(int):void, block:B:2:0x0000 */
    protected void writeDoubleArray(double[] dArr) {
        VersionedParcel outputField;
        outputField.setOutputField(dArr);
        outputField.writeDoubleArray(this);
    }

    public void writeDoubleArray(double[] dArr, int i) {
        setOutputField(i);
        writeDoubleArray(dArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r2 I:boolean) = (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.readField(int):boolean, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0006: RETURN  (r1 I:double[]), block:B:4:0x0006 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.versionedparcelable.VersionedParcel] */
    protected double[] readDoubleArray() {
        ?? readField;
        double[] readDoubleArray;
        return !readField.readField(this) ? readDoubleArray : readField.readDoubleArray();
    }

    public double[] readDoubleArray(double[] dArr, int i) {
        return !readField(i) ? dArr : readDoubleArray();
    }

    public <T> void writeSet(Set<T> set, int i) {
        writeCollection(set, i);
    }

    public <T> void writeList(List<T> list, int i) {
        writeCollection(list, i);
    }

    private <T> void writeCollection(Collection<T> collection, int i) {
        setOutputField(i);
        if (collection == null) {
            writeInt(-1);
            return;
        }
        int size = collection.size();
        writeInt(size);
        if (size > 0) {
            int type = getType(collection.iterator().next());
            writeInt(type);
            switch (type) {
                case 1:
                    for (T t : collection) {
                        writeVersionedParcelable(t);
                    }
                    return;
                case 2:
                    for (T t2 : collection) {
                        writeParcelable(t2);
                    }
                    return;
                case 3:
                    for (T t3 : collection) {
                        writeSerializable(t3);
                    }
                    return;
                case 4:
                    for (T t4 : collection) {
                        writeString(t4);
                    }
                    return;
                case 5:
                    for (T t5 : collection) {
                        writeStrongBinder(t5);
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.setOutputField(int):void, block:B:2:0x0000 */
    protected <T> void writeArray(T[] tArr) {
        VersionedParcel outputField;
        outputField.setOutputField(tArr);
        outputField.writeArray(this);
    }

    public <T> void writeArray(T[] tArr, int i) {
        setOutputField(i);
        writeArray(tArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.setOutputField(int):void, block:B:2:0x0000 */
    public void writeVersionedParcelable(VersionedParcelable versionedParcelable) {
        VersionedParcel outputField;
        outputField.setOutputField(versionedParcelable);
        outputField.writeVersionedParcelable(this);
    }

    public void writeVersionedParcelable(VersionedParcelable versionedParcelable, int i) {
        setOutputField(i);
        writeVersionedParcelable(versionedParcelable);
    }

    public void writeSerializable(Serializable serializable, int i) {
        setOutputField(i);
        writeSerializable(serializable);
    }

    protected void writeNoException() {
        writeInt(0);
    }

    public Exception readException(Exception exc, int i) {
        int readExceptionCode;
        return (readField(i) && (readExceptionCode = readExceptionCode()) != 0) ? readException(readExceptionCode, readString()) : exc;
    }

    private int readExceptionCode() {
        return readInt();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r1 I:java.lang.Exception) = (r0 I:androidx.versionedparcelable.VersionedParcel), (r1 I:int), (r2 I:java.lang.String) type: DIRECT call: androidx.versionedparcelable.VersionedParcel.createException(int, java.lang.String):java.lang.Exception, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.versionedparcelable.VersionedParcel] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Exception, int] */
    private <T> int getType(T t) {
        ?? createException;
        return createException.createException(this, t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T, S extends Collection<T>> S readCollection(int i, S s) {
        return (S) createException(i, s);
    }

    private Exception readException(int i, String str) {
        return createException(i, str);
    }

    @NonNull
    protected static Throwable getRootCause(@NonNull Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    public byte readByte(byte b, int i) {
        return !readField(i) ? b : (byte) (readInt() & 255);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r2 I:boolean) = (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.readField(int):boolean, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.versionedparcelable.VersionedParcel] */
    protected <T> T[] readArray(T[] tArr) {
        ?? readField;
        return !readField.readField(tArr) ? this : (T[]) readField.readArray(this);
    }

    public <T> T[] readArray(T[] tArr, int i) {
        return !readField(i) ? tArr : (T[]) readArray(tArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r2 I:boolean) = (r0 I:androidx.versionedparcelable.VersionedParcel), (r2 I:int) type: VIRTUAL call: androidx.versionedparcelable.VersionedParcel.readField(int):boolean, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0006: RETURN  (r1 I:T extends androidx.versionedparcelable.VersionedParcelable), block:B:4:0x0006 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.versionedparcelable.VersionedParcel] */
    public <T extends VersionedParcelable> T readVersionedParcelable() {
        ?? readField;
        T t;
        return !readField.readField(this) ? t : (T) readField.readVersionedParcelable();
    }

    public <T extends VersionedParcelable> T readVersionedParcelable(T t, int i) {
        return !readField(i) ? t : (T) readVersionedParcelable();
    }

    /* renamed from: androidx.versionedparcelable.VersionedParcel$1 */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    class C14541 extends ObjectInputStream {
        C14541(InputStream inputStream) {
            super(inputStream);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.io.ObjectInputStream
        protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
            super((Throwable) objectStreamClass);
            return;
        }
    }

    protected static <T extends VersionedParcelable> T readFromParcel(String str, VersionedParcel versionedParcel) {
        try {
            return (T) Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class).invoke(null, versionedParcel);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    private void writeSerializable(Serializable serializable) {
        try {
            findParcelClass(this).getDeclaredMethod("write", getClass(), VersionedParcel.class).invoke(null, this, serializable);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    protected static <T extends VersionedParcelable> void writeToParcel(T t, VersionedParcel versionedParcel) {
        try {
            findParcelClass(t).getDeclaredMethod("write", t.getClass(), VersionedParcel.class).invoke(null, t, versionedParcel);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException: -2
        	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
        	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:25)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:136)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:41)
        */
    private java.lang.Exception createException(int r-1, java.lang.String r0) {
        /*
            r-2 = this;
            java.lang.Class r0 = r0.getClass()
            java.lang.Class r0 = findParcelClass(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcel.createException(int, java.lang.String):java.lang.Exception");
    }

    private static <T extends VersionedParcelable> Class findParcelClass(T t) throws ClassNotFoundException {
        return findParcelClass((Class<? extends VersionedParcelable>) t.getClass());
    }

    private static Class findParcelClass(Class<? extends VersionedParcelable> cls) throws ClassNotFoundException {
        return Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class ParcelException extends RuntimeException {
        public ParcelException(Throwable th) {
            super(th);
        }
    }
}
