package szorg.mp4parser.aspectj.lang.reflect;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface DeclareSoft {
    AjType getDeclaringType();

    PointcutExpression getPointcutExpression();

    AjType getSoftenedExceptionType();
}