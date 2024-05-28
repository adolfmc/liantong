package com.hjq.gson.factory.element;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class ReflectiveFieldBound {
    private final boolean mDeserialized;
    private final String mFieldName;
    private final boolean mSerialized;

    public abstract void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

    public abstract void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

    public abstract boolean writeField(Object obj) throws IOException, IllegalAccessException;

    public ReflectiveFieldBound(String str, boolean z, boolean z2) {
        this.mFieldName = str;
        this.mSerialized = z;
        this.mDeserialized = z2;
    }

    public String getFieldName() {
        return this.mFieldName;
    }

    public boolean isDeserialized() {
        return this.mDeserialized;
    }

    public boolean isSerialized() {
        return this.mSerialized;
    }
}
