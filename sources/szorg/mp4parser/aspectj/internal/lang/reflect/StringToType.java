package szorg.mp4parser.aspectj.internal.lang.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.StringTokenizer;
import szorg.mp4parser.aspectj.lang.reflect.AjTypeSystem;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StringToType {
    public static Type[] commaSeparatedListToTypeArray(String str, Class cls) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        Type[] typeArr = new Type[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            typeArr[i] = stringToType(stringTokenizer.nextToken().trim(), cls);
            i++;
        }
        return typeArr;
    }

    private static Type makeParameterizedType(String str, Class cls) {
        int indexOf = str.indexOf(60);
        final Class<?> cls2 = Class.forName(str.substring(0, indexOf), false, cls.getClassLoader());
        final Type[] commaSeparatedListToTypeArray = commaSeparatedListToTypeArray(str.substring(indexOf + 1, str.lastIndexOf(62)), cls);
        return new ParameterizedType() { // from class: szorg.mp4parser.aspectj.internal.lang.reflect.StringToType.1
            @Override // java.lang.reflect.ParameterizedType
            public Type[] getActualTypeArguments() {
                return commaSeparatedListToTypeArray;
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type getOwnerType() {
                return cls2.getEnclosingClass();
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type getRawType() {
                return cls2;
            }
        };
    }

    public static Type stringToType(String str, Class cls) {
        try {
            return str.indexOf("<") == -1 ? AjTypeSystem.getAjType(Class.forName(str, false, cls.getClassLoader())) : makeParameterizedType(str, cls);
        } catch (ClassNotFoundException unused) {
            TypeVariable[] typeParameters = cls.getTypeParameters();
            for (int i = 0; i < typeParameters.length; i++) {
                if (typeParameters[i].getName().equals(str)) {
                    return typeParameters[i];
                }
            }
            throw new ClassNotFoundException(str);
        }
    }
}
