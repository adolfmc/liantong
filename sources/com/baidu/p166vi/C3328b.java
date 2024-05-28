package com.baidu.p166vi;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
class C3328b extends Thread {

    /* renamed from: a */
    final /* synthetic */ AudioRecorder f8206a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3328b(AudioRecorder audioRecorder, String str) {
        super(str);
        this.f8206a = audioRecorder;
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0016 */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r4 = this;
            r0 = -19
            android.os.Process.setThreadPriority(r0)
            com.baidu.vi.AudioRecorder r0 = r4.f8206a
            android.media.AudioRecord r0 = com.baidu.p166vi.AudioRecorder.m17410b(r0)
            r0.startRecording()
            r0 = 0
            r1 = r0
        L10:
            com.baidu.vi.AudioRecorder r2 = r4.f8206a
            boolean r2 = com.baidu.p166vi.AudioRecorder.m17413a(r2)
            if (r2 == 0) goto L50
            com.baidu.vi.AudioRecorder r2 = r4.f8206a
            int r2 = com.baidu.p166vi.AudioRecorder.m17409c(r2)
            byte[] r2 = new byte[r2]
            com.baidu.vi.AudioRecorder r3 = r4.f8206a
            android.media.AudioRecord r3 = com.baidu.p166vi.AudioRecorder.m17410b(r3)
            if (r3 == 0) goto L38
            com.baidu.vi.AudioRecorder r1 = r4.f8206a
            android.media.AudioRecord r1 = com.baidu.p166vi.AudioRecorder.m17410b(r1)
            com.baidu.vi.AudioRecorder r3 = r4.f8206a
            int r3 = com.baidu.p166vi.AudioRecorder.m17409c(r3)
            int r1 = r1.read(r2, r0, r3)
        L38:
            r3 = -3
            if (r1 == r3) goto L4a
            r3 = -2
            if (r1 == r3) goto L4a
            r3 = -1
            if (r1 == r3) goto L4a
            if (r1 != 0) goto L44
            goto L4a
        L44:
            com.baidu.vi.AudioRecorder r3 = r4.f8206a
            com.baidu.p166vi.AudioRecorder.m17412a(r3, r2, r1)
            goto L10
        L4a:
            com.baidu.vi.AudioRecorder r2 = r4.f8206a
            com.baidu.p166vi.AudioRecorder.m17408d(r2)
            goto L10
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p166vi.C3328b.run():void");
    }
}
