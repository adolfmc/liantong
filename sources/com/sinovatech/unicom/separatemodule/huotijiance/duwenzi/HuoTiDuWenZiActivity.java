package com.sinovatech.unicom.separatemodule.huotijiance.duwenzi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bytedance.applog.tracker.Tracker;
import com.cjt2325.cameralibrary.CameraInterface;
import com.fort.andjni.JniLib;
import com.megvii.livenesslib.util.FileSizeUtil;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.huotijiance.HuoTiEntity;
import com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil;
import com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.MyWenZiJCameraView;
import com.sinovatech.unicom.separatemodule.huotijiance.util.FileUtils;
import com.sinovatech.unicom.separatemodule.huotijiance.view.LineProgressView;
import com.sinovatech.unicom.separatemodule.huotijiance.view.RecordButtonView;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HuoTiDuWenZiActivity extends BaseActivity {
    public static float MAX_VIDEO_TIME = 7000.0f;
    public static final float MIN_VIDEO_TIME = 2000.0f;
    private static final String TAG = "HuoTiDuWenZiActivity";
    public NBSTraceUnit _nbs_trace;
    private TextView ht_tishi_text;
    private HuoTiEntity huoTiEntity;
    private ImageView imageView;
    private LongRunble longRunble;
    private MyWenZiJCameraView mCustom_CarmeraView;
    private RecordButtonView mCustom_LuZhiView;
    private LineProgressView mCustom_ProgressView;
    private ImageView mImg_BottomBack;
    private ImageView mImg_SwitchCamera;
    private ImageView mImg_TopBack;
    private ImageView mImg_WanCheng;
    private RelativeLayout mRl_LuZhiLayout;
    private RelativeLayout mRl_TopLayout;
    private LinearLayout mRl_WanChengLayout;
    private TextView mTv_AnZhuPai;
    private TextView mTv_DaoJiShi;
    private TextView mTv_Number;
    private CountDownTimer timer;
    private Toast toast;
    private TextView tvDTime;
    private long recordTime = 0;
    private boolean isLuZhi = false;
    private int maxTime = 6;
    private int minTime = 1;
    private boolean isBackCamera = false;
    private boolean btnClick = true;
    private String videoUrl = "";
    private Handler handler = new Handler();
    private CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.8
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            try {
                String valueOf = String.valueOf((int) (j / 1000));
                HuoTiDuWenZiActivity.this.tvDTime.setTextSize(1, 96.0f);
                if ("4".equals(valueOf)) {
                    HuoTiDuWenZiActivity.this.tvDTime.setText("3");
                } else if ("3".equals(valueOf)) {
                    HuoTiDuWenZiActivity.this.tvDTime.setText("2");
                } else if ("2".equals(valueOf)) {
                    HuoTiDuWenZiActivity.this.tvDTime.setText("1");
                    if (HuoTiDuWenZiActivity.this.longRunble != null) {
                        HuoTiDuWenZiActivity.this.handler.postDelayed(HuoTiDuWenZiActivity.this.longRunble, 100L);
                    }
                } else if ("1".equals(valueOf)) {
                    HuoTiDuWenZiActivity.this.tvDTime.setTextSize(1, 61.0f);
                    HuoTiDuWenZiActivity.this.tvDTime.setText("开始");
                } else {
                    HuoTiDuWenZiActivity.this.tvDTime.setTextSize(1, 96.0f);
                    HuoTiDuWenZiActivity.this.tvDTime.setText("3");
                    HuoTiDuWenZiActivity.this.tvDTime.setVisibility(8);
                }
            } catch (Exception e) {
                MsLogUtil.m7980d(e.getMessage());
            }
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            HuoTiDuWenZiActivity.this.btnClick = true;
        }
    };
    private int countTime = 0;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 84);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
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
        this.longRunble = new LongRunble();
        try {
            this.maxTime = Integer.parseInt(this.huoTiEntity.getMaxDuration());
            MAX_VIDEO_TIME = Float.valueOf(this.maxTime).floatValue() * 1000.0f;
        } catch (Exception unused) {
            this.maxTime = 6;
        }
        try {
            this.minTime = Integer.parseInt(this.huoTiEntity.getMinDuration());
        } catch (Exception unused2) {
            this.minTime = 1;
        }
        this.ht_tishi_text = (TextView) findViewById(2131297233);
        HuoTiEntity huoTiEntity = this.huoTiEntity;
        if (huoTiEntity != null) {
            this.ht_tishi_text.setText(huoTiEntity.getHintText());
        }
        this.mRl_TopLayout = (RelativeLayout) findViewById(2131299519);
        this.mRl_LuZhiLayout = (RelativeLayout) findViewById(2131297229);
        this.mRl_WanChengLayout = (LinearLayout) findViewById(2131297235);
        this.mImg_TopBack = (ImageView) findViewById(2131297223);
        this.mImg_BottomBack = (ImageView) findViewById(2131297224);
        this.mImg_WanCheng = (ImageView) findViewById(2131297234);
        this.mImg_SwitchCamera = (ImageView) findViewById(2131297232);
        this.mTv_AnZhuPai = (TextView) findViewById(2131297222);
        this.mTv_DaoJiShi = (TextView) findViewById(2131297226);
        this.mTv_Number = (TextView) findViewById(2131297230);
        this.tvDTime = (TextView) findViewById(2131298923);
        this.tvDTime.setTextSize(1, 96.0f);
        this.tvDTime.setText("3");
        this.tvDTime.setVisibility(8);
        this.mCustom_LuZhiView = (RecordButtonView) findViewById(2131297231);
        this.mCustom_ProgressView = (LineProgressView) findViewById(2131297228);
        this.mCustom_CarmeraView = (MyWenZiJCameraView) findViewById(2131297225);
        CameraInterface.getInstance().setHuoTi(true);
        this.mCustom_CarmeraView.setMediaQuality(2000000);
        this.mCustom_LuZhiView.setMinTime(Integer.parseInt(this.huoTiEntity.getMinDuration()));
        this.imageView = (ImageView) findViewById(2131297227);
        this.mCustom_LuZhiView.setMaxTime(this.maxTime);
        this.mRl_TopLayout.setPadding(0, UIUtils.getStatusBarHeight(this), 0, 0);
        this.mTv_Number.setText(this.huoTiEntity.getReadNumber());
        this.mCustom_CarmeraView.setSaveVideoPath(FileUtils.getSaveDir(this));
        this.mCustom_CarmeraView.setPicListener(new MyWenZiJCameraView.ShowPic() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.1
            @Override // com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.MyWenZiJCameraView.ShowPic
            public void onPic(Bitmap bitmap, boolean z) {
                if (z) {
                    HuoTiDuWenZiActivity.this.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                } else {
                    HuoTiDuWenZiActivity.this.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                HuoTiDuWenZiActivity.this.imageView.setImageBitmap(bitmap);
                HuoTiDuWenZiActivity.this.imageView.setVisibility(0);
            }
        });
        this.mCustom_CarmeraView.setPlayVideo(new MyWenZiJCameraView.PlayVideo() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.2
            @Override // com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.MyWenZiJCameraView.PlayVideo
            public void onPlay(String str) {
                HuoTiDuWenZiActivity.this.videoUrl = str;
            }
        });
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.mCustom_CarmeraView.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        cancel();
        this.mCustom_CarmeraView.onPause();
    }

    private void initListener() {
        this.mImg_SwitchCamera.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (HuoTiDuWenZiActivity.this.isBackCamera) {
                    HuoTiDuWenZiActivity.this.isBackCamera = false;
                } else {
                    HuoTiDuWenZiActivity.this.isBackCamera = true;
                }
                if (HuoTiDuWenZiActivity.this.isLuZhi) {
                    HuoTiDuWenZiActivity.this.btnClick = false;
                    HuoTiDuWenZiActivity.this.countTime = 0;
                    if (HuoTiDuWenZiActivity.this.longRunble != null) {
                        HuoTiDuWenZiActivity.this.handler.removeCallbacks(HuoTiDuWenZiActivity.this.longRunble);
                    }
                    HuoTiDuWenZiActivity.this.mTv_AnZhuPai.setVisibility(0);
                    HuoTiDuWenZiActivity.this.mCustom_LuZhiView.doStopAnim();
                    HuoTiDuWenZiActivity.this.recordEnd(false);
                }
                HuoTiDuWenZiActivity.this.mCustom_CarmeraView.switchCamera();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mImg_WanCheng.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                HuoTiDuWenZiActivity huoTiDuWenZiActivity = HuoTiDuWenZiActivity.this;
                huoTiDuWenZiActivity.successHuoTi(huoTiDuWenZiActivity.videoUrl);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mImg_TopBack.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (HuoTiDuWenZiActivity.this.huoTiEntity != null) {
                    PvCurrencyLogUtils.pvHuoTiFaceVideoZDLog("录制视频失败", TextUtils.isEmpty(HuoTiDuWenZiActivity.this.huoTiEntity.getUrl()) ? "" : HuoTiDuWenZiActivity.this.huoTiEntity.getUrl(), "", "用户取消录制");
                }
                HuoTiDuWenZiActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mImg_BottomBack.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                HuoTiDuWenZiActivity.this.mCustom_CarmeraView.cancel();
                HuoTiDuWenZiActivity.this.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HuoTiDuWenZiActivity.this.cancel();
                    }
                });
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mCustom_LuZhiView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (HuoTiDuWenZiActivity.this.btnClick) {
                    HuoTiDuWenZiActivity.this.btnClick = false;
                    if (!HuoTiDuWenZiActivity.this.isLuZhi) {
                        HuoTiDuWenZiActivity.this.isLuZhi = false;
                        HuoTiDuWenZiActivity.this.tvDTime.setVisibility(0);
                        HuoTiDuWenZiActivity.this.countDownTimer.start();
                    } else {
                        if (HuoTiDuWenZiActivity.this.longRunble != null) {
                            HuoTiDuWenZiActivity.this.handler.removeCallbacks(HuoTiDuWenZiActivity.this.longRunble);
                        }
                        HuoTiDuWenZiActivity.this.mTv_AnZhuPai.setVisibility(0);
                        HuoTiDuWenZiActivity.this.mCustom_LuZhiView.doStopAnim();
                        if (HuoTiDuWenZiActivity.this.isLuZhi) {
                            HuoTiDuWenZiActivity.this.recordEnd(true);
                        }
                    }
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    public void cancel() {
        this.videoUrl = "";
        this.mRl_LuZhiLayout.setVisibility(0);
        this.mRl_WanChengLayout.setVisibility(8);
        this.mCustom_ProgressView.setProgress(0.0f);
        this.mTv_DaoJiShi.setText("1s");
        this.mTv_DaoJiShi.setVisibility(8);
        this.mTv_AnZhuPai.setVisibility(0);
        this.mCustom_CarmeraView.setVisibility(0);
        this.mCustom_CarmeraView.stopVideo();
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class LongRunble implements Runnable {
        private LongRunble() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                HuoTiDuWenZiActivity.this.isLuZhi = true;
                HuoTiDuWenZiActivity.this.countTime = 0;
                HuoTiDuWenZiActivity.this.mCustom_CarmeraView.startVideo();
                if (CameraInterface.getInstance().getSELECTED_CAMERA() == 1) {
                    HuoTiDuWenZiActivity.this.isBackCamera = true;
                } else {
                    HuoTiDuWenZiActivity.this.isBackCamera = false;
                }
                HuoTiDuWenZiActivity.this.mTv_AnZhuPai.setText("停止录制");
                HuoTiDuWenZiActivity.this.mTv_AnZhuPai.setVisibility(8);
                HuoTiDuWenZiActivity.this.mTv_DaoJiShi.setVisibility(0);
                HuoTiDuWenZiActivity.this.mTv_DaoJiShi.setText("1s");
                HuoTiDuWenZiActivity.this.mCustom_LuZhiView.doStartAnim();
                HuoTiDuWenZiActivity.this.recordTime = System.currentTimeMillis();
                HuoTiDuWenZiActivity.this.runLoopPro();
                if (HuoTiDuWenZiActivity.this.huoTiEntity != null) {
                    PvCurrencyLogUtils.pvHuoTiFaceVideoZDLog("开始录制视频", TextUtils.isEmpty(HuoTiDuWenZiActivity.this.huoTiEntity.getUrl()) ? "" : HuoTiDuWenZiActivity.this.huoTiEntity.getUrl(), "", "成功");
                }
            } catch (Exception e) {
                MsLogUtil.m7977e("异常：", e.getMessage());
                if (HuoTiDuWenZiActivity.this.huoTiEntity != null) {
                    String url = TextUtils.isEmpty(HuoTiDuWenZiActivity.this.huoTiEntity.getUrl()) ? "" : HuoTiDuWenZiActivity.this.huoTiEntity.getUrl();
                    PvCurrencyLogUtils.pvHuoTiFaceVideoZDLog("开始录制视频", url, "", "异常：" + e.getMessage());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void successHuoTi(String str) {
        double fileOrFilesSize = !TextUtils.isEmpty(str) ? FileSizeUtil.getFileOrFilesSize(str, 2) : 0.0d;
        HuoTiEntity huoTiEntity = this.huoTiEntity;
        if (huoTiEntity != null) {
            String url = TextUtils.isEmpty(huoTiEntity.getUrl()) ? "" : this.huoTiEntity.getUrl();
            PvCurrencyLogUtils.pvHuoTiFaceVideoZDLog("录制视频成功", url, fileOrFilesSize + "kb", "成功");
        }
        Intent intent = new Intent();
        intent.putExtra("videoUrl", str);
        intent.putExtra("entity", this.huoTiEntity);
        intent.putExtra("isBackCamera", this.isBackCamera);
        intent.putExtra("videoSize", fileOrFilesSize + "kb");
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runLoopPro() {
        RxJavaUtil.loop(20L, new RxJavaUtil.OnRxLoopListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.9
            @Override // com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.OnRxLoopListener
            public void onFinish() {
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.OnRxLoopListener
            public Boolean takeWhile() {
                return Boolean.valueOf(HuoTiDuWenZiActivity.this.isLuZhi);
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.OnRxLoopListener
            public void onExecute() {
                float currentTimeMillis = (float) (System.currentTimeMillis() - HuoTiDuWenZiActivity.this.recordTime);
                if (currentTimeMillis > HuoTiDuWenZiActivity.MAX_VIDEO_TIME || !HuoTiDuWenZiActivity.this.isLuZhi) {
                    HuoTiDuWenZiActivity.this.mCustom_ProgressView.setProgress(0.0f);
                } else {
                    HuoTiDuWenZiActivity.this.mCustom_ProgressView.setProgress(currentTimeMillis / HuoTiDuWenZiActivity.MAX_VIDEO_TIME);
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.OnRxLoopListener
            public void onError(Throwable th) {
                th.printStackTrace();
            }
        });
        countDown3();
    }

    private void countDown3() {
        if (this.timer == null) {
            this.timer = new CountDownTimer((this.maxTime * 1000) + 700, 1000L) { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.10
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                    HuoTiDuWenZiActivity huoTiDuWenZiActivity = HuoTiDuWenZiActivity.this;
                    huoTiDuWenZiActivity.countTime = ((int) ((((huoTiDuWenZiActivity.maxTime * 1000) + 700) - j) / 1000)) + 1;
                    if (HuoTiDuWenZiActivity.this.countTime > HuoTiDuWenZiActivity.this.maxTime) {
                        HuoTiDuWenZiActivity huoTiDuWenZiActivity2 = HuoTiDuWenZiActivity.this;
                        huoTiDuWenZiActivity2.countTime = huoTiDuWenZiActivity2.maxTime;
                    }
                    TextView textView = HuoTiDuWenZiActivity.this.mTv_DaoJiShi;
                    textView.setText(HuoTiDuWenZiActivity.this.countTime + "s");
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    HuoTiDuWenZiActivity.this.recordEnd(true);
                }
            };
        }
        this.timer.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordEnd(boolean z) {
        if (this.countTime <= this.minTime) {
            CountDownTimer countDownTimer = this.timer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.isLuZhi = false;
            if (z) {
                UIUtils.toast("您的录制时长太短，需要重新录制");
            }
            this.mCustom_CarmeraView.timeShort(System.currentTimeMillis() - this.recordTime);
            this.mCustom_ProgressView.setProgress(0.0f);
            this.mCustom_LuZhiView.doStopAnim();
            this.mTv_DaoJiShi.setText("1s");
            this.mTv_DaoJiShi.setVisibility(8);
            this.btnClick = true;
            this.mTv_AnZhuPai.setText("点击录制");
            return;
        }
        this.mCustom_CarmeraView.stopVideo();
        this.isLuZhi = false;
        runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiDuWenZiActivity.11
            @Override // java.lang.Runnable
            public void run() {
                if (HuoTiDuWenZiActivity.this.timer != null) {
                    HuoTiDuWenZiActivity.this.timer.cancel();
                }
                HuoTiDuWenZiActivity.this.mCustom_LuZhiView.doStopAnim();
                HuoTiDuWenZiActivity.this.mRl_LuZhiLayout.setVisibility(8);
                HuoTiDuWenZiActivity.this.mRl_WanChengLayout.setVisibility(0);
                HuoTiDuWenZiActivity.this.mCustom_CarmeraView.playVideo(System.currentTimeMillis() - HuoTiDuWenZiActivity.this.recordTime, false);
                HuoTiDuWenZiActivity.this.btnClick = true;
                HuoTiDuWenZiActivity.this.mTv_AnZhuPai.setText("点击录制");
            }
        });
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            onBackPressed();
            HuoTiEntity huoTiEntity = this.huoTiEntity;
            if (huoTiEntity != null) {
                PvCurrencyLogUtils.pvHuoTiFaceVideoZDLog("录制视频失败", TextUtils.isEmpty(huoTiEntity.getUrl()) ? "" : this.huoTiEntity.getUrl(), "", "用户取消录制");
                return true;
            }
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.timer = null;
        }
        LongRunble longRunble = this.longRunble;
        if (longRunble != null) {
            this.handler.removeCallbacks(longRunble);
            this.longRunble = null;
        }
        CountDownTimer countDownTimer2 = this.countDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        CameraInterface.getInstance().setHuoTi(false);
    }
}
