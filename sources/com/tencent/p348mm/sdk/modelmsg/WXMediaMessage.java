package com.tencent.p348mm.sdk.modelmsg;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.tencent.p348mm.sdk.p354b.C10393b;
import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelmsg.WXMediaMessage */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class WXMediaMessage {
    public static final String ACTION_WXAPPMESSAGE = "com.tencent.mm.sdk.openapi.Intent.ACTION_WXAPPMESSAGE";
    private static final int DESCRIPTION_LENGTH_LIMIT = 1024;
    private static final int MEDIA_TAG_NAME_LENGTH_LIMIT = 64;
    private static final int MESSAGE_ACTION_LENGTH_LIMIT = 2048;
    private static final int MESSAGE_EXT_LENGTH_LIMIT = 2048;
    private static final String TAG = "MicroMsg.SDK.WXMediaMessage";
    public static final int THUMB_LENGTH_LIMIT = 32768;
    private static final int TITLE_LENGTH_LIMIT = 512;
    public String description;
    public IMediaObject mediaObject;
    public String mediaTagName;
    public String messageAction;
    public String messageExt;
    public int sdkVer;
    public byte[] thumbData;
    public String title;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelmsg.WXMediaMessage$Builder */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Builder {
        public static final String KEY_IDENTIFIER = "_wxobject_identifier_";

        public static WXMediaMessage fromBundle(Bundle bundle) {
            WXMediaMessage wXMediaMessage = new WXMediaMessage();
            wXMediaMessage.sdkVer = bundle.getInt("_wxobject_sdkVer");
            wXMediaMessage.title = bundle.getString("_wxobject_title");
            wXMediaMessage.description = bundle.getString("_wxobject_description");
            wXMediaMessage.thumbData = bundle.getByteArray("_wxobject_thumbdata");
            wXMediaMessage.mediaTagName = bundle.getString("_wxobject_mediatagname");
            wXMediaMessage.messageAction = bundle.getString("_wxobject_message_action");
            wXMediaMessage.messageExt = bundle.getString("_wxobject_message_ext");
            String pathOldToNew = pathOldToNew(bundle.getString("_wxobject_identifier_"));
            if (pathOldToNew != null && pathOldToNew.length() > 0) {
                try {
                    wXMediaMessage.mediaObject = (IMediaObject) Class.forName(pathOldToNew).newInstance();
                    wXMediaMessage.mediaObject.unserialize(bundle);
                    return wXMediaMessage;
                } catch (Exception e) {
                    e.printStackTrace();
                    C10393b.m6195a("MicroMsg.SDK.WXMediaMessage", "get media object from bundle failed: unknown ident " + pathOldToNew + ", ex = " + e.getMessage());
                }
            }
            return wXMediaMessage;
        }

        private static String pathNewToOld(String str) {
            if (str == null || str.length() == 0) {
                C10393b.m6195a("MicroMsg.SDK.WXMediaMessage", "pathNewToOld fail, newPath is null");
                return str;
            }
            return str.replace("com.tencent.mm.sdk.modelmsg", "com.tencent.mm.sdk.openapi");
        }

        private static String pathOldToNew(String str) {
            if (str == null || str.length() == 0) {
                C10393b.m6195a("MicroMsg.SDK.WXMediaMessage", "pathOldToNew fail, oldPath is null");
                return str;
            }
            return str.replace("com.tencent.mm.sdk.openapi", "com.tencent.mm.sdk.modelmsg");
        }

        public static Bundle toBundle(WXMediaMessage wXMediaMessage) {
            Bundle bundle = new Bundle();
            bundle.putInt("_wxobject_sdkVer", wXMediaMessage.sdkVer);
            bundle.putString("_wxobject_title", wXMediaMessage.title);
            bundle.putString("_wxobject_description", wXMediaMessage.description);
            bundle.putByteArray("_wxobject_thumbdata", wXMediaMessage.thumbData);
            if (wXMediaMessage.mediaObject != null) {
                bundle.putString("_wxobject_identifier_", pathNewToOld(wXMediaMessage.mediaObject.getClass().getName()));
                wXMediaMessage.mediaObject.serialize(bundle);
            }
            bundle.putString("_wxobject_mediatagname", wXMediaMessage.mediaTagName);
            bundle.putString("_wxobject_message_action", wXMediaMessage.messageAction);
            bundle.putString("_wxobject_message_ext", wXMediaMessage.messageExt);
            return bundle;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface IMediaObject {
        public static final int TYPE_APPDATA = 7;
        public static final int TYPE_CARD_SHARE = 16;
        public static final int TYPE_DEVICE_ACCESS = 12;
        public static final int TYPE_EMOJI = 8;
        public static final int TYPE_EMOTICON_GIFT = 11;
        public static final int TYPE_EMOTICON_SHARED = 15;
        public static final int TYPE_FILE = 6;
        public static final int TYPE_IMAGE = 2;
        public static final int TYPE_LOCATION_SHARE = 17;
        public static final int TYPE_MALL_PRODUCT = 13;
        public static final int TYPE_MUSIC = 3;
        public static final int TYPE_OLD_TV = 14;
        public static final int TYPE_PRODUCT = 10;
        public static final int TYPE_RECORD = 19;
        public static final int TYPE_TEXT = 1;
        public static final int TYPE_TV = 20;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_URL = 5;
        public static final int TYPE_VIDEO = 4;

        boolean checkArgs();

        void serialize(Bundle bundle);

        int type();

        void unserialize(Bundle bundle);
    }

    public WXMediaMessage() {
        this(null);
    }

    public WXMediaMessage(IMediaObject iMediaObject) {
        this.mediaObject = iMediaObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean checkArgs() {
        String str;
        String str2;
        byte[] bArr;
        if (getType() == 8 && ((bArr = this.thumbData) == null || bArr.length == 0)) {
            str = "MicroMsg.SDK.WXMediaMessage";
            str2 = "checkArgs fail, thumbData should not be null when send emoji";
        } else {
            byte[] bArr2 = this.thumbData;
            if (bArr2 == null || bArr2.length <= 32768) {
                String str3 = this.title;
                if (str3 == null || str3.length() <= 512) {
                    String str4 = this.description;
                    if (str4 != null && str4.length() > 1024) {
                        str = "MicroMsg.SDK.WXMediaMessage";
                        str2 = "checkArgs fail, description is invalid";
                    } else if (this.mediaObject == null) {
                        str = "MicroMsg.SDK.WXMediaMessage";
                        str2 = "checkArgs fail, mediaObject is null";
                    } else {
                        String str5 = this.mediaTagName;
                        if (str5 == null || str5.length() <= 64) {
                            String str6 = this.messageAction;
                            if (str6 == null || str6.length() <= 2048) {
                                String str7 = this.messageExt;
                                if (str7 == null || str7.length() <= 2048) {
                                    return this.mediaObject.checkArgs();
                                }
                                str = "MicroMsg.SDK.WXMediaMessage";
                                str2 = "checkArgs fail, messageExt is too long";
                            } else {
                                str = "MicroMsg.SDK.WXMediaMessage";
                                str2 = "checkArgs fail, messageAction is too long";
                            }
                        } else {
                            str = "MicroMsg.SDK.WXMediaMessage";
                            str2 = "checkArgs fail, mediaTagName is too long";
                        }
                    }
                } else {
                    str = "MicroMsg.SDK.WXMediaMessage";
                    str2 = "checkArgs fail, title is invalid";
                }
            } else {
                str = "MicroMsg.SDK.WXMediaMessage";
                str2 = "checkArgs fail, thumbData is invalid";
            }
        }
        C10393b.m6195a(str, str2);
        return false;
    }

    public final int getType() {
        IMediaObject iMediaObject = this.mediaObject;
        if (iMediaObject == null) {
            return 0;
        }
        return iMediaObject.type();
    }

    public final void setThumbImage(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            this.thumbData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            C10393b.m6195a("MicroMsg.SDK.WXMediaMessage", "put thumb failed");
        }
    }
}
