package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import android.app.Activity;
import android.content.Intent;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sinovatech.unicom.basic.p315ui.activity.CustomMainActivity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.UnicomHomeFragment;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.security.LockPatternUtil;
import io.objectbox.Box;
import io.reactivex.functions.Consumer;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AnquanzhognxinCheckStart {
    private Box<AnquanzhongxinEntity> box = App.getBoxStore().boxFor(AnquanzhongxinEntity.class);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface StartCallBack {
        void complete();

        void onCancel();
    }

    public void welcomStart(final Activity activity, final String str, final StartCallBack startCallBack) {
        final AnquanzhongxinEntity findFirst = this.box.query().equal(AnquanzhongxinEntity_.mobile, str).build().findFirst();
        if (findFirst == null || !findFirst.isChooseGroup2() || findFirst.isChooseGroup1()) {
            startCallBack.complete();
        } else if (findFirst.isChooseGroup2() && findFirst.isSelected1() && DeviceHelper.getSupportFinggerNum() != 0) {
            CustomDialogManager.show2(activity, "与该账号绑定的指纹解锁已失效，请您重新登录。", new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart.1
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    findFirst.setSelected1(false);
                    findFirst.setSelected2(false);
                    AnquanzhognxinCheckStart.this.box.remove(findFirst.getId());
                    AnquanzhognxinCheckStart.this.box.put((Box) findFirst);
                    MsLogUtil.m7979d("", "重置了手势和指纹，更新数据库--" + findFirst);
                    LoginManager.logout(activity);
                    startCallBack.complete();
                }
            });
        } else if (findFirst.isSelected1()) {
            new FingerScreenManager(activity).startFingerprit(new FingerScreenManager.FingerCompleteInterface() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart.2
                @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                public void success() {
                    startCallBack.complete();
                }

                @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                public void fail() {
                    AnquanzhognxinCheckStart.this.logoutLogin(str, activity, startCallBack);
                }

                @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                public void reset() {
                    findFirst.setSelected1(false);
                    findFirst.setSelected2(false);
                    LockPatternUtil.clearLockPattern(activity, UserManager.getInstance().getCurrentPhoneNumber());
                    AnquanzhognxinCheckStart.this.box.remove(findFirst.getId());
                    AnquanzhognxinCheckStart.this.box.put((Box) findFirst);
                    MsLogUtil.m7979d("", "重置了手势和指纹，更新数据库--" + findFirst);
                    UnicomHomeFragment.currentPhone = "";
                    UserManager.getInstance().removeSelectAccountName(str);
                    LoginManager.logout(activity);
                    Intent intent = new Intent(activity, LoginActivity.class);
                    intent.putExtra("from", "LoginBindActivity");
                    intent.putExtra("logintype", str);
                    intent.putExtra("fromLockPattern", "fromLockPattern");
                    intent.putExtra("fromLockPatternbackground", "fromLockPatternbackground");
                    activity.startActivity(intent);
                    if (CustomMainActivity.isCustomMain()) {
                        CustomMainActivity.goMainActivity(activity);
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.FingerScreenManager.FingerCompleteInterface
                public void cancel() {
                    AnquanzhognxinCheckStart.this.logoutLogin(str, activity, startCallBack);
                }
            });
        } else if (findFirst.isSelected2()) {
            LockScreenManager.welcomeStart(activity, str, new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart.3
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    startCallBack.complete();
                }
            });
        } else {
            startCallBack.complete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logoutLogin(String str, final Activity activity, final StartCallBack startCallBack) {
        App.clearPersistentCookiesList();
        UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
        LoginManager.logout(activity);
        UnicomHomeFragment.currentPhone = "";
        RequestParams requestParams = new RequestParams();
        requestParams.put("version", activity.getString(2131886969));
        requestParams.put("desmobile", str);
        requestParams.put("token_online", UserManager.getInstance().getOnlineToken(str));
        final CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(activity);
        customePorgressDialog.setMessage("退出登录");
        customePorgressDialog.setCanceledOnTouchOutside(false);
        customePorgressDialog.setCancelable(false);
        customePorgressDialog.show();
        App.getAsyncHttpClient(5, 5, 5).post(URLSet.getLogoutURL(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart.4
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                UIUtils.dismissDialog(activity, customePorgressDialog);
                startCallBack.onCancel();
            }
        });
    }

    public int getOpenState(String str) {
        AnquanzhongxinEntity findFirst = this.box.query().equal(AnquanzhongxinEntity_.mobile, str).build().findFirst();
        if (findFirst == null || !findFirst.isChooseGroup2() || findFirst.isChooseGroup1()) {
            return 0;
        }
        if (findFirst.isSelected1()) {
            return 3;
        }
        return findFirst.isSelected2() ? 1 : 0;
    }

    public void reset(String str) {
        AnquanzhongxinEntity findFirst = this.box.query().equal(AnquanzhongxinEntity_.mobile, str).build().findFirst();
        if (findFirst != null) {
            findFirst.setSelected1(false);
            findFirst.setSelected2(false);
            this.box.remove(findFirst.getId());
            this.box.put((Box<AnquanzhongxinEntity>) findFirst);
            MsLogUtil.m7979d("", "重置了手势和指纹，更新数据库--" + findFirst);
        }
    }
}
