package com.sinovatech.unicom.separatemodule.tongyicaiji;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.HttpCallBackListener;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.NetCollectTempEntitiy;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TYCJNetParseUtils implements HttpCallBackListener {
    private static String TAG = "TYCJNetParseUtils";
    private static TYCJNetParseUtils tycjNetParseUtils;
    private Map<String, NetCollectTempEntitiy> tycjNetEntityMap;

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void callEnd(Call call, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void callFailed(@NotNull Call call, @NotNull IOException iOException, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void canceled(Call call, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void connectionAcquired(@NotNull Call call, @NotNull Connection connection, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void connectionReleased(@NotNull Call call, @NotNull Connection connection, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void proxySelectEnd(@NotNull Call call, @NotNull HttpUrl httpUrl, @NotNull List<Proxy> list, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void proxySelectStart(@NotNull Call call, @NotNull HttpUrl httpUrl, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void requestBodyStart(@NotNull Call call, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void requestFailed(@NotNull Call call, @NotNull IOException iOException, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void requestHeadersEnd(@NotNull Call call, @NotNull Request request, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void requestHeadersStart(@NotNull Call call, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void responseBodyStart(@NotNull Call call, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void responseFailed(@NotNull Call call, @NotNull IOException iOException, String str) {
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void responseHeadersStart(Call call, String str) {
    }

    private TYCJNetParseUtils() {
    }

    public static TYCJNetParseUtils getInstance() {
        if (tycjNetParseUtils == null) {
            synchronized (TYCJNetParseUtils.class) {
                if (tycjNetParseUtils == null) {
                    tycjNetParseUtils = new TYCJNetParseUtils();
                }
            }
        }
        return tycjNetParseUtils;
    }

    public void init() {
        String str = TAG;
        MsLogUtil.m7979d(str, TYCJConfigUtil.isOpen("netLoading") + "");
        if (TYCJConfigUtil.isOpen("netLoading")) {
            App.addOkHttpLister(this);
            this.tycjNetEntityMap = new HashMap();
            return;
        }
        App.removeOkHttpLister(this);
        if (this.tycjNetEntityMap != null) {
            this.tycjNetEntityMap = null;
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void callStart(Call call, String str) {
        try {
            if (initData(str)) {
                this.tycjNetEntityMap.get(str).setExecute_time(SystemTimeUtil.currentTimeMillis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void connectEnd(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol, String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (initData(str)) {
                this.tycjNetEntityMap.get(str).setTcpEnd(currentTimeMillis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void connectFailed(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol, @NotNull IOException iOException, String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (initData(str)) {
                this.tycjNetEntityMap.get(str).setTcpEnd(currentTimeMillis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void connectStart(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (initData(str)) {
                this.tycjNetEntityMap.get(str).setTcpStart(currentTimeMillis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void dnsEnd(@NotNull Call call, @NotNull String str, @NotNull List<InetAddress> list, String str2) {
        try {
            if (initData(str2)) {
                this.tycjNetEntityMap.get(str2).setDnsEnd(System.currentTimeMillis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void dnsStart(@NotNull Call call, @NotNull String str, String str2) {
        try {
            if (initData(str2)) {
                this.tycjNetEntityMap.get(str2).setDnsStart(System.currentTimeMillis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void requestBodyEnd(@NotNull Call call, long j, String str) {
        try {
            if (initData(str)) {
                this.tycjNetEntityMap.get(str).setSend(j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void responseBodyEnd(@NotNull Call call, long j, String str) {
        try {
            if (initData(str)) {
                this.tycjNetEntityMap.get(str).setReveive(j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void responseHeadersEnd(@NotNull Call call, @NotNull Response response, String str) {
        try {
            int code = response.code();
            if (initData(str)) {
                this.tycjNetEntityMap.get(str).setCode(String.valueOf(code));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void secureConnectEnd(@NotNull Call call, @Nullable Handshake handshake, String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (initData(str)) {
                this.tycjNetEntityMap.get(str).setSslEnd(currentTimeMillis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void secureConnectStart(@NotNull Call call, String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (initData(str)) {
                this.tycjNetEntityMap.get(str).setSslStart(currentTimeMillis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.HttpCallBackListener
    public void callFinish(Call call, String str, String str2, String str3) {
        try {
            Request request = call.request();
            String httpUrl = request.url().toString();
            if (initData(str) && TYCJConfigUtil.isOpenAndWhiteUrl("netLoading", httpUrl, "") && !httpUrl.equals(TYCJConfigUtil.getUpLoadUrl())) {
                String method = request.method();
                long byteCount = this.tycjNetEntityMap.get(str).getByteCount();
                long reveive = this.tycjNetEntityMap.get(str).getReveive();
                long execute_time = this.tycjNetEntityMap.get(str).getExecute_time();
                String requestBody = getRequestBody(request);
                long dnsEnd = this.tycjNetEntityMap.get(str).getDnsEnd() - this.tycjNetEntityMap.get(str).getDnsStart();
                String valueOf = dnsEnd > 0 ? String.valueOf(dnsEnd) : "-";
                long tcpEnd = this.tycjNetEntityMap.get(str).getTcpEnd() - this.tycjNetEntityMap.get(str).getTcpStart();
                String valueOf2 = tcpEnd > 0 ? String.valueOf(tcpEnd) : "-";
                long sslEnd = this.tycjNetEntityMap.get(str).getSslEnd() - this.tycjNetEntityMap.get(str).getSslStart();
                getNetWorkData(httpUrl, byteCount, reveive, System.currentTimeMillis() - execute_time, execute_time, str2, this.tycjNetEntityMap.get(str).getCode(), requestBody, method, str, valueOf, valueOf2, sslEnd > 0 ? String.valueOf(sslEnd) : "-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRequestBody(Request request) {
        try {
            RequestBody body = request.body();
            Buffer buffer = new Buffer();
            if (body == null) {
                return "";
            }
            body.writeTo(buffer);
            Charset charset = StandardCharsets.UTF_8;
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(StandardCharsets.UTF_8);
            }
            return buffer.readString(charset);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean initData(String str) {
        Map<String, NetCollectTempEntitiy> map = this.tycjNetEntityMap;
        if (map == null) {
            return false;
        }
        if (map.containsKey(str)) {
            return true;
        }
        this.tycjNetEntityMap.put(str, new NetCollectTempEntitiy());
        return true;
    }

    private void getNetWorkData(String str, long j, long j2, long j3, long j4, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        String str10;
        String[] split;
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            String str11 = "";
            if (str.contains("?")) {
                str10 = str.split("\\?")[0];
                str11 = "?" + split[1];
                MsLogUtil.m7979d(TAG, "url1==" + str10);
                MsLogUtil.m7979d(TAG, "url2==" + str11);
            } else {
                str10 = str;
            }
            String str12 = str2.equals("0") ? "" : str4;
            arrayList.add(DeviceHelper.getDeviceID(false));
            String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
            arrayList.add((TextUtils.isEmpty(currentPhoneNumber) || currentPhoneNumber.equals("0")) ? "-" : "-");
            arrayList.add(App.getPvLogSessionId());
            arrayList.add(str11);
            arrayList.add(String.valueOf(j));
            arrayList.add(String.valueOf(j2));
            arrayList.add(String.valueOf(j3));
            arrayList.add(String.valueOf(j4));
            arrayList.add(str2);
            arrayList.add(str3);
            arrayList.add(str12);
            arrayList.add(str5);
            arrayList.add(str6);
            arrayList.add(str7);
            arrayList.add(str8);
            arrayList.add(str9);
            arrayList.add(App.getInstance().getVersion());
            TYInsertDataManager.getInstance().insertNetData(str, str2);
            TYCJBoxManager.getInstance().collectNetInfo("netLoading", str10, arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
