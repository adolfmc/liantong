package com.xiaomi.push;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.fc */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11359fc {
    /* renamed from: a */
    public static int m3861a(Throwable th) {
        boolean z = th instanceof C11368fi;
        if (z) {
            C11368fi c11368fi = (C11368fi) th;
            if (c11368fi.m3835a() != null) {
                th = c11368fi.m3835a();
            }
        }
        String message = th.getMessage();
        if (th.getCause() != null) {
            message = th.getCause().getMessage();
        }
        if (th instanceof SocketTimeoutException) {
            return 105;
        }
        if (th instanceof SocketException) {
            if (message.indexOf("Network is unreachable") != -1) {
                return 102;
            }
            if (message.indexOf("Connection refused") != -1) {
                return 103;
            }
            if (message.indexOf("Connection timed out") != -1) {
                return 105;
            }
            if (message.endsWith("EACCES (Permission denied)")) {
                return 101;
            }
            if (message.indexOf("Connection reset by peer") != -1) {
                return 109;
            }
            if (message.indexOf("Broken pipe") != -1) {
                return 110;
            }
            if (message.indexOf("No route to host") != -1) {
                return 104;
            }
            return message.endsWith("EINVAL (Invalid argument)") ? 106 : 199;
        } else if (th instanceof UnknownHostException) {
            return 107;
        } else {
            return z ? 399 : 0;
        }
    }
}
