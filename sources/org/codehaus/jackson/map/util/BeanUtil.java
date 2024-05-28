package org.codehaus.jackson.map.util;

import org.codehaus.jackson.map.introspect.AnnotatedMethod;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BeanUtil {
    public static String okNameForGetter(AnnotatedMethod annotatedMethod) {
        String name = annotatedMethod.getName();
        String okNameForIsGetter = okNameForIsGetter(annotatedMethod, name);
        return okNameForIsGetter == null ? okNameForRegularGetter(annotatedMethod, name) : okNameForIsGetter;
    }

    public static String okNameForRegularGetter(AnnotatedMethod annotatedMethod, String str) {
        if (str.startsWith("get")) {
            if ("getCallbacks".equals(str)) {
                if (isCglibGetCallbacks(annotatedMethod)) {
                    return null;
                }
            } else if ("getMetaClass".equals(str) && isGroovyMetaClassGetter(annotatedMethod)) {
                return null;
            }
            return manglePropertyName(str.substring(3));
        }
        return null;
    }

    public static String okNameForIsGetter(AnnotatedMethod annotatedMethod, String str) {
        if (str.startsWith("is")) {
            Class<?> rawType = annotatedMethod.getRawType();
            if (rawType == Boolean.class || rawType == Boolean.TYPE) {
                return manglePropertyName(str.substring(2));
            }
            return null;
        }
        return null;
    }

    public static String okNameForSetter(AnnotatedMethod annotatedMethod) {
        String manglePropertyName;
        String name = annotatedMethod.getName();
        if (!name.startsWith("set") || (manglePropertyName = manglePropertyName(name.substring(3))) == null) {
            return null;
        }
        if ("metaClass".equals(manglePropertyName) && isGroovyMetaClassSetter(annotatedMethod)) {
            return null;
        }
        return manglePropertyName;
    }

    protected static boolean isCglibGetCallbacks(AnnotatedMethod annotatedMethod) {
        Package r2;
        Class<?> rawType = annotatedMethod.getRawType();
        if (rawType != null && rawType.isArray() && (r2 = rawType.getComponentType().getPackage()) != null) {
            String name = r2.getName();
            if (name.startsWith("net.sf.cglib") || name.startsWith("org.hibernate.repackage.cglib")) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isGroovyMetaClassSetter(AnnotatedMethod annotatedMethod) {
        Package r2 = annotatedMethod.getParameterClass(0).getPackage();
        return r2 != null && r2.getName().startsWith("groovy.lang");
    }

    protected static boolean isGroovyMetaClassGetter(AnnotatedMethod annotatedMethod) {
        Package r2;
        Class<?> rawType = annotatedMethod.getRawType();
        return (rawType == null || rawType.isArray() || (r2 = rawType.getPackage()) == null || !r2.getName().startsWith("groovy.lang")) ? false : true;
    }

    protected static String manglePropertyName(String str) {
        int length = str.length();
        StringBuilder sb = null;
        if (length == 0) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            char lowerCase = Character.toLowerCase(charAt);
            if (charAt == lowerCase) {
                break;
            }
            if (sb == null) {
                sb = new StringBuilder(str);
            }
            sb.setCharAt(i, lowerCase);
        }
        return sb == null ? str : sb.toString();
    }
}
