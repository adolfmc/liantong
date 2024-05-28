package com.baidu.license.sticker;

import android.content.Context;
import com.baidu.cloud.download.DownloadManager;
import com.baidu.cloud.download.base.DownloadCallback;
import com.baidu.license.INotProguard;
import com.baidu.license.SDKHttpConfig;
import com.baidu.license.api.ApiCallBack;
import java.util.HashMap;
import p001a.p002a.p003a.p004a.ApiManager;
import p001a.p002a.p003a.p004a.RequestParameterUtil;
import p001a.p002a.p003a.p057f.AuthenticationManager;
import retrofit2.Call;
import retrofit2.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DownStickerHelper implements INotProguard {
    public static final String MSG_FAIL = "获取数据失败";
    public static final int RESPONSE_SUCCESS = 3010;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface StickerDataListener extends INotProguard {
        void onFail(String str);

        void onListSuccess(String str);

        void onOneSuccess(String str);
    }

    public DownStickerHelper(Context context, String str, int i) {
        SDKHttpConfig.appId = str;
        SDKHttpConfig.packageName = context.getPackageName();
        SDKHttpConfig.arVersion = i;
    }

    public static void downLoadSticker(String str, String str2, String str3, DownloadCallback downloadCallback) {
        try {
            DownloadManager.getInstance().download(RequestParameterUtil.m22365a(str, AuthenticationManager.m22352a()), str2, str3, downloadCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadOneStickerList(long j, final StickerDataListener stickerDataListener) {
        HashMap<String, Object> m22368a = RequestParameterUtil.m22368a();
        String m22364a = RequestParameterUtil.m22364a(SDKHttpConfig.getFaceSignStr(j), AuthenticationManager.m22352a(), "utf-8");
        m22368a.put("faceId", Long.valueOf(j));
        m22368a.put("arVersion", Integer.valueOf(SDKHttpConfig.arVersion));
        m22368a.put("sign", m22364a);
        ApiManager.m22371a().reqOneSticker(m22368a).enqueue(new ApiCallBack<StickerOneModel>() { // from class: com.baidu.license.sticker.DownStickerHelper.2
            @Override // com.baidu.license.api.ApiCallBack
            public void onFail(Call<StickerOneModel> call, Throwable th) {
                StickerDataListener stickerDataListener2 = stickerDataListener;
                if (stickerDataListener2 != null) {
                    stickerDataListener2.onFail("获取数据失败");
                }
            }

            @Override // com.baidu.license.api.ApiCallBack
            public void onSuccess(Call<StickerOneModel> call, Response<StickerOneModel> response) {
                if (response.body().getResult() != null) {
                    if (response.body().getResult().getCode() == 3010) {
                        StickerDataListener stickerDataListener2 = stickerDataListener;
                        if (stickerDataListener2 != null) {
                            stickerDataListener2.onOneSuccess(response.body().getResult().getFaceInfo().toString());
                            return;
                        }
                        return;
                    }
                    StickerDataListener stickerDataListener3 = stickerDataListener;
                    if (stickerDataListener3 != null) {
                        stickerDataListener3.onFail(response.body().getResult().getMessage());
                        return;
                    }
                    return;
                }
                StickerDataListener stickerDataListener4 = stickerDataListener;
                if (stickerDataListener4 != null) {
                    stickerDataListener4.onFail("获取数据失败");
                }
            }
        });
    }

    public void loadStickerList(final StickerDataListener stickerDataListener) {
        HashMap<String, Object> m22368a = RequestParameterUtil.m22368a();
        String m22364a = RequestParameterUtil.m22364a(SDKHttpConfig.getSignStr(), AuthenticationManager.m22352a(), "utf-8");
        m22368a.put("arVersion", Integer.valueOf(SDKHttpConfig.arVersion));
        m22368a.put("sign", m22364a);
        ApiManager.m22371a().reqStickerList(m22368a).enqueue(new ApiCallBack<StickerListModel>() { // from class: com.baidu.license.sticker.DownStickerHelper.1
            @Override // com.baidu.license.api.ApiCallBack
            public void onFail(Call<StickerListModel> call, Throwable th) {
                StickerDataListener stickerDataListener2 = stickerDataListener;
                if (stickerDataListener2 != null) {
                    stickerDataListener2.onFail("获取数据失败");
                }
            }

            @Override // com.baidu.license.api.ApiCallBack
            public void onSuccess(Call<StickerListModel> call, Response<StickerListModel> response) {
                if (response.body().getResult() != null) {
                    if (response.body().getResult().getCode() == 3010) {
                        StickerDataListener stickerDataListener2 = stickerDataListener;
                        if (stickerDataListener2 != null) {
                            stickerDataListener2.onListSuccess(response.body().getResult().getFaceInfoList().toString());
                            return;
                        }
                        return;
                    }
                    StickerDataListener stickerDataListener3 = stickerDataListener;
                    if (stickerDataListener3 != null) {
                        stickerDataListener3.onFail(response.body().getResult().getMessage());
                        return;
                    }
                    return;
                }
                StickerDataListener stickerDataListener4 = stickerDataListener;
                if (stickerDataListener4 != null) {
                    stickerDataListener4.onFail("获取数据失败");
                }
            }
        });
    }
}
