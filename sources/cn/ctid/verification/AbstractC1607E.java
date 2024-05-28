package cn.ctid.verification;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.tfd.sdk.LF8bOvWP4;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.E */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractC1607E {

    /* renamed from: a */
    private int f2664a;

    /* renamed from: b */
    private FragmentManager f2665b;

    /* renamed from: c */
    private List<Fragment> f2666c = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.ctid.verification.E$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC1608a<T extends Fragment> {
        /* renamed from: a */
        void mo22098a(T t);

        /* renamed from: a */
        void mo22097a(FragmentTransaction fragmentTransaction);

        /* renamed from: a */
        void mo22095a(boolean z);

        /* renamed from: a */
        boolean mo22099a();

        /* renamed from: b */
        void mo22094b();

        /* renamed from: b */
        void mo22093b(T t);

        /* renamed from: c */
        boolean mo22091c();

        /* renamed from: d */
        boolean mo22090d();

        boolean isHidden();
    }

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(104);
    }

    public AbstractC1607E(int i, FragmentManager fragmentManager) {
        this.f2664a = i;
        this.f2665b = fragmentManager;
    }

    /* renamed from: b */
    public static native <T> T m22100b(String str);

    /* renamed from: a */
    public native Fragment m22102a(String str);

    /* renamed from: a */
    public native void m22103a();

    /* renamed from: a */
    public final native void m22101a(String str, InterfaceC1608a interfaceC1608a, Bundle bundle);
}
