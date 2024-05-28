package org.codehaus.jackson;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface PrettyPrinter {
    void beforeArrayValues(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException;

    void beforeObjectEntries(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException;

    void writeArrayValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException;

    void writeEndArray(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException;

    void writeEndObject(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException;

    void writeObjectEntrySeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException;

    void writeObjectFieldValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException;

    void writeRootValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException;

    void writeStartArray(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException;

    void writeStartObject(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException;
}