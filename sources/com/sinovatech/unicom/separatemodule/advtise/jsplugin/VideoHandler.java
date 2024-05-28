package com.sinovatech.unicom.separatemodule.advtise.jsplugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebView;
import com.dueeeke.videoplayer.util.NetworkUtil;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.advtise.service.TaskModel;
import com.sinovatech.unicom.separatemodule.advtise.utils.ExtraUitls;
import com.sinovatech.unicom.separatemodule.chuanshanjia.SignUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.tms.SubmitCoinDialogManager;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import java.net.URLEncoder;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoHandler extends AbstractJsHandler {
    private Handler handler;
    private String orderId;

    public VideoHandler(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity, IAdInterface iAdInterface, BaseJSPlugin baseJSPlugin) {
        super(appCompatActivity, adConfigEntity, iAdInterface, baseJSPlugin);
        this.orderId = "";
        this.handler = new Handler();
    }

    public VideoHandler(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity, IAdInterface iAdInterface, WebView webView) {
        super(appCompatActivity, adConfigEntity, iAdInterface, webView);
        this.orderId = "";
        this.handler = new Handler();
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.jsplugin.AbstractJsHandler
    public void exec() {
        if (!App.hasLogined()) {
            this.activity.startActivity(new Intent(this.activity, LoginBindActivity.class));
            return;
        }
        this.orderId = SignUtils.getWebSign();
        this.entity.setOrderId(this.orderId);
        final String extarString = ExtraUitls.getExtarString(this.entity);
        if (this.entity.isPrepareLoad()) {
            if (NetworkUtil.getNetworkType(this.activity) == 3) {
                cacheVido(extarString);
                return;
            } else {
                callbackSuccess("onError", "缓存数据失败");
                return;
            }
        }
        pdShow();
        UIUtils.logD("videoHandler", "---" + this.entity.getOrderId());
        final TaskModel taskModel = new TaskModel(this.entity);
        taskModel.getTaskInfo(new TaskModel.ITaskQueryInterface() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.-$$Lambda$VideoHandler$YvX9tUxzwM74nd4nfCYI9Y1m--k
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.TaskModel.ITaskQueryInterface
            public final void onComplete(TaskModel.TaskQueryBean taskQueryBean) {
                VideoHandler.lambda$exec$5(VideoHandler.this, extarString, taskModel, taskQueryBean);
            }
        });
    }

    public static /* synthetic */ void lambda$exec$5(final VideoHandler videoHandler, final String str, final TaskModel taskModel, TaskModel.TaskQueryBean taskQueryBean) {
        if (taskQueryBean == null) {
            videoHandler.loadVideo(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.-$$Lambda$VideoHandler$WRS5O5IWpXC_hvWcq67JVNaMO3g
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoHandler.lambda$exec$0(VideoHandler.this, (String) obj);
                }
            });
        } else if ("0000".equals(taskQueryBean.getCode()) && !"1".equals(taskQueryBean.getTimeFlag())) {
            videoHandler.loadVideo(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.-$$Lambda$VideoHandler$ksJVW84uk1sfbAkQHMFoi7J-jno
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoHandler videoHandler2 = VideoHandler.this;
                    new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.-$$Lambda$VideoHandler$AXu1HWSq7FSse7I3oK0i2N-qSMM
                        @Override // java.lang.Runnable
                        public final void run() {
                            r2.taskComplete(r3, new TaskModel.ITaskSendInterface() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.-$$Lambda$VideoHandler$3Mh7-R7OTgdsmEZpzgVKVxNhMso
                                @Override // com.sinovatech.unicom.separatemodule.advtise.service.TaskModel.ITaskSendInterface
                                public final void onComplete(TaskModel.TaskSendBean taskSendBean) {
                                    VideoHandler.lambda$exec$1(VideoHandler.this, taskSendBean);
                                }
                            });
                        }
                    }, videoHandler2.entity.getWaitTime());
                }
            });
        } else {
            videoHandler.pdDissmiss();
            CustomDialogManager.show((Activity) videoHandler.activity, "", taskQueryBean.getDesc(), true, "狠心离开", "继续观看", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.-$$Lambda$VideoHandler$lhCsVIMnfHlUloIQWkuqrHA6Dys
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public final void onClickOk() {
                    VideoHandler.this.loadVideo(str).subscribe();
                }
            });
        }
    }

    public static /* synthetic */ void lambda$exec$0(VideoHandler videoHandler, String str) throws Exception {
        if (videoHandler.entity.isUnWantedToast2()) {
            return;
        }
        UIUtils.toastCenter("视频播放结束，请关闭当前页面查看奖励！");
    }

    public static /* synthetic */ void lambda$exec$1(VideoHandler videoHandler, TaskModel.TaskSendBean taskSendBean) {
        if ("0000".equals(taskSendBean.getCode())) {
            if (!videoHandler.entity.isUnWantedToast()) {
                int parseInt = Integer.parseInt(taskSendBean.getPrizeCount());
                Activity topActivity = SoulPermission.getInstance().getTopActivity();
                if (topActivity != null) {
                    new SubmitCoinDialogManager(topActivity).show(parseInt);
                } else {
                    UIUtils.toastCenter("+" + taskSendBean.getPrizeCount() + "积分");
                }
            } else if (!videoHandler.entity.isUnWantedToast2()) {
                UIUtils.toastCenter("视频播放结束，请关闭当前页面查看积分！");
            }
            videoHandler.entity.setNum(taskSendBean.getPrizeCount());
        } else if (videoHandler.entity.isUnWantedToast()) {
        } else {
            UIUtils.toastCenter(taskSendBean.getDesc());
        }
    }

    private void cacheVido(String str) {
        this.adInterface.loadVido(str, this.orderId, new IAdInterface.IVideoCallBack() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.-$$Lambda$VideoHandler$lnu-qUkG0EUUyB8AEA6poRtnZbs
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IVideoCallBack
            public final void onResult(int i, String str2) {
                VideoHandler.lambda$cacheVido$6(VideoHandler.this, i, str2);
            }
        });
    }

    public static /* synthetic */ void lambda$cacheVido$6(VideoHandler videoHandler, int i, String str) {
        if (i != 14 || videoHandler.entity.isOldVersion()) {
            return;
        }
        videoHandler.callbackSuccess("onCache", str);
    }

    private Observable<String> loadVideo(final String str) {
        final PublishSubject create = PublishSubject.create();
        return create.doOnSubscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.-$$Lambda$VideoHandler$Em9FcqdK2fc2yUfjDvXRcyGDrss
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Disposable disposable = (Disposable) obj;
                VideoHandler.this.loadAd(str, create);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAd(final String str, final PublishSubject<String> publishSubject) {
        pdShow();
        this.adInterface.loadVido(str, this.orderId, new IAdInterface.IVideoCallBack() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.-$$Lambda$VideoHandler$tNOQRSekXhq-uJeTLuxkFBdPgcY
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IVideoCallBack
            public final void onResult(int i, String str2) {
                VideoHandler.lambda$loadAd$8(VideoHandler.this, publishSubject, str, i, str2);
            }
        });
    }

    public static /* synthetic */ void lambda$loadAd$8(VideoHandler videoHandler, PublishSubject publishSubject, String str, int i, String str2) {
        videoHandler.pdDissmiss();
        switch (i) {
            case 12:
                if ("1".equals(videoHandler.entity.getSubFlag()) && !TextUtils.isEmpty(videoHandler.entity.getSubAdType()) && !TextUtils.isEmpty(videoHandler.entity.getSubCodeId()) && !videoHandler.entity.isPrepareLoad()) {
                    videoHandler.entity.setSubFlag("0");
                    videoHandler.entity.setAdType(videoHandler.entity.getSubAdType());
                    videoHandler.entity.setCodeId(videoHandler.entity.getSubCodeId());
                    videoHandler.adInterface = AdFactory.getAd(videoHandler.activity, videoHandler.entity);
                    videoHandler.loadAd(str, publishSubject);
                    return;
                } else if (videoHandler.entity.isOldVersion()) {
                    String str3 = "{\"status\":\"failed\",\"errorMessage\":\"" + str2 + "\"}";
                    try {
                        str3 = URLEncoder.encode(str3, "UTF-8");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    WebView webView = videoHandler.webView;
                    String str4 = "javascript:UnicomCSJCoinAd.showToutiaoVideoAd.onError(" + str3 + ")";
                    if (webView instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) webView, str4);
                        return;
                    } else {
                        webView.loadUrl(str4);
                        return;
                    }
                } else {
                    videoHandler.callbackSuccess("onError", str2);
                    return;
                }
            case 13:
                if (videoHandler.entity.isOldVersion()) {
                    WebView webView2 = videoHandler.webView;
                    if (webView2 instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) webView2, "javascript:UnicomCSJCoinAd.showToutiaoVideoAd.onRewardVerify(true)");
                    } else {
                        webView2.loadUrl("javascript:UnicomCSJCoinAd.showToutiaoVideoAd.onRewardVerify(true)");
                    }
                } else {
                    videoHandler.callbackSuccess("onRewardVerify", str2);
                }
                videoHandler.entity.setOrderId(str2);
                publishSubject.onNext(str2);
                publishSubject.onComplete();
                return;
            case 14:
                if (videoHandler.entity.isOldVersion()) {
                    return;
                }
                videoHandler.callbackSuccess("onCache", str2);
                return;
            case 15:
                if (videoHandler.entity.isOldVersion()) {
                    String str5 = "javascript:UnicomCSJCoinAd.showToutiaoVideoAd.onComplete(" + videoHandler.entity.getNum() + ",'" + videoHandler.entity.getOrderId() + "')";
                    WebView webView3 = videoHandler.webView;
                    if (webView3 instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) webView3, str5);
                        return;
                    } else {
                        webView3.loadUrl(str5);
                        return;
                    }
                }
                videoHandler.callbackSuccess("onComplete", videoHandler.entity.getNum());
                return;
            default:
                return;
        }
    }

    private void callbackSuccess(final String str, final String str2) {
        this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.-$$Lambda$VideoHandler$anhjIIpS2a3Z6rYkccNj6oexEZk
            @Override // java.lang.Runnable
            public final void run() {
                VideoHandler.lambda$callbackSuccess$9(VideoHandler.this, str, str2);
            }
        });
    }

    public static /* synthetic */ void lambda$callbackSuccess$9(VideoHandler videoHandler, String str, String str2) {
        if (videoHandler.entity.isOldVersion()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", str);
            jSONObject.put("info", str2);
            jSONObject.put("orderId", videoHandler.entity.getOrderId());
            UIUtils.logD("videoHandler", "---" + videoHandler.entity.getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        videoHandler.jsPlugin.callbackSuccess(jSONObject);
    }

    private void pdDissmiss() {
        try {
            if (this.activity == null || this.activity.isDestroyed() || this.activity.isFinishing() || this.f18456pd == null || !this.f18456pd.isShowing()) {
                return;
            }
            this.f18456pd.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pdShow() {
        try {
            if (this.activity == null || this.activity.isDestroyed() || this.activity.isFinishing() || this.f18456pd == null || this.f18456pd.isShowing()) {
                return;
            }
            this.f18456pd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
