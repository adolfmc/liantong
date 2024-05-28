package com.hjq.gson.factory.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FloatTypeAdapter extends TypeAdapter<Float> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Float read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
            case NUMBER:
                return Float.valueOf((float) jsonReader.nextDouble());
            case STRING:
                String nextString = jsonReader.nextString();
                if (nextString == null || "".equals(nextString)) {
                    return Float.valueOf(0.0f);
                }
                try {
                    return Float.valueOf(Float.parseFloat(nextString));
                } catch (Exception e) {
                    e.printStackTrace();
                    return Float.valueOf(0.0f);
                }
            case NULL:
                jsonReader.nextNull();
                return null;
            default:
                jsonReader.skipValue();
                throw new IllegalArgumentException();
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Float f) throws IOException {
        jsonWriter.value(f);
    }
}
