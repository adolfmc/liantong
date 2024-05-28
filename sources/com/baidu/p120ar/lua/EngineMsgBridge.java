package com.baidu.p120ar.lua;

import android.content.Context;
import com.baidu.p120ar.arplay.core.message.ARPMessage;
import com.baidu.p120ar.arplay.message.ArSdkMessageHandler;
import com.baidu.p120ar.utils.ARLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.lua.EngineMsgBridge */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EngineMsgBridge extends ArSdkMessageHandler {
    private static final String TAG = "EngineMsgBridge";
    private List<WeakReference<EngineMsgListener>> mEngineMsgListeners;
    private LuaMsgBridge mLuaMsgBridge;
    private boolean mUserPlayAudio;

    public EngineMsgBridge(Context context) {
        super(context);
        this.mEngineMsgListeners = Collections.synchronizedList(new ArrayList());
        this.mUserPlayAudio = false;
        registerMessage();
        this.mLuaMsgBridge = new LuaMsgBridge();
        addEngineMsgListener(this.mLuaMsgBridge);
    }

    public LuaMsgBridge getLuaMsgBridge() {
        return this.mLuaMsgBridge;
    }

    public void setUserPlayAudio(boolean z) {
        this.mUserPlayAudio = z;
    }

    public synchronized boolean addEngineMsgListener(EngineMsgListener engineMsgListener) {
        if (this.mEngineMsgListeners == null) {
            return false;
        }
        for (WeakReference<EngineMsgListener> weakReference : this.mEngineMsgListeners) {
            if (weakReference.get() != null && weakReference.get().equals(engineMsgListener)) {
                return false;
            }
        }
        ARLog.m20421d("EngineMsgBridge", "addEngineMsgListener engineMsgListener = " + engineMsgListener.hashCode());
        return this.mEngineMsgListeners.add(new WeakReference<>(engineMsgListener));
    }

    public synchronized boolean removeEngineMsgListener(EngineMsgListener engineMsgListener) {
        if (this.mEngineMsgListeners == null) {
            return false;
        }
        for (WeakReference<EngineMsgListener> weakReference : this.mEngineMsgListeners) {
            if (weakReference.get() != null && weakReference.get().equals(engineMsgListener)) {
                ARLog.m20421d("EngineMsgBridge", "removeEngineMsgListener engineMsgListener = " + engineMsgListener.hashCode());
                boolean remove = this.mEngineMsgListeners.remove(weakReference);
                weakReference.clear();
                return remove;
            }
        }
        return false;
    }

    public void sendMsg2Engine(int i, HashMap<String, Object> hashMap) {
        ARPMessage.getInstance().sendMessage(i, hashMap);
    }

    public void sendLuaScript2Engine(String str) {
        ARPMessage.getInstance().sendLuaScriptToEngine(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    @Override // com.baidu.p120ar.arplay.message.ArSdkMessageHandler, com.baidu.p120ar.arplay.core.message.ARPMessage.MessageHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void handleMessage(int r5, int r6, java.util.HashMap<java.lang.String, java.lang.Object> r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = "EngineMsgBridge"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7c
            r1.<init>()     // Catch: java.lang.Throwable -> L7c
            java.lang.String r2 = "handleMessage aMessageType = "
            r1.append(r2)     // Catch: java.lang.Throwable -> L7c
            r1.append(r5)     // Catch: java.lang.Throwable -> L7c
            java.lang.String r2 = " && aMessageID = "
            r1.append(r2)     // Catch: java.lang.Throwable -> L7c
            r1.append(r6)     // Catch: java.lang.Throwable -> L7c
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L7c
            com.baidu.p120ar.utils.ARLog.m20421d(r0, r1)     // Catch: java.lang.Throwable -> L7c
            boolean r0 = r4.mUserPlayAudio     // Catch: java.lang.Throwable -> L7c
            if (r0 == 0) goto L2d
            boolean r0 = r4.audioMessageType(r5, r6, r7)     // Catch: java.lang.Throwable -> L7c
            if (r0 != 0) goto L2a
            goto L2d
        L2a:
            r5 = 1901(0x76d, float:2.664E-42)
            goto L30
        L2d:
            super.handleMessage(r5, r6, r7)     // Catch: java.lang.Throwable -> L7c
        L30:
            java.util.List<java.lang.ref.WeakReference<com.baidu.ar.lua.EngineMsgListener>> r0 = r4.mEngineMsgListeners     // Catch: java.lang.Throwable -> L7c
            if (r0 != 0) goto L36
            monitor-exit(r4)
            return
        L36:
            r0 = 0
        L37:
            java.util.List<java.lang.ref.WeakReference<com.baidu.ar.lua.EngineMsgListener>> r1 = r4.mEngineMsgListeners     // Catch: java.lang.Throwable -> L7c
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L7c
            if (r0 >= r1) goto L7a
            java.util.List<java.lang.ref.WeakReference<com.baidu.ar.lua.EngineMsgListener>> r1 = r4.mEngineMsgListeners     // Catch: java.lang.Throwable -> L7c
            java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> L7c
            java.lang.ref.WeakReference r1 = (java.lang.ref.WeakReference) r1     // Catch: java.lang.Throwable -> L7c
            if (r1 != 0) goto L4a
            goto L77
        L4a:
            java.lang.Object r1 = r1.get()     // Catch: java.lang.Throwable -> L7c
            com.baidu.ar.lua.EngineMsgListener r1 = (com.baidu.p120ar.lua.EngineMsgListener) r1     // Catch: java.lang.Throwable -> L7c
            if (r1 == 0) goto L77
            java.util.List r2 = r1.getMsgTypesListened()     // Catch: java.lang.Throwable -> L7c
            if (r2 != 0) goto L59
            goto L77
        L59:
            java.util.List r2 = r1.getMsgTypesListened()     // Catch: java.lang.Throwable -> L7c
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L7c
        L61:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L7c
            if (r3 == 0) goto L77
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L7c
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: java.lang.Throwable -> L7c
            int r3 = r3.intValue()     // Catch: java.lang.Throwable -> L7c
            if (r5 != r3) goto L61
            r1.onEngineMessage(r5, r6, r7)     // Catch: java.lang.Throwable -> L7c
            goto L61
        L77:
            int r0 = r0 + 1
            goto L37
        L7a:
            monitor-exit(r4)
            return
        L7c:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.lua.EngineMsgBridge.handleMessage(int, int, java.util.HashMap):void");
    }

    public synchronized void destroy() {
        if (this.mLuaMsgBridge != null) {
            this.mLuaMsgBridge.destroy();
            this.mLuaMsgBridge = null;
        }
        if (this.mEngineMsgListeners != null) {
            for (WeakReference<EngineMsgListener> weakReference : this.mEngineMsgListeners) {
                weakReference.clear();
            }
            this.mEngineMsgListeners.clear();
            this.mEngineMsgListeners = null;
        }
    }

    private synchronized boolean audioMessageType(int i, int i2, HashMap<String, Object> hashMap) {
        if (i == 1001) {
            hashMap.put("audio_status", "audio_play");
            return true;
        } else if (i == 1003) {
            hashMap.put("audio_status", "audio_pause");
            return true;
        } else if (i == 1005) {
            hashMap.put("audio_status", "audio_resume");
            return true;
        } else if (i == 1007) {
            hashMap.put("audio_status", "audio_stop");
            return true;
        } else if (i != 1012) {
            return false;
        } else {
            hashMap.put("audio_status", "audio_reset");
            return true;
        }
    }
}
