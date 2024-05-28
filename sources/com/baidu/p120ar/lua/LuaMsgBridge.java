package com.baidu.p120ar.lua;

import android.text.TextUtils;
import com.baidu.p120ar.utils.ARLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.lua.LuaMsgBridge */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LuaMsgBridge implements EngineMsgListener {
    private static final String TAG = "LuaMsgBridge";
    private List<LuaMsgListener> mLuaMsgListeners = Collections.synchronizedList(new ArrayList());

    public synchronized boolean addLuaMsgListener(LuaMsgListener luaMsgListener) {
        if (this.mLuaMsgListeners == null) {
            return false;
        }
        for (LuaMsgListener luaMsgListener2 : this.mLuaMsgListeners) {
            if (luaMsgListener2 != null && luaMsgListener2.equals(luaMsgListener)) {
                return false;
            }
        }
        ARLog.m20421d("LuaMsgBridge", "addLuaMsgListener luaMsgListener = " + luaMsgListener.hashCode());
        return this.mLuaMsgListeners.add(luaMsgListener);
    }

    public synchronized boolean removeLuaMsgListener(LuaMsgListener luaMsgListener) {
        if (this.mLuaMsgListeners == null) {
            return false;
        }
        for (LuaMsgListener luaMsgListener2 : this.mLuaMsgListeners) {
            if (luaMsgListener2 != null && luaMsgListener2.equals(luaMsgListener)) {
                ARLog.m20421d("LuaMsgBridge", "removeLuaMsgListener luaMsgListener = " + luaMsgListener.hashCode());
                return this.mLuaMsgListeners.remove(luaMsgListener2);
            }
        }
        return false;
    }

    public synchronized void destroy() {
        if (this.mLuaMsgListeners != null) {
            this.mLuaMsgListeners.clear();
            this.mLuaMsgListeners = null;
        }
    }

    @Override // com.baidu.p120ar.lua.EngineMsgListener
    public List<Integer> getMsgTypesListened() {
        return Arrays.asList(1901);
    }

    @Override // com.baidu.p120ar.lua.EngineMsgListener
    public synchronized void onEngineMessage(int i, int i2, HashMap<String, Object> hashMap) {
        List<String> msgKeyListened;
        if (this.mLuaMsgListeners != null && hashMap != null) {
            for (int i3 = 0; i3 < this.mLuaMsgListeners.size(); i3++) {
                LuaMsgListener luaMsgListener = this.mLuaMsgListeners.get(i3);
                if (luaMsgListener != null && (msgKeyListened = luaMsgListener.getMsgKeyListened()) != null) {
                    for (int i4 = 0; i4 < msgKeyListened.size(); i4++) {
                        String str = msgKeyListened.get(i4);
                        if (!TextUtils.isEmpty(str) && hashMap.get(str) != null) {
                            luaMsgListener.onLuaMessage(hashMap);
                        }
                    }
                }
            }
        }
    }
}
