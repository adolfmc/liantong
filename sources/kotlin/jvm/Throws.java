package kotlin.jvm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: JvmPlatformAnnotations.kt */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B$\u0012\"\u0010\u0002\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00040\u0003\"\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004R\u001f\u0010\u0002\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00040\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u0007"}, m1890d2 = {"Lkotlin/jvm/Throws;", "", "exceptionClasses", "", "Lkotlin/reflect/KClass;", "", "()[Ljava/lang/Class;", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.CONSTRUCTOR})
@Retention(RetentionPolicy.SOURCE)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
/* loaded from: E:\11617560_dexfile_execute.dex */
public @interface Throws {
    Class<? extends Throwable>[] exceptionClasses();
}
