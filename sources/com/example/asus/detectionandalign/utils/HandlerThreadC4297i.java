package com.example.asus.detectionandalign.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.utils.i */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HandlerThreadC4297i extends HandlerThread {

    /* renamed from: a */
    private static Handler f10094a;

    /* renamed from: b */
    private static HandlerThreadC4297i f10095b;

    /* renamed from: c */
    private static SimpleDateFormat f10096c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

    private HandlerThreadC4297i(String str) {
        super(str);
    }

    /* renamed from: a */
    public static void m15943a() {
        new File("/sdcard/", "szLog.txt").delete();
        if (f10095b == null) {
            f10095b = new HandlerThreadC4297i("LOG_THREAD");
            f10095b.start();
            f10094a = new Handler(f10095b.getLooper()) { // from class: com.example.asus.detectionandalign.utils.i.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    if (message != null) {
                        Bundle data = message.getData();
                        HandlerThreadC4297i.m15940b(data.getString("TAG", "tag is null"), data.getString("MESSAGE", "message is null"));
                    }
                }
            };
        }
    }

    /* renamed from: b */
    public static void m15941b() {
        if (f10095b != null) {
            f10094a.removeCallbacksAndMessages(null);
            f10095b.quit();
            f10095b = null;
            f10094a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m15940b(String str, String str2) {
        FileOutputStream fileOutputStream;
        File file;
        if (TextUtils.isEmpty(str)) {
            str = "--Tag null--";
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    file = new File("/sdcard/", "szLog.txt");
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(file, true);
                    try {
                    } catch (IOException e) {
                        e = e;
                        fileOutputStream2 = fileOutputStream;
                        e.printStackTrace();
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        return;
                    } catch (Throwable th) {
                        th = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                }
            } catch (IOException e3) {
                e = e3;
            }
            if (fileOutputStream.getChannel().size() > 20971520) {
                fileOutputStream.close();
                file.delete();
                try {
                    fileOutputStream.close();
                    return;
                } catch (IOException e4) {
                    e4.printStackTrace();
                    return;
                }
            }
            fileOutputStream.write((f10096c.format(new Date()) + "/" + str + "=====>>[" + str2 + "]\n").getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }
}
