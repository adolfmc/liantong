package com.sinovatech.unicom.p318ui;

import com.fort.andjni.JniLib;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* renamed from: com.sinovatech.unicom.ui.OkhttpEventListener */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OkhttpEventListener extends EventListener {
    public static final EventListener.Factory FACTORY = new EventListener.Factory() { // from class: com.sinovatech.unicom.ui.OkhttpEventListener.1
        {
            JniLib.m15918cV(this, 328);
        }

        @Override // okhttp3.EventListener.Factory
        public EventListener create(Call call) {
            Object m15920cL = JniLib.m15920cL(this, call, 327);
            if (m15920cL == null) {
                return null;
            }
            return (EventListener) m15920cL;
        }
    };
    private static String TAG = "OkhttpEventListener";
    private final String callId;
    List<HttpCallBackListener> list;

    public OkhttpEventListener(String str, List<HttpCallBackListener> list) {
        this.callId = String.valueOf(str);
        this.list = list;
    }

    @Override // okhttp3.EventListener
    public void callStart(@NotNull Call call) {
        super.callStart(call);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).callStart(call, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void callFailed(@NotNull Call call, @NotNull IOException iOException) {
        super.callFailed(call, iOException);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).callFailed(call, iOException, this.callId);
                    this.list.get(i).callFinish(call, this.callId, "1", iOException.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void canceled(@NotNull Call call) {
        super.canceled(call);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).canceled(call, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void connectEnd(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).connectEnd(call, inetSocketAddress, proxy, protocol, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void connectFailed(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol, @NotNull IOException iOException) {
        super.connectFailed(call, inetSocketAddress, proxy, protocol, iOException);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).connectFailed(call, inetSocketAddress, proxy, protocol, iOException, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void connectStart(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy) {
        super.connectStart(call, inetSocketAddress, proxy);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).connectStart(call, inetSocketAddress, proxy, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void connectionAcquired(@NotNull Call call, @NotNull Connection connection) {
        super.connectionAcquired(call, connection);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).connectionAcquired(call, connection, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void connectionReleased(@NotNull Call call, @NotNull Connection connection) {
        super.connectionReleased(call, connection);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).connectionReleased(call, connection, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void dnsEnd(@NotNull Call call, @NotNull String str, @NotNull List<InetAddress> list) {
        super.dnsEnd(call, str, list);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).dnsEnd(call, str, list, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void dnsStart(@NotNull Call call, @NotNull String str) {
        super.dnsStart(call, str);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).dnsStart(call, str, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void proxySelectEnd(@NotNull Call call, @NotNull HttpUrl httpUrl, @NotNull List<Proxy> list) {
        super.proxySelectEnd(call, httpUrl, list);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).proxySelectEnd(call, httpUrl, list, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void proxySelectStart(@NotNull Call call, @NotNull HttpUrl httpUrl) {
        super.proxySelectStart(call, httpUrl);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).proxySelectStart(call, httpUrl, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void requestBodyEnd(@NotNull Call call, long j) {
        super.requestBodyEnd(call, j);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).requestBodyEnd(call, j, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void requestBodyStart(@NotNull Call call) {
        super.requestBodyStart(call);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).requestBodyStart(call, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void requestFailed(@NotNull Call call, @NotNull IOException iOException) {
        super.requestFailed(call, iOException);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).requestFailed(call, iOException, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void requestHeadersEnd(@NotNull Call call, @NotNull Request request) {
        super.requestHeadersEnd(call, request);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).requestHeadersEnd(call, request, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void requestHeadersStart(@NotNull Call call) {
        super.requestHeadersStart(call);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).requestHeadersStart(call, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void responseBodyEnd(@NotNull Call call, long j) {
        super.responseBodyEnd(call, j);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).responseBodyEnd(call, j, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void responseBodyStart(@NotNull Call call) {
        super.responseBodyStart(call);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).responseBodyStart(call, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void responseFailed(@NotNull Call call, @NotNull IOException iOException) {
        super.responseFailed(call, iOException);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).responseFailed(call, iOException, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void responseHeadersEnd(@NotNull Call call, @NotNull Response response) {
        super.responseHeadersEnd(call, response);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).responseHeadersEnd(call, response, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void responseHeadersStart(@NotNull Call call) {
        super.responseHeadersStart(call);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).responseHeadersStart(call, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void secureConnectEnd(@NotNull Call call, @Nullable Handshake handshake) {
        super.secureConnectEnd(call, handshake);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).secureConnectEnd(call, handshake, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void secureConnectStart(@NotNull Call call) {
        super.secureConnectStart(call);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).secureConnectStart(call, this.callId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // okhttp3.EventListener
    public void callEnd(@NotNull Call call) {
        super.callEnd(call);
        try {
            if (this.list != null && this.list.size() > 0) {
                for (int i = 0; i < this.list.size(); i++) {
                    this.list.get(i).callEnd(call, this.callId);
                    this.list.get(i).callFinish(call, this.callId, "0", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
