package p475rx.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import p475rx.annotations.Experimental;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.exceptions.CompositeException */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class CompositeException extends RuntimeException {
    private static final long serialVersionUID = 3026362227162912146L;
    private Throwable cause;
    private final List<Throwable> exceptions;
    private final String message;

    @Deprecated
    public CompositeException(String str, Collection<? extends Throwable> collection) {
        this.cause = null;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            for (Throwable th : collection) {
                if (th instanceof CompositeException) {
                    linkedHashSet.addAll(((CompositeException) th).getExceptions());
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException());
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException());
        }
        arrayList.addAll(linkedHashSet);
        this.exceptions = Collections.unmodifiableList(arrayList);
        this.message = this.exceptions.size() + " exceptions occurred. ";
    }

    public CompositeException(Collection<? extends Throwable> collection) {
        this(null, collection);
    }

    @Experimental
    public CompositeException(Throwable... thArr) {
        this.cause = null;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        if (thArr != null) {
            for (Throwable th : thArr) {
                if (th instanceof CompositeException) {
                    linkedHashSet.addAll(((CompositeException) th).getExceptions());
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException());
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException());
        }
        arrayList.addAll(linkedHashSet);
        this.exceptions = Collections.unmodifiableList(arrayList);
        this.message = this.exceptions.size() + " exceptions occurred. ";
    }

    public List<Throwable> getExceptions() {
        return this.exceptions;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        if (this.cause == null) {
            CompositeExceptionCausalChain compositeExceptionCausalChain = new CompositeExceptionCausalChain();
            HashSet hashSet = new HashSet();
            Iterator<Throwable> it = this.exceptions.iterator();
            CompositeExceptionCausalChain compositeExceptionCausalChain2 = compositeExceptionCausalChain;
            while (it.hasNext()) {
                Throwable next = it.next();
                if (!hashSet.contains(next)) {
                    hashSet.add(next);
                    for (Throwable th : getListOfCauses(next)) {
                        if (hashSet.contains(th)) {
                            next = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                        } else {
                            hashSet.add(th);
                        }
                    }
                    try {
                        compositeExceptionCausalChain2.initCause(next);
                        compositeExceptionCausalChain2 = compositeExceptionCausalChain2.getCause();
                    } catch (Throwable unused) {
                        compositeExceptionCausalChain2 = next;
                    }
                }
            }
            this.cause = compositeExceptionCausalChain;
        }
        return this.cause;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        printStackTrace(new WrappedPrintStream(printStream));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        printStackTrace(new WrappedPrintWriter(printWriter));
    }

    private void printStackTrace(PrintStreamOrWriter printStreamOrWriter) {
        StackTraceElement[] stackTrace;
        StringBuilder sb = new StringBuilder();
        sb.append(this);
        sb.append("\n");
        for (StackTraceElement stackTraceElement : getStackTrace()) {
            sb.append("\tat ");
            sb.append(stackTraceElement);
            sb.append("\n");
        }
        int i = 1;
        for (Throwable th : this.exceptions) {
            sb.append("  ComposedException ");
            sb.append(i);
            sb.append(" :");
            sb.append("\n");
            appendStackTrace(sb, th, "\t");
            i++;
        }
        synchronized (printStreamOrWriter.lock()) {
            printStreamOrWriter.println(sb.toString());
        }
    }

    private void appendStackTrace(StringBuilder sb, Throwable th, String str) {
        StackTraceElement[] stackTrace;
        sb.append(str);
        sb.append(th);
        sb.append("\n");
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            sb.append("\t\tat ");
            sb.append(stackTraceElement);
            sb.append("\n");
        }
        if (th.getCause() != null) {
            sb.append("\tCaused by: ");
            appendStackTrace(sb, th.getCause(), "");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.exceptions.CompositeException$PrintStreamOrWriter */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class PrintStreamOrWriter {
        abstract Object lock();

        abstract void println(Object obj);

        private PrintStreamOrWriter() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.exceptions.CompositeException$WrappedPrintStream */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class WrappedPrintStream extends PrintStreamOrWriter {
        private final PrintStream printStream;

        WrappedPrintStream(PrintStream printStream) {
            super();
            this.printStream = printStream;
        }

        @Override // p475rx.exceptions.CompositeException.PrintStreamOrWriter
        Object lock() {
            return this.printStream;
        }

        @Override // p475rx.exceptions.CompositeException.PrintStreamOrWriter
        void println(Object obj) {
            this.printStream.println(obj);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.exceptions.CompositeException$WrappedPrintWriter */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class WrappedPrintWriter extends PrintStreamOrWriter {
        private final PrintWriter printWriter;

        WrappedPrintWriter(PrintWriter printWriter) {
            super();
            this.printWriter = printWriter;
        }

        @Override // p475rx.exceptions.CompositeException.PrintStreamOrWriter
        Object lock() {
            return this.printWriter;
        }

        @Override // p475rx.exceptions.CompositeException.PrintStreamOrWriter
        void println(Object obj) {
            this.printWriter.println(obj);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.exceptions.CompositeException$CompositeExceptionCausalChain */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class CompositeExceptionCausalChain extends RuntimeException {
        static String MESSAGE = "Chain of Causes for CompositeException In Order Received =>";
        private static final long serialVersionUID = 3875212506787802066L;

        CompositeExceptionCausalChain() {
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return MESSAGE;
        }
    }

    private List<Throwable> getListOfCauses(Throwable th) {
        ArrayList arrayList = new ArrayList();
        Throwable cause = th.getCause();
        if (cause == null) {
            return arrayList;
        }
        while (true) {
            arrayList.add(cause);
            if (cause.getCause() == null) {
                return arrayList;
            }
            cause = cause.getCause();
        }
    }
}
