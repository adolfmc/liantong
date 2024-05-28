package com.baidu.license.template;

import android.content.Context;
import com.baidu.cloud.download.DownloadManager;
import com.baidu.cloud.download.exception.DownloadException;
import com.baidu.license.INotProguard;
import com.baidu.license.SDKHttpConfig;
import com.baidu.license.api.ApiCallBack;
import com.baidu.license.template.bean.TemplateData;
import com.baidu.license.template.bean.TemplateListModel;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import p001a.p002a.p003a.p004a.ApiManager;
import p001a.p002a.p003a.p004a.RequestParameterUtil;
import p001a.p002a.p003a.p055d.EffectInfoHelp;
import p001a.p002a.p003a.p057f.AuthenticationManager;
import retrofit2.Call;
import retrofit2.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DownTemplateHelper implements INotProguard {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnDownTemplateListener {
        void onDownFail(DownloadException downloadException);

        void onDownSuccess(String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnLoadTemplateListener {
        void onLoadFail(int i, String str);

        void onLoadSuccess(List<TemplateData> list);
    }

    public DownTemplateHelper(Context context, String str) {
        SDKHttpConfig.appId = str;
        SDKHttpConfig.packageName = context.getPackageName();
    }

    public static String getFilePath(String str) {
        return EffectInfoHelp.m22360a().getAbsolutePath() + File.separator + EffectInfoHelp.m22355c(str);
    }

    public static boolean isTemplateExist(String str) {
        return EffectInfoHelp.m22356b(str);
    }

    public void downTemplateZip(String str, String str2, OnDownTemplateListener onDownTemplateListener) {
        if (str2 != null && str2.equals(AuthenticationManager.m22347c(str))) {
            String m22358a = EffectInfoHelp.m22358a(str);
            File file = new File(m22358a + ".temp");
            DownloadManager.getInstance().download(str, EffectInfoHelp.m22360a(), file.getName(), new p001a.p002a.p003a.p055d.DownTemplateHelper(this, file, new File(m22358a + JtClient.UXUE_TEMP_FILE_SUFFIX), m22358a, onDownTemplateListener));
            return;
        }
        onDownTemplateListener.onDownFail(new DownloadException("download url is not equal fileSign"));
    }

    public void loadTemplateList(final OnLoadTemplateListener onLoadTemplateListener) {
        HashMap<String, Object> m22368a = RequestParameterUtil.m22368a();
        m22368a.put("sign", RequestParameterUtil.m22364a(SDKHttpConfig.getSignStr(), AuthenticationManager.m22352a(), "utf-8"));
        ApiManager.m22371a().reqTemplateList(m22368a).enqueue(new ApiCallBack<TemplateListModel>() { // from class: com.baidu.license.template.DownTemplateHelper.1
            @Override // com.baidu.license.api.ApiCallBack
            public void onFail(Call<TemplateListModel> call, Throwable th) {
                OnLoadTemplateListener onLoadTemplateListener2 = onLoadTemplateListener;
                if (onLoadTemplateListener2 != null) {
                    onLoadTemplateListener2.onLoadFail(9980, "获取数据失败");
                }
            }

            @Override // com.baidu.license.api.ApiCallBack
            public void onSuccess(Call<TemplateListModel> call, Response<TemplateListModel> response) {
                if (response.body().getResult() != null) {
                    if (response.body().getResult().getCode() == 3010) {
                        OnLoadTemplateListener onLoadTemplateListener2 = onLoadTemplateListener;
                        if (onLoadTemplateListener2 != null) {
                            onLoadTemplateListener2.onLoadSuccess(response.body().getResult().getTemplateList());
                            return;
                        }
                        return;
                    }
                    OnLoadTemplateListener onLoadTemplateListener3 = onLoadTemplateListener;
                    if (onLoadTemplateListener3 != null) {
                        onLoadTemplateListener3.onLoadFail(response.body().getResult().getCode(), response.body().getResult().getMessage());
                        return;
                    }
                    return;
                }
                OnLoadTemplateListener onLoadTemplateListener4 = onLoadTemplateListener;
                if (onLoadTemplateListener4 != null) {
                    onLoadTemplateListener4.onLoadFail(9970, "获取数据失败");
                }
            }
        });
    }
}
