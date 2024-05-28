package com.sinovatech.unicom.separatemodule.appscore;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RSACryptos;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.live.capture.util.TimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PingjiaDialogManager {
    private static String deviceModel;
    private static int screenWidth;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface PingjiaDialogListener {
        void onError();

        void onNext(JSONObject jSONObject);
    }

    public static void show(Activity activity, String str, String str2, String str3) {
        String loginType = UserManager.getInstance().getLoginType();
        if ("01".equals(loginType) || "06".equals(loginType)) {
            showDialog(activity, str, str2, str3);
        } else {
            jumpAppMarket(activity);
        }
    }

    public static void showDialog(final Activity activity, final String str, String str2, final String str3) {
        try {
            final String str4 = DeviceHelper.getDeviceBrand() + "_" + DeviceHelper.getDeviceBranD() + "_" + DeviceHelper.getDeviceModel();
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i = displayMetrics.densityDpi;
            float f2 = displayMetrics.xdpi;
            float f3 = displayMetrics.ydpi;
            int i2 = displayMetrics.widthPixels;
            int i3 = displayMetrics.heightPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i2;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            Window window = dialog.getWindow();
            window.setAttributes(attributes);
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = activity.getLayoutInflater().inflate(2131493399, (ViewGroup) null);
            ImageView imageView = (ImageView) inflate.findViewById(2131297047);
            TextView textView = (TextView) inflate.findViewById(2131298785);
            TextView textView2 = (TextView) inflate.findViewById(2131298087);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(2131298708);
            ImageView imageView2 = (ImageView) inflate.findViewById(2131298709);
            TextView textView3 = (TextView) inflate.findViewById(2131298710);
            TextView textView4 = (TextView) inflate.findViewById(2131296960);
            ImageView imageView3 = (ImageView) inflate.findViewById(2131298628);
            if (!TextUtils.isEmpty(str) && str != null) {
                if ("01".equals(str)) {
                    imageView.setVisibility(0);
                    GlideApp.with(activity).load((Integer) 2131232186).into(imageView);
                    textView.setText("福利提醒");
                    textView2.setText("通过本入口评价五星好评最多可领取" + str2 + "奖励积分！");
                    imageView2.setVisibility(0);
                    GlideApp.with(activity).load((Integer) 2131232187).into(imageView2);
                    textView3.setText("立即评价");
                    textView4.setVisibility(0);
                    textView4.setText("我要吐槽");
                } else if ("02".equals(str)) {
                    imageView.setVisibility(0);
                    GlideApp.with(activity).load((Integer) 2131232189).into(imageView);
                    textView.setText("领取成功");
                    textView2.setText("感谢您的评价！" + str2 + "奖励积分将在24小时内派送至您的账户");
                    textView3.setText("逛逛积分商城");
                } else if ("03".equals(str)) {
                    textView.setText("温馨提示");
                    textView2.setText("只能参与一次活动哦，已为您发放积分~");
                    textView3.setText("逛逛积分商城");
                } else if ("04".equals(str)) {
                    textView.setText("温馨提示");
                    textView2.setText("今日领取名额达上限明天早点来哦~");
                    textView3.setText("逛逛积分商城");
                }
            }
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    String str5;
                    String str6;
                    String str7;
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (!TextUtils.isEmpty(str) && (str5 = str) != null) {
                        if ("01".equals(str5)) {
                            PingjiaDialogManager.rewardReceive(activity);
                            PingjiaDialogManager.jumpAppMarket(activity);
                            if ("0".equals(str3)) {
                                str6 = "设置";
                                str7 = "立即评价";
                            } else if ("1".equals(str3)) {
                                str6 = "话费";
                                str7 = "立即评价";
                            } else if ("2".equals(str3)) {
                                str6 = "快讯";
                                str7 = "立即评价";
                            } else {
                                str6 = "";
                                str7 = "立即评价";
                            }
                        } else if ("02".equals(str)) {
                            IntentManager.generateIntentAndGo(activity, "https://img.client.10010.com/adnew/find/#/fulishe?from=97000001107", "福利商城", false, "get");
                            str6 = "领取成功";
                            str7 = "逛逛积分商城";
                        } else if ("03".equals(str)) {
                            IntentManager.generateIntentAndGo(activity, "https://img.client.10010.com/adnew/find/#/fulishe?from=97000001105", "福利商城", false, "get");
                            str6 = "已参与";
                            str7 = "逛逛积分商城";
                        } else if ("04".equals(str)) {
                            IntentManager.generateIntentAndGo(activity, "https://img.client.10010.com/adnew/find/#/fulishe?from=97000001106", "福利商城", false, "get");
                            str6 = "名额上限";
                            str7 = "逛逛积分商城";
                        } else {
                            str6 = "";
                            str7 = "";
                        }
                        StatisticsUploadUtils.upload(activity, "sscpf0001", str6, str7, str4, "评论弹窗", "");
                    }
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    String str5;
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if ("0".equals(str3)) {
                        str5 = "设置";
                    } else if ("1".equals(str3)) {
                        str5 = "话费";
                    } else {
                        str5 = "2".equals(str3) ? "快讯" : "";
                    }
                    IntentManager.generateIntentAndGo(activity, "https://img.client.10010.com/tucao/entrance.html", "反馈与建议", false, "get");
                    StatisticsUploadUtils.upload(activity, "sscpf0001", str5, "我要吐槽", str4, "评论弹窗", "");
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            if (!TextUtils.isEmpty(str) && str != null) {
                dialog.show();
            }
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jumpAppMarket(Activity activity) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("market://details?id=" + activity.getPackageName()));
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(intent);
            } else {
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + activity.getPackageName()));
                if (intent.resolveActivity(activity.getPackageManager()) != null) {
                    activity.startActivity(intent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rewardQuery(Activity activity, final PingjiaDialogListener pingjiaDialogListener) {
        try {
            String defaultPhoneNumber = UserManager.getInstance().getDefaultPhoneNumber();
            HashMap hashMap = new HashMap();
            hashMap.put("mobile", Base64.encodeToString(RSACryptos.encryptByPublicKey(defaultPhoneNumber.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCy0tZaZgUJqvoBfcVXoGuquEq3vaU8EDjRlqSE+YALleDp4VWvRQ1ME4u7PDAFlhnDxR8wd6QIXt1rXezGHleT8Zvr+mUdY3YtpstcEylk4cCuWMlDYPy30MzIj2s1ZSk1gFMY5vTCHBOYPiQpiSCAiqd5R41fD+a2uPNhYOhIeQIDAQAB", 2)), 2));
            hashMap.put("accessType", "2");
            UIUtils.logD("积分查询URL：" + URLSet.getScorePointsQuery());
            UIUtils.logD("积分查询入参：" + hashMap);
            App.getAsyncHttpClient(3, 3, 3).rxPost(URLSet.getScorePointsQuery(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.5
                @Override // io.reactivex.functions.Function
                public String apply(String str) throws Exception {
                    UIUtils.logD("积分查询报文：" + str);
                    return str;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.4
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(String str) {
                    try {
                        if (!TextUtils.isEmpty(str)) {
                            JSONObject jSONObject = new JSONObject(str);
                            PingjiaDialogListener.this.onNext(jSONObject);
                            UIUtils.logD("积分查询onNext：" + jSONObject.optString("desc"));
                            return;
                        }
                        throw new RuntimeException("积分查询结果为空");
                    } catch (Exception e) {
                        e.printStackTrace();
                        UIUtils.logD("积分查询onNext：" + e.getMessage());
                        PingjiaDialogListener.this.onError();
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    UIUtils.logD("积分查询错误：" + th.getMessage());
                    PingjiaDialogListener.this.onError();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.logD("积分查询错误：" + e.getMessage());
            pingjiaDialogListener.onError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void rewardReceive(final Activity activity) {
        try {
            String defaultPhoneNumber = UserManager.getInstance().getDefaultPhoneNumber();
            Random random = new Random();
            HashMap hashMap = new HashMap();
            hashMap.put("mobile", Base64.encodeToString(RSACryptos.encryptByPublicKey(defaultPhoneNumber.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCy0tZaZgUJqvoBfcVXoGuquEq3vaU8EDjRlqSE+YALleDp4VWvRQ1ME4u7PDAFlhnDxR8wd6QIXt1rXezGHleT8Zvr+mUdY3YtpstcEylk4cCuWMlDYPy30MzIj2s1ZSk1gFMY5vTCHBOYPiQpiSCAiqd5R41fD+a2uPNhYOhIeQIDAQAB", 2)), 2));
            hashMap.put("seq", Base64.encodeToString(RSACryptos.encryptByPublicKey((TimeUtil.timeStamp2Date(System.currentTimeMillis(), "yyyyMMddHHmmss") + "_" + (random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9)) + "_" + defaultPhoneNumber).getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCy0tZaZgUJqvoBfcVXoGuquEq3vaU8EDjRlqSE+YALleDp4VWvRQ1ME4u7PDAFlhnDxR8wd6QIXt1rXezGHleT8Zvr+mUdY3YtpstcEylk4cCuWMlDYPy30MzIj2s1ZSk1gFMY5vTCHBOYPiQpiSCAiqd5R41fD+a2uPNhYOhIeQIDAQAB", 2)), 2));
            hashMap.put("reqTime", String.valueOf(System.currentTimeMillis()));
            hashMap.put("deviceModel", DeviceHelper.getDeviceBrand() + "_" + DeviceHelper.getDeviceBranD() + "_" + DeviceHelper.getDeviceModel());
            StringBuilder sb = new StringBuilder();
            sb.append("积分领取URL：");
            sb.append(URLSet.getScorePointsReceive());
            UIUtils.logD(sb.toString());
            UIUtils.logD("积分领取入参：" + hashMap);
            App.getAsyncHttpClient().rxPost(URLSet.getScorePointsReceive(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.7
                @Override // io.reactivex.functions.Function
                public String apply(String str) throws Exception {
                    UIUtils.logD("积分领取报文：" + str);
                    return str;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.6
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(String str) {
                    try {
                        if (!TextUtils.isEmpty(str)) {
                            JSONObject jSONObject = new JSONObject(str);
                            String optString = jSONObject.optString("code");
                            String optString2 = jSONObject.optString("data");
                            if ("0000".equals(optString) || "0001".equals(optString)) {
                                PingjiaDialogManager.show(activity, "02", optString2, "");
                            }
                            UIUtils.logD("积分领取onNext：" + jSONObject.optString("desc"));
                            return;
                        }
                        throw new RuntimeException("积分领取结果为空");
                    } catch (Exception e) {
                        e.printStackTrace();
                        UIUtils.logD("积分领取onNext：" + e.getMessage());
                        UIUtils.showCenterOnlyTextToast(activity, "活动太火爆，请稍后再试。", 0);
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    UIUtils.logD("积分领取错误：" + th.getMessage());
                    UIUtils.showCenterOnlyTextToast(activity, "活动太火爆，请稍后再试。", 0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.logD("积分领取错误：" + e.getMessage());
            UIUtils.showCenterOnlyTextToast(activity, "活动太火爆，请稍后再试。", 0);
        }
    }

    public static void showGoShiChangDialog(Activity activity, String str) {
        gradeRewardPicture(activity, str);
    }

    public static void gradeRewardGive(final Activity activity, final String str) {
        try {
            String defaultPhoneNumber = UserManager.getInstance().getDefaultPhoneNumber();
            Random random = new Random();
            String str2 = TimeUtil.timeStamp2Date(System.currentTimeMillis(), "yyyyMMddHHmmss") + "_" + (random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9)) + "_" + defaultPhoneNumber;
            HashMap hashMap = new HashMap();
            hashMap.put("seq", Base64.encodeToString(RSACryptos.encryptByPublicKey(str2.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCy0tZaZgUJqvoBfcVXoGuquEq3vaU8EDjRlqSE+YALleDp4VWvRQ1ME4u7PDAFlhnDxR8wd6QIXt1rXezGHleT8Zvr+mUdY3YtpstcEylk4cCuWMlDYPy30MzIj2s1ZSk1gFMY5vTCHBOYPiQpiSCAiqd5R41fD+a2uPNhYOhIeQIDAQAB", 2)), 2));
            hashMap.put("reqTime", String.valueOf(System.currentTimeMillis()));
            hashMap.put("deviceType", "1");
            hashMap.put("deviceModel", DeviceHelper.getDeviceBrand() + "_" + DeviceHelper.getDeviceBranD() + "_" + DeviceHelper.getDeviceModel());
            App.getAsyncHttpClient().post(URLSet.gradeRewardGive(), hashMap, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.8
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str3) {
                    super.onSuccess(i, str3);
                    if (str3 != null) {
                        try {
                            if (!TextUtils.isEmpty(str3)) {
                                JSONObject jSONObject = new JSONObject(str3);
                                if ("0000".equals(jSONObject.optString("code"))) {
                                    StatisticsUploadUtils.uploadPingJiaShiChang(activity, "sscpf0001", "应用市场评分弹窗", "评分弹窗", "展示弹窗", "去评价", str, PingjiaDialogManager.deviceModel, jSONObject.optJSONObject("data").optString("userstar"));
                                } else {
                                    StatisticsUploadUtils.uploadPingJiaShiChang(activity, "sscpf0001", "应用市场评分弹窗", "评分弹窗", "展示弹窗", "去评价", str, PingjiaDialogManager.deviceModel);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            MsLogUtil.m7980d(e.getMessage());
                        }
                    }
                    MsLogUtil.m7979d("激励弹窗确定", "onSuccess: " + str3);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str3) {
                    super.onFailure(th, str3);
                    MsLogUtil.m7979d("激励弹窗确定", "onFailure: " + str3);
                    StatisticsUploadUtils.uploadPingJiaShiChang(activity, "sscpf0001", "应用市场评分弹窗", "评分弹窗", "展示弹窗", "去评价", str, PingjiaDialogManager.deviceModel);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void gradeRewardClose(final Activity activity, final String str, final String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("deviceType", "1");
        App.getAsyncHttpClient().post(URLSet.gradeRewardClose(), hashMap, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.9
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str3) {
                super.onSuccess(i, str3);
                if (str3 != null) {
                    try {
                        if (!TextUtils.isEmpty(str3)) {
                            JSONObject jSONObject = new JSONObject(str3);
                            if ("0000".equals(jSONObject.optString("code"))) {
                                StatisticsUploadUtils.uploadPingJiaShiChang(activity, "sscpf0001", "应用市场评分弹窗", "评分弹窗", "展示弹窗", str2, str, PingjiaDialogManager.deviceModel, jSONObject.optJSONObject("data").optString("userstar"));
                            } else {
                                StatisticsUploadUtils.uploadPingJiaShiChang(activity, "sscpf0001", "应用市场评分弹窗", "评分弹窗", "展示弹窗", str2, str, PingjiaDialogManager.deviceModel);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        MsLogUtil.m7980d(e.getMessage());
                    }
                }
                MsLogUtil.m7979d("激励弹窗取消", "onSuccess: " + str3);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str3) {
                super.onFailure(th, str3);
                MsLogUtil.m7979d("激励弹窗取消", "onFailure: " + str3);
                StatisticsUploadUtils.uploadPingJiaShiChang(activity, "sscpf0001", "应用市场评分弹窗", "评分弹窗", "展示弹窗", str2, str, PingjiaDialogManager.deviceModel);
            }
        });
    }

    private static void gradeRewardPicture(final Activity activity, final String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("yw_code", UserManager.getInstance().getYwCode(UserManager.getInstance().getCurrentPhoneNumber()));
        hashMap.put("brand", DeviceHelper.getDeviceBrand());
        App.getAsyncHttpClient().post(URLSet.gradeRewardPicture(), hashMap, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.10
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str2) {
                super.onSuccess(i, str2);
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        JSONObject jSONObject = new JSONObject(str2);
                        if ("0000".equals(jSONObject.optString("code"))) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            if (optJSONObject != null) {
                                String optString = optJSONObject.optString("imgSrc");
                                if (TextUtils.isEmpty(optString)) {
                                    UIUtils.toast("我的百倍用心，愿您十分满意");
                                    return;
                                }
                                String[] split = optString.split(",");
                                if (split.length >= 3) {
                                    String str3 = split[0];
                                    String str4 = split[1];
                                    String str5 = split[2];
                                    if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str5)) {
                                        PingjiaDialogManager.createCustomDialog(activity, str, str3, str4, str5);
                                    }
                                    UIUtils.toast("我的百倍用心，愿您十分满意");
                                    return;
                                }
                                UIUtils.toast("我的百倍用心，愿您十分满意");
                            } else {
                                UIUtils.toast("我的百倍用心，愿您十分满意");
                            }
                        } else {
                            UIUtils.toast("我的百倍用心，愿您十分满意");
                        }
                    } else {
                        UIUtils.toast("我的百倍用心，愿您十分满意");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MsLogUtil.m7980d(e.getMessage());
                }
                MsLogUtil.m7979d("弹窗背景请求", "onSuccess: " + str2);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str2) {
                super.onFailure(th, str2);
                MsLogUtil.m7979d("弹窗背景请求", "onFailure: " + str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void createCustomDialog(final Activity activity, final String str, String str2, String str3, String str4) {
        final Dialog dialog = new Dialog(activity, 2131951850);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = (int) (screenWidth * 0.6d);
        attributes.height = -2;
        Window window = dialog.getWindow();
        window.setAttributes(attributes);
        window.setDimAmount(0.6f);
        window.addFlags(2);
        View inflate = activity.getLayoutInflater().inflate(2131493151, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(2131297080);
        ImageView imageView2 = (ImageView) inflate.findViewById(2131298275);
        ImageView imageView3 = (ImageView) inflate.findViewById(2131298620);
        ImageView imageView4 = (ImageView) inflate.findViewById(2131296678);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            GlideApp.with(activity).load(str2).into(imageView3);
            GlideApp.with(activity).load(str3).into(imageView);
            GlideApp.with(activity).load(str4).into(imageView2);
        }
        deviceModel = DeviceHelper.getDeviceBrand() + "_" + DeviceHelper.getDeviceBranD() + "_" + DeviceHelper.getDeviceModel();
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                PingjiaDialogManager.gradeRewardGive(activity, str);
                PingjiaDialogManager.jumpAppMarket(activity);
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                PingjiaDialogManager.gradeRewardClose(activity, str, "残忍拒绝");
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                PingjiaDialogManager.gradeRewardClose(activity, str, "关闭按钮");
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setLayout((int) (screenWidth * 0.8d), -2);
    }

    private static void createDefaultDialog(final Activity activity, final String str) {
        final Dialog dialog = new Dialog(activity, 2131951850);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = (int) (screenWidth * 0.6d);
        attributes.height = -2;
        Window window = dialog.getWindow();
        window.setAttributes(attributes);
        window.setDimAmount(0.6f);
        window.addFlags(2);
        View inflate = activity.getLayoutInflater().inflate(2131493151, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(2131298620);
        deviceModel = DeviceHelper.getDeviceBrand() + "_" + DeviceHelper.getDeviceBranD() + "_" + DeviceHelper.getDeviceModel();
        ((ImageView) inflate.findViewById(2131297080)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                PingjiaDialogManager.gradeRewardGive(activity, str);
                PingjiaDialogManager.jumpAppMarket(activity);
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        ((ImageView) inflate.findViewById(2131298275)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                PingjiaDialogManager.gradeRewardClose(activity, str, "残忍拒绝");
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        ((ImageView) inflate.findViewById(2131296678)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                PingjiaDialogManager.gradeRewardClose(activity, str, "关闭按钮");
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setLayout((int) (screenWidth * 0.8d), -2);
    }
}
