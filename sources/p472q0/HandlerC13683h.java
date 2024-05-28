package p472q0;

import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

/* renamed from: q0.h */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class HandlerC13683h extends Handler {

    /* renamed from: a */
    public final Handler f27533a;

    public HandlerC13683h(Handler handler) {
        this.f27533a = handler;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        try {
            this.f27533a.handleMessage(message);
        } catch (WindowManager.BadTokenException | IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
