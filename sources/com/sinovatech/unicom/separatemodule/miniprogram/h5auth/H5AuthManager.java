package com.sinovatech.unicom.separatemodule.miniprogram.h5auth;

import android.content.Context;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.JSScopeEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthRecordEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthRecordEntity_;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthScopeManager;
import io.objectbox.Box;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class H5AuthManager {
    private static H5AuthManager instance;
    private Context context;
    private Box<H5RegisterEntity> h5RegisterEntityBox = App.getBoxStore().boxFor(H5RegisterEntity.class);
    private Box<UserAuthRecordEntity> authRecordBox = App.getBoxStore().boxFor(UserAuthRecordEntity.class);

    public void deleteH5AuthRecord(String str) {
    }

    public static synchronized H5AuthManager getInstance(Context context) {
        H5AuthManager h5AuthManager;
        synchronized (H5AuthManager.class) {
            if (instance == null) {
                synchronized (H5AuthManager.class) {
                    if (instance == null) {
                        instance = new H5AuthManager(context);
                    }
                }
            }
            h5AuthManager = instance;
        }
        return h5AuthManager;
    }

    public H5AuthManager(Context context) {
        this.context = context;
    }

    public H5RegisterEntity getH5RegisterEntity(String str) {
        try {
            return this.h5RegisterEntityBox.query().equal(H5RegisterEntity_.appId, str).build().findFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<H5AuthRecord> getH5AuthRecord() {
        ArrayList arrayList = new ArrayList();
        try {
            List<UserAuthRecordEntity> find = this.authRecordBox.query().equal(UserAuthRecordEntity_.scene, "h5").order(UserAuthRecordEntity_.scope).build().find();
            for (int i = 0; i < find.size(); i++) {
                UserAuthRecordEntity userAuthRecordEntity = find.get(i);
                H5RegisterEntity h5RegisterEntity = getH5RegisterEntity(userAuthRecordEntity.getKey());
                JSScopeEntity jSScopeEntity = UserAuthScopeManager.getInstance(this.context).getJSScopeEntity(userAuthRecordEntity.getScope());
                H5AuthRecord h5AuthRecord = new H5AuthRecord();
                h5AuthRecord.setAppId(userAuthRecordEntity.getKey());
                h5AuthRecord.setAppName(h5RegisterEntity.getAppName());
                h5AuthRecord.setAuthScopeCode(jSScopeEntity.getScope());
                h5AuthRecord.setAuthScopeDesc(jSScopeEntity.getDesc());
                h5AuthRecord.setAuthScopeHint(jSScopeEntity.getHint());
                h5AuthRecord.setAuthReson(userAuthRecordEntity.getReason());
                h5AuthRecord.setAuthStatus(userAuthRecordEntity.isGrant());
                arrayList.add(h5AuthRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public List<H5AuthRecord> getH5AuthRecord(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            List<H5AuthRecord> h5AuthRecord = getH5AuthRecord();
            for (int i = 0; i < h5AuthRecord.size(); i++) {
                H5AuthRecord h5AuthRecord2 = h5AuthRecord.get(i);
                if (h5AuthRecord2.getAppId().equals(str)) {
                    arrayList.add(h5AuthRecord2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void updateH5AuthStatus(String str, String str2, boolean z) {
        UserAuthScopeManager.getInstance(this.context).updateH5AuthStatus(str, str2, z);
    }
}
