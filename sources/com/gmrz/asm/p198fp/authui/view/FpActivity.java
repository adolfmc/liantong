package com.gmrz.asm.p198fp.authui.view;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.client.asm.sdk.IMatcher;
import com.bytedance.applog.tracker.Tracker;
import com.gmrz.appsdk.util.Constant;
import com.gmrz.asm.p198fp.authui.FingerprintOperation;
import com.gmrz.asm.p198fp.authui.params.FpParameter;
import com.gmrz.asm.p198fp.authui.params.FpResult;
import com.gmrz.asm.p198fp.port.UserInterfaceParams;
import com.gmrz.asm.p198fp.utils.Logger;
import com.gmrz.fpasm.C4439R;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.security.Signature;
import java.util.concurrent.Executor;
import javax.crypto.Cipher;

@NBSInstrumented
/* renamed from: com.gmrz.asm.fp.authui.view.FpActivity */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FpActivity extends Activity implements View.OnClickListener {
    private static final int MAX_DEFAULT = 5;
    public static final String REQUEST_COUNT = "request_count";
    public static final String REQUEST_ISTRAN = "request_istran";
    public static final String REQUEST_TITLE = "request_title";
    public static final String REQUEST_TRAN = "request_tran";
    private static final String TAG = "FpActivity";
    private static FingerprintManager.CryptoObject mCryptoObject;
    private static Handler sResultHandler;
    public NBSTraceUnit _nbs_trace;
    private boolean isManageStart;
    private ImageView ivIcon;
    private FingerprintOperation mOperation;
    private int maxMismatchCount = 5;
    private int mismatchedCount = 0;
    private String strTitle;
    private String strTransText;
    private TextView tvScanFpHint;
    private TextView tvTransContent;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
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

    static /* synthetic */ int access$208(FpActivity fpActivity) {
        int i = fpActivity.mismatchedCount;
        fpActivity.mismatchedCount = i + 1;
        return i;
    }

    public static void showUI(Context context, FpParameter fpParameter) {
        if (fpParameter != null) {
            sResultHandler = fpParameter.getResultHandler();
            mCryptoObject = fpParameter.getCryptoObject();
            Logger.m15753d(TAG, "showUI:" + fpParameter);
            Intent intent = new Intent(context, FpActivity.class);
            intent.addFlags(335544320);
            intent.putExtra(REQUEST_TITLE, fpParameter.getTitle());
            intent.putExtra(REQUEST_COUNT, fpParameter.getMaxMiss());
            intent.putExtra(REQUEST_ISTRAN, fpParameter.isTransaction());
            if (fpParameter.isTransaction()) {
                intent.putExtra(REQUEST_TRAN, fpParameter.getTransaction());
            }
            context.startActivity(intent);
            return;
        }
        Logger.m15751e(TAG, "showUI error , paramter is null");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        this.mOperation = new FingerprintOperation(this);
        if (Build.VERSION.SDK_INT >= 28 && Constant.BIOMETRIC_UI && this.mOperation.hasEnrolledFingerprints()) {
            FpParameter fpParameter = new FpParameter();
            fpParameter.setTransaction(getIntent().getBooleanExtra(REQUEST_ISTRAN, false));
            fpParameter.setCryptoObject(mCryptoObject);
            fpParameter.setTransaction(getIntent().getStringExtra(REQUEST_TRAN));
            showBiometric(this, fpParameter);
        } else {
            View inflate = LayoutInflater.from(this).inflate(UserInterfaceParams.RES_ID_LAYOUT_MATCHER_UI, (ViewGroup) null);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            inflate.setLayoutParams(layoutParams);
            setContentView(inflate);
            inflate.findViewById(C4439R.C4442id.btn_cancel).setOnClickListener(this);
            this.isManageStart = false;
            this.mismatchedCount = 0;
            Intent intent = getIntent();
            this.strTitle = intent.getStringExtra(REQUEST_TITLE);
            if (intent.getStringExtra(REQUEST_TRAN) != null) {
                this.strTransText = intent.getStringExtra(REQUEST_TRAN);
            }
            this.ivIcon = (ImageView) inflate.findViewById(C4439R.C4442id.finger_imageview);
            this.tvScanFpHint = (TextView) inflate.findViewById(C4439R.C4442id.tv_scan_fp_hint);
            this.tvTransContent = (TextView) inflate.findViewById(C4439R.C4442id.tv_trans_content);
            this.maxMismatchCount = intent.getIntExtra(REQUEST_COUNT, 0);
            int i = this.maxMismatchCount;
            if (i == 0 || i > 5) {
                this.maxMismatchCount = 5;
            }
            Logger.m15753d(TAG, "strTitle: " + this.strTitle);
            Logger.m15753d(TAG, "max_mismatch_times: " + this.maxMismatchCount);
            if (!((KeyguardManager) getSystemService("keyguard")).isKeyguardSecure()) {
                Logger.m15751e(TAG, "Lock screen security not enabled in Settings");
                setNoEnrolledFingerprintUI();
                NBSAppInstrumentation.activityCreateEndIns();
                return;
            } else if (Build.VERSION.SDK_INT < 23) {
                NBSAppInstrumentation.activityCreateEndIns();
                return;
            } else if (checkSelfPermission("android.permission.USE_FINGERPRINT") != 0) {
                Logger.m15751e(TAG, "Fingerprint authentication permission not enabled");
                triggerCallback(IMatcher.RESULT.ERRORAUTH, null);
                NBSAppInstrumentation.activityCreateEndIns();
                return;
            } else if (!this.mOperation.hasEnrolledFingerprints()) {
                Logger.m15753d(TAG, "No Enrolled Fingerprints");
                setNoEnrolledFingerprintUI();
            } else {
                setScanningUI();
                startIdentify();
            }
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    public void setNoEnrolledFingerprintUI() {
        Logger.m15753d(TAG, "setNoEnrolledFingerprintUI called");
        this.ivIcon.setImageResource(UserInterfaceParams.RES_ID_ICON_FINGER_FAILED);
        this.tvScanFpHint.setTextColor(UserInterfaceParams.COLOR_ID_TEXT_FAILED);
        this.tvScanFpHint.setText(UserInterfaceParams.STRING_ID_HINT_NO_ENROLL_FINGERPRINTS_IN_SYSTEM);
    }

    public void setNoSetLockScreenUI() {
        Logger.m15753d(TAG, "setNoEnrolledFingerprintUI called");
        this.ivIcon.setImageResource(UserInterfaceParams.RES_ID_ICON_FINGER_FAILED);
        this.tvScanFpHint.setTextColor(UserInterfaceParams.COLOR_ID_TEXT_FAILED);
        this.tvScanFpHint.setText(UserInterfaceParams.STRING_ID_HINT_NO_SET_LOCK_SCREEN);
    }

    public void setScanningUI() {
        this.ivIcon.setImageResource(UserInterfaceParams.RES_ID_ICON_FINGER_NORMAL);
        this.tvScanFpHint.setTextColor(UserInterfaceParams.COLOR_ID_TEXT_NORMAL);
        this.tvScanFpHint.setText(UserInterfaceParams.STRING_ID_HINT_NORMAL);
        TextView textView = (TextView) findViewById(C4439R.C4442id.tv_title);
        if (!TextUtils.isEmpty(this.strTitle)) {
            textView.setText(this.strTitle);
            textView.setVisibility(0);
        }
        if (TextUtils.isEmpty(this.strTransText)) {
            return;
        }
        textView.setText(C4439R.string.transaction_confirmation);
        textView.setVisibility(0);
        this.tvTransContent.setText(this.strTransText);
        this.tvTransContent.setVisibility(0);
    }

    public void startIdentify() {
        Logger.m15753d("FpActivitycrypto in", "startIdentify:" + mCryptoObject);
        this.mOperation.startListening(mCryptoObject, new FingerprintManager.AuthenticationCallback() { // from class: com.gmrz.asm.fp.authui.view.FpActivity.1
            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
                Logger.m15753d(FpActivity.TAG, "onAuthenticationSucceeded");
                if (authenticationResult != null) {
                    FpActivity.this.setAuthPassedUI(authenticationResult);
                    return;
                }
                Logger.m15751e(FpActivity.TAG, "FingerprintId is not available!");
                FpActivity.this.setUnknownErrorUI();
            }

            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public void onAuthenticationFailed() {
                Logger.m15753d(FpActivity.TAG, "onAuthenticationFailed");
                FpActivity.access$208(FpActivity.this);
                Logger.m15753d(FpActivity.TAG, "mismatchedCount: " + FpActivity.this.mismatchedCount);
                if (FpActivity.this.mismatchedCount < 5) {
                    FpActivity fpActivity = FpActivity.this;
                    fpActivity.setMismatchUI(fpActivity.mismatchedCount >= FpActivity.this.maxMismatchCount);
                }
            }

            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public void onAuthenticationError(int i, CharSequence charSequence) {
                Logger.m15753d(FpActivity.TAG, "onAuthenticationError errorCode:" + i + " errString:" + ((Object) charSequence));
                if (i == 3) {
                    FpActivity.this.setTimeoutUI();
                } else if (i == 5 || i == 10) {
                    FpActivity.this.setCancelUI();
                } else if (i == 7) {
                    FpActivity.this.setMismatchUI(true);
                } else if (i == 9) {
                    FpActivity.this.setMismatchUI(true);
                } else {
                    FpActivity.this.setUnknownErrorUI();
                }
            }

            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public void onAuthenticationHelp(int i, CharSequence charSequence) {
                super.onAuthenticationHelp(i, charSequence);
                if (i == 1011) {
                    FpActivity.this.triggerCallback(IMatcher.RESULT.CANCEL, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAuthPassedUI(FingerprintManager.AuthenticationResult authenticationResult) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        this.ivIcon.setImageResource(UserInterfaceParams.RES_ID_ICON_FINGER_CORRECT);
        this.tvScanFpHint.setTextColor(UserInterfaceParams.COLOR_ID_TEXT_CORRECT);
        this.tvScanFpHint.setText(UserInterfaceParams.STRING_ID_HINT_AUTH_CORRECT);
        FingerprintManager.CryptoObject cryptoObject = authenticationResult.getCryptoObject();
        if (cryptoObject != null) {
            Logger.m15749i(TAG, "fingerprint crypto object is not null, get crypto object from it");
            Signature signature = cryptoObject.getSignature();
            if (signature != null) {
                Log.wtf(TAG, "signature is not null !!!");
                triggerCallback(IMatcher.RESULT.SUCCESS, signature);
                return;
            }
            Cipher cipher = cryptoObject.getCipher();
            if (cipher != null) {
                Log.wtf(TAG, "cipher is not null !!!");
                triggerCallback(IMatcher.RESULT.SUCCESS, cipher);
                return;
            }
            return;
        }
        Logger.m15749i(TAG, "fingerprint crypto object is null, fido 1.0 GM ALG fingerprint authenticator");
        triggerCallback(IMatcher.RESULT.SUCCESS, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMismatchUI(boolean z) {
        this.ivIcon.setImageResource(UserInterfaceParams.RES_ID_ICON_FINGER_FAILED);
        this.tvScanFpHint.setTextColor(UserInterfaceParams.COLOR_ID_TEXT_FAILED);
        if (z) {
            this.tvScanFpHint.setText(UserInterfaceParams.STRING_ID_HINT_TOO_MANY_ATTEMPTS);
            this.tvScanFpHint.startAnimation(AnimationUtils.loadAnimation(this, C4439R.anim.shake));
            new Handler().postDelayed(new Runnable() { // from class: com.gmrz.asm.fp.authui.view.FpActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    Logger.m15751e(FpActivity.TAG, "Maximum mismatch reached: " + FpActivity.this.maxMismatchCount + "count and UI closed.");
                    FpActivity.this.triggerCallback(IMatcher.RESULT.TOOMANYATTEMPTS, null);
                }
            }, 500L);
            return;
        }
        this.tvScanFpHint.setText(UserInterfaceParams.STRING_ID_HINT_AUTH_FAILED);
        this.tvScanFpHint.startAnimation(AnimationUtils.loadAnimation(this, C4439R.anim.shake));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCancelUI() {
        this.ivIcon.setImageResource(UserInterfaceParams.RES_ID_ICON_FINGER_FAILED);
        this.tvScanFpHint.setTextColor(UserInterfaceParams.COLOR_ID_TEXT_FAILED);
        this.tvScanFpHint.setText(C4439R.string.cancel);
        triggerCallback(IMatcher.RESULT.CANCEL, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimeoutUI() {
        this.ivIcon.setImageResource(UserInterfaceParams.RES_ID_ICON_FINGER_FAILED);
        this.tvScanFpHint.setTextColor(UserInterfaceParams.COLOR_ID_TEXT_FAILED);
        this.tvScanFpHint.setText(UserInterfaceParams.STRING_ID_HINT_TIME_OUT);
        triggerCallback(IMatcher.RESULT.TIMEOUT, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUnknownErrorUI() {
        this.ivIcon.setImageResource(UserInterfaceParams.RES_ID_ICON_FINGER_FAILED);
        this.tvScanFpHint.setTextColor(UserInterfaceParams.COLOR_ID_TEXT_FAILED);
        this.tvScanFpHint.setText(UserInterfaceParams.STRING_ID_HINT_UNKNOWN_ERROR);
        triggerCallback(IMatcher.RESULT.ERRORAUTH, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view.getId() == C4439R.C4442id.btn_cancel) {
            this.isManageStart = false;
            triggerCallback(IMatcher.RESULT.CANCEL, null);
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        triggerCallback(IMatcher.RESULT.CANCEL, null);
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        Logger.m15753d(TAG, "onResume()");
        if (this.isManageStart) {
            this.isManageStart = false;
            if (!this.mOperation.hasEnrolledFingerprints()) {
                Logger.m15753d(TAG, "Still No Enrolled Fingerprints");
                triggerCallback(IMatcher.RESULT.CANCEL, null);
            } else {
                setScanningUI();
                startIdentify();
            }
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        Logger.m15749i(TAG, "onPause()");
        if (this.isManageStart) {
            return;
        }
        triggerCallback(IMatcher.RESULT.CANCEL, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerCallback(IMatcher.RESULT result, Object obj) {
        Logger.m15753d(TAG, "triggerCallback");
        this.mOperation.stopListening();
        Message obtain = Message.obtain();
        obtain.arg1 = result.ordinal();
        FpResult result2 = new FpResult().setResult(result);
        if (obj != null) {
            if (obj instanceof Signature) {
                result2.signature = (Signature) obj;
            } else if (obj instanceof Cipher) {
                result2.cipher = (Cipher) obj;
            }
        }
        obtain.obj = result2;
        Handler handler = sResultHandler;
        if (handler != null) {
            handler.sendMessage(obtain);
            sResultHandler = null;
            finish();
        }
    }

    public static Object getReflectionValue(String str, String str2) {
        try {
            Class<?> cls = Class.forName(str);
            Constructor<?> constructor = cls.getConstructor(new Class[0]);
            Field declaredField = cls.getDeclaredField(str2);
            declaredField.setAccessible(true);
            return declaredField.get(constructor.newInstance(new Object[0]));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
            return false;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    private void showBiometric(Context context, FpParameter fpParameter) {
        String string;
        String str = "";
        if (fpParameter.isTransaction() && !TextUtils.isEmpty(fpParameter.getTransaction())) {
            str = fpParameter.getTransaction();
            string = context.getString(C4439R.string.transaction_confirmation);
        } else if (fpParameter.isTransaction()) {
            string = context.getString(C4439R.string.transaction_confirmation);
        } else {
            string = context.getString(C4439R.string.access_confirmation);
        }
        if (checkSelfPermission("android.permission.USE_BIOMETRIC") != 0) {
            Logger.m15751e(TAG, "USE_BIOMETRIC authentication permission not enabled");
            triggerCallback(IMatcher.RESULT.ERRORAUTH, null);
            return;
        }
        BiometricPrompt.AuthenticationCallback authenticationCallback = new BiometricPrompt.AuthenticationCallback() { // from class: com.gmrz.asm.fp.authui.view.FpActivity.3
            @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
                Logger.m15753d(FpActivity.TAG, "onAuthenticationSucceeded");
                if (authenticationResult != null) {
                    if (authenticationResult.getCryptoObject() == null) {
                        Logger.m15749i(FpActivity.TAG, "crypto object is null, fido 1.0 GM ALG in register processing.");
                        FpActivity.this.triggerCallback(IMatcher.RESULT.SUCCESS, null);
                        return;
                    }
                    Signature signature = authenticationResult.getCryptoObject().getSignature();
                    if (signature != null) {
                        FpActivity.this.triggerCallback(IMatcher.RESULT.SUCCESS, signature);
                        return;
                    }
                    Cipher cipher = authenticationResult.getCryptoObject().getCipher();
                    if (cipher != null) {
                        FpActivity.this.triggerCallback(IMatcher.RESULT.SUCCESS, cipher);
                        return;
                    }
                    return;
                }
                Logger.m15751e(FpActivity.TAG, "FingerprintId is not available!");
                FpActivity.this.triggerCallback(IMatcher.RESULT.ERRORAUTH, null);
            }

            @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int i, CharSequence charSequence) {
                Logger.m15753d(FpActivity.TAG, "onAuthenticationError");
                if (i == 3) {
                    FpActivity.this.triggerCallback(IMatcher.RESULT.TIMEOUT, null);
                } else if (i == 7) {
                    FpActivity.this.triggerCallback(IMatcher.RESULT.TOOMANYATTEMPTS, null);
                } else if (i == 5 || i == 10) {
                    FpActivity.this.triggerCallback(IMatcher.RESULT.CANCEL, null);
                } else {
                    FpActivity.this.triggerCallback(IMatcher.RESULT.ERRORAUTH, null);
                }
            }
        };
        BiometricPrompt build = new BiometricPrompt.Builder(context).setTitle(string).setSubtitle(str).setDescription(context.getString(C4439R.string.touch_finger_hint)).setNegativeButton(context.getString(C4439R.string.cancel), context.getMainExecutor(), new DialogInterface.OnClickListener() { // from class: com.gmrz.asm.fp.authui.view.FpActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Tracker.onClick(dialogInterface, i);
                Log.i(FpActivity.TAG, "Cancel button clicked");
                FpActivity.this.triggerCallback(IMatcher.RESULT.CANCEL, null);
            }
        }).build();
        FingerprintManager.CryptoObject cryptoObject = fpParameter.getCryptoObject();
        if (cryptoObject != null) {
            Signature signature = cryptoObject.getSignature();
            r3 = signature != null ? new BiometricPrompt.CryptoObject(signature) : null;
            Cipher cipher = cryptoObject.getCipher();
            if (cipher != null) {
                r3 = new BiometricPrompt.CryptoObject(cipher);
            }
        }
        Executor mainExecutor = context.getMainExecutor();
        CancellationSignal cancellationSignal = this.mOperation.getCancellationSignal();
        if (r3 == null) {
            build.authenticate(cancellationSignal, mainExecutor, authenticationCallback);
        } else {
            build.authenticate(r3, cancellationSignal, mainExecutor, authenticationCallback);
        }
    }
}
