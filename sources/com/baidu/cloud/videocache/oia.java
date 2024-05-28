package com.baidu.cloud.videocache;

import com.baidu.cloud.videocache.file.DiskUsage;
import com.baidu.cloud.videocache.file.FileNameGenerator;
import com.baidu.cloud.videocache.headers.HeaderInjector;
import com.baidu.cloud.videocache.sourcestorage.SourceInfoStorage;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class oia {

    /* renamed from: a */
    public final File f4906a;

    /* renamed from: b */
    public final FileNameGenerator f4907b;

    /* renamed from: c */
    public final DiskUsage f4908c;

    /* renamed from: d */
    public final SourceInfoStorage f4909d;

    /* renamed from: e */
    public final HeaderInjector f4910e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public oia(File file, FileNameGenerator fileNameGenerator, DiskUsage diskUsage, SourceInfoStorage sourceInfoStorage, HeaderInjector headerInjector) {
        this.f4906a = file;
        this.f4907b = fileNameGenerator;
        this.f4908c = diskUsage;
        this.f4909d = sourceInfoStorage;
        this.f4910e = headerInjector;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public File m19768a(String str) {
        return new File(this.f4906a, this.f4907b.generate(str));
    }
}
