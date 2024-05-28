package com.sinovatech.unicom.separatemodule.skin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundTongYongBean;
import com.sinovatech.unicom.separatemodule.skin.event.RefreshSkinEvent;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BackgroundDetailctivity extends BaseActivity {
    private static final String TAG = "BackgroundDetailctivity";
    public NBSTraceUnit _nbs_trace;
    private ImageView mImBtnOk;
    private ImageButton mImgBack;
    private ImageView mImgDefault;
    private ImageView mImgSkinAfter;
    private ImageView mImgSkinFull;
    private ImageView mImgTopPic;
    private TextView mTvSkinContent;
    private TextView mTvSkindesc;
    private BackgroundSkinBean skinBean;
    private BackgroundTongYongBean tongYongBean;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 113);
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

    private void initView() {
        this.mImgBack = (ImageButton) findViewById(2131296473);
        this.mImgTopPic = (ImageView) findViewById(2131296836);
        this.mTvSkinContent = (TextView) findViewById(2131296842);
        this.mTvSkindesc = (TextView) findViewById(2131296835);
        this.mImgDefault = (ImageView) findViewById(2131296837);
        this.mImgSkinAfter = (ImageView) findViewById(2131296839);
        this.mImgSkinFull = (ImageView) findViewById(2131296838);
        this.mImBtnOk = (ImageView) findViewById(2131296834);
    }

    private void initData() {
        try {
            Observable.create(new ObservableOnSubscribe<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundDetailctivity.3
                @Override // io.reactivex.ObservableOnSubscribe
                public void subscribe(@NonNull ObservableEmitter<Boolean> observableEmitter) throws Exception {
                    Intent intent = BackgroundDetailctivity.this.getIntent();
                    if (intent != null) {
                        BackgroundDetailctivity.this.skinBean = (BackgroundSkinBean) intent.getParcelableExtra("skinBean");
                        BackgroundDetailctivity.this.tongYongBean = (BackgroundTongYongBean) intent.getParcelableExtra("tongYongBean");
                    }
                    observableEmitter.onNext(true);
                    observableEmitter.onComplete();
                }
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundDetailctivity.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    if (BackgroundDetailctivity.this.skinBean != null) {
                        BackgroundDetailctivity.this.mTvSkinContent.setText(BackgroundDetailctivity.this.skinBean.getProductSubtitle());
                        BackgroundDetailctivity.this.mTvSkindesc.setText(BackgroundDetailctivity.this.skinBean.getProductDesc());
                        BackgroundDetailctivity backgroundDetailctivity = BackgroundDetailctivity.this;
                        ShowImageUtils.showImageView(backgroundDetailctivity, backgroundDetailctivity.skinBean.getProductImgUrl(), BackgroundDetailctivity.this.mImgTopPic);
                        BackgroundDetailctivity backgroundDetailctivity2 = BackgroundDetailctivity.this;
                        ShowImageUtils.showImageView(backgroundDetailctivity2, backgroundDetailctivity2.skinBean.getProductImgUrl(), BackgroundDetailctivity.this.mImgSkinAfter);
                    }
                    if (BackgroundDetailctivity.this.tongYongBean != null) {
                        BackgroundDetailctivity backgroundDetailctivity3 = BackgroundDetailctivity.this;
                        ShowImageUtils.showImageView(backgroundDetailctivity3, backgroundDetailctivity3.tongYongBean.getDefaultPicLink(), 2131230985, BackgroundDetailctivity.this.mImgDefault);
                        BackgroundDetailctivity backgroundDetailctivity4 = BackgroundDetailctivity.this;
                        ShowImageUtils.showImageView(backgroundDetailctivity4, backgroundDetailctivity4.tongYongBean.getPreviewPicLink(), 2131230984, BackgroundDetailctivity.this.mImgSkinFull);
                        return;
                    }
                    BackgroundDetailctivity backgroundDetailctivity5 = BackgroundDetailctivity.this;
                    ShowImageUtils.showImageView(backgroundDetailctivity5, 2131230985, backgroundDetailctivity5.mImgDefault);
                    BackgroundDetailctivity backgroundDetailctivity6 = BackgroundDetailctivity.this;
                    ShowImageUtils.showImageView(backgroundDetailctivity6, 2131230984, backgroundDetailctivity6.mImgSkinFull);
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundDetailctivity.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "皮肤详情页初始化数据异常:" + e.getMessage());
        }
    }

    private void initListener() {
        this.mImgBack.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundDetailctivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                BackgroundDetailctivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mImBtnOk.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundDetailctivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (!App.hasLogined()) {
                    BackgroundDetailctivity.this.showLoginOutDialog();
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                BackgroundDetailctivity.this.showChangeDialog();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    public static void startNewIntent(Activity activity, BackgroundSkinBean backgroundSkinBean, BackgroundTongYongBean backgroundTongYongBean) {
        try {
            Intent intent = new Intent(activity, BackgroundDetailctivity.class);
            intent.putExtra("skinBean", backgroundSkinBean);
            intent.putExtra("tongYongBean", backgroundTongYongBean);
            activity.startActivity(intent);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "跳转页面异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showChangeDialog() {
        try {
            CustomDialogManager.showSkinDialog(this, "您将更换首页皮肤，除活动、生日等情况，首页将使用您所选中的皮肤。", true, "取消", "确认更换", true, new CustomDialogManager.SimpleCustomeDialogListener2() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundDetailctivity.6
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener2
                public void onCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener2
                public void onClickOk() {
                    if (!App.hasLogined()) {
                        BackgroundDetailctivity.this.showLoginOutDialog();
                        return;
                    }
                    CacheDataCenter.getInstance().setCustomSkin(BackgroundDetailctivity.this.skinBean);
                    EventBusUtils.post(new RefreshSkinEvent(0));
                    if (TextUtils.equals("custom_group", BackgroundDetailctivity.this.skinBean.getShowType())) {
                        PvCurrencyLogUtils.pvLogDJ("皮肤预览页", "S2ndpage1178", "", "", BackgroundDetailctivity.this.skinBean.getGroupName() + "", "立即体验", "确认更换", "", "");
                    } else {
                        PvCurrencyLogUtils.pvLogDJ("皮肤预览页", "S2ndpage1178", "", "", BackgroundDetailctivity.this.skinBean.getProductName() + "", "立即体验", "确认更换", BackgroundDetailctivity.this.skinBean.getProductImgUrl() + "", BackgroundDetailctivity.this.skinBean.getProductId() + "");
                    }
                    BackgroundDetailctivity.this.finish();
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "切换皮肤弹窗异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoginOutDialog() {
        CustomDialogManager.show((Activity) this, "温馨提示", "您的号码在别处登录，本次操作将不会保存", false, "取消", "确认", true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundDetailctivity.7
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }
        });
    }
}
