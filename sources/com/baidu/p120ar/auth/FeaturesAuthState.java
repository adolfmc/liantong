package com.baidu.p120ar.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.FeaturesAuthState */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FeaturesAuthState {
    private String mError;
    private volatile List<Integer> mFeatures;
    private volatile State mState = State.RUNNING;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.auth.FeaturesAuthState$State */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum State {
        RUNNING,
        PASS,
        FAIL
    }

    public FeaturesAuthState(List<Integer> list) {
        this.mFeatures = list;
        notifyAvailFeatures();
    }

    public void setAuthFail(String str) {
        synchronized (this) {
            this.mState = State.FAIL;
            this.mError = str;
        }
        updateAvailFeatures(null);
    }

    public void setAuthPass() {
        synchronized (this) {
            this.mState = State.PASS;
        }
    }

    public boolean isRunning() {
        return this.mState == State.RUNNING;
    }

    public boolean isFailed() {
        return this.mState == State.FAIL;
    }

    public String getError() {
        return this.mError;
    }

    public void updateAvailFeatures(Set<Integer> set) {
        synchronized (this) {
            if (this.mFeatures == null) {
                this.mFeatures = new ArrayList();
            }
            this.mFeatures.clear();
            if (set != null && !set.isEmpty()) {
                this.mFeatures.addAll(set);
            }
            notifyAvailFeatures();
        }
    }

    public final void notifyAvailFeatures() {
        if (this.mFeatures != null) {
            int[] iArr = new int[this.mFeatures.size()];
            int size = this.mFeatures.size();
            for (int i = 0; i < size; i++) {
                iArr[i] = this.mFeatures.get(i).intValue();
            }
            AuthJni.setGrantedFeatures(iArr);
        }
    }

    public boolean isFeatureGranted(int i) {
        return (isFailed() || this.mFeatures == null || !this.mFeatures.contains(Integer.valueOf(i))) ? false : true;
    }

    public List<Integer> getAvailFeatures() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList();
            if (this.mFeatures != null) {
                arrayList.addAll(this.mFeatures);
            }
        }
        return arrayList;
    }
}
