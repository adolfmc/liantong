package com.mob.commons.eventrecoder;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5859o;
import com.mob.commons.InterfaceC5858n;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.FileLocker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class EventRecorder implements PublicMemberKeeper {

    /* renamed from: a */
    private static File f14359a;

    /* renamed from: b */
    private static FileOutputStream f14360b;

    public static final synchronized void prepare() {
        synchronized (EventRecorder.class) {
            m12311a(new InterfaceC5858n() { // from class: com.mob.commons.eventrecoder.EventRecorder.1
                @Override // com.mob.commons.InterfaceC5858n
                /* renamed from: a */
                public boolean mo11219a(FileLocker fileLocker) {
                    try {
                        File unused = EventRecorder.f14359a = new File(MobSDK.getContext().getFilesDir(), ".mrecord");
                        if (!EventRecorder.f14359a.exists()) {
                            EventRecorder.f14359a.createNewFile();
                        }
                        FileOutputStream unused2 = EventRecorder.f14360b = new FileOutputStream(EventRecorder.f14359a, true);
                        return false;
                    } catch (Throwable th) {
                        MobLog.getInstance().m11325w(th);
                        return false;
                    }
                }
            });
        }
    }

    public static final synchronized void addBegin(String str, String str2) {
        synchronized (EventRecorder.class) {
            m12308a(str + " " + str2 + " 0\n");
        }
    }

    /* renamed from: a */
    private static final void m12311a(InterfaceC5858n interfaceC5858n) {
        C5859o.m12225a(new File(MobSDK.getContext().getFilesDir(), C5859o.f14435a), interfaceC5858n);
    }

    public static final synchronized void addEnd(String str, String str2) {
        synchronized (EventRecorder.class) {
            m12308a(str + " " + str2 + " 1\n");
        }
    }

    /* renamed from: a */
    private static final void m12308a(final String str) {
        m12311a(new InterfaceC5858n() { // from class: com.mob.commons.eventrecoder.EventRecorder.2
            @Override // com.mob.commons.InterfaceC5858n
            /* renamed from: a */
            public boolean mo11219a(FileLocker fileLocker) {
                try {
                    EventRecorder.f14360b.write(str.getBytes("utf-8"));
                    EventRecorder.f14360b.flush();
                    return false;
                } catch (Throwable th) {
                    MobLog.getInstance().m11325w(th);
                    return false;
                }
            }
        });
    }

    public static final synchronized String checkRecord(final String str) {
        synchronized (EventRecorder.class) {
            final LinkedList linkedList = new LinkedList();
            m12311a(new InterfaceC5858n() { // from class: com.mob.commons.eventrecoder.EventRecorder.3
                @Override // com.mob.commons.InterfaceC5858n
                /* renamed from: a */
                public boolean mo11219a(FileLocker fileLocker) {
                    int indexOf;
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(EventRecorder.f14359a), "utf-8"));
                        for (String readLine = bufferedReader.readLine(); !TextUtils.isEmpty(readLine); readLine = bufferedReader.readLine()) {
                            String[] split = readLine.split(" ");
                            if (str.equals(split[0])) {
                                if ("0".equals(split[2])) {
                                    linkedList.add(split[1]);
                                } else if ("1".equals(split[2]) && (indexOf = linkedList.indexOf(split[1])) != -1) {
                                    linkedList.remove(indexOf);
                                }
                            }
                        }
                        bufferedReader.close();
                    } catch (Throwable th) {
                        MobLog.getInstance().m11341d(th);
                    }
                    return false;
                }
            });
            if (linkedList.size() > 0) {
                return (String) linkedList.get(0);
            }
            return null;
        }
    }

    public static final synchronized void clear() {
        synchronized (EventRecorder.class) {
            m12311a(new InterfaceC5858n() { // from class: com.mob.commons.eventrecoder.EventRecorder.4
                @Override // com.mob.commons.InterfaceC5858n
                /* renamed from: a */
                public boolean mo11219a(FileLocker fileLocker) {
                    try {
                        EventRecorder.f14360b.close();
                        EventRecorder.f14359a.delete();
                        File unused = EventRecorder.f14359a = new File(MobSDK.getContext().getFilesDir(), ".mrecord");
                        EventRecorder.f14359a.createNewFile();
                        FileOutputStream unused2 = EventRecorder.f14360b = new FileOutputStream(EventRecorder.f14359a, true);
                        return false;
                    } catch (Throwable th) {
                        MobLog.getInstance().m11325w(th);
                        return false;
                    }
                }
            });
        }
    }
}
