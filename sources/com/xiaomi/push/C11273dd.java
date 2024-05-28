package com.xiaomi.push;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.util.Log;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* renamed from: com.xiaomi.push.dd */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11273dd implements LoggerInterface {

    /* renamed from: a */
    private static volatile C11273dd f21848a;

    /* renamed from: a */
    private Context f21852a;

    /* renamed from: a */
    private Handler f21853a;

    /* renamed from: b */
    private String f21854b;

    /* renamed from: a */
    private static final SimpleDateFormat f21850a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aaa");

    /* renamed from: a */
    public static String f21849a = "/MiPushLog";

    /* renamed from: a */
    private static List<Pair<String, Throwable>> f21851a = Collections.synchronizedList(new ArrayList());

    private C11273dd(Context context) {
        this.f21852a = context;
        if (context.getApplicationContext() != null) {
            this.f21852a = context.getApplicationContext();
        }
        this.f21854b = this.f21852a.getPackageName() + "-" + Process.myPid();
        HandlerThread handlerThread = new HandlerThread("Log2FileHandlerThread");
        handlerThread.start();
        this.f21853a = new Handler(handlerThread.getLooper());
    }

    /* renamed from: a */
    public static C11273dd m4369a(Context context) {
        if (f21848a == null) {
            synchronized (C11273dd.class) {
                if (f21848a == null) {
                    f21848a = new C11273dd(context);
                }
            }
        }
        return f21848a;
    }

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public final void setTag(String str) {
        this.f21854b = str;
    }

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public final void log(String str) {
        log(str, null);
    }

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public final void log(final String str, final Throwable th) {
        this.f21853a.post(new Runnable() { // from class: com.xiaomi.push.dd.1
            @Override // java.lang.Runnable
            public void run() {
                C11273dd.f21851a.add(new Pair(String.format("%1$s %2$s %3$s ", C11273dd.f21850a.format(new Date()), C11273dd.this.f21854b, str), th));
                if (C11273dd.f21851a.size() > 20000) {
                    int size = (C11273dd.f21851a.size() - 20000) + 50;
                    for (int i = 0; i < size; i++) {
                        try {
                            if (C11273dd.f21851a.size() > 0) {
                                C11273dd.f21851a.remove(0);
                            }
                        } catch (IndexOutOfBoundsException unused) {
                        }
                    }
                    C11273dd.f21851a.add(new Pair(String.format("%1$s %2$s %3$s ", C11273dd.f21850a.format(new Date()), C11273dd.this.f21854b, "flush " + size + " lines logs."), null));
                }
                try {
                    C11273dd.this.m4370a();
                } catch (Exception e) {
                    Log.e(C11273dd.this.f21854b, "", e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:83:0x0173 -> B:106:0x017a). Please submit an issue!!! */
    /* renamed from: a */
    public void m4370a() {
        FileLock fileLock;
        RandomAccessFile randomAccessFile;
        File file;
        BufferedWriter bufferedWriter = null;
        try {
            try {
                file = new File(this.f21852a.getFilesDir(), f21849a);
            } catch (Exception e) {
                e = e;
                fileLock = null;
                randomAccessFile = null;
            } catch (Throwable th) {
                th = th;
                fileLock = null;
                randomAccessFile = null;
            }
        } catch (IOException e2) {
            Log.e(this.f21854b, "", e2);
        }
        if (!C11646v.m2275a(file)) {
            Log.w(this.f21854b, "Cannot wirte internal file: " + file);
        } else if ((!file.exists() || !file.isDirectory()) && !file.mkdirs()) {
            Log.w(this.f21854b, "Create mipushlog directory fail.");
        } else {
            File file2 = new File(file, "log.lock");
            if (!file2.exists() || file2.isDirectory()) {
                file2.createNewFile();
            }
            randomAccessFile = new RandomAccessFile(file2, "rw");
            try {
                fileLock = randomAccessFile.getChannel().lock();
                try {
                    try {
                        BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file, "log1.txt"), true)));
                        while (!f21851a.isEmpty()) {
                            try {
                                Pair<String, Throwable> remove = f21851a.remove(0);
                                String str = (String) remove.first;
                                if (remove.second != null) {
                                    str = (str + "\n") + Log.getStackTraceString((Throwable) remove.second);
                                }
                                bufferedWriter2.write(str + "\n");
                            } catch (Exception e3) {
                                e = e3;
                                bufferedWriter = bufferedWriter2;
                                Log.e(this.f21854b, "", e);
                                if (bufferedWriter != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (IOException e4) {
                                        Log.e(this.f21854b, "", e4);
                                    }
                                }
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException e5) {
                                        Log.e(this.f21854b, "", e5);
                                    }
                                }
                                if (randomAccessFile != null) {
                                    randomAccessFile.close();
                                }
                                return;
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedWriter = bufferedWriter2;
                                if (bufferedWriter != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (IOException e6) {
                                        Log.e(this.f21854b, "", e6);
                                    }
                                }
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException e7) {
                                        Log.e(this.f21854b, "", e7);
                                    }
                                }
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (IOException e8) {
                                        Log.e(this.f21854b, "", e8);
                                    }
                                }
                                throw th;
                            }
                        }
                        bufferedWriter2.flush();
                        bufferedWriter2.close();
                        File file3 = new File(file, "log1.txt");
                        if (file3.length() >= 1048576) {
                            File file4 = new File(file, "log0.txt");
                            if (file4.exists() && file4.isFile()) {
                                file4.delete();
                            }
                            file3.renameTo(file4);
                        }
                        if (0 != 0) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e9) {
                                Log.e(this.f21854b, "", e9);
                            }
                        }
                        if (fileLock != null && fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (IOException e10) {
                                Log.e(this.f21854b, "", e10);
                            }
                        }
                        randomAccessFile.close();
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Exception e11) {
                    e = e11;
                }
            } catch (Exception e12) {
                e = e12;
                fileLock = null;
            } catch (Throwable th4) {
                th = th4;
                fileLock = null;
            }
        }
    }
}
