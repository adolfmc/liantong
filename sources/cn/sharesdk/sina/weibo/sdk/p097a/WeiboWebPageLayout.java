package cn.sharesdk.sina.weibo.sdk.p097a;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.WebViewUtils;
import cn.sharesdk.sina.weibo.sdk.C1809a;
import cn.sharesdk.sina.weibo.sdk.LoadingBar;
import com.mob.tools.utils.ResHelper;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.sina.weibo.sdk.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeiboWebPageLayout {

    /* renamed from: a */
    private TextView f3014a;

    /* renamed from: b */
    private TextView f3015b;

    /* renamed from: c */
    private WebView f3016c;

    /* renamed from: d */
    private LoadingBar f3017d;

    /* renamed from: e */
    private LinearLayout f3018e;

    /* renamed from: f */
    private TextView f3019f;

    /* renamed from: g */
    private Button f3020g;

    /* renamed from: h */
    private Context f3021h;

    public WeiboWebPageLayout(Context context) {
        this.f3021h = context;
    }

    /* renamed from: a */
    public RelativeLayout m21609a(int i) {
        RelativeLayout relativeLayout = new RelativeLayout(this.f3021h);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(-1);
        RelativeLayout relativeLayout2 = new RelativeLayout(this.f3021h);
        relativeLayout2.setId(ResHelper.getIdRes(this.f3021h, "ssdk_sina_web_title_id"));
        relativeLayout2.setBackgroundColor(-131587);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, ResHelper.dipToPx(this.f3021h, 55));
        layoutParams.addRule(10);
        relativeLayout.addView(relativeLayout2, layoutParams);
        this.f3014a = new TextView(this.f3021h);
        this.f3014a.setTextSize(1, 17.0f);
        this.f3014a.setTextColor(C1809a.m21613a(-32256, 1728020992));
        int stringRes = ResHelper.getStringRes(this.f3021h, "ssdk_sina_web_close");
        if (stringRes > 0) {
            this.f3014a.setText(stringRes);
        }
        int dipToPx = ResHelper.dipToPx(this.f3021h, 10);
        this.f3014a.setPadding(dipToPx, 0, dipToPx, 0);
        this.f3014a.setOnTouchListener(new View.OnTouchListener() { // from class: cn.sharesdk.sina.weibo.sdk.a.a.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    view.setBackgroundColor(-1);
                    return false;
                } else if (motionEvent.getAction() == 1) {
                    view.setBackgroundColor(-131587);
                    return false;
                } else {
                    return false;
                }
            }
        });
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        relativeLayout2.addView(this.f3014a, layoutParams2);
        this.f3015b = new TextView(this.f3021h);
        this.f3015b.setTextColor(-11382190);
        this.f3015b.setTextSize(1, 18.0f);
        if (i > 0) {
            this.f3015b.setText(i);
        }
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13);
        relativeLayout2.addView(this.f3015b, layoutParams3);
        this.f3016c = new WebView(this.f3021h);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams4.addRule(3, relativeLayout2.getId());
        relativeLayout.addView(this.f3016c, layoutParams4);
        this.f3017d = new LoadingBar(this.f3021h);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, ResHelper.dipToPx(this.f3021h, 3));
        layoutParams5.addRule(3, relativeLayout2.getId());
        relativeLayout.addView(this.f3017d, layoutParams5);
        View view = new View(this.f3021h);
        int bitmapRes = ResHelper.getBitmapRes(this.f3021h, "ssdk_weibo_common_shadow_top");
        if (bitmapRes > 0) {
            view.setBackgroundResource(bitmapRes);
        }
        relativeLayout.addView(view, layoutParams5);
        this.f3018e = new LinearLayout(this.f3021h);
        this.f3018e.setVisibility(8);
        this.f3018e.setGravity(17);
        this.f3018e.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams6.addRule(13);
        relativeLayout.addView(this.f3018e, layoutParams6);
        ImageView imageView = new ImageView(this.f3021h);
        int bitmapRes2 = ResHelper.getBitmapRes(this.f3021h, "ssdk_weibo_empty_failed");
        if (bitmapRes2 > 0) {
            imageView.setImageResource(bitmapRes2);
        }
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams7.bottomMargin = ResHelper.dipToPx(this.f3021h, 8);
        this.f3018e.addView(imageView, layoutParams7);
        this.f3019f = new TextView(this.f3021h);
        int stringRes2 = ResHelper.getStringRes(this.f3021h, "ssdk_sina_web_net_error");
        if (stringRes2 > 0) {
            this.f3019f.setText(stringRes2);
        }
        this.f3019f.setTextColor(-4342339);
        this.f3019f.setTextSize(1, 14.0f);
        this.f3018e.addView(this.f3019f, new LinearLayout.LayoutParams(-2, -2));
        this.f3020g = new Button(this.f3021h);
        this.f3020g.setTextColor(-8882056);
        this.f3020g.setGravity(17);
        this.f3020g.setTextSize(1, 16.0f);
        int stringRes3 = ResHelper.getStringRes(this.f3021h, "ssdk_sina_web_refresh");
        if (stringRes3 > 0) {
            this.f3020g.setText(stringRes3);
        }
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(ResHelper.dipToPx(this.f3021h, 142), ResHelper.dipToPx(this.f3021h, 46));
        layoutParams8.topMargin = dipToPx;
        this.f3018e.addView(this.f3020g, layoutParams8);
        m21604e();
        return relativeLayout;
    }

    /* renamed from: e */
    private void m21604e() {
        this.f3016c.getSettings().setJavaScriptEnabled(true);
        WebViewUtils.m21666a(this.f3016c, false);
        this.f3016c.getSettings().setSavePassword(false);
        this.f3016c.getSettings().setUserAgentString(Build.MANUFACTURER + "-" + Build.MODEL + "_" + Build.VERSION.RELEASE + "_weibosdk_0031405000_android");
        this.f3016c.requestFocus();
        this.f3016c.setScrollBarStyle(0);
        if (Build.VERSION.SDK_INT > 10 && Build.VERSION.SDK_INT < 17) {
            try {
                Method method = this.f3016c.getClass().getMethod("removeJavascriptInterface", String.class);
                method.setAccessible(true);
                method.invoke(this.f3016c, "searchBoxJavaBridge_");
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
            }
        }
        this.f3016c.setWebChromeClient(new WebChromeClient() { // from class: cn.sharesdk.sina.weibo.sdk.a.a.2
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                WeiboWebPageLayout.this.f3017d.m21618a(i);
                if (i == 100) {
                    WeiboWebPageLayout.this.f3017d.setVisibility(4);
                } else {
                    WeiboWebPageLayout.this.f3017d.setVisibility(0);
                }
            }
        });
    }

    /* renamed from: a */
    public Button m21610a() {
        return this.f3020g;
    }

    /* renamed from: b */
    public TextView m21607b() {
        return this.f3014a;
    }

    /* renamed from: c */
    public WebView m21606c() {
        return this.f3016c;
    }

    /* renamed from: d */
    public LinearLayout m21605d() {
        return this.f3018e;
    }
}
