package p408n;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import p408n.C12348l;

/* renamed from: n.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12339d extends C12334a {

    /* renamed from: a */
    public ProgressBar f24949a;

    /* renamed from: b */
    public C12348l.C12349a f24950b;

    /* renamed from: n.d$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class HandlerC12340a extends Handler {

        /* renamed from: a */
        public final /* synthetic */ C12359t f24951a;

        public HandlerC12340a(C12359t c12359t) {
            this.f24951a = c12359t;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            int progress = C12339d.this.f24949a.getProgress();
            C12359t c12359t = this.f24951a;
            c12359t.setText(((int) ((progress / C12339d.this.f24949a.getMax()) * 100.0f)) + "%");
        }
    }

    public C12339d(Context context, C12348l.C12349a c12349a) {
        super(context);
        this.f24950b = c12349a;
        m1823a();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0066 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0067  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m1823a() {
        /*
            Method dump skipped, instructions count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p408n.C12339d.m1823a():void");
    }
}
