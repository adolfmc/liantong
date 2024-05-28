package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.PerClause;
import org.aspectj.lang.reflect.PerClauseKind;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PerClauseImpl implements PerClause {
    private final PerClauseKind kind;

    /* JADX INFO: Access modifiers changed from: protected */
    public PerClauseImpl(PerClauseKind perClauseKind) {
        this.kind = perClauseKind;
    }

    @Override // org.aspectj.lang.reflect.PerClause
    public PerClauseKind getKind() {
        return this.kind;
    }

    public String toString() {
        return "issingleton()";
    }
}
