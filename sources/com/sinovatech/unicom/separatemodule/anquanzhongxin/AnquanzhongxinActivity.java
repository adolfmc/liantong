package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import android.content.Intent;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p315ui.activity.LoginActivity;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.basic.view.MeasureListView;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinSMSDialog;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzongxinAdapter;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.security.LockPatternUtil;
import io.objectbox.Box;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AnquanzhongxinActivity extends BaseActivity {
    private static final int MIN_DELAY_TIME = 1000;
    private static final int SELECT_APP = 2;
    private static final int SELECT_CUSTOM = 3;
    private static final int SELECT_NONE = 1;
    private static final String TAG = "AnquanzhongxinActivity";
    private static long lastClickTime;
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private Box<AnquanWhiteEntity> anquanWhiteEntityBox;
    AnquanzhongxinManager anquanzhongxinManager;
    private AnquanzongxinAdapter anquanzongxinAdapter;
    private ImageView backImage;
    private Box<AnquanzhongxinEntity> box;
    private AnquanzhongxinEntity boxEntity;
    private TextView desText;
    private FingerScreenManager fingerScreenManager;
    private ImageView groupCheck1;
    private ImageView groupCheck2;
    private ImageView groupCheck3;
    private ImageView groupImage1;
    private ImageView groupImage2;
    private ImageView groupImage3;
    private LinearLayout groupLayout1;
    private LinearLayout groupLayout2;
    private LinearLayout groupLayout3;
    private TextView groupText1;
    private TextView groupText2;
    private TextView groupText3;
    private LinearLayout listViewLayout;
    private LockScreenManager lockScreenManager;
    private MeasureListView measureListView;
    private CustomePorgressDialog porgressDialog;
    private LinearLayout selectedLayout;
    private TextView switchTip;
    private TextView tipText;
    private ToggleButton toggleButton1;
    private ToggleButton toggleButton2;
    private ToggleButton toggleButton3;
    private LinearLayout toggleLayout1;
    private LinearLayout toggleLayout2;
    private LinearLayout toggleLayout3;
    private LinearLayout toggleLayout4;
    private boolean userChecked;
    private UserManager userManager;
    String remark4 = "";
    private String businessCode = "";
    private boolean isOnBack = false;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 66);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C82141 implements AvoidOnResult.Callback {
        C82141() {
        }

        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
        public void onActivityResult(int i, Intent intent) {
            if (App.hasLogined()) {
                AnquanzhongxinActivity.this.init();
            } else {
                AnquanzhongxinActivity.this.finish();
            }
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        if (this.isOnBack) {
            setInitState();
            this.isOnBack = false;
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        this.isOnBack = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init() {
        this.userManager = UserManager.getInstance();
        this.box = App.getBoxStore().boxFor(AnquanzhongxinEntity.class);
        this.anquanWhiteEntityBox = App.getBoxStore().boxFor(AnquanWhiteEntity.class);
        this.boxEntity = getEntity();
        this.backImage = (ImageView) findViewById(2131296340);
        this.groupLayout1 = (LinearLayout) findViewById(2131296344);
        this.groupLayout2 = (LinearLayout) findViewById(2131296348);
        this.groupLayout3 = (LinearLayout) findViewById(2131296352);
        this.groupImage1 = (ImageView) findViewById(2131296346);
        this.groupImage2 = (ImageView) findViewById(2131296350);
        this.groupImage3 = (ImageView) findViewById(2131296354);
        this.groupText1 = (TextView) findViewById(2131296347);
        this.groupText2 = (TextView) findViewById(2131296351);
        this.groupText3 = (TextView) findViewById(2131296355);
        this.groupCheck1 = (ImageView) findViewById(2131296345);
        this.groupCheck2 = (ImageView) findViewById(2131296349);
        this.groupCheck3 = (ImageView) findViewById(2131296353);
        this.desText = (TextView) findViewById(2131296363);
        this.toggleButton1 = (ToggleButton) findViewById(2131296364);
        this.toggleButton2 = (ToggleButton) findViewById(2131296366);
        this.toggleButton3 = (ToggleButton) findViewById(2131296368);
        this.toggleLayout1 = (LinearLayout) findViewById(2131296365);
        this.toggleLayout2 = (LinearLayout) findViewById(2131296367);
        this.toggleLayout3 = (LinearLayout) findViewById(2131296369);
        this.toggleLayout4 = (LinearLayout) findViewById(2131296370);
        this.selectedLayout = (LinearLayout) findViewById(2131296358);
        this.listViewLayout = (LinearLayout) findViewById(2131296362);
        this.measureListView = (MeasureListView) findViewById(2131296361);
        this.switchTip = (TextView) findViewById(2131296360);
        this.tipText = (TextView) findViewById(2131296356);
        int supportFinggerNum = DeviceHelper.getSupportFinggerNum();
        if (supportFinggerNum == 3 || supportFinggerNum == 1) {
            this.toggleLayout1.setVisibility(8);
        } else {
            this.toggleLayout1.setVisibility(0);
        }
        this.anquanzhongxinManager = new AnquanzhongxinManager(this.activityContext);
        this.fingerScreenManager = new FingerScreenManager(this.activityContext);
        this.porgressDialog = new CustomePorgressDialog(this.activityContext);
        this.anquanzongxinAdapter = new AnquanzongxinAdapter(this.activityContext);
        this.measureListView.setAdapter((ListAdapter) this.anquanzongxinAdapter);
        if (App.hasLogined() && !this.userManager.isYiwang() && !LoginManager.isKuandaiOrGuhua()) {
            this.groupLayout3.setVisibility(0);
        } else {
            this.groupLayout3.setVisibility(8);
        }
        this.businessCode = getIntent().getStringExtra("businessCode");
        final List<AnquanWhiteEntity> whiteList = getWhiteList();
        if (!this.boxEntity.isSelected3() && !this.boxEntity.isSelected4() && !this.boxEntity.isSelected5()) {
            for (AnquanWhiteEntity anquanWhiteEntity : whiteList) {
                anquanWhiteEntity.setSelected(false);
            }
        }
        if (whiteList == null || whiteList.isEmpty()) {
            this.groupLayout3.setVisibility(8);
        } else if (App.hasLogined() && !this.userManager.isYiwang() && !LoginManager.isKuandaiOrGuhua()) {
            this.groupLayout3.setVisibility(0);
        } else {
            this.groupLayout3.setVisibility(8);
        }
        this.anquanzongxinAdapter.updateList(whiteList);
        this.anquanzhongxinManager.getWhiteList(new Consumer<List<AnquanWhiteEntity>>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.2
            @Override // io.reactivex.functions.Consumer
            public void accept(List<AnquanWhiteEntity> list) throws Exception {
                if (list.isEmpty()) {
                    AnquanzhongxinActivity.this.groupLayout3.setVisibility(8);
                    if (AnquanzhongxinActivity.this.boxEntity.isChooseGroup2()) {
                        AnquanzhongxinActivity.this.groupLayout2.performClick();
                    } else {
                        AnquanzhongxinActivity.this.groupLayout1.performClick();
                    }
                    AnquanzhongxinActivity.this.setWhiteList(new ArrayList());
                    return;
                }
                if (!App.hasLogined() || AnquanzhongxinActivity.this.userManager.isYiwang() || LoginManager.isKuandaiOrGuhua()) {
                    AnquanzhongxinActivity.this.groupLayout3.setVisibility(8);
                } else {
                    AnquanzhongxinActivity.this.groupLayout3.setVisibility(0);
                }
                for (AnquanWhiteEntity anquanWhiteEntity2 : list) {
                    String code = anquanWhiteEntity2.getCode();
                    for (AnquanWhiteEntity anquanWhiteEntity3 : whiteList) {
                        if (code.equals(anquanWhiteEntity3.getCode())) {
                            anquanWhiteEntity2.setSelected(anquanWhiteEntity3.isSelected());
                        }
                    }
                    if (!TextUtils.isEmpty(AnquanzhongxinActivity.this.businessCode) && AnquanzhongxinActivity.this.businessCode.equals(code)) {
                        anquanWhiteEntity2.setSelected(true);
                    }
                }
                AnquanzhongxinActivity.this.anquanzongxinAdapter.updateList(list);
                AnquanzhongxinActivity.this.setWhiteList(list);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                AnquanzhongxinActivity.this.remark4 = "失败";
                List list = whiteList;
                if (list == null || list.isEmpty()) {
                    AnquanzhongxinActivity.this.groupLayout3.setVisibility(8);
                    if (AnquanzhongxinActivity.this.boxEntity.isChooseGroup2()) {
                        AnquanzhongxinActivity.this.groupLayout2.performClick();
                        return;
                    } else {
                        AnquanzhongxinActivity.this.groupLayout1.performClick();
                        return;
                    }
                }
                AnquanzhongxinActivity.this.anquanzongxinAdapter.updateList(whiteList);
            }
        });
        if (TextUtils.isEmpty(this.businessCode) || this.userManager.isYiwang() || LoginManager.isKuandaiOrGuhua()) {
            boolean z = this.boxEntity.isSelected1() || this.boxEntity.isSelected2();
            boolean z2 = this.boxEntity.isSelected3() || this.boxEntity.isSelected4() || this.boxEntity.isSelected5();
            if (!z && !z2) {
                this.boxEntity.setChooseGroup1(true);
                this.boxEntity.setChooseGroup2(false);
                this.boxEntity.setChooseGroup3(false);
                this.boxEntity.setGroupChooseNumber(1);
            } else if (z && !z2 && !this.boxEntity.isSelected1()) {
                this.boxEntity.setChooseGroup1(false);
                this.boxEntity.setChooseGroup2(true);
                this.boxEntity.setChooseGroup3(false);
                this.boxEntity.setGroupChooseNumber(2);
            } else if (!z && !this.boxEntity.isSelected1()) {
                this.boxEntity.setChooseGroup1(false);
                this.boxEntity.setChooseGroup2(false);
                this.boxEntity.setChooseGroup3(true);
                this.boxEntity.setGroupChooseNumber(3);
            }
        } else {
            this.boxEntity.setChooseGroup1(false);
            this.boxEntity.setChooseGroup2(false);
            this.boxEntity.setChooseGroup3(true);
            this.boxEntity.setGroupChooseNumber(3);
        }
        setInitState();
        initClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        str = "";
        if (TextUtils.isEmpty(getIntent().getStringExtra("businessCode"))) {
            str2 = "APP设置入口";
        } else {
            str2 = "第三方业务拉起入口";
            if (this.boxEntity.isSelected3() || this.boxEntity.isSelected4() || this.boxEntity.isSelected5()) {
                for (AnquanWhiteEntity anquanWhiteEntity : getWhiteList()) {
                    if (anquanWhiteEntity.isSelected()) {
                        sb.append(anquanWhiteEntity.getName());
                        sb.append(",");
                    }
                }
            }
        }
        if (this.boxEntity.isChooseGroup1()) {
            PvCurrencyLogUtils.pvAnquanzhongxin(str2, sb.toString(), "无需保护", "", "");
        }
        if (this.boxEntity.isChooseGroup2()) {
            str = this.boxEntity.isSelected1() ? "指纹解锁" : "";
            if (this.boxEntity.isSelected2()) {
                str = "手势解锁";
            }
            PvCurrencyLogUtils.pvAnquanzhongxin(str2, sb.toString(), "启动APP时", str, "");
        }
        if (this.boxEntity.isChooseGroup3()) {
            if (this.boxEntity.isSelected3()) {
                str = "指纹解锁";
            }
            if (this.boxEntity.isSelected4()) {
                str = "手势解锁";
            }
            if (this.boxEntity.isSelected5()) {
                str = "短信验证码解锁";
            }
            PvCurrencyLogUtils.pvAnquanzhongxin(str2, sb.toString(), "自定义验证", str, this.remark4);
        }
    }

    private void initClick() {
        this.backImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$tNuqRBrvR1rz6z97OTULOM7-hXw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AnquanzhongxinActivity.lambda$initClick$0(AnquanzhongxinActivity.this, view);
            }
        });
        this.groupLayout1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$x_IF0ttElZDH3hnvSlTv-d1nuzU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AnquanzhongxinActivity.lambda$initClick$1(AnquanzhongxinActivity.this, view);
            }
        });
        this.groupLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$EKZxkIjuzZeankoOJxKBvqhxp-M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AnquanzhongxinActivity.lambda$initClick$2(AnquanzhongxinActivity.this, view);
            }
        });
        this.groupLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$uPlwBIVCfGrKAlDCsVMjz9m4sms
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AnquanzhongxinActivity.lambda$initClick$3(AnquanzhongxinActivity.this, view);
            }
        });
        if (this.boxEntity.getGroupChooseNumber() == 2) {
            this.toggleButton1.setChecked(this.boxEntity.isSelected1());
        } else if (this.boxEntity.getGroupChooseNumber() == 3) {
            this.toggleButton1.setChecked(this.boxEntity.isSelected3());
        }
        this.toggleButton1.setOnClickListener(new View$OnClickListenerC82266());
        if (this.boxEntity.getGroupChooseNumber() == 2) {
            this.toggleButton2.setChecked(this.boxEntity.isSelected2());
        } else if (this.boxEntity.getGroupChooseNumber() == 3) {
            this.toggleButton2.setChecked(this.boxEntity.isSelected4());
        }
        this.toggleButton2.setOnClickListener(new View$OnClickListenerC82287());
        this.toggleLayout3.setOnClickListener(new View$OnClickListenerC82308());
        this.toggleButton3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$2yBwfSLOs1x9fqIrrb_kTImroh0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AnquanzhongxinActivity.lambda$initClick$5(AnquanzhongxinActivity.this, view);
            }
        });
        this.anquanzongxinAdapter.setToggleClick(new C821510());
    }

    public static /* synthetic */ void lambda$initClick$0(AnquanzhongxinActivity anquanzhongxinActivity, View view) {
        final boolean z = anquanzhongxinActivity.boxEntity.isSelected1() || anquanzhongxinActivity.boxEntity.isSelected2();
        final boolean z2 = anquanzhongxinActivity.boxEntity.isSelected3() || anquanzhongxinActivity.boxEntity.isSelected4() || anquanzhongxinActivity.boxEntity.isSelected5();
        if (!z && anquanzhongxinActivity.boxEntity.getGroupChooseNumber() == 2) {
            anquanzhongxinActivity.showBackDialog(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.4
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    if (!z2) {
                        AnquanzhongxinActivity.this.boxEntity.setChooseGroup1(true);
                        AnquanzhongxinActivity.this.boxEntity.setChooseGroup2(false);
                        AnquanzhongxinActivity.this.boxEntity.setChooseGroup3(false);
                        AnquanzhongxinActivity.this.boxEntity.setGroupChooseNumber(1);
                    } else {
                        AnquanzhongxinActivity.this.boxEntity.setChooseGroup1(false);
                        AnquanzhongxinActivity.this.boxEntity.setChooseGroup2(false);
                        AnquanzhongxinActivity.this.boxEntity.setChooseGroup3(true);
                        AnquanzhongxinActivity.this.boxEntity.setGroupChooseNumber(3);
                    }
                    AnquanzhongxinActivity anquanzhongxinActivity2 = AnquanzhongxinActivity.this;
                    anquanzhongxinActivity2.setEntity(anquanzhongxinActivity2.boxEntity);
                    AnquanzhongxinActivity.this.log();
                    AnquanzhongxinActivity.this.finish();
                }
            });
            return;
        }
        if (!z2) {
            final List<AnquanWhiteEntity> whiteList = anquanzhongxinActivity.getWhiteList();
            boolean z3 = false;
            for (AnquanWhiteEntity anquanWhiteEntity : whiteList) {
                if (anquanWhiteEntity.isSelected()) {
                    z3 = true;
                }
            }
            if (z3 || anquanzhongxinActivity.boxEntity.getGroupChooseNumber() == 3) {
                anquanzhongxinActivity.showBackDialog(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.5
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        for (AnquanWhiteEntity anquanWhiteEntity2 : whiteList) {
                            anquanWhiteEntity2.setSelected(false);
                        }
                        AnquanzhongxinActivity.this.setWhiteList(whiteList);
                        if (!z) {
                            AnquanzhongxinActivity.this.boxEntity.setChooseGroup1(true);
                            AnquanzhongxinActivity.this.boxEntity.setChooseGroup2(false);
                            AnquanzhongxinActivity.this.boxEntity.setChooseGroup3(false);
                            AnquanzhongxinActivity.this.boxEntity.setGroupChooseNumber(1);
                        } else {
                            AnquanzhongxinActivity.this.boxEntity.setChooseGroup1(false);
                            AnquanzhongxinActivity.this.boxEntity.setChooseGroup2(true);
                            AnquanzhongxinActivity.this.boxEntity.setChooseGroup3(false);
                            AnquanzhongxinActivity.this.boxEntity.setGroupChooseNumber(2);
                        }
                        AnquanzhongxinActivity anquanzhongxinActivity2 = AnquanzhongxinActivity.this;
                        anquanzhongxinActivity2.setEntity(anquanzhongxinActivity2.boxEntity);
                        AnquanzhongxinActivity.this.log();
                        AnquanzhongxinActivity.this.finish();
                    }
                });
                return;
            }
        }
        if (!z && !z2) {
            anquanzhongxinActivity.boxEntity.setChooseGroup1(true);
            anquanzhongxinActivity.boxEntity.setChooseGroup2(false);
            anquanzhongxinActivity.boxEntity.setChooseGroup3(false);
            anquanzhongxinActivity.boxEntity.setGroupChooseNumber(1);
        } else if (z && !z2 && !anquanzhongxinActivity.boxEntity.isSelected1()) {
            anquanzhongxinActivity.boxEntity.setChooseGroup1(false);
            anquanzhongxinActivity.boxEntity.setChooseGroup2(true);
            anquanzhongxinActivity.boxEntity.setChooseGroup3(false);
            anquanzhongxinActivity.boxEntity.setGroupChooseNumber(2);
        } else if (!z && !anquanzhongxinActivity.boxEntity.isSelected1()) {
            anquanzhongxinActivity.boxEntity.setChooseGroup1(false);
            anquanzhongxinActivity.boxEntity.setChooseGroup2(false);
            anquanzhongxinActivity.boxEntity.setChooseGroup3(true);
            anquanzhongxinActivity.boxEntity.setGroupChooseNumber(3);
        }
        anquanzhongxinActivity.setEntity(anquanzhongxinActivity.boxEntity);
        anquanzhongxinActivity.log();
        anquanzhongxinActivity.finish();
    }

    public static /* synthetic */ void lambda$initClick$1(AnquanzhongxinActivity anquanzhongxinActivity, View view) {
        if (anquanzhongxinActivity.boxEntity.isSelected1() || anquanzhongxinActivity.boxEntity.isSelected2() || anquanzhongxinActivity.boxEntity.isSelected3() || anquanzhongxinActivity.boxEntity.isSelected4() || anquanzhongxinActivity.boxEntity.isSelected5()) {
            anquanzhongxinActivity.showTipdialog("您目前有安全验证方式正在生效，请关闭所有安全验证方式后，再启用无需保护。");
            return;
        }
        anquanzhongxinActivity.boxEntity.setChooseGroup1(true);
        anquanzhongxinActivity.boxEntity.setChooseGroup2(false);
        anquanzhongxinActivity.boxEntity.setChooseGroup3(false);
        anquanzhongxinActivity.boxEntity.setGroupChooseNumber(1);
        anquanzhongxinActivity.setInitState();
    }

    public static /* synthetic */ void lambda$initClick$2(AnquanzhongxinActivity anquanzhongxinActivity, View view) {
        anquanzhongxinActivity.boxEntity.setChooseGroup1(false);
        anquanzhongxinActivity.boxEntity.setChooseGroup2(true);
        anquanzhongxinActivity.boxEntity.setGroupChooseNumber(2);
        anquanzhongxinActivity.setInitState();
    }

    public static /* synthetic */ void lambda$initClick$3(AnquanzhongxinActivity anquanzhongxinActivity, View view) {
        anquanzhongxinActivity.boxEntity.setChooseGroup1(false);
        anquanzhongxinActivity.boxEntity.setChooseGroup3(true);
        anquanzhongxinActivity.boxEntity.setGroupChooseNumber(3);
        anquanzhongxinActivity.setInitState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity$6 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC82266 implements View.OnClickListener {
        View$OnClickListenerC82266() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (((ToggleButton) view).isChecked()) {
                if (DeviceHelper.getSupportFinggerNum() == 2) {
                    AnquanzhongxinActivity.this.showTipdialog("您尚未设置指纹，请在手机系统“设置与密码”中添加指纹并且打开密码。");
                    AnquanzhongxinActivity.this.setInitState();
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                } else if ((!AnquanzhongxinActivity.this.boxEntity.isSelected2() || AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() != 2) && (!AnquanzhongxinActivity.this.boxEntity.isSelected4() || AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() != 3)) {
                    if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                        AnquanzhongxinActivity.this.boxEntity.setSelected1(true);
                        AnquanzhongxinActivity.this.setInitState();
                    } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                        try {
                            AnquanzhongxinEntity m24482clone = AnquanzhongxinActivity.this.boxEntity.m24482clone();
                            m24482clone.setSelected3(true);
                            m24482clone.setSelected4(false);
                            m24482clone.setSelected5(false);
                            AnquanzhongxinActivity.this.generateKey(m24482clone, null, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    LockPatternUtil.compareLockPattern(AnquanzhongxinActivity.this.activityContext, AnquanzhongxinActivity.this.userManager.getCurrentPhoneNumber(), true, AnquanzhongxinActivity.this.businessCode, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$6$aQlcOcOE1sbWAH7ciX3eZYcwXFk
                        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                        public final void onActivityResult(int i, Intent intent) {
                            AnquanzhongxinActivity.View$OnClickListenerC82266.lambda$onClick$0(AnquanzhongxinActivity.View$OnClickListenerC82266.this, i, intent);
                        }
                    });
                }
            } else {
                AnquanzhongxinActivity anquanzhongxinActivity = AnquanzhongxinActivity.this;
                anquanzhongxinActivity.startSetFinger(false, anquanzhongxinActivity.toggleButton1, new FingerScreenManager.FingerCompleteInterface() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.6.1
                    @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                    public void success() {
                        if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                            AnquanzhongxinActivity.this.boxEntity.setSelected1(false);
                        } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                            AnquanzhongxinActivity.this.boxEntity.setSelected3(false);
                        }
                        AnquanzhongxinActivity.this.setInitState();
                    }

                    @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                    public void fail() {
                        AnquanzhongxinActivity.this.setInitState();
                    }

                    @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                    public void reset() {
                        if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                            AnquanzhongxinActivity.this.boxEntity.setSelected1(false);
                        } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                            AnquanzhongxinActivity.this.boxEntity.setSelected3(false);
                            DeviceHelper.resetAnquanzhongxinDeviceID(AnquanzhongxinActivity.this.businessCode);
                        }
                        LockPatternUtil.clearLockPattern(AnquanzhongxinActivity.this.activityContext, UserManager.getInstance().getCurrentPhoneNumber());
                        AnquanzhongxinActivity.this.box.remove(AnquanzhongxinActivity.this.boxEntity.getId());
                        AnquanzhongxinActivity.this.box.put((Box) AnquanzhongxinActivity.this.boxEntity);
                        UserManager.getInstance().removeSelectAccountName(AnquanzhongxinActivity.this.boxEntity.getMobile());
                        LoginManager.logout(AnquanzhongxinActivity.this.activityContext);
                        Intent intent = new Intent(AnquanzhongxinActivity.this.activityContext, LoginActivity.class);
                        intent.putExtra("from", "LoginBindActivity");
                        intent.putExtra("logintype", AnquanzhongxinActivity.this.boxEntity.getMobile());
                        intent.putExtra("fromLockPatternbackground", "fromLockPatternbackground");
                        intent.putExtra("fromLockPattern", "fromLockPattern");
                        AnquanzhongxinActivity.this.activityContext.startActivity(intent);
                    }

                    @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                    public void cancel() {
                        AnquanzhongxinActivity.this.setInitState();
                    }
                });
            }
            NBSActionInstrumentation.onClickEventExit();
        }

        public static /* synthetic */ void lambda$onClick$0(View$OnClickListenerC82266 view$OnClickListenerC82266, int i, Intent intent) {
            if (i == -1) {
                if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                    AnquanzhongxinActivity.this.boxEntity.setSelected1(true);
                    AnquanzhongxinActivity.this.boxEntity.setSelected2(false);
                    AnquanzhongxinActivity.this.setInitState();
                    return;
                } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                    try {
                        AnquanzhongxinEntity m24482clone = AnquanzhongxinActivity.this.boxEntity.m24482clone();
                        m24482clone.setSelected3(true);
                        m24482clone.setSelected4(false);
                        m24482clone.setSelected5(false);
                        AnquanzhongxinActivity.this.generateKey(m24482clone, null, null);
                        MsLogUtil.m7979d("generateKey", "0---" + AnquanzhongxinActivity.this.boxEntity.isSelected3());
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    return;
                }
            }
            AnquanzhongxinActivity.this.boxEntity.setSelected3(false);
            AnquanzhongxinActivity.this.setInitState();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity$7 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC82287 implements View.OnClickListener {
        View$OnClickListenerC82287() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            boolean isChecked = ((ToggleButton) view).isChecked();
            if (AnquanzhongxinActivity.isFastClick()) {
                AnquanzhongxinActivity.this.toggleButton2.setChecked(!isChecked);
                NBSActionInstrumentation.onClickEventExit();
                return;
            }
            if (isChecked) {
                if ((!AnquanzhongxinActivity.this.boxEntity.isSelected1() || AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() != 2) && (!AnquanzhongxinActivity.this.boxEntity.isSelected3() || AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() != 3)) {
                    if (LockPatternUtil.hasLockPattern(AnquanzhongxinActivity.this.activityContext, UserManager.getInstance().getCurrentPhoneNumber())) {
                        if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                            AnquanzhongxinActivity.this.boxEntity.setSelected1(false);
                            AnquanzhongxinActivity.this.boxEntity.setSelected2(true);
                            AnquanzhongxinActivity.this.setInitState();
                            UIUtils.toastLong("您已经成功设置了手势密码！");
                        } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                            try {
                                AnquanzhongxinEntity m24482clone = AnquanzhongxinActivity.this.boxEntity.m24482clone();
                                m24482clone.setSelected3(false);
                                m24482clone.setSelected4(true);
                                m24482clone.setSelected5(false);
                                AnquanzhongxinActivity.this.generateKey(m24482clone, null, null);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        LockPatternUtil.createLockPattern(AnquanzhongxinActivity.this.activityContext, AnquanzhongxinActivity.this.userManager.getCurrentPhoneNumber(), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$7$iloc0NdkXOD87FUHtiIf9rs6FRY
                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public final void onActivityResult(int i, Intent intent) {
                                AnquanzhongxinActivity.View$OnClickListenerC82287.lambda$onClick$0(AnquanzhongxinActivity.View$OnClickListenerC82287.this, i, intent);
                            }
                        });
                    }
                } else if (DeviceHelper.getSupportFinggerNum() == 2) {
                    AnquanzhongxinActivity.this.showTipdialog("您尚未设置指纹，请在手机系统“设置”中添加指纹并且打开密码。");
                    AnquanzhongxinActivity.this.setInitState();
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                } else {
                    AnquanzhongxinActivity.this.fingerScreenManager.startFingerprit(new C82291());
                }
            } else {
                LockPatternUtil.compareLockPattern(AnquanzhongxinActivity.this.activityContext, AnquanzhongxinActivity.this.userManager.getCurrentPhoneNumber(), true, AnquanzhongxinActivity.this.businessCode, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$7$DrM5tfTDv6QZXgMxfY9rStVSD_M
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public final void onActivityResult(int i, Intent intent) {
                        AnquanzhongxinActivity.View$OnClickListenerC82287.lambda$onClick$1(AnquanzhongxinActivity.View$OnClickListenerC82287.this, i, intent);
                    }
                });
            }
            NBSActionInstrumentation.onClickEventExit();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity$7$1 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class C82291 implements FingerScreenManager.FingerCompleteInterface {
            C82291() {
            }

            @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
            public void success() {
                if (LockPatternUtil.hasLockPattern(AnquanzhongxinActivity.this.activityContext, UserManager.getInstance().getCurrentPhoneNumber())) {
                    if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                        AnquanzhongxinActivity.this.boxEntity.setSelected1(false);
                        AnquanzhongxinActivity.this.boxEntity.setSelected2(true);
                        AnquanzhongxinActivity.this.setInitState();
                        UIUtils.toastLong("您已经成功设置了手势密码！");
                        return;
                    } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                        try {
                            AnquanzhongxinEntity m24482clone = AnquanzhongxinActivity.this.boxEntity.m24482clone();
                            m24482clone.setSelected3(false);
                            m24482clone.setSelected4(true);
                            m24482clone.setSelected5(false);
                            AnquanzhongxinActivity.this.generateKey(m24482clone, null, null);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    } else {
                        return;
                    }
                }
                LockPatternUtil.createLockPattern(AnquanzhongxinActivity.this.activityContext, AnquanzhongxinActivity.this.userManager.getCurrentPhoneNumber(), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$7$1$SZyCjuAygUvKit8bIRlNb3NZ-vI
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public final void onActivityResult(int i, Intent intent) {
                        AnquanzhongxinActivity.View$OnClickListenerC82287.C82291.lambda$success$0(AnquanzhongxinActivity.View$OnClickListenerC82287.C82291.this, i, intent);
                    }
                });
            }

            public static /* synthetic */ void lambda$success$0(C82291 c82291, int i, Intent intent) {
                if (i == -1) {
                    if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                        AnquanzhongxinActivity.this.boxEntity.setSelected1(false);
                        AnquanzhongxinActivity.this.boxEntity.setSelected2(true);
                        AnquanzhongxinActivity.this.setInitState();
                        UIUtils.toastLong("您已经成功设置了手势密码！");
                        return;
                    } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                        try {
                            AnquanzhongxinEntity m24482clone = AnquanzhongxinActivity.this.boxEntity.m24482clone();
                            m24482clone.setSelected3(false);
                            m24482clone.setSelected4(true);
                            m24482clone.setSelected5(false);
                            AnquanzhongxinActivity.this.generateKey(m24482clone, null, null);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    } else {
                        return;
                    }
                }
                if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                    AnquanzhongxinActivity.this.boxEntity.setSelected1(true);
                    AnquanzhongxinActivity.this.boxEntity.setSelected2(false);
                } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                    AnquanzhongxinActivity.this.boxEntity.setSelected3(true);
                    AnquanzhongxinActivity.this.boxEntity.setSelected4(false);
                    AnquanzhongxinActivity.this.boxEntity.setSelected5(false);
                }
                AnquanzhongxinActivity.this.setInitState();
            }

            @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
            public void fail() {
                AnquanzhongxinActivity.this.setInitState();
            }

            @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
            public void reset() {
                if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                    AnquanzhongxinActivity.this.boxEntity.setSelected1(false);
                } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                    AnquanzhongxinActivity.this.boxEntity.setSelected3(false);
                    DeviceHelper.resetAnquanzhongxinDeviceID(AnquanzhongxinActivity.this.businessCode);
                }
                LockPatternUtil.clearLockPattern(AnquanzhongxinActivity.this.activityContext, UserManager.getInstance().getCurrentPhoneNumber());
                UserManager.getInstance().removeSelectAccountName(AnquanzhongxinActivity.this.boxEntity.getMobile());
                AnquanzhongxinActivity.this.box.remove(AnquanzhongxinActivity.this.boxEntity.getId());
                AnquanzhongxinActivity.this.box.put((Box) AnquanzhongxinActivity.this.boxEntity);
                LoginManager.logout(AnquanzhongxinActivity.this.activityContext);
                Intent intent = new Intent(AnquanzhongxinActivity.this.activityContext, LoginActivity.class);
                intent.putExtra("from", "LoginBindActivity");
                intent.putExtra("logintype", AnquanzhongxinActivity.this.boxEntity.getMobile());
                intent.putExtra("fromLockPatternbackground", "fromLockPatternbackground");
                intent.putExtra("fromLockPattern", "fromLockPattern");
                AnquanzhongxinActivity.this.activityContext.startActivity(intent);
            }

            @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
            public void cancel() {
                AnquanzhongxinActivity.this.setInitState();
            }
        }

        public static /* synthetic */ void lambda$onClick$0(View$OnClickListenerC82287 view$OnClickListenerC82287, int i, Intent intent) {
            if (i == -1) {
                if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                    AnquanzhongxinActivity.this.boxEntity.setSelected1(false);
                    AnquanzhongxinActivity.this.boxEntity.setSelected2(true);
                    AnquanzhongxinActivity.this.setInitState();
                    UIUtils.toastLong("您已经成功设置了手势密码！");
                    return;
                } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                    try {
                        AnquanzhongxinEntity m24482clone = AnquanzhongxinActivity.this.boxEntity.m24482clone();
                        m24482clone.setSelected3(false);
                        m24482clone.setSelected4(true);
                        m24482clone.setSelected5(false);
                        AnquanzhongxinActivity.this.generateKey(m24482clone, null, null);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    return;
                }
            }
            AnquanzhongxinActivity.this.setInitState();
        }

        public static /* synthetic */ void lambda$onClick$1(View$OnClickListenerC82287 view$OnClickListenerC82287, int i, Intent intent) {
            if (i == -1) {
                if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                    AnquanzhongxinActivity.this.boxEntity.setSelected2(false);
                } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                    AnquanzhongxinActivity.this.boxEntity.setSelected4(false);
                }
                if (!AnquanzhongxinActivity.this.boxEntity.isSelected2() && !AnquanzhongxinActivity.this.boxEntity.isSelected4()) {
                    LockPatternUtil.clearLockPattern(AnquanzhongxinActivity.this.activityContext, UserManager.getInstance().getCurrentPhoneNumber());
                }
                AnquanzhongxinActivity.this.setInitState();
                return;
            }
            AnquanzhongxinActivity.this.setInitState();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity$8 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC82308 implements View.OnClickListener {
        View$OnClickListenerC82308() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            LockPatternUtil.compareLockPattern(AnquanzhongxinActivity.this.activityContext, AnquanzhongxinActivity.this.userManager.getCurrentPhoneNumber(), true, true, AnquanzhongxinActivity.this.businessCode, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$8$W96yxZgCUNkGUBtIT2IYvZUydFo
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public final void onActivityResult(int i, Intent intent) {
                    AnquanzhongxinActivity.View$OnClickListenerC82308.lambda$onClick$0(AnquanzhongxinActivity.View$OnClickListenerC82308.this, i, intent);
                }
            });
            NBSActionInstrumentation.onClickEventExit();
        }

        public static /* synthetic */ void lambda$onClick$0(View$OnClickListenerC82308 view$OnClickListenerC82308, int i, Intent intent) {
            if (i == -1) {
                LockPatternUtil.createLockPattern(AnquanzhongxinActivity.this.activityContext, AnquanzhongxinActivity.this.userManager.getCurrentPhoneNumber(), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.8.1
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public void onActivityResult(int i2, Intent intent2) {
                    }
                });
            }
        }
    }

    public static /* synthetic */ void lambda$initClick$5(final AnquanzhongxinActivity anquanzhongxinActivity, View view) {
        if (((ToggleButton) view).isChecked()) {
            if (anquanzhongxinActivity.boxEntity.isSelected3()) {
                anquanzhongxinActivity.fingerScreenManager.startFingerprit(new FingerScreenManager.FingerCompleteInterface() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.9
                    @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                    public void success() {
                        AnquanzhongxinActivity.this.boxEntity.setSelected3(false);
                        AnquanzhongxinActivity.this.boxEntity.setSelected4(false);
                        AnquanzhongxinActivity.this.boxEntity.setSelected5(true);
                        AnquanzhongxinActivity.this.setInitState();
                    }

                    @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                    public void fail() {
                        AnquanzhongxinActivity.this.boxEntity.setSelected5(false);
                        AnquanzhongxinActivity.this.setInitState();
                    }

                    @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                    public void reset() {
                        if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 2) {
                            AnquanzhongxinActivity.this.boxEntity.setSelected1(false);
                        } else if (AnquanzhongxinActivity.this.boxEntity.getGroupChooseNumber() == 3) {
                            AnquanzhongxinActivity.this.boxEntity.setSelected3(false);
                            DeviceHelper.resetAnquanzhongxinDeviceID(AnquanzhongxinActivity.this.businessCode);
                        }
                        LockPatternUtil.clearLockPattern(AnquanzhongxinActivity.this.activityContext, UserManager.getInstance().getCurrentPhoneNumber());
                        AnquanzhongxinActivity.this.box.remove(AnquanzhongxinActivity.this.boxEntity.getId());
                        AnquanzhongxinActivity.this.box.put((Box) AnquanzhongxinActivity.this.boxEntity);
                        UserManager.getInstance().removeSelectAccountName(AnquanzhongxinActivity.this.boxEntity.getMobile());
                        LoginManager.logout(AnquanzhongxinActivity.this.activityContext);
                        Intent intent = new Intent(AnquanzhongxinActivity.this.activityContext, LoginActivity.class);
                        intent.putExtra("from", "LoginBindActivity");
                        intent.putExtra("logintype", AnquanzhongxinActivity.this.boxEntity.getMobile());
                        intent.putExtra("fromLockPatternbackground", "fromLockPatternbackground");
                        intent.putExtra("fromLockPattern", "fromLockPattern");
                        AnquanzhongxinActivity.this.activityContext.startActivity(intent);
                    }

                    @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                    public void cancel() {
                        AnquanzhongxinActivity.this.boxEntity.setSelected5(false);
                        AnquanzhongxinActivity.this.setInitState();
                    }
                });
                return;
            } else if (anquanzhongxinActivity.boxEntity.isSelected4()) {
                LockPatternUtil.compareLockPattern(anquanzhongxinActivity.activityContext, anquanzhongxinActivity.userManager.getCurrentPhoneNumber(), true, anquanzhongxinActivity.businessCode, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$ZYtg501DcUuzPNG_k4vOHrunBU4
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public final void onActivityResult(int i, Intent intent) {
                        AnquanzhongxinActivity.lambda$initClick$4(AnquanzhongxinActivity.this, i, intent);
                    }
                });
                return;
            } else {
                anquanzhongxinActivity.boxEntity.setSelected3(false);
                anquanzhongxinActivity.boxEntity.setSelected4(false);
                anquanzhongxinActivity.boxEntity.setSelected5(true);
                anquanzhongxinActivity.setInitState();
                anquanzhongxinActivity.showCloseDialog();
                return;
            }
        }
        anquanzhongxinActivity.boxEntity.setSelected5(false);
        anquanzhongxinActivity.setInitState();
    }

    public static /* synthetic */ void lambda$initClick$4(AnquanzhongxinActivity anquanzhongxinActivity, int i, Intent intent) {
        if (i == -1) {
            anquanzhongxinActivity.boxEntity.setSelected3(false);
            anquanzhongxinActivity.boxEntity.setSelected4(false);
            anquanzhongxinActivity.boxEntity.setSelected5(true);
            anquanzhongxinActivity.setInitState();
            return;
        }
        anquanzhongxinActivity.boxEntity.setSelected5(false);
        anquanzhongxinActivity.setInitState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity$10 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C821510 implements AnquanzongxinAdapter.ToggleClick {
        C821510() {
        }

        @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzongxinAdapter.ToggleClick
        public void click(final AnquanWhiteEntity anquanWhiteEntity) {
            if (!anquanWhiteEntity.isSelected()) {
                if (AnquanzhongxinActivity.this.boxEntity.isSelected3()) {
                    AnquanzhongxinActivity.this.fingerScreenManager.startFingerprit(new FingerScreenManager.FingerCompleteInterface() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.10.1
                        @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                        public void success() {
                            AnquanzhongxinActivity.this.setWhiteEntity(anquanWhiteEntity);
                        }

                        @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                        public void fail() {
                            anquanWhiteEntity.setSelected(true);
                            AnquanzhongxinActivity.this.setWhiteEntity(anquanWhiteEntity);
                            AnquanzhongxinActivity.this.anquanzongxinAdapter.notifyDataSetChanged();
                        }

                        @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                        public void reset() {
                            anquanWhiteEntity.setSelected(true);
                            AnquanzhongxinActivity.this.setWhiteEntity(anquanWhiteEntity);
                            AnquanzhongxinActivity.this.anquanzongxinAdapter.notifyDataSetChanged();
                            AnquanzhongxinActivity.this.box.remove(AnquanzhongxinActivity.this.boxEntity.getId());
                            AnquanzhongxinActivity.this.box.put((Box) AnquanzhongxinActivity.this.boxEntity);
                            UserManager.getInstance().removeSelectAccountName(AnquanzhongxinActivity.this.boxEntity.getMobile());
                            LoginManager.logout(AnquanzhongxinActivity.this.activityContext);
                            Intent intent = new Intent(AnquanzhongxinActivity.this.activityContext, LoginActivity.class);
                            intent.putExtra("from", "LoginBindActivity");
                            intent.putExtra("logintype", AnquanzhongxinActivity.this.boxEntity.getMobile());
                            intent.putExtra("fromLockPatternbackground", "fromLockPatternbackground");
                            intent.putExtra("fromLockPattern", "fromLockPattern");
                            AnquanzhongxinActivity.this.activityContext.startActivity(intent);
                        }

                        @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                        public void cancel() {
                            anquanWhiteEntity.setSelected(true);
                            AnquanzhongxinActivity.this.setWhiteEntity(anquanWhiteEntity);
                            AnquanzhongxinActivity.this.anquanzongxinAdapter.notifyDataSetChanged();
                        }
                    });
                    return;
                } else if (!AnquanzhongxinActivity.this.boxEntity.isSelected4()) {
                    if (AnquanzhongxinActivity.this.boxEntity.isSelected5()) {
                        anquanWhiteEntity.setSelected(true);
                        AnquanzhongxinActivity.this.setWhiteEntity(anquanWhiteEntity);
                        AnquanzhongxinActivity.this.anquanzongxinAdapter.notifyDataSetChanged();
                        UIUtils.toast("请先关闭短信验证码验证方式");
                        return;
                    }
                    AnquanzhongxinActivity.this.setWhiteEntity(anquanWhiteEntity);
                    return;
                } else {
                    LockPatternUtil.compareLockPattern(AnquanzhongxinActivity.this.activityContext, AnquanzhongxinActivity.this.userManager.getCurrentPhoneNumber(), true, AnquanzhongxinActivity.this.businessCode, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzhongxinActivity$10$wSQV7r0sM_EE3cTznIVavPEkOsw
                        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                        public final void onActivityResult(int i, Intent intent) {
                            AnquanzhongxinActivity.C821510.lambda$click$0(AnquanzhongxinActivity.C821510.this, anquanWhiteEntity, i, intent);
                        }
                    });
                    return;
                }
            }
            AnquanzhongxinActivity.this.setWhiteEntity(anquanWhiteEntity);
        }

        public static /* synthetic */ void lambda$click$0(C821510 c821510, AnquanWhiteEntity anquanWhiteEntity, int i, Intent intent) {
            if (i == -1) {
                AnquanzhongxinActivity.this.setWhiteEntity(anquanWhiteEntity);
                return;
            }
            anquanWhiteEntity.setSelected(true);
            AnquanzhongxinActivity.this.setWhiteEntity(anquanWhiteEntity);
            AnquanzhongxinActivity.this.anquanzongxinAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setInitState() {
        this.selectedLayout.setVisibility(8);
        this.desText.setVisibility(8);
        this.listViewLayout.setVisibility(8);
        this.toggleLayout4.setVisibility(8);
        this.toggleLayout3.setVisibility(8);
        if (this.boxEntity.isChooseGroup1()) {
            this.groupLayout1.setBackgroundResource(2131230829);
            this.groupImage1.setImageResource(2131230836);
            this.groupText1.setTextColor(-1703897);
            this.groupCheck1.setImageResource(2131230848);
            this.desText.setText("使用联通APP无需进行安全验证");
            this.desText.setBackgroundResource(2131230830);
            this.desText.setVisibility(0);
        } else {
            this.groupLayout1.setBackgroundResource(2131230828);
            this.groupImage1.setImageResource(2131230835);
            this.groupText1.setTextColor(-13421773);
            this.groupCheck1.setImageResource(2131230847);
        }
        if (this.boxEntity.isChooseGroup2() && this.boxEntity.getGroupChooseNumber() == 2) {
            this.selectedLayout.setVisibility(0);
            this.groupLayout2.setBackgroundResource(2131230829);
            this.groupImage2.setImageResource(2131230838);
            this.groupText2.setTextColor(-1703897);
            this.groupCheck2.setImageResource(2131230844);
            this.desText.setText("每次回到APP进行安全验证");
            if (!this.userManager.isYiwang() && !LoginManager.isKuandaiOrGuhua() && this.groupLayout3.getVisibility() == 0) {
                this.desText.setBackgroundResource(2131230831);
            } else {
                this.desText.setBackgroundResource(2131230832);
            }
            this.desText.setVisibility(0);
            this.toggleButton1.setChecked(this.boxEntity.isSelected1());
            this.toggleButton2.setChecked(this.boxEntity.isSelected2());
            if (this.boxEntity.isSelected2()) {
                this.toggleLayout3.setVisibility(0);
            }
            this.switchTip.setText("请选择安全验证方式，未选择验证方式该状态将不会保存");
            this.tipText.setVisibility(8);
        } else {
            this.groupLayout2.setBackgroundResource(2131230828);
            this.groupImage2.setImageResource(2131230837);
            this.groupText2.setTextColor(-13421773);
            if (this.boxEntity.getGroupChooseNumber() == 3 && this.boxEntity.isChooseGroup2() && (this.boxEntity.isSelected1() || this.boxEntity.isSelected2())) {
                this.groupCheck2.setImageResource(2131230844);
            } else {
                this.groupCheck2.setImageResource(2131230843);
            }
        }
        if (this.boxEntity.isChooseGroup3() && this.boxEntity.getGroupChooseNumber() == 3) {
            this.selectedLayout.setVisibility(0);
            this.groupLayout3.setBackgroundResource(2131230829);
            this.groupImage3.setImageResource(2131230840);
            this.groupText3.setTextColor(-1703897);
            this.groupCheck3.setImageResource(2131230844);
            if (this.boxEntity.isSelected4()) {
                this.toggleLayout3.setVisibility(0);
            }
            this.toggleLayout4.setVisibility(0);
            this.listViewLayout.setVisibility(0);
            this.toggleButton1.setChecked(this.boxEntity.isSelected3());
            this.toggleButton2.setChecked(this.boxEntity.isSelected4());
            this.toggleButton3.setChecked(this.boxEntity.isSelected5());
            this.switchTip.setText("设置解锁方式");
            this.tipText.setVisibility(0);
        } else {
            this.groupLayout3.setBackgroundResource(2131230828);
            this.groupImage3.setImageResource(2131230839);
            this.groupText3.setTextColor(-13421773);
            if (this.boxEntity.getGroupChooseNumber() == 2 && this.boxEntity.isChooseGroup3() && (this.boxEntity.isSelected3() || this.boxEntity.isSelected4() || this.boxEntity.isSelected5())) {
                this.groupCheck3.setImageResource(2131230844);
            } else {
                this.groupCheck3.setImageResource(2131230843);
            }
        }
        setEntity(this.boxEntity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void generateKey(final AnquanzhongxinEntity anquanzhongxinEntity, String str, final TextView textView) {
        String str2;
        String str3;
        MsLogUtil.m7979d("generateKey", "1---" + this.boxEntity.isSelected3());
        final String sign = anquanzhongxinEntity.getSign();
        String str4 = "";
        String str5 = "0";
        if (anquanzhongxinEntity.isSelected4()) {
            str4 = "1";
            if (anquanzhongxinEntity.getSignWay() != 1) {
                str5 = "1";
            }
        }
        if (!anquanzhongxinEntity.isSelected3()) {
            str2 = str4;
            str3 = str5;
        } else if (anquanzhongxinEntity.getSignWay() != 3) {
            str2 = "3";
            str3 = "1";
        } else {
            str2 = "3";
            str3 = str5;
        }
        if (TextUtils.isEmpty(sign) && TextUtils.isEmpty(str)) {
            AnquanzhongxinSMSDialog.showDialog(this.activityContext, new AnquanzhongxinSMSDialog.AnquanzhongxinSMSListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.11
                @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinSMSDialog.AnquanzhongxinSMSListener
                public void sendSms() {
                    AnquanzhongxinActivity.this.anquanzhongxinManager.sendSms();
                }

                @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinSMSDialog.AnquanzhongxinSMSListener
                public void submmit(String str6, TextView textView2) {
                    AnquanzhongxinActivity.this.generateKey(anquanzhongxinEntity, str6, textView2);
                }

                @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinSMSDialog.AnquanzhongxinSMSListener
                public void onDissMiss() {
                    MsLogUtil.m7979d("generateKey", "2---" + AnquanzhongxinActivity.this.boxEntity.isSelected3());
                    AnquanzhongxinActivity.this.setInitState();
                }
            });
            return;
        }
        this.porgressDialog.setMessage("正在校验");
        this.porgressDialog.show();
        this.anquanzhongxinManager.getGenerateKey(str, sign, str2, str3, new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.12
            @Override // io.reactivex.functions.Consumer
            public void accept(String str6) throws Exception {
                AnquanzhongxinActivity.this.porgressDialog.dismiss();
                JSONObject jSONObject = new JSONObject(str6);
                String optString = jSONObject.optString("code");
                String optString2 = jSONObject.optString("msg");
                String optString3 = jSONObject.optString("data");
                Intent intent = new Intent();
                if ("0000".equals(optString)) {
                    anquanzhongxinEntity.setSign(optString3);
                    if (anquanzhongxinEntity.isSelected3()) {
                        anquanzhongxinEntity.setSignWay(3);
                    }
                    if (anquanzhongxinEntity.isSelected4()) {
                        anquanzhongxinEntity.setSignWay(1);
                    }
                    intent.putExtra("authCode", "sign");
                    intent.putExtra("userChecked", true);
                    AnquanzhongxinActivity.this.setResult(-1, intent);
                    AnquanzhongxinActivity.this.boxEntity = anquanzhongxinEntity;
                    AnquanzhongxinSMSDialog.dissMiss();
                    AnquanzhongxinActivity.this.setInitState();
                    AnquanzhongxinActivity.this.showCloseDialog();
                } else if ("0003".equals(optString) || "2333".equals(optString)) {
                    if (TextUtils.isEmpty(optString3)) {
                        intent.putExtra("authCode", sign);
                        anquanzhongxinEntity.setSign(sign);
                    } else {
                        intent.putExtra("authCode", optString3);
                        anquanzhongxinEntity.setSign(optString3);
                    }
                    if (anquanzhongxinEntity.isSelected3()) {
                        anquanzhongxinEntity.setSignWay(3);
                    }
                    if (anquanzhongxinEntity.isSelected4()) {
                        anquanzhongxinEntity.setSignWay(1);
                    }
                    intent.putExtra("userChecked", true);
                    AnquanzhongxinActivity.this.setResult(-1, intent);
                    AnquanzhongxinActivity.this.boxEntity = anquanzhongxinEntity;
                    AnquanzhongxinSMSDialog.dissMiss();
                    AnquanzhongxinActivity.this.setInitState();
                    AnquanzhongxinActivity.this.showCloseDialog();
                } else if ("1111".equals(optString)) {
                    TextView textView2 = textView;
                    if (textView2 != null) {
                        textView2.setVisibility(0);
                        if (TextUtils.isEmpty(optString2)) {
                            textView.setText("验证码输入错误，请核对后重新输入");
                        } else {
                            textView.setText(optString2);
                        }
                    }
                    AnquanzhongxinActivity.this.setInitState();
                } else if ("1112".equals(optString)) {
                    TextView textView3 = textView;
                    if (textView3 != null) {
                        textView3.setVisibility(0);
                        if (TextUtils.isEmpty(optString2)) {
                            textView.setText("短信验证码校验失败");
                        } else {
                            textView.setText(optString2);
                        }
                    }
                    AnquanzhongxinActivity.this.setInitState();
                } else {
                    UIUtils.toast("校验失败");
                    AnquanzhongxinActivity.this.setInitState();
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.13
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                AnquanzhongxinActivity.this.porgressDialog.dismiss();
                UIUtils.toast("校验失败");
                AnquanzhongxinActivity.this.setInitState();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSetFinger(boolean z, ToggleButton toggleButton, FingerScreenManager.FingerCompleteInterface fingerCompleteInterface) {
        if (z) {
            int supportFinggerNum = DeviceHelper.getSupportFinggerNum();
            if (supportFinggerNum == 3 || supportFinggerNum == 1) {
                MsLogUtil.m7979d(TAG, "您的手机系统暂不支持指纹密码,请选择支持指纹密码设置的手机使用此功能");
                toggleButton.setChecked(false);
                return;
            } else if (supportFinggerNum == 2) {
                MsLogUtil.m7979d(TAG, "您尚未设置指纹密码,请在手机系统设置指纹密码");
                toggleButton.setChecked(false);
                return;
            } else if (supportFinggerNum == 0) {
                this.fingerScreenManager.startFingerprit(fingerCompleteInterface);
                return;
            } else {
                return;
            }
        }
        this.fingerScreenManager.startFingerprit(fingerCompleteInterface);
    }

    private AnquanzhongxinEntity getEntity() {
        AnquanzhongxinEntity findFirst = this.box.query().equal(AnquanzhongxinEntity_.mobile, this.userManager.getCurrentPhoneNumber()).build().findFirst();
        if (findFirst == null) {
            findFirst = new AnquanzhongxinEntity();
            findFirst.setMobile(this.userManager.getCurrentPhoneNumber());
            findFirst.setChooseGroup1(true);
            findFirst.setGroupChooseNumber(1);
        }
        String str = TAG;
        MsLogUtil.m7979d(str, "读取缓存---" + findFirst.toString());
        return findFirst;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEntity(AnquanzhongxinEntity anquanzhongxinEntity) {
        try {
            if (anquanzhongxinEntity.getId() != 0) {
                this.box.remove(anquanzhongxinEntity.getId());
            }
            this.box.put((Box<AnquanzhongxinEntity>) anquanzhongxinEntity);
            String str = TAG;
            MsLogUtil.m7979d(str, "设置缓存---" + getEntity().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<AnquanWhiteEntity> getWhiteList() {
        return this.anquanWhiteEntityBox.query().equal(AnquanWhiteEntity_.mobile, this.userManager.getCurrentPhoneNumber()).build().find();
    }

    public void setWhiteList(List<AnquanWhiteEntity> list) {
        this.anquanWhiteEntityBox.remove(getWhiteList());
        this.anquanWhiteEntityBox.put(list);
    }

    public void setWhiteEntity(AnquanWhiteEntity anquanWhiteEntity) {
        try {
            this.anquanWhiteEntityBox.remove(anquanWhiteEntity.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.anquanWhiteEntityBox.put((Box<AnquanWhiteEntity>) anquanWhiteEntity);
    }

    private void showBackDialog(final Consumer<Boolean> consumer) {
        CustomDialogManager.show3(this.activityContext, "", "您目前存在选择了保护范围，但未选择安全验证方式的操作，退出本页面后此类型操作将不会保存，请您确认是否进行退出。", 17, true, "立即设置", "放弃安全验证", false, true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.14
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
            public void onClickOk() {
                try {
                    consumer.accept(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCloseDialog() {
        if (TextUtils.isEmpty(this.businessCode)) {
            return;
        }
        boolean z = false;
        for (AnquanWhiteEntity anquanWhiteEntity : getWhiteList()) {
            if (anquanWhiteEntity.isSelected() && this.businessCode.equals(anquanWhiteEntity.getCode())) {
                z = true;
            }
        }
        if (z) {
            CustomDialogManager.show4(this.activityContext, "", "解锁方式已设置成功，是否返回业务", 17, true, "返回业务", "取消", false, true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity.15
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    AnquanzhongxinActivity.this.backImage.performClick();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTipdialog(String str) {
        CustomDialogManager.show2(this.activityContext, str, null);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        this.backImage.performClick();
    }

    public static boolean isFastClick() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime < 1000;
        lastClickTime = currentTimeMillis;
        return z;
    }
}
