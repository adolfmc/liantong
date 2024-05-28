package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.eventbus.ChangeNoticNumberEvent;
import com.sinovatech.unicom.basic.eventbus.PopWebViewEvent;
import com.sinovatech.unicom.basic.p315ui.JiaobiaoUtils;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.ServiceFragment;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import java.util.HashMap;
import java.util.Map;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* renamed from: com.sinovatech.unicom.basic.ui.view.MainTabView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MainTabView extends RelativeLayout {
    public static final String TABTAG_FIND = "TABTAG_FIND";
    public static final String TABTAG_HOME = "TABTAG_HOME";
    public static final String TABTAG_HUODONG = "TABTAG_HUODONG";
    public static final String TABTAG_SERVICE = "TABTAG_SERVICE";
    public static final String TABTAG_SHOP = "TABTAG_SHOP";
    public static final String TABTAG_USER = "TABTAG_USER";
    public static String currentTab = "TABTAG_HOME";
    private AppCompatActivity activityContext;
    private String activityHide;
    private ImageView adButtonImage;
    public LinearLayout adButtonLayout;
    private TextView adButtonText;
    private ImageView adJiaoBiaoImage;
    private ImageView adNoticeImage;
    private int adNoticeImageVisible;
    private Animation anime;
    private String caiFuHide;
    private Map<String, Drawable> customTabIconLoadMap;
    private String faxianIcon;
    private String getActivityIcon;
    private String getMyIcon;
    private ImageView homeButtonImage;
    public LinearLayout homeButtonLayout;
    private TextView homeButtonText;
    private String homeHide;
    private String homeIcon;
    private ImageView homeJiaoBiaoImage;
    private ImageView homeNoticImage;
    private int homeNoticImageVisible;
    private ImageView huodongButtonImage;
    public LinearLayout huodongButtonLayout;
    private TextView huodongButtonText;
    private ImageView huodongJiaoBiaoImage;
    private RelativeLayout huodongRl;
    private boolean isSelectHomeTab;
    private int loadCount;
    private String marketHide;
    private String myHide;
    private ImageView serviceButtonImage;
    public LinearLayout serviceButtonLayout;
    private TextView serviceButtonText;
    private String serviceHide;
    private String serviceIcon;
    private ImageView serviceJiaoBiaoImage;
    private ImageView serviceNoticeImage;
    private int serviceNoticeImageVisible;
    private RelativeLayout serviceRl;
    private String shangchengIcon;
    private ImageView shopButtonImage;
    public LinearLayout shopButtonLayout;
    private TextView shopButtonText;
    private ImageView shopJiaoBiaoImage;
    private TabClickListener tabClickListener;
    private String[] tabTagArry;
    private String textPictureColor;
    private TriggerRefresh triggerRefresh;
    private ImageView userButtonImage;
    public LinearLayout userButtonLayout;
    private TextView userButtonText;
    private ImageView userJiaoBiaoImage;
    private TextView userNoticeImage;
    private int userNoticeImageVisible;
    private LinearLayout view;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.MainTabView$TabClickListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface TabClickListener {
        void clickFind();

        void clickHome();

        void clickHuodong();

        void clickService();

        void clickShop();

        void clickUser();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.MainTabView$TriggerRefresh */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface TriggerRefresh {
        void showRefresh();
    }

    /*  JADX ERROR: Failed to decode insn: 0x0462: UNKNOWN(0x13EB), method: com.sinovatech.unicom.basic.ui.view.MainTabView.changeTabUI(java.lang.String):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0462: UNKNOWN(0x13EB)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x049C: UNKNOWN(0x13F1), method: com.sinovatech.unicom.basic.ui.view.MainTabView.changeTabUI(java.lang.String):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x049C: UNKNOWN(0x13F1)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x049E: CONST_STRING r0, method: com.sinovatech.unicom.basic.ui.view.MainTabView.changeTabUI(java.lang.String):void
        java.lang.IllegalArgumentException: newPosition > limit: (1258836564 > 14732120)
        	at java.nio.Buffer.createPositionException(Buffer.java:269)
        	at java.nio.Buffer.position(Buffer.java:244)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:177)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsString(DexInsnData.java:121)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:84)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x04CC: UNKNOWN(0x1400), method: com.sinovatech.unicom.basic.ui.view.MainTabView.changeTabUI(java.lang.String):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04CC: UNKNOWN(0x1400)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0588: FILLED_NEW_ARRAY r15, method: com.sinovatech.unicom.basic.ui.view.MainTabView.changeTabUI(java.lang.String):void
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    public void changeTabUI(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 1540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.view.MainTabView.changeTabUI(java.lang.String):void");
    }

    public void setCaifuRedPoid(int i) {
    }

    static /* synthetic */ int access$108(MainTabView mainTabView) {
        int i = mainTabView.loadCount;
        mainTabView.loadCount = i + 1;
        return i;
    }

    public MainTabView(Context context) {
        super(context);
        this.getActivityIcon = "";
        this.homeHide = "";
        this.serviceHide = "";
        this.activityHide = "";
        this.marketHide = "";
        this.caiFuHide = "";
        this.myHide = "";
        this.userNoticeImageVisible = 8;
        this.tabTagArry = new String[]{"TAB_HOME", "TAB_SERVICE", "TAB_SHOP", "TAB_FIND", "TAB_USER"};
        this.loadCount = 0;
        this.customTabIconLoadMap = new HashMap();
        this.isSelectHomeTab = true;
        this.homeNoticImageVisible = 8;
        this.serviceNoticeImageVisible = 8;
        this.adNoticeImageVisible = 8;
        this.activityContext = (AppCompatActivity) context;
        initTab();
    }

    public MainTabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.getActivityIcon = "";
        this.homeHide = "";
        this.serviceHide = "";
        this.activityHide = "";
        this.marketHide = "";
        this.caiFuHide = "";
        this.myHide = "";
        this.userNoticeImageVisible = 8;
        this.tabTagArry = new String[]{"TAB_HOME", "TAB_SERVICE", "TAB_SHOP", "TAB_FIND", "TAB_USER"};
        this.loadCount = 0;
        this.customTabIconLoadMap = new HashMap();
        this.isSelectHomeTab = true;
        this.homeNoticImageVisible = 8;
        this.serviceNoticeImageVisible = 8;
        this.adNoticeImageVisible = 8;
        this.activityContext = (AppCompatActivity) context;
        initTab();
    }

    public void setOnClick(TabClickListener tabClickListener) {
        this.tabClickListener = tabClickListener;
    }

    public void showRefresh(TriggerRefresh triggerRefresh) {
        this.triggerRefresh = triggerRefresh;
    }

    private void initTab() {
        ConfigManager configManager = new ConfigManager(this.activityContext);
        this.anime = AnimationUtils.loadAnimation(this.activityContext, 2130772094);
        this.view = (LinearLayout) LayoutInflater.from(this.activityContext).inflate(2131493345, (ViewGroup) null);
        this.huodongRl = (RelativeLayout) this.view.findViewById(2131298015);
        this.serviceRl = (RelativeLayout) this.view.findViewById(2131298022);
        this.homeButtonLayout = (LinearLayout) this.view.findViewById(2131298009);
        this.serviceButtonLayout = (LinearLayout) this.view.findViewById(2131298020);
        this.huodongButtonLayout = (LinearLayout) this.view.findViewById(2131298014);
        this.shopButtonLayout = (LinearLayout) this.view.findViewById(2131298026);
        this.adButtonLayout = (LinearLayout) this.view.findViewById(2131298004);
        this.userButtonLayout = (LinearLayout) this.view.findViewById(2131298030);
        this.homeButtonImage = (ImageView) this.view.findViewById(2131298007);
        this.serviceButtonImage = (ImageView) this.view.findViewById(2131298018);
        this.huodongButtonImage = (ImageView) this.view.findViewById(2131298012);
        this.shopButtonImage = (ImageView) this.view.findViewById(2131298024);
        this.adButtonImage = (ImageView) this.view.findViewById(2131298002);
        this.userButtonImage = (ImageView) this.view.findViewById(2131298028);
        this.homeButtonText = (TextView) this.view.findViewById(2131298011);
        this.serviceButtonText = (TextView) this.view.findViewById(2131298023);
        this.huodongButtonText = (TextView) this.view.findViewById(2131298016);
        this.shopButtonText = (TextView) this.view.findViewById(2131298027);
        this.adButtonText = (TextView) this.view.findViewById(2131298006);
        this.userButtonText = (TextView) this.view.findViewById(2131298032);
        this.homeNoticImage = (ImageView) this.view.findViewById(2131298010);
        this.userNoticeImage = (TextView) this.view.findViewById(2131298031);
        this.adNoticeImage = (ImageView) this.view.findViewById(2131298005);
        this.serviceNoticeImage = (ImageView) this.view.findViewById(2131298021);
        this.homeJiaoBiaoImage = (ImageView) this.view.findViewById(2131298008);
        this.serviceJiaoBiaoImage = (ImageView) this.view.findViewById(2131298019);
        this.huodongJiaoBiaoImage = (ImageView) this.view.findViewById(2131298013);
        this.shopJiaoBiaoImage = (ImageView) this.view.findViewById(2131298025);
        this.adJiaoBiaoImage = (ImageView) this.view.findViewById(2131298003);
        this.userJiaoBiaoImage = (ImageView) this.view.findViewById(2131298029);
        this.homeIcon = configManager.getHomeIcon();
        this.serviceIcon = configManager.getServiceIcon();
        this.shangchengIcon = configManager.getMarketIcon();
        this.faxianIcon = configManager.getFindIcon();
        this.getMyIcon = configManager.getMyIcon();
        this.getActivityIcon = configManager.getActivityIcon();
        this.homeHide = configManager.getHomeHide();
        this.activityHide = configManager.getActivityHide();
        this.serviceHide = configManager.getServiceHide();
        this.marketHide = configManager.getMarketHide();
        this.caiFuHide = configManager.getCaiFuHide();
        this.myHide = configManager.getMyHide();
        MyClick myClick = new MyClick();
        this.homeButtonLayout.setOnClickListener(myClick);
        this.serviceButtonLayout.setOnClickListener(myClick);
        this.huodongButtonLayout.setOnClickListener(myClick);
        this.shopButtonLayout.setOnClickListener(myClick);
        this.adButtonLayout.setOnClickListener(myClick);
        this.userButtonLayout.setOnClickListener(myClick);
        setTabText();
        changeTabUI(TABTAG_HOME);
        addView(this.view);
    }

    public void setTabText() {
        this.homeButtonText.setText(LanguageUtil.getInstance().getText("首页", this.homeButtonText));
        this.serviceButtonText.setText(LanguageUtil.getInstance().getText("服务", this.serviceButtonText));
        this.huodongButtonText.setText(LanguageUtil.getInstance().getText("活动", this.huodongButtonText));
        this.shopButtonText.setText(LanguageUtil.getInstance().getText("商城", this.shopButtonText));
        this.adButtonText.setText(LanguageUtil.getInstance().getText("财富", this.adButtonText));
        this.userButtonText.setText(LanguageUtil.getInstance().getText("我的", this.userButtonText));
    }

    public void changeTabIcon(String str, String str2) {
        String[] split;
        try {
            this.textPictureColor = "";
            this.customTabIconLoadMap.clear();
            this.loadCount = 0;
            if (!TextUtils.isEmpty(str) && str.contains(",") && (split = str.split(",")) != null && split.length >= 5) {
                this.textPictureColor = str2;
                for (final int i = 0; i < 5; i++) {
                    String str3 = split[i];
                    if (!TextUtils.isEmpty(str3) && URLUtil.isNetworkUrl(str3)) {
                        ShowImageUtils.showImageViewPreLoad(this.activityContext, str3, new RequestListener<Drawable>() { // from class: com.sinovatech.unicom.basic.ui.view.MainTabView.1
                            @Override // com.bumptech.glide.request.RequestListener
                            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                                MainTabView.access$108(MainTabView.this);
                                MainTabView.this.customTabIconLoadMap.put(MainTabView.this.tabTagArry[i], null);
                                if (MainTabView.this.loadCount >= 5) {
                                    MainTabView.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.view.MainTabView.1.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainTabView.this.changeTabUI(MainTabView.currentTab);
                                        }
                                    });
                                    return false;
                                }
                                return false;
                            }

                            @Override // com.bumptech.glide.request.RequestListener
                            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                                MainTabView.access$108(MainTabView.this);
                                MainTabView.this.customTabIconLoadMap.put(MainTabView.this.tabTagArry[i], drawable);
                                if (MainTabView.this.loadCount >= 5) {
                                    MainTabView.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.view.MainTabView.1.2
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainTabView.this.changeTabUI(MainTabView.currentTab);
                                        }
                                    });
                                    return false;
                                }
                                return false;
                            }
                        });
                    } else {
                        this.customTabIconLoadMap.clear();
                        this.textPictureColor = str2;
                        break;
                    }
                }
            }
            changeTabUI(currentTab);
        } catch (Exception e) {
            MsLogUtil.m7978e(String.format("更新首页底部icon异常:%s", e.getMessage()));
        }
    }

    private void resetJiaoBiao(String str, String str2, ImageView imageView, String str3) {
        boolean isGongJiRi = UIUtils.isGongJiRi();
        if (TextUtils.isEmpty(str2) || !App.hasLogined() || isGongJiRi) {
            imageView.setVisibility(8);
            checkNotice(isGongJiRi);
            return;
        }
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        String string = sharePreferenceUtil.getString(str + UserManager.getInstance().getCurrentPhoneNumber());
        if (TextUtils.equals("1", str3)) {
            showImg(str2, imageView);
        } else if (!TextUtils.equals(string, str2)) {
            showImg(str2, imageView);
        } else {
            imageView.setVisibility(8);
            checkNotice(UIUtils.isGongJiRi());
        }
    }

    private void showImg(String str, final ImageView imageView) {
        GlideApp.with((FragmentActivity) this.activityContext).load(str).listener(new RequestListener<Drawable>() { // from class: com.sinovatech.unicom.basic.ui.view.MainTabView.2
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                imageView.setVisibility(8);
                MainTabView.this.checkNotice(UIUtils.isGongJiRi());
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                imageView.setVisibility(0);
                MainTabView.this.checkNotice(UIUtils.isGongJiRi());
                return false;
            }
        }).into(imageView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.view.MainTabView$MyClick */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MyClick implements View.OnClickListener {
        private MyClick() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(final View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (MainTabView.this.tabClickListener == null) {
                UIUtils.toast("未设置点击事件的监听");
                NBSActionInstrumentation.onClickEventExit();
            } else if (PopWebViewEvent.currentType != 5 || !FuWuConstant.isBianJiState || MainTabView.this.activityContext == null || !(MainTabView.this.activityContext instanceof MainActivity)) {
                MainTabView.this.clickTab(view);
                NBSActionInstrumentation.onClickEventExit();
            } else {
                ((ServiceFragment) ((MainActivity) MainTabView.this.activityContext).getSupportFragmentManager().findFragmentByTag(MainActivity.Fragment_Service)).showSave(true, new ServiceFragment.SaveCallback() { // from class: com.sinovatech.unicom.basic.ui.view.MainTabView.MyClick.1
                    @Override // com.sinovatech.unicom.basic.p315ui.fragment.ServiceFragment.SaveCallback
                    public void onSaveSuccess(boolean z) {
                        MainTabView.this.clickTab(view);
                    }

                    @Override // com.sinovatech.unicom.basic.p315ui.fragment.ServiceFragment.SaveCallback
                    public void onSaveFailed(boolean z) {
                        MainTabView.this.clickTab(view);
                    }
                });
                NBSActionInstrumentation.onClickEventExit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickTab(View view) {
        switch (view.getId()) {
            case 2131298004:
                if (!TextUtils.equals(currentTab, TABTAG_FIND)) {
                    saveJiaoBiaoUrl(ConfigManager.config_caifu_jiaobiao, this.faxianIcon);
                    try {
                        PvCurrencyLogUtils.pvLogTabClick("4990101", "底部标签-财富");
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                }
                changeTabUI(TABTAG_FIND);
                this.tabClickListener.clickFind();
                this.adButtonLayout.startAnimation(this.anime);
                return;
            case 2131298009:
                if (!TextUtils.equals(currentTab, TABTAG_HOME)) {
                    saveJiaoBiaoUrl(ConfigManager.config_home_jiaobiao, this.homeIcon);
                    try {
                        PvCurrencyLogUtils.pvLogTabClick("1990101", "底部标签-首页");
                    } catch (Exception e2) {
                        MsLogUtil.m7978e(e2.getMessage());
                    }
                    changeTabUI(TABTAG_HOME);
                    this.tabClickListener.clickHome();
                    return;
                } else if (this.isSelectHomeTab) {
                    TriggerRefresh triggerRefresh = this.triggerRefresh;
                    if (triggerRefresh != null) {
                        triggerRefresh.showRefresh();
                    }
                    this.isSelectHomeTab = false;
                    new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.view.MainTabView.3
                        @Override // java.lang.Runnable
                        public void run() {
                            MainTabView.this.isSelectHomeTab = true;
                        }
                    }, 300L);
                    return;
                } else {
                    MsLogUtil.m7979d("clickHome", "重复点击 不生效");
                    return;
                }
            case 2131298014:
                if (!TextUtils.equals(currentTab, TABTAG_HUODONG)) {
                    saveJiaoBiaoUrl(ConfigManager.config_activity_jiaobiao, this.getActivityIcon);
                    try {
                        PvCurrencyLogUtils.pvLogTabClick("2990101", "底部标签-活动");
                    } catch (Exception e3) {
                        MsLogUtil.m7978e(e3.getMessage());
                    }
                }
                changeTabUI(TABTAG_HUODONG);
                this.tabClickListener.clickHuodong();
                this.huodongButtonLayout.startAnimation(this.anime);
                return;
            case 2131298020:
                if (!TextUtils.equals(currentTab, TABTAG_SERVICE)) {
                    saveJiaoBiaoUrl(ConfigManager.config_service_jiaobiao, this.serviceIcon);
                    try {
                        PvCurrencyLogUtils.pvLogTabClick("2990101", "底部标签-服务");
                    } catch (Exception e4) {
                        MsLogUtil.m7978e(e4.getMessage());
                    }
                }
                changeTabUI(TABTAG_SERVICE);
                this.tabClickListener.clickService();
                this.serviceButtonLayout.startAnimation(this.anime);
                return;
            case 2131298026:
                if (!TextUtils.equals(currentTab, TABTAG_SHOP)) {
                    saveJiaoBiaoUrl(ConfigManager.config_market_jiaobiao, this.shangchengIcon);
                    try {
                        PvCurrencyLogUtils.pvLogTabClick("3990101", "底部标签-商城");
                    } catch (Exception e5) {
                        MsLogUtil.m7978e(e5.getMessage());
                    }
                }
                changeTabUI(TABTAG_SHOP);
                this.tabClickListener.clickShop();
                this.shopButtonLayout.startAnimation(this.anime);
                return;
            case 2131298030:
                if (!TextUtils.equals(currentTab, TABTAG_USER)) {
                    saveJiaoBiaoUrl(ConfigManager.config_my_jiaobiao, this.getMyIcon);
                    try {
                        PvCurrencyLogUtils.pvLogTabClick("5990101", "底部标签-我的");
                    } catch (Exception e6) {
                        MsLogUtil.m7978e(e6.getMessage());
                    }
                }
                changeTabUI(TABTAG_USER);
                this.tabClickListener.clickUser();
                this.userButtonLayout.startAnimation(this.anime);
                return;
            default:
                return;
        }
    }

    private void saveJiaoBiaoUrl(String str, String str2) {
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        sharePreferenceUtil.putString(str + UserManager.getInstance().getCurrentPhoneNumber(), str2);
    }

    public void changeTabUIMain(String str) {
        if (MainActivity.Fragment_Home.equals(str)) {
            changeTabUI(TABTAG_HOME);
        } else if (MainActivity.Fragment_Service.equals(str)) {
            changeTabUI(TABTAG_SERVICE);
        } else if (MainActivity.Fragment_YiWang.equals(str)) {
            changeTabUI(TABTAG_HUODONG);
        } else if (MainActivity.Fragment_Shop.equals(str)) {
            changeTabUI(TABTAG_SHOP);
        } else if (MainActivity.Fragment_Ad.equals(str)) {
            changeTabUI(TABTAG_FIND);
        } else if (MainActivity.Fragment_User.equals(str)) {
            changeTabUI(TABTAG_USER);
        }
    }

    public void setIsYW(boolean z) {
        if (z) {
            this.huodongRl.setVisibility(0);
            this.serviceRl.setVisibility(8);
            return;
        }
        this.huodongRl.setVisibility(8);
        this.serviceRl.setVisibility(0);
    }

    public void setRedPoint(String str, int i) {
        if (TextUtils.equals(str, TABTAG_HOME)) {
            this.homeNoticImageVisible = i;
        } else if (TextUtils.equals(str, TABTAG_SERVICE)) {
            this.serviceNoticeImageVisible = i;
        } else if (TextUtils.equals(str, TABTAG_FIND)) {
            this.adNoticeImageVisible = i;
        } else if (TextUtils.equals(str, TABTAG_USER) && !UserManager.getInstance().isYiwang()) {
            this.userNoticeImageVisible = i;
        }
        checkNotice(UIUtils.isGongJiRi());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkNotice(boolean z) {
        if (z) {
            this.homeNoticImage.setVisibility(8);
            this.serviceNoticeImage.setVisibility(8);
            this.adNoticeImage.setVisibility(8);
            this.userNoticeImage.setVisibility(8);
            this.homeJiaoBiaoImage.setVisibility(8);
            this.serviceJiaoBiaoImage.setVisibility(8);
            this.shopJiaoBiaoImage.setVisibility(8);
            this.adJiaoBiaoImage.setVisibility(8);
            this.userJiaoBiaoImage.setVisibility(8);
            this.huodongJiaoBiaoImage.setVisibility(8);
            return;
        }
        if (this.homeJiaoBiaoImage.getVisibility() == 0) {
            this.homeNoticImage.setVisibility(8);
        } else {
            this.homeNoticImage.setVisibility(this.homeNoticImageVisible);
        }
        if (this.serviceJiaoBiaoImage.getVisibility() == 0) {
            this.serviceNoticeImage.setVisibility(8);
        } else {
            this.serviceNoticeImage.setVisibility(this.serviceNoticeImageVisible);
        }
        if (this.adJiaoBiaoImage.getVisibility() == 0) {
            this.adNoticeImage.setVisibility(8);
        } else {
            this.adNoticeImage.setVisibility(this.adNoticeImageVisible);
        }
        if (this.userJiaoBiaoImage.getVisibility() == 0) {
            this.userNoticeImage.setVisibility(8);
        } else {
            this.userNoticeImage.setVisibility(this.userNoticeImageVisible);
        }
    }

    public void setUserNum(int i, int i2) {
        EventBusUtils.post(new ChangeNoticNumberEvent(EventBusUtils.EVENT_CHAGNE_NOTIC_NUMBER, i, i2));
        if (i > 3) {
            i = 3;
        }
        if (i2 > 3) {
            i2 = 3;
        }
        int i3 = i + i2;
        if (UIUtils.isGongJiRi()) {
            this.userNoticeImageVisible = 8;
            this.userNoticeImage.setVisibility(8);
            this.userJiaoBiaoImage.setVisibility(8);
        } else {
            if (i3 > 0) {
                this.userNoticeImage.setVisibility(0);
                this.userNoticeImageVisible = 0;
            } else {
                this.userNoticeImageVisible = 8;
                this.userNoticeImage.setVisibility(8);
            }
            if (this.userJiaoBiaoImage.getVisibility() == 0 && this.userNoticeImage.getVisibility() == 0) {
                this.userJiaoBiaoImage.setVisibility(8);
            }
        }
        try {
            new JiaobiaoUtils(this.activityContext).sendBadgeNumber(i, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGJR() {
        try {
            UIUtils.setGJR(this.homeButtonLayout);
        } catch (Exception e) {
            MsLogUtil.m7977e("MainTabView", "设置公祭日异常:" + e.getMessage());
        }
    }
}
