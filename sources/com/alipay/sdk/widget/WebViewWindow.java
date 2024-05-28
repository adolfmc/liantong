package com.alipay.sdk.widget;

import android.content.Context;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alipay.sdk.util.C2049k;
import com.alipay.sdk.util.C2052n;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WebViewWindow extends LinearLayout {

    /* renamed from: f */
    private static Handler f3912f = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    private ImageView f3913a;

    /* renamed from: b */
    private TextView f3914b;

    /* renamed from: c */
    private ImageView f3915c;

    /* renamed from: d */
    private ProgressBar f3916d;

    /* renamed from: e */
    private WebView f3917e;

    /* renamed from: g */
    private InterfaceC2055a f3918g;

    /* renamed from: h */
    private InterfaceC2056b f3919h;

    /* renamed from: i */
    private InterfaceC2057c f3920i;

    /* renamed from: j */
    private View.OnClickListener f3921j;

    /* renamed from: k */
    private final float f3922k;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.widget.WebViewWindow$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2055a {
        /* renamed from: a */
        void mo20601a(WebViewWindow webViewWindow, String str);

        /* renamed from: a */
        boolean mo20600a(WebViewWindow webViewWindow, String str, String str2, String str3, JsPromptResult jsPromptResult);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.widget.WebViewWindow$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2056b {
        /* renamed from: a */
        boolean mo20603a(WebViewWindow webViewWindow, int i, String str, String str2);

        /* renamed from: a */
        boolean mo20602a(WebViewWindow webViewWindow, SslErrorHandler sslErrorHandler, SslError sslError);

        /* renamed from: b */
        boolean mo20591b(WebViewWindow webViewWindow, String str);

        /* renamed from: c */
        boolean mo20586c(WebViewWindow webViewWindow, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.widget.WebViewWindow$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2057c {
        /* renamed from: a */
        void mo20604a(WebViewWindow webViewWindow);

        /* renamed from: b */
        void mo20592b(WebViewWindow webViewWindow);
    }

    public WebViewWindow(Context context) {
        this(context, null);
    }

    public WebViewWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3921j = new View$OnClickListenerC2076q(this);
        this.f3922k = context.getResources().getDisplayMetrics().density;
        setOrientation(1);
        m20637a(context);
        m20631b(context);
        m20629c(context);
    }

    /* renamed from: a */
    private void m20637a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-218103809);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        this.f3913a = new ImageView(context);
        this.f3913a.setOnClickListener(this.f3921j);
        this.f3913a.setScaleType(ImageView.ScaleType.CENTER);
        this.f3913a.setImageDrawable(C2049k.m20682a("iVBORw0KGgoAAAANSUhEUgAAAEgAAABIBAMAAACnw650AAAAFVBMVEUAAAARjusRkOkQjuoRkeoRj+oQjunya570AAAABnRSTlMAinWeSkk7CjRNAAAAZElEQVRIx+3MOw6AIBQF0YsrMDGx1obaLeGH/S9BQgkJ82rypp4ceTN1ilvyKizmZIAyU7FML0JVYig55BBAfQ2EU4V4CpZJ+2AiSj11C6rUoTannBpRn4W6xNQjLBSI2+TN0w/+3HT2wPClrQAAAABJRU5ErkJggg==", context));
        this.f3913a.setPadding(m20638a(12), 0, m20638a(12), 0);
        linearLayout.addView(this.f3913a, new LinearLayout.LayoutParams(-2, -2));
        View view = new View(context);
        view.setBackgroundColor(-2500135);
        linearLayout.addView(view, new LinearLayout.LayoutParams(m20638a(1), m20638a(25)));
        this.f3914b = new TextView(context);
        this.f3914b.setTextColor(-15658735);
        this.f3914b.setTextSize(17.0f);
        this.f3914b.setMaxLines(1);
        this.f3914b.setEllipsize(TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(m20638a(17), 0, 0, 0);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.f3914b, layoutParams);
        this.f3915c = new ImageView(context);
        this.f3915c.setOnClickListener(this.f3921j);
        this.f3915c.setScaleType(ImageView.ScaleType.CENTER);
        this.f3915c.setImageDrawable(C2049k.m20682a("iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAMAAABiM0N1AAAAmVBMVEUAAAARj+oQjuoRkOsVk/AQj+oRjuoQj+oSkO3///8Rj+kRj+oQkOsTk+whm/8Qj+oRj+oQj+oSkus2p/8QjuoQj+oQj+oQj+oQj+oRj+oTkuwRj+oQj+oRj+oRj+oSkOsSkO0ZlfMbk+8XnPgQj+oRj+oQj+oQj+sSj+sRkOoSkescqv8Rj+oQj+oSj+sXku4Rj+kQjuoQjumXGBCVAAAAMnRSTlMAxPtPF8ry7CoB9npbGwe6lm0wBODazb1+aSejm5GEYjcTDwvls6uJc0g/CdWfRCF20AXrk5QAAAJqSURBVFjD7ZfXmpswEIUFphmDCxi3talurGvm/R8uYSDe5FNBwlzsxf6XmvFBmiaZ/PCdWDk9CWn61OhHCMAaXfoRAth7wx6EkMXnWyrho4yg4bDpquI8Jy78Q7eoj9cmUFijsaLM0JsD9CD0uQAa9aNdPuCFvbA7B9t/Becap8Pu6Q/2jcyH81VHc/WCHDQZXwbvtUhQ61iDlqadncU6Rp31yGkZIzOAu7AjtPpYGREzq/pY5DRFHS1siyO6HfkOKTrMjdb2qevV4zosK7MbkFY2LmYk55hL6juCIFWMOI2KGzblmho3b18EIbxL1hs6r5m2Q2WaEElwS3NW4xh6ZZJuzTtUsBKT4G0h35s4y1mNgkNoS6TZ8SKBXTZQGBNYdPTozXGYKoyLAmOasttjThT4xT6Ch+2qIjRhV9Ja3NC87Kyo5We1vCNEMW1T+j1VLZ9UhE54Q1DL52r5piJ0YxdegvWlHOwTu76uKkJX+MOTHno4YFSEbHYdhViojsLrCTg/MKnhKWaEYzvkZFM8aOkPH7iTSvoFZKD7jGEJbarkRaxQyOeWvGVIbsji152jK7TbDgRzcIuz7SGj89BFU8d30TqWeDtrILxyTkD1IXfvmHseuU3lVHDz607bw0f3xDqejm5ncd0j8VDwfoibRy8RcgTkWHBvocbDbMlJsQAkGnAOHwGy90kLmQY1Wkob07/GaCNRIzdoWK7/+6y/XkLDJCcynOGFuUrKIMuCMonNr9VpSOQoIxBgJ0SacGbzZNy4ICrkscvU2fpElYz+U3sd+aQThjfVmjNa5i15kLcojM3Gz8kP34jf4VaV3X55gNEAAAAASUVORK5CYII=", context));
        this.f3915c.setPadding(m20638a(12), 0, m20638a(12), 0);
        linearLayout.addView(this.f3915c, new LinearLayout.LayoutParams(-2, -2));
        addView(linearLayout, new LinearLayout.LayoutParams(-1, m20638a(48)));
    }

    /* renamed from: b */
    private void m20631b(Context context) {
        this.f3916d = new ProgressBar(context, null, 16973855);
        this.f3916d.setProgressDrawable(context.getResources().getDrawable(17301612));
        this.f3916d.setMax(100);
        this.f3916d.setBackgroundColor(-218103809);
        addView(this.f3916d, new LinearLayout.LayoutParams(-1, m20638a(2)));
    }

    /* renamed from: c */
    private void m20629c(Context context) {
        this.f3917e = new WebView(context);
        this.f3917e.setVerticalScrollbarOverlay(true);
        m20636a(this.f3917e, context);
        WebSettings settings = this.f3917e.getSettings();
        settings.setUseWideViewPort(true);
        settings.setAppCacheMaxSize(5242880L);
        settings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(-1);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        try {
            this.f3917e.removeJavascriptInterface("searchBoxJavaBridge_");
            this.f3917e.removeJavascriptInterface("accessibility");
            this.f3917e.removeJavascriptInterface("accessibilityTraversal");
        } catch (Exception unused) {
        }
        addView(this.f3917e, new LinearLayout.LayoutParams(-1, -1));
    }

    /* renamed from: a */
    protected void m20636a(WebView webView, Context context) {
        String userAgentString = webView.getSettings().getUserAgentString();
        String packageName = context.getPackageName();
        String m20643h = C2052n.m20643h(context);
        webView.getSettings().setUserAgentString(userAgentString + " AlipaySDK(" + packageName + "/" + m20643h + "/15.6.8)");
    }

    public void setChromeProxy(InterfaceC2055a interfaceC2055a) {
        this.f3918g = interfaceC2055a;
        if (interfaceC2055a == null) {
            this.f3917e.setWebChromeClient(null);
        } else {
            this.f3917e.setWebChromeClient(new C2078s(this));
        }
    }

    public void setWebClientProxy(InterfaceC2056b interfaceC2056b) {
        this.f3919h = interfaceC2056b;
        if (interfaceC2056b == null) {
            WebView webView = this.f3917e;
            if (webView instanceof WebView) {
                NBSWebLoadInstrument.setWebViewClient(webView, null);
                return;
            } else {
                webView.setWebViewClient(null);
                return;
            }
        }
        WebView webView2 = this.f3917e;
        C2079t c2079t = new C2079t(this);
        if (webView2 instanceof WebView) {
            NBSWebLoadInstrument.setWebViewClient(webView2, c2079t);
        } else {
            webView2.setWebViewClient(c2079t);
        }
    }

    public void setWebEventProxy(InterfaceC2057c interfaceC2057c) {
        this.f3920i = interfaceC2057c;
    }

    public String getUrl() {
        return this.f3917e.getUrl();
    }

    /* renamed from: a */
    public void m20634a(String str) {
        WebView webView = this.f3917e;
        if (webView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView, str);
        } else {
            webView.loadUrl(str);
        }
    }

    /* renamed from: a */
    public void m20633a(String str, byte[] bArr) {
        this.f3917e.postUrl(str, bArr);
    }

    public ImageView getBackButton() {
        return this.f3913a;
    }

    public TextView getTitle() {
        return this.f3914b;
    }

    public ImageView getRefreshButton() {
        return this.f3915c;
    }

    public ProgressBar getProgressbar() {
        return this.f3916d;
    }

    public WebView getWebView() {
        return this.f3917e;
    }

    /* renamed from: a */
    public void m20639a() {
        removeAllViews();
        this.f3917e.removeAllViews();
        WebView webView = this.f3917e;
        if (webView instanceof WebView) {
            NBSWebLoadInstrument.setWebViewClient(webView, null);
        } else {
            webView.setWebViewClient(null);
        }
        this.f3917e.setWebChromeClient(null);
        this.f3917e.destroy();
    }

    /* renamed from: a */
    private int m20638a(int i) {
        return (int) (i * this.f3922k);
    }
}
