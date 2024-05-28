package com.baidu.cloud.videocache.file;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class ass implements DiskUsage {

    /* renamed from: a */
    private static final Logger f4874a = LoggerFactory.getLogger("LruDiskUsage");

    /* renamed from: b */
    private final ExecutorService f4875b = Executors.newSingleThreadExecutor();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class oia implements Callable {

        /* renamed from: b */
        private final File f4877b;

        public oia(File file) {
            this.f4877b = file;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            ass.this.m19820a(this.f4877b);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19820a(File file) {
        nxb.m19813c(file);
        m19819a(nxb.m19814b(file.getParentFile()));
    }

    /* renamed from: a */
    private void m19819a(List list) {
        long m19818b = m19818b(list);
        int size = list.size();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            File file = (File) it.next();
            if (!mo19816a(file, m19818b, size)) {
                long length = file.length();
                if (file.delete()) {
                    size--;
                    m19818b -= length;
                    f4874a.info("Cache file " + file + " is deleted because it exceeds cache limit");
                } else {
                    f4874a.error("Error deleting file " + file + " for trimming cache");
                }
            }
        }
    }

    /* renamed from: b */
    private long m19818b(List list) {
        Iterator it = list.iterator();
        long j = 0;
        while (it.hasNext()) {
            j += ((File) it.next()).length();
        }
        return j;
    }

    /* renamed from: a */
    protected abstract boolean mo19816a(File file, long j, int i);

    @Override // com.baidu.cloud.videocache.file.DiskUsage
    public void touch(File file) {
        this.f4875b.submit(new oia(file));
    }
}
