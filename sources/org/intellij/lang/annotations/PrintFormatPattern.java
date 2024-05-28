package org.intellij.lang.annotations;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PrintFormat.java */
/* loaded from: E:\9227576_dexfile_execute.dex */
class PrintFormatPattern {
    @Language("RegExp")
    private static final String ARG_INDEX = "(?:\\d+\\$)?";
    @Language("RegExp")
    private static final String CONVERSION = "(?:[tT])?(?:[a-zA-Z%])";
    @Language("RegExp")
    private static final String FLAGS = "(?:[-#+ 0,(<]*)?";
    @Language("RegExp")
    private static final String PRECISION = "(?:\\.\\d+)?";
    @Language("RegExp")
    static final String PRINT_FORMAT = "(?:[^%]|%%|(?:%(?:\\d+\\$)?(?:[-#+ 0,(<]*)?(?:\\d+)?(?:\\.\\d+)?(?:[tT])?(?:[a-zA-Z%])))*";
    @Language("RegExp")
    private static final String TEXT = "[^%]|%%";
    @Language("RegExp")
    private static final String WIDTH = "(?:\\d+)?";

    PrintFormatPattern() {
    }
}
