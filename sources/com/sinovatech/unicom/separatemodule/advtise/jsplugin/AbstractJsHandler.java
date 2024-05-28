package com.sinovatech.unicom.separatemodule.advtise.jsplugin;

import android.support.p086v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractJsHandler {
    protected AppCompatActivity activity;
    protected IAdInterface adInterface;
    protected AdConfigEntity entity;
    protected BaseJSPlugin jsPlugin;

    /* renamed from: pd */
    protected CustomePorgressDialog f18456pd;
    protected WebView webView;

    public abstract void exec();

    public AbstractJsHandler(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity, IAdInterface iAdInterface, BaseJSPlugin baseJSPlugin) {
        this.activity = appCompatActivity;
        this.adInterface = iAdInterface;
        this.entity = adConfigEntity;
        this.jsPlugin = baseJSPlugin;
        this.f18456pd = new CustomePorgressDialog(appCompatActivity);
        this.f18456pd.setCanceledOnTouchOutside(true);
        this.f18456pd.setCancelable(true);
    }

    public AbstractJsHandler(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity, IAdInterface iAdInterface, WebView webView) {
        this.activity = appCompatActivity;
        this.adInterface = iAdInterface;
        this.entity = adConfigEntity;
        this.webView = webView;
        this.f18456pd = new CustomePorgressDialog(appCompatActivity);
        this.f18456pd.setCanceledOnTouchOutside(true);
        this.f18456pd.setCancelable(true);
    }
}
