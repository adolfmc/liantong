package github.nisrulz.easydeviceinfo.base;

import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class CheckValidityUtil {
    private CheckValidityUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String handleIllegalCharacterInResult(String str) {
        return (str == null || !str.contains(" ")) ? str : str.replaceAll(" ", "_");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String checkValidData(String str) {
        return (str == null || str.length() == 0) ? EasyDeviceInfo.notFoundVal : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] checkValidData(String[] strArr) {
        return (strArr == null || strArr.length == 0) ? new String[]{EasyDeviceInfo.notFoundVal} : strArr;
    }
}
