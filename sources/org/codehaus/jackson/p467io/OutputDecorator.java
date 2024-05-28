package org.codehaus.jackson.p467io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.codehaus.jackson.io.OutputDecorator */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class OutputDecorator {
    public abstract OutputStream decorate(IOContext iOContext, OutputStream outputStream) throws IOException;

    public abstract Writer decorate(IOContext iOContext, Writer writer) throws IOException;
}
