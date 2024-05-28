package cn.ctid.verification;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tfd.sdk.LF8bOvWP4;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.u */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1635u extends AbstractC1638w<byte[]> {

    /* renamed from: e */
    private HandlerC1636a f2699e;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.ctid.verification.u$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class HandlerC1636a extends Handler {

        /* renamed from: a */
        private WeakReference<C1635u> f2700a;

        static {
            System.loadLibrary("jade2_LF8bOvWP4");
            LF8bOvWP4.interfaceV(143);
        }

        HandlerC1636a(C1635u c1635u) {
            super(Looper.getMainLooper());
            this.f2700a = new WeakReference<>(c1635u);
        }

        @Override // android.os.Handler
        public native void handleMessage(Message message);
    }

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(242);
    }

    public C1635u(Activity activity) {
        super(activity);
        this.f2699e = new HandlerC1636a(this);
        C1640y.m22048a().m22047a(this);
        C1640y.m22048a().f2710c = new C1624j(this.f2702a);
        C1640y.m22048a().f2711d = this.f2699e;
        this.f2703b.f2707b = true;
    }

    /* renamed from: e */
    public native void m22052e();
}
