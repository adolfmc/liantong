package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Activity;
import android.os.Handler;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.sinovatech.unicom.basic.eventbus.PopWebViewEvent;
import com.sinovatech.unicom.basic.p315ui.JSInvokeHandler;
import com.sinovatech.unicom.basic.p315ui.WaveDialogManager;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.basic.webview.WebViewStatusListener;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerMainFuli */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerMainFuli {
    private AppCompatActivity context;
    private String entranceURL;
    private UserManager userManager = UserManager.getInstance();

    /* renamed from: wv */
    private PBWebView f18425wv;

    public ManagerMainFuli(AppCompatActivity appCompatActivity) {
        this.context = appCompatActivity;
    }

    public void initPopAdWebview(final RelativeLayout relativeLayout, int i) {
        if (App.hasLogined() && !"0".equals(this.userManager.getCurrentPhoneNumber())) {
            this.entranceURL = ConfigManager.getConfig_HomePopUpUrl_Key();
            if (!TextUtils.isEmpty(this.entranceURL) && this.entranceURL.startsWith("http")) {
                if (this.entranceURL.contains("?")) {
                    this.entranceURL += "&height=" + UIUtils.getScreenHeight((Activity) this.context) + "&width=" + UIUtils.getScreenWidth((Activity) this.context) + "&appVersion=" + this.context.getString(2131886969) + "&appId=" + DeviceHelper.getDeviceID(false) + "&bizcode=" + PopWebViewEvent.typeEnumToString(i);
                } else {
                    this.entranceURL += "?height=" + UIUtils.getScreenHeight((Activity) this.context) + "&width=" + UIUtils.getScreenWidth((Activity) this.context) + "&appVersion=" + this.context.getString(2131886969) + "&appId=" + DeviceHelper.getDeviceID(false) + "&bizcode=" + PopWebViewEvent.typeEnumToString(i);
                }
            }
            if (TextUtils.isEmpty(this.entranceURL)) {
                return;
            }
            try {
                UnicomCookieManager.addLoginCookie();
                this.f18425wv = new PBWebView(this.context);
                this.f18425wv.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                this.f18425wv.requestFocus();
                this.f18425wv.setBackgroundColor(0);
                this.f18425wv.getSettings().setCacheMode(2);
                this.f18425wv.addJavascriptInterface(new JSInvokeHandler(this.context, this.f18425wv, new Handler()), "js_invoke");
                this.f18425wv.setStatusListener(new WebViewStatusListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainFuli.1
                    @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                    public boolean onShouldOverrideUrlLoading(String str) {
                        return false;
                    }

                    @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                    public boolean onReceivedTitle(String str) {
                        if (TextUtils.isEmpty(str)) {
                            return true;
                        }
                        if (str.contains("受理结果") || str.contains("405 Not Allowed") || str.contains("找不到网页")) {
                            ManagerMainFuli.this.f18425wv.destroy();
                            relativeLayout.removeAllViews();
                            relativeLayout.setVisibility(4);
                            return true;
                        }
                        return true;
                    }

                    @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                    public boolean onPageFinished() {
                        WaveDialogManager.disMiss();
                        return false;
                    }
                });
                this.f18425wv.loadURL(this.entranceURL);
                this.f18425wv.setBackgroundColor(0);
                relativeLayout.removeAllViews();
                relativeLayout.addView(this.f18425wv);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setWvlayoutVisible(PopWebViewEvent popWebViewEvent, RelativeLayout relativeLayout) {
        try {
            if (popWebViewEvent.getAction() == 1) {
                WaveDialogManager.disMiss();
                if (popWebViewEvent.getTabType() != PopWebViewEvent.currentType || UIUtils.isFoldScreen(this.context) || LanguageUtil.getInstance().isShowUSADialog()) {
                    return;
                }
                if (this.context instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) this.context;
                    if (mainActivity.isHOMETAB() && !mainActivity.isTuijian()) {
                        this.f18425wv.destroy();
                        relativeLayout.removeAllViews();
                        relativeLayout.setVisibility(4);
                        MsLogUtil.m7979d("PopWebViewEvent", "首页非推荐");
                        return;
                    }
                }
                MsLogUtil.m7979d("PopWebViewEvent", "正常展示弹窗");
                UIUtils.setGJR(relativeLayout);
                relativeLayout.setVisibility(0);
            } else if (popWebViewEvent.getAction() == 2) {
                this.f18425wv.destroy();
                relativeLayout.removeAllViews();
                relativeLayout.setVisibility(4);
            } else if (popWebViewEvent.getAction() == 3) {
                this.f18425wv.destroy();
                relativeLayout.removeAllViews();
                relativeLayout.setVisibility(4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
