package com.sdk.p295k;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sdk.base.framework.bean.KInfo;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.p290f.C6998d;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.sdk.k.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7009a {

    /* renamed from: a */
    public static final String f18158a = "com.sdk.k.a";

    /* renamed from: b */
    public static final boolean f18159b = C6998d.f18135a;

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static String m8153a(Object obj) {
        JSONArray jSONArray;
        try {
            Field[] declaredFields = Class.forName(obj.getClass().getName()).getDeclaredFields();
            JSONObject jSONObject = new JSONObject();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                String name = field.getName();
                if (!"serialVersionUID".equals(name)) {
                    Object obj2 = field.get(obj);
                    if (field.getType().equals(ArrayList.class)) {
                        jSONArray = new JSONArray();
                        ArrayList arrayList = (ArrayList) obj2;
                        if (arrayList != null) {
                            for (int i = 0; i < arrayList.size(); i++) {
                                Object obj3 = arrayList.get(i);
                                Class<?> cls = obj3.getClass();
                                if (cls.equals(KInfo.class)) {
                                    Field[] declaredFields2 = cls.getDeclaredFields();
                                    JSONObject jSONObject2 = new JSONObject();
                                    for (Field field2 : declaredFields2) {
                                        field2.setAccessible(true);
                                        jSONObject2.put(field2.getName(), field2.get(obj3));
                                    }
                                    jSONArray.put(jSONObject2);
                                } else {
                                    jSONArray.put(obj3);
                                }
                            }
                        }
                    } else {
                        jSONArray = obj2;
                    }
                    jSONObject.put(name, jSONArray);
                }
            }
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception e) {
            LogUtils.m8186e(f18158a, e.getMessage(), Boolean.valueOf(f18159b));
            return null;
        }
    }
}
