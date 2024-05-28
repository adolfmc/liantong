package org.codehaus.jackson.util;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.PrettyPrinter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class MinimalPrettyPrinter implements PrettyPrinter {
    public static final String DEFAULT_ROOT_VALUE_SEPARATOR = " ";
    protected String _rootValueSeparator;

    @Override // org.codehaus.jackson.PrettyPrinter
    public void beforeArrayValues(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
    }

    @Override // org.codehaus.jackson.PrettyPrinter
    public void beforeObjectEntries(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
    }

    public MinimalPrettyPrinter() {
        this(" ");
    }

    public MinimalPrettyPrinter(String str) {
        this._rootValueSeparator = " ";
        this._rootValueSeparator = str;
    }

    public void setRootValueSeparator(String str) {
        this._rootValueSeparator = str;
    }

    @Override // org.codehaus.jackson.PrettyPrinter
    public void writeRootValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        String str = this._rootValueSeparator;
        if (str != null) {
            jsonGenerator.writeRaw(str);
        }
    }

    @Override // org.codehaus.jackson.PrettyPrinter
    public void writeStartObject(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw('{');
    }

    @Override // org.codehaus.jackson.PrettyPrinter
    public void writeObjectFieldValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw(':');
    }

    @Override // org.codehaus.jackson.PrettyPrinter
    public void writeObjectEntrySeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw(',');
    }

    @Override // org.codehaus.jackson.PrettyPrinter
    public void writeEndObject(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw('}');
    }

    @Override // org.codehaus.jackson.PrettyPrinter
    public void writeStartArray(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw('[');
    }

    @Override // org.codehaus.jackson.PrettyPrinter
    public void writeArrayValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw(',');
    }

    @Override // org.codehaus.jackson.PrettyPrinter
    public void writeEndArray(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw(']');
    }
}
