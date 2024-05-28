package com.sinovatech.unicom.separatemodule.esim;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RSAUtil {
    public static final String KEY_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static final int MAX_DECRYPT_BLOCK = 256;
    private static final int MAX_ENCRYPT_BLOCK = 245;
    private static final String pubVal = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiLX5bzboY7jrFGGiWsaU6NdEza24l20wSi6qVprtiwrq0VBcPSWp8j+PvzR9MrQvb5OKhlfhOcgcY15QYHP/ihAj16Ly7Pxcsh/CvCYbPlQKd0yDMi1JDsiokT27/4mpyLZ8XlAIikKcmgaYWnb5cWrWYVq1UK9BzmHg8CXdlORP5ikvVDidVBRGjJVP2fwQcnGerzWh+jOrqP/VwJEsSYmZHc+JKVYO8jI3InM0LzQvwyV/tlRlKIalVlbb9tCdDjda7awHFpIYNXZbRAQV50WVsH5xhLrqqzCCynq65m2RwOPhJRByEglh02wikmuwAy9LayQOHfMf1qwKFPPctwIDAQAB";
    public static final String pvtVal = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDHx7ctd8ecmkJL0KXizkYuwryrqC/LSCzc4YOAnsHWOhL1tOmMTWv4rU89sfF5QjUC1Fbt/qPLB87CUexVFyx/J8j7Sb97ajhvIZ5tXzTqb4yQ0R1dAoDAtVxYGqqb1FOJZsAIG7quBMI1ucVHdpGgdZYdFA21MAwgakBxAxIslEDWOmKzeorN4uDvMfZam1gZaItXF6B5qerEfFYtTp6cLfmQm/9325JbpCvy2YOveEzdRlh5bUOqEdQU6tdH/Vrc6I5SJKRG2xIl7OFbnR+EQiqGUnsrtZi8hqai07UJh9wf+GxvJYPCoJY3Q3fTJTxJTbBXEvTgutx0gVjnNE01AgMBAAECggEAOVSXvYWNE+S0t7sFj8OwIkUiDYSzSnvI3A4fRa7JY2fA/x/OkcO6A2AAyGyjJjAYtFopJB0Z9e6kp+EImBaRX2AUbgBo4crGrSWRqENVFR2z0e8gpQtN1X92I5ZrdVKGMnz5CJqkhMG61d4cvfhJrClyTZ/umxtRr4+uNIA0G/RhcnRnxAaiRkobE/26lclfESYkEN5iJJ/6r/E9BdjmjWFZcm9kHDTJ91G4y0+XP+YuQeBedzMNFoSNgrxpm2dPwTPt/7XbOTqYg3yQsgFBSyuP2LdLBEAd1h0c+SDvIESbZ8JuLkx1fdw82Ad6NW5HHU2RMSpOv01XJRLNqtLHdQKBgQDvMkRcp1GnE4NYti6jS9gtw45b6Av1kCAgvQIjknC6OwyNjE/Oq34mPzNGKqYCpHAPLU3i1G7B+usV7/kv3YdxB51fUikmpusMxDnZ+Bevvy3ejMhZwj91qqmlfcog0JzTBbdFMdnEhu67kaOoivKrsJS3W/gATLP4Fex6/2pP2wKBgQDV0JVA1OdDIp83qnUnQtCoJMBK3JohUpD/z6RhsAHV+WGvsscgWNB+03i5EPqjqky/b4zdsz0lFoMEjhVvZD21HuXXAOjzv4UP0iWHtuii7m8yec2YUY6cL5C1UqODWrhgVim23OMn1jGwHp3VLWAW4lqbl8kil4mg+JZ9e8IsLwKBgQDNls3tOR7UVNA7CnNxhXnsc73DPuIwkU0b9niFLJPZbXFvpB7gSsQliYK/l16SvNVYJh9gjCgacFloxJbQTj7gpqPbzxlxkFxM38e9kiCBvPdm5mg5xdyXfTVM+Z4msEdqlOod/ifnY/aDnz0kWzES4rYgF1tEnqi83dMhMLEmqwKBgQCNBmiLPGHUnA9cDef7n0w0uxjBx8M5W1YcA/6uGAL9+OB/nHFOWoYaQK0Nk69dJ8Q7N/43dDNONQzxgi2NLRauUdTS/NypeJbxGnB6lR/X52AccM40OwbO6WiO0BTdXIbjs7EL1aFV8ycOvQLpSmZocZ/FpFWbpUu3in7oASrPLQKBgQDXhVvIMHXQ+ltNU/XHYdfhb3Zyc8vdPvCzN+FkaVxK7mAhN/XylRvXs7+yp9XwoN9Y4gzqm/KiIVoKQYTrzp3IX4NsgKrD+WCf4OJyF6H6cGJcQgequmypEmbxQBIaZmd0Cys2wsB6yBl1HOBdUwVvVBS/CrsVFZI92hswYgZ+Ww==";
    private static final String signPvtVal = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDi7ByaZLXeDpJdUWothhApPQTAbvb90YQcHMPQR3mKT76PtSGWIW4XpDSDQn9eXQwEfFDLmJr3yfEW2I3EtAPvYXyOi6BCs1QQVj+giJa7S/XoQtFjMSR9dsFwNf5GmqPgafcmJUE8sWtrUfcsiWJBNh1NDkkqW2Sr9UX/Z+nd0+oV5UeaSe40F5AUFJQ4B8tVGeXd2u7A8Bt55/UIifCvGezpH1WqngXbb7Vb3G6KbG8mUWvELWoCoSPe3+Zrq045beVEbEBwvAq+CmRUhzc1PbOemeQUerFmpqmaX9IqZwlZxeTBsEjtF+XmkdYIxoZqpmqcK1xvE7GBpjXTKOM3AgMBAAECggEAC6Wtn8MUwdQJPfiKzmr3wC+2/Ov1I3jLvIGn3BqyQ1LttGr9au5GVbi1tUyf3i3acXR/O4pjeEQJataTC3Cau+bkdMPEswe8Hdm/cRok2nIyQ5Y1lxv6BBj1u5bmrHFz53XcG9bq4hZp+nUxKAkMXc2zc7WmNCIpKNmLATcwZGFLLUduhiFa/LtCjuVQwgJLmXU+HCzf/96OFOgSS+j/GSUgandsIDqJDefRAugZSNec3jEKEcAEftAOh+YNl2Cg7TLs3jYbNcEtMunMA0GKTYsVEvPLmIU9WvSaaz39e3DUvJgd9HfyIRiXgwaWSNxhPm2J9wyhQ4UGtU8zlgZWOQKBgQD7nrQbQN0LapMxuLrS1uwWfJNFQcKpQOLXFYRS2FZLDxtl2ra6L7JrsDeQQT8g7/F+KGMqFaPrAzonONCHKnbbYFP9dcUiT/d3PF9qqdv/u/nlW8ks/gnVsSEFVHwYwimkgs28LjEeoxcFl19/40jYQfbe4g+Ek2wtELE5wB8h2wKBgQDm31kRIyRCFBiFlZxMCDbYOQICoX/WFmwgnk7nCKK2jhe0tEhqb0Gnnvf+yJx3dyFES6Ptqc/fvUGIWWyT8nNVWQ8nirlV4KDLomC7wmLzecSt+ZEv6txbiM5mQnq4nQRonNJO25cjZA1nA0hWZsyAVAk+NTsZKNFAOeUKiD6o1QKBgQDKeeYSvDpj0moKK83ileI7IhncNFOxoS4ox/7IL6AZHFn+t2YtIu6fa5j6sxZaqdqZm2RY3DsghWNJZpWe/xPP6o8LubZdqx3GzEU37uHCw4APCbI0S2ScmcGpqVeHEM8zu7JUiJMsRS7kZ/AIWFwo8cP3jM/R7lOxW+pRP4GN2wKBgAXR03jvHdbcktGrN8li258PsNFIKOTIo+kelHaWbSOU6VwQv1KPg2QqSa4OeIM6aYhDL9qp0Seeqaw6NNZ14hpgqFUi1hEoHGWFrlKNT9k7VI3eIJ7ImM1l4W4KdvV4X8gTS+nY9zJZL01Nc+FZrgX+GE2NIfQ8ZynrS668SHgRAoGBAPS02+EXwHoNsp0M3CZs9rMVf1/Hgh94IyKP7JeF4RZRxWRhMD4wVVnR25uPhmDRDD5uyfBBwQ2iSxDEoCoNez8+NvaeGYqr0mjC6Y/T8gKwl/bj1mGzui8YmQLIzmQPHxd2NyH7hxjR7p7uJfZZB7n+v5UiKthYcjxuhtRGvOlS";

    public static String ecbEncrypt(String str) throws Exception {
        byte[] doFinal;
        byte[] bytes = str.getBytes("UTF-8");
        PublicKey publicKey = toPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiLX5bzboY7jrFGGiWsaU6NdEza24l20wSi6qVprtiwrq0VBcPSWp8j+PvzR9MrQvb5OKhlfhOcgcY15QYHP/ihAj16Ly7Pxcsh/CvCYbPlQKd0yDMi1JDsiokT27/4mpyLZ8XlAIikKcmgaYWnb5cWrWYVq1UK9BzmHg8CXdlORP5ikvVDidVBRGjJVP2fwQcnGerzWh+jOrqP/VwJEsSYmZHc+JKVYO8jI3InM0LzQvwyV/tlRlKIalVlbb9tCdDjda7awHFpIYNXZbRAQV50WVsH5xhLrqqzCCynq65m2RwOPhJRByEglh02wikmuwAy9LayQOHfMf1qwKFPPctwIDAQAB");
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, publicKey);
        int length = bytes.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = length - i;
            if (i3 > 0) {
                if (i3 > 245) {
                    doFinal = cipher.doFinal(bytes, i, 245);
                } else {
                    doFinal = cipher.doFinal(bytes, i, i3);
                }
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i2++;
                i = i2 * 245;
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return EsimBase64.getEncoder().encodeToString(byteArray);
            }
        }
    }

    public static String ecbDecrypt(String str) throws Exception {
        byte[] doFinal;
        byte[] decode = EsimBase64.getDecoder().decode(str);
        PrivateKey privateKey = toPrivateKey("MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDHx7ctd8ecmkJL0KXizkYuwryrqC/LSCzc4YOAnsHWOhL1tOmMTWv4rU89sfF5QjUC1Fbt/qPLB87CUexVFyx/J8j7Sb97ajhvIZ5tXzTqb4yQ0R1dAoDAtVxYGqqb1FOJZsAIG7quBMI1ucVHdpGgdZYdFA21MAwgakBxAxIslEDWOmKzeorN4uDvMfZam1gZaItXF6B5qerEfFYtTp6cLfmQm/9325JbpCvy2YOveEzdRlh5bUOqEdQU6tdH/Vrc6I5SJKRG2xIl7OFbnR+EQiqGUnsrtZi8hqai07UJh9wf+GxvJYPCoJY3Q3fTJTxJTbBXEvTgutx0gVjnNE01AgMBAAECggEAOVSXvYWNE+S0t7sFj8OwIkUiDYSzSnvI3A4fRa7JY2fA/x/OkcO6A2AAyGyjJjAYtFopJB0Z9e6kp+EImBaRX2AUbgBo4crGrSWRqENVFR2z0e8gpQtN1X92I5ZrdVKGMnz5CJqkhMG61d4cvfhJrClyTZ/umxtRr4+uNIA0G/RhcnRnxAaiRkobE/26lclfESYkEN5iJJ/6r/E9BdjmjWFZcm9kHDTJ91G4y0+XP+YuQeBedzMNFoSNgrxpm2dPwTPt/7XbOTqYg3yQsgFBSyuP2LdLBEAd1h0c+SDvIESbZ8JuLkx1fdw82Ad6NW5HHU2RMSpOv01XJRLNqtLHdQKBgQDvMkRcp1GnE4NYti6jS9gtw45b6Av1kCAgvQIjknC6OwyNjE/Oq34mPzNGKqYCpHAPLU3i1G7B+usV7/kv3YdxB51fUikmpusMxDnZ+Bevvy3ejMhZwj91qqmlfcog0JzTBbdFMdnEhu67kaOoivKrsJS3W/gATLP4Fex6/2pP2wKBgQDV0JVA1OdDIp83qnUnQtCoJMBK3JohUpD/z6RhsAHV+WGvsscgWNB+03i5EPqjqky/b4zdsz0lFoMEjhVvZD21HuXXAOjzv4UP0iWHtuii7m8yec2YUY6cL5C1UqODWrhgVim23OMn1jGwHp3VLWAW4lqbl8kil4mg+JZ9e8IsLwKBgQDNls3tOR7UVNA7CnNxhXnsc73DPuIwkU0b9niFLJPZbXFvpB7gSsQliYK/l16SvNVYJh9gjCgacFloxJbQTj7gpqPbzxlxkFxM38e9kiCBvPdm5mg5xdyXfTVM+Z4msEdqlOod/ifnY/aDnz0kWzES4rYgF1tEnqi83dMhMLEmqwKBgQCNBmiLPGHUnA9cDef7n0w0uxjBx8M5W1YcA/6uGAL9+OB/nHFOWoYaQK0Nk69dJ8Q7N/43dDNONQzxgi2NLRauUdTS/NypeJbxGnB6lR/X52AccM40OwbO6WiO0BTdXIbjs7EL1aFV8ycOvQLpSmZocZ/FpFWbpUu3in7oASrPLQKBgQDXhVvIMHXQ+ltNU/XHYdfhb3Zyc8vdPvCzN+FkaVxK7mAhN/XylRvXs7+yp9XwoN9Y4gzqm/KiIVoKQYTrzp3IX4NsgKrD+WCf4OJyF6H6cGJcQgequmypEmbxQBIaZmd0Cys2wsB6yBl1HOBdUwVvVBS/CrsVFZI92hswYgZ+Ww==");
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, privateKey);
        int length = decode.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = length - i;
            if (i3 > 0) {
                if (i3 > 256) {
                    doFinal = cipher.doFinal(decode, i, 256);
                } else {
                    doFinal = cipher.doFinal(decode, i, i3);
                }
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i2++;
                i = i2 * 256;
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return new String(byteArray);
            }
        }
    }

    public static String sign(String str) throws InvalidKeySpecException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        String encoderByMd5_32 = encoderByMd5_32(str);
        PrivateKey privateKey = toPrivateKey("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDi7ByaZLXeDpJdUWothhApPQTAbvb90YQcHMPQR3mKT76PtSGWIW4XpDSDQn9eXQwEfFDLmJr3yfEW2I3EtAPvYXyOi6BCs1QQVj+giJa7S/XoQtFjMSR9dsFwNf5GmqPgafcmJUE8sWtrUfcsiWJBNh1NDkkqW2Sr9UX/Z+nd0+oV5UeaSe40F5AUFJQ4B8tVGeXd2u7A8Bt55/UIifCvGezpH1WqngXbb7Vb3G6KbG8mUWvELWoCoSPe3+Zrq045beVEbEBwvAq+CmRUhzc1PbOemeQUerFmpqmaX9IqZwlZxeTBsEjtF+XmkdYIxoZqpmqcK1xvE7GBpjXTKOM3AgMBAAECggEAC6Wtn8MUwdQJPfiKzmr3wC+2/Ov1I3jLvIGn3BqyQ1LttGr9au5GVbi1tUyf3i3acXR/O4pjeEQJataTC3Cau+bkdMPEswe8Hdm/cRok2nIyQ5Y1lxv6BBj1u5bmrHFz53XcG9bq4hZp+nUxKAkMXc2zc7WmNCIpKNmLATcwZGFLLUduhiFa/LtCjuVQwgJLmXU+HCzf/96OFOgSS+j/GSUgandsIDqJDefRAugZSNec3jEKEcAEftAOh+YNl2Cg7TLs3jYbNcEtMunMA0GKTYsVEvPLmIU9WvSaaz39e3DUvJgd9HfyIRiXgwaWSNxhPm2J9wyhQ4UGtU8zlgZWOQKBgQD7nrQbQN0LapMxuLrS1uwWfJNFQcKpQOLXFYRS2FZLDxtl2ra6L7JrsDeQQT8g7/F+KGMqFaPrAzonONCHKnbbYFP9dcUiT/d3PF9qqdv/u/nlW8ks/gnVsSEFVHwYwimkgs28LjEeoxcFl19/40jYQfbe4g+Ek2wtELE5wB8h2wKBgQDm31kRIyRCFBiFlZxMCDbYOQICoX/WFmwgnk7nCKK2jhe0tEhqb0Gnnvf+yJx3dyFES6Ptqc/fvUGIWWyT8nNVWQ8nirlV4KDLomC7wmLzecSt+ZEv6txbiM5mQnq4nQRonNJO25cjZA1nA0hWZsyAVAk+NTsZKNFAOeUKiD6o1QKBgQDKeeYSvDpj0moKK83ileI7IhncNFOxoS4ox/7IL6AZHFn+t2YtIu6fa5j6sxZaqdqZm2RY3DsghWNJZpWe/xPP6o8LubZdqx3GzEU37uHCw4APCbI0S2ScmcGpqVeHEM8zu7JUiJMsRS7kZ/AIWFwo8cP3jM/R7lOxW+pRP4GN2wKBgAXR03jvHdbcktGrN8li258PsNFIKOTIo+kelHaWbSOU6VwQv1KPg2QqSa4OeIM6aYhDL9qp0Seeqaw6NNZ14hpgqFUi1hEoHGWFrlKNT9k7VI3eIJ7ImM1l4W4KdvV4X8gTS+nY9zJZL01Nc+FZrgX+GE2NIfQ8ZynrS668SHgRAoGBAPS02+EXwHoNsp0M3CZs9rMVf1/Hgh94IyKP7JeF4RZRxWRhMD4wVVnR25uPhmDRDD5uyfBBwQ2iSxDEoCoNez8+NvaeGYqr0mjC6Y/T8gKwl/bj1mGzui8YmQLIzmQPHxd2NyH7hxjR7p7uJfZZB7n+v5UiKthYcjxuhtRGvOlS");
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(encoderByMd5_32.getBytes());
        return new String(EsimBase64.getEncoder().encode(signature.sign()));
    }

    private static PrivateKey toPrivateKey(String str) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(EsimBase64.getDecoder().decode(str.getBytes())));
    }

    private static PublicKey toPublicKey(String str) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(EsimBase64.getDecoder().decode(str.getBytes())));
    }

    private static String encoderByMd5_32(String str) {
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
}
