package com.baidu.license.filter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.cloud.download.DownloadManager;
import com.baidu.cloud.download.exception.DownloadException;
import com.baidu.license.INotProguard;
import com.baidu.license.SDKHttpConfig;
import com.baidu.license.api.ApiCallBack;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import okhttp3.ResponseBody;
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
public class DownFilterHelper implements INotProguard {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnDownFilterListener {
        void onDownFail(DownloadException downloadException);

        void onDownSuccess(String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnLoadFilterListener {
        void onLoadFail(int i, String str);

        void onLoadSuccess(String str);
    }

    public DownFilterHelper(Context context, String str) {
        SDKHttpConfig.appId = str;
        SDKHttpConfig.packageName = context.getPackageName();
    }

    public void downFilterZip(String str, String str2, OnDownFilterListener onDownFilterListener) {
        if (str2 != null && str2.equals(AuthenticationManager.m22347c(str))) {
            String m22358a = EffectInfoHelp.m22358a(str);
            File file = new File(m22358a + ".filter");
            DownloadManager.getInstance().download(str, EffectInfoHelp.m22360a(), file.getName(), new p001a.p002a.p003a.p053b.DownFilterHelper(this, file, new File(m22358a + JtClient.UXUE_TEMP_FILE_SUFFIX), m22358a, onDownFilterListener));
            return;
        }
        onDownFilterListener.onDownFail(new DownloadException("download url is not equal fileSign"));
    }

    public void loadFilterList(final OnLoadFilterListener onLoadFilterListener) {
        HashMap<String, Object> m22368a = RequestParameterUtil.m22368a();
        m22368a.put("sign", RequestParameterUtil.m22364a(SDKHttpConfig.getSignStr(), AuthenticationManager.m22352a(), "utf-8"));
        ApiManager.m22371a().reqFilter(m22368a).enqueue(new ApiCallBack<ResponseBody>() { // from class: com.baidu.license.filter.DownFilterHelper.1
            @Override // com.baidu.license.api.ApiCallBack
            public void onFail(Call<ResponseBody> call, Throwable th) {
                Log.e("body", "body" + th);
                OnLoadFilterListener onLoadFilterListener2 = onLoadFilterListener;
                if (onLoadFilterListener2 != null) {
                    onLoadFilterListener2.onLoadFail(9980, "获取数据失败");
                }
            }

            @Override // com.baidu.license.api.ApiCallBack
            public void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
                String str;
                Log.e("body", "body" + response);
                try {
                    str = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                    str = null;
                }
                Log.e("body", "body" + str);
                if (!TextUtils.isEmpty(str)) {
                    onLoadFilterListener.onLoadSuccess(str);
                    return;
                }
                OnLoadFilterListener onLoadFilterListener2 = onLoadFilterListener;
                if (onLoadFilterListener2 != null) {
                    onLoadFilterListener2.onLoadFail(9970, "获取数据失败");
                }
            }
        });
    }
}
