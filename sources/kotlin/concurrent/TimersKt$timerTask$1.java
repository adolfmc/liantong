package kotlin.concurrent;

import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Timer.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m1890d2 = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "run", "", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class TimersKt$timerTask$1 extends TimerTask {
    final /* synthetic */ Function1 $action;

    public TimersKt$timerTask$1(Function1 function1) {
        this.$action = function1;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.$action.invoke(this);
    }
}
