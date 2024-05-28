package com.baidu.p120ar.bus;

import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bus.SubscriberMethod */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SubscriberMethod {
    final Class<?> eventType;
    final Method method;
    String methodString;
    final ThreadMode threadMode;

    public SubscriberMethod(Method method, Class<?> cls, ThreadMode threadMode) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = cls;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SubscriberMethod) {
            checkMethodString();
            SubscriberMethod subscriberMethod = (SubscriberMethod) obj;
            subscriberMethod.checkMethodString();
            return this.methodString.equals(subscriberMethod.methodString);
        }
        return false;
    }

    private synchronized void checkMethodString() {
        if (this.methodString == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.method.getDeclaringClass().getName());
            sb.append('#');
            sb.append(this.method.getName());
            sb.append('(');
            sb.append(this.eventType.getName());
            this.methodString = sb.toString();
        }
    }

    public int hashCode() {
        return this.method.hashCode();
    }
}
