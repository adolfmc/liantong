package com.sinovatech.unicom.separatemodule.esim;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.p318ui.App;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class JSWhiteUtil {
    private static List<String> vpnBlackList = new ArrayList();
    private static boolean isDailiDialog = false;

    public static void initVpnCheck() {
        try {
            vpnBlackList = new ArrayList();
            String string = App.getSharePreferenceUtil().getString("unicom_esim_vpnBlackDomain");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            vpnBlackList.addAll(Arrays.asList(RSAUtil.ecbDecrypt(string).split(",")));
            isDailiDialog = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkVpn(final Activity activity, String str, Handler handler) {
        try {
            for (String str2 : vpnBlackList) {
                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str2.trim()) && !isDailiDialog && str.contains(str2) && EsimUtil.isVPNorProxy()) {
                    isDailiDialog = true;
                    handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.JSWhiteUtil.1
                        @Override // java.lang.Runnable
                        public void run() {
                            CustomDialogManager.show(activity, "温馨提示", "当前网络环境存在安全隐患，请检查是否存在网络代理，并关闭后继续使用。", false, "取消", "确定", false, false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.esim.JSWhiteUtil.1.1
                                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                                public void onBackKeyDown() {
                                }

                                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                                public void onCancel() {
                                }

                                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                                public void onShow() {
                                }

                                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                                public void onClickOk() {
                                    activity.finish();
                                }

                                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                                public void onClickCancel() {
                                    activity.finish();
                                }
                            });
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isWhite(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            String string = App.getSharePreferenceUtil().getString("unicom_esim_jsWhiteDoman");
            if (!TextUtils.isEmpty(string)) {
                String ecbDecrypt = RSAUtil.ecbDecrypt(string);
                if ("offline".equals(ecbDecrypt)) {
                    return true;
                }
                arrayList.addAll(Arrays.asList(ecbDecrypt.split(",")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (arrayList.size() > 0) {
            try {
                String host = new URL(str).getHost();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (!TextUtils.isEmpty(host) && host.contains((CharSequence) arrayList.get(i))) {
                        return true;
                    }
                }
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
