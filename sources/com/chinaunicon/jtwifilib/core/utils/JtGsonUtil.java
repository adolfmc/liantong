package com.chinaunicon.jtwifilib.core.utils;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.lang.reflect.Type;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtGsonUtil {
    private static Gson gson;
    private static JtGsonUtil gsonUtil;

    public JtGsonUtil() {
        gson = new Gson();
    }

    public static JtGsonUtil getInstance() {
        synchronized (JtGsonUtil.class) {
            if (gsonUtil == null) {
                gsonUtil = new JtGsonUtil();
            }
        }
        return gsonUtil;
    }

    public <T> T fromJson(String str, Type type) {
        Gson gson2 = gson;
        return !(gson2 instanceof Gson) ? (T) gson2.fromJson(str, type) : (T) NBSGsonInstrumentation.fromJson(gson2, str, type);
    }

    public String toJson(Object obj) {
        Gson gson2 = gson;
        return !(gson2 instanceof Gson) ? gson2.toJson(obj) : NBSGsonInstrumentation.toJson(gson2, obj);
    }

    public String toJson(List<Object> list) {
        Gson gson2 = gson;
        return !(gson2 instanceof Gson) ? gson2.toJson(list) : NBSGsonInstrumentation.toJson(gson2, list);
    }

    public <T> T fromJson(String str, Class<T> cls) {
        Gson gson2 = gson;
        return !(gson2 instanceof Gson) ? (T) gson2.fromJson(str, (Class<Object>) cls) : (T) NBSGsonInstrumentation.fromJson(gson2, str, (Class<Object>) cls);
    }
}
