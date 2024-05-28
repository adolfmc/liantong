package com.p210hp.hpl.sparta;

import java.io.PrintStream;

/* compiled from: ParseSource.java */
/* renamed from: com.hp.hpl.sparta.DefaultLog */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class DefaultLog implements ParseLog {
    @Override // com.p210hp.hpl.sparta.ParseLog
    public void error(String str, String str2, int i) {
        PrintStream printStream = System.err;
        printStream.println(str2 + "(" + i + "): " + str + " (ERROR)");
    }

    @Override // com.p210hp.hpl.sparta.ParseLog
    public void warning(String str, String str2, int i) {
        PrintStream printStream = System.out;
        printStream.println(str2 + "(" + i + "): " + str + " (WARNING)");
    }

    @Override // com.p210hp.hpl.sparta.ParseLog
    public void note(String str, String str2, int i) {
        PrintStream printStream = System.out;
        printStream.println(str2 + "(" + i + "): " + str + " (NOTE)");
    }
}
