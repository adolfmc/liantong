package com.gmrz.asm.p198fp.authenticator.matcherparams;

import com.android.client.asm.sdk.IMatcher;
import java.security.Signature;
import javax.crypto.Cipher;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.authenticator.matcherparams.KSMatcherInParams */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class KSMatcherInParams extends IMatcher.MatcherInParams {
    private Cipher cipher;
    private Signature signature;

    public KSMatcherInParams() {
    }

    public KSMatcherInParams(String str, String str2, byte[] bArr, IMatcher.MatcherUI matcherUI, IMatcher.MatcherInParams.IAntiHammeringCallback iAntiHammeringCallback, Signature signature) {
        super(str, str2, bArr, matcherUI, iAntiHammeringCallback);
        this.signature = signature;
    }

    public KSMatcherInParams(String str, String str2, byte[] bArr, IMatcher.MatcherUI matcherUI, IMatcher.MatcherInParams.IAntiHammeringCallback iAntiHammeringCallback, Cipher cipher) {
        super(str, str2, bArr, matcherUI, iAntiHammeringCallback);
        this.cipher = cipher;
    }

    public Signature getSignatureObject() {
        return this.signature;
    }

    public KSMatcherInParams setSignatureObject(Signature signature) {
        this.signature = signature;
        return this;
    }

    public Cipher getCipherObject() {
        return this.cipher;
    }

    public KSMatcherInParams setCipherObject(Cipher cipher) {
        this.cipher = cipher;
        return this;
    }

    public KSMatcherInParams setCustomUI(String str) {
        this.m_customUI = str;
        return this;
    }

    public KSMatcherInParams setTransText(String str) {
        this.m_transText = str;
        return this;
    }

    public KSMatcherInParams setFinalChallenge(byte[] bArr) {
        this.FinalChallenge = bArr;
        return this;
    }

    public KSMatcherInParams setAntihammeringCallback(IMatcher.MatcherInParams.IAntiHammeringCallback iAntiHammeringCallback) {
        this.m_antihammeringCallback = iAntiHammeringCallback;
        return this;
    }

    public KSMatcherInParams setMatchUI(IMatcher.MatcherUI matcherUI) {
        this.m_matcherUI = matcherUI;
        return this;
    }
}
