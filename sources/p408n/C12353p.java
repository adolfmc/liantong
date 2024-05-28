package p408n;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import p395i.C12048b;
import p399k.AbstractC12265h;
import p399k.AbstractC12266i;
import p404l.C12290a;
import p408n.C12348l;

/* renamed from: n.p */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12353p extends LinearLayout {

    /* renamed from: a */
    public AbstractC12266i f25009a;

    /* renamed from: b */
    public C12359t f25010b;

    /* renamed from: c */
    public long f25011c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: n.p$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class View$OnClickListenerC12354a implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ AbstractC12265h f25012a;

        /* renamed from: b */
        public final /* synthetic */ C12048b.InterfaceC12050b f25013b;

        public View$OnClickListenerC12354a(AbstractC12265h abstractC12265h, C12048b.InterfaceC12050b interfaceC12050b) {
            this.f25012a = abstractC12265h;
            this.f25013b = interfaceC12050b;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (C12353p.this.m1807a()) {
                NBSActionInstrumentation.onClickEventExit();
                return;
            }
            this.f25012a.mo1814b();
            C12048b.InterfaceC12050b interfaceC12050b = this.f25013b;
            if (interfaceC12050b != null) {
                interfaceC12050b.mo1801a();
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: n.p$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC12355b implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ C12048b.InterfaceC12052d f25015a;

        public View$OnClickListenerC12355b(C12048b.InterfaceC12052d interfaceC12052d) {
            this.f25015a = interfaceC12052d;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (C12353p.this.m1807a()) {
                NBSActionInstrumentation.onClickEventExit();
                return;
            }
            C12353p.this.f25009a.mo1814b();
            C12048b.InterfaceC12052d interfaceC12052d = this.f25015a;
            if (interfaceC12052d != null) {
                interfaceC12052d.onClick(view);
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    public C12353p(Context context, C12348l.C12349a c12349a) {
        super(context);
        m1806a(c12349a);
    }

    /* renamed from: a */
    public final void m1806a(C12348l.C12349a c12349a) {
        C12359t c12359t;
        C12290a c12290a;
        C12290a c12290a2;
        AbstractC12265h abstractC12265h = c12349a.f24996e;
        this.f25009a = c12349a.f24997f;
        setOrientation(0);
        int i = c12349a.f25000i;
        if (abstractC12265h != null) {
            C12048b.InterfaceC12050b mo1816f = abstractC12265h.mo1816f();
            C12359t c12359t2 = new C12359t(getContext(), null);
            c12359t2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0f));
            c12359t2.setClickable(true);
            c12359t2.setOnClickListener(new View$OnClickListenerC12354a(abstractC12265h, mo1816f));
            c12359t2.setText(abstractC12265h.mo1815a());
            c12359t2.setTextSize(abstractC12265h.mo1811e());
            c12359t2.setTextColor(abstractC12265h.mo1812d());
            c12359t2.setHeight(abstractC12265h.mo1813c());
            if (this.f25009a != null) {
                c12290a2 = r9;
                C12290a c12290a3 = new C12290a(0, 0, 0, i, c12349a.f25001j);
            } else {
                c12290a2 = new C12290a(0, 0, i, i, c12349a.f25001j);
            }
            c12359t2.setBackgroundDrawable(c12290a2);
            addView(c12359t2);
        }
        if (abstractC12265h != null && this.f25009a != null) {
            addView(new C12352o(getContext(), null));
        }
        AbstractC12266i abstractC12266i = this.f25009a;
        if (abstractC12266i != null) {
            C12048b.InterfaceC12052d mo1810f = abstractC12266i.mo1810f();
            C12359t c12359t3 = new C12359t(getContext(), null);
            this.f25010b = c12359t3;
            c12359t3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0f));
            this.f25010b.setClickable(true);
            if (mo1810f != null) {
                this.f25010b.setOnClickListener(new View$OnClickListenerC12355b(mo1810f));
            }
            this.f25010b.setText(this.f25009a.mo1815a());
            this.f25010b.setTextSize(this.f25009a.mo1811e());
            this.f25010b.setTextColor(this.f25009a.mo1812d());
            this.f25010b.setHeight(this.f25009a.mo1813c());
            if (abstractC12265h != null) {
                c12359t = this.f25010b;
                c12290a = new C12290a(0, 0, i, 0, c12349a.f25001j);
            } else {
                c12359t = this.f25010b;
                c12290a = new C12290a(0, 0, i, i, c12349a.f25001j);
            }
            c12359t.setBackgroundDrawable(c12290a);
            addView(this.f25010b);
        }
    }

    /* renamed from: a */
    public final boolean m1807a() {
        if (System.currentTimeMillis() - this.f25011c < 800) {
            return true;
        }
        this.f25011c = System.currentTimeMillis();
        return false;
    }
}
