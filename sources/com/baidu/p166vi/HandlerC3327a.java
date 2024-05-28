package com.baidu.p166vi;

import android.os.Handler;
import android.os.Message;
import com.baidu.p166vi.AudioRecorder;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
final class HandlerC3327a extends Handler {
    @Override // android.os.Handler
    public void handleMessage(Message message) {
        boolean z;
        boolean z2;
        AudioRecorder audioRecorder = ((AudioRecorder.C3326a) message.obj).f8185a;
        switch (message.what) {
            case 1:
                z = audioRecorder.f8182h;
                if (z) {
                    audioRecorder.onReadData(((AudioRecorder.C3326a) message.obj).f8186b, ((AudioRecorder.C3326a) message.obj).f8187c);
                    return;
                }
                return;
            case 2:
                z2 = audioRecorder.f8182h;
                if (z2) {
                    audioRecorder.onReadError();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
