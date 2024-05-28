package com.mob;

import com.mob.tools.network.NetCommunicator;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class MobCommunicator implements PublicMemberKeeper {

    /* renamed from: a */
    private NetCommunicator f13978a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Callback<T> implements PublicMemberKeeper {
        public void onResultError(Throwable th) {
        }

        public void onResultOk(T t) {
        }
    }

    public MobCommunicator(int i, String str, String str2) {
        this.f13978a = new NetCommunicator(i, str, str2);
    }

    public void setThreadPool(ThreadPoolExecutor threadPoolExecutor) {
        this.f13978a.setThreadPool(threadPoolExecutor);
    }

    public <T> void request(HashMap<String, Object> hashMap, String str, boolean z, Callback<T> callback) {
        request(true, null, hashMap, str, z, callback);
    }

    public <T> void request(HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z, Callback<T> callback) {
        request(true, hashMap, hashMap2, str, z, callback);
    }

    public <T> void request(boolean z, HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z2, final Callback<T> callback) {
        if (callback == null) {
            this.f13978a.request(z, hashMap, hashMap2, str, z2, null);
        } else {
            this.f13978a.request(z, hashMap, hashMap2, str, z2, new NetCommunicator.Callback<T>() { // from class: com.mob.MobCommunicator.1
                @Override // com.mob.tools.network.NetCommunicator.Callback
                public void onResultOk(T t) {
                    callback.onResultOk(t);
                }

                @Override // com.mob.tools.network.NetCommunicator.Callback
                public void onResultError(Throwable th) {
                    callback.onResultError(th);
                }
            });
        }
    }

    public <T> T requestSynchronized(HashMap<String, Object> hashMap, String str, boolean z) throws Throwable {
        return (T) requestSynchronized((HashMap<String, String>) null, hashMap, str, z);
    }

    public <T> T requestSynchronized(HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z) throws Throwable {
        return (T) requestSynchronized(true, hashMap, hashMap2, str, z);
    }

    public <T> T requestSynchronized(String str, String str2, boolean z) throws Throwable {
        return (T) requestSynchronized((HashMap<String, String>) null, str, str2, z);
    }

    public <T> T requestSynchronized(HashMap<String, String> hashMap, String str, String str2, boolean z) throws Throwable {
        return (T) requestSynchronized(true, hashMap, str, str2, z);
    }

    public <T> T requestSynchronized(boolean z, HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z2) throws Throwable {
        return (T) this.f13978a.requestSynchronized(z, hashMap, hashMap2, str, z2);
    }

    public <T> T requestSynchronized(boolean z, HashMap<String, String> hashMap, String str, String str2, boolean z2) throws Throwable {
        return (T) this.f13978a.requestSynchronized(z, hashMap, str, str2, z2);
    }

    public static HashMap<String, String> getCommonDefaultHeaders() throws Throwable {
        return NetCommunicator.getCommonDefaultHeaders();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class NetworkError extends Exception implements PublicMemberKeeper {
        private static final long serialVersionUID = -8447657431687664787L;

        public NetworkError(String str) {
            super(str);
        }
    }
}
