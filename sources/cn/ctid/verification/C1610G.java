package cn.ctid.verification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.G */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1610G {

    /* renamed from: a */
    private View f2668a;

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(108);
    }

    public C1610G(Context context, int i) {
        this.f2668a = LayoutInflater.from(context.getApplicationContext()).inflate(i, (ViewGroup) null);
    }

    /* renamed from: a */
    public native View m22089a();

    /* renamed from: a */
    public native <V extends View> V m22088a(int i);
}
