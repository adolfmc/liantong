package com.hjq.gson.factory.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IntegerTypeAdapter extends TypeAdapter<Integer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Integer read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
            case NUMBER:
                try {
                    return Integer.valueOf(jsonReader.nextInt());
                } catch (NumberFormatException unused) {
                    return Integer.valueOf((int) jsonReader.nextDouble());
                }
            case STRING:
                String nextString = jsonReader.nextString();
                if (nextString == null || "".equals(nextString)) {
                    return 0;
                }
                try {
                    return Integer.valueOf(Integer.parseInt(nextString));
                } catch (NumberFormatException unused2) {
                    return Integer.valueOf((int) new BigDecimal(nextString).floatValue());
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
    public void write(JsonWriter jsonWriter, Integer num) throws IOException {
        jsonWriter.value(num);
    }
}
