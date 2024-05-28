package com.sinovatech.unicom.separatemodule.huotijiance;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bytedance.applog.tracker.Tracker;
import com.cjt2325.cameralibrary.CameraInterface;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.huotijiance.MyJCameraView;
import com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil;
import com.sinovatech.unicom.separatemodule.huotijiance.util.FileUtils;
import com.sinovatech.unicom.separatemodule.huotijiance.view.LineProgressView;
import com.sinovatech.unicom.separatemodule.huotijiance.view.RecordButtonView;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HuoTiActivity extends BaseActivity {
    public static float MAX_VIDEO_TIME = 7000.0f;
    public static final float MIN_VIDEO_TIME = 2000.0f;
    private static final String TAG = "HuoTiActivity";
    public NBSTraceUnit _nbs_trace;
    private HuoTiEntity huoTiEntity;
    private ImageView imageView;
    private LongRunble longRunble;
    private MyJCameraView mCustom_CarmeraView;
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
    private long recordTime = 0;
    private boolean isLuZhi = false;
    private int maxTime = 6;
    private int minTime = 1;
    private boolean isBackCamera = false;
    private String videoUrl = "";
    private Handler handler = new Handler();
    private int countTime = 0;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 83);
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
        this.mCustom_LuZhiView = (RecordButtonView) findViewById(2131297231);
        this.mCustom_ProgressView = (LineProgressView) findViewById(2131297228);
        this.mCustom_CarmeraView = (MyJCameraView) findViewById(2131297225);
        CameraInterface.getInstance().setHuoTi(true);
        this.mCustom_CarmeraView.setMediaQuality(2000000);
        this.mCustom_LuZhiView.setMinTime(Integer.parseInt(this.huoTiEntity.getMinDuration()));
        this.imageView = (ImageView) findViewById(2131297227);
        this.mCustom_LuZhiView.setMaxTime(this.maxTime);
        this.mRl_TopLayout.setPadding(0, UIUtils.getStatusBarHeight(this), 0, 0);
        this.mTv_Number.setText(this.huoTiEntity.getReadNumber());
        this.mCustom_CarmeraView.setSaveVideoPath(FileUtils.getSaveDir(this));
        this.mCustom_CarmeraView.setPicListener(new MyJCameraView.ShowPic() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity.1
            @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyJCameraView.ShowPic
            public void onPic(Bitmap bitmap, boolean z) {
                if (z) {
                    HuoTiActivity.this.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                } else {
                    HuoTiActivity.this.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                HuoTiActivity.this.imageView.setImageBitmap(bitmap);
                HuoTiActivity.this.imageView.setVisibility(0);
            }
        });
        this.mCustom_CarmeraView.setPlayVideo(new MyJCameraView.PlayVideo() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity.2
            @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyJCameraView.PlayVideo
            public void onPlay(String str) {
                HuoTiActivity.this.videoUrl = str;
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
        this.mImg_SwitchCamera.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (HuoTiActivity.this.isBackCamera) {
                    HuoTiActivity.this.isBackCamera = false;
                } else {
                    HuoTiActivity.this.isBackCamera = true;
                }
                HuoTiActivity.this.mCustom_CarmeraView.switchCamera();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mImg_WanCheng.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                HuoTiActivity huoTiActivity = HuoTiActivity.this;
                huoTiActivity.successHuoTi(huoTiActivity.videoUrl);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mImg_TopBack.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                HuoTiActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mImg_BottomBack.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                HuoTiActivity.this.mCustom_CarmeraView.cancel();
                HuoTiActivity.this.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HuoTiActivity.this.cancel();
                    }
                });
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mCustom_LuZhiView.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity.7
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    HuoTiActivity.this.isLuZhi = false;
                    HuoTiActivity.this.handler.postDelayed(HuoTiActivity.this.longRunble, 600L);
                } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                    HuoTiActivity.this.handler.removeCallbacks(HuoTiActivity.this.longRunble);
                    HuoTiActivity.this.mTv_AnZhuPai.setVisibility(0);
                    HuoTiActivity.this.mCustom_LuZhiView.doStopAnim();
                    if (HuoTiActivity.this.isLuZhi) {
                        HuoTiActivity.this.recordEnd();
                    }
                }
                return true;
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
            HuoTiActivity.this.isLuZhi = true;
            HuoTiActivity.this.countTime = 0;
            HuoTiActivity.this.mCustom_CarmeraView.startVideo();
            if (CameraInterface.getInstance().getSELECTED_CAMERA() == 1) {
                HuoTiActivity.this.isBackCamera = true;
            } else {
                HuoTiActivity.this.isBackCamera = false;
            }
            Log.d("dddd", "开始录制");
            HuoTiActivity.this.mTv_AnZhuPai.setVisibility(8);
            HuoTiActivity.this.mTv_DaoJiShi.setVisibility(0);
            HuoTiActivity.this.mTv_DaoJiShi.setText("1s");
            HuoTiActivity.this.mCustom_LuZhiView.doStartAnim();
            HuoTiActivity.this.recordTime = System.currentTimeMillis();
            HuoTiActivity.this.runLoopPro();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void successHuoTi(String str) {
        Intent intent = new Intent();
        intent.putExtra("videoUrl", str);
        intent.putExtra("entity", this.huoTiEntity);
        intent.putExtra("isBackCamera", this.isBackCamera);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runLoopPro() {
        RxJavaUtil.loop(20L, new RxJavaUtil.OnRxLoopListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity.8
            @Override // com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.OnRxLoopListener
            public void onFinish() {
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.OnRxLoopListener
            public Boolean takeWhile() {
                return Boolean.valueOf(HuoTiActivity.this.isLuZhi);
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.OnRxLoopListener
            public void onExecute() {
                float currentTimeMillis = (float) (System.currentTimeMillis() - HuoTiActivity.this.recordTime);
                if (currentTimeMillis > HuoTiActivity.MAX_VIDEO_TIME || !HuoTiActivity.this.isLuZhi) {
                    HuoTiActivity.this.mCustom_ProgressView.setProgress(0.0f);
                } else {
                    HuoTiActivity.this.mCustom_ProgressView.setProgress(currentTimeMillis / HuoTiActivity.MAX_VIDEO_TIME);
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
            this.timer = new CountDownTimer((this.maxTime * 1000) + 700, 1000L) { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity.9
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                    String str = HuoTiActivity.TAG;
                    Log.e(str, "onTick: " + j);
                    HuoTiActivity huoTiActivity = HuoTiActivity.this;
                    huoTiActivity.countTime = ((int) ((((long) ((huoTiActivity.maxTime * 1000) + 700)) - j) / 1000)) + 1;
                    if (HuoTiActivity.this.countTime > 6) {
                        HuoTiActivity.this.countTime = 6;
                    }
                    TextView textView = HuoTiActivity.this.mTv_DaoJiShi;
                    textView.setText(HuoTiActivity.this.countTime + "s");
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    Log.e(HuoTiActivity.TAG, "onTick: onFinish");
                    HuoTiActivity.this.recordEnd();
                }
            };
        }
        this.timer.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordEnd() {
        if (this.countTime <= this.minTime) {
            CountDownTimer countDownTimer = this.timer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.isLuZhi = false;
            showToast(this, "您的录制时长太短，需要重新录制", 0);
            this.mCustom_CarmeraView.timeShort(System.currentTimeMillis() - this.recordTime);
            this.mCustom_ProgressView.setProgress(0.0f);
            this.mCustom_LuZhiView.doStopAnim();
            this.mTv_DaoJiShi.setText("1s");
            this.mTv_DaoJiShi.setVisibility(8);
            return;
        }
        this.mCustom_CarmeraView.stopVideo();
        this.isLuZhi = false;
        runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity.10
            @Override // java.lang.Runnable
            public void run() {
                if (HuoTiActivity.this.timer != null) {
                    HuoTiActivity.this.timer.cancel();
                }
                HuoTiActivity.this.mCustom_LuZhiView.doStopAnim();
                HuoTiActivity.this.mRl_LuZhiLayout.setVisibility(8);
                HuoTiActivity.this.mRl_WanChengLayout.setVisibility(0);
                HuoTiActivity.this.mCustom_CarmeraView.playVideo(System.currentTimeMillis() - HuoTiActivity.this.recordTime, false);
            }
        });
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.timer = null;
        }
        CameraInterface.getInstance().setHuoTi(false);
    }

    public void showToast(Context context, String str, int i) {
        Toast toast = this.toast;
        if (toast == null) {
            this.toast = Toast.makeText(context, str, i);
        } else {
            toast.setText(str);
        }
        this.toast.show();
    }
}
