package com.huawei.agconnect.core.p211a;

import android.content.Context;
import android.util.Log;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.core.Service;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.huawei.agconnect.core.a.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4793d {

    /* renamed from: a */
    private static Map<Class<?>, Service> f10812a = new HashMap();

    /* renamed from: b */
    private static Map<Class<?>, Object> f10813b = new HashMap();

    /* renamed from: c */
    private Map<Class<?>, Service> f10814c = new HashMap();

    /* renamed from: d */
    private Map<Class<?>, Object> f10815d = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4793d(List<Service> list, Context context) {
        m15375a(list, context);
    }

    /* renamed from: a */
    private Object m15379a(AGConnectInstance aGConnectInstance, Service service) {
        String str;
        StringBuilder sb;
        String localizedMessage;
        if (service.getInstance() != null) {
            return service.getInstance();
        }
        Class<?> type = service.getType();
        if (type == null) {
            return null;
        }
        try {
            Constructor m15377a = m15377a(type, Context.class, AGConnectInstance.class);
            if (m15377a != null) {
                return m15377a.newInstance(aGConnectInstance.getContext(), aGConnectInstance);
            }
            Constructor m15377a2 = m15377a(type, Context.class);
            return m15377a2 != null ? m15377a2.newInstance(aGConnectInstance.getContext()) : type.newInstance();
        } catch (IllegalAccessException e) {
            str = "ServiceRepository";
            sb = new StringBuilder();
            sb.append("Instantiate service exception ");
            localizedMessage = e.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e(str, sb.toString());
            return null;
        } catch (InstantiationException e2) {
            str = "ServiceRepository";
            sb = new StringBuilder();
            sb.append("Instantiate service exception ");
            localizedMessage = e2.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e(str, sb.toString());
            return null;
        } catch (InvocationTargetException e3) {
            str = "ServiceRepository";
            sb = new StringBuilder();
            sb.append("Instantiate service exception ");
            localizedMessage = e3.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e(str, sb.toString());
            return null;
        }
    }

    /* renamed from: a */
    private static Constructor m15377a(Class cls, Class... clsArr) {
        Constructor<?>[] declaredConstructors;
        boolean z = false;
        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == clsArr.length) {
                boolean z2 = z;
                for (int i = 0; i < clsArr.length; i++) {
                    z2 = parameterTypes[i] == clsArr[i];
                }
                if (z2) {
                    return constructor;
                }
                z = z2;
            }
        }
        return null;
    }

    /* renamed from: a */
    private void m15376a(String str, Exception exc) {
        Log.e("ServiceRepository", "Instantiate shared service " + str + exc.getLocalizedMessage());
        StringBuilder sb = new StringBuilder();
        sb.append("cause message:");
        sb.append(exc.getCause() != null ? exc.getCause().getMessage() : "");
        Log.e("ServiceRepository", sb.toString());
    }

    /* renamed from: a */
    public <T> T m15378a(AGConnectInstance aGConnectInstance, Class<?> cls) {
        T t;
        Service service = this.f10814c.get(cls);
        if (service != null || (service = f10812a.get(cls)) == null) {
            if (service == null) {
                return null;
            }
            if (!service.isSingleton() || (t = (T) this.f10815d.get(cls)) == null) {
                T t2 = (T) m15379a(aGConnectInstance, service);
                if (t2 != null && service.isSingleton()) {
                    this.f10815d.put(cls, t2);
                }
                return t2;
            }
            return t;
        }
        return (T) f10813b.get(cls);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005b A[Catch: InvocationTargetException -> 0x0076, InstantiationException -> 0x007a, IllegalAccessException -> 0x007e, TryCatch #2 {IllegalAccessException -> 0x007e, InstantiationException -> 0x007a, InvocationTargetException -> 0x0076, blocks: (B:20:0x0049, B:22:0x005b, B:24:0x006c, B:23:0x0064), top: B:34:0x0049 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0064 A[Catch: InvocationTargetException -> 0x0076, InstantiationException -> 0x007a, IllegalAccessException -> 0x007e, TryCatch #2 {IllegalAccessException -> 0x007e, InstantiationException -> 0x007a, InvocationTargetException -> 0x0076, blocks: (B:20:0x0049, B:22:0x005b, B:24:0x006c, B:23:0x0064), top: B:34:0x0049 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m15375a(java.util.List<com.huawei.agconnect.core.Service> r7, android.content.Context r8) {
        /*
            r6 = this;
            if (r7 != 0) goto L3
            return
        L3:
            java.util.Iterator r7 = r7.iterator()
        L7:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L85
            java.lang.Object r0 = r7.next()
            com.huawei.agconnect.core.Service r0 = (com.huawei.agconnect.core.Service) r0
            boolean r1 = r0.isSharedInstance()
            if (r1 == 0) goto L28
            java.util.Map<java.lang.Class<?>, com.huawei.agconnect.core.Service> r1 = com.huawei.agconnect.core.p211a.C4793d.f10812a
            java.lang.Class r2 = r0.getInterface()
            boolean r1 = r1.containsKey(r2)
            if (r1 != 0) goto L31
            java.util.Map<java.lang.Class<?>, com.huawei.agconnect.core.Service> r1 = com.huawei.agconnect.core.p211a.C4793d.f10812a
            goto L2a
        L28:
            java.util.Map<java.lang.Class<?>, com.huawei.agconnect.core.Service> r1 = r6.f10814c
        L2a:
            java.lang.Class r2 = r0.getInterface()
            r1.put(r2, r0)
        L31:
            boolean r1 = r0.isAutoCreated()
            if (r1 == 0) goto L7
            java.lang.Class r1 = r0.getType()
            if (r1 == 0) goto L7
            java.util.Map<java.lang.Class<?>, java.lang.Object> r1 = com.huawei.agconnect.core.p211a.C4793d.f10813b
            java.lang.Class r2 = r0.getInterface()
            boolean r1 = r1.containsKey(r2)
            if (r1 != 0) goto L7
            java.lang.Class r1 = r0.getType()     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            java.lang.Class<android.content.Context> r4 = android.content.Context.class
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            java.lang.reflect.Constructor r1 = m15377a(r1, r3)     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            if (r1 == 0) goto L64
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            r2[r5] = r8     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            java.lang.Object r1 = r1.newInstance(r2)     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            goto L6c
        L64:
            java.lang.Class r1 = r0.getType()     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            java.lang.Object r1 = r1.newInstance()     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
        L6c:
            java.util.Map<java.lang.Class<?>, java.lang.Object> r2 = com.huawei.agconnect.core.p211a.C4793d.f10813b     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            java.lang.Class r0 = r0.getInterface()     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            r2.put(r0, r1)     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            goto L7
        L76:
            r0 = move-exception
            java.lang.String r1 = "TargetException"
            goto L81
        L7a:
            r0 = move-exception
            java.lang.String r1 = "InstantiationException"
            goto L81
        L7e:
            r0 = move-exception
            java.lang.String r1 = "AccessException"
        L81:
            r6.m15376a(r1, r0)
            goto L7
        L85:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.core.p211a.C4793d.m15375a(java.util.List, android.content.Context):void");
    }
}
