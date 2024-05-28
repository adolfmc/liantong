package com.bytedance.pangle;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FileProvider extends ContentProvider {

    /* renamed from: a */
    private static final String[] f9002a = {"_display_name", "_size"};

    /* renamed from: b */
    private static final File f9003b = new File("/");

    /* renamed from: c */
    private static InterfaceC3762a f9004c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.FileProvider$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC3762a {
        /* renamed from: a */
        Uri mo16983a(File file);

        /* renamed from: a */
        File mo16984a(Uri uri);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(@NonNull Context context, @NonNull ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        }
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
        if (f9004c != null) {
            throw new SecurityException("仅允许定义一个FileProvider");
        }
        f9004c = new C3763b(providerInfo.authority);
    }

    @Keep
    public static Uri getUriForFile(@NonNull File file) {
        return f9004c.mo16983a(file);
    }

    /* renamed from: a */
    public static void m16986a(Plugin plugin, XmlResourceParser xmlResourceParser) {
        try {
            try {
                String str = plugin.mPkgName;
                Context wrapperContext = ZeusTransformUtils.wrapperContext(Zeus.getAppApplication(), plugin.mPkgName);
                C3763b c3763b = (C3763b) f9004c;
                while (true) {
                    int next = xmlResourceParser.next();
                    if (next == 1) {
                        return;
                    }
                    if (next == 2) {
                        String name = xmlResourceParser.getName();
                        File file = null;
                        String attributeValue = xmlResourceParser.getAttributeValue(null, "name");
                        String attributeValue2 = xmlResourceParser.getAttributeValue(null, "path");
                        if ("root-path".equals(name)) {
                            file = f9003b;
                        } else if ("files-path".equals(name)) {
                            file = wrapperContext.getFilesDir();
                        } else if ("cache-path".equals(name)) {
                            file = wrapperContext.getCacheDir();
                        } else if ("external-path".equals(name)) {
                            file = Environment.getExternalStorageDirectory();
                        } else if (!"external-files-path".equals(name)) {
                            if (!"external-cache-path".equals(name)) {
                                if (Build.VERSION.SDK_INT >= 21 && "external-media-path".equals(name)) {
                                    File[] externalMediaDirs = wrapperContext.getExternalMediaDirs();
                                    if (externalMediaDirs.length > 0) {
                                        file = externalMediaDirs[0];
                                    }
                                }
                            } else {
                                File[] externalCacheDirs = Build.VERSION.SDK_INT >= 19 ? wrapperContext.getExternalCacheDirs() : new File[]{wrapperContext.getExternalCacheDir()};
                                if (externalCacheDirs.length > 0) {
                                    file = externalCacheDirs[0];
                                }
                            }
                        } else {
                            File[] externalFilesDirs = Build.VERSION.SDK_INT >= 19 ? wrapperContext.getExternalFilesDirs(null) : new File[]{wrapperContext.getExternalFilesDir(null)};
                            if (externalFilesDirs.length > 0) {
                                file = externalFilesDirs[0];
                            }
                        }
                        if (file != null) {
                            String str2 = str + "_" + attributeValue;
                            File m16985a = m16985a(file, attributeValue2);
                            if (TextUtils.isEmpty(str2)) {
                                throw new IllegalArgumentException("Name must not be empty");
                            }
                            try {
                                c3763b.f9005a.put(str2, m16985a.getCanonicalFile());
                            } catch (IOException e) {
                                throw new IllegalArgumentException("Failed to resolve canonical path for ".concat(String.valueOf(m16985a)), e);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (XmlPullParserException e3) {
            e = e3;
            e.printStackTrace();
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        File mo16984a = f9004c.mo16984a(uri);
        if (strArr == null) {
            strArr = f9002a;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i] = "_display_name";
                objArr[i] = mo16984a.getName();
                i++;
            } else if ("_size".equals(str3)) {
                strArr3[i] = "_size";
                objArr[i] = Long.valueOf(mo16984a.length());
                i++;
            }
        }
        String[] strArr4 = new String[i];
        System.arraycopy(strArr3, 0, strArr4, 0, i);
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        MatrixCursor matrixCursor = new MatrixCursor(strArr4, 1);
        matrixCursor.addRow(objArr2);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public String getType(@NonNull Uri uri) {
        File mo16984a = f9004c.mo16984a(uri);
        int lastIndexOf = mo16984a.getName().lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(mo16984a.getName().substring(lastIndexOf + 1));
            return mimeTypeFromExtension != null ? mimeTypeFromExtension : "application/octet-stream";
        }
        return "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return f9004c.mo16984a(uri).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String str) {
        int i;
        File mo16984a = f9004c.mo16984a(uri);
        if ("r".equals(str)) {
            i = 268435456;
        } else if ("w".equals(str) || "wt".equals(str)) {
            i = 738197504;
        } else if ("wa".equals(str)) {
            i = 704643072;
        } else if ("rw".equals(str)) {
            i = 939524096;
        } else if (!"rwt".equals(str)) {
            throw new IllegalArgumentException("Invalid mode: ".concat(String.valueOf(str)));
        } else {
            i = 1006632960;
        }
        return ParcelFileDescriptor.open(mo16984a, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.pangle.FileProvider$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3763b implements InterfaceC3762a {

        /* renamed from: a */
        final HashMap<String, File> f9005a = new HashMap<>();

        /* renamed from: b */
        private final String f9006b;

        C3763b(String str) {
            this.f9006b = str;
        }

        @Override // com.bytedance.pangle.FileProvider.InterfaceC3762a
        /* renamed from: a */
        public final Uri mo16983a(File file) {
            String substring;
            try {
                String canonicalPath = file.getCanonicalPath();
                Map.Entry<String, File> entry = null;
                for (Map.Entry<String, File> entry2 : this.f9005a.entrySet()) {
                    String path = entry2.getValue().getPath();
                    if (canonicalPath.startsWith(path) && (entry == null || path.length() > entry.getValue().getPath().length())) {
                        entry = entry2;
                    }
                }
                if (entry == null) {
                    throw new IllegalArgumentException("Failed to find configured root that contains ".concat(String.valueOf(canonicalPath)));
                }
                String path2 = entry.getValue().getPath();
                if (path2.endsWith("/")) {
                    substring = canonicalPath.substring(path2.length());
                } else {
                    substring = canonicalPath.substring(path2.length() + 1);
                }
                return new Uri.Builder().scheme("content").authority(this.f9006b).encodedPath(Uri.encode(entry.getKey()) + '/' + Uri.encode(substring, "/")).build();
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for ".concat(String.valueOf(file)));
            }
        }

        @Override // com.bytedance.pangle.FileProvider.InterfaceC3762a
        /* renamed from: a */
        public final File mo16984a(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = this.f9005a.get(decode);
            if (file == null) {
                throw new IllegalArgumentException("Unable to find configured root for ".concat(String.valueOf(uri)));
            }
            File file2 = new File(file, decode2);
            try {
                File canonicalFile = file2.getCanonicalFile();
                if (canonicalFile.getPath().startsWith(file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("Resolved path jumped beyond configured root");
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for ".concat(String.valueOf(file2)));
            }
        }
    }

    /* renamed from: a */
    private static File m16985a(File file, String... strArr) {
        File file2 = file;
        for (int i = 0; i <= 0; i++) {
            String str = strArr[0];
            if (str != null) {
                file2 = new File(file2, str);
            }
        }
        return file2;
    }
}
