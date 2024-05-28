package com.sinovatech.unicom.separatemodule.miniprogram.jspermission;

import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.Box;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserAuthScopeManager {
    private static UserAuthScopeManager instance;
    private Box<UserAuthRecordEntity> authRecordBox = App.getBoxStore().boxFor(UserAuthRecordEntity.class);
    private Context context;
    private Map<String, JSScopeEntity> scopMap;

    public static synchronized UserAuthScopeManager getInstance(Context context) {
        UserAuthScopeManager userAuthScopeManager;
        synchronized (UserAuthScopeManager.class) {
            if (instance == null) {
                synchronized (UserAuthScopeManager.class) {
                    if (instance == null) {
                        instance = new UserAuthScopeManager(context);
                    }
                }
            }
            userAuthScopeManager = instance;
        }
        return userAuthScopeManager;
    }

    public UserAuthScopeManager(Context context) {
        this.context = context;
        initScopeData();
    }

    private void initScopeData() {
        this.scopMap = new HashMap();
        try {
            JSONArray jSONArray = new JSONArray(FileTools.readFile(this.context.getResources().getAssets().open("jsplugin_scope.json")));
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSScopeEntity jSScopeEntity = new JSScopeEntity(jSONObject.getString("scope"), jSONObject.getString("name"), jSONObject.getString("desc"));
                this.scopMap.put(jSScopeEntity.getScope(), jSScopeEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public JSScopeEntity getJSScopeEntity(String str) {
        return this.scopMap.get(str);
    }

    public boolean checkUserAuth(String str, String str2, String str3, String str4) {
        UserAuthRecordEntity findFirst;
        UserAuthRecordEntity findFirst2;
        MsLogUtil.m7980d("checkUserAuth userAccount=" + str + " edopAppId=" + str2 + " h5AppId=" + str3 + " action=" + str4);
        boolean z = true;
        try {
            JSPermissionEntity jSPermissionEntity = JSPermissionManager.getInstance(this.context).getJSPermissionEntity(str4);
            if (jSPermissionEntity != null && !TextUtils.isEmpty(jSPermissionEntity.getScope())) {
                MsLogUtil.m7980d("checkUserAuth：" + jSPermissionEntity.toString());
                if (!TextUtils.isEmpty(str2) && str2.startsWith("edop_unicom") && jSPermissionEntity.isReqUserGrant_edop()) {
                    CumpEntity appInfoFromBox = CumpLanucher.getInstance(this.context).getAppInfoFromBox(str2);
                    if (appInfoFromBox != null && !appInfoFromBox.isInnerMiniP() && ((findFirst2 = this.authRecordBox.query().equal(UserAuthRecordEntity_.userAccount, str).equal(UserAuthRecordEntity_.key, str2).equal(UserAuthRecordEntity_.scope, jSPermissionEntity.getScope()).build().findFirst()) == null || (findFirst2 != null && !findFirst2.isGrant()))) {
                        z = false;
                    }
                } else if (!TextUtils.isEmpty(str3) && jSPermissionEntity.isReqUserGrant_H5() && ((findFirst = this.authRecordBox.query().equal(UserAuthRecordEntity_.key, str3).equal(UserAuthRecordEntity_.scope, jSPermissionEntity.getScope()).equal(UserAuthRecordEntity_.scene, "h5").build().findFirst()) == null || (findFirst != null && !findFirst.isGrant()))) {
                    z = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    public List<UserAuthRecordEntity> getUserAuthRecord(String str, String str2) {
        List<UserAuthRecordEntity> list;
        try {
            list = this.authRecordBox.query().equal(UserAuthRecordEntity_.userAccount, str).equal(UserAuthRecordEntity_.key, str2).build().find();
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list == null ? new ArrayList() : list;
    }

    public UserAuthRecordEntity getUserAuthRecord(String str, String str2, String str3) {
        try {
            return this.authRecordBox.query().equal(UserAuthRecordEntity_.userAccount, str).equal(UserAuthRecordEntity_.key, str2).equal(UserAuthRecordEntity_.scope, str3).build().findFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserAuthRecordEntity> getH5AuthRecord(String str) {
        List<UserAuthRecordEntity> arrayList = new ArrayList<>();
        try {
            arrayList = this.authRecordBox.query().equal(UserAuthRecordEntity_.key, str).equal(UserAuthRecordEntity_.scene, "h5").build().find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList == null ? new ArrayList() : arrayList;
    }

    public UserAuthRecordEntity getH5AuthRecord(String str, String str2) {
        try {
            return this.authRecordBox.query().equal(UserAuthRecordEntity_.key, str).equal(UserAuthRecordEntity_.scope, str2).equal(UserAuthRecordEntity_.scene, "h5").build().findFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveAuthRecord(String str, String str2, String str3, boolean z) {
        try {
            UserAuthRecordEntity userAuthRecordEntity = new UserAuthRecordEntity(str, str2, str3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), z, "edop", "");
            UserAuthRecordEntity userAuthRecord = getUserAuthRecord(str, str2, str3);
            if (userAuthRecord != null) {
                this.authRecordBox.remove((Box<UserAuthRecordEntity>) userAuthRecord);
            }
            this.authRecordBox.put((Box<UserAuthRecordEntity>) userAuthRecordEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateH5AuthStatus(String str, String str2, boolean z) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            UserAuthRecordEntity h5AuthRecord = getH5AuthRecord(str, str2);
            if (h5AuthRecord != null) {
                h5AuthRecord.setGrant(z);
                h5AuthRecord.setTime(simpleDateFormat.format(new Date()));
                this.authRecordBox.put((Box<UserAuthRecordEntity>) h5AuthRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveH5AuthRecord(String str, String str2, boolean z, String str3) {
        try {
            UserAuthRecordEntity userAuthRecordEntity = new UserAuthRecordEntity("", str, str2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), z, "h5", str3);
            UserAuthRecordEntity h5AuthRecord = getH5AuthRecord(str, str2);
            if (h5AuthRecord != null) {
                this.authRecordBox.remove((Box<UserAuthRecordEntity>) h5AuthRecord);
            }
            this.authRecordBox.put((Box<UserAuthRecordEntity>) userAuthRecordEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteH5AuthRecord(String str) {
        try {
            this.authRecordBox.remove(getH5AuthRecord(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
