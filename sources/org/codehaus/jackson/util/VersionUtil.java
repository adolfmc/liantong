package org.codehaus.jackson.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.codehaus.jackson.Version;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class VersionUtil {
    public static final String VERSION_FILE = "VERSION.txt";
    private static final Pattern VERSION_SEPARATOR = Pattern.compile("[-_./;:]");

    public static Version versionFor(Class<?> cls) {
        Version version = null;
        try {
            InputStream resourceAsStream = cls.getResourceAsStream("VERSION.txt");
            if (resourceAsStream != null) {
                version = parseVersion(new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8")).readLine());
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException unused) {
        }
        return version == null ? Version.unknownVersion() : version;
    }

    public static Version parseVersion(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return null;
        }
        String[] split = VERSION_SEPARATOR.split(trim);
        if (split.length < 2) {
            return null;
        }
        return new Version(parseVersionPart(split[0]), parseVersionPart(split[1]), split.length > 2 ? parseVersionPart(split[2]) : 0, split.length > 3 ? split[3] : null);
    }

    protected static int parseVersionPart(String str) {
        String str2 = str.toString();
        int length = str2.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str2.charAt(i2);
            if (charAt > '9' || charAt < '0') {
                break;
            }
            i = (i * 10) + (charAt - '0');
        }
        return i;
    }
}
