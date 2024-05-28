package com.mob.tools;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;

/* renamed from: com.mob.tools.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C6023a {

    /* renamed from: a */
    private static final Object f14819a = new Object();

    /* renamed from: b */
    private static final Object f14820b = new Object();

    /* renamed from: c */
    private volatile HashSet<String> f14821c = new HashSet<>();

    /* renamed from: d */
    private File f14822d;

    /* renamed from: e */
    private int f14823e;

    /* renamed from: f */
    private String f14824f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC6027a {
        /* renamed from: a */
        void mo11810a(String str);

        /* renamed from: a */
        boolean mo11811a(C6152DH.DHResponse dHResponse);
    }

    /* renamed from: a */
    static /* synthetic */ boolean m11819a(C6023a c6023a, String str) {
        return c6023a.m11813b(str);
    }

    /* renamed from: b */
    static /* synthetic */ void m11814b(C6023a c6023a, String str) {
        c6023a.m11812c(str);
    }

    public C6023a(String str, String str2, int i) {
        this.f14823e = i;
        if (str2 == null) {
            str2 = "null";
        } else if (TextUtils.isDigitsOnly(str2)) {
            str2 = str + str2;
        }
        this.f14824f = str2;
        this.f14822d = ResHelper.getDataCacheFile(MobSDK.getContextSafely(), str);
        if (this.f14822d.isDirectory()) {
            return;
        }
        this.f14822d.mkdirs();
    }

    /* renamed from: a */
    public void m11818a(String str) throws Throwable {
        m11817a(str, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public boolean m11813b(String str) {
        synchronized (this.f14821c) {
            if (this.f14821c.contains(str)) {
                return true;
            }
            this.f14821c.add(str);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m11812c(String str) {
        synchronized (this.f14821c) {
            this.f14821c.remove(str);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: a */
    public void m11817a(java.lang.String r8, boolean r9) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 == 0) goto L7
            return
        L7:
            java.lang.String r0 = "utf-8"
            byte[] r8 = r8.getBytes(r0)
            r0 = 2
            java.lang.String r8 = android.util.Base64.encodeToString(r8, r0)
            boolean r1 = android.text.TextUtils.isEmpty(r8)
            if (r1 == 0) goto L1a
            return
        L1a:
            java.lang.Object r1 = com.mob.tools.C6023a.f14819a
            monitor-enter(r1)
            java.io.File r9 = r7.m11816a(r9)     // Catch: java.lang.Throwable -> L7b
            r2 = 0
            r3 = 0
            r4 = 1
            java.io.FileWriter r5 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L50
            r5.<init>(r9, r4)     // Catch: java.lang.Throwable -> L50
            java.io.BufferedWriter r6 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L4b
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L4b
            r6.newLine()     // Catch: java.lang.Throwable -> L48
            r6.write(r8)     // Catch: java.lang.Throwable -> L48
            java.io.Closeable[] r8 = new java.io.Closeable[r0]     // Catch: java.lang.Throwable -> L7b
            r8[r3] = r6     // Catch: java.lang.Throwable -> L7b
            r8[r4] = r5     // Catch: java.lang.Throwable -> L7b
            com.mob.commons.C5873u.m12179a(r8)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r8 = r9.getName()     // Catch: java.lang.Throwable -> L7b
        L41:
            r7.m11812c(r8)     // Catch: java.lang.Throwable -> L7b
            goto L67
        L45:
            r8 = move-exception
            r2 = r6
            goto L6a
        L48:
            r8 = move-exception
            r2 = r6
            goto L52
        L4b:
            r8 = move-exception
            goto L52
        L4d:
            r8 = move-exception
            r5 = r2
            goto L6a
        L50:
            r8 = move-exception
            r5 = r2
        L52:
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L69
            r6.m11341d(r8)     // Catch: java.lang.Throwable -> L69
            java.io.Closeable[] r8 = new java.io.Closeable[r0]     // Catch: java.lang.Throwable -> L7b
            r8[r3] = r2     // Catch: java.lang.Throwable -> L7b
            r8[r4] = r5     // Catch: java.lang.Throwable -> L7b
            com.mob.commons.C5873u.m12179a(r8)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r8 = r9.getName()     // Catch: java.lang.Throwable -> L7b
            goto L41
        L67:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L7b
            return
        L69:
            r8 = move-exception
        L6a:
            java.io.Closeable[] r0 = new java.io.Closeable[r0]     // Catch: java.lang.Throwable -> L7b
            r0[r3] = r2     // Catch: java.lang.Throwable -> L7b
            r0[r4] = r5     // Catch: java.lang.Throwable -> L7b
            com.mob.commons.C5873u.m12179a(r0)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r9 = r9.getName()     // Catch: java.lang.Throwable -> L7b
            r7.m11812c(r9)     // Catch: java.lang.Throwable -> L7b
            throw r8     // Catch: java.lang.Throwable -> L7b
        L7b:
            r8 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L7b
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.C6023a.m11817a(java.lang.String, boolean):void");
    }

    /* renamed from: a */
    private File m11816a(boolean z) {
        File file;
        File[] listFiles = this.f14822d.listFiles();
        int i = 5;
        if (listFiles != null && listFiles.length > 0) {
            int length = listFiles.length;
            int i2 = 0;
            int i3 = 1;
            while (i2 < length) {
                File file2 = listFiles[i2];
                String name = file2.getName();
                if (name.startsWith(this.f14824f)) {
                    String[] split = name.split("_");
                    if (!z && split.length == 3) {
                        try {
                            int parseInt = Integer.parseInt(split[2]);
                            if (parseInt < this.f14823e && !m11813b(name)) {
                                File file3 = this.f14822d;
                                Object[] objArr = new Object[i];
                                objArr[0] = this.f14824f;
                                objArr[1] = "_";
                                objArr[2] = Integer.valueOf(i3);
                                objArr[3] = "_";
                                objArr[4] = Integer.valueOf(parseInt + 1);
                                File file4 = new File(file3, m11815a(objArr));
                                return file2.renameTo(file4) ? file4 : file2;
                            }
                        } catch (Throwable th) {
                            MobLog.getInstance().m11341d(th);
                        }
                    }
                    if (split.length > 1) {
                        try {
                            if (Integer.parseInt(split[1]) == i3) {
                                i3++;
                            }
                        } catch (Throwable th2) {
                            MobLog.getInstance().m11341d(th2);
                        }
                    }
                }
                i2++;
                i = 5;
            }
            file = new File(this.f14822d, m11815a(this.f14824f, "_", Integer.valueOf(i3), "_", 0));
        } else {
            file = new File(this.f14822d, m11815a(this.f14824f, "_", 1, "_", 0));
        }
        try {
            file.createNewFile();
        } catch (Throwable unused) {
        }
        return file;
    }

    /* renamed from: a */
    public void m11821a(final InterfaceC6027a interfaceC6027a) {
        if (interfaceC6027a == null) {
            return;
        }
        synchronized (f14820b) {
            final File[] listFiles = this.f14822d.listFiles(new FilenameFilter() { // from class: com.mob.tools.a.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str) {
                    return !TextUtils.isEmpty(str) && str.startsWith(C6023a.this.f14824f);
                }
            });
            if (listFiles != null && listFiles.length > 0) {
                C6152DH.requester(MobSDK.getContext()).getDetailNetworkTypeForStatic().getAppName().getDeviceKey().getODH().request(new C6152DH.DHResponder() { // from class: com.mob.tools.a.2
                    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
                        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
                        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
                        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
                        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
                        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
                        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
                        */
                    @Override // com.mob.tools.utils.C6152DH.DHResponder
                    public void onResponse(com.mob.tools.utils.C6152DH.DHResponse r15) {
                        /*
                            r14 = this;
                            java.io.File[] r0 = r2
                            int r1 = r0.length
                            r2 = 0
                            r3 = r2
                        L5:
                            if (r3 >= r1) goto L8e
                            r4 = r0[r3]
                            java.lang.String r5 = r4.getName()
                            com.mob.tools.a r6 = com.mob.tools.C6023a.this
                            boolean r6 = com.mob.tools.C6023a.m11819a(r6, r5)
                            if (r6 == 0) goto L16
                            goto L7a
                        L16:
                            r6 = 0
                            r7 = 2
                            r8 = 1
                            java.io.FileReader r9 = new java.io.FileReader     // Catch: java.lang.Throwable -> L63
                            r9.<init>(r4)     // Catch: java.lang.Throwable -> L63
                            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L5d
                            r10.<init>(r9)     // Catch: java.lang.Throwable -> L5d
                        L23:
                            java.lang.String r6 = r10.readLine()     // Catch: java.lang.Throwable -> L5a
                            if (r6 == 0) goto L3b
                            com.mob.tools.a$a r11 = r3     // Catch: java.lang.Throwable -> L5a
                            java.lang.String r12 = new java.lang.String     // Catch: java.lang.Throwable -> L5a
                            byte[] r6 = android.util.Base64.decode(r6, r7)     // Catch: java.lang.Throwable -> L5a
                            java.lang.String r13 = "utf-8"
                            r12.<init>(r6, r13)     // Catch: java.lang.Throwable -> L5a
                            r11.mo11810a(r12)     // Catch: java.lang.Throwable -> L5a
                            goto L23
                        L3b:
                            com.mob.tools.a$a r6 = r3     // Catch: java.lang.Throwable -> L5a
                            boolean r6 = r6.mo11811a(r15)     // Catch: java.lang.Throwable -> L5a
                            if (r6 == 0) goto L51
                            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L5a
                            java.lang.String r11 = "[LGSM] D l"
                            java.lang.Object[] r12 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L5a
                            r6.m11342d(r11, r12)     // Catch: java.lang.Throwable -> L5a
                            r4.delete()     // Catch: java.lang.Throwable -> L5a
                        L51:
                            java.io.Closeable[] r4 = new java.io.Closeable[r7]
                            r4[r2] = r10
                            r4[r8] = r9
                            goto L72
                        L58:
                            r15 = move-exception
                            goto L7f
                        L5a:
                            r4 = move-exception
                            r6 = r10
                            goto L65
                        L5d:
                            r4 = move-exception
                            goto L65
                        L5f:
                            r15 = move-exception
                            r9 = r6
                            r10 = r9
                            goto L7f
                        L63:
                            r4 = move-exception
                            r9 = r6
                        L65:
                            com.mob.tools.log.NLog r10 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L7d
                            r10.m11341d(r4)     // Catch: java.lang.Throwable -> L7d
                            java.io.Closeable[] r4 = new java.io.Closeable[r7]
                            r4[r2] = r6
                            r4[r8] = r9
                        L72:
                            com.mob.commons.C5873u.m12179a(r4)
                            com.mob.tools.a r4 = com.mob.tools.C6023a.this
                            com.mob.tools.C6023a.m11814b(r4, r5)
                        L7a:
                            int r3 = r3 + 1
                            goto L5
                        L7d:
                            r15 = move-exception
                            r10 = r6
                        L7f:
                            java.io.Closeable[] r0 = new java.io.Closeable[r7]
                            r0[r2] = r10
                            r0[r8] = r9
                            com.mob.commons.C5873u.m12179a(r0)
                            com.mob.tools.a r0 = com.mob.tools.C6023a.this
                            com.mob.tools.C6023a.m11814b(r0, r5)
                            throw r15
                        L8e:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.C6023a.C60252.onResponse(com.mob.tools.utils.DH$DHResponse):void");
                    }
                });
            }
        }
    }

    /* renamed from: a */
    public void m11822a(long j) {
        synchronized (f14820b) {
            File[] listFiles = this.f14822d.listFiles(new FilenameFilter() { // from class: com.mob.tools.a.3
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str) {
                    return !TextUtils.isEmpty(str) && str.startsWith(C6023a.this.f14824f);
                }
            });
            if (listFiles != null && listFiles.length > 0) {
                long j2 = 0;
                for (File file : listFiles) {
                    j2 += file.length();
                }
                if (j2 >= j) {
                    for (File file2 : listFiles) {
                        file2.delete();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static String m11815a(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            sb.append(obj);
        }
        return sb.toString();
    }
}
