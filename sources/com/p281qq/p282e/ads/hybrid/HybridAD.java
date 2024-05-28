package com.p281qq.p282e.ads.hybrid;

import android.content.Context;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.p281qq.p282e.ads.AbstractAD;
import com.p281qq.p282e.comm.p283pi.HADI;
import com.p281qq.p282e.comm.p283pi.POFactory;
import com.p281qq.p282e.comm.util.AdErrorConvertor;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@NBSInstrumented
/* renamed from: com.qq.e.ads.hybrid.HybridAD */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HybridAD extends AbstractAD<HADI> implements HADI {

    /* renamed from: f */
    private HybridADListener f17798f;

    /* renamed from: g */
    private CountDownLatch f17799g = new CountDownLatch(1);

    /* renamed from: h */
    private HybridADSetting f17800h;

    public HybridAD(Context context, HybridADSetting hybridADSetting, HybridADListener hybridADListener) {
        this.f17800h = hybridADSetting;
        this.f17798f = hybridADListener;
        m8344a(context, "NO_POS_ID");
    }

    /* renamed from: a */
    protected HADI m8319a(POFactory pOFactory) {
        return pOFactory.getHybridAD(this.f17800h, this.f17798f);
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public /* bridge */ /* synthetic */ HADI mo8297a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return m8319a(pOFactory);
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public /* synthetic */ void mo8294a(HADI hadi) {
        m8316c();
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: b */
    public void mo8292b(int i) {
        HybridADListener hybridADListener = this.f17798f;
        if (hybridADListener != null) {
            hybridADListener.onError(AdErrorConvertor.formatErrorCode(i));
        }
        this.f17799g.countDown();
    }

    /* renamed from: c */
    protected void m8316c() {
        this.f17799g.countDown();
    }

    @Override // com.p281qq.p282e.comm.p283pi.HADI
    public void loadUrl(final String str) {
        if (m8346a()) {
            if (!m8339b()) {
                new Thread(new Runnable() { // from class: com.qq.e.ads.hybrid.HybridAD.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            HybridAD.this.f17799g.await(30L, TimeUnit.SECONDS);
                            if (!HybridAD.this.m8339b() || HybridAD.this.f17738a == null) {
                                GDTLogger.m8234e("初始化错误：广告实例未被初始化");
                                HybridAD.this.m8345a(2001);
                            } else {
                                HADI hadi = (HADI) HybridAD.this.f17738a;
                                String str2 = str;
                                if (hadi instanceof Object) {
                                    NBSWebLoadInstrument.loadUrl(hadi, str2);
                                } else {
                                    hadi.loadUrl(str2);
                                }
                            }
                        } catch (InterruptedException unused) {
                            GDTLogger.m8234e("初始化错误：广告实例未被初始化");
                            HybridAD.this.m8345a(2001);
                        }
                    }
                }).start();
                return;
            }
            T t = this.f17738a;
            if (t == 0) {
                m8340a("loadUrl");
                return;
            }
            HADI hadi = (HADI) t;
            if (hadi instanceof Object) {
                NBSWebLoadInstrument.loadUrl(hadi, str);
            } else {
                hadi.loadUrl(str);
            }
        }
    }
}
