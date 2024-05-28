package com.sinovatech.unicom.basic.p315ui.home.manager;

import android.support.p086v7.app.AppCompatActivity;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.p314po.HomeTabEntity;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import java.util.HashMap;

/* renamed from: com.sinovatech.unicom.basic.ui.home.manager.HomeTabManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeTabManager extends BaseRequestManager<HomeTabEntity> {
    private static final String CACHE_KEY = "HomeTabManager4";

    public HomeTabManager(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    public ObservableSubscribeProxy<HomeTabEntity> getHomeTab(String str) {
        String currentProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
        String currentCityCode = UserManager.getInstance().getCurrentCityCode();
        Observable<String> rxGet = App.getAsyncHttpClient().rxGet(URLSet.getHomeTabUrl(currentProvinceCode, currentCityCode), new HashMap(), getHeader());
        return setRequestType(str).setNetObserver(rxGet).setCacheKey(CACHE_KEY + currentProvinceCode + currentCityCode, "0").setFunction(new C77001(str)).getObservable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.home.manager.HomeTabManager$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C77001 extends BaseFunction<HomeTabEntity> {
        final /* synthetic */ String val$requestType;

        C77001(String str) {
            this.val$requestType = str;
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x00a1  */
        /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
        @Override // io.reactivex.functions.Function
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.sinovatech.unicom.basic.p314po.HomeTabEntity apply(java.lang.String r5) throws java.lang.Exception {
            /*
                r4 = this;
                com.sinovatech.unicom.basic.po.HomeTabEntity r0 = new com.sinovatech.unicom.basic.po.HomeTabEntity
                r0.<init>()
                com.google.gson.Gson r1 = com.hjq.gson.factory.GsonFactory.getSingletonGson()
                com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$HomeTabManager$1$Xd17CP_2lp6oniBgXxBHpblQxso r2 = new com.hjq.gson.factory.JsonCallback() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$HomeTabManager$1$Xd17CP_2lp6oniBgXxBHpblQxso
                    static {
                        /*
                            com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$HomeTabManager$1$Xd17CP_2lp6oniBgXxBHpblQxso r0 = new com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$HomeTabManager$1$Xd17CP_2lp6oniBgXxBHpblQxso
                            r0.<init>()
                            
                            // error: 0x0005: SPUT  (r0 I:com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$HomeTabManager$1$Xd17CP_2lp6oniBgXxBHpblQxso) com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$HomeTabManager$1$Xd17CP_2lp6oniBgXxBHpblQxso.INSTANCE com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$HomeTabManager$1$Xd17CP_2lp6oniBgXxBHpblQxso
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.home.manager.$$Lambda$HomeTabManager$1$Xd17CP_2lp6oniBgXxBHpblQxso.<clinit>():void");
                    }

                    {
                        /*
                            r0 = this;
                            r0.<init>()
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.home.manager.$$Lambda$HomeTabManager$1$Xd17CP_2lp6oniBgXxBHpblQxso.<init>():void");
                    }

                    @Override // com.hjq.gson.factory.JsonCallback
                    public final void onTypeException(com.google.gson.reflect.TypeToken r1, java.lang.String r2, com.google.gson.stream.JsonToken r3) {
                        /*
                            r0 = this;
                            com.sinovatech.unicom.basic.p315ui.home.manager.HomeTabManager.C77001.lambda$apply$0(r1, r2, r3)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.home.manager.$$Lambda$HomeTabManager$1$Xd17CP_2lp6oniBgXxBHpblQxso.onTypeException(com.google.gson.reflect.TypeToken, java.lang.String, com.google.gson.stream.JsonToken):void");
                    }
                }
                com.hjq.gson.factory.GsonFactory.setJsonCallback(r2)
                boolean r2 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L74
                if (r2 != 0) goto L29
                java.lang.Class<com.sinovatech.unicom.basic.po.HomeTabEntity> r2 = com.sinovatech.unicom.basic.p314po.HomeTabEntity.class
                boolean r3 = r1 instanceof com.google.gson.Gson     // Catch: java.lang.Exception -> L74
                if (r3 != 0) goto L1f
                java.lang.Object r5 = r1.fromJson(r5, r2)     // Catch: java.lang.Exception -> L74
                goto L26
            L1f:
                r3 = r1
                com.google.gson.Gson r3 = (com.google.gson.Gson) r3     // Catch: java.lang.Exception -> L74
                java.lang.Object r5 = com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation.fromJson(r3, r5, r2)     // Catch: java.lang.Exception -> L74
            L26:
                com.sinovatech.unicom.basic.po.HomeTabEntity r5 = (com.sinovatech.unicom.basic.p314po.HomeTabEntity) r5     // Catch: java.lang.Exception -> L74
                r0 = r5
            L29:
                java.lang.String r5 = "1"
                java.lang.String r2 = r4.val$requestType     // Catch: java.lang.Exception -> L74
                boolean r5 = r5.equals(r2)     // Catch: java.lang.Exception -> L74
                if (r5 == 0) goto L72
                java.lang.String r5 = "0000"
                java.lang.String r2 = r0.getRespCode()     // Catch: java.lang.Exception -> L74
                boolean r5 = r5.equals(r2)     // Catch: java.lang.Exception -> L74
                if (r5 == 0) goto L51
                com.sinovatech.unicom.basic.po.HomeTabEntity$DataDTO r5 = r0.getData()     // Catch: java.lang.Exception -> L74
                com.sinovatech.unicom.basic.po.HomeTabEntity$DataDTO$IndexSelectedTabDTO r5 = r5.getIndexSelectedTab()     // Catch: java.lang.Exception -> L74
                java.util.List r5 = r5.getTabCfgArray()     // Catch: java.lang.Exception -> L74
                int r5 = r5.size()     // Catch: java.lang.Exception -> L74
                if (r5 != 0) goto L72
            L51:
                com.sinovatech.unicom.basic.ui.home.manager.HomeTabManager r5 = com.sinovatech.unicom.basic.p315ui.home.manager.HomeTabManager.this     // Catch: java.lang.Exception -> L74
                java.lang.String r5 = r5.getCacheData()     // Catch: java.lang.Exception -> L74
                boolean r2 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L74
                if (r2 != 0) goto L72
                java.lang.Class<com.sinovatech.unicom.basic.po.HomeTabEntity> r2 = com.sinovatech.unicom.basic.p314po.HomeTabEntity.class
                boolean r3 = r1 instanceof com.google.gson.Gson     // Catch: java.lang.Exception -> L74
                if (r3 != 0) goto L68
                java.lang.Object r5 = r1.fromJson(r5, r2)     // Catch: java.lang.Exception -> L74
                goto L6f
            L68:
                r3 = r1
                com.google.gson.Gson r3 = (com.google.gson.Gson) r3     // Catch: java.lang.Exception -> L74
                java.lang.Object r5 = com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation.fromJson(r3, r5, r2)     // Catch: java.lang.Exception -> L74
            L6f:
                com.sinovatech.unicom.basic.po.HomeTabEntity r5 = (com.sinovatech.unicom.basic.p314po.HomeTabEntity) r5     // Catch: java.lang.Exception -> L74
                goto L8f
            L72:
                r5 = r0
                goto L8f
            L74:
                r5 = move-exception
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "解析首页tab数据异常:"
                r2.append(r3)
                java.lang.String r5 = r5.getMessage()
                r2.append(r5)
                java.lang.String r5 = r2.toString()
                com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7978e(r5)
                r5 = r0
            L8f:
                com.sinovatech.unicom.basic.po.HomeTabEntity$DataDTO r0 = r5.getData()
                com.sinovatech.unicom.basic.po.HomeTabEntity$DataDTO$IndexSelectedTabDTO r0 = r0.getIndexSelectedTab()
                java.util.List r0 = r0.getTabCfgArray()
                int r0 = r0.size()
                if (r0 != 0) goto Lca
                com.sinovatech.unicom.basic.ui.home.manager.HomeTabManager r5 = com.sinovatech.unicom.basic.p315ui.home.manager.HomeTabManager.this
                android.support.v7.app.AppCompatActivity r5 = r5.activityContext
                android.content.res.Resources r5 = r5.getResources()
                android.content.res.AssetManager r5 = r5.getAssets()
                java.lang.String r0 = "home_tab.json"
                java.io.InputStream r5 = r5.open(r0)
                java.lang.String r5 = com.sinovatech.unicom.common.FileTools.readInputStream(r5)
                java.lang.Class<com.sinovatech.unicom.basic.po.HomeTabEntity> r0 = com.sinovatech.unicom.basic.p314po.HomeTabEntity.class
                boolean r2 = r1 instanceof com.google.gson.Gson
                if (r2 != 0) goto Lc2
                java.lang.Object r5 = r1.fromJson(r5, r0)
                goto Lc8
            Lc2:
                com.google.gson.Gson r1 = (com.google.gson.Gson) r1
                java.lang.Object r5 = com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation.fromJson(r1, r5, r0)
            Lc8:
                com.sinovatech.unicom.basic.po.HomeTabEntity r5 = (com.sinovatech.unicom.basic.p314po.HomeTabEntity) r5
            Lca:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.home.manager.HomeTabManager.C77001.apply(java.lang.String):com.sinovatech.unicom.basic.po.HomeTabEntity");
        }
    }
}
