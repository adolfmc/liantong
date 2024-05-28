package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapapi.map.SwipeDismissTouchListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.z */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2786z implements SwipeDismissTouchListener.DismissCallbacks {

    /* renamed from: a */
    final /* synthetic */ SwipeDismissView f6600a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2786z(SwipeDismissView swipeDismissView) {
        this.f6600a = swipeDismissView;
    }

    @Override // com.baidu.mapapi.map.SwipeDismissTouchListener.DismissCallbacks
    public boolean canDismiss(Object obj) {
        return true;
    }

    @Override // com.baidu.mapapi.map.SwipeDismissTouchListener.DismissCallbacks
    public void onDismiss(View view, Object obj) {
        if (this.f6600a.f6405a == null) {
            return;
        }
        this.f6600a.f6405a.onDismiss();
    }

    @Override // com.baidu.mapapi.map.SwipeDismissTouchListener.DismissCallbacks
    public void onNotify() {
        if (this.f6600a.f6405a == null) {
            return;
        }
        this.f6600a.f6405a.onNotify();
    }
}
