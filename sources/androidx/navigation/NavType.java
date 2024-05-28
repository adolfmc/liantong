package androidx.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class NavType<T> {
    private final boolean mNullableAllowed;
    @NonNull
    public static final NavType<Integer> IntType = new NavType<Integer>(false) { // from class: androidx.navigation.NavType.1
        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "integer";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        @NonNull
        public Integer parseValue(@NonNull String str) {
            return parseValue(str);
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Integer num) {
            bundle.putInt(str, num.intValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Integer get(@NonNull Bundle bundle, @NonNull String str) {
            return (Integer) bundle.get(str);
        }
    };
    @NonNull
    public static final NavType<Integer> ReferenceType = new NavType<Integer>(false) { // from class: androidx.navigation.NavType.2
        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "reference";
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @AnyRes @NonNull Integer num) {
            bundle.putInt(str, num.intValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        @AnyRes
        public Integer get(@NonNull Bundle bundle, @NonNull String str) {
            return (Integer) bundle.get(str);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        @NonNull
        public Integer parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("References don't support parsing string values.");
        }
    };
    @NonNull
    public static final NavType<int[]> IntArrayType = new NavType<int[]>(true) { // from class: androidx.navigation.NavType.3
        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "integer[]";
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable int[] iArr) {
            bundle.putIntArray(str, iArr);
        }

        @Override // androidx.navigation.NavType
        public int[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (int[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public int[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    @NonNull
    public static final NavType<Long> LongType = new NavType<Long>(false) { // from class: androidx.navigation.NavType.4
        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "long";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        @NonNull
        public Long parseValue(@NonNull String str) {
            return parseValue(str);
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Long l) {
            bundle.putLong(str, l.longValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Long get(@NonNull Bundle bundle, @NonNull String str) {
            return (Long) bundle.get(str);
        }
    };
    @NonNull
    public static final NavType<long[]> LongArrayType = new NavType<long[]>(true) { // from class: androidx.navigation.NavType.5
        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "long[]";
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable long[] jArr) {
            bundle.putLongArray(str, jArr);
        }

        @Override // androidx.navigation.NavType
        public long[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (long[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public long[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    @NonNull
    public static final NavType<Float> FloatType = new NavType<Float>(false) { // from class: androidx.navigation.NavType.6
        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "float";
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Float f) {
            bundle.putFloat(str, f.floatValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Float get(@NonNull Bundle bundle, @NonNull String str) {
            return (Float) bundle.get(str);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        @NonNull
        public Float parseValue(@NonNull String str) {
            return Float.valueOf(Float.parseFloat(str));
        }
    };
    @NonNull
    public static final NavType<float[]> FloatArrayType = new NavType<float[]>(true) { // from class: androidx.navigation.NavType.7
        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "float[]";
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable float[] fArr) {
            bundle.putFloatArray(str, fArr);
        }

        @Override // androidx.navigation.NavType
        public float[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (float[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public float[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    @NonNull
    public static final NavType<Boolean> BoolType = new NavType<Boolean>(false) { // from class: androidx.navigation.NavType.8
        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "boolean";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        @NonNull
        public Boolean parseValue(@NonNull String str) {
            return parseValue(str);
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Boolean bool) {
            bundle.putBoolean(str, bool.booleanValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Boolean get(@NonNull Bundle bundle, @NonNull String str) {
            return (Boolean) bundle.get(str);
        }
    };
    @NonNull
    public static final NavType<boolean[]> BoolArrayType = new NavType<boolean[]>(true) { // from class: androidx.navigation.NavType.9
        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "boolean[]";
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable boolean[] zArr) {
            bundle.putBooleanArray(str, zArr);
        }

        @Override // androidx.navigation.NavType
        public boolean[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (boolean[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public boolean[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    @NonNull
    public static final NavType<String> StringType = new NavType<String>(true) { // from class: androidx.navigation.NavType.10
        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "string";
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String parseValue(@NonNull String str) {
            return str;
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable String str2) {
            bundle.putString(str, str2);
        }

        @Override // androidx.navigation.NavType
        public String get(@NonNull Bundle bundle, @NonNull String str) {
            return (String) bundle.get(str);
        }
    };
    @NonNull
    public static final NavType<String[]> StringArrayType = new NavType<String[]>(true) { // from class: androidx.navigation.NavType.11
        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "string[]";
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable String[] strArr) {
            bundle.putStringArray(str, strArr);
        }

        @Override // androidx.navigation.NavType
        public String[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (String[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };

    @Nullable
    public abstract T get(@NonNull Bundle bundle, @NonNull String str);

    @NonNull
    public abstract String getName();

    @NonNull
    public abstract T parseValue(@NonNull String str);

    public abstract void put(@NonNull Bundle bundle, @NonNull String str, @Nullable T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public NavType(boolean z) {
        this.mNullableAllowed = z;
    }

    public boolean isNullableAllowed() {
        return this.mNullableAllowed;
    }

    @NonNull
    T parseAndPut(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2) {
        T parseValue = parseValue(str2);
        put(bundle, str, parseValue);
        return parseValue;
    }

    @NonNull
    public String toString() {
        return getName();
    }

    @NonNull
    public static NavType<?> fromArgType(@Nullable String str, @Nullable String str2) {
        String str3;
        if (IntType.getName().equals(str)) {
            return IntType;
        }
        if (IntArrayType.getName().equals(str)) {
            return IntArrayType;
        }
        if (LongType.getName().equals(str)) {
            return LongType;
        }
        if (LongArrayType.getName().equals(str)) {
            return LongArrayType;
        }
        if (BoolType.getName().equals(str)) {
            return BoolType;
        }
        if (BoolArrayType.getName().equals(str)) {
            return BoolArrayType;
        }
        if (StringType.getName().equals(str)) {
            return StringType;
        }
        if (StringArrayType.getName().equals(str)) {
            return StringArrayType;
        }
        if (FloatType.getName().equals(str)) {
            return FloatType;
        }
        if (FloatArrayType.getName().equals(str)) {
            return FloatArrayType;
        }
        if (ReferenceType.getName().equals(str)) {
            return ReferenceType;
        }
        if (str != null && !str.isEmpty()) {
            try {
                if (!str.startsWith(".") || str2 == null) {
                    str3 = str;
                } else {
                    str3 = str2 + str;
                }
                if (str.endsWith("[]")) {
                    str3 = str3.substring(0, str3.length() - 2);
                    Class<?> cls = Class.forName(str3);
                    if (Parcelable.class.isAssignableFrom(cls)) {
                        return new ParcelableArrayType(cls);
                    }
                    if (Serializable.class.isAssignableFrom(cls)) {
                        return new SerializableArrayType(cls);
                    }
                } else {
                    Class<?> cls2 = Class.forName(str3);
                    if (Parcelable.class.isAssignableFrom(cls2)) {
                        return new ParcelableType(cls2);
                    }
                    if (Enum.class.isAssignableFrom(cls2)) {
                        return new EnumType(cls2);
                    }
                    if (Serializable.class.isAssignableFrom(cls2)) {
                        return new SerializableType(cls2);
                    }
                }
                throw new IllegalArgumentException(str3 + " is not Serializable or Parcelable.");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return StringType;
    }

    @NonNull
    static NavType inferFromValue(@NonNull String str) {
        try {
            try {
                try {
                    try {
                        IntType.parseValue(str);
                        return IntType;
                    } catch (IllegalArgumentException unused) {
                        LongType.parseValue(str);
                        return LongType;
                    }
                } catch (IllegalArgumentException unused2) {
                    BoolType.parseValue(str);
                    return BoolType;
                }
            } catch (IllegalArgumentException unused3) {
                FloatType.parseValue(str);
                return FloatType;
            }
        } catch (IllegalArgumentException unused4) {
            return StringType;
        }
    }

    @NonNull
    static NavType inferFromValueType(@Nullable Object obj) {
        if (obj instanceof Integer) {
            return IntType;
        }
        if (obj instanceof int[]) {
            return IntArrayType;
        }
        if (obj instanceof Long) {
            return LongType;
        }
        if (obj instanceof long[]) {
            return LongArrayType;
        }
        if (obj instanceof Float) {
            return FloatType;
        }
        if (obj instanceof float[]) {
            return FloatArrayType;
        }
        if (obj instanceof Boolean) {
            return BoolType;
        }
        if (obj instanceof boolean[]) {
            return BoolArrayType;
        }
        if ((obj instanceof String) || obj == null) {
            return StringType;
        }
        if (obj instanceof String[]) {
            return StringArrayType;
        }
        if (obj.getClass().isArray() && Parcelable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new ParcelableArrayType(obj.getClass().getComponentType());
        }
        if (obj.getClass().isArray() && Serializable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new SerializableArrayType(obj.getClass().getComponentType());
        }
        if (obj instanceof Parcelable) {
            return new ParcelableType(obj.getClass());
        }
        if (obj instanceof Enum) {
            return new EnumType(obj.getClass());
        }
        if (obj instanceof Serializable) {
            return new SerializableType(obj.getClass());
        }
        throw new IllegalArgumentException("Object of type " + obj.getClass().getName() + " is not supported for navigation arguments.");
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static final class ParcelableArrayType<D extends Parcelable> extends NavType<D[]> {
        @NonNull
        private final Class<D[]> mArrayType;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0, types: [androidx.navigation.NavType, androidx.navigation.NavType$ParcelableType] */
        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String navType, @Nullable D[] dArr) {
            ?? navType2 = new NavType(true);
            if (!Parcelable.class.isAssignableFrom(dArr) && !Serializable.class.isAssignableFrom(dArr)) {
                throw new IllegalArgumentException(dArr + " does not implement Parcelable or Serializable.");
            }
            ((ParcelableType) navType2).mType = dArr;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r1 I:android.os.Parcelable[]) = (r0 I:androidx.navigation.NavType$ParcelableArrayType), (r1 I:android.os.Bundle), (r2 I:java.lang.String) type: VIRTUAL call: androidx.navigation.NavType.ParcelableArrayType.get(android.os.Bundle, java.lang.String):android.os.Parcelable[], block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.NavType$ParcelableArrayType] */
        /* JADX WARN: Type inference failed for: r1v1, types: [boolean, android.os.Parcelable[]] */
        public boolean equals(Object obj) {
            ?? r0;
            return r0.get(this, obj);
        }

        public ParcelableArrayType(@NonNull Class<D> cls) {
            super(true);
            if (!Parcelable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Parcelable.");
            }
            try {
                this.mArrayType = (Class<D[]>) Class.forName("[L" + cls.getName() + ";");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // androidx.navigation.NavType
        @Nullable
        public D[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (D[]) ((Parcelable[]) bundle.get(str));
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public D[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return this.mArrayType.getName();
        }

        public int hashCode() {
            return this.mArrayType.hashCode();
        }
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static final class ParcelableType<D> extends NavType<D> {
        @NonNull
        private final Class<D> mType;

        public ParcelableType(@NonNull Class<D> cls) {
            super(true);
            if (!Parcelable.class.isAssignableFrom(cls) && !Serializable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Parcelable or Serializable.");
            }
            this.mType = cls;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [boolean, java.lang.Object] */
        public boolean equals(Object obj) {
            return get(obj);
        }

        @Override // androidx.navigation.NavType
        @Nullable
        public D get(@NonNull Bundle bundle, @NonNull String str) {
            return (D) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public D parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Parcelables don't support default values.");
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return this.mType.getName();
        }

        public int hashCode() {
            return this.mType.hashCode();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0, types: [androidx.navigation.NavType, androidx.navigation.NavType$SerializableArrayType] */
        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String navType, @Nullable D d) {
            ?? navType2 = new NavType(true);
            if (!Serializable.class.isAssignableFrom(d)) {
                throw new IllegalArgumentException(d + " does not implement Serializable.");
            }
            try {
                ((SerializableArrayType) navType2).mArrayType = Class.forName("[L" + d.getName() + ";");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class SerializableType<D extends Serializable> extends NavType<D> {
        @NonNull
        private final Class<D> mType;

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable D d) {
            put(bundle, str, (String) d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.navigation.NavType
        public /* bridge */ /* synthetic */ void put(@NonNull Bundle bundle, @NonNull String str, @Nullable Object obj) {
            put(bundle, str, (String) obj);
        }

        public SerializableType(@NonNull Class<D> cls) {
            super(true);
            if (!Serializable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Serializable.");
            } else if (cls.isEnum()) {
                throw new IllegalArgumentException(cls + " is an Enum. You should use EnumType instead.");
            } else {
                this.mType = cls;
            }
        }

        SerializableType(boolean z, @NonNull Class<D> cls) {
            super(z);
            if (!Serializable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Serializable.");
            }
            this.mType = cls;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [boolean, java.io.Serializable] */
        public boolean equals(Object obj) {
            return (Serializable) get(obj);
        }

        @Override // androidx.navigation.NavType
        @Nullable
        public D get(@NonNull Bundle bundle, @NonNull String str) {
            return (D) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public D parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Serializables don't support default values.");
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return this.mType.getName();
        }

        public int hashCode() {
            return this.mType.hashCode();
        }
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static final class SerializableArrayType<D extends Serializable> extends NavType<D[]> {
        @NonNull
        private final Class<D[]> mArrayType;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0, types: [androidx.navigation.NavType, androidx.navigation.NavType$SerializableType] */
        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String navType, @Nullable D[] dArr) {
            ?? navType2 = new NavType(true);
            if (!Serializable.class.isAssignableFrom(dArr)) {
                throw new IllegalArgumentException(dArr + " does not implement Serializable.");
            } else if (dArr.isEnum()) {
                throw new IllegalArgumentException(dArr + " is an Enum. You should use EnumType instead.");
            } else {
                ((SerializableType) navType2).mType = dArr;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r1 I:java.io.Serializable[]) = (r0 I:androidx.navigation.NavType$SerializableArrayType), (r1 I:android.os.Bundle), (r2 I:java.lang.String) type: VIRTUAL call: androidx.navigation.NavType.SerializableArrayType.get(android.os.Bundle, java.lang.String):java.io.Serializable[], block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.NavType$SerializableArrayType] */
        /* JADX WARN: Type inference failed for: r1v1, types: [boolean, java.io.Serializable[]] */
        public boolean equals(Object obj) {
            ?? r0;
            return r0.get(this, obj);
        }

        public SerializableArrayType(@NonNull Class<D> cls) {
            super(true);
            if (!Serializable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Serializable.");
            }
            try {
                this.mArrayType = (Class<D[]>) Class.forName("[L" + cls.getName() + ";");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // androidx.navigation.NavType
        @Nullable
        public D[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (D[]) ((Serializable[]) bundle.get(str));
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public D[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return this.mArrayType.getName();
        }

        public int hashCode() {
            return this.mArrayType.hashCode();
        }
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static final class EnumType<D extends Enum> extends SerializableType<D> {
        @NonNull
        private final Class<D> mType;

        @Override // androidx.navigation.NavType.SerializableType, androidx.navigation.NavType
        @NonNull
        public D parseValue(@NonNull String str) {
            return parseValue(str);
        }

        public EnumType(@NonNull Class<D> cls) {
            super(false, cls);
            if (!cls.isEnum()) {
                throw new IllegalArgumentException(cls + " is not an Enum type.");
            }
            this.mType = cls;
        }

        @Override // androidx.navigation.NavType.SerializableType, androidx.navigation.NavType
        @NonNull
        public String getName() {
            return this.mType.getName();
        }
    }
}
