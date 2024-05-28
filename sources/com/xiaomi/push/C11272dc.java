package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;

/* renamed from: com.xiaomi.push.dc */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11272dc implements LoggerInterface {

    /* renamed from: a */
    private LoggerInterface f21846a;

    /* renamed from: b */
    private LoggerInterface f21847b;

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public void setTag(String str) {
    }

    public C11272dc(LoggerInterface loggerInterface, LoggerInterface loggerInterface2) {
        this.f21846a = null;
        this.f21847b = null;
        this.f21846a = loggerInterface;
        this.f21847b = loggerInterface2;
    }

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public void log(String str) {
        LoggerInterface loggerInterface = this.f21846a;
        if (loggerInterface != null) {
            loggerInterface.log(str);
        }
        LoggerInterface loggerInterface2 = this.f21847b;
        if (loggerInterface2 != null) {
            loggerInterface2.log(str);
        }
    }

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public void log(String str, Throwable th) {
        LoggerInterface loggerInterface = this.f21846a;
        if (loggerInterface != null) {
            loggerInterface.log(str, th);
        }
        LoggerInterface loggerInterface2 = this.f21847b;
        if (loggerInterface2 != null) {
            loggerInterface2.log(str, th);
        }
    }
}
