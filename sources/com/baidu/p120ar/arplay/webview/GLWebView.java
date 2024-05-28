package com.baidu.p120ar.arplay.webview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;
import com.baidu.p120ar.arplay.core.engine.ARPEngine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.webview.GLWebView */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GLWebView extends WebView {
    private boolean isNeedRender;
    private WebViewData mWebViewData;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.webview.GLWebView$WebViewData */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class WebViewData {
        public int height;
        public boolean isRemote;
        public String jsCode;
        public String operation;
        public int textureId;
        public String url;
        public int width;
    }

    public GLWebView(Context context) {
        super(context);
        this.isNeedRender = false;
    }

    public GLWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isNeedRender = false;
    }

    public GLWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isNeedRender = false;
    }

    public void setWebViewData(WebViewData webViewData) {
        this.mWebViewData = webViewData;
    }

    public WebViewData getWebViewData() {
        return this.mWebViewData;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.mWebViewData == null || canvas == null || !this.isNeedRender) {
            return;
        }
        final SurfaceTextureHolder webViewTextureHolder = SurfaceTextureManager.getInstance().getWebViewTextureHolder(this.mWebViewData.textureId);
        if (webViewTextureHolder == null) {
            Log.e("GLWebView", "HtmlTextureHolder is null: mTextureId: " + this.mWebViewData.textureId);
            return;
        }
        Canvas lockCanvas = webViewTextureHolder.lockCanvas();
        if (lockCanvas != null) {
            float width = lockCanvas.getWidth() / canvas.getWidth();
            lockCanvas.scale(width, width);
            lockCanvas.translate(-getScrollX(), -getScrollY());
            lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            super.draw(lockCanvas);
        }
        webViewTextureHolder.unlockCanvas();
        ARPEngine.getInstance().getARPRenderer().runSyncOnRenderContext(new Runnable() { // from class: com.baidu.ar.arplay.webview.GLWebView.1
            @Override // java.lang.Runnable
            public void run() {
                SurfaceTextureHolder surfaceTextureHolder = webViewTextureHolder;
                if (surfaceTextureHolder != null) {
                    surfaceTextureHolder.update();
                    GLWebView.this.isNeedRender = false;
                }
            }
        });
    }

    public void setIsNeedRender(boolean z) {
        this.isNeedRender = z;
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }
}
