package com.xiaomi.push;

import java.net.UnknownHostException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.em */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class C11330em {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.em$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C11331a {

        /* renamed from: a */
        EnumC11324ei f22159a;

        /* renamed from: a */
        String f22160a;

        C11331a() {
        }
    }

    /* renamed from: a */
    private static void m4001a(Exception exc) {
        if (exc == null) {
            throw new NullPointerException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C11331a m4002a(Exception exc) {
        m4001a(exc);
        boolean z = exc instanceof C11368fi;
        Exception exc2 = exc;
        if (z) {
            C11368fi c11368fi = (C11368fi) exc;
            exc2 = exc;
            if (c11368fi.m3835a() != null) {
                exc2 = c11368fi.m3835a();
            }
        }
        C11331a c11331a = new C11331a();
        String message = exc2.getMessage();
        if (exc2.getCause() != null) {
            message = exc2.getCause().getMessage();
        }
        String str = exc2.getClass().getSimpleName() + ":" + message;
        int m3861a = C11359fc.m3861a(exc2);
        if (m3861a != 0) {
            c11331a.f22159a = EnumC11324ei.m4042a(EnumC11324ei.GSLB_REQUEST_SUCCESS.m4043a() + m3861a);
        }
        if (c11331a.f22159a == null) {
            c11331a.f22159a = EnumC11324ei.GSLB_TCP_ERR_OTHER;
        }
        if (c11331a.f22159a == EnumC11324ei.GSLB_TCP_ERR_OTHER) {
            c11331a.f22160a = str;
        }
        return c11331a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static C11331a m4000b(Exception exc) {
        Throwable cause;
        m4001a(exc);
        boolean z = exc instanceof C11368fi;
        Exception exc2 = exc;
        if (z) {
            C11368fi c11368fi = (C11368fi) exc;
            exc2 = exc;
            if (c11368fi.m3835a() != null) {
                exc2 = c11368fi.m3835a();
            }
        }
        C11331a c11331a = new C11331a();
        String message = exc2.getMessage();
        if (exc2.getCause() != null) {
            message = exc2.getCause().getMessage();
        }
        int m3861a = C11359fc.m3861a(exc2);
        String str = exc2.getClass().getSimpleName() + ":" + message;
        if (m3861a != 0) {
            c11331a.f22159a = EnumC11324ei.m4042a(EnumC11324ei.CONN_SUCCESS.m4043a() + m3861a);
            if (c11331a.f22159a == EnumC11324ei.CONN_BOSH_ERR && (cause = exc2.getCause()) != null && (cause instanceof UnknownHostException)) {
                c11331a.f22159a = EnumC11324ei.CONN_BOSH_UNKNOWNHOST;
            }
        } else {
            c11331a.f22159a = EnumC11324ei.CONN_XMPP_ERR;
        }
        if (c11331a.f22159a == EnumC11324ei.CONN_TCP_ERR_OTHER || c11331a.f22159a == EnumC11324ei.CONN_XMPP_ERR || c11331a.f22159a == EnumC11324ei.CONN_BOSH_ERR) {
            c11331a.f22160a = str;
        }
        return c11331a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static C11331a m3999c(Exception exc) {
        m4001a(exc);
        boolean z = exc instanceof C11368fi;
        Exception exc2 = exc;
        if (z) {
            C11368fi c11368fi = (C11368fi) exc;
            exc2 = exc;
            if (c11368fi.m3835a() != null) {
                exc2 = c11368fi.m3835a();
            }
        }
        C11331a c11331a = new C11331a();
        String message = exc2.getMessage();
        if (exc2.getCause() != null) {
            message = exc2.getCause().getMessage();
        }
        int m3861a = C11359fc.m3861a(exc2);
        String str = exc2.getClass().getSimpleName() + ":" + message;
        if (m3861a == 105) {
            c11331a.f22159a = EnumC11324ei.BIND_TCP_READ_TIMEOUT;
        } else if (m3861a == 199) {
            c11331a.f22159a = EnumC11324ei.BIND_TCP_ERR;
        } else if (m3861a != 499) {
            switch (m3861a) {
                case 109:
                    c11331a.f22159a = EnumC11324ei.BIND_TCP_CONNRESET;
                    break;
                case 110:
                    c11331a.f22159a = EnumC11324ei.BIND_TCP_BROKEN_PIPE;
                    break;
                default:
                    c11331a.f22159a = EnumC11324ei.BIND_XMPP_ERR;
                    break;
            }
        } else {
            c11331a.f22159a = EnumC11324ei.BIND_BOSH_ERR;
            if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                c11331a.f22159a = EnumC11324ei.BIND_BOSH_ITEM_NOT_FOUND;
            }
        }
        if (c11331a.f22159a == EnumC11324ei.BIND_TCP_ERR || c11331a.f22159a == EnumC11324ei.BIND_XMPP_ERR || c11331a.f22159a == EnumC11324ei.BIND_BOSH_ERR) {
            c11331a.f22160a = str;
        }
        return c11331a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static C11331a m3998d(Exception exc) {
        m4001a(exc);
        boolean z = exc instanceof C11368fi;
        Exception exc2 = exc;
        if (z) {
            C11368fi c11368fi = (C11368fi) exc;
            exc2 = exc;
            if (c11368fi.m3835a() != null) {
                exc2 = c11368fi.m3835a();
            }
        }
        C11331a c11331a = new C11331a();
        String message = exc2.getMessage();
        int m3861a = C11359fc.m3861a(exc2);
        String str = exc2.getClass().getSimpleName() + ":" + message;
        if (m3861a == 105) {
            c11331a.f22159a = EnumC11324ei.CHANNEL_TCP_READTIMEOUT;
        } else if (m3861a == 199) {
            c11331a.f22159a = EnumC11324ei.CHANNEL_TCP_ERR;
        } else if (m3861a != 499) {
            switch (m3861a) {
                case 109:
                    c11331a.f22159a = EnumC11324ei.CHANNEL_TCP_CONNRESET;
                    break;
                case 110:
                    c11331a.f22159a = EnumC11324ei.CHANNEL_TCP_BROKEN_PIPE;
                    break;
                default:
                    c11331a.f22159a = EnumC11324ei.CHANNEL_XMPPEXCEPTION;
                    break;
            }
        } else {
            c11331a.f22159a = EnumC11324ei.CHANNEL_BOSH_EXCEPTION;
            if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                c11331a.f22159a = EnumC11324ei.CHANNEL_BOSH_ITEMNOTFIND;
            }
        }
        if (c11331a.f22159a == EnumC11324ei.CHANNEL_TCP_ERR || c11331a.f22159a == EnumC11324ei.CHANNEL_XMPPEXCEPTION || c11331a.f22159a == EnumC11324ei.CHANNEL_BOSH_EXCEPTION) {
            c11331a.f22160a = str;
        }
        return c11331a;
    }
}
