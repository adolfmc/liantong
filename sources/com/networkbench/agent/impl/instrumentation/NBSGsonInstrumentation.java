package com.networkbench.agent.impl.instrumentation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.networkbench.agent.impl.harvest.type.MetricCategory;
import com.networkbench.agent.impl.util.C6653u;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSGsonInstrumentation {
    private static final ArrayList<String> categoryParams = new ArrayList<>(Arrays.asList("category", MetricCategory.class.getName(), "JSON"));

    @Deprecated
    /* renamed from: a */
    void m9896a() {
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static String toJson(Gson gson, Object obj) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#toJson", categoryParams);
        String json = gson.toJson(obj);
        NBSTraceEngine.exitMethod();
        return json;
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static String toJson(Gson gson, Object obj, Type type) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#toJson", categoryParams);
        String json = gson.toJson(obj, type);
        NBSTraceEngine.exitMethod();
        return json;
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static void toJson(Gson gson, Object obj, Appendable appendable) throws JsonIOException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#toJson", categoryParams);
        gson.toJson(obj, appendable);
        NBSTraceEngine.exitMethod();
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static void toJson(Gson gson, Object obj, Type type, Appendable appendable) throws JsonIOException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#toJson", categoryParams);
        gson.toJson(obj, type, appendable);
        NBSTraceEngine.exitMethod();
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static void toJson(Gson gson, Object obj, Type type, JsonWriter jsonWriter) throws JsonIOException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#toJson", categoryParams);
        gson.toJson(obj, type, jsonWriter);
        NBSTraceEngine.exitMethod();
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static String toJson(Gson gson, JsonElement jsonElement) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#toJson", categoryParams);
        String json = gson.toJson(jsonElement);
        NBSTraceEngine.exitMethod();
        return json;
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static void toJson(Gson gson, JsonElement jsonElement, Appendable appendable) throws JsonIOException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#toJson", categoryParams);
        gson.toJson(jsonElement, appendable);
        NBSTraceEngine.exitMethod();
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static void toJson(Gson gson, JsonElement jsonElement, JsonWriter jsonWriter) throws JsonIOException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#toJson", categoryParams);
        gson.toJson(jsonElement, jsonWriter);
        NBSTraceEngine.exitMethod();
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, String str, Class<T> cls) throws JsonSyntaxException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#fromJson", categoryParams);
        T t = (T) gson.fromJson(str, (Class<Object>) cls);
        NBSTraceEngine.exitMethod();
        return t;
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, String str, Type type) throws JsonSyntaxException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#fromJson", categoryParams);
        T t = (T) gson.fromJson(str, type);
        NBSTraceEngine.exitMethod();
        return t;
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, Reader reader, Class<T> cls) throws JsonSyntaxException, JsonIOException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#fromJson", categoryParams);
        T t = (T) gson.fromJson(reader, (Class<Object>) cls);
        NBSTraceEngine.exitMethod();
        return t;
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, Reader reader, Type type) throws JsonIOException, JsonSyntaxException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#fromJson", categoryParams);
        T t = (T) gson.fromJson(reader, type);
        NBSTraceEngine.exitMethod();
        return t;
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, JsonReader jsonReader, Type type) throws JsonIOException, JsonSyntaxException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#fromJson", categoryParams);
        T t = (T) gson.fromJson(jsonReader, type);
        NBSTraceEngine.exitMethod();
        return t;
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, JsonElement jsonElement, Class<T> cls) throws JsonSyntaxException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#fromJson", categoryParams);
        T t = (T) gson.fromJson(jsonElement, (Class<Object>) cls);
        NBSTraceEngine.exitMethod();
        return t;
    }

    @NBSReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, JsonElement jsonElement, Type type) throws JsonSyntaxException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "Gson#fromJson", categoryParams);
        T t = (T) gson.fromJson(jsonElement, type);
        NBSTraceEngine.exitMethod();
        return t;
    }
}
