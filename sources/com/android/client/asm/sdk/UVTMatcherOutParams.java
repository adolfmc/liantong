package com.android.client.asm.sdk;

import com.android.client.asm.sdk.IMatcher;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class UVTMatcherOutParams extends IMatcher.MatcherOutParams {
    private byte[] m_UVT;

    public UVTMatcherOutParams(IMatcher.RESULT result, byte[] bArr, List<IMatcher.Extension> list, byte[] bArr2) {
        super(result, bArr, list);
        this.m_UVT = bArr2;
    }

    public UVTMatcherOutParams() {
    }

    public byte[] getUVT() {
        return this.m_UVT;
    }

    public UVTMatcherOutParams setUVT(byte[] bArr) {
        this.m_UVT = bArr;
        return this;
    }

    public UVTMatcherOutParams setMatchResult(IMatcher.RESULT result) {
        this.m_MatchResult = result;
        return this;
    }

    public UVTMatcherOutParams setUserID(byte[] bArr) {
        this.m_UserID = bArr;
        return this;
    }
}
