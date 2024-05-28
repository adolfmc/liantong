package cn.anicert.verification.lib_identify.identification;

import android.util.Base64;
import cn.anicert.verification.lib_identify.third.Result;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IctidAuthService {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class ApplyData {
        public final String appId;
        public final String organizeId;
        public final int type;

        public ApplyData(int i, String str, String str2) {
            this.type = i;
            this.organizeId = str;
            this.appId = str2;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class ApplyIDCardData {
        public final String appId;
        public final String organizeId;
        public final String randomNumber;

        public ApplyIDCardData(String str, String str2, String str3) {
            this.randomNumber = str;
            this.organizeId = str2;
            this.appId = str3;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class IdCardData {
        public final String appId;
        public final byte[] ctid;
        public final String organizeId;
        public final int type;

        public IdCardData(String str, String str2, String str3) {
            this.ctid = Base64.decode(str, 0);
            this.organizeId = str2;
            this.appId = str3;
            this.type = 3;
        }

        public IdCardData(String str, String str2, String str3, int i) {
            this.ctid = Base64.decode(str, 0);
            this.organizeId = str2;
            this.appId = str3;
            this.type = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnCallBack {
        void callBack(Result<String> result);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class QRCodeData {
        public final String appId;
        public final String organizeId;
        public final String qrCode;

        public QRCodeData(String str, String str2, String str3) {
            this.qrCode = str;
            this.organizeId = str2;
            this.appId = str3;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class ReqCodeData {
        public final String appId;
        public final byte[] ctid;
        public final String organizeId;

        public ReqCodeData(String str, String str2, String str3) {
            this.ctid = Base64.decode(str, 0);
            this.organizeId = str2;
            this.appId = str3;
        }
    }
}
