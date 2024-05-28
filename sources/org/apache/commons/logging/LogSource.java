package org.apache.commons.logging;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import org.apache.commons.logging.impl.NoOpLog;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class LogSource {
    protected static boolean jdk14IsAvailable;
    protected static boolean log4jIsAvailable;
    protected static Hashtable logs = new Hashtable();
    protected static Constructor logImplctor = null;

    static {
        log4jIsAvailable = false;
        jdk14IsAvailable = false;
        String str = null;
        try {
            if (Class.forName("org.apache.log4j.Logger") != null) {
                log4jIsAvailable = true;
            } else {
                log4jIsAvailable = false;
            }
        } catch (Throwable th) {
            log4jIsAvailable = false;
        }
        try {
            if (Class.forName("java.util.logging.Logger") != null && Class.forName("org.apache.commons.logging.impl.Jdk14Logger") != null) {
                jdk14IsAvailable = true;
            } else {
                jdk14IsAvailable = false;
            }
        } catch (Throwable th2) {
            jdk14IsAvailable = false;
        }
        try {
            str = System.getProperty("org.apache.commons.logging.log");
            if (str == null) {
                str = System.getProperty("org.apache.commons.logging.Log");
            }
        } catch (Throwable th3) {
        }
        if (str != null) {
            try {
                setLogImplementation(str);
                return;
            } catch (Throwable th4) {
                try {
                    setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
                    return;
                } catch (Throwable th5) {
                    return;
                }
            }
        }
        try {
            if (log4jIsAvailable) {
                setLogImplementation("org.apache.commons.logging.impl.Log4JLogger");
            } else if (jdk14IsAvailable) {
                setLogImplementation("org.apache.commons.logging.impl.Jdk14Logger");
            } else {
                setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
            }
        } catch (Throwable th6) {
            try {
                setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
            } catch (Throwable th7) {
            }
        }
    }

    private LogSource() {
    }

    public static void setLogImplementation(String str) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException, ClassNotFoundException {
        try {
            logImplctor = Class.forName(str).getConstructor("".getClass());
        } catch (Throwable th) {
            logImplctor = null;
        }
    }

    public static void setLogImplementation(Class cls) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException {
        logImplctor = cls.getConstructor("".getClass());
    }

    public static InterfaceC13042Log getInstance(String str) {
        InterfaceC13042Log interfaceC13042Log = (InterfaceC13042Log) logs.get(str);
        if (interfaceC13042Log == null) {
            InterfaceC13042Log makeNewLogInstance = makeNewLogInstance(str);
            logs.put(str, makeNewLogInstance);
            return makeNewLogInstance;
        }
        return interfaceC13042Log;
    }

    public static InterfaceC13042Log getInstance(Class cls) {
        return getInstance(cls.getName());
    }

    public static InterfaceC13042Log makeNewLogInstance(String str) {
        InterfaceC13042Log interfaceC13042Log;
        try {
            interfaceC13042Log = (InterfaceC13042Log) logImplctor.newInstance(str);
        } catch (Throwable th) {
            interfaceC13042Log = null;
        }
        if (interfaceC13042Log == null) {
            return new NoOpLog(str);
        }
        return interfaceC13042Log;
    }

    public static String[] getLogNames() {
        return (String[]) logs.keySet().toArray(new String[logs.size()]);
    }
}
