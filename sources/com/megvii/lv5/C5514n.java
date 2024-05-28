package com.megvii.lv5;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.n */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5514n implements Handler.Callback {

    /* renamed from: a */
    public final /* synthetic */ C5521o f12898a;

    public C5514n(C5521o c5521o) {
        this.f12898a = c5521o;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        String str = "handleMessage()..." + message;
        switch (message.what) {
            case 1:
                C5521o c5521o = this.f12898a;
                int mo13274a = c5521o.mo13274a((Context) null);
                c5521o.f13073o = mo13274a;
                if (-1 == mo13274a) {
                    c5521o.m13275a(3, null);
                    return false;
                }
                try {
                    c5521o.f13072n = Camera.open(mo13274a);
                    c5521o.m13275a(1, c5521o.m13260d());
                    return false;
                } catch (Exception e) {
                    c5521o.m13275a(3, null);
                    e.printStackTrace();
                    return false;
                }
            case 2:
                C5521o c5521o2 = this.f12898a;
                if (c5521o2.m13268a(c5521o2.f13072n)) {
                    try {
                        c5521o2.f13072n.setPreviewTexture(c5521o2.f13078t);
                        c5521o2.f13072n.startPreview();
                        Camera camera = c5521o2.f13072n;
                        Camera.Size previewSize = camera.getParameters().getPreviewSize();
                        int i = ((previewSize.height * previewSize.width) * 3) / 2;
                        camera.addCallbackBuffer(new byte[i]);
                        camera.addCallbackBuffer(new byte[i]);
                        camera.addCallbackBuffer(new byte[i]);
                        Camera camera2 = c5521o2.f13072n;
                        if (camera2 != null) {
                            camera2.setPreviewCallback(c5521o2);
                            return false;
                        }
                        return false;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return false;
                    }
                }
                return false;
            case 3:
                C5521o c5521o3 = this.f12898a;
                if (c5521o3.m13268a(c5521o3.f13072n)) {
                    c5521o3.f13072n.takePicture(null, null, c5521o3);
                    return false;
                }
                return false;
            case 4:
                C5521o c5521o4 = this.f12898a;
                if (c5521o4.m13268a(c5521o4.f13072n)) {
                    c5521o4.f13072n.takePicture(null, null, c5521o4);
                    return false;
                }
                return false;
            default:
                return false;
        }
    }
}
