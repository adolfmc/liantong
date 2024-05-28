package cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.authorize.ResizeLayout;
import cn.sharesdk.framework.utils.SSDKLog;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WebAuthorizeActivity extends AbstractAuthorizeActivity implements Handler.Callback, ResizeLayout.OnResizeListener {
    protected static final int MSG_AUTH_URL_GOT = 2;
    private AuthorizeAdapter adapter;
    protected AuthorizeListener listener;

    /* renamed from: rv */
    private RegisterView f2851rv;
    private WebView webView;

    public void setAuthorizeListener(AuthorizeListener authorizeListener) {
        this.listener = authorizeListener;
    }

    @Override // com.mob.tools.FakeActivity
    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.adapter == null) {
            this.adapter = getAdapter();
            if (this.adapter == null) {
                this.adapter = new AuthorizeAdapter();
            }
        }
        this.adapter.setActivity(activity);
    }

    private AuthorizeAdapter getAdapter() {
        try {
            ActivityInfo activityInfo = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128);
            if (activityInfo.metaData != null && !activityInfo.metaData.isEmpty()) {
                String string = activityInfo.metaData.getString("AuthorizeAdapter");
                if ((string != null && string.length() > 0) || ((string = activityInfo.metaData.getString("Adapter")) != null && string.length() > 0)) {
                    Object newInstance = Class.forName(string).newInstance();
                    if (newInstance instanceof AuthorizeAdapter) {
                        return (AuthorizeAdapter) newInstance;
                    }
                    return null;
                }
                return null;
            }
            return null;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
            return null;
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        if (this.f2851rv == null) {
            this.f2851rv = getBodyView();
            this.f2851rv.m21877a(this);
            this.f2851rv.m21882a(this.adapter.isNotitle());
            this.adapter.setBodyView(this.f2851rv.m21878d());
            this.adapter.setWebView(this.f2851rv.m21881b());
            TitleLayout m21879c = this.f2851rv.m21879c();
            this.adapter.setTitleView(m21879c);
            String name = this.helper.getPlatform().getName();
            this.adapter.setPlatformName(this.helper.getPlatform().getName());
            try {
                Context context = getContext();
                m21879c.getTvTitle().setText(ResHelper.getStringRes(context, "ssdk_" + name.toLowerCase()));
            } catch (Throwable th) {
                try {
                    m21879c.getTvTitle().setText(ResHelper.getStringRes(getContext(), "ssdk_weibo_oauth_regiseter"));
                } catch (Throwable unused) {
                    SSDKLog.m21740b().m21737b(th);
                }
            }
        }
        this.adapter.onCreate();
        AuthorizeAdapter authorizeAdapter = this.adapter;
        if (authorizeAdapter != null && !authorizeAdapter.isPopUpAnimationDisable()) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(550L);
            scaleAnimation.setInterpolator(new animationInterpolatorC1755a());
            this.f2851rv.setAnimation(scaleAnimation);
        }
        disableScreenCapture();
        this.activity.setContentView(this.f2851rv);
    }

    /* JADX WARN: Type inference failed for: r1v9, types: [cn.sharesdk.framework.authorize.WebAuthorizeActivity$2] */
    protected RegisterView getBodyView() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.m21885a().setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.framework.authorize.WebAuthorizeActivity.1
            /* JADX WARN: Type inference failed for: r1v1, types: [cn.sharesdk.framework.authorize.WebAuthorizeActivity$1$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                new Thread() { // from class: cn.sharesdk.framework.authorize.WebAuthorizeActivity.1.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            new Instrumentation().sendKeyDownUpSync(4);
                        } catch (Throwable th) {
                            SSDKLog.m21740b().m21737b(th);
                            AuthorizeListener authorizeListener = WebAuthorizeActivity.this.helper.getAuthorizeListener();
                            if (authorizeListener != null) {
                                authorizeListener.onCancel();
                            }
                            WebAuthorizeActivity.this.finish();
                        }
                    }
                }.start();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.webView = registerView.m21881b();
        WebSettings settings = this.webView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        settings.setSavePassword(false);
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        AuthorizeWebviewClient authorizeWebviewClient = this.helper.getAuthorizeWebviewClient(this);
        String simpleName = authorizeWebviewClient != null ? authorizeWebviewClient.getClass().getSimpleName() : "";
        if ((!TextUtils.isEmpty(simpleName) && simpleName.equals("GooglePlusAuthorizeWebviewClient")) || ((!TextUtils.isEmpty(simpleName) && simpleName.contains("GooglePlus")) || simpleName.equals("YoutubeAuthorizeWebviewClient"))) {
            this.webView.getSettings().setUserAgentString(((("Mozilla/5.0 (Linux; Android 5.1; m2 note Build/LMY47D) AppleWebKit/537.36 (KHTML, like Gecko) ") + "Version/4.0 ") + "Chrome/40.0.2214.127 ") + "Mobile Safari/537.36");
        }
        WebView webView = this.webView;
        if (webView instanceof WebView) {
            NBSWebLoadInstrument.setWebViewClient(webView, authorizeWebviewClient);
        } else {
            webView.setWebViewClient(authorizeWebviewClient);
        }
        new Thread() { // from class: cn.sharesdk.framework.authorize.WebAuthorizeActivity.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    Message message = new Message();
                    message.what = 2;
                    if ("none".equals(DeviceHelper.getInstance(WebAuthorizeActivity.this.activity).getDetailNetworkTypeForStatic())) {
                        message.arg1 = 1;
                        UIHandler.sendMessage(message, WebAuthorizeActivity.this);
                        return;
                    }
                    if (ShareSDK.isRemoveCookieOnAuthorize()) {
                        CookieSyncManager.createInstance(WebAuthorizeActivity.this.activity);
                        CookieManager.getInstance().removeAllCookie();
                    }
                    message.obj = WebAuthorizeActivity.this.helper.getAuthorizeUrl();
                    UIHandler.sendMessage(message, WebAuthorizeActivity.this);
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21737b(th);
                }
            }
        }.start();
        return registerView;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 2) {
            return false;
        }
        if (message.arg1 == 1) {
            AuthorizeListener authorizeListener = this.helper.getAuthorizeListener();
            if (authorizeListener != null) {
                authorizeListener.onError(new Throwable("Network error (platform: " + this.helper.getPlatform().getName() + ")"));
                return false;
            }
            return false;
        }
        String str = (String) message.obj;
        if (TextUtils.isEmpty(str)) {
            finish();
            AuthorizeListener authorizeListener2 = this.helper.getAuthorizeListener();
            if (authorizeListener2 != null) {
                authorizeListener2.onError(new Throwable("Authorize URL is empty (platform: " + this.helper.getPlatform().getName() + ")"));
                return false;
            }
            return false;
        }
        WebView webView = this.webView;
        if (webView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView, str);
            return false;
        }
        webView.loadUrl(str);
        return false;
    }

    @Override // cn.sharesdk.framework.authorize.ResizeLayout.OnResizeListener
    public void OnResize(int i, int i2, int i3, int i4) {
        AuthorizeAdapter authorizeAdapter = this.adapter;
        if (authorizeAdapter != null) {
            authorizeAdapter.onResize(i, i2, i3, i4);
        }
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        AuthorizeListener authorizeListener;
        AuthorizeAdapter authorizeAdapter = this.adapter;
        boolean onKeyEvent = authorizeAdapter != null ? authorizeAdapter.onKeyEvent(i, keyEvent) : false;
        if (!onKeyEvent && i == 4 && keyEvent.getAction() == 0 && (authorizeListener = this.helper.getAuthorizeListener()) != null) {
            authorizeListener.onCancel();
        }
        if (onKeyEvent) {
            return true;
        }
        return super.onKeyEvent(i, keyEvent);
    }

    @Override // com.mob.tools.FakeActivity
    public void onStart() {
        AuthorizeAdapter authorizeAdapter = this.adapter;
        if (authorizeAdapter != null) {
            authorizeAdapter.onStart();
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onPause() {
        AuthorizeAdapter authorizeAdapter = this.adapter;
        if (authorizeAdapter != null) {
            authorizeAdapter.onPause();
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onResume() {
        AuthorizeAdapter authorizeAdapter = this.adapter;
        if (authorizeAdapter != null) {
            authorizeAdapter.onResume();
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onStop() {
        AuthorizeAdapter authorizeAdapter = this.adapter;
        if (authorizeAdapter != null) {
            authorizeAdapter.onStop();
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onRestart() {
        AuthorizeAdapter authorizeAdapter = this.adapter;
        if (authorizeAdapter != null) {
            authorizeAdapter.onRestart();
        }
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onFinish() {
        AuthorizeAdapter authorizeAdapter = this.adapter;
        if (authorizeAdapter != null) {
            return authorizeAdapter.onFinish();
        }
        WebView webView = this.webView;
        if (webView != null) {
            webView.destroy();
            this.webView.removeAllViews();
        }
        if (this.activity != null) {
            ((ViewGroup) this.activity.getWindow().getDecorView()).removeAllViews();
        }
        return super.onFinish();
    }

    @Override // com.mob.tools.FakeActivity
    public void onDestroy() {
        AuthorizeAdapter authorizeAdapter = this.adapter;
        if (authorizeAdapter != null) {
            authorizeAdapter.onDestroy();
        }
        WebView webView = this.webView;
        if (webView != null) {
            webView.setFocusable(false);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.sharesdk.framework.authorize.WebAuthorizeActivity$a  reason: invalid class name */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class animationInterpolatorC1755a implements Interpolator {

        /* renamed from: a */
        private float[] f2855a;

        private animationInterpolatorC1755a() {
            this.f2855a = new float[]{0.0f, 0.02692683f, 0.053847015f, 0.080753915f, 0.10764089f, 0.13450131f, 0.16132854f, 0.18811597f, 0.21485697f, 0.24154496f, 0.26817337f, 0.2947356f, 0.3212251f, 0.34763536f, 0.37395984f, 0.40019205f, 0.42632553f, 0.4523538f, 0.47827047f, 0.50406915f, 0.52974343f, 0.555287f, 0.5806936f, 0.60595685f, 0.6310707f, 0.65602875f, 0.68082494f, 0.70545316f, 0.72990733f, 0.75418144f, 0.7782694f, 0.8021654f, 0.8258634f, 0.8493577f, 0.8726424f, 0.89571184f, 0.9185602f, 0.94118196f, 0.9635715f, 0.9857233f, 1.0076319f, 1.0292919f, 1.0506978f, 1.0718446f, 1.0927268f, 1.1133395f, 1.1336775f, 1.1537358f, 1.1735094f, 1.1929934f, 1.1893399f, 1.1728106f, 1.1565471f, 1.1405534f, 1.1248333f, 1.1093911f, 1.0942302f, 1.0793544f, 1.0647675f, 1.050473f, 1.0364745f, 1.0227754f, 1.0093791f, 0.99628896f, 0.9835081f, 0.9710398f, 0.958887f, 0.9470527f, 0.93553996f, 0.9243516f, 0.91349024f, 0.90295863f, 0.90482706f, 0.9114033f, 0.91775465f, 0.9238795f, 0.9297765f, 0.93544406f, 0.9408808f, 0.94608533f, 0.95105654f, 0.955793f, 0.9602937f, 0.9645574f, 0.96858317f, 0.9723699f, 0.97591674f, 0.97922283f, 0.9822872f, 0.9851093f, 0.98768836f, 0.9900237f, 0.9921147f, 0.993961f, 0.99556196f, 0.9969173f, 0.9980267f, 0.99888986f, 0.99950653f, 0.9998766f, 1.0f};
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            int i = (int) (f * 100.0f);
            if (i < 0) {
                i = 0;
            }
            if (i > 100) {
                i = 100;
            }
            return this.f2855a[i];
        }
    }
}
