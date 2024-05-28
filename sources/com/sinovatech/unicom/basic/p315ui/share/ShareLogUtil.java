package com.sinovatech.unicom.basic.p315ui.share;

import android.app.Activity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.basic.view.WoKouLingDialogManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.share.ShareLogUtil */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ShareLogUtil {
    private static final String TAG = "ShareLogUtil";
    private static String allKey = "step,mobileA,mobileB,businessCode,shareUrl,shareType,businessName,woCommand";

    /* renamed from: pd */
    private static CustomePorgressDialog f18432pd;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.ShareLogUtil$ShortUrlListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ShortUrlListener {
        void onShortUrlListener(boolean z, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.ShareLogUtil$WoKouLingListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface WoKouLingListener {
        void onWoKouLingListener(boolean z, String str);
    }

    public static void upLoadSharedByLog(final Map<String, String> map) {
        try {
            App.getAsyncHttpClient().post(URLSet.ShareInfoLog(), map, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.share.ShareLogUtil.1
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    super.onSuccess(i, str);
                    if (i == 200) {
                        try {
                            for (Map.Entry entry : map.entrySet()) {
                                MsLogUtil.m7979d("日志参数", "方式一：  Key = " + ((String) entry.getKey()) + "  ----  Value = " + ((String) entry.getValue()));
                            }
                            JSONObject jSONObject = new JSONObject(str);
                            String optString = jSONObject.optString("code");
                            String optString2 = jSONObject.optString("msg");
                            if (TextUtils.isEmpty(optString2)) {
                                optString2 = "绑定关系失败,请重试";
                            }
                            if (!TextUtils.equals("0000", optString)) {
                                UIUtils.toast(optString2);
                            }
                        } catch (Exception e) {
                            MsLogUtil.m7977e(ShareLogUtil.TAG, e.getMessage());
                        }
                    }
                    String str2 = ShareLogUtil.TAG;
                    MsLogUtil.m7979d(str2, "statusCode = " + i + " content = " + str);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    String str2 = ShareLogUtil.TAG;
                    MsLogUtil.m7979d(str2, "error = " + th.getLocalizedMessage() + " content = " + str);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("被分享者日志错误", e.getMessage());
        }
    }

    public static void shareUploadData(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        HashMap hashMap = new HashMap();
        hashMap.put("step", str5);
        hashMap.put("mobileA", str);
        hashMap.put("shareUrl", str4);
        hashMap.put("businessName", str2);
        hashMap.put("businessCode", str3);
        hashMap.put("shareType", str6);
        hashMap.put("woCommand", str7);
        hashMap.put("shareCompleteUrls", App.originalUrl);
        uploadData(activity, hashMap);
    }

    public static void uploadData(Activity activity, Map<String, String> map) {
        MsLogUtil.m7979d(TAG, "========绑定关系日志 start======");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String str = TAG;
            MsLogUtil.m7979d(str, entry.getKey() + ":" + entry.getValue());
        }
        MsLogUtil.m7979d(TAG, "========绑定关系日志 end======");
        App.getAsyncHttpClient().post(URLSet.ShareInfoLog(), map, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.share.ShareLogUtil.2
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str2) {
                super.onSuccess(i, str2);
                if (i == 200) {
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        String optString = jSONObject.optString("code");
                        String optString2 = jSONObject.optString("msg");
                        if (TextUtils.isEmpty(optString2)) {
                            optString2 = "绑定关系失败,请重试";
                        }
                        if (!TextUtils.equals("0000", optString)) {
                            UIUtils.toast(optString2);
                        }
                    } catch (Exception e) {
                        UIUtils.logE(ShareLogUtil.TAG, e.getMessage());
                    }
                }
                String str3 = ShareLogUtil.TAG;
                MsLogUtil.m7979d(str3, "statusCode = " + i + " content = " + str2);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str2) {
                super.onFailure(th, str2);
                String str3 = ShareLogUtil.TAG;
                MsLogUtil.m7979d(str3, "error = " + th.getLocalizedMessage() + " content = " + str2);
            }
        });
    }

    public static void wokouling(final Activity activity, Map<String, String> map, final WoKouLingListener woKouLingListener) {
        App.getAsyncHttpClient().rxPost(URLSet.createwWoKouLing(), map).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareLogUtil.3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                CustomePorgressDialog unused = ShareLogUtil.f18432pd = new CustomePorgressDialog(activity);
                ShareLogUtil.f18432pd.setMessage("正在生成沃口令");
                ShareLogUtil.f18432pd.setCanceledOnTouchOutside(false);
                ShareLogUtil.f18432pd.setCancelable(false);
                ShareLogUtil.f18432pd.show();
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String optString = jSONObject.optString("code");
                    String optString2 = jSONObject.optString("msg");
                    String optString3 = jSONObject.optString("woCommand");
                    if (TextUtils.equals("0000", optString)) {
                        if (woKouLingListener != null) {
                            if (!TextUtils.isEmpty(optString3) && optString3.contains("『") && optString3.contains("』")) {
                                woKouLingListener.onWoKouLingListener(true, optString3);
                            } else {
                                woKouLingListener.onWoKouLingListener(false, "沃口令生成失败,请重试");
                            }
                        }
                    } else if (woKouLingListener != null) {
                        WoKouLingListener woKouLingListener2 = woKouLingListener;
                        if (TextUtils.isEmpty(optString2)) {
                            optString2 = "沃口令生成失败,请重试";
                        }
                        woKouLingListener2.onWoKouLingListener(false, optString2);
                    }
                    String str2 = ShareLogUtil.TAG;
                    MsLogUtil.m7979d(str2, " content = " + str);
                } catch (Exception e) {
                    e.printStackTrace();
                    WoKouLingListener woKouLingListener3 = woKouLingListener;
                    if (woKouLingListener3 != null) {
                        woKouLingListener3.onWoKouLingListener(false, "沃口令生成失败,请重试");
                    }
                }
                if (ShareLogUtil.f18432pd == null || !ShareLogUtil.f18432pd.isShowing()) {
                    return;
                }
                ShareLogUtil.f18432pd.dismiss();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                WoKouLingListener woKouLingListener2 = woKouLingListener;
                if (woKouLingListener2 != null) {
                    woKouLingListener2.onWoKouLingListener(false, "沃口令生成失败,请重试");
                }
                MsLogUtil.m7979d(ShareLogUtil.TAG, th.getMessage());
                if (ShareLogUtil.f18432pd == null || !ShareLogUtil.f18432pd.isShowing()) {
                    return;
                }
                ShareLogUtil.f18432pd.dismiss();
            }
        });
    }

    public static void getwokouling(final Activity activity, final Map<String, String> map) {
        MsLogUtil.m7979d("wokouling_share", "加载弹窗");
        App.getAsyncHttpClient(60, 60, 60).post(URLSet.getWoKouLing(), map, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.share.ShareLogUtil.4
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str) {
                super.onSuccess(i, str);
                try {
                    String str2 = ShareLogUtil.TAG;
                    MsLogUtil.m7979d(str2, "kouling = " + str);
                    if (i == 200) {
                        JSONObject jSONObject = new JSONObject(str);
                        String optString = jSONObject.optString("code");
                        jSONObject.optString("msg");
                        if (TextUtils.equals("0000", optString)) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            String optString2 = jSONObject.optString("nickName");
                            String optString3 = jSONObject.optString("headIcon");
                            if (optJSONObject != null) {
                                String optString4 = optJSONObject.optString("shareImg");
                                String optString5 = optJSONObject.optString("shareUrl");
                                String optString6 = optJSONObject.optString("businessName");
                                String optString7 = optJSONObject.optString("mobileB");
                                String optString8 = optJSONObject.optString("provider");
                                String optString9 = optJSONObject.optString("businessCode");
                                String optString10 = optJSONObject.optString("businessDesc");
                                String str3 = TextUtils.isEmpty(optString8) ? "10010" : optString8;
                                String str4 = (String) map.get("woCommand");
                                if (!TextUtils.isEmpty(optString5)) {
                                    WoKouLingDialogManager.show(activity, str3, optString7, optString6, optString10, optString9, optString5, optString4, optString3, optString2, str4, new WoKouLingDialogManager.CopyDialogListener() { // from class: com.sinovatech.unicom.basic.ui.share.ShareLogUtil.4.1
                                        @Override // com.sinovatech.unicom.basic.view.WoKouLingDialogManager.CopyDialogListener
                                        public void onCancel() {
                                        }
                                    });
                                }
                            }
                        }
                    }
                    String str5 = ShareLogUtil.TAG;
                    MsLogUtil.m7979d(str5, "statusCode = " + i + " content = " + str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str) {
                super.onFailure(th, str);
                String str2 = ShareLogUtil.TAG;
                MsLogUtil.m7979d(str2, "error = " + th.getLocalizedMessage() + " content = " + str);
            }
        });
    }

    public static void createShortLinkUrl(final Activity activity, String str, final ShortUrlListener shortUrlListener) {
        CustomePorgressDialog customePorgressDialog;
        try {
            HashMap hashMap = new HashMap();
            Date date = new Date();
            String format = DateFormatUtils.format(date, JtDateUtil.dateFormatYMDHM);
            String str2 = TAG;
            UIUtils.logD(str2, "当前时间:" + format);
            String format2 = DateFormatUtils.format(DateUtils.addMonths(date, 6), JtDateUtil.dateFormatYMDHM);
            String str3 = TAG;
            UIUtils.logD(str3, "当前时间加上6个月后天后:" + format2);
            hashMap.put("longurl", str);
            hashMap.put("failuretime", format2);
            hashMap.put("effectiveTime", format);
            if (!App.hasLogined()) {
                hashMap.put("mobileFlag", "0");
            }
            App.getAsyncHttpClient().rxPost(URLSet.createShortLinkUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareLogUtil.5
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    Activity activity2 = activity;
                    if (activity2 == null || activity2.isFinishing()) {
                        return;
                    }
                    CustomePorgressDialog unused = ShareLogUtil.f18432pd = new CustomePorgressDialog(activity);
                    ShareLogUtil.f18432pd.setMessage("数据加载中");
                    ShareLogUtil.f18432pd.setCanceledOnTouchOutside(false);
                    ShareLogUtil.f18432pd.setCancelable(false);
                    ShareLogUtil.f18432pd.show();
                }

                @Override // io.reactivex.Observer
                public void onNext(String str4) {
                    try {
                        if (shortUrlListener != null) {
                            String optString = new JSONObject(str4).optString("shortUrl");
                            if (!TextUtils.isEmpty(optString)) {
                                String str5 = ShareLogUtil.TAG;
                                UIUtils.logD(str5, "分享短地址 = " + optString);
                                shortUrlListener.onShortUrlListener(true, optString);
                            } else {
                                shortUrlListener.onShortUrlListener(false, "短地址生成失败");
                            }
                        }
                        String str6 = ShareLogUtil.TAG;
                        MsLogUtil.m7979d(str6, "content = " + str4);
                    } catch (Exception e) {
                        ShortUrlListener shortUrlListener2 = shortUrlListener;
                        if (shortUrlListener2 != null) {
                            shortUrlListener2.onShortUrlListener(false, "短地址生成失败");
                        }
                        e.printStackTrace();
                        String str7 = ShareLogUtil.TAG;
                        MsLogUtil.m7979d(str7, "content = " + str4 + "\n Exception = " + e.getLocalizedMessage());
                    }
                    Activity activity2 = activity;
                    if (activity2 == null || activity2.isFinishing() || ShareLogUtil.f18432pd == null || !ShareLogUtil.f18432pd.isShowing()) {
                        return;
                    }
                    ShareLogUtil.f18432pd.dismiss();
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    ShortUrlListener shortUrlListener2 = shortUrlListener;
                    if (shortUrlListener2 != null) {
                        shortUrlListener2.onShortUrlListener(false, "短地址生成失败");
                    }
                    MsLogUtil.m7979d(ShareLogUtil.TAG, th.getMessage());
                    Activity activity2 = activity;
                    if (activity2 == null || activity2.isFinishing() || ShareLogUtil.f18432pd == null || !ShareLogUtil.f18432pd.isShowing()) {
                        return;
                    }
                    ShareLogUtil.f18432pd.dismiss();
                }
            });
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
            if (shortUrlListener != null) {
                shortUrlListener.onShortUrlListener(false, "短地址生成失败");
            }
            if (activity == null || activity.isFinishing() || (customePorgressDialog = f18432pd) == null || !customePorgressDialog.isShowing()) {
                return;
            }
            f18432pd.dismiss();
        }
    }

    public static Map<String, String> getQueryParams(String str) {
        try {
            String decode = URLDecoder.decode(str, "UTF-8");
            MsLogUtil.m7979d(TAG, "获取参数的url = " + decode);
            HashMap hashMap = new HashMap();
            String[] split = decode.split("\\?");
            if (split.length > 2) {
                for (String str2 : split[2].split("&")) {
                    String[] split2 = str2.split("=");
                    String decode2 = URLDecoder.decode(split2[0], "UTF-8");
                    String decode3 = split2.length > 1 ? URLDecoder.decode(split2[1], "UTF-8") : "";
                    if (allKey.contains(decode2)) {
                        hashMap.put(decode2, decode3);
                        UIUtils.logD("日志参数", String.format("key = %s || value = %s", decode2, decode3));
                    }
                }
            } else if (split.length > 1) {
                for (String str3 : split[1].split("&")) {
                    String[] split3 = str3.split("=");
                    String decode4 = URLDecoder.decode(split3[0], "UTF-8");
                    String decode5 = split3.length > 1 ? URLDecoder.decode(split3[1], "UTF-8") : "";
                    if (allKey.contains(decode4)) {
                        hashMap.put(decode4, decode5);
                        UIUtils.logD("日志参数", String.format("key = %s || value = %s", decode4, decode5));
                    }
                }
            }
            hashMap.put("step", "2");
            return hashMap;
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    public static void getDefaultShare(AppCompatActivity appCompatActivity) {
        try {
            App.getAsyncHttpClient().rxPost(URLSet.getDefaultShare(), new HashMap()).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareLogUtil.8
                @Override // io.reactivex.functions.Function
                public String apply(String str) throws Exception {
                    MsLogUtil.m7979d(ShareLogUtil.TAG, str);
                    try {
                        String optString = new JSONObject(str).optString("code");
                        if (!TextUtils.isEmpty(optString)) {
                            if (TextUtils.equals("0000", optString)) {
                                return str;
                            }
                        }
                        return "";
                    } catch (Exception e) {
                        UIUtils.logE(ShareLogUtil.TAG, e.getMessage());
                        return "";
                    }
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareLogUtil.6
                @Override // io.reactivex.functions.Consumer
                public void accept(String str) throws Exception {
                    if (TextUtils.isEmpty(str) || TextUtils.equals("{}", str)) {
                        return;
                    }
                    App.getSharePreferenceUtil().putString("share_default_share", str);
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareLogUtil.7
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    MsLogUtil.m7979d(ShareLogUtil.TAG, th.getMessage());
                }
            });
        } catch (Exception e) {
            UIUtils.logE(TAG, e.getMessage());
        }
    }

    public static String getLongUrl(String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            if (!TextUtils.isEmpty(str3)) {
                String str7 = str + (str.contains("?") ? "&" : "?") + "linkType=unicomNewShare&mobileA=" + str2 + "&businessName=" + str4 + "&businessCode=" + str5 + "&shareType=" + str6;
                return str3 + (str3.contains("?") ? "&" : "?") + "title=" + URLEncoder.encode(str4, "UTF-8") + "&openUrl=" + str7;
            }
            String[] split = str.split("http");
            if (split[split.length - 1].contains("?")) {
                return str + "&linkType=unicomNewShare&mobileA=" + str2 + "&businessName=" + str4 + "&businessCode=" + str5 + "&shareType=" + str6;
            }
            String str8 = "";
            for (int i = 0; i < split.length - 1; i++) {
                if (!TextUtils.isEmpty(split[i])) {
                    str8 = str8 + "http" + split[i];
                }
            }
            return (str8 + "http" + split[split.length - 1] + "?") + "linkType=unicomNewShare&mobileA=" + str2 + "&businessName=" + str4 + "&businessCode=" + str5 + "&shareType=" + str6;
        } catch (Exception e) {
            UIUtils.logE(TAG, e.getMessage());
            return "";
        }
    }
}
