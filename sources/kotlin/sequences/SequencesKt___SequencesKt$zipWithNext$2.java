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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: _Sequences.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m1890d2 = {"<anonymous>", "", "T", "R", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m1889k = 3, m1888mv = {1, 1, 16})
@DebugMetadata(m1882c = "kotlin.sequences.SequencesKt___SequencesKt$zipWithNext$2", m1881f = "_Sequences.kt", m1880i = {0, 0, 0, 0}, m1879l = {1871}, m1878m = "invokeSuspend", m1877n = {"$this$result", "iterator", "current", "next"}, m1876s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class SequencesKt___SequencesKt$zipWithNext$2<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Sequence $this_zipWithNext;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private SequenceScope f24909p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$zipWithNext$2(Sequence sequence, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_zipWithNext = sequence;
        this.$transform = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SequencesKt___SequencesKt$zipWithNext$2 sequencesKt___SequencesKt$zipWithNext$2 = new SequencesKt___SequencesKt$zipWithNext$2(this.$this_zipWithNext, this.$transform, completion);
        sequencesKt___SequencesKt$zipWithNext$2.f24909p$ = (SequenceScope) obj;
        return sequencesKt___SequencesKt$zipWithNext$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$zipWithNext$2) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        SequenceScope sequenceScope;
        SequencesKt___SequencesKt$zipWithNext$2<R> sequencesKt___SequencesKt$zipWithNext$2;
        Object obj2;
        Object obj3;
        Iterator it;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                SequenceScope sequenceScope2 = this.f24909p$;
                Iterator it2 = this.$this_zipWithNext.iterator();
                if (it2.hasNext()) {
                    Object next = it2.next();
                    sequenceScope = sequenceScope2;
                    sequencesKt___SequencesKt$zipWithNext$2 = this;
                    obj2 = coroutine_suspended;
                    obj3 = next;
                    it = it2;
                    break;
                } else {
                    return Unit.INSTANCE;
                }
            case 1:
                Object obj4 = this.L$3;
                Object obj5 = this.L$2;
                it = (Iterator) this.L$1;
                sequenceScope = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                sequencesKt___SequencesKt$zipWithNext$2 = this;
                obj2 = coroutine_suspended;
                obj3 = obj4;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            Object next2 = it.next();
            Object invoke = sequencesKt___SequencesKt$zipWithNext$2.$transform.invoke(obj3, next2);
            sequencesKt___SequencesKt$zipWithNext$2.L$0 = sequenceScope;
            sequencesKt___SequencesKt$zipWithNext$2.L$1 = it;
            sequencesKt___SequencesKt$zipWithNext$2.L$2 = obj3;
            sequencesKt___SequencesKt$zipWithNext$2.L$3 = next2;
            sequencesKt___SequencesKt$zipWithNext$2.label = 1;
            if (sequenceScope.yield(invoke, sequencesKt___SequencesKt$zipWithNext$2) == obj2) {
                return obj2;
            }
            obj3 = next2;
        }
        return Unit.INSTANCE;
    }
}
