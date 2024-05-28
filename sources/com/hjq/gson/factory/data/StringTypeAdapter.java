package com.hjq.gson.factory.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class StringTypeAdapter extends TypeAdapter<String> {
    @Override // com.google.gson.TypeAdapter
    public String read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
            case STRING:
            case NUMBER:
                return jsonReader.nextString();
            case BOOLEAN:
                return Boolean.toString(jsonReader.nextBoolean());
            case NULL:
                jsonReader.nextNull();
                return null;
            default:
                jsonReader.skipValue();
                throw new IllegalArgumentException();
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, String str) throws IOException {
        jsonWriter.value(str);
    }
}
