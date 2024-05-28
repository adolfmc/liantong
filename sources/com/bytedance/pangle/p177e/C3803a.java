package com.bytedance.pangle.p177e;

import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p176d.C3794e;
import com.bytedance.pangle.util.C3947g;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.e.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3803a {
    /* renamed from: a */
    public static boolean m16908a(String[] strArr) {
        if (strArr.length <= 0) {
            return false;
        }
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            InputStream errorStream = exec.getErrorStream();
            InputStream inputStream = exec.getInputStream();
            m16909a(errorStream);
            m16909a(inputStream);
            if (exec.waitFor() != 0) {
                ZeusLogger.errReport("Zeus/install_pangle", "exec dex2oat failed : " + strArr.toString());
                return false;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    private static void m16909a(final InputStream inputStream) {
        C3794e.m16918a(new Runnable() { // from class: com.bytedance.pangle.e.a.1
            @Override // java.lang.Runnable
            public final void run() {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            return;
                        }
                        ZeusLogger.m16794d("Zeus/install_pangle", "exec cmd info : ".concat(String.valueOf(readLine)));
                    } catch (IOException e) {
                        ZeusLogger.errReport("Zeus/install_pangle", "execCmd consumeInputStream failed : ".concat(String.valueOf(e)));
                        return;
                    } finally {
                        C3947g.m16635a(bufferedReader);
                    }
                }
            }
        });
    }
}
