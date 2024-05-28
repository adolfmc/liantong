package com.sinovatech.unicom.basic.p315ui.activity;

import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerWelcome;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.bean.SplashAdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.AdvertiseActivty */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AdvertiseActivty extends AppCompatActivity {
    private static final String TAG = "WelcomeClient222";
    public NBSTraceUnit _nbs_trace;
    public long adDataStartRequestTime;
    public long adFinishTime;
    public long adStartShowTime;
    public long adUrlStartRequestTime;
    public long appOnCreateTime;
    private Disposable countDownSubscribe;
    private AppCompatActivity context = this;
    private boolean pictureIsReady = false;

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

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        this.appOnCreateTime = System.currentTimeMillis();
        MsLogUtil.m7979d(TAG, "+++++++++启动日志：是否冷启动：2");
        MsLogUtil.m7979d(TAG, "+++++++++启动日志：启动时间戳：" + this.appOnCreateTime);
        setContentView(2131493556);
        UIUtils.setFullScreenAndHideStateBar(this);
        this.pictureIsReady = false;
        final FrameLayout frameLayout = (FrameLayout) findViewById(2131298650);
        frameLayout.setBackgroundResource(2131232646);
        this.countDownSubscribe = ((ObservableSubscribeProxy) Observable.timer(5000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.context))).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.basic.ui.activity.AdvertiseActivty.1
            {
                AdvertiseActivty.this = this;
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Long l) throws Exception {
                if (AdvertiseActivty.this.pictureIsReady) {
                    return;
                }
                AdvertiseActivty.this.goMain();
            }
        });
        this.adUrlStartRequestTime = System.currentTimeMillis() - this.appOnCreateTime;
        MsLogUtil.m7979d(TAG, "+++++++++启动日志：广告url开始请求：" + this.adUrlStartRequestTime);
        new ManagerWelcome(this.context).loadAdvertise().subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$AdvertiseActivty$290WyYA9LzYa2aiXxdBsG5dHJkw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AdvertiseActivty.lambda$onCreate$2(AdvertiseActivty.this, frameLayout, (SplashAdConfigEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$AdvertiseActivty$Y7-Ruv1zgUL8GZ81Okg1QRnHJi8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                AdvertiseActivty.this.goMain();
            }
        });
        NBSAppInstrumentation.activityCreateEndIns();
    }

    public static /* synthetic */ void lambda$onCreate$2(AdvertiseActivty advertiseActivty, final FrameLayout frameLayout, SplashAdConfigEntity splashAdConfigEntity) throws Exception {
        AdConfigEntity successConfigEntity = splashAdConfigEntity.getSuccessConfigEntity();
        final AdConfigEntity failConfigEntity = splashAdConfigEntity.getFailConfigEntity();
        StatisticsUploadUtils.uploadRealTime(advertiseActivty.context, "kaipingShow", "启动页-广告", successConfigEntity.getWelcomeType(), successConfigEntity.getAdvertiseId(), successConfigEntity.getAdvertiseTitle(), successConfigEntity.getAdvertiseTargetURL());
        boolean z = !TextUtils.isEmpty(successConfigEntity.getAdType());
        final boolean z2 = !TextUtils.isEmpty(failConfigEntity.getAdType());
        if (!z) {
            advertiseActivty.goMain();
            return;
        }
        advertiseActivty.adDataStartRequestTime = System.currentTimeMillis() - advertiseActivty.appOnCreateTime;
        MsLogUtil.m7979d(TAG, "+++++++++启动日志：广告资源开始加载：" + advertiseActivty.adDataStartRequestTime);
        IAdInterface ad = AdFactory.getAd(advertiseActivty.context, successConfigEntity);
        advertiseActivty.getLifecycle().addObserver(ad);
        ad.loadSplash(frameLayout, 0L, new IAdInterface.ISplashAdCallBack() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$AdvertiseActivty$vm3qxqXTu6VZ8Ae_bVVBEgC9NrU
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.ISplashAdCallBack
            public final void onResult(int i, String str) {
                AdvertiseActivty.lambda$onCreate$1(AdvertiseActivty.this, z2, frameLayout, failConfigEntity, i, str);
            }
        }, new $$Lambda$AdvertiseActivty$vU_H3F4U2cJXtKWXHLh2VAC2B9g(advertiseActivty));
    }

    public static /* synthetic */ void lambda$onCreate$1(AdvertiseActivty advertiseActivty, boolean z, FrameLayout frameLayout, AdConfigEntity adConfigEntity, int i, String str) {
        if (i == 11) {
            StatisticsUploadUtils.uploadRealTime(advertiseActivty.context, "10", "启动页-广告", "广告", "", "", "error-try-1");
        }
        advertiseActivty.adStartShowTime = System.currentTimeMillis() - advertiseActivty.appOnCreateTime;
        MsLogUtil.m7979d(TAG, "+++++++++启动日志：广告开始展示：" + advertiseActivty.adStartShowTime);
        if (!z || i == 10) {
            advertiseActivty.handleAdView(i, str);
            return;
        }
        frameLayout.removeAllViews();
        IAdInterface ad = AdFactory.getAd(advertiseActivty.context, adConfigEntity);
        advertiseActivty.getLifecycle().addObserver(ad);
        ad.loadSplash(frameLayout, 0L, new IAdInterface.ISplashAdCallBack() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$AdvertiseActivty$dTZOsPAdEFtxk9MEmEFthgAMPMg
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.ISplashAdCallBack
            public final void onResult(int i2, String str2) {
                AdvertiseActivty.lambda$onCreate$0(AdvertiseActivty.this, i2, str2);
            }
        }, new $$Lambda$AdvertiseActivty$vU_H3F4U2cJXtKWXHLh2VAC2B9g(advertiseActivty));
    }

    public static /* synthetic */ void lambda$onCreate$0(AdvertiseActivty advertiseActivty, int i, String str) {
        if (i == 11) {
            StatisticsUploadUtils.uploadRealTime(advertiseActivty.context, "10", "启动页-广告", "广告", "", "", "error-try-2");
        }
        advertiseActivty.handleAdView(i, str);
    }

    private void handleAdView(int i, String str) {
        if (i == 11) {
            goMain();
            return;
        }
        this.pictureIsReady = true;
        findViewById(2131299603).setVisibility(0);
        TextView textView = (TextView) findViewById(2131298652);
        if (TextUtils.equals("UNIOCMPIC", str)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    public void goMain() {
        try {
            this.pictureIsReady = true;
            if (this.countDownSubscribe != null && !this.countDownSubscribe.isDisposed()) {
                this.countDownSubscribe.dispose();
            }
            this.adFinishTime = System.currentTimeMillis() - this.appOnCreateTime;
            TYCJBoxManager.getInstance().collectAppIn("1", this.appOnCreateTime + "", "0", "0", "0", this.adUrlStartRequestTime + "", this.adDataStartRequestTime + "", this.adStartShowTime + "", this.adFinishTime + "", System.currentTimeMillis() + "");
            if (!TextUtils.isEmpty(App.exterWelcomURL)) {
                IntentManager.generateIntentAndGo(this, App.exterWelcomURL, "", false, "get");
                App.exterWelcomURL = "";
            }
            this.context.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
