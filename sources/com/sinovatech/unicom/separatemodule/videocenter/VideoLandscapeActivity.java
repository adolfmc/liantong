package com.sinovatech.unicom.separatemodule.videocenter;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoLandscapeActivity extends BaseActivity {
    public static String AC_TYPE = "activityType";
    public static final int LANDSCAPE_RESULT_CODE = 2817;
    public NBSTraceUnit _nbs_trace;
    private View back;
    private RelativeLayout flVideoPlayer;
    private View ivPlayerStatus;
    private View llPlayEndBg;
    private View vPlayEndTop;
    private View videoPlayer;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 118);
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

    public static /* synthetic */ void lambda$onCreate$0(VideoLandscapeActivity videoLandscapeActivity, View view) {
        videoLandscapeActivity.setResult(LANDSCAPE_RESULT_CODE);
        videoLandscapeActivity.finish();
    }

    private void showLandScapeEnd() {
        if (this.vPlayEndTop == null) {
            this.vPlayEndTop = this.videoPlayer.findViewById(2131299490);
        }
        this.vPlayEndTop.setVisibility(0);
        if (this.llPlayEndBg == null) {
            this.llPlayEndBg = this.videoPlayer.findViewById(2131297757);
        }
        this.llPlayEndBg.setBackgroundResource(2131230891);
    }

    private void showVerticalEnd() {
        if (this.vPlayEndTop == null) {
            this.vPlayEndTop = this.videoPlayer.findViewById(2131299490);
        }
        this.vPlayEndTop.setVisibility(8);
        if (this.llPlayEndBg == null) {
            this.llPlayEndBg = this.videoPlayer.findViewById(2131297757);
        }
        this.llPlayEndBg.setBackgroundColor(0);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        UIUtils.logD("VideoLandscapeActivity", "onResume");
        try {
            if (getIntent().hasExtra(AC_TYPE)) {
                if (VideoCenterActivity.isPausedByOnPause) {
                    VideoCenterActivity.isPausedByOnPause = false;
                    VideoCenterActivity.bdCloudVideoView.enterForeground();
                    VideoCenterActivity.bdCloudVideoView.start();
                    this.ivPlayerStatus.setTag(null);
                }
            } else if (VideoDetailsActivity.isPausedByOnPause) {
                VideoDetailsActivity.isPausedByOnPause = false;
                VideoDetailsActivity.bdVideo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        try {
            UIUtils.logD("VideoLandscapeActivity", "onPause");
            showVerticalEnd();
            if (getIntent().hasExtra(AC_TYPE)) {
                if (VideoCenterActivity.bdCloudVideoView.isPlaying()) {
                    VideoCenterActivity.isPausedByOnPause = true;
                    VideoCenterActivity.bdCloudVideoView.pause();
                    return;
                }
                return;
            }
            if (VideoDetailsActivity.bdVideo.isPlaying()) {
                VideoDetailsActivity.isPausedByOnPause = true;
                VideoDetailsActivity.bdVideo.pause();
            }
            if (isFinishing()) {
                VideoDetailsActivity.isFull = false;
                VideoCenterActivity.isFull = false;
                UIUtils.logD("xcyTest", "关闭");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
