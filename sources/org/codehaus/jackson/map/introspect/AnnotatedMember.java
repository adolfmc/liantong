package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Member;
import org.codehaus.jackson.map.util.ClassUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class AnnotatedMember extends Annotated {
    protected final AnnotationMap _annotations;

    public abstract Class<?> getDeclaringClass();

    public abstract Member getMember();

    public abstract void setValue(Object obj, Object obj2) throws UnsupportedOperationException, IllegalArgumentException;

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotatedMember(AnnotationMap annotationMap) {
        this._annotations = annotationMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.codehaus.jackson.map.introspect.Annotated
    public AnnotationMap getAllAnnotations() {
        return this._annotations;
    }

    public final void fixAccess() {
        ClassUtil.checkAndFixAccess(getMember());
    }
}
