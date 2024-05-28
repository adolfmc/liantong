package com.baidu.p120ar.arplay.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.baidu.p120ar.arplay.core.engine.ARPDataInteraction;
import com.baidu.p120ar.arplay.core.engine.ARPEngine;
import com.baidu.p120ar.arplay.core.message.ARPMessage;
import com.baidu.p120ar.arplay.util.LogUtil;
import com.baidu.p120ar.arplay.webview.GLWebView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.arplay.webview.GLWebViewManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GLWebViewManager {
    private static final int MSG_INIT_GLWEBVIEW = 103;
    private static final int MSG_INIT_NATIVE_WEBVIEW = 106;
    private static final int MSG_UPDATE_FINISHED = 105;
    private static final int MSG_UPDATE_GLWEBVIEW = 104;
    private static volatile GLWebViewManager mInstance;
    private Context mContext;
    private ViewGroup mNativeRootView;
    private View.OnTouchListener mNativeTouchListener;
    private WebView mNativeWebView;
    private String mResDir;
    private ViewGroup mRootView;
    private View.OnTouchListener mTouchListener;
    private List<GLWebView> mWebViewList = new ArrayList();
    private Handler mMainHandler = new Handler(Looper.getMainLooper()) { // from class: com.baidu.ar.arplay.webview.GLWebViewManager.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            final GLWebView gLWebView;
            super.handleMessage(message);
            GLWebView.WebViewData webViewData = message.obj instanceof GLWebView.WebViewData ? (GLWebView.WebViewData) message.obj : null;
            switch (message.what) {
                case 103:
                    GLWebViewManager.this.loadGLWebViewInner(webViewData);
                    return;
                case 104:
                    if (webViewData == null || (gLWebView = GLWebViewManager.this.getGLWebView(webViewData.textureId)) == null || webViewData.jsCode == null) {
                        return;
                    }
                    gLWebView.evaluateJavascript(webViewData.jsCode, new ValueCallback<String>() { // from class: com.baidu.ar.arplay.webview.GLWebViewManager.1.1
                        @Override // android.webkit.ValueCallback
                        public void onReceiveValue(String str) {
                            gLWebView.invalidate();
                            gLWebView.setIsNeedRender(true);
                            GLWebViewManager.this.callBackUpdateFinishMsgToLua(((Integer) gLWebView.getTag()).intValue());
                        }
                    });
                    return;
                case 105:
                    GLWebView gLWebView2 = GLWebViewManager.this.getGLWebView(message.arg1);
                    if (gLWebView2 != null) {
                        gLWebView2.setIsNeedRender(true);
                        gLWebView2.invalidate();
                        return;
                    }
                    return;
                case 106:
                    GLWebViewManager.this.loadNativeWebViewInner(webViewData);
                    return;
                default:
                    return;
            }
        }
    };
    WebViewClient mPageFinishedClient = new NBSWebViewClient() { // from class: com.baidu.ar.arplay.webview.GLWebViewManager.6
        @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            Tracker.onPageFinished(this, webView, str);
            super.onPageFinished(webView, str);
        }

        @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Tracker.onPageStarted(this, webView, str, bitmap);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageCommitVisible(WebView webView, String str) {
            super.onPageCommitVisible(webView, str);
            int intValue = ((Integer) webView.getTag()).intValue();
            GLWebViewManager.this.callBackLoadFinishMsgToLua(intValue);
            GLWebViewManager.this.onUpdateFinished(intValue);
        }

        @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            if (webResourceError != null) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.mErrorCode = webResourceError.getErrorCode();
                if (webResourceError.getDescription() != null) {
                    errorInfo.mErrorMsg = webResourceError.getDescription().toString();
                }
                GLWebViewManager.this.callBackLoadErrorMsgToLua(((Integer) webView.getTag()).intValue(), errorInfo);
            }
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            if (webResourceResponse != null) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.mErrorCode = webResourceResponse.getStatusCode();
                errorInfo.mErrorMsg = webResourceResponse.getReasonPhrase();
                GLWebViewManager.this.callBackLoadErrorMsgToLua(((Integer) webView.getTag()).intValue(), errorInfo);
            }
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        }

        @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (sslError != null) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.mErrorCode = sslError.getPrimaryError();
                errorInfo.mErrorMsg = "ssl error!";
                GLWebViewManager.this.callBackLoadErrorMsgToLua(((Integer) webView.getTag()).intValue(), errorInfo);
            }
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() { // from class: com.baidu.ar.arplay.webview.GLWebViewManager.7
        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (i == 100) {
                GLWebViewManager.this.onUpdateFinished(((GLWebView) webView).getWebViewData().textureId);
            }
        }
    };

    public static GLWebViewManager getInstance() {
        if (mInstance == null) {
            synchronized (GLWebViewManager.class) {
                if (mInstance == null) {
                    mInstance = new GLWebViewManager();
                }
            }
        }
        return mInstance;
    }

    public void initialGLWebViewContext(Context context, ViewGroup viewGroup, View.OnTouchListener onTouchListener) {
        this.mContext = context;
        this.mRootView = viewGroup;
        this.mTouchListener = onTouchListener;
        ARPEngine.getInstance().setHtmlUpdateCallback(new ARPDataInteraction.HtmlUpdateCallback() { // from class: com.baidu.ar.arplay.webview.GLWebViewManager.2
            @Override // com.baidu.p120ar.arplay.core.engine.ARPDataInteraction.HtmlUpdateCallback
            public boolean onUpdateHtmlFrame(int i, int i2) {
                GLWebViewManager.this.onUpdateFinished(i);
                return true;
            }
        });
    }

    public void initialNativeWebViewContext(Context context, ViewGroup viewGroup, View.OnTouchListener onTouchListener) {
        this.mContext = context;
        this.mNativeRootView = viewGroup;
        this.mNativeTouchListener = onTouchListener;
    }

    public void setResDir(String str) {
        this.mResDir = str;
    }

    private boolean isValid() {
        if (this.mContext == null || this.mRootView == null) {
            Log.e("GLWebView", "GLWebView context or root is null!");
            return false;
        }
        return true;
    }

    private boolean isNativeValid() {
        if (this.mContext == null || this.mNativeRootView == null) {
            Log.e("GLWebView", "Native WebView context or root is null!");
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadGLWebViewInner(GLWebView.WebViewData webViewData) {
        if (webViewData == null || !isValid()) {
            return;
        }
        GLWebView createGLWebView = createGLWebView(webViewData.width, webViewData.height);
        createGLWebView.setWebViewData(webViewData);
        SurfaceTextureManager.getInstance().generateSurfaceTextureHolder(webViewData.textureId, webViewData.width, webViewData.height);
        createGLWebView.setTag(Integer.valueOf(webViewData.textureId));
        String str = webViewData.url;
        if (!webViewData.isRemote) {
            str = "file://" + this.mContext.getFilesDir().getAbsolutePath().concat(File.separator) + "ar/" + str;
        }
        if (createGLWebView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) createGLWebView, str);
        } else {
            createGLWebView.loadUrl(str);
        }
        createGLWebView.invalidate();
        createGLWebView.setIsNeedRender(true);
        updateWebView(webViewData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadNativeWebViewInner(GLWebView.WebViewData webViewData) {
        if (webViewData == null || !isNativeValid()) {
            return;
        }
        if (this.mNativeWebView == null) {
            this.mNativeWebView = new WebView(this.mContext);
            this.mNativeWebView.setBackgroundColor(17170445);
            WebSettings settings = this.mNativeWebView.getSettings();
            settings.setLoadWithOverviewMode(true);
            settings.setUseWideViewPort(true);
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            settings.setJavaScriptEnabled(true);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            this.mNativeWebView.setOnTouchListener(new View.OnTouchListener() { // from class: com.baidu.ar.arplay.webview.GLWebViewManager.3
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (GLWebViewManager.this.mNativeTouchListener != null) {
                        return GLWebViewManager.this.mNativeTouchListener.onTouch(view, motionEvent);
                    }
                    return false;
                }
            });
            this.mNativeRootView.addView(this.mNativeWebView, layoutParams);
        }
        String str = webViewData.url;
        if (!webViewData.isRemote) {
            str = "file://" + this.mContext.getFilesDir().getAbsolutePath().concat(File.separator) + "ar/" + str;
        }
        WebView webView = this.mNativeWebView;
        if (webView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView, str);
        } else {
            webView.loadUrl(str);
        }
        this.mNativeWebView.invalidate();
    }

    private GLWebView createGLWebView(int i, int i2) {
        ViewGroup.LayoutParams layoutParams;
        final GLWebView gLWebView = new GLWebView(this.mContext);
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup instanceof FrameLayout) {
            layoutParams = new FrameLayout.LayoutParams(i, i2);
        } else if (viewGroup instanceof LinearLayout) {
            layoutParams = new LinearLayout.LayoutParams(i, i2);
        } else {
            layoutParams = viewGroup instanceof RelativeLayout ? new RelativeLayout.LayoutParams(i, i2) : null;
        }
        gLWebView.setBackgroundColor(17170445);
        gLWebView.setWebViewClient(this.mPageFinishedClient);
        gLWebView.setWebChromeClient(this.mWebChromeClient);
        gLWebView.setHorizontalScrollBarEnabled(false);
        gLWebView.setVerticalScrollBarEnabled(false);
        WebSettings settings = gLWebView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 17) {
            gLWebView.addJavascriptInterface(new IJSCallAndroid() { // from class: com.baidu.ar.arplay.webview.GLWebViewManager.4
                @Override // com.baidu.p120ar.arplay.webview.IJSCallAndroid
                @JavascriptInterface
                public void updateFinish(String str) {
                    GLWebView gLWebView2 = gLWebView;
                    if (gLWebView2 == null) {
                        return;
                    }
                    GLWebViewManager.this.onUpdateFinished(((Integer) gLWebView2.getTag()).intValue());
                }
            }, "NativeCallback");
        }
        gLWebView.setOnTouchListener(new View.OnTouchListener() { // from class: com.baidu.ar.arplay.webview.GLWebViewManager.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (GLWebViewManager.this.mTouchListener != null) {
                    return GLWebViewManager.this.mTouchListener.onTouch(view, motionEvent);
                }
                return false;
            }
        });
        this.mRootView.addView(gLWebView, layoutParams);
        this.mWebViewList.add(gLWebView);
        return gLWebView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBackLoadFinishMsgToLua(int i) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("event_name", "webView_operation_load_finish");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("texture_id", Integer.valueOf(i));
        hashMap.put("event_data", hashMap2);
        ARPMessage.getInstance().sendMessage(1902, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBackUpdateFinishMsgToLua(int i) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("event_name", "webView_operation_update_finish");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("texture_id", Integer.valueOf(i));
        hashMap.put("event_data", hashMap2);
        ARPMessage.getInstance().sendMessage(1902, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBackLoadErrorMsgToLua(int i, ErrorInfo errorInfo) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("event_name", "webView_operation_load_failed");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("texture_id", Integer.valueOf(i));
        HashMap hashMap3 = new HashMap();
        hashMap3.put("platform", errorInfo.mPlatform);
        hashMap3.put("error_code", Integer.valueOf(errorInfo.mErrorCode));
        hashMap3.put("error_msg", errorInfo.mErrorMsg);
        hashMap2.put("data", hashMap3);
        hashMap.put("event_data", hashMap2);
        ARPMessage.getInstance().sendMessage(1902, hashMap);
        onUpdateFinished(i);
    }

    public void release() {
        this.mContext = null;
        SurfaceTextureManager.getInstance().release();
        ARPEngine.getInstance().setHtmlUpdateCallback(null);
        Handler handler = this.mMainHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            runOnUiThread(new Runnable() { // from class: com.baidu.ar.arplay.webview.GLWebViewManager.8
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.m20423e(GLWebViewManager.class.getName(), "remove webviewList in ui thread");
                    if (GLWebViewManager.this.mWebViewList != null) {
                        for (GLWebView gLWebView : GLWebViewManager.this.mWebViewList) {
                            if (gLWebView != null) {
                                if (gLWebView.getParent() == GLWebViewManager.this.mRootView) {
                                    GLWebViewManager.this.mRootView.removeView(gLWebView);
                                }
                                gLWebView.destroy();
                            }
                        }
                        GLWebViewManager.this.mWebViewList.clear();
                        GLWebViewManager.this.mWebViewList = null;
                    }
                }
            });
            this.mMainHandler = null;
        }
        mInstance = null;
        this.mNativeWebView = null;
        this.mNativeTouchListener = null;
    }

    public void runOnUiThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            this.mMainHandler.post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GLWebView getGLWebView(int i) {
        for (GLWebView gLWebView : this.mWebViewList) {
            if (gLWebView != null && gLWebView.getWebViewData() != null && gLWebView.getWebViewData().textureId == i) {
                return gLWebView;
            }
        }
        return null;
    }

    public void loadGLWebView(GLWebView.WebViewData webViewData) {
        Message obtainMessage = this.mMainHandler.obtainMessage();
        obtainMessage.what = 103;
        obtainMessage.obj = webViewData;
        this.mMainHandler.sendMessage(obtainMessage);
    }

    public void loadNativeWebView(GLWebView.WebViewData webViewData) {
        Message obtainMessage = this.mMainHandler.obtainMessage();
        obtainMessage.what = 106;
        obtainMessage.obj = webViewData;
        this.mMainHandler.sendMessage(obtainMessage);
    }

    public void updateWebView(GLWebView.WebViewData webViewData) {
        Message obtainMessage = this.mMainHandler.obtainMessage();
        obtainMessage.what = 104;
        obtainMessage.obj = webViewData;
        GLWebView gLWebView = getGLWebView(webViewData.textureId);
        if (gLWebView != null) {
            gLWebView.setWebViewData(webViewData);
            this.mMainHandler.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUpdateFinished(int i) {
        Message obtainMessage = this.mMainHandler.obtainMessage();
        obtainMessage.what = 105;
        obtainMessage.arg1 = i;
        this.mMainHandler.sendMessage(obtainMessage);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.webview.GLWebViewManager$ErrorInfo */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class ErrorInfo {
        public String mErrorMsg;
        public String mPlatform = "android";
        public int mErrorCode = 0;

        public ErrorInfo() {
        }
    }
}
