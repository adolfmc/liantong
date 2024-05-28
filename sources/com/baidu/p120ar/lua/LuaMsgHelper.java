package com.baidu.p120ar.lua;

import android.text.TextUtils;
import android.util.Log;
import com.baidu.p120ar.DefinedLuaListener;
import com.baidu.p120ar.arplay.util.MsgParamsUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.lua.LuaMsgHelper */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LuaMsgHelper {
    public static final int MSG_TYPE_SWITCH_CAMERA = 10202;
    private static final String TAG = "LuaMsgHelper";
    private DefinedLuaListener mDefinedLuaListener;
    private EngineMsgBridge mEngineMsgBridge;
    private EngineMsgListener mEngineMsgListener;
    private LuaMsgListener mLuaMsgListener;

    public LuaMsgHelper(EngineMsgBridge engineMsgBridge) {
        if (engineMsgBridge == null) {
            return;
        }
        this.mEngineMsgBridge = engineMsgBridge;
        createEngineMsgListener();
        if (this.mLuaMsgListener == null) {
            this.mLuaMsgListener = new LuaMsgListener() { // from class: com.baidu.ar.lua.LuaMsgHelper.1
                @Override // com.baidu.p120ar.lua.LuaMsgListener
                public List<String> getMsgKeyListened() {
                    return Arrays.asList("id", "event_name");
                }

                @Override // com.baidu.p120ar.lua.LuaMsgListener
                public void onLuaMessage(HashMap<String, Object> hashMap) {
                    if (LuaMsgHelper.this.mDefinedLuaListener == null || LuaMsgHelper.this.swichCameraByEvent(hashMap)) {
                        return;
                    }
                    LuaMsgHelper.this.switchCameraById(hashMap);
                }
            };
        }
        if (engineMsgBridge.getLuaMsgBridge() != null) {
            engineMsgBridge.getLuaMsgBridge().addLuaMsgListener(this.mLuaMsgListener);
        }
    }

    private void createEngineMsgListener() {
        this.mEngineMsgListener = new EngineMsgListener() { // from class: com.baidu.ar.lua.LuaMsgHelper.2
            @Override // com.baidu.p120ar.lua.EngineMsgListener
            public List<Integer> getMsgTypesListened() {
                return Arrays.asList(1301);
            }

            @Override // com.baidu.p120ar.lua.EngineMsgListener
            public void onEngineMessage(int i, int i2, HashMap<String, Object> hashMap) {
                if (i != 1301 || LuaMsgHelper.this.mDefinedLuaListener == null) {
                    return;
                }
                int intValue = ((Integer) hashMap.get("type")).intValue();
                LuaMsgHelper.this.mDefinedLuaListener.onOpenUrl((String) hashMap.get("url"), intValue, hashMap);
            }
        };
        this.mEngineMsgBridge.addEngineMsgListener(this.mEngineMsgListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean swichCameraByEvent(HashMap<String, Object> hashMap) {
        String str = (String) hashMap.get("event_name");
        if (TextUtils.isEmpty(str) || !"camera_switch".equals(str)) {
            return false;
        }
        String str2 = (String) hashMap.get("camera_action");
        Log.d("LuaMsgHelper", "swichCameraByEvent cameraFace = " + str2);
        if ("front".equals(str2)) {
            this.mDefinedLuaListener.onRequireSwitchCamera(1);
        } else if ("back".equals(str2)) {
            this.mDefinedLuaListener.onRequireSwitchCamera(0);
        } else {
            this.mDefinedLuaListener.onRequireSwitchCamera(-1);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean switchCameraById(HashMap<String, Object> hashMap) {
        int obj2Int = MsgParamsUtil.obj2Int(hashMap.get("id"), -1);
        Log.d("LuaMsgHelper", "switchCameraById id = " + obj2Int);
        if (obj2Int == 10202) {
            this.mDefinedLuaListener.onRequireSwitchCamera(-1);
            return true;
        }
        return false;
    }

    public void setDefinedLuaListener(DefinedLuaListener definedLuaListener) {
        this.mDefinedLuaListener = definedLuaListener;
    }

    public void release() {
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge != null) {
            if (this.mLuaMsgListener != null) {
                engineMsgBridge.getLuaMsgBridge().removeLuaMsgListener(this.mLuaMsgListener);
                this.mLuaMsgListener = null;
            }
            EngineMsgListener engineMsgListener = this.mEngineMsgListener;
            if (engineMsgListener != null) {
                this.mEngineMsgBridge.removeEngineMsgListener(engineMsgListener);
                this.mEngineMsgListener = null;
            }
            this.mEngineMsgBridge = null;
        }
        this.mDefinedLuaListener = null;
    }
}
