package com.sina.weibo.sdk.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.p311b.C7089a;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.share.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7113e {

    /* renamed from: D */
    public long f18340D;

    /* renamed from: a */
    public static void m8032a(Activity activity, WeiboMultiMessage weiboMultiMessage) {
        if (activity != null && m8031a((Context) activity, weiboMultiMessage)) {
            Bundle bundle = new Bundle();
            bundle.putInt("_weibo_command_type", 1);
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            bundle.putString("_weibo_transaction", sb.toString());
            bundle.putAll(weiboMultiMessage.writeToBundle(bundle));
            Intent intent = new Intent(activity, ShareTransActivity.class);
            intent.putExtra("start_flag", 1001);
            intent.putExtras(bundle);
            activity.startActivityForResult(intent, 10001);
        }
    }

    /* renamed from: a */
    private static boolean m8031a(Context context, WeiboMultiMessage weiboMultiMessage) {
        if (weiboMultiMessage != null) {
            List<Object> m8029b = m8029b(weiboMultiMessage);
            if (weiboMultiMessage.superGroupObject == null || C7089a.m8084d(context)) {
                return true;
            }
            if (m8030a(m8029b, weiboMultiMessage.superGroupObject)) {
                Toast.makeText(context, "微博版本过低，不支持超话分享", 0).show();
                return false;
            }
            weiboMultiMessage.superGroupObject = null;
            return true;
        }
        return true;
    }

    /* renamed from: a */
    private static boolean m8030a(List<Object> list, Object obj) {
        return (list == null || list.isEmpty() || obj == null || !list.contains(obj) || list.size() != 1) ? false : true;
    }

    /* renamed from: b */
    private static List<Object> m8029b(WeiboMultiMessage weiboMultiMessage) {
        ArrayList arrayList = new ArrayList();
        if (weiboMultiMessage.textObject != null) {
            arrayList.add(weiboMultiMessage.textObject);
        }
        if (weiboMultiMessage.imageObject != null) {
            arrayList.add(weiboMultiMessage.imageObject);
        }
        if (weiboMultiMessage.mediaObject != null) {
            arrayList.add(weiboMultiMessage.mediaObject);
        }
        if (weiboMultiMessage.multiImageObject != null) {
            arrayList.add(weiboMultiMessage.multiImageObject);
        }
        if (weiboMultiMessage.videoSourceObject != null) {
            arrayList.add(weiboMultiMessage.videoSourceObject);
        }
        if (weiboMultiMessage.superGroupObject != null) {
            arrayList.add(weiboMultiMessage.superGroupObject);
        }
        return arrayList;
    }
}
