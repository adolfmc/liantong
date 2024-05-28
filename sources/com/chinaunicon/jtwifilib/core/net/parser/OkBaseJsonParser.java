package com.chinaunicon.jtwifilib.core.net.parser;

import com.google.gson.internal.C$Gson$Types;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class OkBaseJsonParser<T> extends OkBaseParser<T> {
    public Type mType = getSuperclassTypeParameter(getClass());

    @Override // com.chinaunicon.jtwifilib.core.net.parser.OkBaseParser
    protected abstract T parse(Response response) throws IOException;

    private static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return C$Gson$Types.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }
}
