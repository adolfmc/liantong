package com.hjq.gson.factory.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class LongTypeAdapter extends TypeAdapter<Long> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Long read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
            case NUMBER:
                try {
                    return Long.valueOf(jsonReader.nextLong());
                } catch (NumberFormatException unused) {
                    return Long.valueOf(new BigDecimal(jsonReader.nextString()).longValue());
                }
            case STRING:
                String nextString = jsonReader.nextString();
                if (nextString == null || "".equals(nextString)) {
                    return 0L;
                }
                try {
                    return Long.valueOf(Long.parseLong(nextString));
                } catch (NumberFormatException unused2) {
                    return Long.valueOf(new BigDecimal(nextString).longValue());
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
    public void write(JsonWriter jsonWriter, Long l) throws IOException {
        jsonWriter.value(l);
    }
}
