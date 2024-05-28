package p408n;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import p399k.AbstractC12261d;
import p399k.AbstractC12268k;
import p404l.C12290a;
import p408n.C12348l;

/* renamed from: n.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12336c extends ListView {

    /* renamed from: a */
    public C12348l.C12349a f24942a;

    /* renamed from: n.c$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C12337a<T> extends BaseAdapter {

        /* renamed from: a */
        public int f24943a;

        /* renamed from: b */
        public int f24944b;

        /* renamed from: c */
        public AbstractC12261d f24945c;

        /* renamed from: d */
        public AbstractC12268k f24946d;

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: n.c$a$a */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public class C12338a {

            /* renamed from: a */
            public C12359t f24948a;
        }

        public C12337a(C12348l.C12349a c12349a, AbstractC12261d abstractC12261d) {
            this.f24943a = c12349a.f25000i;
            this.f24944b = c12349a.f25001j;
            this.f24946d = c12349a.f24994c;
            this.f24945c = abstractC12261d;
            throw new IllegalArgumentException("entity must be an Array or an Iterable.");
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            return 0;
        }

        @Override // android.widget.Adapter
        public final T getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return 0L;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            C12338a c12338a;
            C12359t c12359t;
            C12290a c12290a;
            if (view == null) {
                c12338a = new C12338a();
                C12359t c12359t2 = new C12359t(C12336c.this.getContext(), null);
                this.f24945c.getClass();
                c12359t2.setTextSize(50);
                this.f24945c.getClass();
                c12359t2.setTextColor(-13421773);
                c12359t2.setHeight(this.f24945c.m1912e());
                c12338a.f24948a = c12359t2;
                c12359t2.setTag(c12338a);
            } else {
                c12338a = (C12338a) view.getTag();
            }
            if (i == 0 && this.f24946d == null) {
                c12359t = c12338a.f24948a;
                int i2 = this.f24943a;
                c12290a = new C12290a(i2, i2, 0, 0, this.f24944b);
            } else if (i == -1) {
                c12359t = c12338a.f24948a;
                int i3 = this.f24943a;
                c12290a = new C12290a(0, 0, i3, i3, this.f24944b);
            } else {
                c12359t = c12338a.f24948a;
                c12290a = new C12290a(0, 0, 0, 0, this.f24944b);
            }
            c12359t.setBackgroundDrawable(c12290a);
            C12359t c12359t3 = c12338a.f24948a;
            throw null;
        }
    }

    public C12336c(Context context, C12348l.C12349a c12349a) {
        super(context);
        this.f24942a = c12349a;
        m1824a();
    }

    /* renamed from: a */
    public final void m1824a() {
        AbstractC12261d abstractC12261d = (AbstractC12261d) this.f24942a.f24995d;
        if (abstractC12261d == null) {
            return;
        }
        new C12337a(this.f24942a, abstractC12261d);
        throw null;
    }
}
