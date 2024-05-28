package com.networkbench.agent.impl.instrumentation;

import android.os.Build;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.socket.C6610g;
import com.networkbench.agent.impl.socket.C6614k;
import com.networkbench.agent.impl.socket.CustomSSLSocketFactory;
import com.networkbench.agent.impl.socket.CustomSSLSocketFactoryOld;
import com.networkbench.agent.impl.socket.p279b.C6605b;
import com.networkbench.agent.impl.util.C6632b;
import com.networkbench.agent.impl.util.C6633c;
import com.networkbench.agent.impl.util.C6643l;
import com.networkbench.agent.impl.util.C6645n;
import com.networkbench.agent.impl.util.C6653u;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.SocketImplFactory;
import okhttp3.OkHttpClient;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NetworkLibInit {
    public static boolean installNetworkMonitor() {
        boolean z;
        try {
            if (isQuoteOkhttp3()) {
                try {
                    C6643l c6643l = new C6643l(C6653u.m8693j());
                    int m8897a = c6643l.m8897a();
                    int m8896b = c6643l.m8896b();
                    int m8895c = c6643l.m8895c();
                    if (m8897a > 4) {
                        C6396h.m10134h("Okhttp3 版本高于5,将不采集socket数据..");
                        return false;
                    }
                    if (m8897a == 4) {
                        if (m8896b == 0 && m8895c > 1) {
                            C6396h.m10134h("Okhttp3 版本高于4.0.1,将不采集socket数据..");
                            return false;
                        } else if (m8896b == 1 && m8895c > 1) {
                            C6396h.m10134h("Okhttp3 版本高于4.1.2,将不采集socket数据..");
                            return false;
                        } else if (m8896b >= 2) {
                            C6396h.m10134h("Okhttp3 版本高于4.2.0,将不采集socket数据..");
                            return false;
                        }
                    }
                    if (m8897a == 3) {
                        if (m8896b == 13 && m8895c > 1) {
                            C6396h.m10134h("Okhttp3 版本高于3.13.1,将不采集socket数据..");
                            return false;
                        } else if (m8896b == 14 && m8895c > 4) {
                            C6396h.m10134h("Okhttp3 版本高于3.14.4,将不采集socket数据..");
                            return false;
                        } else if (m8896b == 12 && m8895c > 6) {
                            C6396h.m10134h("Okhttp3 版本高于3.12.6,将不采集socket数据..");
                            return false;
                        } else if (m8896b >= 15) {
                            C6396h.m10134h("Okhttp3 版本高于3.15.X,将不采集socket数据..");
                            return false;
                        }
                    }
                } catch (Throwable unused) {
                    return false;
                }
            }
            if (Build.VERSION.SDK_INT > 23) {
                installSocketImplFactoryV24();
            } else {
                C6610g.m9258a();
            }
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 27) {
                C6633c.m9119c("install custom ssl socket factory");
                z = CustomSSLSocketFactory.m9282b();
            } else if (Build.VERSION.SDK_INT < 14 || Build.VERSION.SDK_INT >= 19) {
                z = false;
            } else {
                C6633c.m9119c("install custom ssl socket factory old");
                z = CustomSSLSocketFactoryOld.m9278b();
            }
            if (z) {
                z = C6614k.m9248a();
            }
            C6633c.m9119c("install ssl network monitor:" + z);
            return z;
        } catch (Throwable unused2) {
            return false;
        }
    }

    public static boolean installSocketImplFactoryV24() {
        try {
            Class<? extends SocketImpl> cls = null;
            SocketImplFactory socketImplFactory = (SocketImplFactory) C6645n.m8877a(C6645n.m8883a(Socket.class, SocketImplFactory.class), (Object) null);
            if (socketImplFactory == null) {
                cls = getDefaultSocketImplType();
                if (cls == null) {
                    return false;
                }
            } else if (socketImplFactory instanceof C6605b) {
                return true;
            }
            try {
                if (socketImplFactory != null) {
                    reflectivelyInstallSocketImplFactory(new C6605b(socketImplFactory));
                } else if (cls == null) {
                    return false;
                } else {
                    Socket.setSocketImplFactory(new C6605b(cls));
                }
                return true;
            } catch (C6632b unused) {
                return false;
            } catch (IOException unused2) {
                return false;
            } catch (IllegalAccessException unused3) {
                return false;
            }
        } catch (C6632b unused4) {
            return false;
        }
    }

    public static boolean reflectivelyInstallSocketImplFactory(SocketImplFactory socketImplFactory) throws IllegalAccessException, C6632b {
        try {
            Field m8883a = C6645n.m8883a(Socket.class, SocketImplFactory.class);
            m8883a.setAccessible(true);
            m8883a.set(null, socketImplFactory);
            return true;
        } catch (C6632b e) {
            throw e;
        } catch (IllegalAccessException e2) {
            throw e2;
        }
    }

    public static Class<? extends SocketImpl> getDefaultSocketImplType() {
        try {
            SocketImpl socketImpl = (SocketImpl) C6645n.m8877a(C6645n.m8883a(Socket.class, SocketImpl.class), new Socket());
            if (socketImpl == null) {
                return null;
            }
            return socketImpl.getClass();
        } catch (C6632b unused) {
            return null;
        }
    }

    public static boolean isQuoteOkhttp3() {
        try {
            Class.forName(OkHttpClient.class.getName());
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
