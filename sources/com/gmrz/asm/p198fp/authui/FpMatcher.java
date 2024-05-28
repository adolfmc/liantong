package com.gmrz.asm.p198fp.authui;

import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.client.asm.sdk.IMatcher;
import com.android.client.asm.sdk.ProtocolType;
import com.android.client.asm.sdk.UVTMatcherInParams;
import com.android.client.asm.sdk.UVTMatcherOutParams;
import com.gmrz.asm.p198fp.authenticator.matcherparams.KSMatcherInParams;
import com.gmrz.asm.p198fp.authenticator.matcherparams.KSMatcherOutParams;
import com.gmrz.asm.p198fp.authui.params.FpParameter;
import com.gmrz.asm.p198fp.authui.params.FpResult;
import com.gmrz.asm.p198fp.authui.view.FpActivity;
import com.gmrz.asm.p198fp.port.CustomActionOnMatcherUI;
import com.gmrz.asm.p198fp.utils.Logger;
import java.lang.ref.WeakReference;
import java.security.InvalidParameterException;
import java.security.Signature;
import javax.crypto.Cipher;

/* renamed from: com.gmrz.asm.fp.authui.FpMatcher */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FpMatcher implements IMatcher {
    private static final String TAG = "FpMatcher";
    private static CustomActionOnMatcherUI customActionCallback;
    private final Context mContext;
    private Cipher mCryptoCipher;
    private FingerprintManager.CryptoObject mCryptoObjectIn;
    private Signature mCryptoSignature;
    private final Object lockObject = new Object();
    private IMatcher.RESULT mResult = IMatcher.RESULT.ERRORAUTH;

    @Override // com.android.client.asm.sdk.IMatcher
    public void cancel() {
    }

    public FpMatcher(Context context, ProtocolType protocolType) {
        this.mContext = context;
    }

    public static void setCustomActionCallback(CustomActionOnMatcherUI customActionOnMatcherUI) {
        customActionCallback = customActionOnMatcherUI;
    }

    private void showUI(FpParameter fpParameter, Activity activity) {
        if (activity == null) {
            Logger.m15751e(TAG, "FpMatcher: callerActivity is null");
            return;
        }
        Logger.m15753d(TAG, "@@@ fingerprint MATCHER_UI caller Activity instance:: " + activity.getLocalClassName());
        FpActivity.showUI(this.mContext, fpParameter);
        Logger.m15753d(TAG, "showUI prepare lock");
        lock();
        Logger.m15753d(TAG, "showUI prepare going");
    }

    @Override // com.android.client.asm.sdk.IMatcher
    public IMatcher.MatcherOutParams authenticate(IMatcher.MatcherInParams matcherInParams, Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            Logger.m15751e(TAG, "device current version below Android M");
            return null;
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            Logger.m15751e(TAG, "don't allow main thread use");
            throw new IllegalStateException("don't allow main thread use");
        } else {
            FingerprintManager fingerprintManager = (FingerprintManager) this.mContext.getSystemService(FingerprintManager.class);
            if (!fingerprintManager.isHardwareDetected() || !fingerprintManager.hasEnrolledFingerprints()) {
                Logger.m15751e(TAG, "Fingerprint not enrolled");
                return null;
            }
            try {
                Logger.m15753d(TAG, "Creating Key");
                KSMatcherInParams kSMatcherInParams = (KSMatcherInParams) matcherInParams;
                if (kSMatcherInParams == null) {
                    throw new InvalidParameterException("Invalid KeyMatcherInparams ");
                }
                Signature signatureObject = kSMatcherInParams.getSignatureObject();
                if (signatureObject != null) {
                    this.mCryptoObjectIn = new FingerprintManager.CryptoObject(signatureObject);
                }
                Cipher cipherObject = kSMatcherInParams.getCipherObject();
                if (cipherObject != null) {
                    this.mCryptoObjectIn = new FingerprintManager.CryptoObject(cipherObject);
                }
                UserVerifyResultHandler userVerifyResultHandler = new UserVerifyResultHandler(Looper.getMainLooper());
                userVerifyResultHandler.setMatcher(this);
                FpParameter resultHandler = FpParameter.Builder().setCryptoObject(this.mCryptoObjectIn).setResultHandler(userVerifyResultHandler);
                String transText = kSMatcherInParams.getTransText();
                if (transText != null && transText.length() > 0) {
                    resultHandler.setTransaction(true);
                    resultHandler.setTransaction(transText);
                }
                IMatcher.MatcherUI matcherUI = matcherInParams.m_matcherUI;
                if (matcherUI != null) {
                    Logger.m15753d(TAG, "authenticate max:" + matcherUI.getMaxMiss());
                    Logger.m15753d(TAG, "authenticate title:" + matcherUI.getTitle());
                    resultHandler.setMaxMiss(matcherUI.getMaxMiss());
                    resultHandler.setTitle(matcherUI.getTitle());
                }
                showUI(resultHandler, activity);
                if (this.mResult != IMatcher.RESULT.SUCCESS) {
                    Logger.m15751e(TAG, "register failed by fingerprint:" + this.mResult);
                }
                return new KSMatcherOutParams.KSMatcherOutParamsBuilder().setMatchResult(this.mResult).setAuthenticatedSignature(this.mCryptoSignature).setAuthenticatedCipher(this.mCryptoCipher).createKSMatcherOutParams();
            } catch (RuntimeException e) {
                Logger.m15751e(TAG, "Key Generation failed e:" + e.getMessage());
                return null;
            }
        }
    }

    @Override // com.android.client.asm.sdk.IMatcher
    public IMatcher.MatcherOutParams register(IMatcher.MatcherInParams matcherInParams, Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            Logger.m15751e(TAG, "device current version below Android M");
            return null;
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            Logger.m15751e(TAG, "don't allow main thread use");
            throw new IllegalStateException("don't allow main thread use");
        } else {
            KSMatcherInParams kSMatcherInParams = (KSMatcherInParams) matcherInParams;
            UserVerifyResultHandler userVerifyResultHandler = new UserVerifyResultHandler(Looper.getMainLooper());
            userVerifyResultHandler.setMatcher(this);
            Signature signatureObject = kSMatcherInParams.getSignatureObject();
            Cipher cipherObject = kSMatcherInParams.getCipherObject();
            if (signatureObject == null && cipherObject == null) {
                Logger.m15753d(TAG, "signature object and cipher object are null");
                this.mCryptoObjectIn = null;
            } else {
                if (signatureObject != null) {
                    Logger.m15753d(TAG, "signature object is not null");
                    this.mCryptoObjectIn = new FingerprintManager.CryptoObject(signatureObject);
                }
                if (cipherObject != null) {
                    Logger.m15753d(TAG, "cipherObject object is not null");
                    this.mCryptoObjectIn = new FingerprintManager.CryptoObject(cipherObject);
                }
            }
            FpParameter resultHandler = FpParameter.Builder().setCryptoObject(this.mCryptoObjectIn).setResultHandler(userVerifyResultHandler);
            IMatcher.MatcherUI matcherUI = matcherInParams.m_matcherUI;
            if (matcherUI != null) {
                resultHandler.setMaxMiss(matcherUI.getMaxMiss());
                resultHandler.setTitle(matcherUI.getTitle());
            }
            showUI(resultHandler, activity);
            if (this.mResult != IMatcher.RESULT.SUCCESS) {
                Logger.m15751e(TAG, "authenticate failed by fingerprint: " + this.mResult);
            }
            return new KSMatcherOutParams.KSMatcherOutParamsBuilder().setMatchResult(this.mResult).setAuthenticatedSignature(this.mCryptoSignature).setAuthenticatedCipher(this.mCryptoCipher).createKSMatcherOutParams();
        }
    }

    @Override // com.android.client.asm.sdk.IMatcher
    public IMatcher.MatcherSettingsOutParams settings(IMatcher.MatcherSettingsInParams matcherSettingsInParams) {
        return new IMatcher.MatcherSettingsOutParams(IMatcher.RESULT.ERRORAUTH);
    }

    @Override // com.android.client.asm.sdk.IMatcher
    public boolean isUserIDValid(byte[] bArr) {
        if (Build.VERSION.SDK_INT < 23) {
            Logger.m15751e(TAG, "device current version below Android M");
            return false;
        }
        FingerprintManager fingerprintManager = (FingerprintManager) this.mContext.getSystemService(FingerprintManager.class);
        if (!fingerprintManager.isHardwareDetected() || !fingerprintManager.hasEnrolledFingerprints()) {
            Logger.m15753d(TAG, "isUserIDValid return false");
            return false;
        }
        Logger.m15753d(TAG, "isUserIDValid return true");
        return true;
    }

    @Override // com.android.client.asm.sdk.IMatcher
    public IMatcher.MatcherDefinedParamsClassList getMatcherDefinedParamsClassList() {
        return new IMatcher.MatcherDefinedParamsClassList(UVTMatcherInParams.class, UVTMatcherOutParams.class, IMatcher.MatcherSettingsInParams.class, IMatcher.MatcherSettingsOutParams.class);
    }

    private void lock() {
        synchronized (this.lockObject) {
            try {
                this.lockObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void unlock() {
        synchronized (this.lockObject) {
            this.lockObject.notify();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResult(IMatcher.RESULT result, Object obj) {
        this.mResult = result;
        if (obj != null) {
            if (obj instanceof Signature) {
                this.mCryptoSignature = (Signature) obj;
            } else if (obj instanceof Cipher) {
                this.mCryptoCipher = (Cipher) obj;
            }
        }
    }

    /* renamed from: com.gmrz.asm.fp.authui.FpMatcher$UserVerifyResultHandler */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class UserVerifyResultHandler extends Handler {
        private WeakReference<FpMatcher> wake;

        UserVerifyResultHandler(Looper looper) {
            super(looper);
        }

        public void setMatcher(FpMatcher fpMatcher) {
            this.wake = new WeakReference<>(fpMatcher);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Cipher cipher;
            WeakReference<FpMatcher> weakReference;
            FpResult fpResult = (FpResult) message.obj;
            IMatcher.RESULT result = fpResult.result;
            Signature signature = null;
            if (IMatcher.RESULT.SUCCESS == result) {
                if (fpResult.signature != null) {
                    signature = fpResult.signature;
                    cipher = null;
                } else if (fpResult.cipher != null) {
                    cipher = fpResult.cipher;
                }
                weakReference = this.wake;
                if (weakReference != null || weakReference.get() == null) {
                }
                FpMatcher fpMatcher = this.wake.get();
                fpMatcher.setResult(result, signature);
                fpMatcher.setResult(result, cipher);
                fpMatcher.unlock();
                return;
            }
            cipher = null;
            weakReference = this.wake;
            if (weakReference != null) {
            }
        }
    }
}
