package com.hjq.gson.factory.element;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Collection;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CollectionTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor mConstructorConstructor;

    public CollectionTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
        this.mConstructorConstructor = constructorConstructor;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        Class<? super T> rawType = typeToken.getRawType();
        if (ReflectiveTypeUtils.containsClass(rawType) || (typeToken.getType() instanceof GenericArrayType) || (((typeToken.getType() instanceof Class) && ((Class) typeToken.getType()).isArray()) || !Collection.class.isAssignableFrom(rawType))) {
            return null;
        }
        Type collectionElementType = C$Gson$Types.getCollectionElementType(type, rawType);
        return new CollectionTypeAdapter(gson, collectionElementType, gson.getAdapter(TypeToken.get(collectionElementType)), this.mConstructorConstructor.get(typeToken));
    }
}
