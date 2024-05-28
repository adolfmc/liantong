package com.baidu.p120ar.shake;

import android.content.Context;
import com.baidu.p120ar.arplay.core.message.ARPMessage;
import com.baidu.p120ar.arplay.util.MsgParamsUtil;
import com.baidu.p120ar.lua.EngineMsgBridge;
import com.baidu.p120ar.lua.EngineMsgListener;
import com.baidu.p120ar.shake.ShakeListener;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.shake.ShakeModule */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShakeModule implements EngineMsgListener {
    private static final int MSG_TYPE_ON_SHAKE = 10000;
    private static final int MSG_TYPE_SET_SHAKE_ENABLE = 10004;
    private static final int MSG_TYPE_START_SHAKE = 10001;
    private static final int MSG_TYPE_STOP_SHAKE = 10002;
    private Context mContext;
    private ShakeListener.OnShakeListener mOnShakeListenr;

    public ShakeModule(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void init(EngineMsgBridge engineMsgBridge) {
        this.mOnShakeListenr = new ShakeListener.OnShakeListener() { // from class: com.baidu.ar.shake.ShakeModule.1
            @Override // com.baidu.p120ar.shake.ShakeListener.OnShakeListener
            public void destroy() {
            }

            @Override // com.baidu.p120ar.shake.ShakeListener.OnShakeListener
            public void onShake(float f, float f2, float f3, float f4) {
                ARLog.m20420e("acc  x " + f + " , y : " + f2 + " , z " + f3);
                StatisticApi.onEvent("model_phone_shake");
                ShakeModule.sendAccelerationToLua(f, f2, f3, f4);
            }
        };
        engineMsgBridge.addEngineMsgListener(this);
    }

    @Override // com.baidu.p120ar.lua.EngineMsgListener
    public List<Integer> getMsgTypesListened() {
        return Arrays.asList(1901);
    }

    @Override // com.baidu.p120ar.lua.EngineMsgListener
    public void onEngineMessage(int i, int i2, HashMap<String, Object> hashMap) {
        if (i != 1901 || hashMap == null) {
            return;
        }
        switch (MsgParamsUtil.obj2Int(hashMap.get("id"), -1)) {
            case 10001:
                ShakeManager.getInstance(this.mContext).start(this.mOnShakeListenr);
                return;
            case 10002:
                ShakeManager.getInstance(this.mContext).stop();
                return;
            case 10003:
            default:
                return;
            case 10004:
                ShakeManager.getInstance(this.mContext).setShakeEnable(true);
                return;
        }
    }

    public static void sendAccelerationToLua(float f, float f2, float f3, float f4) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 10000);
        hashMap.put("max_acc", Float.valueOf(f4));
        ARPMessage.getInstance().sendMessage(1902, hashMap);
    }

    public void reset() {
        Context context = this.mContext;
        if (context != null) {
            try {
                ShakeManager.getInstance(context).stop();
            } catch (Throwable unused) {
            }
        }
    }

    public void release() {
        ShakeManager.getInstance(this.mContext).destroy();
        this.mOnShakeListenr = null;
        this.mContext = null;
    }
}
