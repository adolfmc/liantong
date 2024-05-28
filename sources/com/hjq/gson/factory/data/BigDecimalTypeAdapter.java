package com.hjq.gson.factory.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BigDecimalTypeAdapter extends TypeAdapter<BigDecimal> {
    @Override // com.google.gson.TypeAdapter
    public BigDecimal read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
            case NUMBER:
            case STRING:
                String nextString = jsonReader.nextString();
                if (nextString == null || "".equals(nextString)) {
                    return new BigDecimal(0);
                }
                return new BigDecimal(nextString);
            case NULL:
                jsonReader.nextNull();
                return null;
            default:
                jsonReader.skipValue();
                return null;
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, BigDecimal bigDecimal) throws IOException {
        jsonWriter.value(bigDecimal);
    }
}
