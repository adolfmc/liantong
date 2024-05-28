package com.p281qq.p282e.ads;

import com.p281qq.p282e.ads.cfg.DownAPPConfirmPolicy;
import com.p281qq.p282e.comm.p283pi.ADI;
import com.p281qq.p282e.comm.util.AdError;

/* renamed from: com.qq.e.ads.NativeAbstractAD */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class NativeAbstractAD<T extends ADI> extends AbstractAD<T> {

    /* renamed from: f */
    private DownAPPConfirmPolicy f17753f;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.ads.NativeAbstractAD$BasicADListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface BasicADListener {
        void onNoAD(AdError adError);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8305a(T t) {
        DownAPPConfirmPolicy downAPPConfirmPolicy = this.f17753f;
        if (downAPPConfirmPolicy != null) {
            setDownAPPConfirmPolicy(downAPPConfirmPolicy);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    protected /* bridge */ /* synthetic */ void mo8294a(Object obj) {
        mo8305a((NativeAbstractAD<T>) ((ADI) obj));
    }

    public void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        this.f17753f = downAPPConfirmPolicy;
        T t = this.f17738a;
        if (t == 0 || downAPPConfirmPolicy == null) {
            return;
        }
        ((ADI) t).setDownAPPConfirmPolicy(downAPPConfirmPolicy);
    }
}
