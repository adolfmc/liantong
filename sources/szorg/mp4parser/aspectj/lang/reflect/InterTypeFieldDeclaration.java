package szorg.mp4parser.aspectj.lang.reflect;

import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface InterTypeFieldDeclaration extends InterTypeDeclaration {
    Type getGenericType();

    String getName();

    AjType<?> getType();
}
