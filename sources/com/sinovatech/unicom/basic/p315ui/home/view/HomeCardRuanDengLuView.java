package com.sinovatech.unicom.basic.p315ui.home.view;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p314po.HeaderEntity;
import com.sinovatech.unicom.basic.p314po.LoginMemberEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeLogEntity;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginMemberManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/* renamed from: com.sinovatech.unicom.basic.ui.home.view.HomeCardRuanDengLuView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeCardRuanDengLuView extends LinearLayout {
    private static final String TAG = "HomeCardRuanDengLuView";
    private Activity activityContext;
    private HeaderEntity entity;
    private HeaderEntity headerEntity;
    private LinearLayout huaFeiTiShi;
    private ImageView mImgTiShi;
    private TextView mTvNumber;
    private TextView mTvTime;
    private TextView mTvType;
    private View mView;
    private LinearLayout memberLayout;

    public HomeCardRuanDengLuView(Activity activity, HeaderEntity headerEntity) {
        super(activity);
        this.headerEntity = headerEntity;
        init(activity);
    }

    private void init(Activity activity) {
        try {
            this.activityContext = activity;
            this.mView = LayoutInflater.from(activity).inflate(2131493160, this);
            this.mTvTime = (TextView) this.mView.findViewById(2131297154);
            this.memberLayout = (LinearLayout) this.mView.findViewById(2131297125);
            this.mTvNumber = (TextView) this.mView.findViewById(2131297127);
            this.mTvType = (TextView) this.mView.findViewById(2131297128);
            this.mImgTiShi = (ImageView) this.mView.findViewById(2131297155);
            this.huaFeiTiShi = (LinearLayout) this.mView.findViewById(2131297126);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化首页卡片底部时间控件异常" + e.getMessage());
        }
    }

    public void initData(HeaderEntity headerEntity) {
        this.headerEntity = headerEntity;
        setData(headerEntity);
        setMemberInfo();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0046 A[Catch: Exception -> 0x006e, TryCatch #0 {Exception -> 0x006e, blocks: (B:2:0x0000, B:4:0x0015, B:7:0x0022, B:9:0x0039, B:13:0x0050, B:12:0x0046, B:8:0x0032), top: B:18:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setData(final com.sinovatech.unicom.basic.p314po.HeaderEntity r5) {
        /*
            r4 = this;
            r4.headerEntity = r5     // Catch: java.lang.Exception -> L6e
            com.sinovatech.unicom.basic.ui.home.manager.UnicomHomeLogUtils r0 = com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils.getInstance()     // Catch: java.lang.Exception -> L6e
            java.lang.String r1 = "LOG_TYPE_RDL_RQ"
            r0.removeLog(r1)     // Catch: java.lang.Exception -> L6e
            java.lang.String r0 = r5.getFlushUpdateTime()     // Catch: java.lang.Exception -> L6e
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L6e
            if (r0 != 0) goto L32
            java.lang.String r0 = "null"
            java.lang.String r1 = r5.getFlushUpdateTime()     // Catch: java.lang.Exception -> L6e
            boolean r0 = r0.equals(r1)     // Catch: java.lang.Exception -> L6e
            if (r0 == 0) goto L22
            goto L32
        L22:
            android.widget.LinearLayout r0 = r4.huaFeiTiShi     // Catch: java.lang.Exception -> L6e
            r1 = 0
            r0.setVisibility(r1)     // Catch: java.lang.Exception -> L6e
            android.widget.TextView r0 = r4.mTvTime     // Catch: java.lang.Exception -> L6e
            java.lang.String r1 = r5.getFlushUpdateTime()     // Catch: java.lang.Exception -> L6e
            r0.setText(r1)     // Catch: java.lang.Exception -> L6e
            goto L39
        L32:
            android.widget.LinearLayout r0 = r4.huaFeiTiShi     // Catch: java.lang.Exception -> L6e
            r1 = 8
            r0.setVisibility(r1)     // Catch: java.lang.Exception -> L6e
        L39:
            java.lang.String r0 = r5.getAskUrl()     // Catch: java.lang.Exception -> L6e
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L6e
            if (r0 == 0) goto L46
            java.lang.String r0 = ""
            goto L50
        L46:
            android.widget.TextView r0 = r4.mTvTime     // Catch: java.lang.Exception -> L6e
            java.lang.CharSequence r0 = r0.getText()     // Catch: java.lang.Exception -> L6e
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L6e
        L50:
            android.widget.LinearLayout r1 = r4.huaFeiTiShi     // Catch: java.lang.Exception -> L6e
            com.sinovatech.unicom.basic.ui.home.view.-$$Lambda$HomeCardRuanDengLuView$DPgGrM78Iz1WWnP1OBsEpWX8t98 r2 = new com.sinovatech.unicom.basic.ui.home.view.-$$Lambda$HomeCardRuanDengLuView$DPgGrM78Iz1WWnP1OBsEpWX8t98     // Catch: java.lang.Exception -> L6e
            r2.<init>()     // Catch: java.lang.Exception -> L6e
            r1.setOnClickListener(r2)     // Catch: java.lang.Exception -> L6e
            com.sinovatech.unicom.basic.ui.home.manager.UnicomHomeLogUtils r5 = com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils.getInstance()     // Catch: java.lang.Exception -> L6e
            java.lang.String r0 = "LOG_TYPE_RDL_RQ"
            com.sinovatech.unicom.basic.ui.home.entity.HomeLogEntity r1 = new com.sinovatech.unicom.basic.ui.home.entity.HomeLogEntity     // Catch: java.lang.Exception -> L6e
            java.lang.String r2 = "1040101"
            java.lang.String r3 = "余量展示时间"
            r1.<init>(r2, r3)     // Catch: java.lang.Exception -> L6e
            r5.putLockLogData(r0, r1)     // Catch: java.lang.Exception -> L6e
            goto L8a
        L6e:
            r5 = move-exception
            java.lang.String r0 = "HomeCardRuanDengLuView"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "设置首页卡片底部时间view异常"
            r1.append(r2)
            java.lang.String r5 = r5.getMessage()
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7977e(r0, r5)
        L8a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.home.view.HomeCardRuanDengLuView.setData(com.sinovatech.unicom.basic.po.HeaderEntity):void");
    }

    public static /* synthetic */ void lambda$setData$0(HomeCardRuanDengLuView homeCardRuanDengLuView, HeaderEntity headerEntity, String str, View view) {
        IntentManager.gotoWebViewActivity(homeCardRuanDengLuView.activityContext, headerEntity.getAskUrl(), "");
        try {
            PvCurrencyLogUtils.pvLogMainDJ("1040101", headerEntity.getAskUrl() + "", "", str + "", "余量展示时间");
            UnicomHomeLogUtils.getInstance().clickLog("1040101", "余量展示时间");
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void setMemberInfo() {
        try {
            this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.view.HomeCardRuanDengLuView.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (!TextUtils.equals("1", UserManager.getInstance().getMainMemberFlag())) {
                            HomeCardRuanDengLuView.this.memberLayout.setVisibility(8);
                            return;
                        }
                        if (LoginMemberManager.getMemberBoxAllData() == null || LoginMemberManager.getMemberBoxAllData().size() == 0) {
                            LoginMemberManager.saveMemberEntity(new LoginMemberEntity("", "", LoginMemberManager.Phone(UserManager.getInstance().getUserAccountName()), "custom_number", "0", "", ""));
                        }
                        MsLogUtil.m7979d(HomeCardRuanDengLuView.TAG, "App.hasLogined() = " + App.hasLogined());
                        if (App.hasLogined() && LoginMemberManager.getCurrentMemberData() != null && LoginMemberManager.getMemberData(HomeCardRuanDengLuView.this.activityContext).size() > 0) {
                            HomeCardRuanDengLuView.this.memberLayout.setVisibility(0);
                            final LoginMemberEntity currentMemberData = LoginMemberManager.getCurrentMemberData();
                            HomeCardRuanDengLuView.this.mTvNumber.setText(currentMemberData.getNum());
                            final String typeName = currentMemberData.getTypeName();
                            if (!TextUtils.isEmpty(typeName)) {
                                HomeCardRuanDengLuView.this.mTvType.setVisibility(0);
                                HomeCardRuanDengLuView.this.mTvType.setText(typeName);
                            } else {
                                HomeCardRuanDengLuView.this.mTvType.setVisibility(8);
                                HomeCardRuanDengLuView.this.mTvType.setText("");
                            }
                            UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_RDL_HM, new HomeLogEntity("1040102", "软登录"));
                            HomeCardRuanDengLuView.this.memberLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.view.HomeCardRuanDengLuView.1.1
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view) {
                                    NBSActionInstrumentation.onClickEventEnter(view, this);
                                    Tracker.onClick(view);
                                    LoginMemberManager.showMemberDialog(HomeCardRuanDengLuView.this.activityContext);
                                    try {
                                        PvCurrencyLogUtils.pvLogMainDJ("1040102", "", typeName + "", currentMemberData.getNum() + "", "软登录");
                                        UnicomHomeLogUtils.getInstance().clickLog("1040102", "软登录");
                                    } catch (Exception e) {
                                        MsLogUtil.m7978e(e.getMessage());
                                    }
                                    NBSActionInstrumentation.onClickEventExit();
                                }
                            });
                        } else if (App.hasLogined() && LoginMemberManager.getLoginTuiJianBoxAllData().size() > 0) {
                            MsLogUtil.m7979d(HomeCardRuanDengLuView.TAG, "成员号码为空设置推荐数据");
                            HomeCardRuanDengLuView.this.setTuiJianData();
                        } else {
                            MsLogUtil.m7979d(HomeCardRuanDengLuView.TAG, "隐藏");
                            HomeCardRuanDengLuView.this.memberLayout.setVisibility(8);
                            UnicomHomeLogUtils.getInstance().removeLog(UnicomHomeLogUtils.LOG_TYPE_RDL_HM);
                        }
                    } catch (Exception e) {
                        HomeCardRuanDengLuView.this.memberLayout.setVisibility(8);
                        MsLogUtil.m7978e(e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "设置卡片底部成员列表数据异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTuiJianData() {
        try {
            final LoginMemberEntity currentMemberData = LoginMemberManager.getCurrentMemberData();
            this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.view.HomeCardRuanDengLuView.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        HomeCardRuanDengLuView.this.memberLayout.setVisibility(0);
                        final String str = "";
                        if (currentMemberData != null) {
                            HomeCardRuanDengLuView.this.mTvNumber.setText(currentMemberData.getNum());
                            str = currentMemberData.getTypeName();
                            if (TextUtils.isEmpty(str)) {
                                HomeCardRuanDengLuView.this.mTvType.setText("");
                                HomeCardRuanDengLuView.this.mTvType.setVisibility(8);
                            } else {
                                HomeCardRuanDengLuView.this.mTvType.setText(str);
                                HomeCardRuanDengLuView.this.mTvType.setVisibility(0);
                            }
                        } else {
                            HomeCardRuanDengLuView.this.mTvNumber.setText(LoginMemberManager.Phone(UserManager.getInstance().getCurrentPhoneNumber()));
                            HomeCardRuanDengLuView.this.mTvType.setText("");
                            HomeCardRuanDengLuView.this.mTvType.setVisibility(8);
                        }
                        UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_RDL_HM, new HomeLogEntity("1040102", "软登录"));
                        HomeCardRuanDengLuView.this.memberLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.view.HomeCardRuanDengLuView.2.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                NBSActionInstrumentation.onClickEventEnter(view, this);
                                Tracker.onClick(view);
                                LoginMemberManager.showMemberDialog(HomeCardRuanDengLuView.this.activityContext);
                                try {
                                    PvCurrencyLogUtils.pvLogMainDJ("1040102", "", str + "", HomeCardRuanDengLuView.this.mTvNumber.getText().toString() + "", "软登录");
                                    UnicomHomeLogUtils.getInstance().clickLog("1040102", "软登录");
                                } catch (Exception e) {
                                    MsLogUtil.m7978e(e.getMessage());
                                }
                                NBSActionInstrumentation.onClickEventExit();
                            }
                        });
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, "设置智慧推荐的数据异常:" + e.getMessage());
        }
    }

    public void updateMemberInfo() {
        getLoginMember();
    }

    private void getLoginMember() {
        if (App.hasLogined()) {
            MsLogUtil.m7979d(TAG, "已经登录 limitInterface = " + UserManager.getInstance().getLimitInterface());
            if (TextUtils.equals("1", UserManager.getInstance().getLimitInterface()) && TextUtils.equals("1", UserManager.getInstance().getMainMemberFlag())) {
                UIUtils.logD("tuijian", "走单独接口获取数据 ======");
                getMemberAndTuiJianData();
                return;
            }
            UIUtils.logD("tuijian", "老接口数据 =====");
            setMemberInfo();
        }
    }

    private void getMemberAndTuiJianData() {
        Observable.zip(LoginMemberManager.loadLoginMemberData(this.activityContext), LoginMemberManager.loadLoginTuiJainData(), new BiFunction<Boolean, Boolean, Boolean>() { // from class: com.sinovatech.unicom.basic.ui.home.view.HomeCardRuanDengLuView.4
            @Override // io.reactivex.functions.BiFunction
            public Boolean apply(Boolean bool, Boolean bool2) throws Exception {
                try {
                    HomeCardRuanDengLuView.this.setMemberInfo();
                    return null;
                } catch (Exception e) {
                    UIUtils.logE(e.getMessage());
                    return null;
                }
            }
        }).subscribe(new Observer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.home.view.HomeCardRuanDengLuView.3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(Boolean bool) {
                Log.d("HomeFragment", "onNext: =" + bool);
            }
        });
    }
}
