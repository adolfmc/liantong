package com.baidu.cloud.license.api;

import com.baidu.cloud.license.license.LicenseModel;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ApiService {
    public static final String FIRST_CACHE_AND_NET_HEADER_ENTITY = "cache_need_req:y";
    public static final String ONLY_CAHCE_HEADER_ENTITY = "cache_need_req:n";

    @POST("/v1/sdk/license?valid")
    Call<LicenseModel> reqLicenseValid(@Body Map<String, Object> map);
}
