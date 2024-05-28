package com.baidu.p120ar.libloader;

import com.baidu.p120ar.libloader.ILibLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.libloader.ReadyEventDispatcher */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ReadyEventDispatcher {
    private final Map<String, List<ILibLoader.ReadyListener>> mListeners = new HashMap();

    public void addListener(String str, ILibLoader.ReadyListener readyListener) {
        List<ILibLoader.ReadyListener> list = this.mListeners.get(str);
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(readyListener);
            this.mListeners.put(str, arrayList);
            return;
        }
        list.add(readyListener);
    }

    public void removeListener(String str, ILibLoader.ReadyListener readyListener) {
        List<ILibLoader.ReadyListener> list = this.mListeners.get(str);
        if (list != null) {
            list.remove(readyListener);
        }
    }

    public void removeTag(String str) {
        if (this.mListeners.containsKey(str)) {
            this.mListeners.remove(str);
        }
    }

    public void dispatch(String str) {
        callListeners(this.mListeners.get(str));
    }

    private void callListeners(List<ILibLoader.ReadyListener> list) {
        if (list != null) {
            ILibLoader.ReadyListener[] readyListenerArr = new ILibLoader.ReadyListener[list.size()];
            list.toArray(readyListenerArr);
            for (ILibLoader.ReadyListener readyListener : readyListenerArr) {
                readyListener.onReady();
            }
        }
    }

    public void dispatchAll() {
        for (Map.Entry<String, List<ILibLoader.ReadyListener>> entry : this.mListeners.entrySet()) {
            callListeners(entry.getValue());
        }
    }

    public void clearAll() {
        this.mListeners.clear();
    }
}
