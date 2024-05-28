package com.gmrz.asm.p198fp.authenticator.kernel.ecc;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyPermanentlyInvalidatedException;
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
import com.utils.AAID;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

/* renamed from: com.gmrz.asm.fp.authenticator.kernel.ecc.FpAttestationAuthenticatorKernel */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FpAttestationAuthenticatorKernel extends KSAuthenticatorKernel implements IAuthenticatorKernel.IExtAuthenticatorKernel {
    private static final String TAG = "ECC-HKA-Kernel";
    private static final List<Extension> extensions = new ArrayList();

    /* renamed from: fc */
    private static byte[] f10356fc;
    private static boolean mIsRegistrationOp;
    private static Activity sCallerActivity;

    public FpAttestationAuthenticatorKernel(Context context, IMatcher iMatcher) throws AKException {
        super(context, iMatcher);
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public String performInitJni(boolean z) {
        return AuthKernel.initJni(mContext, z, this, AAID.FINGER_HKA_ECC);
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] performProcessJni(byte[] bArr) {
        return AuthKernel.processJni(bArr);
    }

    @Override // com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel
    public byte[] exportPublicKey(byte[] bArr) {
        Logger.m15889i(TAG, "exportPublicKey");
        try {
            String str = new String(bArr, Charsets.utf8Charset);
            Logger.m15889i(TAG, "exportPublicKey uuidBytes:" + str);
            byte[] fpsPublicKeyECDSA = CryptoStore.getFpsPublicKeyECDSA(mContext, str);
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
        if (Build.VERSION.SDK_INT < 23) {
            return null;
        }
        String str = new String(bArr, Charsets.utf8Charset);
        Logger.m15895d(TAG, String.format("key alias name: %s", str));
        try {
            if (CryptoStore.loadKeyStore(mContext).getKey(str, null) == null) {
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
            Logger.m15892e(TAG, "KeyStore key invalidated.");
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
            CryptoStore.removeKey(mContext, new String(bArr, Charsets.utf8Charset));
        } catch (Exception e) {
            Logger.m15891e(TAG, "removeKey failed.", e);
            this.statusCode = 1;
        }
    }

    protected IMatcher.RESULT fpSignData(byte[] bArr, byte[] bArr2, Vector<byte[]> vector) throws Exception {
        KSMatcherOutParams kSMatcherOutParams;
        String str = new String(bArr, Charsets.utf8Charset);
        if (CryptoStore.isFpSetChangedHuawei(mContext, str)) {
            Logger.m15892e(TAG, "fingerprint set has changed ...");
            return IMatcher.RESULT.FINGER_SET_CHANGE;
        }
        KSMatcherInParams matchUI = new KSMatcherInParams().setCustomUI(mMatcherInParams.getCustomUI()).setAntihammeringCallback(mMatcherInParams.getAntiHammeringCallback()).setFinalChallenge(mMatcherInParams.getFinalChallenge()).setTransText(mMatcherInParams.getTransText()).setSignatureObject(CryptoStore.initSignatureECDSA(mContext, str)).setMatchUI(mMatcherInParams.m_matcherUI);
        if (mIsRegistrationOp) {
            kSMatcherOutParams = (KSMatcherOutParams) mMatcher.register(matchUI, sCallerActivity);
        } else {
            kSMatcherOutParams = (KSMatcherOutParams) mMatcher.authenticate(matchUI, sCallerActivity);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("outParam: ");
        sb.append(kSMatcherOutParams.getMatchResult());
        sb.append(": compareWithSuccess: ");
        sb.append(!kSMatcherOutParams.getMatchResult().equals(IMatcher.RESULT.SUCCESS));
        Logger.m15889i(TAG, sb.toString());
        if (kSMatcherOutParams.getMatchResult() != IMatcher.RESULT.SUCCESS) {
            Logger.m15889i(TAG, "Failed");
            return kSMatcherOutParams.getMatchResult();
        }
        Logger.m15889i(TAG, "AKManaged Matcher returned. get signature.");
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
        boolean generateKeyPairECDSA;
        Logger.m15895d(TAG, "Creating Protected Key");
        String uuid = UUID.randomUUID().toString();
        if (Build.VERSION.SDK_INT < 24) {
            generateKeyPairECDSA = CryptoStore.generateKeyPairECDSA(mContext, uuid);
        } else {
            generateKeyPairECDSA = CryptoStore.generateKeyPairECDSA(mContext, uuid, f10356fc);
        }
        if (!generateKeyPairECDSA) {
            if (!new FingerprintOperation(mContext).hasEnrolledFingerprints()) {
                mMatcher.register(new KSMatcherInParams().setCustomUI(mMatcherInParams.getCustomUI()).setAntihammeringCallback(mMatcherInParams.getAntiHammeringCallback()).setMatchUI(mMatcherInParams.m_matcherUI), sCallerActivity);
                this.statusCode = 13;
            } else {
                throw new Exception("FpCryptoStoreUtils.generateKsEcdsaKeyPair failed");
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
        mIsRegistrationOp = false;
        extensions.clear();
        f10356fc = null;
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
        f10356fc = aKRequestParams.finalChallenge;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorKernel.IExtAuthenticatorKernel
    public List<Extension> extExtract(AKProcessor.AKResponseParams aKResponseParams) {
        Logger.m15895d(TAG, "extExtract");
        return extensions;
    }
}
