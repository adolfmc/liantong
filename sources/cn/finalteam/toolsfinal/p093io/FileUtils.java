package cn.finalteam.toolsfinal.p093io;

import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: cn.finalteam.toolsfinal.io.FileUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FileUtils {
    public static final File[] EMPTY_FILE_ARRAY;
    private static final long FILE_COPY_BUFFER_SIZE = 31457280;

    /* renamed from: GB */
    public static final double f2718GB = 1.073741824E9d;

    /* renamed from: KB */
    public static final double f2719KB = 1024.0d;

    /* renamed from: MB */
    public static final double f2720MB = 1048576.0d;
    public static final long ONE_EB = 1152921504606846976L;
    public static final BigInteger ONE_EB_BI;
    public static final long ONE_GB = 1073741824;
    public static final BigInteger ONE_GB_BI;
    public static final long ONE_KB = 1024;
    public static final BigInteger ONE_KB_BI = BigInteger.valueOf(1024);
    public static final long ONE_MB = 1048576;
    public static final BigInteger ONE_MB_BI;
    public static final long ONE_PB = 1125899906842624L;
    public static final BigInteger ONE_PB_BI;
    public static final long ONE_TB = 1099511627776L;
    public static final BigInteger ONE_TB_BI;
    public static final BigInteger ONE_YB;
    public static final BigInteger ONE_ZB;
    private static final Charset UTF8;

    static {
        BigInteger bigInteger = ONE_KB_BI;
        ONE_MB_BI = bigInteger.multiply(bigInteger);
        ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
        ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
        ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
        ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);
        ONE_ZB = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(1152921504606846976L));
        ONE_YB = ONE_KB_BI.multiply(ONE_ZB);
        EMPTY_FILE_ARRAY = new File[0];
        UTF8 = Charset.forName("UTF-8");
    }

    public static File getFile(File file, String... strArr) {
        if (file != null) {
            if (strArr == null) {
                throw new NullPointerException("names must not be null");
            }
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                i++;
                file = new File(file, strArr[i]);
            }
            return file;
        }
        throw new NullPointerException("directorydirectory must not be null");
    }

    public static File getFile(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("names must not be null");
        }
        File file = null;
        for (String str : strArr) {
            if (file == null) {
                file = new File(str);
            } else {
                file = new File(file, str);
            }
        }
        return file;
    }

    public static String getTempDirectoryPath() {
        return System.getProperty("java.io.tmpdir");
    }

    public static File getTempDirectory() {
        return new File(getTempDirectoryPath());
    }

    public static String getUserDirectoryPath() {
        return System.getProperty("user.home");
    }

    public static File getUserDirectory() {
        return new File(getUserDirectoryPath());
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            } else if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            } else {
                return new FileInputStream(file);
            }
        }
        throw new FileNotFoundException("File '" + file + "' does not exist");
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        return openOutputStream(file, false);
    }

    public static FileOutputStream openOutputStream(File file, boolean z) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            } else if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Directory '" + parentFile + "' could not be created");
            }
        }
        return new FileOutputStream(file, z);
    }

    public static String byteCountToDisplaySize(BigInteger bigInteger) {
        if (bigInteger.divide(ONE_EB_BI).compareTo(BigInteger.ZERO) > 0) {
            return String.valueOf(bigInteger.divide(ONE_EB_BI)) + " EB";
        } else if (bigInteger.divide(ONE_PB_BI).compareTo(BigInteger.ZERO) > 0) {
            return String.valueOf(bigInteger.divide(ONE_PB_BI)) + " PB";
        } else if (bigInteger.divide(ONE_TB_BI).compareTo(BigInteger.ZERO) > 0) {
            return String.valueOf(bigInteger.divide(ONE_TB_BI)) + " TB";
        } else if (bigInteger.divide(ONE_GB_BI).compareTo(BigInteger.ZERO) > 0) {
            return String.valueOf(bigInteger.divide(ONE_GB_BI)) + " GB";
        } else if (bigInteger.divide(ONE_MB_BI).compareTo(BigInteger.ZERO) > 0) {
            return String.valueOf(bigInteger.divide(ONE_MB_BI)) + " MB";
        } else if (bigInteger.divide(ONE_KB_BI).compareTo(BigInteger.ZERO) > 0) {
            return String.valueOf(bigInteger.divide(ONE_KB_BI)) + " KB";
        } else {
            return String.valueOf(bigInteger) + " bytes";
        }
    }

    public static String byteCountToDisplaySize(long j) {
        return byteCountToDisplaySize(BigInteger.valueOf(j));
    }

    public static void touch(File file) throws IOException {
        if (!file.exists()) {
            IOUtils.closeQuietly((OutputStream) openOutputStream(file));
        }
        if (file.setLastModified(System.currentTimeMillis())) {
            return;
        }
        throw new IOException("Unable to set the last modification time for " + file);
    }

    public static File[] convertFileCollectionToFileArray(Collection<File> collection) {
        return (File[]) collection.toArray(new File[collection.size()]);
    }

    private static String[] toSuffixes(String[] strArr) {
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = "." + strArr[i];
        }
        return strArr2;
    }

    public static boolean contentEquals(File file, File file2) throws IOException {
        FileInputStream fileInputStream;
        boolean exists = file.exists();
        if (exists != file2.exists()) {
            return false;
        }
        if (!exists) {
            return true;
        }
        if (file.isDirectory() || file2.isDirectory()) {
            throw new IOException("Can't compare directories, only files");
        }
        if (file.length() != file2.length()) {
            return false;
        }
        if (file.getCanonicalFile().equals(file2.getCanonicalFile())) {
            return true;
        }
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                FileInputStream fileInputStream3 = new FileInputStream(file2);
                try {
                    boolean contentEquals = IOUtils.contentEquals(fileInputStream, fileInputStream3);
                    IOUtils.closeQuietly((InputStream) fileInputStream);
                    IOUtils.closeQuietly((InputStream) fileInputStream3);
                    return contentEquals;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream3;
                    IOUtils.closeQuietly((InputStream) fileInputStream);
                    IOUtils.closeQuietly((InputStream) fileInputStream2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    public static boolean contentEqualsIgnoreEOL(File file, File file2, String str) throws IOException {
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        boolean exists = file.exists();
        if (exists != file2.exists()) {
            return false;
        }
        if (!exists) {
            return true;
        }
        if (file.isDirectory() || file2.isDirectory()) {
            throw new IOException("Can't compare directories, only files");
        }
        if (file.getCanonicalFile().equals(file2.getCanonicalFile())) {
            return true;
        }
        InputStreamReader inputStreamReader3 = null;
        try {
            if (str == null) {
                InputStreamReader inputStreamReader4 = new InputStreamReader(new FileInputStream(file));
                try {
                    inputStreamReader2 = new InputStreamReader(new FileInputStream(file2));
                    inputStreamReader3 = inputStreamReader4;
                } catch (Throwable th) {
                    th = th;
                    inputStreamReader = null;
                    inputStreamReader3 = inputStreamReader4;
                    IOUtils.closeQuietly((Reader) inputStreamReader3);
                    IOUtils.closeQuietly((Reader) inputStreamReader);
                    throw th;
                }
            } else {
                InputStreamReader inputStreamReader5 = new InputStreamReader(new FileInputStream(file), str);
                try {
                    inputStreamReader2 = new InputStreamReader(new FileInputStream(file2), str);
                    inputStreamReader3 = inputStreamReader5;
                } catch (Throwable th2) {
                    th = th2;
                    inputStreamReader = null;
                    inputStreamReader3 = inputStreamReader5;
                    IOUtils.closeQuietly((Reader) inputStreamReader3);
                    IOUtils.closeQuietly((Reader) inputStreamReader);
                    throw th;
                }
            }
            try {
                boolean contentEqualsIgnoreEOL = IOUtils.contentEqualsIgnoreEOL(inputStreamReader3, inputStreamReader2);
                IOUtils.closeQuietly((Reader) inputStreamReader3);
                IOUtils.closeQuietly((Reader) inputStreamReader2);
                return contentEqualsIgnoreEOL;
            } catch (Throwable th3) {
                inputStreamReader = inputStreamReader2;
                th = th3;
                IOUtils.closeQuietly((Reader) inputStreamReader3);
                IOUtils.closeQuietly((Reader) inputStreamReader);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = inputStreamReader3;
        }
    }

    public static File toFile(URL url) {
        if (url == null || !"file".equalsIgnoreCase(url.getProtocol())) {
            return null;
        }
        return new File(decodeUrl(url.getFile().replace('/', File.separatorChar)));
    }

    static String decodeUrl(String str) {
        int i;
        if (str == null || str.indexOf(37) < 0) {
            return str;
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        ByteBuffer allocate = ByteBuffer.allocate(length);
        int i2 = 0;
        while (i2 < length) {
            if (str.charAt(i2) == '%') {
                while (true) {
                    i = i2 + 3;
                    try {
                        try {
                            allocate.put((byte) Integer.parseInt(str.substring(i2 + 1, i), 16));
                            if (i >= length) {
                                break;
                            }
                            try {
                                if (str.charAt(i) != '%') {
                                    break;
                                }
                                i2 = i;
                            } catch (RuntimeException unused) {
                                i2 = i;
                                if (allocate.position() > 0) {
                                    allocate.flip();
                                    stringBuffer.append(UTF8.decode(allocate).toString());
                                    allocate.clear();
                                }
                                stringBuffer.append(str.charAt(i2));
                                i2++;
                            }
                        } finally {
                            if (allocate.position() > 0) {
                                allocate.flip();
                                stringBuffer.append(UTF8.decode(allocate).toString());
                                allocate.clear();
                            }
                        }
                    } catch (RuntimeException unused2) {
                    }
                }
                i2 = i;
            }
            stringBuffer.append(str.charAt(i2));
            i2++;
        }
        return stringBuffer.toString();
    }

    public static File[] toFiles(URL[] urlArr) {
        if (urlArr == null || urlArr.length == 0) {
            return EMPTY_FILE_ARRAY;
        }
        File[] fileArr = new File[urlArr.length];
        for (int i = 0; i < urlArr.length; i++) {
            URL url = urlArr[i];
            if (url != null) {
                if (!url.getProtocol().equals("file")) {
                    throw new IllegalArgumentException("URL could not be converted to a File: " + url);
                }
                fileArr[i] = toFile(url);
            }
        }
        return fileArr;
    }

    public static URL[] toURLs(File[] fileArr) throws IOException {
        URL[] urlArr = new URL[fileArr.length];
        for (int i = 0; i < urlArr.length; i++) {
            urlArr[i] = fileArr[i].toURI().toURL();
        }
        return urlArr;
    }

    public static void copyFileToDirectory(File file, File file2) throws IOException {
        copyFileToDirectory(file, file2, true);
    }

    public static void copyFileToDirectory(File file, File file2, boolean z) throws IOException {
        if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (file2.exists() && !file2.isDirectory()) {
            throw new IllegalArgumentException("Destination '" + file2 + "' is not a directory");
        }
        copyFile(file, new File(file2, file.getName()), z);
    }

    public static void copyFile(File file, File file2) throws IOException {
        copyFile(file, file2, true);
    }

    public static void copyFile(File file, File file2, boolean z) throws IOException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!file.exists()) {
            throw new FileNotFoundException("Source '" + file + "' does not exist");
        } else if (file.isDirectory()) {
            throw new IOException("Source '" + file + "' exists but is a directory");
        } else if (file.getCanonicalPath().equals(file2.getCanonicalPath())) {
            throw new IOException("Source '" + file + "' and destination '" + file2 + "' are the same");
        } else {
            File parentFile = file2.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Destination '" + parentFile + "' directory cannot be created");
            } else if (file2.exists() && !file2.canWrite()) {
                throw new IOException("Destination '" + file2 + "' exists but is read-only");
            } else {
                doCopyFile(file, file2, z);
            }
        }
    }

    public static long copyFile(File file, OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return IOUtils.copyLarge(fileInputStream, outputStream);
        } finally {
            fileInputStream.close();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.io.OutputStream, java.io.FileOutputStream] */
    private static void doCopyFile(File file, File file2, boolean z) throws IOException {
        FileInputStream fileInputStream;
        ?? r4;
        FileChannel fileChannel;
        if (file2.exists() && file2.isDirectory()) {
            throw new IOException("Destination '" + file2 + "' exists but is a directory");
        }
        FileChannel fileChannel2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
            r4 = 0;
        }
        try {
            r4 = new FileOutputStream(file2);
            try {
                fileChannel = fileInputStream.getChannel();
            } catch (Throwable th2) {
                th = th2;
                fileChannel = null;
            }
        } catch (Throwable th3) {
            th = th3;
            r4 = 0;
            fileChannel = r4;
            IOUtils.closeQuietly(fileChannel2);
            IOUtils.closeQuietly((OutputStream) r4);
            IOUtils.closeQuietly(fileChannel);
            IOUtils.closeQuietly((InputStream) fileInputStream);
            throw th;
        }
        try {
            fileChannel2 = r4.getChannel();
            long size = fileChannel.size();
            long j = 0;
            while (j < size) {
                long j2 = size - j;
                j += fileChannel2.transferFrom(fileChannel, j, j2 > 31457280 ? 31457280L : j2);
            }
            IOUtils.closeQuietly(fileChannel2);
            IOUtils.closeQuietly((OutputStream) r4);
            IOUtils.closeQuietly(fileChannel);
            IOUtils.closeQuietly((InputStream) fileInputStream);
            if (file.length() == file2.length()) {
                if (z) {
                    file2.setLastModified(file.lastModified());
                    return;
                }
                return;
            }
            throw new IOException("Failed to copy full contents from '" + file + "' to '" + file2 + "'");
        } catch (Throwable th4) {
            th = th4;
            IOUtils.closeQuietly(fileChannel2);
            IOUtils.closeQuietly((OutputStream) r4);
            IOUtils.closeQuietly(fileChannel);
            IOUtils.closeQuietly((InputStream) fileInputStream);
            throw th;
        }
    }

    public static void copyDirectoryToDirectory(File file, File file2) throws IOException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file.exists() && !file.isDirectory()) {
            throw new IllegalArgumentException("Source '" + file2 + "' is not a directory");
        } else if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        } else {
            if (file2.exists() && !file2.isDirectory()) {
                throw new IllegalArgumentException("Destination '" + file2 + "' is not a directory");
            }
            copyDirectory(file, new File(file2, file.getName()), true);
        }
    }

    public static void copyDirectory(File file, File file2) throws IOException {
        copyDirectory(file, file2, true);
    }

    public static void copyDirectory(File file, File file2, boolean z) throws IOException {
        copyDirectory(file, file2, null, z);
    }

    public static void copyDirectory(File file, File file2, FileFilter fileFilter) throws IOException {
        copyDirectory(file, file2, fileFilter, true);
    }

    public static void copyDirectory(File file, File file2, FileFilter fileFilter, boolean z) throws IOException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!file.exists()) {
            throw new FileNotFoundException("Source '" + file + "' does not exist");
        } else if (!file.isDirectory()) {
            throw new IOException("Source '" + file + "' exists but is not a directory");
        } else if (file.getCanonicalPath().equals(file2.getCanonicalPath())) {
            throw new IOException("Source '" + file + "' and destination '" + file2 + "' are the same");
        } else {
            ArrayList arrayList = null;
            if (file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
                File[] listFiles = fileFilter == null ? file.listFiles() : file.listFiles(fileFilter);
                if (listFiles != null && listFiles.length > 0) {
                    arrayList = new ArrayList(listFiles.length);
                    for (File file3 : listFiles) {
                        arrayList.add(new File(file2, file3.getName()).getCanonicalPath());
                    }
                }
            }
            doCopyDirectory(file, file2, fileFilter, z, arrayList);
        }
    }

    private static void doCopyDirectory(File file, File file2, FileFilter fileFilter, boolean z, List<String> list) throws IOException {
        File[] listFiles = fileFilter == null ? file.listFiles() : file.listFiles(fileFilter);
        if (listFiles == null) {
            throw new IOException("Failed to list contents of " + file);
        }
        if (file2.exists()) {
            if (!file2.isDirectory()) {
                throw new IOException("Destination '" + file2 + "' exists but is not a directory");
            }
        } else if (!file2.mkdirs() && !file2.isDirectory()) {
            throw new IOException("Destination '" + file2 + "' directory cannot be created");
        }
        if (!file2.canWrite()) {
            throw new IOException("Destination '" + file2 + "' cannot be written to");
        }
        for (File file3 : listFiles) {
            File file4 = new File(file2, file3.getName());
            if (list == null || !list.contains(file3.getCanonicalPath())) {
                if (file3.isDirectory()) {
                    doCopyDirectory(file3, file4, fileFilter, z, list);
                } else {
                    doCopyFile(file3, file4, z);
                }
            }
        }
        if (z) {
            file2.setLastModified(file.lastModified());
        }
    }

    public static void copyURLToFile(URL url, File file) throws IOException {
        copyInputStreamToFile(url.openStream(), file);
    }

    public static void copyURLToFile(URL url, File file, int i, int i2) throws IOException {
        URLConnection openConnection = NBSInstrumentation.openConnection(url.openConnection());
        openConnection.setConnectTimeout(i);
        openConnection.setReadTimeout(i2);
        copyInputStreamToFile(openConnection.getInputStream(), file);
    }

    public static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        try {
            FileOutputStream openOutputStream = openOutputStream(file);
            IOUtils.copy(inputStream, openOutputStream);
            openOutputStream.close();
            IOUtils.closeQuietly((OutputStream) openOutputStream);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public static void deleteDirectory(File file) throws IOException {
        if (file.exists()) {
            if (!isSymlink(file)) {
                cleanDirectory(file);
            }
            if (file.delete()) {
                return;
            }
            throw new IOException("Unable to delete directory " + file + ".");
        }
    }

    public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        } catch (Exception unused) {
        }
        try {
            return file.delete();
        } catch (Exception unused2) {
            return false;
        }
    }

    public static void cleanDirectory(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        } else if (!file.isDirectory()) {
            throw new IllegalArgumentException(file + " is not a directory");
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("Failed to list contents of " + file);
            }
            IOException e = null;
            for (File file2 : listFiles) {
                try {
                    forceDelete(file2);
                } catch (IOException e2) {
                    e = e2;
                }
            }
            if (e != null) {
                throw e;
            }
        }
    }

    public static boolean waitFor(File file, int i) {
        int i2 = 0;
        int i3 = 0;
        while (!file.exists()) {
            int i4 = i2 + 1;
            if (i2 >= 10) {
                int i5 = i3 + 1;
                if (i3 > i) {
                    return false;
                }
                i3 = i5;
                i2 = 0;
            } else {
                i2 = i4;
            }
            try {
                Thread.sleep(100L);
            } catch (InterruptedException unused) {
            } catch (Exception unused2) {
                return true;
            }
        }
        return true;
    }

    public static String readFileToString(File file, Charset charset) throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = openInputStream(file);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            String iOUtils = IOUtils.toString(fileInputStream, Charsets.toCharset(charset));
            IOUtils.closeQuietly((InputStream) fileInputStream);
            return iOUtils;
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly((InputStream) fileInputStream);
            throw th;
        }
    }

    public static String readFileToString(File file, String str) throws IOException {
        return readFileToString(file, Charsets.toCharset(str));
    }

    public static String readFileToString(File file) throws IOException {
        return readFileToString(file, Charset.defaultCharset());
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = openInputStream(file);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            byte[] byteArray = IOUtils.toByteArray(fileInputStream, file.length());
            IOUtils.closeQuietly((InputStream) fileInputStream);
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly((InputStream) fileInputStream);
            throw th;
        }
    }

    public static List<String> readLines(File file, Charset charset) throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = openInputStream(file);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            List<String> readLines = IOUtils.readLines(fileInputStream, Charsets.toCharset(charset));
            IOUtils.closeQuietly((InputStream) fileInputStream);
            return readLines;
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly((InputStream) fileInputStream);
            throw th;
        }
    }

    public static List<String> readLines(File file, String str) throws IOException {
        return readLines(file, Charsets.toCharset(str));
    }

    public static List<String> readLines(File file) throws IOException {
        return readLines(file, Charset.defaultCharset());
    }

    public static void writeStringToFile(File file, String str, Charset charset) throws IOException {
        writeStringToFile(file, str, charset, false);
    }

    public static void writeStringToFile(File file, String str, String str2) throws IOException {
        writeStringToFile(file, str, str2, false);
    }

    public static void writeStringToFile(File file, String str, Charset charset, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openOutputStream(file, z);
            try {
                IOUtils.write(str, (OutputStream) fileOutputStream, charset);
                fileOutputStream.close();
                IOUtils.closeQuietly((OutputStream) fileOutputStream);
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly((OutputStream) fileOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
    }

    public static void writeStringToFile(File file, String str, String str2, boolean z) throws IOException {
        writeStringToFile(file, str, Charsets.toCharset(str2), z);
    }

    public static void writeStringToFile(File file, String str) throws IOException {
        writeStringToFile(file, str, Charset.defaultCharset(), false);
    }

    public static void writeStringToFile(File file, String str, boolean z) throws IOException {
        writeStringToFile(file, str, Charset.defaultCharset(), z);
    }

    public static void write(File file, CharSequence charSequence) throws IOException {
        write(file, charSequence, Charset.defaultCharset(), false);
    }

    public static void write(File file, CharSequence charSequence, boolean z) throws IOException {
        write(file, charSequence, Charset.defaultCharset(), z);
    }

    public static void write(File file, CharSequence charSequence, Charset charset) throws IOException {
        write(file, charSequence, charset, false);
    }

    public static void write(File file, CharSequence charSequence, String str) throws IOException {
        write(file, charSequence, str, false);
    }

    public static void write(File file, CharSequence charSequence, Charset charset, boolean z) throws IOException {
        writeStringToFile(file, charSequence == null ? null : charSequence.toString(), charset, z);
    }

    public static void write(File file, CharSequence charSequence, String str, boolean z) throws IOException {
        write(file, charSequence, Charsets.toCharset(str), z);
    }

    public static void writeByteArrayToFile(File file, byte[] bArr) throws IOException {
        writeByteArrayToFile(file, bArr, false);
    }

    public static void writeByteArrayToFile(File file, byte[] bArr, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openOutputStream(file, z);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.close();
                IOUtils.closeQuietly((OutputStream) fileOutputStream);
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly((OutputStream) fileOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
    }

    public static void writeLines(File file, String str, Collection<?> collection) throws IOException {
        writeLines(file, str, collection, null, false);
    }

    public static void writeLines(File file, String str, Collection<?> collection, boolean z) throws IOException {
        writeLines(file, str, collection, null, z);
    }

    public static void writeLines(File file, Collection<?> collection) throws IOException {
        writeLines(file, null, collection, null, false);
    }

    public static void writeLines(File file, Collection<?> collection, boolean z) throws IOException {
        writeLines(file, null, collection, null, z);
    }

    public static void writeLines(File file, String str, Collection<?> collection, String str2) throws IOException {
        writeLines(file, str, collection, str2, false);
    }

    public static void writeLines(File file, String str, Collection<?> collection, String str2, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openOutputStream(file, z);
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            IOUtils.writeLines(collection, str2, bufferedOutputStream, str);
            bufferedOutputStream.flush();
            fileOutputStream.close();
            IOUtils.closeQuietly((OutputStream) fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly((OutputStream) fileOutputStream);
            throw th;
        }
    }

    public static void writeLines(File file, Collection<?> collection, String str) throws IOException {
        writeLines(file, null, collection, str, false);
    }

    public static void writeLines(File file, Collection<?> collection, String str, boolean z) throws IOException {
        writeLines(file, null, collection, str, z);
    }

    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
            return;
        }
        boolean exists = file.exists();
        if (file.delete()) {
            return;
        }
        if (!exists) {
            throw new FileNotFoundException("File does not exist: " + file);
        }
        throw new IOException("Unable to delete file: " + file);
    }

    public static void forceDeleteOnExit(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectoryOnExit(file);
        } else {
            file.deleteOnExit();
        }
    }

    private static void deleteDirectoryOnExit(File file) throws IOException {
        if (file.exists()) {
            file.deleteOnExit();
            if (isSymlink(file)) {
                return;
            }
            cleanDirectoryOnExit(file);
        }
    }

    private static void cleanDirectoryOnExit(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        } else if (!file.isDirectory()) {
            throw new IllegalArgumentException(file + " is not a directory");
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("Failed to list contents of " + file);
            }
            IOException e = null;
            for (File file2 : listFiles) {
                try {
                    forceDeleteOnExit(file2);
                } catch (IOException e2) {
                    e = e2;
                }
            }
            if (e != null) {
                throw e;
            }
        }
    }

    public static void forceMkdir(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                return;
            }
            throw new IOException("File " + file + " exists and is not a directory. Unable to create directory.");
        } else if (file.mkdirs() || file.isDirectory()) {
        } else {
            throw new IOException("Unable to create directory " + file);
        }
    }

    public static boolean mkdirs(File file) {
        try {
            forceMkdir(file);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static long sizeOf(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        } else if (file.isDirectory()) {
            return sizeOfDirectory(file);
        } else {
            return file.length();
        }
    }

    public static BigInteger sizeOfAsBigInteger(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        } else if (file.isDirectory()) {
            return sizeOfDirectoryAsBigInteger(file);
        } else {
            return BigInteger.valueOf(file.length());
        }
    }

    public static long sizeOfDirectory(File file) {
        checkDirectory(file);
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0L;
        }
        long j = 0;
        for (File file2 : listFiles) {
            try {
                if (!isSymlink(file2)) {
                    j += sizeOf(file2);
                    if (j < 0) {
                        break;
                    }
                } else {
                    continue;
                }
            } catch (IOException unused) {
            }
        }
        return j;
    }

    public static BigInteger sizeOfDirectoryAsBigInteger(File file) {
        checkDirectory(file);
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return BigInteger.ZERO;
        }
        BigInteger bigInteger = BigInteger.ZERO;
        for (File file2 : listFiles) {
            try {
                if (!isSymlink(file2)) {
                    bigInteger = bigInteger.add(BigInteger.valueOf(sizeOf(file2)));
                }
            } catch (IOException unused) {
            }
        }
        return bigInteger;
    }

    private static void checkDirectory(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        } else if (file.isDirectory()) {
        } else {
            throw new IllegalArgumentException(file + " is not a directory");
        }
    }

    public static boolean isFileNewer(File file, File file2) {
        if (file2 == null) {
            throw new IllegalArgumentException("No specified reference file");
        }
        if (!file2.exists()) {
            throw new IllegalArgumentException("The reference file '" + file2 + "' doesn't exist");
        }
        return isFileNewer(file, file2.lastModified());
    }

    public static boolean isFileNewer(File file, Date date) {
        if (date == null) {
            throw new IllegalArgumentException("No specified date");
        }
        return isFileNewer(file, date.getTime());
    }

    public static boolean isFileNewer(File file, long j) {
        if (file != null) {
            return file.exists() && file.lastModified() > j;
        }
        throw new IllegalArgumentException("No specified file");
    }

    public static boolean isFileOlder(File file, File file2) {
        if (file2 == null) {
            throw new IllegalArgumentException("No specified reference file");
        }
        if (!file2.exists()) {
            throw new IllegalArgumentException("The reference file '" + file2 + "' doesn't exist");
        }
        return isFileOlder(file, file2.lastModified());
    }

    public static boolean isFileOlder(File file, Date date) {
        if (date == null) {
            throw new IllegalArgumentException("No specified date");
        }
        return isFileOlder(file, date.getTime());
    }

    public static boolean isFileOlder(File file, long j) {
        if (file != null) {
            return file.exists() && file.lastModified() < j;
        }
        throw new IllegalArgumentException("No specified file");
    }

    public static void moveDirectory(File file, File file2) throws IOException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!file.exists()) {
            throw new FileNotFoundException("Source '" + file + "' does not exist");
        } else if (!file.isDirectory()) {
            throw new IOException("Source '" + file + "' is not a directory");
        } else if (file2.exists()) {
            throw new FileExistsException("Destination '" + file2 + "' already exists");
        } else if (file.renameTo(file2)) {
        } else {
            if (file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
                throw new IOException("Cannot move directory: " + file + " to a subdirectory of itself: " + file2);
            }
            copyDirectory(file, file2);
            deleteDirectory(file);
            if (file.exists()) {
                throw new IOException("Failed to delete original directory '" + file + "' after copy to '" + file2 + "'");
            }
        }
    }

    public static void moveDirectoryToDirectory(File file, File file2, boolean z) throws IOException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file2 == null) {
            throw new NullPointerException("Destination directory must not be null");
        }
        if (!file2.exists() && z) {
            file2.mkdirs();
        }
        if (!file2.exists()) {
            throw new FileNotFoundException("Destination directory '" + file2 + "' does not exist [createDestDir=" + z + "]");
        } else if (!file2.isDirectory()) {
            throw new IOException("Destination '" + file2 + "' is not a directory");
        } else {
            moveDirectory(file, new File(file2, file.getName()));
        }
    }

    public static void moveFile(File file, File file2) throws IOException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!file.exists()) {
            throw new FileNotFoundException("Source '" + file + "' does not exist");
        } else if (file.isDirectory()) {
            throw new IOException("Source '" + file + "' is a directory");
        } else if (file2.exists()) {
            throw new FileExistsException("Destination '" + file2 + "' already exists");
        } else if (file2.isDirectory()) {
            throw new IOException("Destination '" + file2 + "' is a directory");
        } else if (file.renameTo(file2)) {
        } else {
            copyFile(file, file2);
            if (file.delete()) {
                return;
            }
            deleteQuietly(file2);
            throw new IOException("Failed to delete original file '" + file + "' after copy to '" + file2 + "'");
        }
    }

    public static void moveFileToDirectory(File file, File file2, boolean z) throws IOException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file2 == null) {
            throw new NullPointerException("Destination directory must not be null");
        }
        if (!file2.exists() && z) {
            file2.mkdirs();
        }
        if (!file2.exists()) {
            throw new FileNotFoundException("Destination directory '" + file2 + "' does not exist [createDestDir=" + z + "]");
        } else if (!file2.isDirectory()) {
            throw new IOException("Destination '" + file2 + "' is not a directory");
        } else {
            moveFile(file, new File(file2, file.getName()));
        }
    }

    public static void moveToDirectory(File file, File file2, boolean z) throws IOException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!file.exists()) {
            throw new FileNotFoundException("Source '" + file + "' does not exist");
        } else if (file.isDirectory()) {
            moveDirectoryToDirectory(file, file2, z);
        } else {
            moveFileToDirectory(file, file2, z);
        }
    }

    public static boolean isSymlink(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }
        if (FilenameUtils.isSystemWindows()) {
            return false;
        }
        if (file.getParent() != null) {
            file = new File(file.getParentFile().getCanonicalFile(), file.getName());
        }
        return !file.getCanonicalFile().equals(file.getAbsoluteFile());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.finalteam.toolsfinal.io.FileUtils$FileExistsException */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class FileExistsException extends IOException {
        private static final long serialVersionUID = 1;

        public FileExistsException() {
        }

        public FileExistsException(String str) {
            super(str);
        }

        public FileExistsException(File file) {
            super("File " + file + " exists");
        }
    }
}
