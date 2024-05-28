package com.chinaunicon.jtwifilib.jtcommon.p188db;

import android.database.sqlite.SQLiteDatabase;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.chinaunicon.jtwifilib.jtcommon.db.DatabaseManager */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DatabaseManager {
    private static DatabaseManager instance;
    private static DBManager mDatabaseHelper;
    private SQLiteDatabase mDatabase;
    private AtomicInteger mOpenCounter = new AtomicInteger();

    public static synchronized void initializeInstance(DBManager dBManager) {
        synchronized (DatabaseManager.class) {
            if (instance == null) {
                instance = new DatabaseManager();
                mDatabaseHelper = dBManager;
            }
        }
    }

    public static synchronized DatabaseManager getInstance() {
        DatabaseManager databaseManager;
        synchronized (DatabaseManager.class) {
            if (instance == null) {
                throw new IllegalStateException(DatabaseManager.class.getSimpleName() + " is not initialized, call initializeInstance(..) method first.");
            }
            databaseManager = instance;
        }
        return databaseManager;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (this.mOpenCounter.incrementAndGet() == 1) {
            this.mDatabase = mDatabaseHelper.getSQLiteDatabase();
        }
        return this.mDatabase;
    }

    public synchronized void closeDatabase() {
        if (this.mOpenCounter.decrementAndGet() == 0) {
            this.mDatabase.close();
        }
    }
}
