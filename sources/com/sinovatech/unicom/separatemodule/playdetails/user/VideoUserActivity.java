package com.sinovatech.unicom.separatemodule.playdetails.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.GuanZhuEntity;
import com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity;
import com.sinovatech.unicom.separatemodule.videocenter.entity.VideoUserEntity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoUserActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private String from;
    private ImageView mIvJbBack;
    private BridgeWebView mUserWeb;
    private String user_id;
    private String url = "";
    private String followings = "";

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 104);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    private void isFollowing(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        try {
            App.getAsyncHttpClient().rxPost(URLSet.isFollowing(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.user.VideoUserActivity.1
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str2) {
                    UIUtils.logD("zs作者主页是否关注" + str2);
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (TextUtils.equals(jSONObject.getString("statusCode"), "0000")) {
                            if (TextUtils.equals(jSONObject.optJSONObject("data").optString("following"), "true")) {
                                VideoUserActivity.this.followings = "1";
                            } else {
                                VideoUserActivity.this.followings = "0";
                            }
                            VideoUserActivity.this.mUserWeb.registerHandler("subscibeState", new BridgeHandler() { // from class: com.sinovatech.unicom.separatemodule.playdetails.user.VideoUserActivity.1.1
                                @Override // com.github.lzyzsd.jsbridge.BridgeHandler
                                public void handler(String str3, CallBackFunction callBackFunction) {
                                    callBackFunction.onCallBack(VideoUserActivity.this.followings);
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    UIUtils.toast(th.getMessage());
                    th.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerHandlers() {
        this.mUserWeb.registerHandler("triggerSubscibe", new BridgeHandler() { // from class: com.sinovatech.unicom.separatemodule.playdetails.user.VideoUserActivity.2
            @Override // com.github.lzyzsd.jsbridge.BridgeHandler
            public void handler(String str, CallBackFunction callBackFunction) {
                UIUtils.logD("zs", "作者主页点击关注" + str);
                if (TextUtils.equals(str, "true")) {
                    VideoUserActivity videoUserActivity = VideoUserActivity.this;
                    videoUserActivity.offGuanzhu(videoUserActivity.user_id);
                    callBackFunction.onCallBack("0");
                    return;
                }
                VideoUserActivity videoUserActivity2 = VideoUserActivity.this;
                videoUserActivity2.onGuanzhu(videoUserActivity2.user_id);
                callBackFunction.onCallBack("1");
            }
        });
        this.mUserWeb.registerHandler("playVideoPage", new BridgeHandler() { // from class: com.sinovatech.unicom.separatemodule.playdetails.user.VideoUserActivity.3
            @Override // com.github.lzyzsd.jsbridge.BridgeHandler
            public void handler(String str, CallBackFunction callBackFunction) {
                UIUtils.logD("zs", "作者主页点击跳转详情" + str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String optString = jSONObject.optString("group_id");
                    boolean optBoolean = jSONObject.optJSONObject("user_info").optBoolean("follow");
                    JSONArray optJSONArray = jSONObject.optJSONArray("cover_image_list");
                    String str2 = null;
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        str2 = optJSONArray.optJSONObject(i).optString("url");
                        UIUtils.logD("zs", "作者主页封面图" + str2);
                    }
                    Intent intent = new Intent(VideoUserActivity.this.activityContext, VideoDetailsActivity.class);
                    intent.putExtra("follow", optBoolean);
                    intent.putExtra("imageUrl", str2);
                    intent.putExtra("group_id_str", optString);
                    if (TextUtils.isEmpty(VideoUserActivity.this.from)) {
                        intent.putExtra("from", "videoUser");
                    }
                    VideoUserActivity.this.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initWebView() {
        this.mUserWeb = (BridgeWebView) findViewById(2131299454);
        this.mUserWeb.getSettings().setAllowFileAccess(true);
        this.mUserWeb.getSettings().setAppCacheEnabled(true);
        this.mUserWeb.getSettings().setDatabaseEnabled(true);
        this.mUserWeb.getSettings().setDomStorageEnabled(true);
        this.mUserWeb.getSettings().setJavaScriptEnabled(true);
        this.mUserWeb.getSettings().setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= 21) {
            this.mUserWeb.getSettings().setMixedContentMode(0);
        }
        WebSettings settings = this.mUserWeb.getSettings();
        settings.setUserAgentString(this.mUserWeb.getSettings().getUserAgentString() + "app");
        this.mUserWeb.setWebChromeClient(new WebChromeClient());
        BridgeWebView bridgeWebView = this.mUserWeb;
        bridgeWebView.setWebViewClient(new MyWebViewClient(bridgeWebView));
        BridgeWebView bridgeWebView2 = this.mUserWeb;
        String str = this.url;
        if (bridgeWebView2 instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) bridgeWebView2, str);
        } else {
            bridgeWebView2.loadUrl(str);
        }
    }

    private void initView() {
        this.mIvJbBack = (ImageView) findViewById(2131297412);
        this.mIvJbBack.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.user.VideoUserActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                VideoUserActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGuanzhu(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        try {
            App.getAsyncHttpClient().rxPost(URLSet.GuanzhuON(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.user.VideoUserActivity.5
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str2) {
                    Gson gson = new Gson();
                    GuanZhuEntity guanZhuEntity = (GuanZhuEntity) (!(gson instanceof Gson) ? gson.fromJson(str2, (Class<Object>) GuanZhuEntity.class) : NBSGsonInstrumentation.fromJson(gson, str2, (Class<Object>) GuanZhuEntity.class));
                    if (guanZhuEntity.getStatusCode().equals("0000")) {
                        GuanZhuEntity.DataDTO.UserDTO user = guanZhuEntity.getData().getUser();
                        VideoUserEntity videoUserEntity = new VideoUserEntity();
                        videoUserEntity.setSchema(user.getHome_page());
                        videoUserEntity.setUserId(user.getUser_id() + "");
                        videoUserEntity.setAvatarUrl(user.getAvatar_url());
                        videoUserEntity.setName(user.getName());
                        VideoCenterActivity.attentionUser(videoUserEntity, true);
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    UIUtils.toast("数据访问错误");
                    th.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void offGuanzhu(final String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        try {
            App.getAsyncHttpClient().rxPost(URLSet.GuanzhuOFF(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.user.VideoUserActivity.6
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str2) {
                    try {
                        if (new JSONObject(str2).getString("statusCode").equals("0000")) {
                            VideoUserEntity videoUserEntity = new VideoUserEntity();
                            videoUserEntity.setUserId(str);
                            VideoCenterActivity.attentionUser(videoUserEntity, false);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    UIUtils.toast(th.getMessage());
                    th.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
