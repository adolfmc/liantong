package com.baidu.p120ar.statistic;

import android.content.Context;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Scanner;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.EventRequestCache */
/* loaded from: E:\10201592_dexfile_execute.dex */
class EventRequestCache extends ArrayList<EventData> {
    private static final String CHARSET_NAME = "utf-8";
    private String mCacheFileName;
    private WeakReference<Context> mContextRef;
    private boolean mIsLoaded = false;
    private int mMaxCount;
    private int mMaxPersistCount;

    public EventRequestCache(Context context, String str, int i, int i2) {
        this.mContextRef = new WeakReference<>(context);
        this.mMaxCount = i;
        this.mCacheFileName = str;
        this.mMaxPersistCount = i2;
    }

    public void put(EventData eventData) {
        if (size() < this.mMaxCount) {
            add(eventData);
        }
    }

    public void checkLoaded() {
        if (this.mIsLoaded) {
            return;
        }
        load();
        this.mIsLoaded = true;
    }

    private void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.baidu.ar.statistic.EventRequestCache] */
    private void load() {
        Context context;
        Closeable closeable;
        Throwable th;
        Scanner scanner;
        Exception e;
        EventData deserialize;
        if (this.mMaxPersistCount <= 0 || (context = this.mContextRef.get()) == 0 || !context.getFileStreamPath(this.mCacheFileName).exists()) {
            return;
        }
        try {
            try {
                context = context.openFileInput(this.mCacheFileName);
            } catch (Exception e2) {
                scanner = null;
                e = e2;
                context = 0;
            } catch (Throwable th2) {
                closeable = null;
                th = th2;
                context = 0;
            }
            try {
                scanner = new Scanner((InputStream) context, "utf-8");
                while (scanner.hasNextLine()) {
                    try {
                        String nextLine = scanner.nextLine();
                        if (nextLine != null && !nextLine.isEmpty() && (deserialize = EventData.deserialize(nextLine)) != null) {
                            add(deserialize);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        closeStream(context);
                        closeStream(scanner);
                    }
                }
            } catch (Exception e4) {
                scanner = null;
                e = e4;
            } catch (Throwable th3) {
                closeable = null;
                th = th3;
                closeStream(context);
                closeStream(closeable);
                throw th;
            }
            closeStream(context);
            closeStream(scanner);
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public void removeFromHead(int i) {
        if (i > 0) {
            EventData[] eventDataArr = new EventData[i];
            for (int i2 = 0; i2 < i; i2++) {
                eventDataArr[i2] = get(i2);
            }
            removeRange(0, i);
            EventData.recycle(eventDataArr);
        }
    }

    public void flush() {
        Context context;
        if (this.mMaxPersistCount > 0 && (context = this.mContextRef.get()) != null) {
            int size = size();
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    fileOutputStream = context.openFileOutput(this.mCacheFileName, 0);
                    if (size > 0) {
                        if (size > this.mMaxPersistCount) {
                            size = this.mMaxPersistCount;
                        }
                        for (int i = 0; i < size; i++) {
                            fileOutputStream.write((EventData.serialize((EventData) get(i)) + "\n").getBytes("utf-8"));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                closeStream(fileOutputStream);
            }
        }
    }
}
