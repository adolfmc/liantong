package com.baidu.rtc.internal;

import com.baidu.rtc.BaiduRtcInterface;
import com.baidu.rtc.BaiduRtcLastmile;
import com.baidu.rtc.KcpClient;
import com.baidu.rtc.internal.BaiduRtcLastmileImp;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.webrtc.Logging;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaiduRtcLastmileImp extends BaiduRtcLastmile implements BaiduRtcInterface {
    private static final String TAG = "BaiduRtcLastmileImp";
    static OkHttpClient lastmileHttpClient = null;
    private static String sPlatformUrl = "https://rtc-ss.su.baidubce.com";
    private String mAppId;
    private BaiduRtcLastmile.BaiduRtcLastmileDelegate mBaiduRtcLastmileDelegate;
    private String mTokenStr;
    private final Object threadLock = new Object();
    private KcpClient mKcpClient = null;
    private boolean mStoppedTest = false;
    private boolean mStartedTest = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface LastmileSink {
        void startLastmile(BaiduRtcInterface baiduRtcInterface, String str, int i, long j);
    }

    public BaiduRtcLastmileImp(String str, String str2, BaiduRtcLastmile.BaiduRtcLastmileDelegate baiduRtcLastmileDelegate) {
        this.mAppId = str;
        this.mTokenStr = str2;
        this.mBaiduRtcLastmileDelegate = baiduRtcLastmileDelegate;
    }

    public static void setServerUrl(String str) {
        if (str.length() > 0) {
            sPlatformUrl = str;
        }
    }

    public synchronized void startProbeTest(int i, int i2) {
        if (this.mStartedTest) {
            return;
        }
        this.mStartedTest = true;
        new C32581(i, i2).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.rtc.internal.BaiduRtcLastmileImp$1 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C32581 extends Thread {
        final /* synthetic */ int val$expectedDownlinkBitrate;
        final /* synthetic */ int val$expectedUplinkBitrate;

        C32581(int i, int i2) {
            this.val$expectedUplinkBitrate = i;
            this.val$expectedDownlinkBitrate = i2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (BaiduRtcLastmileImp.this.mStoppedTest) {
                return;
            }
            try {
                final int i = this.val$expectedUplinkBitrate;
                final int i2 = this.val$expectedDownlinkBitrate;
                BaiduRtcLastmileImp.this.requestLastmileConfig(BaiduRtcLastmileImp.sPlatformUrl + "/brtc/client/mediaserver/ip", new LastmileSink() { // from class: com.baidu.rtc.internal.-$$Lambda$BaiduRtcLastmileImp$1$DEuhC5fwZFguWNxsDElHq_mdLjE
                    @Override // com.baidu.rtc.internal.BaiduRtcLastmileImp.LastmileSink
                    public final void startLastmile(BaiduRtcInterface baiduRtcInterface, String str, int i3, long j) {
                        BaiduRtcLastmileImp.C32581.lambda$run$0(BaiduRtcLastmileImp.C32581.this, i, i2, baiduRtcInterface, str, i3, j);
                    }
                });
            } catch (Exception e) {
                Logging.m5305d("BaiduRtcLastmileImp", "lastmile request exception");
                e.printStackTrace();
            }
        }

        public static /* synthetic */ void lambda$run$0(C32581 c32581, int i, int i2, BaiduRtcInterface baiduRtcInterface, String str, int i3, long j) {
            if (BaiduRtcLastmileImp.this.mStoppedTest) {
                return;
            }
            synchronized (BaiduRtcLastmileImp.this.threadLock) {
                if (BaiduRtcLastmileImp.this.mKcpClient == null) {
                    BaiduRtcLastmileImp.this.mKcpClient = new KcpClient(baiduRtcInterface);
                    BaiduRtcLastmileImp.this.mKcpClient.startLastmile(str, i3, i / 1000, i2 / 1000, j);
                }
            }
        }
    }

    static {
        OkHttpClient.Builder readTimeout = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS);
        lastmileHttpClient = !(readTimeout instanceof OkHttpClient.Builder) ? readTimeout.build() : NBSOkHttp3Instrumentation.builderInit(readTimeout);
    }

    public void requestLastmileConfig(String str, LastmileSink lastmileSink) {
        try {
            String str2 = str + "?appId=" + this.mAppId;
            if (this.mTokenStr.length() > 0) {
                str2 = str2 + "&token=" + this.mTokenStr;
            }
            String str3 = str2 + "&kcpturn=false";
            Request build = new Request.Builder().url(str3).build();
            Logging.m5305d("BaiduRtcLastmileImp", "lastmile request url:" + str3);
            try {
                Response execute = lastmileHttpClient.newCall(build).execute();
                String string = execute.body().string();
                Logging.m5305d("BaiduRtcLastmileImp", "code:" + execute.code() + " resp:" + string);
                if (execute.code() != 200) {
                    stopLastmileWithException();
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    String optString = jSONObject.optJSONObject("bestMediaServerIpInfo").optString("ip");
                    int optInt = jSONObject.optInt("mediaServerPort");
                    long optLong = jSONObject.optLong("udpToken");
                    Logging.m5305d("BaiduRtcLastmileImp", "lastmile ip:" + optString + " port:" + optInt);
                    lastmileSink.startLastmile(this, optString, optInt, optLong);
                } catch (JSONException e) {
                    Logging.m5305d("BaiduRtcLastmileImp", "lastmile json exception");
                    stopLastmileWithException();
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                Logging.m5305d("BaiduRtcLastmileImp", "lastmile io exception");
                stopLastmileWithException();
                e2.printStackTrace();
            }
        } catch (Exception e3) {
            Logging.m5305d("BaiduRtcLastmileImp", "lastmile http exception");
            stopLastmileWithException();
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.baidu.rtc.internal.BaiduRtcLastmileImp$2] */
    @Override // com.baidu.rtc.BaiduRtcLastmile
    public synchronized void stopProbeTest() {
        if (this.mStoppedTest) {
            return;
        }
        boolean z = true;
        this.mStoppedTest = true;
        StringBuilder sb = new StringBuilder();
        sb.append("lastmile stop test ");
        if (this.mKcpClient == null) {
            z = false;
        }
        sb.append(z);
        Logging.m5305d("BaiduRtcLastmileImp", sb.toString());
        if (this.mKcpClient != null) {
            final KcpClient kcpClient = this.mKcpClient;
            this.mKcpClient = null;
            new Thread() { // from class: com.baidu.rtc.internal.BaiduRtcLastmileImp.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    synchronized (BaiduRtcLastmileImp.this.threadLock) {
                        kcpClient.stopLastmile();
                    }
                }
            }.start();
        }
    }

    private void stopLastmileWithException() {
        BaiduRtcLastmile.LastmileProbeResult lastmileProbeResult = new BaiduRtcLastmile.LastmileProbeResult();
        lastmileProbeResult.state = (short) BaiduRtcLastmile.RtcLastmileState.PROBE_RESULT_UNAVAILABLE.ordinal();
        lastmileProbeResult.rtt = 0;
        lastmileProbeResult.quality = 0;
        onLastmileProbeResult(lastmileProbeResult);
    }

    @Override // com.baidu.rtc.BaiduRtcInterface
    public void onEvent(int i, byte[] bArr) {
        if (i == 102) {
            KcpClient.PLastmileResult pLastmileResult = new KcpClient.PLastmileResult();
            pLastmileResult.unmarshall(bArr);
            BaiduRtcLastmile.LastmileProbeResult lastmileProbeResult = new BaiduRtcLastmile.LastmileProbeResult();
            lastmileProbeResult.state = pLastmileResult.state;
            lastmileProbeResult.rtt = pLastmileResult.rtt;
            lastmileProbeResult.quality = pLastmileResult.quality;
            lastmileProbeResult.uplinkReport.lossRate = pLastmileResult.uplinkReport.packetLossRate;
            lastmileProbeResult.uplinkReport.jitter = pLastmileResult.uplinkReport.jitter;
            lastmileProbeResult.uplinkReport.bandwidth = pLastmileResult.uplinkReport.availableBandwidth;
            lastmileProbeResult.downlinkReport.lossRate = pLastmileResult.downlinkReport.packetLossRate;
            lastmileProbeResult.downlinkReport.jitter = pLastmileResult.downlinkReport.jitter;
            lastmileProbeResult.downlinkReport.bandwidth = pLastmileResult.downlinkReport.availableBandwidth;
            onLastmileProbeResult(lastmileProbeResult);
        }
    }

    public void onLastmileProbeResult(BaiduRtcLastmile.LastmileProbeResult lastmileProbeResult) {
        BaiduRtcLastmile.BaiduRtcLastmileDelegate baiduRtcLastmileDelegate = this.mBaiduRtcLastmileDelegate;
        if (baiduRtcLastmileDelegate != null) {
            baiduRtcLastmileDelegate.onLastmileProbeResult(lastmileProbeResult);
        }
        stopProbeTest();
    }
}
