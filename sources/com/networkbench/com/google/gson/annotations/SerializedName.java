package com.networkbench.com.google.gson.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: E:\11480076_dexfile_execute.dex */
public @interface SerializedName {
    String value();
}
