package com.p281qq.p282e.comm.util;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.util.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6888a {

    /* renamed from: a */
    private static final Map<String, Boolean> f17976a = new HashMap();

    /* renamed from: a */
    private static boolean m8229a(Class cls, String str, Class... clsArr) {
        String sb;
        if (cls == null) {
            sb = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(cls.getName());
            sb2.append("#");
            sb2.append(str);
            for (Class cls2 : clsArr) {
                sb2.append("_");
                sb2.append(cls2.getName());
            }
            sb = sb2.toString();
        }
        Boolean bool = f17976a.get(sb);
        if (bool == null) {
            try {
                cls.getDeclaredMethod(str, clsArr);
                f17976a.put(sb, Boolean.TRUE);
                return true;
            } catch (NoSuchMethodException unused) {
                f17976a.put(sb, Boolean.FALSE);
                return false;
            }
        }
        return Boolean.TRUE.equals(bool);
    }

    /* renamed from: a */
    public static boolean m8228a(Object obj) {
        if (obj == null) {
            return false;
        }
        return m8229a(obj.getClass(), "onRenderFail", new Class[0]);
    }

    /* renamed from: b */
    public static boolean m8227b(Object obj) {
        if (obj == null) {
            return false;
        }
        return m8229a(obj.getClass(), "onRenderSuccess", new Class[0]);
    }
}
