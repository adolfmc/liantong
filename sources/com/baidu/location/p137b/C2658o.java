package com.baidu.location.p137b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2658o {

    /* renamed from: j */
    private static long f5329j = 12000;

    /* renamed from: a */
    public C2664e f5330a;

    /* renamed from: b */
    private Context f5331b;

    /* renamed from: c */
    private WebView f5332c;

    /* renamed from: d */
    private LocationClient f5333d;

    /* renamed from: e */
    private HandlerC2660a f5334e;

    /* renamed from: f */
    private List<C2661b> f5335f;

    /* renamed from: g */
    private boolean f5336g;

    /* renamed from: h */
    private long f5337h;

    /* renamed from: i */
    private BDLocation f5338i;

    /* renamed from: k */
    private RunnableC2665f f5339k;

    /* renamed from: l */
    private boolean f5340l;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.baidu.location.b.o$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class HandlerC2660a extends Handler {
        HandlerC2660a(Looper looper) {
            super(looper);
        }

        /* renamed from: a */
        private String m19394a(BDLocation bDLocation) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("latitude", bDLocation.getLatitude());
                jSONObject.put("longitude", bDLocation.getLongitude());
                jSONObject.put("radius", bDLocation.getRadius());
                jSONObject.put("errorcode", 1);
                if (bDLocation.hasAltitude()) {
                    jSONObject.put("altitude", bDLocation.getAltitude());
                }
                if (bDLocation.hasSpeed()) {
                    jSONObject.put("speed", bDLocation.getSpeed() / 3.6f);
                }
                if (bDLocation.getLocType() == 61) {
                    jSONObject.put("direction", bDLocation.getDirection());
                }
                if (bDLocation.getBuildingName() != null) {
                    jSONObject.put("buildingname", bDLocation.getBuildingName());
                }
                if (bDLocation.getBuildingID() != null) {
                    jSONObject.put("buildingid", bDLocation.getBuildingID());
                }
                if (bDLocation.getFloor() != null) {
                    jSONObject.put("floor", bDLocation.getFloor());
                }
                return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            } catch (Exception unused) {
                return null;
            }
        }

        /* renamed from: a */
        private void m19393a(String str) {
            C2661b c2661b;
            if (C2658o.this.f5340l) {
                C2658o.this.f5334e.removeCallbacks(C2658o.this.f5339k);
                C2658o.this.f5340l = false;
            }
            if (C2658o.this.f5335f == null || C2658o.this.f5335f.size() <= 0) {
                return;
            }
            Iterator it = C2658o.this.f5335f.iterator();
            while (it.hasNext()) {
                try {
                    if (((C2661b) it.next()).m19391b() != null) {
                        String str2 = "javascript:" + c2661b.m19391b() + "('" + str + "')";
                        WebView webView = C2658o.this.f5332c;
                        if (webView instanceof Object) {
                            NBSWebLoadInstrument.loadUrl((Object) webView, str2);
                        } else {
                            webView.loadUrl(str2);
                        }
                    }
                    it.remove();
                } catch (Exception unused) {
                    return;
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:48:0x013d  */
        /* JADX WARN: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r9) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2658o.HandlerC2660a.handleMessage(android.os.Message):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.o$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2661b {

        /* renamed from: b */
        private String f5343b;

        /* renamed from: c */
        private String f5344c;

        /* renamed from: d */
        private long f5345d;

        C2661b(String str) {
            this.f5343b = null;
            this.f5344c = null;
            this.f5345d = 0L;
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("action")) {
                    this.f5343b = jSONObject.getString("action");
                }
                if (jSONObject.has("callback")) {
                    this.f5344c = jSONObject.getString("callback");
                }
                if (jSONObject.has("timeout")) {
                    long j = jSONObject.getLong("timeout");
                    if (j >= 1000) {
                        long unused = C2658o.f5329j = j;
                    }
                }
                this.f5345d = System.currentTimeMillis();
            } catch (Exception unused2) {
                this.f5343b = null;
                this.f5344c = null;
            }
        }

        /* renamed from: a */
        public String m19392a() {
            return this.f5343b;
        }

        /* renamed from: b */
        public String m19391b() {
            return this.f5344c;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.o$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static final class C2662c {

        /* renamed from: a */
        private static final C2658o f5346a = new C2658o();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.o$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2663d {
        private C2663d() {
        }

        @JavascriptInterface
        public void sendMessage(String str) {
            if (str == null || !C2658o.this.f5336g) {
                return;
            }
            C2661b c2661b = new C2661b(str);
            if (c2661b.m19392a() == null || !c2661b.m19392a().equals("requestLoc") || C2658o.this.f5334e == null) {
                return;
            }
            Message obtainMessage = C2658o.this.f5334e.obtainMessage(1);
            obtainMessage.obj = c2661b;
            obtainMessage.sendToTarget();
        }

        @JavascriptInterface
        public void showLog(String str) {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.o$e */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2664e extends BDAbstractLocationListener {
        public C2664e() {
        }

        @Override // com.baidu.location.BDAbstractLocationListener
        public void onReceiveLocation(BDLocation bDLocation) {
            Message obtainMessage;
            String str;
            if (!C2658o.this.f5336g || bDLocation == null) {
                return;
            }
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            int locType = bDLocation2.getLocType();
            String coorType = bDLocation2.getCoorType();
            if (locType == 61 || locType == 161 || locType == 66) {
                if (coorType != null) {
                    if (coorType.equals("gcj02")) {
                        bDLocation2 = LocationClient.getBDLocationInCoorType(bDLocation2, "gcj2wgs");
                    } else {
                        if (coorType.equals("bd09")) {
                            str = "bd092gcj";
                        } else {
                            str = coorType.equals("bd09ll") ? "bd09ll2gcj" : "bd09ll2gcj";
                        }
                        bDLocation2 = LocationClient.getBDLocationInCoorType(LocationClient.getBDLocationInCoorType(bDLocation2, str), "gcj2wgs");
                    }
                }
                C2658o.this.f5337h = System.currentTimeMillis();
                C2658o.this.f5338i = new BDLocation(bDLocation2);
                obtainMessage = C2658o.this.f5334e.obtainMessage(2);
                obtainMessage.obj = bDLocation2;
            } else {
                obtainMessage = C2658o.this.f5334e.obtainMessage(5);
            }
            obtainMessage.sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.o$f */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class RunnableC2665f implements Runnable {
        private RunnableC2665f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C2658o.this.f5340l = false;
            C2658o.this.f5334e.obtainMessage(6).sendToTarget();
        }
    }

    private C2658o() {
        this.f5331b = null;
        this.f5333d = null;
        this.f5330a = new C2664e();
        this.f5334e = null;
        this.f5335f = null;
        this.f5336g = false;
        this.f5337h = 0L;
        this.f5338i = null;
        this.f5339k = null;
        this.f5340l = false;
    }

    /* renamed from: a */
    public static C2658o m19414a() {
        return C2662c.f5346a;
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    /* renamed from: a */
    private void m19411a(WebView webView) {
        webView.addJavascriptInterface(new C2663d(), "BaiduLocAssistant");
    }

    /* renamed from: a */
    public void m19412a(Context context, WebView webView, LocationClient locationClient) {
        if (!this.f5336g && Integer.valueOf(Build.VERSION.SDK_INT).intValue() >= 17) {
            this.f5331b = context;
            this.f5332c = webView;
            this.f5333d = locationClient;
            this.f5334e = new HandlerC2660a(Looper.getMainLooper());
            this.f5334e.obtainMessage(3).sendToTarget();
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setAllowFileAccess(false);
            settings.setSavePassword(false);
            this.f5332c.removeJavascriptInterface("searchBoxJavaBridge_");
            this.f5332c.removeJavascriptInterface("accessibility");
            this.f5332c.removeJavascriptInterface("accessibilityTraversal");
            m19411a(this.f5332c);
            this.f5336g = true;
        }
    }

    /* renamed from: b */
    public void m19404b() {
        if (this.f5336g) {
            this.f5334e.obtainMessage(4).sendToTarget();
            this.f5336g = false;
        }
    }
}
