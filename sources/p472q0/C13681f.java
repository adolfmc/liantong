package p472q0;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* renamed from: q0.f */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13681f implements InvocationHandler {

    /* renamed from: a */
    public final Object f27531a;

    public C13681f(Object obj) {
        this.f27531a = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        char c;
        String name = method.getName();
        name.getClass();
        int hashCode = name.hashCode();
        if (hashCode == -1581943859) {
            if (name.equals("cancelToast")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1230397970) {
            if (hashCode == 1967758591 && name.equals("enqueueToast")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (name.equals("enqueueToastEx")) {
                c = 1;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
                objArr[0] = "android";
                break;
        }
        return method.invoke(this.f27531a, objArr);
    }
}
