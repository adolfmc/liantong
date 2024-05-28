package org.codehaus.jackson.schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.codehaus.jackson.annotate.JacksonAnnotation;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Target({ElementType.TYPE})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: E:\9227576_dexfile_execute.dex */
public @interface JsonSerializableSchema {
    String schemaItemDefinition() default "##irrelevant";

    String schemaObjectPropertiesDefinition() default "##irrelevant";

    String schemaType() default "any";
}
