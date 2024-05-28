package com.google.protobuf;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
final class UnsafeUtil {
    private static final Unsafe UNSAFE = getUnsafe();
    private static final boolean HAS_UNSAFE_BYTEBUFFER_OPERATIONS = supportsUnsafeByteBufferOperations();
    private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = supportsUnsafeArrayOperations();
    private static final long ARRAY_BASE_OFFSET = byteArrayBaseOffset();
    private static final long BUFFER_ADDRESS_OFFSET = fieldOffset(field(Buffer.class, "address"));

    private UnsafeUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasUnsafeArrayOperations() {
        return HAS_UNSAFE_ARRAY_OPERATIONS;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasUnsafeByteBufferOperations() {
        return HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
    }

    static Object allocateInstance(Class<?> cls) {
        try {
            return UNSAFE.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long objectFieldOffset(java.lang.reflect.Field field) {
        return UNSAFE.objectFieldOffset(field);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getArrayBaseOffset() {
        return ARRAY_BASE_OFFSET;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte getByte(Object obj, long j) {
        return UNSAFE.getByte(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putByte(Object obj, long j, byte b) {
        UNSAFE.putByte(obj, j, b);
    }

    static int getInt(Object obj, long j) {
        return UNSAFE.getInt(obj, j);
    }

    static void putInt(Object obj, long j, int i) {
        UNSAFE.putInt(obj, j, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getLong(Object obj, long j) {
        return UNSAFE.getLong(obj, j);
    }

    static void putLong(Object obj, long j, long j2) {
        UNSAFE.putLong(obj, j, j2);
    }

    static boolean getBoolean(Object obj, long j) {
        return UNSAFE.getBoolean(obj, j);
    }

    static void putBoolean(Object obj, long j, boolean z) {
        UNSAFE.putBoolean(obj, j, z);
    }

    static float getFloat(Object obj, long j) {
        return UNSAFE.getFloat(obj, j);
    }

    static void putFloat(Object obj, long j, float f) {
        UNSAFE.putFloat(obj, j, f);
    }

    static double getDouble(Object obj, long j) {
        return UNSAFE.getDouble(obj, j);
    }

    static void putDouble(Object obj, long j, double d) {
        UNSAFE.putDouble(obj, j, d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object getObject(Object obj, long j) {
        return UNSAFE.getObject(obj, j);
    }

    static void putObject(Object obj, long j, Object obj2) {
        UNSAFE.putObject(obj, j, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void copyMemory(Object obj, long j, Object obj2, long j2, long j3) {
        UNSAFE.copyMemory(obj, j, obj2, j2, j3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte getByte(long j) {
        return UNSAFE.getByte(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putByte(long j, byte b) {
        UNSAFE.putByte(j, b);
    }

    static int getInt(long j) {
        return UNSAFE.getInt(j);
    }

    static void putInt(long j, int i) {
        UNSAFE.putInt(j, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getLong(long j) {
        return UNSAFE.getLong(j);
    }

    static void putLong(long j, long j2) {
        UNSAFE.putLong(j, j2);
    }

    static void copyMemory(long j, long j2, long j3) {
        UNSAFE.copyMemory(j, j2, j3);
    }

    static void setMemory(long j, long j2, byte b) {
        UNSAFE.setMemory(j, j2, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long addressOffset(ByteBuffer byteBuffer) {
        return UNSAFE.getLong(byteBuffer, BUFFER_ADDRESS_OFFSET);
    }

    private static Unsafe getUnsafe() {
        try {
            return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.protobuf.UnsafeUtil.1
                @Override // java.security.PrivilegedExceptionAction
                public Unsafe run() throws Exception {
                    java.lang.reflect.Field[] declaredFields;
                    for (java.lang.reflect.Field field : Unsafe.class.getDeclaredFields()) {
                        field.setAccessible(true);
                        Object obj = field.get(null);
                        if (Unsafe.class.isInstance(obj)) {
                            return (Unsafe) Unsafe.class.cast(obj);
                        }
                    }
                    return null;
                }
            });
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean supportsUnsafeArrayOperations() {
        Unsafe unsafe = UNSAFE;
        if (unsafe != null) {
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", java.lang.reflect.Field.class);
                cls.getMethod("allocateInstance", Class.class);
                cls.getMethod("arrayBaseOffset", Class.class);
                cls.getMethod("getByte", Object.class, Long.TYPE);
                cls.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
                cls.getMethod("getBoolean", Object.class, Long.TYPE);
                cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
                cls.getMethod("getInt", Object.class, Long.TYPE);
                cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
                cls.getMethod("getFloat", Object.class, Long.TYPE);
                cls.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
                cls.getMethod("getDouble", Object.class, Long.TYPE);
                cls.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
                cls.getMethod("getObject", Object.class, Long.TYPE);
                cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
                cls.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
                return true;
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private static boolean supportsUnsafeByteBufferOperations() {
        Unsafe unsafe = UNSAFE;
        if (unsafe != null) {
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", java.lang.reflect.Field.class);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                cls.getMethod("getByte", Long.TYPE);
                cls.getMethod("putByte", Long.TYPE, Byte.TYPE);
                cls.getMethod("getInt", Long.TYPE);
                cls.getMethod("putInt", Long.TYPE, Integer.TYPE);
                cls.getMethod("getLong", Long.TYPE);
                cls.getMethod("putLong", Long.TYPE, Long.TYPE);
                cls.getMethod("setMemory", Long.TYPE, Long.TYPE, Byte.TYPE);
                cls.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
                return true;
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private static int byteArrayBaseOffset() {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            return UNSAFE.arrayBaseOffset(byte[].class);
        }
        return -1;
    }

    private static long fieldOffset(java.lang.reflect.Field field) {
        Unsafe unsafe;
        if (field == null || (unsafe = UNSAFE) == null) {
            return -1L;
        }
        return unsafe.objectFieldOffset(field);
    }

    private static java.lang.reflect.Field field(Class<?> cls, String str) {
        try {
            java.lang.reflect.Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }
}
