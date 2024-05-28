package com.p281qq.p282e.ads.nativ;

import android.content.Context;
import android.text.TextUtils;
import com.p281qq.p282e.ads.NativeAbstractAD;
import com.p281qq.p282e.comm.adevent.ADEvent;
import com.p281qq.p282e.comm.adevent.ADListener;
import com.p281qq.p282e.comm.constants.LoadAdParams;
import com.p281qq.p282e.comm.p283pi.NUADI;
import com.p281qq.p282e.comm.p283pi.POFactory;
import com.p281qq.p282e.comm.util.AdErrorConvertor;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.qq.e.ads.nativ.NativeUnifiedAD */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NativeUnifiedAD extends NativeAbstractAD<NUADI> {

    /* renamed from: g */
    private AdListenerAdapter f17841g;

    /* renamed from: h */
    private NativeADUnifiedListener f17842h;

    /* renamed from: i */
    private List<Integer> f17843i = new ArrayList();

    /* renamed from: j */
    private List<String> f17844j;

    /* renamed from: k */
    private volatile int f17845k;

    /* renamed from: l */
    private volatile int f17846l;

    /* renamed from: m */
    private String f17847m;

    /* renamed from: n */
    private LoadAdParams f17848n;

    /* renamed from: com.qq.e.ads.nativ.NativeUnifiedAD$AdListenerAdapter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static class AdListenerAdapter implements ADListener {

        /* renamed from: a */
        private NativeADUnifiedListener f17849a;

        public AdListenerAdapter(NativeADUnifiedListener nativeADUnifiedListener) {
            this.f17849a = nativeADUnifiedListener;
        }

        @Override // com.p281qq.p282e.comm.adevent.ADListener
        public void onADEvent(ADEvent aDEvent) {
            Integer num;
            if (this.f17849a != null) {
                int type = aDEvent.getType();
                if (type != 100) {
                    if (type == 101 && (num = (Integer) aDEvent.getParam(Integer.class)) != null) {
                        this.f17849a.onNoAD(AdErrorConvertor.formatErrorCode(num.intValue()));
                        return;
                    }
                    return;
                }
                List<NativeUnifiedADData> list = (List) aDEvent.getParam(List.class);
                if (list == null || list.size() <= 0) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (NativeUnifiedADData nativeUnifiedADData : list) {
                    arrayList.add(new NativeUnifiedADDataAdapter(nativeUnifiedADData));
                }
                this.f17849a.onADLoaded(arrayList);
            }
        }
    }

    public NativeUnifiedAD(Context context, String str, NativeADUnifiedListener nativeADUnifiedListener) {
        this.f17842h = nativeADUnifiedListener;
        this.f17841g = new AdListenerAdapter(nativeADUnifiedListener);
        m8344a(context, str);
    }

    public NativeUnifiedAD(Context context, String str, NativeADUnifiedListener nativeADUnifiedListener, String str2) {
        this.f17842h = nativeADUnifiedListener;
        this.f17841g = new AdListenerAdapter(nativeADUnifiedListener);
        m8343a(context, str, str2);
    }

    /* renamed from: a */
    private void m8306a(int i, boolean z) {
        if (m8346a()) {
            if (!m8339b()) {
                if (z) {
                    this.f17843i.add(Integer.valueOf(i));
                    return;
                }
                return;
            }
            T t = this.f17738a;
            if (t != 0) {
                LoadAdParams loadAdParams = this.f17848n;
                if (loadAdParams != null) {
                    ((NUADI) t).loadData(i, loadAdParams);
                } else {
                    ((NUADI) t).loadData(i);
                }
            }
        }
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public Object mo8297a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return pOFactory.getNativeAdManagerDelegate(context, str, str2, str3, this.f17841g);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p281qq.p282e.ads.NativeAbstractAD, com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo8294a(NUADI nuadi) {
        super.mo8305a((NativeUnifiedAD) nuadi);
        nuadi.setMinVideoDuration(this.f17845k);
        nuadi.setMaxVideoDuration(this.f17846l);
        nuadi.setVastClassName(this.f17847m);
        List<String> list = this.f17844j;
        if (list != null) {
            setCategories(list);
        }
        for (Integer num : this.f17843i) {
            m8306a(num.intValue(), false);
        }
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: b */
    public void mo8292b(int i) {
        NativeADUnifiedListener nativeADUnifiedListener = this.f17842h;
        if (nativeADUnifiedListener != null) {
            nativeADUnifiedListener.onNoAD(AdErrorConvertor.formatErrorCode(i));
        }
    }

    public String getAdNetWorkName() {
        T t = this.f17738a;
        if (t != 0) {
            return ((NUADI) t).getAdNetWorkName();
        }
        m8340a("getAdNetWorkName");
        return null;
    }

    public void loadData(int i) {
        m8306a(i, true);
    }

    public void loadData(int i, LoadAdParams loadAdParams) {
        this.f17848n = loadAdParams;
        loadData(i);
    }

    public void setCategories(List<String> list) {
        this.f17844j = list;
        T t = this.f17738a;
        if (t == 0 || list == null) {
            return;
        }
        ((NUADI) t).setCategories(list);
    }

    public void setMaxVideoDuration(int i) {
        this.f17846l = i;
        if (this.f17846l > 0 && this.f17845k > this.f17846l) {
            GDTLogger.m8234e("maxVideoDuration 设置值非法，不得小于minVideoDuration");
        }
        T t = this.f17738a;
        if (t != 0) {
            ((NUADI) t).setMaxVideoDuration(this.f17846l);
        }
    }

    public void setMinVideoDuration(int i) {
        this.f17845k = i;
        if (this.f17846l > 0 && this.f17845k > this.f17846l) {
            GDTLogger.m8234e("minVideoDuration 设置值非法，不得大于maxVideoDuration");
        }
        T t = this.f17738a;
        if (t != 0) {
            ((NUADI) t).setMinVideoDuration(this.f17845k);
        }
    }

    public void setVastClassName(String str) {
        if (TextUtils.isEmpty(str)) {
            GDTLogger.m8234e("Vast class name 不能为空");
            return;
        }
        this.f17847m = str;
        T t = this.f17738a;
        if (t != 0) {
            ((NUADI) t).setVastClassName(str);
        }
    }
}
