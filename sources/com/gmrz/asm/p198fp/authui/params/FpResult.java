package com.gmrz.asm.p198fp.authui.params;

import com.android.client.asm.sdk.IMatcher;
import java.security.Signature;
import javax.crypto.Cipher;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.authui.params.FpResult */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FpResult {
    public Cipher cipher;
    public IMatcher.RESULT result;
    public Signature signature;

    public FpResult setResult(IMatcher.RESULT result) {
        this.result = result;
        return this;
    }

    public FpResult setSignature(Signature signature) {
        this.signature = signature;
        return this;
    }

    public FpResult setCipher(Cipher cipher) {
        this.cipher = cipher;
        return this;
    }
}
