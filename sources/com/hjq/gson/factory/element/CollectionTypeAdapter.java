package com.hjq.gson.factory.element;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.hjq.gson.factory.GsonFactory;
import com.hjq.gson.factory.JsonCallback;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CollectionTypeAdapter<E> extends TypeAdapter<Collection<E>> {
    private final TypeAdapter<E> mElementTypeAdapter;
    private String mFieldName;
    private final ObjectConstructor<? extends Collection<E>> mObjectConstructor;
    private TypeToken<?> mTypeToken;

    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) throws IOException {
        write(jsonWriter, (Collection) ((Collection) obj));
    }

    public CollectionTypeAdapter(Gson gson, Type type, TypeAdapter<E> typeAdapter, ObjectConstructor<? extends Collection<E>> objectConstructor) {
        this.mElementTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
        this.mObjectConstructor = objectConstructor;
    }

    public void setReflectiveType(TypeToken<?> typeToken, String str) {
        this.mTypeToken = typeToken;
        this.mFieldName = str;
    }

    @Override // com.google.gson.TypeAdapter
    public Collection<E> read(JsonReader jsonReader) throws IOException {
        JsonToken peek = jsonReader.peek();
        if (peek == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        } else if (peek != JsonToken.BEGIN_ARRAY) {
            jsonReader.skipValue();
            JsonCallback jsonCallback = GsonFactory.getJsonCallback();
            if (jsonCallback != null) {
                jsonCallback.onTypeException(this.mTypeToken, this.mFieldName, peek);
            }
            return null;
        } else {
            Collection<E> construct = this.mObjectConstructor.construct();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                construct.add(this.mElementTypeAdapter.read(jsonReader));
            }
            jsonReader.endArray();
            return construct;
        }
    }

    public void write(JsonWriter jsonWriter, Collection<E> collection) throws IOException {
        if (collection == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        for (E e : collection) {
            this.mElementTypeAdapter.write(jsonWriter, e);
        }
        jsonWriter.endArray();
    }
}
