package cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.WebViewUtils;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RegisterView extends ResizeLayout {

    /* renamed from: a */
    private TitleLayout f2843a;

    /* renamed from: b */
    private RelativeLayout f2844b;

    /* renamed from: c */
    private WebView f2845c;

    /* renamed from: d */
    private TextView f2846d;

    public RegisterView(Context context) {
        super(context);
        m21884a(context);
    }

    public RegisterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m21884a(context);
    }

    /* renamed from: a */
    private void m21884a(Context context) {
        setBackgroundColor(-1);
        setOrientation(1);
        final int m21880b = m21880b(context);
        this.f2843a = new TitleLayout(context);
        try {
            int bitmapRes = ResHelper.getBitmapRes(context, "ssdk_auth_title_back");
            if (bitmapRes > 0) {
                this.f2843a.setBackgroundResource(bitmapRes);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
        this.f2843a.getBtnRight().setVisibility(8);
        int stringRes = ResHelper.getStringRes(getContext(), "ssdk_weibo_oauth_regiseter");
        if (stringRes > 0) {
            this.f2843a.getTvTitle().setText(stringRes);
        }
        addView(this.f2843a);
        ImageView imageView = new ImageView(context);
        int bitmapRes2 = ResHelper.getBitmapRes(context, "ssdk_logo");
        if (bitmapRes2 > 0) {
            imageView.setImageResource(bitmapRes2);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(0, 0, ResHelper.dipToPx(context, 10), 0);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.framework.authorize.RegisterView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                try {
                    int stringRes2 = ResHelper.getStringRes(view.getContext(), "ssdk_website");
                    String string = stringRes2 > 0 ? view.getResources().getString(stringRes2) : null;
                    if (!TextUtils.isEmpty(string)) {
                        view.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string)));
                    }
                } catch (Throwable th2) {
                    SSDKLog.m21740b().m21742a(th2);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f2843a.addView(imageView);
        this.f2844b = new RelativeLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        layoutParams.weight = 1.0f;
        this.f2844b.setLayoutParams(layoutParams);
        addView(this.f2844b);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.f2844b.addView(linearLayout);
        this.f2846d = new TextView(context);
        this.f2846d.setLayoutParams(new LinearLayout.LayoutParams(-1, 5));
        this.f2846d.setBackgroundColor(-12929302);
        linearLayout.addView(this.f2846d);
        this.f2846d.setVisibility(8);
        this.f2845c = new WebView(context);
        WebViewUtils.m21666a(this.f2845c, false);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams2.weight = 1.0f;
        this.f2845c.setLayoutParams(layoutParams2);
        WebChromeClient webChromeClient = new WebChromeClient() { // from class: cn.sharesdk.framework.authorize.RegisterView.2
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) RegisterView.this.f2846d.getLayoutParams();
                layoutParams3.width = (m21880b * i) / 100;
                RegisterView.this.f2846d.setLayoutParams(layoutParams3);
                if (i <= 0 || i >= 100) {
                    RegisterView.this.f2846d.setVisibility(8);
                } else {
                    RegisterView.this.f2846d.setVisibility(0);
                }
            }
        };
        if (Build.VERSION.SDK_INT > 10 && Build.VERSION.SDK_INT < 17) {
            try {
                Method method = this.f2845c.getClass().getMethod("removeJavascriptInterface", String.class);
                method.setAccessible(true);
                method.invoke(this.f2845c, "searchBoxJavaBridge_");
            } catch (Throwable th2) {
                SSDKLog.m21740b().m21742a(th2);
            }
        }
        this.f2845c.setWebChromeClient(webChromeClient);
        linearLayout.addView(this.f2845c);
        this.f2845c.requestFocus();
    }

    /* renamed from: b */
    private int m21880b(Context context) {
        WindowManager windowManager;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if ((context instanceof Activity) && (windowManager = ((Activity) context).getWindowManager()) != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        }
        return 0;
    }

    /* renamed from: a */
    public View m21885a() {
        return this.f2843a.getBtnBack();
    }

    /* renamed from: a */
    public void m21882a(boolean z) {
        this.f2843a.setVisibility(z ? 8 : 0);
    }

    /* renamed from: b */
    public WebView m21881b() {
        return this.f2845c;
    }

    /* renamed from: c */
    public TitleLayout m21879c() {
        return this.f2843a;
    }

    /* renamed from: d */
    public RelativeLayout m21878d() {
        return this.f2844b;
    }
}
