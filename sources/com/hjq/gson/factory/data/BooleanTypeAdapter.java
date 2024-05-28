package com.hjq.gson.factory.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BooleanTypeAdapter extends TypeAdapter<Boolean> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Boolean read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
            case BOOLEAN:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case STRING:
                return Boolean.valueOf(Boolean.parseBoolean(jsonReader.nextString()));
            case NUMBER:
                return Boolean.valueOf(jsonReader.nextInt() != 0);
            case NULL:
                jsonReader.nextNull();
                return null;
            default:
                jsonReader.skipValue();
                throw new IllegalArgumentException();
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
        jsonWriter.value(bool);
    }
}
