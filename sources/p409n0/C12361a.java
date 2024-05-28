package p409n0;

import android.text.TextUtils;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.callback.NativeFunctionCallBack;
import com.unicom.pay.qpay.setting.bean.WPSettingInfoBean;
import com.unicom.pay.qpay.setting.p361ui.WPQPaySettingActivity;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import p395i.C12048b;

/* renamed from: n0.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12361a implements C12048b.InterfaceC12050b {

    /* renamed from: a */
    public final /* synthetic */ WPQPaySettingActivity f25021a;

    public C12361a(WPQPaySettingActivity wPQPaySettingActivity) {
        this.f25021a = wPQPaySettingActivity;
    }

    @Override // p395i.C12048b.InterfaceC12050b
    /* renamed from: a */
    public final void mo1801a() {
        WPSettingInfoBean wPSettingInfoBean = this.f25021a.f20433z;
        if (wPSettingInfoBean != null && !TextUtils.isEmpty(wPSettingInfoBean.getRetentionUrl())) {
            NativeFunctionCallBack nativeFunctionCallback = UnicomPaySDK.getInstance().getNativeFunctionCallback();
            WPQPaySettingActivity wPQPaySettingActivity = this.f25021a;
            nativeFunctionCallback.openWebview(wPQPaySettingActivity, wPQPaySettingActivity.f20433z.getRetentionUrl());
        } else {
            this.f25021a.m6061m0();
        }
        WPTrendsEventsUtils.trendsPageButtonData("关闭确认弹窗", "98U01170qp016", "qp016", "点击-关闭极速支付弹窗-仍要关闭");
    }
}
