package com.networkbench.agent.impl.instrumentation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Target({ElementType.METHOD})
/* loaded from: E:\11480076_dexfile_execute.dex */
public @interface NBSWrapReturn {
    String className();

    String methodDesc();

    String methodName();
}
