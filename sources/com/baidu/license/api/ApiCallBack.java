package com.baidu.license.api;

import com.baidu.license.INotProguard;
import p001a.p002a.p003a.p004a.ApiManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class ApiCallBack<T> implements INotProguard, Callback<T> {
    public abstract void onFail(Call<T> call, Throwable th);

    @Override // retrofit2.Callback
    public final void onFailure(Call<T> call, Throwable th) {
        if (!call.isCanceled()) {
            onFail(call, th);
        }
        ApiManager.m22369b().m22370a(getClass().getName(), call);
    }

    @Override // retrofit2.Callback
    public final void onResponse(Call<T> call, Response<T> response) {
        if (response != null && response.body() != null && response.isSuccessful()) {
            onSuccess(call, response);
        } else {
            onFailure(call, new Throwable(response == null ? "" : response.message()));
        }
        ApiManager.m22369b().m22370a(getClass().getName(), call);
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);
}
