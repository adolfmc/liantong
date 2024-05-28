package com.hjq.gson.factory.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DoubleTypeAdapter extends TypeAdapter<Double> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Double read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
            case NUMBER:
                return Double.valueOf(jsonReader.nextDouble());
            case STRING:
                String nextString = jsonReader.nextString();
                if (nextString == null || "".equals(nextString)) {
                    return Double.valueOf(0.0d);
                }
                try {
                    return Double.valueOf(Double.parseDouble(nextString));
                } catch (Exception e) {
                    e.printStackTrace();
                    return Double.valueOf(0.0d);
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
    public void write(JsonWriter jsonWriter, Double d) throws IOException {
        jsonWriter.value(d);
    }
}
