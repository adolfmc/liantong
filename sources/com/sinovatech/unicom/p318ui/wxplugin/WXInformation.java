package com.sinovatech.unicom.p318ui.wxplugin;

import com.fort.andjni.JniLib;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.ui.wxplugin.WXInformation */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WXInformation {
    private static WXInformation wxInformation;
    private WXInterface wxIF;

    /* renamed from: com.sinovatech.unicom.ui.wxplugin.WXInformation$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97381 implements Observer<String> {
        final /* synthetic */ WXInterface val$wxIF;

        C97381(WXInterface wXInterface) {
            JniLib.m15918cV(this, wXInterface, 376);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            JniLib.m15918cV(this, 373);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            JniLib.m15918cV(this, th, 374);
        }

        @Override // io.reactivex.Observer
        public void onNext(String str) {
            JniLib.m15918cV(this, str, 375);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.ui.wxplugin.WXInformation$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C97392 implements Function<String, String> {
        C97392() {
            JniLib.m15918cV(this, 377);
        }

        @Override // io.reactivex.functions.Function
        public String apply(String str) throws Exception {
            return str;
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.wxplugin.WXInformation$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97403 implements Observer<String> {
        final /* synthetic */ WXInterface val$wxIF;

        C97403(WXInterface wXInterface) {
            JniLib.m15918cV(this, wXInterface, 381);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            JniLib.m15918cV(this, 378);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            JniLib.m15918cV(this, th, 379);
        }

        @Override // io.reactivex.Observer
        public void onNext(String str) {
            JniLib.m15918cV(this, str, 380);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.ui.wxplugin.WXInformation$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C97414 implements Function<String, String> {
        C97414() {
            JniLib.m15918cV(this, 382);
        }

        @Override // io.reactivex.functions.Function
        public String apply(String str) throws Exception {
            return str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.ui.wxplugin.WXInformation$WXInterface */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface WXInterface {
        void fail(String str);

        void success(JSONObject jSONObject);
    }

    public WXInformation() {
        JniLib.m15918cV(this, 383);
    }

    public static void getWXAccessToken(String str, String str2, String str3, String str4, WXInterface wXInterface) {
        JniLib.m15918cV(str, str2, str3, str4, wXInterface, 384);
    }

    public static void getWXUserinfo(String str, String str2, String str3, WXInterface wXInterface) {
        JniLib.m15918cV(str, str2, str3, wXInterface, 385);
    }

    public static synchronized WXInformation getInstance() {
        WXInformation wXInformation;
        synchronized (WXInformation.class) {
            if (wxInformation == null) {
                synchronized (WXInformation.class) {
                    if (wxInformation == null) {
                        wxInformation = new WXInformation();
                    }
                }
            }
            wXInformation = wxInformation;
        }
        return wXInformation;
    }
}
