package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.voiceCall.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.api.FailedBinderCallBack;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.voiceCall.VoiceCallJSPlugin;
import com.tydic.softphone.utils.NetUtil;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NetBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "tydic111";
    public static NetEvevt evevt;
    private VoiceCallJSPlugin voiceCallJSPlugin;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface NetEvevt {
        void onNetChange(int i);
    }

    public NetBroadcastReceiver(VoiceCallJSPlugin voiceCallJSPlugin) {
        this.voiceCallJSPlugin = voiceCallJSPlugin;
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
                int intExtra2 = intent.getIntExtra("durationTalk", 0);
                Log.i(TAG, "callid:" + ziFuChuai);
                Log.i(TAG, "code:" + intExtra);
                Log.i(TAG, "msg:" + ziFuChuai2);
                Log.i(TAG, "durationTalk:" + intExtra2);
                this.voiceCallJSPlugin.sendMessage("12", String.valueOf(intExtra), ziFuChuai, ziFuChuai2, String.valueOf(intExtra2));
            } else if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                NetUtil.init(context);
                int netWorkState = NetUtil.getNetWorkState();
                Log.e(TAG, "网络:" + netWorkState);
                this.voiceCallJSPlugin.sendMessage("12", String.valueOf(netWorkState), "", "", "");
                if (evevt != null) {
                    evevt.onNetChange(netWorkState);
                }
            }
        } catch (Exception e) {
            VoiceCallJSPlugin voiceCallJSPlugin = this.voiceCallJSPlugin;
            voiceCallJSPlugin.sendMessage("10", "", "", "异常错误：" + e.getMessage(), "");
            Log.e(TAG, "报错异常:" + e.getMessage());
        }
    }

    public static void setEvevt(NetEvevt netEvevt) {
        evevt = netEvevt;
    }

    private String ziFuChuai(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }
}
