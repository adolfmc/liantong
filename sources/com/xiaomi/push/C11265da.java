package com.xiaomi.push;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.C11571ax;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.xiaomi.push.da */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class C11265da {

    /* renamed from: a */
    private static String f21815a = "/MiPushLog";

    /* renamed from: a */
    private int f21816a;

    /* renamed from: a */
    private boolean f21819a;

    /* renamed from: b */
    private String f21821b;

    /* renamed from: c */
    private String f21822c;
    @SuppressLint({"SimpleDateFormat"})

    /* renamed from: a */
    private final SimpleDateFormat f21817a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /* renamed from: b */
    private int f21820b = 2097152;

    /* renamed from: a */
    private ArrayList<File> f21818a = new ArrayList<>();

    /* renamed from: a */
    C11265da m4387a(Date date, Date date2) {
        if (date.after(date2)) {
            this.f21821b = this.f21817a.format(date2);
            this.f21822c = this.f21817a.format(date);
        } else {
            this.f21821b = this.f21817a.format(date);
            this.f21822c = this.f21817a.format(date2);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m4392a(int i) {
        if (i != 0) {
            this.f21820b = i;
        }
    }

    /* renamed from: a */
    C11265da m4389a(File file) {
        if (file.exists()) {
            this.f21818a.add(file);
        }
        return this;
    }

    /* renamed from: a */
    private void m4390a(BufferedReader bufferedReader, BufferedWriter bufferedWriter, Pattern pattern) {
        char[] cArr = new char[4096];
        int read = bufferedReader.read(cArr);
        boolean z = false;
        while (read != -1 && !z) {
            String str = new String(cArr, 0, read);
            Matcher matcher = pattern.matcher(str);
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= read || !matcher.find(i)) {
                    break;
                }
                int start = matcher.start();
                String substring = str.substring(start, this.f21821b.length() + start);
                if (!this.f21819a) {
                    if (substring.compareTo(this.f21821b) >= 0) {
                        this.f21819a = true;
                        i2 = start;
                    }
                } else if (substring.compareTo(this.f21822c) > 0) {
                    z = true;
                    read = start;
                    break;
                }
                int indexOf = str.indexOf(10, start);
                i = indexOf != -1 ? start + indexOf : start + this.f21821b.length();
            }
            if (this.f21819a) {
                int i3 = read - i2;
                this.f21816a += i3;
                if (z) {
                    bufferedWriter.write(cArr, i2, i3);
                    return;
                }
                bufferedWriter.write(cArr, i2, i3);
                if (this.f21816a > this.f21820b) {
                    return;
                }
            }
            read = bufferedReader.read(cArr);
        }
    }

    /* renamed from: a */
    private void m4388a(File file) {
        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader;
        Pattern compile = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            } catch (FileNotFoundException e) {
                e = e;
                bufferedReader = null;
            } catch (IOException e2) {
                e = e2;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                bufferedWriter = null;
            }
            try {
                bufferedWriter.write("model :" + C11470k.m2955a() + "; os :" + Build.VERSION.INCREMENTAL + "; uid :" + C11571ax.m2624a() + "; lng :" + Locale.getDefault().toString() + "; sdk :48; andver :" + Build.VERSION.SDK_INT + "\n");
                this.f21816a = 0;
                Iterator<File> it = this.f21818a.iterator();
                while (it.hasNext()) {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(it.next())));
                    try {
                        m4390a(bufferedReader, bufferedWriter, compile);
                        bufferedReader.close();
                        bufferedReader2 = bufferedReader;
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        bufferedReader2 = bufferedWriter;
                        AbstractC11049b.m5270c("LOG: filter error = " + e.getMessage());
                        C11647w.m2274a(bufferedReader2);
                        C11647w.m2274a(bufferedReader);
                        return;
                    } catch (IOException e4) {
                        e = e4;
                        bufferedReader2 = bufferedWriter;
                        AbstractC11049b.m5270c("LOG: filter error = " + e.getMessage());
                        C11647w.m2274a(bufferedReader2);
                        C11647w.m2274a(bufferedReader);
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = bufferedReader;
                        C11647w.m2274a(bufferedWriter);
                        C11647w.m2274a(bufferedReader2);
                        throw th;
                    }
                }
                bufferedWriter.write(C11232cg.m4574a().m4555c());
                C11647w.m2274a(bufferedWriter);
                C11647w.m2274a(bufferedReader2);
            } catch (FileNotFoundException e5) {
                e = e5;
                bufferedReader = bufferedReader2;
            } catch (IOException e6) {
                e = e6;
                bufferedReader = bufferedReader2;
            } catch (Throwable th3) {
                th = th3;
                C11647w.m2274a(bufferedWriter);
                C11647w.m2274a(bufferedReader2);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedWriter = bufferedReader2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public File m4391a(Context context, Date date, Date date2, File file) {
        File file2;
        if ("com.xiaomi.xmsf".equalsIgnoreCase(context.getPackageName())) {
            file2 = C11263cz.m4401a(context);
            if (file2 == null) {
                return null;
            }
            m4389a(new File(file2, "xmsf.log.1"));
            m4389a(new File(file2, "xmsf.log"));
        } else {
            File file3 = new File(context.getFilesDir() + f21815a);
            if (!C11646v.m2275a(file3)) {
                return null;
            }
            m4389a(new File(file3, "log0.txt"));
            m4389a(new File(file3, "log1.txt"));
            file2 = file3;
        }
        if (file2.isDirectory()) {
            File file4 = new File(file, date.getTime() + "-" + date2.getTime() + JtClient.UXUE_TEMP_FILE_SUFFIX);
            if (file4.exists()) {
                return null;
            }
            m4387a(date, date2);
            long currentTimeMillis = System.currentTimeMillis();
            File file5 = new File(file, "log.txt");
            m4388a(file5);
            AbstractC11049b.m5270c("LOG: filter cost = " + (System.currentTimeMillis() - currentTimeMillis));
            if (file5.exists()) {
                long currentTimeMillis2 = System.currentTimeMillis();
                C11647w.m2271a(file4, file5);
                AbstractC11049b.m5270c("LOG: zip cost = " + (System.currentTimeMillis() - currentTimeMillis2));
                file5.delete();
                if (file4.exists()) {
                    return file4;
                }
            }
            return null;
        }
        return null;
    }
}
