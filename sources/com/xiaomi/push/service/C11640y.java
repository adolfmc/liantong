package com.xiaomi.push.service;

import android.content.SharedPreferences;
import com.xiaomi.push.C11184bb;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.y */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11640y {

    /* renamed from: a */
    private static Object f23792a = new Object();

    /* renamed from: a */
    private static Map<String, Queue<String>> f23793a = new HashMap();

    /* renamed from: a */
    public static boolean m2287a(XMPushService xMPushService, String str, String str2) {
        synchronized (f23792a) {
            SharedPreferences sharedPreferences = xMPushService.getSharedPreferences("push_message_ids", 0);
            Queue<String> queue = f23793a.get(str);
            if (queue == null) {
                String[] split = sharedPreferences.getString(str, "").split(",");
                LinkedList linkedList = new LinkedList();
                for (String str3 : split) {
                    linkedList.add(str3);
                }
                f23793a.put(str, linkedList);
                queue = linkedList;
            }
            if (queue.contains(str2)) {
                return true;
            }
            queue.add(str2);
            if (queue.size() > 25) {
                queue.poll();
            }
            String m4753a = C11184bb.m4753a(queue, ",");
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(str, m4753a);
            edit.commit();
            return false;
        }
    }
}
