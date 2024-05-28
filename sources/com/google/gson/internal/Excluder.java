package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class Excluder implements TypeAdapterFactory, Cloneable {
    public static final Excluder DEFAULT = new Excluder();
    private static final double IGNORE_VERSIONS = -1.0d;
    private boolean requireExpose;
    private double version = -1.0d;
    private int modifiers = 136;
    private boolean serializeInnerClasses = true;
    private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
    private List<ExclusionStrategy> deserializationStrategies = Collections.emptyList();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: clone */
    public Excluder m24469clone() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public Excluder withVersion(double d) {
        Excluder m24469clone = m24469clone();
        m24469clone.version = d;
        return m24469clone;
    }

    public Excluder withModifiers(int... iArr) {
        Excluder m24469clone = m24469clone();
        m24469clone.modifiers = 0;
        for (int i : iArr) {
            m24469clone.modifiers = i | m24469clone.modifiers;
        }
        return m24469clone;
    }

    public Excluder disableInnerClassSerialization() {
        Excluder m24469clone = m24469clone();
        m24469clone.serializeInnerClasses = false;
        return m24469clone;
    }

    public Excluder excludeFieldsWithoutExposeAnnotation() {
        Excluder m24469clone = m24469clone();
        m24469clone.requireExpose = true;
        return m24469clone;
    }

    public Excluder withExclusionStrategy(ExclusionStrategy exclusionStrategy, boolean z, boolean z2) {
        Excluder m24469clone = m24469clone();
        if (z) {
            m24469clone.serializationStrategies = new ArrayList(this.serializationStrategies);
            m24469clone.serializationStrategies.add(exclusionStrategy);
        }
        if (z2) {
            m24469clone.deserializationStrategies = new ArrayList(this.deserializationStrategies);
            m24469clone.deserializationStrategies.add(exclusionStrategy);
        }
        return m24469clone;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        boolean excludeClassChecks = excludeClassChecks(rawType);
        final boolean z = excludeClassChecks || excludeClassInStrategy(rawType, true);
        final boolean z2 = excludeClassChecks || excludeClassInStrategy(rawType, false);
        if (z || z2) {
            return new TypeAdapter<T>() { // from class: com.google.gson.internal.Excluder.1
                private TypeAdapter<T> delegate;

                @Override // com.google.gson.TypeAdapter
                public T read(JsonReader jsonReader) throws IOException {
                    if (z2) {
                        jsonReader.skipValue();
                        return null;
                    }
                    return delegate().read(jsonReader);
                }

                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, T t) throws IOException {
                    if (z) {
                        jsonWriter.nullValue();
                    } else {
                        delegate().write(jsonWriter, t);
                    }
                }

                private TypeAdapter<T> delegate() {
                    TypeAdapter<T> typeAdapter = this.delegate;
                    if (typeAdapter != null) {
                        return typeAdapter;
                    }
                    TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(Excluder.this, typeToken);
                    this.delegate = delegateAdapter;
                    return delegateAdapter;
                }
            };
        }
        return null;
    }

    public boolean excludeField(Field field, boolean z) {
        Expose expose;
        if ((this.modifiers & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.version == -1.0d || isValidVersion((Since) field.getAnnotation(Since.class), (Until) field.getAnnotation(Until.class))) && !field.isSynthetic()) {
            if (!this.requireExpose || ((expose = (Expose) field.getAnnotation(Expose.class)) != null && (!z ? !expose.deserialize() : !expose.serialize()))) {
                if ((this.serializeInnerClasses || !isInnerClass(field.getType())) && !isAnonymousOrLocal(field.getType())) {
                    List<ExclusionStrategy> list = z ? this.serializationStrategies : this.deserializationStrategies;
                    if (list.isEmpty()) {
                        return false;
                    }
                    FieldAttributes fieldAttributes = new FieldAttributes(field);
                    for (ExclusionStrategy exclusionStrategy : list) {
                        if (exclusionStrategy.shouldSkipField(fieldAttributes)) {
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    private boolean excludeClassChecks(Class<?> cls) {
        if (this.version == -1.0d || isValidVersion((Since) cls.getAnnotation(Since.class), (Until) cls.getAnnotation(Until.class))) {
            return (!this.serializeInnerClasses && isInnerClass(cls)) || isAnonymousOrLocal(cls);
        }
        return true;
    }

    public boolean excludeClass(Class<?> cls, boolean z) {
        return excludeClassChecks(cls) || excludeClassInStrategy(cls, z);
    }

    private boolean excludeClassInStrategy(Class<?> cls, boolean z) {
        for (ExclusionStrategy exclusionStrategy : z ? this.serializationStrategies : this.deserializationStrategies) {
            if (exclusionStrategy.shouldSkipClass(cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnonymousOrLocal(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean isInnerClass(Class<?> cls) {
        return cls.isMemberClass() && !isStatic(cls);
    }

    private boolean isStatic(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean isValidVersion(Since since, Until until) {
        return isValidSince(since) && isValidUntil(until);
    }

    private boolean isValidSince(Since since) {
        return since == null || since.value() <= this.version;
    }

    private boolean isValidUntil(Until until) {
        return until == null || until.value() > this.version;
    }
}
