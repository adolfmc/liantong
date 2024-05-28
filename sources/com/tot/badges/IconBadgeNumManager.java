package com.tot.badges;

import android.app.Application;
import android.app.Notification;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class IconBadgeNumManager implements IconBadgeNumModel {
    private static final String NOT_SUPPORT_LAUNCHER = "not support your launcher [ default launcher is null ]";
    private static final String NOT_SUPPORT_LAUNCHER_ = "not support ";
    private static final String NOT_SUPPORT_MANUFACTURER_ = "not support ";
    private static final String NOT_SUPPORT_PHONE = "not support your phone [ Build.MANUFACTURER is null ]";
    private LauncherHelper launcherHelper = new LauncherHelper();
    private Map<String, IconBadgeNumModel> iconBadgeNumModelMap = new HashMap();

    @Override // com.tot.badges.IconBadgeNumModel
    public Notification setIconBadgeNum(@NonNull Application application, Notification notification, int i) throws Exception {
        return getSetIconBadgeNumModel(application).setIconBadgeNum(application, notification, i);
    }

    @NonNull
    @Deprecated
    private IconBadgeNumModel getIconBadgeNumModelByManufacturer() throws Exception {
        if (TextUtils.isEmpty(Build.MANUFACTURER)) {
            throw new Exception(NOT_SUPPORT_PHONE);
        }
        String lowerCase = Build.MANUFACTURER.toLowerCase();
        char c = 65535;
        switch (lowerCase.hashCode()) {
            case -1240244679:
                if (lowerCase.equals("google")) {
                    c = 6;
                    break;
                }
                break;
            case -1206476313:
                if (lowerCase.equals("huawei")) {
                    c = 0;
                    break;
                }
                break;
            case -759499589:
                if (lowerCase.equals("xiaomi")) {
                    c = 1;
                    break;
                }
                break;
            case 3418016:
                if (lowerCase.equals("oppo")) {
                    c = 3;
                    break;
                }
                break;
            case 3620012:
                if (lowerCase.equals("vivo")) {
                    c = 2;
                    break;
                }
                break;
            case 103777484:
                if (lowerCase.equals("meizu")) {
                    c = 5;
                    break;
                }
                break;
            case 1864941562:
                if (lowerCase.equals("samsung")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new GoogleModelImpl();
            case 1:
                return new XiaoMiModelImpl();
            case 2:
                return new VIVOModelImpl();
            case 3:
                return new OPPOModelImpl();
            case 4:
                return new SamsungModelImpl();
            case 5:
                return new MeizuModelImpl();
            case 6:
                return new GoogleModelImpl();
            default:
                throw new Exception("not support " + Build.MANUFACTURER);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @NonNull
    private IconBadgeNumModel getIconBadgeNumModelByLauncher(@NonNull String str) throws Exception {
        char c;
        switch (str.hashCode()) {
            case -1240244679:
                if (str.equals("google")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1206476313:
                if (str.equals("huawei")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -759499589:
                if (str.equals("xiaomi")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3418016:
                if (str.equals("oppo")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3620012:
                if (str.equals("vivo")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 103777484:
                if (str.equals("meizu")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1864941562:
                if (str.equals("samsung")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return new HuaWeiModelImpl();
            case 1:
                return new XiaoMiModelImpl();
            case 2:
                return new VIVOModelImpl();
            case 3:
                return new OPPOModelImpl();
            case 4:
                return new SamsungModelImpl();
            case 5:
                return new MeizuModelImpl();
            case 6:
                return new GoogleModelImpl();
            default:
                throw new Exception("not support " + str);
        }
    }

    @NonNull
    private IconBadgeNumModel getSetIconBadgeNumModel(@NonNull Application application) throws Exception {
        String launcherPackageName = this.launcherHelper.getLauncherPackageName(application);
        if (TextUtils.isEmpty(launcherPackageName)) {
            throw new Exception(NOT_SUPPORT_LAUNCHER);
        }
        String launcherTypeByName = this.launcherHelper.getLauncherTypeByName(launcherPackageName);
        if (TextUtils.isEmpty(launcherTypeByName)) {
            throw new Exception("not support " + launcherPackageName);
        } else if (this.iconBadgeNumModelMap.containsKey(launcherTypeByName)) {
            return this.iconBadgeNumModelMap.get(launcherTypeByName);
        } else {
            IconBadgeNumModel iconBadgeNumModelByLauncher = getIconBadgeNumModelByLauncher(launcherTypeByName);
            this.iconBadgeNumModelMap.put(launcherTypeByName, iconBadgeNumModelByLauncher);
            return iconBadgeNumModelByLauncher;
        }
    }
}
