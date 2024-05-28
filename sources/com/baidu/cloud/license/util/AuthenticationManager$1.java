package com.baidu.cloud.license.util;

import com.baidu.cloud.license.api.ApiCallBack;
import com.baidu.cloud.license.license.LicenseModel;
import retrofit2.Call;
import retrofit2.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class AuthenticationManager$1 extends ApiCallBack<LicenseModel> {
    @Override // com.baidu.cloud.license.api.ApiCallBack
    public final void onFail(Call<LicenseModel> call, Throwable th) {
    }

    AuthenticationManager$1() {
    }

    @Override // com.baidu.cloud.license.api.ApiCallBack
    public final void onSuccess(Call<LicenseModel> call, Response<LicenseModel> response) {
        if (response.body() != null) {
            C2492oi.m20077a(response.body());
        }
    }
}
