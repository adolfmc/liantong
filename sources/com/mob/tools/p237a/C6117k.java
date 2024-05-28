package com.mob.tools.p237a;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5829d;
import com.mob.commons.C5868q;
import com.mob.commons.C5873u;
import com.mob.commons.C5892y;
import com.mob.tools.MobLog;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.ReflectHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.tools.a.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6117k {

    /* renamed from: a */
    static volatile IBinder f14974a = null;

    /* renamed from: b */
    private static int f14975b = 0;

    /* renamed from: c */
    private static volatile int f14976c = Integer.MIN_VALUE;

    /* renamed from: a */
    public static Set<String> m11375a(Context context, int i) {
        HandlerThread handlerThread = new HandlerThread(C5892y.f14523a + "XPL-1");
        handlerThread.start();
        Set<String> set = null;
        try {
            if (i == 1) {
                set = m11370a(context, true, handlerThread);
            } else if (i == 4 && !C5868q.m12203b("0056ce^eNchfbcf").equalsIgnoreCase(C6031c.m11708a(context).m11704d().mo11516m()) && m11377a()) {
                set = m11374a(context, handlerThread);
            }
            if (Build.VERSION.SDK_INT >= 18) {
                handlerThread.quitSafely();
            } else {
                handlerThread.quit();
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        return set;
    }

    /* renamed from: a */
    public static Set<String> m11374a(Context context, HandlerThread handlerThread) throws Throwable {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        if (C5829d.m12330b()) {
            File file = new File(context.getFilesDir(), ".tmp11");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, C5868q.m12203b("0025chEd") + System.currentTimeMillis());
            File file3 = new File(file, "out" + System.currentTimeMillis());
            File file4 = new File(file, "err" + System.currentTimeMillis());
            if (file3.exists()) {
                file3.delete();
            }
            try {
                m11371a(context, C5868q.m12203b("007icb7ck^c(ddVe"), new String[]{C5868q.m12203b("004fLchegUh"), "packages"}, file2, file3, file4, handlerThread);
                if (file3.exists() && file3.length() > 0) {
                    HashSet hashSet = new HashSet();
                    fileInputStream = new FileInputStream(file3);
                    try {
                        inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamReader = null;
                    }
                    try {
                        String m12203b = C5868q.m12203b("008icb_ck=c*ddGej");
                        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                            String trim = readLine.trim();
                            if (trim.length() > m12203b.length() && trim.substring(0, m12203b.length()).equalsIgnoreCase(m12203b)) {
                                String trim2 = trim.substring(m12203b.length()).trim();
                                if (!TextUtils.isEmpty(trim2)) {
                                    hashSet.add(trim2);
                                }
                            }
                        }
                        C5873u.m12179a(bufferedReader, inputStreamReader, fileInputStream);
                        file2.delete();
                        file3.delete();
                        file4.delete();
                        return hashSet;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader2 = bufferedReader;
                        C5873u.m12179a(bufferedReader2, inputStreamReader, fileInputStream);
                        file2.delete();
                        file3.delete();
                        file4.delete();
                        throw th;
                    }
                }
                C5873u.m12179a(null, null, null);
                file2.delete();
                file3.delete();
                file4.delete();
            } catch (Throwable th4) {
                th = th4;
                fileInputStream = null;
                inputStreamReader = null;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static Set<String> m11370a(Context context, boolean z, HandlerThread handlerThread) throws Throwable {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        if (C5829d.m12330b()) {
            File file = new File(context.getFilesDir(), ".tmp11");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, C5868q.m12203b("002NchUd") + System.currentTimeMillis());
            File file3 = new File(file, "out" + System.currentTimeMillis());
            File file4 = new File(file, "err" + System.currentTimeMillis());
            if (file3.exists()) {
                file3.delete();
            }
            try {
                if (z) {
                    m11371a(context, C5868q.m12203b("007icb%ckHc=dd?e"), new String[]{C5868q.m12203b("016$cdcf>eJcicjgj_cbhAchccch7hHchSe>eg"), "-a", C5868q.m12203b("026cdYcbcidcchcbecch.dhedh-ec3cbh9chdc3d-ecgbdkdhdg"), "--user", "0"}, file2, file3, file4, handlerThread);
                } else {
                    m11371a(context, C5868q.m12203b("007icbCckGc.ddMe"), new String[]{C5868q.m12203b("0161cdcf<eUcicjgj2cbhXchccchRh'ch(e^eg"), "-a", C5868q.m12203b("026cdPcbcidcchcbecch9dhedh.ecKcbhBchdc<dAecgbdkdhdg"), "-c", C5868q.m12203b("032cdQcbcidcchcbecchRdhedh9ecZbche7dddccicjecebdkdfdgfieifffg"), "--user", "0"}, file2, file3, file4, handlerThread);
                }
                if (file3.exists() && file3.length() > 0) {
                    HashSet hashSet = new HashSet();
                    fileInputStream = new FileInputStream(file3);
                    try {
                        inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamReader = null;
                    }
                    try {
                        String m12203b = C5868q.m12203b("012icb4ckMcNdd.e(dg=c6ce[eOhh");
                        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                            String trim = readLine.trim();
                            if (trim.length() > m12203b.length() && trim.substring(0, m12203b.length()).equalsIgnoreCase(m12203b)) {
                                String trim2 = trim.substring(m12203b.length()).trim();
                                if (!TextUtils.isEmpty(trim2)) {
                                    hashSet.add(trim2);
                                }
                            }
                        }
                        C5873u.m12179a(bufferedReader, inputStreamReader, fileInputStream);
                        file2.delete();
                        file3.delete();
                        file4.delete();
                        return hashSet;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader2 = bufferedReader;
                        C5873u.m12179a(bufferedReader2, inputStreamReader, fileInputStream);
                        file2.delete();
                        file3.delete();
                        file4.delete();
                        throw th;
                    }
                }
                C5873u.m12179a(null, null, null);
                file2.delete();
                file3.delete();
                file4.delete();
            } catch (Throwable th4) {
                th = th4;
                fileInputStream = null;
                inputStreamReader = null;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static int m11371a(Context context, String str, String[] strArr, File file, File file2, File file3, HandlerThread handlerThread) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        FileOutputStream fileOutputStream3;
        IBinder iBinder;
        Object m11643a;
        try {
            iBinder = (IBinder) C6040h.m11645a(context).m11642a(C5868q.m12203b("025cd^cbcidcchcbecdcegecdi-eRciccchQbeYgb!cdcYdd6e3ci"), (Object) null, C5868q.m12203b("010-ddZeh6di?eGciccch*be"), new Class[]{String.class}, new Object[]{str});
            m11643a = C6040h.m11645a(context).m11643a(C5868q.m12203b("024cdVcbcidcchcbecdcegecdi3geff6fiRcffEedHcb6ck"));
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
            fileOutputStream2 = null;
        }
        try {
            fileOutputStream2 = new FileOutputStream(file2);
            try {
                fileOutputStream3 = new FileOutputStream(file3);
                try {
                    C6040h.m11645a(context).m11644a(IBinder.class, iBinder, C5868q.m12203b("012YegEgeffOfidccece8cd7cb"), new Class[]{FileDescriptor.class, FileDescriptor.class, FileDescriptor.class, String[].class, Class.forName(C5868q.m12203b("024cd*cbcidcchcbecdcegecdi;geffQfi.cffCed;cb^ck")), ResultReceiver.class}, new Object[]{fileOutputStream.getFD(), fileOutputStream2.getFD(), fileOutputStream3.getFD(), strArr, m11643a, new ResultReceiver(new Handler(handlerThread.getLooper()))});
                    C5873u.m12179a(fileOutputStream, fileOutputStream2, fileOutputStream3);
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    C5873u.m12179a(fileOutputStream, fileOutputStream2, fileOutputStream3);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream3 = null;
            }
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream2 = null;
            fileOutputStream3 = fileOutputStream2;
            C5873u.m12179a(fileOutputStream, fileOutputStream2, fileOutputStream3);
            throw th;
        }
    }

    /* renamed from: a */
    public static Object m11373a(Context context, String str, int i) throws Throwable {
        return m11372a(context, str, i, m11368c(), m11376a(context));
    }

    /* renamed from: a */
    private static Object m11372a(Context context, String str, int i, int i2, int i3) throws Throwable {
        if (Build.VERSION.SDK_INT < 23) {
            return null;
        }
        if (f14974a == null) {
            f14974a = (IBinder) C6040h.m11645a(context).m11642a(C5868q.m12203b("025cdCcbcidcchcbecdcegecdi7e>ciccch!beDgb]cdcVddWeFci"), (Object) null, C5868q.m12203b("010-ddHeh7diMeYciccch6be"), new Class[]{String.class}, new Object[]{C5868q.m12203b("007icb'ckRcAdd4e")});
        }
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(C5868q.m12203b("034cd9cbcidcchcbecRbEdc>dhedh:ec%iFceecdhfjDcbMckRc+dd$e=gb(cdc%dd3e$ci"));
            obtain.writeString(str);
            obtain.writeInt(i);
            obtain.writeInt(i2);
            f14974a.transact(i3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readTypedObject(m11369b());
        } finally {
            obtain2.recycle();
            obtain.recycle();
            C6040h.m11645a(context).m11639b(context);
        }
    }

    /* renamed from: b */
    private static Parcelable.Creator<?> m11369b() throws Throwable {
        return (Parcelable.Creator) ReflectHelper.getStaticField(ReflectHelper.importClass(C5868q.m12203b("030cd]cbcidcchcbecRb*dcOdhedh6ec_i'ceecfjPcb2ck%cSdd6e dhWd=dedc")), C5868q.m12203b("0075fifgffdkdjfkfg"));
    }

    /* renamed from: a */
    private static int m11376a(Context context) throws Throwable {
        int i = f14975b;
        if (i != 0) {
            return i;
        }
        String m12203b = C5868q.m12203b("034cd'cbcidcchcbec0b6dc]dhedh2ecLi%ceecdhfj:cbOckNc[dd*eWgbZcdcJdd0e0ci");
        String m12203b2 = C5868q.m12203b("004*di6hAcfed");
        f14975b = ((Integer) C6040h.m11645a(context).m11641a(m12203b + "$" + m12203b2, C5868q.m12203b("026?djfgdkdgdidkfidjdhfkdgcgddYehZfjGcb^ckHcHddTe6dh=d)dedc"), (Object) null)).intValue();
        return f14975b;
    }

    /* renamed from: c */
    private static int m11368c() {
        if (f14976c != Integer.MIN_VALUE) {
            return f14976c;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                int intValue = ((Integer) ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(C5868q.m12203b("021cdWcbcidcchcbecdcegecdfeg>e1ciei'cdNcb1fe")), C5868q.m12203b("0098dd_ehNdfeg(eHcidhcb"), new Object[]{Integer.valueOf(Process.myUid())}, new Class[]{Integer.TYPE})).intValue();
                f14976c = intValue;
                return intValue;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static boolean m11377a() {
        try {
            if (C5868q.m12203b("006g;cfLcFeeZe=ch").equalsIgnoreCase(C6152DH.SyncMtd.getManufacturer())) {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                final int[] iArr = new int[1];
                C6152DH.requester(MobSDK.getContext()).getHmOsDetailedVer().request(new C6152DH.DHResponder() { // from class: com.mob.tools.a.k.1
                    @Override // com.mob.tools.utils.C6152DH.DHResponder
                    public void onResponse(C6152DH.DHResponse dHResponse) {
                        String hmOsDetailedVer = dHResponse.getHmOsDetailedVer();
                        if (hmOsDetailedVer == null) {
                            hmOsDetailedVer = "";
                        }
                        iArr[0] = "3.0.0.200".compareTo(hmOsDetailedVer);
                        countDownLatch.countDown();
                    }
                });
                countDownLatch.await();
                return iArr[0] > 0;
            }
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return true;
        }
    }
}
