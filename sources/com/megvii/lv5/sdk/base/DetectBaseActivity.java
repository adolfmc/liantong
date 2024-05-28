package com.megvii.lv5.sdk.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.bytedance.applog.tracker.Tracker;
import com.megvii.lv5.AbstractC5378a1;
import com.megvii.lv5.C5527o2;
import com.megvii.lv5.C5538q0;
import com.megvii.lv5.C5540q2;
import com.megvii.lv5.C5628v2;
import com.megvii.lv5.C5667z2;
import com.megvii.lv5.sdk.C5559R;
import com.megvii.lv5.sdk.base.DetectBasePresenter;
import com.megvii.lv5.sdk.detect.RecordService;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class DetectBaseActivity<P extends DetectBasePresenter> extends Activity implements BaseView {
    public AlertDialog alertDialog;
    public String apiKey;
    public Bitmap bitmapBottom;
    public Bitmap bitmapTop;
    public int currentVolume;
    public int[] defaultFailureColor;
    public Intent intentRecord;
    public String language;
    public int livenessActionSuccessDrawableId;
    public int livenessCloseDrawableId;
    public int livenessCloseWhiteDrawableId;
    public int livenessHomeActionRemindSize;
    public int livenessHomeActionTimeTextColor;
    public int livenessHomeActionTimeTextSize;
    public int livenessHomeBackgroundColor1;
    public int livenessHomeBackgroundColor2;
    public int livenessHomeCustomPromptBackgroundColor;
    public int livenessHomeCustomPromptTextColor;
    public int livenessHomeDeviceVerticalRemindColor;
    public int livenessHomeDeviceVerticalRemindSize;
    public int livenessHomeFailedRemindTextColor;
    public int livenessHomeLoadingTextSize;
    public int livenessHomeNormalRemindTextColor;
    public int livenessHomeProcessBarColor;
    public int livenessHomeRemindSize;
    public int livenessLogoDrawableId;
    public int livenessScrnAuthorizedRejectButtonTextId;
    public int livenessScrnAuthorizedRejectTextId;
    public int livenessVerticalDrawableId;
    public AudioManager mAudioManager;
    public C5540q2 mDialogUtil;
    public String mHost;
    public boolean mIsShowLogo;
    public String mLanguage;
    public AbstractC5378a1 mManagerImpl;
    private P mPresenter;
    public RecordService mRecordService;
    public int mSuggestVolume;
    public int mVerticalCheckType;
    public MediaProjection mediaProjection;
    public String mediaSourcePath;
    public MediaProjectionManager projectionManager;
    public String videoKey;
    public boolean mIsAutoAdjustVolume = false;
    public boolean isRequestingScreenRecordPermission = false;
    public boolean mIsBinded = false;

    private void changeAppBrightness(int i) {
        float f;
        if (C5527o2.m13223h(this).f13025s0 == 1) {
            Window window = getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (i == -1) {
                f = -1.0f;
            } else {
                if (i <= 0) {
                    i = 1;
                }
                f = i / 255.0f;
            }
            attributes.screenBrightness = f;
            window.setAttributes(attributes);
        }
    }

    private void initColor() {
        this.defaultFailureColor = C5538q0.m13195a(getResources().getColor(C5559R.C5560color.megvii_liveness_progress_failure));
        this.livenessHomeProcessBarColor = C5667z2.m12879a(this).m12878a(getResources().getString(C5559R.string.key_liveness_home_progressbar_color));
        this.livenessHomeNormalRemindTextColor = C5667z2.m12879a(this).m12878a(getResources().getString(C5559R.string.key_liveness_home_normal_remind_text_color));
        this.livenessHomeFailedRemindTextColor = C5667z2.m12879a(this).m12878a(getResources().getString(C5559R.string.key_liveness_home_failed_remind_text_color));
        this.livenessHomeBackgroundColor1 = C5667z2.m12879a(this).m12878a(getResources().getString(C5559R.string.key_liveness_home_background_color1));
        this.livenessHomeBackgroundColor2 = C5667z2.m12879a(this).m12878a(getResources().getString(C5559R.string.key_liveness_home_background_color2));
        this.livenessHomeDeviceVerticalRemindColor = C5667z2.m12879a(this).m12878a(getResources().getString(C5559R.string.key_liveness_home_device_vertical_remind_color));
        this.livenessHomeActionTimeTextColor = C5667z2.m12879a(this).m12878a(getResources().getString(C5559R.string.key_liveness_home_action_time_text_color));
        this.livenessHomeCustomPromptBackgroundColor = C5667z2.m12879a(this).m12878a(getResources().getString(C5559R.string.key_liveness_home_custom_prompt_background_color));
        this.livenessHomeCustomPromptTextColor = C5667z2.m12879a(this).m12878a(getResources().getString(C5559R.string.key_liveness_home_custom_prompt_text_color));
        this.livenessHomeRemindSize = C5559R.dimen.Meglive_liveness_home_remind_size;
        this.livenessHomeActionRemindSize = C5559R.dimen.meglive_liveness_home_action_remind_size;
        this.livenessHomeLoadingTextSize = C5559R.dimen.meglive_liveness_home_loading_text_size;
        this.livenessHomeDeviceVerticalRemindSize = C5559R.dimen.meglive_liveness_home_device_vertical_remind_size;
        this.livenessHomeActionTimeTextSize = C5559R.dimen.meglive_liveness_home_action_time_text_size;
        this.livenessLogoDrawableId = C5667z2.m12879a(this).m12877b(getResources().getString(C5559R.string.key_liveness_logo_icon));
        this.livenessVerticalDrawableId = C5667z2.m12879a(this).m12877b(getResources().getString(C5559R.string.key_liveness_home_vertical_remind));
        this.livenessCloseDrawableId = C5667z2.m12879a(this).m12877b(getResources().getString(C5559R.string.key_liveness_home_close));
        this.livenessCloseWhiteDrawableId = C5667z2.m12879a(this).m12877b(getResources().getString(C5559R.string.key_liveness_home_close_white));
        this.livenessActionSuccessDrawableId = C5667z2.m12879a(this).m12877b(getResources().getString(C5559R.string.key_liveness_action_success_icon));
        this.bitmapTop = C5538q0.m13182b(getResources().getColor(this.livenessHomeBackgroundColor1));
        this.bitmapBottom = C5538q0.m13182b(getResources().getColor(this.livenessHomeBackgroundColor2));
    }

    private void initText() {
        this.livenessScrnAuthorizedRejectTextId = C5667z2.m12879a(this).m12875d(getResources().getString(C5559R.string.key_liveness_home_scrn_authorized_reject_text));
        this.livenessScrnAuthorizedRejectButtonTextId = C5667z2.m12879a(this).m12875d(getResources().getString(C5559R.string.key_liveness_home_scrn_authorized_reject_button_text));
    }

    private void setLanguage() {
        String stringExtra = getIntent().getStringExtra("language");
        this.language = stringExtra;
        C5527o2.m13236b(this, stringExtra);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        if (context.getResources().getConfiguration().fontScale != 1.0f) {
            Configuration configuration = new Configuration(context.getResources().getConfiguration());
            configuration.fontScale = 1.0f;
            applyOverrideConfiguration(configuration);
        }
        super.attachBaseContext(context);
    }

    public void changeVolume(int i) {
        if (i > 100) {
            i = 100;
        }
        if (i < 0) {
            i = 0;
        }
        try {
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            this.mAudioManager = audioManager;
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            this.currentVolume = this.mAudioManager.getStreamVolume(3);
            int i2 = (int) ((i / 100.0f) * streamMaxVolume);
            String str = "changeVolume: currentVolume = " + this.currentVolume;
            r0 = "changeVolume: volume = " + i2;
            r0 = "changeVolume: maxVolume = " + streamMaxVolume;
            if (this.currentVolume < i2) {
                this.mAudioManager.setStreamVolume(3, i2, 4);
            }
            C5628v2.m12958b("changeVolume", "maxVolume:" + streamMaxVolume + ",currentVolume:" + this.currentVolume);
        } catch (Exception unused) {
        }
    }

    public abstract P createPresenter();

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.megvii.lv5.sdk.base.BaseView
    public Activity getActivity() {
        return this;
    }

    @Override // com.megvii.lv5.sdk.base.BaseView
    public Context getContext() {
        return getApplicationContext();
    }

    public abstract int getLayoutResId();

    public String getMirroFailedMsg(int i) {
        Resources resources;
        C5667z2 m12879a;
        int i2;
        if (1 == i) {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptFrontalFace_text;
        } else if (2 == i || 3 == i || 4 == i) {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptRightPose_text;
        } else if (5 == i) {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptFaceErea_text;
        } else if (6 == i) {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptBrighter_text;
        } else if (7 == i) {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptDarker_text;
        } else if (8 == i) {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptCloser_text;
        } else if (9 == i) {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptFurther_text;
        } else if (10 == i) {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptNoBacklighting_text;
        } else if (11 == i) {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptFrontalFaceInBoundingBox_text;
        } else if (12 == i) {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptNoEyesOcclusion_text;
        } else if (13 == i) {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptNoMouthOcclusion_text;
        } else if (14 != i) {
            if (15 != i) {
                if (16 == i) {
                    resources = getResources();
                    m12879a = C5667z2.m12879a(this);
                    i2 = C5559R.string.key_livenessHomePromptMultiplayerText;
                } else if (17 == i) {
                    resources = getResources();
                    m12879a = C5667z2.m12879a(this);
                    i2 = C5559R.string.key_liveness_home_promptEnvBrighter_text;
                } else if (18 == i) {
                    resources = getResources();
                    m12879a = C5667z2.m12879a(this);
                    i2 = C5559R.string.key_liveness_home_promptEnvDarker_text;
                }
            }
            return " ";
        } else {
            resources = getResources();
            m12879a = C5667z2.m12879a(this);
            i2 = C5559R.string.key_liveness_home_promptStayStill_text;
        }
        return resources.getString(m12879a.m12875d(getString(i2)));
    }

    public P getPresenter() {
        return this.mPresenter;
    }

    public abstract void initData();

    public abstract void initView();

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0242  */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onCreate(android.os.Bundle r10) {
        /*
            Method dump skipped, instructions count: 704
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.base.DetectBaseActivity.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        changeAppBrightness(-1);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            audioManager.setStreamVolume(3, this.currentVolume, 4);
        }
    }

    public void soundOff() {
        try {
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            this.mAudioManager = audioManager;
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            this.currentVolume = this.mAudioManager.getStreamVolume(3);
            this.mAudioManager.setStreamVolume(3, 0, 4);
            C5628v2.m12958b("soundOff", "maxVolume:" + streamMaxVolume + ",currentVolume:" + this.currentVolume);
        } catch (Exception unused) {
        }
    }
}
