package com.hjq.gson.factory.element;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ReflectiveTypeUtils {
    private static final ArrayList<Class<?>> TYPE_TOKENS = new ArrayList<>();

    static {
        TYPE_TOKENS.add(String.class);
        TYPE_TOKENS.add(Integer.class);
        TYPE_TOKENS.add(Boolean.class);
        TYPE_TOKENS.add(Byte.class);
        TYPE_TOKENS.add(Short.class);
        TYPE_TOKENS.add(Long.class);
        TYPE_TOKENS.add(Double.class);
        TYPE_TOKENS.add(Float.class);
        TYPE_TOKENS.add(Number.class);
        TYPE_TOKENS.add(AtomicInteger.class);
        TYPE_TOKENS.add(AtomicBoolean.class);
        TYPE_TOKENS.add(AtomicLong.class);
        TYPE_TOKENS.add(AtomicLongArray.class);
        TYPE_TOKENS.add(AtomicIntegerArray.class);
        TYPE_TOKENS.add(Character.class);
        TYPE_TOKENS.add(StringBuilder.class);
        TYPE_TOKENS.add(StringBuffer.class);
        TYPE_TOKENS.add(BigDecimal.class);
        TYPE_TOKENS.add(BigInteger.class);
        TYPE_TOKENS.add(URL.class);
        TYPE_TOKENS.add(URI.class);
        TYPE_TOKENS.add(UUID.class);
        TYPE_TOKENS.add(Currency.class);
        TYPE_TOKENS.add(Locale.class);
        TYPE_TOKENS.add(InetAddress.class);
        TYPE_TOKENS.add(BitSet.class);
        TYPE_TOKENS.add(Date.class);
        TYPE_TOKENS.add(GregorianCalendar.class);
        TYPE_TOKENS.add(Calendar.class);
        TYPE_TOKENS.add(Time.class);
        TYPE_TOKENS.add(java.sql.Date.class);
        TYPE_TOKENS.add(Timestamp.class);
        TYPE_TOKENS.add(Class.class);
    }

    public static boolean containsClass(Class<?> cls) {
        return TYPE_TOKENS.contains(cls);
    }

    public static ReflectiveFieldBound createBoundField(final Gson gson, final ConstructorConstructor constructorConstructor, final Field field, final String str, final TypeToken<?> typeToken, boolean z, boolean z2) {
        final boolean isPrimitive = Primitives.isPrimitive(typeToken.getRawType());
        return new ReflectiveFieldBound(str, z, z2) { // from class: com.hjq.gson.factory.element.ReflectiveTypeUtils.1
            final TypeAdapter<?> typeAdapter;

            {
                this.typeAdapter = ReflectiveTypeUtils.getFieldAdapter(gson, constructorConstructor, field, typeToken, str);
            }

            @Override // com.hjq.gson.factory.element.ReflectiveFieldBound
            public void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException {
                new TypeAdapterRuntimeTypeWrapper(gson, this.typeAdapter, typeToken.getType()).write(jsonWriter, field.get(obj));
            }

            @Override // com.hjq.gson.factory.element.ReflectiveFieldBound
            public void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException {
                Object read = this.typeAdapter.read(jsonReader);
                if (read == null && isPrimitive) {
                    return;
                }
                field.set(obj, read);
            }

            @Override // com.hjq.gson.factory.element.ReflectiveFieldBound
            public boolean writeField(Object obj) throws IOException, IllegalAccessException {
                return isSerialized() && field.get(obj) != obj;
            }
        };
    }

    public static TypeAdapter<?> getFieldAdapter(Gson gson, ConstructorConstructor constructorConstructor, Field field, TypeToken<?> typeToken, String str) {
        TypeAdapter<?> typeAdapter;
        JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null || (typeAdapter = getTypeAdapter(constructorConstructor, gson, typeToken, jsonAdapter)) == null) {
            TypeAdapter<?> adapter = gson.getAdapter(typeToken);
            if (adapter instanceof CollectionTypeAdapter) {
                ((CollectionTypeAdapter) adapter).setReflectiveType(TypeToken.get((Class) field.getDeclaringClass()), str);
            }
            if (adapter instanceof ReflectiveTypeAdapter) {
                ((ReflectiveTypeAdapter) adapter).setReflectiveType(TypeToken.get((Class) field.getDeclaringClass()), str);
            }
            return adapter;
        }
        return typeAdapter;
    }

    public static TypeAdapter<?> getTypeAdapter(ConstructorConstructor constructorConstructor, Gson gson, TypeToken<?> typeToken, JsonAdapter jsonAdapter) {
        TypeAdapter<?> create;
        Class<?> value = jsonAdapter.value();
        if (TypeAdapter.class.isAssignableFrom(value)) {
            create = (TypeAdapter) constructorConstructor.get(TypeToken.get((Class) value)).construct();
        } else if (TypeAdapterFactory.class.isAssignableFrom(value)) {
            create = ((TypeAdapterFactory) constructorConstructor.get(TypeToken.get((Class) value)).construct()).create(gson, typeToken);
        } else {
            throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
        }
        return create != null ? create.nullSafe() : create;
    }

    public static List<String> getFieldName(FieldNamingStrategy fieldNamingStrategy, Field field) {
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        LinkedList linkedList = new LinkedList();
        if (serializedName == null) {
            linkedList.add(fieldNamingStrategy.translateName(field));
        } else {
            linkedList.add(serializedName.value());
            String[] alternate = serializedName.alternate();
            for (String str : alternate) {
                linkedList.add(str);
            }
        }
        return linkedList;
    }
}
