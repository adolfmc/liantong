package com.sinovatech.unicom.basic.p315ui.share;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.PopupWindow;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.p315ui.share.ShareCreateImg;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.p315ui.share.WebMenuManager;
import com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.common.ScreenShotUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.share.ManagerShare */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerShare {
    private AppCompatActivity activityContext;
    private View moreImage;
    private PopupWindow popupWindow;
    private HashMap<String, String> thirdUrlMap = new HashMap<>();

    /* renamed from: wv */
    private PBWebView f18427wv;

    public ManagerShare(AppCompatActivity appCompatActivity, PBWebView pBWebView) {
        this.f18427wv = pBWebView;
        this.activityContext = appCompatActivity;
    }

    public void showWindow(View view) {
        this.moreImage = view;
        this.f18427wv.loadURL("javascript:try{var result = getMenuConfig_Local();js_invoke.handleMenuConfig(result);}catch(err){js_invoke.handleMenuConfig(err);}");
    }

    public void handlerConfig(String str, List<WebMenuManager.WebMenuEntity> list) {
        try {
            if (TextUtils.isEmpty(str)) {
                String url = this.f18427wv.getUrl();
                if (!TextUtils.isEmpty(url) && url.contains("?")) {
                    String[] split = url.split("\\?");
                    if (split.length > 0) {
                        url = split[0];
                    }
                }
                str = this.thirdUrlMap.get(url);
            }
            List<WebMenuManager.WebMenuEntity> parseWebMenuJsonData = WebMenuManager.parseWebMenuJsonData(str, this.f18427wv.getTitle());
            if (parseWebMenuJsonData == null) {
                return;
            }
            parseWebMenuJsonData.addAll(list);
            WebMoreFunctionView webMoreFunctionView = new WebMoreFunctionView(this.activityContext, "", new WebMoreFunctionView.Listener() { // from class: com.sinovatech.unicom.basic.ui.share.ManagerShare.1
                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onBackHome() {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void refresh() {
                    try {
                        if (ManagerShare.this.f18427wv == null) {
                            return;
                        }
                        ManagerShare.this.f18427wv.reload();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onClick() {
                    ManagerShare.this.popupWindow.dismiss();
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void tiaozhuan(String str2) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("desmobile", UserManager.getInstance().getPassBackDesmobile());
                    hashMap.put("version", ManagerShare.this.activityContext.getString(2131886969));
                    ManagerShare.this.f18427wv.get(str2, hashMap);
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onShareComplete(String str2) {
                    ManagerShare.this.setShareStatusToServer("success", str2);
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onShareCancle(String str2) {
                    ManagerShare.this.setShareStatusToServer("fail", str2);
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onShareError(String str2, String str3) {
                    try {
                        if (!TextUtils.isEmpty(str3)) {
                            int stringRes = ResHelper.getStringRes(ManagerShare.this.activityContext, str3);
                            if (stringRes > 0) {
                                UIUtils.toastLong(ManagerShare.this.activityContext.getResources().getString(stringRes));
                            } else {
                                UIUtils.toastLong(str3);
                            }
                        } else {
                            UIUtils.toastLong("分享失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ManagerShare.this.setShareStatusToServer("fail", str2);
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onMenu() {
                    try {
                        if (ManagerShare.this.popupWindow == null || !ManagerShare.this.popupWindow.isShowing()) {
                            return;
                        }
                        ManagerShare.this.popupWindow.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void doCaptureWebview(String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, final WebMoreFunctionView.CaptureCallback captureCallback) {
                    MsLogUtil.m7979d("截图分享类型", "==" + str2);
                    if (str2.equals("longScreenshot")) {
                        PermissionDialog.show("为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
                        new RxPermissions(ManagerShare.this.activityContext).request("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.share.ManagerShare.1.1
                            @Override // io.reactivex.functions.Consumer
                            public void accept(Boolean bool) throws Exception {
                                PermissionDialog.dimissDialog();
                                MsLogUtil.m7979d("截图分享权限", "==" + bool);
                                if (bool.booleanValue()) {
                                    try {
                                        ShareCreateImg.createImg(ManagerShare.this.activityContext, ManagerShare.this.f18427wv, str5, str4, str6, str7, str8, str3, new ShareCreateImg.ShareImgOrScreenshot() { // from class: com.sinovatech.unicom.basic.ui.share.ManagerShare.1.1.1
                                            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareCreateImg.ShareImgOrScreenshot
                                            public void onSuccess() {
                                                captureCallback.captureFinish();
                                            }
                                        });
                                        return;
                                    } catch (Exception e) {
                                        UIUtils.logE(e.getMessage());
                                        return;
                                    }
                                }
                                UIUtils.toast("未开启存储卡权限");
                            }
                        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.share.ManagerShare.1.2
                            @Override // io.reactivex.functions.Consumer
                            public void accept(Throwable th) throws Exception {
                                PermissionDialog.dimissDialog();
                            }
                        });
                    }
                }
            });
            this.popupWindow = new PopupWindow(this.activityContext);
            this.popupWindow.setAnimationStyle(2131952229);
            this.popupWindow.setFocusable(true);
            this.popupWindow.setOutsideTouchable(true);
            this.popupWindow.setBackgroundDrawable(new ColorDrawable(0));
            this.popupWindow.setWidth(UIUtils.dip2px(this.activityContext, 160.0f));
            if (parseWebMenuJsonData.size() > 2) {
                this.popupWindow.setHeight(((UIUtils.dip2px(this.activityContext, 50.0f) + 1) * parseWebMenuJsonData.size()) + UIUtils.dip2px(this.activityContext, 10.0f));
            } else {
                this.popupWindow.setHeight((UIUtils.dip2px(this.activityContext, 50.0f) + 1) * parseWebMenuJsonData.size());
            }
            this.popupWindow.setContentView(webMoreFunctionView.getContentView(parseWebMenuJsonData));
            this.popupWindow.showAsDropDown(this.moreImage);
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
    }

    public void getThirdShareData() {
        final String url = this.f18427wv.getUrl();
        if (!TextUtils.isEmpty(url) && url.contains("?")) {
            String[] split = url.split("\\?");
            if (split.length > 0) {
                url = split[0];
            }
        }
        try {
            if (!url.contains(URLSet.ApplicationServer_URL) && !this.thirdUrlMap.containsKey(url)) {
                RequestParams requestParams = new RequestParams();
                requestParams.put("thirdUrl", url);
                App.getAsyncHttpClient().post(URLSet.getThirdshare_url(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.share.ManagerShare.2
                    @Override // com.loopj.android.http.AsyncHttpResponseHandler
                    public void onSuccess(int i, String str) {
                        super.onSuccess(i, str);
                        ManagerShare.this.thirdUrlMap.put(url, str);
                    }

                    @Override // com.loopj.android.http.AsyncHttpResponseHandler
                    public void onFailure(Throwable th, String str) {
                        super.onFailure(th, str);
                    }

                    @Override // com.loopj.android.http.AsyncHttpResponseHandler
                    public void onFinish() {
                        super.onFinish();
                    }
                });
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setShareStatusToServer(String str, String str2) {
        try {
            String str3 = "{\"status\":\"" + str + "\",\"target\":\"" + str2 + "\",\"token\":\"" + UserManager.getInstance().getUserToken() + "\"}";
            this.f18427wv.loadURL("javascript:try{setShareStatus_Local('" + str3 + "');}catch(err){}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.sinovatech.unicom.basic.ui.share.ManagerShare$3] */
    public void createBitmap(final WebMoreFunctionView.CaptureCallback captureCallback, final Bitmap bitmap) {
        new AsyncTask<String, Integer, String>() { // from class: com.sinovatech.unicom.basic.ui.share.ManagerShare.3

            /* renamed from: b */
            Bitmap f18428b;

            /* renamed from: pd */
            CustomePorgressDialog f18429pd;

            @Override // android.os.AsyncTask
            protected void onPreExecute() {
                super.onPreExecute();
                try {
                    this.f18429pd = new CustomePorgressDialog(ManagerShare.this.activityContext);
                    this.f18429pd.setMessage("正在生成 请稍候");
                    this.f18429pd.setCanceledOnTouchOutside(false);
                    this.f18429pd.setCancelable(false);
                    this.f18429pd.show();
                    this.f18428b = ScreenShotUtils.createSnapshotWithBottomLogo(ManagerShare.this.activityContext, ManagerShare.this.f18427wv, bitmap, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(String... strArr) {
                ScreenShotUtils.SaveBitmapToFile(ManagerShare.this.activityContext, this.f18428b);
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String str) {
                super.onPostExecute((AsyncTaskC78783) str);
                this.f18429pd.dismiss();
                captureCallback.captureFinish();
            }
        }.execute(new String[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.ManagerShare$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C78794 implements ShareManager.ShareListener {
        final /* synthetic */ String val$shareContent;

        C78794(String str) {
            this.val$shareContent = str;
        }

        @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
        public void onError(String str, String str2) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    int stringRes = ResHelper.getStringRes(ManagerShare.this.activityContext, str2);
                    if (stringRes > 0) {
                        UIUtils.toastLong(ManagerShare.this.activityContext.getResources().getString(stringRes));
                    } else {
                        UIUtils.toastLong(str2);
                    }
                } else {
                    UIUtils.toastLong("分享失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ManagerShare.this.setShareStatusToServer("fail", str);
        }

        @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
        public void onComplete(String str) {
            if (str.equals("jietufenxiang")) {
                final WebMoreFunctionView.CaptureCallback captureCallback = new WebMoreFunctionView.CaptureCallback() { // from class: com.sinovatech.unicom.basic.ui.share.ManagerShare.4.1
                    @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.CaptureCallback
                    public void captureFinish() {
                        try {
                            JSONObject jSONObject = new JSONObject(C78794.this.val$shareContent);
                            jSONObject.put("shareType", "longScreenshot");
                            ShareManager.ShowShareDialog(ManagerShare.this.activityContext, "wechat,wechatmoments,qq,qzone", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), new ShareManager.ShareListener() { // from class: com.sinovatech.unicom.basic.ui.share.ManagerShare.4.1.1
                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onComplete(String str2) {
                                    ManagerShare.this.setShareStatusToServer("success", str2);
                                }

                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onCancel(String str2) {
                                    ManagerShare.this.setShareStatusToServer("fail", str2);
                                }

                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onError(String str2, String str3) {
                                    try {
                                        if (!TextUtils.isEmpty(str3)) {
                                            int stringRes = ResHelper.getStringRes(ManagerShare.this.activityContext, str3);
                                            if (stringRes > 0) {
                                                UIUtils.toastLong(ManagerShare.this.activityContext.getResources().getString(stringRes));
                                            } else {
                                                UIUtils.toastLong(str3);
                                            }
                                        } else {
                                            UIUtils.toastLong("分享失败");
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    ManagerShare.this.setShareStatusToServer("fail", str2);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                try {
                    String optString = new JSONObject(this.val$shareContent).optString("shareQrcodeURL");
                    if (TextUtils.isEmpty(optString)) {
                        ManagerShare.this.createBitmap(captureCallback, BitmapFactory.decodeResource(ManagerShare.this.activityContext.getResources(), 2131231921));
                    } else {
                        Glide.with((FragmentActivity) ManagerShare.this.activityContext).asBitmap().load(optString).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.share.ManagerShare.4.2
                            @Override // com.bumptech.glide.request.target.Target
                            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                            }

                            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                ManagerShare.this.createBitmap(captureCallback, bitmap);
                            }

                            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                            public void onLoadFailed(@Nullable Drawable drawable) {
                                super.onLoadFailed(drawable);
                                ManagerShare.this.createBitmap(captureCallback, BitmapFactory.decodeResource(ManagerShare.this.activityContext.getResources(), 2131231921));
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ManagerShare.this.setShareStatusToServer("success", str);
        }

        @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
        public void onCancel(String str) {
            ManagerShare.this.setShareStatusToServer("fail", str);
        }
    }

    public void showShareDialog(String str, String str2) {
        ShareManager.ShowShareDialog(this.activityContext, this.f18427wv, str, str2, new C78794(str2));
    }
}
