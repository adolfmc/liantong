package com.baidu.cloud.license.api;

import com.baidu.cloud.license.INotProguard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class ApiCallBack<T> implements INotProguard, Callback<T> {
    public abstract void onFail(Call<T> call, Throwable th);

    public abstract void onSuccess(Call<T> call, Response<T> response);

    @Override // retrofit2.Callback
    public final void onResponse(Call<T> call, Response<T> response) {
        if (response == null || response.body() == null || !response.isSuccessful()) {
            onFailure(call, new Throwable(response == null ? "" : response.message()));
        } else {
            onSuccess(call, response);
        }
        C2490oi.m20085a().m20084a(getClass().getName(), call);
    }

    @Override // retrofit2.Callback
    public final void onFailure(Call<T> call, Throwable th) {
        if (!call.isCanceled()) {
            onFail(call, th);
        }
        C2490oi.m20085a().m20084a(getClass().getName(), call);
    }
}
