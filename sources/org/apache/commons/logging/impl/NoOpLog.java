package org.apache.commons.logging.impl;

import java.io.Serializable;
import org.apache.commons.logging.InterfaceC13042Log;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class NoOpLog implements InterfaceC13042Log, Serializable {
    public NoOpLog() {
    }

    public NoOpLog(String str) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void trace(Object obj) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void trace(Object obj, Throwable th) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void debug(Object obj) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void debug(Object obj, Throwable th) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void info(Object obj) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void info(Object obj, Throwable th) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void warn(Object obj) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void warn(Object obj, Throwable th) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void error(Object obj) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void error(Object obj, Throwable th) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void fatal(Object obj) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public void fatal(Object obj, Throwable th) {
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public final boolean isDebugEnabled() {
        return false;
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public final boolean isErrorEnabled() {
        return false;
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public final boolean isFatalEnabled() {
        return false;
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public final boolean isInfoEnabled() {
        return false;
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public final boolean isTraceEnabled() {
        return false;
    }

    @Override // org.apache.commons.logging.InterfaceC13042Log
    public final boolean isWarnEnabled() {
        return false;
    }
}
