package org.apache.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class VersionInfo {
    public static final String PROPERTY_MODULE = "info.module";
    public static final String PROPERTY_RELEASE = "info.release";
    public static final String PROPERTY_TIMESTAMP = "info.timestamp";
    public static final String UNAVAILABLE = "UNAVAILABLE";
    public static final String VERSION_PROPERTY_FILE = "version.properties";
    private final String infoClassloader;
    private final String infoModule;
    private final String infoPackage;
    private final String infoRelease;
    private final String infoTimestamp;

    protected VersionInfo(String str, String str2, String str3, String str4, String str5) {
        if (str == null) {
            throw new IllegalArgumentException("Package identifier must not be null.");
        }
        this.infoPackage = str;
        this.infoModule = str2 == null ? "UNAVAILABLE" : str2;
        this.infoRelease = str3 == null ? "UNAVAILABLE" : str3;
        this.infoTimestamp = str4 == null ? "UNAVAILABLE" : str4;
        this.infoClassloader = str5 == null ? "UNAVAILABLE" : str5;
    }

    public final String getPackage() {
        return this.infoPackage;
    }

    public final String getModule() {
        return this.infoModule;
    }

    public final String getRelease() {
        return this.infoRelease;
    }

    public final String getTimestamp() {
        return this.infoTimestamp;
    }

    public final String getClassloader() {
        return this.infoClassloader;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(this.infoPackage.length() + 20 + this.infoModule.length() + this.infoRelease.length() + this.infoTimestamp.length() + this.infoClassloader.length());
        stringBuffer.append("VersionInfo(").append(this.infoPackage).append(':').append(this.infoModule);
        if (!"UNAVAILABLE".equals(this.infoRelease)) {
            stringBuffer.append(':').append(this.infoRelease);
        }
        if (!"UNAVAILABLE".equals(this.infoTimestamp)) {
            stringBuffer.append(':').append(this.infoTimestamp);
        }
        stringBuffer.append(')');
        if (!"UNAVAILABLE".equals(this.infoClassloader)) {
            stringBuffer.append('@').append(this.infoClassloader);
        }
        return stringBuffer.toString();
    }

    public static final VersionInfo[] loadVersionInfo(String[] strArr, ClassLoader classLoader) {
        if (strArr == null) {
            throw new IllegalArgumentException("Package identifier list must not be null.");
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            VersionInfo loadVersionInfo = loadVersionInfo(str, classLoader);
            if (loadVersionInfo != null) {
                arrayList.add(loadVersionInfo);
            }
        }
        return (VersionInfo[]) arrayList.toArray(new VersionInfo[arrayList.size()]);
    }

    public static final VersionInfo loadVersionInfo(String str, ClassLoader classLoader) {
        Properties properties;
        if (str == null) {
            throw new IllegalArgumentException("Package identifier must not be null.");
        }
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        try {
            InputStream resourceAsStream = classLoader.getResourceAsStream(str.replace('.', '/') + "/version.properties");
            if (resourceAsStream == null) {
                properties = null;
            } else {
                properties = new Properties();
                properties.load(resourceAsStream);
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                }
            }
        } catch (IOException e2) {
            properties = null;
        }
        if (properties == null) {
            return null;
        }
        return fromMap(str, properties, classLoader);
    }

    protected static final VersionInfo fromMap(String str, Map map, ClassLoader classLoader) {
        String str2;
        String str3;
        String str4;
        String str5;
        if (str == null) {
            throw new IllegalArgumentException("Package identifier must not be null.");
        }
        if (map == null) {
            str2 = null;
            str3 = null;
            str4 = null;
        } else {
            String str6 = (String) map.get("info.module");
            if (str6 != null && str6.length() < 1) {
                str6 = null;
            }
            String str7 = (String) map.get("info.release");
            if (str7 != null && (str7.length() < 1 || str7.equals("${pom.version}"))) {
                str7 = null;
            }
            String str8 = (String) map.get("info.timestamp");
            if (str8 != null && (str8.length() < 1 || str8.equals("${mvn.timestamp}"))) {
                str4 = null;
                str2 = str6;
                str3 = str7;
            } else {
                str4 = str8;
                str2 = str6;
                str3 = str7;
            }
        }
        if (classLoader == null) {
            str5 = null;
        } else {
            str5 = classLoader.toString();
        }
        return new VersionInfo(str, str2, str3, str4, str5);
    }
}
