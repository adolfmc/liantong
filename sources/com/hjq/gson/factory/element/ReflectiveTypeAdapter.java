package com.hjq.gson.factory.element;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.hjq.gson.factory.GsonFactory;
import com.hjq.gson.factory.JsonCallback;
import java.io.IOException;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ReflectiveTypeAdapter<T> extends TypeAdapter<T> {
    private final Map<String, ReflectiveFieldBound> mBoundFields;
    private final ObjectConstructor<T> mConstructor;
    private String mFieldName;
    private TypeToken<?> mTypeToken;

    public ReflectiveTypeAdapter(ObjectConstructor<T> objectConstructor, Map<String, ReflectiveFieldBound> map) {
        this.mConstructor = objectConstructor;
        this.mBoundFields = map;
    }

    public void setReflectiveType(TypeToken<?> typeToken, String str) {
        this.mTypeToken = typeToken;
        this.mFieldName = str;
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader jsonReader) throws IOException {
        JsonToken peek = jsonReader.peek();
        if (peek == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        } else if (peek != JsonToken.BEGIN_OBJECT) {
            jsonReader.skipValue();
            JsonCallback jsonCallback = GsonFactory.getJsonCallback();
            if (jsonCallback != null) {
                jsonCallback.onTypeException(this.mTypeToken, this.mFieldName, peek);
            }
            return null;
        } else {
            T construct = this.mConstructor.construct();
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                ReflectiveFieldBound reflectiveFieldBound = this.mBoundFields.get(jsonReader.nextName());
                if (reflectiveFieldBound == null || !reflectiveFieldBound.isDeserialized()) {
                    jsonReader.skipValue();
                } else {
                    JsonToken peek2 = jsonReader.peek();
                    try {
                        reflectiveFieldBound.read(jsonReader, construct);
                    } catch (IllegalAccessException e) {
                        throw new AssertionError(e);
                    } catch (IllegalArgumentException unused) {
                        JsonCallback jsonCallback2 = GsonFactory.getJsonCallback();
                        if (jsonCallback2 != null) {
                            jsonCallback2.onTypeException(TypeToken.get((Class) construct.getClass()), reflectiveFieldBound.getFieldName(), peek2);
                        }
                    } catch (IllegalStateException e2) {
                        throw new JsonSyntaxException(e2);
                    }
                }
            }
            jsonReader.endObject();
            return construct;
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, T t) throws IOException {
        if (t == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        for (ReflectiveFieldBound reflectiveFieldBound : this.mBoundFields.values()) {
            try {
                if (reflectiveFieldBound.writeField(t)) {
                    jsonWriter.name(reflectiveFieldBound.getFieldName());
                    reflectiveFieldBound.write(jsonWriter, t);
                }
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
        jsonWriter.endObject();
    }
}
