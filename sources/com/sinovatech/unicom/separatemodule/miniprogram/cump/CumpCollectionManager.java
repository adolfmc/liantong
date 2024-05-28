package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import android.content.Context;
import com.sinovatech.unicom.p318ui.App;
import io.objectbox.Box;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CumpCollectionManager {
    public static String TAG = "CumpCollectionManager";
    private static CumpCollectionManager instance;
    private Box<CumpCollectionEntity> collectionBox = App.getBoxStore().boxFor(CumpCollectionEntity.class);
    private Context context;

    public static synchronized CumpCollectionManager getInstance(Context context) {
        CumpCollectionManager cumpCollectionManager;
        synchronized (CumpCollectionManager.class) {
            if (instance == null) {
                synchronized (CumpCollectionManager.class) {
                    if (instance == null) {
                        instance = new CumpCollectionManager(context);
                    }
                }
            }
            cumpCollectionManager = instance;
        }
        return cumpCollectionManager;
    }

    public CumpCollectionManager(Context context) {
        this.context = context;
    }

    public void addCollection(CumpCollectionEntity cumpCollectionEntity) {
        this.collectionBox.put((Box<CumpCollectionEntity>) cumpCollectionEntity);
    }

    public void removeCollection(CumpCollectionEntity cumpCollectionEntity) {
        this.collectionBox.remove((Box<CumpCollectionEntity>) cumpCollectionEntity);
    }

    public CumpCollectionEntity getCumpFromCollection(String str) {
        return this.collectionBox.query().equal(CumpCollectionEntity_.appId, str).build().findFirst();
    }
}
