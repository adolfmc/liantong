package com.p210hp.hpl.sparta;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.ParseSource */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ParseSource {
    public static final ParseLog DEFAULT_LOG = new DefaultLog();
    public static final int MAXLOOKAHEAD = 71;

    int getLineNumber();

    String getSystemId();

    String toString();
}
