package com.gmrz.asm.p198fp.authenticator.kernel.p199gm;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.util.Log;
import com.android.AKException;
import com.android.client.asm.sdk.IAuthenticatorKernel;
import com.android.client.asm.sdk.IMatcher;
import com.android.uaf.asmcore.AKProcessor;
import com.gmrz.android.client.asm.api.uaf.json.Extension;
import com.gmrz.android.client.utils.Charsets;
import com.gmrz.android.client.utils.Logger;
import com.gmrz.asm.p198fp.authenticator.CryptoStore;
import com.gmrz.asm.p198fp.authenticator.matcherparams.KSMatcherInParams;
import com.gmrz.asm.p198fp.authenticator.matcherparams.KSMatcherOutParams;
import com.gmrz.asm.p198fp.authui.FingerprintOperation;
import com.gmrz.authenticationso.AuthKernel;
import com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel;
import com.gmrz.authenticationso.utils.UtilByte;
import com.utils.AAID;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import javax.crypto.Cipher;

/* renamed from: com.gmrz.asm.fp.authenticator.kernel.gm.FpAttestationAuthenticatorKernel */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FpAttestationAuthenticatorKernel extends KSAuthenticatorKernel implements IAuthenticatorKernel.IExtAuthenticatorKernel {
    private static final String TAG = "GM-KA-Kernel";
    private static final List<Extension> extensions = new ArrayList();

    /* renamed from: fc */
    private static byte[] f10358fc;
    private static Activity sCallerActivity;
    private static boolean sIsRegisterOperation;

    public FpAttestationAuthenticatorKernel(Context context, IMatcher iMatcher) throws AKException {
        super(context, iMatcher);
        setAKDigestMethodSM3();
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public String performInitJni(boolean z) {
        return AuthKernel.initJni(mContext, z, this, AAID.FINGER_KA_GM);
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] performProcessJni(byte[] bArr) {
        Log.v(TAG, "send to Huhu => " + UtilByte.byte2hex(bArr));
        byte[] processJni = AuthKernel.processJni(bArr);
        Log.v(TAG, "received from Huhu <= " + UtilByte.byte2hex(processJni));
        return processJni;
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] encryptDecryptData(byte[] bArr, byte[] bArr2) {
        if (Build.VERSION.SDK_INT < 23) {
            return null;
        }
        String str = new String(bArr, Charsets.utf8Charset);
        Logger.m15895d(TAG, String.format("key alias name: %s", str));
        try {
            if (CryptoStore.loadKeyStore(mContext).getKey(str, null) == null) {
                throw new Exception("Unable to get the signing key by name " + str);
            }
            Logger.m15889i(TAG, "Begin encrypt or decrypt command");
            Vector<byte[]> vector = new Vector<>(1);
            IMatcher.RESULT fpEncryptDecryptData = fpEncryptDecryptData(bArr, bArr2, vector);
            Logger.m15895d(TAG, "data encrypt or decrypt result:" + fpEncryptDecryptData);
            switch (fpEncryptDecryptData) {
                case SUCCESS:
                    byte[] bArr3 = vector.get(0);
                    Logger.m15889i(TAG, "encrypt and decrypt command completed");
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
            Logger.m15892e(TAG, "KeyStore key invalidated.");
            this.statusCode = 9;
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            Logger.m15892e(TAG, "encrypt or decrypt failed");
            this.statusCode = 1;
            return null;
        }
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] generateKeyPair() {
        try {
            Logger.m15889i(TAG, "Begin Key generation");
            sIsRegisterOperation = true;
            byte[] generateKeypairUsingFpKeyStore = generateKeypairUsingFpKeyStore();
            ByteBuffer allocate = ByteBuffer.allocate(generateKeypairUsingFpKeyStore.length + 2 + 1);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putShort((short) (generateKeypairUsingFpKeyStore.length + 1));
            allocate.put(generateKeypairUsingFpKeyStore);
            Logger.m15889i(TAG, "End Key generation");
            return allocate.array();
        } catch (Exception e) {
            Logger.m15891e(TAG, "KeyGeneration failed.", e);
            e.printStackTrace();
            sIsRegisterOperation = false;
            this.statusCode = 1;
            return null;
        }
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public void removeKey(byte[] bArr) {
        try {
            Logger.m15889i(TAG, "Begin remove key.");
            CryptoStore.removeKey(mContext, new String(bArr, Charsets.utf8Charset));
        } catch (Exception e) {
            Logger.m15891e(TAG, "removeKey failed.", e);
            e.printStackTrace();
            this.statusCode = 1;
        }
    }

    protected IMatcher.RESULT fpEncryptDecryptData(byte[] bArr, byte[] bArr2, Vector<byte[]> vector) throws Exception {
        KSMatcherOutParams kSMatcherOutParams;
        byte[] doFinal;
        if (Build.VERSION.SDK_INT < 23) {
            Logger.m15892e(TAG, "device android version below Android M");
            return null;
        }
        String str = new String(bArr, Charsets.utf8Charset);
        Logger.m15895d(TAG, String.format("key alias name: %s", str));
        if (CryptoStore.isFpSetChangedHuawei(mContext, str)) {
            Logger.m15892e(TAG, "fingerprint set has changed ...");
            return IMatcher.RESULT.FINGER_SET_CHANGE;
        }
        Cipher initCipherRSA = CryptoStore.initCipherRSA(mContext, str, sIsRegisterOperation ? 1 : 2);
        if (initCipherRSA == null) {
            Logger.m15892e(TAG, "cipher RSA init failed");
            return IMatcher.RESULT.MISMATCH;
        }
        Logger.m15895d(TAG, "cipher RSA init successfully");
        KSMatcherInParams matchUI = new KSMatcherInParams().setCustomUI(mMatcherInParams.getCustomUI()).setAntihammeringCallback(mMatcherInParams.getAntiHammeringCallback()).setFinalChallenge(mMatcherInParams.getFinalChallenge()).setTransText(mMatcherInParams.getTransText()).setCipherObject(sIsRegisterOperation ? null : initCipherRSA).setMatchUI(mMatcherInParams.m_matcherUI);
        if (sIsRegisterOperation) {
            kSMatcherOutParams = (KSMatcherOutParams) mMatcher.register(matchUI, sCallerActivity);
        } else {
            kSMatcherOutParams = (KSMatcherOutParams) mMatcher.authenticate(matchUI, sCallerActivity);
        }
        if (kSMatcherOutParams.getMatchResult() != IMatcher.RESULT.SUCCESS) {
            Logger.m15889i(TAG, "Failed");
            return kSMatcherOutParams.getMatchResult();
        }
        Logger.m15889i(TAG, "AKManaged Matcher returned Success. next will get authenticated cipher.");
        if (sIsRegisterOperation) {
            Logger.m15895d(TAG, "Reg process: data to encrypt");
            doFinal = initCipherRSA.doFinal(bArr2);
        } else {
            Logger.m15895d(TAG, "Auth process: data to decrypt");
            Cipher authenticatedCipher = kSMatcherOutParams.getAuthenticatedCipher();
            if (authenticatedCipher == null) {
                Logger.m15892e(TAG, "authenticated cipher instance is null");
                return IMatcher.RESULT.MISMATCH;
            }
            doFinal = authenticatedCipher.doFinal(bArr2);
        }
        vector.add(0, doFinal);
        Logger.m15895d(TAG, "fpEncryptDecryptData complete");
        return IMatcher.RESULT.SUCCESS;
    }

    protected byte[] generateKeypairUsingFpKeyStore() throws Exception {
        String uuid = UUID.randomUUID().toString();
        if (!(Build.VERSION.SDK_INT < 24 ? false : CryptoStore.generateKeyPairRSA(mContext, uuid, f10358fc))) {
            if (!new FingerprintOperation(mContext).hasEnrolledFingerprints()) {
                mMatcher.register(new KSMatcherInParams().setCustomUI(mMatcherInParams.getCustomUI()).setAntihammeringCallback(mMatcherInParams.getAntiHammeringCallback()).setMatchUI(mMatcherInParams.m_matcherUI), sCallerActivity);
                this.statusCode = 13;
            } else {
                throw new Exception("FpCryptoStoreUtils.generateKsRsaKeyPair failed");
            }
        }
        Logger.m15895d(TAG, "FPSUI: uuid: " + uuid);
        exportKeyAttestationExtension(uuid);
        return uuid.getBytes(Charsets.utf8Charset);
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel, com.android.client.asm.sdk.IAuthenticatorKernel
    public void setCallerActivity(Activity activity) {
        sCallerActivity = activity;
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel, com.android.client.asm.sdk.IAuthenticatorKernel
    public boolean postProcess() {
        Logger.m15895d(TAG, "postProcess");
        super.postProcess();
        sIsRegisterOperation = false;
        extensions.clear();
        f10358fc = null;
        return true;
    }

    private void exportKeyAttestationExtension(String str) {
        Logger.m15895d(TAG, "exportKeyAttestationExtension");
        String exportKeyAttestation = CryptoStore.exportKeyAttestation(mContext, str);
        Extension extension = new Extension();
        extension.data = exportKeyAttestation;
        extension.f10152id = "fido.uaf.android.key_attestation";
        extension.fail_if_unknown = false;
        extensions.add(extension);
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorKernel.IExtAuthenticatorKernel
    public void extInit(AKProcessor.AKRequestParams aKRequestParams) {
        Logger.m15895d(TAG, "extInit");
        f10358fc = aKRequestParams.finalChallenge;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorKernel.IExtAuthenticatorKernel
    public List<Extension> extExtract(AKProcessor.AKResponseParams aKResponseParams) {
        Logger.m15895d(TAG, "extExtract");
        return extensions;
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] exportPublicKey(byte[] bArr) {
        return new byte[0];
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] signData(byte[] bArr, byte[] bArr2) {
        return new byte[0];
    }
}
