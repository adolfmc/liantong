package com.tydic.tydic_tracker.tyWebview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
class AjaxInterceptJavascriptInterface {
    private static String interceptHeader;
    private TyWebViewClient mWebViewClient;

    public AjaxInterceptJavascriptInterface(TyWebViewClient tyWebViewClient) {
        this.mWebViewClient = null;
        this.mWebViewClient = tyWebViewClient;
    }

    public static String enableIntercept(Context context, byte[] bArr) throws IOException {
        if (interceptHeader == null) {
            interceptHeader = new String(Utils.consumeInputStream(context.getAssets().open("interceptheader.html")));
        }
        Document parse = Jsoup.parse(new String(bArr));
        parse.outputSettings().prettyPrint(true);
        Elements elementsByTag = parse.getElementsByTag("head");
        if (elementsByTag.size() > 0) {
            elementsByTag.get(0).prepend(interceptHeader);
        }
        return parse.toString();
    }

    @JavascriptInterface
    public void customAjax(String str, String str2) {
        this.mWebViewClient.addAjaxRequest(str, str2);
    }
}
