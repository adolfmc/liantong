package io.objectbox.internal;

import io.objectbox.annotation.apihint.Internal;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

@Internal
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ReflectionCache {
    private static final ReflectionCache instance = new ReflectionCache();
    private final Map<Class, Map<String, Field>> fields = new HashMap();

    public static ReflectionCache getInstance() {
        return instance;
    }

    @Nonnull
    public synchronized Field getField(Class cls, String str) {
        Field field;
        Map<String, Field> map = this.fields.get(cls);
        if (map == null) {
            map = new HashMap<>();
            this.fields.put(cls, map);
        }
        field = map.get(str);
        if (field == null) {
            try {
                field = cls.getDeclaredField(str);
                field.setAccessible(true);
                map.put(str, field);
            } catch (NoSuchFieldException e) {
                throw new IllegalStateException(e);
            }
        }
        return field;
    }
}
