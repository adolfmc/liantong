package com.sinovatech.unicom.separatemodule.login.yinsixieyi;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import com.sinovatech.unicom.basic.boxcenter.LoginConfigDataCenter;
import com.sinovatech.unicom.common.CustomDensityHandler;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.dialog.BaseDialog;
import com.sinovatech.unicom.separatemodule.dialog.YinsixieyiiDialog;
import com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.JiaoFeiManager;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class YinSiXieYiUtil {
    private final Activity activityContext;

    public YinSiXieYiUtil(Activity activity) {
        this.activityContext = activity;
    }

    public Observable<Boolean> checkDialog(final boolean z, final String str, final String str2) {
        final PublishSubject create = PublishSubject.create();
        return create.doOnSubscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.login.yinsixieyi.-$$Lambda$YinSiXieYiUtil$LwSp-VGnp4OxLpk6-TvcRFNFQbU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                YinSiXieYiUtil.lambda$checkDialog$2(YinSiXieYiUtil.this, z, create, str2, str, (Disposable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$checkDialog$2(YinSiXieYiUtil yinSiXieYiUtil, boolean z, final PublishSubject publishSubject, final String str, final String str2, Disposable disposable) throws Exception {
        if (z) {
            new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.login.yinsixieyi.YinSiXieYiUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    publishSubject.onNext(true);
                    publishSubject.onComplete();
                }
            });
        } else if (!"on".equals(LoginConfigDataCenter.getInstance().getEntity().getPrivacySwitch())) {
            if ("SMS".equals(str)) {
                UIUtils.toastCenter("请先勾选协议");
            } else {
                UIUtils.toastCenter("请先勾选协议后再进行登录");
            }
            new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.login.yinsixieyi.YinSiXieYiUtil.2
                @Override // java.lang.Runnable
                public void run() {
                    publishSubject.onNext(false);
                    publishSubject.onComplete();
                }
            });
        } else {
            if ("SMS".equals(str)) {
                JiaoFeiManager.uploadLogin(str2, "0002隐私协议勾选提醒弹窗弹出", "3");
            } else {
                JiaoFeiManager.uploadLogin(str2, "0002隐私协议勾选提醒弹窗弹出", str);
            }
            Activity activity = yinSiXieYiUtil.activityContext;
            CustomDensityHandler.setCustomDensity(activity, activity.getApplication());
            new YinsixieyiiDialog.Builder(yinSiXieYiUtil.activityContext).setOnClickListener(2131299122, new BaseDialog.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.yinsixieyi.-$$Lambda$YinSiXieYiUtil$RxbawIFYHXxWWSepjcElpE_L8zc
                @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    YinSiXieYiUtil.lambda$checkDialog$0(PublishSubject.this, str2, str, baseDialog, view);
                }
            }).setOnClickListener(2131299121, new BaseDialog.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.yinsixieyi.-$$Lambda$YinSiXieYiUtil$9onuqPtpdZz0DSrP_AsGyGKCD0Q
                @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    YinSiXieYiUtil.lambda$checkDialog$1(str2, str, publishSubject, baseDialog, view);
                }
            }).setCanceledOnTouchOutside(false).setCancelable(false).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$checkDialog$0(PublishSubject publishSubject, String str, String str2, BaseDialog baseDialog, View view) {
        baseDialog.dismiss();
        publishSubject.onNext(true);
        publishSubject.onComplete();
        JiaoFeiManager.uploadLogin(str, "0003隐私协议弹窗点击已阅读并同意", str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$checkDialog$1(String str, String str2, PublishSubject publishSubject, BaseDialog baseDialog, View view) {
        JiaoFeiManager.uploadLogin(str, "0004隐私协议弹窗点击不同意", str2);
        baseDialog.dismiss();
        publishSubject.onNext(false);
        publishSubject.onComplete();
    }
}
