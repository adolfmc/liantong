package cn.sharesdk.framework.p094a.p095a;

import android.content.ContentValues;
import android.database.Cursor;
import cn.sharesdk.framework.utils.SSDKLog;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MessageUtils {
    /* renamed from: a */
    public static synchronized long m21964a(String str, long j) {
        synchronized (MessageUtils.class) {
            if (str != null) {
                if (str.trim() != "") {
                    DBProvider m21970a = DBProvider.m21970a();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("post_time", Long.valueOf(j));
                    contentValues.put("message_data", str.toString());
                    return m21970a.m21968a("message", contentValues);
                }
            }
            return -1L;
        }
    }

    /* renamed from: a */
    public static synchronized long m21962a(ArrayList<String> arrayList) {
        synchronized (MessageUtils.class) {
            if (arrayList == null) {
                return 0L;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                sb.append("'");
                sb.append(arrayList.get(i));
                sb.append("'");
                sb.append(",");
            }
            String substring = sb.toString().substring(0, sb.length() - 1);
            int m21967a = DBProvider.m21970a().m21967a("message", "_id in ( " + substring + " )", null);
            SSDKLog.m21740b().m21735c("delete COUNT == %s", Integer.valueOf(m21967a));
            return m21967a;
        }
    }

    /* renamed from: a */
    private static synchronized ArrayList<MessageModel> m21963a(String str, String[] strArr) {
        ArrayList<MessageModel> arrayList;
        synchronized (MessageUtils.class) {
            arrayList = new ArrayList<>();
            MessageModel messageModel = new MessageModel();
            StringBuilder sb = new StringBuilder();
            Cursor m21966a = DBProvider.m21970a().m21966a("message", new String[]{"_id", "post_time", "message_data"}, str, strArr, null);
            while (m21966a != null && m21966a.moveToNext()) {
                messageModel.f2775b.add(m21966a.getString(0));
                if (messageModel.f2775b.size() == 100) {
                    sb.append(m21966a.getString(2));
                    messageModel.f2774a = sb.toString();
                    arrayList.add(messageModel);
                    messageModel = new MessageModel();
                    sb = new StringBuilder();
                } else {
                    sb.append(m21966a.getString(2) + "\n");
                }
            }
            m21966a.close();
            if (messageModel.f2775b.size() != 0) {
                messageModel.f2774a = sb.toString().substring(0, sb.length() - 1);
                arrayList.add(messageModel);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static synchronized ArrayList<MessageModel> m21965a() {
        synchronized (MessageUtils.class) {
            if (DBProvider.m21970a().m21969a("message") > 0) {
                return m21963a((String) null, (String[]) null);
            }
            return new ArrayList<>();
        }
    }
}
