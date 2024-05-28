package com.networkbench.nbslens.nbsnativecrashlib;

import java.io.File;
import java.io.FilenameFilter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.nbslens.nbsnativecrashlib.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
class C6781e {

    /* renamed from: m */
    private static final C6781e f17568m = new C6781e();

    /* renamed from: a */
    private String f17569a = "placeholder";

    /* renamed from: b */
    private String f17570b = ".clean.nbscrash";

    /* renamed from: c */
    private String f17571c = ".dirty.nbscrash";

    /* renamed from: d */
    private String f17572d = null;

    /* renamed from: e */
    private int f17573e = 0;

    /* renamed from: f */
    private int f17574f = 0;

    /* renamed from: g */
    private int f17575g = 0;

    /* renamed from: h */
    private int f17576h = 1;

    /* renamed from: i */
    private int f17577i = 0;

    /* renamed from: j */
    private int f17578j = 0;

    /* renamed from: k */
    private int f17579k = 0;

    /* renamed from: l */
    private AtomicInteger f17580l = new AtomicInteger();

    private C6781e() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C6781e m8462a() {
        return f17568m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m8457a(String str, int i, int i2, int i3, int i4, int i5, int i6) {
        File[] listFiles;
        this.f17572d = str;
        this.f17573e = i;
        this.f17574f = i2;
        this.f17575g = i3;
        this.f17577i = i4;
        this.f17578j = i5;
        this.f17579k = i6;
        try {
            File file = new File(str);
            if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
                int i7 = 0;
                int i8 = 0;
                int i9 = 0;
                int i10 = 0;
                int i11 = 0;
                int i12 = 0;
                for (File file2 : listFiles) {
                    if (file2.isFile()) {
                        String name = file2.getName();
                        if (!name.startsWith("tombstone_")) {
                            if (name.startsWith(this.f17569a + "_")) {
                                if (name.endsWith(this.f17570b)) {
                                    i11++;
                                } else if (name.endsWith(this.f17571c)) {
                                    i12++;
                                }
                            }
                        } else if (name.endsWith(".java.nbscrash")) {
                            i7++;
                        } else if (name.endsWith(".native.nbscrash")) {
                            i8++;
                        } else if (name.endsWith(".anr.nbscrash")) {
                            i9++;
                        } else if (name.endsWith(".trace.nbscrash")) {
                            i10++;
                        }
                    }
                }
                if (i7 <= this.f17573e && i8 <= this.f17574f && i9 <= this.f17575g && i10 <= this.f17576h && i11 == this.f17577i && i12 == 0) {
                    this.f17579k = -1;
                    return;
                }
                if (i7 <= this.f17573e + 10 && i8 <= this.f17574f + 10 && i9 <= this.f17575g + 10 && i10 <= this.f17576h + 10 && i11 <= this.f17577i + 10 && i12 <= 10) {
                    if (i7 > this.f17573e || i8 > this.f17574f || i9 > this.f17575g || i10 > this.f17576h || i11 > this.f17577i || i12 > 0) {
                        this.f17579k = 0;
                        return;
                    }
                    return;
                }
                m8449d();
                this.f17579k = -1;
            }
        } catch (Exception e) {
            NBSNativeCrash.m8519d().mo8433e("nbscrash", "FileManager init failed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m8455b() {
        int i;
        if (this.f17572d == null || (i = this.f17579k) < 0) {
            return;
        }
        try {
            if (i == 0) {
                new Thread(new Runnable() { // from class: com.networkbench.nbslens.nbsnativecrashlib.e.1
                    @Override // java.lang.Runnable
                    public void run() {
                        C6781e.this.m8449d();
                    }
                }, "nbscrash_file_mgr").start();
            } else {
                new Timer("nbscrash_file_mgr").schedule(new TimerTask() { // from class: com.networkbench.nbslens.nbsnativecrashlib.e.3
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        C6781e.this.m8449d();
                    }
                }, this.f17579k);
            }
        } catch (Exception e) {
            NBSNativeCrash.m8519d().mo8433e("nbscrash", "FileManager maintain start failed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean m8452c() {
        if (C6805n.m8382a(this.f17572d)) {
            try {
                return m8459a(new File(this.f17572d), ".anr.nbscrash", this.f17575g);
            } catch (Exception e) {
                NBSNativeCrash.m8519d().mo8433e("nbscrash", "FileManager maintainAnr failed", e);
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public File m8458a(String str) {
        String str2 = this.f17572d;
        if (str2 != null && C6805n.m8382a(str2)) {
            File file = new File(str);
            File[] listFiles = new File(this.f17572d).listFiles(new FilenameFilter() { // from class: com.networkbench.nbslens.nbsnativecrashlib.e.4
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str3) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(C6781e.this.f17569a);
                    sb.append("_");
                    return str3.startsWith(sb.toString()) && str3.endsWith(C6781e.this.f17570b);
                }
            });
            if (listFiles != null) {
                for (int length = listFiles.length; length > 0; length--) {
                    File file2 = listFiles[length - 1];
                    try {
                    } catch (Exception e) {
                        NBSNativeCrash.m8519d().mo8433e("nbscrash", "FileManager createLogFile by renameTo failed", e);
                    }
                    if (file2.renameTo(file)) {
                        return file;
                    }
                    file2.delete();
                }
            }
            try {
                if (file.createNewFile()) {
                    return file;
                }
                NBSNativeCrash.m8519d().mo8434e("nbscrash", "FileManager createLogFile by createNewFile failed, file already exists");
                return null;
            } catch (Exception e2) {
                NBSNativeCrash.m8519d().mo8433e("nbscrash", "FileManager createLogFile by createNewFile failed", e2);
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m8456a(String str, String str2) {
        RandomAccessFile randomAccessFile;
        long j;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(str, "rws");
                try {
                    if (randomAccessFile.length() > 0) {
                        MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, randomAccessFile.length());
                        j = randomAccessFile.length();
                        while (j > 0 && map.get(((int) j) - 1) == 0) {
                            j--;
                        }
                    } else {
                        j = 0;
                    }
                    randomAccessFile.seek(j);
                    randomAccessFile.write(str2.getBytes("UTF-8"));
                    try {
                        randomAccessFile.close();
                    } catch (Exception unused) {
                    }
                    return true;
                } catch (Exception e) {
                    e = e;
                    randomAccessFile2 = randomAccessFile;
                    NBSNativeCrash.m8519d().mo8433e("nbscrash", "FileManager appendText failed", e);
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (Exception unused2) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (Exception unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m8460a(File file) {
        if (file == null) {
            return false;
        }
        String str = this.f17572d;
        if (str == null || this.f17577i <= 0) {
            try {
                return file.delete();
            } catch (Exception unused) {
                return false;
            }
        }
        try {
            File[] listFiles = new File(str).listFiles(new FilenameFilter() { // from class: com.networkbench.nbslens.nbsnativecrashlib.e.5
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(C6781e.this.f17569a);
                    sb.append("_");
                    return str2.startsWith(sb.toString()) && str2.endsWith(C6781e.this.f17570b);
                }
            });
            if (listFiles != null && listFiles.length >= this.f17577i) {
                try {
                    return file.delete();
                } catch (Exception unused2) {
                    return false;
                }
            }
            File file2 = new File(String.format(Locale.US, "%s/%s_%020d%s", this.f17572d, this.f17569a, Long.valueOf((new Date().getTime() * 1000) + m8446e()), this.f17571c));
            if (!file.renameTo(file2)) {
                try {
                    return file.delete();
                } catch (Exception unused3) {
                    return false;
                }
            }
            return m8447d(file2);
        } catch (Exception e) {
            NBSNativeCrash.m8519d().mo8433e("nbscrash", "FileManager recycleLogFile failed", e);
            try {
                return file.delete();
            } catch (Exception unused4) {
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m8449d() {
        if (C6805n.m8382a(this.f17572d)) {
            File file = new File(this.f17572d);
            try {
                m8453b(file);
            } catch (Exception e) {
                NBSNativeCrash.m8519d().mo8433e("nbscrash", "FileManager doMaintainTombstone failed", e);
            }
            try {
                m8450c(file);
            } catch (Exception e2) {
                NBSNativeCrash.m8519d().mo8433e("nbscrash", "FileManager doMaintainPlaceholder failed", e2);
            }
        }
    }

    /* renamed from: b */
    private void m8453b(File file) {
        m8459a(file, ".native.nbscrash", this.f17574f);
        m8459a(file, ".java.nbscrash", this.f17573e);
        m8459a(file, ".anr.nbscrash", this.f17575g);
        m8459a(file, ".trace.nbscrash", this.f17576h);
    }

    /* renamed from: a */
    private boolean m8459a(File file, final String str, int i) {
        File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.networkbench.nbslens.nbsnativecrashlib.e.6
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str2) {
                return str2.startsWith("tombstone_") && str2.endsWith(str);
            }
        });
        if (listFiles == null || listFiles.length <= i) {
            return true;
        }
        if (i > 0) {
            Arrays.sort(listFiles, new Comparator<File>() { // from class: com.networkbench.nbslens.nbsnativecrashlib.e.7
                @Override // java.util.Comparator
                /* renamed from: a */
                public int compare(File file2, File file3) {
                    return file2.getName().compareTo(file3.getName());
                }
            });
        }
        boolean z = true;
        for (int i2 = 0; i2 < listFiles.length - i; i2++) {
            if (!m8460a(listFiles[i2])) {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: c */
    private void m8450c(File file) {
        File[] listFiles;
        int i;
        int i2;
        File[] listFiles2 = file.listFiles(new FilenameFilter() { // from class: com.networkbench.nbslens.nbsnativecrashlib.e.8
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                StringBuilder sb = new StringBuilder();
                sb.append(C6781e.this.f17569a);
                sb.append("_");
                return str.startsWith(sb.toString()) && str.endsWith(C6781e.this.f17570b);
            }
        });
        if (listFiles2 == null || (listFiles = file.listFiles(new FilenameFilter() { // from class: com.networkbench.nbslens.nbsnativecrashlib.e.9
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                StringBuilder sb = new StringBuilder();
                sb.append(C6781e.this.f17569a);
                sb.append("_");
                return str.startsWith(sb.toString()) && str.endsWith(C6781e.this.f17571c);
            }
        })) == null) {
            return;
        }
        int length = listFiles2.length;
        int length2 = listFiles.length;
        char c = 0;
        int i3 = 0;
        while (true) {
            if (length >= this.f17577i) {
                i = i3;
                break;
            }
            if (length2 > 0) {
                if (m8447d(listFiles[length2 - 1])) {
                    length++;
                }
                length2--;
                i2 = i3;
            } else {
                try {
                    Locale locale = Locale.US;
                    Object[] objArr = new Object[4];
                    objArr[c] = this.f17572d;
                    objArr[1] = this.f17569a;
                    i2 = i3;
                    try {
                        objArr[2] = Long.valueOf((new Date().getTime() * 1000) + m8446e());
                        objArr[3] = this.f17571c;
                        File file2 = new File(String.format(locale, "%s/%s_%020d%s", objArr));
                        if (file2.createNewFile() && m8447d(file2)) {
                            length++;
                        }
                    } catch (Exception unused) {
                    }
                } catch (Exception unused2) {
                    i2 = i3;
                }
            }
            i3 = i2 + 1;
            if (i3 > this.f17577i * 2) {
                i = i3;
                break;
            }
            c = 0;
        }
        if (i > 0) {
            listFiles2 = file.listFiles(new FilenameFilter() { // from class: com.networkbench.nbslens.nbsnativecrashlib.e.10
                @Override // java.io.FilenameFilter
                public boolean accept(File file3, String str) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(C6781e.this.f17569a);
                    sb.append("_");
                    return str.startsWith(sb.toString()) && str.endsWith(C6781e.this.f17570b);
                }
            });
            listFiles = file.listFiles(new FilenameFilter() { // from class: com.networkbench.nbslens.nbsnativecrashlib.e.2
                @Override // java.io.FilenameFilter
                public boolean accept(File file3, String str) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(C6781e.this.f17569a);
                    sb.append("_");
                    return str.startsWith(sb.toString()) && str.endsWith(C6781e.this.f17571c);
                }
            });
        }
        if (listFiles2 != null && listFiles2.length > this.f17577i) {
            for (int i4 = 0; i4 < listFiles2.length - this.f17577i; i4++) {
                listFiles2[i4].delete();
            }
        }
        if (listFiles != null) {
            for (File file3 : listFiles) {
                file3.delete();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00b0 A[Catch: Exception -> 0x00b3, TRY_ENTER, TRY_LEAVE, TryCatch #6 {Exception -> 0x00b3, blocks: (B:36:0x00b0, B:21:0x0090), top: B:57:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m8447d(java.io.File r17) {
        /*
            r16 = this;
            r1 = r16
            r0 = 1024(0x400, float:1.435E-42)
            r2 = 0
            r3 = 0
            byte[] r4 = new byte[r0]     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            java.util.Arrays.fill(r4, r2)     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            int r5 = r1.f17578j     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            long r5 = (long) r5     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            long r7 = r17.length()     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            int r9 = r1.f17578j     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            int r9 = r9 * r0
            long r9 = (long) r9     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            r9 = 0
            r11 = 1024(0x400, double:5.06E-321)
            if (r0 <= 0) goto L29
            long r5 = r7 / r11
            long r13 = r7 % r11
            int r0 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
            if (r0 == 0) goto L29
            r13 = 1
            long r5 = r5 + r13
        L29:
            java.io.FileOutputStream r13 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            java.io.File r0 = r17.getAbsoluteFile()     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            r13.<init>(r0, r2)     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            r0 = r2
        L33:
            long r14 = (long) r0
            int r3 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r3 >= 0) goto L50
            int r0 = r0 + 1
            long r14 = (long) r0
            int r3 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r3 != 0) goto L4c
            long r14 = r7 % r11
            int r3 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r3 == 0) goto L4c
            long r14 = r7 % r11
            int r3 = (int) r14     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r13.write(r4, r2, r3)     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            goto L33
        L4c:
            r13.write(r4)     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            goto L33
        L50:
            r13.flush()     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            java.util.Locale r0 = java.util.Locale.US     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            java.lang.String r3 = "%s/%s_%020d%s"
            r4 = 4
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            java.lang.String r5 = r1.f17572d     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r4[r2] = r5     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            java.lang.String r5 = r1.f17569a     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r6 = 1
            r4[r6] = r5     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r5 = 2
            java.util.Date r6 = new java.util.Date     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r6.<init>()     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            long r6 = r6.getTime()     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 * r8
            int r8 = r16.m8446e()     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            long r8 = (long) r8     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            long r6 = r6 + r8
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r4[r5] = r6     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r5 = 3
            java.lang.String r6 = r1.f17570b     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r4[r5] = r6     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            java.lang.String r0 = java.lang.String.format(r0, r3, r4)     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r4 = r17
            boolean r2 = r4.renameTo(r3)     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            r13.close()     // Catch: java.lang.Exception -> Lb3
            goto Lb3
        L94:
            r0 = move-exception
            goto L9b
        L96:
            r0 = move-exception
            goto Lb9
        L98:
            r0 = move-exception
            r4 = r17
        L9b:
            r3 = r13
            goto La3
        L9d:
            r0 = move-exception
            r13 = r3
            goto Lb9
        La0:
            r0 = move-exception
            r4 = r17
        La3:
            com.networkbench.nbslens.nbsnativecrashlib.h r5 = com.networkbench.nbslens.nbsnativecrashlib.NBSNativeCrash.m8519d()     // Catch: java.lang.Throwable -> L9d
            java.lang.String r6 = "nbscrash"
            java.lang.String r7 = "FileManager cleanTheDirtyFile failed"
            r5.mo8433e(r6, r7, r0)     // Catch: java.lang.Throwable -> L9d
            if (r3 == 0) goto Lb3
            r3.close()     // Catch: java.lang.Exception -> Lb3
        Lb3:
            if (r2 != 0) goto Lb8
            r17.delete()     // Catch: java.lang.Exception -> Lb8
        Lb8:
            return r2
        Lb9:
            if (r13 == 0) goto Lbe
            r13.close()     // Catch: java.lang.Exception -> Lbe
        Lbe:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.nbslens.nbsnativecrashlib.C6781e.m8447d(java.io.File):boolean");
    }

    /* renamed from: e */
    private int m8446e() {
        int incrementAndGet = this.f17580l.incrementAndGet();
        if (incrementAndGet >= 999) {
            this.f17580l.set(0);
        }
        return incrementAndGet;
    }
}
