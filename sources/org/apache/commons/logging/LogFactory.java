package org.apache.commons.logging;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.commons.logging.impl.Jdk14Logger;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class LogFactory {
    public static final String DIAGNOSTICS_DEST_PROPERTY = "org.apache.commons.logging.diagnostics.dest";
    public static final String FACTORY_DEFAULT = "org.apache.commons.logging.impl.LogFactoryImpl";
    public static final String FACTORY_PROPERTIES = "commons-logging.properties";
    public static final String FACTORY_PROPERTY = "org.apache.commons.logging.LogFactory";
    public static final String HASHTABLE_IMPLEMENTATION_PROPERTY = "org.apache.commons.logging.LogFactory.HashtableImpl";
    public static final String PRIORITY_KEY = "priority";
    protected static final String SERVICE_ID = "META-INF/services/org.apache.commons.logging.LogFactory";
    public static final String TCCL_KEY = "use_tccl";
    private static final String WEAK_HASHTABLE_CLASSNAME = "org.apache.commons.logging.impl.WeakHashtable";
    private static String diagnosticPrefix;
    protected static Hashtable factories;
    private static PrintStream diagnosticsStream = null;
    protected static LogFactory nullClassLoaderFactory = null;
    private static ClassLoader thisClassLoader = getClassLoader(LogFactory.class);

    public abstract Object getAttribute(String str);

    public abstract String[] getAttributeNames();

    public abstract InterfaceC13042Log getInstance(Class cls) throws LogConfigurationException;

    public abstract InterfaceC13042Log getInstance(String str) throws LogConfigurationException;

    public abstract void release();

    public abstract void removeAttribute(String str);

    public abstract void setAttribute(String str, Object obj);

    static {
        factories = null;
        initDiagnostics();
        logClassLoaderEnvironment(LogFactory.class);
        factories = createFactoryStore();
        if (isDiagnosticsEnabled()) {
            logDiagnostic("BOOTSTRAP COMPLETED");
        }
    }

    private static final Hashtable createFactoryStore() {
        Hashtable hashtable;
        String property = System.getProperty("org.apache.commons.logging.LogFactory.HashtableImpl");
        if (property == null) {
            property = "org.apache.commons.logging.impl.WeakHashtable";
        }
        try {
            hashtable = (Hashtable) Class.forName(property).newInstance();
        } catch (Throwable th) {
            if (!"org.apache.commons.logging.impl.WeakHashtable".equals(property)) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[ERROR] LogFactory: Load of custom hashtable failed");
                } else {
                    System.err.println("[ERROR] LogFactory: Load of custom hashtable failed");
                }
            }
            hashtable = null;
        }
        if (hashtable == null) {
            return new Hashtable();
        }
        return hashtable;
    }

    public static LogFactory getFactory() throws LogConfigurationException {
        ClassLoader classLoader;
        BufferedReader bufferedReader;
        String property;
        ClassLoader contextClassLoader = getContextClassLoader();
        if (contextClassLoader == null && isDiagnosticsEnabled()) {
            logDiagnostic("Context classloader is null.");
        }
        LogFactory cachedFactory = getCachedFactory(contextClassLoader);
        if (cachedFactory != null) {
            return cachedFactory;
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("[LOOKUP] LogFactory implementation requested for the first time for context classloader " + objectId(contextClassLoader));
            logHierarchy("[LOOKUP] ", contextClassLoader);
        }
        Properties configurationFile = getConfigurationFile(contextClassLoader, "commons-logging.properties");
        if (configurationFile != null && (property = configurationFile.getProperty("use_tccl")) != null && !Boolean.valueOf(property).booleanValue()) {
            classLoader = thisClassLoader;
        } else {
            classLoader = contextClassLoader;
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("[LOOKUP] Looking for system property [org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
        }
        try {
            String property2 = System.getProperty("org.apache.commons.logging.LogFactory");
            if (property2 != null) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] Creating an instance of LogFactory class '" + property2 + "' as specified by system property org.apache.commons.logging.LogFactory");
                }
                cachedFactory = newFactory(property2, classLoader, contextClassLoader);
            } else if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] No system property [org.apache.commons.logging.LogFactory] defined.");
            }
        } catch (SecurityException e) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + e.getMessage().trim() + "]. Trying alternative implementations...");
            }
        } catch (RuntimeException e2) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: [" + e2.getMessage().trim() + "] as specified by a system property.");
            }
            throw e2;
        }
        if (cachedFactory == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] Looking for a resource file of name [META-INF/services/org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
            }
            try {
                InputStream resourceAsStream = getResourceAsStream(contextClassLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
                if (resourceAsStream != null) {
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"));
                    } catch (UnsupportedEncodingException e3) {
                        bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
                    }
                    String readLine = bufferedReader.readLine();
                    bufferedReader.close();
                    if (readLine != null && !"".equals(readLine)) {
                        if (isDiagnosticsEnabled()) {
                            logDiagnostic("[LOOKUP]  Creating an instance of LogFactory class " + readLine + " as specified by file 'META-INF/services/org.apache.commons.logging.LogFactory' which was present in the path of the context classloader.");
                        }
                        cachedFactory = newFactory(readLine, classLoader, contextClassLoader);
                    }
                } else if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] No resource file with name 'META-INF/services/org.apache.commons.logging.LogFactory' found.");
                }
            } catch (Exception e4) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + e4.getMessage().trim() + "]. Trying alternative implementations...");
                }
            }
        }
        if (cachedFactory == null) {
            if (configurationFile != null) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] Looking in properties file for entry with key 'org.apache.commons.logging.LogFactory' to define the LogFactory subclass to use...");
                }
                String property3 = configurationFile.getProperty("org.apache.commons.logging.LogFactory");
                if (property3 != null) {
                    if (isDiagnosticsEnabled()) {
                        logDiagnostic("[LOOKUP] Properties file specifies LogFactory subclass '" + property3 + "'");
                    }
                    cachedFactory = newFactory(property3, classLoader, contextClassLoader);
                } else if (isDiagnosticsEnabled()) {
                    logDiagnostic("[LOOKUP] Properties file has no entry specifying LogFactory subclass.");
                }
            } else if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] No properties file available to determine LogFactory subclass from..");
            }
        }
        if (cachedFactory == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] Loading the default LogFactory implementation 'org.apache.commons.logging.impl.LogFactoryImpl' via the same classloader that loaded this LogFactory class (ie not looking in the context classloader).");
            }
            cachedFactory = newFactory("org.apache.commons.logging.impl.LogFactoryImpl", thisClassLoader, contextClassLoader);
        }
        if (cachedFactory != null) {
            cacheFactory(contextClassLoader, cachedFactory);
            if (configurationFile != null) {
                Enumeration<?> propertyNames = configurationFile.propertyNames();
                while (propertyNames.hasMoreElements()) {
                    String str = (String) propertyNames.nextElement();
                    cachedFactory.setAttribute(str, configurationFile.getProperty(str));
                }
            }
        }
        return cachedFactory;
    }

    public static InterfaceC13042Log getLog(Class cls) throws LogConfigurationException {
        return getLog(cls.getName());
    }

    public static InterfaceC13042Log getLog(String str) throws LogConfigurationException {
        return new Jdk14Logger(str);
    }

    public static void release(ClassLoader classLoader) {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Releasing factory for classloader " + objectId(classLoader));
        }
        synchronized (factories) {
            if (classLoader == null) {
                LogFactory logFactory = nullClassLoaderFactory;
                if (logFactory != null) {
                    logFactory.release();
                    nullClassLoaderFactory = null;
                }
            } else {
                LogFactory logFactory2 = (LogFactory) factories.get(classLoader);
                if (logFactory2 != null) {
                    logFactory2.release();
                    factories.remove(classLoader);
                }
            }
        }
    }

    public static void releaseAll() {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Releasing factory for all classloaders.");
        }
        synchronized (factories) {
            Enumeration elements = factories.elements();
            while (elements.hasMoreElements()) {
                ((LogFactory) elements.nextElement()).release();
            }
            factories.clear();
            LogFactory logFactory = nullClassLoaderFactory;
            if (logFactory != null) {
                logFactory.release();
                nullClassLoaderFactory = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ClassLoader getClassLoader(Class cls) {
        try {
            return cls.getClassLoader();
        } catch (SecurityException e) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Unable to get classloader for class '" + cls + "' due to security restrictions - " + e.getMessage());
            }
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ClassLoader getContextClassLoader() throws LogConfigurationException {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.logging.LogFactory.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return LogFactory.directGetContextClassLoader();
            }
        });
    }

    protected static ClassLoader directGetContextClassLoader() throws LogConfigurationException {
        try {
            ClassLoader classLoader = null;
            Class[] clsArr = null;
            try {
                try {
                    Object[] objArr = null;
                    classLoader = (ClassLoader) Thread.class.getMethod("getContextClassLoader", null).invoke(Thread.currentThread(), null);
                } catch (InvocationTargetException e) {
                    if (!(e.getTargetException() instanceof SecurityException)) {
                        throw new LogConfigurationException("Unexpected InvocationTargetException", e.getTargetException());
                    }
                }
                return classLoader;
            } catch (IllegalAccessException e2) {
                throw new LogConfigurationException("Unexpected IllegalAccessException", e2);
            }
        } catch (NoSuchMethodException e3) {
            return getClassLoader(LogFactory.class);
        }
    }

    private static LogFactory getCachedFactory(ClassLoader classLoader) {
        if (classLoader == null) {
            return nullClassLoaderFactory;
        }
        return (LogFactory) factories.get(classLoader);
    }

    private static void cacheFactory(ClassLoader classLoader, LogFactory logFactory) {
        if (logFactory != null) {
            if (classLoader == null) {
                nullClassLoaderFactory = logFactory;
            } else {
                factories.put(classLoader, logFactory);
            }
        }
    }

    protected static LogFactory newFactory(final String str, final ClassLoader classLoader, ClassLoader classLoader2) throws LogConfigurationException {
        Object doPrivileged = AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.logging.LogFactory.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return LogFactory.createFactory(str, classLoader);
            }
        });
        if (doPrivileged instanceof LogConfigurationException) {
            LogConfigurationException logConfigurationException = (LogConfigurationException) doPrivileged;
            if (isDiagnosticsEnabled()) {
                logDiagnostic("An error occurred while loading the factory class:" + logConfigurationException.getMessage());
            }
            throw logConfigurationException;
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Created object " + objectId(doPrivileged) + " to manage classloader " + objectId(classLoader2));
        }
        return (LogFactory) doPrivileged;
    }

    protected static LogFactory newFactory(String str, ClassLoader classLoader) {
        return newFactory(str, classLoader, null);
    }

    protected static Object createFactory(String str, ClassLoader classLoader) {
        String str2;
        Class<?> cls = null;
        try {
            if (classLoader != null) {
                try {
                    try {
                        cls = classLoader.loadClass(str);
                        if (LogFactory.class.isAssignableFrom(cls)) {
                            if (isDiagnosticsEnabled()) {
                                logDiagnostic("Loaded class " + cls.getName() + " from classloader " + objectId(classLoader));
                            }
                        } else if (isDiagnosticsEnabled()) {
                            logDiagnostic("Factory class " + cls.getName() + " loaded from classloader " + objectId(cls.getClassLoader()) + " does not extend '" + LogFactory.class.getName() + "' as loaded by this classloader.");
                            logHierarchy("[BAD CL TREE] ", classLoader);
                        }
                        return (LogFactory) cls.newInstance();
                    } catch (ClassNotFoundException e) {
                        if (classLoader == thisClassLoader) {
                            if (isDiagnosticsEnabled()) {
                                logDiagnostic("Unable to locate any class called '" + str + "' via classloader " + objectId(classLoader));
                            }
                            throw e;
                        }
                    } catch (NoClassDefFoundError e2) {
                        if (classLoader == thisClassLoader) {
                            if (isDiagnosticsEnabled()) {
                                logDiagnostic("Class '" + str + "' cannot be loaded via classloader " + objectId(classLoader) + " - it depends on some other class that cannot be found.");
                            }
                            throw e2;
                        }
                    }
                } catch (ClassCastException e3) {
                    if (classLoader == thisClassLoader) {
                        String str3 = "The application has specified that a custom LogFactory implementation should be used but Class '" + str + "' cannot be converted to '" + LogFactory.class.getName() + "'. ";
                        if (implementsLogFactory(cls)) {
                            str2 = str3 + "The conflict is caused by the presence of multiple LogFactory classes in incompatible classloaders. Background can be found in http://jakarta.apache.org/commons/logging/tech.html. If you have not explicitly specified a custom LogFactory then it is likely that the container has set one without your knowledge. In this case, consider using the commons-logging-adapters.jar file or specifying the standard LogFactory from the command line. ";
                        } else {
                            str2 = str3 + "Please check the custom implementation. ";
                        }
                        String str4 = str2 + "Help can be found @http://jakarta.apache.org/commons/logging/troubleshooting.html.";
                        if (isDiagnosticsEnabled()) {
                            logDiagnostic(str4);
                        }
                        throw new ClassCastException(str4);
                    }
                }
            }
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Unable to load factory class via classloader " + objectId(classLoader) + " - trying the classloader associated with this LogFactory.");
            }
            return (LogFactory) Class.forName(str).newInstance();
        } catch (Exception e4) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Unable to create LogFactory instance.");
            }
            if (cls != null && !LogFactory.class.isAssignableFrom(cls)) {
                return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", e4);
            }
            return new LogConfigurationException(e4);
        }
    }

    private static boolean implementsLogFactory(Class cls) {
        boolean z = false;
        if (cls != null) {
            try {
                ClassLoader classLoader = cls.getClassLoader();
                if (classLoader == null) {
                    logDiagnostic("[CUSTOM LOG FACTORY] was loaded by the boot classloader");
                } else {
                    logHierarchy("[CUSTOM LOG FACTORY] ", classLoader);
                    z = Class.forName("org.apache.commons.logging.LogFactory", false, classLoader).isAssignableFrom(cls);
                    if (z) {
                        logDiagnostic("[CUSTOM LOG FACTORY] " + cls.getName() + " implements LogFactory but was loaded by an incompatible classloader.");
                    } else {
                        logDiagnostic("[CUSTOM LOG FACTORY] " + cls.getName() + " does not implement LogFactory.");
                    }
                }
            } catch (ClassNotFoundException e) {
                logDiagnostic("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
            } catch (LinkageError e2) {
                logDiagnostic("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + e2.getMessage());
            } catch (SecurityException e3) {
                logDiagnostic("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + e3.getMessage());
            }
        }
        return z;
    }

    private static InputStream getResourceAsStream(final ClassLoader classLoader, final String str) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.logging.LogFactory.3
            @Override // java.security.PrivilegedAction
            public Object run() {
                ClassLoader classLoader2 = classLoader;
                if (classLoader2 != null) {
                    return classLoader2.getResourceAsStream(str);
                }
                return ClassLoader.getSystemResourceAsStream(str);
            }
        });
    }

    private static Enumeration getResources(final ClassLoader classLoader, final String str) {
        return (Enumeration) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.logging.LogFactory.4
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    ClassLoader classLoader2 = classLoader;
                    if (classLoader2 != null) {
                        return classLoader2.getResources(str);
                    }
                    return ClassLoader.getSystemResources(str);
                } catch (IOException e) {
                    if (LogFactory.isDiagnosticsEnabled()) {
                        LogFactory.logDiagnostic("Exception while trying to find configuration file " + str + ":" + e.getMessage());
                    }
                    return null;
                } catch (NoSuchMethodError e2) {
                    return null;
                }
            }
        });
    }

    private static Properties getProperties(final URL url) {
        return (Properties) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.logging.LogFactory.5
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    InputStream openStream = url.openStream();
                    if (openStream != null) {
                        Properties properties = new Properties();
                        properties.load(openStream);
                        openStream.close();
                        return properties;
                    }
                    return null;
                } catch (IOException e) {
                    if (LogFactory.isDiagnosticsEnabled()) {
                        LogFactory.logDiagnostic("Unable to read URL " + url);
                        return null;
                    }
                    return null;
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final java.util.Properties getConfigurationFile(java.lang.ClassLoader r13, java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 323
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.logging.LogFactory.getConfigurationFile(java.lang.ClassLoader, java.lang.String):java.util.Properties");
    }

    private static void initDiagnostics() {
        String str;
        try {
            String property = System.getProperty("org.apache.commons.logging.diagnostics.dest");
            if (property == null) {
                return;
            }
            if (property.equals("STDOUT")) {
                diagnosticsStream = System.out;
            } else if (property.equals("STDERR")) {
                diagnosticsStream = System.err;
            } else {
                try {
                    diagnosticsStream = new PrintStream(new FileOutputStream(property, true));
                } catch (IOException e) {
                    return;
                }
            }
            try {
                ClassLoader classLoader = thisClassLoader;
                if (classLoader == null) {
                    str = "BOOTLOADER";
                } else {
                    str = objectId(classLoader);
                }
            } catch (SecurityException e2) {
                str = "UNKNOWN";
            }
            diagnosticPrefix = "[LogFactory from " + str + "] ";
        } catch (SecurityException e3) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isDiagnosticsEnabled() {
        return diagnosticsStream != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void logDiagnostic(String str) {
        PrintStream printStream = diagnosticsStream;
        if (printStream != null) {
            printStream.print(diagnosticPrefix);
            diagnosticsStream.println(str);
            diagnosticsStream.flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final void logRawDiagnostic(String str) {
        PrintStream printStream = diagnosticsStream;
        if (printStream != null) {
            printStream.println(str);
            diagnosticsStream.flush();
        }
    }

    private static void logClassLoaderEnvironment(Class cls) {
        if (!isDiagnosticsEnabled()) {
            return;
        }
        try {
            logDiagnostic("[ENV] Extension directories (java.ext.dir): " + System.getProperty("java.ext.dir"));
            logDiagnostic("[ENV] Application classpath (java.class.path): " + System.getProperty("java.class.path"));
        } catch (SecurityException e) {
            logDiagnostic("[ENV] Security setting prevent interrogation of system classpaths.");
        }
        String name = cls.getName();
        try {
            ClassLoader classLoader = getClassLoader(cls);
            logDiagnostic("[ENV] Class " + name + " was loaded via classloader " + objectId(classLoader));
            logHierarchy("[ENV] Ancestry of classloader which loaded " + name + " is ", classLoader);
        } catch (SecurityException e2) {
            logDiagnostic("[ENV] Security forbids determining the classloader for " + name);
        }
    }

    private static void logHierarchy(String str, ClassLoader classLoader) {
        if (!isDiagnosticsEnabled()) {
            return;
        }
        if (classLoader != null) {
            logDiagnostic(str + objectId(classLoader) + " == '" + classLoader.toString() + "'");
        }
        try {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            if (classLoader != null) {
                StringBuffer stringBuffer = new StringBuffer(str + "ClassLoader tree:");
                do {
                    stringBuffer.append(objectId(classLoader));
                    if (classLoader == systemClassLoader) {
                        stringBuffer.append(" (SYSTEM) ");
                    }
                    try {
                        classLoader = classLoader.getParent();
                        stringBuffer.append(" --> ");
                    } catch (SecurityException e) {
                        stringBuffer.append(" --> SECRET");
                    }
                } while (classLoader != null);
                stringBuffer.append("BOOT");
                logDiagnostic(stringBuffer.toString());
            }
        } catch (SecurityException e2) {
            logDiagnostic(str + "Security forbids determining the system classloader.");
        }
    }

    public static String objectId(Object obj) {
        if (obj == null) {
            return "null";
        }
        return obj.getClass().getName() + "@" + System.identityHashCode(obj);
    }
}
