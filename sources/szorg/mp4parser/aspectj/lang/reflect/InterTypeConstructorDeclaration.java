package szorg.mp4parser.aspectj.lang.reflect;

import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface InterTypeConstructorDeclaration extends InterTypeDeclaration {
    AjType<?>[] getExceptionTypes();

    Type[] getGenericParameterTypes();

    AjType<?>[] getParameterTypes();
}
