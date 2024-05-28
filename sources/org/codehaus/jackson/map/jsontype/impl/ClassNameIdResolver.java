package org.codehaus.jackson.map.jsontype.impl;

import java.util.EnumMap;
import java.util.EnumSet;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ClassNameIdResolver extends TypeIdResolverBase {
    public void registerSubtype(Class<?> cls, String str) {
    }

    public ClassNameIdResolver(JavaType javaType, TypeFactory typeFactory) {
        super(javaType, typeFactory);
    }

    @Override // org.codehaus.jackson.map.jsontype.TypeIdResolver
    public JsonTypeInfo.EnumC13396Id getMechanism() {
        return JsonTypeInfo.EnumC13396Id.CLASS;
    }

    @Override // org.codehaus.jackson.map.jsontype.TypeIdResolver
    public String idFromValue(Object obj) {
        return _idFrom(obj, obj.getClass());
    }

    @Override // org.codehaus.jackson.map.jsontype.TypeIdResolver
    public String idFromValueAndType(Object obj, Class<?> cls) {
        return _idFrom(obj, cls);
    }

    @Override // org.codehaus.jackson.map.jsontype.TypeIdResolver
    public JavaType typeFromId(String str) {
        if (str.indexOf(60) > 0) {
            return TypeFactory.fromCanonical(str);
        }
        try {
            return this._typeFactory.constructSpecializedType(this._baseType, ClassUtil.findClass(str));
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException("Invalid type id '" + str + "' (for id type 'Id.class'): no such class found");
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid type id '" + str + "' (for id type 'Id.class'): " + e.getMessage(), e);
        }
    }

    protected final String _idFrom(Object obj, Class<?> cls) {
        if (Enum.class.isAssignableFrom(cls) && !cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        String name = cls.getName();
        if (!name.startsWith("java.util")) {
            return (name.indexOf(36) < 0 || ClassUtil.getOuterClass(cls) == null || ClassUtil.getOuterClass(this._baseType.getRawClass()) != null) ? name : this._baseType.getRawClass().getName();
        } else if (obj instanceof EnumSet) {
            return TypeFactory.defaultInstance().constructCollectionType(EnumSet.class, ClassUtil.findEnumType((EnumSet) obj)).toCanonical();
        } else if (obj instanceof EnumMap) {
            return TypeFactory.defaultInstance().constructMapType(EnumMap.class, ClassUtil.findEnumType((EnumMap) obj), Object.class).toCanonical();
        } else {
            String substring = name.substring(9);
            return ((substring.startsWith(".Arrays$") || substring.startsWith(".Collections$")) && name.indexOf("List") >= 0) ? "java.util.ArrayList" : name;
        }
    }
}
