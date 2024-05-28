package com.hjq.gson.factory.element;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor mConstructorConstructor;
    private final Excluder mExcluder;
    private final FieldNamingStrategy mFieldNamingPolicy;

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingStrategy, Excluder excluder) {
        this.mConstructorConstructor = constructorConstructor;
        this.mFieldNamingPolicy = fieldNamingStrategy;
        this.mExcluder = excluder;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (ReflectiveTypeUtils.containsClass(rawType) || (typeToken.getType() instanceof GenericArrayType) || (((typeToken.getType() instanceof Class) && ((Class) typeToken.getType()).isArray()) || !Object.class.isAssignableFrom(rawType) || Collection.class.isAssignableFrom(rawType) || Map.class.isAssignableFrom(rawType) || ((JsonAdapter) rawType.getAnnotation(JsonAdapter.class)) != null)) {
            return null;
        }
        if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
            return new ReflectiveTypeAdapter(this.mConstructorConstructor.get(typeToken), getBoundFields(gson, typeToken, rawType));
        }
        return null;
    }

    private Map<String, ReflectiveFieldBound> getBoundFields(Gson gson, TypeToken<?> typeToken, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type type = typeToken.getType();
        TypeToken<?> typeToken2 = typeToken;
        Class<?> cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            int length = declaredFields.length;
            for (int i = 0; i < length; i++) {
                Field field = declaredFields[i];
                boolean excludeField = excludeField(field, true);
                boolean excludeField2 = excludeField(field, false);
                if (excludeField || excludeField2) {
                    field.setAccessible(true);
                    Type resolve = C$Gson$Types.resolve(typeToken2.getType(), cls2, field.getGenericType());
                    List<String> fieldNames = getFieldNames(field);
                    int i2 = 0;
                    ReflectiveFieldBound reflectiveFieldBound = null;
                    while (i2 < fieldNames.size()) {
                        String str = fieldNames.get(i2);
                        boolean z = i2 != 0 ? false : excludeField;
                        int i3 = i2;
                        List<String> list = fieldNames;
                        Field field2 = field;
                        ReflectiveFieldBound reflectiveFieldBound2 = (ReflectiveFieldBound) linkedHashMap.put(str, ReflectiveTypeUtils.createBoundField(gson, this.mConstructorConstructor, field, str, TypeToken.get(resolve), z, excludeField2));
                        if (reflectiveFieldBound == null) {
                            reflectiveFieldBound = reflectiveFieldBound2;
                        }
                        i2 = i3 + 1;
                        excludeField = z;
                        fieldNames = list;
                        field = field2;
                    }
                    if (reflectiveFieldBound != null) {
                        throw new IllegalArgumentException(type + " declares multiple JSON fields named " + reflectiveFieldBound.getFieldName());
                    }
                }
            }
            typeToken2 = TypeToken.get(C$Gson$Types.resolve(typeToken2.getType(), cls2, cls2.getGenericSuperclass()));
            cls2 = typeToken2.getRawType();
        }
        return linkedHashMap;
    }

    private boolean excludeField(Field field, boolean z) {
        return excludeField(field, z, this.mExcluder);
    }

    private static boolean excludeField(Field field, boolean z, Excluder excluder) {
        return (excluder.excludeClass(field.getType(), z) || excluder.excludeField(field, z)) ? false : true;
    }

    private List<String> getFieldNames(Field field) {
        return ReflectiveTypeUtils.getFieldName(this.mFieldNamingPolicy, field);
    }
}
