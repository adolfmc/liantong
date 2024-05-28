package com.p319ss.android.socialbase.downloader.p342db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;

@NBSInstrumented
/* renamed from: com.ss.android.socialbase.downloader.db.DownloadDBHelper */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadDBHelper extends SQLiteOpenHelper {
    private static volatile DownloadDBHelper instance;
    private boolean tempDirSetted;

    public static DownloadDBHelper getInstance() {
        if (instance == null) {
            synchronized (DownloadDBHelper.class) {
                if (instance == null) {
                    instance = new DownloadDBHelper();
                }
            }
        }
        return instance;
    }

    private DownloadDBHelper() {
        super(DownloadComponentManager.getAppContext(), "downloader.db", (SQLiteDatabase.CursorFactory) null, 15);
        this.tempDirSetted = false;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public SQLiteDatabase getReadableDatabase() {
        Context appContext = DownloadComponentManager.getAppContext();
        if (!this.tempDirSetted && appContext != null) {
            try {
                File file = new File("/data/data/" + appContext.getPackageName() + "/database/main/");
                if (!file.exists()) {
                    file.mkdir();
                }
                SQLiteDatabase readableDatabase = super.getReadableDatabase();
                if (readableDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(readableDatabase, "PRAGMA temp_store_directory = tempDir");
                } else {
                    readableDatabase.execSQL("PRAGMA temp_store_directory = tempDir");
                }
                this.tempDirSetted = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.getReadableDatabase();
        }
        return super.getReadableDatabase();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS downloader( _id INTEGER PRIMARY KEY, url TEXT, savePath TEXT, tempPath TEXT, name TEXT, chunkCount INTEGER, status INTEGER, curBytes INTEGER, totalBytes INTEGER, eTag TEXT, onlyWifi INTEGER, force INTEGER, retryCount INTEGER, extra TEXT, mimeType TEXT, title TEXT, notificationEnable INTEGER, notificationVisibility INTEGER, isFirstDownload INTEGER, isFirstSuccess INTEGER, needHttpsToHttpRetry INTEGER, downloadTime INTEGER, packageName TEXT, md5 TEXT, retryDelay INTEGER, curRetryTime INTEGER, retryDelayStatus INTEGER, defaultHttpServiceBackUp INTEGER, chunkRunnableReuse INTEGER, retryDelayTimeArray TEXT, chunkDowngradeRetry INTEGER, backUpUrlsStr TEXT, backUpUrlRetryCount INTEGER, realDownloadTime INTEGER, retryScheduleMinutes INTEGER, independentProcess INTEGER, auxiliaryJsonobjectString TEXT, iconUrl TEXT, appVersionCode INTEGER, taskId TEXT)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS downloader( _id INTEGER PRIMARY KEY, url TEXT, savePath TEXT, tempPath TEXT, name TEXT, chunkCount INTEGER, status INTEGER, curBytes INTEGER, totalBytes INTEGER, eTag TEXT, onlyWifi INTEGER, force INTEGER, retryCount INTEGER, extra TEXT, mimeType TEXT, title TEXT, notificationEnable INTEGER, notificationVisibility INTEGER, isFirstDownload INTEGER, isFirstSuccess INTEGER, needHttpsToHttpRetry INTEGER, downloadTime INTEGER, packageName TEXT, md5 TEXT, retryDelay INTEGER, curRetryTime INTEGER, retryDelayStatus INTEGER, defaultHttpServiceBackUp INTEGER, chunkRunnableReuse INTEGER, retryDelayTimeArray TEXT, chunkDowngradeRetry INTEGER, backUpUrlsStr TEXT, backUpUrlRetryCount INTEGER, realDownloadTime INTEGER, retryScheduleMinutes INTEGER, independentProcess INTEGER, auxiliaryJsonobjectString TEXT, iconUrl TEXT, appVersionCode INTEGER, taskId TEXT)");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS downloadChunk( _id INTEGER, chunkIndex INTEGER, startOffset INTEGER, curOffset INTEGER, endOffset INTEGER, chunkContentLen INTEGER, hostChunkIndex INTEGER )");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS downloadChunk( _id INTEGER, chunkIndex INTEGER, startOffset INTEGER, curOffset INTEGER, endOffset INTEGER, chunkContentLen INTEGER, hostChunkIndex INTEGER )");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS segments( _id INTEGER PRIMARY KEY,info TEXT )");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS segments( _id INTEGER PRIMARY KEY,info TEXT )");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        super.onConfigure(sQLiteDatabase);
        if (DownloadSetting.obtainGlobal().optInt("disable_sqlite_wal") <= 0 || Build.VERSION.SDK_INT != 28) {
            return;
        }
        sQLiteDatabase.disableWriteAheadLogging();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        switch (i) {
            case 1:
                boolean z = sQLiteDatabase instanceof SQLiteDatabase;
                if (z) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD mimeType TEXT");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD mimeType TEXT");
                }
                if (z) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD title TEXT");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD title TEXT");
                }
                if (z) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD notificationEnable INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD notificationEnable INTEGER");
                }
                if (z) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD notificationVisibility INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD notificationVisibility INTEGER");
                }
            case 2:
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD isFirstDownload INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD isFirstDownload INTEGER");
                }
            case 3:
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD isFirstSuccess INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD isFirstSuccess INTEGER");
                }
            case 4:
                boolean z2 = sQLiteDatabase instanceof SQLiteDatabase;
                if (z2) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD needHttpsToHttpRetry INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD needHttpsToHttpRetry INTEGER");
                }
                if (z2) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD downloadTime INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD downloadTime INTEGER");
                }
            case 5:
                boolean z3 = sQLiteDatabase instanceof SQLiteDatabase;
                if (z3) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD packageName TEXT");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD packageName TEXT");
                }
                if (z3) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD md5 TEXT");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD md5 TEXT");
                }
            case 6:
                boolean z4 = sQLiteDatabase instanceof SQLiteDatabase;
                if (z4) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD retryDelay INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD retryDelay INTEGER");
                }
                if (z4) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD curRetryTime INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD curRetryTime INTEGER");
                }
                if (z4) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD retryDelayStatus INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD retryDelayStatus INTEGER");
                }
                if (z4) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD defaultHttpServiceBackUp INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD defaultHttpServiceBackUp INTEGER");
                }
            case 7:
                boolean z5 = sQLiteDatabase instanceof SQLiteDatabase;
                if (z5) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloadChunk ADD chunkContentLen INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloadChunk ADD chunkContentLen INTEGER");
                }
                if (z5) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloadChunk ADD hostChunkIndex INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloadChunk ADD hostChunkIndex INTEGER");
                }
            case 8:
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD chunkRunnableReuse INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD chunkRunnableReuse INTEGER");
                }
            case 9:
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD retryDelayTimeArray TEXT");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD retryDelayTimeArray TEXT");
                }
            case 10:
                boolean z6 = sQLiteDatabase instanceof SQLiteDatabase;
                if (z6) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD chunkDowngradeRetry INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD chunkDowngradeRetry INTEGER");
                }
                if (z6) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD backUpUrlsStr TEXT");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD backUpUrlsStr TEXT");
                }
                if (z6) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD backUpUrlRetryCount INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD backUpUrlRetryCount INTEGER");
                }
                if (z6) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD realDownloadTime INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD realDownloadTime INTEGER");
                }
                if (z6) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD retryScheduleMinutes INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD retryScheduleMinutes INTEGER");
                }
                if (z6) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD independentProcess INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD independentProcess INTEGER");
                }
            case 11:
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD auxiliaryJsonobjectString TEXT");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD auxiliaryJsonobjectString TEXT");
                }
            case 12:
                boolean z7 = sQLiteDatabase instanceof SQLiteDatabase;
                if (z7) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD iconUrl TEXT");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD iconUrl TEXT");
                }
                if (z7) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD appVersionCode INTEGER");
                } else {
                    sQLiteDatabase.execSQL("ALTER TABLE downloader ADD appVersionCode INTEGER");
                }
            case 13:
                if (!(sQLiteDatabase instanceof SQLiteDatabase)) {
                    sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS segments( _id INTEGER PRIMARY KEY,info TEXT )");
                    break;
                } else {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS segments( _id INTEGER PRIMARY KEY,info TEXT )");
                    break;
                }
            case 14:
                break;
            default:
                return;
        }
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE downloader ADD taskId TEXT");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE downloader ADD taskId TEXT");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (Logger.debug()) {
            Logger.m6476d("onDowngrade");
        }
    }
}
