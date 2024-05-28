package p475rx.plugins;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.plugins.RxJavaSingleExecutionHookDefault */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RxJavaSingleExecutionHookDefault extends RxJavaSingleExecutionHook {
    private static final RxJavaSingleExecutionHookDefault INSTANCE = new RxJavaSingleExecutionHookDefault();

    RxJavaSingleExecutionHookDefault() {
    }

    public static RxJavaSingleExecutionHook getInstance() {
        return INSTANCE;
    }
}
