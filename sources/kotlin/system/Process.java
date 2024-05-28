package kotlin.system;

import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b¨\u0006\u0004"}, m1890d2 = {"exitProcess", "", "status", "", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
@JvmName(name = "ProcessKt")
/* renamed from: kotlin.system.ProcessKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class Process {
    @InlineOnly
    private static final Void exitProcess(int i) {
        System.exit(i);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}
