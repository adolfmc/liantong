package com.gmrz.asm.p198fp.authenticator.matcherparams;

import com.android.client.asm.sdk.IMatcher;
import java.security.Signature;
import java.util.List;
import javax.crypto.Cipher;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.authenticator.matcherparams.KSMatcherOutParams */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class KSMatcherOutParams extends IMatcher.MatcherOutParams {
    private Cipher cipher;
    private Signature signature;

    public KSMatcherOutParams(IMatcher.RESULT result, byte[] bArr) {
        super(result, bArr, null);
    }

    public KSMatcherOutParams(IMatcher.RESULT result, byte[] bArr, List<IMatcher.Extension> list, Signature signature) {
        super(result, bArr, list);
        this.signature = signature;
    }

    public KSMatcherOutParams(IMatcher.RESULT result, byte[] bArr, List<IMatcher.Extension> list, Cipher cipher) {
        super(result, bArr, list);
        this.cipher = cipher;
    }

    public KSMatcherOutParams setResult(IMatcher.RESULT result) {
        this.m_MatchResult = result;
        return this;
    }

    public KSMatcherOutParams setUserID(byte[] bArr) {
        this.m_UserID = bArr;
        return this;
    }

    public KSMatcherOutParams setAuthenticatedSignature(Signature signature) {
        this.signature = signature;
        return this;
    }

    public Signature getAuthenticatedSignature() {
        return this.signature;
    }

    public KSMatcherOutParams setAuthenticatedCipher(Cipher cipher) {
        this.cipher = cipher;
        return this;
    }

    public Cipher getAuthenticatedCipher() {
        return this.cipher;
    }

    /* renamed from: com.gmrz.asm.fp.authenticator.matcherparams.KSMatcherOutParams$KSMatcherOutParamsBuilder */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class KSMatcherOutParamsBuilder {
        private byte[] arrayOfByte;
        private Cipher cipher;
        private IMatcher.RESULT result;
        private Signature signature;

        public KSMatcherOutParamsBuilder setMatchResult(IMatcher.RESULT result) {
            this.result = result;
            return this;
        }

        public KSMatcherOutParamsBuilder setUserID(byte[] bArr) {
            this.arrayOfByte = bArr;
            return this;
        }

        public KSMatcherOutParamsBuilder setAuthenticatedSignature(Signature signature) {
            this.signature = signature;
            return this;
        }

        public KSMatcherOutParamsBuilder setAuthenticatedCipher(Cipher cipher) {
            this.cipher = cipher;
            return this;
        }

        public KSMatcherOutParams createKSMatcherOutParams() {
            Signature signature = this.signature;
            if (signature != null) {
                return new KSMatcherOutParams(this.result, this.arrayOfByte, (List<IMatcher.Extension>) null, signature);
            }
            Cipher cipher = this.cipher;
            if (cipher != null) {
                return new KSMatcherOutParams(this.result, this.arrayOfByte, (List<IMatcher.Extension>) null, cipher);
            }
            return new KSMatcherOutParams(this.result, this.arrayOfByte);
        }
    }
}
