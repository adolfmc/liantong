package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthRecordEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthScopeManager;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.shortcut.ShortcutUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.FastToast;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.MsJSEvent;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentStateEntity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.objectbox.Box;
import io.reactivex.functions.Consumer;
import java.util.List;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CumpNavigationBar {
    public static String TAG = "cumpTag";
    private static CumpNavigationBar instance;
    private Context context;
    private final long ONCE_WEEK = 604800000;
    private boolean isButtonClickable = true;
    private final long ONCE_MONTH = -1702967296;
    private Box<CumpEntity> cumpBox = App.getBoxStore().boxFor(CumpEntity.class);

    public static synchronized CumpNavigationBar getInstance(Context context) {
        CumpNavigationBar cumpNavigationBar;
        synchronized (CumpNavigationBar.class) {
            if (instance == null) {
                synchronized (CumpNavigationBar.class) {
                    if (instance == null) {
                        instance = new CumpNavigationBar(context);
                    }
                }
            }
            cumpNavigationBar = instance;
        }
        return cumpNavigationBar;
    }

    public CumpNavigationBar(Context context) {
        this.context = context;
    }

    public void showCumpDetail(Activity activity, WebFragment webFragment, WebView webView, String str, CumpLanucher.lanuchListener lanuchlistener) {
        try {
            CumpEntity cumpEntity = webFragment.currentCumpAppEntity;
            Dialog dialog = new Dialog(activity, 2131952223);
            LinearLayout linearLayout = (LinearLayout) activity.getLayoutInflater().inflate(2131493071, (ViewGroup) null);
            initCumpDetailView(activity, dialog, webFragment, linearLayout, cumpEntity, webView, str, lanuchlistener);
            dialog.setContentView(linearLayout);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            Window window = dialog.getWindow();
            window.setGravity(80);
            window.setAttributes(attributes);
            window.setWindowAnimations(2131952210);
            UIUtils.showDialog(activity, dialog);
            PvCurrencyLogUtils.pvLogLL("", "S2ndpage1151", cumpEntity.getAppId(), "", "", "小程序操作页", "胶囊按钮", "", "上拉菜单");
        } catch (Exception unused) {
            MsLogUtil.m7977e(TAG, "显示小程序详情异常");
        }
    }

    private void initCumpDetailView(final Activity activity, final Dialog dialog, final WebFragment webFragment, View view, final CumpEntity cumpEntity, final WebView webView, final String str, final CumpLanucher.lanuchListener lanuchlistener) {
        int i;
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        LinearLayout linearLayout3;
        LinearLayout linearLayout4;
        int i2;
        CumpNavigationBar cumpNavigationBar;
        int i3;
        LinearLayout linearLayout5;
        LinearLayout linearLayout6;
        TextView textView = (TextView) view.findViewById(2131296753);
        final ImageView imageView = (ImageView) view.findViewById(2131296743);
        final TextView textView2 = (TextView) view.findViewById(2131296744);
        LinearLayout linearLayout7 = (LinearLayout) view.findViewById(2131296742);
        LinearLayout linearLayout8 = (LinearLayout) view.findViewById(2131296746);
        LinearLayout linearLayout9 = (LinearLayout) view.findViewById(2131296749);
        LinearLayout linearLayout10 = (LinearLayout) view.findViewById(2131296750);
        LinearLayout linearLayout11 = (LinearLayout) view.findViewById(2131296740);
        LinearLayout linearLayout12 = (LinearLayout) view.findViewById(2131296745);
        LinearLayout linearLayout13 = (LinearLayout) view.findViewById(2131296751);
        LinearLayout linearLayout14 = (LinearLayout) view.findViewById(2131296747);
        linearLayout14.setVisibility(8);
        LinearLayout linearLayout15 = (LinearLayout) view.findViewById(2131296748);
        textView.setText(cumpEntity.getAppName());
        Glide.with(activity).load(cumpEntity.getAppImg()).into((ImageView) view.findViewById(2131296752));
        ((TextView) view.findViewById(2131296741)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                dialog.dismiss();
                Activity activity2 = activity;
                IntentManager.gotoWebViewActivity(activity2, URLSet.getEdopDetailUrl() + "?appid=" + cumpEntity.getAppId(), "");
                PvCurrencyLogUtils.pvLogLL("", "S2ndpage1151", cumpEntity.getAppId(), "", "", "小程序操作页", "小程序详情", "", "");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        if (cumpEntity.getPlugCodeList().contains("_collection")) {
            linearLayout7.setVisibility(0);
            if (CumpCollectionManager.getInstance(activity).getCumpFromCollection(cumpEntity.getAppId()) == null) {
                imageView.setImageResource(2131231163);
                textView2.setText("收藏");
            } else {
                imageView.setImageResource(2131231188);
                textView2.setText("取消收藏");
            }
            linearLayout = linearLayout15;
            i = 0;
            linearLayout2 = linearLayout12;
            linearLayout3 = linearLayout13;
            linearLayout4 = linearLayout14;
            linearLayout7.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    if (CumpNavigationBar.this.isButtonClickable) {
                        CumpNavigationBar.this.isButtonClickable = false;
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                CumpNavigationBar.this.isButtonClickable = true;
                            }
                        }, 2000L);
                        final CumpCollectionEntity cumpFromCollection = CumpCollectionManager.getInstance(activity).getCumpFromCollection(cumpEntity.getAppId());
                        if (cumpFromCollection == null) {
                            new RecentCustomManager((AppCompatActivity) activity).deleteOrAddMenu(RecentCustomManager.ADD, cumpEntity.getAppId(), cumpEntity.getAppId(), new Consumer<RecentStateEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.3.2
                                @Override // io.reactivex.functions.Consumer
                                public void accept(RecentStateEntity recentStateEntity) throws Exception {
                                    try {
                                        if (recentStateEntity.isSuccess()) {
                                            FastToast.showText(activity, "收藏成功");
                                            CumpCollectionEntity cumpCollectionEntity = new CumpCollectionEntity();
                                            cumpCollectionEntity.setAppId(cumpEntity.getAppId());
                                            cumpCollectionEntity.setAppName(cumpEntity.getAppName());
                                            CumpCollectionManager.getInstance(activity).addCollection(cumpCollectionEntity);
                                            imageView.setImageResource(2131231188);
                                            textView2.setText("取消收藏");
                                            webFragment.setNavigationBarColor(webFragment.currentCumpTheme);
                                            PvCurrencyLogUtils.pvLogLL("", "S2ndpage1151", cumpEntity.getAppId(), "", "", "小程序操作页", "收藏", "", "");
                                            webFragment.postEventToJS(MsJSEvent.onEdopCollectionStatusChanged, new JSONObject("{status:'1'}"));
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } else {
                            new RecentCustomManager((AppCompatActivity) activity).deleteOrAddMenu(RecentCustomManager.CANCEL, cumpEntity.getAppId(), cumpEntity.getAppId(), new Consumer<RecentStateEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.3.3
                                @Override // io.reactivex.functions.Consumer
                                public void accept(RecentStateEntity recentStateEntity) throws Exception {
                                    try {
                                        if (recentStateEntity.isSuccess()) {
                                            FastToast.showText(activity, "已取消收藏");
                                            CumpCollectionManager.getInstance(activity).removeCollection(cumpFromCollection);
                                            imageView.setImageResource(2131231163);
                                            textView2.setText("收藏");
                                            webFragment.setNavigationBarColor(webFragment.currentCumpTheme);
                                            PvCurrencyLogUtils.pvLogLL("", "S2ndpage1151", cumpEntity.getAppId(), "", "", "小程序操作页", "取消收藏", "", "");
                                            webFragment.postEventToJS(MsJSEvent.onEdopCollectionStatusChanged, new JSONObject("{status:'2'}"));
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    } else {
                        FastToast.showText(activity, "操作过于频繁，请稍后再试");
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } else {
            i = 0;
            linearLayout = linearLayout15;
            linearLayout2 = linearLayout12;
            linearLayout3 = linearLayout13;
            linearLayout4 = linearLayout14;
            linearLayout7.setVisibility(8);
        }
        linearLayout9.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                String str2 = CumpNavigationBar.TAG;
                MsLogUtil.m7979d(str2, "重新进入小程序 " + str);
                dialog.dismiss();
                if (lanuchlistener != null) {
                    CumpLanucher.getInstance(activity).deleteApp(cumpEntity.getAppId());
                    lanuchlistener.onLanuchRetry(cumpEntity.getServerPublishType(), false);
                }
                PvCurrencyLogUtils.pvLogLL("", "S2ndpage1151", cumpEntity.getAppId(), "", "", "小程序操作页", "重新进入", "", "");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        if (webFragment.currentEdopShareConfig == null) {
            linearLayout10.setVisibility(8);
        } else {
            linearLayout10.setVisibility(i);
            linearLayout10.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    CumpNavigationBar.this.share(webFragment, webView, cumpEntity);
                    dialog.dismiss();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }
        List<UserAuthRecordEntity> userAuthRecord = UserAuthScopeManager.getInstance(activity).getUserAuthRecord(UserManager.getInstance().getCurrentPhoneNumber(), cumpEntity.getAppId());
        if (userAuthRecord != null && userAuthRecord.size() > 0) {
            linearLayout11.setVisibility(i);
            i2 = i;
            cumpNavigationBar = this;
            linearLayout11.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    try {
                        dialog.dismiss();
                        Intent intent = new Intent(activity, CumpAuthRecordActivity.class);
                        intent.putExtra("appId", cumpEntity.getAppId());
                        activity.startActivity(intent);
                        PvCurrencyLogUtils.pvLogLL("", "S2ndpage1151", cumpEntity.getAppId(), "", "", "小程序操作页", "设置", "", "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } else {
            i2 = i;
            cumpNavigationBar = this;
            linearLayout11.setVisibility(8);
        }
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                dialog.dismiss();
                Activity activity2 = activity;
                IntentManager.gotoWebViewActivity(activity2, URLSet.getEdopFankuiUrl() + "?appid=" + cumpEntity.getAppId(), "反馈");
                PvCurrencyLogUtils.pvLogLL("", "S2ndpage1151", cumpEntity.getAppId(), "", "", "小程序操作页", "反馈", "", "");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        if (cumpEntity.getPlugCodeList().contains("_shortcut") && !TextUtils.isEmpty(cumpEntity.getDesktopIcon())) {
            LinearLayout linearLayout16 = linearLayout3;
            linearLayout16.setVisibility(i2);
            linearLayout16.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    UIUtils.dismissDialog(activity, dialog);
                    CumpNavigationBar.this.addPinedShortcutToDesktop(activity, cumpEntity);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            i3 = 8;
        } else {
            i3 = 8;
            linearLayout3.setVisibility(8);
        }
        if (!TextUtils.isEmpty(cumpEntity.getServiceTelephone())) {
            linearLayout8.setVisibility(i2);
            linearLayout8.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    dialog.dismiss();
                    CumpNavigationBar.this.kefuBohao(activity, cumpEntity.getServiceTelephone());
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            linearLayout5 = linearLayout;
            linearLayout6 = linearLayout4;
        } else {
            linearLayout8.setVisibility(i3);
            linearLayout5 = linearLayout;
            linearLayout6 = linearLayout4;
        }
        ManagerMoreApplication.handleMore(activity, linearLayout6, linearLayout5);
        linearLayout6.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                dialog.dismiss();
                IntentManager.gotoWebViewActivity(activity, URLSet.getEdopMoreH5Url(), "");
                PvCurrencyLogUtils.pvLogLL("", "S2ndpage1151", cumpEntity.getAppId(), "", "", "小程序操作页", "更多小程序", "", "");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    public void setNavigationBarColor(Activity activity, WebFragment webFragment, View view) {
        try {
            View findViewById = view.findViewById(2131296758);
            ImageView imageView = (ImageView) view.findViewById(2131296754);
            ImageView imageView2 = (ImageView) view.findViewById(2131296760);
            ImageView imageView3 = (ImageView) view.findViewById(2131296755);
            ((GradientDrawable) ((LinearLayout) view.findViewById(2131296759)).getBackground()).setColor(webFragment.currentCumpTheme.getNavBarBackgroundColor());
            findViewById.setBackgroundColor(webFragment.currentCumpTheme.getNavBarLineColor());
            ((GradientDrawable) ((RelativeLayout) view.findViewById(2131296756)).getBackground()).setColor(webFragment.currentCumpTheme.getNavBarBackgroundColor());
            if ("white".equalsIgnoreCase(webFragment.currentCumpTheme.getNavBarIconTheme())) {
                imageView2.setImageResource(2131231167);
                imageView.setImageResource(2131231162);
            } else {
                imageView2.setImageResource(2131231165);
                imageView.setImageResource(2131231161);
            }
            if (webFragment.currentCumpAppEntity != null) {
                if (CumpCollectionManager.getInstance(activity).getCumpFromCollection(webFragment.currentCumpAppEntity.getAppId()) == null) {
                    if ("white".equalsIgnoreCase(webFragment.currentCumpTheme.getNavBarIconTheme())) {
                        imageView3.setImageResource(2131231177);
                    } else {
                        imageView3.setImageResource(2131231179);
                    }
                } else if ("white".equalsIgnoreCase(webFragment.currentCumpTheme.getNavBarIconTheme())) {
                    imageView3.setImageResource(2131231176);
                } else {
                    imageView3.setImageResource(2131231178);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afterLanuchSuccess(Activity activity, WebFragment webFragment, View view, WebView webView) {
        CumpEntity cumpEntity = webFragment.currentCumpAppEntity;
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(2131296756);
        ImageView imageView = (ImageView) view.findViewById(2131296755);
        relativeLayout.setVisibility(8);
        PopupWindow popupWindow = new PopupWindow(activity);
        try {
            try {
                new RecentCustomManager((AppCompatActivity) activity).queryCollectMenu(cumpEntity.getAppId(), cumpEntity.getAppId(), new C901411(cumpEntity, relativeLayout, activity, webFragment, view, imageView, popupWindow), new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.12
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        th.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar$11 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C901411 implements Consumer<JSONObject> {
        final /* synthetic */ Activity val$activityContext;
        final /* synthetic */ RelativeLayout val$collLayout;
        final /* synthetic */ PopupWindow val$collPopupWindow;
        final /* synthetic */ ImageView val$collectionImage;
        final /* synthetic */ CumpEntity val$cumpEntity;
        final /* synthetic */ View val$framgmentRootView;
        final /* synthetic */ WebFragment val$webFragment;

        C901411(CumpEntity cumpEntity, RelativeLayout relativeLayout, Activity activity, WebFragment webFragment, View view, ImageView imageView, PopupWindow popupWindow) {
            this.val$cumpEntity = cumpEntity;
            this.val$collLayout = relativeLayout;
            this.val$activityContext = activity;
            this.val$webFragment = webFragment;
            this.val$framgmentRootView = view;
            this.val$collectionImage = imageView;
            this.val$collPopupWindow = popupWindow;
        }

        /* JADX WARN: Removed duplicated region for block: B:141:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:89:0x0233 A[Catch: Exception -> 0x036c, TryCatch #0 {Exception -> 0x036c, blocks: (B:9:0x0018, B:11:0x0022, B:13:0x0028, B:16:0x0032, B:18:0x0038, B:20:0x003e, B:22:0x0044, B:25:0x0055, B:28:0x0065, B:30:0x006a, B:32:0x007f, B:34:0x0092, B:36:0x00a4, B:42:0x00e8, B:44:0x00ff, B:51:0x013d, B:53:0x0143, B:59:0x0151, B:61:0x0161, B:63:0x0169, B:65:0x0171, B:47:0x012f, B:68:0x01dc, B:70:0x01e0, B:72:0x01e8, B:74:0x01fd, B:76:0x020b, B:89:0x0233, B:96:0x0277, B:98:0x027d, B:100:0x0283, B:106:0x0291, B:108:0x02a0, B:110:0x02a8, B:112:0x02b0, B:114:0x0300, B:116:0x0308, B:118:0x0310, B:119:0x031d, B:121:0x0328, B:123:0x0334, B:122:0x032e, B:92:0x0269, B:79:0x0218, B:82:0x0221, B:84:0x0229, B:37:0x00c5, B:39:0x00cd, B:41:0x00df, B:66:0x01d5), top: B:128:0x0018 }] */
        @Override // io.reactivex.functions.Consumer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void accept(org.json.JSONObject r25) throws java.lang.Exception {
            /*
                Method dump skipped, instructions count: 881
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.C901411.accept(org.json.JSONObject):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addPinedShortcutToDesktop(Activity activity, CumpEntity cumpEntity) {
        String str;
        try {
            String str2 = cumpEntity.getAppId() + "_" + cumpEntity.getServerPublishType();
            if ("2".equalsIgnoreCase(cumpEntity.getServerPublishType())) {
                str = cumpEntity.getAppName();
            } else {
                str = cumpEntity.getAppName() + "体验";
            }
            try {
                ShortcutUtil.addPinedShortcutToDesktop(activity, str2, str, cumpEntity.getDesktopIcon(), "chinaunicom://app?desktopshortcut=desktopshortcut&openUrl=" + ("https://edop_unicom?appid=" + cumpEntity.getAppId() + "&publishType=" + cumpEntity.getServerPublishType()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            PvCurrencyLogUtils.pvLogLL("", "S2ndpage1151", cumpEntity.getAppId(), "", "", "小程序操作页", "添加到桌面", "", "");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void share(WebFragment webFragment, WebView webView, CumpEntity cumpEntity) {
        try {
            JSONObject jSONObject = webFragment.currentEdopShareConfig;
            String str = "";
            if (jSONObject != null) {
                str = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            }
            String str2 = "javascript:ms.showShareMenu(" + str + ").then(function(res){window.setShareStatus_updateEdopShareMenu(res)})";
            if (webView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) webView, str2);
            } else {
                webView.loadUrl(str2);
            }
            PvCurrencyLogUtils.pvLogLL("", "S2ndpage1151", cumpEntity.getAppId(), "", "", "小程序操作页", "分享", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void kefuBohao(final Activity activity, final String str) {
        try {
            PermissionDialog.show("为您提供APP内快速联系业务人员服务，需要获取您拨打电话权限，对于您授权提供的信息，我们会竭尽全力提供安全保护。");
            new RxPermissions(activity).request("android.permission.CALL_PHONE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.13
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    PermissionDialog.dimissDialog();
                    if (bool.booleanValue()) {
                        activity.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + str)));
                        return;
                    }
                    Toast.makeText(activity, "没有拨打电话权限", 1).show();
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar.14
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    PermissionDialog.dimissDialog();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
