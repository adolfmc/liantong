package com.sinovatech.unicom.separatemodule.login.mengceng;

import android.app.Activity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.binioter.guideview.Component;
import com.binioter.guideview.Guide;
import com.binioter.guideview.GuideBuilder;
import com.sinovatech.unicom.basic.p314po.LoginConfigEntity;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.login.mengceng.LoginMengCengUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LoginMengCengUtil {
    private static final String PASS_KEY = "login_pwd_mc_key";
    private static Guide guide;
    private static Guide guide2;
    private static boolean isShowingg;

    public static void showPasswordDialog(final AppCompatActivity appCompatActivity, boolean z, final View view, final LoginConfigEntity loginConfigEntity, final String str) {
        if (isShowingg || z || !"on".equals(loginConfigEntity.getPopupSwitch())) {
            return;
        }
        String string = App.getSharePreferenceUtil().getString("login_pwd_mc_key");
        String popVersion = loginConfigEntity.getPopVersion();
        if (TextUtils.isEmpty(popVersion) || popVersion.equals(string)) {
            return;
        }
        isShowingg = true;
        view.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.login.mengceng.LoginMengCengUtil.1
            @Override // java.lang.Runnable
            public void run() {
                GuideBuilder guideBuilder = new GuideBuilder();
                guideBuilder.setTargetView(view).setAlpha(150).setHighTargetCorner(0).setAutoDismiss(false).setHighTargetGraphStyle(0);
                guideBuilder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() { // from class: com.sinovatech.unicom.separatemodule.login.mengceng.LoginMengCengUtil.1.1
                    @Override // com.binioter.guideview.GuideBuilder.OnVisibilityChangedListener
                    public void onShown() {
                    }

                    @Override // com.binioter.guideview.GuideBuilder.OnVisibilityChangedListener
                    public void onDismiss() {
                        boolean unused = LoginMengCengUtil.isShowingg = false;
                    }
                });
                guideBuilder.addComponent(new C89752());
                Guide unused = LoginMengCengUtil.guide = guideBuilder.createGuide();
                LoginMengCengUtil.guide.setShouldCheckLocInWindow(false);
                LoginMengCengUtil.guide.show(appCompatActivity);
                PvCurrencyLogUtils.pvloginPasswoedTip(str, "01");
            }

            /* renamed from: com.sinovatech.unicom.separatemodule.login.mengceng.LoginMengCengUtil$1$2 */
            /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
            class C89752 implements Component {
                @Override // com.binioter.guideview.Component
                public int getAnchor() {
                    return 4;
                }

                @Override // com.binioter.guideview.Component
                public int getFitPosition() {
                    return 32;
                }

                @Override // com.binioter.guideview.Component
                public int getXOffset() {
                    return 0;
                }

                @Override // com.binioter.guideview.Component
                public int getYOffset() {
                    return 0;
                }

                C89752() {
                }

                @Override // com.binioter.guideview.Component
                public View getView(LayoutInflater layoutInflater) {
                    View inflate = layoutInflater.inflate(2131493049, (ViewGroup) null);
                    boolean z = ((double) ((float) UIUtils.getScreenHeight((Activity) appCompatActivity))) < ((double) ((float) UIUtils.getScreenWidth((Activity) appCompatActivity))) * 1.7d;
                    if (UIUtils.isFoldScreen(appCompatActivity) || z) {
                        inflate = layoutInflater.inflate(2131493050, (ViewGroup) null);
                    }
                    TextView textView = (TextView) inflate.findViewById(2131296696);
                    TextView textView2 = (TextView) inflate.findViewById(2131296695);
                    String popupContent = loginConfigEntity.getPopupContent();
                    if (!TextUtils.isEmpty(popupContent)) {
                        textView2.setText(popupContent);
                    }
                    final LoginConfigEntity loginConfigEntity = loginConfigEntity;
                    final String str = str;
                    textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.mengceng.-$$Lambda$LoginMengCengUtil$1$2$WTrppAKTwfRFF47t9SVcdhzuJLw
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            LoginMengCengUtil.RunnableC89731.C89752.lambda$getView$0(LoginConfigEntity.this, str, view);
                        }
                    });
                    return inflate;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                public static /* synthetic */ void lambda$getView$0(LoginConfigEntity loginConfigEntity, String str, View view) {
                    boolean unused = LoginMengCengUtil.isShowingg = false;
                    App.getSharePreferenceUtil().putString("login_pwd_mc_key", loginConfigEntity.getPopVersion());
                    if (LoginMengCengUtil.guide != null) {
                        LoginMengCengUtil.guide.dismiss();
                        Guide unused2 = LoginMengCengUtil.guide = null;
                    }
                    PvCurrencyLogUtils.pvloginPasswoedTip(str, "02");
                }
            }
        });
    }

    public static void handlePassWordTipLayout(final AppCompatActivity appCompatActivity, boolean z, final View view, final LoginConfigEntity loginConfigEntity, LinearLayout linearLayout, boolean z2, final String str) {
        if (!z2) {
            linearLayout.setVisibility(4);
        } else if (z) {
            linearLayout.setVisibility(4);
        } else if (!"on".equals(loginConfigEntity.getEntranceSwitch())) {
            linearLayout.setVisibility(4);
        } else {
            linearLayout.setVisibility(0);
            if (!TextUtils.isEmpty(loginConfigEntity.getEntranceContent())) {
                ((TextView) linearLayout.findViewById(2131297906)).setText(loginConfigEntity.getEntranceContent());
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.mengceng.-$$Lambda$LoginMengCengUtil$IR1tIkghlQk1fjzEmr3B7MgOIOU
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    LoginMengCengUtil.lambda$handlePassWordTipLayout$0(str, appCompatActivity, view, loginConfigEntity, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$handlePassWordTipLayout$0(String str, AppCompatActivity appCompatActivity, View view, LoginConfigEntity loginConfigEntity, View view2) {
        PvCurrencyLogUtils.pvloginPasswoedTip(str, "01");
        showPasswordDialog2(appCompatActivity, view, loginConfigEntity, str);
    }

    public static void showPasswordDialog2(final AppCompatActivity appCompatActivity, final View view, final LoginConfigEntity loginConfigEntity, final String str) {
        view.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.login.mengceng.LoginMengCengUtil.2
            @Override // java.lang.Runnable
            public void run() {
                GuideBuilder guideBuilder = new GuideBuilder();
                guideBuilder.setTargetView(view).setAlpha(150).setHighTargetCorner(0).setAutoDismiss(false).setHighTargetGraphStyle(0);
                guideBuilder.addComponent(new C89771());
                Guide unused = LoginMengCengUtil.guide2 = guideBuilder.createGuide();
                LoginMengCengUtil.guide2.setShouldCheckLocInWindow(false);
                LoginMengCengUtil.guide2.show(appCompatActivity);
            }

            /* renamed from: com.sinovatech.unicom.separatemodule.login.mengceng.LoginMengCengUtil$2$1 */
            /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
            class C89771 implements Component {
                @Override // com.binioter.guideview.Component
                public int getAnchor() {
                    return 4;
                }

                @Override // com.binioter.guideview.Component
                public int getFitPosition() {
                    return 32;
                }

                @Override // com.binioter.guideview.Component
                public int getXOffset() {
                    return 0;
                }

                @Override // com.binioter.guideview.Component
                public int getYOffset() {
                    return 0;
                }

                C89771() {
                }

                @Override // com.binioter.guideview.Component
                public View getView(LayoutInflater layoutInflater) {
                    View inflate = layoutInflater.inflate(2131493049, (ViewGroup) null);
                    boolean z = ((double) ((float) UIUtils.getScreenHeight((Activity) appCompatActivity))) < ((double) ((float) UIUtils.getScreenWidth((Activity) appCompatActivity))) * 1.7d;
                    if (UIUtils.isFoldScreen(appCompatActivity) || z) {
                        inflate = layoutInflater.inflate(2131493050, (ViewGroup) null);
                    }
                    TextView textView = (TextView) inflate.findViewById(2131296696);
                    TextView textView2 = (TextView) inflate.findViewById(2131296695);
                    String popupContent = loginConfigEntity.getPopupContent();
                    if (!TextUtils.isEmpty(popupContent)) {
                        textView2.setText(popupContent);
                    }
                    final String str = str;
                    textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.mengceng.-$$Lambda$LoginMengCengUtil$2$1$6oPr8zY-4_8B8IAm5y41mWO6VFo
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            LoginMengCengUtil.RunnableC89762.C89771.lambda$getView$0(str, view);
                        }
                    });
                    return inflate;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                public static /* synthetic */ void lambda$getView$0(String str, View view) {
                    if (LoginMengCengUtil.guide2 != null) {
                        LoginMengCengUtil.guide2.dismiss();
                        Guide unused = LoginMengCengUtil.guide2 = null;
                    }
                    PvCurrencyLogUtils.pvloginPasswoedTip(str, "02");
                }
            }
        });
    }
}
