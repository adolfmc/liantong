package cn.anicert.verification.lib_identify.p092ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.anicert.verification.lib_identify.p092ui.widgets.MyEditView;
import cn.ctid.verification.FragmentC1631q;
import com.tfd.sdk.LF8bOvWP4;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.anicert.verification.lib_identify.ui.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FragmentC1586d extends FragmentC1631q {

    /* renamed from: b */
    private View.OnClickListener f2609b;

    /* renamed from: c */
    private MyEditView f2610c;

    /* renamed from: d */
    private View f2611d;

    /* renamed from: e */
    private View f2612e;

    /* renamed from: f */
    private TextView f2613f;

    /* renamed from: g */
    private TextView f2614g;

    /* renamed from: h */
    private HandlerC1587a f2615h;

    /* renamed from: i */
    private String f2616i = "请输入8位网证口令";

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.anicert.verification.lib_identify.ui.d$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class HandlerC1587a extends Handler {

        /* renamed from: a */
        private WeakReference<FragmentC1586d> f2617a;

        static {
            System.loadLibrary("jade2_LF8bOvWP4");
            LF8bOvWP4.interfaceV(20);
        }

        HandlerC1587a(FragmentC1586d fragmentC1586d) {
            this.f2617a = new WeakReference<>(fragmentC1586d);
        }

        @Override // android.os.Handler
        public native void handleMessage(Message message);
    }

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(188);
    }

    /* renamed from: a */
    static native /* synthetic */ View m22149a(FragmentC1586d fragmentC1586d);

    /* renamed from: a */
    static native /* synthetic */ void m22148a(FragmentC1586d fragmentC1586d, String str);

    /* renamed from: a */
    private native void m22147a(String str);

    /* renamed from: b */
    static native /* synthetic */ MyEditView m22146b(FragmentC1586d fragmentC1586d);

    /* renamed from: c */
    static native /* synthetic */ String m22145c(FragmentC1586d fragmentC1586d);

    /* renamed from: d */
    static native /* synthetic */ View.OnClickListener m22144d(FragmentC1586d fragmentC1586d);

    @Override // android.app.Fragment
    public native void onActivityCreated(Bundle bundle);

    @Override // android.app.Fragment
    public native View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    @Override // android.app.Fragment
    public native void onDestroyView();

    @Override // cn.ctid.verification.FragmentC1631q, android.app.Fragment
    public native void onViewCreated(View view, Bundle bundle);
}
