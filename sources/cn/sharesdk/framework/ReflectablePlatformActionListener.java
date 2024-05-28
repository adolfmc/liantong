package cn.sharesdk.framework;

import android.os.Handler;
import android.os.Message;
import com.mob.tools.utils.UIHandler;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ReflectablePlatformActionListener implements PlatformActionListener {

    /* renamed from: a */
    private int f2748a;

    /* renamed from: b */
    private Handler.Callback f2749b;

    /* renamed from: c */
    private int f2750c;

    /* renamed from: d */
    private Handler.Callback f2751d;

    /* renamed from: e */
    private int f2752e;

    /* renamed from: f */
    private Handler.Callback f2753f;

    public void setOnCompleteCallback(int i, Handler.Callback callback) {
        this.f2748a = i;
        this.f2749b = callback;
    }

    @Override // cn.sharesdk.framework.PlatformActionListener
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (this.f2749b != null) {
            Message message = new Message();
            message.what = this.f2748a;
            message.obj = new Object[]{platform, Integer.valueOf(i), hashMap};
            UIHandler.sendMessage(message, this.f2749b);
        }
    }

    public void setOnErrorCallback(int i, Handler.Callback callback) {
        this.f2750c = i;
        this.f2751d = callback;
    }

    @Override // cn.sharesdk.framework.PlatformActionListener
    public void onError(Platform platform, int i, Throwable th) {
        if (this.f2751d != null) {
            Message message = new Message();
            message.what = this.f2750c;
            message.obj = new Object[]{platform, Integer.valueOf(i), th};
            UIHandler.sendMessage(message, this.f2751d);
        }
    }

    public void setOnCancelCallback(int i, Handler.Callback callback) {
        this.f2752e = i;
        this.f2753f = callback;
    }

    @Override // cn.sharesdk.framework.PlatformActionListener
    public void onCancel(Platform platform, int i) {
        if (this.f2753f != null) {
            Message message = new Message();
            message.what = this.f2752e;
            message.obj = new Object[]{platform, Integer.valueOf(i)};
            UIHandler.sendMessage(message, this.f2753f);
        }
    }
}
