package com.sinovatech.unicom.separatemodule.gamecenter;

import android.support.p086v7.app.AppCompatActivity;
import io.reactivex.functions.Consumer;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class GameCentersLogManager {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$log$0(String str) throws Exception {
    }

    public static void log(AppCompatActivity appCompatActivity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        HashMap hashMap = new HashMap();
        hashMap.put("p2", "40526");
        hashMap.put("p3", "游戏专区App客户端");
        hashMap.put("p5", "2");
        hashMap.put("p24", str9);
        hashMap.put("p25", str);
        hashMap.put("p26", str2);
        hashMap.put("p27", str3);
        hashMap.put("p28", str4);
        hashMap.put("p31", str5);
        hashMap.put("p32", "Android");
        hashMap.put("p33", str6);
        hashMap.put("p34", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("p35", str7);
        hashMap.put("p23", "");
        hashMap.put("p16", str8);
        GameCentersDataManager.getInstance().commonLog(appCompatActivity, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersLogManager$3gaYWdZ3HqPd63WbKrG2sY9E6wA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCentersLogManager.lambda$log$0((String) obj);
            }
        });
    }
}
