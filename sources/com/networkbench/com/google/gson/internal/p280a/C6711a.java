package com.networkbench.com.google.gson.internal.p280a;

import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.Gson$$$Internal;
import com.networkbench.com.google.gson.TypeAdapter;
import com.networkbench.com.google.gson.TypeAdapterFactory;
import com.networkbench.com.google.gson.annotations.Adapt;
import com.networkbench.com.google.gson.internal.ConstructorConstructor;
import com.networkbench.com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6711a implements TypeAdapterFactory {

    /* renamed from: a */
    private final ConstructorConstructor f17372a;

    public C6711a(ConstructorConstructor constructorConstructor) {
        this.f17372a = constructorConstructor;
    }

    @Override // com.networkbench.com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Adapt adapt = (Adapt) typeToken.getRawType().getAnnotation(Adapt.class);
        if (adapt == null) {
            return null;
        }
        return (TypeAdapter<T>) m8618a(gson, this.f17372a, adapt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static TypeAdapter<?> m8618a(Gson gson, ConstructorConstructor constructorConstructor, Adapt adapt) {
        TypeAdapter<?> typeAdapter = (TypeAdapter) constructorConstructor.get(TypeToken.get((Class) adapt.value())).construct();
        Gson$$$Internal.addGeneratedTypeAdapter(gson, typeAdapter);
        return typeAdapter;
    }
}
