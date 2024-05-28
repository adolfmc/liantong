package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: _Sequences.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m1890d2 = {"<anonymous>", "", "T", "R", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m1889k = 3, m1888mv = {1, 1, 16})
@DebugMetadata(m1882c = "kotlin.sequences.SequencesKt___SequencesKt$scan$1", m1881f = "_Sequences.kt", m1880i = {0, 1, 1, 1}, m1879l = {1433, 1437}, m1878m = "invokeSuspend", m1877n = {"$this$sequence", "$this$sequence", "accumulator", "element"}, m1876s = {"L$0", "L$0", "L$1", "L$2"})
/* loaded from: E:\11617560_dexfile_execute.dex */
final class SequencesKt___SequencesKt$scan$1<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $initial;
    final /* synthetic */ Function2 $operation;
    final /* synthetic */ Sequence $this_scan;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private SequenceScope f24905p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$scan$1(Sequence sequence, Object obj, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_scan = sequence;
        this.$initial = obj;
        this.$operation = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SequencesKt___SequencesKt$scan$1 sequencesKt___SequencesKt$scan$1 = new SequencesKt___SequencesKt$scan$1(this.$this_scan, this.$initial, this.$operation, completion);
        sequencesKt___SequencesKt$scan$1.f24905p$ = (SequenceScope) obj;
        return sequencesKt___SequencesKt$scan$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$scan$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        SequenceScope sequenceScope;
        SequenceScope sequenceScope2;
        Iterator it;
        Object obj2;
        Object obj3;
        SequencesKt___SequencesKt$scan$1<R> sequencesKt___SequencesKt$scan$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                sequenceScope = this.f24905p$;
                Object obj4 = this.$initial;
                this.L$0 = sequenceScope;
                this.label = 1;
                if (sequenceScope.yield(obj4, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Object obj5 = this.$initial;
                sequenceScope2 = sequenceScope;
                it = this.$this_scan.iterator();
                obj2 = coroutine_suspended;
                obj3 = obj5;
                sequencesKt___SequencesKt$scan$1 = this;
                break;
            case 1:
                sequenceScope = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                Object obj52 = this.$initial;
                sequenceScope2 = sequenceScope;
                it = this.$this_scan.iterator();
                obj2 = coroutine_suspended;
                obj3 = obj52;
                sequencesKt___SequencesKt$scan$1 = this;
                break;
            case 2:
                it = (Iterator) this.L$3;
                Object obj6 = this.L$2;
                Object obj7 = this.L$1;
                sequenceScope2 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                sequencesKt___SequencesKt$scan$1 = this;
                obj2 = coroutine_suspended;
                obj3 = obj7;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            Object next = it.next();
            obj3 = sequencesKt___SequencesKt$scan$1.$operation.invoke(obj3, next);
            sequencesKt___SequencesKt$scan$1.L$0 = sequenceScope2;
            sequencesKt___SequencesKt$scan$1.L$1 = obj3;
            sequencesKt___SequencesKt$scan$1.L$2 = next;
            sequencesKt___SequencesKt$scan$1.L$3 = it;
            sequencesKt___SequencesKt$scan$1.label = 2;
            if (sequenceScope2.yield(obj3, sequencesKt___SequencesKt$scan$1) == obj2) {
                return obj2;
            }
        }
        return Unit.INSTANCE;
    }
}
