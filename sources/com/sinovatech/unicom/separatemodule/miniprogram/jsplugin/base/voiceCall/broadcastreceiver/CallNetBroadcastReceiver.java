package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.voiceCall.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.api.FailedBinderCallBack;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.tydic.softphone.utils.NetUtil;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CallNetBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "tydic111";
    public static NetEvevt evevt;
    private Activity activity;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface NetEvevt {
        void onNetChange(int i);
    }

    public CallNetBroadcastReceiver(Activity activity) {
        this.activity = activity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            Log.i(TAG, "收到了消息");
            if (intent.getAction().equals("com.tydic.softphone.callid")) {
                Log.i(TAG, "succ");
                String ziFuChuai = ziFuChuai(intent.getStringExtra(FailedBinderCallBack.CALLER_ID));
                int intExtra = intent.getIntExtra("code", -1);
                String ziFuChuai2 = ziFuChuai(intent.getStringExtra("msg"));
                Log.i(TAG, "callid:" + ziFuChuai);
                Log.i(TAG, "code:" + intExtra);
                Log.i(TAG, "msg:" + ziFuChuai2);
                if (intExtra == 2 || intExtra == 3 || intExtra == 8) {
                    try {
                        if (this.activity != null) {
                            this.activity.unregisterReceiver(this);
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e("软电话移除监听异常：" + e.getMessage());
                    }
                }
            } else if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                NetUtil.init(context);
                int netWorkState = NetUtil.getNetWorkState();
                Log.e(TAG, "网络:" + netWorkState);
                if (evevt != null) {
                    evevt.onNetChange(netWorkState);
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "报错异常:" + e2.getMessage());
        }
    }

    public static void setEvevt(NetEvevt netEvevt) {
        evevt = netEvevt;
    }

    private String ziFuChuai(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }
}
