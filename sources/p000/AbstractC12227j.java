package p000;

import android.support.annotation.NonNull;
import com.crb.jscard.entity.CardResult;

/* JADX WARN: Classes with same name are omitted:
  synthetic
 */
/* compiled from: BaseSmartCard.java */
/* renamed from: j */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC12227j {

    /* renamed from: a */
    public EnumReaderType f24820a;

    /* renamed from: a */
    public abstract CardResult mo1832a(@NonNull String str);

    /* renamed from: a */
    public abstract void mo1833a();

    /* renamed from: b */
    public CardResult m1928b(String str) {
        mo1831b();
        return new CardResult(-1, str);
    }

    /* renamed from: b */
    public abstract void mo1831b();

    /* renamed from: c */
    public abstract void mo1830c();

    /* renamed from: d */
    public EnumReaderType m1927d() {
        EnumReaderType enumReaderType = this.f24820a;
        return enumReaderType == null ? EnumReaderType.READER_TYPE_SIM : enumReaderType;
    }
}
