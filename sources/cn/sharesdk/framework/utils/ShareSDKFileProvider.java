package cn.sharesdk.framework.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareSDKFileProvider extends ContentProvider {

    /* renamed from: a */
    private static final String[] f2945a = {"_display_name", "_size"};

    /* renamed from: b */
    private static final File f2946b = new File("/");

    /* renamed from: c */
    private static HashMap<String, PathStrategy> f2947c = new HashMap<>();

    /* renamed from: d */
    private PathStrategy f2948d;

    /* renamed from: e */
    private ProviderInfo f2949e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface PathStrategy {
        File getFileForUri(Uri uri);

        Uri getUriForFile(File file);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        this.f2949e = providerInfo;
    }

    /* renamed from: a */
    public static Uri m21729a(Context context, String str, File file) {
        try {
            return m21730a(context, str).getUriForFile(file);
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21743a("getUriForFile fail" + th);
            return null;
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        m21727a("q");
        PathStrategy pathStrategy = this.f2948d;
        if (pathStrategy == null) {
            return null;
        }
        File fileForUri = pathStrategy.getFileForUri(uri);
        if (strArr == null) {
            strArr = f2945a;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i] = "_display_name";
                objArr[i] = fileForUri.getName();
                i++;
            } else if ("_size".equals(str3)) {
                strArr3[i] = "_size";
                objArr[i] = Long.valueOf(fileForUri.length());
                i++;
            }
        }
        String[] m21725a = m21725a(strArr3, i);
        Object[] m21726a = m21726a(objArr, i);
        MatrixCursor matrixCursor = new MatrixCursor(m21725a, 1);
        matrixCursor.addRow(m21726a);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        m21727a("g-t");
        PathStrategy pathStrategy = this.f2948d;
        if (pathStrategy == null) {
            return "";
        }
        File fileForUri = pathStrategy.getFileForUri(uri);
        int lastIndexOf = fileForUri.getName().lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileForUri.getName().substring(lastIndexOf + 1));
            return mimeTypeFromExtension != null ? mimeTypeFromExtension : "application/octet-stream";
        }
        return "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        m21727a("del");
        PathStrategy pathStrategy = this.f2948d;
        if (pathStrategy == null) {
            return 0;
        }
        return pathStrategy.getFileForUri(uri).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        m21727a("o-f");
        PathStrategy pathStrategy = this.f2948d;
        if (pathStrategy == null) {
            return null;
        }
        return ParcelFileDescriptor.open(pathStrategy.getFileForUri(uri), m21722b(str));
    }

    /* renamed from: a */
    private static PathStrategy m21730a(Context context, String str) {
        PathStrategy pathStrategy;
        synchronized (f2947c) {
            pathStrategy = f2947c.get(str);
            if (pathStrategy == null) {
                pathStrategy = m21723b(context, str);
                f2947c.put(str, pathStrategy);
            }
        }
        return pathStrategy;
    }

    /* renamed from: a */
    private void m21727a(String str) {
        ProviderInfo providerInfo;
        if (this.f2948d == null && (providerInfo = this.f2949e) != null) {
            if (providerInfo.exported) {
                throw new SecurityException("Provider must not be exported");
            }
            if (!this.f2949e.grantUriPermissions) {
                throw new SecurityException("Provider must grant uri permissions");
            }
            try {
                this.f2948d = m21730a(getContext(), this.f2949e.authority);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: b */
    private static PathStrategy m21723b(Context context, String str) {
        C1779a c1779a = new C1779a(str);
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            c1779a.m21721a("imageNameFilesDir", m21728a(filesDir, "Mob/cache/images"));
            c1779a.m21721a("videoNameFilesDir", m21728a(filesDir, "Mob/cache/videos"));
        }
        String str2 = "Mob/" + context.getPackageName() + "/cache/images";
        if (context.getCacheDir() != null) {
            c1779a.m21721a("cachename", m21728a(filesDir, "."));
            c1779a.m21721a("imageNameExternal", m21728a(filesDir, str2));
            c1779a.m21721a("imageNameExternal", m21728a(filesDir, "Mob/cache/images"));
        }
        String str3 = "Mob/" + context.getPackageName() + "/cache/images";
        String str4 = "Mob/" + context.getPackageName() + "/cache/videos";
        File[] m21731a = m21731a(context);
        File file = m21731a.length > 0 ? m21731a[0] : null;
        if (file != null) {
            c1779a.m21721a("imageNameExternal", m21728a(file, str3));
            c1779a.m21721a("videoNameExternal", m21728a(file, str4));
            c1779a.m21721a("mihayou", m21728a(file, "."));
            c1779a.m21721a("more", m21728a(file, "./."));
        }
        String str5 = "Mob/" + context.getPackageName() + "/cache/images";
        String str6 = "Mob/" + context.getPackageName() + "/cache/videos";
        File[] m21724b = m21724b(context);
        File file2 = m21724b.length > 0 ? m21724b[0] : null;
        if (file2 != null) {
            c1779a.m21721a("imageNameEtc", m21728a(file2, str5));
            c1779a.m21721a("videoNameEtc", m21728a(file2, str6));
        }
        if (f2946b != null) {
            c1779a.m21721a("imageNameRoot", m21728a((File) null, "Mob/cache/images"));
            c1779a.m21721a("videoNameRoot", m21728a((File) null, "Mob/cache/videos"));
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            c1779a.m21721a("externalStDir", m21728a(externalStorageDirectory, "."));
        }
        return c1779a;
    }

    /* renamed from: a */
    public static File[] m21731a(Context context) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalFilesDirs(null) : new File[]{context.getExternalFilesDir(null)};
    }

    /* renamed from: b */
    public static File[] m21724b(Context context) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalCacheDirs() : new File[]{context.getExternalCacheDir()};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.sharesdk.framework.utils.ShareSDKFileProvider$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C1779a implements PathStrategy {

        /* renamed from: a */
        private final String f2950a;

        /* renamed from: b */
        private final HashMap<String, File> f2951b = new HashMap<>();

        public C1779a(String str) {
            this.f2950a = str;
        }

        /* renamed from: a */
        public void m21721a(String str, File file) {
            File absoluteFile;
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Name must not be empty");
            }
            try {
                absoluteFile = file.getCanonicalFile();
            } catch (Throwable unused) {
                absoluteFile = file.getAbsoluteFile();
            }
            this.f2951b.put(str, absoluteFile);
        }

        @Override // cn.sharesdk.framework.utils.ShareSDKFileProvider.PathStrategy
        public Uri getUriForFile(File file) {
            String substring;
            try {
                String canonicalPath = file.getCanonicalPath();
                Map.Entry<String, File> entry = null;
                for (Map.Entry<String, File> entry2 : this.f2951b.entrySet()) {
                    String path = entry2.getValue().getPath();
                    if (canonicalPath.startsWith(path) && (entry == null || path.length() > entry.getValue().getPath().length())) {
                        entry = entry2;
                    }
                }
                if (entry == null) {
                    throw new IllegalArgumentException("Failed to find configured root that contains " + canonicalPath);
                }
                String path2 = entry.getValue().getPath();
                if (path2.endsWith("/")) {
                    substring = canonicalPath.substring(path2.length());
                } else {
                    substring = canonicalPath.substring(path2.length() + 1);
                }
                return new Uri.Builder().scheme("content").authority(this.f2950a).encodedPath(Uri.encode(entry.getKey()) + '/' + Uri.encode(substring, "/")).build();
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
        }

        @Override // cn.sharesdk.framework.utils.ShareSDKFileProvider.PathStrategy
        public File getFileForUri(Uri uri) {
            File absoluteFile;
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = this.f2951b.get(decode);
            if (file == null) {
                throw new IllegalArgumentException("Unable to find configured root for " + uri);
            }
            File file2 = new File(file, decode2);
            try {
                absoluteFile = file2.getCanonicalFile();
            } catch (Throwable unused) {
                absoluteFile = file2.getAbsoluteFile();
            }
            if (absoluteFile.getPath().startsWith(file.getPath())) {
                return absoluteFile;
            }
            throw new SecurityException("Resolved path jumped beyond configured root");
        }
    }

    /* renamed from: b */
    private static int m21722b(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + str);
    }

    /* renamed from: a */
    private static File m21728a(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    /* renamed from: a */
    private static String[] m21725a(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    /* renamed from: a */
    private static Object[] m21726a(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }
}
