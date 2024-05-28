package com.uber.autodispose;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import org.jetbrains.annotations.Nullable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class TestLifecycleScopeProvider implements LifecycleScopeProvider<TestLifecycle> {
    private final BehaviorSubject<TestLifecycle> lifecycleSubject;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum TestLifecycle {
        STARTED,
        STOPPED
    }

    private TestLifecycleScopeProvider(@Nullable TestLifecycle testLifecycle) {
        if (testLifecycle == null) {
            this.lifecycleSubject = BehaviorSubject.create();
        } else {
            this.lifecycleSubject = BehaviorSubject.createDefault(testLifecycle);
        }
    }

    public static TestLifecycleScopeProvider create() {
        return new TestLifecycleScopeProvider(null);
    }

    public static TestLifecycleScopeProvider createInitial(TestLifecycle testLifecycle) {
        return new TestLifecycleScopeProvider(testLifecycle);
    }

    @Override // com.uber.autodispose.LifecycleScopeProvider
    public Observable<TestLifecycle> lifecycle() {
        return this.lifecycleSubject.hide();
    }

    @Override // com.uber.autodispose.LifecycleScopeProvider
    public Function<TestLifecycle, TestLifecycle> correspondingEvents() {
        return new Function<TestLifecycle, TestLifecycle>() { // from class: com.uber.autodispose.TestLifecycleScopeProvider.1
            @Override // io.reactivex.functions.Function
            public TestLifecycle apply(TestLifecycle testLifecycle) {
                switch (C105242.f20041x44f9699c[testLifecycle.ordinal()]) {
                    case 1:
                        return TestLifecycle.STOPPED;
                    case 2:
                        throw new LifecycleEndedException();
                    default:
                        throw new IllegalStateException("Unknown lifecycle event.");
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.uber.autodispose.TestLifecycleScopeProvider$2 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static /* synthetic */ class C105242 {

        /* renamed from: $SwitchMap$com$uber$autodispose$TestLifecycleScopeProvider$TestLifecycle */
        static final /* synthetic */ int[] f20041x44f9699c = new int[TestLifecycle.values().length];

        static {
            try {
                f20041x44f9699c[TestLifecycle.STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f20041x44f9699c[TestLifecycle.STOPPED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.uber.autodispose.LifecycleScopeProvider
    public TestLifecycle peekLifecycle() {
        return this.lifecycleSubject.getValue();
    }

    public void start() {
        this.lifecycleSubject.onNext(TestLifecycle.STARTED);
    }

    public void stop() {
        if (this.lifecycleSubject.getValue() != TestLifecycle.STARTED) {
            throw new IllegalStateException("Attempting to stop lifecycle before starting it.");
        }
        this.lifecycleSubject.onNext(TestLifecycle.STOPPED);
    }
}
