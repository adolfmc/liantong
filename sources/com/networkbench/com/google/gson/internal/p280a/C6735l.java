package com.networkbench.com.google.gson.internal.p280a;

import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.TypeAdapter;
import com.networkbench.com.google.gson.internal.p280a.C6727i;
import com.networkbench.com.google.gson.reflect.TypeToken;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.l */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6735l<T> extends TypeAdapter<T> {

    /* renamed from: a */
    private final Gson f17418a;

    /* renamed from: b */
    private final TypeAdapter<T> f17419b;

    /* renamed from: c */
    private final Type f17420c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C6735l(Gson gson, TypeAdapter<T> typeAdapter, Type type) {
        this.f17418a = gson;
        this.f17419b = typeAdapter;
        this.f17420c = type;
    }

    @Override // com.networkbench.com.google.gson.TypeAdapter
    public T read(JsonReader jsonReader) throws IOException {
        return this.f17419b.read(jsonReader);
    }

    @Override // com.networkbench.com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, T t) throws IOException {
        TypeAdapter<T> typeAdapter = this.f17419b;
        Type m8587a = m8587a(this.f17420c, t);
        if (m8587a != this.f17420c) {
            typeAdapter = this.f17418a.getAdapter(TypeToken.get(m8587a));
            if (typeAdapter instanceof C6727i.C6729a) {
                TypeAdapter<T> typeAdapter2 = this.f17419b;
                if (!(typeAdapter2 instanceof C6727i.C6729a)) {
                    typeAdapter = typeAdapter2;
                }
            }
        }
        typeAdapter.write(jsonWriter, t);
    }

    /* renamed from: a */
    private Type m8587a(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }
}
