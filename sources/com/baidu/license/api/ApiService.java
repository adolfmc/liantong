package com.baidu.license.api;

import com.baidu.license.license.LicenseModel;
import com.baidu.license.sticker.StickerListModel;
import com.baidu.license.sticker.StickerOneModel;
import com.baidu.license.template.bean.TemplateListModel;
import java.util.Map;
import okhttp3.ResponseBody;
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

    @POST("/v1/sdk/filter?list")
    Call<ResponseBody> reqFilter(@Body Map<String, Object> map);

    @POST("v1/sdk/subtitle?get")
    Call<ResponseBody> reqGetResolveResult(@Body Map<String, Object> map);

    @POST("/v1/sdk/license?valid")
    Call<LicenseModel> reqLicenseValid(@Body Map<String, Object> map);

    @POST("/v1/sdk/face")
    Call<StickerOneModel> reqOneSticker(@Body Map<String, Object> map);

    @POST("/v1/sdk/subtitle?process")
    Call<ResponseBody> reqStartResolve(@Body Map<String, Object> map);

    @POST("/v1/sdk/face?list")
    Call<StickerListModel> reqStickerList(@Body Map<String, Object> map);

    @POST("/v1/sdk/template?list")
    Call<TemplateListModel> reqTemplateList(@Body Map<String, Object> map);

    @POST("/v1/sdk/transition?list")
    Call<ResponseBody> reqTransitions(@Body Map<String, Object> map);

    @POST("/v1/sdk/subtitle?apply")
    Call<ResponseBody> reqUploadParams(@Body Map<String, Object> map);
}
