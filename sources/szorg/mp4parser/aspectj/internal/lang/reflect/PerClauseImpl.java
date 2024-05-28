package szorg.mp4parser.aspectj.internal.lang.reflect;

import szorg.mp4parser.aspectj.lang.reflect.PerClause;
import szorg.mp4parser.aspectj.lang.reflect.PerClauseKind;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PerClauseImpl implements PerClause {
    private final PerClauseKind kind;

    /* JADX INFO: Access modifiers changed from: protected */
    public PerClauseImpl(PerClauseKind perClauseKind) {
        this.kind = perClauseKind;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.PerClause
    public PerClauseKind getKind() {
        return this.kind;
    }

    public String toString() {
        return "issingleton()";
    }
}
