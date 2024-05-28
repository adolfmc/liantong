package com.xiaomi.channel.commonutils.logger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface LoggerInterface {
    void log(String str);

    void log(String str, Throwable th);

    void setTag(String str);
}
