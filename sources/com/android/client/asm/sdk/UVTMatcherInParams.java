package com.android.client.asm.sdk;

import com.android.client.asm.sdk.IMatcher;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class UVTMatcherInParams extends IMatcher.MatcherInParams {
    public UVTMatcherInParams(String str, String str2, byte[] bArr, IMatcher.MatcherUI matcherUI, IMatcher.MatcherInParams.IAntiHammeringCallback iAntiHammeringCallback) {
        super(str, str2, bArr, matcherUI, iAntiHammeringCallback);
    }

    public UVTMatcherInParams() {
    }

    public UVTMatcherInParams setCustomUI(String str) {
        this.m_customUI = str;
        return this;
    }

    public UVTMatcherInParams setTransText(String str) {
        this.m_transText = str;
        return this;
    }

    public UVTMatcherInParams setFinalChallenge(byte[] bArr) {
        this.FinalChallenge = bArr;
        return this;
    }

    public UVTMatcherInParams setAntihammeringCallback(IMatcher.MatcherInParams.IAntiHammeringCallback iAntiHammeringCallback) {
        this.m_antihammeringCallback = iAntiHammeringCallback;
        return this;
    }
}
