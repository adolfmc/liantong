package cn.sharesdk.framework;

import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.OperationCallback;
import com.mob.RHolder;
import com.mob.commons.SHARESDK;
import com.mob.commons.dialog.entity.InternalPolicyUi;
import com.mob.tools.utils.ResHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ProvicyCanContinue {

    /* renamed from: a */
    private static volatile ProvicyCanContinue f2745a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnBusinessListener {
        void onContinue();

        void onError(Throwable th);

        void onStop();
    }

    private ProvicyCanContinue() {
        m22011b();
    }

    /* renamed from: b */
    private void m22011b() {
        RHolder.getInstance().setActivityThemeId(ResHelper.getStyleRes(MobSDK.getContext(), "mobcommon_TranslucentTheme")).setDialogThemeId(ResHelper.getStyleRes(MobSDK.getContext(), "mobcommon_DialogStyle")).setDialogLayoutId(ResHelper.getLayoutRes(MobSDK.getContext(), "mob_authorize_dialog"));
        SSDKLog.m21740b().m21744a("ShareSDK", "ProvicyCanContinue initMobCommonView()");
    }

    /* renamed from: a */
    public static ProvicyCanContinue m22013a() {
        synchronized (ProvicyCanContinue.class) {
            if (f2745a == null) {
                synchronized (ProvicyCanContinue.class) {
                    if (f2745a == null) {
                        f2745a = new ProvicyCanContinue();
                    }
                }
            }
        }
        return f2745a;
    }

    /* renamed from: a */
    public void m22012a(final OnBusinessListener onBusinessListener) {
        MobSDK.canIContinueBusiness(new SHARESDK(), new InternalPolicyUi.Builder().setTitleText(MobSDK.getContext().getResources().getString(ResHelper.getStringRes(MobSDK.getContext(), "mobcommon_authorize_dialog_title"))).setContentText(MobSDK.getContext().getResources().getString(ResHelper.getStringRes(MobSDK.getContext(), "mobcommon_authorize_dialog_content"))).build(), new OperationCallback<Boolean>() { // from class: cn.sharesdk.framework.ProvicyCanContinue.1
            @Override // com.mob.OperationCallback
            public void onComplete(Boolean bool) {
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21744a("ShareSDK", "canIContinueBusiness: onComplete(), " + bool);
                if (bool.booleanValue()) {
                    OnBusinessListener onBusinessListener2 = onBusinessListener;
                    if (onBusinessListener2 != null) {
                        onBusinessListener2.onContinue();
                    }
                    SSDKLog.m21740b().m21744a("ShareSDK", "MobSDK.canIContinueBusiness if ");
                    return;
                }
                OnBusinessListener onBusinessListener3 = onBusinessListener;
                if (onBusinessListener3 != null) {
                    onBusinessListener3.onStop();
                }
                SSDKLog.m21740b().m21744a("ShareSDK", "MobSDK.canIContinueBusiness else ");
            }

            @Override // com.mob.OperationCallback
            public void onFailure(Throwable th) {
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21744a("ShareSDK", "canIContinueBusiness: onFailure() " + th);
                OnBusinessListener onBusinessListener2 = onBusinessListener;
                if (onBusinessListener2 != null) {
                    onBusinessListener2.onError(th);
                }
            }
        });
    }
}
