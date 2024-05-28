package github.nisrulz.easydeviceinfo.base;

import android.os.Build;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyCpuMod {
    public final String[] getSupportedABIS() {
        String[] strArr = {EasyDeviceInfo.notFoundVal};
        if (Build.VERSION.SDK_INT >= 21) {
            strArr = Build.SUPPORTED_ABIS;
        }
        return CheckValidityUtil.checkValidData(strArr);
    }

    public final String getStringSupportedABIS() {
        String str;
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = Build.SUPPORTED_ABIS;
            StringBuilder sb = new StringBuilder();
            if (strArr.length > 0) {
                for (String str2 : strArr) {
                    sb.append(str2);
                    sb.append("_");
                }
                sb.deleteCharAt(sb.lastIndexOf("_"));
            } else {
                sb.append("");
            }
            str = sb.toString();
        } else {
            str = null;
        }
        return CheckValidityUtil.checkValidData(CheckValidityUtil.handleIllegalCharacterInResult(str));
    }

    public final String getStringSupported32bitABIS() {
        String str;
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = Build.SUPPORTED_32_BIT_ABIS;
            StringBuilder sb = new StringBuilder();
            if (strArr.length > 0) {
                for (String str2 : strArr) {
                    sb.append(str2);
                    sb.append("_");
                }
                sb.deleteCharAt(sb.lastIndexOf("_"));
            } else {
                sb.append("");
            }
            str = sb.toString();
        } else {
            str = null;
        }
        return CheckValidityUtil.checkValidData(CheckValidityUtil.handleIllegalCharacterInResult(str));
    }

    public final String getStringSupported64bitABIS() {
        String str;
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = Build.SUPPORTED_64_BIT_ABIS;
            StringBuilder sb = new StringBuilder();
            if (strArr.length > 0) {
                for (String str2 : strArr) {
                    sb.append(str2);
                    sb.append("_");
                }
                sb.deleteCharAt(sb.lastIndexOf("_"));
            } else {
                sb.append("");
            }
            str = sb.toString();
        } else {
            str = null;
        }
        return CheckValidityUtil.checkValidData(CheckValidityUtil.handleIllegalCharacterInResult(str));
    }

    public final String[] getSupported32bitABIS() {
        String[] strArr = {EasyDeviceInfo.notFoundVal};
        if (Build.VERSION.SDK_INT >= 21) {
            strArr = Build.SUPPORTED_32_BIT_ABIS;
        }
        return CheckValidityUtil.checkValidData(strArr);
    }

    public final String[] getSupported64bitABIS() {
        String[] strArr = {EasyDeviceInfo.notFoundVal};
        if (Build.VERSION.SDK_INT >= 21) {
            strArr = Build.SUPPORTED_64_BIT_ABIS;
        }
        return CheckValidityUtil.checkValidData(strArr);
    }
}
