package com.mob.tools.p237a;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import com.mob.commons.C5829d;
import com.mob.commons.C5868q;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.C6202d;
import com.mob.tools.utils.ReflectHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.tools.a.i */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C6044i {
    /* renamed from: a */
    public static Object m11632a(Context context, String str) throws Throwable {
        Object m11642a;
        Parcel parcel;
        Parcel parcel2;
        if (!C5829d.m12326f() || !C6202d.m11087a().m11078a(str) || Build.VERSION.SDK_INT < 23) {
            return null;
        }
        C6040h m11645a = C6040h.m11645a(context);
        if (Build.VERSION.SDK_INT >= 31) {
            Object m11643a = m11645a.m11643a(C5868q.m12203b("036cdUcbcidcchcbec[f+dcXbchFchdcQd)ecebXc-eg h@ebdc%bch*chdc*dIfgYeCcdcf[e'egMh") + "$" + C5868q.m12203b("007Tehcfch.f?cb,eJci"));
            m11642a = m11645a.m11642a(C5868q.m12203b("036cdWcbcidcchcbec3f!dcQbchSchdc]dGeceb,c7egJhWebdcAbchEchdcBdWfg0e8cdcf2e=egJh") + "$" + C5868q.m12203b("007Fehcfch f*cbRe6ci"), m11643a, C5868q.m12203b("005 edcfch)fCcb"), (Class[]) null, (Object[]) null);
        } else {
            m11642a = m11645a.m11642a(C5868q.m12203b("032cd>cbcidcchcbec fAdc9bchMchdcUdGecebdcPbchNchdcYd@fg^eHcdcf[e1egQh"), (Object) null, C5868q.m12203b("028b3ci)eche$ekcidcceej1ei]ciJebcheEcbfjcidcccchcb$e]ci"), new Class[]{String.class, Long.TYPE, Float.TYPE, Boolean.TYPE}, new Object[]{str, 0, 0, true});
        }
        int intValue = ((Integer) m11645a.m11641a(C5868q.m12203b("033cdNcbcidcchcbecRfDdc!bchCchdc2d)ecdhebdc)bch5chdcGd%gb]cdcPdd*eEci") + "$" + C5868q.m12203b("0045di8h@cfed"), C5868q.m12203b("027+djfgdkdgdidkfidjdhfkdgcgddHehZeb7c,eg<h6ebdcObchQchdcVd"), (Object) null)).intValue();
        IBinder iBinder = (IBinder) m11645a.m11642a(C5868q.m12203b("025cd6cbcidcchcbecdcegecdiAeJciccch5be1gbYcdc:ddEe_ci"), (Object) null, C5868q.m12203b("010+dd1ehHdi1eDciccchVbe"), new Class[]{String.class}, new Object[]{C5868q.m12203b("008f]dc3bch+chdc>d")});
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(C5868q.m12203b("033cdMcbcidcchcbec4fUdcKbch%chdc>dWecdhebdcRbchEchdcHdBgbFcdcRdd-eUci"));
            if (Build.VERSION.SDK_INT >= 31) {
                obtain.writeString(str);
                obtain.writeTypedObject((Parcelable) m11642a, 0);
                parcel = obtain2;
                parcel2 = obtain;
            } else {
                obtain.writeInt(1);
                Class<?> cls = m11642a.getClass();
                Object obj = m11642a;
                parcel = obtain2;
                parcel2 = obtain;
                try {
                    m11645a.m11644a(cls, obj, C5868q.m12203b("013%eecichOheTdjdcfj%c?ci=bef"), new Class[]{Parcel.class, Integer.TYPE}, new Object[]{obtain, 0});
                } catch (Throwable th) {
                    th = th;
                    C6040h.m11645a(context).m11639b(context);
                    parcel.recycle();
                    parcel2.recycle();
                    throw th;
                }
            }
            parcel2.writeString(context.getPackageName());
            if (Build.VERSION.SDK_INT >= 30) {
                parcel2.writeString(context.getAttributionTag());
            }
            iBinder.transact(intValue, parcel2, parcel, 0);
            parcel.readException();
            Object readTypedObject = parcel.readTypedObject(m11633a());
            C6040h.m11645a(context).m11639b(context);
            parcel.recycle();
            parcel2.recycle();
            return readTypedObject;
        } catch (Throwable th2) {
            th = th2;
            parcel = obtain2;
            parcel2 = obtain;
        }
    }

    /* renamed from: a */
    private static Parcelable.Creator<?> m11633a() throws Throwable {
        return (Parcelable.Creator) ReflectHelper.getStaticField(ReflectHelper.importClass(C5868q.m12203b("025cd.cbcidcchcbec.f*dc4bch'chdc1d[ecebdc4bch]chdc3d")), C5868q.m12203b("007^fifgffdkdjfkfg"));
    }

    /* renamed from: a */
    public static Object m11631a(Context context, String str, long j) throws Throwable {
        int i;
        Object m11642a;
        Parcel parcel;
        Parcel parcel2;
        Object m11640a;
        char c;
        if (!C5829d.m12327e() || !C6202d.m11087a().m11078a(str) || Build.VERSION.SDK_INT < 23) {
            return null;
        }
        C6040h m11645a = C6040h.m11645a(context);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Object[] objArr = new Object[1];
        if (Build.VERSION.SDK_INT >= 31) {
            i = 0;
            m11642a = m11645a.m11642a(C5868q.m12203b("032cd%cbcidcchcbec%f@dcVbchNchdc_dEecebdcAbch)chdcOd<fgBeScdcfXeUegEh") + "$" + C5868q.m12203b("007Fehcfch%fZcb4e!ci"), m11645a.m11640a(C5868q.m12203b("032cd5cbcidcchcbecWf8dc%bchTchdc,d-ecebdcQbchZchdc:d,fgLeBcdcf:eNeg.h") + "$" + C5868q.m12203b("007JehcfchTfEcbMeSci"), new Class[]{Long.TYPE}, new Object[]{0L}), C5868q.m12203b("005GedcfchUf7cb"), (Class[]) null, (Object[]) null);
        } else {
            i = 0;
            m11642a = m11645a.m11642a(C5868q.m12203b("032cdCcbcidcchcbec[f=dcYbchCchdc.dVecebdcPbchEchdc>d(fg'e(cdcf4eUeg<h"), (Object) null, C5868q.m12203b("028bOci;eche[ekcidcceejKei;ci?ebche]cbfjcidcccchcbJe$ci"), new Class[]{String.class, Long.TYPE, Float.TYPE, Boolean.TYPE}, new Object[]{str, 0, 0, true});
        }
        String m12203b = C5868q.m12203b("025cd<cbcidcchcbecdcegecdi6eFciccch7be$gbDcdc(ddGe4ci");
        String m12203b2 = C5868q.m12203b("010)dd(ehWdi%eEciccchJbe");
        Class[] clsArr = new Class[1];
        clsArr[i] = String.class;
        Object[] objArr2 = new Object[1];
        objArr2[i] = C5868q.m12203b("008fLdc>bch*chdcHd");
        IBinder iBinder = (IBinder) m11645a.m11642a(m12203b, (Object) null, m12203b2, clsArr, objArr2);
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(C5868q.m12203b("033cdLcbcidcchcbec9f8dc-bch?chdc=d:ecdhebdcUbchMchdc!d*gbTcdc!ddHeUci"));
            if (Build.VERSION.SDK_INT >= 31) {
                obtain.writeString(str);
                obtain.writeTypedObject((Parcelable) m11642a, i);
                Consumer consumer = new Consumer() { // from class: com.mob.tools.a.i.1
                    @Override // java.util.function.Consumer
                    public void accept(Object obj) {
                        try {
                            objArr[0] = obj;
                        } finally {
                            try {
                            } finally {
                            }
                        }
                    }
                };
                Class[] clsArr2 = new Class[3];
                clsArr2[i] = Executor.class;
                clsArr2[1] = Consumer.class;
                clsArr2[2] = CancellationSignal.class;
                Object[] objArr3 = new Object[3];
                objArr3[i] = Executors.newSingleThreadExecutor();
                objArr3[1] = consumer;
                objArr3[2] = null;
                Object m11640a2 = m11645a.m11640a(C5868q.m12203b("032cd.cbcidcchcbecCfRdcAbch>chdc9d=ecebdcDbch^chdc'dZgbDcdc:dd5e)ci") + "$" + C5868q.m12203b("027BhcQeh!ficfciciMedh$ebdc?bch+chdc>d*djci?cdAeg:iKdcciXh"), clsArr2, objArr3);
                String m12203b3 = C5868q.m12203b("012hRdcfg6ebeXchccQe?cidhcb");
                Class[] clsArr3 = new Class[1];
                clsArr3[i] = Object.class;
                Object[] objArr4 = new Object[1];
                objArr4[i] = consumer;
                parcel = obtain2;
                try {
                    obtain.writeStrongInterface((IInterface) m11640a2);
                    obtain.writeString(context.getPackageName());
                    obtain.writeString(context.getAttributionTag());
                    obtain.writeString((String) m11645a.m11644a(AppOpsManager.class, (Object) null, m12203b3, clsArr3, objArr4));
                    iBinder.transact(((Integer) m11645a.m11641a(C5868q.m12203b("033cd[cbcidcchcbecVf(dc$bchYchdcTd[ecdhebdcZbch'chdc(d7gb1cdc;dd,e7ci") + "$" + C5868q.m12203b("004?diOh0cfed"), C5868q.m12203b("030VdjfgdkdgdidkfidjdhfkdgcgddIeh?ficfciciIedh0ebdcEbch chdcNd"), (Object) null)).intValue(), obtain, parcel, 0);
                    parcel2 = obtain;
                    c = 0;
                } catch (Throwable th) {
                    th = th;
                    parcel2 = obtain;
                    parcel.recycle();
                    parcel2.recycle();
                    C6040h.m11645a(context).m11639b(context);
                    throw th;
                }
            } else {
                parcel = obtain2;
                try {
                    obtain.writeInt(1);
                } catch (Throwable th2) {
                    th = th2;
                    parcel2 = obtain;
                }
                try {
                    m11645a.m11644a(m11642a.getClass(), m11642a, C5868q.m12203b("013,eecich]heXdjdcfj>cHci8bef"), new Class[]{Parcel.class, Integer.TYPE}, new Object[]{obtain, 0});
                    HashMap hashMap = new HashMap();
                    final int identityHashCode = System.identityHashCode(hashMap);
                    hashMap.put(C5868q.m12203b("017=dc9d@ebdcTbch)chdc'd fiXgcd<ddCeTcb"), new ReflectHelper.InterfaceC6184a<Object[], Object>() { // from class: com.mob.tools.a.i.2
                        @Override // com.mob.tools.utils.ReflectHelper.InterfaceC6184a
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public Object mo11056a(Object[] objArr5) {
                            if (objArr5 != null) {
                                try {
                                    if (objArr5.length > 0) {
                                        NLog mobLog = MobLog.getInstance();
                                        mobLog.m11342d("[212] oncge" + objArr5[0], new Object[0]);
                                        if ((objArr5[0] instanceof List) && ((List) objArr5[0]).size() > 0) {
                                            List list = (List) objArr5[0];
                                            objArr[0] = list.get(list.size() - 1);
                                        } else {
                                            objArr[0] = objArr5[0];
                                        }
                                    }
                                } finally {
                                    try {
                                    } catch (Throwable th3) {
                                    }
                                }
                            }
                            countDownLatch.countDown();
                            return null;
                        }
                    });
                    hashMap.put("equals", new ReflectHelper.InterfaceC6184a<Object[], Object>() { // from class: com.mob.tools.a.i.3
                        @Override // com.mob.tools.utils.ReflectHelper.InterfaceC6184a
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public Object mo11056a(Object[] objArr5) {
                            if (objArr5 == null || objArr5[0] == null) {
                                return false;
                            }
                            return Boolean.valueOf(objArr5[0].hashCode() == identityHashCode);
                        }
                    });
                    hashMap.put(C5868q.m12203b("008gcAegCgIfidccb]e"), new ReflectHelper.InterfaceC6184a<Object[], Object>() { // from class: com.mob.tools.a.i.4
                        @Override // com.mob.tools.utils.ReflectHelper.InterfaceC6184a
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public Object mo11056a(Object[] objArr5) {
                            return Integer.valueOf(identityHashCode);
                        }
                    });
                    Object createProxy = ReflectHelper.createProxy((Map<String, ReflectHelper.InterfaceC6184a<Object[], Object>>) hashMap, Class.forName(C5868q.m12203b("033cd9cbcidcchcbecAfFdcRbchGchdc-d5ecebdcAbchKchdc:d=ebcheg5hedeAci")));
                    if (Build.VERSION.SDK_INT >= 30) {
                        m11640a = m11645a.m11640a(C5868q.m12203b("032cdLcbcidcchcbec%fSdcZbchMchdcLdHecebdc$bchOchdc=d2gbYcdc-ddWeTci") + "$" + C5868q.m12203b("0259ebdcPbchVchdcUdFebchegBhede)cidjci<cdMegSi3dcci:h"), new Class[]{Class.forName(C5868q.m12203b("032cd+cbcidcchcbecRf*dcQbch:chdc8dEecebdc=bchWchdcDdRgb@cdcZdd_e'ci")), Class.forName(C5868q.m12203b("033cd<cbcidcchcbecDfVdc2bchQchdc)dKecebdc$bchCchdcEdRebcheg1hedeOci"))}, new Object[]{C6152DH.SyncMtd.getSystemServiceSafe(C5868q.m12203b("008f)dcYbchEchdc)d")), createProxy});
                        m11645a.m11644a(m11640a.getClass(), m11640a, C5868q.m12203b("008Dci,eSddcheg%heSci"), new Class[]{Executor.class}, new Object[]{Executors.newSingleThreadExecutor()});
                    } else {
                        m11640a = m11645a.m11640a(C5868q.m12203b("032cd8cbcidcchcbecMf dc3bch7chdc=dXecebdcJbchVchdcXdUgb=cdcEdd+eRci") + "$" + C5868q.m12203b("0170ebcheg_hede%cidjci_cdHegAi;dcciMh"), new Class[]{Class.forName(C5868q.m12203b("032cd;cbcidcchcbecAf:dcVbchXchdcAd8ecebdcLbch9chdcHd'gbPcdcCddPeZci")), Class.forName(C5868q.m12203b("033cd?cbcidcchcbec:f+dc7bchAchdcAdNecebdcXbchGchdc_dCebchegJhedeFci")), Looper.class}, new Object[]{C6152DH.SyncMtd.getSystemServiceSafe(C5868q.m12203b("008f(dcEbchLchdcKd")), createProxy, C5731l.m12681a().m12670c()});
                    }
                    parcel2 = obtain;
                } catch (Throwable th3) {
                    th = th3;
                    parcel2 = obtain;
                    parcel.recycle();
                    parcel2.recycle();
                    C6040h.m11645a(context).m11639b(context);
                    throw th;
                }
                try {
                    parcel2.writeStrongBinder((IBinder) m11640a);
                    parcel2.writeInt(0);
                    parcel2.writeString(context.getPackageName());
                    c = 0;
                    iBinder.transact(((Integer) m11645a.m11641a(C5868q.m12203b("033cd?cbcidcchcbecJf.dc;bch3chdc,dTecdhebdc+bch+chdc@d7gbPcdc]dd.eXci") + "$" + C5868q.m12203b("004Rdi%hXcfed"), C5868q.m12203b("034Ldjfgdkdgdidkfidjdhfkdgcgci7eKcdcfWe9egIh:ebdc.bchJchdc)d,df,i=cb]che$eg"), (Object) null)).intValue(), parcel2, parcel, 0);
                } catch (Throwable th4) {
                    th = th4;
                    parcel.recycle();
                    parcel2.recycle();
                    C6040h.m11645a(context).m11639b(context);
                    throw th;
                }
            }
            parcel.readException();
            countDownLatch.await(j, TimeUnit.MILLISECONDS);
            Object obj = objArr[c];
            parcel.recycle();
            parcel2.recycle();
            C6040h.m11645a(context).m11639b(context);
            return obj;
        } catch (Throwable th5) {
            th = th5;
            parcel = obtain2;
            parcel2 = obtain;
        }
    }

    /* renamed from: com.mob.tools.a.i$a */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    public static class C6049a {

        /* renamed from: a */
        private final Object f14890a;

        public C6049a(Object obj) {
            this.f14890a = obj;
        }

        /* renamed from: a */
        public float m11627a() {
            return ((Float) ReflectHelper.invokeInstanceMethodNoThrow(this.f14890a, C5868q.m12203b("011;dd*ehTdkKbb'cfciNcb:cj"), Float.valueOf(0.0f), new Object[0])).floatValue();
        }

        /* renamed from: b */
        public double m11625b() {
            return ((Double) ReflectHelper.invokeInstanceMethodNoThrow(this.f14890a, C5868q.m12203b("011Ndd[eh8eb?ch4ch=hLcfcb5e"), Double.valueOf(0.0d), new Object[0])).doubleValue();
        }

        /* renamed from: c */
        public double m11624c() {
            return ((Double) ReflectHelper.invokeInstanceMethodNoThrow(this.f14890a, C5868q.m12203b("0125ddSeh:ebdc2dWddch3h2cfcbTe"), Double.valueOf(0.0d), new Object[0])).doubleValue();
        }

        /* renamed from: d */
        public long m11623d() {
            return ((Long) ReflectHelper.invokeInstanceMethodNoThrow(this.f14890a, C5868q.m12203b("0073dd+ehJdjchce?e"), 0L, new Object[0])).longValue();
        }

        /* renamed from: e */
        public String m11622e() {
            return (String) ReflectHelper.invokeInstanceMethodNoThrow(this.f14890a, C5868q.m12203b("011JddKehEfjcidcccchcb<e5ci"), null, new Object[0]);
        }

        /* renamed from: f */
        public double m11621f() {
            return ((Double) ReflectHelper.invokeInstanceMethodNoThrow(this.f14890a, C5868q.m12203b("011Jdd6eh%dk=fh@ch%hXcfcb8e"), Double.valueOf(0.0d), new Object[0])).doubleValue();
        }

        /* renamed from: g */
        public float m11620g() {
            return ((Float) ReflectHelper.invokeInstanceMethodNoThrow(this.f14890a, C5868q.m12203b("0109dd*ehCehLec[cich=d+dd"), Float.valueOf(0.0f), new Object[0])).floatValue();
        }

        /* renamed from: h */
        public float m11619h() {
            return ((Float) ReflectHelper.invokeInstanceMethodNoThrow(this.f14890a, C5868q.m12203b("008Tdd.ehAdi'iee+cb"), Float.valueOf(0.0f), new Object[0])).floatValue();
        }

        /* renamed from: i */
        public boolean m11618i() {
            if (Build.VERSION.SDK_INT >= 26) {
                return ((Boolean) ReflectHelper.invokeInstanceMethodNoThrow(this.f14890a, C5868q.m12203b("019gcPegfh_e+ci[h6ch?bcf(dk5bb?cfciEcbAcj"), false, new Object[0])).booleanValue();
            }
            return false;
        }

        /* renamed from: j */
        public float m11617j() {
            if (Build.VERSION.SDK_INT >= 26) {
                return ((Float) ReflectHelper.invokeInstanceMethodNoThrow(this.f14890a, C5868q.m12203b("025?dd$eh'fhLeLci3hXchGbcfKdkNbb*cfci+cbUcjgbHeheQcieg"), Float.valueOf(0.0f), new Object[0])).floatValue();
            }
            return 0.0f;
        }

        /* renamed from: a */
        public float m11626a(Object obj) {
            if (obj != null) {
                try {
                    return ((Float) ReflectHelper.invokeInstanceMethod(this.f14890a, C5868q.m12203b("010CcbchegJhcdbeTdjdc"), new Object[]{obj}, new Class[]{Class.forName(C5868q.m12203b("025cd<cbcidcchcbec5fMdcPbch-chdc>dWecebdc%bchKchdc$d"))})).floatValue();
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                }
            }
            return 0.0f;
        }
    }
}
