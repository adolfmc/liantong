package com.gmrz.asm.p198fp.authenticator.kernel.ecc;

import android.app.Activity;
import android.content.Context;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import com.android.AKException;
import com.android.client.asm.sdk.IMatcher;
import com.gmrz.android.client.utils.Charsets;
import com.gmrz.android.client.utils.Logger;
import com.gmrz.asm.p198fp.authenticator.CryptoStore;
import com.gmrz.asm.p198fp.authenticator.matcherparams.KSMatcherInParams;
import com.gmrz.asm.p198fp.authenticator.matcherparams.KSMatcherOutParams;
import com.gmrz.asm.p198fp.authui.FingerprintOperation;
import com.gmrz.authenticationso.AuthKernel;
import com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel;
import com.utils.AAID;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.KeyStore;
import java.security.Signature;
import java.util.UUID;
import java.util.Vector;

/* renamed from: com.gmrz.asm.fp.authenticator.kernel.ecc.FpAuthenticatorKernel */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FpAuthenticatorKernel extends KSAuthenticatorKernel {
    private static final String TAG = "ECC-Kernel";
    private static boolean mIsRegistrationOp;
    private static Activity sCallerActivity;

    public FpAuthenticatorKernel(Context context, IMatcher iMatcher) throws AKException {
        super(context, iMatcher);
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public String performInitJni(boolean z) {
        Logger.m15889i(TAG, "use new aaid or old aaid, " + z);
        if (z) {
            return AuthKernel.initJni(mContext, true, this, AAID.FINGER_ECC_NEW);
        }
        return AuthKernel.initJni(mContext, false, this, AAID.FINGER_ECC);
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] performProcessJni(byte[] bArr) {
        return AuthKernel.processJni(bArr);
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] exportPublicKey(byte[] bArr) {
        Logger.m15889i(TAG, "exportPublicKey");
        try {
            byte[] fpsPublicKeyECDSA = CryptoStore.getFpsPublicKeyECDSA(mContext, new String(bArr, Charsets.utf8Charset), true);
            Logger.m15889i(TAG, "Key Export completed");
            return fpsPublicKeyECDSA;
        } catch (Exception e) {
            e.printStackTrace();
            Logger.m15892e(TAG, "Exception caught");
            this.statusCode = 1;
            return null;
        }
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] signData(byte[] bArr, byte[] bArr2) {
        try {
            String str = new String(bArr, Charsets.utf8Charset);
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (keyStore.getKey(str, null) == null) {
                throw new Exception("Unable to get the signing key by name " + str);
            }
            Logger.m15889i(TAG, "Begin Sign command");
            Vector<byte[]> vector = new Vector<>(1);
            IMatcher.RESULT fpSignData = fpSignData(bArr, bArr2, vector);
            Logger.m15895d(TAG, "signData result:" + fpSignData);
            switch (fpSignData) {
                case SUCCESS:
                    byte[] bArr3 = vector.get(0);
                    Logger.m15889i(TAG, "Sign Command completed");
                    ByteBuffer allocate = ByteBuffer.allocate(bArr3.length + 2);
                    allocate.order(ByteOrder.LITTLE_ENDIAN);
                    allocate.putShort((short) bArr3.length);
                    allocate.put(bArr3);
                    return allocate.array();
                case FINGER_SET_CHANGE:
                    this.statusCode = 9;
                    return null;
                case CHANGE_AUTHENTICATOR:
                    this.statusCode = 11;
                    return null;
                case CANCEL:
                    this.statusCode = 13;
                    return null;
                case USER_BIOMETRIC_PREFERRED_IRIS:
                    this.statusCode = 14;
                    return null;
                default:
                    this.statusCode = 1;
                    return null;
            }
        } catch (KeyPermanentlyInvalidatedException e) {
            e.printStackTrace();
            Logger.m15892e(TAG, "指纹集发生变化");
            this.statusCode = 9;
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            Logger.m15892e(TAG, "Signing has failed");
            this.statusCode = 1;
            return null;
        }
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] encryptDecryptData(byte[] bArr, byte[] bArr2) {
        return new byte[0];
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] generateKeyPair() {
        try {
            Logger.m15889i(TAG, "Begin Key generation");
            mIsRegistrationOp = true;
            byte[] generateKeypairUsingFpKeyStore = generateKeypairUsingFpKeyStore();
            ByteBuffer allocate = ByteBuffer.allocate(generateKeypairUsingFpKeyStore.length + 2 + 1);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putShort((short) (generateKeypairUsingFpKeyStore.length + 1));
            allocate.put(generateKeypairUsingFpKeyStore);
            Logger.m15889i(TAG, "End Key generation");
            return allocate.array();
        } catch (Exception e) {
            Logger.m15891e(TAG, "KeyGeneration failed.", e);
            this.statusCode = 1;
            return null;
        }
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public void removeKey(byte[] bArr) {
        try {
            Logger.m15889i(TAG, "Begin remove key.");
            CryptoStore.removeKey(mContext, new String(bArr, Charsets.utf8Charset), true);
        } catch (Exception e) {
            Logger.m15891e(TAG, "removeKey failed.", e);
            this.statusCode = 1;
        }
    }

    protected IMatcher.RESULT fpSignData(byte[] bArr, byte[] bArr2, Vector<byte[]> vector) throws Exception {
        KSMatcherOutParams kSMatcherOutParams;
        KSMatcherInParams matchUI = new KSMatcherInParams().setCustomUI(mMatcherInParams.getCustomUI()).setAntihammeringCallback(mMatcherInParams.getAntiHammeringCallback()).setFinalChallenge(mMatcherInParams.getFinalChallenge()).setTransText(mMatcherInParams.getTransText()).setSignatureObject(CryptoStore.initSignatureECDSA(mContext, new String(bArr, Charsets.utf8Charset), true)).setMatchUI(mMatcherInParams.m_matcherUI);
        Logger.m15895d("mIsRegistrationOp", Boolean.toString(mIsRegistrationOp));
        if (mIsRegistrationOp) {
            kSMatcherOutParams = (KSMatcherOutParams) mMatcher.register(matchUI, sCallerActivity);
        } else {
            kSMatcherOutParams = (KSMatcherOutParams) mMatcher.authenticate(matchUI, sCallerActivity);
        }
        Logger.m15889i(TAG, "outParam: " + kSMatcherOutParams.getMatchResult() + ": compareWithSuccess: " + (true ^ kSMatcherOutParams.getMatchResult().equals(IMatcher.RESULT.SUCCESS)));
        if (kSMatcherOutParams.getMatchResult() != IMatcher.RESULT.SUCCESS) {
            Logger.m15889i(TAG, "Failed");
            return kSMatcherOutParams.getMatchResult();
        }
        Logger.m15889i(TAG, "AKManaged Matcher returned Success. get signature.");
        Signature authenticatedSignature = kSMatcherOutParams.getAuthenticatedSignature();
        if (authenticatedSignature == null) {
            Logger.m15892e(TAG, "authed signature from outParams is null");
            return IMatcher.RESULT.MISMATCH;
        }
        byte[] signDataUsingAuthenticatedSignature = signDataUsingAuthenticatedSignature(bArr2, authenticatedSignature);
        Logger.m15895d(TAG, "fpSignData complete");
        vector.add(0, signDataUsingAuthenticatedSignature);
        return IMatcher.RESULT.SUCCESS;
    }

    private byte[] signDataUsingAuthenticatedSignature(byte[] bArr, Signature signature) throws Exception {
        Logger.m15895d(TAG, "signDataUsingAuthenticatedCrypto");
        signature.update(bArr);
        byte[] packageSignedDataECDSA = CryptoStore.packageSignedDataECDSA(signature.sign());
        Logger.m15895d(TAG, "signDataUsingAuthenticatedCrypto complete");
        return packageSignedDataECDSA;
    }

    protected byte[] generateKeypairUsingFpKeyStore() throws Exception {
        Logger.m15895d(TAG, "Creating Protected Key");
        String uuid = UUID.randomUUID().toString();
        if (!CryptoStore.generateKeyPairECDSA(mContext, uuid)) {
            if (!new FingerprintOperation(mContext).hasEnrolledFingerprints()) {
                mMatcher.register(new KSMatcherInParams().setCustomUI(mMatcherInParams.getCustomUI()).setAntihammeringCallback(mMatcherInParams.getAntiHammeringCallback()).setMatchUI(mMatcherInParams.m_matcherUI), sCallerActivity);
                this.statusCode = 13;
            } else {
                throw new Exception("FpCryptoStoreUtils.generateKsEcdsaKeyPair failed");
            }
        }
        Logger.m15895d(TAG, "FPSUI: uuid: " + uuid);
        return uuid.getBytes(Charsets.utf8Charset);
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel, com.android.client.asm.sdk.IAuthenticatorKernel
    public void setCallerActivity(Activity activity) {
        sCallerActivity = activity;
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel, com.android.client.asm.sdk.IAuthenticatorKernel
    public boolean postProcess() {
        super.postProcess();
        mIsRegistrationOp = false;
        return true;
    }
}
