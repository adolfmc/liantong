package org.codehaus.jackson.p467io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.codehaus.jackson.io.InputDecorator */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class InputDecorator {
    public abstract InputStream decorate(IOContext iOContext, InputStream inputStream) throws IOException;

    public abstract InputStream decorate(IOContext iOContext, byte[] bArr, int i, int i2) throws IOException;

    public abstract Reader decorate(IOContext iOContext, Reader reader) throws IOException;
}
