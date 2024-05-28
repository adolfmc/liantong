package com.gmrz.authenticationso.authenticator;

import android.accounts.AuthenticatorException;
import com.android.client.asm.core.AKDigestMethod;
import com.android.client.asm.sdk.IAKDigestMethod;
import com.android.client.asm.sdk.IAuthenticatorKernel;
import com.utils.sm3.SM3AKDigestMethod;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AuthenticatorKernel implements IAuthenticatorKernel {
    private IAKDigestMethod mAKDigestMethod = new AKDigestMethod();

    public abstract String getLabel();

    public abstract byte[] processRequestJni(byte[] bArr, Map<IAuthenticatorKernel.AKDataKeys, Object> map) throws AuthenticatorException;

    public void setAKDigestMethodSM3() {
        this.mAKDigestMethod = new SM3AKDigestMethod();
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorKernel
    public byte[] processRequest(byte[] bArr, Map<IAuthenticatorKernel.AKDataKeys, Object> map) {
        try {
            return processRequestJni(bArr, map);
        } catch (Exception e) {
            throw new IllegalStateException(e.toString());
        }
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorKernel
    public IAKDigestMethod getDigestMethod() {
        return this.mAKDigestMethod;
    }
}
