package org.slf4j.helpers;

import java.io.ObjectStreamException;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class NamedLoggerBase implements Serializable, Logger {
    private static final long serialVersionUID = 7535258609338176893L;
    protected String name;

    public String getName() {
        return this.name;
    }

    protected Object readResolve() throws ObjectStreamException {
        return LoggerFactory.getLogger(getName());
    }
}
