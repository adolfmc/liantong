package com.baidu.p120ar.bus;

import android.os.Looper;
import com.baidu.p120ar.utils.ARLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bus.ARBus */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARBus {
    private Looper controllerLoop;
    HandlerPoster controllerThreadPoster;
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType = new HashMap();
    private final Map<Object, List<Class<?>>> typesBySubscriber = new HashMap();
    SubscriberMethodFinder subscriberMethodFinder = new SubscriberMethodFinder();
    HandlerPoster mainThreadPoster = new HandlerPoster(this, Looper.getMainLooper(), 10);

    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public boolean isControllerThread() {
        Looper looper = this.controllerLoop;
        return looper != null && looper == Looper.myLooper();
    }

    public void reSetControllerLooper(Looper looper) {
        HandlerPoster handlerPoster = this.controllerThreadPoster;
        if (handlerPoster != null) {
            handlerPoster.release();
        }
        this.controllerLoop = looper;
        this.controllerThreadPoster = new HandlerPoster(this, looper, 10);
    }

    public void register(Object obj) {
        List<SubscriberMethod> findSubscriberMethods = this.subscriberMethodFinder.findSubscriberMethods(obj.getClass());
        synchronized (this) {
            for (SubscriberMethod subscriberMethod : findSubscriberMethods) {
                subscribe(obj, subscriberMethod);
            }
        }
    }

    private void subscribe(Object obj, SubscriberMethod subscriberMethod) {
        Class<?> cls = subscriberMethod.eventType;
        Subscription subscription = new Subscription(obj, subscriberMethod);
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.subscriptionsByEventType.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(subscription)) {
            ARLog.m20420e("Subscriber " + obj.getClass() + " already registered to event " + cls);
            return;
        }
        copyOnWriteArrayList.add(subscription);
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list == null) {
            list = new ArrayList<>();
            this.typesBySubscriber.put(obj, list);
        }
        list.add(cls);
    }

    public synchronized void unRegister(Object obj) {
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list != null) {
            for (Class<?> cls : list) {
                unsubscribeByEventType(obj, cls);
            }
            this.typesBySubscriber.remove(obj);
        } else {
            ARLog.m20420e("Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    private void unsubscribeByEventType(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        if (copyOnWriteArrayList != null) {
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i < size) {
                Subscription subscription = copyOnWriteArrayList.get(i);
                if (subscription.subscriber == obj) {
                    subscription.active = false;
                    copyOnWriteArrayList.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
        }
    }

    public void post(Object obj) {
        if (obj == null) {
            return;
        }
        Class<?> cls = obj.getClass();
        if (postSingleEventForEventType(obj, cls)) {
            return;
        }
        ARLog.m20420e("No subscribers registered for event " + cls);
    }

    private boolean postSingleEventForEventType(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator<Subscription> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            postToSubscription(it.next(), obj);
        }
        return true;
    }

    private void postToSubscription(Subscription subscription, Object obj) {
        switch (subscription.subscriberMethod.threadMode) {
            case POSTING:
                invokeSubscriber(subscription, obj);
                return;
            case MAIN:
                if (isMainThread()) {
                    invokeSubscriber(subscription, obj);
                    return;
                } else {
                    this.mainThreadPoster.enqueue(subscription, obj);
                    return;
                }
            case CONTROLLER:
                if (isControllerThread()) {
                    invokeSubscriber(subscription, obj);
                    return;
                } else {
                    this.controllerThreadPoster.enqueue(subscription, obj);
                    return;
                }
            default:
                ARLog.m20420e("Unknown thread mode: " + subscription.subscriberMethod.threadMode);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void invokeSubscriber(PendingPost pendingPost) {
        Object obj = pendingPost.event;
        Subscription subscription = pendingPost.subscription;
        PendingPost.releasePendingPost(pendingPost);
        if (subscription.active) {
            invokeSubscriber(subscription, obj);
        }
    }

    void invokeSubscriber(Subscription subscription, Object obj) {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
