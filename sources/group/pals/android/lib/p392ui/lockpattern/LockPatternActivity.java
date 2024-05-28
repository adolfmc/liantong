package group.pals.android.lib.p392ui.lockpattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import group.pals.android.lib.p392ui.lockpattern.prefs.DisplayPrefs;
import group.pals.android.lib.p392ui.lockpattern.prefs.SecurityPrefs;
import group.pals.android.lib.p392ui.lockpattern.util.C11988UI;
import group.pals.android.lib.p392ui.lockpattern.util.IEncrypter;
import group.pals.android.lib.p392ui.lockpattern.util.ShakeUtils;
import group.pals.android.lib.p392ui.lockpattern.widget.LockPatternUtils;
import group.pals.android.lib.p392ui.lockpattern.widget.LockPatternView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NBSInstrumented
/* renamed from: group.pals.android.lib.ui.lockpattern.LockPatternActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class LockPatternActivity extends Activity {
    private static final long DELAY_TIME_TO_RELOAD_LOCK_PATTERN_VIEW = 0;
    public static final int RESULT_FAILED = 2;
    public static final int RESULT_FORGOT_PATTERN = 3;
    public static CallBackInterface callBackInterface;
    public NBSTraceUnit _nbs_trace;
    private boolean isH5;
    private boolean isReset;
    private boolean mAutoSave;
    private ButtonOkCommand mBtnOkCmd;
    private IEncrypter mEncrypter;
    private View mFooter;
    private Intent mIntentResult;
    private TextView mLargeTextInfo;
    private LockPatternView mLockPatternView;
    private int mMaxRetry;
    private int mMinWiredDots;
    private TextView mTextAccountInfo;
    private TextView mTextInfo;
    private static final String CLASSNAME = "group.pals.android.lib.ui.lockpattern.LockPatternActivity";
    public static final String ACTION_CREATE_PATTERN = CLASSNAME + ".create_pattern";
    public static final String ACTION_COMPARE_PATTERN = CLASSNAME + ".compare_pattern";
    public static final String ACTION_VERIFY_CAPTCHA = CLASSNAME + ".verify_captcha";
    public static final String EXTRA_RETRY_COUNT = CLASSNAME + ".retry_count";
    public static final String EXTRA_THEME = CLASSNAME + ".theme";
    public static final String EXTRA_PATTERN = CLASSNAME + ".pattern";
    public static final String EXTRA_RESULT_RECEIVER = CLASSNAME + ".result_receiver";
    public static final String EXTRA_PENDING_INTENT_OK = CLASSNAME + ".pending_intent_ok";
    public static final String EXTRA_PENDING_INTENT_CANCELLED = CLASSNAME + ".pending_intent_cancelled";
    public static final String EXTRA_INTENT_ACTIVITY_FORGOT_PATTERN = CLASSNAME + ".intent_activity_forgot_pattern";
    private static final int default_key_hint = C11982R.C11984drawable.nine_box_normal;
    private static final int selected_key_hint = C11982R.C11984drawable.nine_box_selected;
    public static final String EXTRA_INTENT_ACTIVITY_ACCOUNT_INFO = CLASSNAME + ".intent_activity_account_info";
    private int mRetryCount = 0;
    private String mobile = "0";
    private final LockPatternView.OnPatternListener mLockPatternViewListener = new LockPatternView.OnPatternListener() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.4
        @Override // group.pals.android.lib.p392ui.lockpattern.widget.LockPatternView.OnPatternListener
        public void onPatternCellAdded(List<LockPatternView.Cell> list) {
        }

        @Override // group.pals.android.lib.p392ui.lockpattern.widget.LockPatternView.OnPatternListener
        public void onPatternStart() {
            LockPatternActivity.this.mLockPatternView.removeCallbacks(LockPatternActivity.this.mLockPatternViewReloader);
            LockPatternActivity.this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);
            if (LockPatternActivity.ACTION_CREATE_PATTERN.equals(LockPatternActivity.this.getIntent().getAction())) {
                if (LockPatternActivity.this.mBtnOkCmd == ButtonOkCommand.CONTINUE) {
                    LockPatternActivity.this.getIntent().removeExtra(LockPatternActivity.EXTRA_PATTERN);
                }
            } else if (LockPatternActivity.ACTION_COMPARE_PATTERN.equals(LockPatternActivity.this.getIntent().getAction())) {
                LockPatternActivity.this.showLargeTextViewInfo(C11982R.string.alp_msg_draw_pattern_to_unlock);
            } else if (LockPatternActivity.ACTION_VERIFY_CAPTCHA.equals(LockPatternActivity.this.getIntent().getAction())) {
                LockPatternActivity.this.mTextInfo.setText(C11982R.string.alp_msg_redraw_pattern_to_confirm);
                LockPatternActivity.this.showTextViewInfo();
                LockPatternActivity.this.mTextInfo.setTextColor(LockPatternActivity.this.getResources().getColor(C11982R.C11983color.re_confirm_color));
            }
        }

        @Override // group.pals.android.lib.p392ui.lockpattern.widget.LockPatternView.OnPatternListener
        public void onPatternDetected(List<LockPatternView.Cell> list) {
            if (LockPatternActivity.ACTION_CREATE_PATTERN.equals(LockPatternActivity.this.getIntent().getAction())) {
                LockPatternActivity.this.doCheckAndCreatePattern(list);
            } else if (LockPatternActivity.ACTION_COMPARE_PATTERN.equals(LockPatternActivity.this.getIntent().getAction())) {
                LockPatternActivity.this.doComparePattern(list);
            } else if (!LockPatternActivity.ACTION_VERIFY_CAPTCHA.equals(LockPatternActivity.this.getIntent().getAction()) || LockPatternView.DisplayMode.Animate.equals(LockPatternActivity.this.mLockPatternView.getDisplayMode())) {
            } else {
                LockPatternActivity.this.doComparePattern(list);
            }
        }

        @Override // group.pals.android.lib.p392ui.lockpattern.widget.LockPatternView.OnPatternListener
        public void onPatternCleared() {
            LockPatternActivity.this.mLockPatternView.removeCallbacks(LockPatternActivity.this.mLockPatternViewReloader);
            if (LockPatternActivity.ACTION_CREATE_PATTERN.equals(LockPatternActivity.this.getIntent().getAction())) {
                LockPatternActivity.this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);
                if (LockPatternActivity.this.mBtnOkCmd == ButtonOkCommand.CONTINUE) {
                    LockPatternActivity.this.getIntent().removeExtra(LockPatternActivity.EXTRA_PATTERN);
                    LockPatternActivity.this.showTextViewInfo();
                }
            } else if (LockPatternActivity.ACTION_COMPARE_PATTERN.equals(LockPatternActivity.this.getIntent().getAction())) {
                LockPatternActivity.this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);
            } else if (LockPatternActivity.ACTION_VERIFY_CAPTCHA.equals(LockPatternActivity.this.getIntent().getAction())) {
                LockPatternActivity.this.mTextInfo.setText(C11982R.string.alp_msg_redraw_pattern_to_confirm);
                LockPatternActivity.this.showTextViewInfo();
                LockPatternActivity.this.mTextInfo.setTextColor(LockPatternActivity.this.getResources().getColor(C11982R.C11983color.re_confirm_color));
                LockPatternActivity.this.mLockPatternView.setPattern(LockPatternView.DisplayMode.Animate, LockPatternActivity.this.getIntent().getParcelableArrayListExtra(LockPatternActivity.EXTRA_PATTERN));
            }
        }
    };
    private final View.OnClickListener mBtnCancelOnClickListener = new View.OnClickListener() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            LockPatternActivity.this.finishWithNegativeResult(0);
            NBSActionInstrumentation.onClickEventExit();
        }
    };
    private final View.OnClickListener mBtnConfirmOnClickListener = new View.OnClickListener() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (LockPatternActivity.ACTION_CREATE_PATTERN.equals(LockPatternActivity.this.getIntent().getAction())) {
                if (LockPatternActivity.this.mBtnOkCmd == ButtonOkCommand.CONTINUE) {
                    LockPatternActivity.this.mBtnOkCmd = ButtonOkCommand.DONE;
                    LockPatternActivity lockPatternActivity = LockPatternActivity.this;
                    lockPatternActivity.showKeyHint(lockPatternActivity.mLockPatternView.getPattern());
                    LockPatternActivity.this.mLockPatternView.clearPattern();
                    LockPatternActivity.this.mTextInfo.setText(C11982R.string.alp_msg_redraw_pattern_to_confirm);
                    LockPatternActivity.this.showTextViewInfo();
                    LockPatternActivity.this.mTextInfo.setTextColor(LockPatternActivity.this.getResources().getColor(C11982R.C11983color.re_confirm_color));
                } else {
                    char[] charArrayExtra = LockPatternActivity.this.getIntent().getCharArrayExtra(LockPatternActivity.EXTRA_PATTERN);
                    if (LockPatternActivity.this.mAutoSave) {
                        LockPatternActivity lockPatternActivity2 = LockPatternActivity.this;
                        SecurityPrefs.setPattern(lockPatternActivity2, lockPatternActivity2.mobile, charArrayExtra);
                    }
                    LockPatternActivity.this.finishWithResultOk(charArrayExtra);
                }
            } else if (LockPatternActivity.ACTION_COMPARE_PATTERN.equals(LockPatternActivity.this.getIntent().getAction())) {
                LockPatternActivity lockPatternActivity3 = LockPatternActivity.this;
                lockPatternActivity3.startActivity((Intent) lockPatternActivity3.getIntent().getParcelableExtra(LockPatternActivity.EXTRA_INTENT_ACTIVITY_FORGOT_PATTERN));
                LockPatternActivity.this.finishWithNegativeResult(3);
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    };
    private final Runnable mLockPatternViewReloader = new Runnable() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.7
        @Override // java.lang.Runnable
        public void run() {
            LockPatternActivity.this.mLockPatternView.clearPattern();
            LockPatternActivity.this.mLockPatternViewListener.onPatternCleared();
        }
    };
    private View view01;
    private View view02;
    private View view03;
    private View view04;
    private View view05;
    private View view06;
    private View view07;
    private View view08;
    private View view09;
    private View[] views = {this.view01, this.view02, this.view03, this.view04, this.view05, this.view06, this.view07, this.view08, this.view09};
    private int[] views_ids = {C11982R.C11985id.key_hint_01, C11982R.C11985id.key_hint_02, C11982R.C11985id.key_hint_03, C11982R.C11985id.key_hint_04, C11982R.C11985id.key_hint_05, C11982R.C11985id.key_hint_06, C11982R.C11985id.key_hint_07, C11982R.C11985id.key_hint_08, C11982R.C11985id.key_hint_09};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: group.pals.android.lib.ui.lockpattern.LockPatternActivity$ButtonOkCommand */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum ButtonOkCommand {
        CONTINUE,
        FORGOT_PATTERN,
        DONE
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: group.pals.android.lib.ui.lockpattern.LockPatternActivity$CallBackInterface */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface CallBackInterface {
        void gotoLogin();

        void onKeyDownForBack();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        if (getIntent().hasExtra(EXTRA_THEME)) {
            setTheme(getIntent().getIntExtra(EXTRA_THEME, C11982R.C11987style.Alp_Theme_Dark));
        }
        super.onCreate(bundle);
        this.mMinWiredDots = DisplayPrefs.getMinWiredDots(this);
        this.mMaxRetry = DisplayPrefs.getMaxRetry(this);
        this.mAutoSave = SecurityPrefs.isAutoSavePattern(this);
        char[] encrypterClass = SecurityPrefs.getEncrypterClass(this);
        if (encrypterClass != null) {
            this.mEncrypter = (IEncrypter) Class.forName(new String(encrypterClass), false, getClassLoader()).newInstance();
        }
        this.mobile = getIntent().getStringExtra(EXTRA_INTENT_ACTIVITY_ACCOUNT_INFO);
        this.isH5 = getIntent().getBooleanExtra("isH5", false);
        this.mIntentResult = new Intent();
        setResult(0, this.mIntentResult);
        initContentView();
        NBSAppInstrumentation.activityCreateEndIns();
    }

    private boolean fixOrientation() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mActivityInfo");
            declaredField.setAccessible(true);
            ((ActivityInfo) declaredField.get(this)).screenOrientation = -1;
            declaredField.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isTranslucentOrFloating() {
        Method method;
        boolean booleanValue;
        boolean z = false;
        try {
            TypedArray obtainStyledAttributes = obtainStyledAttributes((int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null));
            method = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            method.setAccessible(true);
            booleanValue = ((Boolean) method.invoke(null, obtainStyledAttributes)).booleanValue();
        } catch (Exception e) {
            e = e;
        }
        try {
            method.setAccessible(false);
            return booleanValue;
        } catch (Exception e2) {
            e = e2;
            z = booleanValue;
            e.printStackTrace();
            return z;
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        initContentView();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && ACTION_COMPARE_PATTERN.equals(getIntent().getAction())) {
            CallBackInterface callBackInterface2 = callBackInterface;
            if (callBackInterface2 != null) {
                callBackInterface2.onKeyDownForBack();
                return true;
            }
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initContentView() {
        boolean z;
        ArrayList<LockPatternView.Cell> arrayList;
        TextView textView = this.mTextInfo;
        CharSequence text = textView != null ? textView.getText() : null;
        LockPatternView lockPatternView = this.mLockPatternView;
        LockPatternView.DisplayMode displayMode = lockPatternView != null ? lockPatternView.getDisplayMode() : null;
        LockPatternView lockPatternView2 = this.mLockPatternView;
        List<LockPatternView.Cell> pattern = lockPatternView2 != null ? lockPatternView2.getPattern() : null;
        setContentView(C11982R.C11986layout.alp_lock_pattern_activity);
        C11988UI.adjustDialogSizeForLargeScreen(getWindow());
        this.mTextInfo = (TextView) findViewById(C11982R.C11985id.alp_textview_info);
        this.mLargeTextInfo = (TextView) findViewById(C11982R.C11985id.alp_large_textview_info);
        this.mTextAccountInfo = (TextView) findViewById(C11982R.C11985id.alp_textview_account_info);
        this.mLockPatternView = (LockPatternView) findViewById(C11982R.C11985id.alp_view_lock_pattern);
        this.mTextAccountInfo.setText(getAccountInfo());
        this.mFooter = findViewById(C11982R.C11985id.alp_viewgroup_footer);
        findViewById(C11982R.C11985id.close).setOnClickListener(new View.OnClickListener() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                LockPatternActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        findViewById(C11982R.C11985id.reset_pattern).setOnClickListener(new View.OnClickListener() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                LockPatternActivity.this.mBtnOkCmd = null;
                LockPatternActivity.this.mTextInfo = null;
                LockPatternActivity.this.getIntent().removeExtra(LockPatternActivity.EXTRA_PATTERN);
                LockPatternActivity.this.initContentView();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        findViewById(C11982R.C11985id.forgot_pattern).setOnClickListener(new View.OnClickListener() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                LockPatternActivity.this.showDialog();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        ((TextView) findViewById(C11982R.C11985id.reset_pattern)).setText(getResources().getString(C11982R.string.alp_msg_reset_pattern));
        ((TextView) findViewById(C11982R.C11985id.forgot_pattern)).setText(getResources().getString(C11982R.string.alp_msg_forgot_pattern));
        if (getResources().getBoolean(C11982R.bool.alp_is_large_screen)) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(C11982R.dimen.alp_lockpatternview_size);
            ViewGroup.LayoutParams layoutParams = this.mLockPatternView.getLayoutParams();
            layoutParams.width = dimensionPixelSize;
            layoutParams.height = dimensionPixelSize;
            this.mLockPatternView.setLayoutParams(layoutParams);
        }
        boolean z2 = true;
        try {
            z = Settings.System.getInt(getContentResolver(), "haptic_feedback_enabled", 0) != 0;
        } catch (Throwable unused) {
            z = false;
        }
        this.mLockPatternView.setTactileFeedbackEnabled(z);
        LockPatternView lockPatternView3 = this.mLockPatternView;
        if (!DisplayPrefs.isStealthMode(this) || ACTION_VERIFY_CAPTCHA.equals(getIntent().getAction())) {
            z2 = false;
        }
        lockPatternView3.setInStealthMode(z2);
        this.mLockPatternView.setOnPatternListener(this.mLockPatternViewListener);
        if (pattern != null && displayMode != null && !ACTION_VERIFY_CAPTCHA.equals(getIntent().getAction())) {
            this.mLockPatternView.setPattern(displayMode, pattern);
        }
        if (ACTION_CREATE_PATTERN.equals(getIntent().getAction())) {
            this.mFooter.setVisibility(0);
            findViewById(C11982R.C11985id.close).setVisibility(0);
            setupKeyHintViews();
            if (text != null) {
                this.mTextInfo.setText(text);
            } else {
                this.mTextInfo.setText(C11982R.string.alp_msg_draw_an_unlock_pattern);
                showTextViewInfo();
                this.mTextInfo.setTextColor(getResources().getColor(C11982R.C11983color.re_confirm_color));
            }
            if (this.mBtnOkCmd == null) {
                this.mBtnOkCmd = ButtonOkCommand.CONTINUE;
            }
            switch (this.mBtnOkCmd) {
                case CONTINUE:
                case DONE:
                default:
                    return;
            }
        } else if (ACTION_COMPARE_PATTERN.equals(getIntent().getAction())) {
            getWindow().setBackgroundDrawable(new ColorDrawable(-15848366));
            this.mTextAccountInfo.setText(getAccountInfo());
            this.mTextAccountInfo.setVisibility(0);
            this.mFooter.setVisibility(0);
            findViewById(C11982R.C11985id.forgot_pattern).setVisibility(0);
            findViewById(C11982R.C11985id.reset_pattern).setVisibility(8);
            if (TextUtils.isEmpty(text)) {
                showLargeTextViewInfo(C11982R.string.alp_msg_draw_pattern_to_unlock);
            } else {
                this.mTextInfo.setText(text);
            }
            if (getIntent().hasExtra(EXTRA_INTENT_ACTIVITY_FORGOT_PATTERN)) {
                this.mFooter.setVisibility(0);
            }
        } else if (ACTION_VERIFY_CAPTCHA.equals(getIntent().getAction())) {
            this.mTextInfo.setText(C11982R.string.alp_msg_redraw_pattern_to_confirm);
            showTextViewInfo();
            this.mTextInfo.setTextColor(getResources().getColor(C11982R.C11983color.re_confirm_color));
            if (getIntent().hasExtra(EXTRA_PATTERN)) {
                arrayList = getIntent().getParcelableArrayListExtra(EXTRA_PATTERN);
            } else {
                Intent intent = getIntent();
                String str = EXTRA_PATTERN;
                ArrayList<LockPatternView.Cell> genCaptchaPattern = LockPatternUtils.genCaptchaPattern(DisplayPrefs.getCaptchaWiredDots(this));
                intent.putParcelableArrayListExtra(str, genCaptchaPattern);
                arrayList = genCaptchaPattern;
            }
            this.mLockPatternView.setPattern(LockPatternView.DisplayMode.Animate, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doComparePattern(List<LockPatternView.Cell> list) {
        boolean equals;
        if (list == null) {
            return;
        }
        if (ACTION_COMPARE_PATTERN.equals(getIntent().getAction())) {
            char[] charArrayExtra = getIntent().getCharArrayExtra(EXTRA_PATTERN);
            if (charArrayExtra == null) {
                charArrayExtra = SecurityPrefs.getPattern(this, this.mobile);
            }
            if (charArrayExtra != null) {
                IEncrypter iEncrypter = this.mEncrypter;
                if (iEncrypter != null) {
                    equals = list.equals(iEncrypter.decrypt(this, charArrayExtra));
                } else {
                    equals = Arrays.equals(charArrayExtra, LockPatternUtils.patternToSha1(list).toCharArray());
                }
            } else {
                equals = false;
            }
        } else {
            equals = ACTION_VERIFY_CAPTCHA.equals(getIntent().getAction()) ? list.equals(getIntent().getParcelableArrayListExtra(EXTRA_PATTERN)) : false;
        }
        if (equals) {
            finishWithResultOk(null);
            return;
        }
        this.mRetryCount++;
        this.mIntentResult.putExtra(EXTRA_RETRY_COUNT, this.mRetryCount);
        if (this.mRetryCount >= this.mMaxRetry) {
            showDialog();
            return;
        }
        this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
        showLargeTextViewInfo(0);
        this.mLockPatternView.postDelayed(this.mLockPatternViewReloader, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCheckAndCreatePattern(List<LockPatternView.Cell> list) {
        boolean equals;
        if (list.size() < this.mMinWiredDots) {
            this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
            TextView textView = this.mTextInfo;
            Resources resources = getResources();
            int i = C11982R.plurals.alp_pmsg_connect_x_dots;
            int i2 = this.mMinWiredDots;
            textView.setText(resources.getQuantityString(i, i2, Integer.valueOf(i2)));
            showTextViewInfo();
            this.mTextInfo.setTextColor(getResources().getColor(C11982R.C11983color.limit_link_point_color));
            this.mLockPatternView.postDelayed(this.mLockPatternViewReloader, 0L);
        } else if (getIntent().hasExtra(EXTRA_PATTERN)) {
            IEncrypter iEncrypter = this.mEncrypter;
            if (iEncrypter != null) {
                equals = list.equals(iEncrypter.decrypt(this, getIntent().getCharArrayExtra(EXTRA_PATTERN)));
            } else {
                equals = Arrays.equals(getIntent().getCharArrayExtra(EXTRA_PATTERN), LockPatternUtils.patternToSha1(list).toCharArray());
            }
            if (equals) {
                this.mTextInfo.setText("");
                this.mBtnConfirmOnClickListener.onClick(null);
                return;
            }
            this.mTextInfo.setText(C11982R.string.alp_msg_redraw_pattern_to_confirm_error);
            showTextViewInfo();
            this.mTextInfo.setTextColor(getResources().getColor(C11982R.C11983color.limit_link_point_color));
            this.mFooter.setVisibility(0);
            findViewById(C11982R.C11985id.reset_pattern).setVisibility(0);
            this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
            this.mLockPatternView.postDelayed(this.mLockPatternViewReloader, 0L);
            ShakeUtils.vibrate(this, 500L);
        } else {
            Intent intent = getIntent();
            String str = EXTRA_PATTERN;
            IEncrypter iEncrypter2 = this.mEncrypter;
            intent.putExtra(str, iEncrypter2 != null ? iEncrypter2.encrypt(this, list) : LockPatternUtils.patternToSha1(list).toCharArray());
            this.mBtnConfirmOnClickListener.onClick(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishWithResultOk(char[] cArr) {
        if (ACTION_CREATE_PATTERN.equals(getIntent().getAction())) {
            this.mIntentResult.putExtra(EXTRA_PATTERN, cArr);
        } else {
            this.mIntentResult.putExtra(EXTRA_RETRY_COUNT, this.mRetryCount + 1);
        }
        setResult(-1, this.mIntentResult);
        ResultReceiver resultReceiver = (ResultReceiver) getIntent().getParcelableExtra(EXTRA_RESULT_RECEIVER);
        if (resultReceiver != null) {
            Bundle bundle = new Bundle();
            if (ACTION_CREATE_PATTERN.equals(getIntent().getAction())) {
                bundle.putCharArray(EXTRA_PATTERN, cArr);
            } else {
                bundle.putInt(EXTRA_RETRY_COUNT, this.mRetryCount + 1);
            }
            resultReceiver.send(-1, bundle);
        }
        PendingIntent pendingIntent = (PendingIntent) getIntent().getParcelableExtra(EXTRA_PENDING_INTENT_OK);
        if (pendingIntent != null) {
            try {
                pendingIntent.send(this, -1, this.mIntentResult);
            } catch (Throwable unused) {
            }
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishWithNegativeResult(int i) {
        if (ACTION_COMPARE_PATTERN.equals(getIntent().getAction())) {
            this.mIntentResult.putExtra(EXTRA_RETRY_COUNT, this.mRetryCount);
        }
        setResult(i, this.mIntentResult);
        ResultReceiver resultReceiver = (ResultReceiver) getIntent().getParcelableExtra(EXTRA_RESULT_RECEIVER);
        if (resultReceiver != null) {
            Bundle bundle = null;
            if (ACTION_COMPARE_PATTERN.equals(getIntent().getAction())) {
                bundle = new Bundle();
                bundle.putInt(EXTRA_RETRY_COUNT, this.mRetryCount);
            }
            resultReceiver.send(i, bundle);
        }
        PendingIntent pendingIntent = (PendingIntent) getIntent().getParcelableExtra(EXTRA_PENDING_INTENT_CANCELLED);
        if (pendingIntent != null) {
            try {
                pendingIntent.send(this, i, this.mIntentResult);
            } catch (Throwable unused) {
            }
        }
        finish();
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(C11982R.string.alp_msg_forgot_pattern_dlg);
        builder.setPositiveButton(C11982R.string.alp_msg_re_login, new DialogInterface.OnClickListener() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                Tracker.onClick(dialogInterface, i2);
                LockPatternActivity.this.gotoLogin();
            }
        });
        builder.setNegativeButton(C11982R.string.alp_msg_cancel, new DialogInterface.OnClickListener() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                Tracker.onClick(dialogInterface, i2);
                if (LockPatternActivity.this.mRetryCount >= LockPatternActivity.this.mMaxRetry) {
                    LockPatternActivity.this.gotoLogin();
                }
                if (LockPatternActivity.this.isH5) {
                    LockPatternActivity.this.finish();
                }
                dialogInterface.cancel();
            }
        });
        builder.setCancelable(false);
        return builder.create();
    }

    public void showDialog() {
        try {
            final Dialog dialog = new Dialog(this, C11982R.C11987style.CustomDialog_lock);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
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
            View inflate = getLayoutInflater().inflate(C11982R.C11986layout.lock_custom_dialog, (ViewGroup) null);
            ((TextView) inflate.findViewById(C11982R.C11985id.custom_dialog_title_textview)).setText("温馨提示");
            ((TextView) inflate.findViewById(C11982R.C11985id.custom_dialog_message_textview)).setText(C11982R.string.alp_msg_forgot_pattern_dlg);
            Button button = (Button) inflate.findViewById(C11982R.C11985id.custom_dialog_ok_button);
            button.setText(C11982R.string.alp_msg_re_login);
            button.setOnClickListener(new View.OnClickListener() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    LockPatternActivity.this.gotoLogin();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(C11982R.C11985id.custom_dialog_cancel_button);
            button2.setTextColor(Color.parseColor("#666666"));
            button2.setVisibility(0);
            button.setBackgroundResource(C11982R.C11984drawable.custom_dialog_button_right_selector);
            button2.setText(C11982R.string.alp_msg_cancel);
            button2.setOnClickListener(new View.OnClickListener() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    if (LockPatternActivity.this.mRetryCount >= LockPatternActivity.this.mMaxRetry) {
                        LockPatternActivity.this.gotoLogin();
                    }
                    if (LockPatternActivity.this.isH5) {
                        LockPatternActivity.this.finish();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: group.pals.android.lib.ui.lockpattern.LockPatternActivity.12
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i4, KeyEvent keyEvent) {
                    return i4 == 4 && keyEvent.getRepeatCount() == 0;
                }
            });
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupKeyHintViews() {
        int i = 0;
        findViewById(C11982R.C11985id.key_hint_layout).setVisibility(0);
        while (true) {
            View[] viewArr = this.views;
            if (i >= viewArr.length) {
                return;
            }
            viewArr[i] = findViewById(this.views_ids[i]);
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showKeyHint(List<LockPatternView.Cell> list) {
        int i = 0;
        while (true) {
            View[] viewArr = this.views;
            if (i >= viewArr.length) {
                break;
            }
            viewArr[i].setBackgroundResource(default_key_hint);
            i++;
        }
        for (LockPatternView.Cell cell : list) {
            setKeyHint(cell);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setKeyHint(group.pals.android.lib.p392ui.lockpattern.widget.LockPatternView.Cell r4) {
        /*
            r3 = this;
            int r0 = r4.getRow()
            int r4 = r4.getColumn()
            r1 = 2
            r2 = 1
            if (r0 != 0) goto L14
            switch(r4) {
                case 0: goto L12;
                case 1: goto L10;
                case 2: goto L2e;
                default: goto Lf;
            }
        Lf:
            goto L2d
        L10:
            r1 = r2
            goto L2e
        L12:
            r1 = 0
            goto L2e
        L14:
            if (r0 != r2) goto L20
            switch(r4) {
                case 0: goto L1e;
                case 1: goto L1c;
                case 2: goto L1a;
                default: goto L19;
            }
        L19:
            goto L2d
        L1a:
            r1 = 5
            goto L2e
        L1c:
            r1 = 4
            goto L2e
        L1e:
            r1 = 3
            goto L2e
        L20:
            if (r0 != r1) goto L2d
            switch(r4) {
                case 0: goto L2b;
                case 1: goto L29;
                case 2: goto L26;
                default: goto L25;
            }
        L25:
            goto L2d
        L26:
            r1 = 8
            goto L2e
        L29:
            r1 = 7
            goto L2e
        L2b:
            r1 = 6
            goto L2e
        L2d:
            r1 = -1
        L2e:
            android.view.View[] r4 = r3.views
            r4 = r4[r1]
            int r0 = group.pals.android.lib.p392ui.lockpattern.LockPatternActivity.selected_key_hint
            r4.setBackgroundResource(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: group.pals.android.lib.p392ui.lockpattern.LockPatternActivity.setKeyHint(group.pals.android.lib.ui.lockpattern.widget.LockPatternView$Cell):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoLogin() {
        CallBackInterface callBackInterface2 = callBackInterface;
        if (callBackInterface2 != null) {
            callBackInterface2.gotoLogin();
            finish();
        }
    }

    public void showTextViewInfo() {
        this.mTextInfo.setVisibility(0);
        this.mLargeTextInfo.setVisibility(8);
    }

    public void showLargeTextViewInfo(int i) {
        if (i == 0) {
            this.mLargeTextInfo.setText(getResources().getString(C11982R.string.alp_msg_try_again, Integer.valueOf(this.mMaxRetry - this.mRetryCount)));
            this.mLargeTextInfo.setTextColor(getResources().getColor(C11982R.C11983color.limit_link_point_color));
            ShakeUtils.vibrate(this, 500L);
        } else {
            if (getIntent().getBooleanExtra("isReset", false)) {
                this.mLargeTextInfo.setText(C11982R.string.alp_msg_draw_pattern_to_unlock_reset);
            } else {
                this.mLargeTextInfo.setText(C11982R.string.alp_msg_draw_pattern_to_unlock);
            }
            this.mLargeTextInfo.setTextColor(getResources().getColor(C11982R.C11983color.input_hint_color));
        }
        this.mLargeTextInfo.setVisibility(0);
        this.mTextInfo.setVisibility(8);
    }

    private String getAccountInfo() {
        if (getIntent().hasExtra(EXTRA_INTENT_ACTIVITY_ACCOUNT_INFO)) {
            String stringExtra = getIntent().getStringExtra(EXTRA_INTENT_ACTIVITY_ACCOUNT_INFO);
            if (TextUtils.isEmpty(this.mTextInfo.getText().toString())) {
                this.mTextInfo.setText("请输入手势密码");
            }
            if (TextUtils.isEmpty(stringExtra) || stringExtra.length() != 11) {
                return stringExtra;
            }
            return stringExtra.substring(0, 3) + "****" + stringExtra.substring(7, 11);
        }
        Log.w("", "no account info.");
        return "";
    }
}
