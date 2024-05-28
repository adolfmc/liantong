package p472q0;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Handler;
import android.widget.Toast;
import java.lang.reflect.Field;

@TargetApi(19)
/* renamed from: q0.i */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13684i extends C13682g {

    /* renamed from: c */
    public boolean f27534c;

    public C13684i(Application application) {
        super(application);
    }

    @Override // p472q0.C13682g, android.widget.Toast, p474r0.InterfaceC13715a
    public final void show() {
        if (!this.f27534c) {
            this.f27534c = true;
            try {
                Field declaredField = Toast.class.getDeclaredField("mTN");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(this);
                Field declaredField2 = declaredField.getType().getDeclaredField("mHandler");
                declaredField2.setAccessible(true);
                Handler handler = (Handler) declaredField2.get(obj);
                if (!(handler instanceof HandlerC13683h)) {
                    declaredField2.set(obj, new HandlerC13683h(handler));
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        super.show();
    }
}
