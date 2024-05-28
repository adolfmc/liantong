package p475rx.observers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import p475rx.Notification;
import p475rx.Observer;
import p475rx.Subscriber;
import p475rx.exceptions.CompositeException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.observers.TestSubscriber */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TestSubscriber<T> extends Subscriber<T> {
    private static final Observer<Object> INERT = new Observer<Object>() { // from class: rx.observers.TestSubscriber.1
        @Override // p475rx.Observer
        public void onCompleted() {
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
        }

        @Override // p475rx.Observer
        public void onNext(Object obj) {
        }
    };
    private volatile Thread lastSeenThread;
    private final CountDownLatch latch;
    private final TestObserver<T> testObserver;

    public TestSubscriber(long j) {
        this(INERT, j);
    }

    public TestSubscriber(Observer<T> observer, long j) {
        this.latch = new CountDownLatch(1);
        if (observer == null) {
            throw new NullPointerException();
        }
        this.testObserver = new TestObserver<>(observer);
        if (j >= 0) {
            request(j);
        }
    }

    public TestSubscriber(Subscriber<T> subscriber) {
        this(subscriber, -1L);
    }

    public TestSubscriber(Observer<T> observer) {
        this(observer, -1L);
    }

    public TestSubscriber() {
        this(-1L);
    }

    public static <T> TestSubscriber<T> create() {
        return new TestSubscriber<>();
    }

    public static <T> TestSubscriber<T> create(long j) {
        return new TestSubscriber<>(j);
    }

    public static <T> TestSubscriber<T> create(Observer<T> observer, long j) {
        return new TestSubscriber<>(observer, j);
    }

    public static <T> TestSubscriber<T> create(Subscriber<T> subscriber) {
        return new TestSubscriber<>((Subscriber) subscriber);
    }

    public static <T> TestSubscriber<T> create(Observer<T> observer) {
        return new TestSubscriber<>(observer);
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        try {
            this.lastSeenThread = Thread.currentThread();
            this.testObserver.onCompleted();
        } finally {
            this.latch.countDown();
        }
    }

    public List<Notification<T>> getOnCompletedEvents() {
        return this.testObserver.getOnCompletedEvents();
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        try {
            this.lastSeenThread = Thread.currentThread();
            this.testObserver.onError(th);
        } finally {
            this.latch.countDown();
        }
    }

    public List<Throwable> getOnErrorEvents() {
        return this.testObserver.getOnErrorEvents();
    }

    @Override // p475rx.Observer
    public void onNext(T t) {
        this.lastSeenThread = Thread.currentThread();
        this.testObserver.onNext(t);
    }

    public void requestMore(long j) {
        request(j);
    }

    public List<T> getOnNextEvents() {
        return this.testObserver.getOnNextEvents();
    }

    public void assertReceivedOnNext(List<T> list) {
        this.testObserver.assertReceivedOnNext(list);
    }

    public void assertTerminalEvent() {
        this.testObserver.assertTerminalEvent();
    }

    public void assertUnsubscribed() {
        if (!isUnsubscribed()) {
            throw new AssertionError("Not unsubscribed.");
        }
    }

    public void assertNoErrors() {
        List<Throwable> onErrorEvents = getOnErrorEvents();
        if (onErrorEvents.size() > 0) {
            AssertionError assertionError = new AssertionError("Unexpected onError events: " + getOnErrorEvents().size());
            if (onErrorEvents.size() == 1) {
                assertionError.initCause(getOnErrorEvents().get(0));
            } else {
                assertionError.initCause(new CompositeException(onErrorEvents));
            }
            throw assertionError;
        }
    }

    public void awaitTerminalEvent() {
        try {
            this.latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted", e);
        }
    }

    public void awaitTerminalEvent(long j, TimeUnit timeUnit) {
        try {
            this.latch.await(j, timeUnit);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted", e);
        }
    }

    public void awaitTerminalEventAndUnsubscribeOnTimeout(long j, TimeUnit timeUnit) {
        try {
            if (this.latch.await(j, timeUnit)) {
                return;
            }
            unsubscribe();
        } catch (InterruptedException unused) {
            unsubscribe();
        }
    }

    public Thread getLastSeenThread() {
        return this.lastSeenThread;
    }

    public void assertCompleted() {
        int size = this.testObserver.getOnCompletedEvents().size();
        if (size == 0) {
            throw new AssertionError("Not completed!");
        }
        if (size <= 1) {
            return;
        }
        throw new AssertionError("Completed multiple times: " + size);
    }

    public void assertNotCompleted() {
        int size = this.testObserver.getOnCompletedEvents().size();
        if (size == 1) {
            throw new AssertionError("Completed!");
        }
        if (size <= 1) {
            return;
        }
        throw new AssertionError("Completed multiple times: " + size);
    }

    public void assertError(Class<? extends Throwable> cls) {
        List<Throwable> onErrorEvents = this.testObserver.getOnErrorEvents();
        if (onErrorEvents.size() == 0) {
            throw new AssertionError("No errors");
        }
        if (onErrorEvents.size() > 1) {
            AssertionError assertionError = new AssertionError("Multiple errors: " + onErrorEvents.size());
            assertionError.initCause(new CompositeException(onErrorEvents));
            throw assertionError;
        } else if (cls.isInstance(onErrorEvents.get(0))) {
        } else {
            AssertionError assertionError2 = new AssertionError("Exceptions differ; expected: " + cls + ", actual: " + onErrorEvents.get(0));
            assertionError2.initCause(onErrorEvents.get(0));
            throw assertionError2;
        }
    }

    public void assertError(Throwable th) {
        List<Throwable> onErrorEvents = this.testObserver.getOnErrorEvents();
        if (onErrorEvents.size() == 0) {
            throw new AssertionError("No errors");
        }
        if (onErrorEvents.size() > 1) {
            AssertionError assertionError = new AssertionError("Multiple errors: " + onErrorEvents.size());
            assertionError.initCause(new CompositeException(onErrorEvents));
            throw assertionError;
        } else if (th.equals(onErrorEvents.get(0))) {
        } else {
            AssertionError assertionError2 = new AssertionError("Exceptions differ; expected: " + th + ", actual: " + onErrorEvents.get(0));
            assertionError2.initCause(onErrorEvents.get(0));
            throw assertionError2;
        }
    }

    public void assertNoTerminalEvent() {
        List<Throwable> onErrorEvents = this.testObserver.getOnErrorEvents();
        int size = this.testObserver.getOnCompletedEvents().size();
        if (onErrorEvents.size() > 0 || size > 0) {
            if (onErrorEvents.isEmpty()) {
                throw new AssertionError("Found " + onErrorEvents.size() + " errors and " + size + " completion events instead of none");
            } else if (onErrorEvents.size() == 1) {
                AssertionError assertionError = new AssertionError("Found " + onErrorEvents.size() + " errors and " + size + " completion events instead of none");
                assertionError.initCause(onErrorEvents.get(0));
                throw assertionError;
            } else {
                AssertionError assertionError2 = new AssertionError("Found " + onErrorEvents.size() + " errors and " + size + " completion events instead of none");
                assertionError2.initCause(new CompositeException(onErrorEvents));
                throw assertionError2;
            }
        }
    }

    public void assertNoValues() {
        int size = this.testObserver.getOnNextEvents().size();
        if (size <= 0) {
            return;
        }
        throw new AssertionError("No onNext events expected yet some received: " + size);
    }

    public void assertValueCount(int i) {
        int size = this.testObserver.getOnNextEvents().size();
        if (size == i) {
            return;
        }
        throw new AssertionError("Number of onNext events differ; expected: " + i + ", actual: " + size);
    }

    public void assertValues(T... tArr) {
        assertReceivedOnNext(Arrays.asList(tArr));
    }

    public void assertValue(T t) {
        assertReceivedOnNext(Collections.singletonList(t));
    }
}
