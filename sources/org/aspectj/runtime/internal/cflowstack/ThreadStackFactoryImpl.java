package org.aspectj.runtime.internal.cflowstack;

import java.util.Stack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ThreadStackFactoryImpl implements ThreadStackFactory {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl$1 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C130631 {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class ThreadCounterImpl extends ThreadLocal implements ThreadCounter {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public static class Counter {
            protected int value = 0;

            Counter() {
            }
        }

        private ThreadCounterImpl() {
        }

        ThreadCounterImpl(C130631 c130631) {
            this();
        }

        @Override // org.aspectj.runtime.internal.cflowstack.ThreadCounter
        public void dec() {
            Counter threadCounter = getThreadCounter();
            threadCounter.value--;
        }

        public Counter getThreadCounter() {
            return (Counter) get();
        }

        @Override // org.aspectj.runtime.internal.cflowstack.ThreadCounter
        public void inc() {
            getThreadCounter().value++;
        }

        @Override // java.lang.ThreadLocal
        public Object initialValue() {
            return new Counter();
        }

        @Override // org.aspectj.runtime.internal.cflowstack.ThreadCounter
        public boolean isNotZero() {
            return getThreadCounter().value != 0;
        }

        @Override // org.aspectj.runtime.internal.cflowstack.ThreadCounter
        public void removeThreadCounter() {
            remove();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class ThreadStackImpl extends ThreadLocal implements ThreadStack {
        private ThreadStackImpl() {
        }

        ThreadStackImpl(C130631 c130631) {
            this();
        }

        @Override // org.aspectj.runtime.internal.cflowstack.ThreadStack
        public Stack getThreadStack() {
            return (Stack) get();
        }

        @Override // java.lang.ThreadLocal
        public Object initialValue() {
            return new Stack();
        }

        @Override // org.aspectj.runtime.internal.cflowstack.ThreadStack
        public void removeThreadStack() {
            remove();
        }
    }

    @Override // org.aspectj.runtime.internal.cflowstack.ThreadStackFactory
    public ThreadCounter getNewThreadCounter() {
        return new ThreadCounterImpl(null);
    }

    @Override // org.aspectj.runtime.internal.cflowstack.ThreadStackFactory
    public ThreadStack getNewThreadStack() {
        return new ThreadStackImpl(null);
    }
}
