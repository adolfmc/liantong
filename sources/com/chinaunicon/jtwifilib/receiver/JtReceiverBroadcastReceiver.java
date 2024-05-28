package com.chinaunicon.jtwifilib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import com.chinaunicon.jtwifilib.jtcommon.MessageManager;
import com.chinaunicon.jtwifilib.jtcommon.model.JtInitBean;
import com.chinaunicon.jtwifilib.jtcommon.model.Register1Bean;
import com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtReceiverBroadcastReceiver extends BroadcastReceiver {
    private JtInitBean user;

    public void onResult(String str) {
    }

    public JtReceiverBroadcastReceiver(JtInitBean jtInitBean) {
        this.user = jtInitBean;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null && MessageManager.getMessageReceiverAction().equals(intent.getAction())) {
            try {
                String stringExtra = intent.getStringExtra(MessageManager.getMessageAppToSdk());
                JtL.m16342e("收到消息：" + stringExtra);
                Register1Bean register1Bean = new Register1Bean();
                register1Bean.setCmdType(MessageManager.getMessageAppToSdk());
                register1Bean.setStatus("0");
                register1Bean.setMsg(stringExtra);
                JtUploadLog.getInstance(context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), MessageManager.getMessageAppToSdk());
                onResult(stringExtra);
            } catch (Exception unused) {
            }
        }
    }
}
