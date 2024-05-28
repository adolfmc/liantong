package com.sinovatech.unicom.common;

import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.DongtaiMiyaoUtils;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class EncodeHelper {
    public static String encoderByMd5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("utf8"));
            StringBuilder sb = new StringBuilder(digest.length << 1);
            for (int i = 0; i < digest.length; i++) {
                sb.append(Character.forDigit((digest[i] >> 4) & 15, 16));
                sb.append(Character.forDigit(digest[i] & 15, 16));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encoderByMd5_32(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("utf8"));
            StringBuilder sb = new StringBuilder(digest.length << 1);
            for (int i = 0; i < digest.length; i++) {
                sb.append(Character.forDigit((digest[i] >> 4) & 15, 32));
                sb.append(Character.forDigit(digest[i] & 15, 32));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encodeByAES(String str) {
        return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(), EncodeUtils.hexDecode("f6b0d3f905bf02939b4f6d29f257c2ab"), EncodeUtils.hexDecode("1a42eb4565be8628a807403d67dce78d")));
    }

    public static String decodeByAES(String str) {
        return Cryptos.aesDecrypt(EncodeUtils.hexDecode(str), EncodeUtils.hexDecode("4e3fbe5288532126e6b188cdee918bf3"), EncodeUtils.hexDecode("1a42eb4565be8628a807403d67dce78d"));
    }

    public static String encodeByAESLog(String str) {
        return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(), EncodeUtils.hexDecode("4e3fbe5288532126e6b188cdee918bf3"), EncodeUtils.hexDecode("1a42eb4565be8628a807403d67dce78d")));
    }

    public static String encodeByAESSDCJ_TEST(String str) {
        return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(), EncodeUtils.hexDecode("dfe55a24889610ade7e0268964b22a92"), EncodeUtils.hexDecode("2125887327c37da9076c29eb7ab0f4d8")));
    }

    public static String encodeByAESSDCJ_PRO(String str) {
        if (URLEnvironmentConfig.isForTYCJTest()) {
            return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(), EncodeUtils.hexDecode("dfe55a24889610ade7e0268964b22a92"), EncodeUtils.hexDecode("2125887327c37da9076c29eb7ab0f4d8")));
        }
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(), EncodeUtils.hexDecode("d7e864389a65cef116b22822ef9c704c"), EncodeUtils.hexDecode("d2f53e4c7fe967e5c2bbadf820ca74c7")));
        }
        return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(), EncodeUtils.hexDecode("dfe55a24889610ade7e0268964b22a92"), EncodeUtils.hexDecode("2125887327c37da9076c29eb7ab0f4d8")));
    }

    public static String encodeByAESAnquanzhongxin(String str) {
        return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(), EncodeUtils.hexDecode("4a7fb08355c1054cf43ab0de1b451bc7"), EncodeUtils.hexDecode("ed71732154dd952cb4a114e12874d648")));
    }

    public static String decodeByAESAnquanzhongxin(String str) {
        return Cryptos.aesDecrypt(EncodeUtils.hexDecode(str), EncodeUtils.hexDecode("4a7fb08355c1054cf43ab0de1b451bc7"), EncodeUtils.hexDecode("ed71732154dd952cb4a114e12874d648"));
    }

    public static String encodeByAESDongtaimiyao(String str, String str2) {
        String xid = UserManager.getInstance().getXid(str);
        if (!DongtaiMiyaoUtils.isHasDongtaimaiyao(str)) {
            xid = "5e7c7dbe7724402f97a572915f8727e7";
        }
        return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str2.getBytes(), EncodeUtils.hexDecode(xid), EncodeUtils.hexDecode("d26220f98a92429b80f9ca45d994b8c3")));
    }

    public static String decodeByAESDongtaimiayao(String str) {
        return Cryptos.aesDecrypt(EncodeUtils.hexDecode(str), EncodeUtils.hexDecode("5e7c7dbe7724402f97a572915f8727e7"), EncodeUtils.hexDecode("d26220f98a92429b80f9ca45d994b8c3"));
    }

    public static String encodeByAES_touchaun(String str) {
        return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(), EncodeUtils.hexDecode("cb154543e3b396ca923b0a9f79e01c3d"), EncodeUtils.hexDecode("cb154543e3b396ca923b0a9f79e01c3d")));
    }

    public static String decodeByAESTycjWhite(String str) {
        return Cryptos.aesDecrypt(EncodeUtils.hexDecode(str), EncodeUtils.hexDecode("b245282e069ca9de70e6f6dc423d453b"), EncodeUtils.hexDecode("cb23764dd3c5f7bf6d905f67635d876c"));
    }

    public static String decodeByAESUpdate(String str) {
        try {
            return Cryptos.aesDecrypt(EncodeUtils.hexDecode(str), EncodeUtils.hexDecode("26fce1fb701e42f2921d8f33c692bca5"), EncodeUtils.hexDecode("6c5bd6e5371848868880ad0b04d22e6c"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encodeByAESOMO(String str) {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(), EncodeUtils.hexDecode("32db334b9c324e5c8692ab93e4a7e870"), EncodeUtils.hexDecode("ad8f8bb0216e4eb388f5b6b6220da271")));
        }
        return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(), EncodeUtils.hexDecode("26fce1fb701e42f2831a8f33b692bca5"), EncodeUtils.hexDecode("6c5bd6e5371847769980ad0b04d22e7c")));
    }
}
