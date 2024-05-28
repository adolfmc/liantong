package com.p319ss.android.downloadlib.utils;

import com.p319ss.android.downloadlib.C9982hj;
import com.p319ss.android.downloadlib.C9992ko;
import java.lang.ref.SoftReference;

/* renamed from: com.ss.android.downloadlib.utils.Chain */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class Chain<P, R> implements Runnable {
    @ThreadType

    /* renamed from: b */
    private int f19370b;

    /* renamed from: h */
    private Chain<?, P> f19371h;

    /* renamed from: hj */
    private SoftReference<InterfaceC10046mb<P, R>> f19372hj;

    /* renamed from: mb */
    private P f19373mb;

    /* renamed from: ox */
    private R f19374ox;

    /* renamed from: u */
    private Chain<R, ?> f19375u;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.utils.Chain$ThreadType */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface ThreadType {
        public static final int CPU = 1;

        /* renamed from: IO */
        public static final int f19376IO = 2;
        public static final int MAIN = 0;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.utils.Chain$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC10046mb<PARAM, RESULT> {
        /* renamed from: mb */
        RESULT mo7091mb(PARAM param);
    }

    private Chain(@ThreadType int i, InterfaceC10046mb<P, R> interfaceC10046mb, P p) {
        this.f19370b = i;
        this.f19372hj = new SoftReference<>(interfaceC10046mb);
        this.f19373mb = p;
    }

    /* renamed from: mb */
    public static <P, R> Chain<P, R> m7093mb(InterfaceC10046mb<P, R> interfaceC10046mb, P p) {
        return new Chain<>(2, interfaceC10046mb, p);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: mb */
    public <NR> Chain<R, NR> m7095mb(@ThreadType int i, InterfaceC10046mb<R, NR> interfaceC10046mb) {
        Chain chain = (Chain<R, ?>) new Chain(i, interfaceC10046mb, null);
        this.f19375u = chain;
        chain.f19371h = this;
        return chain;
    }

    /* renamed from: mb */
    public <NR> Chain<R, NR> m7094mb(InterfaceC10046mb<R, NR> interfaceC10046mb) {
        return m7095mb(0, interfaceC10046mb);
    }

    /* renamed from: mb */
    public void m7096mb() {
        Chain<?, P> chain = this.f19371h;
        if (chain != null) {
            chain.m7096mb();
        } else {
            run();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        Chain<?, P> chain;
        if (this.f19370b == 0 && !C10050jb.m7057mb()) {
            C9992ko.m7236mb().m7222ox().post(this);
        } else if (this.f19370b == 1 && C10050jb.m7057mb()) {
            C9982hj.m7254mb().m7253mb(this);
        } else if (this.f19370b == 2 && C10050jb.m7057mb()) {
            C9982hj.m7254mb().m7249ox(this);
        } else {
            if (this.f19373mb == null && (chain = this.f19371h) != null) {
                this.f19373mb = chain.m7092ox();
            }
            InterfaceC10046mb<P, R> interfaceC10046mb = this.f19372hj.get();
            if (interfaceC10046mb == null) {
                return;
            }
            this.f19374ox = interfaceC10046mb.mo7091mb(this.f19373mb);
            Chain<R, ?> chain2 = this.f19375u;
            if (chain2 != null) {
                chain2.run();
            }
        }
    }

    /* renamed from: ox */
    private R m7092ox() {
        return this.f19374ox;
    }
}
