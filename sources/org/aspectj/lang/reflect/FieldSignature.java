package org.aspectj.lang.reflect;

import java.lang.reflect.Field;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface FieldSignature extends MemberSignature {
    Field getField();

    Class getFieldType();
}
