package com.baidu.platform.comapi.longlink;

import com.baidu.platform.comapi.exception.ComInitException;
import com.baidu.platform.comapi.exception.InvalidComException;
import com.baidu.platform.comjni.base.longlink.NALongLink;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LongLinkClient {

    /* renamed from: a */
    private int f7555a;

    /* renamed from: b */
    private long f7556b;

    /* renamed from: c */
    private int f7557c;

    private LongLinkClient(long j) {
        this.f7556b = j;
    }

    private LongLinkClient(long j, int i) {
        this.f7556b = j;
        this.f7555a = i;
    }

    public static LongLinkClient create() throws ComInitException {
        long create = NALongLink.create();
        if (create != 0) {
            return new LongLinkClient(create);
        }
        throw new ComInitException("LongLink Component created failed!");
    }

    public static LongLinkClient create(int i) throws ComInitException {
        long create = NALongLink.create();
        if (create != 0) {
            return new LongLinkClient(create, i);
        }
        throw new ComInitException("LongLink Component created failed!");
    }

    public synchronized int getRequestId() {
        return this.f7557c;
    }

    public boolean init(String str, String str2) throws InvalidComException {
        if (isValid()) {
            return NALongLink.init(this.f7556b, str, str2);
        }
        throw new InvalidComException();
    }

    public boolean isValid() {
        return this.f7556b != 0;
    }

    public synchronized boolean register(LongLinkDataCallback longLinkDataCallback) throws InvalidComException {
        if (!isValid()) {
            throw new InvalidComException();
        }
        return NALongLink.register(this.f7556b, this.f7555a, longLinkDataCallback);
    }

    public int release() {
        if (!isValid() || NALongLink.release(this.f7556b) > 0) {
            return -1;
        }
        this.f7556b = 0L;
        return -1;
    }

    public synchronized ELongLinkStatus sendData(byte[] bArr) throws InvalidComException {
        ELongLinkStatus eLongLinkStatus;
        if (!isValid()) {
            throw new InvalidComException();
        }
        this.f7557c++;
        eLongLinkStatus = ELongLinkStatus.values()[NALongLink.sendData(this.f7556b, this.f7555a, this.f7557c, bArr)];
        eLongLinkStatus.setRequestId(this.f7557c);
        return eLongLinkStatus;
    }

    public synchronized ELongLinkStatus sendFileData(String str, ArrayList<LongLinkFileData> arrayList) throws InvalidComException {
        ELongLinkStatus eLongLinkStatus;
        if (!isValid()) {
            throw new InvalidComException();
        }
        this.f7557c++;
        eLongLinkStatus = ELongLinkStatus.values()[NALongLink.sendFileData(this.f7556b, this.f7555a, this.f7557c, str, arrayList)];
        eLongLinkStatus.setRequestId(this.f7557c);
        return eLongLinkStatus;
    }

    public void setModuleId(int i) {
        this.f7555a = i;
    }

    public boolean start() throws InvalidComException {
        if (isValid()) {
            return NALongLink.start(this.f7556b);
        }
        throw new InvalidComException();
    }

    public void stop() throws InvalidComException {
        if (!isValid()) {
            throw new InvalidComException();
        }
        NALongLink.stop(this.f7556b);
    }

    public synchronized boolean unRegister(LongLinkDataCallback longLinkDataCallback) throws InvalidComException {
        if (!isValid()) {
            throw new InvalidComException();
        }
        return NALongLink.unRegister(this.f7556b, this.f7555a, longLinkDataCallback);
    }
}
