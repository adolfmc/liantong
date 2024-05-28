package p482w;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPResult;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p411o.InterfaceC12376b;
import p470p0.C13636a;
import p470p0.C13648k;

@NBSInstrumented
/* renamed from: w.d */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC14257d<T> {

    /* renamed from: a */
    public AbstractC12375a f27778a;

    /* renamed from: b */
    public int f27779b;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: w.d$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class RunnableC14258a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ String f27780a;

        public RunnableC14258a(String str) {
            this.f27780a = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AbstractC14257d.this.mo11d(this.f27780a);
        }
    }

    /* renamed from: w.d$b */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class RunnableC14259b implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ InterfaceC12376b f27782a;

        /* renamed from: b */
        public final /* synthetic */ WPResult f27783b;

        public RunnableC14259b(InterfaceC12376b interfaceC12376b, WPResult wPResult) {
            this.f27782a = interfaceC12376b;
            this.f27783b = wPResult;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (AbstractC14257d.this.f27779b == 0) {
                this.f27782a.mo1793I();
            }
            if (!"0000".equals(this.f27783b.getCode())) {
                AbstractC14257d.this.mo10a(this.f27783b);
                return;
            }
            if (this.f27783b.getCommonResp() != null && !TextUtils.isEmpty(this.f27783b.getCommonResp().getUserTokenId())) {
                C10546a.C10576i.f20125a.m6169a(this.f27783b.getCommonResp().getUserTokenId());
            }
            AbstractC14257d.this.mo9b(this.f27783b);
        }
    }

    /* renamed from: w.d$c */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class RunnableC14260c implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ InterfaceC12376b f27785a;

        /* renamed from: b */
        public final /* synthetic */ String f27786b;

        public RunnableC14260c(InterfaceC12376b interfaceC12376b, String str) {
            this.f27785a = interfaceC12376b;
            this.f27786b = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (AbstractC14257d.this.f27779b == 0) {
                this.f27785a.mo1793I();
            }
            this.f27785a.mo1790i(this.f27786b);
            C13636a.m190a();
        }
    }

    public AbstractC14257d() {
        this(null, 0);
    }

    public AbstractC14257d(AbstractC12375a abstractC12375a) {
        this(abstractC12375a, 0);
    }

    public AbstractC14257d(AbstractC12375a abstractC12375a, int i) {
        this.f27778a = abstractC12375a;
        this.f27779b = i;
        if (i == 0 && abstractC12375a != null && abstractC12375a.m1795b()) {
            this.f27778a.m1798a().mo1792Y();
        }
    }

    /* renamed from: a */
    public final void m27a() {
        AbstractC12375a abstractC12375a;
        if (this.f27779b != 0 || (abstractC12375a = this.f27778a) == null || !abstractC12375a.m1795b() || this.f27778a.m1798a() == null) {
            return;
        }
        this.f27778a.m1798a().mo1793I();
    }

    /* renamed from: a */
    public void mo10a(WPResult<T> wPResult) {
        AbstractC12375a abstractC12375a = this.f27778a;
        if (abstractC12375a == null || !abstractC12375a.m1795b() || this.f27778a.m1798a() == null) {
            return;
        }
        this.f27778a.m1798a().mo1790i(wPResult.getMsg());
    }

    /* renamed from: a */
    public final void m26a(String str) {
        AbstractC12375a abstractC12375a = this.f27778a;
        if (abstractC12375a == null || !abstractC12375a.m1795b() || this.f27778a.m1798a() == null || this.f27778a.m1798a().mo1789l() == null) {
            return;
        }
        InterfaceC12376b m1798a = this.f27778a.m1798a();
        m1798a.mo1789l().runOnUiThread(new RunnableC14260c(m1798a, str));
    }

    /* renamed from: b */
    public final InterfaceC12376b m25b() {
        AbstractC12375a abstractC12375a = this.f27778a;
        if (abstractC12375a != null) {
            return abstractC12375a.m1798a();
        }
        return null;
    }

    /* renamed from: b */
    public abstract void mo9b(WPResult<T> wPResult);

    /* renamed from: b */
    public void mo24b(String str) {
        AbstractC12375a abstractC12375a = this.f27778a;
        if (abstractC12375a == null || !abstractC12375a.m1795b() || this.f27778a.m1798a() == null || this.f27778a.m1798a().mo1789l() == null) {
            return;
        }
        this.f27778a.m1798a().mo1789l().runOnUiThread(new RunnableC14258a(str));
    }

    /* renamed from: c */
    public abstract Type mo8c();

    /* renamed from: c */
    public void mo23c(String str) {
        Type mo8c = mo8c();
        Gson gson = C13648k.f27492a;
        WPResult wPResult = (WPResult) (!(gson instanceof Gson) ? gson.fromJson(str, mo8c) : NBSGsonInstrumentation.fromJson(gson, str, mo8c));
        AbstractC12375a abstractC12375a = this.f27778a;
        if (abstractC12375a == null || !abstractC12375a.m1795b() || this.f27778a.m1798a() == null || this.f27778a.m1798a().mo1789l() == null) {
            return;
        }
        InterfaceC12376b m1798a = this.f27778a.m1798a();
        m1798a.mo1789l().runOnUiThread(new RunnableC14259b(m1798a, wPResult));
    }

    /* renamed from: d */
    public void mo11d(String str) {
        AbstractC12375a abstractC12375a = this.f27778a;
        if (abstractC12375a == null || !abstractC12375a.m1795b() || this.f27778a.m1798a() == null) {
            return;
        }
        InterfaceC12376b m1798a = this.f27778a.m1798a();
        m1798a.mo1790i(str);
        if (this.f27779b == 0) {
            m1798a.mo1793I();
        }
    }
}
