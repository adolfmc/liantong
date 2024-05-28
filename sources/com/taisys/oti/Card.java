package com.taisys.oti;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.taisys.oti.C10351f;
import java.util.Calendar;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Card {
    public static final int GET_CARD_INFOR = 0;
    public static final int WRITE_CARD = 1;

    /* renamed from: a */
    private static final int f19784a = 2;

    /* renamed from: b */
    private static final int f19785b = 3;

    /* renamed from: c */
    private static final int f19786c = 4;

    /* renamed from: d */
    private static final int f19787d = -1;

    /* renamed from: e */
    private Process f19788e;

    /* renamed from: h */
    private Context f19791h;

    /* renamed from: i */
    private SimReady f19792i;

    /* renamed from: j */
    private C10351f f19793j;

    /* renamed from: f */
    private final int f19789f = 0;

    /* renamed from: g */
    private final int f19790g = 1;

    /* renamed from: k */
    private Handler f19794k = new Handler() { // from class: com.taisys.oti.Card.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (Card.this.f19792i != null) {
                        Card.this.f19792i.simReady(true);
                        return;
                    }
                    return;
                case 1:
                    if (Card.this.f19792i != null) {
                        Card.this.f19792i.simReady(false);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface SimReady {
        void simReady(boolean z);
    }

    public void setPrintLog(boolean z) {
        C10350e.f19917j = z;
        if (z) {
            try {
                this.f19788e = Runtime.getRuntime().exec(String.format("logcat -c", new Object[0]));
                Calendar calendar = Calendar.getInstance();
                this.f19788e = Runtime.getRuntime().exec(String.format("logcat -v time -f %s", String.format("/sdcard/testWriteCard_Log_%04d%02d%02d_%02d%02d%02d.txt", Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5)), Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12)), Integer.valueOf(calendar.get(13)))));
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Process process = this.f19788e;
        if (process != null) {
            process.destroy();
        }
    }

    public void isSimReady(Context context, SimReady simReady) {
        this.f19791h = context;
        this.f19792i = simReady;
        m6353a();
    }

    public String getIccid() {
        return C10350e.f19918k;
    }

    public String getStatus() {
        return C10350e.f19919l;
    }

    public String WriteCard(String str) {
        byte[] m6254a = this.f19793j.m6254a(1, C10350e.m6270a(str));
        if (m6254a == null) {
            return null;
        }
        C10350e.f19919l = C10350e.m6262b(m6254a).substring(32);
        return C10350e.f19919l;
    }

    /* renamed from: a */
    private void m6353a() {
        this.f19793j = new C10351f(this.f19791h, new C10351f.InterfaceC10356a() { // from class: com.taisys.oti.Card.2
            @Override // com.taisys.oti.C10351f.InterfaceC10356a
            /* renamed from: a */
            public void mo6231a(boolean z) {
                Message obtain = Message.obtain(Card.this.f19794k, 1);
                if (z) {
                    obtain = Message.obtain(Card.this.f19794k, 0);
                    Card.this.f19793j.m6248a(z);
                }
                obtain.sendToTarget();
            }
        });
    }

    public String getOtiType() {
        String str;
        switch (this.f19793j.m6255a()) {
            case 2:
                str = "SMS";
                break;
            case 3:
            case 4:
                str = "ADN";
                break;
            default:
                str = "Not Support";
                break;
        }
        Log.i("OTI", "OTI Type: " + str);
        return str;
    }
}
