package szorg.mp4parser.aspectj.runtime.internal.cflowstack;

import java.util.Stack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ThreadStackFactoryImpl implements ThreadStackFactory {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl$1 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C141551 {
    }

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
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

        ThreadCounterImpl(C141551 c141551) {
            this();
        }

        @Override // szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadCounter
        public void dec() {
            Counter threadCounter = getThreadCounter();
            threadCounter.value--;
        }

        public Counter getThreadCounter() {
            return (Counter) get();
        }

        @Override // szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadCounter
        public void inc() {
            getThreadCounter().value++;
        }

        @Override // java.lang.ThreadLocal
        public Object initialValue() {
            return new Counter();
        }

        @Override // szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadCounter
        public boolean isNotZero() {
            return getThreadCounter().value != 0;
        }

        @Override // szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadCounter
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

        ThreadStackImpl(C141551 c141551) {
            this();
        }

        @Override // szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStack
        public Stack getThreadStack() {
            return (Stack) get();
        }

        @Override // java.lang.ThreadLocal
        public Object initialValue() {
            return new Stack();
        }

        @Override // szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStack
        public void removeThreadStack() {
            remove();
        }
    }

    @Override // szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactory
    public ThreadCounter getNewThreadCounter() {
        return new ThreadCounterImpl(null);
    }

    @Override // szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactory
    public ThreadStack getNewThreadStack() {
        return new ThreadStackImpl(null);
    }
}
