package com.uber.autodispose.android.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import com.uber.autodispose.LifecycleEndedException;
import com.uber.autodispose.LifecycleScopeProvider;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class AndroidLifecycleScopeProvider implements LifecycleScopeProvider<Lifecycle.Event> {
    private static final Function<Lifecycle.Event, Lifecycle.Event> DEFAULT_CORRESPONDING_EVENTS = new Function<Lifecycle.Event, Lifecycle.Event>() { // from class: com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.1
        @Override // io.reactivex.functions.Function
        public Lifecycle.Event apply(Lifecycle.Event event) throws Exception {
            switch (C105282.$SwitchMap$android$arch$lifecycle$Lifecycle$Event[event.ordinal()]) {
                case 1:
                    return Lifecycle.Event.ON_DESTROY;
                case 2:
                    return Lifecycle.Event.ON_STOP;
                case 3:
                    return Lifecycle.Event.ON_PAUSE;
                case 4:
                    return Lifecycle.Event.ON_STOP;
                default:
                    throw new LifecycleEndedException("Lifecycle has ended! Last event was " + event);
            }
        }
    };
    private final Function<Lifecycle.Event, Lifecycle.Event> boundaryResolver;
    private final LifecycleEventsObservable lifecycleObservable;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider$2 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static /* synthetic */ class C105282 {
        static final /* synthetic */ int[] $SwitchMap$android$arch$lifecycle$Lifecycle$Event = new int[Lifecycle.Event.values().length];

        static {
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static AndroidLifecycleScopeProvider from(LifecycleOwner lifecycleOwner) {
        return from(lifecycleOwner.getLifecycle());
    }

    public static AndroidLifecycleScopeProvider from(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        return from(lifecycleOwner.getLifecycle(), event);
    }

    public static AndroidLifecycleScopeProvider from(Lifecycle lifecycle) {
        return from(lifecycle, DEFAULT_CORRESPONDING_EVENTS);
    }

    public static AndroidLifecycleScopeProvider from(Lifecycle lifecycle, Lifecycle.Event event) {
        return from(lifecycle, new UntilEventFunction(event));
    }

    public static AndroidLifecycleScopeProvider from(LifecycleOwner lifecycleOwner, Function<Lifecycle.Event, Lifecycle.Event> function) {
        return from(lifecycleOwner.getLifecycle(), function);
    }

    public static AndroidLifecycleScopeProvider from(Lifecycle lifecycle, Function<Lifecycle.Event, Lifecycle.Event> function) {
        return new AndroidLifecycleScopeProvider(lifecycle, function);
    }

    private AndroidLifecycleScopeProvider(Lifecycle lifecycle, Function<Lifecycle.Event, Lifecycle.Event> function) {
        this.lifecycleObservable = new LifecycleEventsObservable(lifecycle);
        this.boundaryResolver = function;
    }

    @Override // com.uber.autodispose.LifecycleScopeProvider
    public Observable<Lifecycle.Event> lifecycle() {
        return this.lifecycleObservable;
    }

    @Override // com.uber.autodispose.LifecycleScopeProvider
    public Function<Lifecycle.Event, Lifecycle.Event> correspondingEvents() {
        return this.boundaryResolver;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.uber.autodispose.LifecycleScopeProvider
    public Lifecycle.Event peekLifecycle() {
        this.lifecycleObservable.backfillEvents();
        return this.lifecycleObservable.getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class UntilEventFunction implements Function<Lifecycle.Event, Lifecycle.Event> {
        private final Lifecycle.Event untilEvent;

        UntilEventFunction(Lifecycle.Event event) {
            this.untilEvent = event;
        }

        @Override // io.reactivex.functions.Function
        public Lifecycle.Event apply(Lifecycle.Event event) throws Exception {
            return this.untilEvent;
        }
    }
}
