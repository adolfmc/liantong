package com.mob.commons.p230b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.Signature;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5869r;
import com.mob.commons.p230b.AbstractC5764e;
import com.mob.tools.C6122c;
import com.mob.tools.utils.C6152DH;
import java.security.MessageDigest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* renamed from: com.mob.commons.b.i */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5773i extends AbstractC5764e {

    /* renamed from: c */
    private String f14242c;

    public C5773i(Context context) {
        super(context);
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: e */
    public synchronized boolean mo12495e() {
        long j;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Object[] objArr = new Object[1];
        C6152DH.requester(MobSDK.getContext()).getMpfo(C5869r.m12200a("017c'eddffdLhfBdkGidjJfded]jfe*didc"), 0).request(new C6152DH.DHResponder() { // from class: com.mob.commons.b.i.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                try {
                    objArr[0] = dHResponse.getMpfo(new int[0]);
                } finally {
                    countDownLatch.countDown();
                }
            }
        });
        try {
            countDownLatch.await(200L, TimeUnit.MILLISECONDS);
            j = 0;
            if (objArr[0] != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    j = C6122c.m11347g(objArr[0], this.f14225b);
                } else {
                    j = C6122c.m11349f(objArr[0], this.f14225b);
                }
            }
        } catch (Throwable unused) {
            return false;
        }
        return j >= 1;
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: a */
    protected Intent mo12492a() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(C5869r.m12200a("017cWeddffd5hf-dk_idj9fdedOjfe7didc"), C5869r.m12200a("033cUeddffd%hfXdkAidj1fdedHjfe,didcfdeidcVfeiVdiefdkej[fEdjdddiYcf")));
        intent.setAction(C5869r.m12200a("040dciTdied0eBfd0c@eddffd6hfXdk=idj%fdedVjfe4didcfdglgkggehdheifkdhejggghgieigjgg"));
        return intent;
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: a */
    public AbstractC5764e.C5767b mo12491a(IBinder iBinder) {
        AbstractC5764e.C5767b c5767b = new AbstractC5764e.C5767b();
        c5767b.f14234b = m12501a(iBinder, C5869r.m12200a("004,glegeifk"));
        return c5767b;
    }

    /* renamed from: a */
    private final String m12501a(IBinder iBinder, String str) {
        if (TextUtils.isEmpty(this.f14242c)) {
            try {
                final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
                C6152DH.requester(MobSDK.getContext()).getMpfo(this.f14225b, 64).request(new C6152DH.DHResponder() { // from class: com.mob.commons.b.i.2
                    @Override // com.mob.tools.utils.C6152DH.DHResponder
                    public void onResponse(C6152DH.DHResponse dHResponse) {
                        if (dHResponse.getMpfo(new int[0]) != null) {
                            linkedBlockingQueue.offer(dHResponse.getMpfo(new int[0]));
                        } else {
                            linkedBlockingQueue.offer(Boolean.FALSE);
                        }
                    }
                });
                Object poll = linkedBlockingQueue.poll(300L, TimeUnit.MILLISECONDS);
                Signature[] m11357b = poll instanceof Boolean ? null : C6122c.m11357b(poll, this.f14225b);
                if (m11357b != null && m11357b.length > 0) {
                    byte[] byteArray = m11357b[0].toByteArray();
                    MessageDigest messageDigest = MessageDigest.getInstance(C5869r.m12200a("004Xejfjelhf"));
                    if (messageDigest != null) {
                        byte[] digest = messageDigest.digest(byteArray);
                        StringBuilder sb = new StringBuilder();
                        for (byte b : digest) {
                            sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
                        }
                        this.f14242c = sb.toString();
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return m12520a(str, iBinder, C5869r.m12200a("025cKeddffd'hf0dk)idj'fded?jfe0didcfdeiglNjfe$eifk"), 1, this.f14225b, this.f14242c, str);
    }
}
