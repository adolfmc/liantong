package com.sinovatech.unicom.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.ConfigManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SignUtils {
    public static boolean isEquals(Context context) {
        try {
            if ("1".equals(new ConfigManager(context).getQiangzhiTip())) {
                return true;
            }
            byte[] digest = MessageDigest.getInstance("SHA1").digest(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String upperCase = Integer.toHexString(b & 255).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    sb.append("0");
                }
                sb.append(upperCase);
                sb.append(":");
            }
            String sb2 = sb.toString();
            if (!TextUtils.isEmpty(sb2) && !"A4:45:54:9E:3F:55:09:87:D5:3B:66:0D:8B:8F:B7:01:C9:4A:E9:BD:".equals(sb2)) {
                if (!"A4:45:54:9E:3F:55:09:87:D5:3B:66:0D:8B:8F:B7:01:C9:4A:E9:BD:".contains(sb2)) {
                    return false;
                }
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return true;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return true;
        } catch (Exception e3) {
            e3.printStackTrace();
            return true;
        }
    }
}
