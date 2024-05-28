package org.apache.commons.logging.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class LogFactoryImpl extends LogFactory {
    public static final String ALLOW_FLAWED_CONTEXT_PROPERTY = "org.apache.commons.logging.Log.allowFlawedContext";
    public static final String ALLOW_FLAWED_DISCOVERY_PROPERTY = "org.apache.commons.logging.Log.allowFlawedDiscovery";
    public static final String ALLOW_FLAWED_HIERARCHY_PROPERTY = "org.apache.commons.logging.Log.allowFlawedHierarchy";
    private static final String LOGGING_IMPL_JDK14_LOGGER = "org.apache.commons.logging.impl.Jdk14Logger";
    private static final String LOGGING_IMPL_LOG4J_LOGGER = "org.apache.commons.logging.impl.Log4JLogger";
    private static final String LOGGING_IMPL_LUMBERJACK_LOGGER = "org.apache.commons.logging.impl.Jdk13LumberjackLogger";
    private static final String LOGGING_IMPL_SIMPLE_LOGGER = "org.apache.commons.logging.impl.SimpleLog";
    public static final String LOG_PROPERTY = "org.apache.commons.logging.Log";
    protected static final String LOG_PROPERTY_OLD = "org.apache.commons.logging.log";
    private static final String PKG_IMPL = "org.apache.commons.logging.impl.";
    private static final int PKG_LEN = "org.apache.commons.logging.impl.".length();
    private static final String[] classesToDiscover = {"org.apache.commons.logging.impl.Log4JLogger", "org.apache.commons.logging.impl.Jdk14Logger", "org.apache.commons.logging.impl.Jdk13LumberjackLogger", "org.apache.commons.logging.impl.SimpleLog"};
    private boolean allowFlawedContext;
    private boolean allowFlawedDiscovery;
    private boolean allowFlawedHierarchy;
    private String diagnosticPrefix;
    private String logClassName;
    private boolean useTCCL = true;
    protected Hashtable attributes = new Hashtable();
    protected Hashtable instances = new Hashtable();
    protected Constructor logConstructor = null;
    protected Class[] logConstructorSignature = {String.class};
    protected Method logMethod = null;
    protected Class[] logMethodSignature = {LogFactory.class};

    public LogFactoryImpl() {
        initDiagnostics();
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Instance created.");
        }
    }

    @Override // org.apache.commons.logging.LogFactory
    public Object getAttribute(String str) {
        return this.attributes.get(str);
    }

    @Override // org.apache.commons.logging.LogFactory
    public String[] getAttributeNames() {
        Vector vector = new Vector();
        Enumeration keys = this.attributes.keys();
        while (keys.hasMoreElements()) {
            vector.addElement((String) keys.nextElement());
        }
        int size = vector.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    @Override // org.apache.commons.logging.LogFactory
    public InterfaceC13042Log getInstance(Class cls) throws LogConfigurationException {
        return getInstance(cls.getName());
    }

    @Override // org.apache.commons.logging.LogFactory
    public InterfaceC13042Log getInstance(String str) throws LogConfigurationException {
        InterfaceC13042Log interfaceC13042Log = (InterfaceC13042Log) this.instances.get(str);
        if (interfaceC13042Log == null) {
            InterfaceC13042Log newInstance = newInstance(str);
            this.instances.put(str, newInstance);
            return newInstance;
        }
        return interfaceC13042Log;
    }

    @Override // org.apache.commons.logging.LogFactory
    public void release() {
        logDiagnostic("Releasing all known loggers");
        this.instances.clear();
    }

    @Override // org.apache.commons.logging.LogFactory
    public void removeAttribute(String str) {
        this.attributes.remove(str);
    }

    @Override // org.apache.commons.logging.LogFactory
    public void setAttribute(String str, Object obj) {
        if (this.logConstructor != null) {
            logDiagnostic("setAttribute: call too late; configuration already performed.");
        }
        if (obj == null) {
            this.attributes.remove(str);
        } else {
            this.attributes.put(str, obj);
        }
        if (str.equals("use_tccl")) {
            this.useTCCL = Boolean.valueOf(obj.toString()).booleanValue();
        }
    }

    protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
        return LogFactory.getContextClassLoader();
    }

    protected static boolean isDiagnosticsEnabled() {
        return LogFactory.isDiagnosticsEnabled();
    }

    protected static ClassLoader getClassLoader(Class cls) {
        return LogFactory.getClassLoader(cls);
    }

    private void initDiagnostics() {
        String str;
        ClassLoader classLoader = getClassLoader(getClass());
        if (classLoader == null) {
            str = "BOOTLOADER";
        } else {
            try {
                str = objectId(classLoader);
            } catch (SecurityException e) {
                str = "UNKNOWN";
            }
        }
        this.diagnosticPrefix = "[LogFactoryImpl@" + System.identityHashCode(this) + " from " + str + "] ";
    }

    protected void logDiagnostic(String str) {
        if (isDiagnosticsEnabled()) {
            logRawDiagnostic(this.diagnosticPrefix + str);
        }
    }

    protected String getLogClassName() {
        if (this.logClassName == null) {
            discoverLogImplementation(getClass().getName());
        }
        return this.logClassName;
    }

    protected Constructor getLogConstructor() throws LogConfigurationException {
        if (this.logConstructor == null) {
            discoverLogImplementation(getClass().getName());
        }
        return this.logConstructor;
    }

    protected boolean isJdk13LumberjackAvailable() {
        return isLogLibraryAvailable("Jdk13Lumberjack", "org.apache.commons.logging.impl.Jdk13LumberjackLogger");
    }

    protected boolean isJdk14Available() {
        return isLogLibraryAvailable("Jdk14", "org.apache.commons.logging.impl.Jdk14Logger");
    }

    protected boolean isLog4JAvailable() {
        return isLogLibraryAvailable("Log4J", "org.apache.commons.logging.impl.Log4JLogger");
    }

    protected InterfaceC13042Log newInstance(String str) throws LogConfigurationException {
        InterfaceC13042Log interfaceC13042Log;
        try {
            Constructor constructor = this.logConstructor;
            if (constructor == null) {
                interfaceC13042Log = discoverLogImplementation(str);
            } else {
                interfaceC13042Log = (InterfaceC13042Log) constructor.newInstance(str);
            }
            Method method = this.logMethod;
            if (method != null) {
                method.invoke(interfaceC13042Log, this);
            }
            return interfaceC13042Log;
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException != null) {
                throw new LogConfigurationException(targetException);
            }
            throw new LogConfigurationException(e);
        } catch (LogConfigurationException e2) {
            throw e2;
        } catch (Throwable th) {
            throw new LogConfigurationException(th);
        }
    }

    private boolean isLogLibraryAvailable(String str, String str2) {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Checking for '" + str + "'.");
        }
        try {
            if (createLogFromClass(str2, getClass().getName(), false) == null) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("Did not find '" + str + "'.");
                }
                return false;
            } else if (isDiagnosticsEnabled()) {
                logDiagnostic("Found '" + str + "'.");
                return true;
            } else {
                return true;
            }
        } catch (LogConfigurationException e) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Logging system '" + str + "' is available but not useable.");
            }
            return false;
        }
    }

    private String getConfigurationValue(String str) {
        String property;
        if (isDiagnosticsEnabled()) {
            logDiagnostic("[ENV] Trying to get configuration for item " + str);
        }
        Object attribute = getAttribute(str);
        if (attribute != null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[ENV] Found LogFactory attribute [" + attribute + "] for " + str);
            }
            return attribute.toString();
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("[ENV] No LogFactory attribute found for " + str);
        }
        try {
            property = System.getProperty(str);
        } catch (SecurityException e) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[ENV] Security prevented reading system property " + str);
            }
        }
        if (property != null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[ENV] Found system property [" + property + "] for " + str);
            }
            return property;
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("[ENV] No system property found for property " + str);
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("[ENV] No configuration defined for item " + str);
            return null;
        }
        return null;
    }

    private boolean getBooleanConfiguration(String str, boolean z) {
        String configurationValue = getConfigurationValue(str);
        if (configurationValue == null) {
            return z;
        }
        return Boolean.valueOf(configurationValue).booleanValue();
    }

    private void initConfiguration() {
        this.allowFlawedContext = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedContext", true);
        this.allowFlawedDiscovery = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedDiscovery", true);
        this.allowFlawedHierarchy = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedHierarchy", true);
    }

    private InterfaceC13042Log discoverLogImplementation(String str) throws LogConfigurationException {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Discovering a Log implementation...");
        }
        initConfiguration();
        InterfaceC13042Log interfaceC13042Log = null;
        String findUserSpecifiedLogClassName = findUserSpecifiedLogClassName();
        if (findUserSpecifiedLogClassName != null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Attempting to load user-specified log class '" + findUserSpecifiedLogClassName + "'...");
            }
            InterfaceC13042Log createLogFromClass = createLogFromClass(findUserSpecifiedLogClassName, str, true);
            if (createLogFromClass == null) {
                StringBuffer stringBuffer = new StringBuffer("User-specified log class '");
                stringBuffer.append(findUserSpecifiedLogClassName);
                stringBuffer.append("' cannot be found or is not useable.");
                if (findUserSpecifiedLogClassName != null) {
                    informUponSimilarName(stringBuffer, findUserSpecifiedLogClassName, "org.apache.commons.logging.impl.Log4JLogger");
                    informUponSimilarName(stringBuffer, findUserSpecifiedLogClassName, "org.apache.commons.logging.impl.Jdk14Logger");
                    informUponSimilarName(stringBuffer, findUserSpecifiedLogClassName, "org.apache.commons.logging.impl.Jdk13LumberjackLogger");
                    informUponSimilarName(stringBuffer, findUserSpecifiedLogClassName, "org.apache.commons.logging.impl.SimpleLog");
                }
                throw new LogConfigurationException(stringBuffer.toString());
            }
            return createLogFromClass;
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("No user-specified Log implementation; performing discovery using the standard supported logging implementations...");
        }
        int i = 0;
        while (true) {
            String[] strArr = classesToDiscover;
            if (i >= strArr.length || interfaceC13042Log != null) {
                break;
            }
            interfaceC13042Log = createLogFromClass(strArr[i], str, true);
            i++;
        }
        if (interfaceC13042Log == null) {
            throw new LogConfigurationException("No suitable Log implementation");
        }
        return interfaceC13042Log;
    }

    private void informUponSimilarName(StringBuffer stringBuffer, String str, String str2) {
        if (!str.equals(str2) && str.regionMatches(true, 0, str2, 0, PKG_LEN + 5)) {
            stringBuffer.append(" Did you mean '");
            stringBuffer.append(str2);
            stringBuffer.append("'?");
        }
    }

    private String findUserSpecifiedLogClassName() {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.Log'");
        }
        String str = (String) getAttribute("org.apache.commons.logging.Log");
        if (str == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.log'");
            }
            str = (String) getAttribute("org.apache.commons.logging.log");
        }
        if (str == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.Log'");
            }
            try {
                str = System.getProperty("org.apache.commons.logging.Log");
            } catch (SecurityException e) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("No access allowed to system property 'org.apache.commons.logging.Log' - " + e.getMessage());
                }
            }
        }
        if (str == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.log'");
            }
            try {
                str = System.getProperty("org.apache.commons.logging.log");
            } catch (SecurityException e2) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("No access allowed to system property 'org.apache.commons.logging.log' - " + e2.getMessage());
                }
            }
        }
        if (str != null) {
            return str.trim();
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x01a1 A[LOOP:0: B:6:0x003e->B:43:0x01a1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0236 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x019e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.apache.commons.logging.InterfaceC13042Log createLogFromClass(java.lang.String r17, java.lang.String r18, boolean r19) throws org.apache.commons.logging.LogConfigurationException {
        /*
            Method dump skipped, instructions count: 698
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.logging.impl.LogFactoryImpl.createLogFromClass(java.lang.String, java.lang.String, boolean):org.apache.commons.logging.Log");
    }

    private ClassLoader getBaseClassLoader() throws LogConfigurationException {
        ClassLoader classLoader = getClassLoader(LogFactoryImpl.class);
        if (!this.useTCCL) {
            return classLoader;
        }
        ClassLoader contextClassLoader = getContextClassLoader();
        ClassLoader lowestClassLoader = getLowestClassLoader(contextClassLoader, classLoader);
        if (lowestClassLoader == null) {
            if (this.allowFlawedContext) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[WARNING] the context classloader is not part of a parent-child relationship with the classloader that loaded LogFactoryImpl.");
                }
                return contextClassLoader;
            }
            throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
        }
        if (lowestClassLoader != contextClassLoader) {
            if (this.allowFlawedContext) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("Warning: the context classloader is an ancestor of the classloader that loaded LogFactoryImpl; it should be the same or a descendant. The application using commons-logging should ensure the context classloader is used correctly.");
                }
            } else {
                throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
            }
        }
        return lowestClassLoader;
    }

    private ClassLoader getLowestClassLoader(ClassLoader classLoader, ClassLoader classLoader2) {
        if (classLoader == null) {
            return classLoader2;
        }
        if (classLoader2 == null) {
            return classLoader;
        }
        for (ClassLoader classLoader3 = classLoader; classLoader3 != null; classLoader3 = classLoader3.getParent()) {
            if (classLoader3 == classLoader2) {
                return classLoader;
            }
        }
        for (ClassLoader classLoader4 = classLoader2; classLoader4 != null; classLoader4 = classLoader4.getParent()) {
            if (classLoader4 == classLoader) {
                return classLoader2;
            }
        }
        return null;
    }

    private void handleFlawedDiscovery(String str, ClassLoader classLoader, Throwable th) {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Could not instantiate Log '" + str + "' -- " + th.getClass().getName() + ": " + th.getLocalizedMessage());
        }
        if (!this.allowFlawedDiscovery) {
            throw new LogConfigurationException(th);
        }
    }

    private void handleFlawedHierarchy(ClassLoader classLoader, Class cls) throws LogConfigurationException {
        String name = InterfaceC13042Log.class.getName();
        Class<?>[] interfaces = cls.getInterfaces();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= interfaces.length) {
                break;
            } else if (!name.equals(interfaces[i].getName())) {
                i++;
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            if (isDiagnosticsEnabled()) {
                try {
                    logDiagnostic("Class '" + cls.getName() + "' was found in classloader " + objectId(classLoader) + ". It is bound to a Log interface which is not the one loaded from classloader " + objectId(getClassLoader(InterfaceC13042Log.class)));
                } catch (Throwable th) {
                    logDiagnostic("Error while trying to output diagnostics about bad class '" + cls + "'");
                }
            }
            if (!this.allowFlawedHierarchy) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Terminating logging for this context ");
                stringBuffer.append("due to bad log hierarchy. ");
                stringBuffer.append("You have more than one version of '");
                stringBuffer.append(InterfaceC13042Log.class.getName());
                stringBuffer.append("' visible.");
                if (isDiagnosticsEnabled()) {
                    logDiagnostic(stringBuffer.toString());
                }
                throw new LogConfigurationException(stringBuffer.toString());
            } else if (isDiagnosticsEnabled()) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Warning: bad log hierarchy. ");
                stringBuffer2.append("You have more than one version of '");
                stringBuffer2.append(InterfaceC13042Log.class.getName());
                stringBuffer2.append("' visible.");
                logDiagnostic(stringBuffer2.toString());
            }
        } else if (!this.allowFlawedDiscovery) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Terminating logging for this context. ");
            stringBuffer3.append("Log class '");
            stringBuffer3.append(cls.getName());
            stringBuffer3.append("' does not implement the Log interface.");
            if (isDiagnosticsEnabled()) {
                logDiagnostic(stringBuffer3.toString());
            }
            throw new LogConfigurationException(stringBuffer3.toString());
        } else if (isDiagnosticsEnabled()) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("[WARNING] Log class '");
            stringBuffer4.append(cls.getName());
            stringBuffer4.append("' does not implement the Log interface.");
            logDiagnostic(stringBuffer4.toString());
        }
    }
}
