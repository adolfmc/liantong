package com.huawei.hms.utils;

import android.text.TextUtils;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.support.log.common.Base64;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JsonUtil {
    protected static final int VAL_BYTE = 2;
    protected static final int VAL_ENTITY = 0;
    protected static final int VAL_LIST = 1;
    protected static final int VAL_MAP = 3;
    protected static final int VAL_NULL = -1;
    protected static final String VAL_TYPE = "_val_type_";

    /* renamed from: a */
    private static String m14060a(IMessageEntity iMessageEntity) throws IllegalAccessException, JSONException {
        Field[] declaredFields;
        JSONObject jSONObject = new JSONObject();
        for (Class<?> cls = iMessageEntity.getClass(); cls != null; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.isAnnotationPresent(Packed.class)) {
                    boolean isAccessible = field.isAccessible();
                    m14053a(field, true);
                    String name = field.getName();
                    Object obj = field.get(iMessageEntity);
                    m14053a(field, isAccessible);
                    m14058a(name, obj, jSONObject);
                }
            }
        }
        return jSONObject.toString();
    }

    /* renamed from: b */
    private static Object m14050b(IMessageEntity iMessageEntity, Field field, JSONObject jSONObject) throws JSONException, IllegalAccessException {
        Object m14055a = m14055a(field.getName(), jSONObject);
        if (m14055a != null) {
            try {
                if (field.getType().getName().startsWith("com.huawei") && (field.getType().newInstance() instanceof IMessageEntity)) {
                    return jsonToEntity((String) m14055a, (IMessageEntity) field.getType().newInstance());
                }
                if ((m14055a instanceof JSONObject) && ((JSONObject) m14055a).has("_val_type_")) {
                    int i = ((JSONObject) m14055a).getInt("_val_type_");
                    if (i != 1 && i != 0) {
                        if (i == 2) {
                            return m14051a((JSONObject) m14055a);
                        }
                        if (i == 3) {
                            return m14049b(field.getGenericType(), (JSONObject) m14055a);
                        }
                        HMSLog.m14112e("JsonUtil", "cannot support type : " + i);
                    }
                    return m14052a(field.getGenericType(), (JSONObject) m14055a);
                }
                return m14055a;
            } catch (InstantiationException unused) {
                HMSLog.m14112e("JsonUtil", "InstantiationException  ");
            }
        }
        return null;
    }

    public static String createJsonString(IMessageEntity iMessageEntity) {
        if (iMessageEntity == null) {
            HMSLog.m14112e("JsonUtil", "createJsonString error, the input IMessageEntity is null");
            return "";
        }
        try {
            return m14060a(iMessageEntity);
        } catch (IllegalAccessException e) {
            HMSLog.m14112e("JsonUtil", "catch IllegalAccessException " + e.getMessage());
            return "";
        } catch (JSONException e2) {
            HMSLog.m14112e("JsonUtil", "catch JSONException " + e2.getMessage());
            return "";
        }
    }

    public static Object getInfoFromJsonobject(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.has(str2)) {
                    return null;
                }
                Object obj = jSONObject.get(str2);
                if (obj instanceof String) {
                    return obj;
                }
            } catch (JSONException unused) {
                HMSLog.m14112e("JsonUtil", "getInfoFromJsonobject:parser json error :" + str2);
            }
        }
        return null;
    }

    public static int getIntValue(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null || !jSONObject.has(str)) {
            return -1;
        }
        return jSONObject.getInt(str);
    }

    public static String getStringValue(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null || !jSONObject.has(str)) {
            return null;
        }
        return jSONObject.getString(str);
    }

    public static IMessageEntity jsonToEntity(String str, IMessageEntity iMessageEntity) {
        Field[] declaredFields;
        try {
            JSONObject jSONObject = new JSONObject(str);
            for (Class<?> cls = iMessageEntity.getClass(); cls != null; cls = cls.getSuperclass()) {
                for (Field field : cls.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Packed.class)) {
                        try {
                            m14059a(iMessageEntity, field, jSONObject);
                        } catch (IllegalAccessException unused) {
                            HMSLog.m14112e("JsonUtil", "jsonToEntity, set value of the field exception, field name:" + field.getName());
                        }
                    }
                }
            }
        } catch (JSONException e) {
            HMSLog.m14112e("JsonUtil", "catch JSONException when parse jsonString" + e.getMessage());
        }
        return iMessageEntity;
    }

    /* renamed from: a */
    private static void m14053a(final Field field, final boolean z) {
        AccessController.doPrivileged(new PrivilegedAction() { // from class: com.huawei.hms.utils.JsonUtil.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                field.setAccessible(z);
                return null;
            }
        });
    }

    /* renamed from: a */
    private static boolean m14058a(String str, Object obj, JSONObject jSONObject) throws JSONException, IllegalAccessException {
        if (obj instanceof String) {
            jSONObject.put(str, (String) obj);
            return true;
        } else if (obj instanceof Integer) {
            jSONObject.put(str, ((Integer) obj).intValue());
            return true;
        } else if (obj instanceof Short) {
            jSONObject.put(str, (Short) obj);
            return true;
        } else if (obj instanceof Long) {
            jSONObject.put(str, (Long) obj);
            return true;
        } else if (obj instanceof Float) {
            jSONObject.put(str, (Float) obj);
            return true;
        } else if (obj instanceof Double) {
            jSONObject.put(str, (Double) obj);
            return true;
        } else if (obj instanceof Boolean) {
            jSONObject.put(str, (Boolean) obj);
            return true;
        } else if (obj instanceof JSONObject) {
            jSONObject.put(str, (JSONObject) obj);
            return true;
        } else if (obj instanceof byte[]) {
            m14054a(str, (byte[]) obj, jSONObject);
            return true;
        } else if (obj instanceof List) {
            m14057a(str, (List<?>) obj, jSONObject);
            return true;
        } else if (obj instanceof Map) {
            m14056a(str, (Map) obj, jSONObject);
            return true;
        } else {
            if (obj instanceof IMessageEntity) {
                try {
                    jSONObject.put(str, m14060a((IMessageEntity) obj));
                    return true;
                } catch (IllegalAccessException e) {
                    HMSLog.m14112e("JsonUtil", "IllegalAccessException , " + e);
                }
            }
            return false;
        }
    }

    /* renamed from: b */
    private static Map m14049b(Type type, JSONObject jSONObject) throws JSONException, IllegalAccessException, InstantiationException {
        Class cls = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        JSONArray jSONArray = new JSONArray(jSONObject.getString("_map_"));
        HashMap hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i += 2) {
            if (cls.newInstance() instanceof IMessageEntity) {
                hashMap.put(jSONArray.get(i), jsonToEntity(jSONArray.getString(i + 1), (IMessageEntity) cls.newInstance()));
            } else {
                hashMap.put(jSONArray.get(i), jSONArray.get(i + 1));
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    private static void m14056a(String str, Map map, JSONObject jSONObject) throws JSONException, IllegalAccessException {
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key instanceof IMessageEntity) {
                jSONArray.put(m14060a((IMessageEntity) key));
            } else {
                jSONArray.put(key);
            }
            if (value instanceof IMessageEntity) {
                jSONArray.put(m14060a((IMessageEntity) value));
            } else {
                jSONArray.put(value);
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("_val_type_", 3);
        jSONObject2.put("_map_", jSONArray.toString());
        jSONObject.put(str, jSONObject2);
    }

    /* renamed from: a */
    private static void m14054a(String str, byte[] bArr, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("_val_type_", 2);
        try {
            jSONObject2.put("_byte_", Base64.encode(bArr));
        } catch (IllegalArgumentException e) {
            HMSLog.m14112e("JsonUtil", "writeByte failed : " + e.getMessage());
        }
        jSONObject.put(str, jSONObject2);
    }

    /* renamed from: a */
    private static void m14057a(String str, List<?> list, JSONObject jSONObject) throws JSONException, IllegalAccessException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("_val_type_", 1);
        jSONObject2.put("_list_size_", list.size());
        for (int i = 0; i < list.size(); i++) {
            m14058a("_list_item_" + i, list.get(i), jSONObject2);
            if (list.get(i) instanceof IMessageEntity) {
                jSONObject2.put("_val_type_", 0);
            }
        }
        jSONObject.put(str, jSONObject2);
    }

    /* renamed from: a */
    private static void m14059a(IMessageEntity iMessageEntity, Field field, JSONObject jSONObject) throws JSONException, IllegalAccessException {
        Object m14050b = m14050b(iMessageEntity, field, jSONObject);
        if (m14050b != null) {
            boolean isAccessible = field.isAccessible();
            m14053a(field, true);
            field.set(iMessageEntity, m14050b);
            m14053a(field, isAccessible);
        }
    }

    /* renamed from: a */
    private static Object m14055a(String str, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has(str)) {
            return jSONObject.get(str);
        }
        if (jSONObject.has("header") && jSONObject.getJSONObject("header").has(str)) {
            return jSONObject.getJSONObject("header").get(str);
        }
        if (jSONObject.has("body") && jSONObject.getJSONObject("body").has(str)) {
            return jSONObject.getJSONObject("body").get(str);
        }
        return null;
    }

    /* renamed from: a */
    private static List<Object> m14052a(Type type, JSONObject jSONObject) throws JSONException, IllegalAccessException, InstantiationException {
        int i = jSONObject.getInt("_list_size_");
        int i2 = jSONObject.getInt("_val_type_");
        ArrayList arrayList = new ArrayList(i);
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = jSONObject.get("_list_item_" + i3);
            if (i2 == 0) {
                arrayList.add(jsonToEntity((String) obj, (IMessageEntity) ((Class) ((ParameterizedType) type).getActualTypeArguments()[0]).newInstance()));
            } else if (i2 == 1) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static byte[] m14051a(JSONObject jSONObject) throws JSONException {
        try {
            return Base64.decode(jSONObject.getString("_byte_"));
        } catch (IllegalArgumentException e) {
            HMSLog.m14112e("JsonUtil", "readByte failed : " + e.getMessage());
            return null;
        }
    }
}
