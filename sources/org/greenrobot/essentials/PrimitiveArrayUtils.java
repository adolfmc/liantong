package org.greenrobot.essentials;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteOrder;
import java.util.NoSuchElementException;
import sun.misc.Unsafe;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class PrimitiveArrayUtils {
    private static final PrimitiveArrayUtils instance;
    private static final PrimitiveArrayUtils instanceSafe = new SafeImpl();

    public abstract int getIntBE(byte[] bArr, int i);

    public abstract int getIntLE(byte[] bArr, int i);

    public abstract int getIntLE(char[] cArr, int i);

    public abstract long getLongBE(byte[] bArr, int i);

    public abstract long getLongLE(byte[] bArr, int i);

    static {
        PrimitiveArrayUtils primitiveArrayUtils = null;
        try {
            if (UnsafeImpl.UNSAFE != null) {
                primitiveArrayUtils = new UnsafeImpl();
            }
        } catch (Throwable unused) {
        }
        if (primitiveArrayUtils == null) {
            primitiveArrayUtils = instanceSafe;
        }
        instance = primitiveArrayUtils;
    }

    public static PrimitiveArrayUtils getInstance() {
        return instance;
    }

    public static PrimitiveArrayUtils getInstanceSafe() {
        return instanceSafe;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class UnsafeImpl extends PrimitiveArrayUtils {
        private static final long BYTE_ARRAY_OFFSET;
        private static final long CHAR_ARRAY_OFFSET;
        private static final Unsafe UNSAFE;
        private static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
        private static final boolean UNALIGNED = initUnaligned();

        private UnsafeImpl() {
        }

        static {
            if (UNALIGNED) {
                UNSAFE = initUnsafe();
                BYTE_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(byte[].class);
                CHAR_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(char[].class);
                return;
            }
            UNSAFE = null;
            BYTE_ARRAY_OFFSET = 0L;
            CHAR_ARRAY_OFFSET = 0L;
        }

        private static boolean initUnaligned() {
            String property = System.getProperty("java.vendor");
            if (property != null ? property.contains("Android") : false) {
                return guessUnalignedFromOsArch();
            }
            try {
                Method declaredMethod = Class.forName("java.nio.Bits", false, ClassLoader.getSystemClassLoader()).getDeclaredMethod("unaligned", new Class[0]);
                declaredMethod.setAccessible(true);
                return Boolean.TRUE.equals(declaredMethod.invoke(null, new Object[0]));
            } catch (Throwable unused) {
                return guessUnalignedFromOsArch();
            }
        }

        private static boolean guessUnalignedFromOsArch() {
            String property = System.getProperty("os.arch");
            return property != null && property.matches("^(i[3-6]86|x86(_64)?|x64|amd64)$");
        }

        private static Unsafe initUnsafe() {
            Field declaredField;
            try {
                try {
                    declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                } catch (NoSuchElementException unused) {
                    declaredField = Unsafe.class.getDeclaredField("THE_ONE");
                }
                declaredField.setAccessible(true);
                Unsafe unsafe = (Unsafe) declaredField.get(null);
                int i = unsafe.getInt(new byte[]{-54, -2, -70, -66}, unsafe.arrayBaseOffset(byte[].class));
                if (i == -889275714) {
                    if (BIG_ENDIAN) {
                        return unsafe;
                    }
                    System.err.println("Big endian confusion");
                } else if (i == -1095041334) {
                    if (!BIG_ENDIAN) {
                        return unsafe;
                    }
                    System.err.println("Little endian confusion");
                }
            } catch (Throwable unused2) {
            }
            return null;
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntLE(byte[] bArr, int i) {
            int i2 = UNSAFE.getInt(bArr, BYTE_ARRAY_OFFSET + i);
            return BIG_ENDIAN ? Integer.reverseBytes(i2) : i2;
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntLE(char[] cArr, int i) {
            int i2 = UNSAFE.getInt(cArr, CHAR_ARRAY_OFFSET + (i << 2));
            return BIG_ENDIAN ? Integer.reverseBytes(i2) : i2;
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntBE(byte[] bArr, int i) {
            int i2 = UNSAFE.getInt(bArr, BYTE_ARRAY_OFFSET + i);
            return BIG_ENDIAN ? i2 : Integer.reverseBytes(i2);
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public long getLongLE(byte[] bArr, int i) {
            long j = UNSAFE.getLong(bArr, BYTE_ARRAY_OFFSET + i);
            return BIG_ENDIAN ? Long.reverseBytes(j) : j;
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public long getLongBE(byte[] bArr, int i) {
            long j = UNSAFE.getLong(bArr, BYTE_ARRAY_OFFSET + i);
            return BIG_ENDIAN ? j : Long.reverseBytes(j);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class SafeImpl extends PrimitiveArrayUtils {
        private SafeImpl() {
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntLE(byte[] bArr, int i) {
            return (bArr[i + 3] << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntBE(byte[] bArr, int i) {
            return (bArr[i] << 24) | (bArr[i + 3] & 255) | ((bArr[i + 2] & 255) << 8) | ((bArr[i + 1] & 255) << 16);
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public long getLongLE(byte[] bArr, int i) {
            return (bArr[i + 7] << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public long getLongBE(byte[] bArr, int i) {
            return (bArr[i] << 56) | (bArr[i + 7] & 255) | ((bArr[i + 6] & 255) << 8) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 2] & 255) << 40) | ((bArr[i + 1] & 255) << 48);
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntLE(char[] cArr, int i) {
            return ((cArr[i + 1] & 65535) << 16) | (cArr[i] & 65535);
        }
    }
}
