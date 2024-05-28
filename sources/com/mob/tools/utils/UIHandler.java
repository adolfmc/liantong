package com.mob.tools.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.mob.tools.proguard.PublicMemberKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UIHandler implements PublicMemberKeeper {

    /* renamed from: a */
    private static Handler f15296a;

    /* renamed from: a */
    private static synchronized void m11101a() {
        synchronized (UIHandler.class) {
            if (f15296a == null) {
                m11097b();
            }
        }
    }

    /* renamed from: b */
    private static void m11097b() {
        f15296a = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.mob.tools.utils.UIHandler.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                UIHandler.m11096b(message);
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m11096b(Message message) {
        C6198a c6198a = (C6198a) message.obj;
        Message message2 = c6198a.f15297a;
        Handler.Callback callback = c6198a.f15298b;
        if (callback != null) {
            callback.handleMessage(message2);
        }
    }

    /* renamed from: a */
    private static Message m11098a(Message message, Handler.Callback callback) {
        Message message2 = new Message();
        message2.obj = new C6198a(message, callback);
        return message2;
    }

    /* renamed from: a */
    private static Message m11100a(int i, Handler.Callback callback) {
        Message message = new Message();
        message.what = i;
        return m11098a(message, callback);
    }

    public static boolean sendEmptyMessage(int i, Handler.Callback callback) {
        m11101a();
        return f15296a.sendMessage(m11100a(i, callback));
    }

    public static boolean sendEmptyMessageDelayed(int i, long j, Handler.Callback callback) {
        m11101a();
        return f15296a.sendMessageDelayed(m11100a(i, callback), j);
    }

    public static boolean sendMessage(Message message, Handler.Callback callback) {
        m11101a();
        return f15296a.sendMessage(m11098a(message, callback));
    }

    public static boolean sendMessageDelayed(Message message, long j, Handler.Callback callback) {
        m11101a();
        return f15296a.sendMessageDelayed(m11098a(message, callback), j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.utils.UIHandler$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C6198a {

        /* renamed from: a */
        public final Message f15297a;

        /* renamed from: b */
        public final Handler.Callback f15298b;

        public C6198a(Message message, Handler.Callback callback) {
            this.f15297a = message;
            this.f15298b = callback;
        }
    }
}
