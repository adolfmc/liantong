package cn.finalteam.toolsfinal.p093io.stream;

import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.finalteam.toolsfinal.io.stream.ClosedInputStream */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ClosedInputStream extends InputStream {
    public static final ClosedInputStream CLOSED_INPUT_STREAM = new ClosedInputStream();

    @Override // java.io.InputStream
    public int read() {
        return -1;
    }
}
