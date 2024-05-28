package org.codehaus.jackson.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface Indenter {
    boolean isInline();

    void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException;
}
