package org.aspectj.lang.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface InterTypeMethodDeclaration extends InterTypeDeclaration {
    AjType<?>[] getExceptionTypes();

    Type[] getGenericParameterTypes();

    Type getGenericReturnType();

    String getName();

    AjType<?>[] getParameterTypes();

    AjType<?> getReturnType();

    TypeVariable<Method>[] getTypeParameters();
}
