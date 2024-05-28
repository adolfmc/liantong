package com.networkbench.agent.impl.p252e;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6642k;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6360k {

    /* renamed from: a */
    public static final InterfaceC6393e f16011a = C6394f.m10150a();

    /* renamed from: b */
    private static final String[] f16012b = {"window.png", "window_click.png", "page.png", "handle.png", "exit.png", "hand_enable_new.png"};

    /* renamed from: c */
    private String f16013c;

    /* renamed from: d */
    private Context f16014d;

    /* renamed from: e */
    private List<String> f16015e = new ArrayList();

    /* renamed from: f */
    private List<String> f16016f = new ArrayList();

    public C6360k(String str, Context context) {
        this.f16013c = str;
        this.f16014d = context;
    }

    /* renamed from: a */
    private boolean m10294a(String str) {
        return new File(str).exists();
    }

    /* renamed from: b */
    private void m10293b() {
        String[] strArr;
        String m10303a = C6356g.m10303a(this.f16014d);
        for (String str : f16012b) {
            if (!m10294a(m10303a + File.separator + str)) {
                this.f16015e.add(m10303a + File.separator + str);
                this.f16016f.add(this.f16013c + File.separator + str);
            }
        }
    }

    /* renamed from: a */
    public void m10295a() {
        m10293b();
        for (int i = 0; i < this.f16015e.size(); i++) {
            C6369q.m10273a().m10272a(new RunnableC6361a(this.f16016f.get(i), this.f16015e.get(i)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.e.k$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class RunnableC6361a implements Runnable {

        /* renamed from: b */
        private String f16018b;

        /* renamed from: c */
        private String f16019c;

        public RunnableC6361a(String str, String str2) {
            this.f16018b = str;
            this.f16019c = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                C6356g.m10302a(this.f16019c, C6642k.m8901f(this.f16018b));
                m10292a(this.f16019c);
            } catch (Throwable th) {
                C6360k.f16011a.mo10121a("load bitmap error", th);
            }
        }

        /* renamed from: a */
        private void m10292a(String str) {
            C6367o m10313b = C6353e.m10315a().m10313b();
            if (m10313b != null) {
                for (final AbstractC6364m abstractC6364m : m10313b.m10276b()) {
                    if (abstractC6364m.getViewImageBmpPath() != null && abstractC6364m.getViewImageBmpPath().equals(str)) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.networkbench.agent.impl.e.k.a.1
                            @Override // java.lang.Runnable
                            public void run() {
                                abstractC6364m.m10285g();
                            }
                        });
                    }
                }
            }
        }
    }
}
