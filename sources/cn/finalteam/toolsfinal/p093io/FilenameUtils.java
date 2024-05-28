package cn.finalteam.toolsfinal.p093io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.finalteam.toolsfinal.io.FilenameUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FilenameUtils {
    public static final char EXTENSION_SEPARATOR = '.';
    private static final char OTHER_SEPARATOR;
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';
    public static final String EXTENSION_SEPARATOR_STR = Character.toString('.');
    private static final char SYSTEM_SEPARATOR = File.separatorChar;

    private static boolean isSeparator(char c) {
        return c == '/' || c == '\\';
    }

    static {
        if (isSystemWindows()) {
            OTHER_SEPARATOR = '/';
        } else {
            OTHER_SEPARATOR = '\\';
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == '\\';
    }

    public static String normalize(String str) {
        return doNormalize(str, SYSTEM_SEPARATOR, true);
    }

    public static String normalize(String str, boolean z) {
        return doNormalize(str, z ? '/' : '\\', true);
    }

    public static String normalizeNoEndSeparator(String str) {
        return doNormalize(str, SYSTEM_SEPARATOR, false);
    }

    public static String normalizeNoEndSeparator(String str, boolean z) {
        return doNormalize(str, z ? '/' : '\\', false);
    }

    private static String doNormalize(String str, char c, boolean z) {
        int i;
        boolean z2;
        int i2;
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return str;
        }
        int prefixLength = getPrefixLength(str);
        if (prefixLength < 0) {
            return null;
        }
        char[] cArr = new char[length + 2];
        str.getChars(0, str.length(), cArr, 0);
        char c2 = SYSTEM_SEPARATOR;
        if (c == c2) {
            c2 = OTHER_SEPARATOR;
        }
        for (int i3 = 0; i3 < cArr.length; i3++) {
            if (cArr[i3] == c2) {
                cArr[i3] = c;
            }
        }
        if (cArr[length - 1] != c) {
            i = length + 1;
            cArr[length] = c;
            z2 = false;
        } else {
            i = length;
            z2 = true;
        }
        int i4 = prefixLength + 1;
        int i5 = i;
        int i6 = i4;
        while (i6 < i5) {
            if (cArr[i6] == c) {
                int i7 = i6 - 1;
                if (cArr[i7] == c) {
                    System.arraycopy(cArr, i6, cArr, i7, i5 - i6);
                    i5--;
                    i6--;
                }
            }
            i6++;
        }
        int i8 = i4;
        while (i8 < i5) {
            if (cArr[i8] == c) {
                int i9 = i8 - 1;
                if (cArr[i9] == '.' && (i8 == i4 || cArr[i8 - 2] == c)) {
                    if (i8 == i5 - 1) {
                        z2 = true;
                    }
                    System.arraycopy(cArr, i8 + 1, cArr, i9, i5 - i8);
                    i5 -= 2;
                    i8--;
                }
            }
            i8++;
        }
        int i10 = prefixLength + 2;
        boolean z3 = z2;
        int i11 = i10;
        while (i11 < i5) {
            if (cArr[i11] != c || cArr[i11 - 1] != '.' || cArr[i11 - 2] != '.' || (i11 != i10 && cArr[i11 - 3] != c)) {
                i2 = i11;
            } else if (i11 == i10) {
                return null;
            } else {
                if (i11 == i5 - 1) {
                    z3 = true;
                }
                int i12 = i11 - 4;
                while (true) {
                    if (i12 >= prefixLength) {
                        if (cArr[i12] == c) {
                            i2 = i12 + 1;
                            System.arraycopy(cArr, i11 + 1, cArr, i2, i5 - i11);
                            i5 -= i11 - i12;
                            break;
                        }
                        i12--;
                    } else {
                        int i13 = i11 + 1;
                        System.arraycopy(cArr, i13, cArr, prefixLength, i5 - i11);
                        i5 -= i13 - prefixLength;
                        i2 = i4;
                        break;
                    }
                }
            }
            i11 = i2 + 1;
        }
        if (i5 <= 0) {
            return "";
        }
        if (i5 <= prefixLength) {
            return new String(cArr, 0, i5);
        }
        if (z3 && z) {
            return new String(cArr, 0, i5);
        }
        return new String(cArr, 0, i5 - 1);
    }

    public static String concat(String str, String str2) {
        int prefixLength = getPrefixLength(str2);
        if (prefixLength < 0) {
            return null;
        }
        if (prefixLength > 0) {
            return normalize(str2);
        }
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return normalize(str2);
        }
        if (isSeparator(str.charAt(length - 1))) {
            return normalize(str + str2);
        }
        return normalize(str + '/' + str2);
    }

    public static String separatorsToUnix(String str) {
        return (str == null || str.indexOf(92) == -1) ? str : str.replace('\\', '/');
    }

    public static String separatorsToWindows(String str) {
        return (str == null || str.indexOf(47) == -1) ? str : str.replace('/', '\\');
    }

    public static String separatorsToSystem(String str) {
        if (str == null) {
            return null;
        }
        if (isSystemWindows()) {
            return separatorsToWindows(str);
        }
        return separatorsToUnix(str);
    }

    public static int getPrefixLength(String str) {
        if (str == null) {
            return -1;
        }
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        char charAt = str.charAt(0);
        if (charAt == ':') {
            return -1;
        }
        if (length == 1) {
            if (charAt == '~') {
                return 2;
            }
            return isSeparator(charAt) ? 1 : 0;
        } else if (charAt == '~') {
            int indexOf = str.indexOf(47, 1);
            int indexOf2 = str.indexOf(92, 1);
            if (indexOf == -1 && indexOf2 == -1) {
                return length + 1;
            }
            if (indexOf == -1) {
                indexOf = indexOf2;
            }
            if (indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            return Math.min(indexOf, indexOf2) + 1;
        } else {
            char charAt2 = str.charAt(1);
            if (charAt2 == ':') {
                char upperCase = Character.toUpperCase(charAt);
                if (upperCase < 'A' || upperCase > 'Z') {
                    return -1;
                }
                return (length == 2 || !isSeparator(str.charAt(2))) ? 2 : 3;
            } else if (isSeparator(charAt) && isSeparator(charAt2)) {
                int indexOf3 = str.indexOf(47, 2);
                int indexOf4 = str.indexOf(92, 2);
                if ((indexOf3 == -1 && indexOf4 == -1) || indexOf3 == 2 || indexOf4 == 2) {
                    return -1;
                }
                if (indexOf3 == -1) {
                    indexOf3 = indexOf4;
                }
                if (indexOf4 == -1) {
                    indexOf4 = indexOf3;
                }
                return Math.min(indexOf3, indexOf4) + 1;
            } else {
                return isSeparator(charAt) ? 1 : 0;
            }
        }
    }

    public static int indexOfLastSeparator(String str) {
        if (str == null) {
            return -1;
        }
        return Math.max(str.lastIndexOf(47), str.lastIndexOf(92));
    }

    public static int indexOfExtension(String str) {
        int lastIndexOf;
        if (str != null && indexOfLastSeparator(str) <= (lastIndexOf = str.lastIndexOf(46))) {
            return lastIndexOf;
        }
        return -1;
    }

    public static String getPrefix(String str) {
        int prefixLength;
        if (str != null && (prefixLength = getPrefixLength(str)) >= 0) {
            if (prefixLength > str.length()) {
                return str + '/';
            }
            return str.substring(0, prefixLength);
        }
        return null;
    }

    public static String getPath(String str) {
        return doGetPath(str, 1);
    }

    public static String getPathNoEndSeparator(String str) {
        return doGetPath(str, 0);
    }

    private static String doGetPath(String str, int i) {
        int prefixLength;
        if (str != null && (prefixLength = getPrefixLength(str)) >= 0) {
            int indexOfLastSeparator = indexOfLastSeparator(str);
            int i2 = i + indexOfLastSeparator;
            return (prefixLength >= str.length() || indexOfLastSeparator < 0 || prefixLength >= i2) ? "" : str.substring(prefixLength, i2);
        }
        return null;
    }

    public static String getFullPath(String str) {
        return doGetFullPath(str, true);
    }

    public static String getFullPathNoEndSeparator(String str) {
        return doGetFullPath(str, false);
    }

    private static String doGetFullPath(String str, boolean z) {
        int prefixLength;
        if (str != null && (prefixLength = getPrefixLength(str)) >= 0) {
            if (prefixLength >= str.length()) {
                return z ? getPrefix(str) : str;
            }
            int indexOfLastSeparator = indexOfLastSeparator(str);
            if (indexOfLastSeparator < 0) {
                return str.substring(0, prefixLength);
            }
            int i = indexOfLastSeparator + (z ? 1 : 0);
            if (i == 0) {
                i++;
            }
            return str.substring(0, i);
        }
        return null;
    }

    public static String getName(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(indexOfLastSeparator(str) + 1);
    }

    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int indexOfExtension = indexOfExtension(str);
        return indexOfExtension == -1 ? "" : str.substring(indexOfExtension + 1);
    }

    public static boolean isExtension(String str, String str2) {
        if (str == null) {
            return false;
        }
        if (str2 == null || str2.length() == 0) {
            return indexOfExtension(str) == -1;
        }
        return getExtension(str).equals(str2);
    }

    public static boolean isExtension(String str, String[] strArr) {
        if (str == null) {
            return false;
        }
        if (strArr == null || strArr.length == 0) {
            return indexOfExtension(str) == -1;
        }
        String extension = getExtension(str);
        for (String str2 : strArr) {
            if (extension.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExtension(String str, Collection<String> collection) {
        if (str == null) {
            return false;
        }
        if (collection == null || collection.isEmpty()) {
            return indexOfExtension(str) == -1;
        }
        String extension = getExtension(str);
        for (String str2 : collection) {
            if (extension.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    static String[] splitOnTokens(String str) {
        if (str.indexOf(63) == -1 && str.indexOf(42) == -1) {
            return new String[]{str};
        }
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '?' || charArray[i] == '*') {
                if (sb.length() != 0) {
                    arrayList.add(sb.toString());
                    sb.setLength(0);
                }
                if (charArray[i] == '?') {
                    arrayList.add("?");
                } else if (arrayList.isEmpty() || (i > 0 && !((String) arrayList.get(arrayList.size() - 1)).equals("*"))) {
                    arrayList.add("*");
                }
            } else {
                sb.append(charArray[i]);
            }
        }
        if (sb.length() != 0) {
            arrayList.add(sb.toString());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
