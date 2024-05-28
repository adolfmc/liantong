package p472q0;

import android.app.Application;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* renamed from: q0.g */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class C13682g extends C13685j {

    /* renamed from: b */
    public static boolean f27532b;

    public C13682g(Application application) {
        super(application);
    }

    @Override // android.widget.Toast, p474r0.InterfaceC13715a
    public void show() {
        if (!f27532b) {
            f27532b = true;
            try {
                Method declaredMethod = Toast.class.getDeclaredMethod("getService", new Class[0]);
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(null, new Object[0]);
                if (invoke != null && (!Proxy.isProxyClass(invoke.getClass()) || !(Proxy.getInvocationHandler(invoke) instanceof C13681f))) {
                    Object newProxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Class.forName("android.app.INotificationManager")}, new C13681f(invoke));
                    Field declaredField = Toast.class.getDeclaredField("sService");
                    declaredField.setAccessible(true);
                    declaredField.set(null, newProxyInstance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.show();
    }
}
