package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class MessageLiteToString {
    private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
    private static final String BYTES_SUFFIX = "Bytes";
    private static final String LIST_SUFFIX = "List";

    MessageLiteToString() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toString(MessageLite messageLite, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        reflectivePrintWithIndent(messageLite, sb, 0);
        return sb.toString();
    }

    private static void reflectivePrintWithIndent(MessageLite messageLite, StringBuilder sb, int i) {
        java.lang.reflect.Method[] declaredMethods;
        Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object> next;
        boolean booleanValue;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (java.lang.reflect.Method method : messageLite.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String replaceFirst = str.replaceFirst("get", "");
            if (replaceFirst.endsWith("List") && !replaceFirst.endsWith("OrBuilderList")) {
                String str2 = replaceFirst.substring(0, 1).toLowerCase() + replaceFirst.substring(1, replaceFirst.length() - 4);
                java.lang.reflect.Method method2 = (java.lang.reflect.Method) hashMap.get("get" + replaceFirst);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    printField(sb, i, camelCaseToSnakeCase(str2), GeneratedMessageLite.invokeOrDie(method2, messageLite, new Object[0]));
                }
            }
            if (((java.lang.reflect.Method) hashMap2.get("set" + replaceFirst)) != null) {
                if (replaceFirst.endsWith("Bytes")) {
                    if (hashMap.containsKey("get" + replaceFirst.substring(0, replaceFirst.length() - 5))) {
                    }
                }
                String str3 = replaceFirst.substring(0, 1).toLowerCase() + replaceFirst.substring(1);
                java.lang.reflect.Method method3 = (java.lang.reflect.Method) hashMap.get("get" + replaceFirst);
                java.lang.reflect.Method method4 = (java.lang.reflect.Method) hashMap.get("has" + replaceFirst);
                if (method3 != null) {
                    Object invokeOrDie = GeneratedMessageLite.invokeOrDie(method3, messageLite, new Object[0]);
                    if (method4 == null) {
                        booleanValue = !isDefaultValue(invokeOrDie);
                    } else {
                        booleanValue = ((Boolean) GeneratedMessageLite.invokeOrDie(method4, messageLite, new Object[0])).booleanValue();
                    }
                    if (booleanValue) {
                        printField(sb, i, camelCaseToSnakeCase(str3), invokeOrDie);
                    }
                }
            }
        }
        if (messageLite instanceof GeneratedMessageLite.ExtendableMessage) {
            Iterator<Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object>> it = ((GeneratedMessageLite.ExtendableMessage) messageLite).extensions.iterator();
            while (it.hasNext()) {
                printField(sb, i, "[" + next.getKey().getNumber() + "]", it.next().getValue());
            }
        }
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) messageLite;
        if (generatedMessageLite.unknownFields != null) {
            generatedMessageLite.unknownFields.printWithIndent(sb, i);
        }
    }

    private static boolean isDefaultValue(Object obj) {
        if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue() == 0;
        } else if (obj instanceof Float) {
            return ((Float) obj).floatValue() == 0.0f;
        } else if (obj instanceof Double) {
            return ((Double) obj).doubleValue() == 0.0d;
        } else if (obj instanceof String) {
            return obj.equals("");
        } else {
            if (obj instanceof ByteString) {
                return obj.equals(ByteString.EMPTY);
            }
            return obj instanceof MessageLite ? obj == ((MessageLite) obj).getDefaultInstanceForType() : (obj instanceof java.lang.Enum) && ((java.lang.Enum) obj).ordinal() == 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void printField(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                printField(sb, i, str, obj2);
            }
            return;
        }
        sb.append('\n');
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(TextFormatEscaper.escapeText((String) obj));
            sb.append('\"');
        } else if (obj instanceof ByteString) {
            sb.append(": \"");
            sb.append(TextFormatEscaper.escapeBytes((ByteString) obj));
            sb.append('\"');
        } else if (obj instanceof GeneratedMessageLite) {
            sb.append(" {");
            reflectivePrintWithIndent((GeneratedMessageLite) obj, sb, i + 2);
            sb.append("\n");
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append("}");
        } else {
            sb.append(": ");
            sb.append(obj.toString());
        }
    }

    private static final String camelCaseToSnakeCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
