package com.networkbench.com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.$Gson$Types  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C$Gson$Types {

    /* renamed from: a */
    static final Type[] f17276a = new Type[0];

    private C$Gson$Types() {
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type type, Type type2, Type... typeArr) {
        return new C6673b(type, type2, typeArr);
    }

    public static GenericArrayType arrayOf(Type type) {
        return new C6672a(type);
    }

    public static WildcardType subtypeOf(Type type) {
        return new C6674c(new Type[]{type}, f17276a);
    }

    public static WildcardType supertypeOf(Type type) {
        return new C6674c(new Type[]{Object.class}, new Type[]{type});
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.networkbench.com.google.gson.internal.$Gson$Types$a] */
    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (cls.isArray()) {
                cls = new C6672a(canonicalize(cls.getComponentType()));
            }
            return cls;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new C6673b(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new C6672a(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                return new C6674c(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
            }
            return type;
        }
    }

    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            C$Gson$Preconditions.checkArgument(rawType instanceof Class);
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return getRawType(((WildcardType) type).getUpperBounds()[0]);
            }
            String name = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + name);
        }
    }

    /* renamed from: a */
    static boolean m8651a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean equals(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (type2 instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                ParameterizedType parameterizedType2 = (ParameterizedType) type2;
                return m8651a(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
            }
            return false;
        } else if (type instanceof GenericArrayType) {
            if (type2 instanceof GenericArrayType) {
                return equals(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return false;
        } else if (type instanceof WildcardType) {
            if (type2 instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                WildcardType wildcardType2 = (WildcardType) type2;
                return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
            }
            return false;
        } else if ((type instanceof TypeVariable) && (type2 instanceof TypeVariable)) {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
        } else {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int hashCodeOrZero(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    /* renamed from: a */
    static Type m8649a(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(interfaces[i])) {
                    return m8649a(cls.getGenericInterfaces()[i], interfaces[i], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<? super Object> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return m8649a(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    /* renamed from: b */
    static Type m8647b(Type type, Class<?> cls, Class<?> cls2) {
        C$Gson$Preconditions.checkArgument(cls2.isAssignableFrom(cls));
        return resolve(type, cls, m8649a(type, cls, cls2));
    }

    public static Type getArrayComponentType(Type type) {
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }
        return ((Class) type).getComponentType();
    }

    public static Type getCollectionElementType(Type type, Class<?> cls) {
        Type m8647b = m8647b(type, cls, Collection.class);
        if (m8647b instanceof WildcardType) {
            m8647b = ((WildcardType) m8647b).getUpperBounds()[0];
        }
        if (m8647b instanceof ParameterizedType) {
            return ((ParameterizedType) m8647b).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static Type[] getMapKeyAndValueTypes(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type m8647b = m8647b(type, cls, Map.class);
        return m8647b instanceof ParameterizedType ? ((ParameterizedType) m8647b).getActualTypeArguments() : new Type[]{Object.class, Object.class};
    }

    public static Type resolve(Type type, Class<?> cls, Type type2) {
        while (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            Type m8648a = m8648a(type, cls, typeVariable);
            if (m8648a == typeVariable) {
                return m8648a;
            }
            type2 = m8648a;
        }
        if (type2 instanceof Class) {
            Class cls2 = (Class) type2;
            if (cls2.isArray()) {
                Class<?> componentType = cls2.getComponentType();
                Type resolve = resolve(type, cls, componentType);
                return componentType == resolve ? cls2 : arrayOf(resolve);
            }
        }
        if (type2 instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type2;
            Type genericComponentType = genericArrayType.getGenericComponentType();
            Type resolve2 = resolve(type, cls, genericComponentType);
            return genericComponentType == resolve2 ? genericArrayType : arrayOf(resolve2);
        }
        if (type2 instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type2;
            Type ownerType = parameterizedType.getOwnerType();
            Type resolve3 = resolve(type, cls, ownerType);
            boolean z = resolve3 != ownerType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            int length = actualTypeArguments.length;
            for (int i = 0; i < length; i++) {
                Type resolve4 = resolve(type, cls, actualTypeArguments[i]);
                if (resolve4 != actualTypeArguments[i]) {
                    if (!z) {
                        actualTypeArguments = (Type[]) actualTypeArguments.clone();
                        z = true;
                    }
                    actualTypeArguments[i] = resolve4;
                }
            }
            return z ? newParameterizedTypeWithOwner(resolve3, parameterizedType.getRawType(), actualTypeArguments) : parameterizedType;
        } else if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type2;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Type[] upperBounds = wildcardType.getUpperBounds();
            if (lowerBounds.length == 1) {
                Type resolve5 = resolve(type, cls, lowerBounds[0]);
                if (resolve5 != lowerBounds[0]) {
                    return supertypeOf(resolve5);
                }
            } else if (upperBounds.length == 1) {
                Type resolve6 = resolve(type, cls, upperBounds[0]);
                if (resolve6 != upperBounds[0]) {
                    return subtypeOf(resolve6);
                }
            }
            return wildcardType;
        } else {
            return type2;
        }
    }

    public static Type getFirstTypeArgument(Type type) {
        try {
            if (type instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                if (actualTypeArguments.length == 0) {
                    return null;
                }
                return canonicalize(actualTypeArguments[0]);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    static Type m8648a(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class<?> declaringClassOf = declaringClassOf(typeVariable);
        if (declaringClassOf == null) {
            return typeVariable;
        }
        Type m8649a = m8649a(type, cls, declaringClassOf);
        if (m8649a instanceof ParameterizedType) {
            return ((ParameterizedType) m8649a).getActualTypeArguments()[indexOf(declaringClassOf.getTypeParameters(), typeVariable)];
        }
        return typeVariable;
    }

    private static int indexOf(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        Object genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkNotPrimitive(Type type) {
        C$Gson$Preconditions.checkArgument(((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.$Gson$Types$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C6673b implements Serializable, ParameterizedType {

        /* renamed from: d */
        private static final long f17279d = 0;

        /* renamed from: a */
        private final Type f17280a;

        /* renamed from: b */
        private final Type f17281b;

        /* renamed from: c */
        private final Type[] f17282c;

        public C6673b(Type type, Type type2, Type... typeArr) {
            int i = 0;
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                boolean z = true;
                boolean z2 = Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null;
                if (type == null && !z2) {
                    z = false;
                }
                C$Gson$Preconditions.checkArgument(z);
            }
            this.f17280a = type == null ? null : C$Gson$Types.canonicalize(type);
            this.f17281b = C$Gson$Types.canonicalize(type2);
            this.f17282c = (Type[]) typeArr.clone();
            while (true) {
                Type[] typeArr2 = this.f17282c;
                if (i >= typeArr2.length) {
                    return;
                }
                C$Gson$Preconditions.checkNotNull(typeArr2[i]);
                C$Gson$Types.checkNotPrimitive(this.f17282c[i]);
                Type[] typeArr3 = this.f17282c;
                typeArr3[i] = C$Gson$Types.canonicalize(typeArr3[i]);
                i++;
            }
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.f17282c.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.f17281b;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.f17280a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && C$Gson$Types.equals(this, (ParameterizedType) obj);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.f17282c) ^ this.f17281b.hashCode()) ^ C$Gson$Types.hashCodeOrZero(this.f17280a);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((this.f17282c.length + 1) * 30);
            sb.append(C$Gson$Types.typeToString(this.f17281b));
            if (this.f17282c.length == 0) {
                return sb.toString();
            }
            sb.append("<");
            sb.append(C$Gson$Types.typeToString(this.f17282c[0]));
            for (int i = 1; i < this.f17282c.length; i++) {
                sb.append(", ");
                sb.append(C$Gson$Types.typeToString(this.f17282c[i]));
            }
            sb.append(">");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.$Gson$Types$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C6672a implements Serializable, GenericArrayType {

        /* renamed from: b */
        private static final long f17277b = 0;

        /* renamed from: a */
        private final Type f17278a;

        public C6672a(Type type) {
            this.f17278a = C$Gson$Types.canonicalize(type);
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.f17278a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && C$Gson$Types.equals(this, (GenericArrayType) obj);
        }

        public int hashCode() {
            return this.f17278a.hashCode();
        }

        public String toString() {
            return C$Gson$Types.typeToString(this.f17278a) + "[]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.$Gson$Types$c */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C6674c implements Serializable, WildcardType {

        /* renamed from: c */
        private static final long f17283c = 0;

        /* renamed from: a */
        private final Type f17284a;

        /* renamed from: b */
        private final Type f17285b;

        public C6674c(Type[] typeArr, Type[] typeArr2) {
            C$Gson$Preconditions.checkArgument(typeArr2.length <= 1);
            C$Gson$Preconditions.checkArgument(typeArr.length == 1);
            if (typeArr2.length == 1) {
                C$Gson$Preconditions.checkNotNull(typeArr2[0]);
                C$Gson$Types.checkNotPrimitive(typeArr2[0]);
                C$Gson$Preconditions.checkArgument(typeArr[0] == Object.class);
                this.f17285b = C$Gson$Types.canonicalize(typeArr2[0]);
                this.f17284a = Object.class;
                return;
            }
            C$Gson$Preconditions.checkNotNull(typeArr[0]);
            C$Gson$Types.checkNotPrimitive(typeArr[0]);
            this.f17285b = null;
            this.f17284a = C$Gson$Types.canonicalize(typeArr[0]);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return new Type[]{this.f17284a};
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            Type type = this.f17285b;
            return type != null ? new Type[]{type} : C$Gson$Types.f17276a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && C$Gson$Types.equals(this, (WildcardType) obj);
        }

        public int hashCode() {
            Type type = this.f17285b;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.f17284a.hashCode() + 31);
        }

        public String toString() {
            if (this.f17285b != null) {
                return "? super " + C$Gson$Types.typeToString(this.f17285b);
            } else if (this.f17284a == Object.class) {
                return "?";
            } else {
                return "? extends " + C$Gson$Types.typeToString(this.f17284a);
            }
        }
    }
}
