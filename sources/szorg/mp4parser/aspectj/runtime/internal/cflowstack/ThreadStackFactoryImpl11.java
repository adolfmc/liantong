package szorg.mp4parser.aspectj.runtime.internal.cflowstack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ThreadStackFactoryImpl11 implements ThreadStackFactory {
    @Override // szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactory
    public ThreadCounter getNewThreadCounter() {
        return new ThreadCounterImpl11();
    }

    @Override // szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactory
    public ThreadStack getNewThreadStack() {
        return new ThreadStackImpl11();
    }
}
