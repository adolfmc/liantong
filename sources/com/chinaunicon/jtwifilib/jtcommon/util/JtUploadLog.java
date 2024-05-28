package com.chinaunicon.jtwifilib.jtcommon.util;

import android.content.Context;
import com.chinaunicon.jtwifilib.core.global.JtApp;
import com.chinaunicon.jtwifilib.core.net.callback.OkCallback;
import com.chinaunicon.jtwifilib.core.net.parser.OkJsonParser;
import com.chinaunicon.jtwifilib.core.net.proxy.OkHttpProxy;
import com.chinaunicon.jtwifilib.core.utils.AesEncryptUtil;
import com.chinaunicon.jtwifilib.core.utils.AppInfo;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import com.chinaunicon.jtwifilib.core.utils.SharedPreferencesHelp;
import com.chinaunicon.jtwifilib.core.utils.StringUtil;
import com.chinaunicon.jtwifilib.jtcommon.model.JtInitBean;
import com.chinaunicon.jtwifilib.jtcommon.model.JtLogModel;
import com.chinaunicon.jtwifilib.jtcommon.model.JtRes;
import com.chinaunicon.jtwifilib.jtcommon.p188db.DBManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtUploadLog {
    private static JtUploadLog jtUploadLog;
    private Context context;
    private JtInitBean user;
    private String privinceName = "";
    private String privinceCode = "";
    private String cityName = "";
    private String cityCode = "";
    private String userPhone = "";
    private String Location_X = " ";
    private String Location_Y = "";
    private String uniqueIdentification = "";

    public JtUploadLog(Context context) {
        this.context = context;
    }

    public static JtUploadLog getInstance(Context context) {
        if (jtUploadLog == null) {
            jtUploadLog = new JtUploadLog(context);
        }
        return jtUploadLog;
    }

    public void setUser(JtInitBean jtInitBean) {
        this.user = jtInitBean;
    }

    public void setUniqueIdentification(String str) {
        this.uniqueIdentification = str;
    }

    public void setCityInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.privinceName = str;
        this.privinceCode = str2;
        this.cityName = str3;
        this.cityCode = str4;
        this.userPhone = str5;
        this.Location_X = str6;
        this.Location_Y = str7;
    }

    public void updateData(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        JtLogModel jtLogModel = new JtLogModel();
        jtLogModel.setPlatform("android");
        jtLogModel.setType(str);
        jtLogModel.setMessage(str2);
        jtLogModel.setOperType(str3);
        int i = SharedPreferencesHelp.getInstance(this.context, "version").getInt("JT_VERSION_CODE", 0);
        jtLogModel.setVersion(i + "");
        jtLogModel.setSdkVersion("1.1.2");
        jtLogModel.setUuid(StringUtil.getOnly(this.context));
        jtLogModel.setTime(System.currentTimeMillis() + "");
        jtLogModel.setAppId(AppInfo.getPackageName(this.context));
        jtLogModel.setAppKey(JtApp.getInstance().getAppKey());
        jtLogModel.setUniqueIdentification(this.uniqueIdentification);
        JtInitBean jtInitBean = this.user;
        if (jtInitBean != null) {
            jtLogModel.setUser(JtMD5Util.getMD5(jtInitBean.getUser()));
            jtLogModel.setProvince(this.user.getProvinceName());
            jtLogModel.setCity(this.user.getCityName());
            jtLogModel.setCityCode(this.user.getCity());
            jtLogModel.setProvinceCode(this.user.getProvince());
            jtLogModel.setLocation_X(this.user.getLocation_X());
            jtLogModel.setLocation_Y(this.user.getLocation_Y());
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("user", JtMD5Util.getMD5(this.user.getUser()));
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("province", this.user.getProvinceName());
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("city", this.user.getCityName());
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("provinceCode", this.user.getProvince());
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("cityCode", this.user.getCity());
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("Location_X", this.user.getLocation_X());
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("Location_Y", this.user.getLocation_Y());
        } else {
            jtLogModel.setProvince(this.privinceName);
            jtLogModel.setProvinceCode(this.privinceCode);
            jtLogModel.setCity(this.cityName);
            jtLogModel.setCityCode(this.cityCode);
            jtLogModel.setUser(JtMD5Util.getMD5(this.userPhone));
            jtLogModel.setLocation_X(this.Location_X);
            jtLogModel.setLocation_Y(this.Location_Y);
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("user", JtMD5Util.getMD5(this.userPhone));
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("province", this.privinceName);
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("city", this.cityName);
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("provinceCode", this.privinceCode);
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("cityCode", this.cityCode);
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("Location_X", this.Location_X);
            SharedPreferencesHelp.getInstance(this.context, "uuid").putString("Location_Y", this.Location_Y);
        }
        arrayList.add(jtLogModel);
        save(jtLogModel);
    }

    public void upListUserLog(final List<JtLogModel> list, final DBManager dBManager) {
        if (list.size() == 0) {
            return;
        }
        String str = "";
        try {
            str = AesEncryptUtil.encryptString(JtGsonUtil.getInstance().toJson((Object) list));
        } catch (Exception unused) {
        }
        OkHttpProxy.post().setCustomRequestBodyEntity(str).url("http://120.52.12.5:18080/zw_interface/api/user/uploadUser").tag(this).enqueue(new OkCallback<String>(new OkJsonParser<String>() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog.1
        }) { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog.2
            @Override // com.chinaunicon.jtwifilib.core.net.callback.OkCallback
            public void onFailure(Exception exc) {
            }

            @Override // com.chinaunicon.jtwifilib.core.net.callback.OkCallback
            public void onSuccess(int i, String str2) {
                if (i == 0) {
                    try {
                        String decryptString = AesEncryptUtil.decryptString(str2);
                        JtL.m16342e("返回值：" + decryptString);
                        if ("SUCCESS".equals(((JtRes) JtGsonUtil.getInstance().fromJson(decryptString, (Class<Object>) JtRes.class)).getRes())) {
                            dBManager.drapTable(((JtLogModel) list.get(list.size() - 1)).getId());
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
        });
    }

    private void save(final JtLogModel jtLogModel) {
        JtApp.getInstance().executorServiceDb.execute(new Runnable() { // from class: com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog.3
            @Override // java.lang.Runnable
            public void run() {
                JtApp.getInstance().getManager().insertLog(jtLogModel);
            }
        });
    }

    public void saveToDb(String str, String str2, String str3) {
        JtLogModel jtLogModel = new JtLogModel();
        jtLogModel.setPlatform("android");
        jtLogModel.setType(str);
        jtLogModel.setMessage(str2);
        jtLogModel.setOperType(str3);
        int i = SharedPreferencesHelp.getInstance(this.context, "version").getInt("JT_VERSION_CODE", 0);
        jtLogModel.setVersion(i + "");
        jtLogModel.setSdkVersion("1.1.2");
        jtLogModel.setUuid(StringUtil.getOnly(this.context));
        jtLogModel.setTime(System.currentTimeMillis() + "");
        jtLogModel.setAppId(AppInfo.getPackageName(this.context));
        jtLogModel.setAppKey(JtApp.getInstance().getAppKey());
        jtLogModel.setUniqueIdentification(this.uniqueIdentification);
        JtInitBean jtInitBean = this.user;
        if (jtInitBean != null) {
            jtLogModel.setUser(JtMD5Util.getMD5(jtInitBean.getUser()));
            jtLogModel.setProvince(this.user.getProvinceName());
            jtLogModel.setCity(this.user.getCityName());
            jtLogModel.setCityCode(this.user.getCity());
            jtLogModel.setProvinceCode(this.user.getProvince());
            jtLogModel.setLocation_X(this.user.getLocation_X());
            jtLogModel.setLocation_Y(this.user.getLocation_Y());
        } else {
            jtLogModel.setProvince(this.privinceName);
            jtLogModel.setProvinceCode(this.privinceCode);
            jtLogModel.setCity(this.cityName);
            jtLogModel.setCityCode(this.cityCode);
            jtLogModel.setUser(JtMD5Util.getMD5(this.userPhone));
            jtLogModel.setLocation_X(this.Location_X);
            jtLogModel.setLocation_Y(this.Location_Y);
        }
        save(jtLogModel);
    }
}
