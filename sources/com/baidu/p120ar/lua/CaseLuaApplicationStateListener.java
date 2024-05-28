package com.baidu.p120ar.lua;

import com.baidu.p120ar.ILuaApplicationState;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.lua.CaseLuaApplicationStateListener */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CaseLuaApplicationStateListener implements ILuaApplicationState, EngineMsgListener {
    private boolean mIsSetOnTrackingLostHandler = false;
    private boolean mIsSetOnTrackingFoundHandler = false;

    public void init(EngineMsgBridge engineMsgBridge) {
        engineMsgBridge.addEngineMsgListener(this);
    }

    @Override // com.baidu.p120ar.lua.EngineMsgListener
    public List<Integer> getMsgTypesListened() {
        return Arrays.asList(103, 104);
    }

    @Override // com.baidu.p120ar.lua.EngineMsgListener
    public void onEngineMessage(int i, int i2, HashMap<String, Object> hashMap) {
        switch (i) {
            case 103:
                this.mIsSetOnTrackingFoundHandler = true;
                return;
            case 104:
                this.mIsSetOnTrackingLostHandler = true;
                return;
            default:
                return;
        }
    }

    public void resetState() {
        this.mIsSetOnTrackingLostHandler = false;
        this.mIsSetOnTrackingFoundHandler = false;
    }

    @Override // com.baidu.p120ar.ILuaApplicationState
    public boolean isSetOnTrackingLostHandler() {
        return this.mIsSetOnTrackingLostHandler;
    }

    @Override // com.baidu.p120ar.ILuaApplicationState
    public boolean isSetOnTrackingFoundHandler() {
        return this.mIsSetOnTrackingFoundHandler;
    }

    public void release() {
        resetState();
    }
}
