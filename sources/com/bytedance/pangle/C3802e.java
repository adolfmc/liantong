package com.bytedance.pangle;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.RequiresApi;
import android.view.ContextThemeWrapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* renamed from: com.bytedance.pangle.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3802e extends ContextThemeWrapper {
    private File mCacheDir;
    private File mCodeCacheDir;
    private File mDataDir;
    private File mExternalCacheDir;
    private File mFilesDir;
    private File mNoBackupFilesDir;
    private File mObbDir;
    private final Object mSync;

    public C3802e() {
        this.mSync = new Object();
    }

    public C3802e(Context context, int i) {
        super(context, i);
        this.mSync = new Object();
    }

    @RequiresApi(api = 23)
    public C3802e(Context context, Resources.Theme theme) {
        super(context, theme);
        this.mSync = new Object();
    }

    private static File ensurePrivateDirExists(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private static File makeFilename(File file, String str) {
        if (str.indexOf(File.separatorChar) < 0) {
            return new File(file, str);
        }
        throw new IllegalArgumentException("File " + str + " contains a path separator");
    }

    private String makeSubName(String str) {
        return getPluginDirName() + "_" + str;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String[] databaseList() {
        if (getPluginDirName() == null) {
            return super.databaseList();
        }
        String[] databaseList = super.databaseList();
        boolean[] zArr = new boolean[databaseList.length];
        int i = 0;
        for (int i2 = 0; i2 < databaseList.length; i2++) {
            if (databaseList[i2].startsWith(getPluginDirName())) {
                zArr[i2] = true;
                i++;
            } else {
                zArr[i2] = false;
            }
        }
        String[] strArr = new String[i];
        int i3 = 0;
        for (int i4 = 0; i4 < zArr.length; i4++) {
            if (zArr[i4]) {
                strArr[i3] = databaseList[i4];
                i3++;
            }
        }
        return strArr;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean deleteDatabase(String str) {
        return getPluginDirName() == null ? super.deleteDatabase(str) : super.deleteDatabase(makeSubName(str));
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean deleteFile(String str) {
        return makeFilename(getFilesDir(), str).delete();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean deleteSharedPreferences(String str) {
        return getPluginDirName() == null ? super.deleteSharedPreferences(str) : super.deleteSharedPreferences(makeSubName(str));
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getCacheDir() {
        File ensurePrivateDirExists;
        if (getPluginDirName() == null) {
            return super.getCacheDir();
        }
        synchronized (this.mSync) {
            if (this.mCacheDir == null) {
                this.mCacheDir = new File(super.getCacheDir(), getPluginDirName());
            }
            ensurePrivateDirExists = ensurePrivateDirExists(this.mCacheDir);
        }
        return ensurePrivateDirExists;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getCodeCacheDir() {
        File ensurePrivateDirExists;
        if (getPluginDirName() == null) {
            return super.getCodeCacheDir();
        }
        synchronized (this.mSync) {
            if (this.mCodeCacheDir == null) {
                this.mCodeCacheDir = new File(super.getCodeCacheDir(), getPluginDirName());
            }
            ensurePrivateDirExists = ensurePrivateDirExists(this.mCodeCacheDir);
        }
        return ensurePrivateDirExists;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getDataDir() {
        File ensurePrivateDirExists;
        if (getPluginDirName() == null) {
            return super.getDataDir();
        }
        synchronized (this.mSync) {
            if (this.mDataDir == null) {
                this.mDataDir = new File(getHostDataDir(), getPluginDirName());
            }
            ensurePrivateDirExists = ensurePrivateDirExists(this.mDataDir);
        }
        return ensurePrivateDirExists;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getDatabasePath(String str) {
        return getPluginDirName() == null ? super.getDatabasePath(str) : super.getDatabasePath(makeSubName(str));
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getDir(String str, int i) {
        return (i != 0 || getPluginDirName() == null) ? super.getDir(str, i) : ensurePrivateDirExists(new File(super.getDir(str, i), getPluginDirName()));
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getExternalCacheDir() {
        File ensurePrivateDirExists;
        if (getPluginDirName() == null) {
            return super.getExternalCacheDir();
        }
        synchronized (this.mSync) {
            if (this.mExternalCacheDir == null) {
                this.mExternalCacheDir = new File(super.getExternalCacheDir(), getPluginDirName());
            }
            ensurePrivateDirExists = ensurePrivateDirExists(this.mExternalCacheDir);
        }
        return ensurePrivateDirExists;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File[] getExternalCacheDirs() {
        if (getPluginDirName() == null) {
            return super.getExternalCacheDirs();
        }
        File[] externalCacheDirs = super.getExternalCacheDirs();
        File[] fileArr = new File[externalCacheDirs.length];
        for (int i = 0; i < externalCacheDirs.length; i++) {
            fileArr[i] = ensurePrivateDirExists(new File(externalCacheDirs[i], getPluginDirName()));
        }
        return fileArr;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getExternalFilesDir(String str) {
        return getPluginDirName() == null ? super.getExternalFilesDir(str) : ensurePrivateDirExists(new File(super.getExternalFilesDir(str), getPluginDirName()));
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File[] getExternalFilesDirs(String str) {
        if (getPluginDirName() == null) {
            return super.getExternalFilesDirs(str);
        }
        File[] externalFilesDirs = super.getExternalFilesDirs(str);
        File[] fileArr = new File[externalFilesDirs.length];
        for (int i = 0; i < externalFilesDirs.length; i++) {
            fileArr[i] = ensurePrivateDirExists(new File(externalFilesDirs[i], getPluginDirName()));
        }
        return fileArr;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File[] getExternalMediaDirs() {
        if (getPluginDirName() == null) {
            return super.getExternalMediaDirs();
        }
        File[] externalMediaDirs = super.getExternalMediaDirs();
        File[] fileArr = new File[externalMediaDirs.length];
        for (int i = 0; i < externalMediaDirs.length; i++) {
            fileArr[i] = ensurePrivateDirExists(new File(externalMediaDirs[i], getPluginDirName()));
        }
        return fileArr;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getFilesDir() {
        File ensurePrivateDirExists;
        if (getPluginDirName() == null) {
            return super.getFilesDir();
        }
        synchronized (this.mSync) {
            if (this.mFilesDir == null) {
                this.mFilesDir = new File(super.getFilesDir(), getPluginDirName());
            }
            ensurePrivateDirExists = ensurePrivateDirExists(this.mFilesDir);
        }
        return ensurePrivateDirExists;
    }

    public File getHostDataDir() {
        return super.getFilesDir().getParentFile();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getNoBackupFilesDir() {
        File ensurePrivateDirExists;
        if (getPluginDirName() == null) {
            return super.getNoBackupFilesDir();
        }
        synchronized (this.mSync) {
            if (this.mNoBackupFilesDir == null) {
                this.mNoBackupFilesDir = new File(super.getNoBackupFilesDir(), getPluginDirName());
            }
            ensurePrivateDirExists = ensurePrivateDirExists(this.mNoBackupFilesDir);
        }
        return ensurePrivateDirExists;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getObbDir() {
        File ensurePrivateDirExists;
        if (getPluginDirName() == null) {
            return super.getObbDir();
        }
        synchronized (this.mSync) {
            if (this.mObbDir == null) {
                this.mObbDir = new File(super.getObbDir(), getPluginDirName());
            }
            ensurePrivateDirExists = ensurePrivateDirExists(this.mObbDir);
        }
        return ensurePrivateDirExists;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File[] getObbDirs() {
        if (getPluginDirName() == null) {
            return super.getObbDirs();
        }
        File[] obbDirs = super.getObbDirs();
        File[] fileArr = new File[obbDirs.length];
        for (int i = 0; i < obbDirs.length; i++) {
            fileArr[i] = ensurePrivateDirExists(new File(obbDirs[i], getPluginDirName()));
        }
        return fileArr;
    }

    protected String getPluginDirName() {
        return "pangle_" + getPluginPkg();
    }

    protected String getPluginPkg() {
        return null;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SharedPreferences getSharedPreferences(String str, int i) {
        return (i != 0 || getPluginDirName() == null) ? super.getSharedPreferences(str, i) : super.getSharedPreferences(makeSubName(str), i);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean moveDatabaseFrom(Context context, String str) {
        if (getPluginDirName() == null) {
            return super.moveDatabaseFrom(context, str);
        }
        throw new UnsupportedOperationException("Calling moveDatabaseFrom in plugins is not supported.");
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public FileInputStream openFileInput(String str) {
        return getPluginDirName() == null ? super.openFileInput(str) : new FileInputStream(makeFilename(getFilesDir(), str));
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public FileOutputStream openFileOutput(String str, int i) {
        return (i != 0 || getPluginDirName() == null) ? super.openFileOutput(str, i) : new FileOutputStream(makeFilename(getFilesDir(), str), false);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory) {
        return (i != 0 || getPluginDirName() == null) ? super.openOrCreateDatabase(str, i, cursorFactory) : super.openOrCreateDatabase(makeSubName(str), i, cursorFactory);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return (i != 0 || getPluginDirName() == null) ? super.openOrCreateDatabase(str, i, cursorFactory, databaseErrorHandler) : super.openOrCreateDatabase(makeSubName(str), i, cursorFactory, databaseErrorHandler);
    }
}
