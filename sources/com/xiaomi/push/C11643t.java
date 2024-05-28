package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.xiaomi.push.t */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11643t {

    /* renamed from: a */
    private static final Set<String> f23799a = Collections.synchronizedSet(new HashSet());

    /* renamed from: a */
    private Context f23800a;

    /* renamed from: a */
    private RandomAccessFile f23801a;

    /* renamed from: a */
    private String f23802a;

    /* renamed from: a */
    private FileLock f23803a;

    private C11643t(Context context) {
        this.f23800a = context;
    }

    /* renamed from: a */
    public static C11643t m2279a(Context context, File file) {
        AbstractC11049b.m5270c("Locking: " + file.getAbsolutePath());
        String str = file.getAbsolutePath() + ".LOCK";
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.getParentFile().mkdirs();
            file2.createNewFile();
        }
        if (f23799a.add(str)) {
            C11643t c11643t = new C11643t(context);
            c11643t.f23802a = str;
            try {
                c11643t.f23801a = new RandomAccessFile(file2, "rw");
                c11643t.f23803a = c11643t.f23801a.getChannel().lock();
                AbstractC11049b.m5270c("Locked: " + str + " :" + c11643t.f23803a);
                return c11643t;
            } finally {
                if (c11643t.f23803a == null) {
                    RandomAccessFile randomAccessFile = c11643t.f23801a;
                    if (randomAccessFile != null) {
                        C11647w.m2274a(randomAccessFile);
                    }
                    f23799a.remove(c11643t.f23802a);
                }
            }
        }
        throw new IOException("abtain lock failure");
    }

    /* renamed from: a */
    public void m2280a() {
        AbstractC11049b.m5270c("unLock: " + this.f23803a);
        FileLock fileLock = this.f23803a;
        if (fileLock != null && fileLock.isValid()) {
            try {
                this.f23803a.release();
            } catch (IOException unused) {
            }
            this.f23803a = null;
        }
        RandomAccessFile randomAccessFile = this.f23801a;
        if (randomAccessFile != null) {
            C11647w.m2274a(randomAccessFile);
        }
        f23799a.remove(this.f23802a);
    }
}
