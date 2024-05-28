package com.sinovatech.unicom.separatemodule.baidumap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.blankj.utilcode.util.AppUtils;
import com.sinovatech.unicom.basic.view.CustomNotitleDialogManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate.OpenExternalMapJSPlugin;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduH5BusinessDetails {
    private Activity activityContext;
    private LatLng mBusinessLatLng;
    private String mBusinessName;
    private String phoneNumber;

    public BaiduH5BusinessDetails(Activity activity, String str, LatLng latLng) {
        this.activityContext = activity;
        this.mBusinessName = str;
        this.mBusinessLatLng = latLng;
    }

    public BaiduH5BusinessDetails(Activity activity, String str) {
        this.activityContext = activity;
        this.phoneNumber = str;
    }

    public void setTell() {
        if (!TextUtils.isEmpty(this.phoneNumber)) {
            Activity activity = this.activityContext;
            CustomNotitleDialogManager.show(activity, "", "呼叫" + this.phoneNumber, true, "取消", "呼叫", new CustomNotitleDialogManager.CustomNotitleDialogListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.-$$Lambda$BaiduH5BusinessDetails$m4Cveq4FPm9BlGak2AzkXP-_l4A
                @Override // com.sinovatech.unicom.basic.view.CustomNotitleDialogManager.CustomNotitleDialogListener
                public final void onClickOk() {
                    BaiduH5BusinessDetails baiduH5BusinessDetails = BaiduH5BusinessDetails.this;
                    baiduH5BusinessDetails.activityContext.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + baiduH5BusinessDetails.phoneNumber)));
                }
            });
            return;
        }
        UIUtils.toast("管理员有点懒，未留电话");
    }

    public void setUpBaiduAPPByMine(boolean z, OpenExternalMapJSPlugin.MapCallBack mapCallBack) {
        logMap(z, "百度地图");
        try {
            if (isInstallByread("com.baidu.BaiduMap")) {
                Intent intent = new Intent();
                CoordinateConverter coordinateConverter = new CoordinateConverter();
                coordinateConverter.from(CoordinateConverter.CoordType.COMMON);
                coordinateConverter.coord(this.mBusinessLatLng);
                LatLng convert = coordinateConverter.convert();
                intent.setData(Uri.parse("baidumap://map/direction?origin=我的位置&destination=name:" + this.mBusinessName + "|latlng:" + convert.latitude + "," + convert.longitude + "&mode=driving&src=" + this.activityContext.getPackageName()));
                this.activityContext.startActivity(intent);
                if (mapCallBack != null) {
                    mapCallBack.callBack("0", "打开成功");
                }
            } else if (mapCallBack != null) {
                mapCallBack.callBack("14", "拉起失败，没有安装app");
            } else {
                UIUtils.toast("请先安装百度地图");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (mapCallBack != null) {
                mapCallBack.callBack("10", "程序异常");
                return;
            }
            UIUtils.toast("程序错误【" + e.getMessage() + "】");
        }
    }

    public void setUpGaodeAppByMine(boolean z, OpenExternalMapJSPlugin.MapCallBack mapCallBack) {
        logMap(z, "高德地图");
        try {
            if (isInstallByread("com.autonavi.minimap")) {
                this.activityContext.startActivity(Intent.getIntent("androidamap://route?sourceApplication=" + this.activityContext.getPackageName() + "&sname=我的位置&dlat=" + this.mBusinessLatLng.latitude + "&dlon=" + this.mBusinessLatLng.longitude + "&dname=" + this.mBusinessName + "&dev=0&m=0&t=2"));
                if (mapCallBack != null) {
                    mapCallBack.callBack("0", "打开成功");
                }
            } else if (mapCallBack != null) {
                mapCallBack.callBack("14", "拉起失败，没有安装app");
            } else {
                UIUtils.toast("请先安装高德地图");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (mapCallBack != null) {
                mapCallBack.callBack("10", "程序异常");
                return;
            }
            UIUtils.toast("程序错误【" + e.getMessage() + "】");
        }
    }

    public static boolean isInstallByread(String str) {
        return AppUtils.isAppInstalled(str);
    }

    private void logMap(boolean z, String str) {
        PvCurrencyLogUtils.pvLogUserDJ("5070201", z ? "H5-地图渠道" : "我的页面-营业厅地图渠道", str, "", "", "");
    }
}
