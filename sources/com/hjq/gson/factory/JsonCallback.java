package com.hjq.gson.factory;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonToken;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface JsonCallback {
    void onTypeException(TypeToken<?> typeToken, String str, JsonToken jsonToken);
}
