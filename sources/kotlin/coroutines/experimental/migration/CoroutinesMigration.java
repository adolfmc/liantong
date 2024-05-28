package kotlin.coroutines.experimental.migration;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContextImpl;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, m1890d2 = {"Lkotlin/coroutines/experimental/migration/ContextMigration;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "(Lkotlin/coroutines/experimental/CoroutineContext;)V", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "Key", "kotlin-stdlib-coroutines"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: kotlin.coroutines.experimental.migration.ContextMigration */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class CoroutinesMigration extends CoroutineContextImpl {
    public static final Key Key = new Key(null);
    @NotNull
    private final CoroutineContext context;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: CoroutinesMigration.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, m1890d2 = {"Lkotlin/coroutines/experimental/migration/ContextMigration$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlin/coroutines/experimental/migration/ContextMigration;", "()V", "kotlin-stdlib-coroutines"}, m1889k = 1, m1888mv = {1, 1, 16})
    /* renamed from: kotlin.coroutines.experimental.migration.ContextMigration$Key */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Key implements CoroutineContext.Key<CoroutinesMigration> {
        private Key() {
        }

        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutinesMigration(@NotNull kotlin.coroutines.experimental.CoroutineContext context) {
        super(Key);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @NotNull
    public final kotlin.coroutines.experimental.CoroutineContext getContext() {
        return this.context;
    }
}
