package retrofit2.adapter.rxjava;

import java.lang.reflect.Type;
import p475rx.Observable;
import p475rx.Single;
import retrofit2.Call;
import retrofit2.CallAdapter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
final class SingleHelper {
    SingleHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CallAdapter<Single<?>> makeSingle(final CallAdapter<Observable<?>> callAdapter) {
        return new CallAdapter<Single<?>>() { // from class: retrofit2.adapter.rxjava.SingleHelper.1
            @Override // retrofit2.CallAdapter
            public Type responseType() {
                return CallAdapter.this.responseType();
            }

            @Override // retrofit2.CallAdapter
            public <R> Single<?> adapt(Call<R> call) {
                return ((Observable) CallAdapter.this.adapt(call)).toSingle();
            }
        };
    }
}
