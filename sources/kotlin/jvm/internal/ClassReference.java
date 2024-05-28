package kotlin.jvm.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Tuples;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMapping;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.functions.Functions;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ClassReference.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 K2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001KB\u0011\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010B\u001a\u00020\u00122\b\u0010C\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010D\u001a\u00020EH\u0002J\b\u0010F\u001a\u00020GH\u0016J\u0012\u0010H\u001a\u00020\u00122\b\u0010I\u001a\u0004\u0018\u00010\u0002H\u0017J\b\u0010J\u001a\u00020-H\u0016R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0018\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u0018\u0010\u0015R\u001a\u0010\u001a\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001a\u0010\u0015R\u001a\u0010\u001c\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001c\u0010\u0015R\u001a\u0010\u001e\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001f\u0010\u0014\u001a\u0004\b\u001e\u0010\u0015R\u001a\u0010 \u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b!\u0010\u0014\u001a\u0004\b \u0010\u0015R\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001e\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030%0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0010R\u001e\u0010'\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0010R\u0016\u0010)\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0016\u0010,\u001a\u0004\u0018\u00010-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R(\u00100\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00010\b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b1\u0010\u0014\u001a\u0004\b2\u0010\u000bR\u0016\u00103\u001a\u0004\u0018\u00010-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u0010/R \u00105\u001a\b\u0012\u0004\u0012\u0002060\b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b7\u0010\u0014\u001a\u0004\b8\u0010\u000bR \u00109\u001a\b\u0012\u0004\u0012\u00020:0\b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b;\u0010\u0014\u001a\u0004\b<\u0010\u000bR\u001c\u0010=\u001a\u0004\u0018\u00010>8VX\u0097\u0004¢\u0006\f\u0012\u0004\b?\u0010\u0014\u001a\u0004\b@\u0010A¨\u0006L"}, m1890d2 = {"Lkotlin/jvm/internal/ClassReference;", "Lkotlin/reflect/KClass;", "", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "jClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "constructors", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "isAbstract", "", "isAbstract$annotations", "()V", "()Z", "isCompanion", "isCompanion$annotations", "isData", "isData$annotations", "isFinal", "isFinal$annotations", "isInner", "isInner$annotations", "isOpen", "isOpen$annotations", "isSealed", "isSealed$annotations", "getJClass", "()Ljava/lang/Class;", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "qualifiedName", "", "getQualifiedName", "()Ljava/lang/String;", "sealedSubclasses", "sealedSubclasses$annotations", "getSealedSubclasses", "simpleName", "getSimpleName", "supertypes", "Lkotlin/reflect/KType;", "supertypes$annotations", "getSupertypes", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "typeParameters$annotations", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "visibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "equals", "other", "error", "", "hashCode", "", "isInstance", "value", "toString", "Companion", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ClassReference implements ClassBasedDeclarationContainer, KClass<Object> {
    public static final Companion Companion = new Companion(null);
    private static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES;
    private static final HashMap<String, String> classFqNames;
    private static final HashMap<String, String> primitiveFqNames;
    private static final HashMap<String, String> primitiveWrapperFqNames;
    private static final Map<String, String> simpleNames;
    @NotNull
    private final Class<?> jClass;

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isAbstract$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isCompanion$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isData$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isFinal$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isInner$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isOpen$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isSealed$annotations() {
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void sealedSubclasses$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void supertypes$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void typeParameters$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void visibility$annotations() {
    }

    public ClassReference(@NotNull Class<?> jClass) {
        Intrinsics.checkParameterIsNotNull(jClass, "jClass");
        this.jClass = jClass;
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    @NotNull
    public Class<?> getJClass() {
        return this.jClass;
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public String getSimpleName() {
        return Companion.getClassSimpleName(getJClass());
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public String getQualifiedName() {
        return Companion.getClassQualifiedName(getJClass());
    }

    @Override // kotlin.reflect.KDeclarationContainer, kotlin.reflect.KClass
    @NotNull
    public Collection<KCallable<?>> getMembers() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public Collection<KFunction<Object>> getConstructors() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public Collection<KClass<?>> getNestedClasses() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KAnnotatedElement
    @NotNull
    public List<Annotation> getAnnotations() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public Object getObjectInstance() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @SinceKotlin(version = "1.1")
    public boolean isInstance(@Nullable Object obj) {
        return Companion.isInstance(obj, getJClass());
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public List<KTypeParameter> getTypeParameters() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public List<KType> getSupertypes() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public List<KClass<? extends Object>> getSealedSubclasses() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public KVisibility getVisibility() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isFinal() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isOpen() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isAbstract() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isSealed() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isData() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isInner() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isCompanion() {
        error();
        throw null;
    }

    private final Void error() {
        throw new KotlinReflectionNotSupportedError();
    }

    @Override // kotlin.reflect.KClass
    public boolean equals(@Nullable Object obj) {
        return (obj instanceof ClassReference) && Intrinsics.areEqual(JvmClassMapping.getJavaObjectType(this), JvmClassMapping.getJavaObjectType((KClass) obj));
    }

    @Override // kotlin.reflect.KClass
    public int hashCode() {
        return JvmClassMapping.getJavaObjectType(this).hashCode();
    }

    @NotNull
    public String toString() {
        return getJClass().toString() + " (Kotlin reflection is not available)";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: ClassReference.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\n2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\n2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005J\u001c\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00012\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005R&\u0010\u0003\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m1890d2 = {"Lkotlin/jvm/internal/ClassReference$Companion;", "", "()V", "FUNCTION_CLASSES", "", "Ljava/lang/Class;", "Lkotlin/Function;", "", "classFqNames", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "primitiveFqNames", "primitiveWrapperFqNames", "simpleNames", "getClassQualifiedName", "jClass", "getClassSimpleName", "isInstance", "", "value", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0069  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String getClassSimpleName(@org.jetbrains.annotations.NotNull java.lang.Class<?> r6) {
            /*
                r5 = this;
                java.lang.String r0 = "jClass"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
                boolean r0 = r6.isAnonymousClass()
                r1 = 0
                if (r0 == 0) goto Le
                goto Lca
            Le:
                boolean r0 = r6.isLocalClass()
                if (r0 == 0) goto L77
                java.lang.String r0 = r6.getSimpleName()
                java.lang.reflect.Method r2 = r6.getEnclosingMethod()
                r3 = 2
                if (r2 == 0) goto L41
                java.lang.String r4 = "name"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r2 = r2.getName()
                r4.append(r2)
                java.lang.String r2 = "$"
                r4.append(r2)
                java.lang.String r2 = r4.toString()
                java.lang.String r2 = kotlin.text.StringsKt.substringAfter$default(r0, r2, r1, r3, r1)
                if (r2 == 0) goto L41
                r6 = r2
                goto L67
            L41:
                java.lang.reflect.Constructor r6 = r6.getEnclosingConstructor()
                if (r6 == 0) goto L66
                java.lang.String r2 = "name"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r6 = r6.getName()
                r2.append(r6)
                java.lang.String r6 = "$"
                r2.append(r6)
                java.lang.String r6 = r2.toString()
                java.lang.String r6 = kotlin.text.StringsKt.substringAfter$default(r0, r6, r1, r3, r1)
                goto L67
            L66:
                r6 = r1
            L67:
                if (r6 == 0) goto L6b
                r1 = r6
                goto Lca
            L6b:
                java.lang.String r6 = "name"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r6)
                r6 = 36
                java.lang.String r1 = kotlin.text.StringsKt.substringAfter$default(r0, r6, r1, r3, r1)
                goto Lca
            L77:
                boolean r0 = r6.isArray()
                if (r0 == 0) goto Lb4
                java.lang.Class r6 = r6.getComponentType()
                java.lang.String r0 = "componentType"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
                boolean r0 = r6.isPrimitive()
                if (r0 == 0) goto Lae
                java.util.Map r0 = kotlin.jvm.internal.ClassReference.access$getSimpleNames$cp()
                java.lang.String r6 = r6.getName()
                java.lang.Object r6 = r0.get(r6)
                java.lang.String r6 = (java.lang.String) r6
                if (r6 == 0) goto Lae
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                r0.append(r6)
                java.lang.String r6 = "Array"
                r0.append(r6)
                java.lang.String r6 = r0.toString()
                r1 = r6
            Lae:
                if (r1 == 0) goto Lb1
                goto Lca
            Lb1:
                java.lang.String r1 = "Array"
                goto Lca
            Lb4:
                java.util.Map r0 = kotlin.jvm.internal.ClassReference.access$getSimpleNames$cp()
                java.lang.String r1 = r6.getName()
                java.lang.Object r0 = r0.get(r1)
                r1 = r0
                java.lang.String r1 = (java.lang.String) r1
                if (r1 == 0) goto Lc6
                goto Lca
            Lc6:
                java.lang.String r1 = r6.getSimpleName()
            Lca:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.internal.ClassReference.Companion.getClassSimpleName(java.lang.Class):java.lang.String");
        }

        @Nullable
        public final String getClassQualifiedName(@NotNull Class<?> jClass) {
            String str;
            Intrinsics.checkParameterIsNotNull(jClass, "jClass");
            String str2 = null;
            if (jClass.isAnonymousClass() || jClass.isLocalClass()) {
                return null;
            }
            if (!jClass.isArray()) {
                String str3 = (String) ClassReference.classFqNames.get(jClass.getName());
                return str3 != null ? str3 : jClass.getCanonicalName();
            }
            Class<?> componentType = jClass.getComponentType();
            Intrinsics.checkExpressionValueIsNotNull(componentType, "componentType");
            if (componentType.isPrimitive() && (str = (String) ClassReference.classFqNames.get(componentType.getName())) != null) {
                str2 = str + "Array";
            }
            return str2 != null ? str2 : "kotlin.Array";
        }

        public final boolean isInstance(@Nullable Object obj, @NotNull Class<?> jClass) {
            Intrinsics.checkParameterIsNotNull(jClass, "jClass");
            Map map = ClassReference.FUNCTION_CLASSES;
            if (map != null) {
                Integer num = (Integer) map.get(jClass);
                if (num != null) {
                    return TypeIntrinsics.isFunctionOfArity(obj, num.intValue());
                }
                if (jClass.isPrimitive()) {
                    jClass = JvmClassMapping.getJavaObjectType(JvmClassMapping.getKotlinClass(jClass));
                }
                return jClass.isInstance(obj);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
        }
    }

    static {
        int i = 0;
        List listOf = CollectionsKt.listOf((Object[]) new Class[]{Functions.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listOf, 10));
        for (Object obj : listOf) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(TuplesKt.m1883to((Class) obj, Integer.valueOf(i)));
            i = i2;
        }
        FUNCTION_CLASSES = MapsKt.toMap(arrayList);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("boolean", "kotlin.Boolean");
        hashMap.put("char", "kotlin.Char");
        hashMap.put("byte", "kotlin.Byte");
        hashMap.put("short", "kotlin.Short");
        hashMap.put("int", "kotlin.Int");
        hashMap.put("float", "kotlin.Float");
        hashMap.put("long", "kotlin.Long");
        hashMap.put("double", "kotlin.Double");
        primitiveFqNames = hashMap;
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("java.lang.Boolean", "kotlin.Boolean");
        hashMap2.put("java.lang.Character", "kotlin.Char");
        hashMap2.put("java.lang.Byte", "kotlin.Byte");
        hashMap2.put("java.lang.Short", "kotlin.Short");
        hashMap2.put("java.lang.Integer", "kotlin.Int");
        hashMap2.put("java.lang.Float", "kotlin.Float");
        hashMap2.put("java.lang.Long", "kotlin.Long");
        hashMap2.put("java.lang.Double", "kotlin.Double");
        primitiveWrapperFqNames = hashMap2;
        HashMap<String, String> hashMap3 = new HashMap<>();
        hashMap3.put("java.lang.Object", "kotlin.Any");
        hashMap3.put("java.lang.String", "kotlin.String");
        hashMap3.put("java.lang.CharSequence", "kotlin.CharSequence");
        hashMap3.put("java.lang.Throwable", "kotlin.Throwable");
        hashMap3.put("java.lang.Cloneable", "kotlin.Cloneable");
        hashMap3.put("java.lang.Number", "kotlin.Number");
        hashMap3.put("java.lang.Comparable", "kotlin.Comparable");
        hashMap3.put("java.lang.Enum", "kotlin.Enum");
        hashMap3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        hashMap3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        hashMap3.put("java.util.Iterator", "kotlin.collections.Iterator");
        hashMap3.put("java.util.Collection", "kotlin.collections.Collection");
        hashMap3.put("java.util.List", "kotlin.collections.List");
        hashMap3.put("java.util.Set", "kotlin.collections.Set");
        hashMap3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        hashMap3.put("java.util.Map", "kotlin.collections.Map");
        hashMap3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        hashMap3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        hashMap3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        hashMap3.putAll(primitiveFqNames);
        hashMap3.putAll(primitiveWrapperFqNames);
        Collection<String> values = primitiveFqNames.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "primitiveFqNames.values");
        for (String kotlinName : values) {
            StringBuilder sb = new StringBuilder();
            sb.append("kotlin.jvm.internal.");
            Intrinsics.checkExpressionValueIsNotNull(kotlinName, "kotlinName");
            sb.append(StringsKt.substringAfterLast$default(kotlinName, '.', (String) null, 2, (Object) null));
            sb.append("CompanionObject");
            Tuples m1883to = TuplesKt.m1883to(sb.toString(), kotlinName + ".Companion");
            hashMap3.put(m1883to.getFirst(), m1883to.getSecond());
        }
        for (Map.Entry<Class<? extends Function<?>>, Integer> entry : FUNCTION_CLASSES.entrySet()) {
            int intValue = entry.getValue().intValue();
            hashMap3.put(entry.getKey().getName(), "kotlin.Function" + intValue);
        }
        classFqNames = hashMap3;
        HashMap<String, String> hashMap4 = classFqNames;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(hashMap4.size()));
        Iterator<T> it = hashMap4.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it.next();
            linkedHashMap.put(entry2.getKey(), StringsKt.substringAfterLast$default((String) entry2.getValue(), '.', (String) null, 2, (Object) null));
        }
        simpleNames = linkedHashMap;
    }
}
