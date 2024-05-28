package io.socket.emitter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Emitter {
    private ConcurrentMap<String, ConcurrentLinkedQueue<Listener>> callbacks = new ConcurrentHashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface Listener {
        void call(Object... objArr);
    }

    /* renamed from: on */
    public Emitter m1930on(String str, Listener listener) {
        ConcurrentLinkedQueue<Listener> putIfAbsent;
        ConcurrentLinkedQueue<Listener> concurrentLinkedQueue = this.callbacks.get(str);
        if (concurrentLinkedQueue == null && (putIfAbsent = this.callbacks.putIfAbsent(str, (concurrentLinkedQueue = new ConcurrentLinkedQueue<>()))) != null) {
            concurrentLinkedQueue = putIfAbsent;
        }
        concurrentLinkedQueue.add(listener);
        return this;
    }

    public Emitter once(String str, Listener listener) {
        m1930on(str, new OnceListener(str, listener));
        return this;
    }

    public Emitter off() {
        this.callbacks.clear();
        return this;
    }

    public Emitter off(String str) {
        this.callbacks.remove(str);
        return this;
    }

    public Emitter off(String str, Listener listener) {
        ConcurrentLinkedQueue<Listener> concurrentLinkedQueue = this.callbacks.get(str);
        if (concurrentLinkedQueue != null) {
            Iterator<Listener> it = concurrentLinkedQueue.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (sameAs(listener, it.next())) {
                    it.remove();
                    break;
                }
            }
        }
        return this;
    }

    private static boolean sameAs(Listener listener, Listener listener2) {
        if (listener.equals(listener2)) {
            return true;
        }
        if (listener2 instanceof OnceListener) {
            return listener.equals(((OnceListener) listener2).f24816fn);
        }
        return false;
    }

    public Emitter emit(String str, Object... objArr) {
        ConcurrentLinkedQueue<Listener> concurrentLinkedQueue = this.callbacks.get(str);
        if (concurrentLinkedQueue != null) {
            Iterator<Listener> it = concurrentLinkedQueue.iterator();
            while (it.hasNext()) {
                it.next().call(objArr);
            }
        }
        return this;
    }

    public List<Listener> listeners(String str) {
        ConcurrentLinkedQueue<Listener> concurrentLinkedQueue = this.callbacks.get(str);
        return concurrentLinkedQueue != null ? new ArrayList(concurrentLinkedQueue) : new ArrayList(0);
    }

    public boolean hasListeners(String str) {
        ConcurrentLinkedQueue<Listener> concurrentLinkedQueue = this.callbacks.get(str);
        return (concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class OnceListener implements Listener {
        public final String event;

        /* renamed from: fn */
        public final Listener f24816fn;

        public OnceListener(String str, Listener listener) {
            this.event = str;
            this.f24816fn = listener;
        }

        @Override // io.socket.emitter.Emitter.Listener
        public void call(Object... objArr) {
            Emitter.this.off(this.event, this);
            this.f24816fn.call(objArr);
        }
    }
}
