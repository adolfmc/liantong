package com.hjq.gson.factory.element;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {
    private final TypeAdapter<T> mDelegate;
    private final Gson mGson;
    private final Type mType;

    public TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter<T> typeAdapter, Type type) {
        this.mGson = gson;
        this.mDelegate = typeAdapter;
        this.mType = type;
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader jsonReader) throws IOException {
        return this.mDelegate.read(jsonReader);
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, T t) throws IOException {
        TypeAdapter<T> typeAdapter = this.mDelegate;
        Type runtimeTypeIfMoreSpecific = getRuntimeTypeIfMoreSpecific(this.mType, t);
        if (runtimeTypeIfMoreSpecific != this.mType) {
            typeAdapter = this.mGson.getAdapter(TypeToken.get(runtimeTypeIfMoreSpecific));
            if (typeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter) {
                TypeAdapter<T> typeAdapter2 = this.mDelegate;
                if (!(typeAdapter2 instanceof ReflectiveTypeAdapterFactory.Adapter)) {
                    typeAdapter = typeAdapter2;
                }
            }
        }
        typeAdapter.write(jsonWriter, t);
    }

    private Type getRuntimeTypeIfMoreSpecific(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }
}
