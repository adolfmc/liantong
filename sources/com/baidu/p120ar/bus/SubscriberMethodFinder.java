package com.baidu.p120ar.bus;

import com.baidu.p120ar.utils.ARLog;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bus.SubscriberMethodFinder */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SubscriberMethodFinder {
    private static final int BRIDGE = 64;
    private static final int MODIFIERS_IGNORE = 5192;
    private static final int POOL_SIZE = 4;
    private static final int SYNTHETIC = 4096;
    private static final Map<Class<?>, List<SubscriberMethod>> METHOD_CACHE = new ConcurrentHashMap();
    private static final FindState[] FIND_STATE_POOL = new FindState[4];

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<SubscriberMethod> findSubscriberMethods(Class<?> cls) {
        List<SubscriberMethod> list = METHOD_CACHE.get(cls);
        if (list != null) {
            return list;
        }
        List<SubscriberMethod> findUsingInfo = findUsingInfo(cls);
        if (findUsingInfo.isEmpty()) {
            ARLog.m20420e("Subscriber " + cls + " and its super classes have no public methods with the @CallBack annotation");
            return null;
        }
        METHOD_CACHE.put(cls, findUsingInfo);
        return findUsingInfo;
    }

    private List<SubscriberMethod> findUsingInfo(Class<?> cls) {
        FindState prepareFindState = prepareFindState();
        prepareFindState.initForSubscriber(cls);
        while (prepareFindState.clazz != null) {
            findUsingReflectionInSingleClass(prepareFindState);
            prepareFindState.moveToSuperclass();
        }
        return getMethodsAndRelease(prepareFindState);
    }

    private List<SubscriberMethod> getMethodsAndRelease(FindState findState) {
        ArrayList arrayList = new ArrayList(findState.subscriberMethods);
        findState.recycle();
        synchronized (FIND_STATE_POOL) {
            int i = 0;
            while (true) {
                if (i >= 4) {
                    break;
                } else if (FIND_STATE_POOL[i] == null) {
                    FIND_STATE_POOL[i] = findState;
                    break;
                } else {
                    i++;
                }
            }
        }
        return arrayList;
    }

    private FindState prepareFindState() {
        synchronized (FIND_STATE_POOL) {
            for (int i = 0; i < 4; i++) {
                FindState findState = FIND_STATE_POOL[i];
                if (findState != null) {
                    FIND_STATE_POOL[i] = null;
                    return findState;
                }
            }
            return new FindState();
        }
    }

    private void findUsingReflectionInSingleClass(FindState findState) {
        Method[] methods;
        CallBack callBack;
        try {
            try {
                methods = findState.clazz.getDeclaredMethods();
            } catch (Throwable unused) {
                methods = findState.clazz.getMethods();
                findState.skipSuperClasses = true;
            }
            for (Method method : methods) {
                int modifiers = method.getModifiers();
                if ((modifiers & 1) != 0 && (modifiers & 5192) == 0) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1 && (callBack = (CallBack) method.getAnnotation(CallBack.class)) != null) {
                        Class<?> cls = parameterTypes[0];
                        if (findState.checkAdd(method, cls)) {
                            findState.subscriberMethods.add(new SubscriberMethod(method, cls, callBack.threadMode()));
                        }
                    }
                }
            }
        } catch (LinkageError unused2) {
            ARLog.m20420e(("Could not inspect methods of " + findState.clazz.getName()) + ". Please make this class visible to EventBus annotation processor to avoid reflection.");
        }
    }

    static void clearCaches() {
        METHOD_CACHE.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.bus.SubscriberMethodFinder$FindState */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class FindState {
        Class<?> clazz;
        boolean skipSuperClasses;
        Class<?> subscriberClass;
        final List<SubscriberMethod> subscriberMethods = new ArrayList();
        final Map<Class, Object> anyMethodByEventType = new HashMap();
        final Map<String, Class> subscriberClassByMethodKey = new HashMap();
        final StringBuilder methodKeyBuilder = new StringBuilder(128);

        FindState() {
        }

        void initForSubscriber(Class<?> cls) {
            this.clazz = cls;
            this.subscriberClass = cls;
            this.skipSuperClasses = false;
        }

        void recycle() {
            this.subscriberMethods.clear();
            this.anyMethodByEventType.clear();
            this.subscriberClassByMethodKey.clear();
            this.methodKeyBuilder.setLength(0);
            this.subscriberClass = null;
            this.clazz = null;
            this.skipSuperClasses = false;
        }

        boolean checkAdd(Method method, Class<?> cls) {
            Object put = this.anyMethodByEventType.put(cls, method);
            if (put == null) {
                return true;
            }
            if (put instanceof Method) {
                if (!checkAddWithMethodSignature((Method) put, cls)) {
                    throw new IllegalStateException();
                }
                this.anyMethodByEventType.put(cls, this);
            }
            return checkAddWithMethodSignature(method, cls);
        }

        private boolean checkAddWithMethodSignature(Method method, Class<?> cls) {
            this.methodKeyBuilder.setLength(0);
            this.methodKeyBuilder.append(method.getName());
            StringBuilder sb = this.methodKeyBuilder;
            sb.append('>');
            sb.append(cls.getName());
            String sb2 = this.methodKeyBuilder.toString();
            Class<?> declaringClass = method.getDeclaringClass();
            Class put = this.subscriberClassByMethodKey.put(sb2, declaringClass);
            if (put == null || put.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.subscriberClassByMethodKey.put(sb2, put);
            return false;
        }

        void moveToSuperclass() {
            if (this.skipSuperClasses) {
                this.clazz = null;
                return;
            }
            this.clazz = this.clazz.getSuperclass();
            String name = this.clazz.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.") || name.startsWith("androidx.")) {
                this.clazz = null;
            }
        }
    }
}
