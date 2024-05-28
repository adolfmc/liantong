package com.networkbench.com.google.gson;

import java.util.HashSet;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class Gson$$$Internal {
    public static void addGeneratedTypeAdapter(Gson gson, TypeAdapter<?> typeAdapter) {
        boolean z;
        Set set;
        z = gson.inConstructorPhase;
        if (z) {
            set = gson.preconfiguredGeneratedTypeAdapters;
            set.add(typeAdapter);
            return;
        }
        getRuntimeGeneratedTypeAdapters(gson).add(typeAdapter);
    }

    public static boolean isGeneratedTypeAdapter(Gson gson, TypeAdapter<?> typeAdapter) {
        Set set;
        set = gson.preconfiguredGeneratedTypeAdapters;
        boolean contains = set.contains(typeAdapter);
        return !contains ? getRuntimeGeneratedTypeAdapters(gson).contains(typeAdapter) : contains;
    }

    private static Set<TypeAdapter<?>> getRuntimeGeneratedTypeAdapters(Gson gson) {
        ThreadLocal threadLocal;
        ThreadLocal threadLocal2;
        threadLocal = gson.runtimeGeneratedTypeAdapters;
        Set<TypeAdapter<?>> set = (Set) threadLocal.get();
        if (set == null) {
            set = new HashSet<>();
        }
        threadLocal2 = gson.runtimeGeneratedTypeAdapters;
        threadLocal2.set(set);
        return set;
    }
}
