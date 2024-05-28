package p475rx.observers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p475rx.Notification;
import p475rx.Observer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.observers.TestObserver */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TestObserver<T> implements Observer<T> {
    private static Observer<Object> INERT = new Observer<Object>() { // from class: rx.observers.TestObserver.1
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
    private final Observer<T> delegate;
    private final ArrayList<Notification<T>> onCompletedEvents;
    private final ArrayList<Throwable> onErrorEvents;
    private final ArrayList<T> onNextEvents;

    public TestObserver(Observer<T> observer) {
        this.onNextEvents = new ArrayList<>();
        this.onErrorEvents = new ArrayList<>();
        this.onCompletedEvents = new ArrayList<>();
        this.delegate = observer;
    }

    public TestObserver() {
        this.onNextEvents = new ArrayList<>();
        this.onErrorEvents = new ArrayList<>();
        this.onCompletedEvents = new ArrayList<>();
        this.delegate = (Observer<T>) INERT;
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        this.onCompletedEvents.add(Notification.createOnCompleted());
        this.delegate.onCompleted();
    }

    public List<Notification<T>> getOnCompletedEvents() {
        return Collections.unmodifiableList(this.onCompletedEvents);
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        this.onErrorEvents.add(th);
        this.delegate.onError(th);
    }

    public List<Throwable> getOnErrorEvents() {
        return Collections.unmodifiableList(this.onErrorEvents);
    }

    @Override // p475rx.Observer
    public void onNext(T t) {
        this.onNextEvents.add(t);
        this.delegate.onNext(t);
    }

    public List<T> getOnNextEvents() {
        return Collections.unmodifiableList(this.onNextEvents);
    }

    public List<Object> getEvents() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.onNextEvents);
        arrayList.add(this.onErrorEvents);
        arrayList.add(this.onCompletedEvents);
        return Collections.unmodifiableList(arrayList);
    }

    public void assertReceivedOnNext(List<T> list) {
        if (this.onNextEvents.size() != list.size()) {
            throw new AssertionError("Number of items does not match. Provided: " + list.size() + "  Actual: " + this.onNextEvents.size() + ".\nProvided values: " + list + "\nActual values: " + this.onNextEvents);
        }
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            T t2 = this.onNextEvents.get(i);
            if (t == null) {
                if (t2 != null) {
                    throw new AssertionError("Value at index: " + i + " expected to be [null] but was: [" + t2 + "]");
                }
            } else if (!t.equals(t2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Value at index: ");
                sb.append(i);
                sb.append(" expected to be [");
                sb.append(t);
                sb.append("] (");
                sb.append(t.getClass().getSimpleName());
                sb.append(") but was: [");
                sb.append(t2);
                sb.append("] (");
                sb.append(t2 != null ? t2.getClass().getSimpleName() : "null");
                sb.append(")");
                throw new AssertionError(sb.toString());
            }
        }
    }

    public void assertTerminalEvent() {
        if (this.onErrorEvents.size() > 1) {
            throw new AssertionError("Too many onError events: " + this.onErrorEvents.size());
        } else if (this.onCompletedEvents.size() > 1) {
            throw new AssertionError("Too many onCompleted events: " + this.onCompletedEvents.size());
        } else if (this.onCompletedEvents.size() == 1 && this.onErrorEvents.size() == 1) {
            throw new AssertionError("Received both an onError and onCompleted. Should be one or the other.");
        } else {
            if (this.onCompletedEvents.size() == 0 && this.onErrorEvents.size() == 0) {
                throw new AssertionError("No terminal events received.");
            }
        }
    }
}
