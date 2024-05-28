package com.hjq.gson.factory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TypeAdapters;
import com.hjq.gson.factory.data.BigDecimalTypeAdapter;
import com.hjq.gson.factory.data.BooleanTypeAdapter;
import com.hjq.gson.factory.data.DoubleTypeAdapter;
import com.hjq.gson.factory.data.FloatTypeAdapter;
import com.hjq.gson.factory.data.IntegerTypeAdapter;
import com.hjq.gson.factory.data.LongTypeAdapter;
import com.hjq.gson.factory.data.StringTypeAdapter;
import com.hjq.gson.factory.element.CollectionTypeAdapterFactory;
import com.hjq.gson.factory.element.ReflectiveTypeAdapterFactory;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class GsonFactory {
    private static final HashMap<Type, InstanceCreator<?>> INSTANCE_CREATORS = new HashMap<>(0);
    private static final List<TypeAdapterFactory> TYPE_ADAPTER_FACTORIES = new ArrayList();
    private static volatile Gson sGson;
    private static JsonCallback sJsonCallback;

    private GsonFactory() {
    }

    public static Gson getSingletonGson() {
        if (sGson == null) {
            synchronized (GsonFactory.class) {
                if (sGson == null) {
                    sGson = newGsonBuilder().create();
                }
            }
        }
        return sGson;
    }

    public static void setSingletonGson(Gson gson) {
        sGson = gson;
    }

    public static void setJsonCallback(JsonCallback jsonCallback) {
        sJsonCallback = jsonCallback;
    }

    public static JsonCallback getJsonCallback() {
        return sJsonCallback;
    }

    public static void registerInstanceCreator(Type type, InstanceCreator<?> instanceCreator) {
        INSTANCE_CREATORS.put(type, instanceCreator);
    }

    public static void registerTypeAdapterFactory(TypeAdapterFactory typeAdapterFactory) {
        TYPE_ADAPTER_FACTORIES.add(typeAdapterFactory);
    }

    public static GsonBuilder newGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        for (TypeAdapterFactory typeAdapterFactory : TYPE_ADAPTER_FACTORIES) {
            gsonBuilder.registerTypeAdapterFactory(typeAdapterFactory);
        }
        ConstructorConstructor constructorConstructor = new ConstructorConstructor(INSTANCE_CREATORS);
        return gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(String.class, new StringTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(Boolean.TYPE, Boolean.class, new BooleanTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(Integer.TYPE, Integer.class, new IntegerTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(Long.TYPE, Long.class, new LongTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(Float.TYPE, Float.class, new FloatTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(Double.TYPE, Double.class, new DoubleTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(BigDecimal.class, new BigDecimalTypeAdapter())).registerTypeAdapterFactory(new CollectionTypeAdapterFactory(constructorConstructor)).registerTypeAdapterFactory(new ReflectiveTypeAdapterFactory(constructorConstructor, FieldNamingPolicy.IDENTITY, Excluder.DEFAULT));
    }
}
