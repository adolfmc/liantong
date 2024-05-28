package io.objectbox.query;

import io.objectbox.Property;
import io.objectbox.query.QueryBuilder;
import java.util.concurrent.Callable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PropertyQuery {
    boolean distinct;
    boolean enableNull;
    boolean noCaseIfDistinct = true;
    double nullValueDouble;
    float nullValueFloat;
    long nullValueLong;
    String nullValueString;
    final Property property;
    final int propertyId;
    final Query query;
    final long queryHandle;
    boolean unique;

    native double nativeAvg(long j, long j2, int i);

    native long nativeCount(long j, long j2, int i, boolean z);

    native byte[] nativeFindBytes(long j, long j2, int i, boolean z, boolean z2, byte b);

    native char[] nativeFindChars(long j, long j2, int i, boolean z, boolean z2, char c);

    native double[] nativeFindDoubles(long j, long j2, int i, boolean z, boolean z2, double d);

    native float[] nativeFindFloats(long j, long j2, int i, boolean z, boolean z2, float f);

    native int[] nativeFindInts(long j, long j2, int i, boolean z, boolean z2, int i2);

    native long[] nativeFindLongs(long j, long j2, int i, boolean z, boolean z2, long j3);

    native Object nativeFindNumber(long j, long j2, int i, boolean z, boolean z2, boolean z3, long j3, float f, double d);

    native short[] nativeFindShorts(long j, long j2, int i, boolean z, boolean z2, short s);

    native String nativeFindString(long j, long j2, int i, boolean z, boolean z2, boolean z3, boolean z4, String str);

    native String[] nativeFindStrings(long j, long j2, int i, boolean z, boolean z2, boolean z3, String str);

    native long nativeMax(long j, long j2, int i);

    native double nativeMaxDouble(long j, long j2, int i);

    native long nativeMin(long j, long j2, int i);

    native double nativeMinDouble(long j, long j2, int i);

    native long nativeSum(long j, long j2, int i);

    native double nativeSumDouble(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public PropertyQuery(Query query, Property property) {
        this.query = query;
        this.queryHandle = query.handle;
        this.property = property;
        this.propertyId = property.f24389id;
    }

    public PropertyQuery reset() {
        this.distinct = false;
        this.noCaseIfDistinct = true;
        this.unique = false;
        this.enableNull = false;
        this.nullValueDouble = 0.0d;
        this.nullValueFloat = 0.0f;
        this.nullValueString = null;
        this.nullValueLong = 0L;
        return this;
    }

    public PropertyQuery distinct() {
        this.distinct = true;
        return this;
    }

    public PropertyQuery distinct(QueryBuilder.StringOrder stringOrder) {
        if (this.property.type != String.class) {
            throw new RuntimeException("Reserved for string properties, but got " + this.property);
        }
        this.distinct = true;
        this.noCaseIfDistinct = stringOrder == QueryBuilder.StringOrder.CASE_INSENSITIVE;
        return this;
    }

    public PropertyQuery unique() {
        this.unique = true;
        return this;
    }

    public PropertyQuery nullValue(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Null values are not allowed");
        }
        boolean z = obj instanceof String;
        boolean z2 = obj instanceof Number;
        if (!z && !z2) {
            throw new IllegalArgumentException("Unsupported value class: " + obj.getClass());
        }
        this.enableNull = true;
        this.nullValueString = z ? (String) obj : null;
        boolean z3 = obj instanceof Float;
        this.nullValueFloat = z3 ? ((Float) obj).floatValue() : 0.0f;
        boolean z4 = obj instanceof Double;
        this.nullValueDouble = z4 ? ((Double) obj).doubleValue() : 0.0d;
        this.nullValueLong = (!z2 || z3 || z4) ? 0L : ((Number) obj).longValue();
        return this;
    }

    public String[] findStrings() {
        return (String[]) this.query.callInReadTx(new Callable<String[]>() { // from class: io.objectbox.query.PropertyQuery.1
            @Override // java.util.concurrent.Callable
            public String[] call() {
                boolean z = PropertyQuery.this.distinct && PropertyQuery.this.noCaseIfDistinct;
                long cursorHandle = PropertyQuery.this.query.cursorHandle();
                PropertyQuery propertyQuery = PropertyQuery.this;
                return propertyQuery.nativeFindStrings(propertyQuery.queryHandle, cursorHandle, PropertyQuery.this.propertyId, PropertyQuery.this.distinct, z, PropertyQuery.this.enableNull, PropertyQuery.this.nullValueString);
            }
        });
    }

    public long[] findLongs() {
        return (long[]) this.query.callInReadTx(new Callable<long[]>() { // from class: io.objectbox.query.PropertyQuery.2
            @Override // java.util.concurrent.Callable
            public long[] call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return propertyQuery.nativeFindLongs(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId, PropertyQuery.this.distinct, PropertyQuery.this.enableNull, PropertyQuery.this.nullValueLong);
            }
        });
    }

    public int[] findInts() {
        return (int[]) this.query.callInReadTx(new Callable<int[]>() { // from class: io.objectbox.query.PropertyQuery.3
            @Override // java.util.concurrent.Callable
            public int[] call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return propertyQuery.nativeFindInts(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId, PropertyQuery.this.distinct, PropertyQuery.this.enableNull, (int) PropertyQuery.this.nullValueLong);
            }
        });
    }

    public short[] findShorts() {
        return (short[]) this.query.callInReadTx(new Callable<short[]>() { // from class: io.objectbox.query.PropertyQuery.4
            @Override // java.util.concurrent.Callable
            public short[] call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return propertyQuery.nativeFindShorts(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId, PropertyQuery.this.distinct, PropertyQuery.this.enableNull, (short) PropertyQuery.this.nullValueLong);
            }
        });
    }

    public char[] findChars() {
        return (char[]) this.query.callInReadTx(new Callable<char[]>() { // from class: io.objectbox.query.PropertyQuery.5
            @Override // java.util.concurrent.Callable
            public char[] call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return propertyQuery.nativeFindChars(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId, PropertyQuery.this.distinct, PropertyQuery.this.enableNull, (char) PropertyQuery.this.nullValueLong);
            }
        });
    }

    public byte[] findBytes() {
        return (byte[]) this.query.callInReadTx(new Callable<byte[]>() { // from class: io.objectbox.query.PropertyQuery.6
            @Override // java.util.concurrent.Callable
            public byte[] call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return propertyQuery.nativeFindBytes(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId, PropertyQuery.this.distinct, PropertyQuery.this.enableNull, (byte) PropertyQuery.this.nullValueLong);
            }
        });
    }

    public float[] findFloats() {
        return (float[]) this.query.callInReadTx(new Callable<float[]>() { // from class: io.objectbox.query.PropertyQuery.7
            @Override // java.util.concurrent.Callable
            public float[] call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return propertyQuery.nativeFindFloats(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId, PropertyQuery.this.distinct, PropertyQuery.this.enableNull, PropertyQuery.this.nullValueFloat);
            }
        });
    }

    public double[] findDoubles() {
        return (double[]) this.query.callInReadTx(new Callable<double[]>() { // from class: io.objectbox.query.PropertyQuery.8
            @Override // java.util.concurrent.Callable
            public double[] call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return propertyQuery.nativeFindDoubles(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId, PropertyQuery.this.distinct, PropertyQuery.this.enableNull, PropertyQuery.this.nullValueDouble);
            }
        });
    }

    public String findString() {
        return (String) this.query.callInReadTx(new Callable<String>() { // from class: io.objectbox.query.PropertyQuery.9
            @Override // java.util.concurrent.Callable
            public String call() {
                boolean z = PropertyQuery.this.distinct && !PropertyQuery.this.noCaseIfDistinct;
                PropertyQuery propertyQuery = PropertyQuery.this;
                return propertyQuery.nativeFindString(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId, PropertyQuery.this.unique, PropertyQuery.this.distinct, z, PropertyQuery.this.enableNull, PropertyQuery.this.nullValueString);
            }
        });
    }

    private Object findNumber() {
        return this.query.callInReadTx(new Callable<Object>() { // from class: io.objectbox.query.PropertyQuery.10
            @Override // java.util.concurrent.Callable
            public Object call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return propertyQuery.nativeFindNumber(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId, PropertyQuery.this.unique, PropertyQuery.this.distinct, PropertyQuery.this.enableNull, PropertyQuery.this.nullValueLong, PropertyQuery.this.nullValueFloat, PropertyQuery.this.nullValueDouble);
            }
        });
    }

    public Long findLong() {
        return (Long) findNumber();
    }

    public Integer findInt() {
        return (Integer) findNumber();
    }

    public Short findShort() {
        return (Short) findNumber();
    }

    public Character findChar() {
        return (Character) findNumber();
    }

    public Byte findByte() {
        return (Byte) findNumber();
    }

    public Boolean findBoolean() {
        return (Boolean) findNumber();
    }

    public Float findFloat() {
        return (Float) findNumber();
    }

    public Double findDouble() {
        return (Double) findNumber();
    }

    public long sum() {
        return ((Long) this.query.callInReadTx(new Callable<Long>() { // from class: io.objectbox.query.PropertyQuery.11
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return Long.valueOf(propertyQuery.nativeSum(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId));
            }
        })).longValue();
    }

    public double sumDouble() {
        return ((Double) this.query.callInReadTx(new Callable<Double>() { // from class: io.objectbox.query.PropertyQuery.12
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Double call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return Double.valueOf(propertyQuery.nativeSumDouble(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId));
            }
        })).doubleValue();
    }

    public long max() {
        return ((Long) this.query.callInReadTx(new Callable<Long>() { // from class: io.objectbox.query.PropertyQuery.13
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return Long.valueOf(propertyQuery.nativeMax(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId));
            }
        })).longValue();
    }

    public double maxDouble() {
        return ((Double) this.query.callInReadTx(new Callable<Double>() { // from class: io.objectbox.query.PropertyQuery.14
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Double call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return Double.valueOf(propertyQuery.nativeMaxDouble(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId));
            }
        })).doubleValue();
    }

    public long min() {
        return ((Long) this.query.callInReadTx(new Callable<Long>() { // from class: io.objectbox.query.PropertyQuery.15
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return Long.valueOf(propertyQuery.nativeMin(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId));
            }
        })).longValue();
    }

    public double minDouble() {
        return ((Double) this.query.callInReadTx(new Callable<Double>() { // from class: io.objectbox.query.PropertyQuery.16
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Double call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return Double.valueOf(propertyQuery.nativeMinDouble(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId));
            }
        })).doubleValue();
    }

    public double avg() {
        return ((Double) this.query.callInReadTx(new Callable<Double>() { // from class: io.objectbox.query.PropertyQuery.17
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Double call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return Double.valueOf(propertyQuery.nativeAvg(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId));
            }
        })).doubleValue();
    }

    public long count() {
        return ((Long) this.query.callInReadTx(new Callable<Long>() { // from class: io.objectbox.query.PropertyQuery.18
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() {
                PropertyQuery propertyQuery = PropertyQuery.this;
                return Long.valueOf(propertyQuery.nativeCount(propertyQuery.queryHandle, PropertyQuery.this.query.cursorHandle(), PropertyQuery.this.propertyId, PropertyQuery.this.distinct));
            }
        })).longValue();
    }
}
